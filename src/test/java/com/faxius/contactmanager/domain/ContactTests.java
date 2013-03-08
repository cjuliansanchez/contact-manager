package com.faxius.contactmanager.domain;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"domain-context.xml"})
public class ContactTests {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void canSaveContact(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Contact contact = new Contact();
		contact.setName("Carlos");
		session.save(contact);
		
		transaction.commit();		
		session.clear();
		
		Contact dbContact01 = (Contact)session.get(Contact.class, contact.getId());
		Assert.assertEquals(contact, dbContact01);
		session.clear();
		
		Contact dbContact02 = (Contact)session.createCriteria(Contact.class)
				.add(Restrictions.eq("name", "Carlos"))
				.uniqueResult();
		
		Assert.assertEquals(contact, dbContact02);
		session.clear();		
		
		session.close();
	}
}
