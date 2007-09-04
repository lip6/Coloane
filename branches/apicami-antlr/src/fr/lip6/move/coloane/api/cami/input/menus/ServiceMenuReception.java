package fr.lip6.move.coloane.api.cami.input.menus;

import java.util.Collection;

public final class ServiceMenuReception {

	public MenuName name;
	public Collection<QuestionAdd> questions;

	public ServiceMenuReception(MenuName name, Collection<QuestionAdd> questions) {
		this.name = name;
		this.questions = questions;
	}

}
