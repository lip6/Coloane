package fr.lip6.move.coloane.api.cami.input.menus;

/*
 'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
 question_type=number? ',' question_behavior=number? ',' 
 set_item=number? ','  historic=number? ',' stop_authorized=number? ',' 
 ouput_formalism=CAMI_STRING? ',' active=number? ')'
 */
public final class QuestionAdd {

	private String parentMenu;
	private String entryName;
	private Integer questionType;
	private Integer questionBehavior;
	private Integer setItem;
	private Integer historic;
	private Integer stopAuthorized;
	private String outputFormalism;
	private Integer activeNumber;

	public String getParentMenu() {
		return parentMenu;
	}

	public String getEntryName() {
		return entryName;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public Integer getQuestionBehavior() {
		return questionBehavior;
	}

	public Integer getSetItem() {
		return setItem;
	}

	public Integer getHistoric() {
		return historic;
	}

	public Integer getStopAuthorized() {
		return stopAuthorized;
	}

	public String getOutputFormalism() {
		return outputFormalism;
	}

	public Integer getActiveNumber() {
		return activeNumber;
	}

}
