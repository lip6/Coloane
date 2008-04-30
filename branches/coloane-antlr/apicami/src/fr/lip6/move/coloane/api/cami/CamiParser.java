// $ANTLR 3.0.1 Cami.g 2008-04-30 17:44:11

package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'CQ('", "'AQ('", "'MO('"
    };
    public static final int FIXED_LENGTH_STRING=6;
    public static final int CAMI_STRING=4;
    public static final int EOF=-1;
    public static final int NUMBER=5;

        public CamiParser(TokenStream input) {
            super(input);
        }


    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Cami.g"; }


       ArrayList<String> listOfArgs; /* liste des arguments pour la construction des objets de notification */
       ArrayList<ArrayList<String>> menuList;
       HashMap<String, Object> hashObservable;

       ISessionController sc;

       IFkInfo fkinfo;
       IMenu menu;


       public CamiParser(TokenStream input, ISessionController sessionController, HashMap<String, Object> hm) {
           this(input);
           hashObservable = hm;
           sc = sessionController;
       }



    // $ANTLR start command
    // Cami.g:35:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:36:5: ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu )
            int alt1=4;
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
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 21:
                {
                alt1=3;
                }
                break;
            case 17:
            case 18:
            case 19:
            case 20:
                {
                alt1=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("35:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Cami.g:37:5: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command32);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:37:30: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command36);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:38:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command44);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:38:26: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command48);
                    receving_menu();
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
    // Cami.g:42:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:43:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:44:2: 'SC(' CAMI_STRING ')'
            {
            match(input,7,FOLLOW_7_in_ack_open_communication65);
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication67);
            match(input,8,FOLLOW_8_in_ack_open_communication69);

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
    // Cami.g:55:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:56:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:57:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_ack_open_connection86);
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection95);

                        listOfArgs.add(v1.getText());

            match(input,10,FOLLOW_10_in_ack_open_connection104);
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection113);
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }

            match(input,8,FOLLOW_8_in_ack_open_connection121);

            }

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
    // Cami.g:73:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table | pecial_message );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:74:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table | pecial_message )
            int alt2=5;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                alt2=2;
                }
                break;
            case 13:
                {
                alt2=3;
                }
                break;
            case 14:
            case 15:
            case 16:
                {
                alt2=4;
                }
                break;
            case 21:
                {
                alt2=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("73:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table | pecial_message );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:75:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,11,FOLLOW_11_in_ack_open_session139);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session141);
                    match(input,8,FOLLOW_8_in_ack_open_session142);

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");


                    }
                    break;
                case 2 :
                    // Cami.g:79:3: 'TD()'
                    {
                    match(input,12,FOLLOW_12_in_ack_open_session147);

                                // Ajouter un controle qu'on doit bien recevoir TD
                                System.out.println("TD");


                    }
                    break;
                case 3 :
                    // Cami.g:83:3: 'FA()'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session152);
                    // Ajouter un controle qu'on doit bien recevoir FA}
                                System.out.println("FA");


                    }
                    break;
                case 4 :
                    // Cami.g:87:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session161);
                    interlocutor_table();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:88:6: pecial_message
                    {
                    pushFollow(FOLLOW_pecial_message_in_ack_open_session168);
                    pecial_message();
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


    // $ANTLR start interlocutor_table
    // Cami.g:93:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:94:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt3=1;
                }
                break;
            case 15:
                {
                alt3=2;
                }
                break;
            case 16:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("93:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:95:5: 'TL()'
                    {
                    match(input,14,FOLLOW_14_in_interlocutor_table189);



                    }
                    break;
                case 2 :
                    // Cami.g:97:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,15,FOLLOW_15_in_interlocutor_table197);
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table201);
                    match(input,10,FOLLOW_10_in_interlocutor_table203);
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table207);
                    match(input,10,FOLLOW_10_in_interlocutor_table209);
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table213);
                    match(input,10,FOLLOW_10_in_interlocutor_table217);
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table221);
                    match(input,8,FOLLOW_8_in_interlocutor_table224);


                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());


                    }
                    break;
                case 3 :
                    // Cami.g:105:6: 'FL()'
                    {
                    match(input,16,FOLLOW_16_in_interlocutor_table233);

                                fkinfo = CamiObjectBuilder.buildFkInfo(listOfArgs);

                                System.out.println("fkinfo");
                                for(int i=0; i<this.listOfArgs.size(); i++){
                                    System.out.println(this.listOfArgs.get(i));
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
    // Cami.g:119:1: receving_menu : ( 'DQ()' | menu_name | question_add | 'FQ()' );
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:120:5: ( 'DQ()' | menu_name | question_add | 'FQ()' )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt4=1;
                }
                break;
            case 19:
                {
                alt4=2;
                }
                break;
            case 20:
                {
                alt4=3;
                }
                break;
            case 18:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("119:1: receving_menu : ( 'DQ()' | menu_name | question_add | 'FQ()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:121:5: 'DQ()'
                    {
                    match(input,17,FOLLOW_17_in_receving_menu259);

                                /* créer la menuList  */
                                menuList = new ArrayList<ArrayList<String>>();


                    }
                    break;
                case 2 :
                    // Cami.g:125:6: menu_name
                    {
                    pushFollow(FOLLOW_menu_name_in_receving_menu267);
                    menu_name();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:126:3: question_add
                    {
                    pushFollow(FOLLOW_question_add_in_receving_menu271);
                    question_add();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:127:3: 'FQ()'
                    {
                    match(input,18,FOLLOW_18_in_receving_menu275);

                                /* fin de la reception des menus : demander la construction du menu */
                                menu = CamiObjectBuilder.buildMenu(menuList);


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
    // $ANTLR end receving_menu


    // $ANTLR start menu_name
    // Cami.g:135:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:136:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:137:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,19,FOLLOW_19_in_menu_name291);
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name295);
            match(input,10,FOLLOW_10_in_menu_name297);
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name301);
            match(input,10,FOLLOW_10_in_menu_name303);
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name307);
            match(input,8,FOLLOW_8_in_menu_name309);


                        // TODO :  Veifier qu'on est dans la réception de menus racine !!!

                        /* racine des question  */
                        ArrayList<String> cq = new ArrayList<String>();
                        cq.add(name.getText()); /* racine  */
                        cq.add(question_type.getText()); /* type de la question  */
                        cq.add(question_behavior.getText()); /* type du choix */

                        menuList.add(cq); /* ajouter a la liste des AQ */

                                   System.out.println("CQ");
                                    System.out.println("name.getText() " + name.getText() );
                                    System.out.println("question_type.getText() " + question_type.getText());
                                    System.out.println("question_behavior.getText() " + question_behavior.getText());



            }

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
    // Cami.g:158:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:159:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:160:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,20,FOLLOW_20_in_question_add324);
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add328);
            match(input,10,FOLLOW_10_in_question_add330);
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add334);
            match(input,10,FOLLOW_10_in_question_add336);
            // Cami.g:161:16: (question_type= NUMBER )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==NUMBER) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // Cami.g:161:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add343);

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_question_add346);
            // Cami.g:161:46: (question_behavior= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:161:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add350);

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_question_add353);
            // Cami.g:162:11: (set_item= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:162:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add360);

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_question_add363);
            // Cami.g:162:31: (dialog= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:162:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add368);

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_question_add371);
            // Cami.g:162:59: (stop_authorized= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:162:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add375);

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_question_add378);
            // Cami.g:163:19: (output_formalism= CAMI_STRING )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==CAMI_STRING) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:163:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add385);

                    }
                    break;

            }

            match(input,10,FOLLOW_10_in_question_add388);
            // Cami.g:163:43: (active= NUMBER )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NUMBER) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:163:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add392);

                    }
                    break;

            }

            match(input,8,FOLLOW_8_in_question_add395);


                        // TODO Veifier qu'on est dans la réception de menus
                        ArrayList<String> aq = new ArrayList<String>();
                        aq.add(parent_menu.getText()); /* parent  */
                        aq.add(entry_name.getText());  /* entry_name  */
                        aq.add(question_type.getText()); /* question_type  */
                        aq.add(question_behavior.getText()); /* question_behavior  */
                        aq.add(set_item.getText()); /* validation par defaut  */
                        aq.add(dialog.getText()); /* dialog autorisé ?  */
                        aq.add(stop_authorized.getText()); /* on autorise l'arrêt du service ? */
                        aq.add(output_formalism.getText()); /* formalisme */
                        aq.add(active.getText()); /* grisé ou non ? */

                        menuList.add(aq); /* ajouter à la liste de AQ */

                                    System.out.println("AQ( " + parent_menu.getText() +
                                    entry_name.getText() + ", " +
                                    question_type.getText() + ", " +
                                    question_behavior.getText() + ", " +
                                    set_item.getText() + ", " +
                                    dialog.getText() + ", " +
                                    stop_authorized.getText() + ", " +
                                    output_formalism.getText() + ", " +
                                    active.getText() + ")");



            }

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


    // $ANTLR start pecial_message
    // Cami.g:194:1: pecial_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void pecial_message() throws RecognitionException {
        try {
            // Cami.g:195:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:196:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,21,FOLLOW_21_in_pecial_message416);
            match(input,NUMBER,FOLLOW_NUMBER_in_pecial_message418);
            match(input,10,FOLLOW_10_in_pecial_message420);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_pecial_message422);
            match(input,8,FOLLOW_8_in_pecial_message424);

                        // verifier qu'on est dans la reception des menus ou autre
            			System.out.println("MOMOMOMOMOMOMO reçu");


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end pecial_message




    public static final BitSet FOLLOW_ack_open_communication_in_command32 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command36 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command44 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command48 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_ack_open_communication65 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication67 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_communication69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_ack_open_connection86 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection95 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_ack_open_connection104 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection113 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_connection121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ack_open_session139 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session141 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_session142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ack_open_session147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pecial_message_in_ack_open_session168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_interlocutor_table189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_interlocutor_table197 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table201 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interlocutor_table203 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table207 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interlocutor_table209 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table213 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interlocutor_table217 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table221 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_interlocutor_table224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_receving_menu259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_add_in_receving_menu271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_receving_menu275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_menu_name291 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name295 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_menu_name297 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name301 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_menu_name303 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name307 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_menu_name309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_question_add324 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add328 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add330 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add334 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add336 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_question_add343 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add346 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_question_add350 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add353 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_question_add360 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add363 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_question_add368 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add371 = new BitSet(new long[]{0x0000000000000420L});
    public static final BitSet FOLLOW_NUMBER_in_question_add375 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add378 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add385 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_question_add388 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_NUMBER_in_question_add392 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_question_add395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_pecial_message416 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_pecial_message418 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_pecial_message420 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_pecial_message422 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_pecial_message424 = new BitSet(new long[]{0x0000000000000002L});

}