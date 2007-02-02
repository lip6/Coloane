package fr.lip6.move.coloane.interfaces;

import fr.lip6.move.coloane.interfaces.models.IModel;

public interface IComMotor {
	
	/**
	 * Connexion du modele ˆ la plate-forme (ouverture de session)
	 * @param modele Modele aconnecter
	 * @return booleen selon le resultat de la connexion 
	 * @throws Exception
	 */
	public boolean openSession(IModel modele) throws Exception;
	
	public boolean closeSession() throws Exception;

}
