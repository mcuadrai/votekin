package com.forum.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {

//		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
//		
//		FlashMap flashMap = new FlashMap();
//		
//		flashMap.put("success", "Hola " +authentication.getName()+ ", haz iniciado sesión con éxito!");
//		
//		flashMapManager.saveOutputFlashMap(flashMap, request, response);
//		
//		if(authentication != null) {
//			logger.info("El usuario '"+authentication.getName()+"' ha iniciado sesión con éxito");
//		}
//		if (authentication != null && authentication.getAuthorities().contains(new Role("UNVERIFIED"))) {
//			flashMap.put("error", "Your account is not verified, you have to click on the link in your mailbox ");
//		}
		
		// User user = userService.fetchByUserName(authentication.getName());
		// session.setAttribute("userSession", user);
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
}
