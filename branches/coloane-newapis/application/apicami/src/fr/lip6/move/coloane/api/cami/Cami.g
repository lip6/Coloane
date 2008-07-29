grammar Cami;

@lexer::header{
	package fr.lip6.move.coloane.api.cami;
}           

@header{
	package fr.lip6.move.coloane.api.cami;

	import fr.lip6.move.coloane.api.camiObject.ConnectionInfo;
	import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
	import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
	import fr.lip6.move.coloane.api.interfaces.ISessionController;
	import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
	import fr.lip6.move.coloane.api.observables.ConnectionObservable;
	import fr.lip6.move.coloane.api.observables.DisconnectObservable;
	import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
	import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
	import fr.lip6.move.coloane.api.session.SessionController;
	import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
	import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
	import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
	import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
	import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
	import fr.lip6.move.coloane.interfaces.objects.service.IService;
	import fr.lip6.move.coloane.api.observables.ReceptDialogObservable; 
	import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
	import fr.lip6.move.coloane.api.camiObject.ReceptServiceState;
	import fr.lip6.move.coloane.api.observables.ReceptServiceStateObservable;
	import fr.lip6.move.coloane.api.observables.ReceptResultObservable;
	import fr.lip6.move.coloane.api.camiObject.Dialog; 

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.logging.Logger;
}

