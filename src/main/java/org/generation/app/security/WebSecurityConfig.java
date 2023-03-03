package org.generation.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// paso 1. personalizar la seguridad
@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // paso 7: habilitar los roles en los endpoints
public class WebSecurityConfig {
	
	@Autowired // paso 11: inyectar una implementación de la interfaz
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTAuthorizationFilter jwtAuthorizationFilter;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,
			AuthenticationManager authManager) throws Exception {
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		http
			.httpBasic()
			.and()
			.authorizeHttpRequests()
			.anyRequest() // .permitAll(); // paso 2: Deshabilitamos la seguridad
			.authenticated() // paso 3: Habilitamos la seguridad
			.and()
			//Filtro para dar el token para los usuarios autenticados
			.addFilter(jwtAuthenticationFilter)
			// ToDo Verificar el token JWT para las peticiones http.
			.addFilterBefore( jwtAuthorizationFilter  , UsernamePasswordAuthenticationFilter.class)
			.csrf().disable();					
		
		return http.build();
		
	}
	
	// Paso 4: Crear usuario y contraseña en memoria
	//@Bean //paso 12: deshabilitar la carga de usuarios en memoria
	public UserDetailsService userDetailsService() {
		UserDetails juan = User.builder()
				.username("juan")
				//.password("{noop}malote") //ToDo encriptar la contraseña
				.password( passwordEncoder().encode("malote") )  // paso 6: codificar la contraseña
				.roles("ADMIN")
				.build();
		UserDetails luis = User.builder()
				.username("luis")
				//.password("{noop}fierro") //ToDo encriptar la contraseña
				.password( passwordEncoder().encode("fierro") ) // paso 6: codificar la contraseña
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(juan, luis);
	}
	
	// paso 13: Incerceptamos el manejo de autenticación de los usuarios
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http
				.getSharedObject( AuthenticationManagerBuilder.class)
				.userDetailsService( userDetailsService )
				.passwordEncoder( passwordEncoder() )
				.and()
				.build();
	}
	
	// paso 5: Crear el bean que codificará la contraseña
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();		
	}
	
	public static void main(String[] args) {
		System.out.println("Password: " + new BCryptPasswordEncoder().encode("malote"));
	}

}
