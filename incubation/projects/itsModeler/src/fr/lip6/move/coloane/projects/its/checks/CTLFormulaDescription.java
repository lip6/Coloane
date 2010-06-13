package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

public class CTLFormulaDescription extends SimpleObservable {

	private String name="formula";
	private String comments="New formula";
	private String ctlFormula="TRUE;";
	private CTLCheckService parent;
	
	
	public CTLFormulaDescription(String name, String formula, String comments, CTLCheckService parent) {
		this.name = name;
		this.comments = comments;
		this.ctlFormula = formula;
		this.parent = parent;
	}
	
	public void setComments(String comments) {
		if (!this.comments.equals(comments)) {
			this.comments = comments;
			notifyObservers();
		}
	}
	
	public void setFormula(String formula) {
		if (!formula.equals(ctlFormula)) {
			ctlFormula = formula;
			notifyObservers();
		}
	}
	
	public void setName(String name) {
		if (! this.name.equals(name)) {
			this.name = name;
			notifyObservers();
		}
	}
	
	public String getComments() {
		return comments;
	}
	public String getCtlFormula() {
		return ctlFormula;
	}
	public String getName() {
		return name;
	}

	public CTLCheckService getParent() {
		return parent;
	}
	
}
