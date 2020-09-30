package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.PaymentResponseDTO;
import shoestore.entity.PaymentResponseEntity;

@Component
public class PaymentResponseConverter {
	public PaymentResponseDTO convertToDTO(PaymentResponseEntity paymentResponseEntity) {
		ModelMapper modelMapper=new ModelMapper();
		PaymentResponseDTO paymentResponseDTO=modelMapper.map(paymentResponseEntity, PaymentResponseDTO.class);
		return paymentResponseDTO;
	}
	public PaymentResponseEntity convertToEntity(PaymentResponseDTO paymentResponseDTO) {
		ModelMapper modelMapper =new ModelMapper();
		PaymentResponseEntity paymentResponseEntity=modelMapper.map(paymentResponseDTO, PaymentResponseEntity.class);
		return paymentResponseEntity;
	}
}
