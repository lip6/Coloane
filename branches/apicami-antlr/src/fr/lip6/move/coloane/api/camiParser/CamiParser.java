package fr.lip6.move.coloane.api.camiParser;

// $ANTLR 3.0.1 Cami.g 2007-08-28 18:01:30

import org.antlr.runtime.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'DB()'", "'FB()'", "'CN('", "','", "')'", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'-1'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'RI('", "'TR('", "'WN('", "'MO('", "'SC('", "'OC('", "'FC()'", "'KO(1,'", "'TL()'", "'FL()'", "'VI('", "'3'", "'DR()'", "'FR('", "'RQ('", "'1'", "'TQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'RO('", "'ME('", "'MT('", "'SU('", "'SI('", "'OS('", "'TD()'", "'FA()'", "'FS()'", "'SS()'", "'RS('", "'DF()'", "'DF(-2,'", "'MS('", "'DQ()'", "'FQ()'", "'CQ('", "'AQ('", "'VQ('", "'EQ('", "'QQ('", "'HQ('"
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



    // $ANTLR start model_definition
    // Cami.g:15:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try {
            // Cami.g:16:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // Cami.g:17:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,8,FOLLOW_8_in_model_definition24); 
            // Cami.g:18:2: ( syntactic | aestetic )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==10||(LA1_0>=13 && LA1_0<=16)) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=17 && LA1_0<=18)||(LA1_0>=20 && LA1_0<=21)) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("18:2: ( syntactic | aestetic )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Cami.g:18:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition29);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:18:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition33);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_model_definition38); 

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
    // $ANTLR end model_definition


    // $ANTLR start syntactic
    // Cami.g:22:1: syntactic : ( node | box | arc | textual_attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // Cami.g:23:2: ( node | box | arc | textual_attribute )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt2=1;
                }
                break;
            case 13:
                {
                alt2=2;
                }
                break;
            case 14:
                {
                alt2=3;
                }
                break;
            case 15:
            case 16:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("22:1: syntactic : ( node | box | arc | textual_attribute );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:24:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic50);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:24:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic54);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:24:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic58);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:24:21: textual_attribute
                    {
                    pushFollow(FOLLOW_textual_attribute_in_syntactic62);
                    textual_attribute();
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
    // $ANTLR end syntactic


    // $ANTLR start node
    // Cami.g:27:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // Cami.g:27:6: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // Cami.g:28:2: 'CN(' CAMI_STRING ',' NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_node74); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node76); 
            match(input,11,FOLLOW_11_in_node78); 
            match(input,NUMBER,FOLLOW_NUMBER_in_node80); 
            match(input,12,FOLLOW_12_in_node82); 

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
    // $ANTLR end node


    // $ANTLR start box
    // Cami.g:31:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // Cami.g:31:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // Cami.g:32:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,13,FOLLOW_13_in_box94); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box96); 
            match(input,11,FOLLOW_11_in_box98); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box100); 
            match(input,11,FOLLOW_11_in_box102); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box104); 
            match(input,12,FOLLOW_12_in_box106); 

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
    // $ANTLR end box


    // $ANTLR start arc
    // Cami.g:35:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // Cami.g:35:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // Cami.g:36:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,14,FOLLOW_14_in_arc118); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc120); 
            match(input,11,FOLLOW_11_in_arc122); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc124); 
            match(input,11,FOLLOW_11_in_arc126); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc128); 
            match(input,11,FOLLOW_11_in_arc130); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc132); 
            match(input,12,FOLLOW_12_in_arc134); 

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
    // $ANTLR end arc


    // $ANTLR start textual_attribute
    // Cami.g:39:1: textual_attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void textual_attribute() throws RecognitionException {
        try {
            // Cami.g:40:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            else if ( (LA3_0==16) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("39:1: textual_attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // Cami.g:41:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_textual_attribute148); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute150); 
                    match(input,11,FOLLOW_11_in_textual_attribute152); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute154); 
                    match(input,11,FOLLOW_11_in_textual_attribute156); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute158); 
                    match(input,12,FOLLOW_12_in_textual_attribute160); 

                    }
                    break;
                case 2 :
                    // Cami.g:42:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_textual_attribute165); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute167); 
                    match(input,11,FOLLOW_11_in_textual_attribute169); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute171); 
                    match(input,11,FOLLOW_11_in_textual_attribute173); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute175); 
                    match(input,11,FOLLOW_11_in_textual_attribute177); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute179); 
                    match(input,11,FOLLOW_11_in_textual_attribute181); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute183); 
                    match(input,12,FOLLOW_12_in_textual_attribute185); 

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
    // $ANTLR end textual_attribute


    // $ANTLR start aestetic
    // Cami.g:46:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // Cami.g:47:2: ( object_position | text_position | intermediary_point )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 17:
            case 18:
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
                    new NoViableAltException("46:1: aestetic : ( object_position | text_position | intermediary_point );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:48:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic198);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:48:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic202);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:48:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic206);
                    intermediary_point();
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
    // $ANTLR end aestetic


    // $ANTLR start object_position
    // Cami.g:51:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // Cami.g:52:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==NUMBER) ) {
                    alt5=1;
                }
                else if ( (LA5_1==19) ) {
                    alt5=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("51:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA5_0==18) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("51:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Cami.g:53:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,17,FOLLOW_17_in_object_position220); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position224); 
                    match(input,11,FOLLOW_11_in_object_position226); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position230); 
                    match(input,11,FOLLOW_11_in_object_position232); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position236); 
                    match(input,12,FOLLOW_12_in_object_position238); 

                    }
                    break;
                case 2 :
                    // Cami.g:54:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,18,FOLLOW_18_in_object_position243); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position247); 
                    match(input,11,FOLLOW_11_in_object_position249); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position253); 
                    match(input,11,FOLLOW_11_in_object_position255); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position259); 
                    match(input,12,FOLLOW_12_in_object_position261); 

                    }
                    break;
                case 3 :
                    // Cami.g:55:4: 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,17,FOLLOW_17_in_object_position266); 
                    match(input,19,FOLLOW_19_in_object_position268); 
                    match(input,11,FOLLOW_11_in_object_position270); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position274); 
                    match(input,11,FOLLOW_11_in_object_position276); 
                    left=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position280); 
                    match(input,11,FOLLOW_11_in_object_position282); 
                    right=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position286); 
                    match(input,11,FOLLOW_11_in_object_position288); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position292); 
                    match(input,11,FOLLOW_11_in_object_position294); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position298); 
                    match(input,12,FOLLOW_12_in_object_position299); 

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
    // $ANTLR end object_position


    // $ANTLR start text_position
    // Cami.g:58:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // Cami.g:59:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // Cami.g:60:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,20,FOLLOW_20_in_text_position312); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position316); 
            match(input,11,FOLLOW_11_in_text_position318); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position322); 
            match(input,11,FOLLOW_11_in_text_position324); 
            h_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position328); 
            match(input,11,FOLLOW_11_in_text_position330); 
            v_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position334); 
            match(input,12,FOLLOW_12_in_text_position336); 

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
    // $ANTLR end text_position


    // $ANTLR start intermediary_point
    // Cami.g:63:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // Cami.g:64:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // Cami.g:65:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,21,FOLLOW_21_in_intermediary_point349); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point351); 
            match(input,11,FOLLOW_11_in_intermediary_point353); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point355); 
            match(input,11,FOLLOW_11_in_intermediary_point357); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point359); 
            match(input,12,FOLLOW_12_in_intermediary_point361); 

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
    // $ANTLR end intermediary_point


    // $ANTLR start dialog_definition
    // Cami.g:72:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        try {
            // Cami.g:73:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // Cami.g:74:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,22,FOLLOW_22_in_dialog_definition377); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition380);
            dialog_creation();
            _fsp--;

            // Cami.g:76:2: ( next_dialog )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==25) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Cami.g:76:2: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition383);
            	    next_dialog();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            match(input,23,FOLLOW_23_in_dialog_definition387); 

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
    // $ANTLR end dialog_definition


    // $ANTLR start dialog_creation
    // Cami.g:80:1: dialog_creation : 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        try {
            // Cami.g:81:2: ( 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')' )
            // Cami.g:82:2: 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')'
            {
            match(input,24,FOLLOW_24_in_dialog_creation399); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation401); 
            match(input,11,FOLLOW_11_in_dialog_creation403); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation405); 
            match(input,11,FOLLOW_11_in_dialog_creation407); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation409); 
            match(input,11,FOLLOW_11_in_dialog_creation411); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation414); 
            match(input,11,FOLLOW_11_in_dialog_creation416); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation418); 
            match(input,11,FOLLOW_11_in_dialog_creation420); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation422); 
            match(input,11,FOLLOW_11_in_dialog_creation424); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation429); 
            match(input,11,FOLLOW_11_in_dialog_creation431); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation433); 
            match(input,11,FOLLOW_11_in_dialog_creation435); 
            // Cami.g:83:25: ( CAMI_STRING )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==CAMI_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:83:25: CAMI_STRING
                    {
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation437); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_dialog_creation440); 

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
    // $ANTLR end dialog_creation


    // $ANTLR start next_dialog
    // Cami.g:86:1: next_dialog : 'DS(' NUMBER ',' CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        try {
            // Cami.g:87:2: ( 'DS(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:88:2: 'DS(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_next_dialog452); 
            match(input,NUMBER,FOLLOW_NUMBER_in_next_dialog454); 
            match(input,11,FOLLOW_11_in_next_dialog456); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog458); 
            match(input,12,FOLLOW_12_in_next_dialog460); 

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
    // $ANTLR end next_dialog


    // $ANTLR start display_dialog
    // Cami.g:91:1: display_dialog : 'AD(' NUMBER ')' ;
    public final void display_dialog() throws RecognitionException {
        try {
            // Cami.g:92:2: ( 'AD(' NUMBER ')' )
            // Cami.g:93:2: 'AD(' NUMBER ')'
            {
            match(input,26,FOLLOW_26_in_display_dialog472); 
            match(input,NUMBER,FOLLOW_NUMBER_in_display_dialog474); 
            match(input,12,FOLLOW_12_in_display_dialog476); 

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
    // $ANTLR end display_dialog


    // $ANTLR start hide_dialog
    // Cami.g:96:1: hide_dialog : 'HD(' NUMBER ')' ;
    public final void hide_dialog() throws RecognitionException {
        try {
            // Cami.g:97:2: ( 'HD(' NUMBER ')' )
            // Cami.g:98:2: 'HD(' NUMBER ')'
            {
            match(input,27,FOLLOW_27_in_hide_dialog489); 
            match(input,NUMBER,FOLLOW_NUMBER_in_hide_dialog491); 
            match(input,12,FOLLOW_12_in_hide_dialog493); 

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
    // $ANTLR end hide_dialog


    // $ANTLR start destroy_dialog
    // Cami.g:101:1: destroy_dialog : 'DG(' NUMBER ')' ;
    public final void destroy_dialog() throws RecognitionException {
        try {
            // Cami.g:102:2: ( 'DG(' NUMBER ')' )
            // Cami.g:103:2: 'DG(' NUMBER ')'
            {
            match(input,28,FOLLOW_28_in_destroy_dialog506); 
            match(input,NUMBER,FOLLOW_NUMBER_in_destroy_dialog508); 
            match(input,12,FOLLOW_12_in_destroy_dialog510); 

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
    // $ANTLR end destroy_dialog


    // $ANTLR start interactive_response
    // Cami.g:106:1: interactive_response : 'RI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // Cami.g:107:2: ( 'RI(' NUMBER ',' NUMBER ')' )
            // Cami.g:108:2: 'RI(' NUMBER ',' NUMBER ')'
            {
            match(input,29,FOLLOW_29_in_interactive_response522); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response524); 
            match(input,11,FOLLOW_11_in_interactive_response526); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response528); 
            match(input,12,FOLLOW_12_in_interactive_response530); 

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
    // $ANTLR end interactive_response


    // $ANTLR start message_to_user
    // Cami.g:118:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // Cami.g:119:2: ( trace_message | warning_message | special_message )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt8=1;
                }
                break;
            case 31:
                {
                alt8=2;
                }
                break;
            case 32:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("118:1: message_to_user : ( trace_message | warning_message | special_message );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // Cami.g:120:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user548);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:120:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user552);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:120:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user556);
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
    // Cami.g:123:1: trace_message : 'TR(' CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        try {
            // Cami.g:124:2: ( 'TR(' CAMI_STRING ')' )
            // Cami.g:125:2: 'TR(' CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_trace_message568); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message570); 
            match(input,12,FOLLOW_12_in_trace_message572); 

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
    // Cami.g:128:1: warning_message : 'WN(' CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        try {
            // Cami.g:129:2: ( 'WN(' CAMI_STRING ')' )
            // Cami.g:130:2: 'WN(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_warning_message584); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message586); 
            match(input,12,FOLLOW_12_in_warning_message588); 

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
    // Cami.g:133:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        try {
            // Cami.g:134:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:135:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message601); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message603); 
            match(input,11,FOLLOW_11_in_special_message605); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message607); 
            match(input,12,FOLLOW_12_in_special_message609); 

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


    // $ANTLR start ack_open_communication
    // Cami.g:141:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        try {
            // Cami.g:142:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:143:2: 'SC(' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_communication625); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication627); 
            match(input,12,FOLLOW_12_in_ack_open_communication629); 

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
    // Cami.g:146:1: ack_open_connection : 'OC(' NUMBER ',' NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        try {
            // Cami.g:147:2: ( 'OC(' NUMBER ',' NUMBER ')' )
            // Cami.g:148:2: 'OC(' NUMBER ',' NUMBER ')'
            {
            match(input,34,FOLLOW_34_in_ack_open_connection642); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection644); 
            match(input,11,FOLLOW_11_in_ack_open_connection646); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection648); 
            match(input,12,FOLLOW_12_in_ack_open_connection650); 

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


    // $ANTLR start close_connection_normal
    // Cami.g:151:1: close_connection_normal : 'FC()' ;
    public final void close_connection_normal() throws RecognitionException {
        try {
            // Cami.g:152:2: ( 'FC()' )
            // Cami.g:153:2: 'FC()'
            {
            match(input,35,FOLLOW_35_in_close_connection_normal662); 

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
    // $ANTLR end close_connection_normal


    // $ANTLR start close_connection_panic
    // Cami.g:156:1: close_connection_panic : 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' ;
    public final void close_connection_panic() throws RecognitionException {
        Token mess=null;
        Token level=null;

        try {
            // Cami.g:157:2: ( 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' )
            // Cami.g:158:2: 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')'
            {
            match(input,36,FOLLOW_36_in_close_connection_panic675); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic679); 
            match(input,11,FOLLOW_11_in_close_connection_panic681); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_close_connection_panic685); 
            match(input,12,FOLLOW_12_in_close_connection_panic687); 

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
    // $ANTLR end close_connection_panic


    // $ANTLR start interlocutor_table
    // Cami.g:163:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // Cami.g:164:2: ( 'TL()' ( body_table )+ 'FL()' )
            // Cami.g:165:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,37,FOLLOW_37_in_interlocutor_table701); 
            // Cami.g:166:2: ( body_table )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==39) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Cami.g:166:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table705);
            	    body_table();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            match(input,38,FOLLOW_38_in_interlocutor_table710); 

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


    // $ANTLR start body_table
    // Cami.g:170:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token new_model=null;

        try {
            // Cami.g:171:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')' )
            // Cami.g:172:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')'
            {
            match(input,39,FOLLOW_39_in_body_table723); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table727); 
            match(input,11,FOLLOW_11_in_body_table729); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table733); 
            match(input,11,FOLLOW_11_in_body_table735); 
            match(input,40,FOLLOW_40_in_body_table737); 
            match(input,11,FOLLOW_11_in_body_table739); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table743); 
            match(input,12,FOLLOW_12_in_body_table745); 

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
    // $ANTLR end body_table


    // $ANTLR start pre_result_reception
    // Cami.g:177:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // Cami.g:178:2: ( question_state ask_hierarchic )
            // Cami.g:179:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception758);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception761);
            ask_hierarchic();
            _fsp--;


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
    // $ANTLR end pre_result_reception


    // $ANTLR start result_reception
    // Cami.g:183:1: result_reception : 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')' ;
    public final void result_reception() throws RecognitionException {
        try {
            // Cami.g:184:2: ( 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')' )
            // Cami.g:185:2: 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')'
            {
            match(input,41,FOLLOW_41_in_result_reception773); 
            pushFollow(FOLLOW_question_reply_in_result_reception776);
            question_reply();
            _fsp--;

            // Cami.g:187:2: ( question_state | special_message | warning_message | result )*
            loop10:
            do {
                int alt10=5;
                switch ( input.LA(1) ) {
                case 45:
                    {
                    alt10=1;
                    }
                    break;
                case 32:
                    {
                    alt10=2;
                    }
                    break;
                case 31:
                    {
                    alt10=3;
                    }
                    break;
                case 46:
                    {
                    alt10=4;
                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // Cami.g:187:4: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_result_reception781);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Cami.g:187:21: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_result_reception785);
            	    special_message();
            	    _fsp--;


            	    }
            	    break;
            	case 3 :
            	    // Cami.g:187:39: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_result_reception789);
            	    warning_message();
            	    _fsp--;


            	    }
            	    break;
            	case 4 :
            	    // Cami.g:187:57: result
            	    {
            	    pushFollow(FOLLOW_result_in_result_reception793);
            	    result();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match(input,42,FOLLOW_42_in_result_reception800); 
            match(input,NUMBER,FOLLOW_NUMBER_in_result_reception802); 
            match(input,12,FOLLOW_12_in_result_reception804); 

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
    // Cami.g:191:1: question_reply : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' '1' ')' ;
    public final void question_reply() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;

        try {
            // Cami.g:192:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' '1' ')' )
            // Cami.g:193:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' '1' ')'
            {
            match(input,43,FOLLOW_43_in_question_reply816); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply820); 
            match(input,11,FOLLOW_11_in_question_reply822); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply826); 
            match(input,11,FOLLOW_11_in_question_reply828); 
            match(input,44,FOLLOW_44_in_question_reply830); 
            match(input,12,FOLLOW_12_in_question_reply832); 

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


    // $ANTLR start question_state
    // Cami.g:196:1: question_state : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' ;
    public final void question_state() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:197:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:198:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')'
            {
            match(input,45,FOLLOW_45_in_question_state845); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state849); 
            match(input,11,FOLLOW_11_in_question_state851); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state855); 
            match(input,11,FOLLOW_11_in_question_state857); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_question_state861); 
            match(input,11,FOLLOW_11_in_question_state863); 
            // Cami.g:198:88: (mess= CAMI_STRING )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==CAMI_STRING) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // Cami.g:198:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state867); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state870); 

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


    // $ANTLR start result
    // Cami.g:201:1: result : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // Cami.g:201:8: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' )
            // Cami.g:202:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,46,FOLLOW_46_in_result882); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result886); 
            match(input,11,FOLLOW_11_in_result888); 
            ensemble_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result892); 
            match(input,12,FOLLOW_12_in_result894); 
            // Cami.g:203:2: ( result_body )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==10||(LA12_0>=13 && LA12_0<=16)||LA12_0==46||(LA12_0>=48 && LA12_0<=54)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Cami.g:203:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result897);
            	    result_body();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            match(input,47,FOLLOW_47_in_result901); 

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


    // $ANTLR start result_body
    // Cami.g:207:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // Cami.g:208:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt13=8;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt13=1;
                }
                break;
            case 48:
                {
                alt13=2;
                }
                break;
            case 49:
                {
                alt13=3;
                }
                break;
            case 50:
                {
                alt13=4;
                }
                break;
            case 51:
                {
                alt13=5;
                }
                break;
            case 52:
                {
                alt13=6;
                }
                break;
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
                {
                alt13=7;
                }
                break;
            case 53:
            case 54:
                {
                alt13=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("207:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // Cami.g:209:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body918);
                    result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:210:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body924);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:211:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body930);
                    attribute_change();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:212:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body936);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:213:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body942);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:214:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body948);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // Cami.g:215:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body954);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // Cami.g:216:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body960);
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
    // Cami.g:219:2: textual_result : 'RT(' CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        try {
            // Cami.g:220:3: ( 'RT(' CAMI_STRING ')' )
            // Cami.g:221:3: 'RT(' CAMI_STRING ')'
            {
            match(input,48,FOLLOW_48_in_textual_result977); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result979); 
            match(input,12,FOLLOW_12_in_textual_result981); 

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


    // $ANTLR start attribute_change
    // Cami.g:224:2: attribute_change : 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token new_value=null;

        try {
            // Cami.g:225:3: ( 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // Cami.g:226:3: 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,49,FOLLOW_49_in_attribute_change998); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change1002); 
            match(input,11,FOLLOW_11_in_attribute_change1004); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1008); 
            match(input,11,FOLLOW_11_in_attribute_change1010); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1014); 
            match(input,12,FOLLOW_12_in_attribute_change1016); 

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
    // $ANTLR end attribute_change


    // $ANTLR start object_designation
    // Cami.g:229:2: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:230:3: ( 'RO(' id= NUMBER ')' )
            // Cami.g:231:3: 'RO(' id= NUMBER ')'
            {
            match(input,50,FOLLOW_50_in_object_designation1033); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1037); 
            match(input,12,FOLLOW_12_in_object_designation1039); 

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
    // Cami.g:234:2: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:235:3: ( 'ME(' id= NUMBER ')' )
            // Cami.g:236:3: 'ME(' id= NUMBER ')'
            {
            match(input,51,FOLLOW_51_in_object_outline1056); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1060); 
            match(input,12,FOLLOW_12_in_object_outline1062); 

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
    // Cami.g:239:2: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token begin=null;
        Token end=null;

        try {
            // Cami.g:240:3: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // Cami.g:241:3: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,52,FOLLOW_52_in_attribute_outline1079); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1083); 
            match(input,11,FOLLOW_11_in_attribute_outline1085); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1089); 
            match(input,11,FOLLOW_11_in_attribute_outline1091); 
            // Cami.g:241:54: (begin= NUMBER )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==NUMBER) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // Cami.g:241:54: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1095); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1098); 
            // Cami.g:241:70: (end= NUMBER )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NUMBER) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // Cami.g:241:70: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1102); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1105); 

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
    // Cami.g:244:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void object_creation() throws RecognitionException {
        try {
            // Cami.g:245:3: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt16=5;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt16=1;
                }
                break;
            case 13:
                {
                alt16=2;
                }
                break;
            case 14:
                {
                alt16=3;
                }
                break;
            case 15:
                {
                alt16=4;
                }
                break;
            case 16:
                {
                alt16=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("244:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // Cami.g:246:4: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1123); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1125); 
                    match(input,11,FOLLOW_11_in_object_creation1127); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1129); 
                    match(input,12,FOLLOW_12_in_object_creation1131); 

                    }
                    break;
                case 2 :
                    // Cami.g:247:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1136); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1138); 
                    match(input,11,FOLLOW_11_in_object_creation1140); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1142); 
                    match(input,11,FOLLOW_11_in_object_creation1144); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1146); 
                    match(input,12,FOLLOW_12_in_object_creation1148); 

                    }
                    break;
                case 3 :
                    // Cami.g:248:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1153); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1155); 
                    match(input,11,FOLLOW_11_in_object_creation1157); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1159); 
                    match(input,11,FOLLOW_11_in_object_creation1161); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1163); 
                    match(input,11,FOLLOW_11_in_object_creation1165); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1167); 
                    match(input,12,FOLLOW_12_in_object_creation1169); 

                    }
                    break;
                case 4 :
                    // Cami.g:249:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1174); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1176); 
                    match(input,11,FOLLOW_11_in_object_creation1178); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1180); 
                    match(input,11,FOLLOW_11_in_object_creation1182); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1184); 
                    match(input,12,FOLLOW_12_in_object_creation1186); 

                    }
                    break;
                case 5 :
                    // Cami.g:250:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1191); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1193); 
                    match(input,11,FOLLOW_11_in_object_creation1195); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1197); 
                    match(input,11,FOLLOW_11_in_object_creation1199); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1201); 
                    match(input,11,FOLLOW_11_in_object_creation1203); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1205); 
                    match(input,11,FOLLOW_11_in_object_creation1207); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1209); 
                    match(input,12,FOLLOW_12_in_object_creation1211); 

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
    // Cami.g:253:2: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // Cami.g:254:3: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==53) ) {
                alt17=1;
            }
            else if ( (LA17_0==54) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("253:2: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // Cami.g:255:5: 'SU(' id= NUMBER ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1230); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1234); 
                    match(input,12,FOLLOW_12_in_object_deletion1236); 

                    }
                    break;
                case 2 :
                    // Cami.g:256:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,54,FOLLOW_54_in_object_deletion1242); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1246); 
                    match(input,11,FOLLOW_11_in_object_deletion1248); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1252); 
                    match(input,12,FOLLOW_12_in_object_deletion1254); 

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


    // $ANTLR start ack_open_session
    // Cami.g:261:1: ack_open_session : 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:262:2: ( 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // Cami.g:263:2: 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,55,FOLLOW_55_in_ack_open_session1270); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1272); 
            match(input,12,FOLLOW_12_in_ack_open_session1273); 
            match(input,56,FOLLOW_56_in_ack_open_session1276); 
            match(input,57,FOLLOW_57_in_ack_open_session1279); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1282);
            interlocutor_table();
            _fsp--;


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


    // $ANTLR start ack_close_current_session
    // Cami.g:269:1: ack_close_current_session : 'FS()' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // Cami.g:270:2: ( 'FS()' )
            // Cami.g:271:2: 'FS()'
            {
            match(input,58,FOLLOW_58_in_ack_close_current_session1296); 

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


    // $ANTLR start ack_suspend_current_session
    // Cami.g:274:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:275:2: ( 'SS()' )
            // Cami.g:276:2: 'SS()'
            {
            match(input,59,FOLLOW_59_in_ack_suspend_current_session1311); 

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
    // Cami.g:279:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:280:2: ( 'RS(' CAMI_STRING ')' )
            // Cami.g:281:2: 'RS(' CAMI_STRING ')'
            {
            match(input,60,FOLLOW_60_in_ack_resume_suspend_current_session1323); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session1325); 
            match(input,12,FOLLOW_12_in_ack_resume_suspend_current_session1327); 

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


    // $ANTLR start ask_for_a_model
    // Cami.g:286:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // Cami.g:287:2: ( ask_simple | ask_hierarchic )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==61) ) {
                alt18=1;
            }
            else if ( (LA18_0==62) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("286:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // Cami.g:288:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model1341);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:288:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model1345);
                    ask_hierarchic();
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
    // $ANTLR end ask_for_a_model


    // $ANTLR start ask_simple
    // Cami.g:291:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // Cami.g:292:2: ( 'DF()' )
            // Cami.g:293:2: 'DF()'
            {
            match(input,61,FOLLOW_61_in_ask_simple1357); 

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
    // $ANTLR end ask_simple


    // $ANTLR start ask_hierarchic
    // Cami.g:296:1: ask_hierarchic : 'DF(-2,' NUMBER ',' NUMBER ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // Cami.g:297:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // Cami.g:298:2: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,62,FOLLOW_62_in_ask_hierarchic1369); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_hierarchic1371); 
            match(input,11,FOLLOW_11_in_ask_hierarchic1373); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_hierarchic1375); 
            match(input,12,FOLLOW_12_in_ask_hierarchic1377); 

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
    // $ANTLR end ask_hierarchic


    // $ANTLR start change_date
    // Cami.g:303:1: change_date : 'MS(' NUMBER ')' ;
    public final void change_date() throws RecognitionException {
        try {
            // Cami.g:304:2: ( 'MS(' NUMBER ')' )
            // Cami.g:305:2: 'MS(' NUMBER ')'
            {
            match(input,63,FOLLOW_63_in_change_date1391); 
            match(input,NUMBER,FOLLOW_NUMBER_in_change_date1393); 
            match(input,12,FOLLOW_12_in_change_date1395); 

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
    // $ANTLR end change_date


    // $ANTLR start service_menu_reception
    // Cami.g:310:1: service_menu_reception : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final void service_menu_reception() throws RecognitionException {
        try {
            // Cami.g:311:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // Cami.g:312:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,64,FOLLOW_64_in_service_menu_reception1409); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception1412);
            menu_name();
            _fsp--;

            // Cami.g:314:2: ( question_add )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==67) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // Cami.g:314:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception1415);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            match(input,65,FOLLOW_65_in_service_menu_reception1419); 

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
    // $ANTLR end service_menu_reception


    // $ANTLR start menu_name
    // Cami.g:318:1: menu_name : 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;

        try {
            // Cami.g:319:2: ( 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // Cami.g:320:2: 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,66,FOLLOW_66_in_menu_name1431); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name1435); 
            match(input,11,FOLLOW_11_in_menu_name1437); 
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name1439); 
            match(input,11,FOLLOW_11_in_menu_name1441); 
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name1443); 
            match(input,12,FOLLOW_12_in_menu_name1445); 

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
    // Cami.g:323:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
    public final void question_add() throws RecognitionException {
        Token parent_menu=null;
        Token entry_name=null;
        Token question_type=null;
        Token question_behavior=null;
        Token set_item=null;
        Token historic=null;
        Token stop_authorized=null;
        Token ouput_formalism=null;
        Token active=null;

        try {
            // Cami.g:324:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:325:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,67,FOLLOW_67_in_question_add1457); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1461); 
            match(input,11,FOLLOW_11_in_question_add1463); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1467); 
            match(input,11,FOLLOW_11_in_question_add1469); 
            // Cami.g:326:16: (question_type= NUMBER )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NUMBER) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // Cami.g:326:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1476); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1479); 
            // Cami.g:326:46: (question_behavior= NUMBER )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==NUMBER) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // Cami.g:326:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1483); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1486); 
            // Cami.g:327:11: (set_item= NUMBER )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==NUMBER) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // Cami.g:327:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1493); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1496); 
            // Cami.g:327:33: (historic= NUMBER )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NUMBER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // Cami.g:327:33: historic= NUMBER
                    {
                    historic=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1501); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1504); 
            // Cami.g:327:61: (stop_authorized= NUMBER )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // Cami.g:327:61: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1508); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1511); 
            // Cami.g:328:18: (ouput_formalism= CAMI_STRING )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==CAMI_STRING) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // Cami.g:328:18: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1518); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1521); 
            // Cami.g:328:42: (active= NUMBER )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // Cami.g:328:42: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1525); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add1528); 

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


    // $ANTLR start service_menu_modification
    // Cami.g:331:1: service_menu_modification : enable_main_question ( question_state )* end_menu_transmission ;
    public final void service_menu_modification() throws RecognitionException {
        try {
            // Cami.g:332:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // Cami.g:333:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification1540);
            enable_main_question();
            _fsp--;

            // Cami.g:334:2: ( question_state )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==45) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // Cami.g:334:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification1543);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification1547);
            end_menu_transmission();
            _fsp--;


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
    // $ANTLR end service_menu_modification


    // $ANTLR start enable_main_question
    // Cami.g:338:1: enable_main_question : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final void enable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // Cami.g:339:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // Cami.g:340:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,68,FOLLOW_68_in_enable_main_question1559); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question1563); 
            match(input,12,FOLLOW_12_in_enable_main_question1565); 

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
    // $ANTLR end enable_main_question


    // $ANTLR start disable_main_question
    // Cami.g:343:1: disable_main_question : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final void disable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // Cami.g:344:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // Cami.g:345:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,69,FOLLOW_69_in_disable_main_question1577); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question1581); 
            match(input,12,FOLLOW_12_in_disable_main_question1583); 

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
    // $ANTLR end disable_main_question


    // $ANTLR start end_menu_transmission
    // Cami.g:348:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        try {
            // Cami.g:349:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:350:2: 'QQ(' NUMBER ')'
            {
            match(input,70,FOLLOW_70_in_end_menu_transmission1595); 
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission1597); 
            match(input,12,FOLLOW_12_in_end_menu_transmission1599); 

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


    // $ANTLR start help_question
    // Cami.g:353:1: help_question : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final void help_question() throws RecognitionException {
        Token question_name=null;
        Token help_message=null;

        try {
            // Cami.g:354:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // Cami.g:355:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,71,FOLLOW_71_in_help_question1611); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1615); 
            match(input,11,FOLLOW_11_in_help_question1617); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1621); 
            match(input,12,FOLLOW_12_in_help_question1623); 

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
    // $ANTLR end help_question


 

    public static final BitSet FOLLOW_8_in_model_definition24 = new BitSet(new long[]{0x000000000037E400L});
    public static final BitSet FOLLOW_syntactic_in_model_definition29 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_aestetic_in_model_definition33 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_definition38 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_attribute_in_syntactic62 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_node74 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node76 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node78 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node80 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_node82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_box94 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box96 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box98 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box100 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box102 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box104 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_box106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_arc118 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc120 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc122 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc124 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc126 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc128 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc130 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc132 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_arc134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_textual_attribute148 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute150 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute152 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute154 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute156 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute158 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_attribute160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_textual_attribute165 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute167 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute169 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute171 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute173 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute175 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute177 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute179 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute181 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute183 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_attribute185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_object_position220 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position224 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position226 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position230 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position232 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position236 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_object_position243 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position247 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position249 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position253 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position255 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position259 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_object_position266 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_object_position268 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position270 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position274 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position276 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position280 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position282 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position286 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position288 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position292 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position294 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position298 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_text_position312 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position316 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position318 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position322 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position324 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position328 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position330 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position334 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_text_position336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_intermediary_point349 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point351 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point353 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point355 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point357 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point359 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_intermediary_point361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_dialog_definition377 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition380 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition383 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_dialog_definition387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_dialog_creation399 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation401 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation403 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation405 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation407 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation409 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation411 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation414 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation416 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation418 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation420 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation422 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation424 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation429 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation431 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation433 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation435 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation437 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_dialog_creation440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_next_dialog452 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_next_dialog454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog456 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog458 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_next_dialog460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_display_dialog472 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_display_dialog474 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_display_dialog476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_hide_dialog489 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_hide_dialog491 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_hide_dialog493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_destroy_dialog506 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_destroy_dialog508 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_destroy_dialog510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_interactive_response522 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response524 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response526 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response528 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_interactive_response530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_trace_message568 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message570 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_trace_message572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_warning_message584 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message586 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_warning_message588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_special_message601 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message603 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message605 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message607 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_communication625 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication627 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ack_open_connection642 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection644 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection646 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection648 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_normal662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_close_connection_panic675 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic679 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic681 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_close_connection_panic685 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_interlocutor_table701 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table705 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_38_in_interlocutor_table710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body_table723 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table727 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table729 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table733 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table735 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_body_table737 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table739 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table743 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception758 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_result_reception773 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_question_reply_in_result_reception776 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception781 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception785 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception789 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_result_in_result_reception793 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_42_in_result_reception800 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception802 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_question_reply816 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply820 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply822 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply826 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply828 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_question_reply830 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_reply832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_question_state845 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state849 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state851 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state855 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state857 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_state861 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state863 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state867 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_result882 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result886 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result888 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result892 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result894 = new BitSet(new long[]{0x007F40000001E400L});
    public static final BitSet FOLLOW_result_body_in_result897 = new BitSet(new long[]{0x007FC0000001E400L});
    public static final BitSet FOLLOW_47_in_result901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_textual_result977 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result979 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_attribute_change998 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change1002 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1004 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1008 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1010 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1014 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_designation1033 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1037 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_outline1056 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1060 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_attribute_outline1079 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1083 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1085 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1089 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1091 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1095 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1098 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1102 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1123 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1125 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1127 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1129 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1136 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1138 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1140 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1142 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1144 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1146 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1153 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1155 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1157 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1159 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1161 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1163 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1165 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1167 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1174 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1176 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1178 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1180 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1182 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1184 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1191 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1193 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1195 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1197 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1199 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1201 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1203 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1205 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1207 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1209 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1230 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1234 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_object_deletion1242 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1246 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1248 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1252 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ack_open_session1270 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1272 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1273 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ack_open_session1276 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ack_open_session1279 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_close_current_session1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ack_suspend_current_session1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ack_resume_suspend_current_session1323 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session1325 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_suspend_current_session1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model1341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ask_simple1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ask_hierarchic1369 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_hierarchic1371 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic1373 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_hierarchic1375 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_change_date1391 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_change_date1393 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_change_date1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_service_menu_reception1409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception1412 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception1415 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_65_in_service_menu_reception1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_menu_name1431 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name1435 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1437 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name1439 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1441 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name1443 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name1445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_question_add1457 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1461 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1463 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1467 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1469 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1476 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1479 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1483 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1486 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1493 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1496 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1501 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1504 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1508 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1511 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1518 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1521 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1525 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification1540 = new BitSet(new long[]{0x0000200000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification1543 = new BitSet(new long[]{0x0000200000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_enable_main_question1559 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question1563 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_disable_main_question1577 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question1581 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question1583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_end_menu_transmission1595 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission1597 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_help_question1611 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1615 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question1617 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1621 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question1623 = new BitSet(new long[]{0x0000000000000002L});

}