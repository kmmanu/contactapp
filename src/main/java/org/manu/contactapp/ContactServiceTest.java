package org.manu.contactapp;

import java.util.List;

import org.manu.contactapp.domain.Contact;
import org.manu.contactapp.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ContactServiceTest {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();

		System.out.println("App context initialized successfully");

		final ContactService contactService = ctx.getBean("contactService",
				ContactService.class);

		final List<Contact> contacts = contactService.findAll();

		for (final Contact contact : contacts) {
			System.out.println(contact);
		}

	}

}
