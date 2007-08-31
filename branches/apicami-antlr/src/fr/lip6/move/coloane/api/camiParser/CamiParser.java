// $ANTLR 3.0.1 /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g 2007-08-31 14:20:44

package fr.lip6.move.coloane.api.camiParser;

import fr.lip6.move.coloane.api.session.states.authentication.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'DB()'", "'FB()'", "'CN('", "','", "')'", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'RI('", "'TR('", "'WN('", "'MO('", "'SC('", "'OC('", "'FC()'", "'KO('", "'TL()'", "'FL()'", "'VI('", "'3'", "'DR()'", "'FR('", "'RQ('", "'TQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'RO('", "'ME('", "'MT('", "'SU('", "'SI('", "'OS('", "'TD()'", "'FA()'", "'FS()'", "'SS()'", "'RS('", "'DF()'", "'DF(-2,'", "'MS('", "'DQ()'", "'FQ()'", "'CQ('", "'AQ('", "'VQ('", "'EQ('", "'QQ('", "'HQ('"
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
    public String getGrammarFileName() { return "/Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g"; }



    // $ANTLR start model_definition
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:21:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:22:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:23:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,8,FOLLOW_8_in_model_definition32); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:24:2: ( syntactic | aestetic )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==10||(LA1_0>=13 && LA1_0<=16)) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=17 && LA1_0<=20)) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("24:2: ( syntactic | aestetic )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:24:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition37);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:24:16: aestetic
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:28:1: syntactic : ( node | box | arc | textual_attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:29:2: ( node | box | arc | textual_attribute )
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:30:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic58);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:30:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic62);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:30:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic66);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:30:21: textual_attribute
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:33:1: node : 'CN(' CAMI_STRING ',' NUMBER ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:33:6: ( 'CN(' CAMI_STRING ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:34:2: 'CN(' CAMI_STRING ',' NUMBER ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:37:1: box : 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:37:5: ( 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:38:2: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:41:1: arc : 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:41:5: ( 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:42:2: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:45:1: textual_attribute : ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void textual_attribute() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:46:2: ( 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:47:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:48:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:52:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:53:2: ( object_position | text_position | intermediary_point )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 17:
            case 18:
                {
                alt4=1;
                }
                break;
            case 19:
                {
                alt4=2;
                }
                break;
            case 20:
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:54:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic206);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:54:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic210);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:54:36: intermediary_point
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );
    public final void object_position() throws RecognitionException {
        Token id=null;
        Token h_distance=null;
        Token v_distance=null;
        Token left=null;
        Token right=null;
        Token top=null;
        Token bottom=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:58:2: ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==NUMBER) ) {
                    int LA5_3 = input.LA(3);

                    if ( (LA5_3==11) ) {
                        int LA5_4 = input.LA(4);

                        if ( (LA5_4==NUMBER) ) {
                            int LA5_5 = input.LA(5);

                            if ( (LA5_5==11) ) {
                                int LA5_6 = input.LA(6);

                                if ( (LA5_6==NUMBER) ) {
                                    int LA5_7 = input.LA(7);

                                    if ( (LA5_7==11) ) {
                                        alt5=3;
                                    }
                                    else if ( (LA5_7==12) ) {
                                        alt5=1;
                                    }
                                    else {
                                        NoViableAltException nvae =
                                            new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 7, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    NoViableAltException nvae =
                                        new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 6, input);

                                    throw nvae;
                                }
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 5, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA5_0==18) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("57:1: object_position : ( 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')' | 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')' );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:59:4: 'PO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:60:4: 'pO(' id= NUMBER ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:61:4: 'PO(' NUMBER ',' id= NUMBER ',' left= NUMBER ',' right= NUMBER ',' top= NUMBER ',' bottom= NUMBER ')'
                    {
                    match(input,17,FOLLOW_17_in_object_position274); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_position276); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:64:1: text_position : 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' ;
    public final void text_position() throws RecognitionException {
        Token id=null;
        Token name_attr=null;
        Token h_distance=null;
        Token v_distance=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:65:2: ( 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:66:2: 'PT(' id= NUMBER ',' name_attr= CAMI_STRING ',' h_distance= NUMBER ',' v_distance= NUMBER ')'
            {
            match(input,19,FOLLOW_19_in_text_position320); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:69:1: intermediary_point : 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:70:2: ( 'PI(' NUMBER ',' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:71:2: 'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
            {
            match(input,20,FOLLOW_20_in_intermediary_point357); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:78:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:79:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:80:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,21,FOLLOW_21_in_dialog_definition385); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition388);
            dialog_creation();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:82:2: ( next_dialog )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==24) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:82:2: next_dialog
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

            match(input,22,FOLLOW_22_in_dialog_definition395); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:86:1: dialog_creation : 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:87:2: ( 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:88:2: 'CE(' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' NUMBER ',' NUMBER ',' ( CAMI_STRING )? ')'
            {
            match(input,23,FOLLOW_23_in_dialog_creation407); 
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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:89:25: ( CAMI_STRING )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==CAMI_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:89:25: CAMI_STRING
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:92:1: next_dialog : 'DS(' NUMBER ',' CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:93:2: ( 'DS(' NUMBER ',' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:94:2: 'DS(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,24,FOLLOW_24_in_next_dialog460); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:97:1: display_dialog : 'AD(' NUMBER ')' ;
    public final void display_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:98:2: ( 'AD(' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:99:2: 'AD(' NUMBER ')'
            {
            match(input,25,FOLLOW_25_in_display_dialog480); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:102:1: hide_dialog : 'HD(' NUMBER ')' ;
    public final void hide_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:103:2: ( 'HD(' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:104:2: 'HD(' NUMBER ')'
            {
            match(input,26,FOLLOW_26_in_hide_dialog497); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:107:1: destroy_dialog : 'DG(' NUMBER ')' ;
    public final void destroy_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:108:2: ( 'DG(' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:109:2: 'DG(' NUMBER ')'
            {
            match(input,27,FOLLOW_27_in_destroy_dialog514); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:112:1: interactive_response : 'RI(' NUMBER ',' NUMBER ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:113:2: ( 'RI(' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:114:2: 'RI(' NUMBER ',' NUMBER ')'
            {
            match(input,28,FOLLOW_28_in_interactive_response530); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:124:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:125:2: ( trace_message | warning_message | special_message )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt8=1;
                }
                break;
            case 30:
                {
                alt8=2;
                }
                break;
            case 31:
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:126:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user556);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:126:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user560);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:126:36: special_message
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:129:1: trace_message : 'TR(' CAMI_STRING ')' ;
    public final void trace_message() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:130:2: ( 'TR(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:131:2: 'TR(' CAMI_STRING ')'
            {
            match(input,29,FOLLOW_29_in_trace_message576); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:134:1: warning_message : 'WN(' CAMI_STRING ')' ;
    public final void warning_message() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:135:2: ( 'WN(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:136:2: 'WN(' CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_warning_message592); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:139:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
    public final void special_message() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:140:2: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:141:2: 'MO(' NUMBER ',' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_special_message609); 
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:147:1: open_communication returns [AuthenticationAck message] : ( ack_open_communication | close_connection_panic );
    public final AuthenticationAck open_communication() throws RecognitionException {
        AuthenticationAck message = null;

        String close_connection_panic1 = null;


        System.err.println("Before open_communication");
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:150:2: ( ack_open_communication | close_connection_panic )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==32) ) {
                alt9=1;
            }
            else if ( (LA9_0==35) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("147:1: open_communication returns [AuthenticationAck message] : ( ack_open_communication | close_connection_panic );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:151:4: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_open_communication645);
                    ack_open_communication();
                    _fsp--;


                    	  	message = new AuthenticationAck();
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:155:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_open_communication656);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:162:1: check_version returns [AuthenticationAck message] : ( ack_open_connection | close_connection_panic );
    public final AuthenticationAck check_version() throws RecognitionException {
        AuthenticationAck message = null;

        String close_connection_panic2 = null;


        System.err.println("Before check_version");
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:165:2: ( ack_open_connection | close_connection_panic )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==33) ) {
                alt10=1;
            }
            else if ( (LA10_0==35) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("162:1: check_version returns [AuthenticationAck message] : ( ack_open_connection | close_connection_panic );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:166:4: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_check_version684);
                    ack_open_connection();
                    _fsp--;


                    	  	System.err.println("ack_open_communication recognized");
                    	  	message = new AuthenticationAck();  
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:171:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_check_version694);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:178:1: ack_open_communication : 'SC(' CAMI_STRING ')' ;
    public final void ack_open_communication() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:179:2: ( 'SC(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:180:2: 'SC(' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_ack_open_communication711); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication713); 
            match(input,12,FOLLOW_12_in_ack_open_communication715); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:183:1: ack_open_connection : 'OC(' NUMBER ',' NUMBER ')' ;
    public final void ack_open_connection() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:184:2: ( 'OC(' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:185:2: 'OC(' NUMBER ',' NUMBER ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_connection729); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection731); 
            match(input,11,FOLLOW_11_in_ack_open_connection733); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection735); 
            match(input,12,FOLLOW_12_in_ack_open_connection737); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:188:1: close_connection_normal : 'FC()' ;
    public final void close_connection_normal() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:189:2: ( 'FC()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:190:2: 'FC()'
            {
            match(input,34,FOLLOW_34_in_close_connection_normal749); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:193:1: close_connection_panic returns [String s] : 'KO(' NUMBER ',' mess= CAMI_STRING ',' level= NUMBER ')' ;
    public final String close_connection_panic() throws RecognitionException {
        String s = null;

        Token mess=null;
        Token level=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:195:2: ( 'KO(' NUMBER ',' mess= CAMI_STRING ',' level= NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:196:2: 'KO(' NUMBER ',' mess= CAMI_STRING ',' level= NUMBER ')'
            {
            match(input,35,FOLLOW_35_in_close_connection_panic767); 
            match(input,NUMBER,FOLLOW_NUMBER_in_close_connection_panic769); 
            match(input,11,FOLLOW_11_in_close_connection_panic771); 
            mess=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic775); 
            match(input,11,FOLLOW_11_in_close_connection_panic777); 
            level=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_close_connection_panic781); 
            match(input,12,FOLLOW_12_in_close_connection_panic783); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:204:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:205:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:206:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,36,FOLLOW_36_in_interlocutor_table800); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:207:2: ( body_table )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==38) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:207:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table804);
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

            match(input,37,FOLLOW_37_in_interlocutor_table809); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:211:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        Token new_model=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:212:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:213:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= NUMBER ')'
            {
            match(input,38,FOLLOW_38_in_body_table822); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table826); 
            match(input,11,FOLLOW_11_in_body_table828); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table832); 
            match(input,11,FOLLOW_11_in_body_table834); 
            match(input,39,FOLLOW_39_in_body_table836); 
            match(input,11,FOLLOW_11_in_body_table838); 
            new_model=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_body_table842); 
            match(input,12,FOLLOW_12_in_body_table844); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:218:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:219:2: ( question_state ask_hierarchic )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:220:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception857);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception860);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:224:1: result_reception : 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')' ;
    public final void result_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:225:2: ( 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:226:2: 'DR()' question_reply ( question_state | special_message | warning_message | result )* 'FR(' NUMBER ')'
            {
            match(input,40,FOLLOW_40_in_result_reception872); 
            pushFollow(FOLLOW_question_reply_in_result_reception875);
            question_reply();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:228:2: ( question_state | special_message | warning_message | result )*
            loop12:
            do {
                int alt12=5;
                switch ( input.LA(1) ) {
                case 43:
                    {
                    alt12=1;
                    }
                    break;
                case 31:
                    {
                    alt12=2;
                    }
                    break;
                case 30:
                    {
                    alt12=3;
                    }
                    break;
                case 44:
                    {
                    alt12=4;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:228:4: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_result_reception880);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;
            	case 2 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:228:21: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_result_reception884);
            	    special_message();
            	    _fsp--;


            	    }
            	    break;
            	case 3 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:228:39: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_result_reception888);
            	    warning_message();
            	    _fsp--;


            	    }
            	    break;
            	case 4 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:228:57: result
            	    {
            	    pushFollow(FOLLOW_result_in_result_reception892);
            	    result();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_result_reception899); 
            match(input,NUMBER,FOLLOW_NUMBER_in_result_reception901); 
            match(input,12,FOLLOW_12_in_result_reception903); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:232:1: question_reply : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' NUMBER ')' ;
    public final void question_reply() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:233:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:234:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' NUMBER ')'
            {
            match(input,42,FOLLOW_42_in_question_reply915); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply919); 
            match(input,11,FOLLOW_11_in_question_reply921); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_reply925); 
            match(input,11,FOLLOW_11_in_question_reply927); 
            match(input,NUMBER,FOLLOW_NUMBER_in_question_reply929); 
            match(input,12,FOLLOW_12_in_question_reply931); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:237:1: question_state : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' ;
    public final void question_state() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;
        Token state=null;
        Token mess=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:238:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:239:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= NUMBER ',' (mess= CAMI_STRING )? ')'
            {
            match(input,43,FOLLOW_43_in_question_state944); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state948); 
            match(input,11,FOLLOW_11_in_question_state950); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state954); 
            match(input,11,FOLLOW_11_in_question_state956); 
            state=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_question_state960); 
            match(input,11,FOLLOW_11_in_question_state962); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:239:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:239:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state966); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state969); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:242:1: result : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' ;
    public final void result() throws RecognitionException {
        Token ensemble_name=null;
        Token ensemble_type=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:242:8: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:243:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' ( result_body )+ 'FE()'
            {
            match(input,44,FOLLOW_44_in_result981); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result985); 
            match(input,11,FOLLOW_11_in_result987); 
            ensemble_type=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_result991); 
            match(input,12,FOLLOW_12_in_result993); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:244:2: ( result_body )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==10||(LA14_0>=13 && LA14_0<=16)||LA14_0==44||(LA14_0>=46 && LA14_0<=52)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:244:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result996);
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

            match(input,45,FOLLOW_45_in_result1000); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:248:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final void result_body() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:249:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt15=8;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt15=1;
                }
                break;
            case 46:
                {
                alt15=2;
                }
                break;
            case 47:
                {
                alt15=3;
                }
                break;
            case 48:
                {
                alt15=4;
                }
                break;
            case 49:
                {
                alt15=5;
                }
                break;
            case 50:
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
            case 51:
            case 52:
                {
                alt15=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("248:1: result_body : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:250:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1017);
                    result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:251:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1023);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:252:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1029);
                    attribute_change();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:253:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1035);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:254:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1041);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:255:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1047);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:256:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1053);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1059);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:260:2: textual_result : 'RT(' CAMI_STRING ')' ;
    public final void textual_result() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:261:3: ( 'RT(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:262:3: 'RT(' CAMI_STRING ')'
            {
            match(input,46,FOLLOW_46_in_textual_result1076); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1078); 
            match(input,12,FOLLOW_12_in_textual_result1080); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:265:2: attribute_change : 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final void attribute_change() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token new_value=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:266:3: ( 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:267:3: 'WE(' id= NUMBER ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,47,FOLLOW_47_in_attribute_change1097); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_change1101); 
            match(input,11,FOLLOW_11_in_attribute_change1103); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1107); 
            match(input,11,FOLLOW_11_in_attribute_change1109); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1113); 
            match(input,12,FOLLOW_12_in_attribute_change1115); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:270:2: object_designation : 'RO(' id= NUMBER ')' ;
    public final void object_designation() throws RecognitionException {
        Token id=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:271:3: ( 'RO(' id= NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:272:3: 'RO(' id= NUMBER ')'
            {
            match(input,48,FOLLOW_48_in_object_designation1132); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1136); 
            match(input,12,FOLLOW_12_in_object_designation1138); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:2: object_outline : 'ME(' id= NUMBER ')' ;
    public final void object_outline() throws RecognitionException {
        Token id=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:276:3: ( 'ME(' id= NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:277:3: 'ME(' id= NUMBER ')'
            {
            match(input,49,FOLLOW_49_in_object_outline1155); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1159); 
            match(input,12,FOLLOW_12_in_object_outline1161); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:280:2: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
    public final void attribute_outline() throws RecognitionException {
        Token id=null;
        Token attr_name=null;
        Token begin=null;
        Token end=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:281:3: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:282:3: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
            {
            match(input,50,FOLLOW_50_in_attribute_outline1178); 
            id=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1182); 
            match(input,11,FOLLOW_11_in_attribute_outline1184); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1188); 
            match(input,11,FOLLOW_11_in_attribute_outline1190); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:282:54: (begin= NUMBER )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==NUMBER) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:282:54: begin= NUMBER
                    {
                    begin=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1194); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1197); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:282:70: (end= NUMBER )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NUMBER) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:282:70: end= NUMBER
                    {
                    end=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1201); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1204); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:285:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
    public final void object_creation() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:286:3: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
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
                    new NoViableAltException("285:2: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:287:4: 'CN(' CAMI_STRING ',' NUMBER ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1222); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1224); 
                    match(input,11,FOLLOW_11_in_object_creation1226); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1228); 
                    match(input,12,FOLLOW_12_in_object_creation1230); 

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:288:4: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1235); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1237); 
                    match(input,11,FOLLOW_11_in_object_creation1239); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1241); 
                    match(input,11,FOLLOW_11_in_object_creation1243); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1245); 
                    match(input,12,FOLLOW_12_in_object_creation1247); 

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:289:4: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1252); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1254); 
                    match(input,11,FOLLOW_11_in_object_creation1256); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1258); 
                    match(input,11,FOLLOW_11_in_object_creation1260); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1262); 
                    match(input,11,FOLLOW_11_in_object_creation1264); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1266); 
                    match(input,12,FOLLOW_12_in_object_creation1268); 

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:290:4: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1273); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1275); 
                    match(input,11,FOLLOW_11_in_object_creation1277); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1279); 
                    match(input,11,FOLLOW_11_in_object_creation1281); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1283); 
                    match(input,12,FOLLOW_12_in_object_creation1285); 

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:291:4: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1290); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1292); 
                    match(input,11,FOLLOW_11_in_object_creation1294); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1296); 
                    match(input,11,FOLLOW_11_in_object_creation1298); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1300); 
                    match(input,11,FOLLOW_11_in_object_creation1302); 
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1304); 
                    match(input,11,FOLLOW_11_in_object_creation1306); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1308); 
                    match(input,12,FOLLOW_12_in_object_creation1310); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:294:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
    public final void object_deletion() throws RecognitionException {
        Token id=null;
        Token page_id=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:295:2: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==51) ) {
                alt19=1;
            }
            else if ( (LA19_0==52) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("294:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:296:5: 'SU(' id= NUMBER ')'
                    {
                    match(input,51,FOLLOW_51_in_object_deletion1327); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1331); 
                    match(input,12,FOLLOW_12_in_object_deletion1333); 

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:297:5: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
                    {
                    match(input,52,FOLLOW_52_in_object_deletion1339); 
                    page_id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1343); 
                    match(input,11,FOLLOW_11_in_object_deletion1345); 
                    id=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1349); 
                    match(input,12,FOLLOW_12_in_object_deletion1351); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:302:1: ack_open_session : 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final void ack_open_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:303:2: ( 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:304:2: 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,53,FOLLOW_53_in_ack_open_session1367); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1369); 
            match(input,12,FOLLOW_12_in_ack_open_session1370); 
            match(input,54,FOLLOW_54_in_ack_open_session1373); 
            match(input,55,FOLLOW_55_in_ack_open_session1376); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1379);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:310:1: ack_close_current_session : 'FS()' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:311:2: ( 'FS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:312:2: 'FS()'
            {
            match(input,56,FOLLOW_56_in_ack_close_current_session1393); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:315:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:316:2: ( 'SS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:317:2: 'SS()'
            {
            match(input,57,FOLLOW_57_in_ack_suspend_current_session1408); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:320:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
    public final void ack_resume_suspend_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:321:2: ( 'RS(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:322:2: 'RS(' CAMI_STRING ')'
            {
            match(input,58,FOLLOW_58_in_ack_resume_suspend_current_session1420); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session1422); 
            match(input,12,FOLLOW_12_in_ack_resume_suspend_current_session1424); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:327:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:328:2: ( ask_simple | ask_hierarchic )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==59) ) {
                alt20=1;
            }
            else if ( (LA20_0==60) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("327:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:329:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model1438);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:329:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model1442);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:332:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:333:2: ( 'DF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:334:2: 'DF()'
            {
            match(input,59,FOLLOW_59_in_ask_simple1454); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:1: ask_hierarchic : 'DF(-2,' NUMBER ',' NUMBER ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:338:2: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:339:2: 'DF(-2,' NUMBER ',' NUMBER ')'
            {
            match(input,60,FOLLOW_60_in_ask_hierarchic1466); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_hierarchic1468); 
            match(input,11,FOLLOW_11_in_ask_hierarchic1470); 
            match(input,NUMBER,FOLLOW_NUMBER_in_ask_hierarchic1472); 
            match(input,12,FOLLOW_12_in_ask_hierarchic1474); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:344:1: change_date : 'MS(' NUMBER ')' ;
    public final void change_date() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:345:2: ( 'MS(' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:346:2: 'MS(' NUMBER ')'
            {
            match(input,61,FOLLOW_61_in_change_date1488); 
            match(input,NUMBER,FOLLOW_NUMBER_in_change_date1490); 
            match(input,12,FOLLOW_12_in_change_date1492); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:351:1: service_menu_reception : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final void service_menu_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:352:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:353:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,62,FOLLOW_62_in_service_menu_reception1506); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception1509);
            menu_name();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:2: ( question_add )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==65) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception1512);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            match(input,63,FOLLOW_63_in_service_menu_reception1516); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:359:1: menu_name : 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:360:2: ( 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:361:2: 'CQ(' name= CAMI_STRING ',' NUMBER ',' NUMBER ')'
            {
            match(input,64,FOLLOW_64_in_menu_name1528); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name1532); 
            match(input,11,FOLLOW_11_in_menu_name1534); 
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name1536); 
            match(input,11,FOLLOW_11_in_menu_name1538); 
            match(input,NUMBER,FOLLOW_NUMBER_in_menu_name1540); 
            match(input,12,FOLLOW_12_in_menu_name1542); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:364:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:365:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:366:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (historic= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
            {
            match(input,65,FOLLOW_65_in_question_add1554); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1558); 
            match(input,11,FOLLOW_11_in_question_add1560); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1564); 
            match(input,11,FOLLOW_11_in_question_add1566); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:367:16: (question_type= NUMBER )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==NUMBER) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:367:16: question_type= NUMBER
                    {
                    question_type=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1573); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1576); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:367:46: (question_behavior= NUMBER )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NUMBER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:367:46: question_behavior= NUMBER
                    {
                    question_behavior=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1580); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1583); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:11: (set_item= NUMBER )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:11: set_item= NUMBER
                    {
                    set_item=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1590); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1593); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:33: (historic= NUMBER )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:33: historic= NUMBER
                    {
                    historic=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1598); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1601); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:61: (stop_authorized= NUMBER )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:61: stop_authorized= NUMBER
                    {
                    stop_authorized=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1605); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1608); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:369:18: (ouput_formalism= CAMI_STRING )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==CAMI_STRING) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:369:18: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1615); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1618); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:369:42: (active= NUMBER )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==NUMBER) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:369:42: active= NUMBER
                    {
                    active=(Token)input.LT(1);
                    match(input,NUMBER,FOLLOW_NUMBER_in_question_add1622); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add1625); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:372:1: service_menu_modification : enable_main_question ( question_state )* end_menu_transmission ;
    public final void service_menu_modification() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:373:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:374:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification1637);
            enable_main_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:375:2: ( question_state )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==43) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:375:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification1640);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification1644);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:379:1: enable_main_question : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final void enable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:380:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:381:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,66,FOLLOW_66_in_enable_main_question1656); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question1660); 
            match(input,12,FOLLOW_12_in_enable_main_question1662); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:384:1: disable_main_question : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final void disable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:385:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:386:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,67,FOLLOW_67_in_disable_main_question1674); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question1678); 
            match(input,12,FOLLOW_12_in_disable_main_question1680); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:389:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:390:2: ( 'QQ(' NUMBER ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:391:2: 'QQ(' NUMBER ')'
            {
            match(input,68,FOLLOW_68_in_end_menu_transmission1692); 
            match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission1694); 
            match(input,12,FOLLOW_12_in_end_menu_transmission1696); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:394:1: help_question : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final void help_question() throws RecognitionException {
        Token question_name=null;
        Token help_message=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:395:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:396:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,69,FOLLOW_69_in_help_question1708); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1712); 
            match(input,11,FOLLOW_11_in_help_question1714); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1718); 
            match(input,12,FOLLOW_12_in_help_question1720); 

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


 

    public static final BitSet FOLLOW_8_in_model_definition32 = new BitSet(new long[]{0x00000000001FE400L});
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
    public static final BitSet FOLLOW_17_in_object_position274 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_position276 = new BitSet(new long[]{0x0000000000000800L});
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
    public static final BitSet FOLLOW_19_in_text_position320 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position324 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position330 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position332 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position336 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position338 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_text_position342 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_text_position344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_intermediary_point357 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point359 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point361 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point363 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point365 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_intermediary_point367 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_intermediary_point369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_dialog_definition385 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition388 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition391 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_22_in_dialog_definition395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_dialog_creation407 = new BitSet(new long[]{0x0000000000000020L});
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
    public static final BitSet FOLLOW_24_in_next_dialog460 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_next_dialog462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog464 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog466 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_next_dialog468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_display_dialog480 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_display_dialog482 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_display_dialog484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_hide_dialog497 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_hide_dialog499 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_hide_dialog501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_destroy_dialog514 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_destroy_dialog516 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_destroy_dialog518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_interactive_response530 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response532 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response534 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_interactive_response536 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_interactive_response538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_trace_message576 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message578 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_trace_message580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_warning_message592 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message594 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_warning_message596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_special_message609 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_special_message611 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message613 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message615 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_communication_in_open_communication645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_open_communication656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_check_version684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_check_version694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ack_open_communication711 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication713 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_connection729 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection731 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection733 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ack_open_connection735 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_close_connection_normal749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_panic767 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_close_connection_panic769 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic771 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic775 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic777 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_close_connection_panic781 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_interlocutor_table800 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table804 = new BitSet(new long[]{0x0000006000000000L});
    public static final BitSet FOLLOW_37_in_interlocutor_table809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_body_table822 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table826 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table828 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table832 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table834 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_body_table836 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table838 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_body_table842 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception857 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_result_reception872 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_question_reply_in_result_reception875 = new BitSet(new long[]{0x00001A00C0000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception880 = new BitSet(new long[]{0x00001A00C0000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception884 = new BitSet(new long[]{0x00001A00C0000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception888 = new BitSet(new long[]{0x00001A00C0000000L});
    public static final BitSet FOLLOW_result_in_result_reception892 = new BitSet(new long[]{0x00001A00C0000000L});
    public static final BitSet FOLLOW_41_in_result_reception899 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result_reception901 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_question_reply915 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply919 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply921 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_reply925 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_reply927 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_reply929 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_reply931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_question_state944 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state948 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state950 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state954 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state956 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_question_state960 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state962 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state966 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_result981 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result985 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result987 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_result991 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result993 = new BitSet(new long[]{0x001FD0000001E400L});
    public static final BitSet FOLLOW_result_body_in_result996 = new BitSet(new long[]{0x001FF0000001E400L});
    public static final BitSet FOLLOW_45_in_result1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_textual_result1076 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1078 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result1080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_attribute_change1097 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_change1101 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1103 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1107 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1109 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1113 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_object_designation1132 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_designation1136 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_outline1155 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_outline1159 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_attribute_outline1178 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1182 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1184 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1188 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1190 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1194 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1197 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_NUMBER_in_attribute_outline1201 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1222 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1224 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1226 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1228 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1235 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1237 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1239 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1241 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1243 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1245 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1252 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1254 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1256 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1258 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1260 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1262 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1264 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1266 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1273 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1275 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1279 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1281 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1283 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1290 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1292 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1294 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1296 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1298 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1300 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1302 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_creation1304 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1306 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1308 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_deletion1327 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1331 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_deletion1339 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1343 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1345 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_object_deletion1349 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ack_open_session1367 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1369 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1370 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ack_open_session1373 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ack_open_session1376 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ack_close_current_session1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ack_suspend_current_session1408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_resume_suspend_current_session1420 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session1422 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_suspend_current_session1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model1438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ask_simple1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ask_hierarchic1466 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_hierarchic1468 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic1470 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_ask_hierarchic1472 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_change_date1488 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_change_date1490 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_change_date1492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_service_menu_reception1506 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception1509 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception1512 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_service_menu_reception1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_menu_name1528 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name1532 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1534 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name1536 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1538 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_menu_name1540 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_question_add1554 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1558 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1560 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1564 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1566 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1573 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1576 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1580 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1583 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1590 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1593 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1598 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1601 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1605 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1608 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1615 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1618 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_NUMBER_in_question_add1622 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification1637 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification1640 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification1644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_enable_main_question1656 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question1660 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_disable_main_question1674 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question1678 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_end_menu_transmission1692 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission1694 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission1696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_help_question1708 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1712 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question1714 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1718 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question1720 = new BitSet(new long[]{0x0000000000000002L});

}