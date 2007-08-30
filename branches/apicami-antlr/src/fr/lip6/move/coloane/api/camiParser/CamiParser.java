// $ANTLR 3.0.1 Cami.g 2007-08-30 21:41:24

package fr.lip6.move.coloane.api.camiParser;

import fr.lip6.move.coloane.api.session.states.authentication.*;


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
    // Cami.g:21:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try {
            // Cami.g:22:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // Cami.g:23:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,8,FOLLOW_8_in_model_definition32); 
            // Cami.g:24:2: ( syntactic | aestetic )
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
                    new NoViableAltException("24:2: ( syntactic | aestetic )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Cami.g:24:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition37);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:24:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition41);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_model_definition46); 

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
    // Cami.g:28:1: syntactic : ( node | box | arc | textual_attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // Cami.g:29:2: ( node | box | arc | textual_attribute )
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
                    new NoViableAltException("28:1: syntactic : ( node | box | arc | textual_attribute );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Cami.g:30:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic58);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:30:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic62);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:30:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic66);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:30:21: textual_attribute
                    {
                    pushFollow(FOLLOW_textual_attribute_in_syntactic70);
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
    // Cami.g:33:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // Cami.g:33:6: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // Cami.g:34:2: 'CN(' CAMI_STRING ',' NUMBER ')'
            {
            match(input,10,FOLLOW_10_in_node82); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node84); 
            match(input,11,FOLLOW_11_in_node86); 
            match(input,NUMBER,FOLLOW_NUMBER_in_node88); 
            match(input,12,FOLLOW_12_in_node90); 

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
    // Cami.g:37:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // Cami.g:37:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // Cami.g:38:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,13,FOLLOW_13_in_box102); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box104); 
            match(input,11,FOLLOW_11_in_box106); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box108); 
            match(input,11,FOLLOW_11_in_box110); 
            match(input,NUMBER,FOLLOW_NUMBER_in_box112); 
            match(input,12,FOLLOW_12_in_box114); 

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
    // Cami.g:41:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // Cami.g:41:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // Cami.g:42:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,14,FOLLOW_14_in_arc126); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc128); 
            match(input,11,FOLLOW_11_in_arc130); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc132); 
            match(input,11,FOLLOW_11_in_arc134); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc136); 
            match(input,11,FOLLOW_11_in_arc138); 
            match(input,NUMBER,FOLLOW_NUMBER_in_arc140); 
            match(input,12,FOLLOW_12_in_arc142); 

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
    // Cami.g:45:1: textual_attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void textual_attribute() throws RecognitionException {
        try {
            // Cami.g:46:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
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
                    new NoViableAltException("45:1: textual_attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // Cami.g:47:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_textual_attribute156); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute158); 
                    match(input,11,FOLLOW_11_in_textual_attribute160); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute162); 
                    match(input,11,FOLLOW_11_in_textual_attribute164); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute166); 
                    match(input,12,FOLLOW_12_in_textual_attribute168); 

                    }
                    break;
                case 2 :
                    // Cami.g:48:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_textual_attribute173); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute175); 
                    match(input,11,FOLLOW_11_in_textual_attribute177); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute179); 
                    match(input,11,FOLLOW_11_in_textual_attribute181); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute183); 
                    match(input,11,FOLLOW_11_in_textual_attribute185); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_textual_attribute187); 
                    match(input,11,FOLLOW_11_in_textual_attribute189); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute191); 
                    match(input,12,FOLLOW_12_in_textual_attribute193); 

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
    // Cami.g:52:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // Cami.g:53:2: ( object_position | text_position | intermediary_point )
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
                    new NoViableAltException("52:1: aestetic : ( object_position | text_position | intermediary_point );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Cami.g:54:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic206);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:54:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic210);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:54:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic214);
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
    // Cami.g:57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // Cami.g:58:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==19) ) {
                    alt5=3;
                }
                else if ( (LA5_1==NUMBER) ) {
                    alt5=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA5_0==18) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Cami.g:59:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,17,FOLLOW_17_in_object_position228); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position232); 
                    match(input,11,FOLLOW_11_in_object_position234); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position238); 
                    match(input,11,FOLLOW_11_in_object_position240); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position244); 
                    match(input,12,FOLLOW_12_in_object_position246); 

                    }
                    break;
                case 2 :
                    // Cami.g:60:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
                    {
                    match(input,18,FOLLOW_18_in_object_position251); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position255); 
                    match(input,11,FOLLOW_11_in_object_position257); 
                    h_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position261); 
                    match(input,11,FOLLOW_11_in_object_position263); 
                    v_distance=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position267); 
                    match(input,12,FOLLOW_12_in_object_position269); 

                    }
                    break;
                case 3 :
                    // Cami.g:61:4: 'PO(' '-1' ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,17,FOLLOW_17_in_object_position274); 
                    match(input,19,FOLLOW_19_in_object_position276); 
                    match(input,11,FOLLOW_11_in_object_position278); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position282); 
                    match(input,11,FOLLOW_11_in_object_position284); 
                    left=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position288); 
                    match(input,11,FOLLOW_11_in_object_position290); 
                    right=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position294); 
                    match(input,11,FOLLOW_11_in_object_position296); 
                    top=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position300); 
                    match(input,11,FOLLOW_11_in_object_position302); 
                    bottom=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position306); 
                    match(input,12,FOLLOW_12_in_object_position307); 

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
    // Cami.g:64:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // Cami.g:65:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // Cami.g:66:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,20,FOLLOW_20_in_text_position320); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position324); 
            match(input,11,FOLLOW_11_in_text_position326); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position330); 
            match(input,11,FOLLOW_11_in_text_position332); 
            h_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position336); 
            match(input,11,FOLLOW_11_in_text_position338); 
            v_distance=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_text_position342); 
            match(input,12,FOLLOW_12_in_text_position344); 

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
    // Cami.g:69:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // Cami.g:70:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // Cami.g:71:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,21,FOLLOW_21_in_intermediary_point357); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point359); 
            match(input,11,FOLLOW_11_in_intermediary_point361); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point363); 
            match(input,11,FOLLOW_11_in_intermediary_point365); 
            match(input,NUMBER,FOLLOW_NUMBER_in_intermediary_point367); 
            match(input,12,FOLLOW_12_in_intermediary_point369); 

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
    // Cami.g:78:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        try {
            // Cami.g:79:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // Cami.g:80:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,22,FOLLOW_22_in_dialog_definition385); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition388);
            dialog_creation();
            _fsp--;

            // Cami.g:82:2: ( next_dialog )+
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
            	    // Cami.g:82:2: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition391);
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

            match(input,23,FOLLOW_23_in_dialog_definition395); 

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
    // Cami.g:86:1: dialog_creation : 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        try {
            // Cami.g:87:2: ( 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')' )
            // Cami.g:88:2: 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')'
            {
            match(input,24,FOLLOW_24_in_dialog_creation407); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation409); 
            match(input,11,FOLLOW_11_in_dialog_creation411); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation413); 
            match(input,11,FOLLOW_11_in_dialog_creation415); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation417); 
            match(input,11,FOLLOW_11_in_dialog_creation419); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation422); 
            match(input,11,FOLLOW_11_in_dialog_creation424); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation426); 
            match(input,11,FOLLOW_11_in_dialog_creation428); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation430); 
            match(input,11,FOLLOW_11_in_dialog_creation432); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation437); 
            match(input,11,FOLLOW_11_in_dialog_creation439); 
            match(input,NUMBER,FOLLOW_NUMBER_in_dialog_creation441); 
            match(input,11,FOLLOW_11_in_dialog_creation443); 
            // Cami.g:89:25: ( CAMI_STRING )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==CAMI_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Cami.g:89:25: CAMI_STRING
                    {
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation445); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_dialog_creation448); 

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
    // Cami.g:92:1: next_dialog : 'DS(' NUMBER ',' CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        try {
            // Cami.g:93:2: ( 'DS(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:94:2: 'DS(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_next_dialog460); 
            match(input,NUMBER,FOLLOW_NUMBER_in_next_dialog462); 
            match(input,11,FOLLOW_11_in_next_dialog464); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog466); 
            match(input,12,FOLLOW_12_in_next_dialog468); 

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
    // Cami.g:97:1: display_dialog : 'AD(' NUMBER ')' ;
    public final void display_dialog() throws RecognitionException {
        try {
            // Cami.g:98:2: ( 'AD(' NUMBER ')' )
            // Cami.g:99:2: 'AD(' NUMBER ')'
            {
            match(input,26,FOLLOW_26_in_display_dialog480); 
            match(input,NUMBER,FOLLOW_NUMBER_in_display_dialog482); 
            match(input,12,FOLLOW_12_in_display_dialog484); 

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
    // Cami.g:102:1: hide_dialog : 'HD(' NUMBER ')' ;
    public final void hide_dialog() throws RecognitionException {
        try {
            // Cami.g:103:2: ( 'HD(' NUMBER ')' )
            // Cami.g:104:2: 'HD(' NUMBER ')'
            {
            match(input,27,FOLLOW_27_in_hide_dialog497); 
            match(input,NUMBER,FOLLOW_NUMBER_in_hide_dialog499); 
            match(input,12,FOLLOW_12_in_hide_dialog501); 

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
    // Cami.g:107:1: destroy_dialog : 'DG(' NUMBER ')' ;
    public final void destroy_dialog() throws RecognitionException {
        try {
            // Cami.g:108:2: ( 'DG(' NUMBER ')' )
            // Cami.g:109:2: 'DG(' NUMBER ')'
            {
            match(input,28,FOLLOW_28_in_destroy_dialog514); 
            match(input,NUMBER,FOLLOW_NUMBER_in_destroy_dialog516); 
            match(input,12,FOLLOW_12_in_destroy_dialog518); 

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
    // Cami.g:112:1: interactive_response : 'RI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // Cami.g:113:2: ( 'RI(' NUMBER ',' NUMBER ')' )
            // Cami.g:114:2: 'RI(' NUMBER ',' NUMBER ')'
            {
            match(input,29,FOLLOW_29_in_interactive_response530); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response532); 
            match(input,11,FOLLOW_11_in_interactive_response534); 
            match(input,NUMBER,FOLLOW_NUMBER_in_interactive_response536); 
            match(input,12,FOLLOW_12_in_interactive_response538); 

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
    // Cami.g:124:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // Cami.g:125:2: ( trace_message | warning_message | special_message )
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
                    new NoViableAltException("124:1: message_to_user : ( trace_message | warning_message | special_message );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // Cami.g:126:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user556);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:126:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user560);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:126:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user564);
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
    // Cami.g:129:1: trace_message : 'TR(' CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        try {
            // Cami.g:130:2: ( 'TR(' CAMI_STRING ')' )
            // Cami.g:131:2: 'TR(' CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_trace_message576); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message578); 
            match(input,12,FOLLOW_12_in_trace_message580); 

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
    // Cami.g:134:1: warning_message : 'WN(' CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        try {
            // Cami.g:135:2: ( 'WN(' CAMI_STRING ')' )
            // Cami.g:136:2: 'WN(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_warning_message592); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message594); 
            match(input,12,FOLLOW_12_in_warning_message596); 

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
    // Cami.g:139:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        try {
            // Cami.g:140:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // Cami.g:141:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message609); 
            match(input,NUMBER,FOLLOW_NUMBER_in_special_message611); 
            match(input,11,FOLLOW_11_in_special_message613); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message615); 
            match(input,12,FOLLOW_12_in_special_message617); 

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


    // $ANTLR start open_communication
    // Cami.g:147:1: open_communication returns [AuthenticationAck message] : ( ack_open_communication | close_connection_panic );
    public final AuthenticationAck open_communication() throws RecognitionException {
        AuthenticationAck message = null;

        String close_connection_panic1 = null;


        try {
            // Cami.g:149:2: ( ack_open_communication | close_connection_panic )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==33) ) {
                alt9=1;
            }
            else if ( (LA9_0==36) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("147:1: open_communication returns [AuthenticationAck message] : ( ack_open_communication | close_connection_panic );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Cami.g:150:4: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_open_communication640);
                    ack_open_communication();
                    _fsp--;


                    	  	message = new AuthenticationAck();
                    	  

                    }
                    break;
                case 2 :
                    // Cami.g:154:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_open_communication651);
                    close_connection_panic1=close_connection_panic();
                    _fsp--;


                    	  	if( true ) // to avoid an error in the generated code
                    		  	throw new AuthenticationFailure(close_connection_panic1);
                    	  

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
        return message;
    }
    // $ANTLR end open_communication


    // $ANTLR start check_version
    // Cami.g:161:1: check_version returns [AuthenticationAck message] : ( ack_open_connection | close_connection_panic );
    public final AuthenticationAck check_version() throws RecognitionException {
        AuthenticationAck message = null;

        String close_connection_panic2 = null;


        try {
            // Cami.g:163:2: ( ack_open_connection | close_connection_panic )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==34) ) {
                alt10=1;
            }
            else if ( (LA10_0==36) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("161:1: check_version returns [AuthenticationAck message] : ( ack_open_connection | close_connection_panic );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // Cami.g:164:4: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_check_version675);
                    ack_open_connection();
                    _fsp--;


                    	  	message = new AuthenticationAck();  
                    	  

                    }
                    break;
                case 2 :
                    // Cami.g:168:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_check_version685);
                    close_connection_panic2=close_connection_panic();
                    _fsp--;


                    	  	if(true) // to avoid an error in the generated code
                    	  		throw new VersionFailure(close_connection_panic2);
                    	  

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
        return message;
    }
    // $ANTLR end check_version


    // $ANTLR start ack_open_communication
    // Cami.g:175:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        try {
            // Cami.g:176:2: ( 'SC(' CAMI_STRING ')' )
            // Cami.g:177:2: 'SC(' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_communication702); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication704); 
            match(input,12,FOLLOW_12_in_ack_open_communication706); 

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
    // Cami.g:180:1: ack_open_connection : 'OC(' NUMBER ',' NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        try {
            // Cami.g:181:2: ( 'OC(' NUMBER ',' NUMBER ')' )
            // Cami.g:182:2: 'OC(' NUMBER ',' NUMBER ')'
            {
            match(input,34,FOLLOW_34_in_ack_open_connection719); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection721); 
            match(input,11,FOLLOW_11_in_ack_open_connection723); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection725); 
            match(input,12,FOLLOW_12_in_ack_open_connection727); 

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
    // Cami.g:185:1: close_connection_normal : 'FC()' ;
    public final void close_connection_normal() throws RecognitionException {
        try {
            // Cami.g:186:2: ( 'FC()' )
            // Cami.g:187:2: 'FC()'
            {
            match(input,35,FOLLOW_35_in_close_connection_normal739); 

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
    // Cami.g:190:1: close_connection_panic returns [String s] : 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' ;
    public final String close_connection_panic() throws RecognitionException {
        String s = null;

        Token mess=null;
        Token level=null;

        try {
            // Cami.g:192:2: ( 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' )
            // Cami.g:193:2: 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')'
            {
            match(input,36,FOLLOW_36_in_close_connection_panic757); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic761); 
            match(input,11,FOLLOW_11_in_close_connection_panic763); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_close_connection_panic767); 
            match(input,12,FOLLOW_12_in_close_connection_panic769); 

            		s=mess.getText();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return s;
    }
    // $ANTLR end close_connection_panic


    // $ANTLR start interlocutor_table
    // Cami.g:201:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // Cami.g:202:2: ( 'TL()' ( body_table )+ 'FL()' )
            // Cami.g:203:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,37,FOLLOW_37_in_interlocutor_table786); 
            // Cami.g:204:2: ( body_table )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==39) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // Cami.g:204:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table790);
            	    body_table();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            match(input,38,FOLLOW_38_in_interlocutor_table795); 

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
    // Cami.g:208:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token new_model=null;

        try {
            // Cami.g:209:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')' )
            // Cami.g:210:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')'
            {
            match(input,39,FOLLOW_39_in_body_table808); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table812); 
            match(input,11,FOLLOW_11_in_body_table814); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table818); 
            match(input,11,FOLLOW_11_in_body_table820); 
            match(input,40,FOLLOW_40_in_body_table822); 
            match(input,11,FOLLOW_11_in_body_table824); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table828); 
            match(input,12,FOLLOW_12_in_body_table830); 

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
    // Cami.g:215:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // Cami.g:216:2: ( question_state ask_hierarchic )
            // Cami.g:217:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception843);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception846);
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
    // Cami.g:221:1: result_reception : 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')' ;
    public final void result_reception() throws RecognitionException {
        try {
            // Cami.g:222:2: ( 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')' )
            // Cami.g:223:2: 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')'
            {
            match(input,41,FOLLOW_41_in_result_reception858); 
            pushFollow(FOLLOW_question_reply_in_result_reception861);
            question_reply();
            _fsp--;

            // Cami.g:225:2: ( question_state | special_message | warning_message | result )*
            loop12:
            do {
                int alt12=5;
                switch ( input.LA(1) ) {
                case 45:
                    {
                    alt12=1;
                    }
                    break;
                case 32:
                    {
                    alt12=2;
                    }
                    break;
                case 31:
                    {
                    alt12=3;
                    }
                    break;
                case 46:
                    {
                    alt12=4;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // Cami.g:225:4: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_result_reception866);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // Cami.g:225:21: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_result_reception870);
            	    special_message();
            	    _fsp--;


            	    }
            	    break;
            	case 3 :
            	    // Cami.g:225:39: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_result_reception874);
            	    warning_message();
            	    _fsp--;


            	    }
            	    break;
            	case 4 :
            	    // Cami.g:225:57: result
            	    {
            	    pushFollow(FOLLOW_result_in_result_reception878);
            	    result();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match(input,42,FOLLOW_42_in_result_reception885); 
            match(input,NUMBER,FOLLOW_NUMBER_in_result_reception887); 
            match(input,12,FOLLOW_12_in_result_reception889); 

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
    // Cami.g:229:1: question_reply : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' '1' ')' ;
    public final void question_reply() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;

        try {
            // Cami.g:230:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' '1' ')' )
            // Cami.g:231:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' '1' ')'
            {
            match(input,43,FOLLOW_43_in_question_reply901); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply905); 
            match(input,11,FOLLOW_11_in_question_reply907); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply911); 
            match(input,11,FOLLOW_11_in_question_reply913); 
            match(input,44,FOLLOW_44_in_question_reply915); 
            match(input,12,FOLLOW_12_in_question_reply917); 

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
    // Cami.g:234:1: question_state : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' ;
    public final void question_state() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // Cami.g:235:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' )
            // Cami.g:236:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')'
            {
            match(input,45,FOLLOW_45_in_question_state930); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state934); 
            match(input,11,FOLLOW_11_in_question_state936); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state940); 
            match(input,11,FOLLOW_11_in_question_state942); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_question_state946); 
            match(input,11,FOLLOW_11_in_question_state948); 
            // Cami.g:236:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // Cami.g:236:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state952); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state955); 

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
    // Cami.g:239:1: result : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // Cami.g:239:8: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' )
            // Cami.g:240:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,46,FOLLOW_46_in_result967); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result971); 
            match(input,11,FOLLOW_11_in_result973); 
            ensemble_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result977); 
            match(input,12,FOLLOW_12_in_result979); 
            // Cami.g:241:2: ( result_body )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==10||(LA14_0>=13 && LA14_0<=16)||LA14_0==46||(LA14_0>=48 && LA14_0<=54)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // Cami.g:241:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result982);
            	    result_body();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            match(input,47,FOLLOW_47_in_result986); 

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
    // Cami.g:245:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // Cami.g:246:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt15=8;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt15=1;
                }
                break;
            case 48:
                {
                alt15=2;
                }
                break;
            case 49:
                {
                alt15=3;
                }
                break;
            case 50:
                {
                alt15=4;
                }
                break;
            case 51:
                {
                alt15=5;
                }
                break;
            case 52:
                {
                alt15=6;
                }
                break;
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
                {
                alt15=7;
                }
                break;
            case 53:
            case 54:
                {
                alt15=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("245:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // Cami.g:247:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1003);
                    result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:248:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1009);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // Cami.g:249:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1015);
                    attribute_change();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // Cami.g:250:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1021);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // Cami.g:251:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1027);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // Cami.g:252:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1033);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // Cami.g:253:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1039);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // Cami.g:254:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1045);
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
    // Cami.g:257:2: textual_result : 'RT(' CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        try {
            // Cami.g:258:3: ( 'RT(' CAMI_STRING ')' )
            // Cami.g:259:3: 'RT(' CAMI_STRING ')'
            {
            match(input,48,FOLLOW_48_in_textual_result1062); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1064); 
            match(input,12,FOLLOW_12_in_textual_result1066); 

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
    // Cami.g:262:2: attribute_change : 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token new_value=null;

        try {
            // Cami.g:263:3: ( 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // Cami.g:264:3: 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,49,FOLLOW_49_in_attribute_change1083); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change1087); 
            match(input,11,FOLLOW_11_in_attribute_change1089); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1093); 
            match(input,11,FOLLOW_11_in_attribute_change1095); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1099); 
            match(input,12,FOLLOW_12_in_attribute_change1101); 

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
    // Cami.g:267:2: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:268:3: ( 'RO(' id= NUMBER ')' )
            // Cami.g:269:3: 'RO(' id= NUMBER ')'
            {
            match(input,50,FOLLOW_50_in_object_designation1118); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1122); 
            match(input,12,FOLLOW_12_in_object_designation1124); 

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
    // Cami.g:272:2: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // Cami.g:273:3: ( 'ME(' id= NUMBER ')' )
            // Cami.g:274:3: 'ME(' id= NUMBER ')'
            {
            match(input,51,FOLLOW_51_in_object_outline1141); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1145); 
            match(input,12,FOLLOW_12_in_object_outline1147); 

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
    // Cami.g:277:2: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token begin=null;
        Token end=null;

        try {
            // Cami.g:278:3: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // Cami.g:279:3: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,52,FOLLOW_52_in_attribute_outline1164); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1168); 
            match(input,11,FOLLOW_11_in_attribute_outline1170); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1174); 
            match(input,11,FOLLOW_11_in_attribute_outline1176); 
            // Cami.g:279:54: (begin= NUMBER )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==NUMBER) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // Cami.g:279:54: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1180); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1183); 
            // Cami.g:279:70: (end= NUMBER )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NUMBER) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // Cami.g:279:70: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1187); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1190); 

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
    // Cami.g:282:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void object_creation() throws RecognitionException {
        try {
            // Cami.g:283:3: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
            int alt18=5;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt18=1;
                }
                break;
            case 13:
                {
                alt18=2;
                }
                break;
            case 14:
                {
                alt18=3;
                }
                break;
            case 15:
                {
                alt18=4;
                }
                break;
            case 16:
                {
                alt18=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("282:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // Cami.g:284:4: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1208); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1210); 
                    match(input,11,FOLLOW_11_in_object_creation1212); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1214); 
                    match(input,12,FOLLOW_12_in_object_creation1216); 

                    }
                    break;
                case 2 :
                    // Cami.g:285:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1221); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1223); 
                    match(input,11,FOLLOW_11_in_object_creation1225); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1227); 
                    match(input,11,FOLLOW_11_in_object_creation1229); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1231); 
                    match(input,12,FOLLOW_12_in_object_creation1233); 

                    }
                    break;
                case 3 :
                    // Cami.g:286:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1238); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1240); 
                    match(input,11,FOLLOW_11_in_object_creation1242); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1244); 
                    match(input,11,FOLLOW_11_in_object_creation1246); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1248); 
                    match(input,11,FOLLOW_11_in_object_creation1250); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1252); 
                    match(input,12,FOLLOW_12_in_object_creation1254); 

                    }
                    break;
                case 4 :
                    // Cami.g:287:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1259); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1261); 
                    match(input,11,FOLLOW_11_in_object_creation1263); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1265); 
                    match(input,11,FOLLOW_11_in_object_creation1267); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1269); 
                    match(input,12,FOLLOW_12_in_object_creation1271); 

                    }
                    break;
                case 5 :
                    // Cami.g:288:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1276); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1278); 
                    match(input,11,FOLLOW_11_in_object_creation1280); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1282); 
                    match(input,11,FOLLOW_11_in_object_creation1284); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1286); 
                    match(input,11,FOLLOW_11_in_object_creation1288); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1290); 
                    match(input,11,FOLLOW_11_in_object_creation1292); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1294); 
                    match(input,12,FOLLOW_12_in_object_creation1296); 

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
    // Cami.g:291:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // Cami.g:292:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==53) ) {
                alt19=1;
            }
            else if ( (LA19_0==54) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("291:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // Cami.g:293:5: 'SU(' id= NUMBER ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1313); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1317); 
                    match(input,12,FOLLOW_12_in_object_deletion1319); 

                    }
                    break;
                case 2 :
                    // Cami.g:294:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,54,FOLLOW_54_in_object_deletion1325); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1329); 
                    match(input,11,FOLLOW_11_in_object_deletion1331); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1335); 
                    match(input,12,FOLLOW_12_in_object_deletion1337); 

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
    // Cami.g:299:1: ack_open_session : 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final void ack_open_session() throws RecognitionException {
        try {
            // Cami.g:300:2: ( 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // Cami.g:301:2: 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,55,FOLLOW_55_in_ack_open_session1353); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1355); 
            match(input,12,FOLLOW_12_in_ack_open_session1356); 
            match(input,56,FOLLOW_56_in_ack_open_session1359); 
            match(input,57,FOLLOW_57_in_ack_open_session1362); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1365);
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
    // Cami.g:307:1: ack_close_current_session : 'FS()' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // Cami.g:308:2: ( 'FS()' )
            // Cami.g:309:2: 'FS()'
            {
            match(input,58,FOLLOW_58_in_ack_close_current_session1379); 

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
    // Cami.g:312:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:313:2: ( 'SS()' )
            // Cami.g:314:2: 'SS()'
            {
            match(input,59,FOLLOW_59_in_ack_suspend_current_session1394); 

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
    // Cami.g:317:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        try {
            // Cami.g:318:2: ( 'RS(' CAMI_STRING ')' )
            // Cami.g:319:2: 'RS(' CAMI_STRING ')'
            {
            match(input,60,FOLLOW_60_in_ack_resume_suspend_current_session1406); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session1408); 
            match(input,12,FOLLOW_12_in_ack_resume_suspend_current_session1410); 

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
    // Cami.g:324:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // Cami.g:325:2: ( ask_simple | ask_hierarchic )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==61) ) {
                alt20=1;
            }
            else if ( (LA20_0==62) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("324:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // Cami.g:326:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model1424);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // Cami.g:326:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model1428);
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
    // Cami.g:329:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // Cami.g:330:2: ( 'DF()' )
            // Cami.g:331:2: 'DF()'
            {
            match(input,61,FOLLOW_61_in_ask_simple1440); 

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
    // Cami.g:334:1: ask_hierarchic : 'DF(-2,' NUMBER ',' NUMBER ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // Cami.g:335:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // Cami.g:336:2: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,62,FOLLOW_62_in_ask_hierarchic1452); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_hierarchic1454); 
            match(input,11,FOLLOW_11_in_ask_hierarchic1456); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_hierarchic1458); 
            match(input,12,FOLLOW_12_in_ask_hierarchic1460); 

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
    // Cami.g:341:1: change_date : 'MS(' NUMBER ')' ;
    public final void change_date() throws RecognitionException {
        try {
            // Cami.g:342:2: ( 'MS(' NUMBER ')' )
            // Cami.g:343:2: 'MS(' NUMBER ')'
            {
            match(input,63,FOLLOW_63_in_change_date1474); 
            match(input,NUMBER,FOLLOW_NUMBER_in_change_date1476); 
            match(input,12,FOLLOW_12_in_change_date1478); 

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
    // Cami.g:348:1: service_menu_reception : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final void service_menu_reception() throws RecognitionException {
        try {
            // Cami.g:349:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // Cami.g:350:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,64,FOLLOW_64_in_service_menu_reception1492); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception1495);
            menu_name();
            _fsp--;

            // Cami.g:352:2: ( question_add )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==67) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // Cami.g:352:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception1498);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            match(input,65,FOLLOW_65_in_service_menu_reception1502); 

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
    // Cami.g:356:1: menu_name : 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;

        try {
            // Cami.g:357:2: ( 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // Cami.g:358:2: 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,66,FOLLOW_66_in_menu_name1514); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name1518); 
            match(input,11,FOLLOW_11_in_menu_name1520); 
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name1522); 
            match(input,11,FOLLOW_11_in_menu_name1524); 
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name1526); 
            match(input,12,FOLLOW_12_in_menu_name1528); 

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
    // Cami.g:361:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // Cami.g:362:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // Cami.g:363:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,67,FOLLOW_67_in_question_add1540); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1544); 
            match(input,11,FOLLOW_11_in_question_add1546); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1550); 
            match(input,11,FOLLOW_11_in_question_add1552); 
            // Cami.g:364:16: (question_type= NUMBER )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==NUMBER) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // Cami.g:364:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1559); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1562); 
            // Cami.g:364:46: (question_behavior= NUMBER )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NUMBER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // Cami.g:364:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1566); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1569); 
            // Cami.g:365:11: (set_item= NUMBER )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // Cami.g:365:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1576); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1579); 
            // Cami.g:365:33: (historic= NUMBER )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // Cami.g:365:33: historic= NUMBER
                    {
                    historic=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1584); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1587); 
            // Cami.g:365:61: (stop_authorized= NUMBER )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // Cami.g:365:61: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1591); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1594); 
            // Cami.g:366:18: (ouput_formalism= CAMI_STRING )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==CAMI_STRING) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // Cami.g:366:18: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1601); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1604); 
            // Cami.g:366:42: (active= NUMBER )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==NUMBER) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // Cami.g:366:42: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1608); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add1611); 

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
    // Cami.g:369:1: service_menu_modification : enable_main_question ( question_state )* end_menu_transmission ;
    public final void service_menu_modification() throws RecognitionException {
        try {
            // Cami.g:370:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // Cami.g:371:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification1623);
            enable_main_question();
            _fsp--;

            // Cami.g:372:2: ( question_state )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==45) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // Cami.g:372:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification1626);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification1630);
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
    // Cami.g:376:1: enable_main_question : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final void enable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // Cami.g:377:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // Cami.g:378:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,68,FOLLOW_68_in_enable_main_question1642); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question1646); 
            match(input,12,FOLLOW_12_in_enable_main_question1648); 

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
    // Cami.g:381:1: disable_main_question : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final void disable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // Cami.g:382:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // Cami.g:383:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,69,FOLLOW_69_in_disable_main_question1660); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question1664); 
            match(input,12,FOLLOW_12_in_disable_main_question1666); 

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
    // Cami.g:386:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        try {
            // Cami.g:387:2: ( 'QQ(' NUMBER ')' )
            // Cami.g:388:2: 'QQ(' NUMBER ')'
            {
            match(input,70,FOLLOW_70_in_end_menu_transmission1678); 
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission1680); 
            match(input,12,FOLLOW_12_in_end_menu_transmission1682); 

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
    // Cami.g:391:1: help_question : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final void help_question() throws RecognitionException {
        Token question_name=null;
        Token help_message=null;

        try {
            // Cami.g:392:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // Cami.g:393:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,71,FOLLOW_71_in_help_question1694); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1698); 
            match(input,11,FOLLOW_11_in_help_question1700); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1704); 
            match(input,12,FOLLOW_12_in_help_question1706); 

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


 

    public static final BitSet FOLLOW_8_in_model_definition32 = new BitSet(new long[]{0x000000000037E400L});
    public static final BitSet FOLLOW_syntactic_in_model_definition37 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_aestetic_in_model_definition41 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_definition46 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic58 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic62 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_attribute_in_syntactic70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_node82 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node84 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node86 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_node88 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_node90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_box102 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box104 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box106 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box108 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box110 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_box112 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_box114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_arc126 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc128 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc130 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc132 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc134 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc136 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc138 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_arc140 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_arc142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_textual_attribute156 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute158 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute160 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute162 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute164 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute166 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_attribute168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_textual_attribute173 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute175 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute177 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute179 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute181 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute183 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute185 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_textual_attribute187 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute191 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_attribute193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_object_position228 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position232 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position234 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position238 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position240 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position244 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_object_position251 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position255 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position257 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position261 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position263 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position267 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_object_position274 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_object_position276 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position278 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position282 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position284 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position288 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position290 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position294 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position296 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position300 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position302 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position306 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_text_position320 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position324 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position330 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position332 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position336 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position338 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position342 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_text_position344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_intermediary_point357 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point359 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point361 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point363 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point365 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point367 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_intermediary_point369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_dialog_definition385 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition388 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition391 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_dialog_definition395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_dialog_creation407 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation409 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation411 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation413 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation415 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation417 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation419 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation422 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation424 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation426 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation428 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation430 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation432 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation437 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation439 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_dialog_creation441 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation443 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation445 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_dialog_creation448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_next_dialog460 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_next_dialog462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog464 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog466 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_next_dialog468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_display_dialog480 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_display_dialog482 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_display_dialog484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_hide_dialog497 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_hide_dialog499 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_hide_dialog501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_destroy_dialog514 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_destroy_dialog516 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_destroy_dialog518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_interactive_response530 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response532 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response534 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response536 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_interactive_response538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_trace_message576 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message578 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_trace_message580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_warning_message592 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message594 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_warning_message596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_special_message609 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message611 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message613 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message615 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_communication_in_open_communication640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_open_communication651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_check_version675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_check_version685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_communication702 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication704 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ack_open_connection719 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection721 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection723 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection725 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_normal739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_close_connection_panic757 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic761 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic763 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_close_connection_panic767 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_interlocutor_table786 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table790 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_38_in_interlocutor_table795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body_table808 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table812 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table814 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table818 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table820 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_body_table822 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table824 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table828 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception843 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_result_reception858 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_question_reply_in_result_reception861 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception866 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception870 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception874 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_result_in_result_reception878 = new BitSet(new long[]{0x0000640180000000L});
    public static final BitSet FOLLOW_42_in_result_reception885 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception887 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_question_reply901 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply905 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply907 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply911 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply913 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_question_reply915 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_reply917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_question_state930 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state934 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state936 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state940 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state942 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_state946 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state948 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state952 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_result967 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result971 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result973 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result977 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result979 = new BitSet(new long[]{0x007F40000001E400L});
    public static final BitSet FOLLOW_result_body_in_result982 = new BitSet(new long[]{0x007FC0000001E400L});
    public static final BitSet FOLLOW_47_in_result986 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_textual_result1062 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1064 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_attribute_change1083 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change1087 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1089 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1093 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1095 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1099 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_designation1118 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1122 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_outline1141 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1145 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_attribute_outline1164 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1168 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1170 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1174 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1176 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1180 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1183 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1187 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1208 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1210 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1212 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1214 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1221 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1223 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1225 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1227 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1229 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1231 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1238 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1240 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1242 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1244 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1246 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1248 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1250 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1252 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1259 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1261 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1263 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1265 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1267 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1269 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1276 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1278 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1280 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1282 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1284 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1286 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1288 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1290 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1292 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1294 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1313 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1317 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_object_deletion1325 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1329 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1331 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1335 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ack_open_session1353 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1355 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1356 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ack_open_session1359 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ack_open_session1362 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_close_current_session1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ack_suspend_current_session1394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ack_resume_suspend_current_session1406 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session1408 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_suspend_current_session1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model1428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ask_simple1440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ask_hierarchic1452 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_hierarchic1454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic1456 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_hierarchic1458 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic1460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_change_date1474 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_change_date1476 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_change_date1478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_service_menu_reception1492 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception1495 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception1498 = new BitSet(new long[]{0x0000000000000000L,0x000000000000000AL});
    public static final BitSet FOLLOW_65_in_service_menu_reception1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_menu_name1514 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name1518 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1520 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name1522 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1524 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name1526 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_question_add1540 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1544 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1546 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1550 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1552 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1559 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1562 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1566 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1569 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1576 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1579 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1584 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1587 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1591 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1594 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1601 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1604 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1608 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add1611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification1623 = new BitSet(new long[]{0x0000200000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification1626 = new BitSet(new long[]{0x0000200000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification1630 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_enable_main_question1642 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question1646 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question1648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_disable_main_question1660 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question1664 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question1666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_end_menu_transmission1678 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission1680 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_help_question1694 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1698 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question1700 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1704 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question1706 = new BitSet(new long[]{0x0000000000000002L});

}