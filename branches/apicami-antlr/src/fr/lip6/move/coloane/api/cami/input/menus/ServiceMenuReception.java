package fr.lip6.move.coloane.api.cami.input.menus;

import java.util.Collection;

public final class ServiceMenuReception {

	private MenuName name;
	private Collection<QuestionAdd> questions;

	public ServiceMenuReception(MenuName name, Collection<QuestionAdd> questions) {
		this.name = name;
		this.questions = questions;
	}

	public MenuName getName() {
		return name;
	}

	public Collection<QuestionAdd> getQuestions() {
		return questions;
	}

}
