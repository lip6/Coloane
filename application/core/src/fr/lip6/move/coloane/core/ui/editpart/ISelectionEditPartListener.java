package fr.lip6.move.coloane.core.ui.editpart;

import org.eclipse.gef.EditPartListener;

/**
 * Les classes qui implémentent cette interface doivent pouvoir fournir un EditPartListener.
 */
public interface ISelectionEditPartListener {

	/**
	 * Constantes pour chaque état de sélection en plus des constantes définies dans EditPart
	 */
	int HIGHLIGHT = 10;
	int HIGHLIGHT_NONE = 11;

	/**
	 * @return listener qui va écouter les changements de sélection
	 */
	EditPartListener getSelectionEditPartListener();
}
