package fr.lip6.move.coloane.projects.its.ctl;

public abstract class CTLUnaryOp implements CTLFormula {
	
	private CTLFormula operand;

	public CTLUnaryOp(CTLFormula operand) {
		this.operand = operand;
	}
	
	public CTLFormula getOperand() {
		return operand;
	}
	
	@Override
	public String toString() {
		return getOperator() + " (" + getOperand() + ")";
	}

}
