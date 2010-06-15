package fr.lip6.move.coloane.projects.its.ctl;

public abstract class CTLBinaryOp implements CTLFormula {
	
	private CTLFormula left;
	private CTLFormula right;

	public CTLBinaryOp(CTLFormula left, CTLFormula right) {
		this.left = left;
		this.right = right;
	}
	
	public CTLFormula getLeft () {
		return left;
	}
	
	public CTLFormula getRight () {
		return right;
	}
}
