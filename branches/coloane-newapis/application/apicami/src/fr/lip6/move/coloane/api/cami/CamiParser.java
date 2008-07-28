// $ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-07-28 18:34:56

	package fr.lip6.move.coloane.api.cami;

	import fr.lip6.move.coloane.api.camiObject.ConnectionInfo;
	import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
	import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
	import fr.lip6.move.coloane.api.interfaces.ISessionController;
	import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
	import fr.lip6.move.coloane.api.observables.ConnectionObservable;
	import fr.lip6.move.coloane.api.observables.DisconnectObservable;
	import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
	import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
	import fr.lip6.move.coloane.api.session.SessionController;
	import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
	import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
	import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
	import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
	import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
	import fr.lip6.move.coloane.interfaces.objects.service.IService;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.logging.Logger;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "NEWLINE", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'FS()'", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'TR('", "'WN('", "'MO('", "'KO(1,'", "'DF(-2,'", "'DR()'", "'<EOF>'", "'RQ('", "'FR('", "'ZA('", "'FE()'", "'DE('", "'DE()'", "'RT('", "'RO('", "'ME('", "'MT('", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'SU('", "'SI('", "'TD('", "'OB('", "'AT('", "'DB()'", "'FB()'", "'PO('", "'pO('", "'DS('", "'CE('", "'FF('", "'DC('", "'AD('", "'CD('", "'DG('"
    };
    public static final int CAMI_STRING=4;
    public static final int FIXED_LENGTH_STRING=7;
    public static final int EOF=-1;
    public static final int NUMBER=5;
    public static final int NEWLINE=6;

        public CamiParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g"; }


    	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");
    	
    	List<String> listOfArgs; /* liste des arguments pour la construction des objets de notification */
    	List<List<String>> camiQuestions; /* liste servant a construire les objets Correspondant aux AQ (questions) */
    	List<List<String>> camiUpdates; /* liste servant a construire les objets Correspondant aux TQ 7 et 8 */

    	Map<String, Object> hashObservable; /* Table de hash des observables */

    	ISessionController sessionControl;
    	ISessionInfo sessionInfo;

    	IDialog dialog;
    	List<String> camiDialog; /* represente une boite de dialogue */
    	Map<Integer,IDialog> dialogs ;
    	
    	/** La liste des menus root transmis */
    	List<ISubMenu> rootMenus = new ArrayList<ISubMenu>();
    	/** La liste des services */
    	List<IService> services = new ArrayList<IService>();
    	
    	/* Constructeur du parser */
    	public CamiParser(TokenStream input, Map<String, Object> hm) {
    		this(input);
    		hashObservable = hm;
    		sessionControl = SessionController.getInstance();
    		dialogs = new HashMap<Integer,IDialog>();
    	}
    	


    // $ANTLR start command
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );
    public final void command() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:70:2: ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception )
            int alt3=14;
            switch ( input.LA(1) ) {
            case 8:
                {
                alt3=1;
                }
                break;
            case 10:
                {
                alt3=2;
                }
                break;
            case 12:
                {
                alt3=3;
                }
                break;
            case 13:
            case 14:
                {
                alt3=4;
                }
                break;
            case 15:
            case 19:
            case 20:
            case 21:
                {
                alt3=4;
                }
                break;
            case 22:
                {
                alt3=5;
                }
                break;
            case 27:
                {
                int LA3_7 = input.LA(2);

                if ( (LA3_7==CAMI_STRING) ) {
                    int LA3_19 = input.LA(3);

                    if ( (LA3_19==11) ) {
                        int LA3_23 = input.LA(4);

                        if ( (LA3_23==CAMI_STRING) ) {
                            int LA3_27 = input.LA(5);

                            if ( (LA3_27==11) ) {
                                int LA3_29 = input.LA(6);

                                if ( ((LA3_29>=28 && LA3_29<=29)) ) {
                                    alt3=6;
                                }
                                else if ( (LA3_29==NUMBER) ) {
                                    alt3=14;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 29, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 27, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 23, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 19, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 7, input);

                    throw nvae;
                }
                }
                break;
            case EOF:
                {
                alt3=6;
                }
                break;
            case 30:
                {
                alt3=7;
                }
                break;
            case 16:
                {
                alt3=8;
                }
                break;
            case 17:
                {
                alt3=9;
                }
                break;
            case 18:
                {
                alt3=10;
                }
                break;
            case 35:
                {
                alt3=11;
                }
                break;
            case 31:
                {
                int LA3_14 = input.LA(2);

                if ( (LA3_14==CAMI_STRING) ) {
                    int LA3_20 = input.LA(3);

                    if ( (LA3_20==9) ) {
                        alt3=12;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 20, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 14, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA3_15 = input.LA(2);

                if ( (LA3_15==CAMI_STRING) ) {
                    int LA3_21 = input.LA(3);

                    if ( (LA3_21==9) ) {
                        alt3=12;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 21, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 15, input);

                    throw nvae;
                }
                }
                break;
            case 33:
                {
                int LA3_16 = input.LA(2);

                if ( (LA3_16==NUMBER) ) {
                    int LA3_22 = input.LA(3);

                    if ( (LA3_22==11) ) {
                        int LA3_26 = input.LA(4);

                        if ( (LA3_26==CAMI_STRING) ) {
                            int LA3_28 = input.LA(5);

                            if ( (LA3_28==9) ) {
                                alt3=12;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 28, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 26, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 22, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 16, input);

                    throw nvae;
                }
                }
                break;
            case 34:
                {
                alt3=13;
                }
                break;
            case NEWLINE:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                {
                alt3=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("69:2: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:2: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command50);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:27: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command54);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:49: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_command58);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:72:4: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command63);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:72:23: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command67);
                    receving_menu();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:73:3: ( update )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:73:3: ( update )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==27) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:73:3: update
                    	    {
                    	    pushFollow(FOLLOW_update_in_command71);
                    	    update();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:74:3: ( end_menu_transmission )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:74:3: ( end_menu_transmission )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==30) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:74:3: end_menu_transmission
                    	    {
                    	    pushFollow(FOLLOW_end_menu_transmission_in_command76);
                    	    end_menu_transmission();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:76:3: ack_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_suspend_current_session_in_command82);
                    ack_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:77:3: ack_resume_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_resume_suspend_current_session_in_command86);
                    ack_resume_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:78:3: ack_close_current_session
                    {
                    pushFollow(FOLLOW_ack_close_current_session_in_command90);
                    ack_close_current_session();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:80:3: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_command95);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 12 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:81:3: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_command99);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 13 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:82:3: brutal_interrupt
                    {
                    pushFollow(FOLLOW_brutal_interrupt_in_command103);
                    brutal_interrupt();
                    _fsp--;


                    }
                    break;
                case 14 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:83:3: result_reception
                    {
                    pushFollow(FOLLOW_result_reception_in_command107);
                    result_reception();
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
    // $ANTLR end command


    // $ANTLR start ack_open_communication
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:87:2: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:88:2: ( 'SC(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:89:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication123); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication125); 
            match(input,9,FOLLOW_9_in_ack_open_communication127); 

            		listOfArgs = new ArrayList<String>();
            		listOfArgs.add(CAMI_STRING1.getText());
            		synchronized (hashObservable) {
            			hashObservable.notify();
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
    // $ANTLR end ack_open_communication


    // $ANTLR start ack_open_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:99:2: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:100:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:101:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection144); 
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection150); 

            		listOfArgs.add(v1.getText());
            	
            match(input,11,FOLLOW_11_in_ack_open_connection156); 
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection162); 

            		listOfArgs.add(v2.getText());
            		synchronized (hashObservable) {
            			hashObservable.notify();
            		}
            		// Construction de l'objet contenant les informations de connexion
            		LOGGER.finest("Fin de la construction des objets de connexion. Transmission...");
            		IConnectionInfo connect = new ConnectionInfo(listOfArgs.get(0), listOfArgs.get(1), listOfArgs.get(2));
            		((ConnectionObservable) hashObservable.get("IConnection")).notifyObservers(connect);
            	
            match(input,9,FOLLOW_9_in_ack_open_connection167); 

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
    // $ANTLR end ack_open_connection


    // $ANTLR start close_connection
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:120:2: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:121:2: ( 'FC()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:122:2: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection183); 

            		((DisconnectObservable) hashObservable.get("IDisconnect")).notifyObservers();
            	

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


    // $ANTLR start ack_open_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:127:2: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:128:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt4=1;
                }
                break;
            case 14:
                {
                alt4=2;
                }
                break;
            case 15:
                {
                alt4=3;
                }
                break;
            case 19:
            case 20:
            case 21:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("127:2: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:129:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session199); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session201); 
                    match(input,9,FOLLOW_9_in_ack_open_session202); 

                    		// Initialisation de la table des menus et des modifications
                    		// TODO: Trouver un autre endroit ?
                    		LOGGER.finest("Creation des tables de menus et de modifications");
                    		camiUpdates = new ArrayList<List<String>>();
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:135:3: 'TD()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session207); 

                    		LOGGER.finest("Reception d'un TD");
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:138:3: 'FA()'
                    {
                    match(input,15,FOLLOW_15_in_ack_open_session212); 

                    		LOGGER.finest("Reception d'un FA");
                    	

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:142:3: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session218);
                    interlocutor_table();
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
    // $ANTLR end ack_open_session


    // $ANTLR start ack_suspend_current_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:147:2: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:148:2: ( 'SS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:149:2: 'SS()'
            {
            match(input,16,FOLLOW_16_in_ack_suspend_current_session238); 

            		// Notifier au sessionController de l'acquittement du SS
            		sessionControl.notifyEndSuspendSession();
            	

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
    // $ANTLR end ack_suspend_current_session


    // $ANTLR start ack_resume_suspend_current_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:156:2: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        Token CAMI_STRING2=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:157:2: ( 'RS(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:158:2: 'RS(' CAMI_STRING ')'
            {
            match(input,17,FOLLOW_17_in_ack_resume_suspend_current_session255); 
            CAMI_STRING2=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session257); 
            match(input,9,FOLLOW_9_in_ack_resume_suspend_current_session259); 

            		sessionControl.notifyEndResumeSession(CAMI_STRING2.getText());
            	

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
    // $ANTLR end ack_resume_suspend_current_session


    // $ANTLR start ack_close_current_session
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:164:2: ack_close_current_session : 'FS()' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:165:2: ( 'FS()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:167:2: 'FS()'
            {
            match(input,18,FOLLOW_18_in_ack_close_current_session278); 

            		sessionControl.notifyEndCloseSession();
            	

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
    // $ANTLR end ack_close_current_session


    // $ANTLR start interlocutor_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:2: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:174:2: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt5=1;
                }
                break;
            case 20:
                {
                alt5=2;
                }
                break;
            case 21:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("173:2: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:175:2: 'TL()'
                    {
                    match(input,19,FOLLOW_19_in_interlocutor_table295); 
                     
                    		LOGGER.finest("Reception d'un TL");
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:179:3: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,20,FOLLOW_20_in_interlocutor_table302); 
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table306); 
                    match(input,11,FOLLOW_11_in_interlocutor_table308); 
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table312); 
                    match(input,11,FOLLOW_11_in_interlocutor_table314); 
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table318); 
                    match(input,11,FOLLOW_11_in_interlocutor_table320); 
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table324); 
                    match(input,9,FOLLOW_9_in_interlocutor_table325); 

                    		listOfArgs = new ArrayList<String>();
                    		listOfArgs.add(service_name.getText());
                    		listOfArgs.add(about_service.getText());
                    		listOfArgs.add(incremental.getText());
                    		listOfArgs.add(new_model.getText());
                    		LOGGER.finest("Fin du parse de VI");
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:187:3: 'FL()'
                    {
                    match(input,21,FOLLOW_21_in_interlocutor_table331); 

                    		sessionInfo = CamiObjectBuilder.buildSessionInfo(listOfArgs);
                    		sessionControl.notifyReceptSessionInfo(sessionInfo);
                    		LOGGER.finest("Fin du parse de FL");
                    	

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
    // $ANTLR end interlocutor_table


    // $ANTLR start receving_menu
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:195:2: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' name= CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        Token name=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:196:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' name= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:197:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' name= CAMI_STRING ')'
            {
            match(input,22,FOLLOW_22_in_receving_menu348); 

            		LOGGER.finest("Creation des tables de menus");
            		// Initialisation de la liste des questions
            		camiQuestions = new ArrayList<List<String>>();
            	
            pushFollow(FOLLOW_menu_name_in_receving_menu352);
            menu_name();
            _fsp--;

            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:203:2: ( question_add )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==26) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:203:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu355);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_receving_menu359); 

            		// Construction du menu racine
            		ISubMenu root = CamiObjectBuilder.buildRootMenu(camiQuestions.get(0));
            		rootMenus.add(root);
            		LOGGER.finest("Construction du RootMenu " + root.getName() + " et ajout a la liste des menu");

            		// Suppression de la description du root et construction de la liste des questions
            		camiQuestions.remove(0);
            		List<IQuestion> questions = CamiObjectBuilder.buildQuestions(camiQuestions);
            		LOGGER.finest("Nombre de questions recues : " + questions.size());
            		
            		// Construction de l'objet menu
            		root = CamiObjectBuilder.buildMenus(root, questions);
            		
            		// Ajout de la liste des services
            		LOGGER.finest("Ajout des services pour le menu " + root.getName());
            		services.addAll(CamiObjectBuilder.buildServices(root, questions));
            	
            match(input,24,FOLLOW_24_in_receving_menu363); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu366); 
            match(input,9,FOLLOW_9_in_receving_menu367); 

            		LOGGER.finest("Affichage du menu " + name.getText());
            	

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
    // $ANTLR end receving_menu


    // $ANTLR start menu_name
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:228:2: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:229:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:230:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,25,FOLLOW_25_in_menu_name384); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name388); 
            match(input,11,FOLLOW_11_in_menu_name390); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name394); 
            match(input,11,FOLLOW_11_in_menu_name396); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name400); 
            match(input,9,FOLLOW_9_in_menu_name402); 
            		
            		// Reception de la description d'une racine de menu
            		List<String> cq = new ArrayList<String>();
            		cq.add(name.getText()); // Nom du menu racine
            		cq.add(question_type.getText()); // Type du menu
            		cq.add(question_behavior.getText()); // Type du comportement

            		LOGGER.finest("Ajout de la description du menu root");
            		camiQuestions.add(cq); // Ajouter au sommet de la liste des AQ
            	

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
    // $ANTLR end menu_name


    // $ANTLR start question_add
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:244:2: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
    public final void question_add() throws RecognitionException {
        Token parent_menu=null;
        Token entry_name=null;
        Token question_type=null;
        Token question_behavior=null;
        Token set_item=null;
        Token dialog=null;
        Token stop_authorized=null;
        Token output_formalism=null;
        Token active=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:245:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:246:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,26,FOLLOW_26_in_question_add420); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add424); 
            match(input,11,FOLLOW_11_in_question_add426); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add430); 
            match(input,11,FOLLOW_11_in_question_add432); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:247:15: (question_type= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:247:15: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add438); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add441); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:247:45: (question_behavior= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:247:45: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add445); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add448); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:10: (set_item= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:10: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add454); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add457); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:30: (dialog= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:30: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add462); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add465); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:58: (stop_authorized= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:248:58: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add469); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add472); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:18: (output_formalism= CAMI_STRING )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==CAMI_STRING) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:18: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add478); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add481); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:42: (active= NUMBER )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NUMBER) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:249:42: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add485); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add488); 


            		LOGGER.finest("Reception d'une question " + entry_name.getText());

            		List<String> aq = new ArrayList<String>();
            		aq.add(parent_menu.getText());
            		aq.add(entry_name.getText());

            		// Le type de la question
            		if (question_type != null) {
            			aq.add(question_type.getText());
            		} else {
            			aq.add(null);
            		}

            		// Le comportement de la question
            		if (question_behavior != null) {
            			aq.add(question_behavior.getText());
            		} else {
            			aq.add(null);
            		}

            		if (set_item != null) {
            			aq.add(set_item.getText());
            		} else {
            			aq.add(null);
            		}

            		// Les boite des dialogue sont-elles possible ?
            		if(dialog != null) {
            			aq.add(dialog.getText());
            		} else {
            			aq.add(null);
            		}

            		// Le service est-il suspensible ?
            		if(stop_authorized != null) {
            			aq.add(stop_authorized.getText());
            		} else {
            			aq.add(null);
            		}

            		// Le formalisme de sortie
            		if(output_formalism != null) {
            			aq.add(output_formalism.getText());
            		} else {
            			aq.add(null);
            		}

            		// La question est-elle visible ou non ?
            		if(active != null) {
            			aq.add(active.getText());
            		} else {
            			aq.add(null);
            		}

            		camiQuestions.add(aq); /* Ajouter a la liste de AQ */
            	

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
    // $ANTLR end question_add


    // $ANTLR start update
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:311:2: update : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' ;
    public final void update() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:312:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:313:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')'
            {
            match(input,27,FOLLOW_27_in_update509); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update513); 
            match(input,11,FOLLOW_11_in_update515); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update519); 
            match(input,11,FOLLOW_11_in_update521); 
            state=(Token)input.LT(1);
            if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update525);    throw mse;
            }

            match(input,11,FOLLOW_11_in_update531); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:313:91: (mess= CAMI_STRING )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==CAMI_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:313:91: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update535); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_update538); 


            		List<String> update = new ArrayList<String>();

            		update.add(service_name.getText());
            		update.add(question_name.getText());
            		update.add(state.getText());

            		// Si c'est un modificateur de menu
            		if(!state.getText().equals("7") && !state.getText().equals("8"))
            		update.add(mess.getText());

            		camiUpdates.add(update);

            		LOGGER.finest("Reception d'un modificateur de menu :" + service_name.getText());
            	

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
    // $ANTLR end update


    // $ANTLR start end_menu_transmission
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:333:2: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER3=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:334:2: ( 'QQ(' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:335:2: 'QQ(' NUMBER ')'
            {
            match(input,30,FOLLOW_30_in_end_menu_transmission563); 
            NUMBER3=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission565); 
            match(input,9,FOLLOW_9_in_end_menu_transmission567); 

            		List<IUpdateMenu> updates;
            		LOGGER.finest("Fin de la transmission d'un menu");
            		if(NUMBER3.getText().equals("3")) {
            			updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
            			((ReceptMenuObservable) hashObservable.get("ISession")).notifyObservers(rootMenus, updates, services);
            			// Nettoyage des updates
            			camiUpdates.clear();
            			sessionControl.notifyEndOpenSession();
            		} else {
            			updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
            			((ReceptMenuObservable) hashObservable.get("ISession")).notifyObservers(null, updates, null);
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
    // $ANTLR end end_menu_transmission


    // $ANTLR start message_to_user
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:353:2: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:354:2: ( trace_message | warning_message | special_message )
            int alt15=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt15=1;
                }
                break;
            case 32:
                {
                alt15=2;
                }
                break;
            case 33:
                {
                alt15=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("353:2: message_to_user : ( trace_message | warning_message | special_message );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:355:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user585);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:355:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user589);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:355:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user593);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:358:2: trace_message : 'TR(' CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token CAMI_STRING4=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:359:2: ( 'TR(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:360:2: 'TR(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message606); 
            CAMI_STRING4=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message608); 
            match(input,9,FOLLOW_9_in_trace_message610); 

            		LOGGER.finest("Reception d'un message de trace");
            		IReceptMessage msg = (IReceptMessage) new ReceptMessage(4,CAMI_STRING4.getText());
            		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:367:2: warning_message : 'WN(' CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token CAMI_STRING5=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:368:2: ( 'WN(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:369:2: 'WN(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message624); 
            CAMI_STRING5=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message626); 
            match(input,9,FOLLOW_9_in_warning_message628); 
              
            		LOGGER.finest("Reception d'un message de warning");
            		IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,CAMI_STRING5.getText());
            		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:376:2: special_message : 'MO(' level= NUMBER ',' mess= CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token level=null;
        Token mess=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:377:2: ( 'MO(' level= NUMBER ',' mess= CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:378:2: 'MO(' level= NUMBER ',' mess= CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message643); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message647); 
            match(input,11,FOLLOW_11_in_special_message649); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message653); 
            match(input,9,FOLLOW_9_in_special_message655); 

            		if ("2".equals(level.getText())) {
            			LOGGER.finest("Reception d'un message MO fatal");
            			((BrutalInterruptObservable) hashObservable.get("IBrutalInterrupt")).notifyObservers(mess.getText());
            		}
            		// TODO : Expliquer ce qu'est le MO ? est-ce vraiment un warning ?
            		LOGGER.finest("Reception d'un message special (MO)");
            		IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,mess.getText());
            		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
            	

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


    // $ANTLR start brutal_interrupt
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:391:2: brutal_interrupt : 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' ;
    public final void brutal_interrupt() throws RecognitionException {
        Token mess=null;
        Token level=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:392:2: ( 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:2: 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')'
            {
            match(input,34,FOLLOW_34_in_brutal_interrupt672); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_brutal_interrupt676); 
            match(input,11,FOLLOW_11_in_brutal_interrupt678); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_brutal_interrupt682); 
            match(input,9,FOLLOW_9_in_brutal_interrupt684); 

            		// TODO : Differencier les KOs (1 2 ou 3)
            		LOGGER.finest("Reception d'un message KO");
            		((BrutalInterruptObservable) hashObservable.get("IBrutalInterrupt")).notifyObservers(mess.getText());
            	

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
    // $ANTLR end brutal_interrupt


    // $ANTLR start ask_for_a_model
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:402:2: ask_for_a_model : 'DF(-2,' NUMBER ',' NUMBER ')' ;
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:403:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:404:2: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,35,FOLLOW_35_in_ask_for_a_model757); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model759); 
            match(input,11,FOLLOW_11_in_ask_for_a_model761); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model763); 
            match(input,9,FOLLOW_9_in_ask_for_a_model765); 

            		System.out.println("je parse le DF");
            		sessionControl.notifyWaitingForModel();
            		//    ((IAskForModelObservable)hashObservable.get("IAskForModel")).notifyObservers();
            	

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
    // $ANTLR end ask_for_a_model


    // $ANTLR start result_reception
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:412:2: result_reception : ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' );
    public final void result_reception() throws RecognitionException {
        Token service_name1=null;
        Token question_name1=null;
        Token num1=null;
        Token service_name2=null;
        Token question_name2=null;
        Token state2=null;
        Token mess2=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:416:2: ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' )
            int alt24=10;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:417:2: 'DR()'
                    {
                    match(input,36,FOLLOW_36_in_result_reception785); 
                     
                    		// initialiser la liste des updates 
                    		//    camiUpdates = new ArrayList<ArrayList<String>>();
                    		System.out.println("je parse DR");
                    		sessionControl.notifyWaitingForResult();
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:423:3: ( '<EOF>' )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:423:3: ( '<EOF>' )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==37) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:423:3: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_result_reception790); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:424:3: 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')'
                    {
                    match(input,38,FOLLOW_38_in_result_reception795); 
                    service_name1=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception799); 
                    match(input,11,FOLLOW_11_in_result_reception801); 
                    question_name1=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception805); 
                    match(input,11,FOLLOW_11_in_result_reception807); 
                    num1=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_result_reception811); 
                    match(input,9,FOLLOW_9_in_result_reception813); 

                    		System.out.println("je parse RQ"); 
                    	

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:427:3: ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')'
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:427:3: ( '<EOF>' )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==37) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:427:3: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_result_reception819); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    match(input,27,FOLLOW_27_in_result_reception823); 
                    service_name2=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception827); 
                    match(input,11,FOLLOW_11_in_result_reception829); 
                    question_name2=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception833); 
                    match(input,11,FOLLOW_11_in_result_reception835); 
                    state2=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_result_reception839); 
                    match(input,11,FOLLOW_11_in_result_reception842); 
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:428:121: (mess2= CAMI_STRING )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==CAMI_STRING) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:428:121: mess2= CAMI_STRING
                            {
                            mess2=(Token)input.LT(1);
                            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception846); 

                            }
                            break;

                    }

                    match(input,9,FOLLOW_9_in_result_reception849); 
                     


                    		if(mess2.getText() != null){
                    			ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(3,mess2.getText());
                    			((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
                    			//  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
                    			System.out.println("je parse TQ2");
                    		}
                    		else
                    		{
                    			//     ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(3,"");
                    			// ((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
                    			//  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
                    			System.out.println("je parse TQ2");  


                    		}

                    	

                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:3: ( result )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:3: ( result )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( ((LA19_0>=41 && LA19_0<=54)) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:3: result
                    	    {
                    	    pushFollow(FOLLOW_result_in_result_reception854);
                    	    result();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:3: ( message_utils )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:3: ( message_utils )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==NEWLINE||(LA20_0>=31 && LA20_0<=33)||LA20_0==40) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:449:3: message_utils
                    	    {
                    	    pushFollow(FOLLOW_message_utils_in_result_reception859);
                    	    message_utils();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:3: ( domaine_table )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:3: ( domaine_table )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==15||(LA21_0>=55 && LA21_0<=57)) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:450:3: domaine_table
                    	    {
                    	    pushFollow(FOLLOW_domaine_table_in_result_reception864);
                    	    domaine_table();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:3: ( dialogue )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:3: ( dialogue )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==NEWLINE||(LA22_0>=62 && LA22_0<=68)) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:3: dialogue
                    	    {
                    	    pushFollow(FOLLOW_dialogue_in_result_reception869);
                    	    dialogue();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;
                case 9 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:3: ( modele )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:3: ( modele )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==58) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:3: modele
                    	    {
                    	    pushFollow(FOLLOW_modele_in_result_reception874);
                    	    modele();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    }
                    break;
                case 10 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:453:3: 'FR(' NUMBER ')'
                    {
                    match(input,39,FOLLOW_39_in_result_reception879); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_result_reception881); 
                    match(input,9,FOLLOW_9_in_result_reception883); 

                    		System.out.println("je parse FR");
                    		sessionControl.notifyEndResult();
                    		//TODO notifier Coloane  de la fin de reception des resultats et envoyer les resultats
                    	

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
    // $ANTLR end result_reception


    // $ANTLR start message_utils
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:460:2: message_utils : ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
    public final void message_utils() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:461:2: ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            int alt25=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt25=1;
                }
                break;
            case 32:
                {
                alt25=2;
                }
                break;
            case 33:
                {
                alt25=3;
                }
                break;
            case NEWLINE:
                {
                alt25=4;
                }
                break;
            case 40:
                {
                alt25=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("460:2: message_utils : ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:462:2: trace_message2
                    {
                    pushFollow(FOLLOW_trace_message2_in_message_utils897);
                    trace_message2();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:463:4: warning_message2
                    {
                    pushFollow(FOLLOW_warning_message2_in_message_utils903);
                    warning_message2();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:464:4: special_message2
                    {
                    pushFollow(FOLLOW_special_message2_in_message_utils909);
                    special_message2();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:465:3: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_message_utils913); 

                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:466:4: 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,40,FOLLOW_40_in_message_utils918); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils919); 
                    match(input,11,FOLLOW_11_in_message_utils921); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils923); 
                    match(input,11,FOLLOW_11_in_message_utils925); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils927); 
                    match(input,11,FOLLOW_11_in_message_utils929); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils931); 
                    match(input,11,FOLLOW_11_in_message_utils933); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils935); 
                    match(input,9,FOLLOW_9_in_message_utils937); 

                    		ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(4,"");
                    		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
                    		System.out.println("je parse ZA");
                    	

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
    // $ANTLR end message_utils


    // $ANTLR start trace_message2
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:473:2: trace_message2 : 'TR(' CAMI_STRING ')' ;
    public final void trace_message2() throws RecognitionException {
        Token CAMI_STRING6=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:2: ( 'TR(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:475:2: 'TR(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message2951); 
            CAMI_STRING6=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message2953); 
            match(input,9,FOLLOW_9_in_trace_message2955); 
             
            		ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(2,CAMI_STRING6.getText());
            		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
            		//  ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers(CAMI_STRING6.getText());
            		System.out.println("je parse le TR");
            	

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
    // $ANTLR end trace_message2


    // $ANTLR start warning_message2
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:483:2: warning_message2 : 'WN(' CAMI_STRING ')' ;
    public final void warning_message2() throws RecognitionException {
        Token CAMI_STRING7=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:484:2: ( 'WN(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:485:2: 'WN(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message2969); 
            CAMI_STRING7=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message2971); 
            match(input,9,FOLLOW_9_in_warning_message2973); 

            		ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(1,CAMI_STRING7.getText());
            		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
            		// ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING7.getText());
            		System.out.println("je parse le WN");
            	

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
    // $ANTLR end warning_message2


    // $ANTLR start special_message2
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:493:2: special_message2 : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message2() throws RecognitionException {
        Token CAMI_STRING8=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:494:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:495:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message2988); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message2990); 
            match(input,11,FOLLOW_11_in_special_message2992); 
            CAMI_STRING8=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message2994); 
            match(input,9,FOLLOW_9_in_special_message2996); 

            		ISpecialMessage msg =(ISpecialMessage) new SpecialMessage(1,CAMI_STRING8.getText());
            		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
            		//  ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING8.getText());            
            		System.out.println("je parse le MO");
            	

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
    // $ANTLR end special_message2


    // $ANTLR start result
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:502:2: result : ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );
    public final void result() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:502:9: ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' )
            int alt28=4;
            switch ( input.LA(1) ) {
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                {
                alt28=1;
                }
                break;
            case 41:
                {
                alt28=2;
                }
                break;
            case 42:
                {
                alt28=3;
                }
                break;
            case 43:
                {
                alt28=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("502:2: result : ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:503:2: ( result_body )+ ( '<EOF>' )*
                    {
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:503:2: ( result_body )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        switch ( input.LA(1) ) {
                        case 44:
                            {
                            alt26=1;
                            }
                            break;
                        case 45:
                            {
                            alt26=1;
                            }
                            break;
                        case 46:
                            {
                            alt26=1;
                            }
                            break;
                        case 47:
                            {
                            alt26=1;
                            }
                            break;
                        case 48:
                            {
                            alt26=1;
                            }
                            break;
                        case 49:
                            {
                            alt26=1;
                            }
                            break;
                        case 50:
                            {
                            alt26=1;
                            }
                            break;
                        case 51:
                            {
                            alt26=1;
                            }
                            break;
                        case 52:
                            {
                            alt26=1;
                            }
                            break;
                        case 53:
                            {
                            alt26=1;
                            }
                            break;
                        case 54:
                            {
                            alt26=1;
                            }
                            break;

                        }

                        switch (alt26) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:503:2: result_body
                    	    {
                    	    pushFollow(FOLLOW_result_body_in_result1008);
                    	    result_body();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt26 >= 1 ) break loop26;
                                EarlyExitException eee =
                                    new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    } while (true);

                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:504:2: ( '<EOF>' )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==37) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:504:2: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_result1012); 

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:505:3: 'FE()'
                    {
                    match(input,41,FOLLOW_41_in_result1017); 

                    		System.out.println("je parse FE"); 
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:3: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')'
                    {
                    match(input,42,FOLLOW_42_in_result1022); 
                    ensemble_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1026); 
                    match(input,11,FOLLOW_11_in_result1028); 
                    ensemble_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_result1032); 
                    match(input,9,FOLLOW_9_in_result1034); 

                    		System.out.println("je parse DE"); 
                    	

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:511:3: 'DE()'
                    {
                    match(input,43,FOLLOW_43_in_result1039); 

                    		System.out.println("je parse DE sans parametre"); 
                    	

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
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:516:2: result_body : ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:517:2: ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt29=6;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt29=1;
                }
                break;
            case 45:
                {
                alt29=2;
                }
                break;
            case 46:
                {
                alt29=3;
                }
                break;
            case 47:
                {
                alt29=4;
                }
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                {
                alt29=5;
                }
                break;
            case 53:
            case 54:
                {
                alt29=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("516:2: result_body : ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:518:2: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1053);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:519:4: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1058);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:520:4: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1063);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:521:4: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1068);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:522:4: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1073);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:523:4: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1078);
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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:526:2: textual_result : 'RT(' CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:527:2: ( 'RT(' CAMI_STRING ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:528:2: 'RT(' CAMI_STRING ')'
            {
            match(input,44,FOLLOW_44_in_textual_result1091); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1093); 
            match(input,9,FOLLOW_9_in_textual_result1095); 

            		System.out.println("je parse RT"); 
            	

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


    // $ANTLR start object_designation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:533:2: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:534:2: ( 'RO(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:535:2: 'RO(' id= NUMBER ')'
            {
            match(input,45,FOLLOW_45_in_object_designation1109); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1113); 
            match(input,9,FOLLOW_9_in_object_designation1115); 

            		System.out.println("je parse RO"); 
            	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:540:2: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:541:2: ( 'ME(' id= NUMBER ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:542:2: 'ME(' id= NUMBER ')'
            {
            match(input,46,FOLLOW_46_in_object_outline1129); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1133); 
            match(input,9,FOLLOW_9_in_object_outline1135); 

            		System.out.println("je parse ME"); 
            	

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


    // $ANTLR start attribute_outline
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:547:2: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:548:2: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:2: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,47,FOLLOW_47_in_attribute_outline1149); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1153); 
            match(input,11,FOLLOW_11_in_attribute_outline1155); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1159); 
            match(input,11,FOLLOW_11_in_attribute_outline1161); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:53: (begin= NUMBER )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==NUMBER) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:53: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1165); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1168); 
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:69: (end= NUMBER )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==NUMBER) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:549:69: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1172); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1175); 

            		System.out.println("je parse MT"); 
            	

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


    // $ANTLR start object_creation
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:554:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void object_creation() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:555:2: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt32=5;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt32=1;
                }
                break;
            case 49:
                {
                alt32=2;
                }
                break;
            case 50:
                {
                alt32=3;
                }
                break;
            case 51:
                {
                alt32=4;
                }
                break;
            case 52:
                {
                alt32=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("554:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:556:2: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_object_creation1189); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1191); 
                    match(input,11,FOLLOW_11_in_object_creation1193); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1195); 
                    match(input,9,FOLLOW_9_in_object_creation1197); 

                    		System.out.println("je parse CN"); 
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:559:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,49,FOLLOW_49_in_object_creation1203); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1205); 
                    match(input,11,FOLLOW_11_in_object_creation1207); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1209); 
                    match(input,11,FOLLOW_11_in_object_creation1211); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1213); 
                    match(input,9,FOLLOW_9_in_object_creation1215); 

                    		System.out.println("je parse CB"); 
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:562:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,50,FOLLOW_50_in_object_creation1221); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1223); 
                    match(input,11,FOLLOW_11_in_object_creation1225); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1227); 
                    match(input,11,FOLLOW_11_in_object_creation1229); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1231); 
                    match(input,11,FOLLOW_11_in_object_creation1233); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1235); 
                    match(input,9,FOLLOW_9_in_object_creation1237); 

                    		System.out.println("je parse CA"); 
                    	

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:565:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,51,FOLLOW_51_in_object_creation1243); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1245); 
                    match(input,11,FOLLOW_11_in_object_creation1247); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1249); 
                    match(input,11,FOLLOW_11_in_object_creation1251); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1253); 
                    match(input,9,FOLLOW_9_in_object_creation1255); 
                     
                    		System.out.println("je parse CT"); 
                    	

                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:568:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,52,FOLLOW_52_in_object_creation1261); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1263); 
                    match(input,11,FOLLOW_11_in_object_creation1265); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1267); 
                    match(input,11,FOLLOW_11_in_object_creation1269); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1271); 
                    match(input,11,FOLLOW_11_in_object_creation1273); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1275); 
                    match(input,11,FOLLOW_11_in_object_creation1277); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1279); 
                    match(input,9,FOLLOW_9_in_object_creation1281); 

                    		System.out.println("je parse CM"); 
                    	

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
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:2: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:574:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==53) ) {
                alt33=1;
            }
            else if ( (LA33_0==54) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("573:2: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:575:2: 'SU(' id= NUMBER ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1295); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1299); 
                    match(input,9,FOLLOW_9_in_object_deletion1301); 

                    		System.out.println("je parse SU"); 
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:578:4: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,54,FOLLOW_54_in_object_deletion1307); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1311); 
                    match(input,11,FOLLOW_11_in_object_deletion1313); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1317); 
                    match(input,9,FOLLOW_9_in_object_deletion1319); 

                    		System.out.println("je parse SI"); 
                    	

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


    // $ANTLR start domaine_table
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:584:2: domaine_table : ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' );
    public final void domaine_table() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:585:2: ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' )
            int alt34=4;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt34=1;
                }
                break;
            case 56:
                {
                alt34=2;
                }
                break;
            case 57:
                {
                alt34=3;
                }
                break;
            case 15:
                {
                alt34=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("584:2: domaine_table : ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' );", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:586:2: 'TD(' CAMI_STRING ')'
                    {
                    match(input,55,FOLLOW_55_in_domaine_table1334); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1336); 
                    match(input,9,FOLLOW_9_in_domaine_table1338); 


                    		System.out.println("je parse le TD dans table domaine");
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:590:3: 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,56,FOLLOW_56_in_domaine_table1343); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1345); 
                    match(input,11,FOLLOW_11_in_domaine_table1347); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1349); 
                    match(input,11,FOLLOW_11_in_domaine_table1351); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1353); 
                    match(input,9,FOLLOW_9_in_domaine_table1355); 


                    		System.out.println("je parse le OB dans table domaine");
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:594:3: 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,57,FOLLOW_57_in_domaine_table1360); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1362); 
                    match(input,11,FOLLOW_11_in_domaine_table1364); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1366); 
                    match(input,11,FOLLOW_11_in_domaine_table1368); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1370); 
                    match(input,11,FOLLOW_11_in_domaine_table1372); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1374); 
                    match(input,11,FOLLOW_11_in_domaine_table1376); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1378); 
                    match(input,9,FOLLOW_9_in_domaine_table1380); 


                    		System.out.println("je parse le AT dans table domaine");
                    	

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:598:3: 'FA()'
                    {
                    match(input,15,FOLLOW_15_in_domaine_table1385); 


                    		System.out.println("je parse le FA dans table domaine");
                    	

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
    // $ANTLR end domaine_table


    // $ANTLR start modele
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:603:2: modele : 'DB()' ( modele )* 'FB()' ;
    public final void modele() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:604:2: ( 'DB()' ( modele )* 'FB()' )
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:605:2: 'DB()' ( modele )* 'FB()'
            {
            match(input,58,FOLLOW_58_in_modele1398); 

            		System.out.println("je parse BD"); 
            	
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:608:2: ( modele )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==58) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:608:2: modele
            	    {
            	    pushFollow(FOLLOW_modele_in_modele1402);
            	    modele();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            match(input,59,FOLLOW_59_in_modele1406); 

            		System.out.println("je parse FB"); 
            	

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
    // $ANTLR end modele


    // $ANTLR start modele2
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:614:2: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
    public final void modele2() throws RecognitionException {
        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:615:2: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            int alt36=7;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt36=1;
                }
                break;
            case 49:
                {
                alt36=2;
                }
                break;
            case 50:
                {
                alt36=3;
                }
                break;
            case 51:
                {
                alt36=4;
                }
                break;
            case 52:
                {
                alt36=5;
                }
                break;
            case 60:
                {
                alt36=6;
                }
                break;
            case 61:
                {
                alt36=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("614:2: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:616:2: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_modele21420); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21422); 
                    match(input,11,FOLLOW_11_in_modele21424); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21426); 
                    match(input,9,FOLLOW_9_in_modele21428); 

                    		System.out.println("je parse CN"); 
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:619:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,49,FOLLOW_49_in_modele21434); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21436); 
                    match(input,11,FOLLOW_11_in_modele21438); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21440); 
                    match(input,11,FOLLOW_11_in_modele21442); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21444); 
                    match(input,9,FOLLOW_9_in_modele21446); 

                    		System.out.println("je parse CB"); 
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:622:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,50,FOLLOW_50_in_modele21452); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21454); 
                    match(input,11,FOLLOW_11_in_modele21456); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21458); 
                    match(input,11,FOLLOW_11_in_modele21460); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21462); 
                    match(input,11,FOLLOW_11_in_modele21464); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21466); 
                    match(input,9,FOLLOW_9_in_modele21468); 

                    		System.out.println("je parse CA"); 
                    	

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:625:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,51,FOLLOW_51_in_modele21474); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21476); 
                    match(input,11,FOLLOW_11_in_modele21478); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21480); 
                    match(input,11,FOLLOW_11_in_modele21482); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21484); 
                    match(input,9,FOLLOW_9_in_modele21486); 
                     
                    		System.out.println("je parse CT"); 
                    	

                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:628:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,52,FOLLOW_52_in_modele21492); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21494); 
                    match(input,11,FOLLOW_11_in_modele21496); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21498); 
                    match(input,11,FOLLOW_11_in_modele21500); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21502); 
                    match(input,11,FOLLOW_11_in_modele21504); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21506); 
                    match(input,11,FOLLOW_11_in_modele21508); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21510); 
                    match(input,9,FOLLOW_9_in_modele21512); 

                    		System.out.println("je parse CM"); 
                    	

                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:631:3: 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,60,FOLLOW_60_in_modele21517); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21518); 
                    match(input,11,FOLLOW_11_in_modele21520); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21522); 
                    match(input,11,FOLLOW_11_in_modele21524); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21526); 
                    match(input,11,FOLLOW_11_in_modele21528); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21530); 
                    match(input,9,FOLLOW_9_in_modele21532); 

                    		System.out.println("je parse PO");
                    	

                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:634:3: 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,61,FOLLOW_61_in_modele21537); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21538); 
                    match(input,11,FOLLOW_11_in_modele21540); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21542); 
                    match(input,11,FOLLOW_11_in_modele21544); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21546); 
                    match(input,11,FOLLOW_11_in_modele21548); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21550); 
                    match(input,11,FOLLOW_11_in_modele21552); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21554); 
                    match(input,9,FOLLOW_9_in_modele21556); 

                    		System.out.println("je parse pO");
                    	

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
    // $ANTLR end modele2


    // $ANTLR start dialogue
    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:639:2: dialogue : ( 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' | 'DC(' | 'AD(' dialog_id= NUMBER ')' | 'CD(' dialog_id= NUMBER ')' | 'DG(' dialog_id= NUMBER ')' );
    public final void dialogue() throws RecognitionException {
        Token dialog_id=null;
        Token line_number=null;
        Token dialog_type=null;
        Token buttons_type=null;
        Token window_title=null;
        Token help=null;
        Token title_or_message=null;
        Token input_type=null;
        Token line_type=null;
        Token default_value=null;

        try {
            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:640:2: ( 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' | 'DC(' | 'AD(' dialog_id= NUMBER ')' | 'CD(' dialog_id= NUMBER ')' | 'DG(' dialog_id= NUMBER ')' )
            int alt38=8;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt38=1;
                }
                break;
            case 63:
                {
                alt38=2;
                }
                break;
            case NEWLINE:
                {
                alt38=3;
                }
                break;
            case 64:
                {
                alt38=4;
                }
                break;
            case 65:
                {
                alt38=5;
                }
                break;
            case 66:
                {
                alt38=6;
                }
                break;
            case 67:
                {
                alt38=7;
                }
                break;
            case 68:
                {
                alt38=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("639:2: dialogue : ( 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' | 'DC(' | 'AD(' dialog_id= NUMBER ')' | 'CD(' dialog_id= NUMBER ')' | 'DG(' dialog_id= NUMBER ')' );", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:641:2: 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')'
                    {
                    match(input,62,FOLLOW_62_in_dialogue1570); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1573); 
                    match(input,11,FOLLOW_11_in_dialogue1575); 
                    line_number=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1579); 
                    match(input,9,FOLLOW_9_in_dialogue1581); 

                    		camiDialog.add(line_number.getText());
                    		System.out.println("je parse DS"); 
                    	

                    }
                    break;
                case 2 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:645:4: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
                    {
                    match(input,63,FOLLOW_63_in_dialogue1587); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1591); 
                    match(input,11,FOLLOW_11_in_dialogue1593); 
                    dialog_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1597); 
                    match(input,11,FOLLOW_11_in_dialogue1599); 
                    buttons_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1603); 
                    match(input,11,FOLLOW_11_in_dialogue1605); 
                    window_title=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1610); 
                    match(input,11,FOLLOW_11_in_dialogue1612); 
                    help=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1616); 
                    match(input,11,FOLLOW_11_in_dialogue1618); 
                    title_or_message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1634); 
                    match(input,11,FOLLOW_11_in_dialogue1636); 
                    input_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1640); 
                    match(input,11,FOLLOW_11_in_dialogue1642); 
                    line_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1646); 
                    match(input,11,FOLLOW_11_in_dialogue1648); 
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:645:230: (default_value= CAMI_STRING )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==CAMI_STRING) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:645:230: default_value= CAMI_STRING
                            {
                            default_value=(Token)input.LT(1);
                            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1652); 

                            }
                            break;

                    }

                    match(input,9,FOLLOW_9_in_dialogue1655); 



                    		camiDialog.add(dialog_id.getText()); 
                    		camiDialog.add(dialog_type.getText()); 
                    		camiDialog.add(buttons_type.getText()); 
                    		camiDialog.add(window_title.getText()); 
                    		camiDialog.add(help.getText()); 
                    		camiDialog.add(title_or_message.getText()); 
                    		camiDialog.add(input_type.getText());
                    		camiDialog.add(line_type.getText());

                    		if(default_value != null)
                    		camiDialog.add(default_value.getText()); 
                    		else
                    		camiDialog.add(null/*new String("")*/);

                    		System.out.println("je parse CE"); 
                    	

                    }
                    break;
                case 3 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:664:3: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_dialogue1660); 

                    }
                    break;
                case 4 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:665:3: 'FF('
                    {
                    match(input,64,FOLLOW_64_in_dialogue1664); 
                     
                    		IDialog dialog = (IDialog)CamiObjectBuilder.buildDialog(camiDialog);

                    		dialogs.put((Integer) dialog.getId(), dialog); 

                    		System.out.println("je parse FF");
                    	

                    }
                    break;
                case 5 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:672:3: 'DC('
                    {
                    match(input,65,FOLLOW_65_in_dialogue1669); 

                    		camiDialog = new ArrayList<String>();
                    		System.out.println("je parse DC");
                    	

                    }
                    break;
                case 6 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:676:3: 'AD(' dialog_id= NUMBER ')'
                    {
                    match(input,66,FOLLOW_66_in_dialogue1674); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1677); 
                    match(input,9,FOLLOW_9_in_dialogue1679); 



                    		Integer i = Integer.parseInt(dialog_id.getText());

                    		((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(i),1);
                    		System.out.println("je parse AD");
                    	

                    }
                    break;
                case 7 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:684:3: 'CD(' dialog_id= NUMBER ')'
                    {
                    match(input,67,FOLLOW_67_in_dialogue1684); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1687); 
                    match(input,9,FOLLOW_9_in_dialogue1689); 


                    		Integer j = Integer.parseInt(dialog_id.getText());
                    		((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(j),2);
                    		System.out.println("je parse CD");
                    	

                    }
                    break;
                case 8 :
                    // /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:690:3: 'DG(' dialog_id= NUMBER ')'
                    {
                    match(input,68,FOLLOW_68_in_dialogue1694); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1698); 
                    match(input,9,FOLLOW_9_in_dialogue1700); 

                    		Integer k = Integer.parseInt(dialog_id.getText());
                    		((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(k),3);
                    		dialogs.remove( k);

                    		System.out.println("je parse DG");
                    	

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
    // $ANTLR end dialogue


    protected DFA24 dfa24 = new DFA24(this);
    static final String DFA24_eotS =
        "\15\uffff";
    static final String DFA24_eofS =
        "\1\3\1\uffff\1\3\12\uffff";
    static final String DFA24_minS =
        "\1\6\1\uffff\1\33\12\uffff";
    static final String DFA24_maxS =
        "\1\104\1\uffff\1\45\12\uffff";
    static final String DFA24_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\1\3\1\4\1\5\2\6\1\7\1\10\1\11\1\12";
    static final String DFA24_specialS =
        "\15\uffff}>";
    static final String[] DFA24_transitionS = {
            "\1\10\10\uffff\1\11\13\uffff\1\5\3\uffff\3\7\2\uffff\1\1\1\2"+
            "\1\4\1\14\1\10\16\6\3\11\1\13\3\uffff\7\12",
            "",
            "\1\5\11\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
    static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
    static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
    static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
    static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
    static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
    static final short[][] DFA24_transition;

    static {
        int numStates = DFA24_transitionS.length;
        DFA24_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
        }
    }

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = DFA24_eot;
            this.eof = DFA24_eof;
            this.min = DFA24_min;
            this.max = DFA24_max;
            this.accept = DFA24_accept;
            this.special = DFA24_special;
            this.transition = DFA24_transition;
        }
        public String getDescription() {
            return "412:2: result_reception : ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' );";
        }
    }
 

    public static final BitSet FOLLOW_ack_open_communication_in_command50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_command58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_in_command71 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command76 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ack_suspend_current_session_in_command82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_resume_suspend_current_session_in_command86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_close_current_session_in_command90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_command95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_command99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_brutal_interrupt_in_command103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_reception_in_command107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication125 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection144 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection150 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection156 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection162 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session199 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session201 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ack_open_session212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ack_suspend_current_session238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ack_resume_suspend_current_session255 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session257 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_resume_suspend_current_session259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ack_close_current_session278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_interlocutor_table295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_interlocutor_table302 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table306 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table308 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table312 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table314 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table318 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table320 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table324 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_interlocutor_table331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_receving_menu348 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu352 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu355 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_receving_menu359 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_receving_menu363 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu366 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_menu_name384 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name388 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name390 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name394 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name396 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name400 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_question_add420 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add424 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add426 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add430 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add432 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add438 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add441 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add445 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add448 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add457 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add465 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add469 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add472 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add478 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add481 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add485 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_update509 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update513 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update515 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update519 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update521 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_update525 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update531 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update535 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_update538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_end_menu_transmission563 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission565 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message606 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message608 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message624 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message626 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message643 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message647 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message649 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message653 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_brutal_interrupt672 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_brutal_interrupt676 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_brutal_interrupt678 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_brutal_interrupt682 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_brutal_interrupt684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ask_for_a_model757 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model759 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_a_model761 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model763 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ask_for_a_model765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_result_reception785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_result_reception790 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_38_in_result_reception795 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception799 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception801 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception805 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception807 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception811 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_result_reception819 = new BitSet(new long[]{0x0000002008000000L});
    public static final BitSet FOLLOW_27_in_result_reception823 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception827 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception829 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception833 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception835 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception839 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception842 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception846 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_reception854 = new BitSet(new long[]{0x007FFE0000000002L});
    public static final BitSet FOLLOW_message_utils_in_result_reception859 = new BitSet(new long[]{0x0000010380000042L});
    public static final BitSet FOLLOW_domaine_table_in_result_reception864 = new BitSet(new long[]{0x0380000000008002L});
    public static final BitSet FOLLOW_dialogue_in_result_reception869 = new BitSet(new long[]{0xC000000000000042L,0x000000000000001FL});
    public static final BitSet FOLLOW_modele_in_result_reception874 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_39_in_result_reception879 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception881 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message2_in_message_utils897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message2_in_message_utils903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message2_in_message_utils909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_message_utils913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_message_utils918 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils919 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils921 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils923 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils925 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils927 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils929 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils931 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils933 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils935 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_message_utils937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message2951 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message2953 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message2955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message2969 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message2971 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message2973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message2988 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message2990 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message2992 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message2994 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message2996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_body_in_result1008 = new BitSet(new long[]{0x007FF02000000002L});
    public static final BitSet FOLLOW_37_in_result1012 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_41_in_result1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_result1022 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1026 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1028 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result1032 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_result1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_textual_result1091 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1093 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_object_designation1109 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1113 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_object_outline1129 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1133 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_attribute_outline1149 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1153 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1155 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1159 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1161 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1165 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1168 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1172 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_object_creation1189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1191 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1193 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1195 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_creation1203 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1205 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1207 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1209 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1211 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1213 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_creation1221 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1223 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1225 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1227 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1229 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1231 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1233 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1235 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_creation1243 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1245 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1247 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1249 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1251 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1253 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_creation1261 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1263 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1265 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1267 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1269 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1271 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1273 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1275 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1277 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1279 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1295 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1299 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_object_deletion1307 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1311 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1313 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1317 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_domaine_table1334 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1336 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_domaine_table1343 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1345 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1347 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1349 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1351 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1353 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_domaine_table1360 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1362 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1364 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1366 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1368 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1370 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1372 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1374 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1376 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1378 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_domaine_table1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_modele1398 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_modele_in_modele1402 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_modele1406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_modele21420 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21422 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21424 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21426 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_modele21434 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21436 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21438 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21440 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21442 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21444 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_modele21452 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21456 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21458 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21460 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21464 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21466 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_modele21474 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21476 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21478 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21480 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21482 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21484 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_modele21492 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21494 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21496 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21498 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21500 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21502 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21504 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21506 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21508 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21510 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_modele21517 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21518 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21520 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21522 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21524 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21526 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21528 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21530 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_modele21537 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21538 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21540 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21542 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21544 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21546 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21548 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21550 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21552 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21554 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_dialogue1570 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1573 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1575 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1579 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_dialogue1587 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1591 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1593 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1597 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1599 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1603 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1605 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1610 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1612 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1616 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1618 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1634 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1636 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1640 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1642 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1646 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialogue1648 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1652 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue1655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_dialogue1660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_dialogue1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_dialogue1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_dialogue1674 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1677 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue1679 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_dialogue1684 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1687 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue1689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_dialogue1694 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1698 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue1700 = new BitSet(new long[]{0x0000000000000002L});

}