package shoestore.api.customer;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.ContactDTO;
import shoestore.dto.StatementDTO;
import shoestore.service.impl.ContactService;
import shoestore.util.jwt.JwtProvider;

@RestController
public class ContactAPI {
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(value = "/api/contact/add",method = RequestMethod.POST)
	public HashMap<String, Object> saveContact(@RequestBody ContactDTO contactDTO,HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		String result=null;
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("TOKEN")) {
				result=cookie.getValue();
			}
		}
		String username=jwtProvider.getUsernameFromToken(result);
		Boolean existed=contactService.existedContact(contactDTO, username);
		
		if(existed==false) {
			contactService.addContact(contactDTO, username);
			StatementDTO statementDTO=new StatementDTO(true, 200, "add successful");
			hashMap.put("notification",statementDTO);
		}else {
			StatementDTO statementDTO=new StatementDTO(true, 200, "existed contact");
			hashMap.put("notification",statementDTO);
		}
		
		return hashMap;
	}
}
