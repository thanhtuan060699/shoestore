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

import shoestore.dto.BrandDTO;
import shoestore.dto.ProductAttributeDTO;
import shoestore.dto.ProductDTO;
import shoestore.service.impl.BrandService;
import shoestore.service.impl.ProductAttributeService;
import shoestore.service.impl.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductAttributeService productAttributeService;
	
	@Autowired
	BrandService brandService;
	
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
		brandDTOs=productAttributeService.quantityOfBrand(brandDTOs);
		modelAndView.addObject("productActive",true);
		modelAndView.addObject("brands", brandDTOs);
		modelAndView.addObject("listProducts", productDTOs);
		return modelAndView;
	}
}	
