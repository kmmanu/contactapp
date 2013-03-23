package org.manu.contactapp.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.manu.contactapp.domain.Contact;
import org.manu.contactapp.service.ContactService;
import org.manu.contactapp.web.form.ContactGrid;
import org.manu.contactapp.web.form.Message;
import org.manu.contactapp.web.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(ContactController.class);
	@Autowired
	private ContactService contactService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = GET)
	public String list(final Model uiModel) {
		this.logger.info("Listing contacts...");

		final List<Contact> contacts = this.contactService.findAll();

		uiModel.addAttribute("contacts", contacts);
		this.logger.info("No of contacts = " + contacts.size());

		return "contacts/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") final Long id, final Model uiModel) {
		this.logger.info("List contact with id " + id);
		final Contact contact = this.contactService.findById(id);
		uiModel.addAttribute("contact", contact);
		return "contacts/show";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid final Contact contact,
			final BindingResult bindingResult, final Model uiModel,
			final HttpServletRequest httpServletRequest,
			final RedirectAttributes redirectAttributes, final Locale locale) {

		this.logger.info("Updating contact...");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", this.messageSource.getMessage(
							"contact_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("contact", contact);
			return "contacts/update";
		}

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", this.messageSource.getMessage(
						"contact_save_success", new Object[] {}, locale)));

		this.contactService.save(contact);
		return "redirect:/contacts/"
				+ UrlUtil.encodeUrlPathSegment(contact.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") final Long id,
			final Model uiModel) {
		uiModel.addAttribute("contact", this.contactService.findById(id));
		return "contacts/update";
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(final Model uiModel) {
		final Contact contact = new Contact();
		uiModel.addAttribute("contact", contact);
		return "contacts/create";
	}

	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid final Contact contact,
			final BindingResult bindingResult, final Model uiModel,
			final HttpServletRequest httpServletRequest,
			final RedirectAttributes redirectAttributes, final Locale locale) {
		this.logger.info("Create contact..");

		if (bindingResult.hasErrors()) {
			uiModel.addAttribute(
					"message",
					new Message("error", this.messageSource.getMessage(
							"contact_save_fail", new Object[] {}, locale)));
			uiModel.addAttribute("contact", contact);
			return "contact/create";
		}

		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", this.messageSource.getMessage(
						"contact_save_success", new Object[] {}, locale)));
		this.logger.info("Contact id = " + contact.getId());
		this.contactService.save(contact);
		return "redirect:/contacts/"
				+ UrlUtil.encodeUrlPathSegment(contact.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ContactGrid listGrid(
			@RequestParam(value = "page", required = false) final Integer page,
			@RequestParam(value = "rows", required = false) final Integer rows,
			@RequestParam(value = "sidx", required = false) final String sortBy,
			@RequestParam(value = "sord", required = false) final String order) {
		Sort sort = null;
		String orderBy = sortBy;

		if ("birthDateString".equals(orderBy)) {
			orderBy = "birthDate";
		}

		if (orderBy != null && order != null) {
			if ("desc".equals(orderBy)) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else {
				sort = new Sort(Sort.Direction.ASC, orderBy);
			}
		}

		// Page request for current page.
		// Page no. for spring data jpa starts with 0. for jqgrid its 1.
		PageRequest pageRequest = null;
		if (sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page, rows);
		}

		final Page<Contact> contactPage = this.contactService
				.findAllByPage(pageRequest);

		final ContactGrid contactGrid = createContactGrid(contactPage);

		return contactGrid;
	}

	private ContactGrid createContactGrid(final Page<Contact> contactPage) {
		final ContactGrid contactGrid = new ContactGrid();
		contactGrid.setCurrentPage(contactPage.getNumber() + 1);
		contactGrid.setTotalPages(contactPage.getTotalPages());
		contactGrid.setTotalRecords(contactPage.getTotalElements());
		contactGrid.setContactData(Lists.newArrayList(contactPage.iterator()));
		return contactGrid;
	}
}
