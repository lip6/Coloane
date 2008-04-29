package fr.lip6.move.coloane.core.results_new.reports;

import fr.lip6.move.coloane.core.results_new.IResultTree;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public interface IReport {
	public IResultTree build(IResultsCom result);
}
