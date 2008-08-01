// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-08-01 18:40:11

package fr.lip6.move.coloane.api.cami;
	
import fr.lip6.move.coloane.api.camiObject.ConnectionInfo;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.api.observables.DisconnectObservable;
import fr.lip6.move.coloane.api.observables.ConnectionObservable;
import fr.lip6.move.coloane.api.session.SessionController;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	
import java.util.Map;
import java.util.logging.Logger;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'FL()'", "'VI('", "'SS()'", "'RS('", "'FS()'", "'QQ(3)'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'KO(1'", "'TR('", "'WN('", "'MO('", "'DR()'", "'RQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'MT('", "'RO('", "'ME('", "'SU('", "'SI('", "'DB()'", "'FB()'", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('"
    };
    public static final int CAMI_STRING=4;
    public static final int FIXED_LENGTH_STRING=6;
    public static final int NUMBER=5;
    public static final int EOF=-1;

        public CamiParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g"; }



    /* L'objet pour la synchronisation */
    Map<String, Object> hash;
    	
    /* Le gestionnaire de session */
    ISessionController sessionController;

    /* Indique tous les observers disponibles */
    public void setObservers(Map<String, Object> hash) {
    	this.hash = hash;
    	this.sessionController = SessionController.getInstance();
    }

    /** Le logger des evenements */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

    /* Divers objets utiles pour les notifications */
    private IConnectionInfo connectionInfo;




    // $ANTLR start main
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:61:1: main : ( open_communication | open_connection | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message );
    public final void main() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:63:2: ( open_communication | open_connection | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message )
            int alt1=12;
            switch ( input.LA(1) ) {
            case 7:
                {
                alt1=1;
                }
                break;
            case 9:
                {
                alt1=2;
                }
                break;
            case 11:
                {
                alt1=3;
                }
                break;
            case 12:
                {
                alt1=4;
                }
                break;
            case 20:
                {
                alt1=5;
                }
                break;
            case 18:
                {
                alt1=6;
                }
                break;
            case 19:
                {
                alt1=7;
                }
                break;
            case 22:
                {
                alt1=8;
                }
                break;
            case 27:
                {
                alt1=9;
                }
                break;
            case 29:
            case 30:
            case 31:
                {
                alt1=10;
                }
                break;
            case 32:
                {
                alt1=11;
                }
                break;
            case 28:
                {
                alt1=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("61:1: main : ( open_communication | open_connection | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:63:4: open_communication
                    {
                    pushFollow(FOLLOW_open_communication_in_main53);
                    open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:64:4: open_connection
                    {
                    pushFollow(FOLLOW_open_connection_in_main58);
                    open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:65:4: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_main63);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:67:4: open_session
                    {
                    pushFollow(FOLLOW_open_session_in_main71);
                    open_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:68:4: close_session
                    {
                    pushFollow(FOLLOW_close_session_in_main76);
                    close_session();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:69:4: suspend_session
                    {
                    pushFollow(FOLLOW_suspend_session_in_main81);
                    suspend_session();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:70:4: resume_session
                    {
                    pushFollow(FOLLOW_resume_session_in_main86);
                    resume_session();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:72:4: receive_services
                    {
                    pushFollow(FOLLOW_receive_services_in_main94);
                    receive_services();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:73:4: state_service
                    {
                    pushFollow(FOLLOW_state_service_in_main99);
                    state_service();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:75:4: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_main107);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:77:4: receive_results
                    {
                    pushFollow(FOLLOW_receive_results_in_main115);
                    receive_results();
                    _fsp--;


                    }
                    break;
                case 12 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:79:4: ko_message
                    {
                    pushFollow(FOLLOW_ko_message_in_main123);
                    ko_message();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end main


    // $ANTLR start open_communication
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:87:1: open_communication : 'SC(' fkName= CAMI_STRING ')' ;
    public final void open_communication() throws RecognitionException {
        Token fkName=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:88:2: ( 'SC(' fkName= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:89:2: 'SC(' fkName= CAMI_STRING ')'
            {
            match(input,7,FOLLOW_7_in_open_communication144); 
            fkName=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_communication148); 
            match(input,8,FOLLOW_8_in_open_communication150); 

            		LOGGER.finest("Creation de l'objet de connexion");
            		connectionInfo = new ConnectionInfo(fkName.getText());
            		synchronized (hash) {
            			hash.notify();
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end open_communication


    // $ANTLR start open_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:99:1: open_connection : 'OC(' major= NUMBER ',' minor= NUMBER ')' ;
    public final void open_connection() throws RecognitionException {
        Token major=null;
        Token minor=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:100:2: ( 'OC(' major= NUMBER ',' minor= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:101:2: 'OC(' major= NUMBER ',' minor= NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_open_connection166); 
            major=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection170); 
            match(input,10,FOLLOW_10_in_open_connection172); 
            minor=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection176); 
            match(input,8,FOLLOW_8_in_open_connection178); 

            		((ConnectionInfo) connectionInfo).setFkMajor(major.getText());
            		((ConnectionInfo) connectionInfo).setFkMinor(minor.getText());
            		((ConnectionObservable) hash.get("IConnection")).notifyObservers(connectionInfo);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end open_connection


    // $ANTLR start close_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:109:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:110:2: ( 'FC()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:111:2: 'FC()'
            {
            match(input,11,FOLLOW_11_in_close_connection194); 

            		((DisconnectObservable) hash.get("IDisconnect")).notifyObservers();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end close_connection

    protected static class open_session_scope {
        List<String> sessionArgs;
    }
    protected Stack open_session_stack = new Stack();


    // $ANTLR start open_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:121:1: open_session : 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final void open_session() throws RecognitionException {
        open_session_stack.push(new open_session_scope());
        Token session_name=null;

         List<String> sessionArgs = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:124:2: ( 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:125:2: 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,12,FOLLOW_12_in_open_session228); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_session232); 
            match(input,8,FOLLOW_8_in_open_session234); 
            match(input,13,FOLLOW_13_in_open_session237); 
            match(input,14,FOLLOW_14_in_open_session240); 
            pushFollow(FOLLOW_interlocutor_table_in_open_session243);
            interlocutor_table();
            _fsp--;


            		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo(sessionArgs);
            		sessionController.notifyReceptSessionInfo(sessionInfo);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            open_session_stack.pop();
        }
        return ;
    }
    // $ANTLR end open_session


    // $ANTLR start interlocutor_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:135:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:136:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:137:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,15,FOLLOW_15_in_interlocutor_table260); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:138:2: ( body_table )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:138:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table263);
            	    body_table();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            match(input,16,FOLLOW_16_in_interlocutor_table267); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end interlocutor_table


    // $ANTLR start body_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:143:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token deprecated=null;
        Token new_model=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:144:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:145:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')'
            {
            match(input,17,FOLLOW_17_in_body_table282); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table286); 
            match(input,10,FOLLOW_10_in_body_table288); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table292); 
            match(input,10,FOLLOW_10_in_body_table294); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table298); 
            match(input,10,FOLLOW_10_in_body_table300); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table304); 
            match(input,8,FOLLOW_8_in_body_table306); 

            		((open_session_scope)open_session_stack.peek()).sessionArgs.add(service_name.getText());
            		((open_session_scope)open_session_stack.peek()).sessionArgs.add(about_service.getText());
            		((open_session_scope)open_session_stack.peek()).sessionArgs.add(deprecated.getText());
            		((open_session_scope)open_session_stack.peek()).sessionArgs.add(new_model.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end body_table


    // $ANTLR start suspend_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:1: suspend_session : 'SS()' ;
    public final void suspend_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:155:2: ( 'SS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:156:2: 'SS()'
            {
            match(input,18,FOLLOW_18_in_suspend_session323); 

            		LOGGER.finest("Reception d'un acquittement de suspension de session");
            		sessionController.notifyEndSuspendSession();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end suspend_session


    // $ANTLR start resume_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:163:1: resume_session : 'RS(' session_name= CAMI_STRING ')' ;
    public final void resume_session() throws RecognitionException {
        Token session_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:164:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:165:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,19,FOLLOW_19_in_resume_session339); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_resume_session343); 
            match(input,8,FOLLOW_8_in_resume_session345); 

            		LOGGER.finest("Reception d'un acquittement de reprise de session");
            		sessionController.notifyEndResumeSession(session_name.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end resume_session


    // $ANTLR start close_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:172:1: close_session : 'FS()' ;
    public final void close_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:2: ( 'FS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:174:2: 'FS()'
            {
            match(input,20,FOLLOW_20_in_close_session362); 

            		LOGGER.finest("Reception d'un acquittement de fermeture de session");
            		sessionController.notifyEndCloseSession();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end close_session

    protected static class receive_services_scope {
        List<ISubMenu> roots;
        List<IUpdateMenu> updates;
        List<IService> services;
    }
    protected Stack receive_services_stack = new Stack();


    // $ANTLR start receive_services
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:185:1: receive_services : ( receive_services_group )+ ( state_service )* 'QQ(3)' ;
    public final void receive_services() throws RecognitionException {
        receive_services_stack.push(new receive_services_scope());
         
        		List<ISubMenu> roots = new ArrayList<ISubMenu>();
        		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>();
        		List<IService> services = new ArrayList<IService>();
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:196:2: ( ( receive_services_group )+ ( state_service )* 'QQ(3)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: ( receive_services_group )+ ( state_service )* 'QQ(3)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: ( receive_services_group )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==22) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: receive_services_group
            	    {
            	    pushFollow(FOLLOW_receive_services_group_in_receive_services396);
            	    receive_services_group();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:198:2: ( state_service )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==27) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:198:2: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_services400);
            	    state_service();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,21,FOLLOW_21_in_receive_services404); 

            		LOGGER.finest("Fin de la transmission des services");
            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(roots, updates, services);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            receive_services_stack.pop();
        }
        return ;
    }
    // $ANTLR end receive_services

    protected static class receive_services_group_scope {
        ISubMenu root;
    }
    protected Stack receive_services_group_stack = new Stack();


    // $ANTLR start receive_services_group
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:205:1: receive_services_group : 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? ;
    public final void receive_services_group() throws RecognitionException {
        receive_services_group_stack.push(new receive_services_group_scope());
        Token root_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:207:2: ( 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:208:2: 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )?
            {
            match(input,22,FOLLOW_22_in_receive_services_group424); 
            pushFollow(FOLLOW_root_description_in_receive_services_group427);
            root_description();
            _fsp--;

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:210:2: ( service_description )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==26) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:210:2: service_description
            	    {
            	    pushFollow(FOLLOW_service_description_in_receive_services_group430);
            	    service_description();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_receive_services_group434); 

            		LOGGER.finest("Fin de la reception du groupe de services");
            		((receive_services_scope)receive_services_stack.peek()).roots.add(((receive_services_group_scope)receive_services_group_stack.peek()).root);
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:215:2: ( 'VQ(' root_name= CAMI_STRING ')' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==24) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:216:2: 'VQ(' root_name= CAMI_STRING ')'
                    {
                    match(input,24,FOLLOW_24_in_receive_services_group442); 
                    root_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_services_group446); 
                    match(input,8,FOLLOW_8_in_receive_services_group448); 


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            receive_services_group_stack.pop();
        }
        return ;
    }
    // $ANTLR end receive_services_group


    // $ANTLR start root_description
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:221:1: root_description : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void root_description() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:222:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:223:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,25,FOLLOW_25_in_root_description468); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_root_description472); 
            match(input,10,FOLLOW_10_in_root_description474); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description478); 
            match(input,10,FOLLOW_10_in_root_description480); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description484); 
            match(input,8,FOLLOW_8_in_root_description485); 

            		((receive_services_group_scope)receive_services_group_stack.peek()).root = CamiObjectBuilder.buildRootMenu(name.getText(), question_type.getText(), question_behavior.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end root_description


    // $ANTLR start service_description
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:229:1: service_description : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' output_formalism= CAMI_STRING ',' (active_state= NUMBER )? ')' ;
    public final void service_description() throws RecognitionException {
        Token parent_menu=null;
        Token entry_name=null;
        Token question_type=null;
        Token question_behavior=null;
        Token set_item=null;
        Token dialog=null;
        Token stop_authorized=null;
        Token output_formalism=null;
        Token active_state=null;

         List<String> aq = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' output_formalism= CAMI_STRING ',' (active_state= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:232:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' output_formalism= CAMI_STRING ',' (active_state= NUMBER )? ')'
            {
            match(input,26,FOLLOW_26_in_service_description507); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description511); 
            match(input,10,FOLLOW_10_in_service_description513); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description517); 
            match(input,10,FOLLOW_10_in_service_description519); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:15: (question_type= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:15: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description524); 

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_service_description527); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:45: (question_behavior= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:45: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description531); 

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_service_description534); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:10: (set_item= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:10: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description539); 

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_service_description542); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:29: (dialog= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:29: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description546); 

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_service_description549); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:17: (stop_authorized= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:17: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description554); 

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_service_description557); 
            output_formalism=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description561); 
            match(input,10,FOLLOW_10_in_service_description563); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:75: (active_state= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:75: active_state= NUMBER
                    {
                    active_state=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description567); 

                    }
                    break;

            }

            match(input,8,FOLLOW_8_in_service_description570); 

            	
            		LOGGER.finest("Reception d'une question " + entry_name.getText());

            		aq.add(parent_menu.getText());
            		aq.add(entry_name.getText());
            		if (question_type != null) { aq.add(question_type.getText()); } else { aq.add(null); }
            		if (question_behavior != null) { aq.add(question_behavior.getText()); } else { aq.add(null); }
            		if (set_item != null) { aq.add(set_item.getText()); } else { aq.add(null); }
            		if (dialog != null) { aq.add(dialog.getText()); } else { aq.add(null); }
            		if (stop_authorized != null) { aq.add(stop_authorized.getText()); } else { aq.add(null); }
            		if (output_formalism != null) { aq.add(output_formalism.getText()); } else { aq.add(null); }
                   		if (active_state != null) { aq.add(active_state.getText()); } else { aq.add(null); }

            		// Construction de la question
            		IQuestion question = CamiObjectBuilder.buildQuestion(aq);

            		// Ajout au menu root existant
            		((SubMenu) ((receive_services_group_scope)receive_services_group_stack.peek()).root).addQuestion(question);

            		// Ajout ˆ la liste des services
            		IService service = CamiObjectBuilder.buildService(((receive_services_group_scope)receive_services_group_stack.peek()).root, question);
            		((receive_services_scope)receive_services_stack.peek()).services.add(service);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end service_description


    // $ANTLR start state_service
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:262:1: state_service : 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' ;
    public final void state_service() throws RecognitionException {
        Token root_name=null;
        Token question_name=null;
        Token state=null;
        Token message=null;

         List<String> tq = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:2: ( 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:265:2: 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')'
            {
            match(input,27,FOLLOW_27_in_state_service592); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service596); 
            match(input,10,FOLLOW_10_in_state_service598); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service602); 
            match(input,10,FOLLOW_10_in_state_service604); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_state_service608); 
            match(input,10,FOLLOW_10_in_state_service610); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:265:88: (message= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:265:88: message= CAMI_STRING
                    {
                    message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service614); 

                    }
                    break;

            }

            match(input,8,FOLLOW_8_in_state_service617); 

            		if(state.getText().equals("7") || state.getText().equals("8")) {
            			LOGGER.finest("Reception d'un etat de service");
            			tq.add(root_name.getText());
            			tq.add(question_name.getText());
            			tq.add(state.getText());
            			tq.add(message.getText());
            			
            			// Ajout ˆ la liste des updates
            			IUpdateMenu update = CamiObjectBuilder.buildUpdate(tq);
            			((receive_services_scope)receive_services_stack.peek()).updates.add(update);
            		} else {
            			// TODO: Lever une exception ?
            			LOGGER.warning("Reception d'un etat de service non conforme -> " + state.getText());
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end state_service


    // $ANTLR start ko_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:287:1: ko_message : 'KO(1' mess= CAMI_STRING ',' severity= NUMBER ')' ;
    public final void ko_message() throws RecognitionException {
        Token mess=null;
        Token severity=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:288:2: ( 'KO(1' mess= CAMI_STRING ',' severity= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:289:2: 'KO(1' mess= CAMI_STRING ',' severity= NUMBER ')'
            {
            match(input,28,FOLLOW_28_in_ko_message638); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ko_message642); 
            match(input,10,FOLLOW_10_in_ko_message644); 
            severity=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ko_message648); 
            match(input,8,FOLLOW_8_in_ko_message650); 

            		// TODO: Differencier les KOs (1 2 ou 3)
            		// TODO: Traiter le dernier argument du KO
            		LOGGER.warning("Reception d'un message asynchrone");
            		((BrutalInterruptObservable) hash.get("IBrutalInterrupt")).notifyObservers(mess.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end ko_message


    // $ANTLR start message_to_user
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:298:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:299:2: ( trace_message | warning_message | special_message )
            int alt14=3;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt14=1;
                }
                break;
            case 30:
                {
                alt14=2;
                }
                break;
            case 31:
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("298:1: message_to_user : ( trace_message | warning_message | special_message );", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:299:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user665);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:300:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user670);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:301:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user675);
                    special_message();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end message_to_user


    // $ANTLR start trace_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:305:1: trace_message : 'TR(' message= CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:306:2: ( 'TR(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:307:2: 'TR(' message= CAMI_STRING ')'
            {
            match(input,29,FOLLOW_29_in_trace_message689); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message693); 
            match(input,8,FOLLOW_8_in_trace_message695); 

            		LOGGER.finest("Reception d'un message de trace");
            		IReceptMessage msg = (IReceptMessage) new ReceptMessage(4, message.getText());
            		((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end trace_message


    // $ANTLR start warning_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:315:1: warning_message : 'WN(' message= CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:316:2: ( 'WN(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:317:2: 'WN(' message= CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_warning_message711); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message715); 
            match(input,8,FOLLOW_8_in_warning_message717); 

            		LOGGER.finest("Reception d'un message de trace");
            		IReceptMessage msg = (IReceptMessage) new ReceptMessage(2, message.getText());
            		((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end warning_message


    // $ANTLR start special_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:325:1: special_message : 'MO(' type= NUMBER ',' message= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token type=null;
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:326:2: ( 'MO(' type= NUMBER ',' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:327:2: 'MO(' type= NUMBER ',' message= CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_special_message733); 
            type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message737); 
            match(input,10,FOLLOW_10_in_special_message739); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message743); 
            match(input,8,FOLLOW_8_in_special_message745); 

            		if(type.getText().equals("1")) { 
            			LOGGER.finest("Reception d'un message de l'administrateur"); 
            			IReceptMessage msg =(IReceptMessage) new ReceptMessage(1,message.getText()); 
            			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
            		}
                
            		if(type.getText().equals("2")) {
            			//TODO :Verifier qu'il faut traiter ce message comme un KO
            			LOGGER.finest("Reception d'un message court et urgent"); 
            			((BrutalInterruptObservable) hash.get("IBrutalInterrupt")).notifyObservers(message.getText());  
            		}

            		if(type.getText().equals("3")) { 
            			LOGGER.finest("Reception d'un message copyright"); 
            			IReceptMessage msg =(IReceptMessage) new ReceptMessage(3,message.getText()); 
            			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
            		}
                
            		if(type.getText().equals("4")) { 
            			LOGGER.finest("Reception d'un message a propos des statistiques d'execution"); 
            			IReceptMessage msg =(IReceptMessage) new ReceptMessage(4,message.getText()); 
            			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end special_message


    // $ANTLR start receive_results
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:359:1: receive_results : 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | result )* ;
    public final void receive_results() throws RecognitionException {
        Token root_name=null;
        Token service_name=null;
        Token deprecated=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:360:2: ( 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | result )* )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:361:2: 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | result )*
            {
            match(input,32,FOLLOW_32_in_receive_results769); 
            match(input,33,FOLLOW_33_in_receive_results772); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results776); 
            match(input,10,FOLLOW_10_in_receive_results778); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results782); 
            match(input,10,FOLLOW_10_in_receive_results784); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_receive_results788); 
            match(input,8,FOLLOW_8_in_receive_results790); 

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:2: ( state_service | special_message | warning_message | result )*
            loop15:
            do {
                int alt15=5;
                switch ( input.LA(1) ) {
                case 27:
                    {
                    alt15=1;
                    }
                    break;
                case 31:
                    {
                    alt15=2;
                    }
                    break;
                case 30:
                    {
                    alt15=3;
                    }
                    break;
                case 34:
                    {
                    alt15=4;
                    }
                    break;

                }

                switch (alt15) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_results797);
            	    state_service();
            	    _fsp--;



            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:364:4: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_receive_results804);
            	    special_message();
            	    _fsp--;



            	    }
            	    break;
            	case 3 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:365:4: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_receive_results811);
            	    warning_message();
            	    _fsp--;



            	    }
            	    break;
            	case 4 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:366:4: result
            	    {
            	    pushFollow(FOLLOW_result_in_receive_results818);
            	    result();
            	    _fsp--;



            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end receive_results


    // $ANTLR start result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:371:1: result : 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token set_name=null;
        Token set_type=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:372:2: ( 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:373:2: 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,34,FOLLOW_34_in_result840); 
            set_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result844); 
            match(input,10,FOLLOW_10_in_result846); 
            set_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result850); 
            match(input,8,FOLLOW_8_in_result852); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:374:2: ( result_body )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==34||(LA16_0>=36 && LA16_0<=42)||(LA16_0>=45 && LA16_0<=49)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:374:4: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result857);
            	    result_body();
            	    _fsp--;



            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);

            match(input,35,FOLLOW_35_in_result865); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:379:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:2: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt17=8;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt17=1;
                }
                break;
            case 36:
                {
                alt17=2;
                }
                break;
            case 37:
                {
                alt17=3;
                }
                break;
            case 39:
                {
                alt17=4;
                }
                break;
            case 40:
                {
                alt17=5;
                }
                break;
            case 38:
                {
                alt17=6;
                }
                break;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                {
                alt17=7;
                }
                break;
            case 41:
            case 42:
                {
                alt17=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("379:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:4: result
                    {
                    pushFollow(FOLLOW_result_in_result_body880);
                    result();
                    _fsp--;



                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:381:4: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body887);
                    textual_result();
                    _fsp--;



                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:382:4: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body894);
                    attribute_change();
                    _fsp--;



                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:383:4: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body901);
                    object_designation();
                    _fsp--;



                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:384:4: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body908);
                    object_outline();
                    _fsp--;



                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:385:4: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body915);
                    attribute_outline();
                    _fsp--;



                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:386:4: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body922);
                    object_creation();
                    _fsp--;



                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:387:4: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body929);
                    object_deletion();
                    _fsp--;



                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end result_body


    // $ANTLR start textual_result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:391:1: textual_result : 'RT(' text= CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        Token text=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:392:2: ( 'RT(' text= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:2: 'RT(' text= CAMI_STRING ')'
            {
            match(input,36,FOLLOW_36_in_textual_result946); 
            text=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result950); 
            match(input,8,FOLLOW_8_in_textual_result952); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end textual_result


    // $ANTLR start attribute_change
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:397:1: attribute_change : 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token new_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:398:2: ( 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:399:2: 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,37,FOLLOW_37_in_attribute_change969); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change973); 
            match(input,10,FOLLOW_10_in_attribute_change975); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change979); 
            match(input,10,FOLLOW_10_in_attribute_change981); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change985); 
            match(input,8,FOLLOW_8_in_attribute_change987); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attribute_change


    // $ANTLR start attribute_outline
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:403:1: attribute_outline : 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:404:2: ( 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:2: 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,38,FOLLOW_38_in_attribute_outline1004); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1008); 
            match(input,10,FOLLOW_10_in_attribute_outline1010); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1014); 
            match(input,10,FOLLOW_10_in_attribute_outline1016); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:58: (begin= NUMBER )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==NUMBER) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:58: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1020); 

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_attribute_outline1023); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:74: (end= NUMBER )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NUMBER) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:74: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1027); 

                    }
                    break;

            }

            match(input,8,FOLLOW_8_in_attribute_outline1030); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attribute_outline


    // $ANTLR start object_designation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:409:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:410:2: ( 'RO(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:411:2: 'RO(' id= NUMBER ')'
            {
            match(input,39,FOLLOW_39_in_object_designation1046); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1050); 
            match(input,8,FOLLOW_8_in_object_designation1052); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end object_designation


    // $ANTLR start object_outline
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:415:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:416:2: ( 'ME(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:417:2: 'ME(' id= NUMBER ')'
            {
            match(input,40,FOLLOW_40_in_object_outline1070); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1074); 
            match(input,8,FOLLOW_8_in_object_outline1076); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end object_outline


    // $ANTLR start object_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:421:1: object_creation : ( node | box | arc | attribute );
    public final void object_creation() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:422:2: ( node | box | arc | attribute )
            int alt20=4;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt20=1;
                }
                break;
            case 46:
                {
                alt20=2;
                }
                break;
            case 47:
                {
                alt20=3;
                }
                break;
            case 48:
            case 49:
                {
                alt20=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("421:1: object_creation : ( node | box | arc | attribute );", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:422:4: node
                    {
                    pushFollow(FOLLOW_node_in_object_creation1091);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:423:4: box
                    {
                    pushFollow(FOLLOW_box_in_object_creation1096);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:424:4: arc
                    {
                    pushFollow(FOLLOW_arc_in_object_creation1101);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:425:4: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_object_creation1106);
                    attribute();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end object_creation


    // $ANTLR start object_deletion
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:429:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:430:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==41) ) {
                alt21=1;
            }
            else if ( (LA21_0==42) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("429:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:430:4: 'SU(' id= NUMBER ')'
                    {
                    match(input,41,FOLLOW_41_in_object_deletion1119); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1123); 
                    match(input,8,FOLLOW_8_in_object_deletion1125); 


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:431:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,42,FOLLOW_42_in_object_deletion1133); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1137); 
                    match(input,10,FOLLOW_10_in_object_deletion1139); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1143); 
                    match(input,8,FOLLOW_8_in_object_deletion1145); 


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end object_deletion

    protected static class model_definition_scope {
        IGraph model;
    }
    protected Stack model_definition_stack = new Stack();


    // $ANTLR start model_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:439:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        model_definition_stack.push(new model_definition_scope());
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:441:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:442:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,43,FOLLOW_43_in_model_definition1177); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:443:2: ( syntactic | aestetic )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=45 && LA22_0<=49)) ) {
                alt22=1;
            }
            else if ( ((LA22_0>=50 && LA22_0<=54)) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("443:2: ( syntactic | aestetic )", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:443:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition1182);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:443:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition1186);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,44,FOLLOW_44_in_model_definition1191); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            model_definition_stack.pop();
        }
        return ;
    }
    // $ANTLR end model_definition


    // $ANTLR start syntactic
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:1: syntactic : ( node | box | arc | attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:2: ( node | box | arc | attribute )
            int alt23=4;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt23=1;
                }
                break;
            case 46:
                {
                alt23=2;
                }
                break;
            case 47:
                {
                alt23=3;
                }
                break;
            case 48:
            case 49:
                {
                alt23=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("448:1: syntactic : ( node | box | arc | attribute );", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic1205);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic1209);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic1213);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:21: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_syntactic1217);
                    attribute();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end syntactic


    // $ANTLR start node
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:454:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:455:2: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:456:2: 'CN(' CAMI_STRING ',' NUMBER ')'
            {
            match(input,45,FOLLOW_45_in_node1232); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node1234); 
            match(input,10,FOLLOW_10_in_node1236); 
            match(input,NUMBER,FOLLOW_NUMBER_in_node1238); 
            match(input,8,FOLLOW_8_in_node1240); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end node


    // $ANTLR start box
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:460:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:460:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:461:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,46,FOLLOW_46_in_box1253); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box1255); 
            match(input,10,FOLLOW_10_in_box1257); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1259); 
            match(input,10,FOLLOW_10_in_box1261); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1263); 
            match(input,8,FOLLOW_8_in_box1265); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end box


    // $ANTLR start arc
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:465:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:465:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:466:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,47,FOLLOW_47_in_arc1278); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc1280); 
            match(input,10,FOLLOW_10_in_arc1282); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1284); 
            match(input,10,FOLLOW_10_in_arc1286); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1288); 
            match(input,10,FOLLOW_10_in_arc1290); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1292); 
            match(input,8,FOLLOW_8_in_arc1294); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end arc


    // $ANTLR start attribute
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:470:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void attribute() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:471:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==48) ) {
                alt24=1;
            }
            else if ( (LA24_0==49) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("470:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:471:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,48,FOLLOW_48_in_attribute1307); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1309); 
                    match(input,10,FOLLOW_10_in_attribute1311); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1313); 
                    match(input,10,FOLLOW_10_in_attribute1315); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1317); 
                    match(input,8,FOLLOW_8_in_attribute1319); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,49,FOLLOW_49_in_attribute1324); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1326); 
                    match(input,10,FOLLOW_10_in_attribute1328); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1330); 
                    match(input,10,FOLLOW_10_in_attribute1332); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1334); 
                    match(input,10,FOLLOW_10_in_attribute1336); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1338); 
                    match(input,10,FOLLOW_10_in_attribute1340); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1342); 
                    match(input,8,FOLLOW_8_in_attribute1344); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end attribute


    // $ANTLR start aestetic
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:476:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:477:2: ( object_position | text_position | intermediary_point )
            int alt25=3;
            switch ( input.LA(1) ) {
            case 50:
            case 51:
            case 52:
                {
                alt25=1;
                }
                break;
            case 53:
                {
                alt25=2;
                }
                break;
            case 54:
                {
                alt25=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("476:1: aestetic : ( object_position | text_position | intermediary_point );", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:478:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic1358);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:478:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic1362);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:478:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic1366);
                    intermediary_point();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end aestetic


    // $ANTLR start object_position
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:482:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:483:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt26=3;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt26=1;
                }
                break;
            case 51:
                {
                alt26=2;
                }
                break;
            case 52:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("482:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:483:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,50,FOLLOW_50_in_object_position1379); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1383); 
                    match(input,10,FOLLOW_10_in_object_position1385); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1389); 
                    match(input,10,FOLLOW_10_in_object_position1391); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1395); 
                    match(input,8,FOLLOW_8_in_object_position1397); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:484:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,51,FOLLOW_51_in_object_position1402); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1406); 
                    match(input,10,FOLLOW_10_in_object_position1408); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1412); 
                    match(input,10,FOLLOW_10_in_object_position1414); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1418); 
                    match(input,8,FOLLOW_8_in_object_position1420); 

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:485:4: 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,52,FOLLOW_52_in_object_position1425); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1429); 
                    match(input,10,FOLLOW_10_in_object_position1431); 
                    left=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1435); 
                    match(input,10,FOLLOW_10_in_object_position1437); 
                    right=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1441); 
                    match(input,10,FOLLOW_10_in_object_position1443); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1447); 
                    match(input,10,FOLLOW_10_in_object_position1449); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1453); 
                    match(input,8,FOLLOW_8_in_object_position1454); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end object_position


    // $ANTLR start text_position
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:489:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:490:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:491:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,53,FOLLOW_53_in_text_position1469); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1473); 
            match(input,10,FOLLOW_10_in_text_position1475); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position1479); 
            match(input,10,FOLLOW_10_in_text_position1481); 
            h_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1485); 
            match(input,10,FOLLOW_10_in_text_position1487); 
            v_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1491); 
            match(input,8,FOLLOW_8_in_text_position1493); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end text_position


    // $ANTLR start intermediary_point
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:495:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:496:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:497:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,54,FOLLOW_54_in_intermediary_point1508); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1510); 
            match(input,10,FOLLOW_10_in_intermediary_point1512); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1514); 
            match(input,10,FOLLOW_10_in_intermediary_point1516); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1518); 
            match(input,8,FOLLOW_8_in_intermediary_point1520); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end intermediary_point

    protected static class dialog_definition_scope {
        List<IDialog> dialogs;
    }
    protected Stack dialog_definition_stack = new Stack();


    // $ANTLR start dialog_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:505:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        dialog_definition_stack.push(new dialog_definition_scope());
         List<IDialog> dialogs = new ArrayList<IDialog>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:509:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,55,FOLLOW_55_in_dialog_definition1554); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition1557);
            dialog_creation();
            _fsp--;

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:511:2: ( next_dialog )+
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==58) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:512:2: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition1563);
            	    next_dialog();
            	    _fsp--;

            	     

            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);

            match(input,56,FOLLOW_56_in_dialog_definition1573); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            dialog_definition_stack.pop();
        }
        return ;
    }
    // $ANTLR end dialog_definition


    // $ANTLR start dialog_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:518:1: dialog_creation : 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        Token dialog_id=null;
        Token dialog_type=null;
        Token buttons_type=null;
        Token window_title=null;
        Token help=null;
        Token title_or_message=null;
        Token input_type=null;
        Token line_type=null;
        Token default_value=null;

         List<String> ce = new ArrayList<String>();
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:520:2: ( 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:521:2: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
            {
            match(input,57,FOLLOW_57_in_dialog_creation1595); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1599); 
            match(input,10,FOLLOW_10_in_dialog_creation1601); 
            dialog_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1605); 
            match(input,10,FOLLOW_10_in_dialog_creation1607); 
            buttons_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1613); 
            match(input,10,FOLLOW_10_in_dialog_creation1615); 
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1620); 
            match(input,10,FOLLOW_10_in_dialog_creation1622); 
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1628); 
            match(input,10,FOLLOW_10_in_dialog_creation1630); 
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1634); 
            match(input,10,FOLLOW_10_in_dialog_creation1636); 
            input_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1642); 
            match(input,10,FOLLOW_10_in_dialog_creation1644); 
            line_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1648); 
            match(input,10,FOLLOW_10_in_dialog_creation1650); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:525:15: (default_value= CAMI_STRING )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==CAMI_STRING) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:525:15: default_value= CAMI_STRING
                    {
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1656); 

                    }
                    break;

            }

            match(input,8,FOLLOW_8_in_dialog_creation1659); 

            	
            		ce.add(dialog_id.getText());
            		ce.add(dialog_type.getText());
            		ce.add(buttons_type.getText());
            		ce.add(window_title.getText());
            		ce.add(help.getText());
            		ce.add(title_or_message.getText());
            		ce.add(input_type.getText());
            		ce.add(line_type.getText());
            		if (default_value != null) { ce.add(default_value.getText()); } else { ce.add(null); }
            		
            		// Construction de l'objet boite de dialogue
            		IDialog dialog = CamiObjectBuilder.buildDialog(ce);
            		// Ajout de la boite de dialogue ˆ la liste
            		((dialog_definition_scope)dialog_definition_stack.peek()).dialogs.add(dialog);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end dialog_creation


    // $ANTLR start next_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:545:1: next_dialog : 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        Token dialog_id=null;
        Token line=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:2: ( 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:547:2: 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')'
            {
            match(input,58,FOLLOW_58_in_next_dialog1675); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_next_dialog1679); 
            match(input,10,FOLLOW_10_in_next_dialog1681); 
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog1685); 
            match(input,8,FOLLOW_8_in_next_dialog1687); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end next_dialog


    // $ANTLR start display_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:1: display_dialog : 'AD(' dialog_id= NUMBER ')' ;
    public final void display_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:552:2: ( 'AD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:553:2: 'AD(' dialog_id= NUMBER ')'
            {
            match(input,59,FOLLOW_59_in_display_dialog1703); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_display_dialog1707); 
            match(input,8,FOLLOW_8_in_display_dialog1709); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end display_dialog


    // $ANTLR start hide_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:1: hide_dialog : 'HD(' dialog_id= NUMBER ')' ;
    public final void hide_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:558:2: ( 'HD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:559:2: 'HD(' dialog_id= NUMBER ')'
            {
            match(input,60,FOLLOW_60_in_hide_dialog1725); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_hide_dialog1729); 
            match(input,8,FOLLOW_8_in_hide_dialog1731); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end hide_dialog


    // $ANTLR start destroy_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:563:1: destroy_dialog : 'DG(' dialog_id= NUMBER ')' ;
    public final void destroy_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:564:2: ( 'DG(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:565:2: 'DG(' dialog_id= NUMBER ')'
            {
            match(input,61,FOLLOW_61_in_destroy_dialog1748); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_destroy_dialog1752); 
            match(input,8,FOLLOW_8_in_destroy_dialog1754); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end destroy_dialog


    // $ANTLR start interactive_response
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:569:1: interactive_response : 'MI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:570:2: ( 'MI(' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:571:2: 'MI(' NUMBER ',' NUMBER ')'
            {
            match(input,62,FOLLOW_62_in_interactive_response1769); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response1771); 
            match(input,10,FOLLOW_10_in_interactive_response1773); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response1775); 
            match(input,8,FOLLOW_8_in_interactive_response1777); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end interactive_response


 

    public static final BitSet FOLLOW_open_communication_in_main53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_connection_in_main58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_main63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_session_in_main71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_session_in_main76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_suspend_session_in_main81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resume_session_in_main86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_in_main94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_main99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_main107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_results_in_main115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ko_message_in_main123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_open_communication144 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_communication148 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_open_communication150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_open_connection166 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection170 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_open_connection172 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection176 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_open_connection178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_close_connection194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_open_session228 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_session232 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_open_session234 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_open_session237 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_open_session240 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_interlocutor_table_in_open_session243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_interlocutor_table260 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table263 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_interlocutor_table267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_body_table282 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table286 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_body_table288 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table292 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_body_table294 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table298 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_body_table300 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table304 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_body_table306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_suspend_session323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_resume_session339 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_resume_session343 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_resume_session345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_close_session362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_group_in_receive_services396 = new BitSet(new long[]{0x0000000008600000L});
    public static final BitSet FOLLOW_state_service_in_receive_services400 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_21_in_receive_services404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_receive_services_group424 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_root_description_in_receive_services_group427 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_service_description_in_receive_services_group430 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_receive_services_group434 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_receive_services_group442 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_services_group446 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_receive_services_group448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_root_description468 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_root_description472 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_root_description474 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description478 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_root_description480 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description484 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_root_description485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_service_description507 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description511 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description513 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description517 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description519 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_service_description524 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description527 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_service_description531 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description534 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_service_description539 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description542 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_service_description546 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description549 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_service_description554 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description557 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description561 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_service_description563 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_NUMBER_in_service_description567 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_service_description570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_state_service592 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service596 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_state_service598 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service602 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_state_service604 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_state_service608 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_state_service610 = new BitSet(new long[]{0x0000000000000110L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service614 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_state_service617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ko_message638 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ko_message642 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_ko_message644 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ko_message648 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ko_message650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_trace_message689 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message693 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_trace_message695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_warning_message711 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message715 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_warning_message717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_special_message733 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message737 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_special_message739 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message743 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_special_message745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_receive_results769 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_receive_results772 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results776 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_receive_results778 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results782 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_receive_results784 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_receive_results788 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_receive_results790 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_state_service_in_receive_results797 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_special_message_in_receive_results804 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_warning_message_in_receive_results811 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_result_in_receive_results818 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_34_in_result840 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result844 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_result846 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result850 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_result852 = new BitSet(new long[]{0x0003E7F400000000L});
    public static final BitSet FOLLOW_result_body_in_result857 = new BitSet(new long[]{0x0003E7FC00000000L});
    public static final BitSet FOLLOW_35_in_result865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_textual_result946 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result950 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_textual_result952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_attribute_change969 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change973 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_change975 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change979 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_change981 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change985 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute_change987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_attribute_outline1004 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1008 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_outline1010 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1014 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_outline1016 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1020 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_outline1023 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1027 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute_outline1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_object_designation1046 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1050 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_designation1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_object_outline1070 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1074 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_outline1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_object_creation1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_object_creation1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_object_creation1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_object_creation1106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_object_deletion1119 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1123 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_deletion1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_object_deletion1133 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1137 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_deletion1139 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1143 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_deletion1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_model_definition1177 = new BitSet(new long[]{0x007FE00000000000L});
    public static final BitSet FOLLOW_syntactic_in_model_definition1182 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_aestetic_in_model_definition1186 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_model_definition1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic1209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_syntactic1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_node1232 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node1234 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_node1236 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node1238 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_node1240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_box1253 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box1255 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_box1257 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1259 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_box1261 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1263 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_box1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_arc1278 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc1280 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_arc1282 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1284 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_arc1286 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1288 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_arc1290 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1292 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_arc1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_attribute1307 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1309 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1311 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1313 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1315 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1317 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_attribute1324 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1326 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1328 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1330 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1332 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1334 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1336 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1338 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1340 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1342 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute1344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_position1379 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1383 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1385 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1389 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1391 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1395 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_position1397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_position1402 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1406 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1408 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1412 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1414 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1418 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_position1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_position1425 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1429 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1431 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1435 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1437 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1441 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1443 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1447 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1449 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1453 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_position1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_text_position1469 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1473 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_text_position1475 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position1479 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_text_position1481 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1485 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_text_position1487 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1491 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_text_position1493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_intermediary_point1508 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1510 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_intermediary_point1512 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1514 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_intermediary_point1516 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1518 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_intermediary_point1520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_dialog_definition1554 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition1557 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition1563 = new BitSet(new long[]{0x0500000000000000L});
    public static final BitSet FOLLOW_56_in_dialog_definition1573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_dialog_creation1595 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1599 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1601 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1605 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1607 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1613 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1615 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1620 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1622 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1628 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1630 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1634 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1636 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1642 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1644 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1648 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1650 = new BitSet(new long[]{0x0000000000000110L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1656 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_dialog_creation1659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_next_dialog1675 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_next_dialog1679 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_next_dialog1681 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog1685 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_next_dialog1687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_display_dialog1703 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_display_dialog1707 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_display_dialog1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_hide_dialog1725 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_hide_dialog1729 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_hide_dialog1731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_destroy_dialog1748 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_destroy_dialog1752 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_destroy_dialog1754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_interactive_response1769 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response1771 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interactive_response1773 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response1775 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_interactive_response1777 = new BitSet(new long[]{0x0000000000000002L});

}