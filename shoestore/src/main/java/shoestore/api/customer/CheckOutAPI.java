package shoestore.api.customer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoestore.constant.PaymentConstant;
import shoestore.dto.CartDTO;
import shoestore.dto.OrderDTO;
import shoestore.dto.PaymentRequestDTO;
import shoestore.dto.StatementDTO;
import shoestore.dto.UserDTO;
import shoestore.service.impl.OrderService;
import shoestore.service.impl.PaymentRequestService;
import shoestore.service.impl.UserService;
import shoestore.util.SessionUtil;
import shoestore.util.jwt.JwtProvider;

@RestController
public class CheckOutAPI {
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PaymentRequestService paymentRequestService;
	
	@RequestMapping(value = "/api/customer/order/add",method = RequestMethod.POST)
	public @ResponseBody StatementDTO addOrder(@RequestBody OrderDTO orderDTO,HttpServletRequest request) {
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		Cookie[] cookies=request.getCookies();
		String result=null;
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("TOKEN")) {
				result=cookie.getValue();
			}
		}
		String username=jwtProvider.getUsernameFromToken(result);
		UserDTO userDTO=userService.loadUserByUsername(username);
		String urlPayment=null;
		if(orderDTO.getMethodPayment().equals("onePay")) {
			orderDTO.setMethodPayment("ONEPAY");
			OrderDTO orderSave =orderService.addNewOrder(userDTO, orderDTO, cartDTOs);
			PaymentRequestDTO paymentRequestDTO=new PaymentRequestDTO();
			paymentRequestDTO.setIpAddress("192.168.31.74");
			paymentRequestDTO.setVpcVersion(PaymentConstant.VPC_VERSION);
			paymentRequestDTO.setVpcCurrency(PaymentConstant.VPC_CURRENCY);
			paymentRequestDTO.setVpcCommand(PaymentConstant.VPC_COMMAND);
			paymentRequestDTO.setVpcAccessCode(PaymentConstant.AccessCode);
			paymentRequestDTO.setVpcMerchant(PaymentConstant.Merchant);
			paymentRequestDTO.setVpcLocale(PaymentConstant.VPC_LOCALE);
			paymentRequestDTO.setReturnUrl(PaymentConstant.ReturnURL);
			paymentRequestDTO.setSecureSecret(PaymentConstant.SECURE_SECRET);
			Map<String, String> postData=new HashMap<String, String>();
			Date date=new Date();
			postData.put("SECURE_SECRET", PaymentConstant.SECURE_SECRET);
 	        postData.put("Title", PaymentConstant.TITLE);
 	        postData.put("vpc_Locale", PaymentConstant.VPC_LOCALE);
 	        postData.put("vpc_Version", PaymentConstant.VPC_VERSION);
 	        postData.put("vpc_Currency", PaymentConstant.VPC_CURRENCY);
 	        postData.put("vpc_Command", PaymentConstant.VPC_COMMAND);
 	        postData.put("vpc_Merchant", PaymentConstant.Merchant);
 	        postData.put("vpc_AccessCode", PaymentConstant.AccessCode);
 	        postData.put("vpc_MerchTxnRef",date.toString());
 	        postData.put("vpc_Amount", String.valueOf(sumTotalPrice(cartDTOs)*100));
 	        postData.put("vpc_ReturnURL", PaymentConstant.ReturnURL);
 	        postData.put("vpc_OrderInfo",String.valueOf(orderSave.getId()));
 	        postData.put("vpc_TicketNo", "192.168.31.74");
 	        postData.put("AgainLink", "http://onepay.vn");
 	        
			if(StringUtils.isNotEmpty(PaymentConstant.SECURE_SECRET)) {
				String secureHash=paymentRequestService.hashAllFields(postData,PaymentConstant.SECURE_SECRET);
				postData.put("vpc_SecureHash", secureHash);
				 StringBuffer url = new StringBuffer();
				url.append(PaymentConstant.VPCRequest).append("?");
				paymentRequestService.appendQueryFields(url, postData);
				urlPayment=url.toString();
			}
			//save payment request
			paymentRequestDTO=paymentRequestService.addNewPaymentRequest(paymentRequestDTO);
			//update payment request in order table
			orderService.updatePaymentRequest(orderSave, paymentRequestDTO);
		}
		//end of one pay
		if(orderDTO.getMethodPayment().equals("onePayWorld")) {
			orderDTO.setMethodPayment("ONEPAYWORLD");
			OrderDTO orderSave =orderService.addNewOrder(userDTO, orderDTO, cartDTOs);
			PaymentRequestDTO paymentRequestDTO=new PaymentRequestDTO();
			paymentRequestDTO.setIpAddress("192.168.31.74");
			paymentRequestDTO.setVpcVersion(PaymentConstant.VPC_VERSION);
			paymentRequestDTO.setVpcCurrency(PaymentConstant.VPC_CURRENCY);
			paymentRequestDTO.setVpcCommand(PaymentConstant.VPC_COMMAND);
			paymentRequestDTO.setVpcAccessCode(PaymentConstant.AccessCodeWorld);
			paymentRequestDTO.setVpcMerchant(PaymentConstant.MerchantWorld);
			paymentRequestDTO.setVpcLocale(PaymentConstant.VPC_LOCALE);
			paymentRequestDTO.setReturnUrl(PaymentConstant.ReturnURLWorld);
			paymentRequestDTO.setSecureSecret(PaymentConstant.SECURE_SECRET_WORLD);
			Map<String, String> postData=new HashMap<String, String>();
			Date date=new Date();
			postData.put("SECURE_SECRET", PaymentConstant.SECURE_SECRET_WORLD);
 	        postData.put("Title", PaymentConstant.TITLE);
 	        postData.put("vpc_Locale", PaymentConstant.VPC_LOCALE);
 	        postData.put("vpc_Version", PaymentConstant.VPC_VERSION);
 	        postData.put("vpc_Currency", PaymentConstant.VPC_CURRENCY);
 	        postData.put("vpc_Command", PaymentConstant.VPC_COMMAND);
 	        postData.put("vpc_Merchant", PaymentConstant.MerchantWorld);
 	        postData.put("vpc_AccessCode", PaymentConstant.AccessCodeWorld);
 	        postData.put("vpc_MerchTxnRef",date.toString());
 	        postData.put("vpc_Amount", String.valueOf(sumTotalPrice(cartDTOs)*100));
 	        postData.put("vpc_ReturnURL", PaymentConstant.ReturnURLWorld);
 	        postData.put("vpc_OrderInfo",String.valueOf(orderSave.getId()));
 	        postData.put("vpc_TicketNo", "192.168.31.74");
 	        postData.put("AgainLink", "http://onepay.vn");
 	        
			if(StringUtils.isNotEmpty(PaymentConstant.SECURE_SECRET_WORLD)) {
				String secureHash=paymentRequestService.hashAllFields(postData,PaymentConstant.SECURE_SECRET_WORLD);
				postData.put("vpc_SecureHash", secureHash);
				 StringBuffer url = new StringBuffer();
				url.append(PaymentConstant.VPCRequestWorld).append("?");
				paymentRequestService.appendQueryFields(url, postData);
				urlPayment=url.toString();
			}
			//save payment request
			paymentRequestDTO=paymentRequestService.addNewPaymentRequest(paymentRequestDTO);
			//update payment request in order table
			orderService.updatePaymentRequest(orderSave, paymentRequestDTO);
		}
		StatementDTO statementDTO=new StatementDTO(true, 200, urlPayment);
		return statementDTO;
	}
	private Long sumTotalPrice(List<CartDTO> cartDTOs) {
		Long sumTotalPrice=(long) 0;
		for(CartDTO cartDTO: cartDTOs) {
			sumTotalPrice=sumTotalPrice+cartDTO.getTotal();
		}
		return sumTotalPrice;
	}
}
