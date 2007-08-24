package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;

public class SyntaxCheckerReport extends Report {

	/**
	 * Constructeur du rapport
	 * @param results
	 */
	public SyntaxCheckerReport(IResultsCom results) {
		super("Petri Net Syntax Checker", results);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.results.reports.Report#buildReport()
	 */
	@Override
	protected final void buildReport() {
		String description = "";

		// Si il n'y a pas de resultat : Aucun probleme !
		if (getResultsCom().getSubResults().size() == 0) {
			getResultList().add(new Result("Your model is correct !", ""));
		}

		// Parcours de tous les DE-FE
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			String label = sr.getCmdRT().get(0);

			if (label.equalsIgnoreCase("List of unnamed places.")) {
				description = "This place is unnamed";
			} else if (label.equalsIgnoreCase("List of unnamed transitions.")) {
				description = "This transition is unnamed";
			} else {
				for (String desc : sr.getCmdRT()) {	description += desc; }
			}

			// Parcours de tous les objets mis en valeur
			for (String object : sr.getCmdRO()) {
				getResultList().add(new Result(object, description));
			}
		}
	}


}
