package com.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.forum.services.IUploadFileService;



@SpringBootApplication
//@EnableSpringDataWebSupport
//@EnableAsync
public class ForumApplication extends SpringBootServletInitializer implements CommandLineRunner  {

	@Autowired
	IUploadFileService uploadFileService;
	
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ForumApplication.class);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//uploadFileService.deleteAll();
		uploadFileService.init();
	}
}
