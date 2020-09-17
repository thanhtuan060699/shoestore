package shoestore.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView=new ModelAndView("home");
		return modelAndView;
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView=new ModelAndView("web/weblogin");
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
