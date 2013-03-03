package org.manu.contactapp.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.manu.contactapp.domain.Contact;
import org.manu.contactapp.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(ContactController.class);
	@Autowired
	private ContactService contactService;

	@RequestMapping(method = GET)
	public String list(final Model uiModel) {
		this.logger.info("Listing contacts...");

		final List<Contact> contacts = this.contactService.findAll();

		uiModel.addAttribute("contacts", contacts);
		this.logger.info("No of contacts = " + contacts.size());

		return "contacts/list";
	}
}
