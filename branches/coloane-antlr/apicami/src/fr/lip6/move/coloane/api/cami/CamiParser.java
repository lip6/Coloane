// $ANTLR 3.0.1 Cami.g 2008-04-29 17:31:48

package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'OS('", "'TD()'", "'FA()'", "'TL()'", "'VI('", "'FL()'"
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


       ArrayList<String> listOfArgs;
       HashMap<String, Object> hashObservable;
       ISessionController sc;

       public CamiParser(TokenStream input, HashMap<String, Object> hm) {
           this(input);
           hashObservable = hm;
       }



    // $ANTLR start command
    // Cami.g:28:1: command : ( ack_open_communication | ack_open_connection | ack_open_session );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:29:5: ( ack_open_communication | ack_open_connection | ack_open_session )
            int alt1=3;
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
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("28:1: command : ( ack_open_communication | ack_open_connection | ack_open_session );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Cami.g:30:5: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command32);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:30:30: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command36);
                    ack_open_connection();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:31:7: ack_open_session
                    {
                    pushFollow(FOLLOW_ack_open_session_in_command44);
                    ack_open_session();
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
    // Cami.g:35:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:36:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:37:2: 'SC(' CAMI_STRING ')'
            {
            match(input,7,FOLLOW_7_in_ack_open_communication61);
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication63);
            match(input,8,FOLLOW_8_in_ack_open_communication65);

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
    // Cami.g:48:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:49:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:50:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_ack_open_connection82);
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection91);

                        listOfArgs.add(v1.getText());

            match(input,10,FOLLOW_10_in_ack_open_connection100);
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection109);
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }

            match(input,8,FOLLOW_8_in_ack_open_connection117);

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
    // Cami.g:66:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:67:2: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
            int alt2=4;
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
            default:
                NoViableAltException nvae =
                    new NoViableAltException("66:1: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:68:2: 'OS(' CAMI_STRING ')'
                    {
                    match(input,11,FOLLOW_11_in_ack_open_session135);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session137);
                    match(input,8,FOLLOW_8_in_ack_open_session138);

                                //TODO ajouter un controle que c OS
                                System.out.println("OS");


                    }
                    break;
                case 2 :
                    // Cami.g:72:3: 'TD()'
                    {
                    match(input,12,FOLLOW_12_in_ack_open_session143);

                                // Ajouter un controle qu'on doit bien recevoir TD
                                System.out.println("TD");


                    }
                    break;
                case 3 :
                    // Cami.g:76:3: 'FA()'
                    {
                    match(input,13,FOLLOW_13_in_ack_open_session148);
                    // Ajouter un controle qu'on doit bien recevoir FA}
                                System.out.println("FA");


                    }
                    break;
                case 4 :
                    // Cami.g:80:6: interlocutor_table
                    {
                    pushFollow(FOLLOW_interlocutor_table_in_ack_open_session157);
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
    // Cami.g:84:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
    public final void interlocutor_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token incremental=null;
        Token new_model=null;

        try {
            // Cami.g:85:5: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
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
                    new NoViableAltException("84:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Cami.g:86:5: 'TL()'
                    {
                    match(input,14,FOLLOW_14_in_interlocutor_table177);



                    }
                    break;
                case 2 :
                    // Cami.g:88:6: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
                    {
                    match(input,15,FOLLOW_15_in_interlocutor_table185);
                    service_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table189);
                    match(input,10,FOLLOW_10_in_interlocutor_table191);
                    about_service=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table195);
                    match(input,10,FOLLOW_10_in_interlocutor_table197);
                    incremental=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table201);
                    match(input,10,FOLLOW_10_in_interlocutor_table205);
                    new_model=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table209);
                    match(input,8,FOLLOW_8_in_interlocutor_table212);


                                listOfArgs = new ArrayList<String>();
                                listOfArgs.add(service_name.getText());
                                listOfArgs.add(about_service.getText());
                                listOfArgs.add(incremental.getText());
                                listOfArgs.add(new_model.getText());


                    }
                    break;
                case 3 :
                    // Cami.g:96:6: 'FL()'
                    {
                    match(input,16,FOLLOW_16_in_interlocutor_table221);

                    //            IFkInfo info = CamiObjectBuilder.buildFkInfo(listOfArgs);
                    //            ((ISessionObservable)hashObservable.get("ISession")).notifyObservers(info);

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




    public static final BitSet FOLLOW_ack_open_communication_in_command32 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command36 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_session_in_command44 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_ack_open_communication61 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication63 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_communication65 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_ack_open_connection82 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection91 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_ack_open_connection100 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection109 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_connection117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ack_open_session135 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session137 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_session138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ack_open_session143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ack_open_session148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_interlocutor_table177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_interlocutor_table185 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table189 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interlocutor_table191 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table195 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interlocutor_table197 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table201 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_interlocutor_table205 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interlocutor_table209 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_interlocutor_table212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_interlocutor_table221 = new BitSet(new long[]{0x0000000000000002L});

}