package com.forum.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.userinfo.GoogleUserInfo;
import org.springframework.stereotype.Service;

import com.forum.domain.User;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
 
    @Autowired
    private UserService userService;
    
    private IUploadFileService uploadFileService;
 
    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        
        if(connection.getApi() instanceof Facebook) {
        	Facebook facebook = (Facebook) connection.getApi();
            String [] fields = { "id", "email"};
            User userProfile = facebook.fetchObject("me", User.class, fields);
            user.setEmail(userProfile.getEmail());
            user.setPassword(String.valueOf(userProfile.getId()));
            
            user.setName(connection.getDisplayName());
            user.setEnabled(true);
            userService.signup(user,true,connection.getImageUrl());
            
        }else if(connection.getApi() instanceof Google){
        	Google google = (Google)connection.getApi();
            GoogleUserInfo gUser = google.userOperations().getUserInfo();
            
            user.setEmail(gUser.getEmail());
            user.setPassword(String.valueOf(gUser.getId()));
            
            user.setName(gUser.getFirstName()+" "+gUser.getLastName());
            user.setEnabled(true);
            userService.signup(user,true,gUser.getProfilePictureUrl());
            
            
        }else {
        	user.setEmail(connection.fetchUserProfile().getEmail());
        	user.setPassword(UUID.randomUUID().toString());
        	
        	user.setName(connection.getDisplayName());
            user.setEnabled(true);
            userService.signup(user,true,connection.getImageUrl());
        }
        
        
        
        
        
        
        
        return user.getUsername();
    }
}
