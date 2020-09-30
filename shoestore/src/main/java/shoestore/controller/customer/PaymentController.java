package shoestore.controller.customer;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import shoestore.dto.PaymentResponseDTO;
import shoestore.service.impl.PaymentRequestService;
import shoestore.service.impl.PaymentResponseService;

@Controller
public class PaymentController {
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	PaymentResponseService paymentResponseService;
	
	@RequestMapping(value = "/karma/payment/onepay/result",method = RequestMethod.GET)
	public ModelAndView resultOnePay(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("web/paymentresult");
		Map fields = new HashMap();
	    for (Enumeration enum1 = request.getParameterNames(); enum1.hasMoreElements(); ) {
	        String fieldName = (String) enum1.nextElement();
	        String fieldValue = request.getParameter(fieldName);
	        if ((fieldValue != null) && (fieldValue.length() > 0)) {
	            fields.put(fieldName, fieldValue);
	        }
	    }
	    String vpc_Txn_Secure_Hash = paymentRequestService.null2unknown((String) fields.remove("vpc_SecureHash"));
	    String hashValidated = null;
	    if (fields.get("vpc_TxnResponseCode") != null || fields.get("vpc_TxnResponseCode") != "No Value Returned") {
	        String secureHash = paymentRequestService.hashAllFields(fields);
	        if (vpc_Txn_Secure_Hash.equalsIgnoreCase(secureHash)) {
	            hashValidated = "CORRECT";
	        } else {
	            hashValidated = "INVALID HASH";
	        }
	    } else {
	        hashValidated = "INVALID HASH";
	    }
	    PaymentResponseDTO paymentResponseDTO=new PaymentResponseDTO();
	    String vpcTxnResponseCode=paymentRequestService.null2unknown((String) fields.get("vpc_TxnResponseCode"));
	    String vpcTransactionNo=paymentRequestService.null2unknown((String) fields.get("vpc_TransactionNo"));
	    String vpcAmount=paymentRequestService.null2unknown((String) fields.get("vpc_Amount"));
	    String vpcOrderInfo=paymentRequestService.null2unknown((String) fields.get("vpc_OrderInfo"));
	    String transStatus = "";
	    //set payment response
	    paymentResponseDTO.setPurchaseAmount(Long.parseLong(vpcAmount)/100);
	    paymentResponseDTO.setOrderInfo(vpcOrderInfo);
	    paymentResponseDTO.setResponseCode(Integer.parseInt(vpcTxnResponseCode));
	    //save payment response
	    paymentResponseDTO=paymentResponseService.saveNewPaymentResponse(paymentResponseDTO);
	    
	    modelAndView.addObject("amount", Integer.parseInt(vpcAmount)/100);
	    modelAndView.addObject("transactionNo", vpcTransactionNo);
	    modelAndView.addObject("orderInfo", vpcOrderInfo);
	    
		return modelAndView;
	}
}
