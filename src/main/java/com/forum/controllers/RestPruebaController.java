package com.forum.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forum.commands.Demo;

@RestController
public class RestPruebaController {
	
	private static Log log = LogFactory.getLog(RestPruebaController.class);

	@RequestMapping(value="/prueba/getdata", method=RequestMethod.GET)
	public ResponseEntity<Object> getData() {
		Demo demo = new Demo();
		demo.setId("1");
		demo.setName("talk2Amareswaran");
		return new ResponseEntity<>(demo,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/prueba/postdata", method=RequestMethod.POST)
	public Demo postData(@RequestBody Demo demo) {
		log.debug("demo id:"+demo.getId());
		log.debug("demo name:"+demo.getName());
		return demo;
	}	
//	}
//	public ResponseEntity<Object> postData(@RequestBody Demo demo) {
//		log.debug("demo id:"+demo.getId());
//		log.debug("demo name:"+demo.getName());
//		return new ResponseEntity<>(demo, HttpStatus.OK);
//	}	
	
	
	
}
