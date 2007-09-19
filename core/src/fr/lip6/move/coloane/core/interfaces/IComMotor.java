package fr.lip6.move.coloane.core.interfaces;

import fr.lip6.move.coloane.core.ui.dialogs.AuthenticationInformation;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Toutes les methodes qui doivent etre implementees par la COM pour le MOTOR
 */
public interface IComMotor {

	/**
	 * Authentification de l'utilisateur
	 * @param authInformation Toutes les informations requises pour l'authentification
	 * @param monitor Le moniteur d'avancement associee a l'operation (barre de progression)
	 * @return boolean indiquant si l'authentification c'est bien passee
	 * @see AuthenticationInformation
	 */
	boolean authentication(AuthenticationInformation authInformation, IProgressMonitor monitor);

	/**
	 * Connexion du modele ˆ la plate-forme (ouverture de session)
	 * @param model Le modelea connecter a la plateforme
	 * @param monitor Le moniteur d'avancement associee a l'operation (barre de progression)
	 * @return boolean Selon le resultat de la connexion
	 */
	boolean openSession(IModelImpl modele, IProgressMonitor monitor);

	/**
	 * Fermeture d'une session ouverte au prealable sur la plateforme (demande en provenance de Coloane)
	 * @param monitor Le moniteur d'avancement associee a l'operation (barre de progression)
	 * @return Un booleen en fonction de la reussite de l'operation
	 */
	boolean closeSession(IProgressMonitor monitor);

	/**
	 * Demande d'un service a la plateforme
	 * @param rootMenuName Le menu racine
	 * @param referenceName Le menu de reference (qui n'est pas forcement le parent)
	 * @param serviceName Le service a executer
	 * @param monitor monitor Le moniteur d'avancement associee a l'operation (barre de progression)
	 */
	void askForService(String rootMenuName, String referenceName, String serviceName, IProgressMonitor monitor);
}
