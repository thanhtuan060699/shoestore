package shoestore.api.customer;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.OrderDTO;

@RestController
public class CheckOutAPI {
	@RequestMapping(value = "/api/customer/order/add",method = RequestMethod.POST)
	public void addOrder(@RequestBody OrderDTO orderDTO) {
		System.out.println("tt");
	}
}
