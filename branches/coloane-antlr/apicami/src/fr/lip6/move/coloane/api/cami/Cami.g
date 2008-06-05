grammar Cami;

@lexer::header{
package fr.lip6.move.coloane.api.cami;
            
        }           

@header{
package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ISessionObservable;
import fr.lip6.move.coloane.api.interfaces.observables.ITraceMessageObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IWarningObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IAskForModelObservable;
import fr.lip6.move.coloane.api.interfaces.observables.IBrutalInterruptObservable;

import java.util.ArrayList;
import java.util.HashMap;
}

@members {
   ArrayList<String> listOfArgs; /* liste des arguments pour la construction des 
                                    objets de notification */
   
   ArrayList<ArrayList<String>> camiMenuList; /* liste servant à construire les objets 
                                               Correspondant aux AQ */

   ArrayList<ArrayList<String>> camiUpdates; /* liste servant à construire les objets 
  //                                             Correspondant aux TQ 7 et 8 */

   HashMap<String, Object> hashObservable; /* Table de hash des observables */

   ISessionController sc; /* Controleur de la session */

   IFkInfo fkInfo; 

   IMenu menu;
   ArrayList<IMenu> menuList;

   ArrayList<IUpdateItem> updates;


  
   /* Constructeur du parser */
   public CamiParser(TokenStream input, ISessionController sessionController, 
                                                    HashMap<String, Object> hm) {
       this(input);
       hashObservable = hm;       
       sc = sessionController;
   }
} /* fin du header */


/* --------------------------------------------------------------------------- *
 *                                  PRODUCTIONS                                *
 * --------------------------------------------------------------------------- */


command
    : 
    ack_open_communication | ack_open_connection | close_connection
    | ack_open_session | receving_menu
    |update*
    |end_menu_transmission

    |ack_suspend_current_session
    |ack_resume_suspend_current_session
    |ack_close_current_session

    |ask_for_a_model
    |message_to_user
    |brutal_interrupt 
    |result_reception
    ;



/* ---------------------  Ouverture de la connexion  SC ----------------------- */
ack_open_communication
	:
	'SC(' CAMI_STRING ')'{
            listOfArgs = new ArrayList<String>();
            listOfArgs.add($CAMI_STRING.text);
            synchronized(hashObservable){
                hashObservable.notify();
            }
        }
    ;
	



/* ---------------------- Ouverture de la connexion OC ------------------------- */
ack_open_connection
	:
	'OC(' 
    v1=NUMBER {
            listOfArgs.add($v1.text);
        } 
    ',' 
    v2=NUMBER {listOfArgs.add($v2.text);
            IFkVersion version = CamiObjectBuilder.buildFkVersion(listOfArgs);
            ((IConnectionObservable)hashObservable.get("IConnection")).notifyObservers(version);
            synchronized(hashObservable){
                hashObservable.notify();
            }
        }
    ')'    
	;


/* ---------------------- Fermeture de la connexion coté FrameKit -------------- */
close_connection
    :
    'FC()'{
            /* TODO ON VERRA CE QU'ON FERA ICIIIIIIIIIIIII*/
        }
    ;
/* ---------------------- Ouverture de la session ------------------------------ */
ack_open_session
	:
	'OS(' CAMI_STRING')'{
            //TODO ajouter un controle que c OS
            System.out.println("je parse le OS");
            
            /* on initialise ici la table des menus : on ne voit pas d'autre endroit ....*/
            menuList = new ArrayList<IMenu>();
            /*  */
            camiUpdates = new ArrayList<ArrayList<String>>();
        }
	|'TD()'{
            // Ajouter un controle qu'on doit bien recevoir TD
           System.out.println("je parse le TD");
        }
	|'FA()'{// Ajouter un controle qu'on doit bien recevoir FA}
           System.out.println("je parse le FA");
        }
        
    |interlocutor_table
        
	;
    
/* ----------------------  Suspension de la session courante -------------------- */ /*8888888888888888 -TODO- 888888888888888**/
ack_suspend_current_session 
	:	 
	'SS()'{/* Notifier au sessionController de l'acquittement du SS  */
            sc.notifyEndSuspendSession();
        }
	;



/* ----------------------------  reprise d'une session -------------------------- */ /*8888888888888888 -TODO- 888888888888888**/
ack_resume_suspend_current_session
	:
	'RS(' CAMI_STRING ')'{
            sc.notifyEndResumeSession($CAMI_STRING.text);
        }
	;


/* ----------------------------  fermeture d'une session -------------------------- */ /*8888888888888888 -TODO- 888888888888888**/
ack_close_current_session
	:
	'FS(' CAMI_STRING ')'{
            sc.notifyEndCloseSession();
        }
	;




