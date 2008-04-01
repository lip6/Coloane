package fr.lip6.move.coloane.extension.exportToDOT;

import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.interfaces.IExportTo;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ExportToImpl implements IExportTo {

	public ExportToImpl() {
		// TODO Auto-generated constructor stub
	}

	public void export(IModelImpl modeleCourant, String filePath)
			throws ColoaneException {
		// TODO Auto-generated method stub
		DotTranslator translator = new DotTranslator();
		Vector<String> model = translator.translateModel(modeleCourant.getGenericModel());
		translator.saveModel(model, filePath);
	}

}
