package shoestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import shoestore.constant.SystemConstant;
import shoestore.convert.UserConverter;
import shoestore.dto.UserDTO;
import shoestore.entity.UserEntity;
import shoestore.repository.UserRepository;
import shoestore.service.IUserService;

@Service
public class UserService implements IUserService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserConverter userConverter;
	
	@Override
	public boolean checkLogin(UserDTO userDTO) {
		UserEntity userEntity=userRepository.findOneByUserNameAndStatus(userDTO.getUsername(),SystemConstant.ACTIVE_STATUS);
		if(userEntity!=null) {
			return BCrypt.checkpw(userDTO.getPassword(), userEntity.getPassword());
		}
		return false;
	}

	@Override
	public UserDTO loadUserByUsername(String username) {
		UserEntity userEntity=userRepository.findByUserName(username);
		return userConverter.convertToDTO(userEntity);
	}

}
