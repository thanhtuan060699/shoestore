package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.OrderDTO;
import shoestore.entity.OrderEntity;

@Component
public class OrderConverter {
	public OrderDTO convertToDTO(OrderEntity orderEntity) {
		ModelMapper modelMapper=new ModelMapper();
		OrderDTO orderDTO=modelMapper.map(orderEntity, OrderDTO.class);
		return orderDTO;
	}
	public OrderEntity convertToEntity(OrderDTO orderDTO) {
		ModelMapper modelMapper =new ModelMapper();
		OrderEntity orderEntity=modelMapper.map(orderDTO, OrderEntity.class);
		return orderEntity;
	}
}
