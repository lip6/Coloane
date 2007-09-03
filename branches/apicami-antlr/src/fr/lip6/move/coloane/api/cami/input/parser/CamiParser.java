// $ANTLR 3.0.1 /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g 2007-09-03 12:27:37

package fr.lip6.move.coloane.api.cami.input.parser;

import fr.lip6.move.coloane.api.cami.*;
import fr.lip6.move.coloane.api.cami.SpecialMessages.*;
import fr.lip6.move.coloane.api.cami.results.*;
import fr.lip6.move.coloane.api.camiCommands.AttributeChange;
import fr.lip6.move.coloane.api.camiCommands.AttributeOutline;
import fr.lip6.move.coloane.api.camiCommands.CreateArc;
import fr.lip6.move.coloane.api.camiCommands.CreateBox;
import fr.lip6.move.coloane.api.camiCommands.CreateMonolineAttribute;
import fr.lip6.move.coloane.api.camiCommands.CreateMultilineAttribute;
import fr.lip6.move.coloane.api.camiCommands.CreateNode;
import fr.lip6.move.coloane.api.camiCommands.IResult;
import fr.lip6.move.coloane.api.camiCommands.MultipleObjectDeletion;
import fr.lip6.move.coloane.api.camiCommands.ObjectDeletion;
import fr.lip6.move.coloane.api.camiCommands.ObjectDesignation;
import fr.lip6.move.coloane.api.camiCommands.ObjectOutline;
import fr.lip6.move.coloane.api.camiCommands.QuestionState;
import fr.lip6.move.coloane.api.camiCommands.ResultSet;
import fr.lip6.move.coloane.api.camiCommands.Results;
import fr.lip6.move.coloane.api.camiCommands.TextualResult;
import fr.lip6.move.coloane.api.session.states.*;
import fr.lip6.move.coloane.api.session.states.authentication.*;


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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:152:1: special_message returns [SpecialMessages camiContent] : 'MO(' message_type= number ',' message= CAMI_STRING ')' ;
    public final SpecialMessages special_message() throws RecognitionException {
        SpecialMessages camiContent = null;

        Token message=null;
        int message_type = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:154:2: ( 'MO(' message_type= number ',' message= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:155:2: 'MO(' message_type= number ',' message= CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message629); 
            pushFollow(FOLLOW_number_in_special_message633);
            message_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_special_message635); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message639); 
            match(input,12,FOLLOW_12_in_special_message641); 

            		camiContent = new SpecialMessages(SpecialMessages.SpecialMessageType(message_type),message.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:164:1: open_communication returns [AuthenticationCommunicationAck message] : ( ack_open_communication | close_connection_panic | special_message );
    public final AuthenticationCommunicationAck open_communication() throws RecognitionException {
        AuthenticationCommunicationAck message = null;

        AckOpenCommunication ack_open_communication3 = null;

        CloseConnectionPanic close_connection_panic4 = null;

        SpecialMessages special_message5 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:166:2: ( ack_open_communication | close_connection_panic | special_message )
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
                    new NoViableAltException("164:1: open_communication returns [AuthenticationCommunicationAck message] : ( ack_open_communication | close_connection_panic | special_message );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:167:4: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_open_communication667);
                    ack_open_communication3=ack_open_communication();
                    _fsp--;


                    	  	message = new AuthenticationCommunicationAck(ack_open_communication3);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:171:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_open_communication678);
                    close_connection_panic4=close_connection_panic();
                    _fsp--;


                    	  	if( true ) // to avoid an error in the generated code
                    		  	throw new AuthenticationFailure(close_connection_panic4);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:176:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_open_communication690);
                    special_message5=special_message();
                    _fsp--;


                    	  	if(true) // to avoid an error in the generated code
                    	  		throw new MessageFormatFailure(special_message5);
                    	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:184:1: check_version returns [AuthenticationVersionAck message] : ( ack_open_connection | special_message );
    public final AuthenticationVersionAck check_version() throws RecognitionException {
        AuthenticationVersionAck message = null;

        AckOpenConnection ack_open_connection6 = null;

        SpecialMessages special_message7 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:186:2: ( ack_open_connection | special_message )
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
                    new NoViableAltException("184:1: check_version returns [AuthenticationVersionAck message] : ( ack_open_connection | special_message );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:187:4: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_check_version715);
                    ack_open_connection6=ack_open_connection();
                    _fsp--;


                    	  	message = new AuthenticationVersionAck(ack_open_connection6);  
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:191:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_check_version725);
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
    // $ANTLR end check_version


    // $ANTLR start ack_open_communication
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:198:1: ack_open_communication returns [AckOpenCommunication camiContent] : 'SC(' CAMI_STRING ')' ;
    public final AckOpenCommunication ack_open_communication() throws RecognitionException {
        AckOpenCommunication camiContent = null;

        Token CAMI_STRING8=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:200:2: ( 'SC(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:201:2: 'SC(' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_communication747); 
            CAMI_STRING8=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication749); 
            match(input,12,FOLLOW_12_in_ack_open_communication751); 

            		camiContent = new AckOpenCommunication(CAMI_STRING8.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:207:1: ack_open_connection returns [AckOpenConnection camiContent] : 'OC(' n1= number ',' n2= number ')' ;
    public final AckOpenConnection ack_open_connection() throws RecognitionException {
        AckOpenConnection camiContent = null;

        int n1 = 0;

        int n2 = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:209:2: ( 'OC(' n1= number ',' n2= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:210:2: 'OC(' n1= number ',' n2= number ')'
            {
            match(input,34,FOLLOW_34_in_ack_open_connection772); 
            pushFollow(FOLLOW_number_in_ack_open_connection776);
            n1=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ack_open_connection778); 
            pushFollow(FOLLOW_number_in_ack_open_connection782);
            n2=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ack_open_connection784); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:216:1: close_connection_normal : 'FC()' ;
    public final void close_connection_normal() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:217:2: ( 'FC()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:218:2: 'FC()'
            {
            match(input,35,FOLLOW_35_in_close_connection_normal799); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:221:1: close_connection_panic returns [CloseConnectionPanic camiContent] : 'KO(1,' CAMI_STRING ',' number ')' ;
    public final CloseConnectionPanic close_connection_panic() throws RecognitionException {
        CloseConnectionPanic camiContent = null;

        Token CAMI_STRING9=null;
        int number10 = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:223:2: ( 'KO(1,' CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:224:2: 'KO(1,' CAMI_STRING ',' number ')'
            {
            match(input,36,FOLLOW_36_in_close_connection_panic817); 
            CAMI_STRING9=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic819); 
            match(input,11,FOLLOW_11_in_close_connection_panic821); 
            pushFollow(FOLLOW_number_in_close_connection_panic823);
            number10=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_close_connection_panic825); 

            		camiContent = new CloseConnectionPanic(CAMI_STRING9.getText(),number10);
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:232:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:233:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:234:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,37,FOLLOW_37_in_interlocutor_table842); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:235:2: ( body_table )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:235:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table846);
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

            match(input,38,FOLLOW_38_in_interlocutor_table851); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:239:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= number ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        int new_model = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:240:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:241:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' '3' ',' new_model= number ')'
            {
            match(input,39,FOLLOW_39_in_body_table864); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table868); 
            match(input,11,FOLLOW_11_in_body_table870); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table874); 
            match(input,11,FOLLOW_11_in_body_table876); 
            match(input,40,FOLLOW_40_in_body_table878); 
            match(input,11,FOLLOW_11_in_body_table880); 
            pushFollow(FOLLOW_number_in_body_table884);
            new_model=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_body_table886); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:246:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:247:2: ( question_state ask_hierarchic )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:248:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception899);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception902);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:252:1: result_reception returns [Results results] : 'DR()' reply_to_question (r+= ( question_state | special_message | warning_message | result ) )* 'FR(' number ')' ;
    public final Results result_reception() throws RecognitionException {
        Results results = null;

        Token r=null;
        List list_r=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:254:2: ( 'DR()' reply_to_question (r+= ( question_state | special_message | warning_message | result ) )* 'FR(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:255:2: 'DR()' reply_to_question (r+= ( question_state | special_message | warning_message | result ) )* 'FR(' number ')'
            {
            match(input,41,FOLLOW_41_in_result_reception919); 
            pushFollow(FOLLOW_reply_to_question_in_result_reception922);
            reply_to_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:3: (r+= ( question_state | special_message | warning_message | result ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=31 && LA13_0<=32)||(LA13_0>=44 && LA13_0<=45)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:3: r+= ( question_state | special_message | warning_message | result )
            	    {
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:5: ( question_state | special_message | warning_message | result )
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
            	            new NoViableAltException("257:5: ( question_state | special_message | warning_message | result )", 12, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt12) {
            	        case 1 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:7: question_state
            	            {
            	            pushFollow(FOLLOW_question_state_in_result_reception929);
            	            question_state();
            	            _fsp--;


            	            }
            	            break;
            	        case 2 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:24: special_message
            	            {
            	            pushFollow(FOLLOW_special_message_in_result_reception933);
            	            special_message();
            	            _fsp--;


            	            }
            	            break;
            	        case 3 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:42: warning_message
            	            {
            	            pushFollow(FOLLOW_warning_message_in_result_reception937);
            	            warning_message();
            	            _fsp--;


            	            }
            	            break;
            	        case 4 :
            	            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:257:60: result
            	            {
            	            pushFollow(FOLLOW_result_in_result_reception941);
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

            match(input,42,FOLLOW_42_in_result_reception947); 
            pushFollow(FOLLOW_number_in_result_reception949);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result_reception951); 

            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:263:1: reply_to_question : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' number ')' ;
    public final void reply_to_question() throws RecognitionException {
        Token service_name=null;
        Token question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:264:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:265:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' number ')'
            {
            match(input,43,FOLLOW_43_in_reply_to_question966); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question970); 
            match(input,11,FOLLOW_11_in_reply_to_question972); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question976); 
            match(input,11,FOLLOW_11_in_reply_to_question978); 
            pushFollow(FOLLOW_number_in_reply_to_question980);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_reply_to_question982); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:268:1: question_state returns [QuestionState questionState] : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' ;
    public final QuestionState question_state() throws RecognitionException {
        QuestionState questionState = null;

        Token service_name=null;
        Token question_name=null;
        Token mess=null;
        int state = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:270:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:271:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')'
            {
            match(input,44,FOLLOW_44_in_question_state999); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1003); 
            match(input,11,FOLLOW_11_in_question_state1005); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1009); 
            match(input,11,FOLLOW_11_in_question_state1011); 
            pushFollow(FOLLOW_number_in_question_state1015);
            state=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_question_state1017); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:271:88: (mess= CAMI_STRING )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==CAMI_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:271:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1021); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state1024); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:277:1: result returns [ResultSet resultSet] : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' ;
    public final ResultSet result() throws RecognitionException {
        ResultSet resultSet = null;

        Token ensemble_name=null;
        int ensemble_type = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:279:2: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:280:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()'
            {
            match(input,45,FOLLOW_45_in_result1045); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1049); 
            match(input,11,FOLLOW_11_in_result1051); 
            pushFollow(FOLLOW_number_in_result1055);
            ensemble_type=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result1057); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:281:2: ( result_body )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:281:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result1060);
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

            match(input,46,FOLLOW_46_in_result1064); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:285:1: result_body returns [IResult res] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final IResult result_body() throws RecognitionException {
        IResult res = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:287:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
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
                    new NoViableAltException("285:1: result_body returns [IResult res] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:288:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1087);
                    result();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:289:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1093);
                    textual_result();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:290:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1099);
                    attribute_change();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:291:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1105);
                    object_designation();
                    _fsp--;


                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:292:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1111);
                    object_outline();
                    _fsp--;


                    }
                    break;
                case 6 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:293:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1117);
                    attribute_outline();
                    _fsp--;


                    }
                    break;
                case 7 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:294:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1123);
                    object_creation();
                    _fsp--;


                    }
                    break;
                case 8 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:295:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1129);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:298:2: textual_result returns [IResult res] : 'RT(' CAMI_STRING ')' ;
    public final IResult textual_result() throws RecognitionException {
        IResult res = null;

        Token CAMI_STRING11=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:300:3: ( 'RT(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:301:3: 'RT(' CAMI_STRING ')'
            {
            match(input,47,FOLLOW_47_in_textual_result1152); 
            CAMI_STRING11=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1154); 
            match(input,12,FOLLOW_12_in_textual_result1156); 

             		res = new TextualResult(CAMI_STRING11.getText());
             	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:307:2: attribute_change returns [IResult res] : 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final IResult attribute_change() throws RecognitionException {
        IResult res = null;

        Token attr_name=null;
        Token new_value=null;
        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:309:3: ( 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:310:3: 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,48,FOLLOW_48_in_attribute_change1183); 
            pushFollow(FOLLOW_number_in_attribute_change1187);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_change1189); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1193); 
            match(input,11,FOLLOW_11_in_attribute_change1195); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1199); 
            match(input,12,FOLLOW_12_in_attribute_change1201); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:316:2: object_designation returns [IResult res] : 'RO(' id= number ')' ;
    public final IResult object_designation() throws RecognitionException {
        IResult res = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:318:3: ( 'RO(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:319:3: 'RO(' id= number ')'
            {
            match(input,49,FOLLOW_49_in_object_designation1228); 
            pushFollow(FOLLOW_number_in_object_designation1232);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_designation1234); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:325:2: object_outline returns [IResult res] : 'ME(' id= number ')' ;
    public final IResult object_outline() throws RecognitionException {
        IResult res = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:327:3: ( 'ME(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:328:3: 'ME(' id= number ')'
            {
            match(input,50,FOLLOW_50_in_object_outline1261); 
            pushFollow(FOLLOW_number_in_object_outline1265);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_outline1267); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:334:2: attribute_outline returns [IResult res] : 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' ;
    public final IResult attribute_outline() throws RecognitionException {
        IResult res = null;

        Token attr_name=null;
        int id = 0;

        int begin = 0;

        int end = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:336:3: ( 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:3: 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')'
            {
            match(input,51,FOLLOW_51_in_attribute_outline1294); 
            pushFollow(FOLLOW_number_in_attribute_outline1298);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_outline1300); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1304); 
            match(input,11,FOLLOW_11_in_attribute_outline1306); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:54: (begin= number )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NUMBER) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:54: begin= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1310);
                    begin=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1313); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:70: (end= number )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==NUMBER) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:337:70: end= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1317);
                    end=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1320); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:343:2: object_creation returns [IResult res] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')' );
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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:345:3: ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')' )
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
                    new NoViableAltException("343:2: object_creation returns [IResult res] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')' );", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:346:4: 'CN(' node_box_type= CAMI_STRING ',' id= number ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1348); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1352); 
                    match(input,11,FOLLOW_11_in_object_creation1354); 
                    pushFollow(FOLLOW_number_in_object_creation1358);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1360); 

                    	  	res = new CreateNode(node_box_type.getText(),id);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:350:4: 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1370); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1374); 
                    match(input,11,FOLLOW_11_in_object_creation1376); 
                    pushFollow(FOLLOW_number_in_object_creation1380);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1382); 
                    pushFollow(FOLLOW_number_in_object_creation1386);
                    page_id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1388); 

                    	  	res = new CreateBox(node_box_type.getText(),id,page_id);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:354:4: 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1398); 
                    arc_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1402); 
                    match(input,11,FOLLOW_11_in_object_creation1404); 
                    pushFollow(FOLLOW_number_in_object_creation1408);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1410); 
                    pushFollow(FOLLOW_number_in_object_creation1414);
                    start_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1416); 
                    pushFollow(FOLLOW_number_in_object_creation1420);
                    end_node=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1422); 

                    	  	res = new CreateArc(arc_type.getText(),id,start_node,end_node);
                    	  

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:358:4: 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1432); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1436); 
                    match(input,11,FOLLOW_11_in_object_creation1438); 
                    pushFollow(FOLLOW_number_in_object_creation1442);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1444); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1448); 
                    match(input,12,FOLLOW_12_in_object_creation1450); 

                    	  	res = new CreateMonolineAttribute(attribute_name.getText(),associated_node,value.getText());
                    	  

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:362:4: 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' number ',' value= CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1460); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1464); 
                    match(input,11,FOLLOW_11_in_object_creation1466); 
                    pushFollow(FOLLOW_number_in_object_creation1470);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1472); 
                    pushFollow(FOLLOW_number_in_object_creation1476);
                    line_number=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1478); 
                    pushFollow(FOLLOW_number_in_object_creation1480);
                    number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1482); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1486); 
                    match(input,12,FOLLOW_12_in_object_creation1488); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:368:1: object_deletion returns [IResult res] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );
    public final IResult object_deletion() throws RecognitionException {
        IResult res = null;

        int id = 0;

        int page_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:370:2: ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' )
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
                    new NoViableAltException("368:1: object_deletion returns [IResult res] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:371:5: 'SU(' id= number ')'
                    {
                    match(input,52,FOLLOW_52_in_object_deletion1516); 
                    pushFollow(FOLLOW_number_in_object_deletion1520);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1522); 

                     	  	res = new ObjectDeletion(id);
                     	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:375:5: 'SI(' page_id= number ',' id= number ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1534); 
                    pushFollow(FOLLOW_number_in_object_deletion1538);
                    page_id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_deletion1540); 
                    pushFollow(FOLLOW_number_in_object_deletion1544);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1546); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:383:1: ack_open_session returns [AckOpenSession camiContent] : 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final AckOpenSession ack_open_session() throws RecognitionException {
        AckOpenSession camiContent = null;

        Token CAMI_STRING12=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:385:2: ( 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:386:2: 'OS(' CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,54,FOLLOW_54_in_ack_open_session1573); 
            CAMI_STRING12=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1575); 
            match(input,12,FOLLOW_12_in_ack_open_session1577); 
            match(input,55,FOLLOW_55_in_ack_open_session1580); 
            match(input,56,FOLLOW_56_in_ack_open_session1583); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1586);
            interlocutor_table();
            _fsp--;


            		camiContent = new AckOpenSession(CAMI_STRING12.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:395:1: ack_close_current_session : 'FS()' ;
    public final void ack_close_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:396:2: ( 'FS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:397:2: 'FS()'
            {
            match(input,57,FOLLOW_57_in_ack_close_current_session1603); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:400:1: ack_suspend_current_session : 'SS()' ;
    public final void ack_suspend_current_session() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:401:2: ( 'SS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:402:2: 'SS()'
            {
            match(input,58,FOLLOW_58_in_ack_suspend_current_session1618); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:405:1: ack_resume_session returns [AckResumeSession camiContent] : 'RS(' CAMI_STRING ')' ;
    public final AckResumeSession ack_resume_session() throws RecognitionException {
        AckResumeSession camiContent = null;

        Token CAMI_STRING13=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:407:2: ( 'RS(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:408:2: 'RS(' CAMI_STRING ')'
            {
            match(input,59,FOLLOW_59_in_ack_resume_session1635); 
            CAMI_STRING13=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_session1637); 
            match(input,12,FOLLOW_12_in_ack_resume_session1639); 

            		camiContent = new AckResumeSession(CAMI_STRING13.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:416:1: ask_and_get_model : ( ask_for_a_model | model_definition | change_date );
    public final void ask_and_get_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:417:2: ( ask_for_a_model | model_definition | change_date )
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
                    new NoViableAltException("416:1: ask_and_get_model : ( ask_for_a_model | model_definition | change_date );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:418:4: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_ask_and_get_model1658);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:419:4: model_definition
                    {
                    pushFollow(FOLLOW_model_definition_in_ask_and_get_model1663);
                    model_definition();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:420:4: change_date
                    {
                    pushFollow(FOLLOW_change_date_in_ask_and_get_model1668);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:423:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:424:2: ( ask_simple | ask_hierarchic )
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
                    new NoViableAltException("423:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:425:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model1680);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:425:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model1684);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:428:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:429:2: ( 'DF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:430:2: 'DF()'
            {
            match(input,60,FOLLOW_60_in_ask_simple1696); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:433:1: ask_hierarchic : 'DF(-2,' number ',' number ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:434:2: ( 'DF(-2,' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:435:2: 'DF(-2,' number ',' number ')'
            {
            match(input,61,FOLLOW_61_in_ask_hierarchic1708); 
            pushFollow(FOLLOW_number_in_ask_hierarchic1710);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ask_hierarchic1712); 
            pushFollow(FOLLOW_number_in_ask_hierarchic1714);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ask_hierarchic1716); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:440:1: change_date : 'MS(' number ')' ;
    public final void change_date() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:441:2: ( 'MS(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:442:2: 'MS(' number ')'
            {
            match(input,62,FOLLOW_62_in_change_date1730); 
            pushFollow(FOLLOW_number_in_change_date1732);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_change_date1734); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:447:1: service_menu_reception : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final void service_menu_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:448:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:449:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,63,FOLLOW_63_in_service_menu_reception1748); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception1751);
            menu_name();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:451:2: ( question_add )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==66) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:451:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception1754);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            match(input,64,FOLLOW_64_in_service_menu_reception1758); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:455:1: menu_name : 'CQ(' name= CAMI_STRING ',' number ',' number ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:456:2: ( 'CQ(' name= CAMI_STRING ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:457:2: 'CQ(' name= CAMI_STRING ',' number ',' number ')'
            {
            match(input,65,FOLLOW_65_in_menu_name1770); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name1774); 
            match(input,11,FOLLOW_11_in_menu_name1776); 
            pushFollow(FOLLOW_number_in_menu_name1778);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_menu_name1780); 
            pushFollow(FOLLOW_number_in_menu_name1782);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_menu_name1784); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:460:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' ;
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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:461:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:462:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')'
            {
            match(input,66,FOLLOW_66_in_question_add1796); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1800); 
            match(input,11,FOLLOW_11_in_question_add1802); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1806); 
            match(input,11,FOLLOW_11_in_question_add1808); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:463:16: (question_type= number )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:463:16: question_type= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1815);
                    question_type=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1818); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:463:46: (question_behavior= number )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:463:46: question_behavior= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1822);
                    question_behavior=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1825); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:464:11: (set_item= number )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:464:11: set_item= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1832);
                    set_item=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1835); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:464:33: (historic= number )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==NUMBER) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:464:33: historic= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1840);
                    historic=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1843); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:464:61: (stop_authorized= number )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==NUMBER) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:464:61: stop_authorized= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1847);
                    stop_authorized=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1850); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:465:18: (ouput_formalism= CAMI_STRING )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==CAMI_STRING) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:465:18: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1857); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1860); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:465:42: (active= number )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==NUMBER) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:465:42: active= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1864);
                    active=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add1867); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:468:1: service_menu_modification : enable_main_question ( question_state )* end_menu_transmission ;
    public final void service_menu_modification() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:469:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:470:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification1879);
            enable_main_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:471:2: ( question_state )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==44) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:471:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification1882);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification1886);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:475:1: enable_main_question : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final void enable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:476:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:477:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,67,FOLLOW_67_in_enable_main_question1898); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question1902); 
            match(input,12,FOLLOW_12_in_enable_main_question1904); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:480:1: disable_main_question : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final void disable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:481:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:482:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,68,FOLLOW_68_in_disable_main_question1916); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question1920); 
            match(input,12,FOLLOW_12_in_disable_main_question1922); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:485:1: end_menu_transmission : 'QQ(' number ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:486:2: ( 'QQ(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:487:2: 'QQ(' number ')'
            {
            match(input,69,FOLLOW_69_in_end_menu_transmission1934); 
            pushFollow(FOLLOW_number_in_end_menu_transmission1936);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_end_menu_transmission1938); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:490:1: help_question : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final void help_question() throws RecognitionException {
        Token question_name=null;
        Token help_message=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:491:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:492:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,70,FOLLOW_70_in_help_question1950); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1954); 
            match(input,11,FOLLOW_11_in_help_question1956); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question1960); 
            match(input,12,FOLLOW_12_in_help_question1962); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:499:1: number returns [int value] : NUMBER ;
    public final int number() throws RecognitionException {
        int value = 0;

        Token NUMBER14=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:501:2: ( NUMBER )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/camiParser/Cami.g:502:2: NUMBER
            {
            NUMBER14=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_number1983); 
            value = Integer.parseInt(NUMBER14.getText());

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
    public static final BitSet FOLLOW_number_in_special_message633 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message635 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message639 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_communication_in_open_communication667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_open_communication678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_open_communication690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_check_version715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_check_version725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_communication747 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication749 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ack_open_connection772 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection776 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection778 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection782 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_normal799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_close_connection_panic817 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic819 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic821 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_close_connection_panic823 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_interlocutor_table842 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table846 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_38_in_interlocutor_table851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body_table864 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table868 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table870 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table874 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table876 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_body_table878 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table880 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_body_table884 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception899 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_result_reception919 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_reply_to_question_in_result_reception922 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception929 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception933 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception937 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_result_in_result_reception941 = new BitSet(new long[]{0x0000340180000000L});
    public static final BitSet FOLLOW_42_in_result_reception947 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result_reception949 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_reply_to_question966 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question970 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question972 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question976 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question978 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_reply_to_question980 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_reply_to_question982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_question_state999 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1003 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1005 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1009 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1011 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_question_state1015 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1017 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1021 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_result1045 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1049 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1051 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result1055 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result1057 = new BitSet(new long[]{0x003FA0000001E400L});
    public static final BitSet FOLLOW_result_body_in_result1060 = new BitSet(new long[]{0x003FE0000001E400L});
    public static final BitSet FOLLOW_46_in_result1064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_textual_result1152 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1154 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_attribute_change1183 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_change1187 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1193 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1195 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1199 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_designation1228 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_designation1232 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_outline1261 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_outline1265 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_attribute_outline1294 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1298 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1300 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1304 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1306 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_attribute_outline1310 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1313 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1317 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1348 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1352 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1354 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1358 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1370 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1374 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1376 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1380 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1382 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1386 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1398 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1402 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1404 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1408 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1410 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1414 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1416 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1420 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1432 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1436 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1438 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1442 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1444 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1448 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1460 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1464 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1466 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1470 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1472 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1476 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1478 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1480 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1482 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1486 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_deletion1516 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1520 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1534 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1538 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1540 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1544 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ack_open_session1573 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1575 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1577 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ack_open_session1580 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ack_open_session1583 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ack_close_current_session1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_suspend_current_session1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ack_resume_session1635 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_session1637 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_session1639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_ask_and_get_model1658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_model_definition_in_ask_and_get_model1663 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_change_date_in_ask_and_get_model1668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model1684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ask_simple1696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ask_hierarchic1708 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic1710 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic1712 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic1714 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic1716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_change_date1730 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_change_date1732 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_change_date1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_service_menu_reception1748 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception1751 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000005L});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception1754 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000005L});
    public static final BitSet FOLLOW_64_in_service_menu_reception1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_menu_name1770 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name1774 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1776 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name1778 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1780 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name1782 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_question_add1796 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1800 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1802 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1806 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1808 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1815 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1818 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1822 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1825 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1832 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1835 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1840 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1843 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1847 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1850 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1857 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1860 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_question_add1864 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add1867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification1879 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification1882 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification1886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_enable_main_question1898 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question1902 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question1904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_disable_main_question1916 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question1920 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question1922 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_end_menu_transmission1934 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_end_menu_transmission1936 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission1938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_help_question1950 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1954 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question1956 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question1960 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question1962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_number1983 = new BitSet(new long[]{0x0000000000000002L});

}