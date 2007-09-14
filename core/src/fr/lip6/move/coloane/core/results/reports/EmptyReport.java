package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public class EmptyReport extends Report {

	public EmptyReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		getResultList().add(new Result("", "")); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
