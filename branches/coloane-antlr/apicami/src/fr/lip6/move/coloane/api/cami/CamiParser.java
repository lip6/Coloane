// $ANTLR 3.0.1 Cami.g 2008-05-27 13:25:15

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

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'FS('", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'TR('", "'WN('", "'MO('", "'KO(1,'", "'DF(-2,'", "'DR()'", "'FR('", "'ZZ('", "'1)'"
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
    // Cami.g:64:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:65:5: ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt )
            int alt2=13;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("64:1: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | end_menu_transmission | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:66:5: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command55);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:66:30: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command59);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:66:52: close_connection
                    {
                    pushFollow(FOLLOW_close_connection_in_command63);
                    close_connection();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:67:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command71);
                    ack_open_session();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:67:26: receving_menu
                    {
                    pushFollow(FOLLOW_receving_menu_in_command75);
                    receving_menu();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:68:6: ( update )*
                    {
                    // Cami.g:68:6: ( update )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==27) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Cami.g:68:6: update
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
                    // Cami.g:69:6: end_menu_transmission
                    {
                    pushFollow(FOLLOW_end_menu_transmission_in_command90);
                    end_menu_transmission();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // Cami.g:71:6: ack_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_suspend_current_session_in_command98);
                    ack_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 9 :
                    // Cami.g:72:6: ack_resume_suspend_current_session
                    {
                    pushFollow(FOLLOW_ack_resume_suspend_current_session_in_command105);
                    ack_resume_suspend_current_session();
                    _fsp--;


                    }
                    break;
                case 10 :
                    // Cami.g:73:6: ack_close_current_session
                    {
                    pushFollow(FOLLOW_ack_close_current_session_in_command112);
                    ack_close_current_session();
                    _fsp--;


                    }
                    break;
                case 11 :
                    // Cami.g:75:6: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_command120);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 12 :
                    // Cami.g:76:6: message_to_user
                    {
                    pushFollow(FOLLOW_message_to_user_in_command127);
                    message_to_user();
                    _fsp--;


                    }
                    break;
                case 13 :
                    // Cami.g:77:6: brutal_interrupt
                    {
                    pushFollow(FOLLOW_brutal_interrupt_in_command134);
                    brutal_interrupt();
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
    // Cami.g:84:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:85:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:86:2: 'SC(' CAMI_STRING ')'
            {
            match(input,8,FOLLOW_8_in_ack_open_communication154); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication156); 
            match(input,9,FOLLOW_9_in_ack_open_communication158); 

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
    // Cami.g:98:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:99:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:100:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_ack_open_connection179); 
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection188); 

                        listOfArgs.add(v1.getText());
                    
            match(input,11,FOLLOW_11_in_ack_open_connection197); 
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection206); 
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }
                    
            match(input,9,FOLLOW_9_in_ack_open_connection214); 

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
    // Cami.g:117:1: close_connection : 'FC()' ;
    public final void close_connection() throws RecognitionException {
        try {
            // Cami.g:118:5: ( 'FC()' )
            // Cami.g:119:5: 'FC()'
            {
            match(input,12,FOLLOW_12_in_close_connection239); 

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
    // Cami.g:124:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:125:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
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
                    new NoViableAltException("124:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:126:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session256); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session258); 
                    match(input,9,FOLLOW_9_in_ack_open_session259); 

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");
                                
                                /* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
                                menuList = new ArrayList<IMenu>();
                                /*  */
                                camiUpdates = new ArrayList<ArrayList<String>>();
                            

                    }
                    break;
                case 2 :
                    // Cami.g:135:3: 'TD()'
                    {
                    match(input,14,FOLLOW_14_in_ack_open_session264); 

                                // Ajouter un controle qu'on doit bien recevoir TD
                    //            System.out.println("TD");
                            

                    }
                    break;
                case 3 :
                    // Cami.g:139:3: 'FA()'
                    {
                    match(input,15,FOLLOW_15_in_ack_open_session269); 
                    // Ajouter un controle qu'on doit bien recevoir FA}
                    //            System.out.println("FA");
                            

                    }
                    break;
                case 4 :
                    // Cami.g:143:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session286);
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
    // Cami.g:148:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:149:2: ( 'SS()' )
            // Cami.g:150:2: 'SS()'
            {
            match(input,16,FOLLOW_16_in_ack_suspend_current_session318); 
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
    // Cami.g:158:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        Token CAMI_STRING2=null;

        try {
            // Cami.g:159:2: ( 'RS(' CAMI_STRING ')' )
            // Cami.g:160:2: 'RS(' CAMI_STRING ')'
            {
            match(input,17,FOLLOW_17_in_ack_resume_suspend_current_session337); 
            CAMI_STRING2=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session339); 
            match(input,9,FOLLOW_9_in_ack_resume_suspend_current_session341); 

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
    // Cami.g:167:1: ack_close_current_session : 'FS(' CAMI_STRING ')' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // Cami.g:168:2: ( 'FS(' CAMI_STRING ')' )
            // Cami.g:169:2: 'FS(' CAMI_STRING ')'
            {
            match(input,18,FOLLOW_18_in_ack_close_current_session359); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_close_current_session361); 
            match(input,9,FOLLOW_9_in_ack_close_current_session363); 

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
    // Cami.g:178:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:179:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
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
                    new NoViableAltException("178:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:180:5: 'TL()'
                    {
                    match(input,19,FOLLOW_19_in_interlocutor_table387); 
                                
                            

                    }
                    break;
                case 2 :
                    // Cami.g:182:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,20,FOLLOW_20_in_interlocutor_table395); 
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table399); 
                    match(input,11,FOLLOW_11_in_interlocutor_table401); 
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table405); 
                    match(input,11,FOLLOW_11_in_interlocutor_table407); 
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table411); 
                    match(input,11,FOLLOW_11_in_interlocutor_table428); 
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table432); 
                    match(input,9,FOLLOW_9_in_interlocutor_table435); 

                               
                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());            
                            

                    }
                    break;
                case 3 :
                    // Cami.g:191:6: 'FL()'
                    {
                    match(input,21,FOLLOW_21_in_interlocutor_table444); 

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
    // Cami.g:205:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
    public final void receving_menu() throws RecognitionException {
        try {
            // Cami.g:206:5: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
            // Cami.g:207:2: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
            {
            match(input,22,FOLLOW_22_in_receving_menu467); 

                        /* créer la menuList  */
                        camiMenuList = new ArrayList<ArrayList<String>>();
                    
            pushFollow(FOLLOW_menu_name_in_receving_menu471);
            menu_name();
            _fsp--;

            // Cami.g:212:2: ( question_add )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==26) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // Cami.g:212:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_receving_menu474);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_receving_menu478); 

                        /* fin de la reception des menus : demander la construction du menu */            
                        menu = CamiObjectBuilder.buildMenu(camiMenuList);

                        System.out.println("nombre de AQ : " + camiMenuList.size());

                        menuList.add(menu);
                        
            //            System.out.println("Menu construit");
            //            System.out.println("FQ()");
                    
            match(input,24,FOLLOW_24_in_receving_menu485); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu486); 
            match(input,9,FOLLOW_9_in_receving_menu487); 
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
    // Cami.g:233:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;
        Token question_type=null;
        Token question_behavior=null;

        try {
            // Cami.g:234:2: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
            // Cami.g:235:2: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
            {
            match(input,25,FOLLOW_25_in_menu_name504); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name508); 
            match(input,11,FOLLOW_11_in_menu_name510); 
            question_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name514); 
            match(input,11,FOLLOW_11_in_menu_name516); 
            question_behavior=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name520); 
            match(input,9,FOLLOW_9_in_menu_name522); 

                 
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
    // Cami.g:257:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:258:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:259:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,26,FOLLOW_26_in_question_add538); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add542); 
            match(input,11,FOLLOW_11_in_question_add544); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add548); 
            match(input,11,FOLLOW_11_in_question_add550); 
            // Cami.g:260:16: (question_type= NUMBER )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NUMBER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Cami.g:260:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add557); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add560); 
            // Cami.g:260:46: (question_behavior= NUMBER )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==NUMBER) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:260:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add564); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add567); 
            // Cami.g:261:11: (set_item= NUMBER )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==NUMBER) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Cami.g:261:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add574); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add577); 
            // Cami.g:261:31: (dialog= NUMBER )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NUMBER) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:261:31: dialog= NUMBER
                    {
                    dialog=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add582); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add585); 
            // Cami.g:261:59: (stop_authorized= NUMBER )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NUMBER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:261:59: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add589); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add592); 
            // Cami.g:262:19: (output_formalism= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:262:19: output_formalism= CAMI_STRING
                    {
                    output_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add599); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add602); 
            // Cami.g:262:43: (active= NUMBER )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==NUMBER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Cami.g:262:43: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add606); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_question_add609); 


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
    // Cami.g:324:1: update : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' ;
    public final void update() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:325:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:326:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')'
            {
            match(input,27,FOLLOW_27_in_update630); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update634); 
            match(input,11,FOLLOW_11_in_update636); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update640); 
            match(input,11,FOLLOW_11_in_update642); 
            state=(Token)input.LT(1);
            if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
                input.consume();
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update646);    throw mse;
            }

            match(input,11,FOLLOW_11_in_update652); 
            // Cami.g:326:91: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:326:91: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update656); 

                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_update659); 

                        
                        
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
    // Cami.g:349:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        Token NUMBER3=null;

        try {
            // Cami.g:350:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:351:2: 'QQ(' NUMBER ')'
            {
            match(input,30,FOLLOW_30_in_end_menu_transmission685); 
            NUMBER3=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission687); 
            match(input,9,FOLLOW_9_in_end_menu_transmission689); 

                        
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
    // Cami.g:376:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // Cami.g:377:2: ( trace_message | warning_message | special_message )
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
                    new NoViableAltException("376:1: message_to_user : ( trace_message | warning_message | special_message );", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // Cami.g:378:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user719);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:378:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user723);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:378:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user727);
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
    // Cami.g:381:1: trace_message : 'TR(' CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        Token CAMI_STRING4=null;

        try {
            // Cami.g:382:2: ( 'TR(' CAMI_STRING ')' )
            // Cami.g:383:2: 'TR(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_trace_message739); 
            CAMI_STRING4=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message741); 
            match(input,9,FOLLOW_9_in_trace_message743); 

                  //      ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers(CAMI_STRING4.getText());
                    

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
    // Cami.g:388:1: warning_message : 'WN(' CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        Token CAMI_STRING5=null;

        try {
            // Cami.g:389:2: ( 'WN(' CAMI_STRING ')' )
            // Cami.g:390:2: 'WN(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_warning_message756); 
            CAMI_STRING5=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message758); 
            match(input,9,FOLLOW_9_in_warning_message760); 

                //        ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING5.getText());
                    

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
    // Cami.g:395:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        Token CAMI_STRING6=null;

        try {
            // Cami.g:396:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:397:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_special_message774); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message776); 
            match(input,11,FOLLOW_11_in_special_message778); 
            CAMI_STRING6=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message780); 
            match(input,9,FOLLOW_9_in_special_message782); 

            //            ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING6.getText());            
                 //TODO pour le MO
                    

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
    // Cami.g:407:1: brutal_interrupt : 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' ;
    public final void brutal_interrupt() throws RecognitionException {
        Token mess=null;
        Token level=null;

        try {
            // Cami.g:408:3: ( 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' )
            // Cami.g:409:3: 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')'
            {
            match(input,34,FOLLOW_34_in_brutal_interrupt802); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_brutal_interrupt806); 
            match(input,11,FOLLOW_11_in_brutal_interrupt808); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_brutal_interrupt812); 
            match(input,9,FOLLOW_9_in_brutal_interrupt814); 

            //			((IBrutalInterrupt)hashObservable.get("IBrutalInterrupt")).notifyObservers(mess.getText());
            		

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
    // Cami.g:416:1: ask_for_a_model : 'DF(-2,' NUMBER ',' NUMBER ')' ;
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // Cami.g:417:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // Cami.g:418:5: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,35,FOLLOW_35_in_ask_for_a_model889); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model891); 
            match(input,11,FOLLOW_11_in_ask_for_a_model893); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model895); 
            match(input,9,FOLLOW_9_in_ask_for_a_model897); 

                    
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
    // Cami.g:427:1: result_reception : 'DR()' 'FR(' NUMBER ')' ;
    public final void result_reception() throws RecognitionException {
        try {
            // Cami.g:428:2: ( 'DR()' 'FR(' NUMBER ')' )
            // Cami.g:429:2: 'DR()' 'FR(' NUMBER ')'
            {
            match(input,36,FOLLOW_36_in_result_reception914); 
             
                        // initialiser la liste des updates 
                        camiUpdates = new ArrayList<ArrayList<String>>();
                    
            match(input,37,FOLLOW_37_in_result_reception922); 
            match(input,NUMBER,FOLLOW_NUMBER_in_result_reception924); 
            match(input,9,FOLLOW_9_in_result_reception926); 

                        //TODO notifier Coloane  de la fin de reception des resulstats et envoyer les resultats
                    

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


    // $ANTLR start question_reply
    // Cami.g:441:1: question_reply : 'ZZ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' '1)' ;
    public final void question_reply() throws RecognitionException {
        Token service_name1=null;
        Token question_name1=null;

        try {
            // Cami.g:442:2: ( 'ZZ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' '1)' )
            // Cami.g:443:2: 'ZZ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' '1)'
            {
            match(input,38,FOLLOW_38_in_question_reply939); 
            service_name1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply943); 
            match(input,11,FOLLOW_11_in_question_reply945); 
            question_name1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply949); 
            match(input,11,FOLLOW_11_in_question_reply951); 
            match(input,39,FOLLOW_39_in_question_reply954); 

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
    // $ANTLR end question_reply


 

    public static final BitSet FOLLOW_ack_open_communication_in_command55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_in_command63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_receving_menu_in_command75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_in_command82 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_end_menu_transmission_in_command90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_suspend_current_session_in_command98 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_resume_suspend_current_session_in_command105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_close_current_session_in_command112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_command120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_message_to_user_in_command127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_brutal_interrupt_in_command134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_ack_open_communication154 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication156 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_communication158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_ack_open_connection179 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection188 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection197 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection206 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_connection214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_close_connection239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session256 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session258 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_open_session259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ack_open_session264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ack_open_session269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ack_suspend_current_session318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ack_resume_suspend_current_session337 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session339 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_resume_suspend_current_session341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ack_close_current_session359 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_close_current_session361 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ack_close_current_session363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_interlocutor_table387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_interlocutor_table395 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table399 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table401 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table405 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table407 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table411 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interlocutor_table428 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table432 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_interlocutor_table435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_interlocutor_table444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_receving_menu467 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_menu_name_in_receving_menu471 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_question_add_in_receving_menu474 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_receving_menu478 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_receving_menu485 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu486 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_receving_menu487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_menu_name504 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name508 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name510 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name514 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name516 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name520 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_menu_name522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_question_add538 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add542 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add544 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add548 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add550 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add557 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add560 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add564 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add567 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add574 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add577 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add582 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add585 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add589 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add592 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add599 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add602 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_NUMBER_in_question_add606 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_question_add609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_update630 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update634 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update636 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update640 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update642 = new BitSet(new long[]{0x0000000030000000L});
    public static final BitSet FOLLOW_set_in_update646 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_update652 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_CAMI_STRING_in_update656 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_update659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_end_menu_transmission685 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission687 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_end_menu_transmission689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_trace_message739 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message741 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_trace_message743 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_warning_message756 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message758 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_warning_message760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_special_message774 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message776 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message778 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message780 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_special_message782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_brutal_interrupt802 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_brutal_interrupt806 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_brutal_interrupt808 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_brutal_interrupt812 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_brutal_interrupt814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ask_for_a_model889 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model891 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_for_a_model893 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model895 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_ask_for_a_model897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_result_reception914 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_result_reception922 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception924 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_result_reception926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_question_reply939 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply943 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply945 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply949 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply951 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_question_reply954 = new BitSet(new long[]{0x0000000000000002L});

}