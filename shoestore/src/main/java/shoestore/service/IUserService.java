package shoestore.service;

import shoestore.dto.UserDTO;

public interface IUserService {
	public boolean checkLogin(UserDTO userDTO);
	public UserDTO loadUserByUsername(String username);
}
