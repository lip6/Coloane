package fr.lip6.move.coloane.projects.its.ctl;

public class EU extends CTLBinaryOp {

	public EU(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.EU;
	}

	@Override
	public String toString() {
		return "E ( (" + getLeft() + ") U (" + getRight() + ") )";
	}

	
}
