package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;

public class InvariantsReport extends Report {

	public InvariantsReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		if (getResultsCom().getSubResults().size() == 0) {
			getResultList().add(new Result("No invariants", ""));
			return;
		}

		for (SubResultsCom sr : getResultsCom().getSubResults()) {

			// En cas d'erreur retournee par l'outil
			if (sr.getDetails().equalsIgnoreCase("Error")) {
				getResultList().add(new Result("Error", sr.getCmdRT().get(0)));
				return;
			}

			String description = sr.getCmdRT().get(0);

			// Parcours de mes resultats
			String liste = "";
			for (String object : sr.getCmdME()) { liste = liste + object + ","; }

			// Suppression de la derniere virgule
			if (liste.length() > 1) { liste = liste.substring(0, liste.length() - 1); }

			// Ajout a la lsite des resultats
			getResultList().add(new Result(liste, description));
		}
	}
}
