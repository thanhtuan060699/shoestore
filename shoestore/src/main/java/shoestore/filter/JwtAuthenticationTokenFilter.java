package shoestore.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import shoestore.constant.SystemConstant;
import shoestore.dto.MyUser;
import shoestore.dto.UserDTO;
import shoestore.entity.RoleEntity;
import shoestore.entity.UserEntity;
import shoestore.entity.UserRoleEntity;
import shoestore.repository.RoleRepository;
import shoestore.repository.UserRepository;
import shoestore.repository.UserRoleRepository;
import shoestore.service.impl.UserService;
import shoestore.util.jwt.JwtProvider;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter{
	
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	      throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httptResponse=(HttpServletResponse) response;
		String result=null;
	    //Get token
		Cookie[] cookies=httpRequest.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("TOKEN")) {
					result=cookie.getValue();
				}
			}
		}
		if(StringUtils.isNotBlank(result)) {
			String username=jwtProvider.getUsernameFromToken(result);
			UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
			UserRoleEntity userRoleEntity=userRoleRepository.findByUserEntity(userEntity);
			RoleEntity roleEntity=roleRepository.findById(userRoleEntity.getRoleEntity().getId());
		    if (jwtProvider.validateTokenLogin(result)) {
		      UserDTO user = userService.loadUserByUsername(username);
		      if (user != null) {
		        boolean enabled = true;
		        boolean accountNonExpired = true;
		        boolean credentialsNonExpired = true;
		        boolean accountNonLocked = true;
		        List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(roleEntity.getCode()));
		        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true, true,
						authorities);
		        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(myUser,
		            null, myUser.getAuthorities());
		        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        chain.doFilter(httpRequest, httptResponse);
		  
		      }
		    }
		}else {
			 chain.doFilter(httpRequest, httptResponse);
		}
	
	 
	    
	  }

}

