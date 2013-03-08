package com.faxius.contactmanager.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faxius.contactmanager.core.IContactManager;

@Controller
public class ContactsController {
	private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);
	
	@Autowired
	private IContactManager contactManager;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String showContacts(Model model) {
		logger.info("Retrieving the list of contacts");		
		model.addAttribute("serverTime", contactManager.getAll());		
		return "contacts";
	}
}
