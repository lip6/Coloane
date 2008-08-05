// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-08-05 14:01:15

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'FL()'", "'VI('", "'SS()'", "'RS('", "'FS()'", "'QQ(3)'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'QQ(2)'", "'KO('", "'TR('", "'WN('", "'MO('", "'DF(-'", "'DR()'", "'RQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'MT('", "'RO('", "'ME('", "'SU('", "'SI('", "'DB()'", "'FB()'", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'FR('", "'MI('"
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:69:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | dialog_definition | dialog_destroy | ko_message );
    public final void main() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:2: ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | dialog_definition | dialog_destroy | ko_message )
            int alt1=13;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:4: open_communication
                    {
                    pushFollow(FOLLOW_open_communication_in_main53);
                    open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:72:4: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_main58);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:74:4: open_session
                    {
                    pushFollow(FOLLOW_open_session_in_main66);
                    open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:75:4: close_session
                    {
                    pushFollow(FOLLOW_close_session_in_main71);
                    close_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:76:4: suspend_session
                    {
                    pushFollow(FOLLOW_suspend_session_in_main76);
                    suspend_session();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:77:4: resume_session
                    {
                    pushFollow(FOLLOW_resume_session_in_main81);
                    resume_session();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:79:4: invalid_model
                    {
                    pushFollow(FOLLOW_invalid_model_in_main89);
                    invalid_model();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:81:4: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_main97);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:83:4: ask_for_model
                    {
                    pushFollow(FOLLOW_ask_for_model_in_main105);
                    ask_for_model();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:84:4: receive_results
                    {
                    pushFollow(FOLLOW_receive_results_in_main110);
                    receive_results();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:85:4: dialog_definition
                    {
                    pushFollow(FOLLOW_dialog_definition_in_main115);
                    dialog_definition();
                    _fsp--;


                    }
                    break;
                case 12 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:86:4: dialog_destroy
                    {
                    pushFollow(FOLLOW_dialog_destroy_in_main120);
                    dialog_destroy();
                    _fsp--;


                    }
                    break;
                case 13 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:88:4: ko_message
                    {
                    pushFollow(FOLLOW_ko_message_in_main128);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:96:1: open_communication : 'SC(' fkName= CAMI_STRING ')' open_connection ;
    public final void open_communication() throws RecognitionException {
        open_communication_stack.push(new open_communication_scope());
        Token fkName=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:98:2: ( 'SC(' fkName= CAMI_STRING ')' open_connection )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:99:2: 'SC(' fkName= CAMI_STRING ')' open_connection
            {
            match(input,8,FOLLOW_8_in_open_communication154); 
            fkName=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_communication157); 
            match(input,9,FOLLOW_9_in_open_communication159); 

            		LOGGER.finest("Creation de l'objet de connexion");
            		((open_communication_scope)open_communication_stack.peek()).connectionInfo = new ConnectionInfo(fkName.getText());
            	
            pushFollow(FOLLOW_open_connection_in_open_communication164);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:107:1: open_connection : 'OC(' major= NUMBER ',' minor= NUMBER ')' ;
    public final void open_connection() throws RecognitionException {
        Token major=null;
        Token minor=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:108:2: ( 'OC(' major= NUMBER ',' minor= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:109:2: 'OC(' major= NUMBER ',' minor= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_open_connection180); 
            major=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection184); 
            match(input,11,FOLLOW_11_in_open_connection186); 
            minor=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection190); 
            match(input,9,FOLLOW_9_in_open_connection192); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:117:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:118:2: ( 'FC()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:119:2: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection208); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:129:1: open_session : 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ( message_to_user )* receive_services ;
    public final void open_session() throws RecognitionException {
        open_session_stack.push(new open_session_scope());
        Token session_name=null;

         ((open_session_scope)open_session_stack.peek()).sessionArgs = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:132:2: ( 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ( message_to_user )* receive_services )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:133:2: 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ( message_to_user )* receive_services
            {
            match(input,13,FOLLOW_13_in_open_session242); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_session246); 
            match(input,9,FOLLOW_9_in_open_session248); 
            match(input,14,FOLLOW_14_in_open_session251); 
            match(input,15,FOLLOW_15_in_open_session254); 
            pushFollow(FOLLOW_interlocutor_table_in_open_session257);
            interlocutor_table();
            _fsp--;


            		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo(((open_session_scope)open_session_stack.peek()).sessionArgs);
            		sessionController.notifyReceptSessionInfo(sessionInfo);
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:140:2: ( message_to_user )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=31 && LA2_0<=33)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:140:2: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_open_session262);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            pushFollow(FOLLOW_receive_services_in_open_session266);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:145:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:146:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:147:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,16,FOLLOW_16_in_interlocutor_table281); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:148:2: ( body_table )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==18) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:148:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table284);
            	    body_table();
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

            match(input,17,FOLLOW_17_in_interlocutor_table288); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:153:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token deprecated=null;
        Token new_model=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:155:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')'
            {
            match(input,18,FOLLOW_18_in_body_table303); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table307); 
            match(input,11,FOLLOW_11_in_body_table309); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table313); 
            match(input,11,FOLLOW_11_in_body_table315); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table319); 
            match(input,11,FOLLOW_11_in_body_table321); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table325); 
            match(input,9,FOLLOW_9_in_body_table327); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:164:1: suspend_session : 'SS()' ;
    public final void suspend_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:165:2: ( 'SS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:166:2: 'SS()'
            {
            match(input,19,FOLLOW_19_in_suspend_session344); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:1: resume_session : 'RS(' session_name= CAMI_STRING ')' ;
    public final void resume_session() throws RecognitionException {
        Token session_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:174:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:175:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,20,FOLLOW_20_in_resume_session360); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_resume_session364); 
            match(input,9,FOLLOW_9_in_resume_session366); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:182:1: close_session : 'FS()' ;
    public final void close_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:183:2: ( 'FS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:184:2: 'FS()'
            {
            match(input,21,FOLLOW_21_in_close_session383); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:195:1: receive_services : ( receive_services_group )+ ( state_service )+ 'QQ(3)' ;
    public final void receive_services() throws RecognitionException {
        receive_services_stack.push(new receive_services_scope());
        ISubMenu receive_services_group1 = null;

        IUpdateMenu state_service2 = null;


         
        		((receive_services_scope)receive_services_stack.peek()).services = new ArrayList<IService>();
        		List<ISubMenu> roots = new ArrayList<ISubMenu>();
        		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>();
        		LOGGER.finest("Reception d'une liste de services");
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:203:2: ( ( receive_services_group )+ ( state_service )+ 'QQ(3)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:204:2: ( receive_services_group )+ ( state_service )+ 'QQ(3)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:204:2: ( receive_services_group )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==23) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:204:4: receive_services_group
            	    {
            	    pushFollow(FOLLOW_receive_services_group_in_receive_services419);
            	    receive_services_group1=receive_services_group();
            	    _fsp--;

            	     roots.add(receive_services_group1); 

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

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:205:2: ( state_service )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==28) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:205:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_services429);
            	    state_service2=state_service();
            	    _fsp--;

            	     updates.add(state_service2); 

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

            match(input,22,FOLLOW_22_in_receive_services437); 

            		LOGGER.finest("Fin de la transmission des services (status=3)");
            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(roots, updates, ((receive_services_scope)receive_services_stack.peek()).services);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:213:1: receive_services_group returns [ISubMenu builtRoot] : 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? ;
    public final ISubMenu receive_services_group() throws RecognitionException {
        ISubMenu builtRoot = null;

        Token root_name=null;
        ISubMenu root_description3 = null;

        IQuestion service_description4 = null;


         LOGGER.finest("Reception d'une liste de services (Groupe)"); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:216:2: ( 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:217:2: 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )?
            {
            match(input,23,FOLLOW_23_in_receive_services_group463); 
            pushFollow(FOLLOW_root_description_in_receive_services_group466);
            root_description3=root_description();
            _fsp--;

             builtRoot = root_description3; 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:219:2: ( service_description )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==27) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:220:2: service_description
            	    {
            	    pushFollow(FOLLOW_service_description_in_receive_services_group475);
            	    service_description4=service_description();
            	    _fsp--;


            	    		// Ajout de la question au menu root existant
            	    		((SubMenu) builtRoot).addQuestion(service_description4);

            	    		// Ajout ˆ la liste des services
            	    		IService service = CamiObjectBuilder.buildService(builtRoot, service_description4);
            	    		((receive_services_scope)receive_services_stack.peek()).services.add(service);
            	    	

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

            match(input,24,FOLLOW_24_in_receive_services_group484); 
             LOGGER.finest("Fin de la reception du groupe de services"); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:2: ( 'VQ(' root_name= CAMI_STRING ')' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==25) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:4: 'VQ(' root_name= CAMI_STRING ')'
                    {
                    match(input,25,FOLLOW_25_in_receive_services_group491); 
                    root_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_services_group495); 
                    match(input,9,FOLLOW_9_in_receive_services_group497); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:1: root_description returns [ISubMenu root] : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final ISubMenu root_description() throws RecognitionException {
        ISubMenu root = null;

        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:236:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:237:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,26,FOLLOW_26_in_root_description519); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_root_description523); 
            match(input,11,FOLLOW_11_in_root_description525); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description529); 
            match(input,11,FOLLOW_11_in_root_description531); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description535); 
            match(input,9,FOLLOW_9_in_root_description536); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:243:1: service_description returns [IQuestion question] : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' ;
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:246:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:247:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')'
            {
            match(input,27,FOLLOW_27_in_service_description563); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description567); 
            match(input,11,FOLLOW_11_in_service_description569); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description573); 
            match(input,11,FOLLOW_11_in_service_description575); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:15: (question_type= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:15: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description580); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description583); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:45: (question_behavior= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:45: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description587); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description590); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:10: (set_item= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:10: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description595); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description598); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:29: (dialog= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:29: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description602); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description605); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:17: (stop_authorized= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:17: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description610); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description613); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:46: (output_formalism= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:46: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description617); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description620); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:76: (active_state= NUMBER )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==NUMBER) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:76: active_state= NUMBER
                    {
                    active_state=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description624); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_service_description627); 

            	
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:270:1: state_service returns [IUpdateMenu builtUpdate] : 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' ;
    public final IUpdateMenu state_service() throws RecognitionException {
        IUpdateMenu builtUpdate = null;

        Token root_name=null;
        Token question_name=null;
        Token state=null;
        Token message=null;

         List<String> tq = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:273:2: ( 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:274:2: 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')'
            {
            match(input,28,FOLLOW_28_in_state_service654); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service658); 
            match(input,11,FOLLOW_11_in_state_service660); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service664); 
            match(input,11,FOLLOW_11_in_state_service666); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_state_service670); 
            match(input,11,FOLLOW_11_in_state_service672); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:274:88: (message= CAMI_STRING )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==CAMI_STRING) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:274:88: message= CAMI_STRING
                    {
                    message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service676); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_state_service679); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:346:1: invalid_model : ( state_service )+ 'QQ(2)' ;
    public final void invalid_model() throws RecognitionException {
        IUpdateMenu state_service5 = null;


         List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:348:2: ( ( state_service )+ 'QQ(2)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:349:2: ( state_service )+ 'QQ(2)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:349:2: ( state_service )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==28) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:349:3: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_invalid_model701);
            	    state_service5=state_service();
            	    _fsp--;

            	     updates.add(state_service5); 

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

            match(input,29,FOLLOW_29_in_invalid_model709); 

            		LOGGER.finest("Fin de la mise a jour (status=2)");
            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates, null); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:361:1: ko_message : 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')' ;
    public final void ko_message() throws RecognitionException {
        Token mess=null;
        Token severity=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:362:2: ( 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:2: 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')'
            {
            match(input,30,FOLLOW_30_in_ko_message730); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ko_message732); 
            match(input,11,FOLLOW_11_in_ko_message734); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ko_message738); 
            match(input,11,FOLLOW_11_in_ko_message740); 
            severity=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ko_message744); 
            match(input,9,FOLLOW_9_in_ko_message746); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:372:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:373:2: ( trace_message | warning_message | special_message )
            int alt17=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt17=1;
                }
                break;
            case 32:
                {
                alt17=2;
                }
                break;
            case 33:
                {
                alt17=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("372:1: message_to_user : ( trace_message | warning_message | special_message );", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:373:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user761);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:374:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user766);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:375:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user771);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:379:1: trace_message : 'TR(' message= CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:2: ( 'TR(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:381:2: 'TR(' message= CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message785); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message789); 
            match(input,9,FOLLOW_9_in_trace_message791); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:389:1: warning_message : 'WN(' message= CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:390:2: ( 'WN(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:391:2: 'WN(' message= CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message807); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message811); 
            match(input,9,FOLLOW_9_in_warning_message813); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:399:1: special_message : 'MO(' type= NUMBER ',' message= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token type=null;
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:400:2: ( 'MO(' type= NUMBER ',' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:401:2: 'MO(' type= NUMBER ',' message= CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message829); 
            type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message833); 
            match(input,11,FOLLOW_11_in_special_message835); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message839); 
            match(input,9,FOLLOW_9_in_special_message841); 

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


    // $ANTLR start ask_for_model
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:432:1: ask_for_model : ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void ask_for_model() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:433:2: ( ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:434:2: ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:434:2: ( state_service )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==28) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:434:2: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_ask_for_model862);
            	    state_service();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            match(input,34,FOLLOW_34_in_ask_for_model866); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_model868); 
            match(input,11,FOLLOW_11_in_ask_for_model870); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_model872); 
            match(input,11,FOLLOW_11_in_ask_for_model874); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_model876); 
            match(input,9,FOLLOW_9_in_ask_for_model878); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:441:1: receive_results : 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | dialog_definition | result )* ;
    public final void receive_results() throws RecognitionException {
        Token root_name=null;
        Token service_name=null;
        Token deprecated=null;
        IUpdateMenu state_service6 = null;

        ISubResult result7 = null;


         List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:443:2: ( 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | dialog_definition | result )* )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:444:2: 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ( state_service | special_message | warning_message | dialog_definition | result )*
            {
            match(input,35,FOLLOW_35_in_receive_results901); 
            match(input,36,FOLLOW_36_in_receive_results904); 
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results908); 
            match(input,11,FOLLOW_11_in_receive_results910); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results914); 
            match(input,11,FOLLOW_11_in_receive_results916); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_receive_results920); 
            match(input,9,FOLLOW_9_in_receive_results922); 

            		IResult result = CamiObjectBuilder.buildResult(root_name.getText(), service_name.getText());
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:2: ( state_service | special_message | warning_message | dialog_definition | result )*
            loop19:
            do {
                int alt19=6;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    alt19=1;
                    }
                    break;
                case 33:
                    {
                    alt19=2;
                    }
                    break;
                case 32:
                    {
                    alt19=3;
                    }
                    break;
                case 58:
                case 64:
                    {
                    alt19=4;
                    }
                    break;
                case 37:
                    {
                    alt19=5;
                    }
                    break;

                }

                switch (alt19) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_results929);
            	    state_service6=state_service();
            	    _fsp--;

            	     updates.add(state_service6); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:4: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_receive_results936);
            	    special_message();
            	    _fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:4: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_receive_results941);
            	    warning_message();
            	    _fsp--;


            	    }
            	    break;
            	case 4 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:4: dialog_definition
            	    {
            	    pushFollow(FOLLOW_dialog_definition_in_receive_results946);
            	    dialog_definition();
            	    _fsp--;


            	    }
            	    break;
            	case 5 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:4: result
            	    {
            	    pushFollow(FOLLOW_result_in_receive_results951);
            	    result7=result();
            	    _fsp--;

            	     ((Result) result).addSubResult(result7); 

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            		LOGGER.finest("Transmission des resultats");
            		((ReceptResultObservable)hash.get("IReceptResult")).notifyObservers(result);
            		LOGGER.finest("Transmission des mises a jour des services");
            		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates, null); 
            		sessionController.notifyEndResult();
            		
            	

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

    protected static class result_scope {
        ISubResult current;
    }
    protected Stack result_stack = new Stack();


    // $ANTLR start result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:464:1: result returns [ISubResult builtResult] : 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final ISubResult result() throws RecognitionException {
        result_stack.push(new result_scope());
        ISubResult builtResult = null;

        Token set_name=null;
        Token set_type=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:467:2: ( 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:468:2: 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,37,FOLLOW_37_in_result983); 
            set_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result987); 
            match(input,11,FOLLOW_11_in_result989); 
            set_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result993); 
            match(input,9,FOLLOW_9_in_result995); 

            		LOGGER.finest("Debut du parcours de l'ensemble de resultats");
            		((result_scope)result_stack.peek()).current = CamiObjectBuilder.buildSubResult(set_name.getText(), set_type.getText());
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:2: ( result_body )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==37||(LA20_0>=39 && LA20_0<=45)||(LA20_0>=48 && LA20_0<=52)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:4: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result1002);
            	    result_body();
            	    _fsp--;



            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            match(input,38,FOLLOW_38_in_result1010); 

            		LOGGER.finest("Fin du parcours de l'ensemble de resultats");
            		builtResult = ((result_scope)result_stack.peek()).current;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            result_stack.pop();
        }
        return builtResult;
    }
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:480:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:481:2: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt21=8;
            switch ( input.LA(1) ) {
            case 37:
                {
                alt21=1;
                }
                break;
            case 39:
                {
                alt21=2;
                }
                break;
            case 40:
                {
                alt21=3;
                }
                break;
            case 42:
                {
                alt21=4;
                }
                break;
            case 43:
                {
                alt21=5;
                }
                break;
            case 41:
                {
                alt21=6;
                }
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                {
                alt21=7;
                }
                break;
            case 44:
            case 45:
                {
                alt21=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("480:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:481:4: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1025);
                    result();
                    _fsp--;



                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:482:4: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1032);
                    textual_result();
                    _fsp--;



                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:483:4: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1039);
                    attribute_change();
                    _fsp--;



                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:484:4: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1046);
                    object_designation();
                    _fsp--;



                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:485:4: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1053);
                    object_outline();
                    _fsp--;



                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:486:4: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1060);
                    attribute_outline();
                    _fsp--;



                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:487:4: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1067);
                    object_creation();
                    _fsp--;



                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:488:4: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1074);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:492:1: textual_result : 'RT(' text= CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        Token text=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:493:2: ( 'RT(' text= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:494:2: 'RT(' text= CAMI_STRING ')'
            {
            match(input,39,FOLLOW_39_in_textual_result1091); 
            text=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1095); 
            match(input,9,FOLLOW_9_in_textual_result1097); 
             ((SubResult) ((result_scope)result_stack.peek()).current).addTextualResult(text.getText()); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:498:1: attribute_change : 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token new_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:2: ( 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:500:2: 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,40,FOLLOW_40_in_attribute_change1114); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change1118); 
            match(input,11,FOLLOW_11_in_attribute_change1120); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1124); 
            match(input,11,FOLLOW_11_in_attribute_change1126); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1130); 
            match(input,9,FOLLOW_9_in_attribute_change1132); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:504:1: attribute_outline : 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:505:2: ( 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:2: 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,41,FOLLOW_41_in_attribute_outline1149); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1153); 
            match(input,11,FOLLOW_11_in_attribute_outline1155); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1159); 
            match(input,11,FOLLOW_11_in_attribute_outline1161); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:58: (begin= NUMBER )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==NUMBER) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:58: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1165); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1168); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:74: (end= NUMBER )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NUMBER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:74: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1172); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1175); 

            		((SubResult) ((result_scope)result_stack.peek()).current).addAttributeOutline(Integer.parseInt(id.getText()), attribute_name.getText());
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:512:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:513:2: ( 'RO(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:514:2: 'RO(' id= NUMBER ')'
            {
            match(input,42,FOLLOW_42_in_object_designation1191); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1195); 
            match(input,9,FOLLOW_9_in_object_designation1197); 
             ((SubResult) ((result_scope)result_stack.peek()).current).addObjectDesignation(Integer.parseInt(id.getText())); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:518:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:519:2: ( 'ME(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:520:2: 'ME(' id= NUMBER ')'
            {
            match(input,43,FOLLOW_43_in_object_outline1215); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1219); 
            match(input,9,FOLLOW_9_in_object_outline1221); 
             ((SubResult) ((result_scope)result_stack.peek()).current).addObjectOutline(Integer.parseInt(id.getText())); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:1: object_creation : ( node | box | arc | attribute );
    public final void object_creation() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:525:2: ( node | box | arc | attribute )
            int alt24=4;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt24=1;
                }
                break;
            case 49:
                {
                alt24=2;
                }
                break;
            case 50:
                {
                alt24=3;
                }
                break;
            case 51:
            case 52:
                {
                alt24=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("524:1: object_creation : ( node | box | arc | attribute );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:525:4: node
                    {
                    pushFollow(FOLLOW_node_in_object_creation1236);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:526:4: box
                    {
                    pushFollow(FOLLOW_box_in_object_creation1241);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:527:4: arc
                    {
                    pushFollow(FOLLOW_arc_in_object_creation1246);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:528:4: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_object_creation1251);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:532:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==44) ) {
                alt25=1;
            }
            else if ( (LA25_0==45) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("532:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:4: 'SU(' id= NUMBER ')'
                    {
                    match(input,44,FOLLOW_44_in_object_deletion1264); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1268); 
                    match(input,9,FOLLOW_9_in_object_deletion1270); 


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:534:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,45,FOLLOW_45_in_object_deletion1278); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1282); 
                    match(input,11,FOLLOW_11_in_object_deletion1284); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1288); 
                    match(input,9,FOLLOW_9_in_object_deletion1290); 


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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:542:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        model_definition_stack.push(new model_definition_scope());
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:544:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:545:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,46,FOLLOW_46_in_model_definition1322); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:2: ( syntactic | aestetic )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=48 && LA26_0<=52)) ) {
                alt26=1;
            }
            else if ( ((LA26_0>=53 && LA26_0<=57)) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("546:2: ( syntactic | aestetic )", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition1327);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition1331);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,47,FOLLOW_47_in_model_definition1336); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:1: syntactic : ( node | box | arc | attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:552:2: ( node | box | arc | attribute )
            int alt27=4;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt27=1;
                }
                break;
            case 49:
                {
                alt27=2;
                }
                break;
            case 50:
                {
                alt27=3;
                }
                break;
            case 51:
            case 52:
                {
                alt27=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("551:1: syntactic : ( node | box | arc | attribute );", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:553:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic1350);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:553:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic1354);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:553:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic1358);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:553:21: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_syntactic1362);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:558:2: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:559:2: 'CN(' CAMI_STRING ',' NUMBER ')'
            {
            match(input,48,FOLLOW_48_in_node1377); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node1379); 
            match(input,11,FOLLOW_11_in_node1381); 
            match(input,NUMBER,FOLLOW_NUMBER_in_node1383); 
            match(input,9,FOLLOW_9_in_node1385); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:563:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:563:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:564:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,49,FOLLOW_49_in_box1398); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box1400); 
            match(input,11,FOLLOW_11_in_box1402); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1404); 
            match(input,11,FOLLOW_11_in_box1406); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1408); 
            match(input,9,FOLLOW_9_in_box1410); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:568:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:568:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:569:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,50,FOLLOW_50_in_arc1423); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc1425); 
            match(input,11,FOLLOW_11_in_arc1427); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1429); 
            match(input,11,FOLLOW_11_in_arc1431); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1433); 
            match(input,11,FOLLOW_11_in_arc1435); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1437); 
            match(input,9,FOLLOW_9_in_arc1439); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void attribute() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:574:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==51) ) {
                alt28=1;
            }
            else if ( (LA28_0==52) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("573:1: attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:574:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,51,FOLLOW_51_in_attribute1452); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1454); 
                    match(input,11,FOLLOW_11_in_attribute1456); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1458); 
                    match(input,11,FOLLOW_11_in_attribute1460); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1462); 
                    match(input,9,FOLLOW_9_in_attribute1464); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:575:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,52,FOLLOW_52_in_attribute1469); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1471); 
                    match(input,11,FOLLOW_11_in_attribute1473); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1475); 
                    match(input,11,FOLLOW_11_in_attribute1477); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1479); 
                    match(input,11,FOLLOW_11_in_attribute1481); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1483); 
                    match(input,11,FOLLOW_11_in_attribute1485); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1487); 
                    match(input,9,FOLLOW_9_in_attribute1489); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:579:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:580:2: ( object_position | text_position | intermediary_point )
            int alt29=3;
            switch ( input.LA(1) ) {
            case 53:
            case 54:
            case 55:
                {
                alt29=1;
                }
                break;
            case 56:
                {
                alt29=2;
                }
                break;
            case 57:
                {
                alt29=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("579:1: aestetic : ( object_position | text_position | intermediary_point );", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:581:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic1503);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:581:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic1507);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:581:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic1511);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:585:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:586:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt30=3;
            switch ( input.LA(1) ) {
            case 53:
                {
                alt30=1;
                }
                break;
            case 54:
                {
                alt30=2;
                }
                break;
            case 55:
                {
                alt30=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("585:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:586:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,53,FOLLOW_53_in_object_position1524); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1528); 
                    match(input,11,FOLLOW_11_in_object_position1530); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1534); 
                    match(input,11,FOLLOW_11_in_object_position1536); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1540); 
                    match(input,9,FOLLOW_9_in_object_position1542); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:587:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,54,FOLLOW_54_in_object_position1547); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1551); 
                    match(input,11,FOLLOW_11_in_object_position1553); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1557); 
                    match(input,11,FOLLOW_11_in_object_position1559); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1563); 
                    match(input,9,FOLLOW_9_in_object_position1565); 

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:588:4: 'PO(-1,' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,55,FOLLOW_55_in_object_position1570); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1574); 
                    match(input,11,FOLLOW_11_in_object_position1576); 
                    left=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1580); 
                    match(input,11,FOLLOW_11_in_object_position1582); 
                    right=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1586); 
                    match(input,11,FOLLOW_11_in_object_position1588); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1592); 
                    match(input,11,FOLLOW_11_in_object_position1594); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1598); 
                    match(input,9,FOLLOW_9_in_object_position1599); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:592:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:593:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:594:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,56,FOLLOW_56_in_text_position1614); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1618); 
            match(input,11,FOLLOW_11_in_text_position1620); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position1624); 
            match(input,11,FOLLOW_11_in_text_position1626); 
            h_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1630); 
            match(input,11,FOLLOW_11_in_text_position1632); 
            v_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1636); 
            match(input,9,FOLLOW_9_in_text_position1638); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:598:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:599:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:600:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,57,FOLLOW_57_in_intermediary_point1653); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1655); 
            match(input,11,FOLLOW_11_in_intermediary_point1657); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1659); 
            match(input,11,FOLLOW_11_in_intermediary_point1661); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point1663); 
            match(input,9,FOLLOW_9_in_intermediary_point1665); 

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
        Map<Integer, IDialog> dialogs;
    }
    protected Stack dialog_definition_stack = new Stack();


    // $ANTLR start dialog_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:608:1: dialog_definition : ( dialog_destroy )? 'DC()' dialog_creation ( next_dialog )* 'FF()' ( dialog_display )? ;
    public final void dialog_definition() throws RecognitionException {
        dialog_definition_stack.push(new dialog_definition_scope());
         ((dialog_definition_scope)dialog_definition_stack.peek()).dialogs = new HashMap<Integer, IDialog>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:611:2: ( ( dialog_destroy )? 'DC()' dialog_creation ( next_dialog )* 'FF()' ( dialog_display )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:612:2: ( dialog_destroy )? 'DC()' dialog_creation ( next_dialog )* 'FF()' ( dialog_display )?
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:612:2: ( dialog_destroy )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==64) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:612:2: dialog_destroy
                    {
                    pushFollow(FOLLOW_dialog_destroy_in_dialog_definition1698);
                    dialog_destroy();
                    _fsp--;


                    }
                    break;

            }

            match(input,58,FOLLOW_58_in_dialog_definition1702); 
             LOGGER.finest("Reception d'une definition d'une boite de dialogue"); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition1707);
            dialog_creation();
            _fsp--;

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:615:2: ( next_dialog )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==61) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:615:4: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition1712);
            	    next_dialog();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            match(input,59,FOLLOW_59_in_dialog_definition1718); 
             LOGGER.finest("Fin de reception des boites de dialogue"); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:617:2: ( dialog_display )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==62) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:617:2: dialog_display
                    {
                    pushFollow(FOLLOW_dialog_display_in_dialog_definition1723);
                    dialog_display();
                    _fsp--;


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
            dialog_definition_stack.pop();
        }
        return ;
    }
    // $ANTLR end dialog_definition


    // $ANTLR start dialog_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:621:1: dialog_creation : 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' ;
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:623:2: ( 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:624:2: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
            {
            match(input,60,FOLLOW_60_in_dialog_creation1744); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1748); 
            match(input,11,FOLLOW_11_in_dialog_creation1750); 
            dialog_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1754); 
            match(input,11,FOLLOW_11_in_dialog_creation1756); 
            buttons_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1762); 
            match(input,11,FOLLOW_11_in_dialog_creation1764); 
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1769); 
            match(input,11,FOLLOW_11_in_dialog_creation1771); 
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1777); 
            match(input,11,FOLLOW_11_in_dialog_creation1779); 
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1783); 
            match(input,11,FOLLOW_11_in_dialog_creation1785); 
            input_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1791); 
            match(input,11,FOLLOW_11_in_dialog_creation1793); 
            line_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation1797); 
            match(input,11,FOLLOW_11_in_dialog_creation1799); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:628:15: (default_value= CAMI_STRING )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==CAMI_STRING) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:628:15: default_value= CAMI_STRING
                    {
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1805); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_dialog_creation1808); 

            	
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
            		((dialog_definition_scope)dialog_definition_stack.peek()).dialogs.put(Integer.parseInt(dialog_id.getText()), dialog);
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:648:1: next_dialog : 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        Token dialog_id=null;
        Token line=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:649:2: ( 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:650:2: 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')'
            {
            match(input,61,FOLLOW_61_in_next_dialog1824); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_next_dialog1828); 
            match(input,11,FOLLOW_11_in_next_dialog1830); 
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog1834); 
            match(input,9,FOLLOW_9_in_next_dialog1836); 

            		LOGGER.finest("Ajout d'une ligne a la boite de dialogue : " + dialog_id.getText());
            		((Dialog) ((dialog_definition_scope)dialog_definition_stack.peek()).dialogs.get(Integer.parseInt(dialog_id.getText()))).addLine(line.getText());
            	

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


    // $ANTLR start dialog_display
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:657:1: dialog_display : 'AD(' dialog_id= NUMBER ')' ;
    public final void dialog_display() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:658:2: ( 'AD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:659:2: 'AD(' dialog_id= NUMBER ')'
            {
            match(input,62,FOLLOW_62_in_dialog_display1852); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_display1856); 
            match(input,9,FOLLOW_9_in_dialog_display1858); 

            		LOGGER.finest("Affichage de la boite de dialogue " + dialog_id.getText());
            		((ReceptDialogObservable) hash.get("IReceptDialog")).notifyObservers(((dialog_definition_scope)dialog_definition_stack.peek()).dialogs.get(Integer.parseInt(dialog_id.getText())));  
            	

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


    // $ANTLR start hide_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:666:1: hide_dialog : 'HD(' dialog_id= NUMBER ')' ;
    public final void hide_dialog() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:667:2: ( 'HD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:668:2: 'HD(' dialog_id= NUMBER ')'
            {
            match(input,63,FOLLOW_63_in_hide_dialog1874); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_hide_dialog1878); 
            match(input,9,FOLLOW_9_in_hide_dialog1880); 


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


    // $ANTLR start dialog_destroy
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:672:1: dialog_destroy : 'DG(' dialog_id= NUMBER ')' ( state_service | message_to_user )* 'FR(' NUMBER ')' ;
    public final void dialog_destroy() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:673:2: ( 'DG(' dialog_id= NUMBER ')' ( state_service | message_to_user )* 'FR(' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:674:2: 'DG(' dialog_id= NUMBER ')' ( state_service | message_to_user )* 'FR(' NUMBER ')'
            {
            match(input,64,FOLLOW_64_in_dialog_destroy1897); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_destroy1901); 
            match(input,9,FOLLOW_9_in_dialog_destroy1903); 
             
            		LOGGER.finest("Destruction de la boite de dialogue " + dialog_id.getText());
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:677:2: ( state_service | message_to_user )*
            loop35:
            do {
                int alt35=3;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==28) ) {
                    alt35=1;
                }
                else if ( ((LA35_0>=31 && LA35_0<=33)) ) {
                    alt35=2;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:677:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_dialog_destroy1910);
            	    state_service();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:677:20: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_dialog_destroy1914);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            match(input,65,FOLLOW_65_in_dialog_destroy1920); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_destroy1922); 
            match(input,9,FOLLOW_9_in_dialog_destroy1924); 
             LOGGER.finest("Fin des echanges"); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:682:1: interactive_response : 'MI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:683:2: ( 'MI(' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:684:2: 'MI(' NUMBER ',' NUMBER ')'
            {
            match(input,66,FOLLOW_66_in_interactive_response1939); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response1941); 
            match(input,11,FOLLOW_11_in_interactive_response1943); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response1945); 
            match(input,9,FOLLOW_9_in_interactive_response1947); 

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
        "\61\uffff";
    static final String DFA1_eofS =
        "\43\uffff\1\47\15\uffff";
    static final String DFA1_minS =
        "\1\10\6\uffff\1\4\3\uffff\1\5\2\uffff\1\13\1\11\1\4\1\34\1\13\3"+
        "\4\3\5\1\13\2\11\1\13\1\11\1\13\1\4\2\34\1\4\1\72\1\4\1\13\1\11"+
        "\1\uffff\1\11\1\34\1\5\1\34\1\uffff\1\13\1\4\1\11\1\34";
    static final String DFA1_maxS =
        "\1\100\6\uffff\1\4\3\uffff\1\5\2\uffff\1\13\1\11\1\4\1\101\1\13"+
        "\3\4\3\5\1\13\2\11\1\13\1\11\1\13\1\4\2\101\1\4\1\72\1\11\1\13\1"+
        "\11\1\uffff\1\11\1\42\1\5\1\101\1\uffff\1\13\2\11\1\101";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\10\1\11\1\12\1\uffff"+
        "\1\13\1\15\31\uffff\1\14\4\uffff\1\7\4\uffff";
    static final String DFA1_specialS =
        "\61\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1\3\uffff\1\2\1\3\5\uffff\1\5\1\6\1\4\6\uffff\1\7\1\uffff"+
            "\1\15\3\10\1\11\1\12\26\uffff\1\14\5\uffff\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\16",
            "",
            "",
            "",
            "\1\17",
            "",
            "",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23\2\uffff\1\24\1\25\1\26\37\uffff\1\27",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\23\2\uffff\1\24\1\25\1\26\37\uffff\1\27",
            "\1\23\2\uffff\1\24\1\25\1\26\37\uffff\1\27",
            "\1\46",
            "\1\14",
            "\1\50\4\uffff\1\51",
            "\1\52",
            "\1\53",
            "",
            "\1\51",
            "\1\7\1\54\4\uffff\1\11",
            "\1\55",
            "\1\23\2\uffff\1\24\1\25\1\26\37\uffff\1\27",
            "",
            "\1\56",
            "\1\57\4\uffff\1\60",
            "\1\60",
            "\1\23\2\uffff\1\24\1\25\1\26\37\uffff\1\27"
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
            return "69:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | dialog_definition | dialog_destroy | ko_message );";
        }
    }
 

    public static final BitSet FOLLOW_open_communication_in_main53 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_main58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_open_session_in_main66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_session_in_main71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_suspend_session_in_main76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_resume_session_in_main81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_invalid_model_in_main89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_main97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_model_in_main105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_results_in_main110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialog_definition_in_main115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialog_destroy_in_main120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ko_message_in_main128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_open_communication154 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_communication157 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_communication159 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_open_connection_in_open_communication164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_open_connection180 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection184 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_open_connection186 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_open_connection190 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_connection192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_open_session242 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_open_session246 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_open_session248 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_open_session251 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_open_session254 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_interlocutor_table_in_open_session257 = new BitSet(new long[]{0x0000000380800000L});
    public static final BitSet FOLLOW_message_to_user_in_open_session262 = new BitSet(new long[]{0x0000000380800000L});
    public static final BitSet FOLLOW_receive_services_in_open_session266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table281 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table284 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_interlocutor_table288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_body_table303 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table307 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table309 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table313 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table315 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table319 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table321 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table325 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_body_table327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_suspend_session344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_resume_session360 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_resume_session364 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_resume_session366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_close_session383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_group_in_receive_services419 = new BitSet(new long[]{0x0000000010800000L});
    public static final BitSet FOLLOW_state_service_in_receive_services429 = new BitSet(new long[]{0x0000000010400000L});
    public static final BitSet FOLLOW_22_in_receive_services437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_receive_services_group463 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_root_description_in_receive_services_group466 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_service_description_in_receive_services_group475 = new BitSet(new long[]{0x0000000009000000L});
    public static final BitSet FOLLOW_24_in_receive_services_group484 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_receive_services_group491 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_services_group495 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receive_services_group497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_root_description519 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_root_description523 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_root_description525 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description529 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_root_description531 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_root_description535 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_root_description536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_service_description563 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description567 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description569 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description573 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description575 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description580 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description583 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description587 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description590 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description595 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description598 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description602 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description605 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_service_description610 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description613 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_service_description617 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_service_description620 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_service_description624 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_service_description627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_state_service654 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service658 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service660 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service664 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service666 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_state_service670 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_state_service672 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_state_service676 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_state_service679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_invalid_model701 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_29_in_invalid_model709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ko_message730 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ko_message732 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ko_message734 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ko_message738 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ko_message740 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ko_message744 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ko_message746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message785 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message789 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message807 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message811 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message829 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message833 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message835 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message839 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_ask_for_model862 = new BitSet(new long[]{0x0000000410000000L});
    public static final BitSet FOLLOW_34_in_ask_for_model866 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_model868 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_model870 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_model872 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_model874 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_model876 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ask_for_model878 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_receive_results901 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_receive_results904 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results908 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_receive_results910 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receive_results914 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_receive_results916 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_receive_results920 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receive_results922 = new BitSet(new long[]{0x0400002310000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_state_service_in_receive_results929 = new BitSet(new long[]{0x0400002310000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_special_message_in_receive_results936 = new BitSet(new long[]{0x0400002310000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_warning_message_in_receive_results941 = new BitSet(new long[]{0x0400002310000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_dialog_definition_in_receive_results946 = new BitSet(new long[]{0x0400002310000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_result_in_receive_results951 = new BitSet(new long[]{0x0400002310000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_37_in_result983 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result987 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result989 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result993 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result995 = new BitSet(new long[]{0x001F3FA000000000L});
    public static final BitSet FOLLOW_result_body_in_result1002 = new BitSet(new long[]{0x001F3FE000000000L});
    public static final BitSet FOLLOW_38_in_result1010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1074 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_textual_result1091 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1095 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result1097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_attribute_change1114 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change1118 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1120 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1124 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1126 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1130 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_change1132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_attribute_outline1149 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1153 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1155 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1159 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1161 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1165 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1168 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1172 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_object_designation1191 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1195 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_object_outline1215 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1219 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_object_creation1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_object_creation1241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_object_creation1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_object_creation1251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_object_deletion1264 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1268 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_object_deletion1278 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1282 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1284 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1288 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_model_definition1322 = new BitSet(new long[]{0x03FF000000000000L});
    public static final BitSet FOLLOW_syntactic_in_model_definition1327 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_aestetic_in_model_definition1331 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_model_definition1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic1358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_syntactic1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_node1377 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node1379 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node1381 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node1383 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_node1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_box1398 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box1400 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1402 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1404 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1406 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1408 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_box1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_arc1423 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc1425 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1427 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1429 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1431 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1433 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1435 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1437 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_arc1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_attribute1452 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1456 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1458 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1460 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1462 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_attribute1469 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1471 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1473 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1475 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1477 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1479 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1481 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1483 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1485 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1487 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic1503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic1511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_position1524 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1528 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1530 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1534 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1536 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1540 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_object_position1547 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1551 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1553 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1557 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1559 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1563 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_object_position1570 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1574 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1576 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1580 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1582 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1586 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1588 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1592 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1594 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1598 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_text_position1614 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1618 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1620 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position1624 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1626 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1630 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1632 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1636 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_text_position1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_intermediary_point1653 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1655 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point1657 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1659 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point1661 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point1663 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_intermediary_point1665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialog_destroy_in_dialog_definition1698 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_dialog_definition1702 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition1707 = new BitSet(new long[]{0x2800000000000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition1712 = new BitSet(new long[]{0x2800000000000000L});
    public static final BitSet FOLLOW_59_in_dialog_definition1718 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_dialog_display_in_dialog_definition1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_dialog_creation1744 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1748 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1750 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1754 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1756 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1762 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1764 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1769 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1771 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1777 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1779 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1783 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1785 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1791 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1793 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation1797 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation1799 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1805 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_creation1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_next_dialog1824 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_next_dialog1828 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog1830 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog1834 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_next_dialog1836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_dialog_display1852 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_display1856 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_display1858 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_hide_dialog1874 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_hide_dialog1878 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_hide_dialog1880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_dialog_destroy1897 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_destroy1901 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_destroy1903 = new BitSet(new long[]{0x0000000390000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_state_service_in_dialog_destroy1910 = new BitSet(new long[]{0x0000000390000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_dialog_destroy1914 = new BitSet(new long[]{0x0000000390000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_dialog_destroy1920 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_destroy1922 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_destroy1924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_interactive_response1939 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response1941 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response1943 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response1945 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interactive_response1947 = new BitSet(new long[]{0x0000000000000002L});

}