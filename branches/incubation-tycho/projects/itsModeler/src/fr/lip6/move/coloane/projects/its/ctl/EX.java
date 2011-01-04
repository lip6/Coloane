package fr.lip6.move.coloane.projects.its.ctl;

public class EX extends CTLUnaryOp {

	public EX(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.EX;
	}

}
