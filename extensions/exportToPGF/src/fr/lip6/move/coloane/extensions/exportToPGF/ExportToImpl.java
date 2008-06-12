package fr.lip6.move.coloane.extensions.exportToPGF;

import java.util.Collection;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

/**
 * Export sur modele courant vers un nouveau format (ici PGF)
 * 
 * @author Jean-Baptiste Voron
 * @author Alban Linard
 */
public class ExportToImpl implements IExportTo {

	public ExportToImpl() {	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IExportTo#export(fr.lip6.move.coloane.core.ui.model.IModelImpl, java.lang.String)
	 */
	public void export(IModelImpl modeleCourant, String newFilePath) throws ColoaneException {
		PGFTranslator translator = new PGFTranslator();
		Collection<String> model = translator.translateModel(modeleCourant.getGenericModel());
		translator.saveModel(model, newFilePath);
	}
}
