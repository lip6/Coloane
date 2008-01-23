package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public interface IImportTo {
	public IModelImpl export(String intputFile) throws Exception;
}
