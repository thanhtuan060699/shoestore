package shoestore.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import shoestore.dto.ProductDTO;
import shoestore.service.impl.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/karma/listproduct",method = RequestMethod.GET)
	public ModelAndView listProduct(@RequestParam("page") int page, 
			 @RequestParam("limit") int limit) {
		int totalItem=productService.getTotalItem();
		int totalPage=(int) Math.ceil((double)totalItem/limit);
		ModelAndView modelAndView=new ModelAndView("web/listproduct");
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("page", page);
		Pageable pageable=new PageRequest(page-1, limit);
		List<ProductDTO> productDTOs=productService.findAll(pageable);
		modelAndView.addObject("listProducts", productDTOs);
		return modelAndView;
	}
}	
