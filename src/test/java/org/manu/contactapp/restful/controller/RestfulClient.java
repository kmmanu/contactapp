package org.manu.contactapp.restful.controller;

import org.manu.contactapp.domain.Contact;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class RestfulClient {
	private static final String RESTFUL_URL = "http://localhost:8080/contactapp/restful/contact";
	private static final String URL_GET_CONTACT_BY_ID = RESTFUL_URL + "/{id}";

	public static void main(final String[] args) {
		final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:Test-restful-client-app-context.xml");
		ctx.refresh();

		final RestTemplate restTemplate = ctx.getBean("restTemplate",
				RestTemplate.class);
		final Contact contact = restTemplate.getForObject(
				URL_GET_CONTACT_BY_ID, Contact.class, 1);
		System.out.println(contact);

	}
}
