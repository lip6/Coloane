package fr.lip6.move.coloane.projects.its.ctl;

public class CTLConstant implements CTLFormula {
	
	private boolean value;

	CTLConstant(boolean b) {
		this.value = b;
	}

	public String getOperator() {
		return CTLFormula.CONSTANT;
	}

	public String toString() {
		if (value) {
			return "TRUE";
		} else {
			return "FALSE";
		}
	}
}
