// $ANTLR 3.0.1 Cami.g 2008-05-05 18:33:57

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'QQ('", "'MO('"
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

       ArrayList<ArrayList<String>> camiUpdatesList; /* liste servant à construire les objets 
                                                   Correspondant aux TQ 7 et 8 */

       HashMap<String, Object> hashObservable; /* Table de hash des observables */

       ISessionController sc; /* Controleur de la session */

       IFkInfo fkInfo; 

       IMenu menu;
       ArrayList<IMenu> menuList;

       ArrayList<IUpdateItem> updates;
       ArrayList<ArrayList<IUpdateItem>> updatesList;

      
       /* Constructeur du parser */
       public CamiParser(TokenStream input, ISessionController sessionController, 
                                                        HashMap<String, Object> hm) {
           this(input);
           hashObservable = hm;       
           sc = sessionController;
       }



    // $ANTLR start command
    // Cami.g:61:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:62:5: ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission )
            int alt2=6;
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
            case 15:
            case 16:
            case 17:
                {
                alt2=3;
                }
                break;
            case 18:
                {
                alt2=4;
                }
                break;
            case EOF:
            case 23:
                {
                alt2=5;
                }
                break;
            case 24:
                {
                alt2=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("61:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission );", 2, 0, input);

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
                    // Cami.g:65:6: ( question_state )*
                    {
                    // Cami.g:65:6: ( question_state )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==23) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Cami.g:65:6: question_state
                    	    {
                    	    pushFollow(FOLLOW_question_state_in_command78);
                    	    question_state();
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

            }
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
    // Cami.g:72:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:73:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:74:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication105); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication107); 
            match(input,9,FOLLOW_9_in_ack_open_communication109); 

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
    // Cami.g:86:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:87:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:88:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection127); 
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection136); 

                        listOfArgs.add(v1.getText());
                    
            match(input,11,FOLLOW_11_in_ack_open_connection145); 
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection154); 
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }
                    
            match(input,9,FOLLOW_9_in_ack_open_connection162); 

            }

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
    // Cami.g:106:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:107:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
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
            case 15:
            case 16:
            case 17:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("106:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:108:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,12,FOLLOW_12_in_ack_open_session182); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session184); 
                    match(input,9,FOLLOW_9_in_ack_open_session185); 

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");
                                
                                /* on initialise ici la table des menus et des mises a jour : on ne voit pas d'autre endroit ....*/
                                menuList = new ArrayList<IMenu>();
                                updatesList = new ArrayList<ArrayList<IUpdateItem>>();


                            

                    }
                    break;
                case 2 :
                    // Cami.g:118:3: 'TD()'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session190); 

                                // Ajouter un controle qu'on doit bien recevoir TD
                                System.out.println("TD");
                            

                    }
                    break;
                case 3 :
                    // Cami.g:122:3: 'FA()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session195); 
                    // Ajouter un controle qu'on doit bien recevoir FA}
                                System.out.println("FA");
                            

                    }
                    break;
                case 4 :
                    // Cami.g:126:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session204);
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


    // $ANTLR start interlocutor_table
    // Cami.g:134:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:135:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt4=1;
                }
                break;
            case 16:
                {
                alt4=2;
                }
                break;
            case 17:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("134:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:136:5: 'TL()'
                    {
                    match(input,15,FOLLOW_15_in_interlocutor_table228); 
                                
                            

                    }
                    break;
                case 2 :
                    // Cami.g:138:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,16,FOLLOW_16_in_interlocutor_table236); 
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table240); 
                    match(input,11,FOLLOW_11_in_interlocutor_table242); 
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table246); 
                    match(input,11,FOLLOW_11_in_interlocutor_table248); 
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table252); 
                    match(input,11,FOLLOW_11_in_interlocutor_table269); 
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table273); 
                    match(input,9,FOLLOW_9_in_interlocutor_table276); 

                               
                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());            
                            

                    }
                    break;
                case 3 :
                    // Cami.g:147:6: 'FL()'
                    {
                    match(input,17,FOLLOW_17_in_interlocutor_table285); 

                                fkInfo = CamiObjectBuilder.buildFkInfo(listOfArgs);

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
    // Cami.g:161:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:162:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:163:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,18,FOLLOW_18_in_receving_menu308); 

                        /* créer la menuList  */
                        camiMenuList = new ArrayList<ArrayList<String>>();

                        /* créer la liste des updates  */
                        camiUpdatesList = new ArrayList<ArrayList<String>>();
                    
            pushFollow(FOLLOW_menu_name_in_receving_menu312);
            menu_name();
            _fsp--;

            // Cami.g:171:2: ( question_add )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==22) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Cami.g:171:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu315);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,19,FOLLOW_19_in_receving_menu319); 

                        /* fin de la reception des menus : demander la construction du menu */            
                        menu = CamiObjectBuilder.buildMenu(camiMenuList);
                        menuList.add(menu);
                        
                        System.out.println("Menu construit");
                        System.out.println("FQ()");
                    
            match(input,20,FOLLOW_20_in_receving_menu326); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu327); 
            match(input,9,FOLLOW_9_in_receving_menu328); 
             /* afficher les questions */
                        System.out.println("VQ");
                        
                        /* construire la liste des updates */
                        updates = CamiObjectBuilder.buildUpdateItem(camiUpdatesList);
                        updatesList.add(updates);

                    

            }

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
    // Cami.g:193:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:194:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:195:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,21,FOLLOW_21_in_menu_name345); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name349); 
            match(input,11,FOLLOW_11_in_menu_name351); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name355); 
            match(input,11,FOLLOW_11_in_menu_name357); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name361); 
            match(input,9,FOLLOW_9_in_menu_name363); 

                 
                        // TODO :  Veifier qu'on est dans la réception de menus racine !!!

                        /* racine des question  */
                        ArrayList<String> cq = new ArrayList<String>();
                        cq.add(name.getText()); /* racine  */
                        cq.add(question_type.getText()); /* type de la question  */
                        cq.add(question_behavior.getText()); /* type du choix */

                        camiMenuList.add(cq); /* ajouter a la liste des AQ */

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
    // Cami.g:217:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:218:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:219:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,22,FOLLOW_22_in_question_add379); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add383); 
            match(input,11,FOLLOW_11_in_question_add385); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add389); 
            match(input,11,FOLLOW_11_in_question_add391); 
            // Cami.g:220:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:220:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add398); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add401); 
            // Cami.g:220:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:220:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add405); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add408); 
            // Cami.g:221:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:221:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add415); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add418); 
            // Cami.g:221:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:221:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add423); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add426); 
            // Cami.g:221:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:221:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add430); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add433); 
            // Cami.g:222:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:222:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add440); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add443); 
            // Cami.g:222:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:222:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add447); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add450); 


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

                                    System.out.print("AQ(" + aq.get(0));
                                    for(int i=1; i<9; i++){
                                        System.out.print(", " + aq.get(i));
                                    }
                                    System.out.println(")");

                    

            }

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


    // $ANTLR start question_state
    // Cami.g:284:1: question_state : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' ;
    public final void question_state() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:285:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:286:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')'
            {
            match(input,23,FOLLOW_23_in_question_state471); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state475); 
            match(input,11,FOLLOW_11_in_question_state477); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state481); 
            match(input,11,FOLLOW_11_in_question_state483); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_question_state487); 
            match(input,11,FOLLOW_11_in_question_state489); 
            // Cami.g:286:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:286:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state493); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_state496); 

                        
                     
                        /*  */
                        ArrayList<String> update = new ArrayList<String>();
                       
                        update.add(service_name.getText()); /* nom du service */
                        update.add(question_name.getText());  /* nom de la question  */
                        update.add(state.getText());  /* état de la question  */

                        if(!state.getText().equals("7") && !state.getText().equals("8")) /* si c'est un modificateur de menu */
                            update.add(mess.getText()); /* message : optionnel */          


                        camiUpdatesList.add(update);/* ajouter à la liste des updates  */
                        
                        System.out.println("TQ(" + service_name.getText() + ", " + question_name.getText() + ", " + 
                                                    state.getText() + ", " + ")");
                    

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end question_state


    // $ANTLR start end_menu_transmission
    // Cami.g:309:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER2=null;

        try {
            // Cami.g:310:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:311:2: 'QQ(' NUMBER ')'
            {
            match(input,24,FOLLOW_24_in_end_menu_transmission515); 
            NUMBER2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission517); 
            match(input,9,FOLLOW_9_in_end_menu_transmission519); 

                        System.out.println("QQ(" + NUMBER2.getText() + ")");
                        ((ISessionObservable)hashObservable.get("ISession")).notifyObservers(fkInfo, menuList, updatesList);
                    

            }

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


    // $ANTLR start pecial_message
    // Cami.g:323:1: pecial_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void pecial_message() throws RecognitionException {
        try {
            // Cami.g:324:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:325:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_pecial_message547); 
            match(input,NUMBER,FOLLOW_NUMBER_in_pecial_message549); 
            match(input,11,FOLLOW_11_in_pecial_message551); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_pecial_message553); 
            match(input,9,FOLLOW_9_in_pecial_message555); 

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
    // $ANTLR end pecial_message


 

    public static final BitSet FOLLOW_ack_open_communication_in_command55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_command78 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication105 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication107 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection127 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection136 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection145 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection154 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ack_open_session182 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session184 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_interlocutor_table228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table236 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table240 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table242 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table246 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table248 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table252 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table269 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table273 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_interlocutor_table285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_receving_menu308 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu312 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu315 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_19_in_receving_menu319 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_receving_menu326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu327 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_menu_name345 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name349 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name351 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name355 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name357 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name361 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_question_add379 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add383 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add385 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add389 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add391 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add398 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add401 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add405 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add408 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add415 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add418 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add423 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add426 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add430 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add433 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add440 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add443 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add447 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_question_state471 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state475 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state477 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state481 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state483 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_state487 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state489 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state493 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_state496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_end_menu_transmission515 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission517 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_pecial_message547 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_pecial_message549 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pecial_message551 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_pecial_message553 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pecial_message555 = new BitSet(new long[]{0x0000000000000002L});

}