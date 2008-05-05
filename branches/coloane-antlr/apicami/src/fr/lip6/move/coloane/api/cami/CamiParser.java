// $ANTLR 3.0.1 Cami.g 2008-05-05 20:11:19

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
    // Cami.g:61:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission | special_message );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:62:5: ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission | special_message )
            int alt2=7;
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
            case 25:
                {
                alt2=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("61:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission | special_message );", 2, 0, input);

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
                case 7 :
                    // Cami.g:67:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_command93);
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
    // $ANTLR end command


    // $ANTLR start ack_open_communication
    // Cami.g:73:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:74:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:75:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication112); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication114); 
            match(input,9,FOLLOW_9_in_ack_open_communication116); 

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
    // Cami.g:87:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:88:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:89:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection134); 
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection143); 

                        listOfArgs.add(v1.getText());
                    
            match(input,11,FOLLOW_11_in_ack_open_connection152); 
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection161); 
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }
                    
            match(input,9,FOLLOW_9_in_ack_open_connection169); 

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
    // Cami.g:107:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:108:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
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
                    new NoViableAltException("107:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:109:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,12,FOLLOW_12_in_ack_open_session189); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session191); 
                    match(input,9,FOLLOW_9_in_ack_open_session192); 

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");
                                
                                /* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
                                menuList = new ArrayList<IMenu>();
                                /*  */
                                camiUpdates = new ArrayList<ArrayList<String>>();
                                


                            

                    }
                    break;
                case 2 :
                    // Cami.g:121:3: 'TD()'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session197); 

                                // Ajouter un controle qu'on doit bien recevoir TD
                    //            System.out.println("TD");
                            

                    }
                    break;
                case 3 :
                    // Cami.g:125:3: 'FA()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session202); 
                    // Ajouter un controle qu'on doit bien recevoir FA}
                    //            System.out.println("FA");
                            

                    }
                    break;
                case 4 :
                    // Cami.g:129:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session211);
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
    // Cami.g:137:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:138:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
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
                    new NoViableAltException("137:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:139:5: 'TL()'
                    {
                    match(input,15,FOLLOW_15_in_interlocutor_table235); 
                                
                            

                    }
                    break;
                case 2 :
                    // Cami.g:141:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,16,FOLLOW_16_in_interlocutor_table243); 
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table247); 
                    match(input,11,FOLLOW_11_in_interlocutor_table249); 
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table253); 
                    match(input,11,FOLLOW_11_in_interlocutor_table255); 
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table259); 
                    match(input,11,FOLLOW_11_in_interlocutor_table276); 
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table280); 
                    match(input,9,FOLLOW_9_in_interlocutor_table283); 

                               
                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());            
                            

                    }
                    break;
                case 3 :
                    // Cami.g:150:6: 'FL()'
                    {
                    match(input,17,FOLLOW_17_in_interlocutor_table292); 

                                fkInfo = CamiObjectBuilder.buildFkInfo(listOfArgs);

                                System.out.println("fkinfo");
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
    // Cami.g:164:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:165:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:166:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,18,FOLLOW_18_in_receving_menu315); 

                        /* créer la menuList  */
                        camiMenuList = new ArrayList<ArrayList<String>>();
                    
            pushFollow(FOLLOW_menu_name_in_receving_menu319);
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
            	    pushFollow(FOLLOW_question_add_in_receving_menu322);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,19,FOLLOW_19_in_receving_menu326); 

                        /* fin de la reception des menus : demander la construction du menu */            
                        menu = CamiObjectBuilder.buildMenu(camiMenuList);
                        menuList.add(menu);
                        
            //            System.out.println("Menu construit");
            //            System.out.println("FQ()");
                    
            match(input,20,FOLLOW_20_in_receving_menu333); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu334); 
            match(input,9,FOLLOW_9_in_receving_menu335); 
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
    // Cami.g:189:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:190:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:191:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,21,FOLLOW_21_in_menu_name352); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name356); 
            match(input,11,FOLLOW_11_in_menu_name358); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name362); 
            match(input,11,FOLLOW_11_in_menu_name364); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name368); 
            match(input,9,FOLLOW_9_in_menu_name370); 

                 
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
    // Cami.g:213:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:214:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:215:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,22,FOLLOW_22_in_question_add386); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add390); 
            match(input,11,FOLLOW_11_in_question_add392); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add396); 
            match(input,11,FOLLOW_11_in_question_add398); 
            // Cami.g:216:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:216:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add405); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add408); 
            // Cami.g:216:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:216:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add412); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add415); 
            // Cami.g:217:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:217:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add422); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add425); 
            // Cami.g:217:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:217:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add430); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add433); 
            // Cami.g:217:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:217:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add437); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add440); 
            // Cami.g:218:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:218:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add447); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add450); 
            // Cami.g:218:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:218:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add454); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add457); 


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


    // $ANTLR start question_state
    // Cami.g:280:1: question_state : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' ;
    public final void question_state() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:281:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:282:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')'
            {
            match(input,23,FOLLOW_23_in_question_state478); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state482); 
            match(input,11,FOLLOW_11_in_question_state484); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state488); 
            match(input,11,FOLLOW_11_in_question_state490); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_question_state494); 
            match(input,11,FOLLOW_11_in_question_state496); 
            // Cami.g:282:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:282:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state500); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_state503); 

                        
                     
                        /*  */
                        ArrayList<String> update = new ArrayList<String>();
                       
                        update.add(service_name.getText()); /* nom du service */
                        update.add(question_name.getText());  /* nom de la question  */
                        update.add(state.getText());  /* état de la question  */

                        if(!state.getText().equals("7") && !state.getText().equals("8")) /* si c'est un modificateur de menu */
                            update.add(mess.getText()); /* message : optionnel */          

                        
                        camiUpdates.add(update);/* ajouter à la liste des updates  */
                        
            //            System.out.println("TQ(" + service_name.getText() + ", " + question_name.getText() + ", " + 
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
    // $ANTLR end question_state


    // $ANTLR start end_menu_transmission
    // Cami.g:305:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER2=null;

        try {
            // Cami.g:306:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:307:2: 'QQ(' NUMBER ')'
            {
            match(input,24,FOLLOW_24_in_end_menu_transmission522); 
            NUMBER2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission524); 
            match(input,9,FOLLOW_9_in_end_menu_transmission526); 

                        
            //            System.out.println("QQ(" + NUMBER2.getText() + ")");
                        
                        
                        updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
                        ((ISessionObservable)hashObservable.get("ISession")).notifyObservers(fkInfo, menuList, updates);
                    

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
    // Cami.g:323:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        try {
            // Cami.g:324:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:325:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_special_message554); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message556); 
            match(input,11,FOLLOW_11_in_special_message558); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message560); 
            match(input,9,FOLLOW_9_in_special_message562); 

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
    public static final BitSet FOLLOW_question_state_in_command78 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_command93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication112 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication114 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection134 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection143 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection152 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection161 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ack_open_session189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session191 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_interlocutor_table235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table243 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table247 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table249 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table253 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table255 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table259 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table276 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table280 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_interlocutor_table292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_receving_menu315 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu319 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu322 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_19_in_receving_menu326 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_receving_menu333 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu334 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_menu_name352 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name356 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name358 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name362 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name364 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name368 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_question_add386 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add390 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add392 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add396 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add398 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add405 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add408 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add412 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add415 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add422 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add425 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add430 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add433 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add437 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add440 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add447 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add450 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add454 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_question_state478 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state482 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state484 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state488 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state490 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_state494 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state496 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state500 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_state503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_end_menu_transmission522 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission524 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_special_message554 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message556 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message558 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message560 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message562 = new BitSet(new long[]{0x0000000000000002L});

}