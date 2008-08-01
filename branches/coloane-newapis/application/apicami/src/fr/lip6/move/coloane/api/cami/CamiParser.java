// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-08-01 14:13:45

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
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
	
import java.util.Map;
import java.util.logging.Logger;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
public class CamiParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'FL()'", "'VI('", "'SS()'", "'RS('", "'FS()'", "'QQ(3)'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'KO(1'", "'TR('", "'WN('", "'MO('", "'DR()'", "'RQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'MT('", "'RO('", "'ME('", "'SU('", "'SI('", "'DB()'", "'FB()'", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('"
    };
    public static final int CAMI_STRING=4;
    public static final int FIXED_LENGTH_STRING=6;
    public static final int NUMBER=5;
    public static final int EOF=-1;
    public static final String[] ruleNames = new String[] {
        "invalidRule", "main", "open_communication", "open_connection", 
        "close_connection", "open_session", "interlocutor_table", "body_table", 
        "suspend_session", "resume_session", "close_session", "receive_services", 
        "receive_services_group", "root_description", "service_description", 
        "state_service", "ko_message", "message_to_user", "trace_message", 
        "warning_message", "special_message", "receive_results", "result", 
        "result_body", "textual_result", "attribute_change", "attribute_outline", 
        "object_designation", "object_outline", "object_creation", "object_deletion", 
        "model_definition", "syntactic", "node", "box", "arc", "attribute", 
        "aestetic", "object_position", "text_position", "intermediary_point", 
        "dialog_definition", "dialog_creation", "next_dialog", "display_dialog", 
        "hide_dialog", "destroy_dialog", "interactive_response", "number"
    };

    public int ruleLevel = 0;
    public CamiParser(TokenStream input, int port) {
            super(input, port);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this, port, null);setDebugListener(proxy);
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }

    }
    public CamiParser(TokenStream input) {
        this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT);
    }
    public CamiParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg);
    }

    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }


    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g"; }



    /* L'objet pour la synchronisation */
    Map<String, Object> hash;
    	
    /* Le gestionnaire de session */
    ISessionController sessionController;

    /* Constructeur du parser */
    /* Ce cosntructeur est nécessaire pour récupérer l'objet de synchronisation */
    public CamiParser(TokenStream input, Map<String, Object> hash) {
    	this(input);
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
        try { dbg.enterRule("main");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(61, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:63:2: ( open_communication | open_connection | close_connection | open_session | close_session | suspend_session | resume_session | receive_services | state_service | message_to_user | receive_results | ko_message )
            int alt1=12;
            try { dbg.enterDecision(1);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(1);}

            switch (alt1) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:63:4: open_communication
                    {
                    dbg.location(63,4);
                    pushFollow(FOLLOW_open_communication_in_main53);
                    open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:64:4: open_connection
                    {
                    dbg.location(64,4);
                    pushFollow(FOLLOW_open_connection_in_main58);
                    open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:65:4: close_connection
                    {
                    dbg.location(65,4);
                    pushFollow(FOLLOW_close_connection_in_main63);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:67:4: open_session
                    {
                    dbg.location(67,4);
                    pushFollow(FOLLOW_open_session_in_main71);
                    open_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:68:4: close_session
                    {
                    dbg.location(68,4);
                    pushFollow(FOLLOW_close_session_in_main76);
                    close_session();
                    _fsp--;


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:69:4: suspend_session
                    {
                    dbg.location(69,4);
                    pushFollow(FOLLOW_suspend_session_in_main81);
                    suspend_session();
                    _fsp--;


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:70:4: resume_session
                    {
                    dbg.location(70,4);
                    pushFollow(FOLLOW_resume_session_in_main86);
                    resume_session();
                    _fsp--;


                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:72:4: receive_services
                    {
                    dbg.location(72,4);
                    pushFollow(FOLLOW_receive_services_in_main94);
                    receive_services();
                    _fsp--;


                    }
                    break;
                case 9 :
                    dbg.enterAlt(9);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:73:4: state_service
                    {
                    dbg.location(73,4);
                    pushFollow(FOLLOW_state_service_in_main99);
                    state_service();
                    _fsp--;


                    }
                    break;
                case 10 :
                    dbg.enterAlt(10);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:75:4: message_to_user
                    {
                    dbg.location(75,4);
                    pushFollow(FOLLOW_message_to_user_in_main107);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 11 :
                    dbg.enterAlt(11);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:77:4: receive_results
                    {
                    dbg.location(77,4);
                    pushFollow(FOLLOW_receive_results_in_main115);
                    receive_results();
                    _fsp--;


                    }
                    break;
                case 12 :
                    dbg.enterAlt(12);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:79:4: ko_message
                    {
                    dbg.location(79,4);
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
        dbg.location(80, 2);

        }
        finally {
            dbg.exitRule("main");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end main


    // $ANTLR start open_communication
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:87:1: open_communication : 'SC(' fkName= CAMI_STRING ')' ;
    public final void open_communication() throws RecognitionException {
        Token fkName=null;

        try { dbg.enterRule("open_communication");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(87, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:88:2: ( 'SC(' fkName= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:89:2: 'SC(' fkName= CAMI_STRING ')'
            {
            dbg.location(89,2);
            match(input,7,FOLLOW_7_in_open_communication144); 
            dbg.location(89,14);
            fkName=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_communication148); 
            dbg.location(89,27);
            match(input,8,FOLLOW_8_in_open_communication150); 
            dbg.location(89,31);

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
        dbg.location(96, 2);

        }
        finally {
            dbg.exitRule("open_communication");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end open_communication


    // $ANTLR start open_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:99:1: open_connection : 'OC(' major= NUMBER ',' minor= NUMBER ')' ;
    public final void open_connection() throws RecognitionException {
        Token major=null;
        Token minor=null;

        try { dbg.enterRule("open_connection");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(99, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:100:2: ( 'OC(' major= NUMBER ',' minor= NUMBER ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:101:2: 'OC(' major= NUMBER ',' minor= NUMBER ')'
            {
            dbg.location(101,2);
            match(input,9,FOLLOW_9_in_open_connection166); 
            dbg.location(101,13);
            major=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection170); 
            dbg.location(101,21);
            match(input,10,FOLLOW_10_in_open_connection172); 
            dbg.location(101,30);
            minor=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_open_connection176); 
            dbg.location(101,38);
            match(input,8,FOLLOW_8_in_open_connection178); 
            dbg.location(101,42);

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
        dbg.location(106, 2);

        }
        finally {
            dbg.exitRule("open_connection");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end open_connection


    // $ANTLR start close_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:109:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try { dbg.enterRule("close_connection");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(109, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:110:2: ( 'FC()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:111:2: 'FC()'
            {
            dbg.location(111,2);
            match(input,11,FOLLOW_11_in_close_connection194); 
            dbg.location(111,9);

            		((DisconnectObservable) hash.get("IDisconnect")).notifyObservers();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(114, 2);

        }
        finally {
            dbg.exitRule("close_connection");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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
        try { dbg.enterRule("open_session");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(121, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:124:2: ( 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:125:2: 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            dbg.location(125,2);
            match(input,12,FOLLOW_12_in_open_session228); 
            dbg.location(125,20);
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_open_session232); 
            dbg.location(125,33);
            match(input,8,FOLLOW_8_in_open_session234); 
            dbg.location(126,2);
            match(input,13,FOLLOW_13_in_open_session237); 
            dbg.location(127,2);
            match(input,14,FOLLOW_14_in_open_session240); 
            dbg.location(128,2);
            pushFollow(FOLLOW_interlocutor_table_in_open_session243);
            interlocutor_table();
            _fsp--;

            dbg.location(128,21);

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
        dbg.location(132, 2);

        }
        finally {
            dbg.exitRule("open_session");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end open_session


    // $ANTLR start interlocutor_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:135:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try { dbg.enterRule("interlocutor_table");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(135, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:136:2: ( 'TL()' ( body_table )+ 'FL()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:137:2: 'TL()' ( body_table )+ 'FL()'
            {
            dbg.location(137,2);
            match(input,15,FOLLOW_15_in_interlocutor_table260); 
            dbg.location(138,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:138:2: ( body_table )+
            int cnt2=0;
            try { dbg.enterSubRule(2);

            loop2:
            do {
                int alt2=2;
                try { dbg.enterDecision(2);

                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                } finally {dbg.exitDecision(2);}

                switch (alt2) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:138:2: body_table
            	    {
            	    dbg.location(138,2);
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table263);
            	    body_table();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt2++;
            } while (true);
            } finally {dbg.exitSubRule(2);}

            dbg.location(139,2);
            match(input,16,FOLLOW_16_in_interlocutor_table267); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(140, 2);

        }
        finally {
            dbg.exitRule("interlocutor_table");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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

        try { dbg.enterRule("body_table");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(143, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:144:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:145:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= NUMBER ',' new_model= NUMBER ')'
            {
            dbg.location(145,2);
            match(input,17,FOLLOW_17_in_body_table282); 
            dbg.location(145,20);
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table286); 
            dbg.location(145,33);
            match(input,10,FOLLOW_10_in_body_table288); 
            dbg.location(145,50);
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table292); 
            dbg.location(145,63);
            match(input,10,FOLLOW_10_in_body_table294); 
            dbg.location(145,77);
            deprecated=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table298); 
            dbg.location(145,85);
            match(input,10,FOLLOW_10_in_body_table300); 
            dbg.location(145,98);
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table304); 
            dbg.location(145,106);
            match(input,8,FOLLOW_8_in_body_table306); 
            dbg.location(145,110);

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
        dbg.location(151, 2);

        }
        finally {
            dbg.exitRule("body_table");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end body_table


    // $ANTLR start suspend_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:1: suspend_session : 'SS()' ;
    public final void suspend_session() throws RecognitionException {
        try { dbg.enterRule("suspend_session");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(154, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:155:2: ( 'SS()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:156:2: 'SS()'
            {
            dbg.location(156,2);
            match(input,18,FOLLOW_18_in_suspend_session323); 
            dbg.location(156,9);

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
        dbg.location(160, 2);

        }
        finally {
            dbg.exitRule("suspend_session");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end suspend_session


    // $ANTLR start resume_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:163:1: resume_session : 'RS(' session_name= CAMI_STRING ')' ;
    public final void resume_session() throws RecognitionException {
        Token session_name=null;

        try { dbg.enterRule("resume_session");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(163, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:164:2: ( 'RS(' session_name= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:165:2: 'RS(' session_name= CAMI_STRING ')'
            {
            dbg.location(165,2);
            match(input,19,FOLLOW_19_in_resume_session339); 
            dbg.location(165,20);
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_resume_session343); 
            dbg.location(165,33);
            match(input,8,FOLLOW_8_in_resume_session345); 
            dbg.location(165,37);

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
        dbg.location(169, 2);

        }
        finally {
            dbg.exitRule("resume_session");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end resume_session


    // $ANTLR start close_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:172:1: close_session : 'FS()' ;
    public final void close_session() throws RecognitionException {
        try { dbg.enterRule("close_session");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(172, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:2: ( 'FS()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:174:2: 'FS()'
            {
            dbg.location(174,2);
            match(input,20,FOLLOW_20_in_close_session362); 
            dbg.location(174,9);

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
        dbg.location(178, 2);

        }
        finally {
            dbg.exitRule("close_session");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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
        	
        try { dbg.enterRule("receive_services");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(185, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:196:2: ( ( receive_services_group )+ ( state_service )* 'QQ(3)' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: ( receive_services_group )+ ( state_service )* 'QQ(3)'
            {
            dbg.location(197,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: ( receive_services_group )+
            int cnt3=0;
            try { dbg.enterSubRule(3);

            loop3:
            do {
                int alt3=2;
                try { dbg.enterDecision(3);

                int LA3_0 = input.LA(1);

                if ( (LA3_0==22) ) {
                    alt3=1;
                }


                } finally {dbg.exitDecision(3);}

                switch (alt3) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: receive_services_group
            	    {
            	    dbg.location(197,2);
            	    pushFollow(FOLLOW_receive_services_group_in_receive_services396);
            	    receive_services_group();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt3++;
            } while (true);
            } finally {dbg.exitSubRule(3);}

            dbg.location(198,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:198:2: ( state_service )*
            try { dbg.enterSubRule(4);

            loop4:
            do {
                int alt4=2;
                try { dbg.enterDecision(4);

                int LA4_0 = input.LA(1);

                if ( (LA4_0==27) ) {
                    alt4=1;
                }


                } finally {dbg.exitDecision(4);}

                switch (alt4) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:198:2: state_service
            	    {
            	    dbg.location(198,2);
            	    pushFollow(FOLLOW_state_service_in_receive_services400);
            	    state_service();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);
            } finally {dbg.exitSubRule(4);}

            dbg.location(199,2);
            match(input,21,FOLLOW_21_in_receive_services404); 
            dbg.location(199,10);

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
        dbg.location(203, 2);

        }
        finally {
            dbg.exitRule("receive_services");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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

        try { dbg.enterRule("receive_services_group");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(205, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:207:2: ( 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )? )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:208:2: 'DQ()' root_description ( service_description )* 'FQ()' ( 'VQ(' root_name= CAMI_STRING ')' )?
            {
            dbg.location(208,2);
            match(input,22,FOLLOW_22_in_receive_services_group424); 
            dbg.location(209,2);
            pushFollow(FOLLOW_root_description_in_receive_services_group427);
            root_description();
            _fsp--;

            dbg.location(210,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:210:2: ( service_description )*
            try { dbg.enterSubRule(5);

            loop5:
            do {
                int alt5=2;
                try { dbg.enterDecision(5);

                int LA5_0 = input.LA(1);

                if ( (LA5_0==26) ) {
                    alt5=1;
                }


                } finally {dbg.exitDecision(5);}

                switch (alt5) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:210:2: service_description
            	    {
            	    dbg.location(210,2);
            	    pushFollow(FOLLOW_service_description_in_receive_services_group430);
            	    service_description();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);
            } finally {dbg.exitSubRule(5);}

            dbg.location(211,2);
            match(input,23,FOLLOW_23_in_receive_services_group434); 
            dbg.location(211,9);

            		LOGGER.finest("Fin de la reception du groupe de services");
            		((receive_services_scope)receive_services_stack.peek()).roots.add(((receive_services_group_scope)receive_services_group_stack.peek()).root);
            	
            dbg.location(215,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:215:2: ( 'VQ(' root_name= CAMI_STRING ')' )?
            int alt6=2;
            try { dbg.enterSubRule(6);
            try { dbg.enterDecision(6);

            int LA6_0 = input.LA(1);

            if ( (LA6_0==24) ) {
                alt6=1;
            }
            } finally {dbg.exitDecision(6);}

            switch (alt6) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:216:2: 'VQ(' root_name= CAMI_STRING ')'
                    {
                    dbg.location(216,2);
                    match(input,24,FOLLOW_24_in_receive_services_group442); 
                    dbg.location(216,17);
                    root_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_services_group446); 
                    dbg.location(216,30);
                    match(input,8,FOLLOW_8_in_receive_services_group448); 
                    dbg.location(216,34);


                    }
                    break;

            }
            } finally {dbg.exitSubRule(6);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            receive_services_group_stack.pop();
        }
        dbg.location(218, 2);

        }
        finally {
            dbg.exitRule("receive_services_group");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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

        try { dbg.enterRule("root_description");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(221, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:222:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:223:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            dbg.location(223,2);
            match(input,25,FOLLOW_25_in_root_description468); 
            dbg.location(223,12);
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_root_description472); 
            dbg.location(223,25);
            match(input,10,FOLLOW_10_in_root_description474); 
            dbg.location(223,42);
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description478); 
            dbg.location(223,50);
            match(input,10,FOLLOW_10_in_root_description480); 
            dbg.location(223,71);
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_root_description484); 
            dbg.location(223,78);
            match(input,8,FOLLOW_8_in_root_description485); 
            dbg.location(223,82);

            		((receive_services_group_scope)receive_services_group_stack.peek()).root = CamiObjectBuilder.buildRootMenu(name.getText(), question_type.getText(), question_behavior.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(226, 2);

        }
        finally {
            dbg.exitRule("root_description");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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
        try { dbg.enterRule("service_description");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(229, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:231:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' output_formalism= CAMI_STRING ',' (active_state= NUMBER )? ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:232:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' output_formalism= CAMI_STRING ',' (active_state= NUMBER )? ')'
            {
            dbg.location(232,2);
            match(input,26,FOLLOW_26_in_service_description507); 
            dbg.location(232,19);
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description511); 
            dbg.location(232,32);
            match(input,10,FOLLOW_10_in_service_description513); 
            dbg.location(232,46);
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description517); 
            dbg.location(232,59);
            match(input,10,FOLLOW_10_in_service_description519); 
            dbg.location(233,15);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:15: (question_type= NUMBER )?
            int alt7=2;
            try { dbg.enterSubRule(7);
            try { dbg.enterDecision(7);

            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            } finally {dbg.exitDecision(7);}

            switch (alt7) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:15: question_type= NUMBER
                    {
                    dbg.location(233,15);
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description524); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(7);}

            dbg.location(233,24);
            match(input,10,FOLLOW_10_in_service_description527); 
            dbg.location(233,45);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:45: (question_behavior= NUMBER )?
            int alt8=2;
            try { dbg.enterSubRule(8);
            try { dbg.enterDecision(8);

            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:45: question_behavior= NUMBER
                    {
                    dbg.location(233,45);
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description531); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(233,54);
            match(input,10,FOLLOW_10_in_service_description534); 
            dbg.location(234,10);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:10: (set_item= NUMBER )?
            int alt9=2;
            try { dbg.enterSubRule(9);
            try { dbg.enterDecision(9);

            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:10: set_item= NUMBER
                    {
                    dbg.location(234,10);
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description539); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(9);}

            dbg.location(234,19);
            match(input,10,FOLLOW_10_in_service_description542); 
            dbg.location(234,29);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:29: (dialog= NUMBER )?
            int alt10=2;
            try { dbg.enterSubRule(10);
            try { dbg.enterDecision(10);

            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            } finally {dbg.exitDecision(10);}

            switch (alt10) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:29: dialog= NUMBER
                    {
                    dbg.location(234,29);
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description546); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(10);}

            dbg.location(234,38);
            match(input,10,FOLLOW_10_in_service_description549); 
            dbg.location(235,17);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:17: (stop_authorized= NUMBER )?
            int alt11=2;
            try { dbg.enterSubRule(11);
            try { dbg.enterDecision(11);

            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            } finally {dbg.exitDecision(11);}

            switch (alt11) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:17: stop_authorized= NUMBER
                    {
                    dbg.location(235,17);
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description554); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(11);}

            dbg.location(235,26);
            match(input,10,FOLLOW_10_in_service_description557); 
            dbg.location(235,46);
            output_formalism=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_service_description561); 
            dbg.location(235,59);
            match(input,10,FOLLOW_10_in_service_description563); 
            dbg.location(235,75);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:75: (active_state= NUMBER )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:75: active_state= NUMBER
                    {
                    dbg.location(235,75);
                    active_state=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_service_description567); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}

            dbg.location(235,84);
            match(input,8,FOLLOW_8_in_service_description570); 
            dbg.location(235,88);

            	
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

            		// Ajout à la liste des services
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
        dbg.location(259, 2);

        }
        finally {
            dbg.exitRule("service_description");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
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
        try { dbg.enterRule("state_service");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(262, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:264:2: ( 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:265:2: 'TQ(' root_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (message= CAMI_STRING )? ')'
            {
            dbg.location(265,2);
            match(input,27,FOLLOW_27_in_state_service592); 
            dbg.location(265,17);
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service596); 
            dbg.location(265,30);
            match(input,10,FOLLOW_10_in_state_service598); 
            dbg.location(265,47);
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service602); 
            dbg.location(265,60);
            match(input,10,FOLLOW_10_in_state_service604); 
            dbg.location(265,69);
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_state_service608); 
            dbg.location(265,77);
            match(input,10,FOLLOW_10_in_state_service610); 
            dbg.location(265,88);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:265:88: (message= CAMI_STRING )?
            int alt13=2;
            try { dbg.enterSubRule(13);
            try { dbg.enterDecision(13);

            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            } finally {dbg.exitDecision(13);}

            switch (alt13) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:265:88: message= CAMI_STRING
                    {
                    dbg.location(265,88);
                    message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_state_service614); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(13);}

            dbg.location(265,102);
            match(input,8,FOLLOW_8_in_state_service617); 
            dbg.location(265,106);

            		if(state.getText().equals("7") || state.getText().equals("8")) {
            			LOGGER.finest("Reception d'un etat de service");
            			tq.add(root_name.getText());
            			tq.add(question_name.getText());
            			tq.add(state.getText());
            			tq.add(message.getText());
            			
            			// Ajout à la liste des updates
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
        dbg.location(281, 2);

        }
        finally {
            dbg.exitRule("state_service");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end state_service


    // $ANTLR start ko_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:287:1: ko_message : 'KO(1' mess= CAMI_STRING ',' severity= number ')' ;
    public final void ko_message() throws RecognitionException {
        Token mess=null;
        int severity = 0;


        try { dbg.enterRule("ko_message");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(287, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:288:2: ( 'KO(1' mess= CAMI_STRING ',' severity= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:289:2: 'KO(1' mess= CAMI_STRING ',' severity= number ')'
            {
            dbg.location(289,2);
            match(input,28,FOLLOW_28_in_ko_message638); 
            dbg.location(289,13);
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ko_message642); 
            dbg.location(289,26);
            match(input,10,FOLLOW_10_in_ko_message644); 
            dbg.location(289,38);
            pushFollow(FOLLOW_number_in_ko_message648);
            severity=number();
            _fsp--;

            dbg.location(289,46);
            match(input,8,FOLLOW_8_in_ko_message650); 
            dbg.location(289,50);

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
        dbg.location(295, 2);

        }
        finally {
            dbg.exitRule("ko_message");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end ko_message


    // $ANTLR start message_to_user
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:298:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try { dbg.enterRule("message_to_user");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(298, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:299:2: ( trace_message | warning_message | special_message )
            int alt14=3;
            try { dbg.enterDecision(14);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(14);}

            switch (alt14) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:299:4: trace_message
                    {
                    dbg.location(299,4);
                    pushFollow(FOLLOW_trace_message_in_message_to_user665);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:300:4: warning_message
                    {
                    dbg.location(300,4);
                    pushFollow(FOLLOW_warning_message_in_message_to_user670);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:301:4: special_message
                    {
                    dbg.location(301,4);
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
        dbg.location(302, 2);

        }
        finally {
            dbg.exitRule("message_to_user");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end message_to_user


    // $ANTLR start trace_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:305:1: trace_message : 'TR(' message= CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token message=null;

        try { dbg.enterRule("trace_message");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(305, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:306:2: ( 'TR(' message= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:307:2: 'TR(' message= CAMI_STRING ')'
            {
            dbg.location(307,2);
            match(input,29,FOLLOW_29_in_trace_message689); 
            dbg.location(307,15);
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message693); 
            dbg.location(307,28);
            match(input,8,FOLLOW_8_in_trace_message695); 
            dbg.location(307,32);

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
        dbg.location(312, 2);

        }
        finally {
            dbg.exitRule("trace_message");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end trace_message


    // $ANTLR start warning_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:315:1: warning_message : 'WN(' message= CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token message=null;

        try { dbg.enterRule("warning_message");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(315, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:316:2: ( 'WN(' message= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:317:2: 'WN(' message= CAMI_STRING ')'
            {
            dbg.location(317,2);
            match(input,30,FOLLOW_30_in_warning_message711); 
            dbg.location(317,15);
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message715); 
            dbg.location(317,28);
            match(input,8,FOLLOW_8_in_warning_message717); 
            dbg.location(317,32);

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
        dbg.location(322, 2);

        }
        finally {
            dbg.exitRule("warning_message");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end warning_message


    // $ANTLR start special_message
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:325:1: special_message : 'MO(' type= NUMBER ',' message= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token type=null;
        Token message=null;

        try { dbg.enterRule("special_message");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(325, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:326:2: ( 'MO(' type= NUMBER ',' message= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:327:2: 'MO(' type= NUMBER ',' message= CAMI_STRING ')'
            {
            dbg.location(327,2);
            match(input,31,FOLLOW_31_in_special_message733); 
            dbg.location(327,12);
            type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message737); 
            dbg.location(327,20);
            match(input,10,FOLLOW_10_in_special_message739); 
            dbg.location(327,31);
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message743); 
            dbg.location(327,44);
            match(input,8,FOLLOW_8_in_special_message745); 
            dbg.location(327,48);

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
        dbg.location(352, 2);

        }
        finally {
            dbg.exitRule("special_message");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end special_message


    // $ANTLR start receive_results
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:359:1: receive_results : 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= number ')' ( state_service | special_message | warning_message | result )* ;
    public final void receive_results() throws RecognitionException {
        Token root_name=null;
        Token service_name=null;
        int deprecated = 0;


        try { dbg.enterRule("receive_results");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(359, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:360:2: ( 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= number ')' ( state_service | special_message | warning_message | result )* )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:361:2: 'DR()' 'RQ(' root_name= CAMI_STRING ',' service_name= CAMI_STRING ',' deprecated= number ')' ( state_service | special_message | warning_message | result )*
            {
            dbg.location(361,2);
            match(input,32,FOLLOW_32_in_receive_results769); 
            dbg.location(362,2);
            match(input,33,FOLLOW_33_in_receive_results772); 
            dbg.location(362,17);
            root_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results776); 
            dbg.location(362,30);
            match(input,10,FOLLOW_10_in_receive_results778); 
            dbg.location(362,46);
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receive_results782); 
            dbg.location(362,59);
            match(input,10,FOLLOW_10_in_receive_results784); 
            dbg.location(362,73);
            pushFollow(FOLLOW_number_in_receive_results788);
            deprecated=number();
            _fsp--;

            dbg.location(362,81);
            match(input,8,FOLLOW_8_in_receive_results790); 
            dbg.location(362,85);

            dbg.location(363,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:2: ( state_service | special_message | warning_message | result )*
            try { dbg.enterSubRule(15);

            loop15:
            do {
                int alt15=5;
                try { dbg.enterDecision(15);

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

                } finally {dbg.exitDecision(15);}

                switch (alt15) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:363:4: state_service
            	    {
            	    dbg.location(363,4);
            	    pushFollow(FOLLOW_state_service_in_receive_results797);
            	    state_service();
            	    _fsp--;

            	    dbg.location(363,18);


            	    }
            	    break;
            	case 2 :
            	    dbg.enterAlt(2);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:364:4: special_message
            	    {
            	    dbg.location(364,4);
            	    pushFollow(FOLLOW_special_message_in_receive_results804);
            	    special_message();
            	    _fsp--;

            	    dbg.location(364,20);


            	    }
            	    break;
            	case 3 :
            	    dbg.enterAlt(3);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:365:4: warning_message
            	    {
            	    dbg.location(365,4);
            	    pushFollow(FOLLOW_warning_message_in_receive_results811);
            	    warning_message();
            	    _fsp--;

            	    dbg.location(365,20);


            	    }
            	    break;
            	case 4 :
            	    dbg.enterAlt(4);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:366:4: result
            	    {
            	    dbg.location(366,4);
            	    pushFollow(FOLLOW_result_in_receive_results818);
            	    result();
            	    _fsp--;

            	    dbg.location(366,11);


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);
            } finally {dbg.exitSubRule(15);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(368, 2);

        }
        finally {
            dbg.exitRule("receive_results");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end receive_results


    // $ANTLR start result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:371:1: result : 'DE(' set_name= CAMI_STRING ',' set_type= number ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token set_name=null;
        int set_type = 0;


        try { dbg.enterRule("result");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(371, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:372:2: ( 'DE(' set_name= CAMI_STRING ',' set_type= number ')' ( result_body )+ 'FE()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:373:2: 'DE(' set_name= CAMI_STRING ',' set_type= number ')' ( result_body )+ 'FE()'
            {
            dbg.location(373,2);
            match(input,34,FOLLOW_34_in_result840); 
            dbg.location(373,16);
            set_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result844); 
            dbg.location(373,29);
            match(input,10,FOLLOW_10_in_result846); 
            dbg.location(373,41);
            pushFollow(FOLLOW_number_in_result850);
            set_type=number();
            _fsp--;

            dbg.location(373,49);
            match(input,8,FOLLOW_8_in_result852); 
            dbg.location(374,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:374:2: ( result_body )+
            int cnt16=0;
            try { dbg.enterSubRule(16);

            loop16:
            do {
                int alt16=2;
                try { dbg.enterDecision(16);

                int LA16_0 = input.LA(1);

                if ( (LA16_0==34||(LA16_0>=36 && LA16_0<=42)||(LA16_0>=45 && LA16_0<=49)) ) {
                    alt16=1;
                }


                } finally {dbg.exitDecision(16);}

                switch (alt16) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:374:4: result_body
            	    {
            	    dbg.location(374,4);
            	    pushFollow(FOLLOW_result_body_in_result857);
            	    result_body();
            	    _fsp--;

            	    dbg.location(374,16);


            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt16++;
            } while (true);
            } finally {dbg.exitSubRule(16);}

            dbg.location(375,2);
            match(input,35,FOLLOW_35_in_result865); 
            dbg.location(375,9);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(376, 2);

        }
        finally {
            dbg.exitRule("result");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:379:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try { dbg.enterRule("result_body");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(379, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:2: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt17=8;
            try { dbg.enterDecision(17);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(17);}

            switch (alt17) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:4: result
                    {
                    dbg.location(380,4);
                    pushFollow(FOLLOW_result_in_result_body880);
                    result();
                    _fsp--;

                    dbg.location(380,11);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:381:4: textual_result
                    {
                    dbg.location(381,4);
                    pushFollow(FOLLOW_textual_result_in_result_body887);
                    textual_result();
                    _fsp--;

                    dbg.location(381,19);


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:382:4: attribute_change
                    {
                    dbg.location(382,4);
                    pushFollow(FOLLOW_attribute_change_in_result_body894);
                    attribute_change();
                    _fsp--;

                    dbg.location(382,21);


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:383:4: object_designation
                    {
                    dbg.location(383,4);
                    pushFollow(FOLLOW_object_designation_in_result_body901);
                    object_designation();
                    _fsp--;

                    dbg.location(383,23);


                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:384:4: object_outline
                    {
                    dbg.location(384,4);
                    pushFollow(FOLLOW_object_outline_in_result_body908);
                    object_outline();
                    _fsp--;

                    dbg.location(384,19);


                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:385:4: attribute_outline
                    {
                    dbg.location(385,4);
                    pushFollow(FOLLOW_attribute_outline_in_result_body915);
                    attribute_outline();
                    _fsp--;

                    dbg.location(385,22);


                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:386:4: object_creation
                    {
                    dbg.location(386,4);
                    pushFollow(FOLLOW_object_creation_in_result_body922);
                    object_creation();
                    _fsp--;

                    dbg.location(386,20);


                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:387:4: object_deletion
                    {
                    dbg.location(387,4);
                    pushFollow(FOLLOW_object_deletion_in_result_body929);
                    object_deletion();
                    _fsp--;

                    dbg.location(387,20);


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
        dbg.location(388, 2);

        }
        finally {
            dbg.exitRule("result_body");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end result_body


    // $ANTLR start textual_result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:391:1: textual_result : 'RT(' text= CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        Token text=null;

        try { dbg.enterRule("textual_result");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(391, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:392:2: ( 'RT(' text= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:2: 'RT(' text= CAMI_STRING ')'
            {
            dbg.location(393,2);
            match(input,36,FOLLOW_36_in_textual_result946); 
            dbg.location(393,12);
            text=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result950); 
            dbg.location(393,25);
            match(input,8,FOLLOW_8_in_textual_result952); 
            dbg.location(393,29);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(394, 2);

        }
        finally {
            dbg.exitRule("textual_result");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end textual_result


    // $ANTLR start attribute_change
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:397:1: attribute_change : 'WE(' id= number ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token attribute_name=null;
        Token new_value=null;
        int id = 0;


        try { dbg.enterRule("attribute_change");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(397, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:398:2: ( 'WE(' id= number ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:399:2: 'WE(' id= number ',' attribute_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            dbg.location(399,2);
            match(input,37,FOLLOW_37_in_attribute_change969); 
            dbg.location(399,10);
            pushFollow(FOLLOW_number_in_attribute_change973);
            id=number();
            _fsp--;

            dbg.location(399,18);
            match(input,10,FOLLOW_10_in_attribute_change975); 
            dbg.location(399,36);
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change979); 
            dbg.location(399,49);
            match(input,10,FOLLOW_10_in_attribute_change981); 
            dbg.location(399,62);
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change985); 
            dbg.location(399,75);
            match(input,8,FOLLOW_8_in_attribute_change987); 
            dbg.location(399,79);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(400, 2);

        }
        finally {
            dbg.exitRule("attribute_change");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end attribute_change


    // $ANTLR start attribute_outline
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:403:1: attribute_outline : 'MT(' id= number ',' attribute_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token attribute_name=null;
        int id = 0;

        int begin = 0;

        int end = 0;


        try { dbg.enterRule("attribute_outline");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(403, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:404:2: ( 'MT(' id= number ',' attribute_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:2: 'MT(' id= number ',' attribute_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')'
            {
            dbg.location(405,2);
            match(input,38,FOLLOW_38_in_attribute_outline1004); 
            dbg.location(405,10);
            pushFollow(FOLLOW_number_in_attribute_outline1008);
            id=number();
            _fsp--;

            dbg.location(405,18);
            match(input,10,FOLLOW_10_in_attribute_outline1010); 
            dbg.location(405,36);
            attribute_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1014); 
            dbg.location(405,49);
            match(input,10,FOLLOW_10_in_attribute_outline1016); 
            dbg.location(405,58);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:58: (begin= number )?
            int alt18=2;
            try { dbg.enterSubRule(18);
            try { dbg.enterDecision(18);

            int LA18_0 = input.LA(1);

            if ( (LA18_0==NUMBER) ) {
                alt18=1;
            }
            } finally {dbg.exitDecision(18);}

            switch (alt18) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:58: begin= number
                    {
                    dbg.location(405,58);
                    pushFollow(FOLLOW_number_in_attribute_outline1020);
                    begin=number();
                    _fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(18);}

            dbg.location(405,67);
            match(input,10,FOLLOW_10_in_attribute_outline1023); 
            dbg.location(405,74);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:74: (end= number )?
            int alt19=2;
            try { dbg.enterSubRule(19);
            try { dbg.enterDecision(19);

            int LA19_0 = input.LA(1);

            if ( (LA19_0==NUMBER) ) {
                alt19=1;
            }
            } finally {dbg.exitDecision(19);}

            switch (alt19) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:405:74: end= number
                    {
                    dbg.location(405,74);
                    pushFollow(FOLLOW_number_in_attribute_outline1027);
                    end=number();
                    _fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(19);}

            dbg.location(405,83);
            match(input,8,FOLLOW_8_in_attribute_outline1030); 
            dbg.location(405,87);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(406, 2);

        }
        finally {
            dbg.exitRule("attribute_outline");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end attribute_outline


    // $ANTLR start object_designation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:409:1: object_designation : 'RO(' id= number ')' ;
    public final void object_designation() throws RecognitionException {
        int id = 0;


        try { dbg.enterRule("object_designation");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(409, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:410:2: ( 'RO(' id= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:411:2: 'RO(' id= number ')'
            {
            dbg.location(411,2);
            match(input,39,FOLLOW_39_in_object_designation1046); 
            dbg.location(411,10);
            pushFollow(FOLLOW_number_in_object_designation1050);
            id=number();
            _fsp--;

            dbg.location(411,18);
            match(input,8,FOLLOW_8_in_object_designation1052); 
            dbg.location(411,22);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(412, 2);

        }
        finally {
            dbg.exitRule("object_designation");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end object_designation


    // $ANTLR start object_outline
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:415:1: object_outline : 'ME(' id= number ')' ;
    public final void object_outline() throws RecognitionException {
        int id = 0;


        try { dbg.enterRule("object_outline");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(415, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:416:2: ( 'ME(' id= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:417:2: 'ME(' id= number ')'
            {
            dbg.location(417,2);
            match(input,40,FOLLOW_40_in_object_outline1070); 
            dbg.location(417,10);
            pushFollow(FOLLOW_number_in_object_outline1074);
            id=number();
            _fsp--;

            dbg.location(417,18);
            match(input,8,FOLLOW_8_in_object_outline1076); 
            dbg.location(417,22);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(418, 2);

        }
        finally {
            dbg.exitRule("object_outline");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end object_outline


    // $ANTLR start object_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:421:1: object_creation : ( node | box | arc | attribute );
    public final void object_creation() throws RecognitionException {
        try { dbg.enterRule("object_creation");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(421, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:422:2: ( node | box | arc | attribute )
            int alt20=4;
            try { dbg.enterDecision(20);

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

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(20);}

            switch (alt20) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:422:4: node
                    {
                    dbg.location(422,4);
                    pushFollow(FOLLOW_node_in_object_creation1091);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:423:4: box
                    {
                    dbg.location(423,4);
                    pushFollow(FOLLOW_box_in_object_creation1096);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:424:4: arc
                    {
                    dbg.location(424,4);
                    pushFollow(FOLLOW_arc_in_object_creation1101);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:425:4: attribute
                    {
                    dbg.location(425,4);
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
        dbg.location(426, 2);

        }
        finally {
            dbg.exitRule("object_creation");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end object_creation


    // $ANTLR start object_deletion
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:429:1: object_deletion : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );
    public final void object_deletion() throws RecognitionException {
        int id = 0;

        int page_id = 0;


        try { dbg.enterRule("object_deletion");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(429, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:430:2: ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' )
            int alt21=2;
            try { dbg.enterDecision(21);

            int LA21_0 = input.LA(1);

            if ( (LA21_0==41) ) {
                alt21=1;
            }
            else if ( (LA21_0==42) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("429:1: object_deletion : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );", 21, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(21);}

            switch (alt21) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:430:4: 'SU(' id= number ')'
                    {
                    dbg.location(430,4);
                    match(input,41,FOLLOW_41_in_object_deletion1119); 
                    dbg.location(430,12);
                    pushFollow(FOLLOW_number_in_object_deletion1123);
                    id=number();
                    _fsp--;

                    dbg.location(430,20);
                    match(input,8,FOLLOW_8_in_object_deletion1125); 
                    dbg.location(430,24);


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:431:5: 'SI(' page_id= number ',' id= number ')'
                    {
                    dbg.location(431,5);
                    match(input,42,FOLLOW_42_in_object_deletion1133); 
                    dbg.location(431,18);
                    pushFollow(FOLLOW_number_in_object_deletion1137);
                    page_id=number();
                    _fsp--;

                    dbg.location(431,26);
                    match(input,10,FOLLOW_10_in_object_deletion1139); 
                    dbg.location(431,32);
                    pushFollow(FOLLOW_number_in_object_deletion1143);
                    id=number();
                    _fsp--;

                    dbg.location(431,40);
                    match(input,8,FOLLOW_8_in_object_deletion1145); 
                    dbg.location(431,44);


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
        dbg.location(432, 3);

        }
        finally {
            dbg.exitRule("object_deletion");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end object_deletion


    // $ANTLR start model_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:439:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try { dbg.enterRule("model_definition");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(439, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:440:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:441:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            dbg.location(441,2);
            match(input,43,FOLLOW_43_in_model_definition1172); 
            dbg.location(442,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:442:2: ( syntactic | aestetic )
            int alt22=2;
            try { dbg.enterSubRule(22);
            try { dbg.enterDecision(22);

            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=45 && LA22_0<=49)) ) {
                alt22=1;
            }
            else if ( ((LA22_0>=50 && LA22_0<=54)) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("442:2: ( syntactic | aestetic )", 22, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(22);}

            switch (alt22) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:442:4: syntactic
                    {
                    dbg.location(442,4);
                    pushFollow(FOLLOW_syntactic_in_model_definition1177);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:442:16: aestetic
                    {
                    dbg.location(442,16);
                    pushFollow(FOLLOW_aestetic_in_model_definition1181);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }
            } finally {dbg.exitSubRule(22);}

            dbg.location(443,2);
            match(input,44,FOLLOW_44_in_model_definition1186); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(444, 2);

        }
        finally {
            dbg.exitRule("model_definition");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end model_definition


    // $ANTLR start syntactic
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:447:1: syntactic : ( node | box | arc | attribute );
    public final void syntactic() throws RecognitionException {
        try { dbg.enterRule("syntactic");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(447, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:2: ( node | box | arc | attribute )
            int alt23=4;
            try { dbg.enterDecision(23);

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
                    new NoViableAltException("447:1: syntactic : ( node | box | arc | attribute );", 23, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(23);}

            switch (alt23) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:2: node
                    {
                    dbg.location(449,2);
                    pushFollow(FOLLOW_node_in_syntactic1200);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:9: box
                    {
                    dbg.location(449,9);
                    pushFollow(FOLLOW_box_in_syntactic1204);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:15: arc
                    {
                    dbg.location(449,15);
                    pushFollow(FOLLOW_arc_in_syntactic1208);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:21: attribute
                    {
                    dbg.location(449,21);
                    pushFollow(FOLLOW_attribute_in_syntactic1212);
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
        dbg.location(450, 2);

        }
        finally {
            dbg.exitRule("syntactic");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end syntactic


    // $ANTLR start node
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:453:1: node : 'CN(' CAMI_STRING ',' number ')' ;
    public final void node() throws RecognitionException {
        try { dbg.enterRule("node");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(453, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:454:2: ( 'CN(' CAMI_STRING ',' number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:455:2: 'CN(' CAMI_STRING ',' number ')'
            {
            dbg.location(455,2);
            match(input,45,FOLLOW_45_in_node1227); 
            dbg.location(455,8);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node1229); 
            dbg.location(455,20);
            match(input,10,FOLLOW_10_in_node1231); 
            dbg.location(455,24);
            pushFollow(FOLLOW_number_in_node1233);
            number();
            _fsp--;

            dbg.location(455,31);
            match(input,8,FOLLOW_8_in_node1235); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(456, 2);

        }
        finally {
            dbg.exitRule("node");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end node


    // $ANTLR start box
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:459:1: box : 'CB(' CAMI_STRING ',' number ',' number ')' ;
    public final void box() throws RecognitionException {
        try { dbg.enterRule("box");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(459, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:459:5: ( 'CB(' CAMI_STRING ',' number ',' number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:460:2: 'CB(' CAMI_STRING ',' number ',' number ')'
            {
            dbg.location(460,2);
            match(input,46,FOLLOW_46_in_box1248); 
            dbg.location(460,8);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box1250); 
            dbg.location(460,20);
            match(input,10,FOLLOW_10_in_box1252); 
            dbg.location(460,24);
            pushFollow(FOLLOW_number_in_box1254);
            number();
            _fsp--;

            dbg.location(460,31);
            match(input,10,FOLLOW_10_in_box1256); 
            dbg.location(460,35);
            pushFollow(FOLLOW_number_in_box1258);
            number();
            _fsp--;

            dbg.location(460,42);
            match(input,8,FOLLOW_8_in_box1260); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(461, 2);

        }
        finally {
            dbg.exitRule("box");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end box


    // $ANTLR start arc
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:464:1: arc : 'CA(' CAMI_STRING ',' number ',' number ',' number ')' ;
    public final void arc() throws RecognitionException {
        try { dbg.enterRule("arc");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(464, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:464:5: ( 'CA(' CAMI_STRING ',' number ',' number ',' number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:465:2: 'CA(' CAMI_STRING ',' number ',' number ',' number ')'
            {
            dbg.location(465,2);
            match(input,47,FOLLOW_47_in_arc1273); 
            dbg.location(465,8);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc1275); 
            dbg.location(465,20);
            match(input,10,FOLLOW_10_in_arc1277); 
            dbg.location(465,24);
            pushFollow(FOLLOW_number_in_arc1279);
            number();
            _fsp--;

            dbg.location(465,31);
            match(input,10,FOLLOW_10_in_arc1281); 
            dbg.location(465,35);
            pushFollow(FOLLOW_number_in_arc1283);
            number();
            _fsp--;

            dbg.location(465,42);
            match(input,10,FOLLOW_10_in_arc1285); 
            dbg.location(465,46);
            pushFollow(FOLLOW_number_in_arc1287);
            number();
            _fsp--;

            dbg.location(465,53);
            match(input,8,FOLLOW_8_in_arc1289); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(466, 2);

        }
        finally {
            dbg.exitRule("arc");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end arc


    // $ANTLR start attribute
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:469:1: attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );
    public final void attribute() throws RecognitionException {
        try { dbg.enterRule("attribute");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(469, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:470:2: ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' )
            int alt24=2;
            try { dbg.enterDecision(24);

            int LA24_0 = input.LA(1);

            if ( (LA24_0==48) ) {
                alt24=1;
            }
            else if ( (LA24_0==49) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("469:1: attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );", 24, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(24);}

            switch (alt24) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:470:4: 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
                    {
                    dbg.location(470,4);
                    match(input,48,FOLLOW_48_in_attribute1302); 
                    dbg.location(470,10);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1304); 
                    dbg.location(470,22);
                    match(input,10,FOLLOW_10_in_attribute1306); 
                    dbg.location(470,26);
                    pushFollow(FOLLOW_number_in_attribute1308);
                    number();
                    _fsp--;

                    dbg.location(470,33);
                    match(input,10,FOLLOW_10_in_attribute1310); 
                    dbg.location(470,37);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1312); 
                    dbg.location(470,49);
                    match(input,8,FOLLOW_8_in_attribute1314); 

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:471:4: 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
                    {
                    dbg.location(471,4);
                    match(input,49,FOLLOW_49_in_attribute1319); 
                    dbg.location(471,10);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1321); 
                    dbg.location(471,22);
                    match(input,10,FOLLOW_10_in_attribute1323); 
                    dbg.location(471,26);
                    pushFollow(FOLLOW_number_in_attribute1325);
                    number();
                    _fsp--;

                    dbg.location(471,33);
                    match(input,10,FOLLOW_10_in_attribute1327); 
                    dbg.location(471,37);
                    pushFollow(FOLLOW_number_in_attribute1329);
                    number();
                    _fsp--;

                    dbg.location(471,44);
                    match(input,10,FOLLOW_10_in_attribute1331); 
                    dbg.location(471,48);
                    pushFollow(FOLLOW_number_in_attribute1333);
                    number();
                    _fsp--;

                    dbg.location(471,55);
                    match(input,10,FOLLOW_10_in_attribute1335); 
                    dbg.location(471,59);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute1337); 
                    dbg.location(471,71);
                    match(input,8,FOLLOW_8_in_attribute1339); 

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
        dbg.location(472, 2);

        }
        finally {
            dbg.exitRule("attribute");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end attribute


    // $ANTLR start aestetic
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:475:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try { dbg.enterRule("aestetic");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(475, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:476:2: ( object_position | text_position | intermediary_point )
            int alt25=3;
            try { dbg.enterDecision(25);

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
                    new NoViableAltException("475:1: aestetic : ( object_position | text_position | intermediary_point );", 25, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(25);}

            switch (alt25) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:477:2: object_position
                    {
                    dbg.location(477,2);
                    pushFollow(FOLLOW_object_position_in_aestetic1353);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:477:20: text_position
                    {
                    dbg.location(477,20);
                    pushFollow(FOLLOW_text_position_in_aestetic1357);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:477:36: intermediary_point
                    {
                    dbg.location(477,36);
                    pushFollow(FOLLOW_intermediary_point_in_aestetic1361);
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
        dbg.location(478, 2);

        }
        finally {
            dbg.exitRule("aestetic");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end aestetic


    // $ANTLR start object_position
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:481:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );
    public final void object_position() throws RecognitionException {
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;

        int left = 0;

        int right = 0;

        int top = 0;

        int bottom = 0;


        try { dbg.enterRule("object_position");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(481, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:482:2: ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' )
            int alt26=3;
            try { dbg.enterDecision(26);

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
                    new NoViableAltException("481:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );", 26, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(26);}

            switch (alt26) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:482:4: 'PO(' id= number ',' h_distance= number ',' v_distance= number ')'
                    {
                    dbg.location(482,4);
                    match(input,50,FOLLOW_50_in_object_position1374); 
                    dbg.location(482,12);
                    pushFollow(FOLLOW_number_in_object_position1378);
                    id=number();
                    _fsp--;

                    dbg.location(482,20);
                    match(input,10,FOLLOW_10_in_object_position1380); 
                    dbg.location(482,34);
                    pushFollow(FOLLOW_number_in_object_position1384);
                    h_distance=number();
                    _fsp--;

                    dbg.location(482,42);
                    match(input,10,FOLLOW_10_in_object_position1386); 
                    dbg.location(482,56);
                    pushFollow(FOLLOW_number_in_object_position1390);
                    v_distance=number();
                    _fsp--;

                    dbg.location(482,64);
                    match(input,8,FOLLOW_8_in_object_position1392); 

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:483:4: 'pO(' id= number ',' h_distance= number ',' v_distance= number ')'
                    {
                    dbg.location(483,4);
                    match(input,51,FOLLOW_51_in_object_position1397); 
                    dbg.location(483,12);
                    pushFollow(FOLLOW_number_in_object_position1401);
                    id=number();
                    _fsp--;

                    dbg.location(483,20);
                    match(input,10,FOLLOW_10_in_object_position1403); 
                    dbg.location(483,34);
                    pushFollow(FOLLOW_number_in_object_position1407);
                    h_distance=number();
                    _fsp--;

                    dbg.location(483,42);
                    match(input,10,FOLLOW_10_in_object_position1409); 
                    dbg.location(483,56);
                    pushFollow(FOLLOW_number_in_object_position1413);
                    v_distance=number();
                    _fsp--;

                    dbg.location(483,64);
                    match(input,8,FOLLOW_8_in_object_position1415); 

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:484:4: 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')'
                    {
                    dbg.location(484,4);
                    match(input,52,FOLLOW_52_in_object_position1420); 
                    dbg.location(484,15);
                    pushFollow(FOLLOW_number_in_object_position1424);
                    id=number();
                    _fsp--;

                    dbg.location(484,23);
                    match(input,10,FOLLOW_10_in_object_position1426); 
                    dbg.location(484,31);
                    pushFollow(FOLLOW_number_in_object_position1430);
                    left=number();
                    _fsp--;

                    dbg.location(484,39);
                    match(input,10,FOLLOW_10_in_object_position1432); 
                    dbg.location(484,48);
                    pushFollow(FOLLOW_number_in_object_position1436);
                    right=number();
                    _fsp--;

                    dbg.location(484,56);
                    match(input,10,FOLLOW_10_in_object_position1438); 
                    dbg.location(484,63);
                    pushFollow(FOLLOW_number_in_object_position1442);
                    top=number();
                    _fsp--;

                    dbg.location(484,71);
                    match(input,10,FOLLOW_10_in_object_position1444); 
                    dbg.location(484,81);
                    pushFollow(FOLLOW_number_in_object_position1448);
                    bottom=number();
                    _fsp--;

                    dbg.location(484,88);
                    match(input,8,FOLLOW_8_in_object_position1449); 

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
        dbg.location(485, 2);

        }
        finally {
            dbg.exitRule("object_position");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end object_position


    // $ANTLR start text_position
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:488:1: text_position : 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' ;
    public final void text_position() throws RecognitionException {
        Token name_attr=null;
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;


        try { dbg.enterRule("text_position");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(488, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:489:2: ( 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:490:2: 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')'
            {
            dbg.location(490,2);
            match(input,53,FOLLOW_53_in_text_position1464); 
            dbg.location(490,10);
            pushFollow(FOLLOW_number_in_text_position1468);
            id=number();
            _fsp--;

            dbg.location(490,18);
            match(input,10,FOLLOW_10_in_text_position1470); 
            dbg.location(490,31);
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position1474); 
            dbg.location(490,44);
            match(input,10,FOLLOW_10_in_text_position1476); 
            dbg.location(490,58);
            pushFollow(FOLLOW_number_in_text_position1480);
            h_distance=number();
            _fsp--;

            dbg.location(490,66);
            match(input,10,FOLLOW_10_in_text_position1482); 
            dbg.location(490,80);
            pushFollow(FOLLOW_number_in_text_position1486);
            v_distance=number();
            _fsp--;

            dbg.location(490,88);
            match(input,8,FOLLOW_8_in_text_position1488); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(491, 2);

        }
        finally {
            dbg.exitRule("text_position");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end text_position


    // $ANTLR start intermediary_point
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:494:1: intermediary_point : 'PI(' number ',' number ',' number ')' ;
    public final void intermediary_point() throws RecognitionException {
        try { dbg.enterRule("intermediary_point");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(494, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:495:2: ( 'PI(' number ',' number ',' number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:496:2: 'PI(' number ',' number ',' number ')'
            {
            dbg.location(496,2);
            match(input,54,FOLLOW_54_in_intermediary_point1503); 
            dbg.location(496,8);
            pushFollow(FOLLOW_number_in_intermediary_point1505);
            number();
            _fsp--;

            dbg.location(496,15);
            match(input,10,FOLLOW_10_in_intermediary_point1507); 
            dbg.location(496,19);
            pushFollow(FOLLOW_number_in_intermediary_point1509);
            number();
            _fsp--;

            dbg.location(496,26);
            match(input,10,FOLLOW_10_in_intermediary_point1511); 
            dbg.location(496,30);
            pushFollow(FOLLOW_number_in_intermediary_point1513);
            number();
            _fsp--;

            dbg.location(496,37);
            match(input,8,FOLLOW_8_in_intermediary_point1515); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(497, 2);

        }
        finally {
            dbg.exitRule("intermediary_point");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end intermediary_point


    // $ANTLR start dialog_definition
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:504:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        try { dbg.enterRule("dialog_definition");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(504, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:505:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:506:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            dbg.location(506,2);
            match(input,55,FOLLOW_55_in_dialog_definition1538); 
            dbg.location(507,2);
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition1541);
            dialog_creation();
            _fsp--;

            dbg.location(508,2);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:2: ( next_dialog )+
            int cnt27=0;
            try { dbg.enterSubRule(27);

            loop27:
            do {
                int alt27=2;
                try { dbg.enterDecision(27);

                int LA27_0 = input.LA(1);

                if ( (LA27_0==58) ) {
                    alt27=1;
                }


                } finally {dbg.exitDecision(27);}

                switch (alt27) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:4: next_dialog
            	    {
            	    dbg.location(508,4);
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition1546);
            	    next_dialog();
            	    _fsp--;

            	    dbg.location(508,16);
            	     

            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        dbg.recognitionException(eee);

                        throw eee;
                }
                cnt27++;
            } while (true);
            } finally {dbg.exitSubRule(27);}

            dbg.location(510,2);
            match(input,56,FOLLOW_56_in_dialog_definition1556); 
            dbg.location(510,9);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(511, 2);

        }
        finally {
            dbg.exitRule("dialog_definition");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end dialog_definition


    // $ANTLR start dialog_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:514:1: dialog_creation : 'CE(' dialog_id= number ',' dialog_type= number ',' buttons_type= number ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= number ',' line_type= number ',' (default_value= CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        Token window_title=null;
        Token help=null;
        Token title_or_message=null;
        Token default_value=null;
        int dialog_id = 0;

        int dialog_type = 0;

        int buttons_type = 0;

        int input_type = 0;

        int line_type = 0;


        try { dbg.enterRule("dialog_creation");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(514, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:515:2: ( 'CE(' dialog_id= number ',' dialog_type= number ',' buttons_type= number ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= number ',' line_type= number ',' (default_value= CAMI_STRING )? ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:516:2: 'CE(' dialog_id= number ',' dialog_type= number ',' buttons_type= number ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= number ',' line_type= number ',' (default_value= CAMI_STRING )? ')'
            {
            dbg.location(516,2);
            match(input,57,FOLLOW_57_in_dialog_creation1572); 
            dbg.location(516,17);
            pushFollow(FOLLOW_number_in_dialog_creation1576);
            dialog_id=number();
            _fsp--;

            dbg.location(516,25);
            match(input,10,FOLLOW_10_in_dialog_creation1578); 
            dbg.location(516,40);
            pushFollow(FOLLOW_number_in_dialog_creation1582);
            dialog_type=number();
            _fsp--;

            dbg.location(516,48);
            match(input,10,FOLLOW_10_in_dialog_creation1584); 
            dbg.location(517,14);
            pushFollow(FOLLOW_number_in_dialog_creation1590);
            buttons_type=number();
            _fsp--;

            dbg.location(517,22);
            match(input,10,FOLLOW_10_in_dialog_creation1592); 
            dbg.location(517,39);
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1597); 
            dbg.location(517,52);
            match(input,10,FOLLOW_10_in_dialog_creation1599); 
            dbg.location(518,6);
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1605); 
            dbg.location(518,19);
            match(input,10,FOLLOW_10_in_dialog_creation1607); 
            dbg.location(518,39);
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1611); 
            dbg.location(518,52);
            match(input,10,FOLLOW_10_in_dialog_creation1613); 
            dbg.location(519,12);
            pushFollow(FOLLOW_number_in_dialog_creation1619);
            input_type=number();
            _fsp--;

            dbg.location(519,20);
            match(input,10,FOLLOW_10_in_dialog_creation1621); 
            dbg.location(519,33);
            pushFollow(FOLLOW_number_in_dialog_creation1625);
            line_type=number();
            _fsp--;

            dbg.location(519,41);
            match(input,10,FOLLOW_10_in_dialog_creation1627); 
            dbg.location(520,15);
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:520:15: (default_value= CAMI_STRING )?
            int alt28=2;
            try { dbg.enterSubRule(28);
            try { dbg.enterDecision(28);

            int LA28_0 = input.LA(1);

            if ( (LA28_0==CAMI_STRING) ) {
                alt28=1;
            }
            } finally {dbg.exitDecision(28);}

            switch (alt28) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:520:15: default_value= CAMI_STRING
                    {
                    dbg.location(520,15);
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation1633); 

                    }
                    break;

            }
            } finally {dbg.exitSubRule(28);}

            dbg.location(520,29);
            match(input,8,FOLLOW_8_in_dialog_creation1636); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(521, 2);

        }
        finally {
            dbg.exitRule("dialog_creation");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end dialog_creation


    // $ANTLR start next_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:524:1: next_dialog : 'DS(' dialog_id= number ',' line= CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        Token line=null;
        int dialog_id = 0;


        try { dbg.enterRule("next_dialog");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(524, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:525:2: ( 'DS(' dialog_id= number ',' line= CAMI_STRING ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:526:2: 'DS(' dialog_id= number ',' line= CAMI_STRING ')'
            {
            dbg.location(526,2);
            match(input,58,FOLLOW_58_in_next_dialog1650); 
            dbg.location(526,17);
            pushFollow(FOLLOW_number_in_next_dialog1654);
            dialog_id=number();
            _fsp--;

            dbg.location(526,25);
            match(input,10,FOLLOW_10_in_next_dialog1656); 
            dbg.location(526,33);
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog1660); 
            dbg.location(526,46);
            match(input,8,FOLLOW_8_in_next_dialog1662); 
            dbg.location(526,50);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(527, 2);

        }
        finally {
            dbg.exitRule("next_dialog");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end next_dialog


    // $ANTLR start display_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:530:1: display_dialog : 'AD(' dialog_id= number ')' ;
    public final void display_dialog() throws RecognitionException {
        int dialog_id = 0;


        try { dbg.enterRule("display_dialog");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(530, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:531:2: ( 'AD(' dialog_id= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:532:2: 'AD(' dialog_id= number ')'
            {
            dbg.location(532,2);
            match(input,59,FOLLOW_59_in_display_dialog1678); 
            dbg.location(532,17);
            pushFollow(FOLLOW_number_in_display_dialog1682);
            dialog_id=number();
            _fsp--;

            dbg.location(532,25);
            match(input,8,FOLLOW_8_in_display_dialog1684); 
            dbg.location(532,29);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(533, 2);

        }
        finally {
            dbg.exitRule("display_dialog");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end display_dialog


    // $ANTLR start hide_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:536:1: hide_dialog : 'HD(' dialog_id= number ')' ;
    public final void hide_dialog() throws RecognitionException {
        int dialog_id = 0;


        try { dbg.enterRule("hide_dialog");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(536, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:537:2: ( 'HD(' dialog_id= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:538:2: 'HD(' dialog_id= number ')'
            {
            dbg.location(538,2);
            match(input,60,FOLLOW_60_in_hide_dialog1700); 
            dbg.location(538,17);
            pushFollow(FOLLOW_number_in_hide_dialog1704);
            dialog_id=number();
            _fsp--;

            dbg.location(538,25);
            match(input,8,FOLLOW_8_in_hide_dialog1706); 
            dbg.location(538,29);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(539, 2);

        }
        finally {
            dbg.exitRule("hide_dialog");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end hide_dialog


    // $ANTLR start destroy_dialog
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:542:1: destroy_dialog : 'DG(' dialog_id= number ')' ;
    public final void destroy_dialog() throws RecognitionException {
        int dialog_id = 0;


        try { dbg.enterRule("destroy_dialog");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(542, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:543:2: ( 'DG(' dialog_id= number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:544:2: 'DG(' dialog_id= number ')'
            {
            dbg.location(544,2);
            match(input,61,FOLLOW_61_in_destroy_dialog1723); 
            dbg.location(544,17);
            pushFollow(FOLLOW_number_in_destroy_dialog1727);
            dialog_id=number();
            _fsp--;

            dbg.location(544,25);
            match(input,8,FOLLOW_8_in_destroy_dialog1729); 
            dbg.location(544,29);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(545, 2);

        }
        finally {
            dbg.exitRule("destroy_dialog");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end destroy_dialog


    // $ANTLR start interactive_response
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:548:1: interactive_response : 'MI(' number ',' number ')' ;
    public final void interactive_response() throws RecognitionException {
        try { dbg.enterRule("interactive_response");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(548, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:2: ( 'MI(' number ',' number ')' )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:550:2: 'MI(' number ',' number ')'
            {
            dbg.location(550,2);
            match(input,62,FOLLOW_62_in_interactive_response1744); 
            dbg.location(550,8);
            pushFollow(FOLLOW_number_in_interactive_response1746);
            number();
            _fsp--;

            dbg.location(550,15);
            match(input,10,FOLLOW_10_in_interactive_response1748); 
            dbg.location(550,19);
            pushFollow(FOLLOW_number_in_interactive_response1750);
            number();
            _fsp--;

            dbg.location(550,26);
            match(input,8,FOLLOW_8_in_interactive_response1752); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(551, 2);

        }
        finally {
            dbg.exitRule("interactive_response");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end interactive_response


    // $ANTLR start number
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:1: number returns [int value] : NUMBER ;
    public final int number() throws RecognitionException {
        int value = 0;

        Token NUMBER1=null;

        try { dbg.enterRule("number");
        if ( ruleLevel==0 ) {dbg.commence();}
        ruleLevel++;
        dbg.location(557, 1);

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:559:2: ( NUMBER )
            dbg.enterAlt(1);

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:560:2: NUMBER
            {
            dbg.location(560,2);
            NUMBER1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_number1776); 
            dbg.location(560,9);
            value = Integer.parseInt(NUMBER1.getText());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(561, 2);

        }
        finally {
            dbg.exitRule("number");
            ruleLevel--;
            if ( ruleLevel==0 ) {dbg.terminate();}
        }

        return value;
    }
    // $ANTLR end number


 

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
    public static final BitSet FOLLOW_number_in_ko_message648 = new BitSet(new long[]{0x0000000000000100L});
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
    public static final BitSet FOLLOW_number_in_receive_results788 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_receive_results790 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_state_service_in_receive_results797 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_special_message_in_receive_results804 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_warning_message_in_receive_results811 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_result_in_receive_results818 = new BitSet(new long[]{0x00000004C8000002L});
    public static final BitSet FOLLOW_34_in_result840 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result844 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_result846 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result850 = new BitSet(new long[]{0x0000000000000100L});
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
    public static final BitSet FOLLOW_number_in_attribute_change973 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_change975 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change979 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_change981 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change985 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute_change987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_attribute_outline1004 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1008 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_outline1010 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1014 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_outline1016 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_number_in_attribute_outline1020 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute_outline1023 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_number_in_attribute_outline1027 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute_outline1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_object_designation1046 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_designation1050 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_designation1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_object_outline1070 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_outline1074 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_outline1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_object_creation1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_object_creation1096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_object_creation1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_object_creation1106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_object_deletion1119 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1123 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_deletion1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_object_deletion1133 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1137 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_deletion1139 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1143 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_deletion1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_model_definition1172 = new BitSet(new long[]{0x007FE00000000000L});
    public static final BitSet FOLLOW_syntactic_in_model_definition1177 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_aestetic_in_model_definition1181 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_model_definition1186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic1204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_in_syntactic1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_node1227 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node1229 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_node1231 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_node1233 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_node1235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_box1248 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box1250 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_box1252 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_box1254 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_box1256 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_box1258 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_box1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_arc1273 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc1275 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_arc1277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_arc1279 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_arc1281 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_arc1283 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_arc1285 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_arc1287 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_arc1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_attribute1302 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1304 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1306 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute1308 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1310 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1312 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_attribute1319 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1321 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1323 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute1325 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1327 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute1329 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1331 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute1333 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_attribute1335 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute1337 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_attribute1339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_position1374 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1378 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1380 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1384 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1386 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1390 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_position1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_position1397 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1401 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1403 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1407 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1409 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1413 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_position1415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_position1420 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1424 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1426 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1430 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1432 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1436 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1438 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1442 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_object_position1444 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position1448 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_object_position1449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_text_position1464 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_text_position1468 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_text_position1470 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position1474 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_text_position1476 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_text_position1480 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_text_position1482 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_text_position1486 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_text_position1488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_intermediary_point1503 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_intermediary_point1505 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_intermediary_point1507 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_intermediary_point1509 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_intermediary_point1511 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_intermediary_point1513 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_intermediary_point1515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_dialog_definition1538 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition1541 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition1546 = new BitSet(new long[]{0x0500000000000000L});
    public static final BitSet FOLLOW_56_in_dialog_definition1556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_dialog_creation1572 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation1576 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1578 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation1582 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1584 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation1590 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1592 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1597 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1599 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1605 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1607 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1611 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1613 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation1619 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1621 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation1625 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_dialog_creation1627 = new BitSet(new long[]{0x0000000000000110L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation1633 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_dialog_creation1636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_next_dialog1650 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_next_dialog1654 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_next_dialog1656 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog1660 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_next_dialog1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_display_dialog1678 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_display_dialog1682 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_display_dialog1684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_hide_dialog1700 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_hide_dialog1704 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_hide_dialog1706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_destroy_dialog1723 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_destroy_dialog1727 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_destroy_dialog1729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_interactive_response1744 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_interactive_response1746 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interactive_response1748 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_interactive_response1750 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_interactive_response1752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_number1776 = new BitSet(new long[]{0x0000000000000002L});

}