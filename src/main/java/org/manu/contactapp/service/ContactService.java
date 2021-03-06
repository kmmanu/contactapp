package org.manu.contactapp.service;

import java.util.List;

import org.manu.contactapp.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

	public List<Contact> findAll();

	public Contact findById(Long id);

	public Contact save(Contact contact);

	public Page<Contact> findAllByPage(Pageable pageable);

}
