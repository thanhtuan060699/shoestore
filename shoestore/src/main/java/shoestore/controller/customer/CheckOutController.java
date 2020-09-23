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
public class CheckOutController {
	@RequestMapping(value = "/karma/checkout",method = RequestMethod.GET)
	public ModelAndView showCheckOut(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("web/checkout");
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		modelAndView.addObject("products", cartDTOs);
		return modelAndView;
	}
}
