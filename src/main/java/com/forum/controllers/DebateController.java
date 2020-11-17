package com.forum.controllers;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.AnswerCommand;
import com.forum.commands.OpinionCommand;
import com.forum.commands.ServiceUtils;
import com.forum.domain.Opinion;
import com.forum.domain.User;
import com.forum.responsemodels.ResponseModel;
import com.forum.responsemodels.ResponseModel.Status;
import com.forum.services.AnswerService;
import com.forum.services.OpinionService;
import com.forum.utils.MyUtils;

@Controller
@RequestMapping("/debate")
public class DebateController {
	
	private static Log log = LogFactory.getLog(DebateController.class);
	
	@Autowired
	private OpinionService opinionService;
	@Autowired
	private AnswerService answerService;
	
	/*
	public DebateController(OpinionService opinionService, AnswerService answerService) {

		this.opinionService = opinionService;
		this.answerService = answerService;
	}
	*/
	@Autowired
	private MessageSource messageSource;
	

	@GetMapping("/{opinionId}")
	public String debate(@PathVariable Long opinionId, Model model) {
		
	    
		model.addAttribute("findAllThemes", opinionService.findAllThemes());
		
		//Get Opinion
		
		//Opinion opinion = getOriginOpinion(opinionId);
		
		//opinion = getOpinionWithAnswers(opinion);
		
		//Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		//answer
		model.addAttribute("answer",opinionService.fetchById(opinionId));
		//model.addAttribute("comments",answerService.findCommentsByOpinionId(opinionId,firstPageWithTwoElements));
		model.addAttribute("service", new ServiceUtils());
		OpinionCommand opinionCmd = new OpinionCommand();
		opinionCmd.setOpinionId(opinionId);
		opinionCmd.setOriginOpinionId(opinionId);
		model.addAttribute("opinion", opinionCmd);
		
		return "debate";
	}
	
	@GetMapping("/ajax/{opinionId}/{pageNumber}")
	public @ResponseBody String findRecentOpinionsAjax(@PathVariable Long opinionId,@PathVariable Integer pageNumber) {
		
		
		Long userId =  MyUtils.currentUser().get().getId();
		
		Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		
		if(pageNumber!=null && pageNumber>0) {
			firstPageWithTwoElements = PageRequest.of(pageNumber, 10);
		}
		
		return ResponseModel.getResponse(Status.SUCCESS, answerService.findCommentsByOpinionId(opinionId,firstPageWithTwoElements),"",userId);
		
		
	}
	
	private Opinion getOriginOpinion(long id) {
		Opinion opinion = opinionService.fetchById(id);
		if(opinion.getOriginOpinionId()!=null) {
			opinion = getOriginOpinion(opinion.getOriginOpinionId());
		}
		
		return opinion;
		
	}
	
	
	/*private Opinion getOpinionWithAnswers(Opinion opinion) {
		
		List<Opinion> answers = answerService.findCommentsByOpinionId(opinion.getId());
		
		
		
		for(Opinion ans : answers) {
			getOpinionWithAnswers(ans);
			
		}
		
		opinion.setComments(answers);
		
		return opinion;
	}*/
	
	@GetMapping("/old/{opinionId}")
	public String debateOld(@PathVariable Long opinionId, Model model) {
		model.addAttribute(opinionService.fetchById(opinionId));
		//model.addAttribute("answers",answerService.findExploreIdeas(opinionId));
		model.addAttribute("positiveResults",answerService.findPositiveResults(opinionId));
		model.addAttribute("negativeResults",answerService.findNegativeResults(opinionId));
		model.addAttribute("interestingResults",answerService.findInterestingResults(opinionId));
		
		model.addAttribute("answer", new AnswerCommand());
		
		return "debate";
	}	


	@PostMapping("/createAnswer")
	@ResponseBody
	public String createAnswer(
			@Valid @ModelAttribute("answer") OpinionCommand opinionCommand,
			BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale) {
		
		if (result.hasErrors())
			return "debate";
            //return "redirect:/debate/"+opinionCommand.getOpinionId();
		
		User user = MyUtils.currentUser().get();
		opinionCommand.setIdUser(user.getId()); 
		opinionService.create(opinionCommand, opinionCommand.getLevelId()+1, opinionCommand.getOriginOpinionId());
		
		//redirectAttributes.addFlashAttribute("success", messageSource.getMessage("message.createdOpinionSuccess",null,locale));
		return ResponseModel.getResponse(Status.SUCCESS, "");
	}		
	
	
	
	
	
	
}
