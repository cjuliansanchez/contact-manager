package com.faxius.contactmanager.core;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.faxius.contactmanager.domain.Contact;

public class ContactManager implements IContactManager {
	
	private SessionFactory sessionFactory;	

	@SuppressWarnings("unchecked")
	public List<Contact> getAll() {
		return (List<Contact>)getCurrentSession().createCriteria(Contact.class)
				.list();
	}
	
	@Override
	public void Save(Contact contact) {
		 getCurrentSession().save(contact);
	}
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
}
