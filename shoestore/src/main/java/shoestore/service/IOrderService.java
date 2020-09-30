package shoestore.service;

import java.util.List;

import shoestore.dto.CartDTO;
import shoestore.dto.OrderDTO;
import shoestore.dto.PaymentRequestDTO;
import shoestore.dto.UserDTO;

public interface IOrderService {
	public OrderDTO addNewOrder(UserDTO userDTO,OrderDTO orderDTO,List<CartDTO> cartDTOs);
	public void updatePaymentRequest(OrderDTO orderDTO,PaymentRequestDTO paymentRequestDTO);
}
