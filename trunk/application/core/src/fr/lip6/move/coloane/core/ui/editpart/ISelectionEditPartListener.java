package fr.lip6.move.coloane.core.ui.editpart;

import org.eclipse.gef.EditPartListener;

public interface ISelectionEditPartListener extends EditPartListener {
	int HIGHLIGHT = 10;
	int HIGHLIGHT_NONE = 11;
	int SPECIAL = 12;
	int SPECIAL_NONE = 13;
}
