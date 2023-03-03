package org.generation.app.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Paso 15: token JWT
 * Esta clase nos ayudará a generar y verificar el token JWT. 
 * @author TuX3
 *
 */
public class TokenUtils {

	private final static String ACCESS_TOKEN_SECRET = "Z-=cmzSL8ve1qzTdLLGPZCdoFmJGQX96zZaNLjJ1WoYyte?!Z1?5h5ewqeK6MtT=YKq5lFP3SMf7V91IF2t?T8Vj-86UkJC!!-Qv5rqK8tz3OJZtRahe2p9W6d79lUWEqqmJJM1gJIeGGRn1tuYI7qwHdEpRXhXVo!A3MTdIn=ISx7Ded8U/6Kg!Ilhb-Zm4zIqT1ADyuNau=W7tA705E7Apda?9hbZDE?oAcRRR01KZQ?Fnrx2xY5!N8P/VAot-";
	private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 300L; // 5 minutos
	
	/** 
	 * Paso 16: Generar Token, Se puede hacer la solicitud Post Login y se response
	 *  con el token en el header de la respuesta.
	 * Genera el token JWT y lo adjunta en el header de la respuesta HTTP
	 * @param fullName
	 * @param email
	 * @return
	 */
	public static String createToken(String fullName, String  email) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date( System.currentTimeMillis() + expirationTime );
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("name", fullName);
		
		return Jwts // Se genera el token
				.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(payload)
				.signWith( Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				.compact();		
		
	}
	
	/**
	 * Paso 18: Validar el token recibido por el cliente
	 * 
	 * @param token
	 * @return
	 */
	public static UsernamePasswordAuthenticationToken getAuthentication(String token ) {

		try {
			
			Claims claims = Jwts
					.parserBuilder()
					.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			String email = claims.getSubject();			
			return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList() );
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(token);
			return null;
		}
	}
	
}
