package fr.lip6.move.coloane.core.motor.session;

public interface ISessionManager {

	/**
	 * Ajoute une session au manager<br>
	 * Si aucune session est courante... Celle la devient la session courante
	 * @param name Le nom de la nouvelle session
	 */
	void newSession(String name);

	/**
	 * Retourne la session courante
	 * @return La session courante
	 */
	ISession getCurrentSession();

	/**
	 * Retourne la session dont le nom est indique
	 * @param sessionName nom de la session
	 * @return la session designe ou NULL si on ne trouve pas la session
	 */
	ISession getSession(String sessionName);

	/**
	 * Suspension d'une session
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
	boolean destroySession(String sessionName);

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
	 * @param authenticated Le nouveau status du client
	 */
	void setAuthenticated(boolean authStatus);
}
