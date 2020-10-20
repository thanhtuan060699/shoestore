package shoestore.controller.heatmap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeHeatMapController {
	@RequestMapping(value = "/heatmap/home",method = RequestMethod.GET)
	public ModelAndView showCart(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("/heatmap/homeheatmap");
		return modelAndView;
	}
}
