package shoestore.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartControlller {
	@RequestMapping(value = "/karma/cart",method = RequestMethod.GET)
	public ModelAndView showCart() {
		ModelAndView modelAndView=new ModelAndView("/web/cart");
		return modelAndView;
	}
}
