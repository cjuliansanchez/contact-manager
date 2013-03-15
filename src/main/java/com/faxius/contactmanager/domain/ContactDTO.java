package com.faxius.contactmanager.domain;

public class ContactDTO {
	private String currentName;
	private String currentLastName;
	private String newName;
	private String newLastName;
	
	public String getCurrentName() {
		return currentName;
	}
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
	public String getCurrentLastName() {
		return currentLastName;
	}
	public void setCurrentLastName(String currentLastName) {
		this.currentLastName = currentLastName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getNewLastName() {
		return newLastName;
	}
	public void setNewLastName(String newLastName) {
		this.newLastName = newLastName;
	}
	
	public static ContactDTO build(Contact contact)
	{
		ContactDTO dto = new ContactDTO();
		dto.setCurrentName(contact.getName());
		dto.setCurrentLastName(contact.getLastName());
		dto.setNewName(contact.getName());
		dto.setNewLastName(contact.getLastName());
		return dto;
	}
}
