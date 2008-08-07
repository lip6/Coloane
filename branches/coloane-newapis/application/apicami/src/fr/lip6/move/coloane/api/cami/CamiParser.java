// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-08-07 18:13:32

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
import fr.lip6.move.coloane.interfaces.model.command.CreateNodeCommand;
import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.api.camiObject.result.Tip;
import fr.lip6.move.coloane.interfaces.model.command.AttributePositionCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateAttributeCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateInflexPointCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteObjectCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateArcCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateNodeCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteInflexPointsCommand;
import fr.lip6.move.coloane.interfaces.model.command.ObjectPositionCommand;
import fr.lip6.move.coloane.api.camiObject.result.Result;
import fr.lip6.move.coloane.api.camiObject.result.SubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'FL()'", "'VI('", "'SS()'", "'RS('", "'FS()'", "'QQ(3)'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'QQ(2)'", "'KO('", "'TR('", "'WN('", "'MO('", "'DF(-'", "'DR()'", "'RQ('", "'FR('", "'DE()'", "'DE('", "'FE()'", "'RT('", "'WE('", "'MT('", "'RO('", "'ME('", "'SU('", "'SI('", "'ZA('", "'XA('", "'TD('", "'OB('", "'AT('", "'DB()'", "'FB()'", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'pI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('"
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:95:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | ko_message );
    public final void main() throws RecognitionException {
         state = ICamiParserState.DEFAULT_STATE; 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:98:2: ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | ko_message )
            int alt1=11;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:98:4: open_communication
                    {
                    pushFollow(FOLLOW_open_communication_in_main58);
                    open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:99:4: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_main63);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:101:4: open_session
                    {
                    pushFollow(FOLLOW_open_session_in_main71);
                    open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:102:4: close_session
                    {
                    pushFollow(FOLLOW_close_session_in_main76);
                    close_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:103:4: suspend_session
                    {
                    pushFollow(FOLLOW_suspend_session_in_main81);
                    suspend_session();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:104:4: resume_session
                    {
                    pushFollow(FOLLOW_resume_session_in_main86);
                    resume_session();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:106:4: invalid_model
                    {
                    pushFollow(FOLLOW_invalid_model_in_main94);
                    invalid_model();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:108:4: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_main102);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:110:4: ask_for_model
                    {
                    pushFollow(FOLLOW_ask_for_model_in_main110);
                    ask_for_model();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:111:4: receive_results
                    {
                    pushFollow(FOLLOW_receive_results_in_main115);
                    receive_results();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:113:4: ko_message
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:121:1: open_communication : 'SC(' fkName= CAMI_STRING ')' open_connection ;
    public final void open_communication() throws RecognitionException {
        open_communication_stack.push(new open_communication_scope());
        Token fkName=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:123:2: ( 'SC(' fkName= CAMI_STRING ')' open_connection )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:124:2: 'SC(' fkName= CAMI_STRING ')' open_connection
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:132:1: open_connection : 'OC(' major= NUMBER ',' minor= NUMBER ')' ;
    public final void open_connection() throws RecognitionException {
        Token major=null;
        Token minor=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:133:2: ( 'OC(' major= NUMBER ',' minor= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:134:2: 'OC(' major= NUMBER ',' minor= NUMBER ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:142:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:143:2: ( 'FC()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:144:2: 'FC()'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:1: open_session : ( message_to_user )* 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table receive_services ;
    public final void open_session() throws RecognitionException {
        open_session_stack.push(new open_session_scope());
        Token session_name=null;

         ((open_session_scope)open_session_stack.peek()).sessionArgs = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:157:2: ( ( message_to_user )* 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table receive_services )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:158:2: ( message_to_user )* 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table receive_services
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:158:2: ( message_to_user )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=31 && LA2_0<=33)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:158:2: message_to_user
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
            	
            pushFollow(FOLLOW_receive_services_in_open_session261);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:170:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:171:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:172:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,16,FOLLOW_16_in_interlocutor_table276); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:2: ( body_table )+
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
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table279);
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

            match(input,17,FOLLOW_17_in_interlocutor_table283); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:178:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token deprecated=null;
        Token new_model=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:179:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:180:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')'
            {
            match(input,18,FOLLOW_18_in_body_table298); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table302); 
            match(input,11,FOLLOW_11_in_body_table304); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table308); 
            match(input,11,FOLLOW_11_in_body_table310); 
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table314); 
            match(input,11,FOLLOW_11_in_body_table316); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table320); 
            match(input,9,FOLLOW_9_in_body_table322); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:189:1: suspend_session : 'SS()' ;
    public final void suspend_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:190:2: ( 'SS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:191:2: 'SS()'
            {
            match(input,19,FOLLOW_19_in_suspend_session339); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:198:1: resume_session : 'RS(' session_name= CAMI_STRING ')' ;
    public final void resume_session() throws RecognitionException {
        Token session_name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:199:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:200:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,20,FOLLOW_20_in_resume_session355); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_resume_session359); 
            match(input,9,FOLLOW_9_in_resume_session361); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:207:1: close_session : 'FS()' ;
    public final void close_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:208:2: ( 'FS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:209:2: 'FS()'
            {
            match(input,21,FOLLOW_21_in_close_session378); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:220:1: receive_services : ( receive_services_group )+ ( state_service )+ 'QQ(3)' ;
    public final void receive_services() throws RecognitionException {
        receive_services_stack.push(new receive_services_scope());
        ISubMenu receive_services_group1 = null;

        IUpdateMenu state_service2 = null;


         
        		List<ISubMenu> roots = new ArrayList<ISubMenu>();
        		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>();
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:226:2: ( ( receive_services_group )+ ( state_service )+ 'QQ(3)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:227:2: ( receive_services_group )+ ( state_service )+ 'QQ(3)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:227:2: ( receive_services_group )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==23||(LA4_0>=31 && LA4_0<=33)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:227:4: receive_services_group
            	    {
            	    pushFollow(FOLLOW_receive_services_group_in_receive_services414);
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

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:228:2: ( state_service )+
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
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:228:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_receive_services424);
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

            match(input,22,FOLLOW_22_in_receive_services432); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:1: receive_services_group returns [ISubMenu builtRoot] : ( message_to_user )* 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? ;
    public final ISubMenu receive_services_group() throws RecognitionException {
        ISubMenu builtRoot = null;

        Token root_name=null;
        ISubMenu root_description3 = null;

        IQuestion service_description4 = null;


         LOGGER.finest("Reception d'une liste de services (Groupe)"); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:238:2: ( ( message_to_user )* 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:239:2: ( message_to_user )* 'DQ()' root_description ( service_description )+ 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )?
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:239:2: ( message_to_user )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=31 && LA6_0<=33)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:239:2: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_receive_services_group457);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_receive_services_group462); 
            pushFollow(FOLLOW_root_description_in_receive_services_group465);
            root_description3=root_description();
            _fsp--;

             builtRoot = root_description3; 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:242:2: ( service_description )+
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
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:242:4: service_description
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:244:2: ( 'VQ(' root_name= CAMI_STRING ')' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==25) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:244:4: 'VQ(' root_name= CAMI_STRING ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:1: root_description returns [ISubMenu root] : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final ISubMenu root_description() throws RecognitionException {
        ISubMenu root = null;

        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:250:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:251:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:257:1: service_description returns [IQuestion question] : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' ;
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active_state= NUMBER )? ')'
            {
            match(input,27,FOLLOW_27_in_service_description560); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description564); 
            match(input,11,FOLLOW_11_in_service_description566); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description570); 
            match(input,11,FOLLOW_11_in_service_description572); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:262:15: (question_type= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:262:15: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description577); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description580); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:262:45: (question_behavior= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:262:45: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description584); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description587); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:263:10: (set_item= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:263:10: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description592); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description595); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:263:29: (dialog= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:263:29: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description599); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description602); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:17: (stop_authorized= NUMBER )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NUMBER) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:17: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description607); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description610); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:46: (output_formalism= CAMI_STRING )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==CAMI_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:46: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description614); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_service_description617); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:76: (active_state= NUMBER )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NUMBER) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:76: active_state= NUMBER
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:284:1: state_service returns [IUpdateMenu builtUpdate] : 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' ;
    public final IUpdateMenu state_service() throws RecognitionException {
        IUpdateMenu builtUpdate = null;

        Token root_name=null;
        Token question_name=null;
        Token state=null;
        Token message=null;

         List<String> tq = new ArrayList<String>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:287:2: ( 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:288:2: 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')'
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:288:88: (message= CAMI_STRING )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==CAMI_STRING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:288:88: message= CAMI_STRING
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:360:1: invalid_model : ( state_service )* 'QQ(2)' ;
    public final void invalid_model() throws RecognitionException {
        IUpdateMenu state_service5 = null;


         List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:362:2: ( ( state_service )* 'QQ(2)' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:2: ( state_service )* 'QQ(2)'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:2: ( state_service )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==28) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:3: state_service
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:375:1: ko_message : 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')' ;
    public final void ko_message() throws RecognitionException {
        Token mess=null;
        Token severity=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:376:2: ( 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:377:2: 'KO(' NUMBER ',' mess= CAMI_STRING ',' severity= NUMBER ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:386:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:387:2: ( trace_message | warning_message | special_message )
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
                    new NoViableAltException("386:1: message_to_user : ( trace_message | warning_message | special_message );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:387:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user758);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:388:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user763);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:389:4: special_message
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:1: trace_message : 'TR(' message= CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:394:2: ( 'TR(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:395:2: 'TR(' message= CAMI_STRING ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:403:1: warning_message : 'WN(' message= CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:404:2: ( 'WN(' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:2: 'WN(' message= CAMI_STRING ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:413:1: special_message : 'MO(' type= NUMBER ',' message= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token type=null;
        Token message=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:414:2: ( 'MO(' type= NUMBER ',' message= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:415:2: 'MO(' type= NUMBER ',' message= CAMI_STRING ')'
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:447:1: ask_for_model : ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void ask_for_model() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:2: ( ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:2: ( state_service )* 'DF(-' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:2: ( state_service )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==28) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:2: state_service
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:456:1: receive_results : 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' ;
    public final void receive_results() throws RecognitionException {
        Token root_name=null;
        Token service_name=null;
        Token deprecated=null;

         
        		state = ICamiParserState.RESULT_STATE;
        		updates = new ArrayList<IUpdateMenu>();
        		dialogs = new HashMap<Integer, IDialog>();
        	
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:462:2: ( 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:463:2: 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= NUMBER ')'
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

            		result = CamiObjectBuilder.buildResult(root_name.getText(), service_name.getText(), sessionController.getActiveSession().getOutputModel());
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:469:1: results : ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes | model_definition )* ( 'FR(' NUMBER ')' )? ;
    public final void results() throws RecognitionException {
        IUpdateMenu state_service6 = null;

        IDialog dialog_definition7 = null;

        ISubResult one_result8 = null;

        ITip model_changes9 = null;

        List<ICommand> model_definition10 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:470:2: ( ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes | model_definition )* ( 'FR(' NUMBER ')' )? )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:471:2: ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes | model_definition )* ( 'FR(' NUMBER ')' )?
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:471:2: ( message_to_user | state_service | dialog_definition | dialog_display | dialog_destroy | one_result | model_changes | model_definition )*
            loop20:
            do {
                int alt20=9;
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
                case 66:
                    {
                    alt20=3;
                    }
                    break;
                case 70:
                    {
                    alt20=4;
                    }
                    break;
                case 72:
                    {
                    alt20=5;
                    }
                    break;
                case 38:
                case 39:
                    {
                    alt20=6;
                    }
                    break;
                case 48:
                case 49:
                case 50:
                    {
                    alt20=7;
                    }
                    break;
                case 53:
                    {
                    alt20=8;
                    }
                    break;

                }

                switch (alt20) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:471:4: message_to_user
            	    {
            	    pushFollow(FOLLOW_message_to_user_in_results936);
            	    message_to_user();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:4: state_service
            	    {
            	    pushFollow(FOLLOW_state_service_in_results941);
            	    state_service6=state_service();
            	    _fsp--;

            	     updates.add(state_service6); 

            	    }
            	    break;
            	case 3 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:473:4: dialog_definition
            	    {
            	    pushFollow(FOLLOW_dialog_definition_in_results950);
            	    dialog_definition7=dialog_definition();
            	    _fsp--;

            	     dialogs.put(dialog_definition7.getId(), dialog_definition7); 

            	    }
            	    break;
            	case 4 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:4: dialog_display
            	    {
            	    pushFollow(FOLLOW_dialog_display_in_results958);
            	    dialog_display();
            	    _fsp--;


            	    }
            	    break;
            	case 5 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:475:4: dialog_destroy
            	    {
            	    pushFollow(FOLLOW_dialog_destroy_in_results963);
            	    dialog_destroy();
            	    _fsp--;


            	    }
            	    break;
            	case 6 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:476:4: one_result
            	    {
            	    pushFollow(FOLLOW_one_result_in_results968);
            	    one_result8=one_result();
            	    _fsp--;

            	     ((Result) result).addSubResult(one_result8); 

            	    }
            	    break;
            	case 7 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:477:4: model_changes
            	    {
            	    pushFollow(FOLLOW_model_changes_in_results977);
            	    model_changes9=model_changes();
            	    _fsp--;

            	     ((Result) result).addTip(model_changes9); 

            	    }
            	    break;
            	case 8 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:478:4: model_definition
            	    {
            	    pushFollow(FOLLOW_model_definition_in_results985);
            	    model_definition10=model_definition();
            	    _fsp--;

            	     ((Result) result).addOutputGraph(model_definition10); 

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:480:2: ( 'FR(' NUMBER ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==37) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:481:2: 'FR(' NUMBER ')'
                    {
                    match(input,37,FOLLOW_37_in_results998); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_results1000); 
                    match(input,9,FOLLOW_9_in_results1002); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:495:1: one_result returns [ISubResult builtResult] : ( 'DE()' | 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ) ( result_body )+ 'FE()' ;
    public final ISubResult one_result() throws RecognitionException {
        one_result_stack.push(new one_result_scope());
        ISubResult builtResult = null;

        Token set_name=null;
        Token set_type=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:498:2: ( ( 'DE()' | 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ) ( result_body )+ 'FE()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:2: ( 'DE()' | 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' ) ( result_body )+ 'FE()'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:2: ( 'DE()' | 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==38) ) {
                alt22=1;
            }
            else if ( (LA22_0==39) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("499:2: ( 'DE()' | 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')' )", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:4: 'DE()'
                    {
                    match(input,38,FOLLOW_38_in_one_result1033); 
                     ((one_result_scope)one_result_stack.peek()).current = CamiObjectBuilder.buildSubResult("","0"); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:500:4: 'DE(' set_name= CAMI_STRING ',' set_type= NUMBER ')'
                    {
                    match(input,39,FOLLOW_39_in_one_result1040); 
                    set_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_one_result1044); 
                    match(input,11,FOLLOW_11_in_one_result1046); 
                    set_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_one_result1050); 
                    match(input,9,FOLLOW_9_in_one_result1052); 
                     ((one_result_scope)one_result_stack.peek()).current = CamiObjectBuilder.buildSubResult(set_name.getText(), set_type.getText()); 

                    }
                    break;

            }

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:502:2: ( result_body )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=38 && LA23_0<=39)||(LA23_0>=41 && LA23_0<=47)||(LA23_0>=55 && LA23_0<=65)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:502:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_one_result1060);
            	    result_body();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);

            match(input,40,FOLLOW_40_in_one_result1064); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:507:1: result_body : ( one_result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        ICommand object_creation11 = null;

        ICommand object_deletion12 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:2: ( one_result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt24=8;
            switch ( input.LA(1) ) {
            case 38:
            case 39:
                {
                alt24=1;
                }
                break;
            case 41:
                {
                alt24=2;
                }
                break;
            case 42:
                {
                alt24=3;
                }
                break;
            case 44:
                {
                alt24=4;
                }
                break;
            case 45:
                {
                alt24=5;
                }
                break;
            case 43:
                {
                alt24=6;
                }
                break;
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
                {
                alt24=7;
                }
                break;
            case 46:
            case 47:
                {
                alt24=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("507:1: result_body : ( one_result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:4: one_result
                    {
                    pushFollow(FOLLOW_one_result_in_result_body1079);
                    one_result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:509:4: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1084);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:510:4: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1089);
                    attribute_change();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:511:4: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1094);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:512:4: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1099);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:513:4: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1104);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:514:4: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1109);
                    object_creation11=object_creation();
                    _fsp--;

                     ((Result) result).addCommand(object_creation11); 

                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:515:4: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1116);
                    object_deletion12=object_deletion();
                    _fsp--;

                     ((Result) result).addCommand(object_deletion12); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:519:1: textual_result : 'RT(' text= CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        Token text=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:520:2: ( 'RT(' text= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:521:2: 'RT(' text= CAMI_STRING ')'
            {
            match(input,41,FOLLOW_41_in_textual_result1133); 
            text=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1137); 
            match(input,9,FOLLOW_9_in_textual_result1139); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:525:1: attribute_change : 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token new_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:526:2: ( 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:527:2: 'WE(' id= NUMBER ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,42,FOLLOW_42_in_attribute_change1156); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change1160); 
            match(input,11,FOLLOW_11_in_attribute_change1162); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1166); 
            match(input,11,FOLLOW_11_in_attribute_change1168); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1172); 
            match(input,9,FOLLOW_9_in_attribute_change1174); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:531:1: attribute_outline : 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attribute_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:532:2: ( 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:2: 'MT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,43,FOLLOW_43_in_attribute_outline1189); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1193); 
            match(input,11,FOLLOW_11_in_attribute_outline1195); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1199); 
            match(input,11,FOLLOW_11_in_attribute_outline1201); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:58: (begin= NUMBER )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:58: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1205); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1208); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:74: (end= NUMBER )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:74: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1212); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1215); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:538:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:539:2: ( 'RO(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:540:2: 'RO(' id= NUMBER ')'
            {
            match(input,44,FOLLOW_44_in_object_designation1233); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1237); 
            match(input,9,FOLLOW_9_in_object_designation1239); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:544:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:545:2: ( 'ME(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:2: 'ME(' id= NUMBER ')'
            {
            match(input,45,FOLLOW_45_in_object_outline1257); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1261); 
            match(input,9,FOLLOW_9_in_object_outline1263); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:550:1: object_creation returns [ICommand command] : ( syntactic | aestetic );
    public final ICommand object_creation() throws RecognitionException {
        ICommand command = null;

        ICommand syntactic13 = null;

        ICommand aestetic14 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:2: ( syntactic | aestetic )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=55 && LA27_0<=59)) ) {
                alt27=1;
            }
            else if ( ((LA27_0>=60 && LA27_0<=65)) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("550:1: object_creation returns [ICommand command] : ( syntactic | aestetic );", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_object_creation1282);
                    syntactic13=syntactic();
                    _fsp--;

                     command = syntactic13; 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:552:4: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_object_creation1289);
                    aestetic14=aestetic();
                    _fsp--;

                     command = aestetic14;  

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
        return command;
    }
    // $ANTLR end object_creation


    // $ANTLR start object_deletion
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:556:1: object_deletion returns [ICommand command] : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final ICommand object_deletion() throws RecognitionException {
        ICommand command = null;

        Token id=null;
        Token page_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==46) ) {
                alt28=1;
            }
            else if ( (LA28_0==47) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("556:1: object_deletion returns [ICommand command] : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:4: 'SU(' id= NUMBER ')'
                    {
                    match(input,46,FOLLOW_46_in_object_deletion1309); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1313); 
                    match(input,9,FOLLOW_9_in_object_deletion1315); 
                     command = new DeleteObjectCommand(Integer.parseInt(id.getText())); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:558:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,47,FOLLOW_47_in_object_deletion1323); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1327); 
                    match(input,11,FOLLOW_11_in_object_deletion1329); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1333); 
                    match(input,9,FOLLOW_9_in_object_deletion1335); 

                     			if ((id.getText()).equals("-1")) { command = new DeleteInflexPointsCommand(); } 
                     			else { command = new DeleteObjectCommand(Integer.parseInt(id.getText())); }
                     		

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
        return command;
    }
    // $ANTLR end object_deletion


    // $ANTLR start model_changes
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:565:2: model_changes returns [ITip tip] : ( attribute_table )? ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )? 'XA(' id_object= NUMBER ',' attribute_name= CAMI_STRING ',' attribute_value= CAMI_STRING ')' ;
    public final ITip model_changes() throws RecognitionException {
        ITip tip = null;

        Token id_object=null;
        Token attribute_name=null;
        Token attribute_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:566:3: ( ( attribute_table )? ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )? 'XA(' id_object= NUMBER ',' attribute_name= CAMI_STRING ',' attribute_value= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:567:3: ( attribute_table )? ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )? 'XA(' id_object= NUMBER ',' attribute_name= CAMI_STRING ',' attribute_value= CAMI_STRING ')'
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:567:3: ( attribute_table )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==50) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:567:3: attribute_table
                    {
                    pushFollow(FOLLOW_attribute_table_in_model_changes1362);
                    attribute_table();
                    _fsp--;


                    }
                    break;

            }

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:568:3: ( 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==48) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:568:5: 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_model_changes1369); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1370); 
                    match(input,11,FOLLOW_11_in_model_changes1372); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1374); 
                    match(input,11,FOLLOW_11_in_model_changes1376); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1378); 
                    match(input,11,FOLLOW_11_in_model_changes1380); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1382); 
                    match(input,11,FOLLOW_11_in_model_changes1384); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1386); 
                    match(input,9,FOLLOW_9_in_model_changes1388); 

                    }
                    break;

            }

            match(input,49,FOLLOW_49_in_model_changes1396); 
            id_object=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_model_changes1400); 
            match(input,11,FOLLOW_11_in_model_changes1402); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_model_changes1406); 
            match(input,11,FOLLOW_11_in_model_changes1408); 
            attribute_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_model_changes1412); 
            match(input,9,FOLLOW_9_in_model_changes1414); 
             tip = new Tip(Integer.parseInt(id_object.getText()), attribute_name.getText(), attribute_value.getText()); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return tip;
    }
    // $ANTLR end model_changes


    // $ANTLR start attribute_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:574:2: attribute_table : 'TD(' CAMI_STRING ')' ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )* 'FA()' ;
    public final void attribute_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:575:3: ( 'TD(' CAMI_STRING ')' ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )* 'FA()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:576:3: 'TD(' CAMI_STRING ')' ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )* 'FA()'
            {
            match(input,50,FOLLOW_50_in_attribute_table1440); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1442); 
            match(input,9,FOLLOW_9_in_attribute_table1444); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:577:3: ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )*
            loop31:
            do {
                int alt31=3;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==51) ) {
                    alt31=1;
                }
                else if ( (LA31_0==52) ) {
                    alt31=2;
                }


                switch (alt31) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:577:5: 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')'
            	    {
            	    match(input,51,FOLLOW_51_in_attribute_table1450); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1452); 
            	    match(input,11,FOLLOW_11_in_attribute_table1454); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1456); 
            	    match(input,11,FOLLOW_11_in_attribute_table1458); 
            	    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1460); 
            	    match(input,9,FOLLOW_9_in_attribute_table1462); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:577:51: 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
            	    {
            	    match(input,52,FOLLOW_52_in_attribute_table1466); 
            	    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1468); 
            	    match(input,11,FOLLOW_11_in_attribute_table1470); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1472); 
            	    match(input,11,FOLLOW_11_in_attribute_table1474); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1476); 
            	    match(input,11,FOLLOW_11_in_attribute_table1478); 
            	    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_table1480); 
            	    match(input,11,FOLLOW_11_in_attribute_table1482); 
            	    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_table1484); 
            	    match(input,9,FOLLOW_9_in_attribute_table1486); 

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_attribute_table1492); 

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


    // $ANTLR start model_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:586:1: model_definition returns [List<ICommand> commandsList] : 'DB()' ( syntactic | aestetic )* 'FB()' ;
    public final List<ICommand> model_definition() throws RecognitionException {
        List<ICommand> commandsList = null;

        ICommand syntactic15 = null;

        ICommand aestetic16 = null;


         commandsList = new ArrayList<ICommand>(); 
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:588:2: ( 'DB()' ( syntactic | aestetic )* 'FB()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:589:2: 'DB()' ( syntactic | aestetic )* 'FB()'
            {
            match(input,53,FOLLOW_53_in_model_definition1526); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:590:2: ( syntactic | aestetic )*
            loop32:
            do {
                int alt32=3;
                int LA32_0 = input.LA(1);

                if ( ((LA32_0>=55 && LA32_0<=59)) ) {
                    alt32=1;
                }
                else if ( ((LA32_0>=60 && LA32_0<=65)) ) {
                    alt32=2;
                }


                switch (alt32) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:590:5: syntactic
            	    {
            	    pushFollow(FOLLOW_syntactic_in_model_definition1532);
            	    syntactic15=syntactic();
            	    _fsp--;

            	     commandsList.add(syntactic15); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:591:5: aestetic
            	    {
            	    pushFollow(FOLLOW_aestetic_in_model_definition1541);
            	    aestetic16=aestetic();
            	    _fsp--;

            	     commandsList.add(aestetic16); 

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            match(input,54,FOLLOW_54_in_model_definition1551); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return commandsList;
    }
    // $ANTLR end model_definition


    // $ANTLR start syntactic
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:597:1: syntactic returns [ICommand command] : ( node | box | arc | attribute );
    public final ICommand syntactic() throws RecognitionException {
        ICommand command = null;

        ICommand node17 = null;

        ICommand arc18 = null;

        ICommand attribute19 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:598:2: ( node | box | arc | attribute )
            int alt33=4;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt33=1;
                }
                break;
            case 56:
                {
                alt33=2;
                }
                break;
            case 57:
                {
                alt33=3;
                }
                break;
            case 58:
            case 59:
                {
                alt33=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("597:1: syntactic returns [ICommand command] : ( node | box | arc | attribute );", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:598:4: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic1568);
                    node17=node();
                    _fsp--;

                     command = node17; 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:599:4: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic1577);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:600:5: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic1584);
                    arc18=arc();
                    _fsp--;

                     command = arc18; 

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:601:5: attribute
                    {
                    pushFollow(FOLLOW_attribute_in_syntactic1594);
                    attribute19=attribute();
                    _fsp--;

                     command = attribute19; 

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
        return command;
    }
    // $ANTLR end syntactic


    // $ANTLR start node
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:605:1: node returns [ICommand command] : 'CN(' type= CAMI_STRING ',' id= NUMBER ')' ;
    public final ICommand node() throws RecognitionException {
        ICommand command = null;

        Token type=null;
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:606:2: ( 'CN(' type= CAMI_STRING ',' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:607:2: 'CN(' type= CAMI_STRING ',' id= NUMBER ')'
            {
            match(input,55,FOLLOW_55_in_node1615); 
            type=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node1619); 
            match(input,11,FOLLOW_11_in_node1621); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_node1625); 
            match(input,9,FOLLOW_9_in_node1627); 
             command = new CreateNodeCommand(Integer.parseInt(id.getText()), type.getText()); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return command;
    }
    // $ANTLR end node


    // $ANTLR start box
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:611:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:612:2: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:613:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,56,FOLLOW_56_in_box1644); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box1646); 
            match(input,11,FOLLOW_11_in_box1648); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1650); 
            match(input,11,FOLLOW_11_in_box1652); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box1654); 
            match(input,9,FOLLOW_9_in_box1656); 
             LOGGER.warning("BOX support is deprecated"); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:617:1: arc returns [ICommand command] : 'CA(' type= CAMI_STRING ',' id= NUMBER ',' source= NUMBER ',' target= NUMBER ')' ;
    public final ICommand arc() throws RecognitionException {
        ICommand command = null;

        Token type=null;
        Token id=null;
        Token source=null;
        Token target=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:618:2: ( 'CA(' type= CAMI_STRING ',' id= NUMBER ',' source= NUMBER ',' target= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:619:2: 'CA(' type= CAMI_STRING ',' id= NUMBER ',' source= NUMBER ',' target= NUMBER ')'
            {
            match(input,57,FOLLOW_57_in_arc1676); 
            type=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc1680); 
            match(input,11,FOLLOW_11_in_arc1682); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1686); 
            match(input,11,FOLLOW_11_in_arc1688); 
            source=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1692); 
            match(input,11,FOLLOW_11_in_arc1694); 
            target=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_arc1698); 
            match(input,9,FOLLOW_9_in_arc1700); 
             command = new CreateArcCommand(Integer.parseInt(id.getText()), type.getText(), Integer.parseInt(source.getText()), Integer.parseInt(target.getText())); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return command;
    }
    // $ANTLR end arc


    // $ANTLR start attribute
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:624:1: attribute returns [ICommand command] : ( 'CT(' name= CAMI_STRING ',' id= NUMBER ',' value= CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final ICommand attribute() throws RecognitionException {
        ICommand command = null;

        Token name=null;
        Token id=null;
        Token value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:625:2: ( 'CT(' name= CAMI_STRING ',' id= NUMBER ',' value= CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==58) ) {
                alt34=1;
            }
            else if ( (LA34_0==59) ) {
                alt34=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("624:1: attribute returns [ICommand command] : ( 'CT(' name= CAMI_STRING ',' id= NUMBER ',' value= CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:625:4: 'CT(' name= CAMI_STRING ',' id= NUMBER ',' value= CAMI_STRING ')'
                    {
                    match(input,58,FOLLOW_58_in_attribute1721); 
                    name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1725); 
                    match(input,11,FOLLOW_11_in_attribute1727); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1731); 
                    match(input,11,FOLLOW_11_in_attribute1733); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1736); 
                    match(input,9,FOLLOW_9_in_attribute1738); 
                     command = new CreateAttributeCommand(name.getText(), Integer.parseInt(id.getText()), value.getText()); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:626:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,59,FOLLOW_59_in_attribute1745); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1747); 
                    match(input,11,FOLLOW_11_in_attribute1749); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1751); 
                    match(input,11,FOLLOW_11_in_attribute1753); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1755); 
                    match(input,11,FOLLOW_11_in_attribute1757); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute1759); 
                    match(input,11,FOLLOW_11_in_attribute1761); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1763); 
                    match(input,9,FOLLOW_9_in_attribute1765); 

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
        return command;
    }
    // $ANTLR end attribute


    // $ANTLR start aestetic
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:630:1: aestetic returns [ICommand command] : ( object_position | text_position | inflex_point );
    public final ICommand aestetic() throws RecognitionException {
        ICommand command = null;

        ICommand object_position20 = null;

        ICommand text_position21 = null;

        ICommand inflex_point22 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:631:2: ( object_position | text_position | inflex_point )
            int alt35=3;
            switch ( input.LA(1) ) {
            case 60:
            case 61:
            case 62:
                {
                alt35=1;
                }
                break;
            case 63:
                {
                alt35=2;
                }
                break;
            case 64:
            case 65:
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("630:1: aestetic returns [ICommand command] : ( object_position | text_position | inflex_point );", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:631:4: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic1782);
                    object_position20=object_position();
                    _fsp--;

                     command = object_position20; 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:632:5: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic1790);
                    text_position21=text_position();
                    _fsp--;

                     command = text_position21; 

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:633:5: inflex_point
                    {
                    pushFollow(FOLLOW_inflex_point_in_aestetic1798);
                    inflex_point22=inflex_point();
                    _fsp--;

                     command = inflex_point22; 

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
        return command;
    }
    // $ANTLR end aestetic


    // $ANTLR start object_position
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:637:1: object_position returns [ICommand command] : ( 'PO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'PO(-1,' id= NUMBER ',' x= NUMBER ',' y= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' ) ;
    public final ICommand object_position() throws RecognitionException {
        ICommand command = null;

        Token id=null;
        Token x=null;
        Token y=null;
        Token top=null;
        Token bottom=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:638:2: ( ( 'PO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'PO(-1,' id= NUMBER ',' x= NUMBER ',' y= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' ) )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:639:2: ( 'PO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'PO(-1,' id= NUMBER ',' x= NUMBER ',' y= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:639:2: ( 'PO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'PO(-1,' id= NUMBER ',' x= NUMBER ',' y= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt36=3;
            switch ( input.LA(1) ) {
            case 60:
                {
                alt36=1;
                }
                break;
            case 61:
                {
                alt36=2;
                }
                break;
            case 62:
                {
                alt36=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("639:2: ( 'PO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'PO(-1,' id= NUMBER ',' x= NUMBER ',' y= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:639:4: 'PO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')'
                    {
                    match(input,60,FOLLOW_60_in_object_position1821); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1825); 
                    match(input,11,FOLLOW_11_in_object_position1827); 
                    x=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1831); 
                    match(input,11,FOLLOW_11_in_object_position1833); 
                    y=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1837); 
                    match(input,9,FOLLOW_9_in_object_position1839); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:640:4: 'pO(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')'
                    {
                    match(input,61,FOLLOW_61_in_object_position1844); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1848); 
                    match(input,11,FOLLOW_11_in_object_position1850); 
                    x=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1854); 
                    match(input,11,FOLLOW_11_in_object_position1856); 
                    y=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1860); 
                    match(input,9,FOLLOW_9_in_object_position1862); 

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:641:4: 'PO(-1,' id= NUMBER ',' x= NUMBER ',' y= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,62,FOLLOW_62_in_object_position1867); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1871); 
                    match(input,11,FOLLOW_11_in_object_position1873); 
                    x=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1877); 
                    match(input,11,FOLLOW_11_in_object_position1879); 
                    y=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1883); 
                    match(input,11,FOLLOW_11_in_object_position1885); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1889); 
                    match(input,11,FOLLOW_11_in_object_position1891); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position1895); 
                    match(input,9,FOLLOW_9_in_object_position1896); 

                    }
                    break;

            }

             command = new ObjectPositionCommand(Integer.parseInt(id.getText()), Integer.parseInt(x.getText()), Integer.parseInt(y.getText())); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return command;
    }
    // $ANTLR end object_position


    // $ANTLR start text_position
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:647:1: text_position returns [ICommand command] : 'PT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' x= NUMBER ',' y= NUMBER ')' ;
    public final ICommand text_position() throws RecognitionException {
        ICommand command = null;

        Token id=null;
        Token attribute_name=null;
        Token x=null;
        Token y=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:648:2: ( 'PT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' x= NUMBER ',' y= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:649:2: 'PT(' id= NUMBER ',' attribute_name= CAMI_STRING ',' x= NUMBER ',' y= NUMBER ')'
            {
            match(input,63,FOLLOW_63_in_text_position1922); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1926); 
            match(input,11,FOLLOW_11_in_text_position1928); 
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position1932); 
            match(input,11,FOLLOW_11_in_text_position1934); 
            x=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1938); 
            match(input,11,FOLLOW_11_in_text_position1940); 
            y=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position1944); 
            match(input,9,FOLLOW_9_in_text_position1946); 
             command = new AttributePositionCommand(Integer.parseInt(id.getText()), attribute_name.getText(), Integer.parseInt(x.getText()), Integer.parseInt(y.getText())); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return command;
    }
    // $ANTLR end text_position


    // $ANTLR start inflex_point
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:654:1: inflex_point returns [ICommand command] : ( 'PI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' ) ;
    public final ICommand inflex_point() throws RecognitionException {
        ICommand command = null;

        Token id=null;
        Token x=null;
        Token y=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:655:2: ( ( 'PI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' ) )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:656:2: ( 'PI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' )
            {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:656:2: ( 'PI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==64) ) {
                alt37=1;
            }
            else if ( (LA37_0==65) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("656:2: ( 'PI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' | 'pI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')' )", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:656:4: 'PI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')'
                    {
                    match(input,64,FOLLOW_64_in_inflex_point1970); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_inflex_point1974); 
                    match(input,11,FOLLOW_11_in_inflex_point1976); 
                    x=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_inflex_point1980); 
                    match(input,11,FOLLOW_11_in_inflex_point1982); 
                    y=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_inflex_point1986); 
                    match(input,9,FOLLOW_9_in_inflex_point1988); 

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:657:4: 'pI(' id= NUMBER ',' x= NUMBER ',' y= NUMBER ')'
                    {
                    match(input,65,FOLLOW_65_in_inflex_point1994); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_inflex_point1998); 
                    match(input,11,FOLLOW_11_in_inflex_point2000); 
                    x=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_inflex_point2004); 
                    match(input,11,FOLLOW_11_in_inflex_point2006); 
                    y=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_inflex_point2010); 
                    match(input,9,FOLLOW_9_in_inflex_point2012); 

                    }
                    break;

            }

             command = new CreateInflexPointCommand(Integer.parseInt(id.getText()), Integer.parseInt(x.getText()), Integer.parseInt(y.getText())); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return command;
    }
    // $ANTLR end inflex_point


    // $ANTLR start dialog_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:667:1: dialog_definition returns [IDialog dialog] : 'DC()' dialog_creation ( dialog_next[dialog] )* 'FF()' ;
    public final IDialog dialog_definition() throws RecognitionException {
        IDialog dialog = null;

        IDialog dialog_creation23 = null;


        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:669:2: ( 'DC()' dialog_creation ( dialog_next[dialog] )* 'FF()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:670:2: 'DC()' dialog_creation ( dialog_next[dialog] )* 'FF()'
            {
            match(input,66,FOLLOW_66_in_dialog_definition2045); 
             LOGGER.finest("Reception d'une definition d'une boite de dialogue"); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition2050);
            dialog_creation23=dialog_creation();
            _fsp--;

             dialog = dialog_creation23; 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:672:2: ( dialog_next[dialog] )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==69) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:672:2: dialog_next[dialog]
            	    {
            	    pushFollow(FOLLOW_dialog_next_in_dialog_definition2055);
            	    dialog_next(dialog);
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

            match(input,67,FOLLOW_67_in_dialog_definition2060); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:677:1: dialog_creation returns [IDialog dialog] : 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' ;
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
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:680:2: ( 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:681:2: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
            {
            match(input,68,FOLLOW_68_in_dialog_creation2087); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation2091); 
            match(input,11,FOLLOW_11_in_dialog_creation2093); 
            dialog_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation2097); 
            match(input,11,FOLLOW_11_in_dialog_creation2099); 
            buttons_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation2103); 
            match(input,11,FOLLOW_11_in_dialog_creation2105); 
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation2110); 
            match(input,11,FOLLOW_11_in_dialog_creation2112); 
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation2118); 
            match(input,11,FOLLOW_11_in_dialog_creation2120); 
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation2124); 
            match(input,11,FOLLOW_11_in_dialog_creation2126); 
            input_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation2130); 
            match(input,11,FOLLOW_11_in_dialog_creation2132); 
            line_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation2136); 
            match(input,11,FOLLOW_11_in_dialog_creation2138); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:683:15: (default_value= CAMI_STRING )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==CAMI_STRING) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:683:15: default_value= CAMI_STRING
                    {
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation2144); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_dialog_creation2147); 

            	
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:701:1: dialog_next[IDialog dialog] : 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' ;
    public final void dialog_next(IDialog dialog) throws RecognitionException {
        Token dialog_id=null;
        Token line=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:702:2: ( 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:703:2: 'DS(' dialog_id= NUMBER ',' line= CAMI_STRING ')'
            {
            match(input,69,FOLLOW_69_in_dialog_next2164); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_next2168); 
            match(input,11,FOLLOW_11_in_dialog_next2170); 
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_next2174); 
            match(input,9,FOLLOW_9_in_dialog_next2176); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:707:1: dialog_display : 'AD(' dialog_id= NUMBER ')' ;
    public final void dialog_display() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:708:2: ( 'AD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:709:2: 'AD(' dialog_id= NUMBER ')'
            {
            match(input,70,FOLLOW_70_in_dialog_display2192); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_display2196); 
            match(input,9,FOLLOW_9_in_dialog_display2198); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:718:1: dialog_hide : 'HD(' dialog_id= NUMBER ')' ;
    public final void dialog_hide() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:719:2: ( 'HD(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:720:2: 'HD(' dialog_id= NUMBER ')'
            {
            match(input,71,FOLLOW_71_in_dialog_hide2215); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_hide2219); 
            match(input,9,FOLLOW_9_in_dialog_hide2221); 

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:724:1: dialog_destroy : 'DG(' dialog_id= NUMBER ')' ;
    public final void dialog_destroy() throws RecognitionException {
        Token dialog_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:725:2: ( 'DG(' dialog_id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:726:2: 'DG(' dialog_id= NUMBER ')'
            {
            match(input,72,FOLLOW_72_in_dialog_destroy2236); 
            dialog_id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_destroy2240); 
            match(input,9,FOLLOW_9_in_dialog_destroy2242); 
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:730:1: interactive_response : 'MI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:731:2: ( 'MI(' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:732:2: 'MI(' NUMBER ',' NUMBER ')'
            {
            match(input,73,FOLLOW_73_in_interactive_response2259); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response2261); 
            match(input,11,FOLLOW_11_in_interactive_response2263); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response2265); 
            match(input,9,FOLLOW_9_in_interactive_response2267); 

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
            return "95:1: main : ( open_communication | close_connection | open_session | close_session | suspend_session | resume_session | invalid_model | message_to_user | ask_for_model | receive_results | ko_message );";
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
    public static final BitSet FOLLOW_receive_services_in_open_session261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table276 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table279 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_interlocutor_table283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_body_table298 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table302 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table304 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table308 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table310 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table314 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table316 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table320 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_body_table322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_suspend_session339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_resume_session355 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_resume_session359 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_resume_session361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_close_session378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receive_services_group_in_receive_services414 = new BitSet(new long[]{0x0000000390800000L});
    public static final BitSet FOLLOW_state_service_in_receive_services424 = new BitSet(new long[]{0x0000000010400000L});
    public static final BitSet FOLLOW_22_in_receive_services432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_receive_services_group457 = new BitSet(new long[]{0x0000000380800000L});
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
    public static final BitSet FOLLOW_message_to_user_in_results936 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_state_service_in_results941 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_dialog_definition_in_results950 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_dialog_display_in_results958 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_dialog_destroy_in_results963 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_one_result_in_results968 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_model_changes_in_results977 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_model_definition_in_results985 = new BitSet(new long[]{0x002700E390000002L,0x0000000000000144L});
    public static final BitSet FOLLOW_37_in_results998 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_results1000 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_results1002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_one_result1033 = new BitSet(new long[]{0xFF80FEC000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_39_in_one_result1040 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_one_result1044 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_one_result1046 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_one_result1050 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_one_result1052 = new BitSet(new long[]{0xFF80FEC000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_result_body_in_one_result1060 = new BitSet(new long[]{0xFF80FFC000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_40_in_one_result1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_one_result_in_result_body1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_textual_result1133 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1137 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_attribute_change1156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change1160 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1162 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1166 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1168 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1172 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_change1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_attribute_outline1189 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1193 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1195 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1199 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1201 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1205 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1208 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1212 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_object_designation1233 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1237 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_object_outline1257 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1261 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_syntactic_in_object_creation1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aestetic_in_object_creation1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_object_deletion1309 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1313 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_object_deletion1323 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1327 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1329 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1333 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_table_in_model_changes1362 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_48_in_model_changes1369 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1370 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1372 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1374 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1376 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1378 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1380 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1382 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1384 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1386 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_changes1388 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_model_changes1396 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_model_changes1400 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1402 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_model_changes1406 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_model_changes1408 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_model_changes1412 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_changes1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_attribute_table1440 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1442 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_table1444 = new BitSet(new long[]{0x0018000000008000L});
    public static final BitSet FOLLOW_51_in_attribute_table1450 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1452 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1454 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1456 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1458 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1460 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_table1462 = new BitSet(new long[]{0x0018000000008000L});
    public static final BitSet FOLLOW_52_in_attribute_table1466 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1468 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1470 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1472 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1474 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1476 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1478 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_table1480 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_table1482 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_table1484 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_table1486 = new BitSet(new long[]{0x0018000000008000L});
    public static final BitSet FOLLOW_15_in_attribute_table1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_model_definition1526 = new BitSet(new long[]{0xFFC0000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_syntactic_in_model_definition1532 = new BitSet(new long[]{0xFFC0000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_aestetic_in_model_definition1541 = new BitSet(new long[]{0xFFC0000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_54_in_model_definition1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic1577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_syntactic1594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_node1615 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node1619 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node1621 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node1625 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_node1627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_box1644 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box1646 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1648 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1650 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box1652 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box1654 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_box1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_arc1676 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc1680 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1682 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1686 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1688 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1692 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc1694 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc1698 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_arc1700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_attribute1721 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1725 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1727 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1731 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1733 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1736 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_attribute1745 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1747 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1749 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1751 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1753 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1755 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1757 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute1759 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute1761 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1763 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute1765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic1782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inflex_point_in_aestetic1798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_object_position1821 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1825 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1827 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1831 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1833 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1837 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_object_position1844 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1848 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1850 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1854 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1856 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1860 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_object_position1867 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1871 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1873 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1877 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1879 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1883 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1885 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1889 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position1891 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position1895 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_position1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_text_position1922 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1926 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1928 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position1932 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1934 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1938 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position1940 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position1944 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_text_position1946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_inflex_point1970 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_inflex_point1974 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_inflex_point1976 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_inflex_point1980 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_inflex_point1982 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_inflex_point1986 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_inflex_point1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_inflex_point1994 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_inflex_point1998 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_inflex_point2000 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_inflex_point2004 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_inflex_point2006 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_inflex_point2010 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_inflex_point2012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_dialog_definition2045 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition2050 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000028L});
    public static final BitSet FOLLOW_dialog_next_in_dialog_definition2055 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000028L});
    public static final BitSet FOLLOW_67_in_dialog_definition2060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_dialog_creation2087 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation2091 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2093 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation2097 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2099 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation2103 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2105 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation2110 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2112 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation2118 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2120 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation2124 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2126 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation2130 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2132 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation2136 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation2138 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation2144 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_creation2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_dialog_next2164 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_next2168 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_next2170 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_next2174 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_next2176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_dialog_display2192 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_display2196 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_display2198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_dialog_hide2215 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_hide2219 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_hide2221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_dialog_destroy2236 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_destroy2240 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog_destroy2242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_interactive_response2259 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response2261 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response2263 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response2265 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interactive_response2267 = new BitSet(new long[]{0x0000000000000002L});

}