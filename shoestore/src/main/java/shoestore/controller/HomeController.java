package shoestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/karma/home",method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView=new ModelAndView("home");
		return modelAndView;
	}
}
