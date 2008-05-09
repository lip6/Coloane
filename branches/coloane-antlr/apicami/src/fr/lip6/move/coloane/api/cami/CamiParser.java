// $ANTLR 3.0.1 Cami.g 2008-05-09 21:14:36

package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'FS('", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'MO('"
    };
    public static final int FIXED_LENGTH_STRING=6;
    public static final int CAMI_STRING=4;
    public static final int NEWLINE=7;
    public static final int NUMBER=5;
    public static final int EOF=-1;

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
    // Cami.g:61:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | special_message | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:62:5: ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | special_message | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session )
            int alt2=11;
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
            case EOF:
            case 27:
                {
                alt2=6;
                }
                break;
            case 30:
                {
                alt2=7;
                }
                break;
            case 31:
                {
                alt2=8;
                }
                break;
            case 16:
                {
                alt2=9;
                }
                break;
            case 17:
                {
                alt2=10;
                }
                break;
            case 18:
                {
                alt2=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("61:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | special_message | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:63:5: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command55);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:63:30: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command59);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:63:52: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_command63);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:64:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command71);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:64:26: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command75);
                    receving_menu();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:65:6: ( update )*
                    {
                    // Cami.g:65:6: ( update )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==27) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Cami.g:65:6: update
                    	    {
                    	    pushFollow(FOLLOW_update_in_command82);
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
                    // Cami.g:66:6: end_menu_transmission
                    {
                    pushFollow(FOLLOW_end_menu_transmission_in_command90);
                    end_menu_transmission();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // Cami.g:67:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_command97);
                    special_message();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // Cami.g:69:6: ack_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_suspend_current_session_in_command105);
                    ack_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // Cami.g:70:6: ack_resume_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_resume_suspend_current_session_in_command112);
                    ack_resume_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // Cami.g:71:6: ack_close_current_session
                    {
                    pushFollow(FOLLOW_ack_close_current_session_in_command119);
                    ack_close_current_session();
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
    // Cami.g:77:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:78:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:79:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication138);
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication140);
            match(input,9,FOLLOW_9_in_ack_open_communication142);

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
    // Cami.g:91:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:92:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:93:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection163);
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection172);

                        listOfArgs.add(v1.getText());

            match(input,11,FOLLOW_11_in_ack_open_connection181);
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection190);
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }

            match(input,9,FOLLOW_9_in_ack_open_connection198);

            }

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
    // Cami.g:110:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // Cami.g:111:5: ( 'FC()' )
            // Cami.g:112:5: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection223);

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
    // Cami.g:117:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:118:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
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
                    new NoViableAltException("117:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:119:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session240);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session242);
                    match(input,9,FOLLOW_9_in_ack_open_session243);

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");

                                /* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
                                menuList = new ArrayList<IMenu>();
                                /*  */
                                camiUpdates = new ArrayList<ArrayList<String>>();


                    }
                    break;
                case 2 :
                    // Cami.g:128:3: 'TD()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session248);

                                // Ajouter un controle qu'on doit bien recevoir TD
                    //            System.out.println("TD");


                    }
                    break;
                case 3 :
                    // Cami.g:132:3: 'FA()'
                    {
                    match(input,15,FOLLOW_15_in_ack_open_session253);
                    // Ajouter un controle qu'on doit bien recevoir FA}
                    //            System.out.println("FA");


                    }
                    break;
                case 4 :
                    // Cami.g:136:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session270);
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
    // Cami.g:141:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:142:2: ( 'SS()' )
            // Cami.g:143:2: 'SS()'
            {
            match(input,16,FOLLOW_16_in_ack_suspend_current_session302);
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
    // Cami.g:151:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        Token CAMI_STRING2=null;

        try {
            // Cami.g:152:2: ( 'RS(' CAMI_STRING ')' )
            // Cami.g:153:2: 'RS(' CAMI_STRING ')'
            {
            match(input,17,FOLLOW_17_in_ack_resume_suspend_current_session321);
            CAMI_STRING2=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session323);
            match(input,9,FOLLOW_9_in_ack_resume_suspend_current_session325);

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
    // Cami.g:160:1: ack_close_current_session : 'FS(' CAMI_STRING ')' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // Cami.g:161:2: ( 'FS(' CAMI_STRING ')' )
            // Cami.g:162:2: 'FS(' CAMI_STRING ')'
            {
            match(input,18,FOLLOW_18_in_ack_close_current_session343);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_close_current_session345);
            match(input,9,FOLLOW_9_in_ack_close_current_session347);

                        sc.notinotifyEndCloseSession();


            }

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
    // Cami.g:171:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:172:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
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
                    new NoViableAltException("171:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:173:5: 'TL()'
                    {
                    match(input,19,FOLLOW_19_in_interlocutor_table371);



                    }
                    break;
                case 2 :
                    // Cami.g:175:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,20,FOLLOW_20_in_interlocutor_table379);
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table383);
                    match(input,11,FOLLOW_11_in_interlocutor_table385);
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table389);
                    match(input,11,FOLLOW_11_in_interlocutor_table391);
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table395);
                    match(input,11,FOLLOW_11_in_interlocutor_table412);
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table416);
                    match(input,9,FOLLOW_9_in_interlocutor_table419);


                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());


                    }
                    break;
                case 3 :
                    // Cami.g:184:6: 'FL()'
                    {
                    match(input,21,FOLLOW_21_in_interlocutor_table428);

                                fkInfo = CamiObjectBuilder.buildFkInfo(listOfArgs);

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
    // Cami.g:198:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:199:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:200:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,22,FOLLOW_22_in_receving_menu451);

                        /* créer la menuList  */
                        camiMenuList = new ArrayList<ArrayList<String>>();

            pushFollow(FOLLOW_menu_name_in_receving_menu455);
            menu_name();
            _fsp--;

            // Cami.g:205:2: ( question_add )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==26) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Cami.g:205:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu458);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_receving_menu462);

                        /* fin de la reception des menus : demander la construction du menu */
                        menu = CamiObjectBuilder.buildMenu(camiMenuList);

                        System.out.println("nombre de AQ : " + camiMenuList.size());

                        menuList.add(menu);

            //            System.out.println("Menu construit");
            //            System.out.println("FQ()");

            match(input,24,FOLLOW_24_in_receving_menu469);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu470);
            match(input,9,FOLLOW_9_in_receving_menu471);
             /* afficher les questions */
                        System.out.println("VQ");



            }

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
    // Cami.g:226:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:227:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:228:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,25,FOLLOW_25_in_menu_name488);
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name492);
            match(input,11,FOLLOW_11_in_menu_name494);
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name498);
            match(input,11,FOLLOW_11_in_menu_name500);
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name504);
            match(input,9,FOLLOW_9_in_menu_name506);


                        // TODO :  Veifier qu'on est dans la réception de menus racine !!!

                        /* racine des question  */
                        ArrayList<String> cq = new ArrayList<String>();
                        cq.add(name.getText()); /* racine  */
                        cq.add(question_type.getText()); /* type de la question  */
                        cq.add(question_behavior.getText()); /* type du choix */

                        camiMenuList.add(cq); /* ajouter a la liste des AQ */

            //                        System.out.println("CQ");
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
    // Cami.g:250:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:251:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:252:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,26,FOLLOW_26_in_question_add522);
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add526);
            match(input,11,FOLLOW_11_in_question_add528);
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add532);
            match(input,11,FOLLOW_11_in_question_add534);
            // Cami.g:253:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:253:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add541);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add544);
            // Cami.g:253:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:253:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add548);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add551);
            // Cami.g:254:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:254:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add558);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add561);
            // Cami.g:254:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:254:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add566);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add569);
            // Cami.g:254:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:254:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add573);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add576);
            // Cami.g:255:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:255:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add583);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add586);
            // Cami.g:255:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:255:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add590);

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add593);


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
    // Cami.g:317:1: update : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' ;
    public final void update() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:318:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:319:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')'
            {
            match(input,27,FOLLOW_27_in_update614);
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update618);
            match(input,11,FOLLOW_11_in_update620);
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update624);
            match(input,11,FOLLOW_11_in_update626);
            state=(Token)input.LT(1);
            if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update630);    throw mse;
            }

            match(input,11,FOLLOW_11_in_update636);
            // Cami.g:319:91: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:319:91: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update640);

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_update643);



                        /*  */
                        ArrayList<String> update = new ArrayList<String>();

                        update.add(service_name.getText()); /* nom du service */
                        update.add(question_name.getText());  /* nom de la question  */
                        update.add(state.getText());  /* état de la question  */

                        if(!state.getText().equals("7") && !state.getText().equals("8")) /* si c'est un modificateur de menu */
                            update.add(mess.getText()); /* message : optionnel */


                        camiUpdates.add(update);/* ajouter à la liste des updates  */

            //            System.out.println("ANTLR : TQ(" + service_name.getText() + ", " + question_name.getText() + ", " +
            //                                        state.getText() + ", " + ")");


            }

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
    // Cami.g:342:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER3=null;

        try {
            // Cami.g:343:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:344:2: 'QQ(' NUMBER ')'
            {
            match(input,30,FOLLOW_30_in_end_menu_transmission669);
            NUMBER3=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission671);
            match(input,9,FOLLOW_9_in_end_menu_transmission673);


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


    // $ANTLR start special_message
    // Cami.g:363:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        try {
            // Cami.g:364:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:365:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_special_message701);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message703);
            match(input,11,FOLLOW_11_in_special_message705);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message707);
            match(input,9,FOLLOW_9_in_special_message709);

                        // verifier qu'on est dans la reception des menus ou autre


            }

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




    public static final BitSet FOLLOW_ack_open_communication_in_command55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_command63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_in_command82 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_command97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_suspend_current_session_in_command105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_resume_suspend_current_session_in_command112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_close_current_session_in_command119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication138 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication140 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection163 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection172 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection181 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection190 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session240 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session242 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ack_open_session253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ack_suspend_current_session302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ack_resume_suspend_current_session321 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session323 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_resume_suspend_current_session325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ack_close_current_session343 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_close_current_session345 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_close_current_session347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_interlocutor_table371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_interlocutor_table379 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table383 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table385 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table389 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table391 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table395 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table412 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table416 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_interlocutor_table428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_receving_menu451 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu455 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu458 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_receving_menu462 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_receving_menu469 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu470 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_menu_name488 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name492 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name494 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name498 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name500 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name504 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_question_add522 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add526 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add528 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add532 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add534 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add541 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add544 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add548 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add551 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add558 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add561 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add566 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add569 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add573 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add576 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add583 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add586 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add590 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_update614 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update618 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update620 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update624 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update626 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_update630 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update636 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update640 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_update643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_end_menu_transmission669 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission671 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_special_message701 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message703 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message705 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message707 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message709 = new BitSet(new long[]{0x0000000000000002L});

}