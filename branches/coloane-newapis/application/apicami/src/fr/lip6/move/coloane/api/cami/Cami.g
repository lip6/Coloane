grammar Cami;

@lexer::header{
	package fr.lip6.move.coloane.api.cami;
}           

@header{
	package fr.lip6.move.coloane.api.cami;

	import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
	import fr.lip6.move.coloane.api.interfaces.ISessionController;
	import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
	import fr.lip6.move.coloane.api.observables.DisconnectObservable;
	import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
	import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
	import fr.lip6.move.coloane.api.session.SessionController;
	import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
	import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
	import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
	import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.logging.Logger;
}

@members {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");
	
	List<String> listOfArgs; /* liste des arguments pour la construction des objets de notification */
	List<List<String>> camiMenuList; /* liste servant a construire les objets Correspondant aux AQ */
	List<List<String>> camiUpdates; /* liste servant a construire les objets Correspondant aux TQ 7 et 8 */

	Map<String, Object> hashObservable; /* Table de hash des observables */

	ISessionController sessionControl;
	ISessionInfo sessionInfo;

	IDialog dialog;
	List<String> camiDialog; /* represente une boite de dialogue */
	Map<Integer,IDialog> dialogs ;

	ISubMenu menu;
	List<ISubMenu> menuList;
	List<IUpdateMenu> updates;

	/* Constructeur du parser */
	public CamiParser(TokenStream input, Map<String, Object> hm) {
		this(input);
		hashObservable = hm;
		sessionControl = SessionController.getInstance();
		dialogs = new HashMap<Integer,IDialog>();
	}
	} /* fin de members */

	/* --------------------------------------------------------------------------- *
	*                                  PRODUCTIONS                                *
	* --------------------------------------------------------------------------- */

	command
	: 
	ack_open_communication | ack_open_connection | close_connection
	| ack_open_session | receving_menu
	|update*
	|end_menu_transmission*

	|ack_suspend_current_session
	|ack_resume_suspend_current_session
	|ack_close_current_session

	|ask_for_a_model
	|message_to_user
	|brutal_interrupt
	|result_reception
	;

	/* ----------------------------  Ouverture de la connexion  SC ----------------------- */
	ack_open_communication
	:
	'SC(' CAMI_STRING ')'{
		listOfArgs = new ArrayList<String>();
		listOfArgs.add($CAMI_STRING.text);
		synchronized(hashObservable) {
			hashObservable.notify();
		}
	}
	;

	/* ---------------------------- Ouverture de la connexion OC ------------------------- */
	ack_open_connection
	:
	'OC(' 
	v1=NUMBER {
		listOfArgs.add($v1.text);
	} 
	',' 
	v2=NUMBER {listOfArgs.add($v2.text);
		synchronized(hashObservable) {
			hashObservable.notify();
		}
	}
	')'
	;

	/* ---------------------------- Fermeture de la connexion cote FrameKit -------------- */
	close_connection
	:
	'FC()'{
		((DisconnectObservable) hashObservable.get("IDisconnect")).notifyObservers();
	}
	;
	/* ---------------------------- Ouverture de la session ------------------------------ */
	ack_open_session
	:
	'OS(' CAMI_STRING')'{
		// Initialisation de la table des menus et des modifications
		// TODO: Trouver un autre endroit ?
		LOGGER.finest("Creation des tables de menus et de modifications");
		camiUpdates = new ArrayList<List<String>>();
	}
	|'TD()'{
		LOGGER.finest("Reception d'un TD");
	}
	|'FA()'{
		LOGGER.finest("Reception d'un FA");
	}

	|interlocutor_table

	;

	/* ----------------------------  Suspension de la session courante ------------------- */
	ack_suspend_current_session 
	:	 
	'SS()'{
		// Notifier au sessionController de l'acquittement du SS
		sessionControl.notifyEndSuspendSession();
	}
	;

	/* ----------------------------  Reprise d'une session ------------------------------- */
	ack_resume_suspend_current_session
	:
	'RS(' CAMI_STRING ')'{
		sessionControl.notifyEndResumeSession($CAMI_STRING.text);
	}
	;

	/* ----------------------------  Fermeture d'une session ----------------------------- */
	ack_close_current_session
	:
	'FS(' CAMI_STRING ')'{
		sessionControl.notifyEndCloseSession();
	}
	;

	/* ----------------------------  Reception des tables  ------------------------------- */
	interlocutor_table
	:
	'TL()'{ 
		LOGGER.finest("Reception d'un TL");
	}
	// TODO: Quid du resultat_calcul ?
	|'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' incremental=NUMBER ',' new_model=NUMBER')' {
		listOfArgs = new ArrayList<String>();
		listOfArgs.add($service_name.text);
		listOfArgs.add($about_service.text);
		listOfArgs.add($incremental.text);
		listOfArgs.add($new_model.text);
		LOGGER.finest("Fin du parse de VI");
	}
	|'FL()'{
		sessionInfo = CamiObjectBuilder.buildSessionInfo(listOfArgs);
		sessionControl.notifyReceptSessionInfo(sessionInfo);
		LOGGER.finest("Fin du parse de FL");
	}
	;

	/* ----------------------------  Reception des menus --------------------------------- */
	receving_menu
	:
	'DQ()'{
		LOGGER.finest("Creation des tables de menus");
		menuList = new ArrayList<ISubMenu>();
		camiMenuList = new ArrayList<List<String>>();
	}
	menu_name
	question_add*
	'FQ()'{
		menu = CamiObjectBuilder.buildMenu(camiMenuList);
		LOGGER.finest("Nombre de questions recues : " + camiMenuList.size());
		LOGGER.finest("Ajout du RootMenu " + menu.getName() + "a la liste des menu");
		menuList.add(menu);
	}
	'VQ('name=CAMI_STRING')'{
		LOGGER.finest("Affichage du menu " + $name.text);
	}
	;

	/* ----------------------------  Reception des menus  CQ ----------------------------- */
	menu_name
	:
	'CQ(' name=CAMI_STRING ',' question_type=NUMBER ',' question_behavior=NUMBER ')'{

		// Reception de la description d'une racine de menu
		List<String> cq = new ArrayList<String>();
		cq.add($name.text); // Nom du menu racine
		cq.add($question_type.text); // Type du menu
		cq.add($question_behavior.text); // Type du comportement

		camiMenuList.add(cq); // Ajouter au sommet de la liste des AQ
		LOGGER.finest("Reception d'un menu racine " + $name.text);
	}
	;


	/* ----------------------------  Reception des menus  AQ ----------------------------- */
	question_add
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
	question_type=NUMBER? ',' question_behavior=NUMBER? ',' 
	set_item=NUMBER? ','  dialog=NUMBER? ',' stop_authorized=NUMBER? ',' 
	output_formalism=CAMI_STRING? ',' active=NUMBER? ')'{

		LOGGER.finest("Reception d'une question " + $entry_name.text);

		List<String> aq = new ArrayList<String>();
		aq.add($parent_menu.text);
		aq.add($entry_name.text);

		// Le type de la question
		if ($question_type != null) {
			aq.add($question_type.text);
		} else {
			aq.add(null);
		}

		// Le comportement de la question
		if ($question_behavior != null) {
			aq.add($question_behavior.text);
		} else {
			aq.add(null);
		}

		if ($set_item != null) {
			aq.add($set_item.text);
		} else {
			aq.add(null);
		}

		// Les boite des dialogue sont-elles possible ?
		if($dialog != null) {
			aq.add($dialog.text);
		} else {
			aq.add(null);
		}

		// Le service est-il suspensible ?
		if($stop_authorized != null) {
			aq.add($stop_authorized.text);
		} else {
			aq.add(null);
		}

		// Le formalisme de sortie
		if($output_formalism != null) {
			aq.add($output_formalism.text);
		} else {
			aq.add(null);
		}

		// La question est-elle visible ou non ?
		if($active != null) {
			aq.add($active.text);
		} else {
			aq.add(null);
		}

		camiMenuList.add(aq); /* ajouter a la liste de AQ */
	}
	;


	/* ----------------------------  Reception des menus  TQ (7 et 8) -------------------- */
	update
	:   
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=('7'|'8') ',' mess=CAMI_STRING? ')'{

		List<String> update = new ArrayList<String>();

		update.add($service_name.text);
		update.add($question_name.text);
		update.add($state.text);

		// Si c'est un modificateur de menu
		if(!$state.text.equals("7") && !$state.text.equals("8"))
		update.add($mess.text);

		camiUpdates.add(update);

		LOGGER.finest("Reception d'un modificateur de menu :" + $service_name.text);
	}
	;


	/* ---------------------------- Fin de la transmission d'un menu QQ(3) --------------- */
	end_menu_transmission
	:       
	'QQ(' NUMBER ')'{
		LOGGER.finest("Fin de la transmission d'un menu");
		if($NUMBER.text.equals("3")) {
			sessionControl.notifyEndOpenSession();
			updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
			((ReceptMenuObservable) hashObservable.get("ISession")).notifyObservers(menuList, updates);
			camiUpdates = new ArrayList<List<String>>();
		} else {
			updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
			((ReceptMenuObservable) hashObservable.get("ISession")).notifyObservers(null, updates);
		}
	}
	;


	/* ---------------------------- Messages en provenance de FrameKit  ------------------ */
	message_to_user
	:
	trace_message | warning_message | special_message
	;

	trace_message
	:
	'TR(' CAMI_STRING ')'{
		LOGGER.finest("Reception d'un message de trace");
		IReceptMessage msg = (IReceptMessage) new ReceptMessage(4,$CAMI_STRING.text);
		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
	}
	;

	warning_message
	:
	'WN(' CAMI_STRING ')'{  
		LOGGER.finest("Reception d'un message de warning");
		IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,$CAMI_STRING.text);
		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
	}
	;

	special_message
	:	
	'MO(' NUMBER ',' CAMI_STRING ')'{
		// TODO : Expliquer ce qu'est le MO ? est-ce vraiment un warning ?
		LOGGER.finest("Reception d'un message special (MO)");
		IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,$CAMI_STRING.text);
		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
	}
	;

	/* ---------------------------- Message KO ------------------------------------------- */
	brutal_interrupt
	:
	'KO(1,' mess=CAMI_STRING ',' level=NUMBER ')'{
		LOGGER.finest("Reception d'un message KO");
		((BrutalInterruptObservable) hashObservable.get("IBrutalInterrupt")).notifyObservers($CAMI_STRING.text);
	}
	;


	/* ---------------------------- Demande du modele  -----------------------------*/
	ask_for_a_model
	:                                                       
	'DF(-2,' NUMBER ',' NUMBER ')'{
		System.out.println("je parse le DF");
		sessionControl.notifyWaitingForModel();
		//    ((IAskForModelObservable)hashObservable.get("IAskForModel")).notifyObservers();
	}
	;


	/******************resultats**************************************/


	result_reception
	:
	'DR()'{ 
		// initialiser la liste des updates 
		//    camiUpdates = new ArrayList<ArrayList<String>>();
		System.out.println("je parse DR");
		sessionControl.notifyWaitingForResult();
	}
	|'<EOF>'*
	|'RQ(' service_name1=CAMI_STRING ',' question_name1=CAMI_STRING ',' num1=NUMBER ')'{
		System.out.println("je parse RQ"); 
	} 
	|'<EOF>'*
	'TQ(' service_name2=CAMI_STRING ',' question_name2=CAMI_STRING ',' state2=NUMBER/*('2'|'3'|'4'|'5'|'6'|'9')*/ ',' mess2=CAMI_STRING? ')'{ 


		if($mess2.text != null){
			ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(3,$mess2.text);
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
	|result*
	|message_utils*
	|domaine_table*
	|dialogue*
	|modele*
	|'FR(' NUMBER ')'{
		System.out.println("je parse FR");
		sessionControl.notifyEndResult();
		//TODO notifier Coloane  de la fin de reception des resultats et envoyer les resultats
	}
	;

	message_utils
	:
	trace_message2 
	| warning_message2 
	| special_message2
	|NEWLINE
	| 'ZA('NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'{
		ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(4,"");
		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
		System.out.println("je parse ZA");
	}
	;

	trace_message2
	:
	'TR(' CAMI_STRING ')'{ 
		ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(2,$CAMI_STRING.text);
		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
		//  ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers($CAMI_STRING.text);
		System.out.println("je parse le TR");
	}
	;

	warning_message2
	:
	'WN(' CAMI_STRING ')'{
		ISpecialMessage msg = (ISpecialMessage)new SpecialMessage(1,$CAMI_STRING.text);
		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
		// ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers($CAMI_STRING.text);
		System.out.println("je parse le WN");
	}
	;

	special_message2
	:	
	'MO(' NUMBER ',' CAMI_STRING ')'{
		ISpecialMessage msg =(ISpecialMessage) new SpecialMessage(1,$CAMI_STRING.text);
		((ISpecialMessageObservable)hashObservable.get("ISpecialMessage")).notifyObservers(msg);
		//  ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers($CAMI_STRING.text);            
		System.out.println("je parse le MO");
	}
	;
	result	:
	result_body+
	'<EOF>'*
	|'FE()'{
		System.out.println("je parse FE"); 
	}
	|'DE(' ensemble_name=CAMI_STRING ',' ensemble_type=NUMBER ')'{
		System.out.println("je parse DE"); 
	}
	|'DE()'{
		System.out.println("je parse DE sans parametre"); 
	}
	;

	result_body
	:
	textual_result
	| object_designation
	| object_outline
	| attribute_outline
	| object_creation
	| object_deletion
	;

	textual_result
	:
	'RT(' CAMI_STRING ')'{
		System.out.println("je parse RT"); 
	}
	;

	object_designation
	:
	'RO(' id=NUMBER ')'{
		System.out.println("je parse RO"); 
	}
	;

	object_outline
	:
	'ME(' id=NUMBER ')'{
		System.out.println("je parse ME"); 
	}
	;

	attribute_outline
	:
	'MT(' id=NUMBER ',' attr_name=CAMI_STRING ',' begin=NUMBER? ',' end=NUMBER? ')'{
		System.out.println("je parse MT"); 
	}
	;

	object_creation
	:
	'CN(' CAMI_STRING ',' NUMBER ')'{
		System.out.println("je parse CN"); 
	}
	| 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'{
		System.out.println("je parse CB"); 
	}
	| 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'{
		System.out.println("je parse CA"); 
	}
	| 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'{ 
		System.out.println("je parse CT"); 
	}
	| 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'{
		System.out.println("je parse CM"); 
	}
	;

	object_deletion
	:
	'SU(' id=NUMBER ')'{
		System.out.println("je parse SU"); 
	}
	| 'SI(' page_id=NUMBER ',' id=NUMBER ')'{
		System.out.println("je parse SI"); 
	}
	;


	domaine_table
	:
	'TD(' CAMI_STRING ')'{

		System.out.println("je parse le TD dans table domaine");
	}
	|'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')'{

		System.out.println("je parse le OB dans table domaine");
	}
	|'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'{

		System.out.println("je parse le AT dans table domaine");
	}
	|'FA()'{

		System.out.println("je parse le FA dans table domaine");
	}
	;
	modele
	:
	'DB()'{
		System.out.println("je parse BD"); 
	}
	modele*
	'FB()'{
		System.out.println("je parse FB"); 
	}
	;

	modele2
	:
	'CN(' CAMI_STRING ',' NUMBER ')'{
		System.out.println("je parse CN"); 
	}
	| 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'{
		System.out.println("je parse CB"); 
	}
	| 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'{
		System.out.println("je parse CA"); 
	}
	| 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'{ 
		System.out.println("je parse CT"); 
	}
	| 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'{
		System.out.println("je parse CM"); 
	}
	|'PO('NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'{
		System.out.println("je parse PO");
	}
	|'pO('NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'{
		System.out.println("je parse pO");
	}
	;

	dialogue
	:
	'DS('dialog_id=NUMBER ',' line_number=CAMI_STRING ')'{
		camiDialog.add($line_number.text);
		System.out.println("je parse DS"); 
	}
	| 'CE(' dialog_id=NUMBER ',' dialog_type=NUMBER ',' buttons_type=NUMBER ','  window_title=CAMI_STRING ',' help=CAMI_STRING ','             title_or_message=CAMI_STRING ',' input_type=NUMBER ',' line_type=NUMBER ',' default_value=CAMI_STRING? ')'{


		camiDialog.add($dialog_id.text); 
		camiDialog.add($dialog_type.text); 
		camiDialog.add($buttons_type.text); 
		camiDialog.add($window_title.text); 
		camiDialog.add($help.text); 
		camiDialog.add($title_or_message.text); 
		camiDialog.add($input_type.text);
		camiDialog.add($line_type.text);

		if($default_value != null)
		camiDialog.add($default_value.text); 
		else
		camiDialog.add(null/*new String("")*/);

		System.out.println("je parse CE"); 
	}
	|NEWLINE
	|'FF('{ 
		IDialog dialog = (IDialog)CamiObjectBuilder.buildDialog(camiDialog);

		dialogs.put((Integer) dialog.getId(), dialog); 

		System.out.println("je parse FF");
	}
	|'DC('{
		camiDialog = new ArrayList<String>();
		System.out.println("je parse DC");
	}
	|'AD('dialog_id=NUMBER ')'{


		Integer i = Integer.parseInt($dialog_id.text);

		((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(i),1);
		System.out.println("je parse AD");
	}
	|'CD('dialog_id=NUMBER ')'{

		Integer j = Integer.parseInt($dialog_id.text);
		((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(j),2);
		System.out.println("je parse CD");
	}
	|'DG(' dialog_id=NUMBER ')'{
		Integer k = Integer.parseInt($dialog_id.text);
		((IReceptDialogObservable)hashObservable.get("IReceptDialog")).notifyObservers(dialogs.get(k),3);
		dialogs.remove( k);

		System.out.println("je parse DG");
	}
	;
	/*
	dialog2
	: 
	'DS('NUMBER ',' CAMI_STRING ')'{
		System.out.println("je parse DS"); 
	}
	| 'CE(' dialog_id=NUMBER ',' dialog_type=NUMBER ',' buttons_type=NUMBER ','  window_title=CAMI_STRING ',' help=CAMI_STRING ',' title_or_message=CAMI_STRING ',' input_type=NUMBER ',' line_type=NUMBER ',' default_value=CAMI_STRING? ')'{
		System.out.println("je parse CE"); 
	}
	|NEWLINE
	|'FF('{ 
		System.out.println("je parse FF");
	}

	;*/



	/* ---------------------- types de base  CAMI_STRING, NUMBER --------------------------------*/


	/* Cami String*/
	CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':' 
	fs=FIXED_LENGTH_STRING[nbToRead]{setText($fs.text);}
	;

	fragment
	FIXED_LENGTH_STRING
	[int len]
	:   
	( { len > 0 }?=> .{len--;})* // Gated predicate : deactivate the '.' when len chars have been read
	;

	NUMBER	: 	
	'0'..'9'+
	;


	NEWLINE
	: 	
	( '\r'?'\n' )+ {skip();}
	;

	EOF     :
	{
		System.out.println("je parse EOOOFFFFF"); 
		skip();}
		;
