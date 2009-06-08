package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Section qui affiche les attributs du model.
 */
public class ModelSection extends AbstractElementSection<IGraph> {

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			refreshControls(
					getElements().get(0).getFormalism().getMasterGraph().getName(),
					getElements().get(0).getFormalism().getMasterGraph().getAttributes());

			refreshContent();
			redraw();
		}
	}

}
