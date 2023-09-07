package com.forum.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.forum.commands.ChoiceCommand;
import com.forum.commands.OpinionChoiceCommand;
import com.forum.domain.Answer;
import com.forum.domain.Answer_User;
import com.forum.domain.ChoiceVote;
import com.forum.domain.OpinionChoice;
import com.forum.domain.User;
import com.forum.repositories.AnswerRepository;
import com.forum.repositories.Answer_UserRepository;
import com.forum.repositories.ChoiceVoteRepository;
import com.forum.repositories.OpinionChoiceRepository;

import jakarta.annotation.PostConstruct;

@Service("choiceService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ChoiceServiceImpl implements ChoiceService {
	
	private final static Log log = LogFactory.getLog(ChoiceServiceImpl.class);
	
	@Autowired	
	private Answer_UserRepository answer_UserRepository;
	@Autowired	
	private AnswerRepository      answerRepository;
	@Autowired	
	private OpinionChoiceRepository  opinionChoiceRepository;
	@Autowired	
	private ChoiceVoteRepository  choiceVoteRepository;
	
	/*
	public ChoiceServiceImpl(Answer_UserRepository choiceRepository, AnswerRepository  answerRepository, OpinionChoiceRepository opinionChoiceRepository, ChoiceVoteRepository choiceVoteRepository) {

		this.answer_UserRepository = choiceRepository;
		this.answerRepository      = answerRepository;
		this.opinionChoiceRepository = opinionChoiceRepository;
		this.choiceVoteRepository   = choiceVoteRepository;
	}
	*/
	
	@PostConstruct
	public void init() {
		
		log.info("Inside Post construct ChoiceService");
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void create(ChoiceCommand choiceCommand) {
		
		Answer_User answer_User = choiceCommand.toAnswer_User();
		Answer answerIn = new Answer();
		answerIn.setId(answer_User.getAnswer().getId());
		
		

		Answer_User answer_user_old = answer_UserRepository.findByAnswerIdAndUserId(answer_User.getAnswer().getId(), answer_User.getUser().getId());
		if (answer_user_old != null ) {
			
			answer_UserRepository.delete(answer_user_old);
			Answer answerOld = answerRepository.findById(answerIn.getId()).get();
			switch (answer_user_old.getChoice()) {
			case "p":answerOld.setPositiveAmount(answerOld.getPositiveAmount()-1);break; 
			case "n":answerOld.setNegativeAmount(answerOld.getNegativeAmount()-1);break;
			case "i":answerOld.setInterestingAmount(answerOld.getInterestingAmount()-1);break;
			}
			answerRepository.save(answerOld);
			
		}
		// answer.setCreationDate(LocalDate.now());
		Answer answerCero = new Answer();
		answerCero.setPositiveAmount((long) 0);
		answerCero.setNegativeAmount((long) 0);
		answerCero.setInterestingAmount((long) 0);
		
		Answer answerSelected = answerRepository.findById(answerIn.getId()).orElse(answerCero);
		
		if (answer_User.getChoice().equals("p")) {
			answerSelected.setPositiveAmount(answerSelected.getPositiveAmount()+1);	
		}
		if (answer_User.getChoice().equals("n")) {
			answerSelected.setNegativeAmount(answerSelected.getNegativeAmount()+1);	
		}
		if (answer_User.getChoice().equals("i")) {
			answerSelected.setInterestingAmount(answerSelected.getInterestingAmount()+1);	
		}
		
		answerRepository.save(answerSelected);

		answer_UserRepository.save(answer_User);
		
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void chooseChoice(Long opinionChoiceId, Long userId) {
		
		log.info("Ingresando a método chooseChoice en Servicio");
		
		OpinionChoice opinionChoiceCurrent = opinionChoiceRepository.findById(opinionChoiceId).get();
		
		if (opinionChoiceCurrent.getOpinionEvaluacion().getTipoEvaluacion().getSonTodosXOR()) {
			
			ChoiceVote votoExistente = choiceVoteRepository.findByOpinionChoiceIdAndUserId(opinionChoiceId, userId);
			if (votoExistente != null) {
				if(opinionChoiceCurrent.getAmount()>0) {
					opinionChoiceCurrent.setAmount(opinionChoiceCurrent.getAmount()-1);
				}else {
					opinionChoiceCurrent.setAmount(0L);
				}
				choiceVoteRepository.delete(votoExistente);
				opinionChoiceRepository.save(opinionChoiceCurrent);
			} else {
				
				List<ChoiceVote> choiceVotesDelete = new ArrayList<>();
				
				List<OpinionChoice> choices =   opinionChoiceRepository.findByOpinionEvaluacionIdAndUserId(opinionChoiceCurrent.getOpinionEvaluacion().getId(), userId);
				for (OpinionChoice opinionChoice2 : choices) {
						if(opinionChoice2.getAmount()>0) {
							opinionChoice2.setAmount(opinionChoice2.getAmount()-1);
							
						}else {
							opinionChoice2.setAmount(0L);
	                        
	        				//opinionChoiceRepository.save(opinionChoice2);
						}
						
						ChoiceVote v = choiceVoteRepository.findByOpinionChoiceIdAndUserId(opinionChoice2.getId(), userId);
						choiceVoteRepository.delete(v);
        				opinionChoiceRepository.save(opinionChoice2);
        				
                        /*for(ChoiceVote choiceVote: opinionChoice2.getChoiceVotes()) {
                        	
                        	//ChoiceVote cv = choiceVoteRepository.getOne(choiceVote.getId());
                        	ChoiceVote vote = new ChoiceVote();
                        	vote.setId(choiceVote.getId());
                        	choiceVotesDelete.add(vote);
                        	//choiceVoteRepository.deleteById(choiceVote.getId());
                        	opinionChoiceCurrent.getChoiceVotes().remove(choiceVote);
                        }*/
        				
        				
				}
				

				/*List<ChoiceVote> votosDeCiudadano = choiceVoteRepository.findByOpinionEvaluacionIdAndUserId(opinionChoiceCurrent.getOpinionEvaluacion().getId(), userId);
				choiceVoteRepository.deleteAll(votosDeCiudadano);*/
				
				/*OpinionChoice opinionChoiceNew = new OpinionChoice();
				opinionChoiceNew.setId(opinionChoiceId);
				opinionChoiceNew.setChoice(opinionChoiceCurrent.getChoice());
				opinionChoiceNew.setOpinionEvaluacion(opinionChoiceCurrent.getOpinionEvaluacion());
				opinionChoiceNew.setAmount(opinionChoiceCurrent.getAmount().longValue()+1);*/
				
				opinionChoiceCurrent.setAmount(opinionChoiceCurrent.getAmount().longValue()+1);
				
				
				
				ChoiceVote choiceVoteNew = new ChoiceVote();
				User user = new User();
				user.setId(userId);
				choiceVoteNew.setUser(user);
				
				opinionChoiceCurrent.addVote(choiceVoteNew);
				
				opinionChoiceRepository.save(opinionChoiceCurrent);
				
				//choiceVoteRepository.deleteAll(choiceVotesDelete);
			}
			
          			
			
		} 
		else {
			
			ChoiceVote votoExistente = choiceVoteRepository.findByOpinionChoiceIdAndUserId(opinionChoiceId, userId);
			
			
			if (votoExistente != null) {
				//log.debug("Voto existe");
				//log.debug("cantidad actual="+opinionChoiceCurrent.getAmount());
				OpinionChoice opinionChoiceNew = new OpinionChoice();
				opinionChoiceNew.setId(opinionChoiceId);
				opinionChoiceNew.setChoice(opinionChoiceCurrent.getChoice());
				opinionChoiceNew.setOpinionEvaluacion(opinionChoiceCurrent.getOpinionEvaluacion());
				opinionChoiceNew.setAmount(opinionChoiceCurrent.getAmount().longValue()-1);
				
				choiceVoteRepository.delete(votoExistente);
				//log.debug("Borrando voto");
				opinionChoiceRepository.save(opinionChoiceNew);
				
			} else {
				//log.debug("Voto No existe");
				//log.debug("cantidad actual="+opinionChoiceCurrent.getAmount());
				
				
				//OpinionChoice opinionChoiceNew = new OpinionChoice();
				//opinionChoiceNew.setId(opinionChoiceId);
				//opinionChoiceNew.setChoice(opinionChoiceCurrent.getChoice());
				//opinionChoiceNew.setOpinionEvaluacion(opinionChoiceCurrent.getOpinionEvaluacion());
				opinionChoiceCurrent.setAmount(opinionChoiceCurrent.getAmount().longValue()+1);
				
				ChoiceVote choiceVoteNew = new ChoiceVote();
				User user = new User();
				user.setId(userId);
				choiceVoteNew.setUser(user);
				opinionChoiceCurrent.addVote(choiceVoteNew);
				
								
				opinionChoiceRepository.save(opinionChoiceCurrent);
			}   

			
			
			
			
		}

	        	
 
	}
	@Override
	public OpinionChoiceCommand findChoiceResult(Long opinionChoiceId) {
		
		OpinionChoice opinionChoice = opinionChoiceRepository.findById(opinionChoiceId).get();
		
		OpinionChoiceCommand opinionChoiceCommand = new OpinionChoiceCommand();
		if (opinionChoice.getOpinionEvaluacion().getTipoEvaluacion().getSonTodosXOR()) {
			opinionChoiceCommand.setSonExcluyentes(Boolean.TRUE);
			opinionChoiceCommand.setId((long) 0);
			opinionChoiceCommand.setAmount((long) 0);
			List<OpinionChoice> choicesDB = opinionChoiceRepository.findByOpinionEvaluacionId(opinionChoice.getOpinionEvaluacion().getId());
			List<OpinionChoiceCommand> choices = new ArrayList<OpinionChoiceCommand>();
			
			for (OpinionChoice opinionChoiceTmp : choicesDB) {
				OpinionChoiceCommand choiceCmd = new OpinionChoiceCommand();
				choiceCmd.setId(opinionChoiceTmp.getId());
				choiceCmd.setAmount(opinionChoiceTmp.getAmount());
				choices.add(choiceCmd);
			}
			opinionChoiceCommand.setChoices(choices);
			
		} else {
			opinionChoiceCommand.setSonExcluyentes(Boolean.FALSE);
			opinionChoiceCommand.setId(opinionChoice.getId());
			opinionChoiceCommand.setAmount(opinionChoice.getAmount());
		}
		
       return opinionChoiceCommand;	
	}
	

}