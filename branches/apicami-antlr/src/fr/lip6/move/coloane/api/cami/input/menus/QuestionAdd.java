package fr.lip6.move.coloane.api.cami.input.menus;

/*
'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
question_type=number? ',' question_behavior=number? ',' 
set_item=number? ','  historic=number? ',' stop_authorized=number? ',' 
ouput_formalism=CAMI_STRING? ',' active=number? ')'
*/
public final class QuestionAdd {

	public String parentMenu;
	public String entryName;
	public Integer questionType;
	public Integer questionBehavior;
	public Integer setItem;
	public Integer historic;
	public Integer stopAuthorized;
	public String outputFormalism;
	public Integer activeNumber;
	
	
}
