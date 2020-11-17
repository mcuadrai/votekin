package com.forum.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.commands.OpinionCommand;
import com.forum.commands.ServiceUtils;
import com.forum.domain.Opinion;
import com.forum.domain.TagsList;
import com.forum.domain.Theme;
import com.forum.domain.User;
import com.forum.model.OpinionFilterModel;
import com.forum.model.OpinionResponseModel;
import com.forum.responsemodels.ResponseModel;
import com.forum.responsemodels.ResponseModel.Status;
import com.forum.services.IUploadFileService;
import com.forum.services.OpinionService;
import com.forum.services.UserService;
import com.forum.utils.MyUtils;
import com.google.gson.Gson;

@Controller
@RequestMapping("/opinion")
public class OpinionController {
	
	private static Log log = LogFactory.getLog(OpinionController.class);
	
    @Autowired	
	private OpinionService opinionService;

	@Autowired
	private MessageSource messageSource;
    
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private UserService userService;
    
    
	@GetMapping
	public String opinion(Model model) {
		OpinionCommand opinionCmd =  new OpinionCommand();
		
		opinionCmd.setThemes_list(opinionService.findAllThemes());
		
		model.addAttribute("opinion", opinionCmd);
		return "opinion";
	}	

	@PostMapping
	public String doCreate(
			@Valid @ModelAttribute("opinion") OpinionCommand opinionCommand,
			@RequestParam("imageFile") MultipartFile imageFile, 
			BindingResult result,
			RedirectAttributes redirectAttributes, Locale locale) {

		User user = MyUtils.currentUser().get();


		if (!imageFile.isEmpty()) {

			String uniqueImageFilename = null;
			try {
				uniqueImageFilename    = uploadFileService.copy(imageFile,870,470); 

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			redirectAttributes.addFlashAttribute("info", messageSource.getMessage("message.user.photo.upload.success", null, locale) + "'" + uniqueImageFilename + "'");


			if (result.hasErrors())
				return "opinion";


			opinionCommand.setIdUser(user.getId());
			opinionCommand.setImage(uniqueImageFilename);
			
			opinionService.create(opinionCommand, 0, null);
			redirectAttributes.addFlashAttribute("success", messageSource.getMessage("message.createdOpinionSuccess", null, locale));

		}		





		return "redirect:/opinion/recentposts";
	}	
	
	
	@GetMapping("/public-list")
	public String findAllOpinions(Model model) {
		
		//((Object) model).addObject("lists",opinionService.findAllRecentOpinions());
		model.addAttribute("lists", opinionService.findAllRecentOpinions());
		return "public-page";
	}
	
	@GetMapping("/myposts_old")
	public String findAll(Model model) {
		
		User user = MyUtils.currentUser().get();
		model.addAttribute("lists", opinionService.findMyPosts(user.getId()));
		return "myPosts";
	}
	
	
	@GetMapping("/addNewPost")
	public String addNewPostOpinions(@RequestParam(required=false) String text,@RequestParam(required=false) Long[] choiceId,@RequestParam(required=false) Integer pageNumber,Model model, Authentication auth) {
		
		//((Object) model).addObject("lists",opinionService.findAllRecentOpinions());
		//opinionCmd.setThemes_list(opinionService.findAllThemes());
		model.addAttribute("findAllThemes", opinionService.findAllThemes());
		
		OpinionCommand opinionCmd =  new OpinionCommand();
		model.addAttribute("opinion", opinionCmd);
		model.addAttribute("service", new ServiceUtils());
		//model.addAttribute("lists", opinionService.findAllRecentOpinions(text));
		Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		
		if(pageNumber!=null && pageNumber>0) {
			firstPageWithTwoElements = PageRequest.of(pageNumber+1, 10);
		}
		
		//model.addAttribute("lists", opinionService.findAllRecentOpinions(text,choiceId,firstPageWithTwoElements));
		model.addAttribute("lists", null);
		
		
		model.addAttribute("isOwn", false);
		return "addNewPost";
	}
	
	@GetMapping("/recentposts")
	public String findRecentOpinions(@RequestParam(required=false) String text,@RequestParam(required=false) Long[] choiceId,@RequestParam(required=false) Integer pageNumber,Model model, Authentication auth) {
		
		//((Object) model).addObject("lists",opinionService.findAllRecentOpinions());
		//opinionCmd.setThemes_list(opinionService.findAllThemes());
		model.addAttribute("findAllThemes", opinionService.findAllThemes());
		
		OpinionCommand opinionCmd =  new OpinionCommand();
		model.addAttribute("opinion", opinionCmd);
		model.addAttribute("service", new ServiceUtils());
		//model.addAttribute("lists", opinionService.findAllRecentOpinions(text));
		Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		
		if(pageNumber!=null && pageNumber>0) {
			firstPageWithTwoElements = PageRequest.of(pageNumber+1, 10);
		}
		
		//model.addAttribute("lists", opinionService.findAllRecentOpinions(text,choiceId,firstPageWithTwoElements));
		model.addAttribute("lists", null);
		
		
		model.addAttribute("isOwn", false);
		return "recent_posts";
	}
	
	@GetMapping("/findAllTheams")
	public @ResponseBody String findAllTheams() {
		List<Theme> theams = opinionService.findAllThemes();
		
		return ResponseModel.getResponse(Status.SUCCESS, theams, "");
	}
	
	@GetMapping("/findComments")
	public String findComments(@RequestParam(required=false) String text,@RequestParam(required=false) Long[] choiceId,@RequestParam(required=false) Integer pageNumber,Model model, Authentication auth) {
		
		//((Object) model).addObject("lists",opinionService.findAllRecentOpinions());
		//opinionCmd.setThemes_list(opinionService.findAllThemes());
		model.addAttribute("findAllThemes", opinionService.findAllThemes());
		
		OpinionCommand opinionCmd =  new OpinionCommand();
		model.addAttribute("opinion", opinionCmd);
		model.addAttribute("service", new ServiceUtils());
		//model.addAttribute("lists", opinionService.findAllRecentOpinions(text));
		Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		
		if(pageNumber!=null && pageNumber>0) {
			firstPageWithTwoElements = PageRequest.of(pageNumber+1, 10);
		}
		
		//model.addAttribute("lists", opinionService.findAllRecentOpinions(text,choiceId,firstPageWithTwoElements));
		model.addAttribute("lists", null);
		
		
		model.addAttribute("isOwn", false);
		return "find_comments";
	}
	
	@GetMapping("/recentpostsAjax")
	public @ResponseBody String findRecentOpinionsAjax(@RequestParam(required=false) String text,@RequestParam(required=false) Long[] choiceId, @RequestParam(required=false) String[] theams,@RequestParam(required=false) String[] tags,@RequestParam(required=false) Integer pageNumber,Model model, Authentication auth) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		
		if(pageNumber!=null && pageNumber>0) {
			firstPageWithTwoElements = PageRequest.of(pageNumber, 10);
		}
		
		Long userId =  MyUtils.currentUser().get().getId();
		
		List<Opinion> opinions = opinionService.findAllRecentOpinions(text,choiceId,theams,tags,firstPageWithTwoElements);
		
		OpinionFilterModel opinionFilterModel = new OpinionFilterModel();
		
		opinionFilterModel.setChoiceId(choiceId);
		opinionFilterModel.setTags(tags);
		opinionFilterModel.setText(text);
		opinionFilterModel.setTheams(theams);
		opinionFilterModel.setPageable(firstPageWithTwoElements);
		
		OpinionResponseModel responseModel = new OpinionResponseModel();
		responseModel.setFilter(opinionFilterModel);
		responseModel.setList(opinions);
		
		return ResponseModel.getResponse(Status.SUCCESS, responseModel,"",userId);
		
		
	}
	
