package shoestore.api.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.CartDTO;
import shoestore.util.SessionUtil;

@RestController
public class CartAPI {
	
	@RequestMapping(value = "/api/cart/add",method = RequestMethod.POST)
	public @ResponseBody Integer addCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
		if(SessionUtil.getInstance().getValue(request, "carts")==null) {
			List<CartDTO> cartDTOs=new ArrayList<CartDTO>();
			cartDTOs.add(cartDTO);
			cartDTO.setId(1);
			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
			SessionUtil.getInstance().putValue(request, "amounts", getSubTotalQuantity(cartDTOs));
			
		}else {
			List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
			for(CartDTO cart: cartDTOs) {
				if(cart.getProductAttributeId()==cartDTO.getProductAttributeId()) {
					return 0;
				}
			}
			Integer id=cartDTOs.get(cartDTOs.size()-1).getId()+1;
	 		cartDTO.setId(id);
	 		cartDTOs.add(cartDTO);
	 		SessionUtil.getInstance().putValue(request, "amounts", getSubTotalQuantity(cartDTOs));
	 		SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
			
		}
		return (Integer) SessionUtil.getInstance().getValue(request, "amounts");
	}
	private Integer getSubTotalQuantity(List<CartDTO> cartDTOs) {
		Integer subTotal=0;
		for(CartDTO cartDTO : cartDTOs) {
			subTotal=subTotal+cartDTO.getQuantity();
		}
		return subTotal;
	}
	@RequestMapping(value = "/api/cart/add/increase",method = RequestMethod.POST)
	public @ResponseBody CartDTO increaseQuantityAddCard(@RequestBody CartDTO cartDTO) {
		if(cartDTO.getQuantity()<cartDTO.getMaxQuantity()) {
			cartDTO.setQuantity(cartDTO.getQuantity()+1);
		}
		return cartDTO;
	}
	
	@RequestMapping(value = "/api/cart/add/reduce",method = RequestMethod.POST)
	public @ResponseBody CartDTO reduceQuantityAddCard(@RequestBody CartDTO cartDTO) {
		if(cartDTO.getQuantity()!=1)
		{
			cartDTO.setQuantity(cartDTO.getQuantity()-1);
		}
		return cartDTO;
	}
	
	@RequestMapping(value = "/api/cart/add/change",method = RequestMethod.POST)
	public @ResponseBody CartDTO changeQuantityAddCard(@RequestBody CartDTO cartDTO) {
		if(cartDTO.getQuantity()>cartDTO.getMaxQuantity()) {
			cartDTO.setQuantity(cartDTO.getMaxQuantity());
		}
		if(cartDTO.getQuantity()<=0) {
			cartDTO.setQuantity(1);
		}
		return cartDTO;
	}
	
	@RequestMapping(value = "/api/cart/edit/increase",method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> increaseQuantityEditCard(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		if(cartDTO.getQuantity()<cartDTO.getMaxQuantity()) {
			List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
			for(CartDTO cart:cartDTOs) {
				if(cart.getId()==cartDTO.getId()) {
					cart.setQuantity(cart.getQuantity()+1);
					cart.setTotal((Long)(cart.getQuantity()*cart.getPrice()));
				}
			}
			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
			Long subTotal=subTotalPrice(cartDTOs);
			cartDTO.setQuantity(cartDTO.getQuantity()+1);
			Long total=(Long)(cartDTO.getQuantity()*cartDTO.getPrice());
			cartDTO.setTotal(total);
			Integer subQuantity=subQuantity(cartDTOs);
			hashMap.put("cartChange", cartDTO);
			hashMap.put("subTotal", subTotal);
			hashMap.put("subQuantity", subQuantity);
		}
		return hashMap;
	}
	@RequestMapping(value = "/api/cart/edit/reduce",method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> reduceQuantityEditCard(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		HashMap<String,Object> hashMap=new HashMap<String, Object>();
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");;
		if(cartDTO.getQuantity()>1) {
			
			for(CartDTO cart:cartDTOs) {
				if(cart.getId()==cartDTO.getId()) {
					cart.setQuantity(cart.getQuantity()-1);
					cart.setTotal((Long)(cart.getQuantity()*cart.getPrice()));
				}
			}
			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
			cartDTO.setQuantity(cartDTO.getQuantity()-1);
			Long total=(Long)(cartDTO.getQuantity()*cartDTO.getPrice());
			cartDTO.setTotal(total);
			
			
		}else {
			cartDTO.setTotal(cartDTO.getPrice());
		}
		Integer subQuantity=subQuantity(cartDTOs);
		Long subTotal=subTotalPrice(cartDTOs);
		hashMap.put("cartChange", cartDTO);
		hashMap.put("subTotal", subTotal);
		hashMap.put("subQuantity", subQuantity);
		return hashMap;
	}
	
	@RequestMapping(value = "/api/cart/edit/change",method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> changeQuantityEditCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		for(CartDTO cart:cartDTOs) {
			if(cart.getId()==cartDTO.getId()) {
				if(cartDTO.getQuantity()>cartDTO.getMaxQuantity()) {
					cart.setQuantity(cartDTO.getMaxQuantity());
					cart.setTotal(cartDTO.getPrice()*cartDTO.getMaxQuantity());
					cartDTO.setQuantity(cartDTO.getMaxQuantity());
					cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getMaxQuantity());
				}
				else if(cartDTO.getQuantity()<=0) {
					cart.setQuantity(1);
					cart.setTotal(cartDTO.getPrice());
					cartDTO.setQuantity(1);
					cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
				}else {
					cart.setQuantity(cartDTO.getQuantity());
					cart.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
					cartDTO.setQuantity(cartDTO.getQuantity());
					cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
				}
				
			}
		}
		hashMap.put("cartChange", cartDTO);
		Long subTotal=subTotalPrice(cartDTOs);
		hashMap.put("subTotal", subTotal);
		SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
		Integer subQuantity=subQuantity(cartDTOs);
		hashMap.put("subQuantity", subQuantity);
		return hashMap;
	}
	@RequestMapping(value = "/api/cart/add/list",method = RequestMethod.POST)
	public @ResponseBody Integer  addCartFromListProduct(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		cartDTO.setQuantity(1);
		cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
		if(SessionUtil.getInstance().getValue(request, "carts")==null) {
			List<CartDTO> cartDTOs=new ArrayList<CartDTO>();
			cartDTOs.add(cartDTO);
			cartDTO.setId(1);
			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
			SessionUtil.getInstance().putValue(request, "amounts", getSubTotalQuantity(cartDTOs));
			
		}else {
			List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
			for(CartDTO cart: cartDTOs) {
				if(cart.getProductAttributeId()==cartDTO.getProductAttributeId()) {
					return 0;
				}
			}
 			Integer id=cartDTOs.get(cartDTOs.size()-1).getId()+1;
 			cartDTO.setId(id);
 			cartDTOs.add(cartDTO);
 			SessionUtil.getInstance().putValue(request, "amounts", getSubTotalQuantity(cartDTOs));
 			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
		}
		return (Integer) SessionUtil.getInstance().getValue(request, "amounts");
	}
	
	@RequestMapping(value = "/api/cart/delete",method = RequestMethod.POST)
	public @ResponseBody List<CartDTO> deleteCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		for(CartDTO cart:cartDTOs) {
			if(cart.getId()==cartDTO.getId()) {
				cartDTOs.remove(cart);
				break;
			}
		}
		SessionUtil.getInstance().putValue(request,"carts", cartDTOs);
		return cartDTOs;
		
	}
   private Long subTotalPrice(List<CartDTO> cartDTOs) {
		Long subTotal = (long) 0;
		for(CartDTO cartDTO : cartDTOs) {
			subTotal=subTotal+cartDTO.getTotal();
		}
		return subTotal;
	}
	private Integer subQuantity(List<CartDTO> cartDTOs) {
		Integer subQuantity=0;
		for(CartDTO cartDTO:cartDTOs) {
			subQuantity=subQuantity+cartDTO.getQuantity();
		}
		return subQuantity;
	}
	
}
