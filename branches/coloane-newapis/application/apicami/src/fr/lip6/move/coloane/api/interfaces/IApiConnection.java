package fr.lip6.move.coloane.api.interfaces;

import java.io.IOException;

import teststub.CloseSessionObserver;
import teststub.SpecialMessageObserver;

import fr.lip6.move.coloane.api.interfaces.observers.*;
import fr.lip6.move.coloane.api.interfaces.*;

/**
 * cette interface est fournise a Coloane pour diverses methodes comme
 * la connexion.
 * @author KAHOO & UU
 *
 */
public interface IApiConnection {




	/**
	 * Donne l'observeur des KO.
	 * @param l'observeur.
	 * @return bool.
	 */

	public boolean setBrutalInterruptObserver( IBrutalInterruptObserver o, boolean createThread);



	/**
	 * Donne l'observeur pour la fermeture de la connexion ordonnée par FK.
	 * @param l'observeur.
	 * @return bool.
	 */

	public boolean setCloseConnectionObserver( ICloseConnectionObserver o, boolean createThread);



	/**
	 * Donne l'observeur du debut du dialogue.
	 * @param l'observeur.
	 * @return bool.
	 */

	public boolean setDialogObserver( IDialogObserver o, boolean createThread);



	/**
	 * Donne l'observeur pour l'ouverture de la session.
	 * @param l'observeur.
	 * @return bool.
	 */

	public boolean setSessionObserver(ISessionObserver o, boolean createThread);

	/**
	 * Donne l'observeur pour la connection.
	 * @param l'observeur.
	 * @return bool.
	 */

	public boolean setConnectionObserver(IConnectionObserver o, boolean createThread);


	/**
	 * Donne l'observeur pour la demande de service.
	 * @param l'observeur.
	 * @return bool.
	 */

	public boolean setServiceObserver(IServiceObserver o, boolean createThread);


	// paramètres de connexion
	/**
	 * Donne l'	adresse IP.
	 * @param représente l'adresse ip.
	 * @return bool.
	 */
	public boolean setIPAdress(String ip);

	/**
	 * Donne le port.
	 * @param représente le numéro de port.
	 * @return bool.
	 */
	public boolean setPort(int p);

	/**
	 * Donne le login.
	 * @param représente le login.
	 * @return bool.
	 */
	public boolean setLogin(String login);

	/**
	 * Donne le mot de passe.
	 * @param représente le mot de passe.
	 * @return bool.
	 */
	public boolean setPassWord(String pw);


	/**
	 * c'est la méthode ouvrir session.
	 */
	public void openConnection();

	/**
	 * c'est la methode fermer session.
	 * @return vraie si la methode a réussi, faux en cas d'echec.
	 * @throws IOException
	 */
	public boolean closeConnection() throws IOException;




	/**
	 * la méthode qui retourne l'objet IAPISession.
	 * @return IAPISession qui représente une seule session.
	 */
	public IApiSession getAPISession();

	

	


	public boolean setCloseSessionObserver(
			CloseSessionObserver closeSessionObserver, boolean b);


	public boolean setSpecialMessageObserver(ISpecialMessageObserver o, boolean createThread) ;

}



