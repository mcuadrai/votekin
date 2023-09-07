package com.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig  {
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
	


//	  @Autowired
//	  private AuthEntryPointJwt unauthorizedHandler;
//
//	  @Bean
//	  public AuthTokenFilter authenticationJwtTokenFilter() {
//	    return new AuthTokenFilter();
//	  }

//	@Bean
//	  public WebSecurityCustomizer webSecurityCustomizer() {
//	    return (web) -> web.ignoring().requestMatchers("/js/**", "/images/**"); 
//	  }
	
	@Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	 @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	
	  //public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
//	// @formatter:off
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		    http
//		    //.csrf(csrf -> csrf.disable())
//		 //       .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//		 //       .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////		        .authorizeHttpRequests(auth -> auth
////		        	  .requestMatchers
////		               ("/", "/css/**", "/fonts/**", "/icons/**", "/js/**", "/images/**", "/locale",
////		                "/signup","/prueba/**", "/forgot-password", "/reset-password/**","/users/**",
////		                "/login*","/signin/**","/signup/**").permitAll()		              
////		              .anyRequest().authenticated()
////		              )
//		        .formLogin((form) -> form
//		      				.loginPage("/login")
//		      				//.defaultSuccessUrl("/")
//		    				.permitAll()
//		    			)
//		        .logout((logout) -> logout.permitAll())
//		    			;
//		              
//		    			
//		             
////				.and().rememberMe()
////				.key(rememberMeKey).rememberMeServices(new TokenBasedRememberMeServices(rememberMeKey, userDetailsService))
////				.and()
////				.exceptionHandling().accessDeniedPage("/error_403")
////		              
////		        );
////       
//	
//		    
////		 http.authenticationProvider(authenticationProvider());
//		 
//		// http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//		 
		
		return http.build();
						
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
