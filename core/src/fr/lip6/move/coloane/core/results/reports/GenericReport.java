package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public class GenericReport extends Report {

	public GenericReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		getResultList().add(new Result("", Messages.GenericReport_1)); //$NON-NLS-1$
	}

}
