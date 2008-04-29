package fr.lip6.move.coloane.api.cami;

// $ANTLR 3.0.1 Cami.g 2008-04-29 16:18:44

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiLexer extends Lexer {
    public static final int FIXED_LENGTH_STRING=6;
    public static final int CAMI_STRING=4;
    public static final int NUMBER=5;
    public static final int T7=7;
    public static final int T10=10;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T8=8;
    public static final int T14=14;
    public static final int T9=9;
    public static final int T15=15;
    public static final int Tokens=17;
    public static final int T16=16;
    public static final int EOF=-1;
    public CamiLexer() {;}
    public CamiLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Cami.g"; }

    // $ANTLR start T7
    public final void mT7() throws RecognitionException {
        try {
            int _type = T7;
            // Cami.g:3:4: ( 'SC(' )
            // Cami.g:3:6: 'SC('
            {
            match("SC(");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T7

    // $ANTLR start T8
    public final void mT8() throws RecognitionException {
        try {
            int _type = T8;
            // Cami.g:4:4: ( ')' )
            // Cami.g:4:6: ')'
            {
            match(')');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T8

    // $ANTLR start T9
    public final void mT9() throws RecognitionException {
        try {
            int _type = T9;
            // Cami.g:5:4: ( 'OC(' )
            // Cami.g:5:6: 'OC('
            {
            match("OC(");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T9

    // $ANTLR start T10
    public final void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // Cami.g:6:5: ( ',' )
            // Cami.g:6:7: ','
            {
            match(',');

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // Cami.g:7:5: ( 'OS(' )
            // Cami.g:7:7: 'OS('
            {
            match("OS(");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // Cami.g:8:5: ( 'TD()' )
            // Cami.g:8:7: 'TD()'
            {
            match("TD()");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // Cami.g:9:5: ( 'FA()' )
            // Cami.g:9:7: 'FA()'
            {
            match("FA()");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // Cami.g:10:5: ( 'TL()' )
            // Cami.g:10:7: 'TL()'
            {
            match("TL()");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // Cami.g:11:5: ( 'VI(' )
            // Cami.g:11:7: 'VI('
            {
            match("VI(");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // Cami.g:12:5: ( 'FL()' )
            // Cami.g:12:7: 'FL()'
            {
            match("FL()");


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start CAMI_STRING
    public final void mCAMI_STRING() throws RecognitionException {
        try {
            int _type = CAMI_STRING;
            Token fs=null;
            Token NUMBER1=null;

            int nbToRead = 0;
            // Cami.g:101:6: ( NUMBER ':' fs= FIXED_LENGTH_STRING[nbToRead] )
            // Cami.g:102:2: NUMBER ':' fs= FIXED_LENGTH_STRING[nbToRead]
            {
            int NUMBER1Start105 = getCharIndex();
            mNUMBER();
            NUMBER1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, NUMBER1Start105, getCharIndex()-1);
            nbToRead = Integer.parseInt(NUMBER1.getText());
            match(':');
            int fsStart116 = getCharIndex();
            mFIXED_LENGTH_STRING(nbToRead);
            fs = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fsStart116, getCharIndex()-1);
            setText(fs.getText());

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CAMI_STRING

    // $ANTLR start FIXED_LENGTH_STRING
    public final void mFIXED_LENGTH_STRING(int len) throws RecognitionException {
        try {
            // Cami.g:110:2: ( ({...}? => . )* )
            // Cami.g:111:2: ({...}? => . )*
            {
            // Cami.g:111:2: ({...}? => . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\uFFFE')) && ( len > 0 )) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Cami.g:111:4: {...}? => .
            	    {
            	    if ( !( len > 0 ) ) {
            	        throw new FailedPredicateException(input, "FIXED_LENGTH_STRING", " len > 0 ");
            	    }
            	    matchAny();
            	    len--;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end FIXED_LENGTH_STRING

    // $ANTLR start NUMBER
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            // Cami.g:114:8: ( ( '0' .. '9' )+ )
            // Cami.g:115:2: ( '0' .. '9' )+
            {
            // Cami.g:115:2: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Cami.g:115:2: '0' .. '9'
            	    {
            	    matchRange('0','9');

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NUMBER

    public void mTokens() throws RecognitionException {
        // Cami.g:1:8: ( T7 | T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | CAMI_STRING | NUMBER )
        int alt3=12;
        alt3 = dfa3.predict(input);
        switch (alt3) {
            case 1 :
                // Cami.g:1:10: T7
                {
                mT7();

                }
                break;
            case 2 :
                // Cami.g:1:13: T8
                {
                mT8();

                }
                break;
            case 3 :
                // Cami.g:1:16: T9
                {
                mT9();

                }
                break;
            case 4 :
                // Cami.g:1:19: T10
                {
                mT10();

                }
                break;
            case 5 :
                // Cami.g:1:23: T11
                {
                mT11();

                }
                break;
            case 6 :
                // Cami.g:1:27: T12
                {
                mT12();

                }
                break;
            case 7 :
                // Cami.g:1:31: T13
                {
                mT13();

                }
                break;
            case 8 :
                // Cami.g:1:35: T14
                {
                mT14();

                }
                break;
            case 9 :
                // Cami.g:1:39: T15
                {
                mT15();

                }
                break;
            case 10 :
                // Cami.g:1:43: T16
                {
                mT16();

                }
                break;
            case 11 :
                // Cami.g:1:47: CAMI_STRING
                {
                mCAMI_STRING();

                }
                break;
            case 12 :
                // Cami.g:1:59: NUMBER
                {
                mNUMBER();

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\10\uffff\1\17\10\uffff";
    static final String DFA3_eofS =
        "\21\uffff";
    static final String DFA3_minS =
        "\1\51\2\uffff\1\103\1\uffff\1\104\1\101\1\uffff\1\60\10\uffff";
    static final String DFA3_maxS =
        "\1\126\2\uffff\1\123\1\uffff\2\114\1\uffff\1\72\10\uffff";
    static final String DFA3_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\2\uffff\1\11\1\uffff\1\3\1\5\1\6\1"+
        "\10\1\7\1\12\1\14\1\13";
    static final String DFA3_specialS =
        "\21\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\2\uffff\1\4\3\uffff\12\10\14\uffff\1\6\10\uffff\1\3\3\uffff"+
            "\1\1\1\5\1\uffff\1\7",
            "",
            "",
            "\1\11\17\uffff\1\12",
            "",
            "\1\13\7\uffff\1\14",
            "\1\15\12\uffff\1\16",
            "",
            "\12\10\1\20",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T7 | T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | CAMI_STRING | NUMBER );";
        }
    }


}