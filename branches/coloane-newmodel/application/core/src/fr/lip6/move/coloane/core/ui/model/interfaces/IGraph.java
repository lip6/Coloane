package fr.lip6.move.coloane.core.ui.model.interfaces;

import fr.lip6.move.coloane.core.motor.formalisms.Formalism;

public interface IGraph extends IElement {
	/**
	 * @return le formalisme associé à ce graphe.
	 */
	Formalism getFormalism();

	/**
	 * Modifie la date du modele (necessaire pour synchronisation avec FK)
	 * Indique si l'envoi d'un message a FK est necessaire
	 * @return boolean Indique si un message doit etre envoye a FK en donnant une datee
	 */
	int modifyDate();

	/**
	 * Retourne la date associee au modele
	 * @return int
	 */
	int getDate();

	/**
	 * Indicateur de fraicheur du modele
	 * @return boolean
	 */
	boolean isDirty();

	/**
	 * Permet de rendre obsolete (ou a jour) le modele (pour demande une maj ou signifier une maj)
	 * @param dirty (true = necessite de mise a jour)
	 */
	void setDirty(boolean dirty);
}
