package shoestore.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import shoestore.dto.CartDTO;
import shoestore.util.SessionUtil;

@Controller
public class CartControlller {
	@RequestMapping(value = "/karma/cart",method = RequestMethod.GET)
	public ModelAndView showCart(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("/web/cart");
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		if(cartDTOs==null||cartDTOs.size()==0) {
			modelAndView.addObject("zeroProduct", 0);
			modelAndView.addObject("amounts", SessionUtil.getInstance().getValue(request, "amounts"));
			return modelAndView;
		}
		modelAndView.addObject("amounts", SessionUtil.getInstance().getValue(request, "amounts"));
		modelAndView.addObject("cart", cartDTOs);
		Long subTotal=subTotalPrice(cartDTOs);
		modelAndView.addObject("subTotal",subTotal );
		return modelAndView;
	}
	private Long subTotalPrice(List<CartDTO> cartDTOs) {
		Long subTotal = (long) 0;
		for(CartDTO cartDTO : cartDTOs) {
			subTotal=subTotal+cartDTO.getTotal();
		}
		return subTotal;
	}
}
