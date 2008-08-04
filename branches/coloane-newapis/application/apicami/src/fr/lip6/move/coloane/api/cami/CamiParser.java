// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-08-04 18:28:16

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'FL()'", "'VI('", "'SS()'", "'RS('", "'FS()'", "'QQ(3)'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'KO(1'", "'TR('", "'WN('", "'MO('", "'DR()'", "'RQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'MT('", "'RO('", "'ME('", "'SU('", "'SI('", "'DB()'", "'FB()'", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('"
    };
    public static final int CAMI_STRING=4;
    public static final int FIXED_LENGTH_STRING=6;
    public static final int EOF=-1;
    public static final int NUMBER=5;
    public static final int NEWLINE=7;

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




    // $ANTLR start main
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:58:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message );
    public final void main() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:60:2: ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message )
            int alt1=11;
            switch ( input.LA(1) ) {
            case 8:
                {
                alt1=1;
                }
                break;
            case 12:
                {
                alt1=2;
                }
                break;
            case 13:
                {
                alt1=3;
                }
                break;
            case 21:
                {
                alt1=4;
                }
                break;
            case 19:
                {
                alt1=5;
                }
                break;
            case 20:
                {
                alt1=6;
                }
                break;
            case 23:
                {
                alt1=7;
                }
                break;
            case 28:
                {
                alt1=8;
                }
                break;
            case 30:
            case 31:
            case 32:
                {
                alt1=9;
                }
                break;
            case 33:
                {
                alt1=10;
                }
                break;
            case 29:
                {
                alt1=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("58:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:60:4: open_communication
                    {
                    pushFollow(FOLLOW_open_communication_in_main53);
                    open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:61:4: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_main58);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:63:4: open_session
                    {
                    pushFollow(FOLLOW_open_session_in_main66);
                    open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:64:4: close_session
                    {
                    pushFollow(FOLLOW_close_session_in_main71);
                    close_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:65:4: suspend_session
                    {
                    pushFollow(FOLLOW_suspend_session_in_main76);
                    suspend_session();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:66:4: resume_session
                    {
                    pushFollow(FOLLOW_resume_session_in_main81);
                    resume_session();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:68:4: receive_services
                    {
                    pushFollow(FOLLOW_receive_services_in_main89);
                    receive_services();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:69:4: state_service
                    {
                    pushFollow(FOLLOW_state_service_in_main94);
                    state_service();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:4: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_main102);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:73:4: receive_results
                    {
                    pushFollow(FOLLOW_receive_results_in_main110);
                    receive_results();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:75:4: ko_message
                    {
                    pushFollow(FOLLOW_ko_message_in_main118);
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

    protected static class open_communication_scope {
        IConnectionInfo connectionInfo;
    }
    protected Stack open_communication_stack = new Stack();


    // $ANTLR start open_communication
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:83:1: open_communication : 'SC(' fkName= CAMI_STRING ')' open_connection ;
    public final void open_communication() throws RecognitionException {
        open_communication_stack.push(new open_communication_scope());
        Token fkName=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:85:2: ( 'SC(' fkName= CAMI_STRING ')' open_connection )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:86:2: 'SC(' fkName= CAMI_STRING ')' open_connection
            {
            match(input,8,FOLLOW_8_in_open_communication144); 
            fkName=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_communication147); 
            match(input,9,FOLLOW_9_in_open_communication149); 

            		LOGGER.finest("Creation de l'objet de connexion");
            		((open_communication_scope)open_communication_stack.peek()).connectionInfo = new ConnectionInfo(fkName.getText());
            	
            pushFollow(FOLLOW_open_connection_in_open_communication154);
            open_connection();
            _fsp--;



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            open_communication_stack.pop();
        }
        return ;
    }
    // $ANTLR end open_communication


    // $ANTLR start open_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:94:1: open_connection : 'OC(' major= NUMBER ',' minor= NUMBER ')' ;
    public final void open_connection() throws RecognitionException {
        Token major=null;
        Token minor=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:95:2: ( 'OC(' major= NUMBER ',' minor= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:96:2: 'OC(' major= NUMBER ',' minor= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_open_connection170); 
            major=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection174); 
            match(input,11,FOLLOW_11_in_open_connection176); 
            minor=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection180); 
            match(input,9,FOLLOW_9_in_open_connection182); 

            		((ConnectionInfo) ((open_communication_scope)open_communication_stack.peek()).connectionInfo).setFkMajor(major.getText());
            		((ConnectionInfo) ((open_communication_scope)open_communication_stack.peek()).connectionInfo).setFkMinor(minor.getText());
            		((ConnectionObservable) hash.get("IConnection")).notifyObservers(((open_communication_scope)open_communication_stack.peek()).connectionInfo);
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:104:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:105:2: ( 'FC()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:106:2: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection198); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:116:1: open_session : 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table receive_services ;
    public final void open_session() throws RecognitionException {
        open_session_stack.push(new open_session_scope());
        Token session_name=null;

         ((open_session_scope)open_session_stack.peek()).sessionArgs = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:119:2: ( 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table receive_services )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:120:2: 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table receive_services
            {
            match(input,13,FOLLOW_13_in_open_session232); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_session236); 
            match(input,9,FOLLOW_9_in_open_session238); 
            match(input,14,FOLLOW_14_in_open_session241); 
            match(input,15,FOLLOW_15_in_open_session244); 
            pushFollow(FOLLOW_interlocutor_table_in_open_session247);
            interlocutor_table();
            _fsp--;


            		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo(((open_session_scope)open_session_stack.peek()).sessionArgs);
            		sessionController.notifyReceptSessionInfo(sessionInfo);
            	
            pushFollow(FOLLOW_receive_services_in_open_session252);
            receive_services();
            _fsp--;


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:131:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:132:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:133:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,16,FOLLOW_16_in_interlocutor_table267); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:134:2: ( body_table )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==18) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:134:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table270);
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

            match(input,17,FOLLOW_17_in_interlocutor_table274); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:139:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token deprecated=null;
        Token new_model=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:140:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:141:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')'
            {
            match(input,18,FOLLOW_18_in_body_table289); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table293); 
            match(input,11,FOLLOW_11_in_body_table295); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table299); 
            match(input,11,FOLLOW_11_in_body_table301); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table305); 
            match(input,11,FOLLOW_11_in_body_table307); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table311); 
            match(input,9,FOLLOW_9_in_body_table313); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:150:1: suspend_session : 'SS()' ;
    public final void suspend_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:151:2: ( 'SS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:152:2: 'SS()'
            {
            match(input,19,FOLLOW_19_in_suspend_session330); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:159:1: resume_session : 'RS(' session_name= CAMI_STRING ')' ;
    public final void resume_session() throws RecognitionException {
        Token session_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:160:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:161:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,20,FOLLOW_20_in_resume_session346); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_resume_session350); 
            match(input,9,FOLLOW_9_in_resume_session352); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:168:1: close_session : 'FS()' ;
    public final void close_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:169:2: ( 'FS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:170:2: 'FS()'
            {
            match(input,21,FOLLOW_21_in_close_session369); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:181:1: receive_services : ( receive_services_group )+ ( state_service )* 'QQ(3)' ;
    public final void receive_services() throws RecognitionException {
        receive_services_stack.push(new receive_services_scope());
         
        		((receive_services_scope)receive_services_stack.peek()).roots = new ArrayList<ISubMenu>();
        		((receive_services_scope)receive_services_stack.peek()).updates = new ArrayList<IUpdateMenu>();
        		((receive_services_scope)receive_services_stack.peek()).services = new ArrayList<IService>();
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:192:2: ( ( receive_services_group )+ ( state_service )* 'QQ(3)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:193:2: ( receive_services_group )+ ( state_service )* 'QQ(3)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:193:2: ( receive_services_group )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==23) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:193:2: receive_services_group
            	    {
            	    pushFollow(FOLLOW_receive_services_group_in_receive_services403);
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

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:194:2: ( state_service )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==28) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:194:2: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_services407);
            	    state_service();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,22,FOLLOW_22_in_receive_services411); 

            		LOGGER.finest("Fin de la transmission des services");
            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(((receive_services_scope)receive_services_stack.peek()).roots, ((receive_services_scope)receive_services_stack.peek()).updates, ((receive_services_scope)receive_services_stack.peek()).services);
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:201:1: receive_services_group : 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? ;
    public final void receive_services_group() throws RecognitionException {
        receive_services_group_stack.push(new receive_services_group_scope());
        Token root_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:203:2: ( 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:204:2: 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )?
            {
            match(input,23,FOLLOW_23_in_receive_services_group431); 
            pushFollow(FOLLOW_root_description_in_receive_services_group434);
            root_description();
            _fsp--;

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:206:2: ( service_description )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==27) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:206:2: service_description
            	    {
            	    pushFollow(FOLLOW_service_description_in_receive_services_group437);
            	    service_description();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,24,FOLLOW_24_in_receive_services_group441); 

            		LOGGER.finest("Fin de la reception du groupe de services");
            		((receive_services_scope)receive_services_stack.peek()).roots.add(((receive_services_group_scope)receive_services_group_stack.peek()).root);
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:211:2: ( 'VQ(' root_name= CAMI_STRING ')' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==25) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:212:2: 'VQ(' root_name= CAMI_STRING ')'
                    {
                    match(input,25,FOLLOW_25_in_receive_services_group449); 
                    root_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_services_group453); 
                    match(input,9,FOLLOW_9_in_receive_services_group455); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:217:1: root_description : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void root_description() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:218:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:219:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,26,FOLLOW_26_in_root_description475); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_root_description479); 
            match(input,11,FOLLOW_11_in_root_description481); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description485); 
            match(input,11,FOLLOW_11_in_root_description487); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description491); 
            match(input,9,FOLLOW_9_in_root_description492); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:225:1: service_description : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' ;
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:227:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:228:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')'
            {
            match(input,27,FOLLOW_27_in_service_description514); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description518); 
            match(input,11,FOLLOW_11_in_service_description520); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description524); 
            match(input,11,FOLLOW_11_in_service_description526); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:229:15: (question_type= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:229:15: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description531); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description534); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:229:45: (question_behavior= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:229:45: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description538); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description541); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:10: (set_item= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:10: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description546); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description549); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:29: (dialog= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:29: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description553); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description556); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:17: (stop_authorized= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:17: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description561); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description564); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:46: (output_formalism= CAMI_STRING )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==CAMI_STRING) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:46: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description568); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description571); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:76: (active_state= NUMBER )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NUMBER) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:76: active_state= NUMBER
                    {
                    active_state=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description575); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_service_description578); 

            	
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:258:1: state_service : 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' ;
    public final void state_service() throws RecognitionException {
        Token root_name=null;
        Token question_name=null;
        Token state=null;
        Token message=null;

         List<String> tq = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:2: ( 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:2: 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')'
            {
            match(input,28,FOLLOW_28_in_state_service600); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service604); 
            match(input,11,FOLLOW_11_in_state_service606); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service610); 
            match(input,11,FOLLOW_11_in_state_service612); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_state_service616); 
            match(input,11,FOLLOW_11_in_state_service618); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:88: (message= CAMI_STRING )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==CAMI_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:88: message= CAMI_STRING
                    {
                    message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service622); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_state_service625); 

            		if(state.getText().equals("7") || state.getText().equals("8")) {
            			LOGGER.finest("Reception d'un etat de service");
            			tq.add(root_name.getText());
            			tq.add(question_name.getText());
            			tq.add(state.getText());
            			if (message != null) { tq.add(message.getText()); } else { tq.add(""); }
            			
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:283:1: ko_message : 'KO(1' mess= CAMI_STRING ',' severity= NUMBER ')' ;
    public final void ko_message() throws RecognitionException {
        Token mess=null;
        Token severity=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:284:2: ( 'KO(1' mess= CAMI_STRING ',' severity= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:285:2: 'KO(1' mess= CAMI_STRING ',' severity= NUMBER ')'
            {
            match(input,29,FOLLOW_29_in_ko_message646); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ko_message650); 
            match(input,11,FOLLOW_11_in_ko_message652); 
            severity=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ko_message656); 
            match(input,9,FOLLOW_9_in_ko_message658); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:294:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:295:2: ( trace_message | warning_message | special_message )
            int alt15=3;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt15=1;
                }
                break;
            case 31:
                {
                alt15=2;
                }
                break;
            case 32:
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("294:1: message_to_user : ( trace_message | warning_message | special_message );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:295:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user673);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:296:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user678);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:297:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user683);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:301:1: trace_message : 'TR(' message= CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:302:2: ( 'TR(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:303:2: 'TR(' message= CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_trace_message697); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message701); 
            match(input,9,FOLLOW_9_in_trace_message703); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:311:1: warning_message : 'WN(' message= CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:312:2: ( 'WN(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:313:2: 'WN(' message= CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_warning_message719); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message723); 
            match(input,9,FOLLOW_9_in_warning_message725); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:321:1: special_message : 'MO(' type= NUMBER ',' message= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token type=null;
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:322:2: ( 'MO(' type= NUMBER ',' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:323:2: 'MO(' type= NUMBER ',' message= CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message741); 
            type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message745); 
            match(input,11,FOLLOW_11_in_special_message747); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message751); 
            match(input,9,FOLLOW_9_in_special_message753); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:355:1: receive_results : 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | result )* ;
    public final void receive_results() throws RecognitionException {
        Token root_name=null;
        Token service_name=null;
        Token deprecated=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:356:2: ( 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | result )* )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:357:2: 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | result )*
            {
            match(input,33,FOLLOW_33_in_receive_results777); 
            match(input,34,FOLLOW_34_in_receive_results780); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results784); 
            match(input,11,FOLLOW_11_in_receive_results786); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results790); 
            match(input,11,FOLLOW_11_in_receive_results792); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_receive_results796); 
            match(input,9,FOLLOW_9_in_receive_results798); 

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:359:2: ( state_service | special_message | warning_message | result )*
            loop16:
            do {
                int alt16=5;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    alt16=1;
                    }
                    break;
                case 32:
                    {
                    alt16=2;
                    }
                    break;
                case 31:
                    {
                    alt16=3;
                    }
                    break;
                case 35:
                    {
                    alt16=4;
                    }
                    break;

                }

                switch (alt16) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:359:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_results805);
            	    state_service();
            	    _fsp--;



            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:360:4: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_receive_results812);
            	    special_message();
            	    _fsp--;



            	    }
            	    break;
            	case 3 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:361:4: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_receive_results819);
            	    warning_message();
            	    _fsp--;



            	    }
            	    break;
            	case 4 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:362:4: result
            	    {
            	    pushFollow(FOLLOW_result_in_receive_results826);
            	    result();
            	    _fsp--;



            	    }
            	    break;

            	default :
            	    break loop16;
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:367:1: result : 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token set_name=null;
        Token set_type=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:368:2: ( 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:369:2: 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,35,FOLLOW_35_in_result848); 
            set_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result852); 
            match(input,11,FOLLOW_11_in_result854); 
            set_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result858); 
            match(input,9,FOLLOW_9_in_result860); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:370:2: ( result_body )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==35||(LA17_0>=37 && LA17_0<=43)||(LA17_0>=46 && LA17_0<=50)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:370:4: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result865);
            	    result_body();
            	    _fsp--;



            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            match(input,36,FOLLOW_36_in_result873); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:375:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:376:2: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt18=8;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt18=1;
                }
                break;
            case 37:
                {
                alt18=2;
                }
                break;
            case 38:
                {
                alt18=3;
                }
                break;
            case 40:
                {
                alt18=4;
                }
                break;
            case 41:
                {
                alt18=5;
                }
                break;
            case 39:
                {
                alt18=6;
                }
                break;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                {
                alt18=7;
                }
                break;
            case 42:
            case 43:
                {
                alt18=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("375:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:376:4: result
                    {
                    pushFollow(FOLLOW_result_in_result_body888);
                    result();
                    _fsp--;



                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:377:4: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body895);
                    textual_result();
                    _fsp--;



                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:378:4: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body902);
                    attribute_change();
                    _fsp--;



                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:379:4: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body909);
                    object_designation();
                    _fsp--;



                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:4: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body916);
                    object_outline();
                    _fsp--;



                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:381:4: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body923);
                    attribute_outline();
                    _fsp--;



                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:382:4: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body930);
                    object_creation();
                    _fsp--;



                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:383:4: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body937);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:387:1: textual_result : 'RT(' text= CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        Token text=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:388:2: ( 'RT(' text= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:389:2: 'RT(' text= CAMI_STRING ')'
            {
            match(input,37,FOLLOW_37_in_textual_result954); 
            text=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result958); 
            match(input,9,FOLLOW_9_in_textual_result960); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:1: attribute_change : 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token new_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:394:2: ( 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:395:2: 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,38,FOLLOW_38_in_attribute_change977); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change981); 
            match(input,11,FOLLOW_11_in_attribute_change983); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change987); 
            match(input,11,FOLLOW_11_in_attribute_change989); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change993); 
            match(input,9,FOLLOW_9_in_attribute_change995); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:399:1: attribute_outline : 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:400:2: ( 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:2: 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,39,FOLLOW_39_in_attribute_outline1012); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1016); 
            match(input,11,FOLLOW_11_in_attribute_outline1018); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1022); 
            match(input,11,FOLLOW_11_in_attribute_outline1024); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:58: (begin= NUMBER )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NUMBER) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:58: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1028); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1031); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:74: (end= NUMBER )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NUMBER) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:74: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1035); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1038); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:406:2: ( 'RO(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:407:2: 'RO(' id= NUMBER ')'
            {
            match(input,40,FOLLOW_40_in_object_designation1054); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1058); 
            match(input,9,FOLLOW_9_in_object_designation1060); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:411:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:412:2: ( 'ME(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:413:2: 'ME(' id= NUMBER ')'
            {
            match(input,41,FOLLOW_41_in_object_outline1078); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1082); 
            match(input,9,FOLLOW_9_in_object_outline1084); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:417:1: object_creation : ( node | box | arc | attribute );
    public final void object_creation() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:418:2: ( node | box | arc | attribute )
            int alt21=4;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt21=1;
                }
                break;
            case 47:
                {
                alt21=2;
                }
                break;
            case 48:
                {
                alt21=3;
                }
                break;
            case 49:
            case 50:
                {
                alt21=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("417:1: object_creation : ( node | box | arc | attribute );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:418:4: node
                    {
                    pushFollow(FOLLOW_node_in_object_creation1099);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:419:4: box
                    {
                    pushFollow(FOLLOW_box_in_object_creation1104);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:420:4: arc
                    {
                    pushFollow(FOLLOW_arc_in_object_creation1109);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:421:4: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_object_creation1114);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:425:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:426:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==42) ) {
                alt22=1;
            }
            else if ( (LA22_0==43) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("425:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:426:4: 'SU(' id= NUMBER ')'
                    {
                    match(input,42,FOLLOW_42_in_object_deletion1127); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1131); 
                    match(input,9,FOLLOW_9_in_object_deletion1133); 


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:427:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,43,FOLLOW_43_in_object_deletion1141); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1145); 
                    match(input,11,FOLLOW_11_in_object_deletion1147); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1151); 
                    match(input,9,FOLLOW_9_in_object_deletion1153); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:435:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        model_definition_stack.push(new model_definition_scope());
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:437:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:438:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,44,FOLLOW_44_in_model_definition1185); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:439:2: ( syntactic | aestetic )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=46 && LA23_0<=50)) ) {
                alt23=1;
            }
            else if ( ((LA23_0>=51 && LA23_0<=55)) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("439:2: ( syntactic | aestetic )", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:439:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition1190);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:439:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition1194);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,45,FOLLOW_45_in_model_definition1199); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:444:1: syntactic : ( node | box | arc | attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:445:2: ( node | box | arc | attribute )
            int alt24=4;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt24=1;
                }
                break;
            case 47:
                {
                alt24=2;
                }
                break;
            case 48:
                {
                alt24=3;
                }
                break;
            case 49:
            case 50:
                {
                alt24=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("444:1: syntactic : ( node | box | arc | attribute );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:446:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic1213);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:446:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic1217);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:446:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic1221);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:446:21: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_syntactic1225);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:2: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:2: 'CN(' CAMI_STRING ',' NUMBER ')'
            {
            match(input,46,FOLLOW_46_in_node1240); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node1242); 
            match(input,11,FOLLOW_11_in_node1244); 
            match(input,NUMBER,FOLLOW_NUMBER_in_node1246); 
            match(input,9,FOLLOW_9_in_node1248); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:456:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:456:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:457:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,47,FOLLOW_47_in_box1261); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box1263); 
            match(input,11,FOLLOW_11_in_box1265); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1267); 
            match(input,11,FOLLOW_11_in_box1269); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1271); 
            match(input,9,FOLLOW_9_in_box1273); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:461:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:461:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:462:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,48,FOLLOW_48_in_arc1286); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc1288); 
            match(input,11,FOLLOW_11_in_arc1290); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1292); 
            match(input,11,FOLLOW_11_in_arc1294); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1296); 
            match(input,11,FOLLOW_11_in_arc1298); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1300); 
            match(input,9,FOLLOW_9_in_arc1302); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:466:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void attribute() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:467:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==49) ) {
                alt25=1;
            }
            else if ( (LA25_0==50) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("466:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:467:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,49,FOLLOW_49_in_attribute1315); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1317); 
                    match(input,11,FOLLOW_11_in_attribute1319); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1321); 
                    match(input,11,FOLLOW_11_in_attribute1323); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1325); 
                    match(input,9,FOLLOW_9_in_attribute1327); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:468:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,50,FOLLOW_50_in_attribute1332); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1334); 
                    match(input,11,FOLLOW_11_in_attribute1336); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1338); 
                    match(input,11,FOLLOW_11_in_attribute1340); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1342); 
                    match(input,11,FOLLOW_11_in_attribute1344); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1346); 
                    match(input,11,FOLLOW_11_in_attribute1348); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1350); 
                    match(input,9,FOLLOW_9_in_attribute1352); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:473:2: ( object_position | text_position | intermediary_point )
            int alt26=3;
            switch ( input.LA(1) ) {
            case 51:
            case 52:
            case 53:
                {
                alt26=1;
                }
                break;
            case 54:
                {
                alt26=2;
                }
                break;
            case 55:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("472:1: aestetic : ( object_position | text_position | intermediary_point );", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic1366);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic1370);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic1374);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:478:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:479:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt27=3;
            switch ( input.LA(1) ) {
            case 51:
                {
                alt27=1;
                }
                break;
            case 52:
                {
                alt27=2;
                }
                break;
            case 53:
                {
                alt27=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("478:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:479:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,51,FOLLOW_51_in_object_position1387); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1391); 
                    match(input,11,FOLLOW_11_in_object_position1393); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1397); 
                    match(input,11,FOLLOW_11_in_object_position1399); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1403); 
                    match(input,9,FOLLOW_9_in_object_position1405); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:480:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,52,FOLLOW_52_in_object_position1410); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1414); 
                    match(input,11,FOLLOW_11_in_object_position1416); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1420); 
                    match(input,11,FOLLOW_11_in_object_position1422); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1426); 
                    match(input,9,FOLLOW_9_in_object_position1428); 

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:481:4: 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,53,FOLLOW_53_in_object_position1433); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1437); 
                    match(input,11,FOLLOW_11_in_object_position1439); 
                    left=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1443); 
                    match(input,11,FOLLOW_11_in_object_position1445); 
                    right=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1449); 
                    match(input,11,FOLLOW_11_in_object_position1451); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1455); 
                    match(input,11,FOLLOW_11_in_object_position1457); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1461); 
                    match(input,9,FOLLOW_9_in_object_position1462); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:485:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:486:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:487:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,54,FOLLOW_54_in_text_position1477); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1481); 
            match(input,11,FOLLOW_11_in_text_position1483); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position1487); 
            match(input,11,FOLLOW_11_in_text_position1489); 
            h_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1493); 
            match(input,11,FOLLOW_11_in_text_position1495); 
            v_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1499); 
            match(input,9,FOLLOW_9_in_text_position1501); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:491:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:492:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:493:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,55,FOLLOW_55_in_intermediary_point1516); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1518); 
            match(input,11,FOLLOW_11_in_intermediary_point1520); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1522); 
            match(input,11,FOLLOW_11_in_intermediary_point1524); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1526); 
            match(input,9,FOLLOW_9_in_intermediary_point1528); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:501:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        dialog_definition_stack.push(new dialog_definition_scope());
         List<IDialog> dialogs = new ArrayList<IDialog>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:504:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:505:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,56,FOLLOW_56_in_dialog_definition1562); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition1565);
            dialog_creation();
            _fsp--;

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:507:2: ( next_dialog )+
            int cnt28=0;
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==59) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:2: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition1571);
            	    next_dialog();
            	    _fsp--;

            	     

            	    }
            	    break;

            	default :
            	    if ( cnt28 >= 1 ) break loop28;
                        EarlyExitException eee =
                            new EarlyExitException(28, input);
                        throw eee;
                }
                cnt28++;
            } while (true);

            match(input,57,FOLLOW_57_in_dialog_definition1581); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:514:1: dialog_creation : 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' ;
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:516:2: ( 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:517:2: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
            {
            match(input,58,FOLLOW_58_in_dialog_creation1603); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1607); 
            match(input,11,FOLLOW_11_in_dialog_creation1609); 
            dialog_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1613); 
            match(input,11,FOLLOW_11_in_dialog_creation1615); 
            buttons_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1621); 
            match(input,11,FOLLOW_11_in_dialog_creation1623); 
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1628); 
            match(input,11,FOLLOW_11_in_dialog_creation1630); 
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1636); 
            match(input,11,FOLLOW_11_in_dialog_creation1638); 
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1642); 
            match(input,11,FOLLOW_11_in_dialog_creation1644); 
            input_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1650); 
            match(input,11,FOLLOW_11_in_dialog_creation1652); 
            line_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1656); 
            match(input,11,FOLLOW_11_in_dialog_creation1658); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:521:15: (default_value= CAMI_STRING )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==CAMI_STRING) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:521:15: default_value= CAMI_STRING
                    {
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1664); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_dialog_creation1667); 

            	
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:541:1: next_dialog : 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        Token dialog_id=null;
        Token line=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:542:2: ( 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:543:2: 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')'
            {
            match(input,59,FOLLOW_59_in_next_dialog1683); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_next_dialog1687); 
            match(input,11,FOLLOW_11_in_next_dialog1689); 
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog1693); 
            match(input,9,FOLLOW_9_in_next_dialog1695); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:547:1: display_dialog : 'AD(' dialog_id= NUMBER ')' ;
    public final void display_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:548:2: ( 'AD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:2: 'AD(' dialog_id= NUMBER ')'
            {
            match(input,60,FOLLOW_60_in_display_dialog1711); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_display_dialog1715); 
            match(input,9,FOLLOW_9_in_display_dialog1717); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:553:1: hide_dialog : 'HD(' dialog_id= NUMBER ')' ;
    public final void hide_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:554:2: ( 'HD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:555:2: 'HD(' dialog_id= NUMBER ')'
            {
            match(input,61,FOLLOW_61_in_hide_dialog1733); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_hide_dialog1737); 
            match(input,9,FOLLOW_9_in_hide_dialog1739); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:559:1: destroy_dialog : 'DG(' dialog_id= NUMBER ')' ;
    public final void destroy_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:560:2: ( 'DG(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:561:2: 'DG(' dialog_id= NUMBER ')'
            {
            match(input,62,FOLLOW_62_in_destroy_dialog1756); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_destroy_dialog1760); 
            match(input,9,FOLLOW_9_in_destroy_dialog1762); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:565:1: interactive_response : 'MI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:566:2: ( 'MI(' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:567:2: 'MI(' NUMBER ',' NUMBER ')'
            {
            match(input,63,FOLLOW_63_in_interactive_response1777); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response1779); 
            match(input,11,FOLLOW_11_in_interactive_response1781); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response1783); 
            match(input,9,FOLLOW_9_in_interactive_response1785); 

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
    public static final BitSet FOLLOW_close_connection_in_main58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_session_in_main66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_session_in_main71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_suspend_session_in_main76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resume_session_in_main81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_in_main89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_main94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_main102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_results_in_main110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ko_message_in_main118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_open_communication144 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_communication147 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_communication149 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_open_connection_in_open_communication154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_open_connection170 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection174 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_open_connection176 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection180 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_connection182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_open_session232 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_session236 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_session238 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_open_session241 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_open_session244 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_interlocutor_table_in_open_session247 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_receive_services_in_open_session252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table267 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table270 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_interlocutor_table274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_body_table289 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table293 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table295 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table299 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table301 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table305 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table307 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table311 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_body_table313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_suspend_session330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_resume_session346 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_resume_session350 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_resume_session352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_close_session369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_group_in_receive_services403 = new BitSet(new long[]{0x0000000010C00000L});
    public static final BitSet FOLLOW_state_service_in_receive_services407 = new BitSet(new long[]{0x0000000010400000L});
    public static final BitSet FOLLOW_22_in_receive_services411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_receive_services_group431 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_root_description_in_receive_services_group434 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_service_description_in_receive_services_group437 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_24_in_receive_services_group441 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_receive_services_group449 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_services_group453 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receive_services_group455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_root_description475 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_root_description479 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_root_description481 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description485 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_root_description487 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description491 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_root_description492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_service_description514 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description518 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description520 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description524 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description526 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description531 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description534 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description538 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description541 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description546 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description549 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description553 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description556 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description561 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description564 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description568 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description571 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_service_description575 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_service_description578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_state_service600 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service604 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service606 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service610 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service612 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_state_service616 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service618 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service622 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_state_service625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ko_message646 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ko_message650 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ko_message652 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ko_message656 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ko_message658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_trace_message697 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message701 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_warning_message719 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message723 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_special_message741 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message745 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message747 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message751 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_receive_results777 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_receive_results780 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results784 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_receive_results786 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results790 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_receive_results792 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_receive_results796 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receive_results798 = new BitSet(new long[]{0x0000000990000002L});
    public static final BitSet FOLLOW_state_service_in_receive_results805 = new BitSet(new long[]{0x0000000990000002L});
    public static final BitSet FOLLOW_special_message_in_receive_results812 = new BitSet(new long[]{0x0000000990000002L});
    public static final BitSet FOLLOW_warning_message_in_receive_results819 = new BitSet(new long[]{0x0000000990000002L});
    public static final BitSet FOLLOW_result_in_receive_results826 = new BitSet(new long[]{0x0000000990000002L});
    public static final BitSet FOLLOW_35_in_result848 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result852 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result854 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result858 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result860 = new BitSet(new long[]{0x0007CFE800000000L});
    public static final BitSet FOLLOW_result_body_in_result865 = new BitSet(new long[]{0x0007CFF800000000L});
    public static final BitSet FOLLOW_36_in_result873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_textual_result954 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result958 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_attribute_change977 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change981 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change983 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change987 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change989 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change993 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_change995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_attribute_outline1012 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1016 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1018 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1022 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1024 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1028 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1031 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1035 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_object_designation1054 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1058 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_object_outline1078 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1082 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_object_creation1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_object_creation1104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_object_creation1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_object_creation1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_object_deletion1127 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1131 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_object_deletion1141 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1145 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1147 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1151 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_model_definition1185 = new BitSet(new long[]{0x00FFC00000000000L});
    public static final BitSet FOLLOW_syntactic_in_model_definition1190 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_aestetic_in_model_definition1194 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_model_definition1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic1213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_syntactic1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_node1240 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node1242 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node1244 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node1246 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_node1248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_box1261 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box1263 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1265 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1267 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1269 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1271 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_box1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_arc1286 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc1288 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1290 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1292 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1294 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1296 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1298 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1300 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_arc1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_attribute1315 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1317 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1319 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1321 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1323 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1325 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_attribute1332 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1334 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1336 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1338 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1340 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1342 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1344 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1346 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1348 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1350 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic1370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic1374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_position1387 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1391 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1393 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1397 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1399 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1403 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_position1410 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1414 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1416 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1420 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1422 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1426 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_position1433 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1437 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1439 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1443 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1445 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1449 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1451 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1455 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1457 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1461 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_text_position1477 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1481 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1483 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position1487 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1489 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1493 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1495 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1499 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_text_position1501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_intermediary_point1516 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1518 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point1520 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1522 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point1524 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1526 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_intermediary_point1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_dialog_definition1562 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition1565 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition1571 = new BitSet(new long[]{0x0A00000000000000L});
    public static final BitSet FOLLOW_57_in_dialog_definition1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_dialog_creation1603 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1607 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1609 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1613 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1615 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1621 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1623 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1628 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1630 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1636 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1638 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1642 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1644 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1650 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1652 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1656 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1658 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1664 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_creation1667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_next_dialog1683 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_next_dialog1687 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog1689 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog1693 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_next_dialog1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_display_dialog1711 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_display_dialog1715 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_display_dialog1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_hide_dialog1733 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_hide_dialog1737 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_hide_dialog1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_destroy_dialog1756 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_destroy_dialog1760 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_destroy_dialog1762 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_interactive_response1777 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response1779 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response1781 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response1783 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interactive_response1785 = new BitSet(new long[]{0x0000000000000002L});

}