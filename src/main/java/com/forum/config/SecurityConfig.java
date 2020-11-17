package com.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	
	private String rememberMeKey;
	
	public SecurityConfig(UserDetailsService userDetailsService,
			@Value("${rememberMeKey}") String rememberMeKey) {

		this.userDetailsService = userDetailsService;
		this.rememberMeKey = rememberMeKey;
	}

	/*@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;*/

	/*@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private FacebookConnectionSignup facebookConnectionSignup;*/

	
	
//	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.requiresChannel().and()
		.csrf().disable().
		authorizeRequests()
		
		.antMatchers("/", "/css/**", "/fonts/**", "/icons/**", "/js/**", "/images/**", "/locale").permitAll()
		.antMatchers("/signup","/prueba/**", "/forgot-password", "/reset-password/**","/users/**").permitAll()
		.antMatchers("/login*","/signin/**","/signup/**").permitAll()
		//.antMatchers(HttpMethod.GET,"/actuator/health", "/actuator/info").permitAll()
		//.mvcMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		    .formLogin()
		        //.successHandler(successHandler)
		        .loginPage("/login")
		        .defaultSuccessUrl("/")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and().rememberMe()
		.key(rememberMeKey).rememberMeServices(new TokenBasedRememberMeServices(rememberMeKey, userDetailsService))
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
		
		
						
	}
	
	/*@Bean
	public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection = new SecurityCollection();
	            collection.addPattern("/*");
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
	    return tomcat;
	}

	private Connector redirectConnector() {
	    Connector connector = new Connector(
	            TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
	    connector.setScheme("http");
	    connector.setPort(8080);
	    connector.setSecure(false);
	    connector.setRedirectPort(8443);
	    return connector;
	}*/
	
//	@Autowired
//	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
//	{
//		
////		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
////		
////		build.inMemoryAuthentication()
//////		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
////		.withUser(users.username("mcuadrai@gmail.com").password("12345").roles("USER"));
//		
//		//build.jdbcAuthentication()
//		//.dataSource(dataSource)
//		build.userDetailsService(userDetailsService)
//		.passwordEncoder(passwordEncoder);
//		//.usersByUsernameQuery("select username, password, enabled from users where username =?")
//		//.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?")
//		
//		
//	}
	
	
	 /*@Bean
	    public ProviderSignInController providerSignInController() {
	        ((JdbcUsersConnectionRepository) usersConnectionRepository)
	          .setConnectionSignUp(facebookConnectionSignup);

	        
	        ProviderSignInController controller = new ProviderSignInController(
	  	          connectionFactoryLocator, 
	  	          usersConnectionRepository, 
	  	          new FacebookSignInAdapter());
	        
	        controller.setApplicationUrl("https://votekin.wm-desk.com");
	        
	        
	        return controller;
	    }*/
	
}
