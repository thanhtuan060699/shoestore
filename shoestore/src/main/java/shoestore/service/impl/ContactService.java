package shoestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.ContactConverter;
import shoestore.dto.ContactDTO;
import shoestore.entity.ContactEntity;
import shoestore.entity.UserEntity;
import shoestore.repository.ContactRepository;
import shoestore.repository.UserRepository;
import shoestore.service.IContactService;

@Service
public class ContactService implements IContactService{
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ContactConverter contactConverter;
	
	@Override
	public void addContact(ContactDTO contactDTO, String username) {
		UserEntity userEntity=userRepository.findByUserName(username);
		ContactEntity contactEntity=contactConverter.convertToEntity(contactDTO);
		contactEntity.setUserEntity(userEntity);
		contactRepository.save(contactEntity);
		
	}

	@Override
	public boolean existedContact(ContactDTO contactDTO, String username) {
		UserEntity userEntity=userRepository.findByUserName(username);
		ContactEntity contactEntity=contactRepository.findByColorAndShoeNameAndSizeAndUserEntity(contactDTO.getColor()
				,contactDTO.getShoeName(),contactDTO.getSize(), userEntity);
		if(contactEntity!=null)
			return true;
		return false;
	}

}