	@GetMapping("/mypostsAjax")
	public @ResponseBody String myPostOpinionsAjax(@RequestParam(required=false) Integer pageNumber,Model model, Authentication auth) {
		
		Pageable firstPageWithTwoElements = PageRequest.of(0, 10);
		
		if(pageNumber!=null && pageNumber>0) {
			firstPageWithTwoElements = PageRequest.of(pageNumber, 10);
		}
		
		Long userId =  MyUtils.currentUser().get().getId();
		
		return ResponseModel.getResponse(Status.SUCCESS, opinionService.getOpinionByCurrentUser(firstPageWithTwoElements),"",userId);
		
		
	}
	
	@PostMapping("/addPrefered")
	public @ResponseBody String addPreferedFilter(@RequestParam String choices) {
		userService.addPreferedFilter(choices);
		return ResponseModel.getResponse(Status.SUCCESS, "");
	}
	
	@PostMapping("/addThemePrefered")
	public @ResponseBody String addThemePrefered(@RequestParam String theme) {
		userService.addThemePrefered(theme);
		return ResponseModel.getResponse(Status.SUCCESS, "");
	}
	
	
	@GetMapping("/myposts")
	public String findMyPosts(Model model) {
		
		User user = MyUtils.currentUser().get();
		model.addAttribute("title", "My posts");
		model.addAttribute("lists", opinionService.findMyPosts(user.getId()));
		model.addAttribute("isOwn", true);
		return "my_posts";
	}
	
//	@GetMapping("/result/{choiceType}")
//	public String findResult(@PathVariable String choiceType, Model model) {
//		
//		User user = MyUtils.currentUser().get();
////		if (choiceType.equals(1)) {
////			model.addAttribute("title", "My posts");	
////		} else {
////			model.addAttribute("title", "My posts");
////		}
//		//model.addAttribute("lists", opinionService.findFirst20ByChoiceTypeOrderByAmountDesc(user.getId()));
//		
//		model.addAttribute("title", "My posts");
//		model.addAttribute("lists", opinionService.findMyPosts(user.getId()));
//		model.addAttribute("isOwn", true);
//		return "my_posts";
//	}
//	
	
	
	
//	@PostMapping("/ajax/createOpinionAndFindAll")
//	@ResponseBody
//	public List<Opinion> ajaxCreateOpinionAndFindAll(@RequestBody   OpinionListCommand opinion) {
//
//		
//		OpinionCommand opinionCmd =  new OpinionCommand();
//		opinionCmd.setText(opinion.getText());
//		opinionCmd.setThemes(opinion.getThemes());
//		User user = MyUtils.currentUser().get();
//		opinionCmd.setIdUser(user.getId()); 
//		
//		opinionService.create(opinionCmd, 0, null);
//		
//		return opinionService.findAllRecentOpinions();
//		
//	}
	
