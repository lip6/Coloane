// $ANTLR 3.0.1 /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g 2007-09-04 10:12:04

package fr.lip6.move.coloane.api.cami.input.parser;

import java.util.Vector;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import fr.lip6.move.coloane.api.cami.input.connection.AckOpenCommunication;
import fr.lip6.move.coloane.api.cami.input.connection.AckOpenConnection;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionNormal;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic;
import fr.lip6.move.coloane.api.cami.input.messages.IMessage;
import fr.lip6.move.coloane.api.cami.input.messages.SpecialMessages;
import fr.lip6.move.coloane.api.cami.input.messages.TraceMessage;
import fr.lip6.move.coloane.api.cami.input.messages.WarningMessage;
import fr.lip6.move.coloane.api.cami.input.results.AttributeChange;
import fr.lip6.move.coloane.api.cami.input.results.AttributeOutline;
import fr.lip6.move.coloane.api.cami.input.results.CreateArc;
import fr.lip6.move.coloane.api.cami.input.results.CreateBox;
import fr.lip6.move.coloane.api.cami.input.results.CreateMonolineAttribute;
import fr.lip6.move.coloane.api.cami.input.results.CreateMultilineAttribute;
import fr.lip6.move.coloane.api.cami.input.results.CreateNode;
import fr.lip6.move.coloane.api.cami.input.results.IResult;
import fr.lip6.move.coloane.api.cami.input.results.MultipleObjectDeletion;
import fr.lip6.move.coloane.api.cami.input.results.ObjectDeletion;
import fr.lip6.move.coloane.api.cami.input.results.ObjectDesignation;
import fr.lip6.move.coloane.api.cami.input.results.ObjectOutline;
import fr.lip6.move.coloane.api.cami.input.results.QuestionAnswer;
import fr.lip6.move.coloane.api.cami.input.results.QuestionState;
import fr.lip6.move.coloane.api.cami.input.results.ResultSet;
import fr.lip6.move.coloane.api.cami.input.results.Results;
import fr.lip6.move.coloane.api.cami.input.results.TextualResult;
import fr.lip6.move.coloane.api.cami.input.session.AckCloseCurrentSession;
import fr.lip6.move.coloane.api.cami.input.session.AckOpenSession;
import fr.lip6.move.coloane.api.cami.input.session.AckResumeSession;
import fr.lip6.move.coloane.api.cami.input.session.AckSuspendCurrentSession;
import fr.lip6.move.coloane.api.session.states.MessageFormatFailure;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationCommunicationAck;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationFailure;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationVersionAck;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'DB()'", "'FB()'", "'CN('", "','", "')'", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('", "'TR('", "'WN('", "'MO('", "'SC('", "'OC('", "'FC()'", "'KO(1,'", "'TL()'", "'FL()'", "'VI('", "'DR()'", "'FR('", "'RQ('", "'TQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'RO('", "'ME('", "'MT('", "'SU('", "'SI('", "'OS('", "'TD()'", "'FA()'", "'FS()'", "'SS()'", "'RS('", "'DF()'", "'DF(-2,'", "'MS('", "'DQ()'", "'FQ()'", "'CQ('", "'AQ('", "'VQ('", "'EQ('", "'QQ('", "'HQ('"
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
    public String getGrammarFileName() { return "/Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g"; }



    // $ANTLR start model_definition
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:55:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:56:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:57:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,8,FOLLOW_8_in_model_definition35); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:58:2: ( syntactic | aestetic )
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
                    new NoViableAltException("58:2: ( syntactic | aestetic )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:58:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition40);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:58:16: aestetic
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:62:1: syntactic : ( node | box | arc | textual_attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:63:2: ( node | box | arc | textual_attribute )
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
                    new NoViableAltException("62:1: syntactic : ( node | box | arc | textual_attribute );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:64:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic61);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:64:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic65);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:64:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic69);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:64:21: textual_attribute
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:67:1: node : 'CN(' CAMI_STRING ',' number ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:67:6: ( 'CN(' CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:68:2: 'CN(' CAMI_STRING ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:71:1: box : 'CB(' CAMI_STRING ',' number ',' number ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:71:5: ( 'CB(' CAMI_STRING ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:72:2: 'CB(' CAMI_STRING ',' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:75:1: arc : 'CA(' CAMI_STRING ',' number ',' number ',' number ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:75:5: ( 'CA(' CAMI_STRING ',' number ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:76:2: 'CA(' CAMI_STRING ',' number ',' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:79:1: textual_attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );
    public final void textual_attribute() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:80:2: ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' )
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
                    new NoViableAltException("79:1: textual_attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:81:4: 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:82:4: 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:86:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:87:2: ( object_position | text_position | intermediary_point )
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
                    new NoViableAltException("86:1: aestetic : ( object_position | text_position | intermediary_point );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:88:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic209);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:88:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic213);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:88:36: intermediary_point
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:91:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );
    public final void object_position() throws RecognitionException {
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;

        int left = 0;

        int right = 0;

        int top = 0;

        int bottom = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:92:2: ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' )
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
                    new NoViableAltException("91:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:93:4: 'PO(' id= number ',' h_distance= number ',' v_distance= number ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:94:4: 'pO(' id= number ',' h_distance= number ',' v_distance= number ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:95:4: 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:98:1: text_position : 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' ;
    public final void text_position() throws RecognitionException {
        Token name_attr=null;
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:99:2: ( 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:100:2: 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:103:1: intermediary_point : 'PI(' number ',' number ',' number ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:104:2: ( 'PI(' number ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:105:2: 'PI(' number ',' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:112:1: dialog_definition : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final void dialog_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:113:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:114:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,22,FOLLOW_22_in_dialog_definition384); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition387);
            dialog_creation();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:116:2: ( next_dialog )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:116:2: next_dialog
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:120:1: dialog_creation : 'CE(' number ',' number ',' number ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' number ',' number ',' ( CAMI_STRING )? ')' ;
    public final void dialog_creation() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:121:2: ( 'CE(' number ',' number ',' number ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' number ',' number ',' ( CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:122:2: 'CE(' number ',' number ',' number ',' CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' number ',' number ',' ( CAMI_STRING )? ')'
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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:123:25: ( CAMI_STRING )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==CAMI_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:123:25: CAMI_STRING
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:126:1: next_dialog : 'DS(' number ',' CAMI_STRING ')' ;
    public final void next_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:127:2: ( 'DS(' number ',' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:128:2: 'DS(' number ',' CAMI_STRING ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:131:1: display_dialog : 'AD(' number ')' ;
    public final void display_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:132:2: ( 'AD(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:133:2: 'AD(' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:136:1: hide_dialog : 'HD(' number ')' ;
    public final void hide_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:137:2: ( 'HD(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:138:2: 'HD(' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:141:1: destroy_dialog : 'DG(' number ')' ;
    public final void destroy_dialog() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:142:2: ( 'DG(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:143:2: 'DG(' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:146:1: interactive_response : 'MI(' number ',' number ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:147:2: ( 'MI(' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:148:2: 'MI(' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:158:1: message_to_user returns [IMessage camiContent] : ( trace_message | warning_message | special_message );
    public final IMessage message_to_user() throws RecognitionException {
        IMessage camiContent = null;

        TraceMessage trace_message1 = null;

        WarningMessage warning_message2 = null;

        SpecialMessages special_message3 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:160:2: ( trace_message | warning_message | special_message )
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
                    new NoViableAltException("158:1: message_to_user returns [IMessage camiContent] : ( trace_message | warning_message | special_message );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:161:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user562);
                    trace_message1=trace_message();
                    _fsp--;


                    	  	camiContent = trace_message1;
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:165:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user573);
                    warning_message2=warning_message();
                    _fsp--;


                    	  	camiContent = warning_message2;
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:170:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user585);
                    special_message3=special_message();
                    _fsp--;


                    	  	camiContent = special_message3;
                    	  

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
        return camiContent;
    }
    // $ANTLR end message_to_user


    // $ANTLR start trace_message
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:176:1: trace_message returns [TraceMessage message] : 'TR(' CAMI_STRING ')' ;
    public final TraceMessage trace_message() throws RecognitionException {
        TraceMessage message = null;

        Token CAMI_STRING4=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:178:2: ( 'TR(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:179:2: 'TR(' CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_trace_message607); 
            CAMI_STRING4=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message609); 
            match(input,12,FOLLOW_12_in_trace_message611); 

            		message = new TraceMessage(CAMI_STRING4.getText());
            	

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
    // $ANTLR end trace_message


    // $ANTLR start warning_message
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:185:1: warning_message returns [WarningMessage message] : 'WN(' CAMI_STRING ')' ;
    public final WarningMessage warning_message() throws RecognitionException {
        WarningMessage message = null;

        Token CAMI_STRING5=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:187:2: ( 'WN(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:188:2: 'WN(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_warning_message631); 
            CAMI_STRING5=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message633); 
            match(input,12,FOLLOW_12_in_warning_message635); 

            		message = new WarningMessage(CAMI_STRING5.getText());
            	

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
    // $ANTLR end warning_message


    // $ANTLR start special_message
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:194:1: special_message returns [SpecialMessages message] : 'MO(' message_type= number ',' message_content= CAMI_STRING ')' ;
    public final SpecialMessages special_message() throws RecognitionException {
        SpecialMessages message = null;

        Token message_content=null;
        int message_type = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:196:2: ( 'MO(' message_type= number ',' message_content= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:197:2: 'MO(' message_type= number ',' message_content= CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message656); 
            pushFollow(FOLLOW_number_in_special_message660);
            message_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_special_message662); 
            message_content=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message666); 
            match(input,12,FOLLOW_12_in_special_message668); 

            		message = new SpecialMessages(SpecialMessages.SpecialMessageType(message_type),message_content.getText());
            	

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
    // $ANTLR end special_message


    // $ANTLR start open_communication
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:206:1: open_communication returns [AuthenticationCommunicationAck message] : ( ack_open_communication | close_connection_panic | special_message );
    public final AuthenticationCommunicationAck open_communication() throws RecognitionException {
        AuthenticationCommunicationAck message = null;

        AckOpenCommunication ack_open_communication6 = null;

        CloseConnectionPanic close_connection_panic7 = null;

        SpecialMessages special_message8 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:208:2: ( ack_open_communication | close_connection_panic | special_message )
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
                    new NoViableAltException("206:1: open_communication returns [AuthenticationCommunicationAck message] : ( ack_open_communication | close_connection_panic | special_message );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:209:4: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_open_communication694);
                    ack_open_communication6=ack_open_communication();
                    _fsp--;


                    	  	message = new AuthenticationCommunicationAck(ack_open_communication6);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:213:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_open_communication705);
                    close_connection_panic7=close_connection_panic();
                    _fsp--;


                    	  	if( true ) // to avoid an error in the generated code
                    		  	throw new AuthenticationFailure(close_connection_panic7);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:218:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_open_communication717);
                    special_message8=special_message();
                    _fsp--;


                    	  	if(true) // to avoid an error in the generated code
                    	  		throw new MessageFormatFailure(special_message8);
                    	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:226:1: check_version returns [AuthenticationVersionAck message] : ( ack_open_connection | special_message );
    public final AuthenticationVersionAck check_version() throws RecognitionException {
        AuthenticationVersionAck message = null;

        AckOpenConnection ack_open_connection9 = null;

        SpecialMessages special_message10 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:228:2: ( ack_open_connection | special_message )
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
                    new NoViableAltException("226:1: check_version returns [AuthenticationVersionAck message] : ( ack_open_connection | special_message );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:229:4: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_check_version742);
                    ack_open_connection9=ack_open_connection();
                    _fsp--;


                    	  	message = new AuthenticationVersionAck(ack_open_connection9);  
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:233:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_check_version752);
                    special_message10=special_message();
                    _fsp--;


                    	  	if(true) // to avoid an error in the generated code
                    	  		throw new MessageFormatFailure(special_message10);
                    	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:240:1: ack_open_communication returns [AckOpenCommunication camiContent] : 'SC(' CAMI_STRING ')' ;
    public final AckOpenCommunication ack_open_communication() throws RecognitionException {
        AckOpenCommunication camiContent = null;

        Token CAMI_STRING11=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:242:2: ( 'SC(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:243:2: 'SC(' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_communication774); 
            CAMI_STRING11=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication776); 
            match(input,12,FOLLOW_12_in_ack_open_communication778); 

            		camiContent = new AckOpenCommunication(CAMI_STRING11.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:249:1: ack_open_connection returns [AckOpenConnection camiContent] : 'OC(' major= number ',' minor= number ')' ;
    public final AckOpenConnection ack_open_connection() throws RecognitionException {
        AckOpenConnection camiContent = null;

        int major = 0;

        int minor = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:251:2: ( 'OC(' major= number ',' minor= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:252:2: 'OC(' major= number ',' minor= number ')'
            {
            match(input,34,FOLLOW_34_in_ack_open_connection799); 
            pushFollow(FOLLOW_number_in_ack_open_connection803);
            major=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ack_open_connection805); 
            pushFollow(FOLLOW_number_in_ack_open_connection809);
            minor=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ack_open_connection811); 

            		camiContent = new AckOpenConnection(major,minor);
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:258:1: close_connection_normal returns [CloseConnectionNormal camiContent] : 'FC()' ;
    public final CloseConnectionNormal close_connection_normal() throws RecognitionException {
        CloseConnectionNormal camiContent = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:260:2: ( 'FC()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:261:2: 'FC()'
            {
            match(input,35,FOLLOW_35_in_close_connection_normal831); 

            		camiContent = new CloseConnectionNormal();
            	

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
    // $ANTLR end close_connection_normal


    // $ANTLR start close_connection_panic
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:267:1: close_connection_panic returns [CloseConnectionPanic camiContent] : 'KO(1,' message= CAMI_STRING ',' severity= number ')' ;
    public final CloseConnectionPanic close_connection_panic() throws RecognitionException {
        CloseConnectionPanic camiContent = null;

        Token message=null;
        int severity = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:269:2: ( 'KO(1,' message= CAMI_STRING ',' severity= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:270:2: 'KO(1,' message= CAMI_STRING ',' severity= number ')'
            {
            match(input,36,FOLLOW_36_in_close_connection_panic852); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic856); 
            match(input,11,FOLLOW_11_in_close_connection_panic858); 
            pushFollow(FOLLOW_number_in_close_connection_panic862);
            severity=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_close_connection_panic864); 

            		camiContent = new CloseConnectionPanic(message.getText(),
            							CloseConnectionPanic.Severity(severity));
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:279:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:280:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:281:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,37,FOLLOW_37_in_interlocutor_table881); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:282:2: ( body_table )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:282:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table885);
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

            match(input,38,FOLLOW_38_in_interlocutor_table890); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:286:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= number ',' new_model= number ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        int deprecated = 0;

        int new_model = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:287:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= number ',' new_model= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:288:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= number ',' new_model= number ')'
            {
            match(input,39,FOLLOW_39_in_body_table903); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table907); 
            match(input,11,FOLLOW_11_in_body_table909); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table913); 
            match(input,11,FOLLOW_11_in_body_table915); 
            pushFollow(FOLLOW_number_in_body_table919);
            deprecated=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_body_table921); 
            pushFollow(FOLLOW_number_in_body_table925);
            new_model=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_body_table927); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:293:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:294:2: ( question_state ask_hierarchic )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:295:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception940);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception943);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:299:1: result_reception returns [Results results] : 'DR()' reply_to_question ( question_state | special_message | warning_message | result )* 'FR(' answer_type= number ')' ;
    public final Results result_reception() throws RecognitionException {
        Results results = null;

        int answer_type = 0;

        QuestionAnswer reply_to_question12 = null;

        QuestionState question_state13 = null;

        SpecialMessages special_message14 = null;

        WarningMessage warning_message15 = null;

        ResultSet result16 = null;



        		Vector<QuestionState> questionStates = new Vector<QuestionState>();
        		Vector<IMessage> messages = new Vector<IMessage>();
        		Vector<ResultSet> resultSets = new Vector<ResultSet>();
        		QuestionAnswer questionAnswer = null;
        	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:308:2: ( 'DR()' reply_to_question ( question_state | special_message | warning_message | result )* 'FR(' answer_type= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:309:2: 'DR()' reply_to_question ( question_state | special_message | warning_message | result )* 'FR(' answer_type= number ')'
            {
            match(input,40,FOLLOW_40_in_result_reception967); 
            pushFollow(FOLLOW_reply_to_question_in_result_reception970);
            reply_to_question12=reply_to_question();
            _fsp--;


            		questionAnswer = reply_to_question12;
            	
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:314:2: ( question_state | special_message | warning_message | result )*
            loop12:
            do {
                int alt12=5;
                switch ( input.LA(1) ) {
                case 43:
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
                case 44:
                    {
                    alt12=4;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:315:4: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_result_reception982);
            	    question_state13=question_state();
            	    _fsp--;


            	    	  	questionStates.add(question_state13);
            	    	  

            	    }
            	    break;
            	case 2 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:319:4: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_result_reception992);
            	    special_message14=special_message();
            	    _fsp--;


            	    	  	messages.add(special_message14);
            	    	  

            	    }
            	    break;
            	case 3 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:323:4: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_result_reception1002);
            	    warning_message15=warning_message();
            	    _fsp--;


            	    	 	messages.add(warning_message15);
            	    	  

            	    }
            	    break;
            	case 4 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:327:4: result
            	    {
            	    pushFollow(FOLLOW_result_in_result_reception1012);
            	    result16=result();
            	    _fsp--;


            	    	  	resultSets.add(result16);
            	    	  

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_result_reception1024); 
            pushFollow(FOLLOW_number_in_result_reception1028);
            answer_type=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result_reception1030); 

            		results = new Results(	questionAnswer,
            					messages,
            					questionStates,
            					resultSets,
            					Results.ResultType(answer_type));
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:342:1: reply_to_question returns [QuestionAnswer questionAnswer] : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' deprecated= number ')' ;
    public final QuestionAnswer reply_to_question() throws RecognitionException {
        QuestionAnswer questionAnswer = null;

        Token service_name=null;
        Token question_name=null;
        int deprecated = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:344:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' deprecated= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:345:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' deprecated= number ')'
            {
            match(input,42,FOLLOW_42_in_reply_to_question1050); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question1054); 
            match(input,11,FOLLOW_11_in_reply_to_question1056); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question1060); 
            match(input,11,FOLLOW_11_in_reply_to_question1062); 
            pushFollow(FOLLOW_number_in_reply_to_question1066);
            deprecated=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_reply_to_question1068); 

            		questionAnswer = new QuestionAnswer(service_name.getText(),question_name.getText());
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return questionAnswer;
    }
    // $ANTLR end reply_to_question


    // $ANTLR start question_state
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:351:1: question_state returns [QuestionState questionState] : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' ;
    public final QuestionState question_state() throws RecognitionException {
        QuestionState questionState = null;

        Token service_name=null;
        Token question_name=null;
        Token mess=null;
        int state = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:353:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:354:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')'
            {
            match(input,43,FOLLOW_43_in_question_state1088); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1092); 
            match(input,11,FOLLOW_11_in_question_state1094); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1098); 
            match(input,11,FOLLOW_11_in_question_state1100); 
            pushFollow(FOLLOW_number_in_question_state1104);
            state=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_question_state1106); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:354:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:354:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1110); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state1113); 

            		String message = null;
            		if( mess != null ) 
            			message = mess.getText();
            		questionState = new QuestionState(service_name.getText(),question_name.getText(),state,message);
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:363:1: result returns [ResultSet res] : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' ;
    public final ResultSet result() throws RecognitionException {
        ResultSet res = null;

        Token ensemble_name=null;
        int ensemble_type = 0;

        IResult result_body17 = null;



        		Vector<IResult> results = new Vector<IResult>();
        	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:369:2: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:370:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()'
            {
            match(input,44,FOLLOW_44_in_result1141); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1145); 
            match(input,11,FOLLOW_11_in_result1147); 
            pushFollow(FOLLOW_number_in_result1151);
            ensemble_type=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result1153); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:371:2: ( result_body )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:372:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result1159);
            	    result_body17=result_body();
            	    _fsp--;


            	    		results.add(result_body17);
            	    	

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

            match(input,45,FOLLOW_45_in_result1169); 

            		res = new ResultSet(	ensemble_name.getText(),
            					ResultSet.ResultSetType(ensemble_type),
            					results );
            	

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
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:385:1: result_body returns [IResult res] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final IResult result_body() throws RecognitionException {
        IResult res = null;

        ResultSet result18 = null;

        IResult textual_result19 = null;

        IResult attribute_change20 = null;

        IResult object_designation21 = null;

        IResult object_outline22 = null;

        IResult attribute_outline23 = null;

        IResult object_creation24 = null;

        IResult object_deletion25 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:387:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
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
                    new NoViableAltException("385:1: result_body returns [IResult res] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:388:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1195);
                    result18=result();
                    _fsp--;


                     		res = result18;
                     	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:392:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1207);
                    textual_result19=textual_result();
                    _fsp--;


                     		res = textual_result19;
                     	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:396:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1219);
                    attribute_change20=attribute_change();
                    _fsp--;


                     		res = attribute_change20;
                     	  

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:400:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1231);
                    object_designation21=object_designation();
                    _fsp--;


                     		res = object_designation21;
                     	  

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:404:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1243);
                    object_outline22=object_outline();
                    _fsp--;


                     		res = object_outline22;
                     	  

                    }
                    break;
                case 6 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:408:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1255);
                    attribute_outline23=attribute_outline();
                    _fsp--;


                     		res = attribute_outline23;
                     	  

                    }
                    break;
                case 7 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:412:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1267);
                    object_creation24=object_creation();
                    _fsp--;


                     		res = object_creation24;
                     	  

                    }
                    break;
                case 8 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:416:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1279);
                    object_deletion25=object_deletion();
                    _fsp--;


                     		res = object_deletion25;
                     	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:422:2: textual_result returns [IResult res] : 'RT(' CAMI_STRING ')' ;
    public final IResult textual_result() throws RecognitionException {
        IResult res = null;

        Token CAMI_STRING26=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:424:3: ( 'RT(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:425:3: 'RT(' CAMI_STRING ')'
            {
            match(input,46,FOLLOW_46_in_textual_result1308); 
            CAMI_STRING26=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1310); 
            match(input,12,FOLLOW_12_in_textual_result1312); 

             		res = new TextualResult(CAMI_STRING26.getText());
             	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:431:2: attribute_change returns [IResult res] : 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final IResult attribute_change() throws RecognitionException {
        IResult res = null;

        Token attr_name=null;
        Token new_value=null;
        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:433:3: ( 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:434:3: 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,47,FOLLOW_47_in_attribute_change1339); 
            pushFollow(FOLLOW_number_in_attribute_change1343);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_change1345); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1349); 
            match(input,11,FOLLOW_11_in_attribute_change1351); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1355); 
            match(input,12,FOLLOW_12_in_attribute_change1357); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:440:2: object_designation returns [IResult res] : 'RO(' id= number ')' ;
    public final IResult object_designation() throws RecognitionException {
        IResult res = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:442:3: ( 'RO(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:443:3: 'RO(' id= number ')'
            {
            match(input,48,FOLLOW_48_in_object_designation1384); 
            pushFollow(FOLLOW_number_in_object_designation1388);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_designation1390); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:449:2: object_outline returns [IResult res] : 'ME(' id= number ')' ;
    public final IResult object_outline() throws RecognitionException {
        IResult res = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:451:3: ( 'ME(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:452:3: 'ME(' id= number ')'
            {
            match(input,49,FOLLOW_49_in_object_outline1417); 
            pushFollow(FOLLOW_number_in_object_outline1421);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_outline1423); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:458:2: attribute_outline returns [IResult res] : 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' ;
    public final IResult attribute_outline() throws RecognitionException {
        IResult res = null;

        Token attr_name=null;
        int id = 0;

        int begin = 0;

        int end = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:460:3: ( 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:461:3: 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')'
            {
            match(input,50,FOLLOW_50_in_attribute_outline1450); 
            pushFollow(FOLLOW_number_in_attribute_outline1454);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_outline1456); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1460); 
            match(input,11,FOLLOW_11_in_attribute_outline1462); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:461:54: (begin= number )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==NUMBER) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:461:54: begin= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1466);
                    begin=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1469); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:461:70: (end= number )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NUMBER) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:461:70: end= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1473);
                    end=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1476); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:467:2: object_creation returns [IResult res] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')' );
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

        int deprecated = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:469:3: ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')' )
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
                    new NoViableAltException("467:2: object_creation returns [IResult res] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')' );", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:470:4: 'CN(' node_box_type= CAMI_STRING ',' id= number ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1504); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1508); 
                    match(input,11,FOLLOW_11_in_object_creation1510); 
                    pushFollow(FOLLOW_number_in_object_creation1514);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1516); 

                    	  	res = new CreateNode(node_box_type.getText(),id);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:474:4: 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1526); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1530); 
                    match(input,11,FOLLOW_11_in_object_creation1532); 
                    pushFollow(FOLLOW_number_in_object_creation1536);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1538); 
                    pushFollow(FOLLOW_number_in_object_creation1542);
                    page_id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1544); 

                    	  	res = new CreateBox(node_box_type.getText(),id,page_id);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:478:4: 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1554); 
                    arc_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1558); 
                    match(input,11,FOLLOW_11_in_object_creation1560); 
                    pushFollow(FOLLOW_number_in_object_creation1564);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1566); 
                    pushFollow(FOLLOW_number_in_object_creation1570);
                    start_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1572); 
                    pushFollow(FOLLOW_number_in_object_creation1576);
                    end_node=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1578); 

                    	  	res = new CreateArc(arc_type.getText(),id,start_node,end_node);
                    	  

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:482:4: 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1588); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1592); 
                    match(input,11,FOLLOW_11_in_object_creation1594); 
                    pushFollow(FOLLOW_number_in_object_creation1598);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1600); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1604); 
                    match(input,12,FOLLOW_12_in_object_creation1606); 

                    	  	res = new CreateMonolineAttribute(attribute_name.getText(),associated_node,value.getText());
                    	  

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:486:4: 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1616); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1620); 
                    match(input,11,FOLLOW_11_in_object_creation1622); 
                    pushFollow(FOLLOW_number_in_object_creation1626);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1628); 
                    pushFollow(FOLLOW_number_in_object_creation1632);
                    line_number=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1634); 
                    pushFollow(FOLLOW_number_in_object_creation1638);
                    deprecated=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1640); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1644); 
                    match(input,12,FOLLOW_12_in_object_creation1646); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:492:1: object_deletion returns [IResult res] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );
    public final IResult object_deletion() throws RecognitionException {
        IResult res = null;

        int id = 0;

        int page_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:494:2: ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' )
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
                    new NoViableAltException("492:1: object_deletion returns [IResult res] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:495:5: 'SU(' id= number ')'
                    {
                    match(input,51,FOLLOW_51_in_object_deletion1674); 
                    pushFollow(FOLLOW_number_in_object_deletion1678);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1680); 

                     	  	res = new ObjectDeletion(id);
                     	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:499:5: 'SI(' page_id= number ',' id= number ')'
                    {
                    match(input,52,FOLLOW_52_in_object_deletion1692); 
                    pushFollow(FOLLOW_number_in_object_deletion1696);
                    page_id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_deletion1698); 
                    pushFollow(FOLLOW_number_in_object_deletion1702);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1704); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:507:1: ack_open_session returns [AckOpenSession camiContent] : 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final AckOpenSession ack_open_session() throws RecognitionException {
        AckOpenSession camiContent = null;

        Token session_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:509:2: ( 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:510:2: 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,53,FOLLOW_53_in_ack_open_session1731); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1735); 
            match(input,12,FOLLOW_12_in_ack_open_session1737); 
            match(input,54,FOLLOW_54_in_ack_open_session1740); 
            match(input,55,FOLLOW_55_in_ack_open_session1743); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1746);
            interlocutor_table();
            _fsp--;


            		camiContent = new AckOpenSession(session_name.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:519:1: ack_close_current_session returns [AckCloseCurrentSession camiContent] : 'FS()' ;
    public final AckCloseCurrentSession ack_close_current_session() throws RecognitionException {
        AckCloseCurrentSession camiContent = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:521:2: ( 'FS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:522:2: 'FS()'
            {
            match(input,56,FOLLOW_56_in_ack_close_current_session1767); 

            		camiContent = new AckCloseCurrentSession();
            	

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
    // $ANTLR end ack_close_current_session


    // $ANTLR start ack_suspend_current_session
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:528:1: ack_suspend_current_session returns [AckSuspendCurrentSession camiContent] : 'SS()' ;
    public final AckSuspendCurrentSession ack_suspend_current_session() throws RecognitionException {
        AckSuspendCurrentSession camiContent = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:530:2: ( 'SS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:531:2: 'SS()'
            {
            match(input,57,FOLLOW_57_in_ack_suspend_current_session1789); 

            		camiContent = new AckSuspendCurrentSession();
            	

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
    // $ANTLR end ack_suspend_current_session


    // $ANTLR start ack_resume_session
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:537:1: ack_resume_session returns [AckResumeSession camiContent] : 'RS(' session_name= CAMI_STRING ')' ;
    public final AckResumeSession ack_resume_session() throws RecognitionException {
        AckResumeSession camiContent = null;

        Token session_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:539:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:540:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,58,FOLLOW_58_in_ack_resume_session1809); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_session1813); 
            match(input,12,FOLLOW_12_in_ack_resume_session1815); 

            		camiContent = new AckResumeSession(session_name.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:548:1: ask_and_get_model : ( ask_for_a_model | model_definition | change_date );
    public final void ask_and_get_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:549:2: ( ask_for_a_model | model_definition | change_date )
            int alt20=3;
            switch ( input.LA(1) ) {
            case 59:
            case 60:
                {
                alt20=1;
                }
                break;
            case 8:
                {
                alt20=2;
                }
                break;
            case 61:
                {
                alt20=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("548:1: ask_and_get_model : ( ask_for_a_model | model_definition | change_date );", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:550:4: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_ask_and_get_model1834);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:551:4: model_definition
                    {
                    pushFollow(FOLLOW_model_definition_in_ask_and_get_model1839);
                    model_definition();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:552:4: change_date
                    {
                    pushFollow(FOLLOW_change_date_in_ask_and_get_model1844);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:555:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:556:2: ( ask_simple | ask_hierarchic )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==59) ) {
                alt21=1;
            }
            else if ( (LA21_0==60) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("555:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:557:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model1856);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:557:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model1860);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:560:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:561:2: ( 'DF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:562:2: 'DF()'
            {
            match(input,59,FOLLOW_59_in_ask_simple1872); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:565:1: ask_hierarchic : 'DF(-2,' number ',' number ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:566:2: ( 'DF(-2,' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:567:2: 'DF(-2,' number ',' number ')'
            {
            match(input,60,FOLLOW_60_in_ask_hierarchic1884); 
            pushFollow(FOLLOW_number_in_ask_hierarchic1886);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ask_hierarchic1888); 
            pushFollow(FOLLOW_number_in_ask_hierarchic1890);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ask_hierarchic1892); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:572:1: change_date : 'MS(' number ')' ;
    public final void change_date() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:573:2: ( 'MS(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:574:2: 'MS(' number ')'
            {
            match(input,61,FOLLOW_61_in_change_date1906); 
            pushFollow(FOLLOW_number_in_change_date1908);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_change_date1910); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:579:1: service_menu_reception : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final void service_menu_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:580:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:581:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,62,FOLLOW_62_in_service_menu_reception1924); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception1927);
            menu_name();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:583:2: ( question_add )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==65) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:583:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception1930);
            	    question_add();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            match(input,63,FOLLOW_63_in_service_menu_reception1934); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:587:1: menu_name : 'CQ(' name= CAMI_STRING ',' number ',' number ')' ;
    public final void menu_name() throws RecognitionException {
        Token name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:588:2: ( 'CQ(' name= CAMI_STRING ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:589:2: 'CQ(' name= CAMI_STRING ',' number ',' number ')'
            {
            match(input,64,FOLLOW_64_in_menu_name1946); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name1950); 
            match(input,11,FOLLOW_11_in_menu_name1952); 
            pushFollow(FOLLOW_number_in_menu_name1954);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_menu_name1956); 
            pushFollow(FOLLOW_number_in_menu_name1958);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_menu_name1960); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:592:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' ;
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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:593:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:594:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')'
            {
            match(input,65,FOLLOW_65_in_question_add1972); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1976); 
            match(input,11,FOLLOW_11_in_question_add1978); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1982); 
            match(input,11,FOLLOW_11_in_question_add1984); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:595:16: (question_type= number )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NUMBER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:595:16: question_type= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1991);
                    question_type=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1994); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:595:46: (question_behavior= number )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:595:46: question_behavior= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1998);
                    question_behavior=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add2001); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:11: (set_item= number )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NUMBER) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:11: set_item= number
                    {
                    pushFollow(FOLLOW_number_in_question_add2008);
                    set_item=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add2011); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:33: (historic= number )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NUMBER) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:33: historic= number
                    {
                    pushFollow(FOLLOW_number_in_question_add2016);
                    historic=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add2019); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:61: (stop_authorized= number )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==NUMBER) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:61: stop_authorized= number
                    {
                    pushFollow(FOLLOW_number_in_question_add2023);
                    stop_authorized=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add2026); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:597:18: (ouput_formalism= CAMI_STRING )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==CAMI_STRING) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:597:18: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add2033); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add2036); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:597:42: (active= number )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==NUMBER) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:597:42: active= number
                    {
                    pushFollow(FOLLOW_number_in_question_add2040);
                    active=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add2043); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:600:1: service_menu_modification : enable_main_question ( question_state )* end_menu_transmission ;
    public final void service_menu_modification() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:601:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:602:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification2055);
            enable_main_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:603:2: ( question_state )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==43) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:603:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification2058);
            	    question_state();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification2062);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:607:1: enable_main_question : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final void enable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:608:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:609:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,66,FOLLOW_66_in_enable_main_question2074); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question2078); 
            match(input,12,FOLLOW_12_in_enable_main_question2080); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:612:1: disable_main_question : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final void disable_main_question() throws RecognitionException {
        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:613:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:614:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,67,FOLLOW_67_in_disable_main_question2092); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question2096); 
            match(input,12,FOLLOW_12_in_disable_main_question2098); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:617:1: end_menu_transmission : 'QQ(' number ')' ;
    public final void end_menu_transmission() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:618:2: ( 'QQ(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:619:2: 'QQ(' number ')'
            {
            match(input,68,FOLLOW_68_in_end_menu_transmission2110); 
            pushFollow(FOLLOW_number_in_end_menu_transmission2112);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_end_menu_transmission2114); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:622:1: help_question : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final void help_question() throws RecognitionException {
        Token question_name=null;
        Token help_message=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:623:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:624:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,69,FOLLOW_69_in_help_question2126); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question2130); 
            match(input,11,FOLLOW_11_in_help_question2132); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question2136); 
            match(input,12,FOLLOW_12_in_help_question2138); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:631:1: number returns [int value] : NUMBER ;
    public final int number() throws RecognitionException {
        int value = 0;

        Token NUMBER27=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:633:2: ( NUMBER )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:634:2: NUMBER
            {
            NUMBER27=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_number2159); 
            value = Integer.parseInt(NUMBER27.getText());

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
    public static final BitSet FOLLOW_trace_message_in_message_to_user562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_trace_message607 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message609 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_trace_message611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_warning_message631 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message633 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_warning_message635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_special_message656 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_special_message660 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message662 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message666 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_communication_in_open_communication694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_open_communication705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_open_communication717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_check_version742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_check_version752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_communication774 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication776 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ack_open_connection799 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection803 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection805 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection809 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_normal831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_close_connection_panic852 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic856 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic858 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_close_connection_panic862 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_interlocutor_table881 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table885 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_38_in_interlocutor_table890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body_table903 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table907 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table909 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table913 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table915 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_body_table919 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table921 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_body_table925 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception940 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_result_reception967 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_reply_to_question_in_result_reception970 = new BitSet(new long[]{0x00001A0180000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception982 = new BitSet(new long[]{0x00001A0180000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception992 = new BitSet(new long[]{0x00001A0180000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception1002 = new BitSet(new long[]{0x00001A0180000000L});
    public static final BitSet FOLLOW_result_in_result_reception1012 = new BitSet(new long[]{0x00001A0180000000L});
    public static final BitSet FOLLOW_41_in_result_reception1024 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result_reception1028 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_reply_to_question1050 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question1054 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question1056 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question1060 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question1062 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_reply_to_question1066 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_reply_to_question1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_question_state1088 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1092 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1094 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1098 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1100 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_question_state1104 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1106 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1110 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_result1141 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1145 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1147 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result1151 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result1153 = new BitSet(new long[]{0x001FD0000001E400L});
    public static final BitSet FOLLOW_result_body_in_result1159 = new BitSet(new long[]{0x001FF0000001E400L});
    public static final BitSet FOLLOW_45_in_result1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_textual_result1308 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1310 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_attribute_change1339 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_change1343 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1345 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1349 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1351 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1355 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_object_designation1384 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_designation1388 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_outline1417 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_outline1421 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_attribute_outline1450 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1454 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1456 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1460 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1462 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_attribute_outline1466 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1469 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1473 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1504 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1508 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1510 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1514 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1526 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1530 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1532 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1536 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1538 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1542 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1554 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1558 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1560 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1564 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1566 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1570 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1572 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1576 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1588 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1592 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1594 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1598 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1600 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1604 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1616 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1620 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1622 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1626 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1628 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1632 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1634 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1638 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1640 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1644 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_object_deletion1674 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1678 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_deletion1692 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1696 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1698 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1702 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ack_open_session1731 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1735 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1737 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_54_in_ack_open_session1740 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ack_open_session1743 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ack_close_current_session1767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ack_suspend_current_session1789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_resume_session1809 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_session1813 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_session1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_ask_and_get_model1834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_model_definition_in_ask_and_get_model1839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_change_date_in_ask_and_get_model1844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model1860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ask_simple1872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ask_hierarchic1884 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic1886 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic1888 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic1890 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic1892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_change_date1906 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_change_date1908 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_change_date1910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_service_menu_reception1924 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception1927 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception1930 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_service_menu_reception1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_menu_name1946 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name1950 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1952 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name1954 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name1956 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name1958 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name1960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_question_add1972 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1976 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1978 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1982 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1984 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1991 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1994 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1998 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add2001 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add2008 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add2011 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add2016 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add2019 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add2023 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add2026 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add2033 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add2036 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_question_add2040 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add2043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification2055 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification2058 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification2062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_enable_main_question2074 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question2078 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question2080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_disable_main_question2092 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question2096 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_end_menu_transmission2110 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_end_menu_transmission2112 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission2114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_help_question2126 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question2130 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question2132 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question2136 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question2138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_number2159 = new BitSet(new long[]{0x0000000000000002L});

}