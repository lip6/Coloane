package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

public class ModelSection extends AbstractSection<IModelImpl> {

	@Override
	public final void refresh() {
		refreshControls(
				getElement().getFormalism().getName(),
				getElement().getFormalism().getListOfAttribute());

		for (LabelText lt : getMap().get(getCurrentType())) {
			for (IAttributeImpl attr : getElement().getAttributes()) {
				if (lt.getLabel().equals(attr.getDisplayName())) {
					lt.setText(attr.getValue());
				}
			}
		}
	}

}
