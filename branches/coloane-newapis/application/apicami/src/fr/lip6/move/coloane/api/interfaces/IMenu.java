package fr.lip6.move.coloane.api.interfaces;

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
	  public IMenu getParent();

	  /**
		 * Donne le nom de l'élèment.
		 * @return string.
		 */
	  public String getName();

	  /**
		 * Donne le type de la question.
		 * @return int.
		 */
	  public int getQuestionType();

	  /**
		 * Donne le type du choix.
		 * @return int.
		 */
	 public int getQuestionBehavior();

	  /**
		 * nous renseigne sur le validation.
		 * valide par defaut.
		 * @return bool.
		 */
	  public boolean isValid();

	  /**
		 * nous renseigne sur le dialogue
		 * si interdit ou permis.
		 * @return bool.
		 */
	  public boolean isDialogAllowed();

	  /**
		 * arrêt possible ou pas.
		 * @return bool.
		 */
	  public boolean stopAuthorized();

	  /**
		 * nous renseigne sur le domaine du résultat.
		 * @return bool.
		 */
	  public String outputFormalism();
	  /**
		 * Enable ou disable
		 * @return bool.
		 */
	  public boolean isActivate();

	  /**
		 * Donne les sous-menus fils de l'élèment.
		 * @return le tableau des fils.
		 */
	  public ArrayList<IMenu> getChildren();

	  
	  public boolean addMenu(String parentName, IMenu menu);


	  public void setParent(IMenu menu);


	}




