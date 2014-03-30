package org.manu.contactapp.restful.controller;

import org.manu.contactapp.domain.Contact;
import org.manu.contactapp.service.ContactService;
import org.manu.contactapp.web.controller.ContactController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/contact")
public class RestfulContactController {
	final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;

	// @RequestMapping(value = "/listdata", method = RequestMethod.GET)
	// @ResponseBody
	// public Contacts listData() {
	// return new Contacts(contactService.findAll());
	// }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Contact findContactById(@PathVariable final Long id) {
		return this.contactService.findById(id);
	}
}
