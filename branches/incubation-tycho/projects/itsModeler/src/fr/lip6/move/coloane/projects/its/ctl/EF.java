package fr.lip6.move.coloane.projects.its.ctl;

public class EF extends CTLUnaryOp {

	public EF(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.EF;
	}

}
