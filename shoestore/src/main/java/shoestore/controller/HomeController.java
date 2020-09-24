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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import shoestore.util.SessionUtil;

@Controller
public class HomeController {
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView modelAndView=new ModelAndView("home");
		modelAndView.addObject("amounts", SessionUtil.getInstance().getValue(request, "amounts"));
		return modelAndView;
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,@RequestParam(required = false) String nonlogin ) {
		ModelAndView modelAndView=new ModelAndView("web/weblogin");
		if(nonlogin!=null) {
			modelAndView.addObject("checkoutlogin", "true");
		}
		modelAndView.addObject("amounts", SessionUtil.getInstance().getValue(request, "amounts"));
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
