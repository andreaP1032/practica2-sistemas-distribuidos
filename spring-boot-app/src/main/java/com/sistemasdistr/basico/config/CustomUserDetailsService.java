/**
 * Servicio personalizado de autenticación de usuarios
 * Esta clase implementa la interfaz UserDetailService de Spring Security,
 * permitiendo cargar usuarios desde la base de datos.
 * Se utiliza durante el proceso de Login para validad las credenciales introducidas por el usuarios.
 * A diferencia de la configuración en memoria, esta clase permite integrar la autenticación con una base
 * de datos real mediante el repositorio UserRepository.
 */
package com.sistemasdistr.basico.config;

import com.sistemasdistr.basico.model.User;
import com.sistemasdistr.basico.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

//Servicio gestionado por Spring
@Service
public class CustomUserDetailsService implements UserDetailsService {

	//Repositorio que permite acceder a los usuarios almacenados en base de datos
    private final UserRepository userRepository;

    //Constructor que inyecta el repositorio de usuarios
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Método utilizado por Spring Security para cargar un usuario
    //a partir del nombre de usuario introducido en el login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException   {
        //Se busca el usuario en la base de datos mediante el repositorio
    	//Si no se encuentra el usuario, Spring lanzará una excepción automáticamente
    	User user = userRepository.findUserByUsername(username);

    	//Se construye un objeot UserDetails de Spring Security,
    	//que contiene username, contraseña y roles
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                //se asigna el rol del usuario como autoridad para controlar el acceso
                List.of(new SimpleGrantedAuthority(user.getUserRole().getRoleName()))
        );
    }
}
