package com.forum.services;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forum.domain.Role;
import com.forum.domain.User;
import com.forum.repositories.UserRepository;
import com.forum.utils.MyUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		return userRepository.findByEmail(username)
//				.orElseThrow(() -> new UsernameNotFoundException(username));
		    Optional<User> usuario = userRepository.findByEmail(username);
	        
	        if(!usuario.isPresent()) {
	        	logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
	        	throw new UsernameNotFoundException(MyUtils.getMessage("message.user.notexist", username));
	        }
	        
	        Collection<? extends GrantedAuthority> authorities =  usuario.get().getAuthorities();
	        
	        if(authorities.isEmpty()) {
	        	throw new UsernameNotFoundException(MyUtils.getMessage("message.user.noroles", username));
	        }
	        if(authorities.contains(new Role("UNVERIFIED"))) {
	        	throw new UsernameNotFoundException(MyUtils.getMessage("message.user.notverified"));
	        }
	        
	       return usuario.get();
		
	}
}
