package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.results.Result;

public class EmptyReport extends Report {

	public EmptyReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		getResultList().add(new Result("", "")); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
