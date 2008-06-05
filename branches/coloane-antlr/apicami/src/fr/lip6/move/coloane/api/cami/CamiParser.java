// $ANTLR 3.0.1 Cami.g 2008-06-05 14:26:21

package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ITraceMessageObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IWarningObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IAskForModelObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IBrutalInterruptObservable;

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "NEWLINE", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'FS('", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'TR('", "'WN('", "'MO('", "'KO(1,'", "'DF(-2,'", "'DR()'", "'<EOF>'", "'RQ('", "'FR('", "'ZA('", "'FE()'", "'DE('", "'DE()'", "'RT('", "'RO('", "'ME('", "'MT('", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'SU('", "'SI('", "'TD('", "'OB('", "'AT('", "'DB()'", "'FB()'", "'PO('", "'pO('", "'DC('", "'AD('", "'DS('", "'CE('", "'FF('"
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
    public String getGrammarFileName() { return "Cami.g"; }


       ArrayList<String> listOfArgs; /* liste des arguments pour la construction des 
                                        objets de notification */
       
       ArrayList<ArrayList<String>> camiMenuList; /* liste servant à construire les objets 
                                                   Correspondant aux AQ */

       ArrayList<ArrayList<String>> camiUpdates; /* liste servant à construire les objets 
      //                                             Correspondant aux TQ 7 et 8 */

       HashMap<String, Object> hashObservable; /* Table de hash des observables */

       ISessionController sc; /* Controleur de la session */

       IFkInfo fkInfo; 

       IMenu menu;
       ArrayList<IMenu> menuList;

       ArrayList<IUpdateItem> updates;


      
       /* Constructeur du parser */
       public CamiParser(TokenStream input, ISessionController sessionController, 
                                                        HashMap<String, Object> hm) {
           this(input);
           hashObservable = hm;       
           sc = sessionController;
       }



    // $ANTLR start command
    // Cami.g:66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:67:5: ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception )
            int alt2=14;
            switch ( input.LA(1) ) {
            case 8:
                {
                alt2=1;
                }
                break;
            case 10:
                {
                alt2=2;
                }
                break;
            case 12:
                {
                alt2=3;
                }
                break;
            case 13:
            case 14:
                {
                alt2=4;
                }
                break;
            case 15:
            case 19:
            case 20:
            case 21:
                {
                alt2=4;
                }
                break;
            case 22:
                {
                alt2=5;
                }
                break;
            case 27:
                {
                int LA2_7 = input.LA(2);

                if ( (LA2_7==CAMI_STRING) ) {
                    int LA2_19 = input.LA(3);

                    if ( (LA2_19==11) ) {
                        int LA2_23 = input.LA(4);

                        if ( (LA2_23==CAMI_STRING) ) {
                            int LA2_27 = input.LA(5);

                            if ( (LA2_27==11) ) {
                                int LA2_29 = input.LA(6);

                                if ( (LA2_29==NUMBER) ) {
                                    alt2=14;
                                }
                                else if ( ((LA2_29>=28 && LA2_29<=29)) ) {
                                    alt2=6;
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 29, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 27, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 23, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 19, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 7, input);

                    throw nvae;
                }
                }
                break;
            case EOF:
                {
                alt2=6;
                }
                break;
            case 30:
                {
                alt2=7;
                }
                break;
            case 16:
                {
                alt2=8;
                }
                break;
            case 17:
                {
                alt2=9;
                }
                break;
            case 18:
                {
                alt2=10;
                }
                break;
            case 35:
                {
                alt2=11;
                }
                break;
            case 31:
                {
                int LA2_14 = input.LA(2);

                if ( (LA2_14==CAMI_STRING) ) {
                    int LA2_20 = input.LA(3);

                    if ( (LA2_20==9) ) {
                        alt2=12;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 20, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 14, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA2_15 = input.LA(2);

                if ( (LA2_15==CAMI_STRING) ) {
                    int LA2_21 = input.LA(3);

                    if ( (LA2_21==9) ) {
                        alt2=12;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 21, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 15, input);

                    throw nvae;
                }
                }
                break;
            case 33:
                {
                int LA2_16 = input.LA(2);

                if ( (LA2_16==NUMBER) ) {
                    int LA2_22 = input.LA(3);

                    if ( (LA2_22==11) ) {
                        int LA2_26 = input.LA(4);

                        if ( (LA2_26==CAMI_STRING) ) {
                            int LA2_28 = input.LA(5);

                            if ( (LA2_28==9) ) {
                                alt2=12;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 28, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 26, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 22, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 16, input);

                    throw nvae;
                }
                }
                break;
            case 34:
                {
                alt2=13;
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
                {
                alt2=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("66:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:68:5: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command56);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:68:30: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command60);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:68:52: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_command64);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:69:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command72);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:69:26: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command76);
                    receving_menu();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:70:6: ( update )*
                    {
                    // Cami.g:70:6: ( update )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==27) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Cami.g:70:6: update
                    	    {
                    	    pushFollow(FOLLOW_update_in_command83);
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
                    // Cami.g:71:6: end_menu_transmission
                    {
                    pushFollow(FOLLOW_end_menu_transmission_in_command91);
                    end_menu_transmission();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // Cami.g:73:6: ack_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_suspend_current_session_in_command99);
                    ack_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // Cami.g:74:6: ack_resume_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_resume_suspend_current_session_in_command106);
                    ack_resume_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // Cami.g:75:6: ack_close_current_session
                    {
                    pushFollow(FOLLOW_ack_close_current_session_in_command113);
                    ack_close_current_session();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // Cami.g:77:6: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_command121);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 12 :
                    // Cami.g:78:6: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_command128);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 13 :
                    // Cami.g:79:6: brutal_interrupt
                    {
                    pushFollow(FOLLOW_brutal_interrupt_in_command135);
                    brutal_interrupt();
                    _fsp--;


                    }
                    break;
                case 14 :
                    // Cami.g:80:6: result_reception
                    {
                    pushFollow(FOLLOW_result_reception_in_command143);
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
    // Cami.g:86:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:87:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:88:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication162); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication164); 
            match(input,9,FOLLOW_9_in_ack_open_communication166); 

                        listOfArgs = new ArrayList<String>();
                        listOfArgs.add(CAMI_STRING1.getText());
                        synchronized(hashObservable){
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
    // Cami.g:101:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:102:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:103:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection188); 
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection197); 

                        listOfArgs.add(v1.getText());
                    
            match(input,11,FOLLOW_11_in_ack_open_connection206); 
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection215); 
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }
                    
            match(input,9,FOLLOW_9_in_ack_open_connection223); 

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
    // Cami.g:120:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // Cami.g:121:5: ( 'FC()' )
            // Cami.g:122:5: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection248); 

                        /* TODO ON VERRA CE QU'ON FERA ICIIIIIIIIIIIII*/
                    

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
    // Cami.g:127:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:128:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
            int alt3=4;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt3=1;
                }
                break;
            case 14:
                {
                alt3=2;
                }
                break;
            case 15:
                {
                alt3=3;
                }
                break;
            case 19:
            case 20:
            case 21:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("127:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:129:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session265); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session267); 
                    match(input,9,FOLLOW_9_in_ack_open_session268); 

                                //TODO ajouter un controle que c OS
                                System.out.println("je parse le OS");
                                
                                /* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
                                menuList = new ArrayList<IMenu>();
                                /*  */
                                camiUpdates = new ArrayList<ArrayList<String>>();
                            

                    }
                    break;
                case 2 :
                    // Cami.g:138:3: 'TD()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session273); 

                                // Ajouter un controle qu'on doit bien recevoir TD
                               System.out.println("je parse le TD");
                            

                    }
                    break;
                case 3 :
                    // Cami.g:142:3: 'FA()'
                    {
                    match(input,15,FOLLOW_15_in_ack_open_session278); 
                    // Ajouter un controle qu'on doit bien recevoir FA}
                               System.out.println("je parse le FA");
                            

                    }
                    break;
                case 4 :
                    // Cami.g:146:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session295);
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
    // Cami.g:151:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:152:2: ( 'SS()' )
            // Cami.g:153:2: 'SS()'
            {
            match(input,16,FOLLOW_16_in_ack_suspend_current_session327); 
            /* Notifier au sessionController de l'acquittement du SS  */
                        sc.notifyEndSuspendSession();
                    

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
    // Cami.g:161:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        Token CAMI_STRING2=null;

        try {
            // Cami.g:162:2: ( 'RS(' CAMI_STRING ')' )
            // Cami.g:163:2: 'RS(' CAMI_STRING ')'
            {
            match(input,17,FOLLOW_17_in_ack_resume_suspend_current_session346); 
            CAMI_STRING2=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session348); 
            match(input,9,FOLLOW_9_in_ack_resume_suspend_current_session350); 

                        sc.notifyEndResumeSession(CAMI_STRING2.getText());
                    

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
    // Cami.g:170:1: ack_close_current_session : 'FS(' CAMI_STRING ')' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // Cami.g:171:2: ( 'FS(' CAMI_STRING ')' )
            // Cami.g:172:2: 'FS(' CAMI_STRING ')'
            {
            match(input,18,FOLLOW_18_in_ack_close_current_session368); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_close_current_session370); 
            match(input,9,FOLLOW_9_in_ack_close_current_session372); 

                        sc.notifyEndCloseSession();
                    

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
    // Cami.g:181:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:182:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt4=1;
                }
                break;
            case 20:
                {
                alt4=2;
                }
                break;
            case 21:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("181:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:183:5: 'TL()'
                    {
                    match(input,19,FOLLOW_19_in_interlocutor_table396); 
                     
                              System.out.println("je parse le TL");           
                            

                    }
                    break;
                case 2 :
                    // Cami.g:186:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,20,FOLLOW_20_in_interlocutor_table404); 
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table408); 
                    match(input,11,FOLLOW_11_in_interlocutor_table410); 
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table414); 
                    match(input,11,FOLLOW_11_in_interlocutor_table416); 
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table420); 
                    match(input,11,FOLLOW_11_in_interlocutor_table437); 
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table441); 
                    match(input,9,FOLLOW_9_in_interlocutor_table444); 

                               
                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText()); 
                                System.out.println("je parse le VI");           
                            
                            

                    }
                    break;
                case 3 :
                    // Cami.g:197:6: 'FL()'
                    {
                    match(input,21,FOLLOW_21_in_interlocutor_table453); 

                                fkInfo = CamiObjectBuilder.buildFkInfo(listOfArgs);
                                System.out.println("je parse le FL");          
                    //            System.out.println("fkinfo");
                                for(int i=0; i<this.listOfArgs.size(); i++){
                    //              System.out.println(this.listOfArgs.get(i));
                                }

                            

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
    // Cami.g:211:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:212:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:213:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,22,FOLLOW_22_in_receving_menu476); 

                        /* créer la menuList  */
                        camiMenuList = new ArrayList<ArrayList<String>>();
                      System.out.println("je parse le DQ");
                    
            pushFollow(FOLLOW_menu_name_in_receving_menu480);
            menu_name();
            _fsp--;

            // Cami.g:219:2: ( question_add )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==26) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Cami.g:219:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu483);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_receving_menu487); 

                        /* fin de la reception des menus : demander la construction du menu */            
                        menu = CamiObjectBuilder.buildMenu(camiMenuList);

                        System.out.println("nombre de AQ : " + camiMenuList.size());
                      System.out.println("je parse le FQ");
                        menuList.add(menu);
                        
            //            System.out.println("Menu construit");
            //            System.out.println("FQ()");
                    
            match(input,24,FOLLOW_24_in_receving_menu494); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu495); 
            match(input,9,FOLLOW_9_in_receving_menu496); 
             /* afficher les questions */
                        System.out.println("je parse le VQ");
                        
                    

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
    // Cami.g:240:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:241:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:242:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,25,FOLLOW_25_in_menu_name513); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name517); 
            match(input,11,FOLLOW_11_in_menu_name519); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name523); 
            match(input,11,FOLLOW_11_in_menu_name525); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name529); 
            match(input,9,FOLLOW_9_in_menu_name531); 

                 
                        // TODO :  Veifier qu'on est dans la réception de menus racine !!!

                        /* racine des question  */
                        ArrayList<String> cq = new ArrayList<String>();
                        cq.add(name.getText()); /* racine  */
                        cq.add(question_type.getText()); /* type de la question  */
                        cq.add(question_behavior.getText()); /* type du choix */

                        camiMenuList.add(cq); /* ajouter a la liste des AQ */
                                    System.out.println("je parse le CQ");
            //                        System.out.println("name.getText() " + name.getText() );
            //                        System.out.println("question_type.getText() " + question_type.getText());
            //                        System.out.println("question_behavior.getText() " + question_behavior.getText());

                    

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
    // Cami.g:263:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:264:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:265:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,26,FOLLOW_26_in_question_add547); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add551); 
            match(input,11,FOLLOW_11_in_question_add553); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add557); 
            match(input,11,FOLLOW_11_in_question_add559); 
            // Cami.g:266:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:266:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add566); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add569); 
            // Cami.g:266:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:266:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add573); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add576); 
            // Cami.g:267:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:267:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add583); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add586); 
            // Cami.g:267:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:267:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add591); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add594); 
            // Cami.g:267:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:267:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add598); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add601); 
            // Cami.g:268:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:268:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add608); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add611); 
            // Cami.g:268:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:268:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add615); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add618); 

                   System.out.println("je parse le AQ");
                        // TODO Veifier qu'on est dans la réception de menus
                        ArrayList<String> aq = new ArrayList<String>();
                        aq.add(parent_menu.getText()); /* parent  */
                        aq.add(entry_name.getText());  /* entry_name  */
                        
                        if(question_type != null)
                            aq.add(question_type.getText()); /* question_type  */
                        else
                            aq.add(null/*new String()*/);

                        if(question_behavior != null)
                            aq.add(question_behavior.getText()); /* question_behavior  */
                        else
                            aq.add(null/*new String("")*/);

                        if(set_item != null)
                            aq.add(null/*set_item.getText()*/); /* validation par defaut  */
                        else
                            aq.add(null/*new String("")*/);
                        
                        if(dialog != null)
                            aq.add(dialog.getText()); /* dialog autorisé ?  */
                        else
                            aq.add(null/*new String("")*/);


                        if(stop_authorized != null)
                            aq.add(stop_authorized.getText()); /* on autorise l'arrêt du service ? */
                        else
                            aq.add(null/*new String("")*/);


                        if(output_formalism != null)
                            aq.add(output_formalism.getText()); /* formalisme */
                        else
                            aq.add(null/*new String("")*/);


                        if(active != null)
                            aq.add(active.getText()); /* grisé ou non ? */
                        else
                            aq.add(null/*new String("")*/);


                        camiMenuList.add(aq); /* ajouter à la liste de AQ */
                        
                        
                  /* TODO : a enlever */

            //                        System.out.print("AQ(" + aq.get(0));
                                    for(int i=1; i<9; i++){
            //                            System.out.print(", " + aq.get(i));
                                    }
            //                        System.out.println(")");

                    

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
    // Cami.g:330:1: update : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' ;
    public final void update() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:331:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:332:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')'
            {
            match(input,27,FOLLOW_27_in_update639); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update643); 
            match(input,11,FOLLOW_11_in_update645); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update649); 
            match(input,11,FOLLOW_11_in_update651); 
            state=(Token)input.LT(1);
            if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update655);    throw mse;
            }

            match(input,11,FOLLOW_11_in_update661); 
            // Cami.g:332:91: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:332:91: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update665); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_update668); 

                        
                        
                        /*  */
                        ArrayList<String> update = new ArrayList<String>();
                        
                        update.add(service_name.getText()); /* nom du service */
                        update.add(question_name.getText());  /* nom de la question  */
                        update.add(state.getText());  /* état de la question  */
                        
                        if(!state.getText().equals("7") && !state.getText().equals("8")) /* si c'est un modificateur de menu */
                            update.add(mess.getText()); /* message : optionnel */          
                        
                        
                        camiUpdates.add(update);/* ajouter à la liste des updates  */
                        
                        System.out.println("je parse le TQ");
                    

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
    // Cami.g:354:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER3=null;

        try {
            // Cami.g:355:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:356:2: 'QQ(' NUMBER ')'
            {
            match(input,30,FOLLOW_30_in_end_menu_transmission694); 
            NUMBER3=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission696); 
            match(input,9,FOLLOW_9_in_end_menu_transmission698); 

                        
            //            System.out.println("QQ(" + NUMBER3.getText() + ")");
                        
                        
                        updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
                        ((ISessionObservable)hashObservable.get("ISession")).notifyObservers(fkInfo, menuList, updates);

                        /* notifier le sessionController qu'on a reçu les menus et les updates */            
                        sc.notifyEndOpenSession();
                    

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
    // Cami.g:381:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // Cami.g:382:2: ( trace_message | warning_message | special_message )
            int alt14=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt14=1;
                }
                break;
            case 32:
                {
                alt14=2;
                }
                break;
            case 33:
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("381:1: message_to_user : ( trace_message | warning_message | special_message );", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // Cami.g:383:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user728);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:383:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user732);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:383:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user736);
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
    // Cami.g:386:1: trace_message : 'TR(' CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token CAMI_STRING4=null;

        try {
            // Cami.g:387:2: ( 'TR(' CAMI_STRING ')' )
            // Cami.g:388:2: 'TR(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message748); 
            CAMI_STRING4=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message750); 
            match(input,9,FOLLOW_9_in_trace_message752); 

                      ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers(CAMI_STRING4.getText());
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
    // $ANTLR end trace_message


    // $ANTLR start warning_message
    // Cami.g:394:1: warning_message : 'WN(' CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token CAMI_STRING5=null;

        try {
            // Cami.g:395:2: ( 'WN(' CAMI_STRING ')' )
            // Cami.g:396:2: 'WN(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message765); 
            CAMI_STRING5=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message767); 
            match(input,9,FOLLOW_9_in_warning_message769); 

                     ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING5.getText());
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
    // $ANTLR end warning_message


    // $ANTLR start special_message
    // Cami.g:402:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token CAMI_STRING6=null;

        try {
            // Cami.g:403:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:404:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message783); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message785); 
            match(input,11,FOLLOW_11_in_special_message787); 
            CAMI_STRING6=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message789); 
            match(input,9,FOLLOW_9_in_special_message791); 

                      ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING6.getText());            
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
    // $ANTLR end special_message


    // $ANTLR start brutal_interrupt
    // Cami.g:414:1: brutal_interrupt : 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' ;
    public final void brutal_interrupt() throws RecognitionException {
        Token mess=null;
        Token level=null;

        try {
            // Cami.g:415:3: ( 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' )
            // Cami.g:416:3: 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')'
            {
            match(input,34,FOLLOW_34_in_brutal_interrupt811); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_brutal_interrupt815); 
            match(input,11,FOLLOW_11_in_brutal_interrupt817); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_brutal_interrupt821); 
            match(input,9,FOLLOW_9_in_brutal_interrupt823); 

                            System.out.println("je parse le KO");
            	//((IBrutalInterruptObservable)hashObservable.get("IBrutalInterrupt")).notifyObservers(mess.getText());
            		

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
    // Cami.g:424:1: ask_for_a_model : 'DF(-2,' NUMBER ',' NUMBER ')' ;
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // Cami.g:425:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // Cami.g:426:5: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,35,FOLLOW_35_in_ask_for_a_model898); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model900); 
            match(input,11,FOLLOW_11_in_ask_for_a_model902); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model904); 
            match(input,9,FOLLOW_9_in_ask_for_a_model906); 

                     System.out.println("je parse le DF");
                  //  ((IAskForModelObservable)hashObservable.get("IAskForModel")).notifyObservers();
                 

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
    // Cami.g:436:1: result_reception : ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' );
    public final void result_reception() throws RecognitionException {
        Token service_name1=null;
        Token question_name1=null;
        Token num1=null;
        Token service_name2=null;
        Token question_name2=null;
        Token state2=null;
        Token mess2=null;

        try {
            // Cami.g:437:8: ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' )
            int alt23=10;
            alt23 = dfa23.predict(input);
            switch (alt23) {
                case 1 :
                    // Cami.g:438:9: 'DR()'
                    {
                    match(input,36,FOLLOW_36_in_result_reception937); 
                     
                                // initialiser la liste des updates 
                            //    camiUpdates = new ArrayList<ArrayList<String>>();
                              System.out.println("je parse DR");
                            

                    }
                    break;
                case 2 :
                    // Cami.g:443:9: ( '<EOF>' )*
                    {
                    // Cami.g:443:9: ( '<EOF>' )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==37) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // Cami.g:443:9: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_result_reception948); 

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // Cami.g:444:9: 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')'
                    {
                    match(input,38,FOLLOW_38_in_result_reception959); 
                    service_name1=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception963); 
                    match(input,11,FOLLOW_11_in_result_reception965); 
                    question_name1=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception969); 
                    match(input,11,FOLLOW_11_in_result_reception971); 
                    num1=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_result_reception975); 
                    match(input,9,FOLLOW_9_in_result_reception977); 

                            System.out.println("je parse RQ"); 
                            

                    }
                    break;
                case 4 :
                    // Cami.g:447:10: ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')'
                    {
                    // Cami.g:447:10: ( '<EOF>' )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==37) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // Cami.g:447:10: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_result_reception990); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    match(input,27,FOLLOW_27_in_result_reception1001); 
                    service_name2=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception1005); 
                    match(input,11,FOLLOW_11_in_result_reception1007); 
                    question_name2=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception1011); 
                    match(input,11,FOLLOW_11_in_result_reception1013); 
                    state2=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_result_reception1017); 
                    match(input,11,FOLLOW_11_in_result_reception1020); 
                    // Cami.g:448:128: (mess2= CAMI_STRING )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==CAMI_STRING) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // Cami.g:448:128: mess2= CAMI_STRING
                            {
                            mess2=(Token)input.LT(1);
                            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception1024); 

                            }
                            break;

                    }

                    match(input,9,FOLLOW_9_in_result_reception1027); 
                     
                                
                              //  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
                              System.out.println("je parse TQ2");
                              

                    }
                    break;
                case 5 :
                    // Cami.g:453:10: ( result )*
                    {
                    // Cami.g:453:10: ( result )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>=41 && LA18_0<=54)) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // Cami.g:453:10: result
                    	    {
                    	    pushFollow(FOLLOW_result_in_result_reception1039);
                    	    result();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;
                case 6 :
                    // Cami.g:454:10: ( message_utils )*
                    {
                    // Cami.g:454:10: ( message_utils )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==NEWLINE||(LA19_0>=31 && LA19_0<=33)||LA19_0==40) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // Cami.g:454:10: message_utils
                    	    {
                    	    pushFollow(FOLLOW_message_utils_in_result_reception1051);
                    	    message_utils();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }
                    break;
                case 7 :
                    // Cami.g:455:10: ( domaine_table )*
                    {
                    // Cami.g:455:10: ( domaine_table )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==15||(LA20_0>=55 && LA20_0<=57)) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // Cami.g:455:10: domaine_table
                    	    {
                    	    pushFollow(FOLLOW_domaine_table_in_result_reception1063);
                    	    domaine_table();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    }
                    break;
                case 8 :
                    // Cami.g:456:10: ( dialogue )*
                    {
                    // Cami.g:456:10: ( dialogue )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==NEWLINE||(LA21_0>=62 && LA21_0<=66)) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // Cami.g:456:10: dialogue
                    	    {
                    	    pushFollow(FOLLOW_dialogue_in_result_reception1075);
                    	    dialogue();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    }
                    break;
                case 9 :
                    // Cami.g:457:10: ( modele )*
                    {
                    // Cami.g:457:10: ( modele )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==58) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // Cami.g:457:10: modele
                    	    {
                    	    pushFollow(FOLLOW_modele_in_result_reception1087);
                    	    modele();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;
                case 10 :
                    // Cami.g:458:10: 'FR(' NUMBER ')'
                    {
                    match(input,39,FOLLOW_39_in_result_reception1099); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_result_reception1101); 
                    match(input,9,FOLLOW_9_in_result_reception1103); 

                             System.out.println("je parse FR");
                                //TODO notifier Coloane  de la fin de reception des resulstats et envoyer les resultats
                            

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
    // Cami.g:464:1: message_utils : ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
    public final void message_utils() throws RecognitionException {
        try {
            // Cami.g:465:11: ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            int alt24=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt24=1;
                }
                break;
            case 32:
                {
                alt24=2;
                }
                break;
            case 33:
                {
                alt24=3;
                }
                break;
            case NEWLINE:
                {
                alt24=4;
                }
                break;
            case 40:
                {
                alt24=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("464:1: message_utils : ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // Cami.g:466:4: trace_message2
                    {
                    pushFollow(FOLLOW_trace_message2_in_message_utils1126);
                    trace_message2();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:467:13: warning_message2
                    {
                    pushFollow(FOLLOW_warning_message2_in_message_utils1141);
                    warning_message2();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:468:13: special_message2
                    {
                    pushFollow(FOLLOW_special_message2_in_message_utils1156);
                    special_message2();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:469:12: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_message_utils1169); 

                    }
                    break;
                case 5 :
                    // Cami.g:470:13: 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,40,FOLLOW_40_in_message_utils1183); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1184); 
                    match(input,11,FOLLOW_11_in_message_utils1186); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1188); 
                    match(input,11,FOLLOW_11_in_message_utils1190); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1192); 
                    match(input,11,FOLLOW_11_in_message_utils1194); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1196); 
                    match(input,11,FOLLOW_11_in_message_utils1198); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1200); 
                    match(input,9,FOLLOW_9_in_message_utils1202); 

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
    // Cami.g:475:1: trace_message2 : 'TR(' CAMI_STRING ')' ;
    public final void trace_message2() throws RecognitionException {
        Token CAMI_STRING7=null;

        try {
            // Cami.g:476:2: ( 'TR(' CAMI_STRING ')' )
            // Cami.g:477:2: 'TR(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message21214); 
            CAMI_STRING7=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message21216); 
            match(input,9,FOLLOW_9_in_trace_message21218); 

                      ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers(CAMI_STRING7.getText());
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
    // Cami.g:483:1: warning_message2 : 'WN(' CAMI_STRING ')' ;
    public final void warning_message2() throws RecognitionException {
        Token CAMI_STRING8=null;

        try {
            // Cami.g:484:2: ( 'WN(' CAMI_STRING ')' )
            // Cami.g:485:2: 'WN(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message21231); 
            CAMI_STRING8=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message21233); 
            match(input,9,FOLLOW_9_in_warning_message21235); 

                     ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING8.getText());
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
    // Cami.g:491:1: special_message2 : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message2() throws RecognitionException {
        Token CAMI_STRING9=null;

        try {
            // Cami.g:492:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:493:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message21249); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message21251); 
            match(input,11,FOLLOW_11_in_special_message21253); 
            CAMI_STRING9=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message21255); 
            match(input,9,FOLLOW_9_in_special_message21257); 

                      ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING9.getText());            
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
    // Cami.g:498:1: result : ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );
    public final void result() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // Cami.g:498:8: ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' )
            int alt27=4;
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
                alt27=1;
                }
                break;
            case 41:
                {
                alt27=2;
                }
                break;
            case 42:
                {
                alt27=3;
                }
                break;
            case 43:
                {
                alt27=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("498:1: result : ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // Cami.g:500:9: ( result_body )+ ( '<EOF>' )*
                    {
                    // Cami.g:500:9: ( result_body )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        switch ( input.LA(1) ) {
                        case 44:
                            {
                            alt25=1;
                            }
                            break;
                        case 45:
                            {
                            alt25=1;
                            }
                            break;
                        case 46:
                            {
                            alt25=1;
                            }
                            break;
                        case 47:
                            {
                            alt25=1;
                            }
                            break;
                        case 48:
                            {
                            alt25=1;
                            }
                            break;
                        case 49:
                            {
                            alt25=1;
                            }
                            break;
                        case 50:
                            {
                            alt25=1;
                            }
                            break;
                        case 51:
                            {
                            alt25=1;
                            }
                            break;
                        case 52:
                            {
                            alt25=1;
                            }
                            break;
                        case 53:
                            {
                            alt25=1;
                            }
                            break;
                        case 54:
                            {
                            alt25=1;
                            }
                            break;

                        }

                        switch (alt25) {
                    	case 1 :
                    	    // Cami.g:500:9: result_body
                    	    {
                    	    pushFollow(FOLLOW_result_body_in_result1277);
                    	    result_body();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);

                    // Cami.g:501:9: ( '<EOF>' )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==37) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // Cami.g:501:9: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_result1288); 

                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Cami.g:502:3: 'FE()'
                    {
                    match(input,41,FOLLOW_41_in_result1293); 

                             System.out.println("je parse FE"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:505:10: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')'
                    {
                    match(input,42,FOLLOW_42_in_result1305); 
                    ensemble_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1309); 
                    match(input,11,FOLLOW_11_in_result1311); 
                    ensemble_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_result1315); 
                    match(input,9,FOLLOW_9_in_result1317); 

                             System.out.println("je parse DE"); 
                             

                    }
                    break;
                case 4 :
                    // Cami.g:508:10: 'DE()'
                    {
                    match(input,43,FOLLOW_43_in_result1329); 

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
    // Cami.g:513:1: result_body : ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // Cami.g:514:3: ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt28=6;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt28=1;
                }
                break;
            case 45:
                {
                alt28=2;
                }
                break;
            case 46:
                {
                alt28=3;
                }
                break;
            case 47:
                {
                alt28=4;
                }
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                {
                alt28=5;
                }
                break;
            case 53:
            case 54:
                {
                alt28=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("513:1: result_body : ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // Cami.g:515:10: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1351);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:516:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1357);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:517:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1363);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:518:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1369);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:519:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1375);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:520:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1381);
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
    // Cami.g:523:1: textual_result : 'RT(' CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        try {
            // Cami.g:524:3: ( 'RT(' CAMI_STRING ')' )
            // Cami.g:525:3: 'RT(' CAMI_STRING ')'
            {
            match(input,44,FOLLOW_44_in_textual_result1397); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1399); 
            match(input,9,FOLLOW_9_in_textual_result1401); 

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
    // Cami.g:530:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:531:3: ( 'RO(' id= NUMBER ')' )
            // Cami.g:532:3: 'RO(' id= NUMBER ')'
            {
            match(input,45,FOLLOW_45_in_object_designation1417); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1421); 
            match(input,9,FOLLOW_9_in_object_designation1423); 

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
    // Cami.g:537:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:538:3: ( 'ME(' id= NUMBER ')' )
            // Cami.g:539:3: 'ME(' id= NUMBER ')'
            {
            match(input,46,FOLLOW_46_in_object_outline1439); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1443); 
            match(input,9,FOLLOW_9_in_object_outline1445); 

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
    // Cami.g:544:1: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token begin=null;
        Token end=null;

        try {
            // Cami.g:545:7: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // Cami.g:546:3: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,47,FOLLOW_47_in_attribute_outline1465); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1469); 
            match(input,11,FOLLOW_11_in_attribute_outline1471); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1475); 
            match(input,11,FOLLOW_11_in_attribute_outline1477); 
            // Cami.g:546:54: (begin= NUMBER )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==NUMBER) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // Cami.g:546:54: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1481); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1484); 
            // Cami.g:546:70: (end= NUMBER )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==NUMBER) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // Cami.g:546:70: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1488); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1491); 

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
    // Cami.g:551:1: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void object_creation() throws RecognitionException {
        try {
            // Cami.g:552:3: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt31=5;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt31=1;
                }
                break;
            case 49:
                {
                alt31=2;
                }
                break;
            case 50:
                {
                alt31=3;
                }
                break;
            case 51:
                {
                alt31=4;
                }
                break;
            case 52:
                {
                alt31=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("551:1: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // Cami.g:553:4: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_object_creation1508); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1510); 
                    match(input,11,FOLLOW_11_in_object_creation1512); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1514); 
                    match(input,9,FOLLOW_9_in_object_creation1516); 

                             System.out.println("je parse CN"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:556:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,49,FOLLOW_49_in_object_creation1522); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1524); 
                    match(input,11,FOLLOW_11_in_object_creation1526); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1528); 
                    match(input,11,FOLLOW_11_in_object_creation1530); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1532); 
                    match(input,9,FOLLOW_9_in_object_creation1534); 

                            System.out.println("je parse CB"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:559:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,50,FOLLOW_50_in_object_creation1540); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1542); 
                    match(input,11,FOLLOW_11_in_object_creation1544); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1546); 
                    match(input,11,FOLLOW_11_in_object_creation1548); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1550); 
                    match(input,11,FOLLOW_11_in_object_creation1552); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1554); 
                    match(input,9,FOLLOW_9_in_object_creation1556); 

                            System.out.println("je parse CA"); 
                             

                    }
                    break;
                case 4 :
                    // Cami.g:562:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,51,FOLLOW_51_in_object_creation1562); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1564); 
                    match(input,11,FOLLOW_11_in_object_creation1566); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1568); 
                    match(input,11,FOLLOW_11_in_object_creation1570); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1572); 
                    match(input,9,FOLLOW_9_in_object_creation1574); 
                     
                            System.out.println("je parse CT"); 
                             

                    }
                    break;
                case 5 :
                    // Cami.g:565:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,52,FOLLOW_52_in_object_creation1580); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1582); 
                    match(input,11,FOLLOW_11_in_object_creation1584); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1586); 
                    match(input,11,FOLLOW_11_in_object_creation1588); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1590); 
                    match(input,11,FOLLOW_11_in_object_creation1592); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1594); 
                    match(input,11,FOLLOW_11_in_object_creation1596); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1598); 
                    match(input,9,FOLLOW_9_in_object_creation1600); 

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
    // Cami.g:570:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // Cami.g:571:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==53) ) {
                alt32=1;
            }
            else if ( (LA32_0==54) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("570:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // Cami.g:572:5: 'SU(' id= NUMBER ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1617); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1621); 
                    match(input,9,FOLLOW_9_in_object_deletion1623); 

                             System.out.println("je parse SU"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:575:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,54,FOLLOW_54_in_object_deletion1630); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1634); 
                    match(input,11,FOLLOW_11_in_object_deletion1636); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1640); 
                    match(input,9,FOLLOW_9_in_object_deletion1642); 

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
    // Cami.g:581:1: domaine_table : ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' );
    public final void domaine_table() throws RecognitionException {
        try {
            // Cami.g:582:7: ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' )
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
            case 15:
                {
                alt33=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("581:1: domaine_table : ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' );", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // Cami.g:583:7: 'TD(' CAMI_STRING ')'
                    {
                    match(input,55,FOLLOW_55_in_domaine_table1667); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1669); 
                    match(input,9,FOLLOW_9_in_domaine_table1671); 

                           
                               System.out.println("je parse le TD dans table domaine");
                            

                    }
                    break;
                case 2 :
                    // Cami.g:587:10: 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,56,FOLLOW_56_in_domaine_table1683); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1685); 
                    match(input,11,FOLLOW_11_in_domaine_table1687); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1689); 
                    match(input,11,FOLLOW_11_in_domaine_table1691); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1693); 
                    match(input,9,FOLLOW_9_in_domaine_table1695); 

                        
                               System.out.println("je parse le OB dans table domaine");
                            

                    }
                    break;
                case 3 :
                    // Cami.g:591:10: 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,57,FOLLOW_57_in_domaine_table1707); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1709); 
                    match(input,11,FOLLOW_11_in_domaine_table1711); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1713); 
                    match(input,11,FOLLOW_11_in_domaine_table1715); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1717); 
                    match(input,11,FOLLOW_11_in_domaine_table1719); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1721); 
                    match(input,11,FOLLOW_11_in_domaine_table1723); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1725); 
                    match(input,9,FOLLOW_9_in_domaine_table1727); 

                             
                               System.out.println("je parse le AT dans table domaine");
                            

                    }
                    break;
                case 4 :
                    // Cami.g:595:9: 'FA()'
                    {
                    match(input,15,FOLLOW_15_in_domaine_table1738); 

                              
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
    // Cami.g:600:1: modele : 'DB()' ( modele )* 'FB()' ;
    public final void modele() throws RecognitionException {
        try {
            // Cami.g:601:5: ( 'DB()' ( modele )* 'FB()' )
            // Cami.g:602:7: 'DB()' ( modele )* 'FB()'
            {
            match(input,58,FOLLOW_58_in_modele1757); 

                     System.out.println("je parse BD"); 
                   
            // Cami.g:605:8: ( modele )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==58) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // Cami.g:605:8: modele
            	    {
            	    pushFollow(FOLLOW_modele_in_modele1767);
            	    modele();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            match(input,59,FOLLOW_59_in_modele1777); 

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
    // Cami.g:611:1: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
    public final void modele2() throws RecognitionException {
        try {
            // Cami.g:612:5: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            int alt35=7;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt35=1;
                }
                break;
            case 49:
                {
                alt35=2;
                }
                break;
            case 50:
                {
                alt35=3;
                }
                break;
            case 51:
                {
                alt35=4;
                }
                break;
            case 52:
                {
                alt35=5;
                }
                break;
            case 60:
                {
                alt35=6;
                }
                break;
            case 61:
                {
                alt35=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("611:1: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // Cami.g:613:7: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_modele21797); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21799); 
                    match(input,11,FOLLOW_11_in_modele21801); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21803); 
                    match(input,9,FOLLOW_9_in_modele21805); 

                             System.out.println("je parse CN"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:616:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,49,FOLLOW_49_in_modele21811); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21813); 
                    match(input,11,FOLLOW_11_in_modele21815); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21817); 
                    match(input,11,FOLLOW_11_in_modele21819); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21821); 
                    match(input,9,FOLLOW_9_in_modele21823); 

                            System.out.println("je parse CB"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:619:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,50,FOLLOW_50_in_modele21829); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21831); 
                    match(input,11,FOLLOW_11_in_modele21833); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21835); 
                    match(input,11,FOLLOW_11_in_modele21837); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21839); 
                    match(input,11,FOLLOW_11_in_modele21841); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21843); 
                    match(input,9,FOLLOW_9_in_modele21845); 

                            System.out.println("je parse CA"); 
                             

                    }
                    break;
                case 4 :
                    // Cami.g:622:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,51,FOLLOW_51_in_modele21851); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21853); 
                    match(input,11,FOLLOW_11_in_modele21855); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21857); 
                    match(input,11,FOLLOW_11_in_modele21859); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21861); 
                    match(input,9,FOLLOW_9_in_modele21863); 
                     
                            System.out.println("je parse CT"); 
                             

                    }
                    break;
                case 5 :
                    // Cami.g:625:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,52,FOLLOW_52_in_modele21869); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21871); 
                    match(input,11,FOLLOW_11_in_modele21873); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21875); 
                    match(input,11,FOLLOW_11_in_modele21877); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21879); 
                    match(input,11,FOLLOW_11_in_modele21881); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21883); 
                    match(input,11,FOLLOW_11_in_modele21885); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21887); 
                    match(input,9,FOLLOW_9_in_modele21889); 

                           System.out.println("je parse CM"); 
                             

                    }
                    break;
                case 6 :
                    // Cami.g:628:10: 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,60,FOLLOW_60_in_modele21901); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21902); 
                    match(input,11,FOLLOW_11_in_modele21904); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21906); 
                    match(input,11,FOLLOW_11_in_modele21908); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21910); 
                    match(input,11,FOLLOW_11_in_modele21912); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21914); 
                    match(input,9,FOLLOW_9_in_modele21916); 

                             System.out.println("je parse PO");
                            

                    }
                    break;
                case 7 :
                    // Cami.g:631:10: 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,61,FOLLOW_61_in_modele21928); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21929); 
                    match(input,11,FOLLOW_11_in_modele21931); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21933); 
                    match(input,11,FOLLOW_11_in_modele21935); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21937); 
                    match(input,11,FOLLOW_11_in_modele21939); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21941); 
                    match(input,11,FOLLOW_11_in_modele21943); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21945); 
                    match(input,9,FOLLOW_9_in_modele21947); 

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
    // Cami.g:636:1: dialogue : ( ( dialog2 )+ ( '<EOF>' )* | 'DC(' | 'AD(' NUMBER ')' );
    public final void dialogue() throws RecognitionException {
        try {
            // Cami.g:637:6: ( ( dialog2 )+ ( '<EOF>' )* | 'DC(' | 'AD(' NUMBER ')' )
            int alt38=3;
            switch ( input.LA(1) ) {
            case NEWLINE:
            case 64:
            case 65:
            case 66:
                {
                alt38=1;
                }
                break;
            case 62:
                {
                alt38=2;
                }
                break;
            case 63:
                {
                alt38=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("636:1: dialogue : ( ( dialog2 )+ ( '<EOF>' )* | 'DC(' | 'AD(' NUMBER ')' );", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // Cami.g:638:5: ( dialog2 )+ ( '<EOF>' )*
                    {
                    // Cami.g:638:5: ( dialog2 )+
                    int cnt36=0;
                    loop36:
                    do {
                        int alt36=2;
                        switch ( input.LA(1) ) {
                        case 64:
                            {
                            alt36=1;
                            }
                            break;
                        case 65:
                            {
                            alt36=1;
                            }
                            break;
                        case NEWLINE:
                            {
                            alt36=1;
                            }
                            break;
                        case 66:
                            {
                            alt36=1;
                            }
                            break;

                        }

                        switch (alt36) {
                    	case 1 :
                    	    // Cami.g:638:5: dialog2
                    	    {
                    	    pushFollow(FOLLOW_dialog2_in_dialogue1966);
                    	    dialog2();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt36 >= 1 ) break loop36;
                                EarlyExitException eee =
                                    new EarlyExitException(36, input);
                                throw eee;
                        }
                        cnt36++;
                    } while (true);

                    // Cami.g:639:5: ( '<EOF>' )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==37) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // Cami.g:639:5: '<EOF>'
                    	    {
                    	    match(input,37,FOLLOW_37_in_dialogue1973); 

                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Cami.g:640:6: 'DC('
                    {
                    match(input,62,FOLLOW_62_in_dialogue1981); 

                            System.out.println("je parse DC");
                        

                    }
                    break;
                case 3 :
                    // Cami.g:643:6: 'AD(' NUMBER ')'
                    {
                    match(input,63,FOLLOW_63_in_dialogue1989); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1990); 
                    match(input,9,FOLLOW_9_in_dialogue1992); 

                             System.out.println("je parse AD");
                            

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


    // $ANTLR start dialog2
    // Cami.g:648:1: dialog2 : ( 'DS(' NUMBER ',' CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' );
    public final void dialog2() throws RecognitionException {
        Token dialog_id=null;
        Token dialog_type=null;
        Token buttons_type=null;
        Token window_title=null;
        Token help=null;
        Token title_or_message=null;
        Token input_type=null;
        Token line_type=null;
        Token default_value=null;

        try {
            // Cami.g:649:5: ( 'DS(' NUMBER ',' CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' )
            int alt40=4;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt40=1;
                }
                break;
            case 65:
                {
                alt40=2;
                }
                break;
            case NEWLINE:
                {
                alt40=3;
                }
                break;
            case 66:
                {
                alt40=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("648:1: dialog2 : ( 'DS(' NUMBER ',' CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' );", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // Cami.g:650:5: 'DS(' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,64,FOLLOW_64_in_dialog22011); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22012); 
                    match(input,11,FOLLOW_11_in_dialog22014); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22016); 
                    match(input,9,FOLLOW_9_in_dialog22018); 

                           System.out.println("je parse DS"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:653:8: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
                    {
                    match(input,65,FOLLOW_65_in_dialog22028); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22032); 
                    match(input,11,FOLLOW_11_in_dialog22034); 
                    dialog_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22038); 
                    match(input,11,FOLLOW_11_in_dialog22040); 
                    buttons_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22044); 
                    match(input,11,FOLLOW_11_in_dialog22046); 
                    window_title=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22051); 
                    match(input,11,FOLLOW_11_in_dialog22053); 
                    help=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22057); 
                    match(input,11,FOLLOW_11_in_dialog22059); 
                    title_or_message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22063); 
                    match(input,11,FOLLOW_11_in_dialog22065); 
                    input_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22069); 
                    match(input,11,FOLLOW_11_in_dialog22071); 
                    line_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22075); 
                    match(input,11,FOLLOW_11_in_dialog22077); 
                    // Cami.g:653:222: (default_value= CAMI_STRING )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==CAMI_STRING) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // Cami.g:653:222: default_value= CAMI_STRING
                            {
                            default_value=(Token)input.LT(1);
                            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22081); 

                            }
                            break;

                    }

                    match(input,9,FOLLOW_9_in_dialog22084); 

                           System.out.println("je parse CE"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:656:8: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_dialog22094); 

                    }
                    break;
                case 4 :
                    // Cami.g:657:9: 'FF('
                    {
                    match(input,66,FOLLOW_66_in_dialog22104); 
                     
                         System.out.println("je parse FF");
                         

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
    // $ANTLR end dialog2


    protected DFA23 dfa23 = new DFA23(this);
    static final String DFA23_eotS =
        "\15\uffff";
    static final String DFA23_eofS =
        "\1\3\1\uffff\1\3\12\uffff";
    static final String DFA23_minS =
        "\1\6\1\uffff\1\33\12\uffff";
    static final String DFA23_maxS =
        "\1\102\1\uffff\1\45\12\uffff";
    static final String DFA23_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\1\3\1\4\1\5\2\6\1\7\1\10\1\11\1\12";
    static final String DFA23_specialS =
        "\15\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\10\10\uffff\1\11\13\uffff\1\5\3\uffff\3\7\2\uffff\1\1\1\2"+
            "\1\4\1\14\1\10\16\6\3\11\1\13\3\uffff\5\12",
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

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "436:1: result_reception : ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' );";
        }
    }
 

    public static final BitSet FOLLOW_ack_open_communication_in_command56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_command64 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command72 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_in_command83 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_suspend_current_session_in_command99 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_resume_suspend_current_session_in_command106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_close_current_session_in_command113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_command121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_command128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_brutal_interrupt_in_command135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_reception_in_command143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication162 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication164 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection188 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection197 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection206 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection215 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session265 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session267 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ack_open_session278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ack_suspend_current_session327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ack_resume_suspend_current_session346 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session348 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_resume_suspend_current_session350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ack_close_current_session368 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_close_current_session370 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_close_current_session372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_interlocutor_table396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_interlocutor_table404 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table408 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table410 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table414 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table416 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table420 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table437 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table441 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_interlocutor_table453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_receving_menu476 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu480 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu483 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_receving_menu487 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_receving_menu494 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu495 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_menu_name513 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name517 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name519 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name523 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name525 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name529 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_question_add547 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add551 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add553 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add557 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add559 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add566 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add569 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add573 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add576 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add583 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add586 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add591 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add594 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add598 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add601 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add608 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add611 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add615 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_update639 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update643 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update645 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update649 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update651 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_update655 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update661 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update665 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_update668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_end_menu_transmission694 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission696 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message748 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message750 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message765 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message767 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message783 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message785 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message787 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message789 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_brutal_interrupt811 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_brutal_interrupt815 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_brutal_interrupt817 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_brutal_interrupt821 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_brutal_interrupt823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ask_for_a_model898 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model900 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_a_model902 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model904 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ask_for_a_model906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_result_reception937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_result_reception948 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_38_in_result_reception959 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception963 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception965 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception969 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception971 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception975 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_result_reception990 = new BitSet(new long[]{0x0000002008000000L});
    public static final BitSet FOLLOW_27_in_result_reception1001 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception1005 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception1007 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception1011 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception1013 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception1017 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception1020 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception1024 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception1027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_reception1039 = new BitSet(new long[]{0x007FFE0000000002L});
    public static final BitSet FOLLOW_message_utils_in_result_reception1051 = new BitSet(new long[]{0x0000010380000042L});
    public static final BitSet FOLLOW_domaine_table_in_result_reception1063 = new BitSet(new long[]{0x0380000000008002L});
    public static final BitSet FOLLOW_dialogue_in_result_reception1075 = new BitSet(new long[]{0xC000000000000042L,0x0000000000000007L});
    public static final BitSet FOLLOW_modele_in_result_reception1087 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_39_in_result_reception1099 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception1101 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception1103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message2_in_message_utils1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message2_in_message_utils1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message2_in_message_utils1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_message_utils1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_message_utils1183 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1184 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1186 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1188 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1190 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1192 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1194 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1196 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1198 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1200 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_message_utils1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message21214 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message21216 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message21218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message21231 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message21233 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message21235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message21249 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message21251 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message21253 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message21255 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message21257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_body_in_result1277 = new BitSet(new long[]{0x007FF02000000002L});
    public static final BitSet FOLLOW_37_in_result1288 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_41_in_result1293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_result1305 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1309 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1311 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result1315 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result1317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_result1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_textual_result1397 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1399 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result1401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_object_designation1417 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1421 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_object_outline1439 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1443 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_attribute_outline1465 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1469 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1471 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1475 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1477 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1481 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1484 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1488 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_object_creation1508 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1510 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1512 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1514 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_creation1522 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1524 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1526 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1528 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1530 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1532 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_creation1540 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1542 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1544 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1546 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1548 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1550 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1552 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1554 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_creation1562 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1564 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1566 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1568 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1570 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1572 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_creation1580 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1582 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1584 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1586 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1588 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1590 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1592 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1594 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1596 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1598 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1617 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1621 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_object_deletion1630 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1634 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1636 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1640 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_domaine_table1667 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1669 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_domaine_table1683 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1685 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1687 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1689 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1691 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1693 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_domaine_table1707 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1709 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1711 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1713 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1715 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1717 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1719 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_domaine_table1721 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_domaine_table1723 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1725 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_domaine_table1738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_modele1757 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_modele_in_modele1767 = new BitSet(new long[]{0x0C00000000000000L});
    public static final BitSet FOLLOW_59_in_modele1777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_modele21797 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21799 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21801 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21803 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_modele21811 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21813 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21815 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21817 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21819 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21821 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_modele21829 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21831 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21833 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21835 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21837 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21839 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21841 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21843 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_modele21851 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21853 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21855 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21857 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21859 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21861 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_modele21869 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21871 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21873 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21875 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21877 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21879 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21881 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21883 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21885 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21887 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_modele21901 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21902 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21904 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21906 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21908 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21910 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21912 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21914 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_modele21928 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21929 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21931 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21933 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21935 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21937 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21939 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21941 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21943 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21945 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialog2_in_dialogue1966 = new BitSet(new long[]{0x0000002000000042L,0x0000000000000007L});
    public static final BitSet FOLLOW_37_in_dialogue1973 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_62_in_dialogue1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_dialogue1989 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue1990 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_dialog22011 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22012 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22014 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22016 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog22018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_dialog22028 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22032 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22034 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22038 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22040 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22044 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22046 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22051 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22053 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22057 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22059 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22063 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22065 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22069 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22071 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22075 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22077 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22081 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog22084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_dialog22094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_dialog22104 = new BitSet(new long[]{0x0000000000000002L});

}