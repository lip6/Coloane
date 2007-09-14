package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class SyntaxCheckerReport extends Report {

	/**
	 * Constructeur du rapport
	 * @param results
	 */
	public SyntaxCheckerReport(IResultsCom results) {
		super(Messages.SyntaxCheckerReport_0, results);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.results.reports.Report#buildReport()
	 */
	@Override
	protected final void buildReport() {
		String description = ""; //$NON-NLS-1$

		// Si il n'y a pas de resultat : Aucun probleme !
		if (getResultsCom().getSubResults().size() == 0) {
			getResultList().add(new Result(Messages.SyntaxCheckerReport_2, "")); //$NON-NLS-1$
		}

		// Parcours de tous les DE-FE
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			String label = sr.getCmdRT().get(0);

			if (label.equalsIgnoreCase("List of unnamed places.")) { //$NON-NLS-1$
				description = Messages.SyntaxCheckerReport_5;
			} else if (label.equalsIgnoreCase("List of unnamed transitions.")) { //$NON-NLS-1$
				description = Messages.SyntaxCheckerReport_7;
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
