package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class InvariantsReport extends Report {

	public InvariantsReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		if (getResultsCom().getSubResults().size() == 0) {
			getResultList().add(new Result(Messages.InvariantsReport_0, "")); //$NON-NLS-1$
			return;
		}

		for (SubResultsCom sr : getResultsCom().getSubResults()) {

			// En cas d'erreur retournee par l'outil
			if (sr.getDetails().equalsIgnoreCase("Error")) { //$NON-NLS-1$
				getResultList().add(new Result(Messages.InvariantsReport_1, sr.getCmdRT().get(0)));
				return;
			}

			String description = sr.getCmdRT().get(0);

			// Parcours de mes resultats
			String liste = ""; //$NON-NLS-1$
			for (String object : sr.getCmdME()) { liste = liste + object + ","; } //$NON-NLS-1$

			// Suppression de la derniere virgule
			if (liste.length() > 1) { liste = liste.substring(0, liste.length() - 1); }

			// Ajout a la lsite des resultats
			getResultList().add(new Result(liste, description));
		}
	}
}
