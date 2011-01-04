package fr.lip6.move.coloane.projects.its.ctl;

public class EG extends CTLUnaryOp {

	public EG(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.EG;
	}

}
