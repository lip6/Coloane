package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.ui.model.IModelImpl;

public interface IComMotor {

	/**
	 * Connexion du modele ˆ la plate-forme (ouverture de session)
	 * @param modele Modele aconnecter
	 * @return booleen selon le resultat de la connexion
	 * @throws Exception
	 */
	boolean openSession(IModelImpl modele);

	boolean closeSession();

}
