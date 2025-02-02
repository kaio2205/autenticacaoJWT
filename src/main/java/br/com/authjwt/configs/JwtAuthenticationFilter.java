package br.com.authjwt.configs;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import br.com.authjwt.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter {
	private HandlerExceptionResolver handlerExceptionResolver = null;

	private JwtService jwtService = new JwtService();
	private UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtService jws, UserDetailsService uds, HandlerExceptionResolver her) {
		handlerExceptionResolver = her;
		jwtService = jws;
		userDetailsService = uds;
	}

	public void doFilter(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			String jwt = authHeader .substring(7);
			String email = jwtService.extractUsername(jwt);
			
			SecurityContext auth = (SecurityContext) SecurityContextHolder.getContext().getAuthentication();
			if (email != null && auth ==null) {
			
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
				
				
				if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken upa = 
				new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				
				
				upa.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			}
		
	}
	
			filterChain.doFilter(request, response );
		}catch (Exception e) {
			handlerExceptionResolver.resolveException(request, response, null, e);
		
		}
			
			
}
	
			
}
