//$ANTLR 3.0.1 /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g 2008-07-24 17:45:37

package fr.lip6.move.coloane.api.cami;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugEventListener;
import org.antlr.runtime.debug.DebugEventSocketProxy;
import org.antlr.runtime.debug.DebugParser;

import fr.lip6.move.coloane.api.camiObject.SpecialMessage;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.ISessionInfo;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.session.SessionController;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.observables.IDisconnectObservable;
import fr.lip6.move.coloane.interfaces.api.observables.IReceptDialogObservable;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
public class CamiParser extends DebugParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CAMI_STRING", "NUMBER", "NEWLINE", "FIXED_LENGTH_STRING", "'SC('", "')'", "'OC('", "','", "'FC()'", "'OS('", "'TD()'", "'FA()'", "'SS()'", "'RS('", "'FS('", "'TL()'", "'VI('", "'FL()'", "'DQ()'", "'FQ()'", "'VQ('", "'CQ('", "'AQ('", "'TQ('", "'7'", "'8'", "'QQ('", "'TR('", "'WN('", "'MO('", "'KO(1,'", "'DF(-2,'", "'DR()'", "'<EOF>'", "'RQ('", "'FR('", "'ZA('", "'FE()'", "'DE('", "'DE()'", "'RT('", "'RO('", "'ME('", "'MT('", "'CN('", "'CB('", "'CA('", "'CT('", "'CM('", "'SU('", "'SI('", "'TD('", "'OB('", "'AT('", "'DB()'", "'FB()'", "'PO('", "'pO('", "'DS('", "'CE('", "'FF('", "'DC('", "'AD('", "'CD('", "'DG('"
	};
	public static final int CAMI_STRING=4;
	public static final int FIXED_LENGTH_STRING=7;
	public static final int EOF=-1;
	public static final int NUMBER=5;
	public static final int NEWLINE=6;
	public static final String[] ruleNames = new String[] {
		"invalidRule", "command", "ack_open_communication", "ack_open_connection", 
		"close_connection", "ack_open_session", "ack_suspend_current_session", 
		"ack_resume_suspend_current_session", "ack_close_current_session", 
		"interlocutor_table", "receving_menu", "menu_name", "question_add", 
		"update", "end_menu_transmission", "message_to_user", "trace_message", 
		"warning_message", "special_message", "brutal_interrupt", "ask_for_a_model", 
		"result_reception", "message_utils", "trace_message2", "warning_message2", 
		"special_message2", "result", "result_body", "textual_result", "object_designation", 
		"object_outline", "attribute_outline", "object_creation", "object_deletion", 
		"domaine_table", "modele", "modele2", "dialogue"
	};

	public int ruleLevel = 0;
	public CamiParser(TokenStream input, int port) {
		super(input, port);
		DebugEventSocketProxy proxy =
			new DebugEventSocketProxy(this, port, null);setDebugListener(proxy);
			try {
				proxy.handshake();
			}
			catch (IOException ioe) {
				reportError(ioe);
			}

	}
	public CamiParser(TokenStream input) {
		this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT);
	}
	public CamiParser(TokenStream input, DebugEventListener dbg) {
		super(input, dbg);
	}

	protected boolean evalPredicate(boolean result, String predicate) {
		dbg.semanticPredicate(result, predicate);
		return result;
	}


	public String[] getTokenNames() { return tokenNames; }
	public String getGrammarFileName() { return "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g"; }


	List<String> listOfArgs; /* liste des arguments pour la construction des objets de notification */
	List<List<String>> camiMenuList; /* liste servant a construire les objets Correspondant aux AQ */
	List<List<String>> camiUpdates; /* liste servant a construire les objets Correspondant aux TQ 7 et 8 */

	Map<String, Object> hashObservable; /* Table de hash des observables */

	ISessionInfo fkInfo; 

	IDialog dialog;
	List<String> camiDialog; /* represente une boite de dialogue */
	Map<Integer,IDialog> dialogs ;

	IMenu menu;
	List<IMenu> menuList;
	List<IUpdateItem> updates;

	/* Constructeur du parser */
	public CamiParser(TokenStream input, Map<String, Object> hm) {
		this(input);
		hashObservable = hm;
		ISessionController sc = SessionController.getInstance();
		dialogs = new HashMap<Integer,IDialog>();
	}



	// $ANTLR start command
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );
	public final void command() throws RecognitionException {
		try { dbg.enterRule("command");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(63, 3);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:64:3: ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception )
			int alt3=14;
			try { dbg.enterDecision(3);

			switch ( input.LA(1) ) {
			case 8:
			{
				alt3=1;
			}
			break;
			case 10:
			{
				alt3=2;
			}
			break;
			case 12:
			{
				alt3=3;
			}
			break;
			case 13:
			case 14:
			{
				alt3=4;
			}
			break;
			case 15:
			case 19:
			case 20:
			case 21:
			{
				alt3=4;
			}
			break;
			case 22:
			{
				alt3=5;
			}
			break;
			case 27:
			{
				int LA3_7 = input.LA(2);

				if ( (LA3_7==CAMI_STRING) ) {
					int LA3_19 = input.LA(3);

					if ( (LA3_19==11) ) {
						int LA3_23 = input.LA(4);

						if ( (LA3_23==CAMI_STRING) ) {
							int LA3_27 = input.LA(5);

							if ( (LA3_27==11) ) {
								int LA3_29 = input.LA(6);

								if ( (LA3_29==NUMBER) ) {
									alt3=14;
								}
								else if ( ((LA3_29>=28 && LA3_29<=29)) ) {
									alt3=6;
								}
								else {
									NoViableAltException nvae =
										new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 29, input);

									dbg.recognitionException(nvae);
									throw nvae;
								}
							}
							else {
								NoViableAltException nvae =
									new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 27, input);

								dbg.recognitionException(nvae);
								throw nvae;
							}
						}
						else {
							NoViableAltException nvae =
								new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 23, input);

							dbg.recognitionException(nvae);
							throw nvae;
						}
					}
					else {
						NoViableAltException nvae =
							new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 19, input);

						dbg.recognitionException(nvae);
						throw nvae;
					}
				}
				else {
					NoViableAltException nvae =
						new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 7, input);

					dbg.recognitionException(nvae);
					throw nvae;
				}
			}
			break;
			case EOF:
			{
				alt3=6;
			}
			break;
			case 30:
			{
				alt3=7;
			}
			break;
			case 16:
			{
				alt3=8;
			}
			break;
			case 17:
			{
				alt3=9;
			}
			break;
			case 18:
			{
				alt3=10;
			}
			break;
			case 35:
			{
				alt3=11;
			}
			break;
			case 31:
			{
				int LA3_14 = input.LA(2);

				if ( (LA3_14==CAMI_STRING) ) {
					int LA3_20 = input.LA(3);

					if ( (LA3_20==9) ) {
						alt3=12;
					}
					else {
						NoViableAltException nvae =
							new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 20, input);

						dbg.recognitionException(nvae);
						throw nvae;
					}
				}
				else {
					NoViableAltException nvae =
						new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 14, input);

					dbg.recognitionException(nvae);
					throw nvae;
				}
			}
			break;
			case 32:
			{
				int LA3_15 = input.LA(2);

				if ( (LA3_15==CAMI_STRING) ) {
					int LA3_21 = input.LA(3);

					if ( (LA3_21==9) ) {
						alt3=12;
					}
					else {
						NoViableAltException nvae =
							new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 21, input);

						dbg.recognitionException(nvae);
						throw nvae;
					}
				}
				else {
					NoViableAltException nvae =
						new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 15, input);

					dbg.recognitionException(nvae);
					throw nvae;
				}
			}
			break;
			case 33:
			{
				int LA3_16 = input.LA(2);

				if ( (LA3_16==NUMBER) ) {
					int LA3_22 = input.LA(3);

					if ( (LA3_22==11) ) {
						int LA3_26 = input.LA(4);

						if ( (LA3_26==CAMI_STRING) ) {
							int LA3_28 = input.LA(5);

							if ( (LA3_28==9) ) {
								alt3=12;
							}
							else {
								NoViableAltException nvae =
									new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 28, input);

								dbg.recognitionException(nvae);
								throw nvae;
							}
						}
						else {
							NoViableAltException nvae =
								new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 26, input);

							dbg.recognitionException(nvae);
							throw nvae;
						}
					}
					else {
						NoViableAltException nvae =
							new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 22, input);

						dbg.recognitionException(nvae);
						throw nvae;
					}
				}
				else {
					NoViableAltException nvae =
						new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 16, input);

					dbg.recognitionException(nvae);
					throw nvae;
				}
			}
			break;
			case 34:
			{
				alt3=13;
			}
			break;
			case NEWLINE:
			case 36:
			case 37:
			case 38:
			case 39:
			case 40:
			case 41:
			case 42:
			case 43:
			case 44:
			case 45:
			case 46:
			case 47:
			case 48:
			case 49:
			case 50:
			case 51:
			case 52:
			case 53:
			case 54:
			case 55:
			case 56:
			case 57:
			case 58:
			case 62:
			case 63:
			case 64:
			case 65:
			case 66:
			case 67:
			case 68:
			{
				alt3=14;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("63:3: command : ( ack_open_communication | ack_open_connection | close_connection | ack_open_session | receving_menu | ( update )* | ( end_menu_transmission )* | ack_suspend_current_session | ack_resume_suspend_current_session | ack_close_current_session | ask_for_a_model | message_to_user | brutal_interrupt | result_reception );", 3, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(3);}

			switch (alt3) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:65:3: ack_open_communication
				{
					dbg.location(65,3);
					pushFollow(FOLLOW_ack_open_communication_in_command54);
					ack_open_communication();
					_fsp--;


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:65:28: ack_open_connection
				{
					dbg.location(65,28);
					pushFollow(FOLLOW_ack_open_connection_in_command58);
					ack_open_connection();
					_fsp--;


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:65:50: close_connection
				{
					dbg.location(65,50);
					pushFollow(FOLLOW_close_connection_in_command62);
					close_connection();
					_fsp--;


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:66:5: ack_open_session
				{
					dbg.location(66,5);
					pushFollow(FOLLOW_ack_open_session_in_command68);
					ack_open_session();
					_fsp--;


				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:66:24: receving_menu
				{
					dbg.location(66,24);
					pushFollow(FOLLOW_receving_menu_in_command72);
					receving_menu();
					_fsp--;


				}
				break;
			case 6 :
				dbg.enterAlt(6);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:67:4: ( update )*
				{
					dbg.location(67,4);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:67:4: ( update )*
					try { dbg.enterSubRule(1);

					loop1:
						do {
							int alt1=2;
							try { dbg.enterDecision(1);

							int LA1_0 = input.LA(1);

							if ( (LA1_0==27) ) {
								alt1=1;
							}


							} finally {dbg.exitDecision(1);}

							switch (alt1) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:67:4: update
								{
									dbg.location(67,4);
									pushFollow(FOLLOW_update_in_command77);
									update();
									_fsp--;


								}
								break;

							default :
								break loop1;
							}
						} while (true);
					} finally {dbg.exitSubRule(1);}


				}
				break;
			case 7 :
				dbg.enterAlt(7);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:68:4: ( end_menu_transmission )*
				{
					dbg.location(68,4);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:68:4: ( end_menu_transmission )*
					try { dbg.enterSubRule(2);

					loop2:
						do {
							int alt2=2;
							try { dbg.enterDecision(2);

							int LA2_0 = input.LA(1);

							if ( (LA2_0==30) ) {
								alt2=1;
							}


							} finally {dbg.exitDecision(2);}

							switch (alt2) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:68:4: end_menu_transmission
								{
									dbg.location(68,4);
									pushFollow(FOLLOW_end_menu_transmission_in_command83);
									end_menu_transmission();
									_fsp--;


								}
								break;

							default :
								break loop2;
							}
						} while (true);
					} finally {dbg.exitSubRule(2);}


				}
				break;
			case 8 :
				dbg.enterAlt(8);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:70:4: ack_suspend_current_session
				{
					dbg.location(70,4);
					pushFollow(FOLLOW_ack_suspend_current_session_in_command90);
					ack_suspend_current_session();
					_fsp--;


				}
				break;
			case 9 :
				dbg.enterAlt(9);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:71:4: ack_resume_suspend_current_session
				{
					dbg.location(71,4);
					pushFollow(FOLLOW_ack_resume_suspend_current_session_in_command95);
					ack_resume_suspend_current_session();
					_fsp--;


				}
				break;
			case 10 :
				dbg.enterAlt(10);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:72:4: ack_close_current_session
				{
					dbg.location(72,4);
					pushFollow(FOLLOW_ack_close_current_session_in_command100);
					ack_close_current_session();
					_fsp--;


				}
				break;
			case 11 :
				dbg.enterAlt(11);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:74:4: ask_for_a_model
				{
					dbg.location(74,4);
					pushFollow(FOLLOW_ask_for_a_model_in_command106);
					ask_for_a_model();
					_fsp--;


				}
				break;
			case 12 :
				dbg.enterAlt(12);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:75:4: message_to_user
				{
					dbg.location(75,4);
					pushFollow(FOLLOW_message_to_user_in_command111);
					message_to_user();
					_fsp--;


				}
				break;
			case 13 :
				dbg.enterAlt(13);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:76:4: brutal_interrupt
				{
					dbg.location(76,4);
					pushFollow(FOLLOW_brutal_interrupt_in_command116);
					brutal_interrupt();
					_fsp--;


				}
				break;
			case 14 :
				dbg.enterAlt(14);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:77:4: result_reception
				{
					dbg.location(77,4);
					pushFollow(FOLLOW_result_reception_in_command122);
					result_reception();
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
		dbg.location(78, 3);

		}
		finally {
			dbg.exitRule("command");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end command


	// $ANTLR start ack_open_communication
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:81:3: ack_open_communication : 'SC(' CAMI_STRING ')' ;
	public final void ack_open_communication() throws RecognitionException {
		Token CAMI_STRING1=null;

		try { dbg.enterRule("ack_open_communication");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(81, 3);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:82:3: ( 'SC(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:83:3: 'SC(' CAMI_STRING ')'
			{
				dbg.location(83,3);
				match(input,8,FOLLOW_8_in_ack_open_communication143); 
				dbg.location(83,9);
				CAMI_STRING1=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_communication145); 
				dbg.location(83,21);
				match(input,9,FOLLOW_9_in_ack_open_communication147); 
				dbg.location(83,24);

				listOfArgs = new ArrayList<String>();
				listOfArgs.add(CAMI_STRING1.getText());
				synchronized(hashObservable) {
					hashObservable.notify();
				}


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(90, 3);

		}
		finally {
			dbg.exitRule("ack_open_communication");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ack_open_communication


	// $ANTLR start ack_open_connection
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:93:3: ack_open_connection : 'OC(' v1= NUMBER ',' v2= NUMBER ')' ;
	public final void ack_open_connection() throws RecognitionException {
		Token v1=null;
		Token v2=null;

		try { dbg.enterRule("ack_open_connection");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(93, 3);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:94:3: ( 'OC(' v1= NUMBER ',' v2= NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:95:3: 'OC(' v1= NUMBER ',' v2= NUMBER ')'
			{
				dbg.location(95,3);
				match(input,10,FOLLOW_10_in_ack_open_connection169); 
				dbg.location(96,5);
				v1=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection176); 
				dbg.location(96,13);

				listOfArgs.add(v1.getText());

				dbg.location(99,3);
				match(input,11,FOLLOW_11_in_ack_open_connection183); 
				dbg.location(100,5);
				v2=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_ack_open_connection190); 
				dbg.location(100,13);
				listOfArgs.add(v2.getText());
				IConnectionInfo version = CamiObjectBuilder.buildFkVersion(listOfArgs);
				synchronized(hashObservable) {
					hashObservable.notify();
				}

				dbg.location(106,3);
				match(input,9,FOLLOW_9_in_ack_open_connection196); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(107, 3);

		}
		finally {
			dbg.exitRule("ack_open_connection");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ack_open_connection


	// $ANTLR start close_connection
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:111:3: close_connection : 'FC()' ;
	public final void close_connection() throws RecognitionException {
		try { dbg.enterRule("close_connection");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(111, 3);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:112:3: ( 'FC()' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:113:3: 'FC()'
			{
				dbg.location(113,3);
				match(input,12,FOLLOW_12_in_close_connection222); 
				dbg.location(113,9);

				((IDisconnectObservable)hashObservable.get("IDisconnect")).notifyObservers();  


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(116, 3);

		}
		finally {
			dbg.exitRule("close_connection");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end close_connection


	// $ANTLR start ack_open_session
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:118:3: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );
	public final void ack_open_session() throws RecognitionException {
		try { dbg.enterRule("ack_open_session");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(118, 3);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:119:3: ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table )
			int alt4=4;
			try { dbg.enterDecision(4);

			switch ( input.LA(1) ) {
			case 13:
			{
				alt4=1;
			}
			break;
			case 14:
			{
				alt4=2;
			}
			break;
			case 15:
			{
				alt4=3;
			}
			break;
			case 19:
			case 20:
			case 21:
			{
				alt4=4;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("118:3: ack_open_session : ( 'OS(' CAMI_STRING ')' | 'TD()' | 'FA()' | interlocutor_table );", 4, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(4);}

			switch (alt4) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:120:3: 'OS(' CAMI_STRING ')'
				{
					dbg.location(120,3);
					match(input,13,FOLLOW_13_in_ack_open_session243); 
					dbg.location(120,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_open_session245); 
					dbg.location(120,20);
					match(input,9,FOLLOW_9_in_ack_open_session246); 
					dbg.location(120,23);

					//TODO ajouter un controle que c OS
					System.out.println("je parse le OS");

					/* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
					menuList = new ArrayList<IMenu>();
					/*  */
					camiUpdates = new ArrayList<ArrayList<String>>();


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:129:4: 'TD()'
				{
					dbg.location(129,4);
					match(input,14,FOLLOW_14_in_ack_open_session252); 
					dbg.location(129,10);

					// Ajouter un controle qu'on doit bien recevoir TD
					System.out.println("je parse le TD");


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:133:4: 'FA()'
				{
					dbg.location(133,4);
					match(input,15,FOLLOW_15_in_ack_open_session258); 
					dbg.location(133,10);
					// Ajouter un controle qu'on doit bien recevoir FA}
					System.out.println("je parse le FA");


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:137:3: interlocutor_table
				{
					dbg.location(137,3);
					pushFollow(FOLLOW_interlocutor_table_in_ack_open_session264);
					interlocutor_table();
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
		dbg.location(139, 2);

		}
		finally {
			dbg.exitRule("ack_open_session");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ack_open_session


	// $ANTLR start ack_suspend_current_session
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:142:2: ack_suspend_current_session : 'SS()' ;
	public final void ack_suspend_current_session() throws RecognitionException {
		try { dbg.enterRule("ack_suspend_current_session");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(142, 2);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:143:2: ( 'SS()' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:144:2: 'SS()'
			{
				dbg.location(144,2);
				match(input,16,FOLLOW_16_in_ack_suspend_current_session286); 
				dbg.location(144,8);
				/* Notifier au sessionController de l'acquittement du SS  */
				sc.notifyEndSuspendSession();


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(147, 1);

		}
		finally {
			dbg.exitRule("ack_suspend_current_session");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ack_suspend_current_session


	// $ANTLR start ack_resume_suspend_current_session
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:152:1: ack_resume_suspend_current_session : 'RS(' CAMI_STRING ')' ;
	public final void ack_resume_suspend_current_session() throws RecognitionException {
		Token CAMI_STRING2=null;

		try { dbg.enterRule("ack_resume_suspend_current_session");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(152, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:153:1: ( 'RS(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:154:1: 'RS(' CAMI_STRING ')'
			{
				dbg.location(154,1);
				match(input,17,FOLLOW_17_in_ack_resume_suspend_current_session302); 
				dbg.location(154,7);
				CAMI_STRING2=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session304); 
				dbg.location(154,19);
				match(input,9,FOLLOW_9_in_ack_resume_suspend_current_session306); 
				dbg.location(154,22);

				sc.notifyEndResumeSession(CAMI_STRING2.getText());


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(157, 1);

		}
		finally {
			dbg.exitRule("ack_resume_suspend_current_session");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ack_resume_suspend_current_session


	// $ANTLR start ack_close_current_session
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:161:1: ack_close_current_session : 'FS(' CAMI_STRING ')' ;
	public final void ack_close_current_session() throws RecognitionException {
		Token CAMI_STRING3=null;

		try { dbg.enterRule("ack_close_current_session");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(161, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:162:1: ( 'FS(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:163:1: 'FS(' CAMI_STRING ')'
			{
				dbg.location(163,1);
				match(input,18,FOLLOW_18_in_ack_close_current_session321); 
				dbg.location(163,7);
				CAMI_STRING3=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_ack_close_current_session323); 
				dbg.location(163,19);
				match(input,9,FOLLOW_9_in_ack_close_current_session325); 
				dbg.location(163,22);

				sc.notifyEndCloseSession();
				//  ((ICloseSessionObservable)hashObservable.get("ICloseSession")).notifyObservers(CAMI_STRING3.getText());  


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(167, 1);

		}
		finally {
			dbg.exitRule("ack_close_current_session");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ack_close_current_session


	// $ANTLR start interlocutor_table
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:173:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );
	public final void interlocutor_table() throws RecognitionException {
		Token service_name=null;
		Token about_service=null;
		Token incremental=null;
		Token new_model=null;

		try { dbg.enterRule("interlocutor_table");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(173, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:174:1: ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' )
			int alt5=3;
			try { dbg.enterDecision(5);

			switch ( input.LA(1) ) {
			case 19:
			{
				alt5=1;
			}
			break;
			case 20:
			{
				alt5=2;
			}
			break;
			case 21:
			{
				alt5=3;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("173:1: interlocutor_table : ( 'TL()' | 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')' | 'FL()' );", 5, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(5);}

			switch (alt5) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:175:1: 'TL()'
				{
					dbg.location(175,1);
					match(input,19,FOLLOW_19_in_interlocutor_table340); 
					dbg.location(175,7);

					System.out.println("je parse le TL");           


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:178:2: 'VI(' service_name= CAMI_STRING ',' about_service= CAMI_STRING ',' incremental= NUMBER ',' new_model= NUMBER ')'
				{
					dbg.location(178,2);
					match(input,20,FOLLOW_20_in_interlocutor_table344); 
					dbg.location(178,20);
					service_name=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table348); 
					dbg.location(178,33);
					match(input,11,FOLLOW_11_in_interlocutor_table350); 
					dbg.location(178,50);
					about_service=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_interlocutor_table354); 
					dbg.location(178,63);
					match(input,11,FOLLOW_11_in_interlocutor_table356); 
					dbg.location(178,78);
					incremental=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table360); 
					dbg.location(179,18);
					match(input,11,FOLLOW_11_in_interlocutor_table365); 
					dbg.location(179,31);
					new_model=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_interlocutor_table369); 
					dbg.location(179,67);
					match(input,9,FOLLOW_9_in_interlocutor_table372); 
					dbg.location(179,71);


					listOfArgs = new ArrayList<String>();
					listOfArgs.add(service_name.getText());
					listOfArgs.add(about_service.getText());
					listOfArgs.add(incremental.getText());
					listOfArgs.add(new_model.getText()); 
					System.out.println("je parse le VI");           



				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:189:2: 'FL()'
				{
					dbg.location(189,2);
					match(input,21,FOLLOW_21_in_interlocutor_table377); 
					dbg.location(189,8);

					fkInfo = CamiObjectBuilder.buildFkInfo(listOfArgs);
					sc.notifyReceptSessionInfo(fkInfo);
					System.out.println("je parse le FL");          
					//            System.out.println("fkinfo");
					for(int i=0; i<this.listOfArgs.size(); i++){
						//              System.out.println(this.listOfArgs.get(i));
					}



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
		dbg.location(199, 1);

		}
		finally {
			dbg.exitRule("interlocutor_table");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end interlocutor_table


	// $ANTLR start receving_menu
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:204:1: receving_menu : 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' ;
	public final void receving_menu() throws RecognitionException {
		try { dbg.enterRule("receving_menu");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(204, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:205:1: ( 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:206:1: 'DQ()' menu_name ( question_add )* 'FQ()' 'VQ(' CAMI_STRING ')'
			{
				dbg.location(206,1);
				match(input,22,FOLLOW_22_in_receving_menu391); 
				dbg.location(206,7);

				/* creer la menuList  */
				camiMenuList = new ArrayList<ArrayList<String>>();
				System.out.println("je parse le DQ");

				dbg.location(211,1);
				pushFollow(FOLLOW_menu_name_in_receving_menu394);
				menu_name();
				_fsp--;

				dbg.location(212,1);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:212:1: ( question_add )*
				try { dbg.enterSubRule(6);

				loop6:
					do {
						int alt6=2;
						try { dbg.enterDecision(6);

						int LA6_0 = input.LA(1);

						if ( (LA6_0==26) ) {
							alt6=1;
						}


						} finally {dbg.exitDecision(6);}

						switch (alt6) {
						case 1 :
							dbg.enterAlt(1);

							// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:212:1: question_add
							{
								dbg.location(212,1);
								pushFollow(FOLLOW_question_add_in_receving_menu396);
								question_add();
								_fsp--;


							}
							break;

						default :
							break loop6;
						}
					} while (true);
				} finally {dbg.exitSubRule(6);}

				dbg.location(213,1);
				match(input,23,FOLLOW_23_in_receving_menu399); 
				dbg.location(213,7);

				/* fin de la reception des menus : demander la construction du menu */            
				menu = CamiObjectBuilder.buildMenu(camiMenuList);

				System.out.println("nombre de AQ : " + camiMenuList.size());
				System.out.println("je parse le FQ");
				menuList.add(menu);

				//            System.out.println("Menu construit");
				//            System.out.println("FQ");

				dbg.location(224,1);
				match(input,24,FOLLOW_24_in_receving_menu402); 
				dbg.location(224,6);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_receving_menu403); 
				dbg.location(224,17);
				match(input,9,FOLLOW_9_in_receving_menu404); 
				dbg.location(224,20);
				/* afficher les questions */
				System.out.println("je parse le VQ");



			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(228, 1);

		}
		finally {
			dbg.exitRule("receving_menu");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end receving_menu


	// $ANTLR start menu_name
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:233:1: menu_name : 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' ;
	public final void menu_name() throws RecognitionException {
		Token name=null;
		Token question_type=null;
		Token question_behavior=null;

		try { dbg.enterRule("menu_name");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(233, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:234:1: ( 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:235:1: 'CQ(' name= CAMI_STRING ',' question_type= NUMBER ',' question_behavior= NUMBER ')'
			{
				dbg.location(235,1);
				match(input,25,FOLLOW_25_in_menu_name418); 
				dbg.location(235,11);
				name=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_menu_name422); 
				dbg.location(235,24);
				match(input,11,FOLLOW_11_in_menu_name424); 
				dbg.location(235,41);
				question_type=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_menu_name428); 
				dbg.location(235,49);
				match(input,11,FOLLOW_11_in_menu_name430); 
				dbg.location(235,70);
				question_behavior=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_menu_name434); 
				dbg.location(235,78);
				match(input,9,FOLLOW_9_in_menu_name436); 
				dbg.location(235,81);


				// TODO :  Veifier qu'on est dans la reception de menus racine !!!

				/* racine des question  */
				ArrayList<String> cq = new ArrayList<String>();
				cq.add(name.getText()); /* racine  */
				cq.add(question_type.getText()); /* type de la question  */
				cq.add(question_behavior.getText()); /* type du choix */

				camiMenuList.add(cq); /* ajouter a la liste des AQ */
				System.out.println("je parse le CQ");
				//                        System.out.println("name.getText() " + name.getText() );
				//                        System.out.println("question_type.getText() " + question_type.getText());
				//                        System.out.println("question_behavior.getText() " + question_behavior.getText());



			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(252, 1);

		}
		finally {
			dbg.exitRule("menu_name");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end menu_name


	// $ANTLR start question_add
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:256:1: question_add : 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' ;
	public final void question_add() throws RecognitionException {
		Token parent_menu=null;
		Token entry_name=null;
		Token question_type=null;
		Token question_behavior=null;
		Token set_item=null;
		Token dialog=null;
		Token stop_authorized=null;
		Token output_formalism=null;
		Token active=null;

		try { dbg.enterRule("question_add");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(256, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:257:1: ( 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:258:1: 'AQ(' parent_menu= CAMI_STRING ',' entry_name= CAMI_STRING ',' (question_type= NUMBER )? ',' (question_behavior= NUMBER )? ',' (set_item= NUMBER )? ',' (dialog= NUMBER )? ',' (stop_authorized= NUMBER )? ',' (output_formalism= CAMI_STRING )? ',' (active= NUMBER )? ')'
			{
				dbg.location(258,1);
				match(input,26,FOLLOW_26_in_question_add449); 
				dbg.location(258,18);
				parent_menu=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add453); 
				dbg.location(258,31);
				match(input,11,FOLLOW_11_in_question_add455); 
				dbg.location(258,45);
				entry_name=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add459); 
				dbg.location(258,58);
				match(input,11,FOLLOW_11_in_question_add461); 
				dbg.location(259,14);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:259:14: (question_type= NUMBER )?
				int alt7=2;
				try { dbg.enterSubRule(7);
				try { dbg.enterDecision(7);

				int LA7_0 = input.LA(1);

				if ( (LA7_0==NUMBER) ) {
					alt7=1;
				}
				} finally {dbg.exitDecision(7);}

				switch (alt7) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:259:14: question_type= NUMBER
					{
						dbg.location(259,14);
						question_type=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_question_add466); 

					}
					break;

				}
				} finally {dbg.exitSubRule(7);}

				dbg.location(259,23);
				match(input,11,FOLLOW_11_in_question_add469); 
				dbg.location(259,44);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:259:44: (question_behavior= NUMBER )?
				int alt8=2;
				try { dbg.enterSubRule(8);
				try { dbg.enterDecision(8);

				int LA8_0 = input.LA(1);

				if ( (LA8_0==NUMBER) ) {
					alt8=1;
				}
				} finally {dbg.exitDecision(8);}

				switch (alt8) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:259:44: question_behavior= NUMBER
					{
						dbg.location(259,44);
						question_behavior=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_question_add473); 

					}
					break;

				}
				} finally {dbg.exitSubRule(8);}

				dbg.location(259,53);
				match(input,11,FOLLOW_11_in_question_add476); 
				dbg.location(260,9);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:9: (set_item= NUMBER )?
				int alt9=2;
				try { dbg.enterSubRule(9);
				try { dbg.enterDecision(9);

				int LA9_0 = input.LA(1);

				if ( (LA9_0==NUMBER) ) {
					alt9=1;
				}
				} finally {dbg.exitDecision(9);}

				switch (alt9) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:9: set_item= NUMBER
					{
						dbg.location(260,9);
						set_item=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_question_add481); 

					}
					break;

				}
				} finally {dbg.exitSubRule(9);}

				dbg.location(260,18);
				match(input,11,FOLLOW_11_in_question_add484); 
				dbg.location(260,29);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:29: (dialog= NUMBER )?
				int alt10=2;
				try { dbg.enterSubRule(10);
				try { dbg.enterDecision(10);

				int LA10_0 = input.LA(1);

				if ( (LA10_0==NUMBER) ) {
					alt10=1;
				}
				} finally {dbg.exitDecision(10);}

				switch (alt10) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:29: dialog= NUMBER
					{
						dbg.location(260,29);
						dialog=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_question_add489); 

					}
					break;

				}
				} finally {dbg.exitSubRule(10);}

				dbg.location(260,38);
				match(input,11,FOLLOW_11_in_question_add492); 
				dbg.location(260,57);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:57: (stop_authorized= NUMBER )?
				int alt11=2;
				try { dbg.enterSubRule(11);
				try { dbg.enterDecision(11);

				int LA11_0 = input.LA(1);

				if ( (LA11_0==NUMBER) ) {
					alt11=1;
				}
				} finally {dbg.exitDecision(11);}

				switch (alt11) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:260:57: stop_authorized= NUMBER
					{
						dbg.location(260,57);
						stop_authorized=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_question_add496); 

					}
					break;

				}
				} finally {dbg.exitSubRule(11);}

				dbg.location(260,66);
				match(input,11,FOLLOW_11_in_question_add499); 
				dbg.location(261,17);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:17: (output_formalism= CAMI_STRING )?
				int alt12=2;
				try { dbg.enterSubRule(12);
				try { dbg.enterDecision(12);

				int LA12_0 = input.LA(1);

				if ( (LA12_0==CAMI_STRING) ) {
					alt12=1;
				}
				} finally {dbg.exitDecision(12);}

				switch (alt12) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:17: output_formalism= CAMI_STRING
					{
						dbg.location(261,17);
						output_formalism=(Token)input.LT(1);
						match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_question_add504); 

					}
					break;

				}
				} finally {dbg.exitSubRule(12);}

				dbg.location(261,31);
				match(input,11,FOLLOW_11_in_question_add507); 
				dbg.location(261,41);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:41: (active= NUMBER )?
				int alt13=2;
				try { dbg.enterSubRule(13);
				try { dbg.enterDecision(13);

				int LA13_0 = input.LA(1);

				if ( (LA13_0==NUMBER) ) {
					alt13=1;
				}
				} finally {dbg.exitDecision(13);}

				switch (alt13) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:261:41: active= NUMBER
					{
						dbg.location(261,41);
						active=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_question_add511); 

					}
					break;

				}
				} finally {dbg.exitSubRule(13);}

				dbg.location(261,50);
				match(input,9,FOLLOW_9_in_question_add514); 
				dbg.location(261,53);

				System.out.println("je parse le AQ");
				// TODO Veifier qu'on est dans la reception de menus
				ArrayList<String> aq = new ArrayList<String>();
				aq.add(parent_menu.getText()); /* parent  */
				aq.add(entry_name.getText());  /* entry_name  */

				if(question_type != null)
					aq.add(question_type.getText()); /* question_type  */
				else
					aq.add(null/*new String()*/);

				if(question_behavior != null)
					aq.add(question_behavior.getText()); /* question_behavior  */
				else
					aq.add(null/*new String("")*/);

				if(set_item != null)
					aq.add(set_item.getText()); /* validation par defaut  */
				else
					aq.add(null/*new String("")*/);

				if(dialog != null)
					aq.add(dialog.getText()); /* dialog autorise ?  */
				else
					aq.add(null/*new String("")*/);


				if(stop_authorized != null)
					aq.add(stop_authorized.getText()); /* on autorise l'arret du service ? */
				else
					aq.add(null/*new String("")*/);


				if(output_formalism != null)
					aq.add(output_formalism.getText()); /* formalisme */
				else
					aq.add(null/*new String("")*/);


				if(active != null)
					aq.add(active.getText()); /* grise ou non ? */
				else
					aq.add(null/*new String("")*/);


				camiMenuList.add(aq); /* ajouter a la liste de AQ */


				/* TODO : a enlever */

				//                        System.out.print("AQ(" + aq.get(0));
				for(int i=1; i<9; i++){
					//                            System.out.print(", " + aq.get(i));
				}
				//                        System.out.println(")");



			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(319, 1);

		}
		finally {
			dbg.exitRule("question_add");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end question_add


	// $ANTLR start update
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:323:1: update : 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' ;
	public final void update() throws RecognitionException {
		Token service_name=null;
		Token question_name=null;
		Token state=null;
		Token mess=null;

		try { dbg.enterRule("update");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(323, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:324:1: ( 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:325:1: 'TQ(' service_name= CAMI_STRING ',' question_name= CAMI_STRING ',' state= ( '7' | '8' ) ',' (mess= CAMI_STRING )? ')'
			{
				dbg.location(325,1);
				match(input,27,FOLLOW_27_in_update532); 
				dbg.location(325,19);
				service_name=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update536); 
				dbg.location(325,32);
				match(input,11,FOLLOW_11_in_update538); 
				dbg.location(325,49);
				question_name=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update542); 
				dbg.location(325,62);
				match(input,11,FOLLOW_11_in_update544); 
				dbg.location(325,71);
				state=(Token)input.LT(1);
				if ( (input.LA(1)>=28 && input.LA(1)<=29) ) {
					input.consume();
					errorRecovery=false;
				}
				else {
					MismatchedSetException mse =
						new MismatchedSetException(null,input);
					dbg.recognitionException(mse);
					recoverFromMismatchedSet(input,mse,FOLLOW_set_in_update548);    throw mse;
				}

				dbg.location(325,82);
				match(input,11,FOLLOW_11_in_update554); 
				dbg.location(325,90);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:325:90: (mess= CAMI_STRING )?
						int alt14=2;
				try { dbg.enterSubRule(14);
				try { dbg.enterDecision(14);

				int LA14_0 = input.LA(1);

				if ( (LA14_0==CAMI_STRING) ) {
					alt14=1;
				}
				} finally {dbg.exitDecision(14);}

				switch (alt14) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:325:90: mess= CAMI_STRING
					{
						dbg.location(325,90);
						mess=(Token)input.LT(1);
						match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_update558); 

					}
					break;

				}
				} finally {dbg.exitSubRule(14);}

				dbg.location(325,104);
				match(input,9,FOLLOW_9_in_update561); 
				dbg.location(325,107);



				/*  */
				ArrayList<String> update = new ArrayList<String>();

				update.add(service_name.getText()); /* nom du service */
				update.add(question_name.getText());  /* nom de la question  */
				update.add(state.getText());  /* etat de la question  */

				if(!state.getText().equals("7") && !state.getText().equals("8")) /* si c'est un modificateur de menu */
					update.add(mess.getText()); /* message : optionnel */          


				camiUpdates.add(update);/* ajouter a la liste des updates  */

				System.out.println("je parse le TQ 7 ou 8");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(343, 1);

		}
		finally {
			dbg.exitRule("update");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end update


	// $ANTLR start end_menu_transmission
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:347:1: end_menu_transmission : 'QQ(' NUMBER ')' ;
	public final void end_menu_transmission() throws RecognitionException {
		Token NUMBER4=null;

		try { dbg.enterRule("end_menu_transmission");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(347, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:348:1: ( 'QQ(' NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:349:1: 'QQ(' NUMBER ')'
			{
				dbg.location(349,1);
				match(input,30,FOLLOW_30_in_end_menu_transmission581); 
				dbg.location(349,7);
				NUMBER4=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_end_menu_transmission583); 
				dbg.location(349,14);
				match(input,9,FOLLOW_9_in_end_menu_transmission585); 
				dbg.location(349,17);


				System.out.println("QQ((((" + NUMBER4.getText() + ")");
				if(NUMBER4.getText().equals("3")){
					sc.notifyEndOpenSession();
					updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
					((ISessionObservable)hashObservable.get("ISession")).notifyObservers( menuList, updates);
					camiUpdates = null;
					camiUpdates = new ArrayList<ArrayList<String>>();

				}
				else {
					System.out.println("je parse eeeeeeeeQQ2");
					updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
					((ISessionObservable)hashObservable.get("ISession")).notifyObservers( null, updates);

				}



			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(368, 1);

		}
		finally {
			dbg.exitRule("end_menu_transmission");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end end_menu_transmission


	// $ANTLR start message_to_user
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:378:1: message_to_user : ( trace_message | warning_message | special_message );
	public final void message_to_user() throws RecognitionException {
		try { dbg.enterRule("message_to_user");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(378, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:379:1: ( trace_message | warning_message | special_message )
			int alt15=3;
			try { dbg.enterDecision(15);

			switch ( input.LA(1) ) {
			case 31:
			{
				alt15=1;
			}
			break;
			case 32:
			{
				alt15=2;
			}
			break;
			case 33:
			{
				alt15=3;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("378:1: message_to_user : ( trace_message | warning_message | special_message );", 15, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(15);}

			switch (alt15) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:1: trace_message
				{
					dbg.location(380,1);
					pushFollow(FOLLOW_trace_message_in_message_to_user604);
					trace_message();
					_fsp--;


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:17: warning_message
				{
					dbg.location(380,17);
					pushFollow(FOLLOW_warning_message_in_message_to_user608);
					warning_message();
					_fsp--;


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:380:35: special_message
				{
					dbg.location(380,35);
					pushFollow(FOLLOW_special_message_in_message_to_user612);
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
		dbg.location(381, 1);

		}
		finally {
			dbg.exitRule("message_to_user");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end message_to_user


	// $ANTLR start trace_message
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:383:1: trace_message : 'TR(' CAMI_STRING ')' ;
	public final void trace_message() throws RecognitionException {
		Token CAMI_STRING5=null;

		try { dbg.enterRule("trace_message");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(383, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:384:1: ( 'TR(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:385:1: 'TR(' CAMI_STRING ')'
			{
				dbg.location(385,1);
				match(input,31,FOLLOW_31_in_trace_message621); 
				dbg.location(385,7);
				CAMI_STRING5=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message623); 
				dbg.location(385,19);
				match(input,9,FOLLOW_9_in_trace_message625); 
				dbg.location(385,22);

				ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(2,CAMI_STRING5.getText());
				((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
				System.out.println("je parse le TR");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(390, 1);

		}
		finally {
			dbg.exitRule("trace_message");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end trace_message


	// $ANTLR start warning_message
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:392:1: warning_message : 'WN(' CAMI_STRING ')' ;
	public final void warning_message() throws RecognitionException {
		Token CAMI_STRING6=null;

		try { dbg.enterRule("warning_message");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(392, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:393:1: ( 'WN(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:394:1: 'WN(' CAMI_STRING ')'
			{
				dbg.location(394,1);
				match(input,32,FOLLOW_32_in_warning_message635); 
				dbg.location(394,7);
				CAMI_STRING6=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message637); 
				dbg.location(394,19);
				match(input,9,FOLLOW_9_in_warning_message639); 
				dbg.location(394,22);

				ISpecialMessage msg =(ISpecialMessage) new SpecialMessage(1,CAMI_STRING6.getText());
				((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
				//    ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING6.getText());
				System.out.println("je parse le WN");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(400, 1);

		}
		finally {
			dbg.exitRule("warning_message");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end warning_message


	// $ANTLR start special_message
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:402:1: special_message : 'MO(' NUMBER ',' CAMI_STRING ')' ;
	public final void special_message() throws RecognitionException {
		Token CAMI_STRING7=null;

		try { dbg.enterRule("special_message");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(402, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:403:1: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:404:1: 'MO(' NUMBER ',' CAMI_STRING ')'
			{
				dbg.location(404,1);
				match(input,33,FOLLOW_33_in_special_message650); 
				dbg.location(404,7);
				match(input,NUMBER,FOLLOW_NUMBER_in_special_message652); 
				dbg.location(404,14);
				match(input,11,FOLLOW_11_in_special_message654); 
				dbg.location(404,18);
				CAMI_STRING7=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message656); 
				dbg.location(404,30);
				match(input,9,FOLLOW_9_in_special_message658); 
				dbg.location(404,33);

				ISpecialMessage msg =(ISpecialMessage) new SpecialMessage(1,CAMI_STRING7.getText());
				((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
				//   ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING7.getText());            
				System.out.println("je parse le MO");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(410, 1);

		}
		finally {
			dbg.exitRule("special_message");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end special_message


	// $ANTLR start brutal_interrupt
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:416:1: brutal_interrupt : 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' ;
	public final void brutal_interrupt() throws RecognitionException {
		Token mess=null;
		Token level=null;

		try { dbg.enterRule("brutal_interrupt");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(416, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:417:1: ( 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:418:1: 'KO(1,' mess= CAMI_STRING ',' level= NUMBER ')'
			{
				dbg.location(418,1);
				match(input,34,FOLLOW_34_in_brutal_interrupt673); 
				dbg.location(418,13);
				mess=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_brutal_interrupt677); 
				dbg.location(418,26);
				match(input,11,FOLLOW_11_in_brutal_interrupt679); 
				dbg.location(418,35);
				level=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_brutal_interrupt683); 
				dbg.location(418,43);
				match(input,9,FOLLOW_9_in_brutal_interrupt685); 
				dbg.location(418,46);

				System.out.println("je parse le KO");
				//((IBrutalInterruptObservable)hashObservable.get("IBrutalInterrupt")).notifyObservers(mess.getText());


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(422, 1);

		}
		finally {
			dbg.exitRule("brutal_interrupt");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end brutal_interrupt


	// $ANTLR start ask_for_a_model
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:426:1: ask_for_a_model : 'DF(-2,' NUMBER ',' NUMBER ')' ;
	public final void ask_for_a_model() throws RecognitionException {
		try { dbg.enterRule("ask_for_a_model");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(426, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:427:1: ( 'DF(-2,' NUMBER ',' NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:428:1: 'DF(-2,' NUMBER ',' NUMBER ')'
			{
				dbg.location(428,1);
				match(input,35,FOLLOW_35_in_ask_for_a_model753); 
				dbg.location(428,10);
				match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model755); 
				dbg.location(428,17);
				match(input,11,FOLLOW_11_in_ask_for_a_model757); 
				dbg.location(428,21);
				match(input,NUMBER,FOLLOW_NUMBER_in_ask_for_a_model759); 
				dbg.location(428,28);
				match(input,9,FOLLOW_9_in_ask_for_a_model761); 
				dbg.location(428,31);

				System.out.println("je parse le DF");
				sc.notifyWaitingForModel();
				//    ((IAskForModelObservable)hashObservable.get("IAskForModel")).notifyObservers();


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(433, 1);

		}
		finally {
			dbg.exitRule("ask_for_a_model");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end ask_for_a_model


	// $ANTLR start result_reception
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:436:1: result_reception : ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' );
	public final void result_reception() throws RecognitionException {
		Token service_name1=null;
		Token question_name1=null;
		Token num1=null;
		Token service_name2=null;
		Token question_name2=null;
		Token state2=null;
		Token mess2=null;

		try { dbg.enterRule("result_reception");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(436, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:440:1: ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' )
			int alt24=10;
			try { dbg.enterDecision(24);

			try {
				isCyclicDecision = true;
				alt24 = dfa24.predict(input);
			}
			catch (NoViableAltException nvae) {
				dbg.recognitionException(nvae);
				throw nvae;
			}
			} finally {dbg.exitDecision(24);}

			switch (alt24) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:441:1: 'DR()'
				{
					dbg.location(441,1);
					match(input,36,FOLLOW_36_in_result_reception776); 
					dbg.location(441,7);

					// initialiser la liste des updates 
					//    camiUpdates = new ArrayList<ArrayList<String>>();
					System.out.println("je parse DR");
					sc.notifyWaitingForResult();


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:447:2: ( '<EOF>' )*
				{
					dbg.location(447,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:447:2: ( '<EOF>' )*
					try { dbg.enterSubRule(16);

					loop16:
						do {
							int alt16=2;
							try { dbg.enterDecision(16);

							int LA16_0 = input.LA(1);

							if ( (LA16_0==37) ) {
								alt16=1;
							}


							} finally {dbg.exitDecision(16);}

							switch (alt16) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:447:2: '<EOF>'
								{
									dbg.location(447,2);
									match(input,37,FOLLOW_37_in_result_reception780); 

								}
								break;

							default :
								break loop16;
							}
						} while (true);
					} finally {dbg.exitSubRule(16);}


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:448:2: 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')'
				{
					dbg.location(448,2);
					match(input,38,FOLLOW_38_in_result_reception784); 
					dbg.location(448,21);
					service_name1=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception788); 
					dbg.location(448,34);
					match(input,11,FOLLOW_11_in_result_reception790); 
					dbg.location(448,52);
					question_name1=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception794); 
					dbg.location(448,65);
					match(input,11,FOLLOW_11_in_result_reception796); 
					dbg.location(448,73);
					num1=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_result_reception800); 
					dbg.location(448,81);
					match(input,9,FOLLOW_9_in_result_reception802); 
					dbg.location(448,84);

					System.out.println("je parse RQ"); 


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:2: ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')'
				{
					dbg.location(451,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:2: ( '<EOF>' )*
					try { dbg.enterSubRule(17);

					loop17:
						do {
							int alt17=2;
							try { dbg.enterDecision(17);

							int LA17_0 = input.LA(1);

							if ( (LA17_0==37) ) {
								alt17=1;
							}


							} finally {dbg.exitDecision(17);}

							switch (alt17) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:451:2: '<EOF>'
								{
									dbg.location(451,2);
									match(input,37,FOLLOW_37_in_result_reception807); 

								}
								break;

							default :
								break loop17;
							}
						} while (true);
					} finally {dbg.exitSubRule(17);}

					dbg.location(452,1);
					match(input,27,FOLLOW_27_in_result_reception810); 
					dbg.location(452,20);
					service_name2=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception814); 
					dbg.location(452,33);
					match(input,11,FOLLOW_11_in_result_reception816); 
					dbg.location(452,51);
					question_name2=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception820); 
					dbg.location(452,64);
					match(input,11,FOLLOW_11_in_result_reception822); 
					dbg.location(452,74);
					state2=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_result_reception826); 
					dbg.location(452,111);
					match(input,11,FOLLOW_11_in_result_reception829); 
					dbg.location(452,120);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:120: (mess2= CAMI_STRING )?
							int alt18=2;
					try { dbg.enterSubRule(18);
					try { dbg.enterDecision(18);

					int LA18_0 = input.LA(1);

					if ( (LA18_0==CAMI_STRING) ) {
						alt18=1;
					}
					} finally {dbg.exitDecision(18);}

					switch (alt18) {
					case 1 :
						dbg.enterAlt(1);

						// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:452:120: mess2= CAMI_STRING
						{
							dbg.location(452,120);
							mess2=(Token)input.LT(1);
							match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result_reception833); 

						}
						break;

					}
					} finally {dbg.exitSubRule(18);}

					dbg.location(452,134);
					match(input,9,FOLLOW_9_in_result_reception836); 
					dbg.location(452,137);



					if(mess2.getText() != null){
						ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(3,mess2.getText());
						((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
						//  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
						System.out.println("je parse TQ2");
					}
					else
					{
						//     ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(3,"");
						// ((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
						//  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
						System.out.println("je parse TQ2");  


					}



				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:2: ( result )*
				{
					dbg.location(472,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:2: ( result )*
					try { dbg.enterSubRule(19);

					loop19:
						do {
							int alt19=2;
							try { dbg.enterDecision(19);

							int LA19_0 = input.LA(1);

							if ( ((LA19_0>=41 && LA19_0<=54)) ) {
								alt19=1;
							}


							} finally {dbg.exitDecision(19);}

							switch (alt19) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:472:2: result
								{
									dbg.location(472,2);
									pushFollow(FOLLOW_result_in_result_reception840);
									result();
									_fsp--;


								}
								break;

							default :
								break loop19;
							}
						} while (true);
					} finally {dbg.exitSubRule(19);}


				}
				break;
			case 6 :
				dbg.enterAlt(6);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:473:2: ( message_utils )*
				{
					dbg.location(473,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:473:2: ( message_utils )*
					try { dbg.enterSubRule(20);

					loop20:
						do {
							int alt20=2;
							try { dbg.enterDecision(20);

							int LA20_0 = input.LA(1);

							if ( (LA20_0==NEWLINE||(LA20_0>=31 && LA20_0<=33)||LA20_0==40) ) {
								alt20=1;
							}


							} finally {dbg.exitDecision(20);}

							switch (alt20) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:473:2: message_utils
								{
									dbg.location(473,2);
									pushFollow(FOLLOW_message_utils_in_result_reception844);
									message_utils();
									_fsp--;


								}
								break;

							default :
								break loop20;
							}
						} while (true);
					} finally {dbg.exitSubRule(20);}


				}
				break;
			case 7 :
				dbg.enterAlt(7);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:2: ( domaine_table )*
				{
					dbg.location(474,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:2: ( domaine_table )*
					try { dbg.enterSubRule(21);

					loop21:
						do {
							int alt21=2;
							try { dbg.enterDecision(21);

							int LA21_0 = input.LA(1);

							if ( (LA21_0==15||(LA21_0>=55 && LA21_0<=57)) ) {
								alt21=1;
							}


							} finally {dbg.exitDecision(21);}

							switch (alt21) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:474:2: domaine_table
								{
									dbg.location(474,2);
									pushFollow(FOLLOW_domaine_table_in_result_reception848);
									domaine_table();
									_fsp--;


								}
								break;

							default :
								break loop21;
							}
						} while (true);
					} finally {dbg.exitSubRule(21);}


				}
				break;
			case 8 :
				dbg.enterAlt(8);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:475:2: ( dialogue )*
				{
					dbg.location(475,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:475:2: ( dialogue )*
					try { dbg.enterSubRule(22);

					loop22:
						do {
							int alt22=2;
							try { dbg.enterDecision(22);

							int LA22_0 = input.LA(1);

							if ( (LA22_0==NEWLINE||(LA22_0>=62 && LA22_0<=68)) ) {
								alt22=1;
							}


							} finally {dbg.exitDecision(22);}

							switch (alt22) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:475:2: dialogue
								{
									dbg.location(475,2);
									pushFollow(FOLLOW_dialogue_in_result_reception852);
									dialogue();
									_fsp--;


								}
								break;

							default :
								break loop22;
							}
						} while (true);
					} finally {dbg.exitSubRule(22);}


				}
				break;
			case 9 :
				dbg.enterAlt(9);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:476:2: ( modele )*
				{
					dbg.location(476,2);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:476:2: ( modele )*
					try { dbg.enterSubRule(23);

					loop23:
						do {
							int alt23=2;
							try { dbg.enterDecision(23);

							int LA23_0 = input.LA(1);

							if ( (LA23_0==58) ) {
								alt23=1;
							}


							} finally {dbg.exitDecision(23);}

							switch (alt23) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:476:2: modele
								{
									dbg.location(476,2);
									pushFollow(FOLLOW_modele_in_result_reception856);
									modele();
									_fsp--;


								}
								break;

							default :
								break loop23;
							}
						} while (true);
					} finally {dbg.exitSubRule(23);}


				}
				break;
			case 10 :
				dbg.enterAlt(10);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:477:2: 'FR(' NUMBER ')'
				{
					dbg.location(477,2);
					match(input,39,FOLLOW_39_in_result_reception860); 
					dbg.location(477,8);
					match(input,NUMBER,FOLLOW_NUMBER_in_result_reception862); 
					dbg.location(477,15);
					match(input,9,FOLLOW_9_in_result_reception864); 
					dbg.location(477,18);

					System.out.println("je parse FR");
					sc.notifyEndResult();
					//TODO notifier Coloane  de la fin de reception des resultats et envoyer les resultats


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
		dbg.location(482, 1);

		}
		finally {
			dbg.exitRule("result_reception");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end result_reception


	// $ANTLR start message_utils
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:484:1: message_utils : ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
	public final void message_utils() throws RecognitionException {
		try { dbg.enterRule("message_utils");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(484, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:485:1: ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
			int alt25=5;
			try { dbg.enterDecision(25);

			switch ( input.LA(1) ) {
			case 31:
			{
				alt25=1;
			}
			break;
			case 32:
			{
				alt25=2;
			}
			break;
			case 33:
			{
				alt25=3;
			}
			break;
			case NEWLINE:
			{
				alt25=4;
			}
			break;
			case 40:
			{
				alt25=5;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("484:1: message_utils : ( trace_message2 | warning_message2 | special_message2 | NEWLINE | 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 25, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(25);}

			switch (alt25) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:486:1: trace_message2
				{
					dbg.location(486,1);
					pushFollow(FOLLOW_trace_message2_in_message_utils874);
					trace_message2();
					_fsp--;


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:487:3: warning_message2
				{
					dbg.location(487,3);
					pushFollow(FOLLOW_warning_message2_in_message_utils879);
					warning_message2();
					_fsp--;


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:488:3: special_message2
				{
					dbg.location(488,3);
					pushFollow(FOLLOW_special_message2_in_message_utils884);
					special_message2();
					_fsp--;


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:489:2: NEWLINE
				{
					dbg.location(489,2);
					match(input,NEWLINE,FOLLOW_NEWLINE_in_message_utils887); 

				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:490:3: 'ZA(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(490,3);
					match(input,40,FOLLOW_40_in_message_utils891); 
					dbg.location(490,8);
					match(input,NUMBER,FOLLOW_NUMBER_in_message_utils892); 
					dbg.location(490,15);
					match(input,11,FOLLOW_11_in_message_utils894); 
					dbg.location(490,19);
					match(input,NUMBER,FOLLOW_NUMBER_in_message_utils896); 
					dbg.location(490,26);
					match(input,11,FOLLOW_11_in_message_utils898); 
					dbg.location(490,30);
					match(input,NUMBER,FOLLOW_NUMBER_in_message_utils900); 
					dbg.location(490,37);
					match(input,11,FOLLOW_11_in_message_utils902); 
					dbg.location(490,41);
					match(input,NUMBER,FOLLOW_NUMBER_in_message_utils904); 
					dbg.location(490,48);
					match(input,11,FOLLOW_11_in_message_utils906); 
					dbg.location(490,52);
					match(input,NUMBER,FOLLOW_NUMBER_in_message_utils908); 
					dbg.location(490,59);
					match(input,9,FOLLOW_9_in_message_utils910); 
					dbg.location(490,62);

					ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(4,"");
					((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
					System.out.println("je parse ZA");


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
		dbg.location(495, 1);

		}
		finally {
			dbg.exitRule("message_utils");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end message_utils


	// $ANTLR start trace_message2
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:497:1: trace_message2 : 'TR(' CAMI_STRING ')' ;
	public final void trace_message2() throws RecognitionException {
		Token CAMI_STRING8=null;

		try { dbg.enterRule("trace_message2");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(497, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:498:1: ( 'TR(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:499:1: 'TR(' CAMI_STRING ')'
			{
				dbg.location(499,1);
				match(input,31,FOLLOW_31_in_trace_message2920); 
				dbg.location(499,7);
				CAMI_STRING8=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_trace_message2922); 
				dbg.location(499,19);
				match(input,9,FOLLOW_9_in_trace_message2924); 
				dbg.location(499,22);

				ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(2,CAMI_STRING8.getText());
				((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
				//  ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers(CAMI_STRING8.getText());
				System.out.println("je parse le TR");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(505, 1);

		}
		finally {
			dbg.exitRule("trace_message2");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end trace_message2


	// $ANTLR start warning_message2
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:507:1: warning_message2 : 'WN(' CAMI_STRING ')' ;
	public final void warning_message2() throws RecognitionException {
		Token CAMI_STRING9=null;

		try { dbg.enterRule("warning_message2");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(507, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:508:1: ( 'WN(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:509:1: 'WN(' CAMI_STRING ')'
			{
				dbg.location(509,1);
				match(input,32,FOLLOW_32_in_warning_message2934); 
				dbg.location(509,7);
				CAMI_STRING9=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_warning_message2936); 
				dbg.location(509,19);
				match(input,9,FOLLOW_9_in_warning_message2938); 
				dbg.location(509,22);

				ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(1,CAMI_STRING9.getText());
				((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
				// ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING9.getText());
				System.out.println("je parse le WN");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(515, 1);

		}
		finally {
			dbg.exitRule("warning_message2");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end warning_message2


	// $ANTLR start special_message2
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:517:1: special_message2 : 'MO(' NUMBER ',' CAMI_STRING ')' ;
	public final void special_message2() throws RecognitionException {
		Token CAMI_STRING10=null;

		try { dbg.enterRule("special_message2");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(517, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:518:1: ( 'MO(' NUMBER ',' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:519:1: 'MO(' NUMBER ',' CAMI_STRING ')'
			{
				dbg.location(519,1);
				match(input,33,FOLLOW_33_in_special_message2949); 
				dbg.location(519,7);
				match(input,NUMBER,FOLLOW_NUMBER_in_special_message2951); 
				dbg.location(519,14);
				match(input,11,FOLLOW_11_in_special_message2953); 
				dbg.location(519,18);
				CAMI_STRING10=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_special_message2955); 
				dbg.location(519,30);
				match(input,9,FOLLOW_9_in_special_message2957); 
				dbg.location(519,33);

				ISpecialMessage msg =(ISpecialMessage) new SpecialMessage(1,CAMI_STRING10.getText());
				((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
				//  ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers(CAMI_STRING10.getText());            
				System.out.println("je parse le MO");


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(525, 1);

		}
		finally {
			dbg.exitRule("special_message2");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end special_message2


	// $ANTLR start result
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:526:1: result : ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );
	public final void result() throws RecognitionException {
		Token ensemble_name=null;
		Token ensemble_type=null;

		try { dbg.enterRule("result");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(526, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:526:8: ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' )
			int alt28=4;
			try { dbg.enterDecision(28);

			switch ( input.LA(1) ) {
			case 44:
			case 45:
			case 46:
			case 47:
			case 48:
			case 49:
			case 50:
			case 51:
			case 52:
			case 53:
			case 54:
			{
				alt28=1;
			}
			break;
			case 41:
			{
				alt28=2;
			}
			break;
			case 42:
			{
				alt28=3;
			}
			break;
			case 43:
			{
				alt28=4;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("526:1: result : ( ( result_body )+ ( '<EOF>' )* | 'FE()' | 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')' | 'DE()' );", 28, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(28);}

			switch (alt28) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:527:1: ( result_body )+ ( '<EOF>' )*
				{
					dbg.location(527,1);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:527:1: ( result_body )+
					int cnt26=0;
					try { dbg.enterSubRule(26);

					loop26:
						do {
							int alt26=2;
							try { dbg.enterDecision(26);

							switch ( input.LA(1) ) {
							case 44:
							{
								alt26=1;
							}
							break;
							case 45:
							{
								alt26=1;
							}
							break;
							case 46:
							{
								alt26=1;
							}
							break;
							case 47:
							{
								alt26=1;
							}
							break;
							case 48:
							{
								alt26=1;
							}
							break;
							case 49:
							{
								alt26=1;
							}
							break;
							case 50:
							{
								alt26=1;
							}
							break;
							case 51:
							{
								alt26=1;
							}
							break;
							case 52:
							{
								alt26=1;
							}
							break;
							case 53:
							{
								alt26=1;
							}
							break;
							case 54:
							{
								alt26=1;
							}
							break;

							}

							} finally {dbg.exitDecision(26);}

							switch (alt26) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:527:1: result_body
								{
									dbg.location(527,1);
									pushFollow(FOLLOW_result_body_in_result966);
									result_body();
									_fsp--;


								}
								break;

							default :
								if ( cnt26 >= 1 ) break loop26;
							EarlyExitException eee =
								new EarlyExitException(26, input);
							dbg.recognitionException(eee);

							throw eee;
							}
							cnt26++;
						} while (true);
					} finally {dbg.exitSubRule(26);}

					dbg.location(528,1);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:528:1: ( '<EOF>' )*
					try { dbg.enterSubRule(27);

					loop27:
						do {
							int alt27=2;
							try { dbg.enterDecision(27);

							int LA27_0 = input.LA(1);

							if ( (LA27_0==37) ) {
								alt27=1;
							}


							} finally {dbg.exitDecision(27);}

							switch (alt27) {
							case 1 :
								dbg.enterAlt(1);

								// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:528:1: '<EOF>'
								{
									dbg.location(528,1);
									match(input,37,FOLLOW_37_in_result969); 

								}
								break;

							default :
								break loop27;
							}
						} while (true);
					} finally {dbg.exitSubRule(27);}


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:529:2: 'FE()'
				{
					dbg.location(529,2);
					match(input,41,FOLLOW_41_in_result973); 
					dbg.location(529,8);

					System.out.println("je parse FE"); 


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:532:2: 'DE(' ensemble_name= CAMI_STRING ',' ensemble_type= NUMBER ')'
				{
					dbg.location(532,2);
					match(input,42,FOLLOW_42_in_result977); 
					dbg.location(532,21);
					ensemble_name=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_result981); 
					dbg.location(532,34);
					match(input,11,FOLLOW_11_in_result983); 
					dbg.location(532,51);
					ensemble_type=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_result987); 
					dbg.location(532,59);
					match(input,9,FOLLOW_9_in_result989); 
					dbg.location(532,62);

					System.out.println("je parse DE"); 


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:535:2: 'DE()'
				{
					dbg.location(535,2);
					match(input,43,FOLLOW_43_in_result993); 
					dbg.location(535,8);

					System.out.println("je parse DE sans parametre"); 


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
		dbg.location(538, 1);

		}
		finally {
			dbg.exitRule("result");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end result


	// $ANTLR start result_body
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:540:1: result_body : ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );
	public final void result_body() throws RecognitionException {
		try { dbg.enterRule("result_body");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(540, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:541:1: ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion )
			int alt29=6;
			try { dbg.enterDecision(29);

			switch ( input.LA(1) ) {
			case 44:
			{
				alt29=1;
			}
			break;
			case 45:
			{
				alt29=2;
			}
			break;
			case 46:
			{
				alt29=3;
			}
			break;
			case 47:
			{
				alt29=4;
			}
			break;
			case 48:
			case 49:
			case 50:
			case 51:
			case 52:
			{
				alt29=5;
			}
			break;
			case 53:
			case 54:
			{
				alt29=6;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("540:1: result_body : ( textual_result | object_designation | object_outline | attribute_outline | object_creation | object_deletion );", 29, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(29);}

			switch (alt29) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:542:1: textual_result
				{
					dbg.location(542,1);
					pushFollow(FOLLOW_textual_result_in_result_body1003);
					textual_result();
					_fsp--;


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:543:3: object_designation
				{
					dbg.location(543,3);
					pushFollow(FOLLOW_object_designation_in_result_body1007);
					object_designation();
					_fsp--;


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:544:3: object_outline
				{
					dbg.location(544,3);
					pushFollow(FOLLOW_object_outline_in_result_body1011);
					object_outline();
					_fsp--;


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:545:3: attribute_outline
				{
					dbg.location(545,3);
					pushFollow(FOLLOW_attribute_outline_in_result_body1015);
					attribute_outline();
					_fsp--;


				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:546:3: object_creation
				{
					dbg.location(546,3);
					pushFollow(FOLLOW_object_creation_in_result_body1019);
					object_creation();
					_fsp--;


				}
				break;
			case 6 :
				dbg.enterAlt(6);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:547:3: object_deletion
				{
					dbg.location(547,3);
					pushFollow(FOLLOW_object_deletion_in_result_body1023);
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
		dbg.location(548, 1);

		}
		finally {
			dbg.exitRule("result_body");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end result_body


	// $ANTLR start textual_result
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:550:1: textual_result : 'RT(' CAMI_STRING ')' ;
	public final void textual_result() throws RecognitionException {
		try { dbg.enterRule("textual_result");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(550, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:551:1: ( 'RT(' CAMI_STRING ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:552:1: 'RT(' CAMI_STRING ')'
			{
				dbg.location(552,1);
				match(input,44,FOLLOW_44_in_textual_result1032); 
				dbg.location(552,7);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_textual_result1034); 
				dbg.location(552,19);
				match(input,9,FOLLOW_9_in_textual_result1036); 
				dbg.location(552,22);

				System.out.println("je parse RT"); 


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(555, 1);

		}
		finally {
			dbg.exitRule("textual_result");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end textual_result


	// $ANTLR start object_designation
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:557:1: object_designation : 'RO(' id= NUMBER ')' ;
	public final void object_designation() throws RecognitionException {
		Token id=null;

		try { dbg.enterRule("object_designation");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(557, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:558:1: ( 'RO(' id= NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:559:1: 'RO(' id= NUMBER ')'
			{
				dbg.location(559,1);
				match(input,45,FOLLOW_45_in_object_designation1046); 
				dbg.location(559,9);
				id=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_object_designation1050); 
				dbg.location(559,17);
				match(input,9,FOLLOW_9_in_object_designation1052); 
				dbg.location(559,20);

				System.out.println("je parse RO"); 


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(562, 1);

		}
		finally {
			dbg.exitRule("object_designation");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end object_designation


	// $ANTLR start object_outline
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:564:1: object_outline : 'ME(' id= NUMBER ')' ;
	public final void object_outline() throws RecognitionException {
		Token id=null;

		try { dbg.enterRule("object_outline");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(564, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:565:1: ( 'ME(' id= NUMBER ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:566:1: 'ME(' id= NUMBER ')'
			{
				dbg.location(566,1);
				match(input,46,FOLLOW_46_in_object_outline1062); 
				dbg.location(566,9);
				id=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_object_outline1066); 
				dbg.location(566,17);
				match(input,9,FOLLOW_9_in_object_outline1068); 
				dbg.location(566,20);

				System.out.println("je parse ME"); 


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(569, 1);

		}
		finally {
			dbg.exitRule("object_outline");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end object_outline


	// $ANTLR start attribute_outline
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:571:1: attribute_outline : 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' ;
	public final void attribute_outline() throws RecognitionException {
		Token id=null;
		Token attr_name=null;
		Token begin=null;
		Token end=null;

		try { dbg.enterRule("attribute_outline");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(571, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:572:1: ( 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:1: 'MT(' id= NUMBER ',' attr_name= CAMI_STRING ',' (begin= NUMBER )? ',' (end= NUMBER )? ')'
			{
				dbg.location(573,1);
				match(input,47,FOLLOW_47_in_attribute_outline1078); 
				dbg.location(573,9);
				id=(Token)input.LT(1);
				match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1082); 
				dbg.location(573,17);
				match(input,11,FOLLOW_11_in_attribute_outline1084); 
				dbg.location(573,30);
				attr_name=(Token)input.LT(1);
				match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_attribute_outline1088); 
				dbg.location(573,43);
				match(input,11,FOLLOW_11_in_attribute_outline1090); 
				dbg.location(573,52);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:52: (begin= NUMBER )?
				int alt30=2;
				try { dbg.enterSubRule(30);
				try { dbg.enterDecision(30);

				int LA30_0 = input.LA(1);

				if ( (LA30_0==NUMBER) ) {
					alt30=1;
				}
				} finally {dbg.exitDecision(30);}

				switch (alt30) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:52: begin= NUMBER
					{
						dbg.location(573,52);
						begin=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1094); 

					}
					break;

				}
				} finally {dbg.exitSubRule(30);}

				dbg.location(573,61);
				match(input,11,FOLLOW_11_in_attribute_outline1097); 
				dbg.location(573,68);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:68: (end= NUMBER )?
				int alt31=2;
				try { dbg.enterSubRule(31);
				try { dbg.enterDecision(31);

				int LA31_0 = input.LA(1);

				if ( (LA31_0==NUMBER) ) {
					alt31=1;
				}
				} finally {dbg.exitDecision(31);}

				switch (alt31) {
				case 1 :
					dbg.enterAlt(1);

					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:573:68: end= NUMBER
					{
						dbg.location(573,68);
						end=(Token)input.LT(1);
						match(input,NUMBER,FOLLOW_NUMBER_in_attribute_outline1101); 

					}
					break;

				}
				} finally {dbg.exitSubRule(31);}

				dbg.location(573,77);
				match(input,9,FOLLOW_9_in_attribute_outline1104); 
				dbg.location(573,80);

				System.out.println("je parse MT"); 


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(576, 1);

		}
		finally {
			dbg.exitRule("attribute_outline");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end attribute_outline


	// $ANTLR start object_creation
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:578:1: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );
	public final void object_creation() throws RecognitionException {
		try { dbg.enterRule("object_creation");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(578, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:579:1: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )
			int alt32=5;
			try { dbg.enterDecision(32);

			switch ( input.LA(1) ) {
			case 48:
			{
				alt32=1;
			}
			break;
			case 49:
			{
				alt32=2;
			}
			break;
			case 50:
			{
				alt32=3;
			}
			break;
			case 51:
			{
				alt32=4;
			}
			break;
			case 52:
			{
				alt32=5;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("578:1: object_creation : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' );", 32, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(32);}

			switch (alt32) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:580:1: 'CN(' CAMI_STRING ',' NUMBER ')'
				{
					dbg.location(580,1);
					match(input,48,FOLLOW_48_in_object_creation1114); 
					dbg.location(580,7);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1116); 
					dbg.location(580,19);
					match(input,11,FOLLOW_11_in_object_creation1118); 
					dbg.location(580,23);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1120); 
					dbg.location(580,30);
					match(input,9,FOLLOW_9_in_object_creation1122); 
					dbg.location(580,33);

					System.out.println("je parse CN"); 


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:583:3: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(583,3);
					match(input,49,FOLLOW_49_in_object_creation1127); 
					dbg.location(583,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1129); 
					dbg.location(583,21);
					match(input,11,FOLLOW_11_in_object_creation1131); 
					dbg.location(583,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1133); 
					dbg.location(583,32);
					match(input,11,FOLLOW_11_in_object_creation1135); 
					dbg.location(583,36);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1137); 
					dbg.location(583,43);
					match(input,9,FOLLOW_9_in_object_creation1139); 
					dbg.location(583,46);

					System.out.println("je parse CB"); 


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:586:3: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(586,3);
					match(input,50,FOLLOW_50_in_object_creation1144); 
					dbg.location(586,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1146); 
					dbg.location(586,21);
					match(input,11,FOLLOW_11_in_object_creation1148); 
					dbg.location(586,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1150); 
					dbg.location(586,32);
					match(input,11,FOLLOW_11_in_object_creation1152); 
					dbg.location(586,36);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1154); 
					dbg.location(586,43);
					match(input,11,FOLLOW_11_in_object_creation1156); 
					dbg.location(586,47);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1158); 
					dbg.location(586,54);
					match(input,9,FOLLOW_9_in_object_creation1160); 
					dbg.location(586,57);

					System.out.println("je parse CA"); 


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:589:3: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
				{
					dbg.location(589,3);
					match(input,51,FOLLOW_51_in_object_creation1165); 
					dbg.location(589,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1167); 
					dbg.location(589,21);
					match(input,11,FOLLOW_11_in_object_creation1169); 
					dbg.location(589,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1171); 
					dbg.location(589,32);
					match(input,11,FOLLOW_11_in_object_creation1173); 
					dbg.location(589,36);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1175); 
					dbg.location(589,48);
					match(input,9,FOLLOW_9_in_object_creation1177); 
					dbg.location(589,51);

					System.out.println("je parse CT"); 


				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:592:3: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
				{
					dbg.location(592,3);
					match(input,52,FOLLOW_52_in_object_creation1182); 
					dbg.location(592,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1184); 
					dbg.location(592,21);
					match(input,11,FOLLOW_11_in_object_creation1186); 
					dbg.location(592,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1188); 
					dbg.location(592,32);
					match(input,11,FOLLOW_11_in_object_creation1190); 
					dbg.location(592,36);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1192); 
					dbg.location(592,43);
					match(input,11,FOLLOW_11_in_object_creation1194); 
					dbg.location(592,47);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_creation1196); 
					dbg.location(592,54);
					match(input,11,FOLLOW_11_in_object_creation1198); 
					dbg.location(592,58);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_object_creation1200); 
					dbg.location(592,70);
					match(input,9,FOLLOW_9_in_object_creation1202); 
					dbg.location(592,73);

					System.out.println("je parse CM"); 


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
		dbg.location(595, 1);

		}
		finally {
			dbg.exitRule("object_creation");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end object_creation


	// $ANTLR start object_deletion
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:597:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );
	public final void object_deletion() throws RecognitionException {
		Token id=null;
		Token page_id=null;

		try { dbg.enterRule("object_deletion");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(597, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:598:1: ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' )
			int alt33=2;
			try { dbg.enterDecision(33);

			int LA33_0 = input.LA(1);

			if ( (LA33_0==53) ) {
				alt33=1;
			}
			else if ( (LA33_0==54) ) {
				alt33=2;
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("597:1: object_deletion : ( 'SU(' id= NUMBER ')' | 'SI(' page_id= NUMBER ',' id= NUMBER ')' );", 33, 0, input);

				dbg.recognitionException(nvae);
				throw nvae;
			}
			} finally {dbg.exitDecision(33);}

			switch (alt33) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:599:1: 'SU(' id= NUMBER ')'
				{
					dbg.location(599,1);
					match(input,53,FOLLOW_53_in_object_deletion1212); 
					dbg.location(599,9);
					id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1216); 
					dbg.location(599,17);
					match(input,9,FOLLOW_9_in_object_deletion1218); 
					dbg.location(599,20);

					System.out.println("je parse SU"); 


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:602:3: 'SI(' page_id= NUMBER ',' id= NUMBER ')'
				{
					dbg.location(602,3);
					match(input,54,FOLLOW_54_in_object_deletion1223); 
					dbg.location(602,16);
					page_id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1227); 
					dbg.location(602,24);
					match(input,11,FOLLOW_11_in_object_deletion1229); 
					dbg.location(602,30);
					id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_object_deletion1233); 
					dbg.location(602,38);
					match(input,9,FOLLOW_9_in_object_deletion1235); 
					dbg.location(602,41);

					System.out.println("je parse SI"); 


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
		dbg.location(605, 1);

		}
		finally {
			dbg.exitRule("object_deletion");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end object_deletion


	// $ANTLR start domaine_table
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:608:1: domaine_table : ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' );
	public final void domaine_table() throws RecognitionException {
		try { dbg.enterRule("domaine_table");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(608, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:609:1: ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' )
			int alt34=4;
			try { dbg.enterDecision(34);

			switch ( input.LA(1) ) {
			case 55:
			{
				alt34=1;
			}
			break;
			case 56:
			{
				alt34=2;
			}
			break;
			case 57:
			{
				alt34=3;
			}
			break;
			case 15:
			{
				alt34=4;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("608:1: domaine_table : ( 'TD(' CAMI_STRING ')' | 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'FA()' );", 34, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(34);}

			switch (alt34) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:610:1: 'TD(' CAMI_STRING ')'
				{
					dbg.location(610,1);
					match(input,55,FOLLOW_55_in_domaine_table1246); 
					dbg.location(610,7);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1248); 
					dbg.location(610,19);
					match(input,9,FOLLOW_9_in_domaine_table1250); 
					dbg.location(610,22);


					System.out.println("je parse le TD dans table domaine");


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:614:2: 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')'
				{
					dbg.location(614,2);
					match(input,56,FOLLOW_56_in_domaine_table1254); 
					dbg.location(614,8);
					match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1256); 
					dbg.location(614,15);
					match(input,11,FOLLOW_11_in_domaine_table1258); 
					dbg.location(614,19);
					match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1260); 
					dbg.location(614,26);
					match(input,11,FOLLOW_11_in_domaine_table1262); 
					dbg.location(614,30);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1264); 
					dbg.location(614,42);
					match(input,9,FOLLOW_9_in_domaine_table1266); 
					dbg.location(614,45);


					System.out.println("je parse le OB dans table domaine");


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:618:2: 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
				{
					dbg.location(618,2);
					match(input,57,FOLLOW_57_in_domaine_table1270); 
					dbg.location(618,8);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1272); 
					dbg.location(618,20);
					match(input,11,FOLLOW_11_in_domaine_table1274); 
					dbg.location(618,24);
					match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1276); 
					dbg.location(618,31);
					match(input,11,FOLLOW_11_in_domaine_table1278); 
					dbg.location(618,35);
					match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1280); 
					dbg.location(618,42);
					match(input,11,FOLLOW_11_in_domaine_table1282); 
					dbg.location(618,46);
					match(input,NUMBER,FOLLOW_NUMBER_in_domaine_table1284); 
					dbg.location(618,53);
					match(input,11,FOLLOW_11_in_domaine_table1286); 
					dbg.location(618,57);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_domaine_table1288); 
					dbg.location(618,69);
					match(input,9,FOLLOW_9_in_domaine_table1290); 
					dbg.location(618,72);


					System.out.println("je parse le AT dans table domaine");


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:622:2: 'FA()'
				{
					dbg.location(622,2);
					match(input,15,FOLLOW_15_in_domaine_table1294); 
					dbg.location(622,8);


					System.out.println("je parse le FA dans table domaine");


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
		dbg.location(626, 1);

		}
		finally {
			dbg.exitRule("domaine_table");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end domaine_table


	// $ANTLR start modele
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:627:1: modele : 'DB()' ( modele )* 'FB()' ;
	public final void modele() throws RecognitionException {
		try { dbg.enterRule("modele");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(627, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:628:1: ( 'DB()' ( modele )* 'FB()' )
			dbg.enterAlt(1);

			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:629:1: 'DB()' ( modele )* 'FB()'
			{
				dbg.location(629,1);
				match(input,58,FOLLOW_58_in_modele1303); 
				dbg.location(629,7);

				System.out.println("je parse BD"); 

				dbg.location(632,1);
				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:632:1: ( modele )*
				try { dbg.enterSubRule(35);

				loop35:
					do {
						int alt35=2;
						try { dbg.enterDecision(35);

						int LA35_0 = input.LA(1);

						if ( (LA35_0==58) ) {
							alt35=1;
						}


						} finally {dbg.exitDecision(35);}

						switch (alt35) {
						case 1 :
							dbg.enterAlt(1);

							// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:632:1: modele
							{
								dbg.location(632,1);
								pushFollow(FOLLOW_modele_in_modele1306);
								modele();
								_fsp--;


							}
							break;

						default :
							break loop35;
						}
					} while (true);
				} finally {dbg.exitSubRule(35);}

				dbg.location(633,1);
				match(input,59,FOLLOW_59_in_modele1309); 
				dbg.location(633,7);

				System.out.println("je parse FB"); 


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		dbg.location(636, 1);

		}
		finally {
			dbg.exitRule("modele");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end modele


	// $ANTLR start modele2
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:638:1: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );
	public final void modele2() throws RecognitionException {
		try { dbg.enterRule("modele2");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(638, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:639:1: ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )
			int alt36=7;
			try { dbg.enterDecision(36);

			switch ( input.LA(1) ) {
			case 48:
			{
				alt36=1;
			}
			break;
			case 49:
			{
				alt36=2;
			}
			break;
			case 50:
			{
				alt36=3;
			}
			break;
			case 51:
			{
				alt36=4;
			}
			break;
			case 52:
			{
				alt36=5;
			}
			break;
			case 60:
			{
				alt36=6;
			}
			break;
			case 61:
			{
				alt36=7;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("638:1: modele2 : ( 'CN(' CAMI_STRING ',' NUMBER ')' | 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' | 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')' | 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' | 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' );", 36, 0, input);

			dbg.recognitionException(nvae);
			throw nvae;
			}

			} finally {dbg.exitDecision(36);}

			switch (alt36) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:640:1: 'CN(' CAMI_STRING ',' NUMBER ')'
				{
					dbg.location(640,1);
					match(input,48,FOLLOW_48_in_modele21319); 
					dbg.location(640,7);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21321); 
					dbg.location(640,19);
					match(input,11,FOLLOW_11_in_modele21323); 
					dbg.location(640,23);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21325); 
					dbg.location(640,30);
					match(input,9,FOLLOW_9_in_modele21327); 
					dbg.location(640,33);

					System.out.println("je parse CN"); 


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:643:3: 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(643,3);
					match(input,49,FOLLOW_49_in_modele21332); 
					dbg.location(643,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21334); 
					dbg.location(643,21);
					match(input,11,FOLLOW_11_in_modele21336); 
					dbg.location(643,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21338); 
					dbg.location(643,32);
					match(input,11,FOLLOW_11_in_modele21340); 
					dbg.location(643,36);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21342); 
					dbg.location(643,43);
					match(input,9,FOLLOW_9_in_modele21344); 
					dbg.location(643,46);

					System.out.println("je parse CB"); 


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:646:3: 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(646,3);
					match(input,50,FOLLOW_50_in_modele21349); 
					dbg.location(646,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21351); 
					dbg.location(646,21);
					match(input,11,FOLLOW_11_in_modele21353); 
					dbg.location(646,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21355); 
					dbg.location(646,32);
					match(input,11,FOLLOW_11_in_modele21357); 
					dbg.location(646,36);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21359); 
					dbg.location(646,43);
					match(input,11,FOLLOW_11_in_modele21361); 
					dbg.location(646,47);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21363); 
					dbg.location(646,54);
					match(input,9,FOLLOW_9_in_modele21365); 
					dbg.location(646,57);

					System.out.println("je parse CA"); 


				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:649:3: 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
				{
					dbg.location(649,3);
					match(input,51,FOLLOW_51_in_modele21370); 
					dbg.location(649,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21372); 
					dbg.location(649,21);
					match(input,11,FOLLOW_11_in_modele21374); 
					dbg.location(649,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21376); 
					dbg.location(649,32);
					match(input,11,FOLLOW_11_in_modele21378); 
					dbg.location(649,36);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21380); 
					dbg.location(649,48);
					match(input,9,FOLLOW_9_in_modele21382); 
					dbg.location(649,51);

					System.out.println("je parse CT"); 


				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:652:3: 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
				{
					dbg.location(652,3);
					match(input,52,FOLLOW_52_in_modele21387); 
					dbg.location(652,9);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21389); 
					dbg.location(652,21);
					match(input,11,FOLLOW_11_in_modele21391); 
					dbg.location(652,25);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21393); 
					dbg.location(652,32);
					match(input,11,FOLLOW_11_in_modele21395); 
					dbg.location(652,36);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21397); 
					dbg.location(652,43);
					match(input,11,FOLLOW_11_in_modele21399); 
					dbg.location(652,47);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21401); 
					dbg.location(652,54);
					match(input,11,FOLLOW_11_in_modele21403); 
					dbg.location(652,58);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_modele21405); 
					dbg.location(652,70);
					match(input,9,FOLLOW_9_in_modele21407); 
					dbg.location(652,73);

					System.out.println("je parse CM"); 


				}
				break;
			case 6 :
				dbg.enterAlt(6);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:655:2: 'PO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(655,2);
					match(input,60,FOLLOW_60_in_modele21411); 
					dbg.location(655,7);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21412); 
					dbg.location(655,14);
					match(input,11,FOLLOW_11_in_modele21414); 
					dbg.location(655,18);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21416); 
					dbg.location(655,25);
					match(input,11,FOLLOW_11_in_modele21418); 
					dbg.location(655,29);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21420); 
					dbg.location(655,36);
					match(input,11,FOLLOW_11_in_modele21422); 
					dbg.location(655,40);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21424); 
					dbg.location(655,47);
					match(input,9,FOLLOW_9_in_modele21426); 
					dbg.location(655,50);

					System.out.println("je parse PO");


				}
				break;
			case 7 :
				dbg.enterAlt(7);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:658:2: 'pO(' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'
				{
					dbg.location(658,2);
					match(input,61,FOLLOW_61_in_modele21430); 
					dbg.location(658,7);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21431); 
					dbg.location(658,14);
					match(input,11,FOLLOW_11_in_modele21433); 
					dbg.location(658,18);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21435); 
					dbg.location(658,25);
					match(input,11,FOLLOW_11_in_modele21437); 
					dbg.location(658,29);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21439); 
					dbg.location(658,36);
					match(input,11,FOLLOW_11_in_modele21441); 
					dbg.location(658,40);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21443); 
					dbg.location(658,47);
					match(input,11,FOLLOW_11_in_modele21445); 
					dbg.location(658,51);
					match(input,NUMBER,FOLLOW_NUMBER_in_modele21447); 
					dbg.location(658,58);
					match(input,9,FOLLOW_9_in_modele21449); 
					dbg.location(658,61);

					System.out.println("je parse pO");


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
		dbg.location(661, 1);

		}
		finally {
			dbg.exitRule("modele2");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end modele2


	// $ANTLR start dialogue
	// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:663:1: dialogue : ( 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' | 'DC(' | 'AD(' dialog_id= NUMBER ')' | 'CD(' dialog_id= NUMBER ')' | 'DG(' dialog_id= NUMBER ')' );
	public final void dialogue() throws RecognitionException {
		Token dialog_id=null;
		Token line_number=null;
		Token dialog_type=null;
		Token buttons_type=null;
		Token window_title=null;
		Token help=null;
		Token title_or_message=null;
		Token input_type=null;
		Token line_type=null;
		Token default_value=null;

		try { dbg.enterRule("dialogue");
		if ( ruleLevel==0 ) {dbg.commence();}
		ruleLevel++;
		dbg.location(663, 1);

		try {
			// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:664:1: ( 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' | 'DC(' | 'AD(' dialog_id= NUMBER ')' | 'CD(' dialog_id= NUMBER ')' | 'DG(' dialog_id= NUMBER ')' )
			int alt38=8;
			try { dbg.enterDecision(38);

			switch ( input.LA(1) ) {
			case 62:
			{
				alt38=1;
			}
			break;
			case 63:
			{
				alt38=2;
			}
			break;
			case NEWLINE:
			{
				alt38=3;
			}
			break;
			case 64:
			{
				alt38=4;
			}
			break;
			case 65:
			{
				alt38=5;
			}
			break;
			case 66:
			{
				alt38=6;
			}
			break;
			case 67:
			{
				alt38=7;
			}
			break;
			case 68:
			{
				alt38=8;
			}
			break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("663:1: dialogue : ( 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')' | 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')' | NEWLINE | 'FF(' | 'DC(' | 'AD(' dialog_id= NUMBER ')' | 'CD(' dialog_id= NUMBER ')' | 'DG(' dialog_id= NUMBER ')' );", 38, 0, input);

					dbg.recognitionException(nvae);
					throw nvae;
			}

			} finally {dbg.exitDecision(38);}

			switch (alt38) {
			case 1 :
				dbg.enterAlt(1);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:665:1: 'DS(' dialog_id= NUMBER ',' line_number= CAMI_STRING ')'
				{
					dbg.location(665,1);
					match(input,62,FOLLOW_62_in_dialogue1459); 
					dbg.location(665,15);
					dialog_id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1462); 
					dbg.location(665,23);
					match(input,11,FOLLOW_11_in_dialogue1464); 
					dbg.location(665,38);
					line_number=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1468); 
					dbg.location(665,51);
					match(input,9,FOLLOW_9_in_dialogue1470); 
					dbg.location(665,54);

					camiDialog.add(line_number.getText());
					System.out.println("je parse DS"); 


				}
				break;
			case 2 :
				dbg.enterAlt(2);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:669:3: 'CE(' dialog_id= NUMBER ',' dialog_type= NUMBER ',' buttons_type= NUMBER ',' window_title= CAMI_STRING ',' help= CAMI_STRING ',' title_or_message= CAMI_STRING ',' input_type= NUMBER ',' line_type= NUMBER ',' (default_value= CAMI_STRING )? ')'
				{
					dbg.location(669,3);
					match(input,63,FOLLOW_63_in_dialogue1475); 
					dbg.location(669,18);
					dialog_id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1479); 
					dbg.location(669,26);
					match(input,11,FOLLOW_11_in_dialogue1481); 
					dbg.location(669,41);
					dialog_type=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1485); 
					dbg.location(669,49);
					match(input,11,FOLLOW_11_in_dialogue1487); 
					dbg.location(669,65);
					buttons_type=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1491); 
					dbg.location(669,73);
					match(input,11,FOLLOW_11_in_dialogue1493); 
					dbg.location(669,90);
					window_title=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1498); 
					dbg.location(669,103);
					match(input,11,FOLLOW_11_in_dialogue1500); 
					dbg.location(669,111);
					help=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1504); 
					dbg.location(669,124);
					match(input,11,FOLLOW_11_in_dialogue1506); 
					dbg.location(669,156);
					title_or_message=(Token)input.LT(1);
					match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1522); 
					dbg.location(669,169);
					match(input,11,FOLLOW_11_in_dialogue1524); 
					dbg.location(669,183);
					input_type=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1528); 
					dbg.location(669,191);
					match(input,11,FOLLOW_11_in_dialogue1530); 
					dbg.location(669,204);
					line_type=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1534); 
					dbg.location(669,212);
					match(input,11,FOLLOW_11_in_dialogue1536); 
					dbg.location(669,229);
					// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:669:229: (default_value= CAMI_STRING )?
							int alt37=2;
					try { dbg.enterSubRule(37);
					try { dbg.enterDecision(37);

					int LA37_0 = input.LA(1);

					if ( (LA37_0==CAMI_STRING) ) {
						alt37=1;
					}
					} finally {dbg.exitDecision(37);}

					switch (alt37) {
					case 1 :
						dbg.enterAlt(1);

						// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:669:229: default_value= CAMI_STRING
						{
							dbg.location(669,229);
							default_value=(Token)input.LT(1);
							match(input,CAMI_STRING,FOLLOW_CAMI_STRING_in_dialogue1540); 

						}
						break;

					}
					} finally {dbg.exitSubRule(37);}

					dbg.location(669,243);
					match(input,9,FOLLOW_9_in_dialogue1543); 
					dbg.location(669,246);



					camiDialog.add(dialog_id.getText()); 
					camiDialog.add(dialog_type.getText()); 
					camiDialog.add(buttons_type.getText()); 
					camiDialog.add(window_title.getText()); 
					camiDialog.add(help.getText()); 
					camiDialog.add(title_or_message.getText()); 
					camiDialog.add(input_type.getText());
					camiDialog.add(line_type.getText());

					if(default_value != null)
						camiDialog.add(default_value.getText()); 
					else
						camiDialog.add(null/*new String("")*/);

					System.out.println("je parse CE"); 


				}
				break;
			case 3 :
				dbg.enterAlt(3);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:688:2: NEWLINE
				{
					dbg.location(688,2);
					match(input,NEWLINE,FOLLOW_NEWLINE_in_dialogue1547); 

				}
				break;
			case 4 :
				dbg.enterAlt(4);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:689:2: 'FF('
				{
					dbg.location(689,2);
					match(input,64,FOLLOW_64_in_dialogue1550); 
					dbg.location(689,7);

					IDialog dialog = (IDialog)CamiObjectBuilder.buildDialog(camiDialog);

					dialogs.put((Integer) dialog.getId(), dialog); 

					System.out.println("je parse FF");


				}
				break;
			case 5 :
				dbg.enterAlt(5);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:696:2: 'DC('
				{
					dbg.location(696,2);
					match(input,65,FOLLOW_65_in_dialogue1554); 
					dbg.location(696,7);

					camiDialog = new ArrayList<String>();
					System.out.println("je parse DC");


				}
				break;
			case 6 :
				dbg.enterAlt(6);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:700:2: 'AD(' dialog_id= NUMBER ')'
				{
					dbg.location(700,2);
					match(input,66,FOLLOW_66_in_dialogue1558); 
					dbg.location(700,16);
					dialog_id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1561); 
					dbg.location(700,24);
					match(input,9,FOLLOW_9_in_dialogue1563); 
					dbg.location(700,27);



					Integer i = Integer.parseInt(dialog_id.getText());

					((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(i),1);
					System.out.println("je parse AD");


				}
				break;
			case 7 :
				dbg.enterAlt(7);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:708:2: 'CD(' dialog_id= NUMBER ')'
				{
					dbg.location(708,2);
					match(input,67,FOLLOW_67_in_dialogue1567); 
					dbg.location(708,16);
					dialog_id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1570); 
					dbg.location(708,24);
					match(input,9,FOLLOW_9_in_dialogue1572); 
					dbg.location(708,27);


					Integer j = Integer.parseInt(dialog_id.getText());
					((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(j),2);
					System.out.println("je parse CD");


				}
				break;
			case 8 :
				dbg.enterAlt(8);

				// /Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g:714:2: 'DG(' dialog_id= NUMBER ')'
				{
					dbg.location(714,2);
					match(input,68,FOLLOW_68_in_dialogue1576); 
					dbg.location(714,17);
					dialog_id=(Token)input.LT(1);
					match(input,NUMBER,FOLLOW_NUMBER_in_dialogue1580); 
					dbg.location(714,25);
					match(input,9,FOLLOW_9_in_dialogue1582); 
					dbg.location(714,28);

					Integer k = Integer.parseInt(dialog_id.getText());
					((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(k),3);
					dialogs.remove( k);

					System.out.println("je parse DG");


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
		dbg.location(721, 1);

		}
		finally {
			dbg.exitRule("dialogue");
			ruleLevel--;
			if ( ruleLevel==0 ) {dbg.terminate();}
		}

		return ;
	}
	// $ANTLR end dialogue


	protected DFA24 dfa24 = new DFA24(this);
	static final String DFA24_eotS =
		"\15\uffff";
	static final String DFA24_eofS =
		"\1\3\1\uffff\1\3\12\uffff";
	static final String DFA24_minS =
		"\1\6\1\uffff\1\33\12\uffff";
	static final String DFA24_maxS =
		"\1\104\1\uffff\1\45\12\uffff";
	static final String DFA24_acceptS =
		"\1\uffff\1\1\1\uffff\1\2\1\3\1\4\1\5\2\6\1\7\1\10\1\11\1\12";
	static final String DFA24_specialS =
		"\15\uffff}>";
	static final String[] DFA24_transitionS = {
		"\1\10\10\uffff\1\11\13\uffff\1\5\3\uffff\3\7\2\uffff\1\1\1\2"+
		"\1\4\1\14\1\10\16\6\3\11\1\13\3\uffff\7\12",
		"",
		"\1\5\11\uffff\1\2",
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

	static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
	static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
	static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
	static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
	static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
	static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
	static final short[][] DFA24_transition;

	static {
		int numStates = DFA24_transitionS.length;
		DFA24_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
		}
	}

	class DFA24 extends DFA {

		public DFA24(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 24;
			this.eot = DFA24_eot;
			this.eof = DFA24_eof;
			this.min = DFA24_min;
			this.max = DFA24_max;
			this.accept = DFA24_accept;
			this.special = DFA24_special;
			this.transition = DFA24_transition;
		}
		public String getDescription() {
			return "436:1: result_reception : ( 'DR()' | ( '<EOF>' )* | 'RQ(' service_name1= CAMI_STRING ',' question_name1= CAMI_STRING ',' num1= NUMBER ')' | ( '<EOF>' )* 'TQ(' service_name2= CAMI_STRING ',' question_name2= CAMI_STRING ',' state2= NUMBER ',' (mess2= CAMI_STRING )? ')' | ( result )* | ( message_utils )* | ( domaine_table )* | ( dialogue )* | ( modele )* | 'FR(' NUMBER ')' );";
		}
		public void error(NoViableAltException nvae) {
			dbg.recognitionException(nvae);
		}
	}


	public static final BitSet FOLLOW_ack_open_communication_in_command54 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ack_open_connection_in_command58 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_close_connection_in_command62 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ack_open_session_in_command68 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_receving_menu_in_command72 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_in_command77 = new BitSet(new long[]{0x0000000008000002L});
	public static final BitSet FOLLOW_end_menu_transmission_in_command83 = new BitSet(new long[]{0x0000000040000002L});
	public static final BitSet FOLLOW_ack_suspend_current_session_in_command90 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ack_resume_suspend_current_session_in_command95 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ack_close_current_session_in_command100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ask_for_a_model_in_command106 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_message_to_user_in_command111 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_brutal_interrupt_in_command116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_reception_in_command122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_8_in_ack_open_communication143 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_communication145 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_ack_open_communication147 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_ack_open_connection169 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_ack_open_connection176 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_ack_open_connection183 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_ack_open_connection190 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_ack_open_connection196 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_close_connection222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_ack_open_session243 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_ack_open_session245 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_ack_open_session246 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_ack_open_session252 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_15_in_ack_open_session258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_interlocutor_table_in_ack_open_session264 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_16_in_ack_suspend_current_session286 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_ack_resume_suspend_current_session302 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_ack_resume_suspend_current_session304 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_ack_resume_suspend_current_session306 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_ack_close_current_session321 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_ack_close_current_session323 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_ack_close_current_session325 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_interlocutor_table340 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_20_in_interlocutor_table344 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table348 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_interlocutor_table350 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_interlocutor_table354 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_interlocutor_table356 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_interlocutor_table360 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_interlocutor_table365 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_interlocutor_table369 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_interlocutor_table372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_21_in_interlocutor_table377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_receving_menu391 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_menu_name_in_receving_menu394 = new BitSet(new long[]{0x0000000004800000L});
	public static final BitSet FOLLOW_question_add_in_receving_menu396 = new BitSet(new long[]{0x0000000004800000L});
	public static final BitSet FOLLOW_23_in_receving_menu399 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_receving_menu402 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_receving_menu403 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_receving_menu404 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_menu_name418 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_menu_name422 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_menu_name424 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_menu_name428 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_menu_name430 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_menu_name434 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_menu_name436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_question_add449 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_question_add453 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add455 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_question_add459 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add461 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_NUMBER_in_question_add466 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add469 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_NUMBER_in_question_add473 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add476 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_NUMBER_in_question_add481 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add484 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_NUMBER_in_question_add489 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add492 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_NUMBER_in_question_add496 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add499 = new BitSet(new long[]{0x0000000000000810L});
	public static final BitSet FOLLOW_CAMI_STRING_in_question_add504 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_question_add507 = new BitSet(new long[]{0x0000000000000220L});
	public static final BitSet FOLLOW_NUMBER_in_question_add511 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_question_add514 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_update532 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_update536 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_update538 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_update542 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_update544 = new BitSet(new long[]{0x0000000030000000L});
	public static final BitSet FOLLOW_set_in_update548 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_update554 = new BitSet(new long[]{0x0000000000000210L});
	public static final BitSet FOLLOW_CAMI_STRING_in_update558 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_update561 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_end_menu_transmission581 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_end_menu_transmission583 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_end_menu_transmission585 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_trace_message_in_message_to_user604 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_warning_message_in_message_to_user608 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_special_message_in_message_to_user612 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_trace_message621 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_trace_message623 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_trace_message625 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_warning_message635 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_warning_message637 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_warning_message639 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_special_message650 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_special_message652 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_special_message654 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_special_message656 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_special_message658 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_brutal_interrupt673 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_brutal_interrupt677 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_brutal_interrupt679 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_brutal_interrupt683 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_brutal_interrupt685 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_ask_for_a_model753 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model755 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_ask_for_a_model757 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_ask_for_a_model759 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_ask_for_a_model761 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_36_in_result_reception776 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_37_in_result_reception780 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_38_in_result_reception784 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_result_reception788 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_result_reception790 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_result_reception794 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_result_reception796 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_result_reception800 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_result_reception802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_37_in_result_reception807 = new BitSet(new long[]{0x0000002008000000L});
	public static final BitSet FOLLOW_27_in_result_reception810 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_result_reception814 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_result_reception816 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_result_reception820 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_result_reception822 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_result_reception826 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_result_reception829 = new BitSet(new long[]{0x0000000000000210L});
	public static final BitSet FOLLOW_CAMI_STRING_in_result_reception833 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_result_reception836 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_in_result_reception840 = new BitSet(new long[]{0x007FFE0000000002L});
	public static final BitSet FOLLOW_message_utils_in_result_reception844 = new BitSet(new long[]{0x0000010380000042L});
	public static final BitSet FOLLOW_domaine_table_in_result_reception848 = new BitSet(new long[]{0x0380000000008002L});
	public static final BitSet FOLLOW_dialogue_in_result_reception852 = new BitSet(new long[]{0xC000000000000042L,0x000000000000001FL});
	public static final BitSet FOLLOW_modele_in_result_reception856 = new BitSet(new long[]{0x0400000000000002L});
	public static final BitSet FOLLOW_39_in_result_reception860 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_result_reception862 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_result_reception864 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_trace_message2_in_message_utils874 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_warning_message2_in_message_utils879 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_special_message2_in_message_utils884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEWLINE_in_message_utils887 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_40_in_message_utils891 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_message_utils892 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_message_utils894 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_message_utils896 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_message_utils898 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_message_utils900 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_message_utils902 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_message_utils904 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_message_utils906 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_message_utils908 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_message_utils910 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_trace_message2920 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_trace_message2922 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_trace_message2924 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_warning_message2934 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_warning_message2936 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_warning_message2938 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_special_message2949 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_special_message2951 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_special_message2953 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_special_message2955 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_special_message2957 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_result_body_in_result966 = new BitSet(new long[]{0x007FF02000000002L});
	public static final BitSet FOLLOW_37_in_result969 = new BitSet(new long[]{0x0000002000000002L});
	public static final BitSet FOLLOW_41_in_result973 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_result977 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_result981 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_result983 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_result987 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_result989 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_43_in_result993 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_textual_result_in_result_body1003 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_object_designation_in_result_body1007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_object_outline_in_result_body1011 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_attribute_outline_in_result_body1015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_object_creation_in_result_body1019 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_object_deletion_in_result_body1023 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_44_in_textual_result1032 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_textual_result1034 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_textual_result1036 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_object_designation1046 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_designation1050 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_designation1052 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_46_in_object_outline1062 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_outline1066 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_outline1068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_47_in_attribute_outline1078 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_attribute_outline1082 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_attribute_outline1084 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_attribute_outline1088 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_attribute_outline1090 = new BitSet(new long[]{0x0000000000000820L});
	public static final BitSet FOLLOW_NUMBER_in_attribute_outline1094 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_attribute_outline1097 = new BitSet(new long[]{0x0000000000000220L});
	public static final BitSet FOLLOW_NUMBER_in_attribute_outline1101 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_attribute_outline1104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_48_in_object_creation1114 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1116 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1118 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1120 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_creation1122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_49_in_object_creation1127 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1129 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1131 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1133 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1135 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1137 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_creation1139 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_object_creation1144 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1146 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1148 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1150 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1152 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1154 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1156 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1158 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_creation1160 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_object_creation1165 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1167 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1169 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1171 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1173 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1175 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_creation1177 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_object_creation1182 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1184 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1186 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1188 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1190 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1192 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1194 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_creation1196 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_creation1198 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_object_creation1200 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_creation1202 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_53_in_object_deletion1212 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_deletion1216 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_deletion1218 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_54_in_object_deletion1223 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_deletion1227 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_object_deletion1229 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_object_deletion1233 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_object_deletion1235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_55_in_domaine_table1246 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1248 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_domaine_table1250 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_domaine_table1254 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_domaine_table1256 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_domaine_table1258 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_domaine_table1260 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_domaine_table1262 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1264 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_domaine_table1266 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_57_in_domaine_table1270 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1272 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_domaine_table1274 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_domaine_table1276 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_domaine_table1278 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_domaine_table1280 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_domaine_table1282 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_domaine_table1284 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_domaine_table1286 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_domaine_table1288 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_domaine_table1290 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_15_in_domaine_table1294 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_58_in_modele1303 = new BitSet(new long[]{0x0C00000000000000L});
	public static final BitSet FOLLOW_modele_in_modele1306 = new BitSet(new long[]{0x0C00000000000000L});
	public static final BitSet FOLLOW_59_in_modele1309 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_48_in_modele21319 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21321 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21323 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21325 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_49_in_modele21332 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21334 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21336 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21338 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21340 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21342 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_modele21349 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21351 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21353 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21355 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21357 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21359 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21361 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21363 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_51_in_modele21370 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21372 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21374 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21376 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21378 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21380 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21382 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_52_in_modele21387 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21389 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21391 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21393 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21395 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21397 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21399 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21401 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21403 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_modele21405 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_60_in_modele21411 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21412 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21414 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21416 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21418 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21420 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21422 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21424 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21426 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_61_in_modele21430 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21431 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21433 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21435 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21437 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21439 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21441 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21443 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_modele21445 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_modele21447 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_modele21449 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_62_in_dialogue1459 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1462 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1464 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1468 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_dialogue1470 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_63_in_dialogue1475 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1479 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1481 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1485 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1487 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1491 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1493 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1498 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1500 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1504 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1506 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1522 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1524 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1528 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1530 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1534 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_dialogue1536 = new BitSet(new long[]{0x0000000000000210L});
	public static final BitSet FOLLOW_CAMI_STRING_in_dialogue1540 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_dialogue1543 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEWLINE_in_dialogue1547 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_64_in_dialogue1550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_65_in_dialogue1554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_66_in_dialogue1558 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1561 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_dialogue1563 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_67_in_dialogue1567 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1570 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_dialogue1572 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_68_in_dialogue1576 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_NUMBER_in_dialogue1580 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_dialogue1582 = new BitSet(new long[]{0x0000000000000002L});

}