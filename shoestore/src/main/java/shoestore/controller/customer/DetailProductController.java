package shoestore.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import shoestore.dto.ProductAttributeDTO;
import shoestore.dto.ProductDTO;
import shoestore.service.impl.ProductAttributeService;
import shoestore.service.impl.ProductService;

@Controller
public class DetailProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductAttributeService productAttributeService;
	
	@RequestMapping(value = "/karma/detailproduct",method = RequestMethod.GET)
	public ModelAndView showDetailProduct(@RequestParam Long id) {
		ModelAndView modelAndView=new ModelAndView("web/detailproduct");
		ProductDTO productDTO=productService.findById(id);
		List<ProductAttributeDTO> productAttributeDTOs=productAttributeService.findAllByProductId(id);
		modelAndView.addObject("sizes", productAttributeDTOs);
		modelAndView.addObject("product", productDTO);
		return modelAndView;
	}
}
