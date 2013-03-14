package com.faxius.contactmanager.web;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.faxius.contactmanager.core.IContactManager;
import com.faxius.contactmanager.domain.Contact;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"classpath:spring/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ContactsControllerTest {	
	
	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	protected IContactManager contactManager;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();		
	}
	
	@Before
	public void initData() {
		/* Alalala */
		Contact contact = new Contact();
		contact.setName("Charly");
		this.contactManager.Save(contact);		
	}
	
	/* First changes */
	@Test
	public void showContacts() throws Exception {
		ResultActions result = this.mockMvc.perform(get("/contacts"));
		result.andExpect(model().attributeExists("contacts"))
			.andExpect(new ResultMatcher() {
			
			@Override
			public void match(MvcResult result) throws Exception {
				ModelAndView mav = result.getModelAndView();
				@SuppressWarnings("unchecked")
				List<Contact> contacts = (List<Contact>)mav.getModel().get("contacts");
				Assert.assertEquals(1, contacts.size());
				Assert.assertEquals("Charly", contacts.get(0).getName());
			}
		});
	}
}
