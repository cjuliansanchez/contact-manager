package com.faxius.contactmanager.domain;

import static junit.framework.Assert.*;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"domain-context.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class PhoneNumberTests {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test(expected = ConstraintViolationException.class)
	public void phoneTypeNotNull(){
		Session session = sessionFactory.getCurrentSession();		
		
		Contact contact = new Contact();
		contact.setName("Carlos");
		contact.setLastName("Sanchez");
		
		PhoneNumber number = new PhoneNumber();
		number.setNumber("4788-4433");
		
		contact.addPhoneNumber(number);
		session.save(contact);		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void contactNotNull(){
		Session session = sessionFactory.getCurrentSession();				
		PhoneNumber number = new PhoneNumber();
		number.setNumber("4788-4433");
		number.setType(PhoneType.HOME);		
		session.save(number);
	}
	
	@Test
	public void canSavePhoneNumber(){
		Session session = sessionFactory.getCurrentSession();		
		
		Contact contact = new Contact();
		contact.setName("Carlos");
		contact.setLastName("Sanchez");
		
		PhoneNumber number = new PhoneNumber();
		number.setNumber("4788-4433");
		number.setType(PhoneType.HOME);
		
		contact.addPhoneNumber(number);
		session.save(contact);
		
		session.flush();		
		session.clear();
		
		Contact dbContact01 = (Contact)session.get(Contact.class, contact.getId());
		assertNotNull(dbContact01);
		assertEquals(1, dbContact01.getPhoneNumbers().size());
		assertTrue("the contact owns the number", dbContact01.getPhoneNumbers().contains(number));
	}

}
