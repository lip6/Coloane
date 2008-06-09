package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ModelSection extends AbstractElementSection<IModelImpl> {

	@Override
	public final void refresh() {
		refreshControls(
				getElement().getFormalism().getName(),
				getElement().getFormalism().getListOfAttribute());

		refreshContent();
		redraw();
	}

}
