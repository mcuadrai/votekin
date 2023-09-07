//package com.forum.config;
//
//import javax.inject.Inject;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.core.env.Environment;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.encrypt.Encryptors;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurer;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
//import org.springframework.social.connect.web.ProviderSignInController;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.social.google.api.Google;
//import org.springframework.social.google.connect.GoogleConnectionFactory;
//import org.springframework.social.linkedin.api.LinkedIn;
//import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
//
//import com.forum.services.FacebookConnectionSignup;
//
//@Configuration
//@EnableSocial
//public class SocialConfigFacebook implements SocialConfigurer {
//
//	@Inject
//	private DataSource dataSource;
//	
//	@Inject
//	Environment environment;
//	
//	@Autowired
//	private ConnectionFactoryLocator connectionFactoryLocator;
//
//	@Autowired
//	private UsersConnectionRepository usersConnectionRepository;
//
//	@Autowired
//	private FacebookConnectionSignup facebookConnectionSignup;
//
//	@Override
//	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//		
//		
//		FacebookConnectionFactory fcf =new FacebookConnectionFactory(env.getProperty("spring.social.facebook.appId"),
//				env.getProperty("spring.social.facebook.appSecret"));
//		
//		fcf.setScope("public_profile,email");
//		
//		cfConfig.addConnectionFactory(fcf);
//	
//	
//		LinkedInConnectionFactory lcf = new LinkedInConnectionFactory(env.getProperty("linkedin.consumerKey"),
//				env.getProperty("linkedin.consumerSecret"));
//		lcf.setScope("r_emailaddress,r_basicprofile");
//		
//		cfConfig.addConnectionFactory(lcf);
//		
//		GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(env.getProperty("google.clientKey"),
//				env.getProperty("google.clientSecret"));
//		
//		googleConnectionFactory.setScope("https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile");
//		
//		cfConfig.addConnectionFactory(googleConnectionFactory);
//		
//	}
//	
//	
//	
//
//	@Override
//	public UserIdSource getUserIdSource() {
//		// TODO Auto-generated method stub
//		String username = null;
//		try {
//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			if (principal instanceof UserDetails) {
//				username = ((UserDetails) principal).getUsername();
//			} else {
//				username = principal.toString();
//			}
//		} catch (Exception e) {
//			username = "";
//		}
//
//		final String un = username;
//
//		return new UserIdSource() {
//			@Override
//			public String getUserId() {
//				return un;
//			}
//		};
//	}
//
//	
//	
//	JdbcUsersConnectionRepository repository;
//	
//	@Override
//	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//		repository = new JdbcUsersConnectionRepository(dataSource,
//				connectionFactoryLocator, Encryptors.noOpText());
//		repository.setConnectionSignUp(new FacebookConnectionSignup());
//		return repository;
//	}
//	
//	
//	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public Facebook facebook() {
//		
//		
//		
//	    return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
//	}
//	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public LinkedIn linkedin() {
//		
//		
//		
//	    return connectionRepository().getPrimaryConnection(LinkedIn.class).getApi();
//	}
//	
//	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public Google google() {
//		
//		
//		
//	    return connectionRepository().getPrimaryConnection(Google.class).getApi();
//	}
//	
//	
//	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//	public ConnectionRepository connectionRepository() {
//		
//	    return repository.createConnectionRepository(getUserIdSource().getUserId());
//	}
//	
//	@Bean
//    public ProviderSignInController providerSignInController() {
//		repository
//          .setConnectionSignUp(facebookConnectionSignup);
//
//        
//        ProviderSignInController controller = new ProviderSignInController(
//  	          connectionFactoryLocator, 
//  	          usersConnectionRepository, 
//  	          new FacebookSignInAdapter());
//        
//        System.out.println(connectionFactoryLocator.registeredProviderIds());
//        
//        //controller.setApplicationUrl("https://votekin.wm-desk.com");
//        
//        controller.setApplicationUrl(environment.getProperty("applicationUrl"));
//        
//        
//        return controller;
//    }
//	
//
//	
//
//
//}
