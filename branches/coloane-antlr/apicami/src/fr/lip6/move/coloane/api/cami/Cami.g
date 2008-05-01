grammar Cami;

@header{
package fr.lip6.move.coloane.api.cami;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.observables.IConnectionObservable;

import java.util.ArrayList;
import java.util.HashMap;
}

@members {
   ArrayList<String> listOfArgs; /* liste des arguments pour la construction des 
                                    objets de notification */
   
   ArrayList<ArrayList<String>> menuList; /* liste servant à construire les objets 
                                               Correspondant aux AQ et les TQ */
   HashMap<String, Object> hashObservable; /* Table de hash des observables */

   ISessionController sc; /* Controleur de la session */

   IFkInfo fkinfo; 

   IMenu menu;

  
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
    ack_open_communication | ack_open_connection
    | ack_open_session | receving_menu
    |question_state*
    |end_menu_transmission
    ;


/* ----------  Ouverture de la connexion  SC --------------- */
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
	

/* ---------- Ouverture de la connexion OC ------------------ */
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

/* ----------- Ouverture de la session ------------------------ */
ack_open_session
	:
	'OS(' CAMI_STRING')'{
            //TODO ajouter un controle que c OS
            System.out.println("OS");
        }
	|'TD()'{
            // Ajouter un controle qu'on doit bien recevoir TD
            System.out.println("TD");
        }
	|'FA()'{// Ajouter un controle qu'on doit bien recevoir FA}
            System.out.println("FA");
        }

    |interlocutor_table

	;

/* -----------  Reception des tables  -------------------------- */
interlocutor_table
    :
    'TL()'{            
        }
    |'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' incremental=NUMBER 
            /* toujours 3 */ ',' new_model=NUMBER /* resultat déja calculé ?*/')' {
           
            listOfArgs = new ArrayList<String>();
            listOfArgs.add($service_name.text);
            listOfArgs.add($about_service.text);
            listOfArgs.add($incremental.text);
            listOfArgs.add($new_model.text);            
        }
    |'FL()'{
            fkinfo = CamiObjectBuilder.buildFkInfo(listOfArgs);

            System.out.println("fkinfo");
            for(int i=0; i<this.listOfArgs.size(); i++){
                System.out.println(this.listOfArgs.get(i));
            }

        }
    ;



/* reception des menus */
receving_menu
    :
	'DQ()'{
            /* créer la menuList  */
            menuList = new ArrayList<ArrayList<String>>();
        }
	menu_name
	question_add*
	'FQ()'{
            /* fin de la reception des menus : demander la construction du menu */            
            //            menu = CamiObjectBuilder.buildMenu(menuList);
            System.out.println("FQ()");
        }
    'VQ('CAMI_STRING')'{
            System.out.println("VQ");
        }
	;


/* reception des menus  CQ */
menu_name
	:
	'CQ(' name=CAMI_STRING ',' question_type=NUMBER ',' question_behavior=NUMBER ')'{
     
            // TODO :  Veifier qu'on est dans la réception de menus racine !!!

            /* racine des question  */
            ArrayList<String> cq = new ArrayList<String>();
            cq.add($name.text); /* racine  */
            cq.add($question_type.text); /* type de la question  */
            cq.add($question_behavior.text); /* type du choix */

            menuList.add(cq); /* ajouter a la liste des AQ */

                       System.out.println("CQ");
                        System.out.println("name.getText() " + name.getText() );
                        System.out.println("question_type.getText() " + question_type.getText());
                        System.out.println("question_behavior.getText() " + question_behavior.getText());

        }
	;

/* reception des menus  AQ */
question_add
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
		question_type=NUMBER? ',' question_behavior=NUMBER? ',' 
		set_item=NUMBER? ','  dialog=NUMBER? ',' stop_authorized=NUMBER? ',' 
		output_formalism=CAMI_STRING? ',' active=NUMBER? ')'{

            // TODO Veifier qu'on est dans la réception de menus
            ArrayList<String> aq = new ArrayList<String>();
            aq.add($parent_menu.text); /* parent  */
            aq.add($entry_name.text);  /* entry_name  */
            
            if($question_type != null)
                aq.add($question_type.text); /* question_type  */
            else
                aq.add(new String(""));

            if($question_behavior != null)
                aq.add($question_behavior.text); /* question_behavior  */
            else
                aq.add(new String(""));

            if($set_item != null)
                aq.add($set_item.text); /* validation par defaut  */
            else
                aq.add(new String(""));
            
            if($dialog != null)
                aq.add($dialog.text); /* dialog autorisé ?  */
            else
                aq.add(new String(""));


            if($stop_authorized != null)
                aq.add($stop_authorized.text); /* on autorise l'arrêt du service ? */
            else
                aq.add(new String(""));


            if($output_formalism != null)
                aq.add($output_formalism.text); /* formalisme */
            else
                aq.add(new String(""));


            if($active != null)
                aq.add($active.text); /* grisé ou non ? */
            else
                aq.add(new String(""));


            menuList.add(aq); /* ajouter à la liste de AQ */

                        System.out.print("AQ(" + aq.get(0));
                        for(int i=1; i<9; i++){
                            System.out.print(", " + aq.get(i));
                        }
                        System.out.println(")");

        }
	;


question_state /* TQ de type 7 et 8*/
	:
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=NUMBER ',' mess=CAMI_STRING? ')'{
            System.out.println("TQ(" + $service_name.text + ", " + $question_name.text + ", " + 
                                        $state.text + ", " + ")");
        }
	;


/* fin de la transmission d'un menu QQ(3)*/
end_menu_transmission
	:
	'QQ(' NUMBER ')'{
            System.out.println("QQ(" + $NUMBER.text + ")");
        }
	;






/* message special MO */
pecial_message
	:	    
	'MO(' NUMBER ',' CAMI_STRING ')'{
            // verifier qu'on est dans la reception des menus ou autre
        }
	;





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
