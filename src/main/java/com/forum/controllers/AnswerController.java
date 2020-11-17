package com.forum.controllers;



import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.AnswerCommand;
import com.forum.domain.Answer;
import com.forum.domain.User;
import com.forum.services.AnswerService;
import com.forum.utils.MyUtils;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	
	private static Log log = LogFactory.getLog(AnswerController.class);
	
	private AnswerService answerService;

	@Autowired  
	public AnswerController(AnswerService answerService) {

		this.answerService = answerService;
	}
	
	@Autowired
	private MessageSource messageSource;
    
	
	@GetMapping("/{opinionId}")
	public String answer(Model model) {
		
		model.addAttribute("answer", new AnswerCommand());
		return "answer";
	}
	
	
	@PostMapping("/create2")
	public String doAnswer(
			@Validated AnswerCommand answerCommand,
			BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale
			) {
		
		
		if (result.hasErrors())
			return "answer";
		
		
		
		answerCommand.setLevelId(answerCommand.getLevelId()+1);
		//answerCommand.setOpinionId(opinionId);
		
		//answerCommand.setOriginAnswerId(originAnswerId.orElse(null));
		User user = MyUtils.currentUser().get();
		answerCommand.setIdUser(user.getId());
		
		log.info("creando respuesta. nivel="+answerCommand.getLevelId()+": respuesta de origen="+answerCommand.getOriginAnswerId());
	
		answerService.create(answerCommand);
		redirectAttributes.addFlashAttribute("success", messageSource.getMessage("message.answerSuccess", null, locale));
				
		return "redirect:/debate/"+answerCommand.getOriginAnswerId();
		
	}
	
	
	
	
	@PostMapping("/ajax")
	public @ResponseBody Answer answerAjax(
			@RequestParam(value="opinionId") Long opinionId,
			@RequestParam(value="levelId") Integer levelId,
			@RequestParam(value="originAnswerId") Long    originAnswerId,
			@RequestParam(value="texto") String   texto
			) {
		
		AnswerCommand answerCommand = new AnswerCommand();
		
		answerCommand.setLevelId(levelId);
		answerCommand.setOpinionId(opinionId);
		answerCommand.setOriginAnswerId(originAnswerId);
		answerCommand.setText(texto);
				
		User user = MyUtils.currentUser().get();
		answerCommand.setIdUser(user.getId());
		
		log.info("creando respuesta. nivel="+answerCommand.getLevelId()+": respuesta de origen="+answerCommand.getOriginAnswerId());
	
		Answer answer = answerService.createAnswer(answerCommand);
		log.info("answer.getId()="+answer.getId());
		
		return answer;
	}
	
	
	
	
}
