/*package com.forum.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;

import com.forum.services.FacebookConnectionSignup;

@Configuration
@EnableSocial
public class SocialConfigLinkdin implements SocialConfigurer {

	@Inject
	private DataSource dataSource;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		
		LinkedInConnectionFactory lcf = new LinkedInConnectionFactory(env.getProperty("spring.social.linkdin.appId"),
				env.getProperty("spring.social.linkdin.appSecret"));
		lcf.setScope("r_emailaddress,r_basicprofile");
		
		cfConfig.addConnectionFactory(lcf);
		
	}
	
	
	

	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		String username = null;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
		} catch (Exception e) {
			username = "";
		}

		final String un = username;

		return new UserIdSource() {
			@Override
			public String getUserId() {
				return un;
			}
		};
	}

	
	
	JdbcUsersConnectionRepository repository;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		repository.setConnectionSignUp(new FacebookConnectionSignup());
		return repository;
	}
	
	
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Facebook facebook() {
		
		
		
	    return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public LinkedIn linkedIn() {
		
		
		
	    return connectionRepository().getPrimaryConnection(LinkedIn.class).getApi();
	}
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		
	    return repository.createConnectionRepository(getUserIdSource().getUserId());
	}
	

	


}
*/