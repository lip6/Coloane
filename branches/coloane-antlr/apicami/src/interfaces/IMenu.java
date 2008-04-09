package interfaces;

import java.util.ArrayList;


/**
 * cette interface décrit un menu ( suite de AQ).
 * @author KAHOO & UU
 *
 */
public interface IMenu {

	/**
	 * donne le parent de l'élèment courant.
	 * @return IMenu (le pére).
	 */ 
	  IMenu getParent();          
	  
	  /**
		 * Donne le nom de l'élèment.
		 * @return string.
		 */
	  String getName();            
	  
	  /**
		 * Donne le type de la question.
		 * @return int.
		 */
	  int getQuestionType();      
	  
	  /**
		 * Donne le type du choix.
		 * @return int.
		 */
	  int getQuestionBehavior();  
	  
	  /**
		 * nous renseigne sur le validation.
		 * valide par defaut.
		 * @return bool.
		 */
	  boolean isValide();          
	  
	  /**
		 * nous renseigne sur le dialogue
		 * si interdit ou permis. 
		 * @return bool.
		 */
	  boolean isDialogAllowed();  
	  
	  /**
		 * arrêt possible ou pas.
		 * @return bool.
		 */
	  boolean StopAuthorized();   
	  
	  /**
		 * nous renseigne sur le domaine du résultat.
		 * @return bool.
		 */
	  boolean outputFormalism();  
	  /**
		 * Enable ou disable
		 * @return bool.
		 */
	  boolean IsActiv();    
	  
	  /**
		 * Donne les sous-menus fils de l'élèment.
		 * @return le tableau des fils.
		 */
	  ArrayList<IMenu> getChildren();  
	}

	
	

