package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.ContactDTO;
import shoestore.entity.ContactEntity;

@Component
public class ContactConverter {
	public ContactDTO convertToDTO(ContactEntity contactEntity) {
		ModelMapper modelMapper=new ModelMapper();
		ContactDTO contactDTO=modelMapper.map(contactEntity, ContactDTO.class);
		return contactDTO;
	}
	public ContactEntity convertToEntity(ContactDTO contactDTO) {
		ModelMapper modelMapper =new ModelMapper();
		ContactEntity contactEntity=modelMapper.map(contactDTO, ContactEntity.class);
		return contactEntity;
	}
}
