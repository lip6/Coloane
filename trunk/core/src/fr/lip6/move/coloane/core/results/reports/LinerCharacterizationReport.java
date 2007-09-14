package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.Result;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;

public class LinerCharacterizationReport extends Report {

	public LinerCharacterizationReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			for (String object : sr.getCmdRO()) {
				if (object.equals("1")) { object = Messages.LinerCharacterizationReport_1; } //$NON-NLS-1$
				getResultList().add(new Result(object, sr.getCmdRT().get(0)));
				return;
			}

			String res = ""; //$NON-NLS-1$
			for (String object : sr.getCmdRT()) {
				res += object + "\n"; //$NON-NLS-1$
			}
			getResultList().add(new Result("", res)); //$NON-NLS-1$
		}
	}

}
