package com.forum.services;

import com.forum.commands.ChoiceCommand;
import com.forum.commands.OpinionChoiceCommand;

public interface ChoiceService {

	
	void create(ChoiceCommand choiceCommand);

	void chooseChoice(Long opinionChoiceId, Long ciudadanoId);



	OpinionChoiceCommand findChoiceResult(Long opinionChoiceId);
}
