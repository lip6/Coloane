package fr.lip6.move.coloane.projects.its.ctl;

public class AU extends CTLBinaryOp {

	public AU(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	public String getOperator() {
		return CTLFormula.AU;
	}
	
	@Override
	public String toString() {
		return "A ( (" + getLeft() + ") U (" + getRight() + ") )";
	}


}
