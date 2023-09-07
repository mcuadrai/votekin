package com.forum.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.forum.commands.OpinionCommand;
import com.forum.domain.Choice;
import com.forum.domain.Opinion;
import com.forum.domain.OpinionChoice;
import com.forum.domain.OpinionEvaluacion;
import com.forum.domain.TagsList;
import com.forum.domain.Theme;
import com.forum.model.OpinionFilterModel;
import com.forum.repositories.ChoiceRepository;
import com.forum.repositories.OpinionRepository;
import com.forum.repositories.TagListRepository;
import com.forum.repositories.ThemeRepository;
import com.forum.repositories.TipoEvaluacionRepository;
import com.forum.utils.MyUtils;

import jakarta.annotation.PostConstruct;

@Service("opinionService")
@Transactional(readOnly = true)
public class OpinionServiceImpl implements OpinionService {

	private static Log log = LogFactory.getLog(OpinionServiceImpl.class);

	@Autowired
	private ThemeRepository themeRepository;

	@Autowired
	private OpinionRepository opinionRepository;

	@Autowired
	private TipoEvaluacionRepository tipoEvaluacionRepository;

	@Autowired
	private ChoiceRepository choiceRepository;

	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private TagListRepository tagListRepository;

	@PostConstruct
	public void init() {

		log.info("Inside Post construct");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void create(OpinionCommand opinionCommand, int levelId, Long originOpinionId) {

		Opinion opinion = opinionCommand.toOpinion();

		String[] tags = opinion.getTags().split(",");
		for(String t : tags) {
			try {
				List<TagsList> tl = tagListRepository.findByValue(t);
				if(tl==null || tl.isEmpty()) {
					saveTags(t);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
		OpinionEvaluacion opinionEvaluacion_1 = this.convertFromIdTipoEvaluacion((long) 1);
		OpinionEvaluacion opinionEvaluacion_4 = this.convertFromIdTipoEvaluacion((long) 4);

		List<OpinionEvaluacion> opinionEvaluacions = new ArrayList<OpinionEvaluacion>();
				
		opinionEvaluacions.add(0,opinionEvaluacion_4);
		opinionEvaluacions.add(1,opinionEvaluacion_1);
		
		opinion.setEvaluacionesDeOpinion(opinionEvaluacions);
		
		//opinion.addEvaluation(opinionEvaluacion_1);
		//opinion.addEvaluation(opinionEvaluacion_4);

		opinion.setCreationDate(LocalDateTime.now());
		
		try {
		
			for (int i = 0; i < opinionCommand.getThemes().length; i++) {
				Theme theme = new Theme();
				theme.setId(Long.parseLong(opinionCommand.getThemes()[i]));
				opinion.getThemes().add(theme);
				theme.getOpinions().add(opinion);
			}
		
		}catch (Exception e) {
			
		}

		if (!opinionCommand.getFile().isEmpty()) {
			try {
				String uniqueFileName = uploadFileService.copy(opinionCommand.getFile());
				opinion.setImage(uniqueFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		opinion.setLevelId(levelId);
		opinion.setOriginOpinionId(originOpinionId);

		opinionRepository.save(opinion);

		// WallOpinion opinionWall = new WallOpinion();
		// opinionWall.setOpinion(opinionFinal);
		// opinionWall.setUser(opinionFinal.getUser());
		// opinionWall.setPais(opinionFinal.getPais());
		// wallOpinionRepository.save(opinionWall);

	}

	@Override
	public List<Theme> findAllThemes() {
		return themeRepository.findAll();

	}

	@Override
	public List<Opinion> findMyPosts(Long userId) {
		return opinionRepository.findByUserId(userId);

	}

	@Override
	public List<Opinion> findAllRecentOpinions() {
		return opinionRepository.findFirst10ByOrderByCreationDateDesc();
	}
	
	@Override
	@Deprecated
	public List<Opinion> findAllRecentOpinions(String text) {
		if(text==null) {
			return opinionRepository.findFirst10ByOrderByCreationDateDesc();
		}else {
			return opinionRepository.findFirst10ByTextIgnoreCaseContaining(text);
		}
		
	}
	
	@Override
	public List<Opinion> findAllRecentOpinions(String text, Long[] choiceId,String[] theams,String[] tags,Pageable pageable) {
		
		//List<Opinion> opinions = null;
		
		OpinionFilterModel filterModel = new OpinionFilterModel();
		filterModel.setPageable(pageable);
		filterModel.setText(text);
		filterModel.setChoiceId(choiceId);
		filterModel.setTheams(theams);
		filterModel.setTags(tags);
		
		/*if(text==null && choiceId == null) {
			opinions =  opinionRepository.findByOrderByCreationDateDesc(pageable);
		}else if(text!=null && choiceId == null) {
			opinions =  opinionRepository.findByTextIgnoreCaseContaining(text,pageable);
		}else if(text==null && choiceId != null) {
			opinions =  opinionRepository.fetchOpinionsResultByChoiceId(Arrays.asList(choiceId),pageable.getPageNumber(),pageable.getPageSize());
		}else {
			opinions =  opinionRepository.fetchOpinionsResultByChoiceIdAndText(Arrays.asList(choiceId),text,pageable.getPageNumber(),pageable.getPageSize());
		}*/
		
		
		
		List<Opinion> opinions = opinionRepository.opinionFiltersCustomRepo(filterModel);
		
		return opinions;
	}
	
	
	

	@Override
	public Opinion fetchById(Long opinionId) {
		Optional<Opinion> opinion = opinionRepository.findById(opinionId);
		// Hibernate.initialize(student.getAddress());
		// List<Object> choices =
		// opinionRepository.findAllOpinionChoicesById(opinionId);
		MyUtils.validate(opinion != null, "opinionNotFound");

		// opinion.get
		/*
		 * user.setEditable(isAdminOrSelfLoggedIn(user)); if (!user.isEditable())
		 * user.setEmail("Confidential");
		 */

		// TODO agregar respuestas a opinión. todas las ideas de exploración
		// TODO agregar resultado de exploración de ideas
		return opinion.get();
	}

	@Override
	public List<Opinion> findOpinionsByChoiceId(Long... choiceId) {
		return opinionRepository.fetchOpinionsResultByChoiceId(Arrays.asList(choiceId),0,120);
	}

	@Override
	public List<Opinion> findOpinionsByOpinionIdChoiceId(Long opinionId, Long choiceId) {
		return opinionRepository.fetchOpinionsResultByOpinionIdChoiceId(opinionId, choiceId);
	}

	private OpinionEvaluacion convertFromIdTipoEvaluacion(Long id) {
		OpinionEvaluacion opinionEvaluacion_1 = new OpinionEvaluacion();
		opinionEvaluacion_1.setTipoEvaluacion(tipoEvaluacionRepository.findById(id).get());
		List<Choice> choices = tipoEvaluacionRepository.findById(id).get().getChoices();

		// TODO mejorar, tal vez no es necesario inicializar y asignar individualmente
		for (Choice choice : choices) {
			OpinionChoice opinionChoice = new OpinionChoice();
			opinionChoice.setChoice(choice);
			opinionChoice.setOpinionEvaluacion(opinionEvaluacion_1);
			opinionChoice.setAmount((long) 0);
			opinionEvaluacion_1.addChoice(opinionChoice);
		}

		return opinionEvaluacion_1;
	}

	@Override
	public Optional<Choice> findChoiceById(Long choiceId) {
		return choiceRepository.findById(choiceId);
	}

	@Override
	public List<TagsList> getTagsListByValue(String value) {
		List<TagsList> optionTags = tagListRepository.findByValueIgnoreCaseContaining(value);
		
		return optionTags;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveTags(String tags) {
	
		TagsList tagsList = new TagsList();
		tagsList.setValue(tags);
		tagListRepository.save(tagsList);
		
	}

	@Override
	public List<Opinion> getOpinionByUserId(Long userId, Pageable pageable){
		
		List<Opinion> opinions = opinionRepository.findByUserIdOrderByCreationDateDesc(userId, pageable);
		
		return opinions;
	}
	
	@Override
	public List<Opinion> getOpinionByCurrentUser(Pageable pageable){
		Long userId = MyUtils.currentUser().get().getId();
		return getOpinionByUserId(userId,pageable);
	}

}