	@PostMapping("/createOpinionAndFindAll")
	@ResponseBody
	public String createOpinionAndFindAll(@Valid @ModelAttribute("opinion") OpinionCommand opinionCmd, Model model, BindingResult result, SessionStatus status) {

		if (result.hasErrors())
			return ResponseModel.getResponse(Status.FAIL, "");
		
		User user = MyUtils.currentUser().get();
		opinionCmd.setIdUser(user.getId()); 
		opinionService.create(opinionCmd, 0, null);
		
		return ResponseModel.getResponse(Status.SUCCESS, "");
	}
	
	
	@GetMapping("/result/{choiceId}")
	public String findResultByChoiceId(@PathVariable Long[] choiceId, Model model) {
		
		//TODO obtener según id de elección más obtener el lenguaje del PC entonces  retornar traducción de idioma.
		if(choiceId.length==1) {
			model.addAttribute("title", "More "+ opinionService.findChoiceById(choiceId[0]).get().getName());
		}else {
			model.addAttribute("title", "More "+ opinionService.findChoiceById(choiceId[0]).get().getName());
		}
		
		
		model.addAttribute("lists", opinionService.findOpinionsByChoiceId(choiceId));
		model.addAttribute("service", new ServiceUtils());
		
		return "result";
	}
	
	@PostMapping(value="/tagsList")
	public @ResponseBody String getTagsList(@RequestParam String value) {
		
		List<TagsList> tagsList = opinionService.getTagsListByValue(value);
		
		String response = ResponseModel.getResponse(Status.SUCCESS, tagsList, "");
		
		return response;
	}
	
	@GetMapping(value="/tagsList")
	public @ResponseBody List<TagsList> getTagsListSearch(@RequestParam(required=false) String value) {
		if(value==null) {
			value="";
		}
		List<TagsList> tagsList = opinionService.getTagsListByValue(value);
		
		Gson gson = new Gson();
		
		String response = gson.toJson(tagsList);
		
		return tagsList;
	}
	
	@PostMapping(value="/addTags")
	public @ResponseBody String addTagsList(@RequestParam String value) {
		try {
			opinionService.saveTags(value);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		String response = ResponseModel.getResponse(Status.SUCCESS, null, "");
		
		return response;
	}
	
}
