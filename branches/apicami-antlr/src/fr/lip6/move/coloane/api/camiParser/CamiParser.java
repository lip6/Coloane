// $ANTLR 3.0.1 /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g 2007-09-02 23:56:46

package fr.lip6.move.coloane.api.camiParser;

import fr.lip6.move.coloane.api.camiCommands.*;
import fr.lip6.move.coloane.api.camiCommands.results.*;
import fr.lip6.move.coloane.api.session.states.*;
import fr.lip6.move.coloane.api.session.states.authentication.*;
import fr.lip6.move.coloane.api.camiCommands.results.*;
import fr.lip6.move.coloane.api.camiCommands.SpecialMessages.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'DB()'", "'FB()'", "'CN('", "','", "')'", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('", "'TR('", "'WN('", "'MO('", "'SC('", "'OC('", "'FC()'", "'KO(1,'", "'TL()'", "'FL()'", "'VI('", "'3'", "'DR()'", "'FR('", "'RQ('", "'TQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'RO('", "'ME('", "'MT('", "'SU('", "'SI('", "'OS('", "'TD()'", "'FA()'", "'FS()'", "'SS()'", "'RS('", "'DF()'", "'DF(-2,'", "'MS('", "'DQ()'", "'FQ()'", "'CQ('", "'AQ('", "'VQ('", "'EQ('", "'QQ('", "'HQ('"
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:26:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:27:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:28:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,8,FOLLOW_8_in_model_definition35); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:29:2: ( syntactic | aestetic )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==10||(LA1_0>=13 && LA1_0<=16)) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=17 && LA1_0<=21)) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("29:2: ( syntactic | aestetic )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:29:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition40);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:29:16: aestetic
                    {
                    pushFollow(FOLLOW_aestetic_in_model_definition44);
                    aestetic();
                    _fsp--;


                    }
                    break;

            }

            match(input,9,FOLLOW_9_in_model_definition49); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:33:1: syntactic : ( node | box | arc | textual_attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:34:2: ( node | box | arc | textual_attribute )
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
                    new NoViableAltException("33:1: syntactic : ( node | box | arc | textual_attribute );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:35:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic61);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:35:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic65);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:35:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic69);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:35:21: textual_attribute
                    {
                    pushFollow(FOLLOW_textual_attribute_in_syntactic73);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:38:1: node : 'CN(' CAMI_STRING ',' number ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:38:6: ( 'CN(' CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:39:2: 'CN(' CAMI_STRING ',' number ')'
            {
            match(input,10,FOLLOW_10_in_node85); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_node87); 
            match(input,11,FOLLOW_11_in_node89); 
            pushFollow(FOLLOW_number_in_node91);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_node93); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:42:1: box : 'CB(' CAMI_STRING ',' number ',' number ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:42:5: ( 'CB(' CAMI_STRING ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:43:2: 'CB(' CAMI_STRING ',' number ',' number ')'
            {
            match(input,13,FOLLOW_13_in_box105); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_box107); 
            match(input,11,FOLLOW_11_in_box109); 
            pushFollow(FOLLOW_number_in_box111);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_box113); 
            pushFollow(FOLLOW_number_in_box115);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_box117); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:46:1: arc : 'CA(' CAMI_STRING ',' number ',' number ',' number ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:46:5: ( 'CA(' CAMI_STRING ',' number ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:47:2: 'CA(' CAMI_STRING ',' number ',' number ',' number ')'
            {
            match(input,14,FOLLOW_14_in_arc129); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_arc131); 
            match(input,11,FOLLOW_11_in_arc133); 
            pushFollow(FOLLOW_number_in_arc135);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_arc137); 
            pushFollow(FOLLOW_number_in_arc139);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_arc141); 
            pushFollow(FOLLOW_number_in_arc143);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_arc145); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:50:1: textual_attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );
    public final void textual_attribute() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:51:2: ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' )
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
                    new NoViableAltException("50:1: textual_attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:52:4: 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_textual_attribute159); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute161); 
                    match(input,11,FOLLOW_11_in_textual_attribute163); 
                    pushFollow(FOLLOW_number_in_textual_attribute165);
                    number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_textual_attribute167); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute169); 
                    match(input,12,FOLLOW_12_in_textual_attribute171); 

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:53:4: 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_textual_attribute176); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute178); 
                    match(input,11,FOLLOW_11_in_textual_attribute180); 
                    pushFollow(FOLLOW_number_in_textual_attribute182);
                    number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_textual_attribute184); 
                    pushFollow(FOLLOW_number_in_textual_attribute186);
                    number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_textual_attribute188); 
                    pushFollow(FOLLOW_number_in_textual_attribute190);
                    number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_textual_attribute192); 
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_attribute194); 
                    match(input,12,FOLLOW_12_in_textual_attribute196); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:57:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:58:2: ( object_position | text_position | intermediary_point )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 17:
            case 18:
            case 19:
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
                    new NoViableAltException("57:1: aestetic : ( object_position | text_position | intermediary_point );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:59:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic209);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:59:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic213);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:59:36: intermediary_point
                    {
                    pushFollow(FOLLOW_intermediary_point_in_aestetic217);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:62:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );
    public final void object_position() throws RecognitionException {
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;

        int left = 0;

        int right = 0;

        int top = 0;

        int bottom = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:63:2: ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt5=1;
                }
                break;
            case 18:
                {
                alt5=2;
                }
                break;
            case 19:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("62:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:64:4: 'PO(' id= number ',' h_distance= number ',' v_distance= number ')'
                    {
                    match(input,17,FOLLOW_17_in_object_position231); 
                    pushFollow(FOLLOW_number_in_object_position235);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position237); 
                    pushFollow(FOLLOW_number_in_object_position241);
                    h_distance=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position243); 
                    pushFollow(FOLLOW_number_in_object_position247);
                    v_distance=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_position249); 

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:65:4: 'pO(' id= number ',' h_distance= number ',' v_distance= number ')'
                    {
                    match(input,18,FOLLOW_18_in_object_position254); 
                    pushFollow(FOLLOW_number_in_object_position258);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position260); 
                    pushFollow(FOLLOW_number_in_object_position264);
                    h_distance=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position266); 
                    pushFollow(FOLLOW_number_in_object_position270);
                    v_distance=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_position272); 

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:66:4: 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')'
                    {
                    match(input,19,FOLLOW_19_in_object_position277); 
                    pushFollow(FOLLOW_number_in_object_position281);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position283); 
                    pushFollow(FOLLOW_number_in_object_position287);
                    left=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position289); 
                    pushFollow(FOLLOW_number_in_object_position293);
                    right=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position295); 
                    pushFollow(FOLLOW_number_in_object_position299);
                    top=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_position301); 
                    pushFollow(FOLLOW_number_in_object_position305);
                    bottom=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_position306); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:69:1: text_position : 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' ;
    public final void text_position() throws RecognitionException {
        Token name_attr=null;
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:70:2: ( 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:71:2: 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')'
            {
            match(input,20,FOLLOW_20_in_text_position319); 
            pushFollow(FOLLOW_number_in_text_position323);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_text_position325); 
            name_attr=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_text_position329); 
            match(input,11,FOLLOW_11_in_text_position331); 
            pushFollow(FOLLOW_number_in_text_position335);
            h_distance=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_text_position337); 
            pushFollow(FOLLOW_number_in_text_position341);
            v_distance=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_text_position343); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:74:1: intermediary_point : 'PI(' number ',' number ',' number ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:75:2: ( 'PI(' number ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:76:2: 'PI(' number ',' number ',' number ')'
            {
            match(input,21,FOLLOW_21_in_intermediary_point356); 
            pushFollow(FOLLOW_number_in_intermediary_point358);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_intermediary_point360); 
            pushFollow(FOLLOW_number_in_intermediary_point362);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_intermediary_point364); 
            pushFollow(FOLLOW_number_in_intermediary_point366);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_intermediary_point368); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:83:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:84:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:85:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,22,FOLLOW_22_in_dialog_definition384); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition387);
            dialog_creation();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:87:2: ( next_dialog )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:87:2: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition390);
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

            match(input,23,FOLLOW_23_in_dialog_definition394); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:91:1: dialog_creation : 'CE(' number ',' number ',' number ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' number ',' number ',' ( CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:92:2: ( 'CE(' number ',' number ',' number ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' number ',' number ',' ( CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:93:2: 'CE(' number ',' number ',' number ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' number ',' number ',' ( CAMI_STRING )? ')'
            {
            match(input,24,FOLLOW_24_in_dialog_creation406); 
            pushFollow(FOLLOW_number_in_dialog_creation408);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation410); 
            pushFollow(FOLLOW_number_in_dialog_creation412);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation414); 
            pushFollow(FOLLOW_number_in_dialog_creation416);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation418); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation421); 
            match(input,11,FOLLOW_11_in_dialog_creation423); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation425); 
            match(input,11,FOLLOW_11_in_dialog_creation427); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation429); 
            match(input,11,FOLLOW_11_in_dialog_creation431); 
            pushFollow(FOLLOW_number_in_dialog_creation436);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation438); 
            pushFollow(FOLLOW_number_in_dialog_creation440);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation442); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:94:25: ( CAMI_STRING )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==CAMI_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:94:25: CAMI_STRING
                    {
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation444); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_dialog_creation447); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:97:1: next_dialog : 'DS(' number ',' CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:98:2: ( 'DS(' number ',' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:99:2: 'DS(' number ',' CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_next_dialog459); 
            pushFollow(FOLLOW_number_in_next_dialog461);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_next_dialog463); 
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog465); 
            match(input,12,FOLLOW_12_in_next_dialog467); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:102:1: display_dialog : 'AD(' number ')' ;
    public final void display_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:103:2: ( 'AD(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:104:2: 'AD(' number ')'
            {
            match(input,26,FOLLOW_26_in_display_dialog479); 
            pushFollow(FOLLOW_number_in_display_dialog481);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_display_dialog483); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:107:1: hide_dialog : 'HD(' number ')' ;
    public final void hide_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:108:2: ( 'HD(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:109:2: 'HD(' number ')'
            {
            match(input,27,FOLLOW_27_in_hide_dialog496); 
            pushFollow(FOLLOW_number_in_hide_dialog498);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_hide_dialog500); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:112:1: destroy_dialog : 'DG(' number ')' ;
    public final void destroy_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:113:2: ( 'DG(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:114:2: 'DG(' number ')'
            {
            match(input,28,FOLLOW_28_in_destroy_dialog513); 
            pushFollow(FOLLOW_number_in_destroy_dialog515);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_destroy_dialog517); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:117:1: interactive_response : 'MI(' number ',' number ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:118:2: ( 'MI(' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:119:2: 'MI(' number ',' number ')'
            {
            match(input,29,FOLLOW_29_in_interactive_response529); 
            pushFollow(FOLLOW_number_in_interactive_response531);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_interactive_response533); 
            pushFollow(FOLLOW_number_in_interactive_response535);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_interactive_response537); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:129:1: message_to_user : ( trace_message | warning_message | special_message );
    public final void message_to_user() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:130:2: ( trace_message | warning_message | special_message )
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
                    new NoViableAltException("129:1: message_to_user : ( trace_message | warning_message | special_message );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:131:2: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user555);
                    trace_message();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:131:18: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user559);
                    warning_message();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:131:36: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user563);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:134:1: trace_message returns [TraceMessage camiContent] : 'TR(' CAMI_STRING ')' ;
    public final TraceMessage trace_message() throws RecognitionException {
        TraceMessage camiContent = null;

        Token CAMI_STRING1=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:136:2: ( 'TR(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:137:2: 'TR(' CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_trace_message580); 
            CAMI_STRING1=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message582); 
            match(input,12,FOLLOW_12_in_trace_message584); 

            		camiContent = new TraceMessage(CAMI_STRING1.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end trace_message


    // $ANTLR start warning_message
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:143:1: warning_message returns [WarningMessage camiContent] : 'WN(' CAMI_STRING ')' ;
    public final WarningMessage warning_message() throws RecognitionException {
        WarningMessage camiContent = null;

        Token CAMI_STRING2=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:145:2: ( 'WN(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:146:2: 'WN(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_warning_message604); 
            CAMI_STRING2=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message606); 
            match(input,12,FOLLOW_12_in_warning_message608); 

            		camiContent = new WarningMessage(CAMI_STRING2.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end warning_message


    // $ANTLR start special_message
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:152:1: special_message returns [SpecialMessages camiContent] : 'MO(' number ',' CAMI_STRING ')' ;
    public final SpecialMessages special_message() throws RecognitionException {
        SpecialMessages camiContent = null;

        Token CAMI_STRING4=null;
        int number3 = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:154:2: ( 'MO(' number ',' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:155:2: 'MO(' number ',' CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message629); 
            pushFollow(FOLLOW_number_in_special_message631);
            number3=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_special_message633); 
            CAMI_STRING4=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message635); 
            match(input,12,FOLLOW_12_in_special_message637); 

            		SpecialMessageType messageType = null;
            		switch (number3) {
            			case 1:
            				messageType = SpecialMessageType.motd;	
            			break;
            		case 2:
            				messageType = SpecialMessageType.quickAndUrgentWarning;
            			break;
            		case 3:
            				messageType = SpecialMessageType.copyrightMessage;
            			break;
            		case 4:
            				messageType = SpecialMessageType.executionStatistics;
            			break;
            		default:
            			break;
            		}
            		
            		camiContent = new SpecialMessages(messageType,CAMI_STRING4.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end special_message


    // $ANTLR start open_communication
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:182:1: open_communication returns [AuthenticationCommunicationAck message] : ( ack_open_communication | close_connection_panic | special_message );
    public final AuthenticationCommunicationAck open_communication() throws RecognitionException {
        AuthenticationCommunicationAck message = null;

        AckOpenCommunication ack_open_communication5 = null;

        CloseConnectionPanic close_connection_panic6 = null;

        SpecialMessages special_message7 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:184:2: ( ack_open_communication | close_connection_panic | special_message )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt9=1;
                }
                break;
            case 36:
                {
                alt9=2;
                }
                break;
            case 32:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("182:1: open_communication returns [AuthenticationCommunicationAck message] : ( ack_open_communication | close_connection_panic | special_message );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:185:4: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_open_communication663);
                    ack_open_communication5=ack_open_communication();
                    _fsp--;


                    	  	message = new AuthenticationCommunicationAck(ack_open_communication5);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:189:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_open_communication674);
                    close_connection_panic6=close_connection_panic();
                    _fsp--;


                    	  	if( true ) // to avoid an error in the generated code
                    		  	throw new AuthenticationFailure(close_connection_panic6);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:194:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_open_communication686);
                    special_message7=special_message();
                    _fsp--;


                    	  	if(true) // to avoid an error in the generated code
                    	  		throw new MessageFormatFailure(special_message7);
                    	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:202:1: check_version returns [AuthenticationVersionAck message] : ( ack_open_connection | special_message );
    public final AuthenticationVersionAck check_version() throws RecognitionException {
        AuthenticationVersionAck message = null;

        AckOpenConnection ack_open_connection8 = null;

        SpecialMessages special_message9 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:204:2: ( ack_open_connection | special_message )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==34) ) {
                alt10=1;
            }
            else if ( (LA10_0==32) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("202:1: check_version returns [AuthenticationVersionAck message] : ( ack_open_connection | special_message );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:205:4: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_check_version711);
                    ack_open_connection8=ack_open_connection();
                    _fsp--;


                    	  	message = new AuthenticationVersionAck(ack_open_connection8);  
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:209:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_check_version721);
                    special_message9=special_message();
                    _fsp--;


                    	  	if(true) // to avoid an error in the generated code
                    	  		throw new MessageFormatFailure(special_message9);
                    	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:216:1: ack_open_communication returns [AckOpenCommunication camiContent] : 'SC(' CAMI_STRING ')' ;
    public final AckOpenCommunication ack_open_communication() throws RecognitionException {
        AckOpenCommunication camiContent = null;

        Token CAMI_STRING10=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:218:2: ( 'SC(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:219:2: 'SC(' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_communication743); 
            CAMI_STRING10=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication745); 
            match(input,12,FOLLOW_12_in_ack_open_communication747); 

            		camiContent = new AckOpenCommunication(CAMI_STRING10.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end ack_open_communication


    // $ANTLR start ack_open_connection
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:225:1: ack_open_connection returns [AckOpenConnection camiContent] : 'OC(' n1= number ',' n2= number ')' ;
    public final AckOpenConnection ack_open_connection() throws RecognitionException {
        AckOpenConnection camiContent = null;

        int n1 = 0;

        int n2 = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:227:2: ( 'OC(' n1= number ',' n2= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:228:2: 'OC(' n1= number ',' n2= number ')'
            {
            match(input,34,FOLLOW_34_in_ack_open_connection768); 
            pushFollow(FOLLOW_number_in_ack_open_connection772);
            n1=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ack_open_connection774); 
            pushFollow(FOLLOW_number_in_ack_open_connection778);
            n2=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ack_open_connection780); 

            		camiContent = new AckOpenConnection(n1,n2);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end ack_open_connection


    // $ANTLR start close_connection_normal
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:234:1: close_connection_normal : 'FC()' ;
    public final void close_connection_normal() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:235:2: ( 'FC()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:236:2: 'FC()'
            {
            match(input,35,FOLLOW_35_in_close_connection_normal795); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:239:1: close_connection_panic returns [CloseConnectionPanic camiContent] : 'KO(1,' CAMI_STRING ',' number ')' ;
    public final CloseConnectionPanic close_connection_panic() throws RecognitionException {
        CloseConnectionPanic camiContent = null;

        Token CAMI_STRING11=null;
        int number12 = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:241:2: ( 'KO(1,' CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:242:2: 'KO(1,' CAMI_STRING ',' number ')'
            {
            match(input,36,FOLLOW_36_in_close_connection_panic813); 
            CAMI_STRING11=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic815); 
            match(input,11,FOLLOW_11_in_close_connection_panic817); 
            pushFollow(FOLLOW_number_in_close_connection_panic819);
            number12=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_close_connection_panic821); 

            		camiContent = new CloseConnectionPanic(CAMI_STRING11.getText(),number12);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end close_connection_panic


    // $ANTLR start interlocutor_table
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:250:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:251:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:252:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,37,FOLLOW_37_in_interlocutor_table838); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:253:2: ( body_table )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:253:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table842);
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

            match(input,38,FOLLOW_38_in_interlocutor_table847); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= number ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        int new_model = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:258:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:259:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= number ')'
            {
            match(input,39,FOLLOW_39_in_body_table860); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table864); 
            match(input,11,FOLLOW_11_in_body_table866); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table870); 
            match(input,11,FOLLOW_11_in_body_table872); 
            match(input,40,FOLLOW_40_in_body_table874); 
            match(input,11,FOLLOW_11_in_body_table876); 
            pushFollow(FOLLOW_number_in_body_table880);
            new_model=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_body_table882); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:264:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:265:2: ( question_state ask_hierarchic )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:266:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception895);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception898);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:270:1: result_reception returns [Results results] : 'DR()' reply_to_question (r+= ( question_state | special_message | warning_message | result ) )* 'FR(' number ')' ;
    public final Results result_reception() throws RecognitionException {
        Results results = null;

        Token r=null;
        List list_r=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:272:2: ( 'DR()' reply_to_question (r+= ( question_state | special_message | warning_message | result ) )* 'FR(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:273:2: 'DR()' reply_to_question (r+= ( question_state | special_message | warning_message | result ) )* 'FR(' number ')'
            {
            match(input,41,FOLLOW_41_in_result_reception915); 
            pushFollow(FOLLOW_reply_to_question_in_result_reception918);
            reply_to_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:3: (r+= ( question_state | special_message | warning_message | result ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=31 && LA13_0<=32)||(LA13_0>=44 && LA13_0<=45)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:3: r+= ( question_state | special_message | warning_message | result )
            	    {
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:5: ( question_state | special_message | warning_message | result )
            	    int alt12=4;
            	    switch ( input.LA(1) ) {
            	    case 44:
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
            	    case 45:
            	        {
            	        alt12=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("275:5: ( question_state | special_message | warning_message | result )", 12, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt12) {
            	        case 1 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:7: question_state
            	            {
            	            pushFollow(FOLLOW_question_state_in_result_reception925);
            	            question_state();
            	            _fsp--;


            	            }
            	            break;
            	        case 2 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:24: special_message
            	            {
            	            pushFollow(FOLLOW_special_message_in_result_reception929);
            	            special_message();
            	            _fsp--;


            	            }
            	            break;
            	        case 3 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:42: warning_message
            	            {
            	            pushFollow(FOLLOW_warning_message_in_result_reception933);
            	            warning_message();
            	            _fsp--;


            	            }
            	            break;
            	        case 4 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:275:60: result
            	            {
            	            pushFollow(FOLLOW_result_in_result_reception937);
            	            result();
            	            _fsp--;


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match(input,42,FOLLOW_42_in_result_reception943); 
            pushFollow(FOLLOW_number_in_result_reception945);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result_reception947); 

            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return results;
    }
    // $ANTLR end result_reception


    // $ANTLR start reply_to_question
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:281:1: reply_to_question : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' number ')' ;
    public final void reply_to_question() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:282:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:283:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' number ')'
            {
            match(input,43,FOLLOW_43_in_reply_to_question962); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question966); 
            match(input,11,FOLLOW_11_in_reply_to_question968); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question972); 
            match(input,11,FOLLOW_11_in_reply_to_question974); 
            pushFollow(FOLLOW_number_in_reply_to_question976);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_reply_to_question978); 

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
    // $ANTLR end reply_to_question


    // $ANTLR start question_state
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:286:1: question_state returns [QuestionState questionState] : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' ;
    public final QuestionState question_state() throws RecognitionException {
        QuestionState questionState = null;

        Token service_name=null;
        Token question_name=null;
        Token mess=null;
        int state = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:288:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:289:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')'
            {
            match(input,44,FOLLOW_44_in_question_state995); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state999); 
            match(input,11,FOLLOW_11_in_question_state1001); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1005); 
            match(input,11,FOLLOW_11_in_question_state1007); 
            pushFollow(FOLLOW_number_in_question_state1011);
            state=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_question_state1013); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:289:88: (mess= CAMI_STRING )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==CAMI_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:289:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1017); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state1020); 

            		questionState = new QuestionState(service_name.getText(),question_name.getText(),state,mess.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return questionState;
    }
    // $ANTLR end question_state


    // $ANTLR start result
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:295:1: result returns [ResultSet resultSet] : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' ;
    public final ResultSet result() throws RecognitionException {
        ResultSet resultSet = null;

        Token ensemble_name=null;
        int ensemble_type = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:297:2: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:298:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()'
            {
            match(input,45,FOLLOW_45_in_result1041); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1045); 
            match(input,11,FOLLOW_11_in_result1047); 
            pushFollow(FOLLOW_number_in_result1051);
            ensemble_type=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result1053); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:299:2: ( result_body )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==10||(LA15_0>=13 && LA15_0<=16)||LA15_0==45||(LA15_0>=47 && LA15_0<=53)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:299:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result1056);
            	    result_body();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);

            match(input,46,FOLLOW_46_in_result1060); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return resultSet;
    }
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:303:1: result_body returns [IResult res] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final IResult result_body() throws RecognitionException {
        IResult res = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:305:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt16=8;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt16=1;
                }
                break;
            case 47:
                {
                alt16=2;
                }
                break;
            case 48:
                {
                alt16=3;
                }
                break;
            case 49:
                {
                alt16=4;
                }
                break;
            case 50:
                {
                alt16=5;
                }
                break;
            case 51:
                {
                alt16=6;
                }
                break;
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
                {
                alt16=7;
                }
                break;
            case 52:
            case 53:
                {
                alt16=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("303:1: result_body returns [IResult res] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:306:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1083);
                    result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:307:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1089);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:308:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1095);
                    attribute_change();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:309:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1101);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:310:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1107);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:311:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1113);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:312:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1119);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:313:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1125);
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
        return res;
    }
    // $ANTLR end result_body


    // $ANTLR start textual_result
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:316:2: textual_result returns [IResult res] : 'RT(' CAMI_STRING ')' ;
    public final IResult textual_result() throws RecognitionException {
        IResult res = null;

        Token CAMI_STRING13=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:318:3: ( 'RT(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:319:3: 'RT(' CAMI_STRING ')'
            {
            match(input,47,FOLLOW_47_in_textual_result1148); 
            CAMI_STRING13=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1150); 
            match(input,12,FOLLOW_12_in_textual_result1152); 

             		res = new TextualResult(CAMI_STRING13.getText());
             	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return res;
    }
    // $ANTLR end textual_result


    // $ANTLR start attribute_change
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:325:2: attribute_change returns [IResult res] : 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final IResult attribute_change() throws RecognitionException {
        IResult res = null;

        Token attr_name=null;
        Token new_value=null;
        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:327:3: ( 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:328:3: 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,48,FOLLOW_48_in_attribute_change1179); 
            pushFollow(FOLLOW_number_in_attribute_change1183);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_change1185); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1189); 
            match(input,11,FOLLOW_11_in_attribute_change1191); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1195); 
            match(input,12,FOLLOW_12_in_attribute_change1197); 

             		res = new AttributeChange(id,attr_name.getText(),new_value.getText());
             	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return res;
    }
    // $ANTLR end attribute_change


    // $ANTLR start object_designation
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:334:2: object_designation returns [IResult res] : 'RO(' id= number ')' ;
    public final IResult object_designation() throws RecognitionException {
        IResult res = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:336:3: ( 'RO(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:3: 'RO(' id= number ')'
            {
            match(input,49,FOLLOW_49_in_object_designation1224); 
            pushFollow(FOLLOW_number_in_object_designation1228);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_designation1230); 

             		res = new ObjectDesignation(id);
             	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return res;
    }
    // $ANTLR end object_designation


    // $ANTLR start object_outline
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:343:2: object_outline returns [IResult res] : 'ME(' id= number ')' ;
    public final IResult object_outline() throws RecognitionException {
        IResult res = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:345:3: ( 'ME(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:346:3: 'ME(' id= number ')'
            {
            match(input,50,FOLLOW_50_in_object_outline1257); 
            pushFollow(FOLLOW_number_in_object_outline1261);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_outline1263); 

             		res = new ObjectOutline(id);
             	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return res;
    }
    // $ANTLR end object_outline


    // $ANTLR start attribute_outline
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:352:2: attribute_outline returns [IResult res] : 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' ;
    public final IResult attribute_outline() throws RecognitionException {
        IResult res = null;

        Token attr_name=null;
        int id = 0;

        int begin = 0;

        int end = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:354:3: ( 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:3: 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')'
            {
            match(input,51,FOLLOW_51_in_attribute_outline1290); 
            pushFollow(FOLLOW_number_in_attribute_outline1294);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_outline1296); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1300); 
            match(input,11,FOLLOW_11_in_attribute_outline1302); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:54: (begin= number )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NUMBER) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:54: begin= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1306);
                    begin=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1309); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:70: (end= number )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==NUMBER) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:355:70: end= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1313);
                    end=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1316); 

            		res = new AttributeOutline(id,attr_name.getText());
             	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return res;
    }
    // $ANTLR end attribute_outline


    // $ANTLR start object_creation
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:361:2: object_creation returns [IResult res] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')' );
    public final IResult object_creation() throws RecognitionException {
        IResult res = null;

        Token node_box_type=null;
        Token arc_type=null;
        Token attribute_name=null;
        Token value=null;
        int id = 0;

        int page_id = 0;

        int start_node = 0;

        int end_node = 0;

        int associated_node = 0;

        int line_number = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:363:3: ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')' )
            int alt19=5;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt19=1;
                }
                break;
            case 13:
                {
                alt19=2;
                }
                break;
            case 14:
                {
                alt19=3;
                }
                break;
            case 15:
                {
                alt19=4;
                }
                break;
            case 16:
                {
                alt19=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("361:2: object_creation returns [IResult res] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')' );", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:364:4: 'CN(' node_box_type= CAMI_STRING ',' id= number ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1344); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1348); 
                    match(input,11,FOLLOW_11_in_object_creation1350); 
                    pushFollow(FOLLOW_number_in_object_creation1354);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1356); 

                    	  	res = new CreateNode(node_box_type.getText(),id);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:4: 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1366); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1370); 
                    match(input,11,FOLLOW_11_in_object_creation1372); 
                    pushFollow(FOLLOW_number_in_object_creation1376);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1378); 
                    pushFollow(FOLLOW_number_in_object_creation1382);
                    page_id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1384); 

                    	  	res = new CreateBox(node_box_type.getText(),id,page_id);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:372:4: 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1394); 
                    arc_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1398); 
                    match(input,11,FOLLOW_11_in_object_creation1400); 
                    pushFollow(FOLLOW_number_in_object_creation1404);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1406); 
                    pushFollow(FOLLOW_number_in_object_creation1410);
                    start_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1412); 
                    pushFollow(FOLLOW_number_in_object_creation1416);
                    end_node=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1418); 

                    	  	res = new CreateArc(arc_type.getText(),id,start_node,end_node);
                    	  

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:376:4: 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1428); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1432); 
                    match(input,11,FOLLOW_11_in_object_creation1434); 
                    pushFollow(FOLLOW_number_in_object_creation1438);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1440); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1444); 
                    match(input,12,FOLLOW_12_in_object_creation1446); 

                    	  	res = new CreateMonolineAttribute(attribute_name.getText(),associated_node,value.getText());
                    	  

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:380:4: 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1456); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1460); 
                    match(input,11,FOLLOW_11_in_object_creation1462); 
                    pushFollow(FOLLOW_number_in_object_creation1466);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1468); 
                    pushFollow(FOLLOW_number_in_object_creation1472);
                    line_number=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1474); 
                    pushFollow(FOLLOW_number_in_object_creation1476);
                    number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1478); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1482); 
                    match(input,12,FOLLOW_12_in_object_creation1484); 

                    	  	res = new CreateMultilineAttribute(attribute_name.getText(),associated_node,line_number,value.getText());
                    	  

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
        return res;
    }
    // $ANTLR end object_creation


    // $ANTLR start object_deletion
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:386:1: object_deletion returns [IResult res] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );
    public final IResult object_deletion() throws RecognitionException {
        IResult res = null;

        int id = 0;

        int page_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:388:2: ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==52) ) {
                alt20=1;
            }
            else if ( (LA20_0==53) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("386:1: object_deletion returns [IResult res] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:389:5: 'SU(' id= number ')'
                    {
                    match(input,52,FOLLOW_52_in_object_deletion1512); 
                    pushFollow(FOLLOW_number_in_object_deletion1516);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1518); 

                     	  	res = new ObjectDeletion(id);
                     	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:393:5: 'SI(' page_id= number ',' id= number ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1530); 
                    pushFollow(FOLLOW_number_in_object_deletion1534);
                    page_id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_deletion1536); 
                    pushFollow(FOLLOW_number_in_object_deletion1540);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1542); 

                     	  	res = new MultipleObjectDeletion(page_id,id);
                     	  

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
        return res;
    }
    // $ANTLR end object_deletion


    // $ANTLR start ack_open_session
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:401:1: ack_open_session returns [AckOpenSession camiContent] : 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final AckOpenSession ack_open_session() throws RecognitionException {
        AckOpenSession camiContent = null;

        Token CAMI_STRING14=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:403:2: ( 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:404:2: 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,54,FOLLOW_54_in_ack_open_session1569); 
            CAMI_STRING14=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1571); 
            match(input,12,FOLLOW_12_in_ack_open_session1573); 
            match(input,55,FOLLOW_55_in_ack_open_session1576); 
            match(input,56,FOLLOW_56_in_ack_open_session1579); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1582);
            interlocutor_table();
            _fsp--;


            		camiContent = new AckOpenSession(CAMI_STRING14.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end ack_open_session


    // $ANTLR start ack_close_current_session
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:413:1: ack_close_current_session : 'FS()' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:414:2: ( 'FS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:415:2: 'FS()'
            {
            match(input,57,FOLLOW_57_in_ack_close_current_session1599); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:418:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:419:2: ( 'SS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:420:2: 'SS()'
            {
            match(input,58,FOLLOW_58_in_ack_suspend_current_session1614); 

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


    // $ANTLR start ack_resume_session
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:423:1: ack_resume_session returns [AckResumeSession camiContent] : 'RS(' CAMI_STRING ')' ;
    public final AckResumeSession ack_resume_session() throws RecognitionException {
        AckResumeSession camiContent = null;

        Token CAMI_STRING15=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:425:2: ( 'RS(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:426:2: 'RS(' CAMI_STRING ')'
            {
            match(input,59,FOLLOW_59_in_ack_resume_session1631); 
            CAMI_STRING15=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_session1633); 
            match(input,12,FOLLOW_12_in_ack_resume_session1635); 

            		camiContent = new AckResumeSession(CAMI_STRING15.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return camiContent;
    }
    // $ANTLR end ack_resume_session


    // $ANTLR start ask_and_get_model
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:434:1: ask_and_get_model : ( ask_for_a_model | model_definition | change_date );
    public final void ask_and_get_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:435:2: ( ask_for_a_model | model_definition | change_date )
            int alt21=3;
            switch ( input.LA(1) ) {
            case 60:
            case 61:
                {
                alt21=1;
                }
                break;
            case 8:
                {
                alt21=2;
                }
                break;
            case 62:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("434:1: ask_and_get_model : ( ask_for_a_model | model_definition | change_date );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:436:4: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_ask_and_get_model1654);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:437:4: model_definition
                    {
                    pushFollow(FOLLOW_model_definition_in_ask_and_get_model1659);
                    model_definition();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:438:4: change_date
                    {
                    pushFollow(FOLLOW_change_date_in_ask_and_get_model1664);
                    change_date();
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
    // $ANTLR end ask_and_get_model


    // $ANTLR start ask_for_a_model
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:441:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:442:2: ( ask_simple | ask_hierarchic )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==60) ) {
                alt22=1;
            }
            else if ( (LA22_0==61) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("441:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:443:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model1676);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:443:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model1680);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:446:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:447:2: ( 'DF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:448:2: 'DF()'
            {
            match(input,60,FOLLOW_60_in_ask_simple1692); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:451:1: ask_hierarchic : 'DF(-2,' number ',' number ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:452:2: ( 'DF(-2,' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:453:2: 'DF(-2,' number ',' number ')'
            {
            match(input,61,FOLLOW_61_in_ask_hierarchic1704); 
            pushFollow(FOLLOW_number_in_ask_hierarchic1706);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ask_hierarchic1708); 
            pushFollow(FOLLOW_number_in_ask_hierarchic1710);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ask_hierarchic1712); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:458:1: change_date : 'MS(' number ')' ;
    public final void change_date() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:459:2: ( 'MS(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:460:2: 'MS(' number ')'
            {
            match(input,62,FOLLOW_62_in_change_date1726); 
            pushFollow(FOLLOW_number_in_change_date1728);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_change_date1730); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:465:1: service_menu_reception : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final void service_menu_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:466:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:467:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,63,FOLLOW_63_in_service_menu_reception1744); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception1747);
            menu_name();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:469:2: ( question_add )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==66) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:469:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception1750);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            match(input,64,FOLLOW_64_in_service_menu_reception1754); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:473:1: menu_name : 'CQ(' name= CAMI_STRING ',' number ',' number ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:474:2: ( 'CQ(' name= CAMI_STRING ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:475:2: 'CQ(' name= CAMI_STRING ',' number ',' number ')'
            {
            match(input,65,FOLLOW_65_in_menu_name1766); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name1770); 
            match(input,11,FOLLOW_11_in_menu_name1772); 
            pushFollow(FOLLOW_number_in_menu_name1774);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_menu_name1776); 
            pushFollow(FOLLOW_number_in_menu_name1778);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_menu_name1780); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:478:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' ;
    public final void question_add() throws RecognitionException {
        Token parent_menu=null;
        Token entry_name=null;
        Token ouput_formalism=null;
        int question_type = 0;

        int question_behavior = 0;

        int set_item = 0;

        int historic = 0;

        int stop_authorized = 0;

        int active = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:479:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:480:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')'
            {
            match(input,66,FOLLOW_66_in_question_add1792); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1796); 
            match(input,11,FOLLOW_11_in_question_add1798); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1802); 
            match(input,11,FOLLOW_11_in_question_add1804); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:481:16: (question_type= number )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:481:16: question_type= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1811);
                    question_type=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1814); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:481:46: (question_behavior= number )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:481:46: question_behavior= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1818);
                    question_behavior=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1821); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:11: (set_item= number )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:11: set_item= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1828);
                    set_item=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1831); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:33: (historic= number )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==NUMBER) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:33: historic= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1836);
                    historic=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1839); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:61: (stop_authorized= number )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==NUMBER) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:61: stop_authorized= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1843);
                    stop_authorized=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1846); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:483:18: (ouput_formalism= CAMI_STRING )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==CAMI_STRING) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:483:18: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1853); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1856); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:483:42: (active= number )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==NUMBER) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:483:42: active= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1860);
                    active=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add1863); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:486:1: service_menu_modification : enable_main_question ( question_state )* end_menu_transmission ;
    public final void service_menu_modification() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:487:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:488:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification1875);
            enable_main_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:489:2: ( question_state )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==44) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:489:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification1878);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification1882);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:493:1: enable_main_question : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final void enable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:494:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:495:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,67,FOLLOW_67_in_enable_main_question1894); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question1898); 
            match(input,12,FOLLOW_12_in_enable_main_question1900); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:498:1: disable_main_question : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final void disable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:499:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:500:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,68,FOLLOW_68_in_disable_main_question1912); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question1916); 
            match(input,12,FOLLOW_12_in_disable_main_question1918); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:503:1: end_menu_transmission : 'QQ(' number ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:504:2: ( 'QQ(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:505:2: 'QQ(' number ')'
            {
            match(input,69,FOLLOW_69_in_end_menu_transmission1930); 
            pushFollow(FOLLOW_number_in_end_menu_transmission1932);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_end_menu_transmission1934); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:508:1: help_question : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final void help_question() throws RecognitionException {
        Token question_name=null;
        Token help_message=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:509:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:510:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,70,FOLLOW_70_in_help_question1946); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1950); 
            match(input,11,FOLLOW_11_in_help_question1952); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1956); 
            match(input,12,FOLLOW_12_in_help_question1958); 

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


    // $ANTLR start number
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:517:1: number returns [int value] : NUMBER ;
    public final int number() throws RecognitionException {
        int value = 0;

        Token NUMBER16=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:519:2: ( NUMBER )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:520:2: NUMBER
            {
            NUMBER16=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_number1979); 
            value = Integer.parseInt(NUMBER16.getText());

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end number


 

    public static final BitSet FOLLOW_8_in_model_definition35 = new BitSet(new long[]{0x00000000003FE400L});
    public static final BitSet FOLLOW_syntactic_in_model_definition40 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_aestetic_in_model_definition44 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_9_in_model_definition49 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_node_in_syntactic61 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_box_in_syntactic65 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arc_in_syntactic69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_attribute_in_syntactic73 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_node85 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_node87 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_node89 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_node91 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_node93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_box105 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_box107 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box109 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_box111 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_box113 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_box115 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_box117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_arc129 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_arc131 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc133 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_arc135 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc137 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_arc139 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_arc141 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_arc143 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_arc145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_textual_attribute159 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute161 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute163 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_textual_attribute165 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute167 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute169 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_attribute171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_textual_attribute176 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute178 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute180 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_textual_attribute182 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute184 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_textual_attribute186 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute188 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_textual_attribute190 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_textual_attribute192 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_attribute194 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_attribute196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_position_in_aestetic209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_text_position_in_aestetic213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_intermediary_point_in_aestetic217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_object_position231 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position235 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position237 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position241 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position243 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position247 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_object_position254 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position258 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position260 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position264 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position266 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position270 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_object_position277 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position281 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position283 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position287 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position289 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position293 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position295 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position299 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_position301 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_position305 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_position306 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_text_position319 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_text_position323 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position325 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_text_position329 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position331 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_text_position335 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_text_position337 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_text_position341 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_text_position343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_intermediary_point356 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_intermediary_point358 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point360 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_intermediary_point362 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_intermediary_point364 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_intermediary_point366 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_intermediary_point368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_dialog_definition384 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition387 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition390 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_dialog_definition394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_dialog_creation406 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation408 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation410 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation412 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation414 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation416 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation418 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation421 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation423 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation425 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation427 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation429 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation431 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation436 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation438 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation440 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation442 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation444 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_dialog_creation447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_next_dialog459 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_next_dialog461 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog463 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog465 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_next_dialog467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_display_dialog479 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_display_dialog481 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_display_dialog483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_hide_dialog496 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_hide_dialog498 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_hide_dialog500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_destroy_dialog513 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_destroy_dialog515 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_destroy_dialog517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_interactive_response529 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_interactive_response531 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response533 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_interactive_response535 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_interactive_response537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_trace_message580 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message582 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_trace_message584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_warning_message604 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message606 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_warning_message608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_special_message629 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_special_message631 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message633 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message635 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_communication_in_open_communication663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_open_communication674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_open_communication686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_check_version711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_check_version721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_communication743 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication745 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ack_open_connection768 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection772 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection774 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection778 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_normal795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_close_connection_panic813 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic815 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic817 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_close_connection_panic819 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_interlocutor_table838 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table842 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_38_in_interlocutor_table847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body_table860 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table864 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table866 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table870 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table872 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_body_table874 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table876 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_body_table880 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception895 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_result_reception915 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_reply_to_question_in_result_reception918 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception925 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception929 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception933 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_result_in_result_reception937 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_42_in_result_reception943 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result_reception945 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_reply_to_question962 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question966 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question968 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question972 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question974 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_reply_to_question976 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_reply_to_question978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_question_state995 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state999 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1001 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1005 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1007 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_question_state1011 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1013 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1017 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_result1041 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1045 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1047 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result1051 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result1053 = new BitSet(new long[]{0x003FA0000001E400L});
    public static final BitSet FOLLOW_result_body_in_result1056 = new BitSet(new long[]{0x003FE0000001E400L});
    public static final BitSet FOLLOW_46_in_result1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_textual_result1148 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1150 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_attribute_change1179 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_change1183 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1185 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1189 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1191 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1195 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_designation1224 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_designation1228 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_outline1257 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_outline1261 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_attribute_outline1290 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1294 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1296 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1300 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1302 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_attribute_outline1306 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1309 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1313 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1344 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1348 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1350 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1354 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1366 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1370 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1372 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1376 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1378 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1382 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1394 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1398 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1400 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1404 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1406 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1410 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1412 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1416 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1428 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1432 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1434 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1438 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1440 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1444 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1456 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1460 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1462 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1466 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1468 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1472 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1474 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1476 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1478 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1482 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_deletion1512 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1516 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1530 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1534 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1536 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1540 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ack_open_session1569 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1571 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1573 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ack_open_session1576 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ack_open_session1579 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ack_close_current_session1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_suspend_current_session1614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ack_resume_session1631 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_session1633 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_session1635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_ask_and_get_model1654 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_model_definition_in_ask_and_get_model1659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_change_date_in_ask_and_get_model1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model1676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ask_simple1692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ask_hierarchic1704 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic1706 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic1708 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic1710 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_change_date1726 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_change_date1728 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_change_date1730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_service_menu_reception1744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception1747 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000005L});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception1750 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000005L});
    public static final BitSet FOLLOW_64_in_service_menu_reception1754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_menu_name1766 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name1770 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1772 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name1774 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1776 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name1778 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name1780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_question_add1792 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1796 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1798 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1802 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1804 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1811 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1814 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1818 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1821 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1828 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1831 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1836 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1839 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1843 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1846 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1853 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1856 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_question_add1860 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add1863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification1875 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification1878 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification1882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_enable_main_question1894 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question1898 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question1900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_disable_main_question1912 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question1916 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_end_menu_transmission1930 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_end_menu_transmission1932 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_help_question1946 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1950 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question1952 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1956 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question1958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_number1979 = new BitSet(new long[]{0x0000000000000002L});

}