package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.PaymentRequestDTO;
import shoestore.entity.PaymentRequestEntity;

@Component
public class PaymentRequestConverter {
	public PaymentRequestDTO convertToDTO(PaymentRequestEntity paymentRequestEntity) {
		ModelMapper modelMapper=new ModelMapper();
		PaymentRequestDTO paymentRequestDTO=modelMapper.map(paymentRequestEntity, PaymentRequestDTO.class);
		return paymentRequestDTO;
	}
	public PaymentRequestEntity convertToEntity(PaymentRequestDTO paymentRequestDTO) {
		ModelMapper modelMapper =new ModelMapper();
		PaymentRequestEntity paymentRequestEntity=modelMapper.map(paymentRequestDTO, PaymentRequestEntity.class);
		return paymentRequestEntity;
	}
}
