package fr.lip6.move.coloane.projects.its.ctl;

public class AG extends CTLUnaryOp {

	public AG(CTLFormula operand) {
		super(operand);
	}

	public String getOperator() {
		return CTLFormula.AG;
	}

}
