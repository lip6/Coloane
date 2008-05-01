package fr.lip6.move.coloane.api.cami;

// $ANTLR 3.0.1 Cami.g 2008-05-02 01:29:58

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiLexer extends Lexer {
    public static final int CAMI_STRING=4;
    public static final int NUMBER=5;
    public static final int T8=8;
    public static final int T9=9;
    public static final int T25=25;
    public static final int Tokens=26;
    public static final int T24=24;
    public static final int EOF=-1;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int FIXED_LENGTH_STRING=6;
    public static final int NEWLINE=7;
    public static final int T10=10;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int T15=15;
    public static final int T16=16;
    public static final int T17=17;
    public static final int T18=18;
    public static final int T19=19;
    public CamiLexer() {;} 
    public CamiLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Cami.g"; }

    // $ANTLR start T8
    public final void mT8() throws RecognitionException {
        try {
            int _type = T8;
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
    // $ANTLR end T8

    // $ANTLR start T9
    public final void mT9() throws RecognitionException {
        try {
            int _type = T9;
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
    // $ANTLR end T9

    // $ANTLR start T10
    public final void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // Cami.g:5:5: ( 'OC(' )
            // Cami.g:5:7: 'OC('
            {
            match("OC("); 


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
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
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
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
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
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
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
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
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
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
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
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
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
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // Cami.g:13:5: ( 'DQ()' )
            // Cami.g:13:7: 'DQ()'
            {
            match("DQ()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // Cami.g:14:5: ( 'FQ()' )
            // Cami.g:14:7: 'FQ()'
            {
            match("FQ()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // Cami.g:15:5: ( 'VQ(' )
            // Cami.g:15:7: 'VQ('
            {
            match("VQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // Cami.g:16:5: ( 'CQ(' )
            // Cami.g:16:7: 'CQ('
            {
            match("CQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // Cami.g:17:5: ( 'AQ(' )
            // Cami.g:17:7: 'AQ('
            {
            match("AQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // Cami.g:18:5: ( 'TQ(' )
            // Cami.g:18:7: 'TQ('
            {
            match("TQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // Cami.g:19:5: ( 'QQ(' )
            // Cami.g:19:7: 'QQ('
            {
            match("QQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // Cami.g:20:5: ( 'MO(' )
            // Cami.g:20:7: 'MO('
            {
            match("MO("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start CAMI_STRING
    public final void mCAMI_STRING() throws RecognitionException {
        try {
            int _type = CAMI_STRING;
            Token fs=null;
            Token NUMBER1=null;

            int nbToRead = 0;
            // Cami.g:271:6: ( NUMBER ':' fs= FIXED_LENGTH_STRING[nbToRead] )
            // Cami.g:272:2: NUMBER ':' fs= FIXED_LENGTH_STRING[nbToRead]
            {
            int NUMBER1Start169 = getCharIndex();
            mNUMBER(); 
            NUMBER1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, NUMBER1Start169, getCharIndex()-1);
            nbToRead = Integer.parseInt(NUMBER1.getText());
            match(':'); 
            int fsStart180 = getCharIndex();
            mFIXED_LENGTH_STRING(nbToRead); 
            fs = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fsStart180, getCharIndex()-1);
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
            // Cami.g:280:2: ( ({...}? => . )* )
            // Cami.g:281:2: ({...}? => . )*
            {
            // Cami.g:281:2: ({...}? => . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\uFFFE')) && ( len > 0 )) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Cami.g:281:4: {...}? => .
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
            // Cami.g:284:8: ( ( '0' .. '9' )+ )
            // Cami.g:285:2: ( '0' .. '9' )+
            {
            // Cami.g:285:2: ( '0' .. '9' )+
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
            	    // Cami.g:285:2: '0' .. '9'
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

    // $ANTLR start NEWLINE
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            // Cami.g:290:2: ( ( ( '\\r' )? '\\n' )+ )
            // Cami.g:291:2: ( ( '\\r' )? '\\n' )+
            {
            // Cami.g:291:2: ( ( '\\r' )? '\\n' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Cami.g:291:4: ( '\\r' )? '\\n'
            	    {
            	    // Cami.g:291:4: ( '\\r' )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0=='\r') ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // Cami.g:291:4: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            skip();

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NEWLINE

    public void mTokens() throws RecognitionException {
        // Cami.g:1:8: ( T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | CAMI_STRING | NUMBER | NEWLINE )
        int alt5=21;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // Cami.g:1:10: T8
                {
                mT8(); 

                }
                break;
            case 2 :
                // Cami.g:1:13: T9
                {
                mT9(); 

                }
                break;
            case 3 :
                // Cami.g:1:16: T10
                {
                mT10(); 

                }
                break;
            case 4 :
                // Cami.g:1:20: T11
                {
                mT11(); 

                }
                break;
            case 5 :
                // Cami.g:1:24: T12
                {
                mT12(); 

                }
                break;
            case 6 :
                // Cami.g:1:28: T13
                {
                mT13(); 

                }
                break;
            case 7 :
                // Cami.g:1:32: T14
                {
                mT14(); 

                }
                break;
            case 8 :
                // Cami.g:1:36: T15
                {
                mT15(); 

                }
                break;
            case 9 :
                // Cami.g:1:40: T16
                {
                mT16(); 

                }
                break;
            case 10 :
                // Cami.g:1:44: T17
                {
                mT17(); 

                }
                break;
            case 11 :
                // Cami.g:1:48: T18
                {
                mT18(); 

                }
                break;
            case 12 :
                // Cami.g:1:52: T19
                {
                mT19(); 

                }
                break;
            case 13 :
                // Cami.g:1:56: T20
                {
                mT20(); 

                }
                break;
            case 14 :
                // Cami.g:1:60: T21
                {
                mT21(); 

                }
                break;
            case 15 :
                // Cami.g:1:64: T22
                {
                mT22(); 

                }
                break;
            case 16 :
                // Cami.g:1:68: T23
                {
                mT23(); 

                }
                break;
            case 17 :
                // Cami.g:1:72: T24
                {
                mT24(); 

                }
                break;
            case 18 :
                // Cami.g:1:76: T25
                {
                mT25(); 

                }
                break;
            case 19 :
                // Cami.g:1:80: CAMI_STRING
                {
                mCAMI_STRING(); 

                }
                break;
            case 20 :
                // Cami.g:1:92: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 21 :
                // Cami.g:1:99: NEWLINE
                {
                mNEWLINE(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\15\uffff\1\31\15\uffff";
    static final String DFA5_eofS =
        "\33\uffff";
    static final String DFA5_minS =
        "\1\12\2\uffff\1\103\1\uffff\1\104\1\101\1\111\5\uffff\1\60\15\uffff";
    static final String DFA5_maxS =
        "\1\126\2\uffff\1\123\1\uffff\3\121\5\uffff\1\72\15\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\3\uffff\1\13\1\16\1\17\1\21\1\22\1"+
        "\uffff\1\25\1\3\1\5\1\10\1\20\1\6\1\7\1\14\1\12\1\11\1\15\1\24\1"+
        "\23";
    static final String DFA5_specialS =
        "\33\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\16\2\uffff\1\16\33\uffff\1\2\2\uffff\1\4\3\uffff\12\15\7"+
            "\uffff\1\12\1\uffff\1\11\1\10\1\uffff\1\6\6\uffff\1\14\1\uffff"+
            "\1\3\1\uffff\1\13\1\uffff\1\1\1\5\1\uffff\1\7",
            "",
            "",
            "\1\17\17\uffff\1\20",
            "",
            "\1\23\7\uffff\1\21\4\uffff\1\22",
            "\1\24\12\uffff\1\26\4\uffff\1\25",
            "\1\27\7\uffff\1\30",
            "",
            "",
            "",
            "",
            "",
            "\12\15\1\32",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | CAMI_STRING | NUMBER | NEWLINE );";
        }
    }
 

}