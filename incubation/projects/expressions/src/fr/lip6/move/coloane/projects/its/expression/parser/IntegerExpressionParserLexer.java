// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g 2011-02-24 17:32:56


package fr.lip6.move.coloane.projects.its.expression.parser;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class IntegerExpressionParserLexer extends Lexer {
    public static final int DOLLAR=17;
    public static final int WS=18;
    public static final int VARIABLE=12;
    public static final int LETTER=14;
    public static final int INFINITY=13;
    public static final int NUMBER=11;
    public static final int SEMICOL=10;
    public static final int OPEN_PAREN=4;
    public static final int PLUS=6;
    public static final int CLOSE_PAREN=5;
    public static final int DIGIT=15;
    public static final int MINUS=7;
    public static final int MULT=8;
    public static final int DIV=9;
    public static final int EOF=-1;
    public static final int STRING=16;

    // delegates
    // delegators

    public IntegerExpressionParserLexer() {;} 
    public IntegerExpressionParserLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public IntegerExpressionParserLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g"; }

    // $ANTLR start "OPEN_PAREN"
    public final void mOPEN_PAREN() throws RecognitionException {
        try {
            int _type = OPEN_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:13:12: ( '(' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:13:14: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OPEN_PAREN"

    // $ANTLR start "CLOSE_PAREN"
    public final void mCLOSE_PAREN() throws RecognitionException {
        try {
            int _type = CLOSE_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:14:13: ( ')' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:14:15: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CLOSE_PAREN"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:15:6: ( '+' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:15:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:16:7: ( '-' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:16:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MULT"
    public final void mMULT() throws RecognitionException {
        try {
            int _type = MULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:17:6: ( '*' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:17:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULT"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:18:5: ( '/' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:18:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "SEMICOL"
    public final void mSEMICOL() throws RecognitionException {
        try {
            int _type = SEMICOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:19:9: ( ';' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:19:11: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMICOL"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:161:17: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:163:17: ( '0' .. '9' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:163:19: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:165:17: ( '\"' ( . )* '\"' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:165:19: '\"' ( . )* '\"'
            {
            match('\"'); 
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:165:22: ( . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='\"') ) {
                    alt1=2;
                }
                else if ( ((LA1_0>='\u0000' && LA1_0<='!')||(LA1_0>='#' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:165:22: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match('\"'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "DOLLAR"
    public final void mDOLLAR() throws RecognitionException {
        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:167:17: ( '$' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:167:19: '$'
            {
            match('$'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "DOLLAR"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:169:5: ( ( ' ' | '\\t' | '\\n' | '\\r' ) )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:169:7: ( ' ' | '\\t' | '\\n' | '\\r' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:171:8: ( DIGIT ( DIGIT )* )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:171:10: DIGIT ( DIGIT )*
            {
            mDIGIT(); 
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:171:16: ( DIGIT )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:171:17: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "INFINITY"
    public final void mINFINITY() throws RecognitionException {
        try {
            int _type = INFINITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:174:10: ( 'inf' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:174:12: 'inf'
            {
            match("inf"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INFINITY"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:11: ( DOLLAR ( STRING | ( LETTER ( LETTER | DIGIT )* ) ) )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:13: DOLLAR ( STRING | ( LETTER ( LETTER | DIGIT )* ) )
            {
            mDOLLAR(); 
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:20: ( STRING | ( LETTER ( LETTER | DIGIT )* ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\"') ) {
                alt4=1;
            }
            else if ( ((LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:22: STRING
                    {
                    mSTRING(); 

                    }
                    break;
                case 2 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:31: ( LETTER ( LETTER | DIGIT )* )
                    {
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:31: ( LETTER ( LETTER | DIGIT )* )
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:32: LETTER ( LETTER | DIGIT )*
                    {
                    mLETTER(); 
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:177:39: ( LETTER | DIGIT )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:
                    	    {
                    	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VARIABLE"

    public void mTokens() throws RecognitionException {
        // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:8: ( OPEN_PAREN | CLOSE_PAREN | PLUS | MINUS | MULT | DIV | SEMICOL | WS | NUMBER | INFINITY | VARIABLE )
        int alt5=11;
        switch ( input.LA(1) ) {
        case '(':
            {
            alt5=1;
            }
            break;
        case ')':
            {
            alt5=2;
            }
            break;
        case '+':
            {
            alt5=3;
            }
            break;
        case '-':
            {
            alt5=4;
            }
            break;
        case '*':
            {
            alt5=5;
            }
            break;
        case '/':
            {
            alt5=6;
            }
            break;
        case ';':
            {
            alt5=7;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt5=8;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt5=9;
            }
            break;
        case 'i':
            {
            alt5=10;
            }
            break;
        case '$':
            {
            alt5=11;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 5, 0, input);

            throw nvae;
        }

        switch (alt5) {
            case 1 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:10: OPEN_PAREN
                {
                mOPEN_PAREN(); 

                }
                break;
            case 2 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:21: CLOSE_PAREN
                {
                mCLOSE_PAREN(); 

                }
                break;
            case 3 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:33: PLUS
                {
                mPLUS(); 

                }
                break;
            case 4 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:38: MINUS
                {
                mMINUS(); 

                }
                break;
            case 5 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:44: MULT
                {
                mMULT(); 

                }
                break;
            case 6 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:49: DIV
                {
                mDIV(); 

                }
                break;
            case 7 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:53: SEMICOL
                {
                mSEMICOL(); 

                }
                break;
            case 8 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:61: WS
                {
                mWS(); 

                }
                break;
            case 9 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:64: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 10 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:71: INFINITY
                {
                mINFINITY(); 

                }
                break;
            case 11 :
                // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:1:80: VARIABLE
                {
                mVARIABLE(); 

                }
                break;

        }

    }


 

}