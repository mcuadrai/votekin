package com.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forum.domain.Answer;
import com.forum.domain.Opinion;
import com.forum.services.AnswerService;
import com.forum.services.OpinionService;

@Controller
public class PruebaController {
	
	//private static Log log = LogFactory.getLog(PruebaController.class);
	
	@Autowired
	private AnswerService  answerService;
	
	@Autowired
	private OpinionService opinionService;
	
	int contador = 0;
	
	
	@GetMapping("/prueba/buscar/{answerId}")
	public @ResponseBody  Answer  verCantidades(@PathVariable Long   answerId) {
		 return answerService.buscarRespuesta(answerId);
	}	

	@GetMapping("/prueba/buscarSoloObjeto")
	public @ResponseBody  Answer  verCantidades() {
		 Answer answer = new Answer();
		 answer.setPositiveAmount((long) 4);
		 return answer;
	}
	@GetMapping("/prueba/buscarSoloObjeto/{answerId}")
	public @ResponseBody  Answer  verCantidad(@PathVariable Long answerId) {
		 Answer answer = new Answer();
		 answer.setPositiveAmount((long) contador++);
		 return answer;
	}
	
	@GetMapping("/prueba/buscarSoloObjeto2/{answerId}")
	public @ResponseBody  Answer  verCantidad(@PathVariable Long answerId, @RequestParam String eleccion) {
		 Answer answer = new Answer();
		 answer.setPositiveAmount((long) contador++);
		 return answer;
	}
	
	
	@GetMapping("/prueba/buscarSolo")
	public @ResponseBody  String  verSoloCantidad() {
		 return "4";
	}
	
	@GetMapping("/prueba/selectize")
	public String verDemoPage() {
		 return "examples";
	}

	
	@GetMapping("/prueba/hola")
	public String  verPagina(Model model) {
		 model.addAttribute("mensaje","Hola Mundo");
		 return "hola";
	}
	
	@GetMapping("/prueba/z_menu")
	public String  verPaginaMenu() {
		 return "z_menu";
	}
	@GetMapping("/prueba/z_menu_base")
	public String  verMenuBase() {
		 return "z_menu_base";
	}
	
	
	@GetMapping("/prueba-boton")
	public String  verPagina2() {
		 return "prueba-boton";
	}
	
	@GetMapping(value="/prueba/index_rest")
	public String PaginaDePruebaRest() {
		return "index_rest_prueba_ajax";
	}	
	@GetMapping(value="/prueba/recentposts")
	public  @ResponseBody  List<Opinion> test_findAllRecentOpinions() {
	    return opinionService.findAllRecentOpinions();	
	}
	
	


	
	
	
	
}
