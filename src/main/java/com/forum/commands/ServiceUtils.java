package com.forum.commands;

import java.util.List;

import com.forum.domain.ChoiceVote;
import com.forum.domain.OpinionChoice;

public class ServiceUtils {

	public static boolean isUserCheckedOnOpinion(OpinionChoice opinionChoice,long userId) {
		try {
		List<ChoiceVote> choiceVotes = opinionChoice.getChoiceVotes();
		
		for(ChoiceVote choiceVote : choiceVotes) {
			try {
				if(choiceVote.getUser().getId()==userId) {
					return true;
				}
			}catch (Exception e) {
				
			}
		}
		return false;
		}catch (Exception e) {
			return false;
		}
	}
	
}
