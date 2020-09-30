package shoestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.PaymentResponseConverter;
import shoestore.dto.PaymentResponseDTO;
import shoestore.entity.PaymentResponseEntity;
import shoestore.repository.PaymentResponseRepository;
import shoestore.service.IPaymentResponseService;

@Service
public class PaymentResponseService implements IPaymentResponseService {

	@Autowired
	PaymentResponseRepository paymentResponseRepository;
	
	@Autowired
	PaymentResponseConverter paymentResponseConverter;
	
	@Override
	public PaymentResponseDTO saveNewPaymentResponse(PaymentResponseDTO paymentResponseDTO) {
		PaymentResponseEntity paymentResponseEntity=paymentResponseConverter.convertToEntity(paymentResponseDTO);
		paymentResponseEntity=paymentResponseRepository.save(paymentResponseEntity);
		return paymentResponseConverter.convertToDTO(paymentResponseEntity);
	}

}
