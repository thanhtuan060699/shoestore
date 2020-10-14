package shoestore.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import shoestore.dto.BrandDTO;
import shoestore.dto.ProductAttributeDTO;
import shoestore.dto.ProductDTO;
import shoestore.service.impl.BrandService;
import shoestore.service.impl.ProductAttributeService;
import shoestore.service.impl.ProductService;
import shoestore.util.SessionUtil;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductAttributeService productAttributeService;
	
	@Autowired
	BrandService brandService;
	
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("home");
		modelAndView.addObject("homeActive",true);
		modelAndView.addObject("amounts", SessionUtil.getInstance().getValue(request, "amounts"));
		Pageable pageable=new PageRequest(0, 8);
		List<ProductDTO> productDTOs=productService.findAll(pageable);
		List<ProductAttributeDTO> productAttributeDTOs=productAttributeService.findSampleOfProduct(productDTOs);
		int i=0;
		for(ProductDTO productDTO:productDTOs) {
			productDTO.setPrice(productAttributeDTOs.get(i).getPrice());
			productDTO.setSize(productAttributeDTOs.get(i).getSize());
			productDTO.setColor(productAttributeDTOs.get(i).getColor());
			productDTO.setQuantity(productAttributeDTOs.get(i).getQuantity());
			productDTO.setProductAttributeId(productAttributeDTOs.get(i).getId());
			i++;
		}
		List<BrandDTO> brandDTOs=brandService.listBrand();
		modelAndView.addObject("brands", brandDTOs);
		modelAndView.addObject("listProducts", productDTOs);
		return modelAndView;
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,@RequestParam(required = false) String nonlogin ) {
		ModelAndView modelAndView=new ModelAndView("web/weblogin");
		if(nonlogin!=null) {
			modelAndView.addObject("checkoutlogin", "true");
		}
		modelAndView.addObject("loginActive", true);
		modelAndView.addObject("amounts", SessionUtil.getInstance().getValue(request, "amounts"));
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("TOKEN")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/login");
	}
}
