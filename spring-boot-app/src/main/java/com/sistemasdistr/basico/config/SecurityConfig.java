/**
 * Configurción de la Seguridad de la aplicación
 * 
 * Esta clase define la configuración de Spring Security, 
 * encargándose de gestionar la autenticación y autorización de los usuarios.
 * 
 * Se configuran:
 * -El sistema de autenticación(usuarios en memoria)
 * -El cifrado de contraseñas
 * -Las reglas de acceso a los endpoints
 * -El formulario de login
 * 
 * Forma parte de la capa de seguridad dentro de la aplicación
 */
package com.sistemasdistr.basico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    //private final CustomUserDetailsService customUserDetailsService;

   /* public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }*/

	//Bean que define el codificador de contraseñas
	//Se utiliza BCrypt para almacenar las contraseñas de forma segura
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //Definición de usuarios en memoria para pruebas.
    //Se crea un usuario con username "admin" y contraseña "1234"
    //con rol USER
    @Bean
    public UserDetailsService users() {
        //Construcción del usuario con credenciales y rol
    	UserDetails user = User
            .withUsername("admin")
            .password(passwordEncoder().encode("1234"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }

    //Bean que proporciona el gestor de autenticación de Spring Security.
    //Se encarga de validar las credenciales del usuario en el login.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

    //Configuración principal de seguridad HTTP
    //Define qué rutas son públicas, cuáles requieren autenticación
    // y cuáles necesitan roles específicos.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        		//Se desactiva CSRF para simplificar pruebas en entorno local
                .csrf(AbstractHttpConfigurer::disable)
                //.userDetailsService(customUserDetailsService)
                .authorizeHttpRequests(auth -> auth
                		//Permite acceso público a la ráiz ("/")
                        .requestMatchers("/").permitAll()
                        //permite acceso publico a rutas de autenticación
                        .requestMatchers("/auth/**").permitAll()
                        //permite acceso a los endpoints de prueba (simulación Flask)
                        .requestMatchers("/test/**").permitAll()
                        //Restringe acceso a rutas de usuarios solo a rol ADMIN
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        //Cualquier otra petición requiere autenticación
                        .anyRequest().authenticated()
                )
                //Configuración del formulario de login por defecto
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                //permite cerrar sesión sin restricciones
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
