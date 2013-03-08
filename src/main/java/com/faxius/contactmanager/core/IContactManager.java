package com.faxius.contactmanager.core;

import java.util.List;

import com.faxius.contactmanager.domain.Contact;

public interface IContactManager {
	List<Contact> getAll();
	void Save(Contact contact);
}
