package shoestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.OrderConverter;
import shoestore.convert.PaymentRequestConverter;
import shoestore.convert.PaymentResponseConverter;
import shoestore.dto.CartDTO;
import shoestore.dto.OrderDTO;
import shoestore.dto.PaymentRequestDTO;
import shoestore.dto.PaymentResponseDTO;
import shoestore.dto.UserDTO;
import shoestore.entity.OrderDetailEntity;
import shoestore.entity.OrderEntity;
import shoestore.entity.PaymentRequestEntity;
import shoestore.entity.PaymentResponseEntity;
import shoestore.entity.ProductAttributeEntity;
import shoestore.entity.UserEntity;
import shoestore.repository.OrderDetailRepository;
import shoestore.repository.OrderRepository;
import shoestore.repository.ProductAttributeRepository;
import shoestore.repository.UserRepository;
import shoestore.service.IOrderService;

@Service
public class OrderService implements IOrderService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderConverter orderConverter;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository orderDetailReposity;
	
	@Autowired
	ProductAttributeRepository productAttributeRepository;
	
	@Autowired
	PaymentRequestConverter paymentRequestConverter;
	
	@Autowired
	PaymentResponseConverter paymentResponseConverter;
	
	@Override
	public OrderDTO addNewOrder(UserDTO userDTO, OrderDTO orderDTO, List<CartDTO> cartDTOs) {
		UserEntity userEntity=userRepository.findOne(userDTO.getId());
		// add table order
		OrderEntity orderEntity=orderConverter.convertToEntity(orderDTO);
		orderEntity.setUserEntity(userEntity);
		orderEntity.setAmount(sumQuantity(cartDTOs));
		orderEntity.setTotalPrice(sumPrice(cartDTOs));
		orderEntity.setPaymentStatus("PENDING");
		orderEntity=orderRepository.save(orderEntity);
		//add table orderdetail
		for(CartDTO cartDTO: cartDTOs) {
			OrderDetailEntity  orderDetailEntity=new OrderDetailEntity();
			orderDetailEntity.setSneakerName(cartDTO.getName());
			orderDetailEntity.setPrice(cartDTO.getPrice());
			orderDetailEntity.setAmount(cartDTO.getQuantity());
			orderDetailEntity.setSize(cartDTO.getSize());
			orderDetailEntity.setColor(cartDTO.getColor());
			orderDetailEntity.setOrderEntity(orderEntity);
			orderDetailReposity.save(orderDetailEntity);
		}
		return orderConverter.convertToDTO(orderEntity);
	}
	
	private Integer sumQuantity(List<CartDTO> cartDTOs) {
		Integer sumQuantity=0;
		for(CartDTO cartDTO:cartDTOs) {
			sumQuantity=sumQuantity+cartDTO.getQuantity();
		}
		return sumQuantity;
	}
	
	private Long sumPrice(List<CartDTO> cartDTOs) {
		Long sumPrice=(long) 0;
		for(CartDTO cartDTO:cartDTOs) {
			sumPrice=sumPrice+cartDTO.getPrice();
		}
		return sumPrice;
	}

	@Override
	public void updatePaymentRequest(OrderDTO orderDTO, PaymentRequestDTO paymentRequestDTO) {
		OrderEntity orderEntity=orderConverter.convertToEntity(orderDTO);
		PaymentRequestEntity paymentRequestEntity=paymentRequestConverter.convertToEntity(paymentRequestDTO);
		orderEntity.setPaymentRequestEntity(paymentRequestEntity);
		orderRepository.save(orderEntity);
		
	}

	@Override
	public void updatePaymentResponse(Long orderId, PaymentResponseDTO paymentResponseDTO) {
		OrderEntity orderEntity=orderRepository.findOne(orderId);
		PaymentResponseEntity paymentResponseEntity=paymentResponseConverter.convertToEntity(paymentResponseDTO);
		orderEntity.setPaymentResponseEntity(paymentResponseEntity);
		orderRepository.save(orderEntity);
		
	}

	@Override
	public void updatePaymentStatus(Long orderId) {
		OrderEntity orderEntity=orderRepository.findOne(orderId);
		orderEntity.setPaymentStatus("SUCCESS");
		orderRepository.save(orderEntity);
		
	}

}
