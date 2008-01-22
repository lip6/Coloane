package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public interface IExportTo {
	public void export(IModelImpl modeleCourant,String outputFile);
}
