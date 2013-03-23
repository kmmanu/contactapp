package org.manu.contactapp.web.form;

import java.util.List;

import org.manu.contactapp.domain.Contact;

public class ContactGrid {

	private int totalPages;
	private int currentPage;
	private long totalRecords;
	private List<Contact> contactData;

	public int getTotalPages() {
		return this.totalPages;
	}

	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecords() {
		return this.totalRecords;
	}

	public void setTotalRecords(final long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Contact> getContactData() {
		return this.contactData;
	}

	public void setContactData(final List<Contact> contactData) {
		this.contactData = contactData;
	}

}
