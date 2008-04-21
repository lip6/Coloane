package fr.lip6.move.coloane.api.FkCommunication;

// $ANTLR 3.0.1 Cami.g 2008-04-21 18:51:24

    import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
    import fr.lip6.move.coloane.api.interfaces.IFKVersion;

    import java.util.ArrayList;


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



    // $ANTLR start ack_open_communication
    // Cami.g:15:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        Token CAMI_STRING1=null;

        try {
            // Cami.g:16:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:17:2: 'SC(' CAMI_STRING ')'
            {
            match(input,7,FOLLOW_7_in_ack_open_communication24);
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication26);
            match(input,8,FOLLOW_8_in_ack_open_communication28);


                        listOfArgs.add("SC");
                        listOfArgs.add(CAMI_STRING1.getText());


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
    // Cami.g:26:1: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        Token v1=null;
        Token v2=null;

        try {
            // Cami.g:27:2: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
            // Cami.g:28:2: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
            {
            match(input,9,FOLLOW_9_in_ack_open_connection45);
            v1=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection54);

                        listOfArgs.add(v1.getText());

            match(input,10,FOLLOW_10_in_ack_open_connection63);
            v2=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection72);
            listOfArgs.add(v2.getText());
                        IFKVersion info = CamiObjectBuilder.buildFkVersion(listOfArgs);

            match(input,8,FOLLOW_8_in_ack_open_connection80);

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




    public static final BitSet FOLLOW_7_in_ack_open_communication24 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication26 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_communication28 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_ack_open_connection45 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection54 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_ack_open_connection63 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection72 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_ack_open_connection80 = new BitSet(new long[]{0x0000000000000002L});

}