package shoestore.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.ProductAttributeDTO;
import shoestore.service.impl.ProductAttributeService;

@RestController
public class ProductAttributeAPI {
	@Autowired
	ProductAttributeService productAttributeService;
	
	@RequestMapping(value = "/api/customer/productattribute",method = RequestMethod.POST)
	public @ResponseBody ProductAttributeDTO getPriceAndQuantity(@RequestBody ProductAttributeDTO productAttributeDTO) {
		ProductAttributeDTO productAttribute=productAttributeService.findByColorSizeAndProductId(productAttributeDTO);
		return productAttribute;
	}
}
