package shoestore.service;

import shoestore.dto.ContactDTO;

public interface IContactService {
	public void addContact(ContactDTO contactDTO,String username);
	public boolean existedContact(ContactDTO contactDTO,String username);
}
