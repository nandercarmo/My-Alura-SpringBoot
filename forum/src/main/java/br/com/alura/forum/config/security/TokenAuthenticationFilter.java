package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.UserRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;
	
	public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {

		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = this.getToken(request);
		boolean valid = tokenService.isTokenValid(token);
		
		if (valid) {
			
			this.authClient(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authClient(String token) {
		
		Long userId = tokenService.getUserId(token);
		
		System.out.println(userId);
		
		User user = userRepository.findById(userId).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String getToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
