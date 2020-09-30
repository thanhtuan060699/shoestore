package shoestore.service;

import java.util.Map;

import shoestore.dto.PaymentRequestDTO;

public interface IPaymentRequestService {
	public String hashAllFields(Map fields);
	public PaymentRequestDTO addNewPaymentRequest(PaymentRequestDTO paymentRequestDTO);
}
