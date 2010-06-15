package fr.lip6.move.coloane.projects.its.ctl;

public abstract class CTLInfixBinop extends CTLBinaryOp {

	public CTLInfixBinop(CTLFormula left, CTLFormula right) {
		super(left, right);
	}

	
	@Override
	public String toString() {
		return "("+ getLeft() + ")" + getOperator()+ "("+ getRight() +")";
	}
}
