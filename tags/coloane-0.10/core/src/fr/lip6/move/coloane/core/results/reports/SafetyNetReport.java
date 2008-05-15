package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class SafetyNetReport extends Report {

	public SafetyNetReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		// Parcours de tous les DE-FE
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			getResultList().add(new Result(sr.getCmdRT().get(0), "")); //$NON-NLS-1$
		}
	}
}
