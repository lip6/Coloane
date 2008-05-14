package fr.lip6.move.coloane.extension.exportToDOT;

import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {	}

	public void export(IModelImpl modeleCourant, String newFilePath) throws ColoaneException {
		DotTranslator translator = new DotTranslator();
		Vector<String> model = translator.translateModel(modeleCourant.getGenericModel());
		translator.saveModel(model, newFilePath);
	}

}
