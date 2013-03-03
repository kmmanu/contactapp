/**
 * Created on Dec 21, 2011
 */
package org.manu.contactapp.service;

import java.util.List;

import org.manu.contactapp.domain.Contact;

public interface ContactService {

	public List<Contact> findAll();

	public Contact findById(Long id);

	public Contact save(Contact contact);

	// public Page<Contact> findAllByPage(Pageable pageable);

}
