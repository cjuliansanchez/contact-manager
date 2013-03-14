package com.faxius.contactmanager.web;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faxius.contactmanager.core.IContactManager;
import com.faxius.contactmanager.domain.Contact;

@Controller
public class ContactsController {
	private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);
	
	@Autowired
	private IContactManager contactManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	@Transactional
	public String showContacts(Model model) {
		logger.info("Retrieving the list of contacts");		
		model.addAttribute("contacts", contactManager.getAll());		
		return "contacts";
	}
	
	@RequestMapping(value = "/contacts/new", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);		
		return "createOrUpdateContact";
	}
	
	@RequestMapping(value = "/contacts/new", method = RequestMethod.POST)
	public String saveNewContact(@Valid Contact contact, BindingResult result) {
		if(result.hasErrors()){
			return "createOrUpdateContact";
		}
		
		logger.info("Saving a new contact " + contact);
		contactManager.Save(contact);
		logger.info(contact + "was saved");
		return "redirect:/contacts";
	}
}
