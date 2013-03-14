package com.faxius.contactmanager.core;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.faxius.contactmanager.domain.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"core-context.xml"})
public class ContactManagerTest {
	@Autowired
	private IContactManager contactManager;
	
	@Test	
	@Transactional
	public void canSaveContact(){	
		Contact contact = new Contact();
		contact.setName("Carlos");		
		contact.setLastName("Sanchez");
		contactManager.Save(contact);
		
		List<Contact> contacts = contactManager.getAll();
		Assert.assertNotNull(contacts);
		Assert.assertEquals(contacts.size(), 1);
		Assert.assertEquals(contacts.get(0), contact);
	}	
}
