// $ANTLR 3.0.1 /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g 2007-09-04 17:41:15

package fr.lip6.move.coloane.api.cami.input.parser;

import java.util.Collection;
import java.util.Vector;	

import fr.lip6.move.coloane.api.cami.ICommand;
import fr.lip6.move.coloane.api.cami.input.connection.AckOpenCommunication;
import fr.lip6.move.coloane.api.cami.input.connection.AckOpenConnection;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionNormal;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic;
import fr.lip6.move.coloane.api.cami.input.dialog.DestroyDialog;
import fr.lip6.move.coloane.api.cami.input.dialog.DialogCreation;
import fr.lip6.move.coloane.api.cami.input.dialog.DialogDefinition;
import fr.lip6.move.coloane.api.cami.input.dialog.DisplayDialog;
import fr.lip6.move.coloane.api.cami.input.dialog.HideDialog;
import fr.lip6.move.coloane.api.cami.input.dialog.NextDialog;
import fr.lip6.move.coloane.api.cami.input.menus.DisableMainQuestion;
import fr.lip6.move.coloane.api.cami.input.menus.EnableMainQuestion;
import fr.lip6.move.coloane.api.cami.input.menus.EndMenuTransmission;
import fr.lip6.move.coloane.api.cami.input.menus.HelpQuestion;
import fr.lip6.move.coloane.api.cami.input.menus.MenuName;
import fr.lip6.move.coloane.api.cami.input.menus.QuestionAdd;
import fr.lip6.move.coloane.api.cami.input.menus.ServiceMenuModification;
import fr.lip6.move.coloane.api.cami.input.menus.ServiceMenuReception;
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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "FIXED_LENGTH_STRING", "NEWLINE", "'DB()'", "'FB()'", "'CN('", "','", "')'", "'CB('", "'CA('", "'CT('", "'CM('", "'PO('", "'pO('", "'PO(-1,'", "'PT('", "'PI('", "'DC()'", "'FF()'", "'CE('", "'DS('", "'AD('", "'HD('", "'DG('", "'MI('", "'TR('", "'WN('", "'MO('", "'SC('", "'OC('", "'FC()'", "'KO(1,'", "'TL()'", "'FL()'", "'VI('", "'DR()'", "'FR('", "'RQ('", "'TQ('", "'AQ('", "'DE('", "'FE()'", "'RT('", "'WE('", "'RO('", "'ME('", "'MT('", "'SU('", "'SI('", "'OS('", "'TD()'", "'FA()'", "'FS()'", "'SS()'", "'RS('", "'DF()'", "'DF(-2,'", "'DQ()'", "'FQ()'", "'CQ('", "'VQ('", "'EQ('", "'QQ('", "'HQ('"
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:72:1: model_definition : 'DB()' ( syntactic | aestetic ) 'FB()' ;
    public final void model_definition() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:73:2: ( 'DB()' ( syntactic | aestetic ) 'FB()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:74:2: 'DB()' ( syntactic | aestetic ) 'FB()'
            {
            match(input,8,FOLLOW_8_in_model_definition35); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:75:2: ( syntactic | aestetic )
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
                    new NoViableAltException("75:2: ( syntactic | aestetic )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:75:4: syntactic
                    {
                    pushFollow(FOLLOW_syntactic_in_model_definition40);
                    syntactic();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:75:16: aestetic
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:79:1: syntactic : ( node | box | arc | textual_attribute );
    public final void syntactic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:80:2: ( node | box | arc | textual_attribute )
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
                    new NoViableAltException("79:1: syntactic : ( node | box | arc | textual_attribute );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:81:2: node
                    {
                    pushFollow(FOLLOW_node_in_syntactic61);
                    node();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:81:9: box
                    {
                    pushFollow(FOLLOW_box_in_syntactic65);
                    box();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:81:15: arc
                    {
                    pushFollow(FOLLOW_arc_in_syntactic69);
                    arc();
                    _fsp--;


                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:81:21: textual_attribute
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:84:1: node : 'CN(' CAMI_STRING ',' number ')' ;
    public final void node() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:84:6: ( 'CN(' CAMI_STRING ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:85:2: 'CN(' CAMI_STRING ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:88:1: box : 'CB(' CAMI_STRING ',' number ',' number ')' ;
    public final void box() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:88:5: ( 'CB(' CAMI_STRING ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:89:2: 'CB(' CAMI_STRING ',' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:92:1: arc : 'CA(' CAMI_STRING ',' number ',' number ',' number ')' ;
    public final void arc() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:92:5: ( 'CA(' CAMI_STRING ',' number ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:93:2: 'CA(' CAMI_STRING ',' number ',' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:96:1: textual_attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );
    public final void textual_attribute() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:97:2: ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' )
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
                    new NoViableAltException("96:1: textual_attribute : ( 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')' );", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:98:4: 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:99:4: 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:103:1: aestetic : ( object_position | text_position | intermediary_point );
    public final void aestetic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:104:2: ( object_position | text_position | intermediary_point )
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
                    new NoViableAltException("103:1: aestetic : ( object_position | text_position | intermediary_point );", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:105:2: object_position
                    {
                    pushFollow(FOLLOW_object_position_in_aestetic209);
                    object_position();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:105:20: text_position
                    {
                    pushFollow(FOLLOW_text_position_in_aestetic213);
                    text_position();
                    _fsp--;


                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:105:36: intermediary_point
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:108:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );
    public final void object_position() throws RecognitionException {
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;

        int left = 0;

        int right = 0;

        int top = 0;

        int bottom = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:109:2: ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' )
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
                    new NoViableAltException("108:1: object_position : ( 'PO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'pO(' id= number ',' h_distance= number ',' v_distance= number ')' | 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:110:4: 'PO(' id= number ',' h_distance= number ',' v_distance= number ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:111:4: 'pO(' id= number ',' h_distance= number ',' v_distance= number ')'
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
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:112:4: 'PO(-1,' id= number ',' left= number ',' right= number ',' top= number ',' bottom= number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:115:1: text_position : 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' ;
    public final void text_position() throws RecognitionException {
        Token name_attr=null;
        int id = 0;

        int h_distance = 0;

        int v_distance = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:116:2: ( 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:117:2: 'PT(' id= number ',' name_attr= CAMI_STRING ',' h_distance= number ',' v_distance= number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:120:1: intermediary_point : 'PI(' number ',' number ',' number ')' ;
    public final void intermediary_point() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:121:2: ( 'PI(' number ',' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:122:2: 'PI(' number ',' number ',' number ')'
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:129:1: dialog_definition returns [DialogDefinition camiContent] : 'DC()' dialog_creation ( next_dialog )+ 'FF()' ;
    public final DialogDefinition dialog_definition() throws RecognitionException {
        DialogDefinition camiContent = null;

        NextDialog next_dialog1 = null;

        DialogCreation dialog_creation2 = null;



        		Collection<NextDialog> nextDialogs = new Vector<NextDialog>();
        	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:135:2: ( 'DC()' dialog_creation ( next_dialog )+ 'FF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:136:2: 'DC()' dialog_creation ( next_dialog )+ 'FF()'
            {
            match(input,22,FOLLOW_22_in_dialog_definition396); 
            pushFollow(FOLLOW_dialog_creation_in_dialog_definition399);
            dialog_creation2=dialog_creation();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:138:2: ( next_dialog )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:138:4: next_dialog
            	    {
            	    pushFollow(FOLLOW_next_dialog_in_dialog_definition404);
            	    next_dialog1=next_dialog();
            	    _fsp--;


            	    		nextDialogs.add(next_dialog1);
            	    	

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

            match(input,23,FOLLOW_23_in_dialog_definition413); 

            		return new DialogDefinition(dialog_creation2, nextDialogs);
            	

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
    // $ANTLR end dialog_definition


    // $ANTLR start dialog_creation
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:148:1: dialog_creation returns [DialogCreation camiContent] : 'CE(' dialog_id= number ',' dialog_type= number ',' buttons_type= number ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= number ',' line_type= number ',' (default_value= CAMI_STRING )? ')' ;
    public final DialogCreation dialog_creation() throws RecognitionException {
        DialogCreation camiContent = null;

        Token window_title=null;
        Token help=null;
        Token title_or_message=null;
        Token default_value=null;
        int dialog_id = 0;

        int dialog_type = 0;

        int buttons_type = 0;

        int input_type = 0;

        int line_type = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:150:2: ( 'CE(' dialog_id= number ',' dialog_type= number ',' buttons_type= number ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= number ',' line_type= number ',' (default_value= CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:151:2: 'CE(' dialog_id= number ',' dialog_type= number ',' buttons_type= number ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= number ',' line_type= number ',' (default_value= CAMI_STRING )? ')'
            {
            match(input,24,FOLLOW_24_in_dialog_creation433); 
            pushFollow(FOLLOW_number_in_dialog_creation437);
            dialog_id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation439); 
            pushFollow(FOLLOW_number_in_dialog_creation443);
            dialog_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation445); 
            pushFollow(FOLLOW_number_in_dialog_creation449);
            buttons_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation451); 
            window_title=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation456); 
            match(input,11,FOLLOW_11_in_dialog_creation458); 
            help=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation462); 
            match(input,11,FOLLOW_11_in_dialog_creation464); 
            title_or_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation468); 
            match(input,11,FOLLOW_11_in_dialog_creation470); 
            pushFollow(FOLLOW_number_in_dialog_creation477);
            input_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation479); 
            pushFollow(FOLLOW_number_in_dialog_creation483);
            line_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_dialog_creation485); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:152:59: (default_value= CAMI_STRING )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==CAMI_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:152:59: default_value= CAMI_STRING
                    {
                    default_value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialog_creation489); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_dialog_creation492); 

            		String defaultValue = "";
            		camiContent = new DialogCreation( 	dialog_id,
            							DialogCreation.DialogType(dialog_type),
            							DialogCreation.ButtonsType(buttons_type),
            							window_title.getText(),
            							help.getText(),
            							title_or_message.getText(),
            							DialogCreation.InputType(input_type),
            							DialogCreation.LineType(line_type),
            							defaultValue);
            	

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
    // $ANTLR end dialog_creation


    // $ANTLR start next_dialog
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:167:1: next_dialog returns [NextDialog camiContent] : 'DS(' dialog_id= number ',' line= CAMI_STRING ')' ;
    public final NextDialog next_dialog() throws RecognitionException {
        NextDialog camiContent = null;

        Token line=null;
        int dialog_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:169:2: ( 'DS(' dialog_id= number ',' line= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:170:2: 'DS(' dialog_id= number ',' line= CAMI_STRING ')'
            {
            match(input,25,FOLLOW_25_in_next_dialog512); 
            pushFollow(FOLLOW_number_in_next_dialog516);
            dialog_id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_next_dialog518); 
            line=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_next_dialog522); 
            match(input,12,FOLLOW_12_in_next_dialog524); 

            		camiContent = new NextDialog(dialog_id,line.getText());
            	

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
    // $ANTLR end next_dialog


    // $ANTLR start display_dialog
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:176:1: display_dialog returns [DisplayDialog camiContent] : 'AD(' dialog_id= number ')' ;
    public final DisplayDialog display_dialog() throws RecognitionException {
        DisplayDialog camiContent = null;

        int dialog_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:178:2: ( 'AD(' dialog_id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:179:2: 'AD(' dialog_id= number ')'
            {
            match(input,26,FOLLOW_26_in_display_dialog544); 
            pushFollow(FOLLOW_number_in_display_dialog548);
            dialog_id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_display_dialog550); 

            		camiContent = new DisplayDialog(dialog_id);
            	

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
    // $ANTLR end display_dialog


    // $ANTLR start hide_dialog
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:185:1: hide_dialog returns [HideDialog camiContent] : 'HD(' dialog_id= number ')' ;
    public final HideDialog hide_dialog() throws RecognitionException {
        HideDialog camiContent = null;

        int dialog_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:187:2: ( 'HD(' dialog_id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:188:2: 'HD(' dialog_id= number ')'
            {
            match(input,27,FOLLOW_27_in_hide_dialog571); 
            pushFollow(FOLLOW_number_in_hide_dialog575);
            dialog_id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_hide_dialog577); 

            		camiContent = new HideDialog(dialog_id);
            	

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
    // $ANTLR end hide_dialog


    // $ANTLR start destroy_dialog
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:194:1: destroy_dialog returns [DestroyDialog camiContent] : 'DG(' dialog_id= number ')' ;
    public final DestroyDialog destroy_dialog() throws RecognitionException {
        DestroyDialog camiContent = null;

        int dialog_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:196:2: ( 'DG(' dialog_id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:197:2: 'DG(' dialog_id= number ')'
            {
            match(input,28,FOLLOW_28_in_destroy_dialog598); 
            pushFollow(FOLLOW_number_in_destroy_dialog602);
            dialog_id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_destroy_dialog604); 

            		camiContent = new DestroyDialog(dialog_id);
            	

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
    // $ANTLR end destroy_dialog


    // $ANTLR start interactive_response
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:204:1: interactive_response : 'MI(' number ',' number ')' ;
    public final void interactive_response() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:205:2: ( 'MI(' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:206:2: 'MI(' number ',' number ')'
            {
            match(input,29,FOLLOW_29_in_interactive_response620); 
            pushFollow(FOLLOW_number_in_interactive_response622);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_interactive_response624); 
            pushFollow(FOLLOW_number_in_interactive_response626);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_interactive_response628); 

            }

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:216:1: message_to_user returns [IMessage camiContent] : ( trace_message | warning_message | special_message );
    public final IMessage message_to_user() throws RecognitionException {
        IMessage camiContent = null;

        TraceMessage trace_message3 = null;

        WarningMessage warning_message4 = null;

        SpecialMessages special_message5 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:218:2: ( trace_message | warning_message | special_message )
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
                    new NoViableAltException("216:1: message_to_user returns [IMessage camiContent] : ( trace_message | warning_message | special_message );", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:219:4: trace_message
                    {
                    pushFollow(FOLLOW_trace_message_in_message_to_user653);
                    trace_message3=trace_message();
                    _fsp--;


                    	  	camiContent = trace_message3;
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:223:4: warning_message
                    {
                    pushFollow(FOLLOW_warning_message_in_message_to_user664);
                    warning_message4=warning_message();
                    _fsp--;


                    	  	camiContent = warning_message4;
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:228:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_message_to_user676);
                    special_message5=special_message();
                    _fsp--;


                    	  	camiContent = special_message5;
                    	  

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:234:1: trace_message returns [TraceMessage camiContent] : 'TR(' CAMI_STRING ')' ;
    public final TraceMessage trace_message() throws RecognitionException {
        TraceMessage camiContent = null;

        Token CAMI_STRING6=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:236:2: ( 'TR(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:237:2: 'TR(' CAMI_STRING ')'
            {
            match(input,30,FOLLOW_30_in_trace_message698); 
            CAMI_STRING6=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message700); 
            match(input,12,FOLLOW_12_in_trace_message702); 

            		camiContent = new TraceMessage(CAMI_STRING6.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:243:1: warning_message returns [WarningMessage camiContent] : 'WN(' CAMI_STRING ')' ;
    public final WarningMessage warning_message() throws RecognitionException {
        WarningMessage camiContent = null;

        Token CAMI_STRING7=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:245:2: ( 'WN(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:246:2: 'WN(' CAMI_STRING ')'
            {
            match(input,31,FOLLOW_31_in_warning_message722); 
            CAMI_STRING7=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message724); 
            match(input,12,FOLLOW_12_in_warning_message726); 

            		camiContent = new WarningMessage(CAMI_STRING7.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:252:1: special_message returns [SpecialMessages camiContent] : 'MO(' message_type= number ',' message_content= CAMI_STRING ')' ;
    public final SpecialMessages special_message() throws RecognitionException {
        SpecialMessages camiContent = null;

        Token message_content=null;
        int message_type = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:254:2: ( 'MO(' message_type= number ',' message_content= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:255:2: 'MO(' message_type= number ',' message_content= CAMI_STRING ')'
            {
            match(input,32,FOLLOW_32_in_special_message747); 
            pushFollow(FOLLOW_number_in_special_message751);
            message_type=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_special_message753); 
            message_content=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message757); 
            match(input,12,FOLLOW_12_in_special_message759); 

            		camiContent = new SpecialMessages(SpecialMessages.SpecialMessageType(message_type),message_content.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:264:1: open_communication returns [ICommand camiContent] : ( ack_open_communication | close_connection_panic | special_message );
    public final ICommand open_communication() throws RecognitionException {
        ICommand camiContent = null;

        AckOpenCommunication ack_open_communication8 = null;

        CloseConnectionPanic close_connection_panic9 = null;

        SpecialMessages special_message10 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:266:2: ( ack_open_communication | close_connection_panic | special_message )
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
                    new NoViableAltException("264:1: open_communication returns [ICommand camiContent] : ( ack_open_communication | close_connection_panic | special_message );", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:267:4: ack_open_communication
                    {
                    pushFollow(FOLLOW_ack_open_communication_in_open_communication785);
                    ack_open_communication8=ack_open_communication();
                    _fsp--;


                    		camiContent = ack_open_communication8;
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:271:4: close_connection_panic
                    {
                    pushFollow(FOLLOW_close_connection_panic_in_open_communication796);
                    close_connection_panic9=close_connection_panic();
                    _fsp--;


                    		camiContent = close_connection_panic9;
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:275:6: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_open_communication808);
                    special_message10=special_message();
                    _fsp--;


                    		camiContent = special_message10;
                    	  

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
    // $ANTLR end open_communication


    // $ANTLR start check_version
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:281:1: check_version returns [ICommand camiContent] : ( ack_open_connection | special_message );
    public final ICommand check_version() throws RecognitionException {
        ICommand camiContent = null;

        AckOpenConnection ack_open_connection11 = null;

        SpecialMessages special_message12 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:283:2: ( ack_open_connection | special_message )
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
                    new NoViableAltException("281:1: check_version returns [ICommand camiContent] : ( ack_open_connection | special_message );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:284:4: ack_open_connection
                    {
                    pushFollow(FOLLOW_ack_open_connection_in_check_version832);
                    ack_open_connection11=ack_open_connection();
                    _fsp--;


                    	  	camiContent = ack_open_connection11;
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:288:4: special_message
                    {
                    pushFollow(FOLLOW_special_message_in_check_version842);
                    special_message12=special_message();
                    _fsp--;


                    	  	camiContent = special_message12;
                    	  

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
    // $ANTLR end check_version


    // $ANTLR start ack_open_communication
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:294:1: ack_open_communication returns [AckOpenCommunication camiContent] : 'SC(' CAMI_STRING ')' ;
    public final AckOpenCommunication ack_open_communication() throws RecognitionException {
        AckOpenCommunication camiContent = null;

        Token CAMI_STRING13=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:296:2: ( 'SC(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:297:2: 'SC(' CAMI_STRING ')'
            {
            match(input,33,FOLLOW_33_in_ack_open_communication864); 
            CAMI_STRING13=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication866); 
            match(input,12,FOLLOW_12_in_ack_open_communication868); 

            		camiContent = new AckOpenCommunication(CAMI_STRING13.getText());
            	

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:303:1: ack_open_connection returns [AckOpenConnection camiContent] : 'OC(' major= number ',' minor= number ')' ;
    public final AckOpenConnection ack_open_connection() throws RecognitionException {
        AckOpenConnection camiContent = null;

        int major = 0;

        int minor = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:305:2: ( 'OC(' major= number ',' minor= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:306:2: 'OC(' major= number ',' minor= number ')'
            {
            match(input,34,FOLLOW_34_in_ack_open_connection889); 
            pushFollow(FOLLOW_number_in_ack_open_connection893);
            major=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ack_open_connection895); 
            pushFollow(FOLLOW_number_in_ack_open_connection899);
            minor=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ack_open_connection901); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:312:1: close_connection_normal returns [CloseConnectionNormal camiContent] : 'FC()' ;
    public final CloseConnectionNormal close_connection_normal() throws RecognitionException {
        CloseConnectionNormal camiContent = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:314:2: ( 'FC()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:315:2: 'FC()'
            {
            match(input,35,FOLLOW_35_in_close_connection_normal921); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:321:1: close_connection_panic returns [CloseConnectionPanic camiContent] : 'KO(1,' message= CAMI_STRING ',' severity= number ')' ;
    public final CloseConnectionPanic close_connection_panic() throws RecognitionException {
        CloseConnectionPanic camiContent = null;

        Token message=null;
        int severity = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:323:2: ( 'KO(1,' message= CAMI_STRING ',' severity= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:324:2: 'KO(1,' message= CAMI_STRING ',' severity= number ')'
            {
            match(input,36,FOLLOW_36_in_close_connection_panic942); 
            message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_close_connection_panic946); 
            match(input,11,FOLLOW_11_in_close_connection_panic948); 
            pushFollow(FOLLOW_number_in_close_connection_panic952);
            severity=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_close_connection_panic954); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:333:1: interlocutor_table : 'TL()' ( body_table )+ 'FL()' ;
    public final void interlocutor_table() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:334:2: ( 'TL()' ( body_table )+ 'FL()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:335:2: 'TL()' ( body_table )+ 'FL()'
            {
            match(input,37,FOLLOW_37_in_interlocutor_table971); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:336:2: ( body_table )+
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
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:336:2: body_table
            	    {
            	    pushFollow(FOLLOW_body_table_in_interlocutor_table975);
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

            match(input,38,FOLLOW_38_in_interlocutor_table980); 

            }

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:340:1: body_table : 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= number ',' new_model= number ')' ;
    public final void body_table() throws RecognitionException {
        Token service_name=null;
        Token about_service=null;
        int deprecated = 0;

        int new_model = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:341:2: ( 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= number ',' new_model= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:342:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' deprecated= number ',' new_model= number ')'
            {
            match(input,39,FOLLOW_39_in_body_table993); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table997); 
            match(input,11,FOLLOW_11_in_body_table999); 
            about_service=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_body_table1003); 
            match(input,11,FOLLOW_11_in_body_table1005); 
            pushFollow(FOLLOW_number_in_body_table1009);
            deprecated=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_body_table1011); 
            pushFollow(FOLLOW_number_in_body_table1015);
            new_model=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_body_table1017); 

            }

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:347:1: pre_result_reception : question_state ask_hierarchic ;
    public final void pre_result_reception() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:348:2: ( question_state ask_hierarchic )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:349:2: question_state ask_hierarchic
            {
            pushFollow(FOLLOW_question_state_in_pre_result_reception1030);
            question_state();
            _fsp--;

            pushFollow(FOLLOW_ask_hierarchic_in_pre_result_reception1033);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:353:1: result_reception returns [Results camiContent] : 'DR()' reply_to_question ( question_state | special_message | warning_message | result )* 'FR(' answer_type= number ')' ;
    public final Results result_reception() throws RecognitionException {
        Results camiContent = null;

        int answer_type = 0;

        QuestionAnswer reply_to_question14 = null;

        QuestionState question_state15 = null;

        SpecialMessages special_message16 = null;

        WarningMessage warning_message17 = null;

        ResultSet result18 = null;



        		Collection<QuestionState> questionStates = new Vector<QuestionState>();
        		Collection<IMessage> messages = new Vector<IMessage>();
        		Collection<ResultSet> resultSets = new Vector<ResultSet>();
        		QuestionAnswer questionAnswer = null;
        	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:362:2: ( 'DR()' reply_to_question ( question_state | special_message | warning_message | result )* 'FR(' answer_type= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:363:2: 'DR()' reply_to_question ( question_state | special_message | warning_message | result )* 'FR(' answer_type= number ')'
            {
            match(input,40,FOLLOW_40_in_result_reception1057); 
            pushFollow(FOLLOW_reply_to_question_in_result_reception1060);
            reply_to_question14=reply_to_question();
            _fsp--;


            		questionAnswer = reply_to_question14;
            	
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:368:2: ( question_state | special_message | warning_message | result )*
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
                case 45:
                    {
                    alt12=4;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:369:4: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_result_reception1072);
            	    question_state15=question_state();
            	    _fsp--;


            	    	  	questionStates.add(question_state15);
            	    	  

            	    }
            	    break;
            	case 2 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:373:4: special_message
            	    {
            	    pushFollow(FOLLOW_special_message_in_result_reception1082);
            	    special_message16=special_message();
            	    _fsp--;


            	    	  	messages.add(special_message16);
            	    	  

            	    }
            	    break;
            	case 3 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:377:4: warning_message
            	    {
            	    pushFollow(FOLLOW_warning_message_in_result_reception1092);
            	    warning_message17=warning_message();
            	    _fsp--;


            	    	 	messages.add(warning_message17);
            	    	  

            	    }
            	    break;
            	case 4 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:381:4: result
            	    {
            	    pushFollow(FOLLOW_result_in_result_reception1102);
            	    result18=result();
            	    _fsp--;


            	    	  	resultSets.add(result18);
            	    	  

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            match(input,41,FOLLOW_41_in_result_reception1114); 
            pushFollow(FOLLOW_number_in_result_reception1118);
            answer_type=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result_reception1120); 

            		camiContent = new Results(	questionAnswer,
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
        return camiContent;
    }
    // $ANTLR end result_reception


    // $ANTLR start reply_to_question
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:396:1: reply_to_question returns [QuestionAnswer camiContent] : 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' deprecated= number ')' ;
    public final QuestionAnswer reply_to_question() throws RecognitionException {
        QuestionAnswer camiContent = null;

        Token service_name=null;
        Token question_name=null;
        int deprecated = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:398:2: ( 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' deprecated= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:399:2: 'RQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' deprecated= number ')'
            {
            match(input,42,FOLLOW_42_in_reply_to_question1140); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question1144); 
            match(input,11,FOLLOW_11_in_reply_to_question1146); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_reply_to_question1150); 
            match(input,11,FOLLOW_11_in_reply_to_question1152); 
            pushFollow(FOLLOW_number_in_reply_to_question1156);
            deprecated=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_reply_to_question1158); 

            		camiContent = new QuestionAnswer(service_name.getText(),question_name.getText());
            	

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
    // $ANTLR end reply_to_question


    // $ANTLR start question_state
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:405:1: question_state returns [QuestionState camiContent] : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' ;
    public final QuestionState question_state() throws RecognitionException {
        QuestionState camiContent = null;

        Token service_name=null;
        Token question_name=null;
        Token mess=null;
        int state = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:407:2: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:408:2: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= number ',' (mess= CAMI_STRING )? ')'
            {
            match(input,43,FOLLOW_43_in_question_state1178); 
            service_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1182); 
            match(input,11,FOLLOW_11_in_question_state1184); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1188); 
            match(input,11,FOLLOW_11_in_question_state1190); 
            pushFollow(FOLLOW_number_in_question_state1194);
            state=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_question_state1196); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:408:88: (mess= CAMI_STRING )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CAMI_STRING) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:408:88: mess= CAMI_STRING
                    {
                    mess=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_state1200); 

                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_state1203); 

            		String message = null;
            		if( mess != null ) 
            			message = mess.getText();
            		camiContent = new QuestionState(service_name.getText(),question_name.getText(),state,message);
            	

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
    // $ANTLR end question_state


    // $ANTLR start question_add
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:416:2: question_add returns [QuestionAdd camiContent] : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' ;
    public final QuestionAdd question_add() throws RecognitionException {
        QuestionAdd camiContent = null;

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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:418:2: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:419:2: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= number )? ',' (question_behavior= number )? ',' (set_item= number )? ',' (historic= number )? ',' (stop_authorized= number )? ',' (ouput_formalism= CAMI_STRING )? ',' (active= number )? ')'
            {
            match(input,44,FOLLOW_44_in_question_add1224); 
            parent_menu=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1228); 
            match(input,11,FOLLOW_11_in_question_add1230); 
            entry_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1234); 
            match(input,11,FOLLOW_11_in_question_add1236); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:420:20: (question_type= number )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==NUMBER) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:420:20: question_type= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1247);
                    question_type=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1250); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:420:50: (question_behavior= number )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NUMBER) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:420:50: question_behavior= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1254);
                    question_behavior=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1257); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:421:15: (set_item= number )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==NUMBER) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:421:15: set_item= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1268);
                    set_item=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1271); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:421:37: (historic= number )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NUMBER) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:421:37: historic= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1276);
                    historic=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1279); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:421:65: (stop_authorized= number )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==NUMBER) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:421:65: stop_authorized= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1283);
                    stop_authorized=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1286); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:422:22: (ouput_formalism= CAMI_STRING )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==CAMI_STRING) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:422:22: ouput_formalism= CAMI_STRING
                    {
                    ouput_formalism=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add1297); 

                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_question_add1300); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:422:46: (active= number )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NUMBER) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:422:46: active= number
                    {
                    pushFollow(FOLLOW_number_in_question_add1304);
                    active=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_question_add1307); 

            		camiContent = new QuestionAdd(); // TODO
            	

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
    // $ANTLR end question_add


    // $ANTLR start result
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:429:1: result returns [ResultSet camiContent] : 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' ;
    public final ResultSet result() throws RecognitionException {
        ResultSet camiContent = null;

        Token ensemble_name=null;
        int ensemble_type = 0;

        IResult result_body19 = null;



        		Collection<IResult> results = new Vector<IResult>();
        	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:435:2: ( 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:436:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= number ')' ( result_body )+ 'FE()'
            {
            match(input,45,FOLLOW_45_in_result1336); 
            ensemble_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result1340); 
            match(input,11,FOLLOW_11_in_result1342); 
            pushFollow(FOLLOW_number_in_result1346);
            ensemble_type=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_result1348); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:437:2: ( result_body )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==10||(LA21_0>=13 && LA21_0<=16)||LA21_0==45||(LA21_0>=47 && LA21_0<=53)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:438:2: result_body
            	    {
            	    pushFollow(FOLLOW_result_body_in_result1354);
            	    result_body19=result_body();
            	    _fsp--;


            	    		results.add(result_body19);
            	    	

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);

            match(input,46,FOLLOW_46_in_result1364); 

            		camiContent = new ResultSet(	ensemble_name.getText(),
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
        return camiContent;
    }
    // $ANTLR end result


    // $ANTLR start result_body
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:451:1: result_body returns [IResult camiContent] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
    public final IResult result_body() throws RecognitionException {
        IResult camiContent = null;

        ResultSet result20 = null;

        IResult textual_result21 = null;

        IResult attribute_change22 = null;

        IResult object_designation23 = null;

        IResult object_outline24 = null;

        IResult attribute_outline25 = null;

        IResult object_creation26 = null;

        IResult object_deletion27 = null;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:453:3: ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
            int alt22=8;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt22=1;
                }
                break;
            case 47:
                {
                alt22=2;
                }
                break;
            case 48:
                {
                alt22=3;
                }
                break;
            case 49:
                {
                alt22=4;
                }
                break;
            case 50:
                {
                alt22=5;
                }
                break;
            case 51:
                {
                alt22=6;
                }
                break;
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
                {
                alt22=7;
                }
                break;
            case 52:
            case 53:
                {
                alt22=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("451:1: result_body returns [IResult camiContent] : ( result | textual_result | attribute_change | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:454:5: result
                    {
                    pushFollow(FOLLOW_result_in_result_body1390);
                    result20=result();
                    _fsp--;


                     		camiContent = result20;
                     	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:458:5: textual_result
                    {
                    pushFollow(FOLLOW_textual_result_in_result_body1402);
                    textual_result21=textual_result();
                    _fsp--;


                     		camiContent = textual_result21;
                     	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:462:5: attribute_change
                    {
                    pushFollow(FOLLOW_attribute_change_in_result_body1414);
                    attribute_change22=attribute_change();
                    _fsp--;


                     		camiContent = attribute_change22;
                     	  

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:466:5: object_designation
                    {
                    pushFollow(FOLLOW_object_designation_in_result_body1426);
                    object_designation23=object_designation();
                    _fsp--;


                     		camiContent = object_designation23;
                     	  

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:470:5: object_outline
                    {
                    pushFollow(FOLLOW_object_outline_in_result_body1438);
                    object_outline24=object_outline();
                    _fsp--;


                     		camiContent = object_outline24;
                     	  

                    }
                    break;
                case 6 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:474:5: attribute_outline
                    {
                    pushFollow(FOLLOW_attribute_outline_in_result_body1450);
                    attribute_outline25=attribute_outline();
                    _fsp--;


                     		camiContent = attribute_outline25;
                     	  

                    }
                    break;
                case 7 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:478:5: object_creation
                    {
                    pushFollow(FOLLOW_object_creation_in_result_body1462);
                    object_creation26=object_creation();
                    _fsp--;


                     		camiContent = object_creation26;
                     	  

                    }
                    break;
                case 8 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:482:5: object_deletion
                    {
                    pushFollow(FOLLOW_object_deletion_in_result_body1474);
                    object_deletion27=object_deletion();
                    _fsp--;


                     		camiContent = object_deletion27;
                     	  

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
    // $ANTLR end result_body


    // $ANTLR start textual_result
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:488:2: textual_result returns [IResult camiContent] : 'RT(' CAMI_STRING ')' ;
    public final IResult textual_result() throws RecognitionException {
        IResult camiContent = null;

        Token CAMI_STRING28=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:490:3: ( 'RT(' CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:491:3: 'RT(' CAMI_STRING ')'
            {
            match(input,47,FOLLOW_47_in_textual_result1503); 
            CAMI_STRING28=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1505); 
            match(input,12,FOLLOW_12_in_textual_result1507); 

             		camiContent = new TextualResult(CAMI_STRING28.getText());
             	

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
    // $ANTLR end textual_result


    // $ANTLR start attribute_change
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:497:2: attribute_change returns [IResult camiContent] : 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' ;
    public final IResult attribute_change() throws RecognitionException {
        IResult camiContent = null;

        Token attr_name=null;
        Token new_value=null;
        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:499:3: ( 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:500:3: 'WE(' id= number ',' attr_name= CAMI_STRING ',' new_value= CAMI_STRING ')'
            {
            match(input,48,FOLLOW_48_in_attribute_change1534); 
            pushFollow(FOLLOW_number_in_attribute_change1538);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_change1540); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1544); 
            match(input,11,FOLLOW_11_in_attribute_change1546); 
            new_value=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_change1550); 
            match(input,12,FOLLOW_12_in_attribute_change1552); 

             		camiContent = new AttributeChange(id,attr_name.getText(),new_value.getText());
             	

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
    // $ANTLR end attribute_change


    // $ANTLR start object_designation
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:506:2: object_designation returns [IResult camiContent] : 'RO(' id= number ')' ;
    public final IResult object_designation() throws RecognitionException {
        IResult camiContent = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:508:3: ( 'RO(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:509:3: 'RO(' id= number ')'
            {
            match(input,49,FOLLOW_49_in_object_designation1579); 
            pushFollow(FOLLOW_number_in_object_designation1583);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_designation1585); 

             		camiContent = new ObjectDesignation(id);
             	

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
    // $ANTLR end object_designation


    // $ANTLR start object_outline
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:515:2: object_outline returns [IResult camiContent] : 'ME(' id= number ')' ;
    public final IResult object_outline() throws RecognitionException {
        IResult camiContent = null;

        int id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:517:3: ( 'ME(' id= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:518:3: 'ME(' id= number ')'
            {
            match(input,50,FOLLOW_50_in_object_outline1612); 
            pushFollow(FOLLOW_number_in_object_outline1616);
            id=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_object_outline1618); 

             		camiContent = new ObjectOutline(id);
             	

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
    // $ANTLR end object_outline


    // $ANTLR start attribute_outline
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:524:2: attribute_outline returns [IResult camiContent] : 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' ;
    public final IResult attribute_outline() throws RecognitionException {
        IResult camiContent = null;

        Token attr_name=null;
        int id = 0;

        int begin = 0;

        int end = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:526:3: ( 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:527:3: 'MT(' id= number ',' attr_name= CAMI_STRING ',' (begin= number )? ',' (end= number )? ')'
            {
            match(input,51,FOLLOW_51_in_attribute_outline1645); 
            pushFollow(FOLLOW_number_in_attribute_outline1649);
            id=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_attribute_outline1651); 
            attr_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1655); 
            match(input,11,FOLLOW_11_in_attribute_outline1657); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:527:54: (begin= number )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NUMBER) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:527:54: begin= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1661);
                    begin=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,11,FOLLOW_11_in_attribute_outline1664); 
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:527:70: (end= number )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NUMBER) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:527:70: end= number
                    {
                    pushFollow(FOLLOW_number_in_attribute_outline1668);
                    end=number();
                    _fsp--;


                    }
                    break;

            }

            match(input,12,FOLLOW_12_in_attribute_outline1671); 

            		camiContent = new AttributeOutline(id,attr_name.getText());
             	

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
    // $ANTLR end attribute_outline


    // $ANTLR start object_creation
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:533:2: object_creation returns [IResult camiContent] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')' );
    public final IResult object_creation() throws RecognitionException {
        IResult camiContent = null;

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
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:535:3: ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')' )
            int alt25=5;
            switch ( input.LA(1) ) {
            case 10:
                {
                alt25=1;
                }
                break;
            case 13:
                {
                alt25=2;
                }
                break;
            case 14:
                {
                alt25=3;
                }
                break;
            case 15:
                {
                alt25=4;
                }
                break;
            case 16:
                {
                alt25=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("533:2: object_creation returns [IResult camiContent] : ( 'CN(' node_box_type= CAMI_STRING ',' id= number ')' | 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')' | 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')' | 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')' | 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')' );", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:536:4: 'CN(' node_box_type= CAMI_STRING ',' id= number ')'
                    {
                    match(input,10,FOLLOW_10_in_object_creation1699); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1703); 
                    match(input,11,FOLLOW_11_in_object_creation1705); 
                    pushFollow(FOLLOW_number_in_object_creation1709);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1711); 

                    	  	camiContent = new CreateNode(node_box_type.getText(),id);
                    	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:540:4: 'CB(' node_box_type= CAMI_STRING ',' id= number ',' page_id= number ')'
                    {
                    match(input,13,FOLLOW_13_in_object_creation1721); 
                    node_box_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1725); 
                    match(input,11,FOLLOW_11_in_object_creation1727); 
                    pushFollow(FOLLOW_number_in_object_creation1731);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1733); 
                    pushFollow(FOLLOW_number_in_object_creation1737);
                    page_id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1739); 

                    	  	camiContent = new CreateBox(node_box_type.getText(),id,page_id);
                    	  

                    }
                    break;
                case 3 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:544:4: 'CA(' arc_type= CAMI_STRING ',' id= number ',' start_node= number ',' end_node= number ')'
                    {
                    match(input,14,FOLLOW_14_in_object_creation1749); 
                    arc_type=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1753); 
                    match(input,11,FOLLOW_11_in_object_creation1755); 
                    pushFollow(FOLLOW_number_in_object_creation1759);
                    id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1761); 
                    pushFollow(FOLLOW_number_in_object_creation1765);
                    start_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1767); 
                    pushFollow(FOLLOW_number_in_object_creation1771);
                    end_node=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_creation1773); 

                    	  	camiContent = new CreateArc(arc_type.getText(),id,start_node,end_node);
                    	  

                    }
                    break;
                case 4 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:548:4: 'CT(' attribute_name= CAMI_STRING ',' associated_node= number ',' value= CAMI_STRING ')'
                    {
                    match(input,15,FOLLOW_15_in_object_creation1783); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1787); 
                    match(input,11,FOLLOW_11_in_object_creation1789); 
                    pushFollow(FOLLOW_number_in_object_creation1793);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1795); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1799); 
                    match(input,12,FOLLOW_12_in_object_creation1801); 

                    	  	camiContent = new CreateMonolineAttribute(attribute_name.getText(),associated_node,value.getText());
                    	  

                    }
                    break;
                case 5 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:552:4: 'CM(' attribute_name= CAMI_STRING ',' associated_node= number ',' line_number= number ',' deprecated= number ',' value= CAMI_STRING ')'
                    {
                    match(input,16,FOLLOW_16_in_object_creation1811); 
                    attribute_name=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1815); 
                    match(input,11,FOLLOW_11_in_object_creation1817); 
                    pushFollow(FOLLOW_number_in_object_creation1821);
                    associated_node=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1823); 
                    pushFollow(FOLLOW_number_in_object_creation1827);
                    line_number=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1829); 
                    pushFollow(FOLLOW_number_in_object_creation1833);
                    deprecated=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_creation1835); 
                    value=(Token)input.LT(1);
                    match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1839); 
                    match(input,12,FOLLOW_12_in_object_creation1841); 

                    	  	camiContent = new CreateMultilineAttribute(attribute_name.getText(),associated_node,line_number,value.getText());
                    	  

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
    // $ANTLR end object_creation


    // $ANTLR start object_deletion
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:558:1: object_deletion returns [IResult camiContent] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );
    public final IResult object_deletion() throws RecognitionException {
        IResult camiContent = null;

        int id = 0;

        int page_id = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:560:2: ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==52) ) {
                alt26=1;
            }
            else if ( (LA26_0==53) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("558:1: object_deletion returns [IResult camiContent] : ( 'SU(' id= number ')' | 'SI(' page_id= number ',' id= number ')' );", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:561:5: 'SU(' id= number ')'
                    {
                    match(input,52,FOLLOW_52_in_object_deletion1869); 
                    pushFollow(FOLLOW_number_in_object_deletion1873);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1875); 

                     	  	camiContent = new ObjectDeletion(id);
                     	  

                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:565:5: 'SI(' page_id= number ',' id= number ')'
                    {
                    match(input,53,FOLLOW_53_in_object_deletion1887); 
                    pushFollow(FOLLOW_number_in_object_deletion1891);
                    page_id=number();
                    _fsp--;

                    match(input,11,FOLLOW_11_in_object_deletion1893); 
                    pushFollow(FOLLOW_number_in_object_deletion1897);
                    id=number();
                    _fsp--;

                    match(input,12,FOLLOW_12_in_object_deletion1899); 

                     	  	camiContent = new MultipleObjectDeletion(page_id,id);
                     	  

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
    // $ANTLR end object_deletion


    // $ANTLR start ack_open_session
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:573:1: ack_open_session returns [AckOpenSession camiContent] : 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table ;
    public final AckOpenSession ack_open_session() throws RecognitionException {
        AckOpenSession camiContent = null;

        Token session_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:575:2: ( 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:576:2: 'OS(' session_name= CAMI_STRING ')' 'TD()' 'FA()' interlocutor_table
            {
            match(input,54,FOLLOW_54_in_ack_open_session1926); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session1930); 
            match(input,12,FOLLOW_12_in_ack_open_session1932); 
            match(input,55,FOLLOW_55_in_ack_open_session1935); 
            match(input,56,FOLLOW_56_in_ack_open_session1938); 
            pushFollow(FOLLOW_interlocutor_table_in_ack_open_session1941);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:585:1: ack_close_current_session returns [AckCloseCurrentSession camiContent] : 'FS()' ;
    public final AckCloseCurrentSession ack_close_current_session() throws RecognitionException {
        AckCloseCurrentSession camiContent = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:587:2: ( 'FS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:588:2: 'FS()'
            {
            match(input,57,FOLLOW_57_in_ack_close_current_session1962); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:594:1: ack_suspend_current_session returns [AckSuspendCurrentSession camiContent] : 'SS()' ;
    public final AckSuspendCurrentSession ack_suspend_current_session() throws RecognitionException {
        AckSuspendCurrentSession camiContent = null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:596:2: ( 'SS()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:597:2: 'SS()'
            {
            match(input,58,FOLLOW_58_in_ack_suspend_current_session1984); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:603:1: ack_resume_session returns [AckResumeSession camiContent] : 'RS(' session_name= CAMI_STRING ')' ;
    public final AckResumeSession ack_resume_session() throws RecognitionException {
        AckResumeSession camiContent = null;

        Token session_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:605:2: ( 'RS(' session_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:606:2: 'RS(' session_name= CAMI_STRING ')'
            {
            match(input,59,FOLLOW_59_in_ack_resume_session2004); 
            session_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_session2008); 
            match(input,12,FOLLOW_12_in_ack_resume_session2010); 

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:614:1: ask_and_get_model : ( ask_for_a_model | model_definition );
    public final void ask_and_get_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:615:2: ( ask_for_a_model | model_definition )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=60 && LA27_0<=61)) ) {
                alt27=1;
            }
            else if ( (LA27_0==8) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("614:1: ask_and_get_model : ( ask_for_a_model | model_definition );", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:616:4: ask_for_a_model
                    {
                    pushFollow(FOLLOW_ask_for_a_model_in_ask_and_get_model2029);
                    ask_for_a_model();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:617:4: model_definition
                    {
                    pushFollow(FOLLOW_model_definition_in_ask_and_get_model2034);
                    model_definition();
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:620:1: ask_for_a_model : ( ask_simple | ask_hierarchic );
    public final void ask_for_a_model() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:621:2: ( ask_simple | ask_hierarchic )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==60) ) {
                alt28=1;
            }
            else if ( (LA28_0==61) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("620:1: ask_for_a_model : ( ask_simple | ask_hierarchic );", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:622:2: ask_simple
                    {
                    pushFollow(FOLLOW_ask_simple_in_ask_for_a_model2046);
                    ask_simple();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:622:15: ask_hierarchic
                    {
                    pushFollow(FOLLOW_ask_hierarchic_in_ask_for_a_model2050);
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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:625:1: ask_simple : 'DF()' ;
    public final void ask_simple() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:626:2: ( 'DF()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:627:2: 'DF()'
            {
            match(input,60,FOLLOW_60_in_ask_simple2062); 

            }

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
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:630:1: ask_hierarchic : 'DF(-2,' number ',' number ')' ;
    public final void ask_hierarchic() throws RecognitionException {
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:631:2: ( 'DF(-2,' number ',' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:632:2: 'DF(-2,' number ',' number ')'
            {
            match(input,61,FOLLOW_61_in_ask_hierarchic2074); 
            pushFollow(FOLLOW_number_in_ask_hierarchic2076);
            number();
            _fsp--;

            match(input,11,FOLLOW_11_in_ask_hierarchic2078); 
            pushFollow(FOLLOW_number_in_ask_hierarchic2080);
            number();
            _fsp--;

            match(input,12,FOLLOW_12_in_ask_hierarchic2082); 

            }

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


    // $ANTLR start service_menu_reception
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:637:1: service_menu_reception returns [ServiceMenuReception camiContent] : 'DQ()' menu_name ( question_add )* 'FQ()' ;
    public final ServiceMenuReception service_menu_reception() throws RecognitionException {
        ServiceMenuReception camiContent = null;

        QuestionAdd question_add29 = null;

        MenuName menu_name30 = null;



        		Collection<QuestionAdd> questions = new Vector<QuestionAdd>();
        	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:643:2: ( 'DQ()' menu_name ( question_add )* 'FQ()' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:644:2: 'DQ()' menu_name ( question_add )* 'FQ()'
            {
            match(input,62,FOLLOW_62_in_service_menu_reception2111); 
            pushFollow(FOLLOW_menu_name_in_service_menu_reception2114);
            menu_name30=menu_name();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:646:2: ( question_add )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==44) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:647:2: question_add
            	    {
            	    pushFollow(FOLLOW_question_add_in_service_menu_reception2120);
            	    question_add29=question_add();
            	    _fsp--;

            	     
            	    		questions.add(question_add29); 
            	    	

            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            match(input,63,FOLLOW_63_in_service_menu_reception2131); 

            		camiContent = new ServiceMenuReception(menu_name30, questions);
            	

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
    // $ANTLR end service_menu_reception


    // $ANTLR start menu_name
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:658:1: menu_name returns [MenuName camiContent] : 'CQ(' name= CAMI_STRING ',' always_three= number ',' always_three= number ')' ;
    public final MenuName menu_name() throws RecognitionException {
        MenuName camiContent = null;

        Token name=null;
        int always_three = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:660:2: ( 'CQ(' name= CAMI_STRING ',' always_three= number ',' always_three= number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:661:2: 'CQ(' name= CAMI_STRING ',' always_three= number ',' always_three= number ')'
            {
            match(input,64,FOLLOW_64_in_menu_name2151); 
            name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name2155); 
            match(input,11,FOLLOW_11_in_menu_name2157); 
            pushFollow(FOLLOW_number_in_menu_name2161);
            always_three=number();
            _fsp--;

            match(input,11,FOLLOW_11_in_menu_name2163); 
            pushFollow(FOLLOW_number_in_menu_name2167);
            always_three=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_menu_name2169); 

            		camiContent = new MenuName(name.getText());
            	

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
    // $ANTLR end menu_name


    // $ANTLR start service_menu_modification
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:668:1: service_menu_modification returns [ServiceMenuModification camiContent] : enable_main_question ( question_state )* end_menu_transmission ;
    public final ServiceMenuModification service_menu_modification() throws RecognitionException {
        ServiceMenuModification camiContent = null;

        QuestionState question_state31 = null;

        EnableMainQuestion enable_main_question32 = null;

        EndMenuTransmission end_menu_transmission33 = null;



         		Collection<QuestionState> questionStates = new Vector<QuestionState>();
         	
        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:674:2: ( enable_main_question ( question_state )* end_menu_transmission )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:675:2: enable_main_question ( question_state )* end_menu_transmission
            {
            pushFollow(FOLLOW_enable_main_question_in_service_menu_modification2200);
            enable_main_question32=enable_main_question();
            _fsp--;

            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:676:2: ( question_state )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==43) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:677:2: question_state
            	    {
            	    pushFollow(FOLLOW_question_state_in_service_menu_modification2206);
            	    question_state31=question_state();
            	    _fsp--;

            	     
            	    		questionStates.add(question_state31); 
            	    	

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            pushFollow(FOLLOW_end_menu_transmission_in_service_menu_modification2217);
            end_menu_transmission33=end_menu_transmission();
            _fsp--;


            		camiContent = new ServiceMenuModification(	enable_main_question32,
            	                                                       	questionStates,
            	                                                        end_menu_transmission33);
            	

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
    // $ANTLR end service_menu_modification


    // $ANTLR start enable_main_question
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:690:1: enable_main_question returns [EnableMainQuestion camiContent] : 'VQ(' main_question_name= CAMI_STRING ')' ;
    public final EnableMainQuestion enable_main_question() throws RecognitionException {
        EnableMainQuestion camiContent = null;

        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:692:2: ( 'VQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:693:2: 'VQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,65,FOLLOW_65_in_enable_main_question2239); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_enable_main_question2243); 
            match(input,12,FOLLOW_12_in_enable_main_question2245); 

            		camiContent = new EnableMainQuestion(main_question_name.getText());
            	

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
    // $ANTLR end enable_main_question


    // $ANTLR start disable_main_question
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:699:1: disable_main_question returns [DisableMainQuestion camiContent] : 'EQ(' main_question_name= CAMI_STRING ')' ;
    public final DisableMainQuestion disable_main_question() throws RecognitionException {
        DisableMainQuestion camiContent = null;

        Token main_question_name=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:701:2: ( 'EQ(' main_question_name= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:702:2: 'EQ(' main_question_name= CAMI_STRING ')'
            {
            match(input,66,FOLLOW_66_in_disable_main_question2266); 
            main_question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_disable_main_question2270); 
            match(input,12,FOLLOW_12_in_disable_main_question2272); 

            		camiContent = new DisableMainQuestion(main_question_name.getText());
            	

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
    // $ANTLR end disable_main_question


    // $ANTLR start end_menu_transmission
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:708:1: end_menu_transmission returns [EndMenuTransmission camiContent] : 'QQ(' number ')' ;
    public final EndMenuTransmission end_menu_transmission() throws RecognitionException {
        EndMenuTransmission camiContent = null;

        int number34 = 0;


        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:710:2: ( 'QQ(' number ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:711:2: 'QQ(' number ')'
            {
            match(input,67,FOLLOW_67_in_end_menu_transmission2292); 
            pushFollow(FOLLOW_number_in_end_menu_transmission2294);
            number34=number();
            _fsp--;

            match(input,12,FOLLOW_12_in_end_menu_transmission2296); 

            		camiContent = new EndMenuTransmission(EndMenuTransmission.AckType(number34));
            	

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
    // $ANTLR end end_menu_transmission


    // $ANTLR start help_question
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:717:1: help_question returns [HelpQuestion camiContent] : 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' ;
    public final HelpQuestion help_question() throws RecognitionException {
        HelpQuestion camiContent = null;

        Token question_name=null;
        Token help_message=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:719:2: ( 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')' )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:720:2: 'HQ(' question_name= CAMI_STRING ',' help_message= CAMI_STRING ')'
            {
            match(input,68,FOLLOW_68_in_help_question2317); 
            question_name=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question2321); 
            match(input,11,FOLLOW_11_in_help_question2323); 
            help_message=(Token)input.LT(1);
            match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_help_question2327); 
            match(input,12,FOLLOW_12_in_help_question2329); 

            		camiContent = new HelpQuestion(question_name.getText(), help_message.getText());
             	

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
    // $ANTLR end help_question


    // $ANTLR start number
    // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:730:1: number returns [int value] : NUMBER ;
    public final int number() throws RecognitionException {
        int value = 0;

        Token NUMBER35=null;

        try {
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:732:2: ( NUMBER )
            // /Users/supermac/Documents/workspace33/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/input/parser/Cami.g:733:2: NUMBER
            {
            NUMBER35=(Token)input.LT(1);
            match(input,NUMBER,FOLLOW_NUMBER_in_number2356); 
            value = Integer.parseInt(NUMBER35.getText());

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
    public static final BitSet FOLLOW_22_in_dialog_definition396 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_dialog_creation_in_dialog_definition399 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_next_dialog_in_dialog_definition404 = new BitSet(new long[]{0x0000000002800000L});
    public static final BitSet FOLLOW_23_in_dialog_definition413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_dialog_creation433 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation437 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation439 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation443 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation445 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation449 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation451 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation456 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation458 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation464 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation468 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation470 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation477 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation479 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_dialog_creation483 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_dialog_creation485 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_dialog_creation489 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_dialog_creation492 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_next_dialog512 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_next_dialog516 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_next_dialog518 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_next_dialog522 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_next_dialog524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_display_dialog544 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_display_dialog548 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_display_dialog550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_hide_dialog571 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_hide_dialog575 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_hide_dialog577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_destroy_dialog598 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_destroy_dialog602 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_destroy_dialog604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_interactive_response620 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_interactive_response622 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_interactive_response624 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_interactive_response626 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_interactive_response628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_trace_message_in_message_to_user653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_warning_message_in_message_to_user664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_message_to_user676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_trace_message698 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_trace_message700 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_trace_message702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_warning_message722 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_warning_message724 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_warning_message726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_special_message747 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_special_message751 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_special_message753 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_special_message757 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_special_message759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_communication_in_open_communication785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_close_connection_panic_in_open_communication796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_open_communication808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ack_open_connection_in_check_version832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_special_message_in_check_version842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ack_open_communication864 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication866 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_communication868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ack_open_connection889 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection893 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ack_open_connection895 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ack_open_connection899 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_connection901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_close_connection_normal921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_close_connection_panic942 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_close_connection_panic946 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_close_connection_panic948 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_close_connection_panic952 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_close_connection_panic954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_interlocutor_table971 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_body_table_in_interlocutor_table975 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_38_in_interlocutor_table980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_body_table993 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table997 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table999 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_body_table1003 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table1005 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_body_table1009 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_body_table1011 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_body_table1015 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_body_table1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_state_in_pre_result_reception1030 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_ask_hierarchic_in_pre_result_reception1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_result_reception1057 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_reply_to_question_in_result_reception1060 = new BitSet(new long[]{0x00002A0180000000L});
    public static final BitSet FOLLOW_question_state_in_result_reception1072 = new BitSet(new long[]{0x00002A0180000000L});
    public static final BitSet FOLLOW_special_message_in_result_reception1082 = new BitSet(new long[]{0x00002A0180000000L});
    public static final BitSet FOLLOW_warning_message_in_result_reception1092 = new BitSet(new long[]{0x00002A0180000000L});
    public static final BitSet FOLLOW_result_in_result_reception1102 = new BitSet(new long[]{0x00002A0180000000L});
    public static final BitSet FOLLOW_41_in_result_reception1114 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result_reception1118 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result_reception1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_reply_to_question1140 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question1144 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question1146 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_reply_to_question1150 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_reply_to_question1152 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_reply_to_question1156 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_reply_to_question1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_question_state1178 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1182 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1184 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1188 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1190 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_question_state1194 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_state1196 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_state1200 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_state1203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_question_add1224 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1228 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1230 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1234 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1236 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1247 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1250 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1254 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1257 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1268 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1271 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1276 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1279 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_question_add1283 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1286 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_CAMI_STRING_in_question_add1297 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_question_add1300 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_question_add1304 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_question_add1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_result1336 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_result1340 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_result1342 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_result1346 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_result1348 = new BitSet(new long[]{0x003FA0000001E400L});
    public static final BitSet FOLLOW_result_body_in_result1354 = new BitSet(new long[]{0x003FE0000001E400L});
    public static final BitSet FOLLOW_46_in_result1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_result_in_result_body1390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_textual_result_in_result_body1402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_change_in_result_body1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_designation_in_result_body1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_outline_in_result_body1438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attribute_outline_in_result_body1450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_creation_in_result_body1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_object_deletion_in_result_body1474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_textual_result1503 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1505 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_textual_result1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_attribute_change1534 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_change1538 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1540 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1544 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_change1546 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_change1550 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_change1552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_object_designation1579 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_designation1583 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_designation1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_object_outline1612 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_outline1616 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_outline1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_attribute_outline1645 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1649 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1651 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1655 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1657 = new BitSet(new long[]{0x0000000000000820L});
    public static final BitSet FOLLOW_number_in_attribute_outline1661 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_attribute_outline1664 = new BitSet(new long[]{0x0000000000001020L});
    public static final BitSet FOLLOW_number_in_attribute_outline1668 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_attribute_outline1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_object_creation1699 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1703 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1705 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1709 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_object_creation1721 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1725 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1727 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1731 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1733 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1737 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_object_creation1749 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1753 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1755 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1759 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1761 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1765 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1767 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1771 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_object_creation1783 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1787 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1789 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1793 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1795 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1799 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_object_creation1811 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1815 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1817 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1821 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1823 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1827 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1829 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_creation1833 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_creation1835 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1839 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_creation1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_object_deletion1869 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1873 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_object_deletion1887 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1891 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_object_deletion1893 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_object_deletion1897 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_object_deletion1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ack_open_session1926 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session1930 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_open_session1932 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ack_open_session1935 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_56_in_ack_open_session1938 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session1941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ack_close_current_session1962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ack_suspend_current_session1984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_ack_resume_session2004 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_session2008 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ack_resume_session2010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_for_a_model_in_ask_and_get_model2029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_model_definition_in_ask_and_get_model2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_simple_in_ask_for_a_model2046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ask_hierarchic_in_ask_for_a_model2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ask_simple2062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ask_hierarchic2074 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic2076 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_ask_hierarchic2078 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_ask_hierarchic2080 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ask_hierarchic2082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_service_menu_reception2111 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_menu_name_in_service_menu_reception2114 = new BitSet(new long[]{0x8000100000000000L});
    public static final BitSet FOLLOW_question_add_in_service_menu_reception2120 = new BitSet(new long[]{0x8000100000000000L});
    public static final BitSet FOLLOW_63_in_service_menu_reception2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_menu_name2151 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_menu_name2155 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name2157 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name2161 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_menu_name2163 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_menu_name2167 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_menu_name2169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enable_main_question_in_service_menu_modification2200 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_question_state_in_service_menu_modification2206 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_end_menu_transmission_in_service_menu_modification2217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_enable_main_question2239 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_enable_main_question2243 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_enable_main_question2245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_disable_main_question2266 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_disable_main_question2270 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_disable_main_question2272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_end_menu_transmission2292 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_number_in_end_menu_transmission2294 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_end_menu_transmission2296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_help_question2317 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question2321 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_help_question2323 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_CAMI_STRING_in_help_question2327 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_help_question2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_number2356 = new BitSet(new long[]{0x0000000000000002L});

}