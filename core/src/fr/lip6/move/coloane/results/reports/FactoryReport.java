package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

/**
 * Classe definissant la Factory pour fabriquer les differents reports de chaque service
 * @author cdcharles
 */
public class FactoryReport {

	private static final String SER_SYNTAXCHECKER = "Petri net syntax checker";
	private static final String SER_COMPUTEBOUNDS = "Compute structural bounds";
	private static final String SER_PPOSITIVEINV = "P-positive invariants";
	private static final String SER_TPOSITIVEINV = "T-positive invariants";
	private static final String SER_PINV = "P-invariants";
	private static final String SER_TINV = "T-invariants";
	private static final String SER_COLOREDINV = "Colored invariants";
	private static final String SER_LINEARCHARVERBOSE = "Linear characterization (verbose)";
	private static final String SER_LINEARCHAR = "Linear characterization";
	private static final String SER_SAFETY = "Is the net structuraly safe?";
	private static final String SER_BOUNDED = "Is the net structurally bounded?";
	private static final String SER_COLORSAFETY = "Is the colored structurally net safe (by unfolding)?";
	private static final String SER_COLORBOUNDED = "Is the colored net structurally bounded?";
	private static final String SER_SYPHON = "Minimal syphon ";
	private static final String SER_TRAPS = "Minimal traps ";
	private static final String SER_BDDSYPHON = "BDD based Minimal syphon";
	private static final String SER_BDDTRAPS = "BDD based Minimal traps";
	private static final String SER_SYPHONUNFOLD = "Minimal syphon by unfolding";
	private static final String SER_TRAPSUNFOLD = "Minimal traps by unfolding";

	private IResultsCom resultCom;

	public FactoryReport(IResultsCom results) {
		this.resultCom = results;
	}

	public final IReport createReport() {
		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_SYNTAXCHECKER)) {
			return new SyntaxCheckerReport(this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_COMPUTEBOUNDS)) {
			return new StructuralBounds(this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_PPOSITIVEINV)) {
			return new InvariantsReport(SER_PPOSITIVEINV, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_TPOSITIVEINV)) {
			return new InvariantsReport(SER_TPOSITIVEINV, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_PINV)) {
			return new InvariantsReport(SER_PINV, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_TINV)) {
			return new InvariantsReport(SER_TINV, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_LINEARCHARVERBOSE)) {
			return new LinerCharacterizationReport(SER_LINEARCHARVERBOSE, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_LINEARCHAR)) {
			return new LinerCharacterizationReport(SER_LINEARCHAR, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_COLOREDINV)) {
			return new InvariantsReport(SER_COLOREDINV, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_SAFETY)) {
			return new SafetyNetReport(SER_SAFETY, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_BOUNDED)) {
			return new SafetyNetReport(SER_BOUNDED, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_COLORSAFETY)) {
			return new SafetyNetReport(SER_COLORSAFETY, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_COLORBOUNDED)) {
			return new InvariantsReport(SER_COLORBOUNDED, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_SYPHON)) {
			return new SyphonTrapsReport(SER_SYPHON, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_TRAPS)) {
			return new SyphonTrapsReport(SER_TRAPS, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_BDDSYPHON)) {
			return new SyphonTrapsReport(SER_BDDSYPHON, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_BDDTRAPS)) {
			return new SyphonTrapsReport(SER_BDDTRAPS, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_SYPHONUNFOLD)) {
			return new SyphonTrapsReport(SER_SYPHONUNFOLD, this.resultCom);
		}

		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_TRAPSUNFOLD)) {
			return new SyphonTrapsReport(SER_TRAPSUNFOLD, this.resultCom);
		}

		return new GenericReport("No results", this.resultCom);
	}
}