/* ---------------------  Reception des tables  -------------------------------- */
interlocutor_table
    :
    'TL()'{ 
          System.out.println("je parse le TL");           
        }
    |'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' incremental=NUMBER 
            /* toujours 3 */ ',' new_model=NUMBER /* resultat déja calculé ?*/')' {
           
            listOfArgs = new ArrayList<String>();
            listOfArgs.add($service_name.text);
            listOfArgs.add($about_service.text);
            listOfArgs.add($incremental.text);
            listOfArgs.add($new_model.text); 
            System.out.println("je parse le VI");           
        
        }
    |'FL()'{
            fkInfo = CamiObjectBuilder.buildFkInfo(listOfArgs);
            System.out.println("je parse le FL");          
//            System.out.println("fkinfo");
            for(int i=0; i<this.listOfArgs.size(); i++){
//              System.out.println(this.listOfArgs.get(i));
            }

        }
    ;



/* ------------------- reception des menus -----------------------------------------*/
receving_menu
    :
	'DQ()'{
            /* créer la menuList  */
            camiMenuList = new ArrayList<ArrayList<String>>();
          System.out.println("je parse le DQ");
        }
	menu_name
	question_add*
	'FQ()'{
            /* fin de la reception des menus : demander la construction du menu */            
            menu = CamiObjectBuilder.buildMenu(camiMenuList);

            System.out.println("nombre de AQ : " + camiMenuList.size());
          System.out.println("je parse le FQ");
            menuList.add(menu);
            
//            System.out.println("Menu construit");
//            System.out.println("FQ()");
        }
    'VQ('CAMI_STRING')'{ /* afficher les questions */
            System.out.println("je parse le VQ");
            
        }
	;



/* ------------------  reception des menus  CQ ------------------------------------- */
menu_name
	:
	'CQ(' name=CAMI_STRING ',' question_type=NUMBER ',' question_behavior=NUMBER ')'{
     
            // TODO :  Veifier qu'on est dans la réception de menus racine !!!

            /* racine des question  */
            ArrayList<String> cq = new ArrayList<String>();
            cq.add($name.text); /* racine  */
            cq.add($question_type.text); /* type de la question  */
            cq.add($question_behavior.text); /* type du choix */

            camiMenuList.add(cq); /* ajouter a la liste des AQ */
                        System.out.println("je parse le CQ");
//                        System.out.println("name.getText() " + name.getText() );
//                        System.out.println("question_type.getText() " + question_type.getText());
//                        System.out.println("question_behavior.getText() " + question_behavior.getText());

        }
	;


/* ------------------- reception des menus  AQ ---------------------------------------*/
question_add
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
		question_type=NUMBER? ',' question_behavior=NUMBER? ',' 
		set_item=NUMBER? ','  dialog=NUMBER? ',' stop_authorized=NUMBER? ',' 
		output_formalism=CAMI_STRING? ',' active=NUMBER? ')'{
       System.out.println("je parse le AQ");
            // TODO Veifier qu'on est dans la réception de menus
            ArrayList<String> aq = new ArrayList<String>();
            aq.add($parent_menu.text); /* parent  */
            aq.add($entry_name.text);  /* entry_name  */
            
            if($question_type != null)
                aq.add($question_type.text); /* question_type  */
            else
                aq.add(null/*new String()*/);

            if($question_behavior != null)
                aq.add($question_behavior.text); /* question_behavior  */
            else
                aq.add(null/*new String("")*/);

            if($set_item != null)
                aq.add(null/*$set_item.text*/); /* validation par defaut  */
            else
                aq.add(null/*new String("")*/);
            
            if($dialog != null)
                aq.add($dialog.text); /* dialog autorisé ?  */
            else
                aq.add(null/*new String("")*/);


            if($stop_authorized != null)
                aq.add($stop_authorized.text); /* on autorise l'arrêt du service ? */
            else
                aq.add(null/*new String("")*/);


            if($output_formalism != null)
                aq.add($output_formalism.text); /* formalisme */
            else
                aq.add(null/*new String("")*/);


            if($active != null)
                aq.add($active.text); /* grisé ou non ? */
            else
                aq.add(null/*new String("")*/);


            camiMenuList.add(aq); /* ajouter à la liste de AQ */
            
            
      /* TODO : a enlever */

//                        System.out.print("AQ(" + aq.get(0));
                        for(int i=1; i<9; i++){
//                            System.out.print(", " + aq.get(i));
                        }
//                        System.out.println(")");

        }
	;