@members {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	List<String> listOfArgs; /* liste des arguments pour la construction des objets de notification */
	List<List<String>> camiQuestions; /* liste servant a construire les objets Correspondant aux AQ (questions) */
	List<List<String>> camiUpdates; /* liste servant a construire les objets Correspondant aux TQ 7 et 8 */
	Map<String, Object> hashObservable; /* Table de hash des observables */

	ISessionController sessionControl; /* le session controller */
	ISessionInfo sessionInfo; /*l'objet retourne a l'ouverture dune session*/        

	IDialog dialog; /* boite de dialogue recu de FK*/
	List<String> camiDialog; /* represente une boite de dialogue */
	Map<Integer,IDialog> dialogs; /* Table de hash des boites de dialogues */

	/** La liste des menus root transmis */
	List<ISubMenu> rootMenus = new ArrayList<ISubMenu>();

	/** La liste des services */
	List<IService> services = new ArrayList<IService>();

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
		synchronized (hashObservable) {
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
	v2=NUMBER {
		listOfArgs.add($v2.text);

		// Construction de l'objet contenant les informations de connexion
		LOGGER.finest("Fin de la construction des objets de connexion. Transmission...");
		IConnectionInfo connect = new ConnectionInfo(listOfArgs.get(0), listOfArgs.get(1), listOfArgs.get(2));
		((ConnectionObservable) hashObservable.get("IConnection")).notifyObservers(connect);
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
		LOGGER.finest("Creation des tables de menus et de modifications");
		camiUpdates = new ArrayList<List<String>>();
		/** La liste des menus root transmis */
		rootMenus = new ArrayList<ISubMenu>();
		/** La liste des services */
		services = new ArrayList<IService>();
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
                //Notifier au sessionController de l'acquittement du RS
		sessionControl.notifyEndResumeSession($CAMI_STRING.text);
	}
	;

	/* ----------------------------  Fermeture d'une session ----------------------------- */
	ack_close_current_session
	:
	'FS()'{
                //Notifier au sessionController de l'acquittement du FS
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
                //notifier le session controller de la construction de sessionInfo
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
		// Initialisation de la liste des questions
		camiQuestions = new ArrayList<List<String>>();
	}
	menu_name
	question_add*
	'FQ()'{
		// Construction du menu racine
		ISubMenu root = CamiObjectBuilder.buildRootMenu(camiQuestions.get(0));
		rootMenus.add(root);
		LOGGER.finest("Construction du RootMenu " + root.getName() + " et ajout a la liste des menu");

		// Suppression de la description du root et construction de la liste des questions
		camiQuestions.remove(0);
		List<IQuestion> questions = CamiObjectBuilder.buildQuestions(camiQuestions);
		LOGGER.finest("Nombre de questions recues : " + questions.size());

		// Construction de l'objet menu
		root = CamiObjectBuilder.buildMenus(root, questions);

		// Ajout de la liste des services
		LOGGER.finest("Ajout des services pour le menu " + root.getName());
		services.addAll(CamiObjectBuilder.buildServices(root, questions));
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

		LOGGER.finest("Ajout de la description du menu root");
		camiQuestions.add(cq); // Ajouter au sommet de la liste des AQ
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

		camiQuestions.add(aq); /* Ajouter a la liste de AQ */
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
		List<IUpdateMenu> updates;
		
		if($NUMBER.text.equals("3")) {
			LOGGER.finest("Fin de la transmission d'un menu");
			updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
			((ReceptMenuObservable) hashObservable.get("ISession")).notifyObservers(rootMenus, updates, services);
			// Nettoyage des updates
			camiUpdates.clear();
			// Notifier au session controleur la fin de l'ouverture de la session (i.e reception des menus + updates)
			sessionControl.notifyEndOpenSession();
		} else {
			LOGGER.finest("Fin de la transmission des updates apres une invalidation de modele");
			// Notifier au session controleur de la reception des updates 
			sessionControl.notifyEndUpdates();
			updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
			((ReceptMenuObservable) hashObservable.get("ISession")).notifyObservers(null, updates, null);
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
	'MO(' number=NUMBER ',' mess=CAMI_STRING ')'{ 
		if($number.text.equals("1")) { 
			LOGGER.finest("Reception d'un message de l'administrateur"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(1,$CAMI_STRING.text); 
			((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg); 
		}
    
		if($number.text.equals("2")) {
			//TODO :Verifier qu'il faut traiter ce message comme un KO
			LOGGER.finest("Reception d'un message court et urgent"); 
			((BrutalInterruptObservable) hashObservable.get("IBrutalInterrupt")).notifyObservers($mess.text);  
		}

		if($number.text.equals("3")) { 
			LOGGER.finest("Reception d'un message copyright"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(3,$CAMI_STRING.text); 
			((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg); 
		}
    
		if($number.text.equals("4")) { 
			LOGGER.finest("Reception d'un message a propos des statistiques d'execution"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(4,$CAMI_STRING.text); 
			((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg); 
		}
	}
	;
    
	/* ---------------------------- Message KO ------------------------------------------- */
	brutal_interrupt
	:
	'KO(1,' mess=CAMI_STRING ',' level=NUMBER ')' {
		// TODO : Differencier les KOs (1 2 ou 3)
		LOGGER.finest("Reception d'un message KO");
		((BrutalInterruptObservable) hashObservable.get("IBrutalInterrupt")).notifyObservers($CAMI_STRING.text);
	}
	;
    
    
	/* ---------------------------- Demande du modele  -----------------------------*/
	ask_for_a_model
	:                                                       
	'DF(-2,' NUMBER ',' NUMBER ')'{
	        LOGGER.finest("Reception d'un message DF");
                // notifier le session controlleur de la demande du modele
		sessionControl.notifyWaitingForModel();
		
	}
	;
    
    
	/*-------------------------resultats--------------------------------------------*/
	result_reception
	:
	'DR()'{ 
		// initialiser la liste des updates 
		//    camiUpdates = new ArrayList<ArrayList<String>>();
		System.out.println("je parse DR");
                // notifier le session controlleur de la demande de service
		sessionControl.notifyWaitingForResult();
	}
	|'<EOF>'*
	|'RQ(' service_name1=CAMI_STRING ',' question_name1=CAMI_STRING ',' num1=NUMBER ')'{
		System.out.println("je parse RQ"); 
	} 
	|'<EOF>'*
	|'TQ(' service_name2=CAMI_STRING ',' question_name2=CAMI_STRING ',' state2=NUMBER ',' mess2=CAMI_STRING? ')' {

		if($state2.text.equals("1")) { 
			if($mess2 != null) { 
				LOGGER.finest("Reception d'un TQ 1"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,1,$mess2.text); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 1"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,1,null); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		}

		if($state2.text.equals("2")) { 
			if($mess2 != null) { 
				LOGGER.finest("Reception d'un TQ 2"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,2,$mess2.text); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 2"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,2,null); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 

		if($state2.text.equals("3")) { 
			if($mess2 != null) { 
				LOGGER.finest("Reception d'un TQ 3"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,3,$mess2.text); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 3"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,3,null); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 
    
		if($state2.text.equals("4")) { 
			if($mess2 != null) { 
				LOGGER.finest("Reception d'un TQ 4"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,4,$mess2.text); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 4"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,4,null); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 
    
		if($state2.text.equals("5")) { 
			if($mess2 != null) { 
				LOGGER.finest("Reception d'un TQ 5"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,5,$mess2.text); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 5"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,5,null); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 

		if($state2.text.equals("6")) {
			if($mess2 != null) { 
				LOGGER.finest("Reception d'un TQ 6"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,6,$mess2.text); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 6"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($service_name2.text,6,null); 
				((ReceptServiceStateObservable)hashObservable.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		}
	}
	|result*
	|message_utils*
	|domaine_table*
	|dialogue*
	|modele*
	|'FR(' NUMBER ')'{
		//TODO envoyer les resultats
		LOGGER.finest("Reception d'un FR"); 
		// notifier Coloane  de la fin de reception des resultats 
		sessionControl.notifyEndResult();
		((ReceptResultObservable) hashObservable.get("IReceptResult")).notifyObservers();
		
	}
	;
    
	message_utils
	:
	trace_message2 
	| warning_message2 
	| special_message2
	|NEWLINE
	| 'ZA('NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'{
		//  le traiter ?
		System.out.println("je parse ZA");
	}
	;
    
	trace_message2
	:
	'TR(' CAMI_STRING ')'{ 
		LOGGER.finest("Reception d'un message de trace"); 
		IReceptMessage msg = (IReceptMessage) new ReceptMessage(4,$CAMI_STRING.text); 
		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
	}
	;
    
	warning_message2
	:
	'WN(' CAMI_STRING ')'{
		LOGGER.finest("Reception d'un message de warning"); 
		IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,$CAMI_STRING.text); 
		((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg);
	}
	;

	special_message2
	:	
	'MO(' number=NUMBER ',' mess=CAMI_STRING ')'{ 
		if($number.text.equals("1")){ 
			LOGGER.finest("Reception d'un message de l'administrateur"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(1,$CAMI_STRING.text); 
			((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg); 
		}
		if($number.text.equals("2")){ 
			LOGGER.finest("Reception d'un message court et urgent"); 
			((BrutalInterruptObservable) hashObservable.get("IBrutalInterrupt")).notifyObservers($mess.text);  
		}
		if($number.text.equals("3")){ 
			LOGGER.finest("Reception d'un message copyright"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(3,$CAMI_STRING.text); 
			((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg); 
		}
		if($number.text.equals("4")){ 
			LOGGER.finest("Reception d'un message a propos des statistiques dexecution"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(4,$CAMI_STRING.text); 
			((ReceptMessageObservable) hashObservable.get("IReceptMessage")).notifyObservers(msg); 
		}
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
    
		if($default_value != null) {
			camiDialog.add($default_value.text); 
		} else {
			camiDialog.add(null);
		}
		
		System.out.println("je parse CE"); 
	}
	|NEWLINE
	|'FF('{ 
                // je construit une boite de dialogue et je la sauvegarde
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
		Dialog dialog = (Dialog)dialogs.get(i); 
		dialog.setVisibility(1); 
		((ReceptDialogObservable) hashObservable.get("IReceptDialog")).notifyObservers(dialog);
		LOGGER.finest("boite de dialogue a afficher"); 
	}
	|'CD('dialog_id=NUMBER ')'{
		Integer j = Integer.parseInt($dialog_id.text);
		Dialog dialog = (Dialog)dialogs.get(j); 
		dialog.setVisibility(2); 
		((ReceptDialogObservable) hashObservable.get("IReceptDialog")).notifyObservers(dialog);
		LOGGER.finest("boite de dialogue a cacher"); 
	}
	|'DG(' dialog_id=NUMBER ')'{
		Integer k = Integer.parseInt($dialog_id.text);
		Dialog dialog = (Dialog)dialogs.get(k); 
		dialog.setVisibility(3); 
		((ReceptDialogObservable) hashObservable.get("IReceptDialog")).notifyObservers(dialog);
		dialogs.remove( k);
		LOGGER.finest("boite de dialogue a effacer"); 
	}
	;
    
    
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
		skip();
	}
	;
