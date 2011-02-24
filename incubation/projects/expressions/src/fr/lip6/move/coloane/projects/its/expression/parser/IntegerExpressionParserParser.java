// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g 2011-02-24 17:03:36


package fr.lip6.move.coloane.projects.its.expression.parser;

import fr.lip6.move.coloane.projects.its.expression.*;
import fr.lip6.move.coloane.projects.its.antlrutil.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class IntegerExpressionParserParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "OPEN_PAREN", "CLOSE_PAREN", "PLUS", "MINUS", "MULT", "DIV", "SEMICOL", "NUMBER", "VARIABLE", "INFINITY", "LETTER", "DIGIT", "STRING", "DOLLAR", "WS"
    };
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
    public static final int DIV=9;
    public static final int MULT=8;
    public static final int MINUS=7;
    public static final int EOF=-1;
    public static final int STRING=16;

    // delegates
    // delegators


        public IntegerExpressionParserParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public IntegerExpressionParserParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return IntegerExpressionParserParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g"; }


        private IErrorReporter errorReporter = null;
        public void setErrorReporter(IErrorReporter errorReporter) {
            this.errorReporter = errorReporter;
        }
        public void emitErrorMessage(String msg) {
            errorReporter.reportError(msg);
        }



    // $ANTLR start "prog"
    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:41:1: prog returns [IntegerExpression expr] : exp= expression EOF ;
    public final IntegerExpression prog() throws RecognitionException {
        IntegerExpression expr = null;

        IntegerExpression exp = null;


        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:41:39: (exp= expression EOF )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:41:41: exp= expression EOF
            {
            pushFollow(FOLLOW_expression_in_prog122);
            exp=expression();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_prog124); 
             expr = exp;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return expr;
    }
    // $ANTLR end "prog"


    // $ANTLR start "expression"
    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:43:1: expression returns [IntegerExpression expr] : exp1= mult_exp (sign= ( '+' | '-' ) exp2= mult_exp )* ;
    public final IntegerExpression expression() throws RecognitionException {
        IntegerExpression expr = null;

        Token sign=null;
        IntegerExpression exp1 = null;

        IntegerExpression exp2 = null;


        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:43:45: (exp1= mult_exp (sign= ( '+' | '-' ) exp2= mult_exp )* )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:44:3: exp1= mult_exp (sign= ( '+' | '-' ) exp2= mult_exp )*
            {
            pushFollow(FOLLOW_mult_exp_in_expression143);
            exp1=mult_exp();

            state._fsp--;

             expr = exp1; 
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:46:3: (sign= ( '+' | '-' ) exp2= mult_exp )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=PLUS && LA1_0<=MINUS)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:46:5: sign= ( '+' | '-' ) exp2= mult_exp
            	    {
            	    sign=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_mult_exp_in_expression168);
            	    exp2=mult_exp();

            	    state._fsp--;

            	     
            	        if (sign.getText().equals("+")) {
            	          if (expr instanceof Add) {
            	            expr.getChildren().add(exp2);
            	          } else {
            	            IntegerExpression tmp = new Add();
            	            tmp.getChildren().add(expr);
            	            tmp.getChildren().add(exp2);
            	            expr = tmp;
            	          }
            	        } else {
            	          if (expr instanceof Minus) {
            	            expr.getChildren().add(exp2);
            	          } else {
            	            IntegerExpression tmp = new Minus();
            	            tmp.getChildren().add(expr);
            	            tmp.getChildren().add(exp2);
            	            expr = tmp;
            	          }    
            	        }
            	      

            	    }
            	    break;

            	default :
            	    break loop1;
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
        return expr;
    }
    // $ANTLR end "expression"


    // $ANTLR start "mult_exp"
    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:69:1: mult_exp returns [IntegerExpression expr] : exp1= unary_exp (sign= ( '*' | '/' ) exp2= unary_exp )* ;
    public final IntegerExpression mult_exp() throws RecognitionException {
        IntegerExpression expr = null;

        Token sign=null;
        IntegerExpression exp1 = null;

        IntegerExpression exp2 = null;


        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:69:43: (exp1= unary_exp (sign= ( '*' | '/' ) exp2= unary_exp )* )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:70:3: exp1= unary_exp (sign= ( '*' | '/' ) exp2= unary_exp )*
            {
            pushFollow(FOLLOW_unary_exp_in_mult_exp193);
            exp1=unary_exp();

            state._fsp--;


                expr = exp1;
              
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:74:3: (sign= ( '*' | '/' ) exp2= unary_exp )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=MULT && LA2_0<=DIV)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:74:5: sign= ( '*' | '/' ) exp2= unary_exp
            	    {
            	    sign=(Token)input.LT(1);
            	    if ( (input.LA(1)>=MULT && input.LA(1)<=DIV) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_unary_exp_in_mult_exp216);
            	    exp2=unary_exp();

            	    state._fsp--;


            	        if (sign.getText().equals("*")) {
            	          if (expr instanceof Mult) {
            	            expr.getChildren().add(exp2);
            	          } else {
            	            IntegerExpression tmp = new Mult();
            	            tmp.getChildren().add(expr);
            	            tmp.getChildren().add(exp2);
            	            expr = tmp;
            	          }
            	        } else {
            	          if (expr instanceof Div) {
            	            expr.getChildren().add(exp2);
            	          } else {
            	            IntegerExpression tmp = new Div();
            	            tmp.getChildren().add(expr);
            	            tmp.getChildren().add(exp2);
            	            expr = tmp;
            	          }    
            	        }    
            	      

            	    }
            	    break;

            	default :
            	    break loop2;
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
        return expr;
    }
    // $ANTLR end "mult_exp"


    // $ANTLR start "unary_exp"
    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:100:1: unary_exp returns [IntegerExpression expr] : ( '-' exp= unary_exp | exp2= factor_exp );
    public final IntegerExpression unary_exp() throws RecognitionException {
        IntegerExpression expr = null;

        IntegerExpression exp = null;

        IntegerExpression exp2 = null;


        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:101:3: ( '-' exp= unary_exp | exp2= factor_exp )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==MINUS) ) {
                alt3=1;
            }
            else if ( (LA3_0==OPEN_PAREN||(LA3_0>=NUMBER && LA3_0<=INFINITY)) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:101:5: '-' exp= unary_exp
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_unary_exp243); 
                    pushFollow(FOLLOW_unary_exp_in_unary_exp247);
                    exp=unary_exp();

                    state._fsp--;


                        expr = new UnaryMinus(exp);
                      

                    }
                    break;
                case 2 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:105:5: exp2= factor_exp
                    {
                    pushFollow(FOLLOW_factor_exp_in_unary_exp260);
                    exp2=factor_exp();

                    state._fsp--;


                        expr = exp2;
                      

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
        return expr;
    }
    // $ANTLR end "unary_exp"


    // $ANTLR start "factor_exp"
    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:111:1: factor_exp returns [IntegerExpression expr] : (num= NUMBER | var= VARIABLE | inf= INFINITY | exp= par_exp );
    public final IntegerExpression factor_exp() throws RecognitionException {
        IntegerExpression expr = null;

        Token num=null;
        Token var=null;
        Token inf=null;
        IntegerExpression exp = null;


        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:111:45: (num= NUMBER | var= VARIABLE | inf= INFINITY | exp= par_exp )
            int alt4=4;
            switch ( input.LA(1) ) {
            case NUMBER:
                {
                alt4=1;
                }
                break;
            case VARIABLE:
                {
                alt4=2;
                }
                break;
            case INFINITY:
                {
                alt4=3;
                }
                break;
            case OPEN_PAREN:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:112:3: num= NUMBER
                    {
                    num=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_factor_exp285); 

                        expr = new Constant(Integer.parseInt(num.getText()));
                      

                    }
                    break;
                case 2 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:117:3: var= VARIABLE
                    {
                    var=(Token)match(input,VARIABLE,FOLLOW_VARIABLE_in_factor_exp301); 

                        expr = new Variable(var.getText());
                      

                    }
                    break;
                case 3 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:122:3: inf= INFINITY
                    {
                    inf=(Token)match(input,INFINITY,FOLLOW_INFINITY_in_factor_exp316); 

                        expr = new Infinity();
                      

                    }
                    break;
                case 4 :
                    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:127:3: exp= par_exp
                    {
                    pushFollow(FOLLOW_par_exp_in_factor_exp333);
                    exp=par_exp();

                    state._fsp--;


                        expr = exp;
                      

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
        return expr;
    }
    // $ANTLR end "factor_exp"


    // $ANTLR start "par_exp"
    // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:132:1: par_exp returns [IntegerExpression expr] : '(' exp= expression ')' ;
    public final IntegerExpression par_exp() throws RecognitionException {
        IntegerExpression expr = null;

        IntegerExpression exp = null;


        try {
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:132:43: ( '(' exp= expression ')' )
            // C:\\workspace-helios\\fr.lip6.move.coloane.projects.expressions\\src\\fr\\lip6\\move\\coloane\\projects\\its\\expression\\parser\\IntegerExpressionParser.g:132:45: '(' exp= expression ')'
            {
            match(input,OPEN_PAREN,FOLLOW_OPEN_PAREN_in_par_exp351); 
            pushFollow(FOLLOW_expression_in_par_exp355);
            exp=expression();

            state._fsp--;

            match(input,CLOSE_PAREN,FOLLOW_CLOSE_PAREN_in_par_exp357); 
             expr = exp; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return expr;
    }
    // $ANTLR end "par_exp"

    // Delegated rules


 

    public static final BitSet FOLLOW_expression_in_prog122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_prog124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mult_exp_in_expression143 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_set_in_expression158 = new BitSet(new long[]{0x0000000000003890L});
    public static final BitSet FOLLOW_mult_exp_in_expression168 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_unary_exp_in_mult_exp193 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_set_in_mult_exp206 = new BitSet(new long[]{0x0000000000003890L});
    public static final BitSet FOLLOW_unary_exp_in_mult_exp216 = new BitSet(new long[]{0x0000000000000302L});
    public static final BitSet FOLLOW_MINUS_in_unary_exp243 = new BitSet(new long[]{0x0000000000003890L});
    public static final BitSet FOLLOW_unary_exp_in_unary_exp247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_exp_in_unary_exp260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_factor_exp285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VARIABLE_in_factor_exp301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INFINITY_in_factor_exp316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_par_exp_in_factor_exp333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPEN_PAREN_in_par_exp351 = new BitSet(new long[]{0x0000000000003890L});
    public static final BitSet FOLLOW_expression_in_par_exp355 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_CLOSE_PAREN_in_par_exp357 = new BitSet(new long[]{0x0000000000000002L});

}