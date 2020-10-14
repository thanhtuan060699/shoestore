package shoestore.api.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.CartDTO;
import shoestore.dto.StatementDTO;
import shoestore.util.SessionUtil;

@RestController
public class CartAPI {
	
	@RequestMapping(value = "/api/cart/add",method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> addCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		//check max quantity zero
		if(cartDTO.getMaxQuantity()==0) {
			Cookie[] cookies=request.getCookies();
			String result=null;
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("TOKEN")) {
					result=cookie.getValue();
				}
			}
			//check non login
			if(result==null) {
				StatementDTO statement=new StatementDTO(true, 200, "sold out and non login");
				hashMap.put("statement", statement);
			}
			//check already login
			else {
				StatementDTO statement=new StatementDTO(true, 200, "sold out and login");
				hashMap.put("statement", statement);
			}
		}else {
			cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
			List<CartDTO> carts=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
			if(carts==null||carts.size()==0) {
				List<CartDTO> cartDTOs=new ArrayList<CartDTO>();
				cartDTOs.add(cartDTO);
				cartDTO.setId(1);
				SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
				SessionUtil.getInstance().putValue(request, "amounts", getSumTotalQuantity(cartDTOs));
				StatementDTO statement=new StatementDTO(true, 200, "success");
				hashMap.put("statement", statement);
				
			}else {
				List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
				int index=0;
				for(CartDTO cart: cartDTOs) {
					if(cart.getProductAttributeId()==cartDTO.getProductAttributeId()) {
						StatementDTO statement=new StatementDTO(true, 200, "product existed");
						hashMap.put("statement", statement);
						index++;
					}
				}
				if(index==0) {
					Integer id=cartDTOs.get(cartDTOs.size()-1).getId()+1;
			 		cartDTO.setId(id);
			 		cartDTOs.add(cartDTO);
			 		StatementDTO statement=new StatementDTO(true, 200, "success");
					hashMap.put("statement", statement);
			 		SessionUtil.getInstance().putValue(request, "amounts", getSumTotalQuantity(cartDTOs));
			 		SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
				}
			
			}
		}
		hashMap.put("amount", SessionUtil.getInstance().getValue(request, "amounts"));
		return hashMap;
	}
	private Integer getSumTotalQuantity(List<CartDTO> cartDTOs) {
		Integer sumTotal=0;
		for(CartDTO cartDTO : cartDTOs) {
			sumTotal=sumTotal+cartDTO.getQuantity();
		}
		return sumTotal;
	}
	@RequestMapping(value = "/api/cart/add/increase",method = RequestMethod.POST)
	public @ResponseBody CartDTO increaseQuantityAddCart(@RequestBody CartDTO cartDTO) {
		if(cartDTO.getQuantity()<cartDTO.getMaxQuantity()) {
			cartDTO.setQuantity(cartDTO.getQuantity()+1);
		}
		
		return cartDTO;
	}
	
	@RequestMapping(value = "/api/cart/add/reduce",method = RequestMethod.POST)
	public @ResponseBody CartDTO reduceQuantityAddCart(@RequestBody CartDTO cartDTO) {
		if(cartDTO.getQuantity()!=1)
		{
			cartDTO.setQuantity(cartDTO.getQuantity()-1);
		}
		return cartDTO;
	}
	
	@RequestMapping(value = "/api/cart/add/change",method = RequestMethod.POST)
	public @ResponseBody CartDTO changeQuantityAddCart(@RequestBody CartDTO cartDTO) {
		if(cartDTO.getQuantity()>cartDTO.getMaxQuantity()) {
			cartDTO.setQuantity(cartDTO.getMaxQuantity());
		}
		if(cartDTO.getQuantity()<=0) {
			cartDTO.setQuantity(1);
		}
		return cartDTO;
	}
	
	@RequestMapping(value = "/api/cart/edit/increase",method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> increaseQuantityEditCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
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
			Long subTotal=sumTotalPrice(cartDTOs);
			cartDTO.setQuantity(cartDTO.getQuantity()+1);
			Long total=(Long)(cartDTO.getQuantity()*cartDTO.getPrice());
			cartDTO.setTotal(total);
			Integer subQuantity=sumQuantity(cartDTOs);
			hashMap.put("cartChange", cartDTO);
			hashMap.put("subTotal", subTotal);
			hashMap.put("subQuantity", subQuantity);
			//increase amount
			Integer amount=(Integer) SessionUtil.getInstance().getValue(request, "amounts");
			amount++;
			SessionUtil.getInstance().putValue(request, "amounts", amount);
		}else {
			List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
			Long subTotal=sumTotalPrice(cartDTOs);
			cartDTO.setTotal((Long)(cartDTO.getQuantity()*cartDTO.getPrice()));
			hashMap.put("cartChange", cartDTO);
			Integer subQuantity=sumQuantity(cartDTOs);
			hashMap.put("subTotal", subTotal);
			hashMap.put("subQuantity", subQuantity);
		}
		
		
		return hashMap;
	}
	@RequestMapping(value = "/api/cart/edit/reduce",method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> reduceQuantityEditCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
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
			Integer amount=(Integer) SessionUtil.getInstance().getValue(request, "amounts");
			amount--;
			SessionUtil.getInstance().putValue(request, "amounts", amount);
			
		}else {
			cartDTO.setTotal(cartDTO.getPrice());
		}
		Integer subQuantity=sumQuantity(cartDTOs);
		Long subTotal=sumTotalPrice(cartDTOs);
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
		Long subTotal=sumTotalPrice(cartDTOs);
		hashMap.put("subTotal", subTotal);
		SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
		Integer subQuantity=sumQuantity(cartDTOs);
		SessionUtil.getInstance().putValue(request, "amounts", subQuantity);
		hashMap.put("subQuantity", subQuantity);
		return hashMap;
	}
	@RequestMapping(value = "/api/cart/add/list",method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object>  addCartFromListProduct(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		cartDTO.setQuantity(1);
		cartDTO.setTotal(cartDTO.getPrice()*cartDTO.getQuantity());
		List<CartDTO> carts=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		StatementDTO statementDTO=new StatementDTO(true, 200, "add cart successful");
		//check quantity 0
		if(cartDTO.getMaxQuantity()==0) {
			Cookie[] cookies=request.getCookies();
			String result=null;
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("TOKEN")) {
					result=cookie.getValue();
				}
			}
			//check non login
			if(result==null) {
				StatementDTO statement=new StatementDTO(true, 200, "sold out and non login");
				hashMap.put("statement", statement);
			}
			//check already login
			else {
				StatementDTO statement=new StatementDTO(true, 200, "sold out and login");
				hashMap.put("statement", statement);
			}
		}else {
			if(carts==null||carts.size()==0) {
				List<CartDTO> cartDTOs=new ArrayList<CartDTO>();
				cartDTOs.add(cartDTO);
				cartDTO.setId(1);
				SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
				SessionUtil.getInstance().putValue(request, "amounts", getSumTotalQuantity(cartDTOs));
				StatementDTO statement=new StatementDTO(true, 200, "success");
				hashMap.put("statement", statement);
			}else {
				List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
				//check quantity<maxQuantity
				for(CartDTO cart: cartDTOs) {
					if(cart.getProductAttributeId()==cartDTO.getProductAttributeId()) {
						if(cart.getQuantity()<cart.getMaxQuantity()) {
							cart.setQuantity(cart.getQuantity()+1);
						}else {
							StatementDTO statement=new StatementDTO(true, 200, "over quantity");
							hashMap.put("amounts", cart.getMaxQuantity());
							hashMap.put("statement", statement);
				 			return hashMap;
						}
						cart.setTotal(cart.getQuantity()*cart.getPrice());
						SessionUtil.getInstance().putValue(request, "amounts", getSumTotalQuantity(cartDTOs));
			 			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
			 			StatementDTO statement=new StatementDTO(true, 200, "product existed");
			 			hashMap.put("amounts", getSumTotalQuantity(cartDTOs));
			 			hashMap.put("statement", statement);
			 			return hashMap;
					}
					
				}
	 			Integer id=cartDTOs.get(cartDTOs.size()-1).getId()+1;
	 			cartDTO.setId(id);
	 			cartDTOs.add(cartDTO);
	 			SessionUtil.getInstance().putValue(request, "amounts", getSumTotalQuantity(cartDTOs));
	 			SessionUtil.getInstance().putValue(request, "carts", cartDTOs);
	 			StatementDTO statement=new StatementDTO(true, 200, "success");
				hashMap.put("statement", statement);
			}
		}
		
		hashMap.put("amounts", (Integer) SessionUtil.getInstance().getValue(request, "amounts"));
		return hashMap;

	}
	
	@RequestMapping(value = "/api/cart/delete",method = RequestMethod.POST)
	public @ResponseBody HashMap<String , Object> deleteCart(@RequestBody CartDTO cartDTO,HttpServletRequest request) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		List<CartDTO> cartDTOs=(List<CartDTO>) SessionUtil.getInstance().getValue(request, "carts");
		for(CartDTO cart:cartDTOs) {
			if(cart.getId()==cartDTO.getId()) {
				cartDTOs.remove(cart);
				break;
			}
		}
		Long sumPrice;
		Integer sumQuantity;
		if(cartDTOs.size()!=0) {
			sumPrice=sumTotalPrice(cartDTOs);
			sumQuantity=sumQuantity(cartDTOs);
		}
		else {
			sumPrice=(long) 0;
			sumQuantity=0;
		}
		hashMap.put("carts", cartDTOs);
		hashMap.put("sumPrice", sumPrice);
		hashMap.put("sumQuantity", sumQuantity);
		SessionUtil.getInstance().putValue(request,"carts", cartDTOs);
		SessionUtil.getInstance().putValue(request,"amounts",sumQuantity );
		return hashMap;
		
	}
   private Long sumTotalPrice(List<CartDTO> cartDTOs) {
		Long sumTotal = (long) 0;
		for(CartDTO cartDTO : cartDTOs) {
			sumTotal=sumTotal+cartDTO.getTotal();
		}
		return sumTotal;
	}
	private Integer sumQuantity(List<CartDTO> cartDTOs) {
		Integer sumQuantity=0;
		for(CartDTO cartDTO:cartDTOs) {
			sumQuantity=sumQuantity+cartDTO.getQuantity();
		}
		return sumQuantity;
	}
	
}
