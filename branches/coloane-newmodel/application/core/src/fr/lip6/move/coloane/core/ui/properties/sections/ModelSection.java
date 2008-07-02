package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.interfaces.IGraph;

/**
 * Section qui affiche les attributs du model.
 */
public class ModelSection extends AbstractElementSection<IGraph> {

	@Override
	public final void refresh() {
		if (!isDisposed()) {
			refreshControls(
					getElement().getFormalism().getMasterGraph().getName(),
					getElement().getFormalism().getMasterGraph().getAttributes());

			refreshContent();
			redraw();
		}
	}

}
