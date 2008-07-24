package fr.lip6.move.coloane.core.motor.session;

/**
 * Gestionnaire de session, gère également l'état de la connection avec l'api.
 */
public interface ISessionManager {

	/**
	 * Ajoute une session au manager<br>
	 * Si aucune session est courante... Celle la devient la session courante.</br>
	 * Le nom de la session ne doit pas etre null ou vide
	 * @param name Le nom de la nouvelle session
	 * @return La nouvelle session ou NULL si le nom est incorrect ou la session si elle existe deja
	 */
	ISession newSession(String name);

	/**
	 * Retourne la session courante ou NULL si aucune session n'est active
	 * @return La session courante ou NULL
	 */
	ISession getCurrentSession();

	/**
	 * Retourne la session dont le nom est indique en parametre
	 * @param sessionName nom de la session
	 * @return la session designe ou NULL si on ne trouve pas la session
	 */
	ISession getSession(String sessionName);

	/**
	 * Suspension d'une session.<br>
	 * Si la session suspendue est la session courante, la session courante est NULL
	 * @param sessionName Le nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	boolean suspendSession(String sessionName);

	/**
	 * Reprendre, rendre active une session
	 * @param sessionName nom de la session
	 * @return booleen Un indicateur de deroulement
	 */
	boolean resumeSession(String sessionName);

	/**
	 * Destruction de la session courante
	 * @param sessionName nom de la session
	 */
	void destroySession(String sessionName);

	/**
	 * Deconnexion brutale de tous les modeles
	 */
	void destroyAllSessions();

	/**
	 * Retourne le status d'authentification du client<br>
	 * L'authentification est valable pour tous les modeles
	 * @return Un booleen
	 */
	boolean isAuthenticated();

	/**
	 * Positionne le status d'authentification du client
	 * @param authStatus Le nouveau status du client
	 */
	void setAuthenticated(boolean authStatus);
}
