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

import shoestore.constant.PaymentConstant;
import shoestore.dto.PaymentResponseDTO;
import shoestore.service.impl.OrderService;
import shoestore.service.impl.PaymentRequestService;
import shoestore.service.impl.PaymentResponseService;
import shoestore.util.SessionUtil;

@Controller
public class PaymentController {
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@Autowired
	PaymentResponseService paymentResponseService;
	
	@Autowired
	OrderService orderService;
	
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
	        String secureHash = paymentRequestService.hashAllFields(fields,PaymentConstant.SECURE_SECRET);
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
	    String vpcMessage=paymentRequestService.null2unknown((String) fields.get("vcp_Message"));
	    String transStatus = "";
	    //set payment response
	    paymentResponseDTO.setResponseCode(vpcTxnResponseCode);
	    paymentResponseDTO.setOrderInfo(vpcOrderInfo);
	    paymentResponseDTO.setTransStatus(vpcTransactionNo);
	    paymentResponseDTO.setMessage(vpcMessage);
	    paymentResponseDTO.setTypeCard("ATM");
	    paymentResponseDTO.setPurchaseAmount(Long.parseLong(vpcAmount)/100);
	    //save payment response
	    paymentResponseDTO=paymentResponseService.saveNewPaymentResponse(paymentResponseDTO);
	    //update table order
	    orderService.updatePaymentResponse(Long.parseLong(vpcOrderInfo), paymentResponseDTO);
	    
	    modelAndView.addObject("amount", Integer.parseInt(vpcAmount)/100);
	    modelAndView.addObject("transactionNo", vpcTransactionNo);
	    modelAndView.addObject("orderInfo", vpcOrderInfo);
	    SessionUtil.getInstance().removeValue(request, "cart");
	    SessionUtil.getInstance().removeValue(request, "amounts");
	    
		return modelAndView;
	}
	@RequestMapping(value = "/karma/payment/onepay/world/result",method = RequestMethod.GET)
	public ModelAndView onePayWorld(HttpServletRequest request) {
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
	        String secureHash = paymentRequestService.hashAllFields(fields,PaymentConstant.SECURE_SECRET);
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
	    String vpcOrderInfo=paymentRequestService.null2unknown((String) fields.get("vpc_OrderInfo"));
	    String vpcAmount=paymentRequestService.null2unknown((String) fields.get("vpc_Amount"));
	    String vpcTransactionNo=paymentRequestService.null2unknown((String) fields.get("vpc_TransactionNo"));
	    String vpcMessage=paymentRequestService.null2unknown((String) fields.get("vpc_Message"));
	    String vpcCard=paymentRequestService.null2unknown((String) fields.get("vpc_Card"));
	    //set payment response
	    paymentResponseDTO.setResponseCode(vpcTxnResponseCode);
	    paymentResponseDTO.setOrderInfo(vpcOrderInfo);
	    paymentResponseDTO.setTransStatus(vpcTransactionNo);
	    paymentResponseDTO.setMessage(vpcMessage);
	    paymentResponseDTO.setTypeCard(vpcCard);
	    paymentResponseDTO.setPurchaseAmount(Long.parseLong(vpcAmount)/100);
	    //save payment response
	    paymentResponseDTO=paymentResponseService.saveNewPaymentResponse(paymentResponseDTO);
	    //update table order
	    orderService.updatePaymentResponse(Long.parseLong(vpcOrderInfo), paymentResponseDTO);
	    
	    modelAndView.addObject("amount", Integer.parseInt(vpcAmount)/100);
	    modelAndView.addObject("transactionNo", vpcTransactionNo);
	    modelAndView.addObject("orderInfo", vpcOrderInfo);
	    SessionUtil.getInstance().removeValue(request, "cart");
	    SessionUtil.getInstance().removeValue(request, "amounts");
	    
		return modelAndView;
	}
}
