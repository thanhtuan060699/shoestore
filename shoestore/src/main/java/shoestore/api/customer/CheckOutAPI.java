package shoestore.api.customer;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.CartDTO;
import shoestore.dto.OrderDTO;
import shoestore.dto.StatementDTO;
import shoestore.dto.UserDTO;
import shoestore.service.impl.OrderService;
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
		orderService.addNewOrder(userDTO, orderDTO, cartDTOs);
		StatementDTO statementDTO=new StatementDTO(true, 200, "order successful");
		return statementDTO;
	}
}
