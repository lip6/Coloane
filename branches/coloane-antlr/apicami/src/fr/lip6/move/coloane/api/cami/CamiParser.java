// $ANTLR 3.0.1 Cami.g 2008-04-23 17:45:12

package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','"
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

       public CamiParser(TokenStream input, HashMap<String, Object> hm) {
           this(input);
           hashObservable = hm;
       }



    // $ANTLR start command
    // Cami.g:25:1: command : ( ack_open_communication | ack_open_connection );
    public final void command() throws RecognitionException {
        try {
            // Cami.g:26:5: ( ack_open_communication | ack_open_connection )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==7) ) {
                alt1=1;
            }
            else if ( (LA1_0==9) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("25:1: command : ( ack_open_communication | ack_open_connection );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Cami.g:26:7: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_command27);
                    ack_open_communication();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:26:32: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_command31);
                    ack_open_connection();
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
    // Cami.g:30:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:31:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:32:2: 'SC(' CAMI_STRING ')'
            {
            match(input,7,FOLLOW_7_in_ack_open_communication48);
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication50);
            match(input,8,FOLLOW_8_in_ack_open_communication52);

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
    // Cami.g:43:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:44:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:45:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_ack_open_connection69);
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection78);

                        listOfArgs.add(v1.getText());

            match(input,10,FOLLOW_10_in_ack_open_connection87);
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection96);
            listOfArgs.add(v2.getText());
                        IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
                        ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
                        synchronized(hashObservable){
                            hashObservable.notify();
                        }

            match(input,8,FOLLOW_8_in_ack_open_connection104);

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




    public static final BitSet FOLLOW_ack_open_communication_in_command27 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_command31 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_ack_open_communication48 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication50 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_communication52 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_ack_open_connection69 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection78 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_ack_open_connection87 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection96 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_connection104 = new BitSet(new long[]{0x0000000000000002L});

}