package com.rbc.shopping.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.shopping.exception.Exception;

/**
 * @author Anmoldeep Singh Kang
 * This filter ensures the the path variable in the /recommendations request matches
 * the user who is authenticated. This is to ensure that a user Jack is not able to
 * request the recommendations for a user Tom.
 */
@Component
@Order(2)
public class RecommendationRequestFilter implements Filter {
	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
	    AntPathRequestMatcher matcher = new AntPathRequestMatcher("/recommendations/**");
	    HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		//Check if the current request if to obtain recommendations
	    if(matcher.matches(req)) {
	    	int lastIndex = req.getRequestURL().toString().lastIndexOf("/");
	    	//Get the authenticated User
			String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();
			//Get the user whose recommendation is requested in the request's Path variable.
			String recommendationUser = req.getRequestURL().toString().substring(lastIndex + 1);
			//If both the users are different, then return a 403 Forbidden exception.
			if (!authenticatedUser.equals(recommendationUser)) {
				Exception response1 = new Exception(403, "Forbidden");
				res.setContentType("application/json");
				res.setStatus(403);
				OutputStream out = res.getOutputStream();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, response1);
				out.flush();
			} else {
				chain.doFilter(request, response);
			}
	    } else {
			chain.doFilter(request, response);
		}

	}

}
