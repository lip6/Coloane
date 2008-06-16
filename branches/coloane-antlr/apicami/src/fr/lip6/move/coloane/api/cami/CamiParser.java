// $ANTLR 3.0.1 Cami.g 2008-06-16 14:50:12

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'FS('", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'TR('", "'WN('", "'MO('", "'KO(1,'", "'DF(-2,'", "'DR()'", "'RQ('", "'ZA('", "'DE('", "'FE()'", "'DE()'", "'RT('", "'RO('", "'ME('", "'MT('", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'SU('", "'SI('", "'TD('", "'OB('", "'AT('", "'DB()'", "'FB()'", "'PO('", "'pO('", "'DC()'", "'AD('", "'DS('", "'CE('", "'FF('"
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
    public final void command() throws RecognitionException, IOException {
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
            case 32:
            case 33:
                {
                alt2=12;
                }
                break;
            case 34:
                {
                alt2=13;
                }
                break;
            case 36:
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
            //            System.out.println("FQ");
                    
            match(input,24,FOLLOW_24_in_receving_menu497); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu498); 
            match(input,9,FOLLOW_9_in_receving_menu499); 
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
            match(input,25,FOLLOW_25_in_menu_name516); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name520); 
            match(input,11,FOLLOW_11_in_menu_name522); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name526); 
            match(input,11,FOLLOW_11_in_menu_name528); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name532); 
            match(input,9,FOLLOW_9_in_menu_name534); 

                 
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
            match(input,26,FOLLOW_26_in_question_add550); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add554); 
            match(input,11,FOLLOW_11_in_question_add556); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add560); 
            match(input,11,FOLLOW_11_in_question_add562); 
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
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add569); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add572); 
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
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add576); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add579); 
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
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add586); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add589); 
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
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add594); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add597); 
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
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add601); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add604); 
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
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add611); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add614); 
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
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add618); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add621); 

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
            match(input,27,FOLLOW_27_in_update642); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update646); 
            match(input,11,FOLLOW_11_in_update648); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update652); 
            match(input,11,FOLLOW_11_in_update654); 
            state=(Token)input.LT(1);
            if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update658);    throw mse;
            }

            match(input,11,FOLLOW_11_in_update664); 
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
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update668); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_update671); 

                        
                        
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
            match(input,30,FOLLOW_30_in_end_menu_transmission697); 
            NUMBER3=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission699); 
            match(input,9,FOLLOW_9_in_end_menu_transmission701); 

                        
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
                    pushFollow(FOLLOW_trace_message_in_message_to_user731);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:383:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user735);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:383:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user739);
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
            match(input,31,FOLLOW_31_in_trace_message751); 
            CAMI_STRING4=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message753); 
            match(input,9,FOLLOW_9_in_trace_message755); 

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
            match(input,32,FOLLOW_32_in_warning_message768); 
            CAMI_STRING5=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message770); 
            match(input,9,FOLLOW_9_in_warning_message772); 

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
            match(input,33,FOLLOW_33_in_special_message786); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message788); 
            match(input,11,FOLLOW_11_in_special_message790); 
            CAMI_STRING6=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message792); 
            match(input,9,FOLLOW_9_in_special_message794); 

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
            match(input,34,FOLLOW_34_in_brutal_interrupt814); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_brutal_interrupt818); 
            match(input,11,FOLLOW_11_in_brutal_interrupt820); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_brutal_interrupt824); 
            match(input,9,FOLLOW_9_in_brutal_interrupt826); 

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
    public final void ask_for_a_model() throws RecognitionException, IOException {
        try {
            // Cami.g:425:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // Cami.g:426:5: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,35,FOLLOW_35_in_ask_for_a_model901); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model903); 
            match(input,11,FOLLOW_11_in_ask_for_a_model905); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model907); 
            match(input,9,FOLLOW_9_in_ask_for_a_model909); 

                     System.out.println("je parse le DF");
                    ((IAskForModelObservable)hashObservable.get("IAskForModel")).notifyObservers();
                 

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
    // Cami.g:460:1: result_reception : 'DR()' 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' ( resultat )* ;
    public final void result_reception() throws RecognitionException {
        Token service_name1=null;
        Token question_name1=null;
        Token num1=null;

        try {
            // Cami.g:461:8: ( 'DR()' 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' ( resultat )* )
            // Cami.g:462:9: 'DR()' 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' ( resultat )*
            {
            match(input,36,FOLLOW_36_in_result_reception939); 
             
                        // initialiser la liste des updates 
                    //    camiUpdates = new ArrayList<ArrayList<String>>();
                      System.out.println("je parse DR");
                    
            match(input,37,FOLLOW_37_in_result_reception949); 
            service_name1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception953); 
            match(input,11,FOLLOW_11_in_result_reception955); 
            question_name1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception959); 
            match(input,11,FOLLOW_11_in_result_reception961); 
            num1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result_reception965); 
            match(input,9,FOLLOW_9_in_result_reception967); 

                    System.out.println("je parse RQ"); 
                    
            // Cami.g:470:9: ( resultat )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==EOF||LA15_0==27||(LA15_0>=31 && LA15_0<=33)||(LA15_0>=38 && LA15_0<=39)||LA15_0==53||LA15_0==56||(LA15_0>=60 && LA15_0<=64)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // Cami.g:470:9: resultat
            	    {
            	    pushFollow(FOLLOW_resultat_in_result_reception979);
            	    resultat();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


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


    // $ANTLR start resultat
    // Cami.g:482:1: resultat : ( ( message_utils )* | domaine_table | dialogue | modele | ( result )* );
    public final void resultat() throws RecognitionException {
        try {
            // Cami.g:483:4: ( ( message_utils )* | domaine_table | dialogue | modele | ( result )* )
            int alt18=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt18=1;
                }
                break;
            case 32:
                {
                alt18=1;
                }
                break;
            case 33:
                {
                alt18=1;
                }
                break;
            case 38:
                {
                alt18=1;
                }
                break;
            case 27:
                {
                alt18=1;
                }
                break;
            case EOF:
                {
                alt18=1;
                }
                break;
            case 53:
                {
                alt18=1;
                }
                break;
            case 62:
                {
                alt18=1;
                }
                break;
            case 63:
                {
                alt18=1;
                }
                break;
            case 64:
                {
                alt18=1;
                }
                break;
            case 60:
                {
                alt18=1;
                }
                break;
            case 61:
                {
                alt18=1;
                }
                break;
            case 56:
                {
                alt18=1;
                }
                break;
            case 39:
                {
                alt18=1;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("482:1: resultat : ( ( message_utils )* | domaine_table | dialogue | modele | ( result )* );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // Cami.g:484:10: ( message_utils )*
                    {
                    // Cami.g:484:10: ( message_utils )*
                    loop16:
                    do {
                        int alt16=2;
                        switch ( input.LA(1) ) {
                        case 31:
                            {
                            alt16=1;
                            }
                            break;
                        case 32:
                            {
                            alt16=1;
                            }
                            break;
                        case 33:
                            {
                            alt16=1;
                            }
                            break;
                        case 38:
                            {
                            alt16=1;
                            }
                            break;
                        case 27:
                            {
                            alt16=1;
                            }
                            break;

                        }

                        switch (alt16) {
                    	case 1 :
                    	    // Cami.g:484:10: message_utils
                    	    {
                    	    pushFollow(FOLLOW_message_utils_in_resultat1020);
                    	    message_utils();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // Cami.g:485:10: domaine_table
                    {
                    pushFollow(FOLLOW_domaine_table_in_resultat1032);
                    domaine_table();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:486:10: dialogue
                    {
                    pushFollow(FOLLOW_dialogue_in_resultat1043);
                    dialogue();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:487:10: modele
                    {
                    pushFollow(FOLLOW_modele_in_resultat1054);
                    modele();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:488:10: ( result )*
                    {
                    // Cami.g:488:10: ( result )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==39) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // Cami.g:488:10: result
                    	    {
                    	    pushFollow(FOLLOW_result_in_resultat1065);
                    	    result();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


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
    // $ANTLR end resultat


    // $ANTLR start message_utils
    // Cami.g:491:1: message_utils : ( trace_message2 | warning_message2 | special_message2 | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' );
    public final void message_utils() throws RecognitionException {
        Token service_name2=null;
        Token question_name2=null;
        Token state2=null;
        Token mess2=null;

        try {
            // Cami.g:492:11: ( trace_message2 | warning_message2 | special_message2 | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' )
            int alt20=5;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt20=1;
                }
                break;
            case 32:
                {
                alt20=2;
                }
                break;
            case 33:
                {
                alt20=3;
                }
                break;
            case 38:
                {
                alt20=4;
                }
                break;
            case 27:
                {
                alt20=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("491:1: message_utils : ( trace_message2 | warning_message2 | special_message2 | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' );", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // Cami.g:493:11: trace_message2
                    {
                    pushFollow(FOLLOW_trace_message2_in_message_utils1102);
                    trace_message2();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:494:13: warning_message2
                    {
                    pushFollow(FOLLOW_warning_message2_in_message_utils1117);
                    warning_message2();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:495:13: special_message2
                    {
                    pushFollow(FOLLOW_special_message2_in_message_utils1132);
                    special_message2();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:496:13: 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,38,FOLLOW_38_in_message_utils1146); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1147); 
                    match(input,11,FOLLOW_11_in_message_utils1149); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1151); 
                    match(input,11,FOLLOW_11_in_message_utils1153); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1155); 
                    match(input,11,FOLLOW_11_in_message_utils1157); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1159); 
                    match(input,11,FOLLOW_11_in_message_utils1161); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1163); 
                    match(input,9,FOLLOW_9_in_message_utils1165); 

                              System.out.println("je parse ZA");
                              

                    }
                    break;
                case 5 :
                    // Cami.g:499:12: 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')'
                    {
                    match(input,27,FOLLOW_27_in_message_utils1179); 
                    service_name2=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_message_utils1183); 
                    match(input,11,FOLLOW_11_in_message_utils1185); 
                    question_name2=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_message_utils1189); 
                    match(input,11,FOLLOW_11_in_message_utils1191); 
                    state2=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_message_utils1195); 
                    match(input,11,FOLLOW_11_in_message_utils1198); 
                    // Cami.g:499:131: (mess2= CAMI_STRING )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==CAMI_STRING) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // Cami.g:499:131: mess2= CAMI_STRING
                            {
                            mess2=(Token)input.LT(1);
                            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_message_utils1202); 

                            }
                            break;

                    }

                    match(input,9,FOLLOW_9_in_message_utils1205); 
                     
                                
                              //  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
                              System.out.println("je parse TQ2");
                              

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
    // Cami.g:507:1: trace_message2 : 'TR(' CAMI_STRING ')' ;
    public final void trace_message2() throws RecognitionException {
        Token CAMI_STRING7=null;

        try {
            // Cami.g:508:2: ( 'TR(' CAMI_STRING ')' )
            // Cami.g:509:2: 'TR(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message21227); 
            CAMI_STRING7=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message21229); 
            match(input,9,FOLLOW_9_in_trace_message21231); 

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
    // Cami.g:515:1: warning_message2 : 'WN(' CAMI_STRING ')' ;
    public final void warning_message2() throws RecognitionException {
        Token CAMI_STRING8=null;

        try {
            // Cami.g:516:2: ( 'WN(' CAMI_STRING ')' )
            // Cami.g:517:2: 'WN(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message21244); 
            CAMI_STRING8=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message21246); 
            match(input,9,FOLLOW_9_in_warning_message21248); 

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
    // Cami.g:523:1: special_message2 : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message2() throws RecognitionException {
        Token CAMI_STRING9=null;

        try {
            // Cami.g:524:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:525:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message21262); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message21264); 
            match(input,11,FOLLOW_11_in_special_message21266); 
            CAMI_STRING9=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message21268); 
            match(input,9,FOLLOW_9_in_special_message21270); 

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
    // Cami.g:532:1: result : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // Cami.g:533:4: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' )
            // Cami.g:535:8: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,39,FOLLOW_39_in_result1294); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1298); 
            match(input,11,FOLLOW_11_in_result1300); 
            ensemble_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result1304); 
            match(input,9,FOLLOW_9_in_result1306); 

                     System.out.println("je parse DE"); 
                     
            // Cami.g:538:9: ( result_body )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==39||(LA21_0>=42 && LA21_0<=52)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // Cami.g:538:9: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result1317);
            	    result_body();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);

            match(input,40,FOLLOW_40_in_result1321); 

                     System.out.println("je parse FE"); 
                     

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


    // $ANTLR start debut
    // Cami.g:544:1: debut : ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );
    public final void debut() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // Cami.g:545:3: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==39) ) {
                alt22=1;
            }
            else if ( (LA22_0==41) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("544:1: debut : ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // Cami.g:546:9: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')'
                    {
                    match(input,39,FOLLOW_39_in_debut1341); 
                    ensemble_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_debut1345); 
                    match(input,11,FOLLOW_11_in_debut1347); 
                    ensemble_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_debut1351); 
                    match(input,9,FOLLOW_9_in_debut1353); 

                             System.out.println("je parse DE"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:549:10: 'DE()'
                    {
                    match(input,41,FOLLOW_41_in_debut1365); 

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
    // $ANTLR end debut


    // $ANTLR start result_body
    // Cami.g:554:1: result_body : ( textual_result | result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // Cami.g:555:3: ( textual_result | result | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt23=7;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt23=1;
                }
                break;
            case 39:
                {
                alt23=2;
                }
                break;
            case 43:
                {
                alt23=3;
                }
                break;
            case 44:
                {
                alt23=4;
                }
                break;
            case 45:
                {
                alt23=5;
                }
                break;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                {
                alt23=6;
                }
                break;
            case 51:
            case 52:
                {
                alt23=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("554:1: result_body : ( textual_result | result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // Cami.g:556:10: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1386);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:557:10: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1397);
                    result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:558:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1403);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:559:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1409);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:560:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1415);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:561:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1421);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // Cami.g:562:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1427);
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
    // Cami.g:565:1: textual_result : 'RT(' CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        try {
            // Cami.g:566:3: ( 'RT(' CAMI_STRING ')' )
            // Cami.g:567:3: 'RT(' CAMI_STRING ')'
            {
            match(input,42,FOLLOW_42_in_textual_result1443); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1445); 
            match(input,9,FOLLOW_9_in_textual_result1447); 

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
    // Cami.g:572:1: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:573:3: ( 'RO(' id= NUMBER ')' )
            // Cami.g:574:3: 'RO(' id= NUMBER ')'
            {
            match(input,43,FOLLOW_43_in_object_designation1463); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1467); 
            match(input,9,FOLLOW_9_in_object_designation1469); 

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
    // Cami.g:579:1: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:580:3: ( 'ME(' id= NUMBER ')' )
            // Cami.g:581:3: 'ME(' id= NUMBER ')'
            {
            match(input,44,FOLLOW_44_in_object_outline1485); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1489); 
            match(input,9,FOLLOW_9_in_object_outline1491); 

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
    // Cami.g:586:1: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token begin=null;
        Token end=null;

        try {
            // Cami.g:587:7: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // Cami.g:588:3: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,45,FOLLOW_45_in_attribute_outline1511); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1515); 
            match(input,11,FOLLOW_11_in_attribute_outline1517); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1521); 
            match(input,11,FOLLOW_11_in_attribute_outline1523); 
            // Cami.g:588:54: (begin= NUMBER )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // Cami.g:588:54: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1527); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1530); 
            // Cami.g:588:70: (end= NUMBER )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // Cami.g:588:70: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1534); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_attribute_outline1537); 

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
    // Cami.g:593:1: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void object_creation() throws RecognitionException {
        try {
            // Cami.g:594:3: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt26=5;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt26=1;
                }
                break;
            case 47:
                {
                alt26=2;
                }
                break;
            case 48:
                {
                alt26=3;
                }
                break;
            case 49:
                {
                alt26=4;
                }
                break;
            case 50:
                {
                alt26=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("593:1: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // Cami.g:595:4: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,46,FOLLOW_46_in_object_creation1554); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1556); 
                    match(input,11,FOLLOW_11_in_object_creation1558); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1560); 
                    match(input,9,FOLLOW_9_in_object_creation1562); 

                             System.out.println("je parse CN"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:598:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,47,FOLLOW_47_in_object_creation1568); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1570); 
                    match(input,11,FOLLOW_11_in_object_creation1572); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1574); 
                    match(input,11,FOLLOW_11_in_object_creation1576); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1578); 
                    match(input,9,FOLLOW_9_in_object_creation1580); 

                            System.out.println("je parse CB"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:601:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_object_creation1586); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1588); 
                    match(input,11,FOLLOW_11_in_object_creation1590); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1592); 
                    match(input,11,FOLLOW_11_in_object_creation1594); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1596); 
                    match(input,11,FOLLOW_11_in_object_creation1598); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1600); 
                    match(input,9,FOLLOW_9_in_object_creation1602); 

                            System.out.println("je parse CA"); 
                             

                    }
                    break;
                case 4 :
                    // Cami.g:604:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,49,FOLLOW_49_in_object_creation1608); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1610); 
                    match(input,11,FOLLOW_11_in_object_creation1612); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1614); 
                    match(input,11,FOLLOW_11_in_object_creation1616); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1618); 
                    match(input,9,FOLLOW_9_in_object_creation1620); 
                     
                            System.out.println("je parse CT"); 
                             

                    }
                    break;
                case 5 :
                    // Cami.g:607:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,50,FOLLOW_50_in_object_creation1626); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1628); 
                    match(input,11,FOLLOW_11_in_object_creation1630); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1632); 
                    match(input,11,FOLLOW_11_in_object_creation1634); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1636); 
                    match(input,11,FOLLOW_11_in_object_creation1638); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1640); 
                    match(input,11,FOLLOW_11_in_object_creation1642); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1644); 
                    match(input,9,FOLLOW_9_in_object_creation1646); 

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
    // Cami.g:612:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // Cami.g:613:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==51) ) {
                alt27=1;
            }
            else if ( (LA27_0==52) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("612:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // Cami.g:614:5: 'SU(' id= NUMBER ')'
                    {
                    match(input,51,FOLLOW_51_in_object_deletion1663); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1667); 
                    match(input,9,FOLLOW_9_in_object_deletion1669); 

                             System.out.println("je parse SU"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:617:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,52,FOLLOW_52_in_object_deletion1676); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1680); 
                    match(input,11,FOLLOW_11_in_object_deletion1682); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1686); 
                    match(input,9,FOLLOW_9_in_object_deletion1688); 

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
    // Cami.g:623:1: domaine_table : 'TD(' CAMI_STRING ')' ( milieu )* 'FA()' ;
    public final void domaine_table() throws RecognitionException {
        try {
            // Cami.g:624:7: ( 'TD(' CAMI_STRING ')' ( milieu )* 'FA()' )
            // Cami.g:625:7: 'TD(' CAMI_STRING ')' ( milieu )* 'FA()'
            {
            match(input,53,FOLLOW_53_in_domaine_table1713); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1715); 
            match(input,9,FOLLOW_9_in_domaine_table1717); 

                   
                       System.out.println("je parse le TD dans table domaine");
                    
            // Cami.g:629:9: ( milieu )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=54 && LA28_0<=55)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // Cami.g:629:9: milieu
            	    {
            	    pushFollow(FOLLOW_milieu_in_domaine_table1728);
            	    milieu();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            match(input,15,FOLLOW_15_in_domaine_table1738); 

                      
                       System.out.println("je parse le FA dans table domaine");
                    

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


    // $ANTLR start milieu
    // Cami.g:636:1: milieu : ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void milieu() throws RecognitionException {
        try {
            // Cami.g:637:1: ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==54) ) {
                alt29=1;
            }
            else if ( (LA29_0==55) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("636:1: milieu : ( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // Cami.g:638:2: 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,54,FOLLOW_54_in_milieu1749); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_milieu1751); 
                    match(input,11,FOLLOW_11_in_milieu1753); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_milieu1755); 
                    match(input,11,FOLLOW_11_in_milieu1757); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_milieu1759); 
                    match(input,9,FOLLOW_9_in_milieu1761); 

                        
                               System.out.println("je parse le OB dans table domaine");
                            

                    }
                    break;
                case 2 :
                    // Cami.g:642:10: 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,55,FOLLOW_55_in_milieu1773); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_milieu1775); 
                    match(input,11,FOLLOW_11_in_milieu1777); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_milieu1779); 
                    match(input,11,FOLLOW_11_in_milieu1781); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_milieu1783); 
                    match(input,11,FOLLOW_11_in_milieu1785); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_milieu1787); 
                    match(input,11,FOLLOW_11_in_milieu1789); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_milieu1791); 
                    match(input,9,FOLLOW_9_in_milieu1793); 

                             
                               System.out.println("je parse le AT dans table domaine");


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
    // $ANTLR end milieu


    // $ANTLR start modele
    // Cami.g:648:1: modele : 'DB()' ( modele2 )* 'FB()' ;
    public final void modele() throws RecognitionException {
        try {
            // Cami.g:649:5: ( 'DB()' ( modele2 )* 'FB()' )
            // Cami.g:650:7: 'DB()' ( modele2 )* 'FB()'
            {
            match(input,56,FOLLOW_56_in_modele1813); 

                     System.out.println("je parse BD"); 
                   
            // Cami.g:653:8: ( modele2 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=46 && LA30_0<=50)||(LA30_0>=58 && LA30_0<=59)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // Cami.g:653:8: modele2
            	    {
            	    pushFollow(FOLLOW_modele2_in_modele1823);
            	    modele2();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            match(input,57,FOLLOW_57_in_modele1833); 

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
    // Cami.g:659:1: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
    public final void modele2() throws RecognitionException {
        try {
            // Cami.g:660:5: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            int alt31=7;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt31=1;
                }
                break;
            case 47:
                {
                alt31=2;
                }
                break;
            case 48:
                {
                alt31=3;
                }
                break;
            case 49:
                {
                alt31=4;
                }
                break;
            case 50:
                {
                alt31=5;
                }
                break;
            case 58:
                {
                alt31=6;
                }
                break;
            case 59:
                {
                alt31=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("659:1: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // Cami.g:661:7: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,46,FOLLOW_46_in_modele21853); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21855); 
                    match(input,11,FOLLOW_11_in_modele21857); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21859); 
                    match(input,9,FOLLOW_9_in_modele21861); 

                             System.out.println("je parse CN"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:664:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,47,FOLLOW_47_in_modele21867); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21869); 
                    match(input,11,FOLLOW_11_in_modele21871); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21873); 
                    match(input,11,FOLLOW_11_in_modele21875); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21877); 
                    match(input,9,FOLLOW_9_in_modele21879); 

                            System.out.println("je parse CB"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:667:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,48,FOLLOW_48_in_modele21885); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21887); 
                    match(input,11,FOLLOW_11_in_modele21889); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21891); 
                    match(input,11,FOLLOW_11_in_modele21893); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21895); 
                    match(input,11,FOLLOW_11_in_modele21897); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21899); 
                    match(input,9,FOLLOW_9_in_modele21901); 

                            System.out.println("je parse CA"); 
                             

                    }
                    break;
                case 4 :
                    // Cami.g:670:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,49,FOLLOW_49_in_modele21907); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21909); 
                    match(input,11,FOLLOW_11_in_modele21911); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21913); 
                    match(input,11,FOLLOW_11_in_modele21915); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21917); 
                    match(input,9,FOLLOW_9_in_modele21919); 
                     
                            System.out.println("je parse CT"); 
                             

                    }
                    break;
                case 5 :
                    // Cami.g:673:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,50,FOLLOW_50_in_modele21925); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21927); 
                    match(input,11,FOLLOW_11_in_modele21929); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21931); 
                    match(input,11,FOLLOW_11_in_modele21933); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21935); 
                    match(input,11,FOLLOW_11_in_modele21937); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21939); 
                    match(input,11,FOLLOW_11_in_modele21941); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21943); 
                    match(input,9,FOLLOW_9_in_modele21945); 

                           System.out.println("je parse CM"); 
                             

                    }
                    break;
                case 6 :
                    // Cami.g:676:10: 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,58,FOLLOW_58_in_modele21957); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21958); 
                    match(input,11,FOLLOW_11_in_modele21960); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21962); 
                    match(input,11,FOLLOW_11_in_modele21964); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21966); 
                    match(input,11,FOLLOW_11_in_modele21968); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21970); 
                    match(input,9,FOLLOW_9_in_modele21972); 

                             System.out.println("je parse PO");
                            

                    }
                    break;
                case 7 :
                    // Cami.g:679:10: 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,59,FOLLOW_59_in_modele21984); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21985); 
                    match(input,11,FOLLOW_11_in_modele21987); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21989); 
                    match(input,11,FOLLOW_11_in_modele21991); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21993); 
                    match(input,11,FOLLOW_11_in_modele21995); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele21997); 
                    match(input,11,FOLLOW_11_in_modele21999); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_modele22001); 
                    match(input,9,FOLLOW_9_in_modele22003); 

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
    // Cami.g:684:1: dialogue : ( ( dialog2 )+ | 'DC()' | 'AD(' NUMBER ')' );
    public final void dialogue() throws RecognitionException {
        try {
            // Cami.g:685:6: ( ( dialog2 )+ | 'DC()' | 'AD(' NUMBER ')' )
            int alt33=3;
            switch ( input.LA(1) ) {
            case 62:
            case 63:
            case 64:
                {
                alt33=1;
                }
                break;
            case 60:
                {
                alt33=2;
                }
                break;
            case 61:
                {
                alt33=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("684:1: dialogue : ( ( dialog2 )+ | 'DC()' | 'AD(' NUMBER ')' );", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // Cami.g:686:5: ( dialog2 )+
                    {
                    // Cami.g:686:5: ( dialog2 )+
                    int cnt32=0;
                    loop32:
                    do {
                        int alt32=2;
                        switch ( input.LA(1) ) {
                        case 62:
                            {
                            alt32=1;
                            }
                            break;
                        case 63:
                            {
                            alt32=1;
                            }
                            break;
                        case 64:
                            {
                            alt32=1;
                            }
                            break;

                        }

                        switch (alt32) {
                    	case 1 :
                    	    // Cami.g:686:5: dialog2
                    	    {
                    	    pushFollow(FOLLOW_dialog2_in_dialogue2022);
                    	    dialog2();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt32 >= 1 ) break loop32;
                                EarlyExitException eee =
                                    new EarlyExitException(32, input);
                                throw eee;
                        }
                        cnt32++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // Cami.g:687:6: 'DC()'
                    {
                    match(input,60,FOLLOW_60_in_dialogue2030); 

                            System.out.println("je parse DC");
                        

                    }
                    break;
                case 3 :
                    // Cami.g:690:6: 'AD(' NUMBER ')'
                    {
                    match(input,61,FOLLOW_61_in_dialogue2038); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialogue2039); 
                    match(input,9,FOLLOW_9_in_dialogue2041); 

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
    // Cami.g:695:1: dialog2 : ( 'DS(' NUMBER ',' CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | 'FF(' );
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
            // Cami.g:696:5: ( 'DS(' NUMBER ',' CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | 'FF(' )
            int alt35=3;
            switch ( input.LA(1) ) {
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
                {
                alt35=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("695:1: dialog2 : ( 'DS(' NUMBER ',' CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | 'FF(' );", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // Cami.g:697:5: 'DS(' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,62,FOLLOW_62_in_dialog22060); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22061); 
                    match(input,11,FOLLOW_11_in_dialog22063); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22065); 
                    match(input,9,FOLLOW_9_in_dialog22067); 

                           System.out.println("je parse DS"); 
                             

                    }
                    break;
                case 2 :
                    // Cami.g:700:8: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
                    {
                    match(input,63,FOLLOW_63_in_dialog22077); 
                    dialog_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22081); 
                    match(input,11,FOLLOW_11_in_dialog22083); 
                    dialog_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22087); 
                    match(input,11,FOLLOW_11_in_dialog22089); 
                    buttons_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22093); 
                    match(input,11,FOLLOW_11_in_dialog22095); 
                    window_title=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22100); 
                    match(input,11,FOLLOW_11_in_dialog22102); 
                    help=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22106); 
                    match(input,11,FOLLOW_11_in_dialog22108); 
                    title_or_message=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22112); 
                    match(input,11,FOLLOW_11_in_dialog22114); 
                    input_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22118); 
                    match(input,11,FOLLOW_11_in_dialog22120); 
                    line_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_dialog22124); 
                    match(input,11,FOLLOW_11_in_dialog22126); 
                    // Cami.g:700:222: (default_value= CAMI_STRING )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==CAMI_STRING) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // Cami.g:700:222: default_value= CAMI_STRING
                            {
                            default_value=(Token)input.LT(1);
                            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog22130); 

                            }
                            break;

                    }

                    match(input,9,FOLLOW_9_in_dialog22133); 

                           System.out.println("je parse CE"); 
                             

                    }
                    break;
                case 3 :
                    // Cami.g:704:9: 'FF('
                    {
                    match(input,64,FOLLOW_64_in_dialog22145); 
                     
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
    public static final BitSet FOLLOW_24_in_receving_menu497 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu498 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_menu_name516 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name520 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name522 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name526 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name528 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name532 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_question_add550 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add554 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add556 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add560 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add562 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add569 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add572 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add576 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add579 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add586 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add589 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add594 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add597 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add601 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add604 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add611 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add614 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add618 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_update642 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update646 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update648 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update652 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update654 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_update658 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update664 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update668 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_update671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_end_menu_transmission697 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission699 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message751 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message753 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message768 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message770 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message786 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message788 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message790 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message792 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_brutal_interrupt814 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_brutal_interrupt818 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_brutal_interrupt820 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_brutal_interrupt824 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_brutal_interrupt826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ask_for_a_model901 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model903 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_a_model905 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model907 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ask_for_a_model909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_result_reception939 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_result_reception949 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception953 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception955 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result_reception959 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result_reception961 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception965 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception967 = new BitSet(new long[]{0xF12000C388000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_resultat_in_result_reception979 = new BitSet(new long[]{0xF12000C388000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_message_utils_in_resultat1020 = new BitSet(new long[]{0x0000004388000002L});
    public static final BitSet FOLLOW_domaine_table_in_resultat1032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialogue_in_resultat1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modele_in_resultat1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_resultat1065 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_trace_message2_in_message_utils1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message2_in_message_utils1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message2_in_message_utils1132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_message_utils1146 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1147 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1149 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1151 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1153 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1155 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1157 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1159 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1161 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1163 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_message_utils1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_message_utils1179 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_message_utils1183 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1185 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_message_utils1189 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1191 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_message_utils1195 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_message_utils1198 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_message_utils1202 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_message_utils1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message21227 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message21229 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message21231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message21244 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message21246 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message21248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message21262 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message21264 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message21266 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message21268 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message21270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_result1294 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1298 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1300 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result1304 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result1306 = new BitSet(new long[]{0x001FFC8000000000L});
    public static final BitSet FOLLOW_result_body_in_result1317 = new BitSet(new long[]{0x001FFD8000000000L});
    public static final BitSet FOLLOW_40_in_result1321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_debut1341 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_debut1345 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_debut1347 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_debut1351 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_debut1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_debut1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_textual_result1443 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1445 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_textual_result1447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_object_designation1463 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1467 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_designation1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_object_outline1485 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1489 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_outline1491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_attribute_outline1511 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1515 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1517 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1521 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1523 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1527 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1530 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1534 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_attribute_outline1537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_object_creation1554 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1556 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1558 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1560 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_object_creation1568 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1570 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1572 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1574 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1576 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1578 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_object_creation1586 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1588 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1590 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1592 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1594 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1596 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1598 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1600 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_creation1608 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1610 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1612 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1614 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1616 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1618 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_creation1626 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1628 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1630 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1632 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1634 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1636 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1638 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1640 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1642 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1644 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_creation1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_deletion1663 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1667 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_deletion1676 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1680 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1682 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1686 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_object_deletion1688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_domaine_table1713 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1715 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_domaine_table1717 = new BitSet(new long[]{0x00C0000000008000L});
    public static final BitSet FOLLOW_milieu_in_domaine_table1728 = new BitSet(new long[]{0x00C0000000008000L});
    public static final BitSet FOLLOW_15_in_domaine_table1738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_milieu1749 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_milieu1751 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_milieu1753 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_milieu1755 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_milieu1757 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_milieu1759 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_milieu1761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_milieu1773 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_milieu1775 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_milieu1777 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_milieu1779 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_milieu1781 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_milieu1783 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_milieu1785 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_milieu1787 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_milieu1789 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_milieu1791 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_milieu1793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_modele1813 = new BitSet(new long[]{0x0E07C00000000000L});
    public static final BitSet FOLLOW_modele2_in_modele1823 = new BitSet(new long[]{0x0E07C00000000000L});
    public static final BitSet FOLLOW_57_in_modele1833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_modele21853 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21855 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21857 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21859 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_modele21867 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21869 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21871 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21873 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21875 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21877 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_modele21885 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21887 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21889 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21891 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21893 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21895 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21897 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21899 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_modele21907 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21909 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21911 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21913 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21915 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21917 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_modele21925 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21927 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21929 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21931 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21933 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21935 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21937 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21939 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21941 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_modele21943 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_modele21957 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21958 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21960 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21962 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21964 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21966 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21968 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21970 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele21972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_modele21984 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21985 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21987 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21989 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21991 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21993 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21995 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele21997 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_modele21999 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_modele22001 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_modele22003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialog2_in_dialogue2022 = new BitSet(new long[]{0xC000000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_60_in_dialogue2030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_dialogue2038 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialogue2039 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialogue2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_dialog22060 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22061 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22063 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22065 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog22067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_dialog22077 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22081 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22083 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22087 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22089 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22093 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22095 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22100 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22102 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22106 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22108 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22112 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22114 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22118 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22120 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog22124 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog22126 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog22130 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_dialog22133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_dialog22145 = new BitSet(new long[]{0x0000000000000002L});

}