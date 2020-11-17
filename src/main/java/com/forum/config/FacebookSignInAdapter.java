package com.forum.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.context.request.NativeWebRequest;

import com.forum.domain.User;
import com.forum.services.UserServiceImpl;

public class FacebookSignInAdapter implements SignInAdapter {
	
	
	
    @Override
    public String signIn(
      String localUserId, 
      Connection<?> connection, 
      NativeWebRequest request) {
        
    	Optional<User> usuario = null;
    	
    	if(connection.getApi() instanceof Facebook) {
    	
    		Facebook facebook = (Facebook) connection.getApi();
            String [] fields = { "id", "email"};
            User userProfile = facebook.fetchObject("me", User.class, fields);
        	
            usuario = UserServiceImpl.getUserRepository().findByEmail(userProfile.getEmail());
    		
    	}else {
    		//LinkedIn linkedIn = (LinkedIn)connection.getApi();
    		usuario = UserServiceImpl.getUserRepository().findByEmail(connection.fetchUserProfile().getEmail());
    	}
    	
    	
        
        Authentication authentication =  new UsernamePasswordAuthenticationToken(usuario.get(), usuario.get().getPassword(), Arrays.asList(new SimpleGrantedAuthority("VERIFIED")));

         Object pric = authentication.getPrincipal();
         
        
        //log.debug("Logging in with {}", authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        
        /*SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(
        		  userProfile.getEmail(), userProfile.getId(), Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));*/
         
        return null;
    }
}