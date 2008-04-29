package fr.lip6.move.coloane.api.session;

 	import fr.lip6.move.coloane.api.interfaces.*;
	import fr.lip6.move.coloane.api.interfaces.ISessionController;

/**
 * cette classe represente une session, elle implemente l'interface IApiSession
	 * @author Kahoo & UU
	 *
	 */
 	public class ApiSession implements IApiSession{

 	        /**la date de la session*/
 	        private String sessionDate;

	        /** le formalisme de la session*/
 	        private String sessionFormalism;

 	        /** le nom de la session*/
 	        private String sessionName;

 	        /** l'outil avec le quel on communique*/
 	        private String interlocutor;

 	        /** le mode */
 	        private int mode;

	         /** le sessionController*/
 	        private ISessionController sessionCont;

 	        /** notre speaker*/
 	        private ISpeaker speaker;

 	   /**
	    * le constructeur de notre session.
 	    */
 	        public ApiSession(ISessionController se,ISpeaker speaker) {
 	                this.interlocutor= null;
	                this.mode = -1;
 	                this.sessionDate = null;
 	                this.sessionFormalism= null;
 	                this.sessionName = null;
	                this.sessionCont = se;
 	                this.speaker = speaker;
 	        }

 	        /**
	         * la methode openSession Ã  invoquer sur la session.
 	         */
 	        public void openSession(String sessionDate, String sessionFormalism,
	                        String sessionName,String interlocutor,int mode) {
 	                this.interlocutor= interlocutor;
 	                this.mode = mode;
 	                this.sessionDate = sessionDate ;
 	                this.sessionFormalism = sessionFormalism;
 	                this.sessionName = sessionName;

 	                if (this.sessionCont.getActiveSession().equals(null)) {
 	                        this.sessionCont.addSession(this);
 	                }

 	      //TODO lexeption sinon ++ etat de l'automate...

 	                speaker.

	        }


	        public void askForService(String rootName, String serviceName) {


	 	        }


 	        public void askForService(String rootName, String serviceName, String date) {
	 	                // TODO Auto-generated method stub

	 	        }


	 	        public void closeSession() {
	 	                // TODO Auto-generated method stub

	 	        }



	 	        public boolean resumeSession() {
	 	                // TODO Auto-generated method stub
	 	                return false;
	 	        }


	 	        public boolean suspendSession() {
	 	                // TODO Auto-generated method stub
	 	                return false;
	 	        }
	 	}