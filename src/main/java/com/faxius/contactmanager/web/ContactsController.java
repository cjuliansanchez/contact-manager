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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.faxius.contactmanager.core.IContactManager;
import com.faxius.contactmanager.domain.Contact;
import com.faxius.contactmanager.domain.ContactDTO;

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
		return "createContact";
	}
	
	@RequestMapping(value = "/contacts/new", method = RequestMethod.POST)
	public String saveNewContact(@Valid Contact contact, BindingResult result) {
		if(result.hasErrors()){
			return "createContact";
		}
		
		logger.info("Saving a new contact " + contact);
		contactManager.Save(contact);
		logger.info(contact + "was saved");
		return "redirect:/contacts";
	}
	
	@RequestMapping(value = "/contacts/edit", method = RequestMethod.GET)
	public ModelAndView editContact(@RequestParam String name, @RequestParam String lastName) {		
		logger.info("Going to edit contact. Name:  " + name + " LastName: " + lastName);		
		Contact contact = contactManager.Get(name, lastName);
		ModelAndView mav = new ModelAndView("editContact");
		mav.addObject("contactDTO", ContactDTO.build(contact));
		return mav;
	}
	
	@Transactional
	@RequestMapping(value = "/contacts/edit", method = RequestMethod.POST)	
	public String updateContact(@Valid ContactDTO contactDTO) throws Exception {		
		logger.info("Updating " + contactDTO);
		
		if(!contactDTO.getNewName().equals(contactDTO.getCurrentLastName()) ||
				!contactDTO.getNewLastName().equals(contactDTO.getCurrentName()))
		{
			Contact contact = this.contactManager.Get(contactDTO.getNewName(), contactDTO.getNewLastName());
			if(contact != null) {
				/* TODO: Handle this case properly */
				throw new Exception("Name and Last name in use");
			}
		}
		
		Contact contact = this.contactManager.Get(contactDTO.getCurrentName(), contactDTO.getCurrentLastName());
		if(contact == null) {
			/* TODO: Handle this case properly */
			throw new Exception("Contact not found");
		}
		
		contact.update(contactDTO);				
		return "redirect:/contacts";
	}
}
