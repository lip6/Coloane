package fr.lip6.move.coloane.projects.its.ctl;

public class AX extends CTLUnaryOp {

	public AX(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.AX;
	}

}
