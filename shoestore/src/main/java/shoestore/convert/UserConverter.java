package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.UserDTO;
import shoestore.entity.UserEntity;

@Component
public class UserConverter {
	public UserDTO convertToDTO(UserEntity userEntity) {
		ModelMapper modelMapper=new ModelMapper();
		UserDTO userDTO=modelMapper.map(userEntity, UserDTO.class);
		return userDTO;
	}
	public UserEntity convertToEntity(UserDTO userDTO) {
		ModelMapper modelMapper=new ModelMapper();
		UserEntity userEntity=modelMapper.map(userDTO,UserEntity.class);
		return userEntity;
		
	}
}
