package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;

public class SyphonTrapsReport extends Report {

	public SyphonTrapsReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			String description = sr.getCmdRT().get(0);

			// Parcours de mes resultats
			String liste = ""; //$NON-NLS-1$
			for (String object : sr.getCmdRO()) { liste = liste + object + ","; } //$NON-NLS-1$

			// Suppression de la derniere virgule
			if (liste.length() > 1) { liste = liste.substring(0, liste.length() - 1); }

			// Ajout a la lsite des resultats
			getResultList().add(new Result(liste, description));
		}
	}
}
