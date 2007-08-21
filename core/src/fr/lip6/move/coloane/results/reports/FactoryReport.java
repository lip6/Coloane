package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

/**
 * Classe definissant la Factory pour fabriquer les differents reports de chaque service
 * @author cdcharles
 */
public class FactoryReport {

	private static final String SER_SYNTAXCHECKER = "Petri net syntax checker";

	private IResultsCom resultCom;

	public FactoryReport(IResultsCom results) {
		this.resultCom = results;
	}

	public final IReport createReport() {
		if (this.resultCom.getQuestion().equalsIgnoreCase(SER_SYNTAXCHECKER)) {
			return new SyntaxCheckerReport(this.resultCom);
		}

		return new GenericServicesReport(resultCom);
	}
}
