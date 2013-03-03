package org.manu.contactapp.repository;

import org.manu.contactapp.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