/* --------------------- reception des TQ de type 7 et 8 -------------------------------- */
update /* TQ de type 7 et 8*/
	:   
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=('7'|'8') ',' mess=CAMI_STRING? ')'{
            
            
            /*  */
            ArrayList<String> update = new ArrayList<String>();
            
            update.add($service_name.text); /* nom du service */
            update.add($question_name.text);  /* nom de la question  */
            update.add($state.text);  /* état de la question  */
            
            if(!$state.text.equals("7") && !$state.text.equals("8")) /* si c'est un modificateur de menu */
                update.add($mess.text); /* message : optionnel */          
            
            
            camiUpdates.add(update);/* ajouter à la liste des updates  */
            
            System.out.println("je parse le TQ");
        }
    ;


/* ----------------------- fin de la transmission d'un menu QQ(3) --------------------------*/
end_menu_transmission
	:       
	'QQ(' NUMBER ')'{
            
//            System.out.println("QQ(" + $NUMBER.text + ")");
            
            
            updates = CamiObjectBuilder.buildUpdateItem(camiUpdates);
            ((ISessionObservable)hashObservable.get("ISession")).notifyObservers(fkInfo, menuList, updates);

            /* notifier le sessionController qu'on a reçu les menus et les updates */            
            sc.notifyEndOpenSession();
        }
    ;




/* ------------------------- Résultats de demande de service  -------------------------------*/






/* ------------------------- Messages provenant de FrameKit  ----- --------------------------*/

message_to_user
	:
	trace_message | warning_message | special_message
	;

trace_message
	:
	'TR(' CAMI_STRING ')'{
          ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers($CAMI_STRING.text);
 System.out.println("je parse le TR");
        }
	;

warning_message
	:
	'WN(' CAMI_STRING ')'{
         ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers($CAMI_STRING.text);
 System.out.println("je parse le WN");
        }
	;

special_message
	:	
	'MO(' NUMBER ',' CAMI_STRING ')'{
          ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers($CAMI_STRING.text);            
      System.out.println("je parse le MO");
        }
	;



/* ------------------------- Message KO ---------------------------*/

brutal_interrupt
		:
		'KO(1,' mess=CAMI_STRING ',' level=NUMBER ')'{
                System.out.println("je parse le KO");
	//((IBrutalInterruptObservable)hashObservable.get("IBrutalInterrupt")).notifyObservers($CAMI_STRING.text);
		}
		;


/* ---------------------------- Demande du modele  -----------------------------*/
ask_for_a_model
	:                                                       
    'DF(-2,' NUMBER ',' NUMBER ')'{
         System.out.println("je parse le DF");
      //  ((IAskForModelObservable)hashObservable.get("IAskForModel")).notifyObservers();
     }
	;


/* ------------------------- reception des resultats ---------------------------*/


result_reception
       :
        'DR()'{ 
            // initialiser la liste des updates 
        //    camiUpdates = new ArrayList<ArrayList<String>>();
          System.out.println("je parse DR");
        }
       |'<EOF>'*
       |'RQ(' service_name1=CAMI_STRING ',' question_name1=CAMI_STRING ',' num1=NUMBER ')'{
        System.out.println("je parse RQ"); 
        } 
        |'<EOF>'*
        'TQ(' service_name2=CAMI_STRING ',' question_name2=CAMI_STRING ',' state2=NUMBER/*('2'|'3'|'4'|'5'|'6'|'9')*/ ',' mess2=CAMI_STRING? ')'{ 
            
          //  ((IServiceStateObservable)hashObservable.get("IServiceState")).notifyObservers();
          System.out.println("je parse TQ2");
          }
        |result*
        |message_utils*
        |domaine_table*
        |dialogue*
        |modele*
        |'FR(' NUMBER ')'{
         System.out.println("je parse FR");
            //TODO notifier Coloane  de la fin de reception des resulstats et envoyer les resultats
        }
;

message_utils
          :
	  trace_message2 
          | warning_message2 
          | special_message2
          |NEWLINE
          | 'ZA('NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')'{
          System.out.println("je parse ZA");
          }
;

trace_message2
	:
	'TR(' CAMI_STRING ')'{
          ((ITraceMessageObservable)hashObservable.get("ITraceMessage")).notifyObservers($CAMI_STRING.text);
 System.out.println("je parse le TR");
        }
	;

warning_message2
	:
	'WN(' CAMI_STRING ')'{
         ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers($CAMI_STRING.text);
 System.out.println("je parse le WN");
        }
	;

special_message2
	:	
	'MO(' NUMBER ',' CAMI_STRING ')'{
          ((IWarningObservable)hashObservable.get("IWarning")).notifyObservers($CAMI_STRING.text);            
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
    dialog2+
    '<EOF>'*
    |'DC('{
        System.out.println("je parse DC");
    }
    |'AD('NUMBER ')'{
         System.out.println("je parse AD");
        }
;

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
