package shoestore.service;

import shoestore.dto.PaymentResponseDTO;

public interface IPaymentResponseService {
	public PaymentResponseDTO saveNewPaymentResponse(PaymentResponseDTO paymentResponseDTO);
}
