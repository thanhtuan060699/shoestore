package shoestore.service;

import java.util.List;

import shoestore.dto.CartDTO;
import shoestore.dto.OrderDTO;
import shoestore.dto.UserDTO;

public interface IOrderService {
	public void addNewOrder(UserDTO userDTO,OrderDTO orderDTO,List<CartDTO> cartDTOs);
}
