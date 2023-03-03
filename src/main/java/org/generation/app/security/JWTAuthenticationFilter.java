package org.generation.app.security;


import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// paso 14: Generar filtro para dar el token para los usuarios autenticados

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	// Autenticar al usuario
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		AuthCredentials authCredentials = new AuthCredentials();
		
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
			
		} catch (StreamReadException e) {			
			e.printStackTrace();
		} catch (DatabindException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),  //Username
				authCredentials.getPassword(),//Password
				Collections.emptyList()//Credenciales (roles)
				);
						
	return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	// Una vez autenticado, generamos el token
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDetails =  (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getFullName() , userDetails.getUsername() )  ; //TODO Generar token
		
		response.addHeader("Authorization", "Bearer " + token);
		response.getWriter().close();
		
		super.successfulAuthentication(request, response, chain, authResult);
		
	}

}
