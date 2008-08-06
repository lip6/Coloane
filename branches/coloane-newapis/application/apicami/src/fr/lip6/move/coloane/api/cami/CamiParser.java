// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-08-06 15:12:51

package fr.lip6.move.coloane.api.cami;
	
import fr.lip6.move.coloane.api.camiObject.ConnectionInfo;
import fr.lip6.move.coloane.api.observables.ReceptDialogObservable;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.api.observables.ReceptServiceStateObservable;
import fr.lip6.move.coloane.api.camiObject.ReceptServiceState;
import fr.lip6.move.coloane.api.observables.DisconnectObservable;
import fr.lip6.move.coloane.api.observables.ConnectionObservable;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.api.session.SessionController;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.api.camiObject.Result;
import fr.lip6.move.coloane.api.camiObject.SubResult;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.api.observables.ReceptResultObservable;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.camiObject.Dialog;
import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'FL()'", "'VI('", "'SS()'", "'RS('", "'FS()'", "'QQ(3)'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'QQ(2)'", "'KO('", "'TR('", "'WN('", "'MO('", "'DF(-'", "'DR()'", "'RQ('", "'FR('", "'DE('", "'FE()'", "'RT('", "'WE('", "'MT('", "'RO('", "'ME('", "'SU('", "'SI('", "'ZA('", "'XA('", "'TD('", "'OB('", "'AT('", "'DB()'", "'FB()'", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('"
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

    /** Un indicateur d'état */
    private static int state = ICamiParserState.DEFAULT_STATE;

    /** Permet de consulter l'état en cours du parser */
    public int getState() {
    	return state;
    }

    /** L'objet résultat : STATIQUE pour pouvoir le remplir en plusieurs passes */
    private static IResult result;

    private static List<IUpdateMenu> updates;
    private static Map<Integer, IDialog> dialogs;




    // $ANTLR start main
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:83:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | ko_message );
    public final void main() throws RecognitionException {
         state = ICamiParserState.DEFAULT_STATE; 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:86:2: ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | ko_message )
            int alt1=11;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:86:4: open_communication
                    {
                    pushFollow(FOLLOW_open_communication_in_main58);
                    open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:87:4: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_main63);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:89:4: open_session
                    {
                    pushFollow(FOLLOW_open_session_in_main71);
                    open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:90:4: close_session
                    {
                    pushFollow(FOLLOW_close_session_in_main76);
                    close_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:91:4: suspend_session
                    {
                    pushFollow(FOLLOW_suspend_session_in_main81);
                    suspend_session();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:92:4: resume_session
                    {
                    pushFollow(FOLLOW_resume_session_in_main86);
                    resume_session();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:94:4: invalid_model
                    {
                    pushFollow(FOLLOW_invalid_model_in_main94);
                    invalid_model();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:96:4: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_main102);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:98:4: ask_for_model
                    {
                    pushFollow(FOLLOW_ask_for_model_in_main110);
                    ask_for_model();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:99:4: receive_results
                    {
                    pushFollow(FOLLOW_receive_results_in_main115);
                    receive_results();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:101:4: ko_message
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

    protected static class open_communication_scope {
        IConnectionInfo connectionInfo;
    }
    protected Stack open_communication_stack = new Stack();


    // $ANTLR start open_communication
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:109:1: open_communication : 'SC(' fkName= CAMI_STRING ')' open_connection ;
    public final void open_communication() throws RecognitionException {
        open_communication_stack.push(new open_communication_scope());
        Token fkName=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:111:2: ( 'SC(' fkName= CAMI_STRING ')' open_connection )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:112:2: 'SC(' fkName= CAMI_STRING ')' open_connection
            {
            match(input,8,FOLLOW_8_in_open_communication149); 
            fkName=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_communication152); 
            match(input,9,FOLLOW_9_in_open_communication154); 

            		LOGGER.finest("Creation de l'objet de connexion");
            		((open_communication_scope)open_communication_stack.peek()).connectionInfo = new ConnectionInfo(fkName.getText());
            	
            pushFollow(FOLLOW_open_connection_in_open_communication159);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:120:1: open_connection : 'OC(' major= NUMBER ',' minor= NUMBER ')' ;
    public final void open_connection() throws RecognitionException {
        Token major=null;
        Token minor=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:121:2: ( 'OC(' major= NUMBER ',' minor= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:122:2: 'OC(' major= NUMBER ',' minor= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_open_connection175); 
            major=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection179); 
            match(input,11,FOLLOW_11_in_open_connection181); 
            minor=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection185); 
            match(input,9,FOLLOW_9_in_open_connection187); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:130:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:131:2: ( 'FC()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:132:2: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection203); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:142:1: open_session : ( message_to_user )* 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ( message_to_user )* receive_services ;
    public final void open_session() throws RecognitionException {
        open_session_stack.push(new open_session_scope());
        Token session_name=null;

         ((open_session_scope)open_session_stack.peek()).sessionArgs = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:145:2: ( ( message_to_user )* 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ( message_to_user )* receive_services )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:146:2: ( message_to_user )* 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ( message_to_user )* receive_services
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:146:2: ( message_to_user )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=31 && LA2_0<=33)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:146:2: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_open_session237);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_open_session241); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_session245); 
            match(input,9,FOLLOW_9_in_open_session247); 
            match(input,14,FOLLOW_14_in_open_session250); 
            match(input,15,FOLLOW_15_in_open_session253); 
            pushFollow(FOLLOW_interlocutor_table_in_open_session256);
            interlocutor_table();
            _fsp--;


            		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo(((open_session_scope)open_session_stack.peek()).sessionArgs);
            		sessionController.notifyReceptSessionInfo(sessionInfo);
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:2: ( message_to_user )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=31 && LA3_0<=33)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:2: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_open_session261);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            pushFollow(FOLLOW_receive_services_in_open_session265);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:159:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:160:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:161:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,16,FOLLOW_16_in_interlocutor_table280); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:162:2: ( body_table )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==18) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:162:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table283);
            	    body_table();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            match(input,17,FOLLOW_17_in_interlocutor_table287); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:167:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token deprecated=null;
        Token new_model=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:168:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:169:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')'
            {
            match(input,18,FOLLOW_18_in_body_table302); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table306); 
            match(input,11,FOLLOW_11_in_body_table308); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table312); 
            match(input,11,FOLLOW_11_in_body_table314); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table318); 
            match(input,11,FOLLOW_11_in_body_table320); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table324); 
            match(input,9,FOLLOW_9_in_body_table326); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:178:1: suspend_session : 'SS()' ;
    public final void suspend_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:179:2: ( 'SS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:180:2: 'SS()'
            {
            match(input,19,FOLLOW_19_in_suspend_session343); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:187:1: resume_session : 'RS(' session_name= CAMI_STRING ')' ;
    public final void resume_session() throws RecognitionException {
        Token session_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:188:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:189:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,20,FOLLOW_20_in_resume_session359); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_resume_session363); 
            match(input,9,FOLLOW_9_in_resume_session365); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:196:1: close_session : 'FS()' ;
    public final void close_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: ( 'FS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:198:2: 'FS()'
            {
            match(input,21,FOLLOW_21_in_close_session382); 

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
        List<IService> services;
    }
    protected Stack receive_services_stack = new Stack();


    // $ANTLR start receive_services
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:209:1: receive_services : ( receive_services_group )+ ( state_service )+ 'QQ(3)' ;
    public final void receive_services() throws RecognitionException {
        receive_services_stack.push(new receive_services_scope());
        ISubMenu receive_services_group1 = null;

        IUpdateMenu state_service2 = null;


         
        		List<ISubMenu> roots = new ArrayList<ISubMenu>();
        		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>();
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:215:2: ( ( receive_services_group )+ ( state_service )+ 'QQ(3)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:216:2: ( receive_services_group )+ ( state_service )+ 'QQ(3)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:216:2: ( receive_services_group )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==23) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:216:4: receive_services_group
            	    {
            	    pushFollow(FOLLOW_receive_services_group_in_receive_services418);
            	    receive_services_group1=receive_services_group();
            	    _fsp--;

            	     roots.add(receive_services_group1); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:217:2: ( state_service )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==28) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:217:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_services428);
            	    state_service2=state_service();
            	    _fsp--;

            	     updates.add(state_service2); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            match(input,22,FOLLOW_22_in_receive_services436); 

            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(roots, updates);
            		sessionController.notifyEndOpenSession();
            	

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


    // $ANTLR start receive_services_group
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:224:1: receive_services_group returns [ISubMenu builtRoot] : 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? ;
    public final ISubMenu receive_services_group() throws RecognitionException {
        ISubMenu builtRoot = null;

        Token root_name=null;
        ISubMenu root_description3 = null;

        IQuestion service_description4 = null;


         LOGGER.finest("Reception d'une liste de services (Groupe)"); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:227:2: ( 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:228:2: 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )?
            {
            match(input,23,FOLLOW_23_in_receive_services_group462); 
            pushFollow(FOLLOW_root_description_in_receive_services_group465);
            root_description3=root_description();
            _fsp--;

             builtRoot = root_description3; 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:2: ( service_description )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==27) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:4: service_description
            	    {
            	    pushFollow(FOLLOW_service_description_in_receive_services_group473);
            	    service_description4=service_description();
            	    _fsp--;

            	     ((SubMenu) builtRoot).addQuestion(service_description4); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            match(input,24,FOLLOW_24_in_receive_services_group481); 
             LOGGER.finest("Fin de la reception du groupe de services"); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:232:2: ( 'VQ(' root_name= CAMI_STRING ')' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==25) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:232:4: 'VQ(' root_name= CAMI_STRING ')'
                    {
                    match(input,25,FOLLOW_25_in_receive_services_group488); 
                    root_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_services_group492); 
                    match(input,9,FOLLOW_9_in_receive_services_group494); 

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
        }
        return builtRoot;
    }
    // $ANTLR end receive_services_group


    // $ANTLR start root_description
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:236:1: root_description returns [ISubMenu root] : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final ISubMenu root_description() throws RecognitionException {
        ISubMenu root = null;

        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:238:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:239:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,26,FOLLOW_26_in_root_description516); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_root_description520); 
            match(input,11,FOLLOW_11_in_root_description522); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description526); 
            match(input,11,FOLLOW_11_in_root_description528); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description532); 
            match(input,9,FOLLOW_9_in_root_description533); 

            		root = CamiObjectBuilder.buildRootMenu(name.getText(), question_type.getText(), question_behavior.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return root;
    }
    // $ANTLR end root_description


    // $ANTLR start service_description
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:245:1: service_description returns [IQuestion question] : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' ;
    public final IQuestion service_description() throws RecognitionException {
        IQuestion question = null;

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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')'
            {
            match(input,27,FOLLOW_27_in_service_description560); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description564); 
            match(input,11,FOLLOW_11_in_service_description566); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description570); 
            match(input,11,FOLLOW_11_in_service_description572); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:15: (question_type= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:15: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description577); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description580); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:45: (question_behavior= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:45: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description584); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description587); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:251:10: (set_item= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:251:10: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description592); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description595); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:251:29: (dialog= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:251:29: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description599); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description602); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:252:17: (stop_authorized= NUMBER )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NUMBER) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:252:17: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description607); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description610); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:252:46: (output_formalism= CAMI_STRING )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==CAMI_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:252:46: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description614); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description617); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:252:76: (active_state= NUMBER )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NUMBER) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:252:76: active_state= NUMBER
                    {
                    active_state=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description621); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_service_description624); 

            	
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
            		question = CamiObjectBuilder.buildQuestion(aq);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return question;
    }
    // $ANTLR end service_description


    // $ANTLR start state_service
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:272:1: state_service returns [IUpdateMenu builtUpdate] : 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' ;
    public final IUpdateMenu state_service() throws RecognitionException {
        IUpdateMenu builtUpdate = null;

        Token root_name=null;
        Token question_name=null;
        Token state=null;
        Token message=null;

         List<String> tq = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:275:2: ( 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:276:2: 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')'
            {
            match(input,28,FOLLOW_28_in_state_service651); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service655); 
            match(input,11,FOLLOW_11_in_state_service657); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service661); 
            match(input,11,FOLLOW_11_in_state_service663); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_state_service667); 
            match(input,11,FOLLOW_11_in_state_service669); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:276:88: (message= CAMI_STRING )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==CAMI_STRING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:276:88: message= CAMI_STRING
                    {
                    message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service673); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_state_service676); 

            		if(state.getText().equals("7") || state.getText().equals("8")) {
            			LOGGER.finest("Reception d'un etat de service");
            			tq.add(root_name.getText());
            			tq.add(question_name.getText());
            			tq.add(state.getText());
            			if (message != null) { tq.add(message.getText()); } else { tq.add(""); }
            			
            			// Construction de la mise a jour
            			builtUpdate = CamiObjectBuilder.buildUpdate(tq);
            		} else if(state.getText().equals("2")) { 
            			if(message != null) { 
            				LOGGER.finest("Reception d'un TQ 2"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),2,message.getText()); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} else { 
            				LOGGER.finest("Reception d'un TQ 2"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),2,null); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} 
            		} 

            		if(state.getText().equals("3")) { 
            			if(message != null) { 
            				LOGGER.finest("Reception d'un TQ 3"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),3,message.getText()); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} else { 
            				LOGGER.finest("Reception d'un TQ 3"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),3,null); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} 
            		} 
                
            		if(state.getText().equals("4")) { 
            			if(message != null) { 
            				LOGGER.finest("Reception d'un TQ 4"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),4,message.getText()); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} else { 
            				LOGGER.finest("Reception d'un TQ 4"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),4,null); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} 
            		} 
                
            		if(state.getText().equals("5")) { 
            			if(message != null) { 
            				LOGGER.finest("Reception d'un TQ 5"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),5,message.getText()); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} else { 
            				LOGGER.finest("Reception d'un TQ 5"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),5,null); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} 
            		} 

            		if(state.getText().equals("6")) {
            			if(message != null) { 
            				LOGGER.finest("Reception d'un TQ 6"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),6,message.getText()); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} else { 
            				LOGGER.finest("Reception d'un TQ 6"); 
            				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState(question_name.getText(),6,null); 
            				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
            			} 
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return builtUpdate;
    }
    // $ANTLR end state_service


    // $ANTLR start invalid_model
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:348:1: invalid_model : ( state_service )* 'QQ(2)' ;
    public final void invalid_model() throws RecognitionException {
        IUpdateMenu state_service5 = null;


         List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:350:2: ( ( state_service )* 'QQ(2)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:351:2: ( state_service )* 'QQ(2)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:351:2: ( state_service )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==28) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:351:3: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_invalid_model698);
            	    state_service5=state_service();
            	    _fsp--;

            	     updates.add(state_service5); 

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            match(input,29,FOLLOW_29_in_invalid_model706); 

            		LOGGER.finest("Fin de la mise a jour (status=2)");
            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates); 
            		sessionController.notifyEndInvalidModel();
            	

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
    // $ANTLR end invalid_model


    // $ANTLR start ko_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:1: ko_message : 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')' ;
    public final void ko_message() throws RecognitionException {
        Token mess=null;
        Token severity=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:364:2: ( 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:365:2: 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')'
            {
            match(input,30,FOLLOW_30_in_ko_message727); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ko_message729); 
            match(input,11,FOLLOW_11_in_ko_message731); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ko_message735); 
            match(input,11,FOLLOW_11_in_ko_message737); 
            severity=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ko_message741); 
            match(input,9,FOLLOW_9_in_ko_message743); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:374:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:375:2: ( trace_message | warning_message | special_message )
            int alt18=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt18=1;
                }
                break;
            case 32:
                {
                alt18=2;
                }
                break;
            case 33:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("374:1: message_to_user : ( trace_message | warning_message | special_message );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:375:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user758);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:376:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user763);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:377:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user768);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:381:1: trace_message : 'TR(' message= CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:382:2: ( 'TR(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:383:2: 'TR(' message= CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message782); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message786); 
            match(input,9,FOLLOW_9_in_trace_message788); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:391:1: warning_message : 'WN(' message= CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:392:2: ( 'WN(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:2: 'WN(' message= CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message804); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message808); 
            match(input,9,FOLLOW_9_in_warning_message810); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:1: special_message : 'MO(' type= NUMBER ',' message= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token type=null;
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:402:2: ( 'MO(' type= NUMBER ',' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:403:2: 'MO(' type= NUMBER ',' message= CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message826); 
            type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message830); 
            match(input,11,FOLLOW_11_in_special_message832); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message836); 
            match(input,9,FOLLOW_9_in_special_message838); 

            		if(type.getText().equals("1")) { 
            			LOGGER.finest("Reception d'un message de l'administrateur"); 
            			IReceptMessage msg =(IReceptMessage) new ReceptMessage(1,message.getText()); 
            			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
            		}
                
            		if(type.getText().equals("2")) {
            			//TODO :Verifier qu'il faut traiter ce message comme un KO
            			LOGGER.finest("Reception d'un message court et urgent");
            			IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,message.getText()); 
            			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
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


    // $ANTLR start ask_for_model
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:435:1: ask_for_model : ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void ask_for_model() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:436:2: ( ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:437:2: ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:437:2: ( state_service )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==28) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:437:2: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_ask_for_model859);
            	    state_service();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            match(input,34,FOLLOW_34_in_ask_for_model863); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_model865); 
            match(input,11,FOLLOW_11_in_ask_for_model867); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_model869); 
            match(input,11,FOLLOW_11_in_ask_for_model871); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_model873); 
            match(input,9,FOLLOW_9_in_ask_for_model875); 

            		sessionController.notifyWaitingForModel();
            	

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
    // $ANTLR end ask_for_model


    // $ANTLR start receive_results
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:444:1: receive_results : 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ;
    public final void receive_results() throws RecognitionException {
        Token root_name=null;
        Token service_name=null;
        Token deprecated=null;

         
        		state = ICamiParserState.RESULT_STATE;
        		updates = new ArrayList<IUpdateMenu>();
        		dialogs = new HashMap<Integer, IDialog>();
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:2: ( 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:2: 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')'
            {
            match(input,35,FOLLOW_35_in_receive_results898); 
            match(input,36,FOLLOW_36_in_receive_results901); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results905); 
            match(input,11,FOLLOW_11_in_receive_results907); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results911); 
            match(input,11,FOLLOW_11_in_receive_results913); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_receive_results917); 
            match(input,9,FOLLOW_9_in_receive_results919); 

            		result = CamiObjectBuilder.buildResult(root_name.getText(), service_name.getText());
            	

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


    // $ANTLR start results
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:457:1: results : ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes )* ( 'FR(' NUMBER ')' )? ;
    public final void results() throws RecognitionException {
        IUpdateMenu state_service6 = null;

        IDialog dialog_definition7 = null;

        ISubResult one_result8 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:458:2: ( ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes )* ( 'FR(' NUMBER ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:459:2: ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes )* ( 'FR(' NUMBER ')' )?
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:459:2: ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes )*
            loop20:
            do {
                int alt20=8;
                switch ( input.LA(1) ) {
                case 31:
                case 32:
                case 33:
                    {
                    alt20=1;
                    }
                    break;
                case 28:
                    {
                    alt20=2;
                    }
                    break;
                case 64:
                    {
                    alt20=3;
                    }
                    break;
                case 68:
                    {
                    alt20=4;
                    }
                    break;
                case 70:
                    {
                    alt20=5;
                    }
                    break;
                case 38:
                    {
                    alt20=6;
                    }
                    break;
                case 47:
                case 48:
                case 49:
                    {
                    alt20=7;
                    }
                    break;

                }

                switch (alt20) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:459:4: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_results936);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:460:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_results941);
            	    state_service6=state_service();
            	    _fsp--;

            	     updates.add(state_service6); 

            	    }
            	    break;
            	case 3 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:461:4: dialog_definition
            	    {
            	    pushFollow(FOLLOW_dialog_definition_in_results948);
            	    dialog_definition7=dialog_definition();
            	    _fsp--;

            	     dialogs.put(dialog_definition7.getId(), dialog_definition7); 

            	    }
            	    break;
            	case 4 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:462:4: dialog_display
            	    {
            	    pushFollow(FOLLOW_dialog_display_in_results955);
            	    dialog_display();
            	    _fsp--;


            	    }
            	    break;
            	case 5 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:463:4: dialog_destroy
            	    {
            	    pushFollow(FOLLOW_dialog_destroy_in_results960);
            	    dialog_destroy();
            	    _fsp--;


            	    }
            	    break;
            	case 6 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:464:4: one_result
            	    {
            	    pushFollow(FOLLOW_one_result_in_results965);
            	    one_result8=one_result();
            	    _fsp--;

            	     ((Result) result).addSubResult(one_result8); 

            	    }
            	    break;
            	case 7 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:465:4: model_changes
            	    {
            	    pushFollow(FOLLOW_model_changes_in_results972);
            	    model_changes();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:467:2: ( 'FR(' NUMBER ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==37) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:468:2: 'FR(' NUMBER ')'
                    {
                    match(input,37,FOLLOW_37_in_results982); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_results984); 
                    match(input,9,FOLLOW_9_in_results986); 

                    		LOGGER.finest("Transmission des resultats");
                    		((ReceptResultObservable)hash.get("IReceptResult")).notifyObservers(result);
                    		LOGGER.finest("Transmission des mises a jour des services");
                    		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates); 
                    		sessionController.notifyEndResult();
                    		
                    		// Remise a zero de l'etat du parser
                    		state = ICamiParserState.DEFAULT_STATE;
                    	

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
        }
        return ;
    }
    // $ANTLR end results

    protected static class one_result_scope {
        ISubResult current;
    }
    protected Stack one_result_stack = new Stack();


    // $ANTLR start one_result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:482:1: one_result returns [ISubResult builtResult] : 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final ISubResult one_result() throws RecognitionException {
        one_result_stack.push(new one_result_scope());
        ISubResult builtResult = null;

        Token set_name=null;
        Token set_type=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:485:2: ( 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:486:2: 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,38,FOLLOW_38_in_one_result1016); 
            set_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_one_result1020); 
            match(input,11,FOLLOW_11_in_one_result1022); 
            set_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_one_result1026); 
            match(input,9,FOLLOW_9_in_one_result1028); 

            		LOGGER.finest("Debut du parcours de l'ensemble de resultats");
            		((one_result_scope)one_result_stack.peek()).current = CamiObjectBuilder.buildSubResult(set_name.getText(), set_type.getText());
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:490:2: ( result_body )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==38||(LA22_0>=40 && LA22_0<=46)||(LA22_0>=54 && LA22_0<=58)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:490:4: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_one_result1035);
            	    result_body();
            	    _fsp--;



            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);

            match(input,39,FOLLOW_39_in_one_result1043); 

            		LOGGER.finest("Fin du parcours de l'ensemble de resultats");
            		builtResult = ((one_result_scope)one_result_stack.peek()).current;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            one_result_stack.pop();
        }
        return builtResult;
    }
    // $ANTLR end one_result


    // $ANTLR start result_body
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:498:1: result_body : ( one_result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:2: ( one_result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt23=8;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt23=1;
                }
                break;
            case 40:
                {
                alt23=2;
                }
                break;
            case 41:
                {
                alt23=3;
                }
                break;
            case 43:
                {
                alt23=4;
                }
                break;
            case 44:
                {
                alt23=5;
                }
                break;
            case 42:
                {
                alt23=6;
                }
                break;
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
                {
                alt23=7;
                }
                break;
            case 45:
            case 46:
                {
                alt23=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("498:1: result_body : ( one_result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:4: one_result
                    {
                    pushFollow(FOLLOW_one_result_in_result_body1058);
                    one_result();
                    _fsp--;



                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:500:4: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1065);
                    textual_result();
                    _fsp--;



                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:501:4: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1072);
                    attribute_change();
                    _fsp--;



                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:502:4: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1079);
                    object_designation();
                    _fsp--;



                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:503:4: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1086);
                    object_outline();
                    _fsp--;



                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:504:4: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1093);
                    attribute_outline();
                    _fsp--;



                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:505:4: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1100);
                    object_creation();
                    _fsp--;



                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:4: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1107);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:510:1: textual_result : 'RT(' text= CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        Token text=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:511:2: ( 'RT(' text= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:512:2: 'RT(' text= CAMI_STRING ')'
            {
            match(input,40,FOLLOW_40_in_textual_result1124); 
            text=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1128); 
            match(input,9,FOLLOW_9_in_textual_result1130); 
             ((SubResult) ((one_result_scope)one_result_stack.peek()).current).addTextualResult(text.getText()); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:516:1: attribute_change : 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token new_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:517:2: ( 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:518:2: 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,41,FOLLOW_41_in_attribute_change1147); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change1151); 
            match(input,11,FOLLOW_11_in_attribute_change1153); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1157); 
            match(input,11,FOLLOW_11_in_attribute_change1159); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1163); 
            match(input,9,FOLLOW_9_in_attribute_change1165); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:522:1: attribute_outline : 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:523:2: ( 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:2: 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,42,FOLLOW_42_in_attribute_outline1182); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1186); 
            match(input,11,FOLLOW_11_in_attribute_outline1188); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1192); 
            match(input,11,FOLLOW_11_in_attribute_outline1194); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:58: (begin= NUMBER )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:58: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1198); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1201); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:74: (end= NUMBER )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:74: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1205); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1208); 

            		((SubResult) ((one_result_scope)one_result_stack.peek()).current).addAttributeOutline(Integer.parseInt(id.getText()), attribute_name.getText());
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:530:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:531:2: ( 'RO(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:532:2: 'RO(' id= NUMBER ')'
            {
            match(input,43,FOLLOW_43_in_object_designation1224); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1228); 
            match(input,9,FOLLOW_9_in_object_designation1230); 
             ((SubResult) ((one_result_scope)one_result_stack.peek()).current).addObjectDesignation(Integer.parseInt(id.getText())); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:536:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:537:2: ( 'ME(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:538:2: 'ME(' id= NUMBER ')'
            {
            match(input,44,FOLLOW_44_in_object_outline1248); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1252); 
            match(input,9,FOLLOW_9_in_object_outline1254); 
             ((SubResult) ((one_result_scope)one_result_stack.peek()).current).addObjectOutline(Integer.parseInt(id.getText())); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:542:1: object_creation : ( node | box | arc | attribute );
    public final void object_creation() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:543:2: ( node | box | arc | attribute )
            int alt26=4;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt26=1;
                }
                break;
            case 55:
                {
                alt26=2;
                }
                break;
            case 56:
                {
                alt26=3;
                }
                break;
            case 57:
            case 58:
                {
                alt26=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("542:1: object_creation : ( node | box | arc | attribute );", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:543:4: node
                    {
                    pushFollow(FOLLOW_node_in_object_creation1269);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:544:4: box
                    {
                    pushFollow(FOLLOW_box_in_object_creation1274);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:545:4: arc
                    {
                    pushFollow(FOLLOW_arc_in_object_creation1279);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:4: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_object_creation1284);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:550:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==45) ) {
                alt27=1;
            }
            else if ( (LA27_0==46) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("550:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:4: 'SU(' id= NUMBER ')'
                    {
                    match(input,45,FOLLOW_45_in_object_deletion1297); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1301); 
                    match(input,9,FOLLOW_9_in_object_deletion1303); 


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:552:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,46,FOLLOW_46_in_object_deletion1311); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1315); 
                    match(input,11,FOLLOW_11_in_object_deletion1317); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1321); 
                    match(input,9,FOLLOW_9_in_object_deletion1323); 


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


    // $ANTLR start model_changes
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:555:2: model_changes : ( attribute_table )? ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )? 'XA(' id_object= NUMBER ',' attribute_name= CAMI_STRING ',' attribute_value= CAMI_STRING ')' ;
    public final void model_changes() throws RecognitionException {
        Token id_object=null;
        Token attribute_name=null;
        Token attribute_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:556:3: ( ( attribute_table )? ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )? 'XA(' id_object= NUMBER ',' attribute_name= CAMI_STRING ',' attribute_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:556:5: ( attribute_table )? ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )? 'XA(' id_object= NUMBER ',' attribute_name= CAMI_STRING ',' attribute_value= CAMI_STRING ')'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:556:5: ( attribute_table )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==49) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:556:5: attribute_table
                    {
                    pushFollow(FOLLOW_attribute_table_in_model_changes1340);
                    attribute_table();
                    _fsp--;


                    }
                    break;

            }

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:4: ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==47) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:6: 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,47,FOLLOW_47_in_model_changes1348); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1349); 
                    match(input,11,FOLLOW_11_in_model_changes1351); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1353); 
                    match(input,11,FOLLOW_11_in_model_changes1355); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1357); 
                    match(input,11,FOLLOW_11_in_model_changes1359); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1361); 
                    match(input,11,FOLLOW_11_in_model_changes1363); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1365); 
                    match(input,9,FOLLOW_9_in_model_changes1367); 

                    }
                    break;

            }

            match(input,48,FOLLOW_48_in_model_changes1376); 
            id_object=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1380); 
            match(input,11,FOLLOW_11_in_model_changes1382); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_model_changes1386); 
            match(input,11,FOLLOW_11_in_model_changes1388); 
            attribute_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_model_changes1392); 
            match(input,9,FOLLOW_9_in_model_changes1394); 


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
    // $ANTLR end model_changes


    // $ANTLR start attribute_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:561:2: attribute_table : 'TD(' CAMI_STRING ')' ( 'OB(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )* 'FA()' ;
    public final void attribute_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:562:3: ( 'TD(' CAMI_STRING ')' ( 'OB(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )* 'FA()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:563:3: 'TD(' CAMI_STRING ')' ( 'OB(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )* 'FA()'
            {
            match(input,49,FOLLOW_49_in_attribute_table1414); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1416); 
            match(input,9,FOLLOW_9_in_attribute_table1418); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:564:3: ( 'OB(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )*
            loop30:
            do {
                int alt30=3;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==50) ) {
                    alt30=1;
                }
                else if ( (LA30_0==51) ) {
                    alt30=2;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:565:3: 'OB(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
            	    {
            	    match(input,50,FOLLOW_50_in_attribute_table1426); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1428); 
            	    match(input,11,FOLLOW_11_in_attribute_table1430); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1432); 
            	    match(input,11,FOLLOW_11_in_attribute_table1434); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1436); 
            	    match(input,11,FOLLOW_11_in_attribute_table1438); 
            	    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1440); 
            	    match(input,9,FOLLOW_9_in_attribute_table1442); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:566:5: 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
            	    {
            	    match(input,51,FOLLOW_51_in_attribute_table1448); 
            	    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1450); 
            	    match(input,11,FOLLOW_11_in_attribute_table1452); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1454); 
            	    match(input,11,FOLLOW_11_in_attribute_table1456); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1458); 
            	    match(input,11,FOLLOW_11_in_attribute_table1460); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1462); 
            	    match(input,11,FOLLOW_11_in_attribute_table1464); 
            	    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1466); 
            	    match(input,9,FOLLOW_9_in_attribute_table1468); 

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_attribute_table1476); 

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
    // $ANTLR end attribute_table

    protected static class model_definition_scope {
        IGraph model;
    }
    protected Stack model_definition_stack = new Stack();


    // $ANTLR start model_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:576:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        model_definition_stack.push(new model_definition_scope());
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:578:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:579:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,52,FOLLOW_52_in_model_definition1505); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:580:2: ( syntactic | aestetic )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=54 && LA31_0<=58)) ) {
                alt31=1;
            }
            else if ( ((LA31_0>=59 && LA31_0<=63)) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("580:2: ( syntactic | aestetic )", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:580:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition1510);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:580:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition1514);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,53,FOLLOW_53_in_model_definition1519); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:585:1: syntactic : ( node | box | arc | attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:586:2: ( node | box | arc | attribute )
            int alt32=4;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt32=1;
                }
                break;
            case 55:
                {
                alt32=2;
                }
                break;
            case 56:
                {
                alt32=3;
                }
                break;
            case 57:
            case 58:
                {
                alt32=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("585:1: syntactic : ( node | box | arc | attribute );", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:587:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic1533);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:587:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic1537);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:587:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic1541);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:587:21: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_syntactic1545);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:591:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:592:2: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:593:2: 'CN(' CAMI_STRING ',' NUMBER ')'
            {
            match(input,54,FOLLOW_54_in_node1560); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node1562); 
            match(input,11,FOLLOW_11_in_node1564); 
            match(input,NUMBER,FOLLOW_NUMBER_in_node1566); 
            match(input,9,FOLLOW_9_in_node1568); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:597:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:597:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:598:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,55,FOLLOW_55_in_box1581); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box1583); 
            match(input,11,FOLLOW_11_in_box1585); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1587); 
            match(input,11,FOLLOW_11_in_box1589); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1591); 
            match(input,9,FOLLOW_9_in_box1593); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:602:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:602:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:603:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,56,FOLLOW_56_in_arc1606); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc1608); 
            match(input,11,FOLLOW_11_in_arc1610); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1612); 
            match(input,11,FOLLOW_11_in_arc1614); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1616); 
            match(input,11,FOLLOW_11_in_arc1618); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1620); 
            match(input,9,FOLLOW_9_in_arc1622); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:607:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void attribute() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:608:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==57) ) {
                alt33=1;
            }
            else if ( (LA33_0==58) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("607:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:608:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,57,FOLLOW_57_in_attribute1635); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1637); 
                    match(input,11,FOLLOW_11_in_attribute1639); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1641); 
                    match(input,11,FOLLOW_11_in_attribute1643); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1645); 
                    match(input,9,FOLLOW_9_in_attribute1647); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:609:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,58,FOLLOW_58_in_attribute1652); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1654); 
                    match(input,11,FOLLOW_11_in_attribute1656); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1658); 
                    match(input,11,FOLLOW_11_in_attribute1660); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1662); 
                    match(input,11,FOLLOW_11_in_attribute1664); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1666); 
                    match(input,11,FOLLOW_11_in_attribute1668); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1670); 
                    match(input,9,FOLLOW_9_in_attribute1672); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:613:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:614:2: ( object_position | text_position | intermediary_point )
            int alt34=3;
            switch ( input.LA(1) ) {
            case 59:
            case 60:
            case 61:
                {
                alt34=1;
                }
                break;
            case 62:
                {
                alt34=2;
                }
                break;
            case 63:
                {
                alt34=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("613:1: aestetic : ( object_position | text_position | intermediary_point );", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:615:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic1686);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:615:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic1690);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:615:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic1694);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:619:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:620:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case 59:
                {
                alt35=1;
                }
                break;
            case 60:
                {
                alt35=2;
                }
                break;
            case 61:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("619:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:620:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,59,FOLLOW_59_in_object_position1707); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1711); 
                    match(input,11,FOLLOW_11_in_object_position1713); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1717); 
                    match(input,11,FOLLOW_11_in_object_position1719); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1723); 
                    match(input,9,FOLLOW_9_in_object_position1725); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:621:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,60,FOLLOW_60_in_object_position1730); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1734); 
                    match(input,11,FOLLOW_11_in_object_position1736); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1740); 
                    match(input,11,FOLLOW_11_in_object_position1742); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1746); 
                    match(input,9,FOLLOW_9_in_object_position1748); 

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:622:4: 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,61,FOLLOW_61_in_object_position1753); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1757); 
                    match(input,11,FOLLOW_11_in_object_position1759); 
                    left=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1763); 
                    match(input,11,FOLLOW_11_in_object_position1765); 
                    right=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1769); 
                    match(input,11,FOLLOW_11_in_object_position1771); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1775); 
                    match(input,11,FOLLOW_11_in_object_position1777); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1781); 
                    match(input,9,FOLLOW_9_in_object_position1782); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:626:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:627:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:628:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,62,FOLLOW_62_in_text_position1797); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1801); 
            match(input,11,FOLLOW_11_in_text_position1803); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position1807); 
            match(input,11,FOLLOW_11_in_text_position1809); 
            h_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1813); 
            match(input,11,FOLLOW_11_in_text_position1815); 
            v_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1819); 
            match(input,9,FOLLOW_9_in_text_position1821); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:632:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:633:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:634:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,63,FOLLOW_63_in_intermediary_point1836); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1838); 
            match(input,11,FOLLOW_11_in_intermediary_point1840); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1842); 
            match(input,11,FOLLOW_11_in_intermediary_point1844); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1846); 
            match(input,9,FOLLOW_9_in_intermediary_point1848); 

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


    // $ANTLR start dialog_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:642:1: dialog_definition returns [IDialog dialog] : 'DC()' dialog_creation ( dialog_next[dialog] )* 'FF()' ;
    public final IDialog dialog_definition() throws RecognitionException {
        IDialog dialog = null;

        IDialog dialog_creation9 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:644:2: ( 'DC()' dialog_creation ( dialog_next[dialog] )* 'FF()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:645:2: 'DC()' dialog_creation ( dialog_next[dialog] )* 'FF()'
            {
            match(input,64,FOLLOW_64_in_dialog_definition1875); 
             LOGGER.finest("Reception d'une definition d'une boite de dialogue"); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition1880);
            dialog_creation9=dialog_creation();
            _fsp--;

             dialog = dialog_creation9; 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:647:2: ( dialog_next[dialog] )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==67) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:647:2: dialog_next[dialog]
            	    {
            	    pushFollow(FOLLOW_dialog_next_in_dialog_definition1885);
            	    dialog_next(dialog);
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            match(input,65,FOLLOW_65_in_dialog_definition1890); 
             LOGGER.finest("Fin de reception des boites de dialogue"); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return dialog;
    }
    // $ANTLR end dialog_definition


    // $ANTLR start dialog_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:652:1: dialog_creation returns [IDialog dialog] : 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' ;
    public final IDialog dialog_creation() throws RecognitionException {
        IDialog dialog = null;

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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:655:2: ( 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:656:2: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
            {
            match(input,66,FOLLOW_66_in_dialog_creation1917); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1921); 
            match(input,11,FOLLOW_11_in_dialog_creation1923); 
            dialog_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1927); 
            match(input,11,FOLLOW_11_in_dialog_creation1929); 
            buttons_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1933); 
            match(input,11,FOLLOW_11_in_dialog_creation1935); 
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1940); 
            match(input,11,FOLLOW_11_in_dialog_creation1942); 
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1948); 
            match(input,11,FOLLOW_11_in_dialog_creation1950); 
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1954); 
            match(input,11,FOLLOW_11_in_dialog_creation1956); 
            input_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1960); 
            match(input,11,FOLLOW_11_in_dialog_creation1962); 
            line_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1966); 
            match(input,11,FOLLOW_11_in_dialog_creation1968); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:658:15: (default_value= CAMI_STRING )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==CAMI_STRING) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:658:15: default_value= CAMI_STRING
                    {
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1974); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_dialog_creation1977); 

            	
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
            		dialog = CamiObjectBuilder.buildDialog(ce);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return dialog;
    }
    // $ANTLR end dialog_creation


    // $ANTLR start dialog_next
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:676:1: dialog_next[IDialog dialog] : 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' ;
    public final void dialog_next(IDialog dialog) throws RecognitionException {
        Token dialog_id=null;
        Token line=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:677:2: ( 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:678:2: 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')'
            {
            match(input,67,FOLLOW_67_in_dialog_next1994); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_next1998); 
            match(input,11,FOLLOW_11_in_dialog_next2000); 
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_next2004); 
            match(input,9,FOLLOW_9_in_dialog_next2006); 
             ((Dialog) dialog).addLine(line.getText()); 

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
    // $ANTLR end dialog_next


    // $ANTLR start dialog_display
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:682:1: dialog_display : 'AD(' dialog_id= NUMBER ')' ;
    public final void dialog_display() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:683:2: ( 'AD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:684:2: 'AD(' dialog_id= NUMBER ')'
            {
            match(input,68,FOLLOW_68_in_dialog_display2022); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_display2026); 
            match(input,9,FOLLOW_9_in_dialog_display2028); 

            		// Demande l'affichage au core
            		((ReceptDialogObservable) hash.get("IReceptDialog")).notifyObservers(dialogs.get(Integer.parseInt(dialog_id.getText())));
            		// Permet de stocker la boite de dialogue dans l'API pour pouvoir y répondre
            		sessionController.notifyReceptDialog(dialogs.get(Integer.parseInt(dialog_id.getText())));
            	

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
    // $ANTLR end dialog_display


    // $ANTLR start dialog_hide
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:693:1: dialog_hide : 'HD(' dialog_id= NUMBER ')' ;
    public final void dialog_hide() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:694:2: ( 'HD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:695:2: 'HD(' dialog_id= NUMBER ')'
            {
            match(input,69,FOLLOW_69_in_dialog_hide2044); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_hide2048); 
            match(input,9,FOLLOW_9_in_dialog_hide2050); 

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
    // $ANTLR end dialog_hide


    // $ANTLR start dialog_destroy
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:699:1: dialog_destroy : 'DG(' dialog_id= NUMBER ')' ;
    public final void dialog_destroy() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:700:2: ( 'DG(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:701:2: 'DG(' dialog_id= NUMBER ')'
            {
            match(input,70,FOLLOW_70_in_dialog_destroy2065); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_destroy2069); 
            match(input,9,FOLLOW_9_in_dialog_destroy2071); 
             LOGGER.finest("Destruction de la boite de dialogue " + dialog_id.getText()); 

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
    // $ANTLR end dialog_destroy


    // $ANTLR start interactive_response
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:705:1: interactive_response : 'MI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:706:2: ( 'MI(' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:707:2: 'MI(' NUMBER ',' NUMBER ')'
            {
            match(input,71,FOLLOW_71_in_interactive_response2086); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response2088); 
            match(input,11,FOLLOW_11_in_interactive_response2090); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response2092); 
            match(input,9,FOLLOW_9_in_interactive_response2094); 

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


    protected DFA1 dfa1 = new DFA1(this);
    static final String DFA1_eotS =
        "\40\uffff";
    static final String DFA1_eofS =
        "\23\uffff\2\27\5\uffff\1\27\5\uffff";
    static final String DFA1_minS =
        "\1\10\2\uffff\2\4\1\5\4\uffff\1\4\4\uffff\2\11\2\13\2\15\2\4\1\uffff"+
        "\1\11\1\13\1\15\1\5\1\13\1\4\1\11\1\34";
    static final String DFA1_maxS =
        "\1\43\2\uffff\2\4\1\5\4\uffff\1\4\4\uffff\2\11\2\13\2\41\2\4\1\uffff"+
        "\1\11\1\13\1\41\1\5\1\13\2\11\1\42";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\2\3\uffff\1\3\1\4\1\5\1\6\1\uffff\1\7\1\11\1\12\1"+
        "\13\10\uffff\1\10\10\uffff";
    static final String DFA1_specialS =
        "\40\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1\3\uffff\1\2\1\6\5\uffff\1\10\1\11\1\7\6\uffff\1\12\1\13"+
            "\1\16\1\3\1\4\1\5\1\14\1\15",
            "",
            "",
            "\1\17",
            "\1\20",
            "\1\21",
            "",
            "",
            "",
            "",
            "\1\22",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\6\21\uffff\3\6",
            "\1\6\21\uffff\3\6",
            "\1\30",
            "\1\31",
            "",
            "\1\32",
            "\1\33",
            "\1\6\21\uffff\3\6",
            "\1\34",
            "\1\35",
            "\1\36\4\uffff\1\37",
            "\1\37",
            "\1\12\1\13\4\uffff\1\14"
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "83:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | ko_message );";
        }
    }
 

    public static final BitSet FOLLOW_open_communication_in_main58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_main63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_session_in_main71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_session_in_main76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_suspend_session_in_main81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resume_session_in_main86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invalid_model_in_main94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_main102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_model_in_main110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_results_in_main115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ko_message_in_main123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_open_communication149 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_communication152 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_communication154 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_open_connection_in_open_communication159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_open_connection175 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection179 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_open_connection181 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection185 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_connection187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_open_session237 = new BitSet(new long[]{0x0000000380002000L});
    public static final BitSet FOLLOW_13_in_open_session241 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_session245 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_session247 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_open_session250 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_open_session253 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_interlocutor_table_in_open_session256 = new BitSet(new long[]{0x0000000380800000L});
    public static final BitSet FOLLOW_message_to_user_in_open_session261 = new BitSet(new long[]{0x0000000380800000L});
    public static final BitSet FOLLOW_receive_services_in_open_session265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table280 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table283 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_interlocutor_table287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_body_table302 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table306 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table308 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table312 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table314 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table318 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table320 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table324 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_body_table326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_suspend_session343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_resume_session359 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_resume_session363 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_resume_session365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_close_session382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_group_in_receive_services418 = new BitSet(new long[]{0x0000000010800000L});
    public static final BitSet FOLLOW_state_service_in_receive_services428 = new BitSet(new long[]{0x0000000010400000L});
    public static final BitSet FOLLOW_22_in_receive_services436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_receive_services_group462 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_root_description_in_receive_services_group465 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_service_description_in_receive_services_group473 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_24_in_receive_services_group481 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_receive_services_group488 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_services_group492 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receive_services_group494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_root_description516 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_root_description520 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_root_description522 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description526 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_root_description528 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description532 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_root_description533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_service_description560 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description564 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description566 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description570 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description572 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description577 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description580 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description584 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description587 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description592 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description595 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description599 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description602 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description607 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description610 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description614 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description617 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_service_description621 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_service_description624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_state_service651 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service655 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service657 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service661 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service663 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_state_service667 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service669 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service673 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_state_service676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_invalid_model698 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_29_in_invalid_model706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ko_message727 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ko_message729 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ko_message731 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ko_message735 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ko_message737 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ko_message741 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ko_message743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message782 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message786 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message804 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message808 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message826 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message830 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message832 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message836 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_ask_for_model859 = new BitSet(new long[]{0x0000000410000000L});
    public static final BitSet FOLLOW_34_in_ask_for_model863 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_model865 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_model867 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_model869 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_model871 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_model873 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ask_for_model875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_receive_results898 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_receive_results901 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results905 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_receive_results907 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results911 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_receive_results913 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_receive_results917 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receive_results919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_results936 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_state_service_in_results941 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_dialog_definition_in_results948 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_dialog_display_in_results955 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_dialog_destroy_in_results960 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_one_result_in_results965 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_model_changes_in_results972 = new BitSet(new long[]{0x0003806390000002L,0x0000000000000051L});
    public static final BitSet FOLLOW_37_in_results982 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_results984 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_results986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_one_result1016 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_one_result1020 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_one_result1022 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_one_result1026 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_one_result1028 = new BitSet(new long[]{0x07C07F4000000000L});
    public static final BitSet FOLLOW_result_body_in_one_result1035 = new BitSet(new long[]{0x07C07FC000000000L});
    public static final BitSet FOLLOW_39_in_one_result1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_one_result_in_result_body1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_textual_result1124 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1128 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result1130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_attribute_change1147 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change1151 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1153 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1157 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1159 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1163 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_change1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_attribute_outline1182 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1186 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1188 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1192 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1194 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1198 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1201 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1205 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_object_designation1224 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1228 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_object_outline1248 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1252 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_object_creation1269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_object_creation1274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_object_creation1279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_object_creation1284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_object_deletion1297 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1301 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_object_deletion1311 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1315 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1317 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1321 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_table_in_model_changes1340 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_47_in_model_changes1348 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1349 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1351 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1353 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1355 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1357 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1359 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1361 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1363 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1365 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_changes1367 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_model_changes1376 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1380 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1382 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_model_changes1386 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1388 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_model_changes1392 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_changes1394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_attribute_table1414 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1416 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_table1418 = new BitSet(new long[]{0x000C000000008000L});
    public static final BitSet FOLLOW_50_in_attribute_table1426 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1428 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1430 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1432 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1434 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1436 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1438 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1440 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_table1442 = new BitSet(new long[]{0x000C000000008000L});
    public static final BitSet FOLLOW_51_in_attribute_table1448 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1450 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1452 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1456 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1458 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1460 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1464 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1466 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_table1468 = new BitSet(new long[]{0x000C000000008000L});
    public static final BitSet FOLLOW_15_in_attribute_table1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_model_definition1505 = new BitSet(new long[]{0xFFC0000000000000L});
    public static final BitSet FOLLOW_syntactic_in_model_definition1510 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_aestetic_in_model_definition1514 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_model_definition1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic1533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_syntactic1545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_node1560 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node1562 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node1564 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node1566 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_node1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_box1581 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box1583 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1585 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1587 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1589 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1591 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_box1593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_arc1606 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc1608 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1610 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1612 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1614 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1616 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1618 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1620 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_arc1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_attribute1635 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1637 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1639 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1641 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1643 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1645 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_attribute1652 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1654 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1656 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1658 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1660 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1662 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1664 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1666 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1668 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1670 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic1690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic1694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_object_position1707 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1711 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1713 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1717 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1719 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1723 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_object_position1730 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1734 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1736 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1740 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1742 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1746 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_object_position1753 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1757 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1759 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1763 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1765 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1769 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1771 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1775 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1777 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1781 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_text_position1797 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1801 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1803 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position1807 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1809 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1813 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1815 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1819 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_text_position1821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_intermediary_point1836 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1838 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point1840 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1842 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point1844 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1846 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_intermediary_point1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_dialog_definition1875 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition1880 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_dialog_next_in_dialog_definition1885 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_65_in_dialog_definition1890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_dialog_creation1917 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1921 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1923 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1927 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1929 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1933 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1935 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1940 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1942 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1948 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1950 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1954 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1956 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1960 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1962 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1966 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1968 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1974 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_creation1977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_dialog_next1994 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_next1998 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_next2000 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_next2004 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_next2006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_dialog_display2022 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_display2026 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_display2028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_dialog_hide2044 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_hide2048 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_hide2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_dialog_destroy2065 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_destroy2069 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_destroy2071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_interactive_response2086 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response2088 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response2090 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response2092 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interactive_response2094 = new BitSet(new long[]{0x0000000000000002L});

}