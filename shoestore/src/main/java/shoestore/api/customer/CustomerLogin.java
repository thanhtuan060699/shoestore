package shoestore.api.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.UserDTO;
import shoestore.service.impl.UserService;
import shoestore.util.jwt.JwtProvider;

@RestController
public class CustomerLogin {
	@Autowired
	UserService userService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@RequestMapping(value = "/api/login",method = RequestMethod.POST)
	public ResponseEntity<String> customerLogin(HttpServletRequest request, @RequestBody UserDTO userDTO){
		String result = "";
	    HttpStatus httpStatus = null;
	    
	    try {
	      if (userService.checkLogin(userDTO)) {
	        result = jwtProvider.generateTokenLogin(userDTO.getUsername());//tra ve token
	        httpStatus = HttpStatus.OK;
	      } else {
	        result = "Wrong userId and password";
	        httpStatus = HttpStatus.BAD_REQUEST;
	      }
	    } catch (Exception ex) {
	      result = "Server Error";
	      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	  
	    
	    return new ResponseEntity<String>(result, httpStatus);
		  
	}
}
