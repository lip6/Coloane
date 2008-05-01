// $ANTLR 3.0.1 Cami.g 2008-05-02 01:29:57

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
    // Cami.g:42:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:43:5: ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission )
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
                    new NoViableAltException("42:1: command : ( ack_open_communication | ack_open_connection | ack_open_session | receving_menu | ( question_state )* | end_menu_transmission );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:44:5: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command35);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:44:30: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command39);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:45:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command47);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:45:26: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command51);
                    receving_menu();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:46:6: ( question_state )*
                    {
                    // Cami.g:46:6: ( question_state )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==23) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Cami.g:46:6: question_state
                    	    {
                    	    pushFollow(FOLLOW_question_state_in_command58);
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
                    // Cami.g:47:6: end_menu_transmission
                    {
                    pushFollow(FOLLOW_end_menu_transmission_in_command66);
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
    // Cami.g:52:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:53:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:54:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication84); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication86); 
            match(input,9,FOLLOW_9_in_ack_open_communication88); 

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
    // Cami.g:65:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:66:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:67:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection105); 
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection114); 

                        listOfArgs.add(v1.getText());
                    
            match(input,11,FOLLOW_11_in_ack_open_connection123); 
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection132); 
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }
                    
            match(input,9,FOLLOW_9_in_ack_open_connection140); 

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
    // Cami.g:83:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:84:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
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
                    new NoViableAltException("83:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:85:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,12,FOLLOW_12_in_ack_open_session158); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session160); 
                    match(input,9,FOLLOW_9_in_ack_open_session161); 

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");
                            

                    }
                    break;
                case 2 :
                    // Cami.g:89:3: 'TD()'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session166); 

                                // Ajouter un controle qu'on doit bien recevoir TD
                                System.out.println("TD");
                            

                    }
                    break;
                case 3 :
                    // Cami.g:93:3: 'FA()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session171); 
                    // Ajouter un controle qu'on doit bien recevoir FA}
                                System.out.println("FA");
                            

                    }
                    break;
                case 4 :
                    // Cami.g:97:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session180);
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
    // Cami.g:102:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:103:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
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
                    new NoViableAltException("102:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:104:5: 'TL()'
                    {
                    match(input,15,FOLLOW_15_in_interlocutor_table201); 
                                
                            

                    }
                    break;
                case 2 :
                    // Cami.g:106:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,16,FOLLOW_16_in_interlocutor_table209); 
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table213); 
                    match(input,11,FOLLOW_11_in_interlocutor_table215); 
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table219); 
                    match(input,11,FOLLOW_11_in_interlocutor_table221); 
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table225); 
                    match(input,11,FOLLOW_11_in_interlocutor_table229); 
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table233); 
                    match(input,9,FOLLOW_9_in_interlocutor_table236); 

                               
                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());            
                            

                    }
                    break;
                case 3 :
                    // Cami.g:114:6: 'FL()'
                    {
                    match(input,17,FOLLOW_17_in_interlocutor_table245); 

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
    // Cami.g:128:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:129:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:130:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,18,FOLLOW_18_in_receving_menu268); 

                        /* créer la menuList  */
                        menuList = new ArrayList<ArrayList<String>>();
                    
            pushFollow(FOLLOW_menu_name_in_receving_menu272);
            menu_name();
            _fsp--;

            // Cami.g:135:2: ( question_add )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==22) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Cami.g:135:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu275);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,19,FOLLOW_19_in_receving_menu279); 

                        /* fin de la reception des menus : demander la construction du menu */            
                        //            menu = CamiObjectBuilder.buildMenu(menuList);
                        System.out.println("FQ()");
                    
            match(input,20,FOLLOW_20_in_receving_menu286); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu287); 
            match(input,9,FOLLOW_9_in_receving_menu288); 

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
    // Cami.g:148:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:149:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:150:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,21,FOLLOW_21_in_menu_name304); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name308); 
            match(input,11,FOLLOW_11_in_menu_name310); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name314); 
            match(input,11,FOLLOW_11_in_menu_name316); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name320); 
            match(input,9,FOLLOW_9_in_menu_name322); 

                 
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
    // Cami.g:171:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:172:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:173:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,22,FOLLOW_22_in_question_add337); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add341); 
            match(input,11,FOLLOW_11_in_question_add343); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add347); 
            match(input,11,FOLLOW_11_in_question_add349); 
            // Cami.g:174:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:174:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add356); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add359); 
            // Cami.g:174:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:174:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add363); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add366); 
            // Cami.g:175:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:175:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add373); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add376); 
            // Cami.g:175:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:175:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add381); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add384); 
            // Cami.g:175:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:175:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add388); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add391); 
            // Cami.g:176:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:176:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add398); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add401); 
            // Cami.g:176:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:176:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add405); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add408); 


                        // TODO Veifier qu'on est dans la réception de menus
                        ArrayList<String> aq = new ArrayList<String>();
                        aq.add(parent_menu.getText()); /* parent  */
                        aq.add(entry_name.getText());  /* entry_name  */
                        
                        if(question_type != null)
                            aq.add(question_type.getText()); /* question_type  */
                        else
                            aq.add(new String(""));

                        if(question_behavior != null)
                            aq.add(question_behavior.getText()); /* question_behavior  */
                        else
                            aq.add(new String(""));

                        if(set_item != null)
                            aq.add(set_item.getText()); /* validation par defaut  */
                        else
                            aq.add(new String(""));
                        
                        if(dialog != null)
                            aq.add(dialog.getText()); /* dialog autorisé ?  */
                        else
                            aq.add(new String(""));


                        if(stop_authorized != null)
                            aq.add(stop_authorized.getText()); /* on autorise l'arrêt du service ? */
                        else
                            aq.add(new String(""));


                        if(output_formalism != null)
                            aq.add(output_formalism.getText()); /* formalisme */
                        else
                            aq.add(new String(""));


                        if(active != null)
                            aq.add(active.getText()); /* grisé ou non ? */
                        else
                            aq.add(new String(""));


                        menuList.add(aq); /* ajouter à la liste de AQ */

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
    // Cami.g:234:1: question_state : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' ;
    public final void question_state() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:235:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:236:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')'
            {
            match(input,23,FOLLOW_23_in_question_state424); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state428); 
            match(input,11,FOLLOW_11_in_question_state430); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state434); 
            match(input,11,FOLLOW_11_in_question_state436); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_question_state440); 
            match(input,11,FOLLOW_11_in_question_state442); 
            // Cami.g:236:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:236:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state446); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_state449); 

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
    // Cami.g:244:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER2=null;

        try {
            // Cami.g:245:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:246:2: 'QQ(' NUMBER ')'
            {
            match(input,24,FOLLOW_24_in_end_menu_transmission465); 
            NUMBER2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission467); 
            match(input,9,FOLLOW_9_in_end_menu_transmission469); 

                        System.out.println("QQ(" + NUMBER2.getText() + ")");
                    

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
    // Cami.g:257:1: pecial_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void pecial_message() throws RecognitionException {
        try {
            // Cami.g:258:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:259:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_pecial_message494); 
            match(input,NUMBER,FOLLOW_NUMBER_in_pecial_message496); 
            match(input,11,FOLLOW_11_in_pecial_message498); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_pecial_message500); 
            match(input,9,FOLLOW_9_in_pecial_message502); 

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


 

    public static final BitSet FOLLOW_ack_open_communication_in_command35 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command47 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command51 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_command58 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication84 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication86 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication88 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection105 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection114 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection123 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection132 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ack_open_session158 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session160 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_interlocutor_table201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table209 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table213 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table215 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table219 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table221 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table225 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table229 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table233 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_interlocutor_table245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_receving_menu268 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu272 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu275 = new BitSet(new long[]{0x0000000000480000L});
    public static final BitSet FOLLOW_19_in_receving_menu279 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_receving_menu286 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu287 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_menu_name304 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name308 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name310 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name314 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name316 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name320 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_question_add337 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add341 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add343 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add347 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add349 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add356 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add359 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add363 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add366 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add373 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add376 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add381 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add384 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add388 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add391 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add398 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add401 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add405 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_question_state424 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state428 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state430 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state434 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state436 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_state440 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state442 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state446 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_state449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_end_menu_transmission465 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission467 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_pecial_message494 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_pecial_message496 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_pecial_message498 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_pecial_message500 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_pecial_message502 = new BitSet(new long[]{0x0000000000000002L});

}