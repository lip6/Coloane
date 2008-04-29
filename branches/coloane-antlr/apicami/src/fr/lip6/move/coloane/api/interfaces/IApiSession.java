package fr.lip6.move.coloane.api.interfaces;

import java.io.IOException;
 	 	 	/**
 	 * cette interface est fournise a Coloane pour diverses methodes
 	 * en relation avec la session.
	 	 * @author KAHOO & UU
	 	 *
	 	 */

	 	public interface IApiSession {



 	        /**
 	         * l'apelle de la methode openSession sur la session.
 	         * @param sessionDate: date de la session.
 	         * @param sessionFormalism: son formalisme.
	 	         * @param sessionName: son nom.
	 	         * @param interlocutor: son interlocuteur (l'outil).
	 	         * @param mode: son mode (interactif ou batch).
 	         * @throws IOException
 	         * @throws InterruptedException
	 	         */

	 	        public void openSession(String sessionDate, String sessionFormalism,
	 	                        String sessionName,String interlocutor,int mode) throws IOException, InterruptedException;


	 	        /**
 	         * Appel de la methode ferme session.
	 	         */
	 	        public void closeSession();


	 	        /**
	 	         *  Appel de la methode suspendre session.
	 	         * @return vraie si la suspension de la session reussie, faux sinon.
	 	         */

	 	        public boolean suspendSession();

	 	        /**
	 	         * Appel de la methode reprendre session.
	 	         * @return vraie si la reprise de la session reussie, faux sinon.
	 	         */
	 	        public boolean resumeSession();

	 	        /**
	 	         * Appel de la methode demande de service.
	 	         * @param le nom de la racine demandÃ©.
	 	         * @param le nom du service demandÃ©.
	 	         */
	 	        public void askForService(String rootName, String serviceName);


	 	        /**
	 	         * Appel de la methode demande de service.
 	             * @param le nom de la racine demandÃ©.
	 	         * @param le nom du service demandÃ©.
	 	         * @param la date .
	 	         */
	 	        public void askForService(String rootName, String serviceName, String Date);

	 	       public void openSession(String sessionDate, String sessionFormalism,
                       String sessionName) throws IOException, InterruptedException ;


	 	}