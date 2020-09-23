package shoestore.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import shoestore.util.SecurityUtils;
import shoestore.util.jwt.JwtProvider;


@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	JwtProvider jwtProvider;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		String result=null;
		result = jwtProvider.generateTokenLogin(SecurityUtils.getPrincipal().getUsername());//get a token
		Cookie cookie=new Cookie("TOKEN", result);
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		if (response.isCommitted()) {
			return;
		}
		
	
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	private String determineTargetUrl(Authentication authentication) {
		String url = "";
		List<String> roles = SecurityUtils.getAuthorities();
		if (isCustomer(roles)) {
			url = "/home";
		}
		return url;
	}

	private boolean isManager(List<String> roles) {
		if (roles.contains("MANAGER")) {
			return true;
		}
		return false;
	}

	private boolean isStaff(List<String> roles) {
		if (roles.contains("STAFF")) {
			return true;
		}
		return false;
	}
	private boolean isCustomer(List<String> roles) {
		if (roles.contains("CUSTOMER")) {
			return true;
		}
		return false;
	}
}
