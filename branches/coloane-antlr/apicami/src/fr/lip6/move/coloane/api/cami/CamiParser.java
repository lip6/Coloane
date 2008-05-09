// $ANTLR 3.0.1 Cami.g 2008-05-09 20:28:16

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'MO('"
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
    // Cami.g:61:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | special_message | ack_suspend_current_session | ack_resume_suspend_current_session );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:62:5: ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | special_message | ack_suspend_current_session | ack_resume_suspend_current_session )
            int alt2=9;
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
            case 13:
            case 14:
            case 17:
            case 18:
            case 19:
                {
                alt2=3;
                }
                break;
            case 20:
                {
                alt2=4;
                }
                break;
            case EOF:
            case 25:
                {
                alt2=5;
                }
                break;
            case 28:
                {
                alt2=6;
                }
                break;
            case 29:
                {
                alt2=7;
                }
                break;
            case 15:
                {
                alt2=8;
                }
                break;
            case 16:
                {
                alt2=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("61:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | special_message | ack_suspend_current_session | ack_resume_suspend_current_session );", 2, 0, input);

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
                    // Cami.g:64:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command67);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:64:26: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command71);
                    receving_menu();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:65:6: ( update )*
                    {
                    // Cami.g:65:6: ( update )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==25) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Cami.g:65:6: update
                    	    {
                    	    pushFollow(FOLLOW_update_in_command78);
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
                case 6 :
                    // Cami.g:66:6: end_menu_transmission
                    {
                    pushFollow(FOLLOW_end_menu_transmission_in_command86);
                    end_menu_transmission();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // Cami.g:67:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_command93);
                    special_message();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // Cami.g:69:6: ack_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_suspend_current_session_in_command101);
                    ack_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // Cami.g:70:6: ack_resume_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_resume_suspend_current_session_in_command108);
                    ack_resume_suspend_current_session();
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
    // Cami.g:76:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:77:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:78:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication127);
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication129);
            match(input,9,FOLLOW_9_in_ack_open_communication131);

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
    // Cami.g:90:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:91:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:92:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection152);
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection161);

                        listOfArgs.add(v1.getText());

            match(input,11,FOLLOW_11_in_ack_open_connection170);
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection179);
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }

            match(input,9,FOLLOW_9_in_ack_open_connection187);

            }

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


    // $ANTLR start ack_open_session
    // Cami.g:110:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:111:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
            int alt3=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt3=1;
                }
                break;
            case 13:
                {
                alt3=2;
                }
                break;
            case 14:
                {
                alt3=3;
                }
                break;
            case 17:
            case 18:
            case 19:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("110:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:112:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,12,FOLLOW_12_in_ack_open_session207);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session209);
                    match(input,9,FOLLOW_9_in_ack_open_session210);

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");

                                /* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
                                menuList = new ArrayList<IMenu>();
                                /*  */
                                camiUpdates = new ArrayList<ArrayList<String>>();





                    }
                    break;
                case 2 :
                    // Cami.g:124:3: 'TD()'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session215);

                                // Ajouter un controle qu'on doit bien recevoir TD
                    //            System.out.println("TD");


                    }
                    break;
                case 3 :
                    // Cami.g:128:3: 'FA()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session220);
                    // Ajouter un controle qu'on doit bien recevoir FA}
                    //            System.out.println("FA");


                    }
                    break;
                case 4 :
                    // Cami.g:132:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session229);
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
    // Cami.g:137:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:138:2: ( 'SS()' )
            // Cami.g:139:2: 'SS()'
            {
            match(input,15,FOLLOW_15_in_ack_suspend_current_session249);
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
    // Cami.g:147:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        Token CAMI_STRING2=null;

        try {
            // Cami.g:148:2: ( 'RS(' CAMI_STRING ')' )
            // Cami.g:149:2: 'RS(' CAMI_STRING ')'
            {
            match(input,16,FOLLOW_16_in_ack_resume_suspend_current_session268);
            CAMI_STRING2=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session270);
            match(input,9,FOLLOW_9_in_ack_resume_suspend_current_session272);

       //                 sc.notifyEndResumeSession(CAMI_STRING2.getText());


            }

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


    // $ANTLR start interlocutor_table
    // Cami.g:157:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:158:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt4=1;
                }
                break;
            case 18:
                {
                alt4=2;
                }
                break;
            case 19:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("157:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:159:5: 'TL()'
                    {
                    match(input,17,FOLLOW_17_in_interlocutor_table295);



                    }
                    break;
                case 2 :
                    // Cami.g:161:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,18,FOLLOW_18_in_interlocutor_table303);
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table307);
                    match(input,11,FOLLOW_11_in_interlocutor_table309);
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table313);
                    match(input,11,FOLLOW_11_in_interlocutor_table315);
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table319);
                    match(input,11,FOLLOW_11_in_interlocutor_table336);
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table340);
                    match(input,9,FOLLOW_9_in_interlocutor_table343);


                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());


                    }
                    break;
                case 3 :
                    // Cami.g:170:6: 'FL()'
                    {
                    match(input,19,FOLLOW_19_in_interlocutor_table352);

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
    // Cami.g:184:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:185:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:186:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,20,FOLLOW_20_in_receving_menu375);

                        /* créer la menuList  */
                        camiMenuList = new ArrayList<ArrayList<String>>();

            pushFollow(FOLLOW_menu_name_in_receving_menu379);
            menu_name();
            _fsp--;

            // Cami.g:191:2: ( question_add )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==24) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Cami.g:191:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu382);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,21,FOLLOW_21_in_receving_menu386);

                        /* fin de la reception des menus : demander la construction du menu */
                        menu = CamiObjectBuilder.buildMenu(camiMenuList);

                        System.out.println("nombre de AQ : " + camiMenuList.size());

                        menuList.add(menu);

            //            System.out.println("Menu construit");
            //            System.out.println("FQ()");

            match(input,22,FOLLOW_22_in_receving_menu393);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu394);
            match(input,9,FOLLOW_9_in_receving_menu395);
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
    // Cami.g:212:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:213:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:214:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,23,FOLLOW_23_in_menu_name412);
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name416);
            match(input,11,FOLLOW_11_in_menu_name418);
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name422);
            match(input,11,FOLLOW_11_in_menu_name424);
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name428);
            match(input,9,FOLLOW_9_in_menu_name430);


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
    // Cami.g:236:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:237:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:238:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,24,FOLLOW_24_in_question_add446);
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add450);
            match(input,11,FOLLOW_11_in_question_add452);
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add456);
            match(input,11,FOLLOW_11_in_question_add458);
            // Cami.g:239:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:239:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add465);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add468);
            // Cami.g:239:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:239:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add472);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add475);
            // Cami.g:240:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:240:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add482);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add485);
            // Cami.g:240:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:240:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add490);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add493);
            // Cami.g:240:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:240:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add497);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add500);
            // Cami.g:241:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:241:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add507);

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add510);
            // Cami.g:241:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:241:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add514);

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add517);


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
    // Cami.g:303:1: update : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' ;
    public final void update() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:304:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:305:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')'
            {
            match(input,25,FOLLOW_25_in_update538);
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update542);
            match(input,11,FOLLOW_11_in_update544);
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update548);
            match(input,11,FOLLOW_11_in_update550);
            state=(Token)input.LT(1);
            if ( (input.LA(1)>=26 && input.LA(1)<=27) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update554);    throw mse;
            }

            match(input,11,FOLLOW_11_in_update560);
            // Cami.g:305:91: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:305:91: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update564);

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_update567);



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
    // Cami.g:328:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER3=null;

        try {
            // Cami.g:329:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:330:2: 'QQ(' NUMBER ')'
            {
            match(input,28,FOLLOW_28_in_end_menu_transmission593);
            NUMBER3=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission595);
            match(input,9,FOLLOW_9_in_end_menu_transmission597);


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
    // Cami.g:349:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        try {
            // Cami.g:350:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:351:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,29,FOLLOW_29_in_special_message625);
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message627);
            match(input,11,FOLLOW_11_in_special_message629);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message631);
            match(input,9,FOLLOW_9_in_special_message633);

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
    public static final BitSet FOLLOW_ack_open_session_in_command67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_in_command78 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_command93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_suspend_current_session_in_command101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_resume_suspend_current_session_in_command108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication127 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication129 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection152 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection161 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection170 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection179 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ack_open_session207 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session209 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ack_suspend_current_session249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ack_resume_suspend_current_session268 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session270 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_resume_suspend_current_session272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_interlocutor_table295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_interlocutor_table303 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table307 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table309 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table313 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table315 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table319 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table336 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table340 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_interlocutor_table352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_receving_menu375 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu379 = new BitSet(new long[]{0x0000000001200000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu382 = new BitSet(new long[]{0x0000000001200000L});
    public static final BitSet FOLLOW_21_in_receving_menu386 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_receving_menu393 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu394 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_menu_name412 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name416 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name418 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name422 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name424 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name428 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_question_add446 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add450 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add452 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add456 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add458 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add465 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add468 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add472 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add475 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add482 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add485 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add490 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add493 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add497 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add500 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add507 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add510 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add514 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_update538 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update542 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update544 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update548 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update550 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_set_in_update554 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update560 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update564 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_update567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_end_menu_transmission593 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission595 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_special_message625 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message627 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message629 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message631 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message633 = new BitSet(new long[]{0x0000000000000002L});

}