package org.manu.contactapp.service;

import java.util.List;

import org.manu.contactapp.domain.Contact;
import org.manu.contactapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return Lists.newArrayList(this.contactRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Contact findById(final Long id) {
		return this.contactRepository.findOne(id);
	}

	@Override
	public Contact save(final Contact contact) {
		return this.contactRepository.save(contact);
	}

}
