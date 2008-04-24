package fr.lip6.move.coloane.api.interfaces;

import teststub.TraceMessageObserver;
import fr.lip6.move.coloane.api.interfaces.observers.*;


/**
 * cette interface est fournise a Coloane pour diverses methodes comme
 * la connexion.
 * @author KAHOO & UU
 *
 */
public interface IApiConnection {


	/**
	 * Donne l'observeur des TR  +  MO(1, 3, 4).
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setTraceMessageObserver( ITraceMessageObserver o, boolean createThread);


	/**
	 * Donne l'observeur des WN  +  MO(2).
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setWarningObserver ( IWarningObserver o, boolean createThread);


	/**
	 * Donne l'observeur des TQ(1, 2, 3, 4, 5, 6).
	 * @param l'observeur.
	 * @return bool
	 */

	boolean setServiceStateObserver( IServiceStateObserver o, boolean createThread);


	/**
	 * Donne l'observeur des KO.
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setBrutalInterruptObserver( IBrutalInterruptObserver o, boolean createThread);



	/**
	 * Donne l'observeur pour la fermeture de la connexion ordonnée par FK.
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setFKCloseConnectionObserver( IFKCloseConnectionObserver o, boolean createThread);



	/**
	 * Donne l'observeur du debut du dialogue.
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setDialogObserver( IDialogObserver o, boolean createThread);



	/**
	 * Donne l'observeur pour l'ouverture de la session.
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setSessionObserver(ISessionObserver o, boolean createThread);

	/**
	 * Donne l'observeur pour la connection.
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setConnectionObserver(IConnectionObserver o, boolean createThread);


	/**
	 * Donne l'observeur pour la demande de service.
	 * @param l'observeur.
	 * @return bool.
	 */

	boolean setServiceObserver(IServiceObserver o, boolean createThread);


	// paramètres de connexion
	/**
	 * Donne l'	adresse IP.
	 * @param représente l'adresse ip.
	 * @return bool.
	 */
	boolean setIPAdress(String ip);

	/**
	 * Donne le port.
	 * @param représente le numéro de port.
	 * @return bool.
	 */
	boolean setPort(int p);

	/**
	 * Donne le login.
	 * @param représente le login.
	 * @return bool.
	 */
	boolean setLogin(String login);

	/**
	 * Donne le mot de passe.
	 * @param représente le mot de passe.
	 * @return bool.
	 */
	boolean setPassWord(String pw);


	/**
	 * c'est la méthode ouvrir session.
	 */
	void openConnection();

	/**
	 * c'est la methode fermer session.
	 * @return vraie si la methode a réussi, faux en cas d'echec.
	 */
	boolean closeConnection();




	/**
	 * la méthode qui retourne l'objet IAPISession.
	 * @return IAPISession qui représente une seule session.
	 */
	IApiSession getAPISession();

}



