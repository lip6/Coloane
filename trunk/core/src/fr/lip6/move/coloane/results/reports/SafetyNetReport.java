package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;

public class SafetyNetReport extends Report {

	public SafetyNetReport(String label, IResultsCom results) {
		super(label, results);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected final void buildReport() {
		// Parcours de tous les DE-FE
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			getResultList().add(new Result(sr.getCmdRT().get(0), ""));
		}
	}
}
