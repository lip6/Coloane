package fr.lip6.move.coloane.core.ui.properties;

import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;

public class ArcSection extends AbstractSection<IArcImpl> {

	@Override
	public final void refresh() {
		refreshControls(
				getElement().getElementBase().getName(),
				getElement().getElementBase().getListOfAttribute());


		for (LabelText lt : getMap().get(getCurrentType())) {
			for (IAttributeImpl attr : getElement().getAttributes()) {
				if (lt.getLabel().equals(attr.getDisplayName())) {
					lt.setText(attr.getValue());
				}
			}
		}
	}

}
