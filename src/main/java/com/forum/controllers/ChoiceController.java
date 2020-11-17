package com.forum.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forum.commands.ChoiceCommand;
import com.forum.commands.OpinionChoiceCommand;
import com.forum.domain.Answer;
import com.forum.domain.User;
import com.forum.services.AnswerService;
import com.forum.services.ChoiceService;
import com.forum.utils.MyUtils;

@Controller
public class ChoiceController {
	
	private static Log log = LogFactory.getLog(ChoiceController.class);

	@Autowired
	private ChoiceService  choiceService;
	@Autowired
	private AnswerService  answerService;
	
	/*
	public ChoiceController(ChoiceService  choiceService,  AnswerService  answerService) {

		this.choiceService  = choiceService;
		this.answerService  = answerService;
	}
	*/

	@GetMapping("choice/{answerId}")
	public @ResponseBody Answer choice(@PathVariable Long   answerId, 
			             @RequestParam String choice,
			             @RequestParam Long opinionId) {
		
		ChoiceCommand choiceCommand = new ChoiceCommand();
		choiceCommand.setChoice(choice);
		choiceCommand.setAnswerId(answerId);
		
		choiceService.create(choiceCommand);
		
		//DebateController debateController = new DebateController(opinionService, answerService);
		//return debateController.debate(opinionId, model);
		return verCantidadesRespuesta(answerId);
	}	

	@GetMapping("choice/op/{opinionChoiceId}")
	public @ResponseBody OpinionChoiceCommand chooseChoice (
			             @PathVariable Long   opinionChoiceId
			             ) {
		User currentUser = MyUtils.currentUser().get();
		log.info("invocando choiceService.chooseChoice...");
		choiceService.chooseChoice(opinionChoiceId, currentUser.getId());
		
		return choiceService.findChoiceResult(opinionChoiceId);
		
		
	}	
	

	@GetMapping("/choice/buscar2/{answerId}")
	public @ResponseBody  Answer  verCantidades(@PathVariable Long   answerId) {
			 Answer answer = new Answer();
			 answer.setId(answerId);
			 answer.setPositiveAmount((long) 4);
			 return answer;
	}	
	
	private Answer  verCantidadesRespuesta(long answerId) {
		 return answerService.buscarRespuesta(answerId);
	}
	
	
	
	
	



	
	
	
	
}
