package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;

import java.util.List;
import java.util.Map;

/**
 * Arbre de résultats<br>
 * Les lignes sont décrites par l'arborescence et les colonnes par la liste d'élément.
 */
public interface IResultTree {

	/**
	 * Retourne le père du sous-arbre
	 * @return l'arbre parent
	 */
	IResultTree getParent();
	
	/**
	 * Indique qui est le père de l'arbre de résultats.<br>
	 * Attention ! Seul le lien fils -> pere est fait.<br>
	 * Utilisez de preference la methode {@link #addChild(IResultTree)}
	 * @param parent L'arbre de resultat parent
	 */
	void setParent(IResultTree parent);

	/**
	 * Retourne la liste des fils du resultat
	 * @return liste des fils
	 */
	List<IResultTree> getChildren();

	/**
	 * Permet de rajouter un fils à ce noeud
	 * @param child fils à ajouter
	 */
	void addChild(IResultTree child);

	/**
	 * Retourne la liste des colonnes associées a un resultat
	 * @return liste d'objets représentant les colonnes d'une ligne de l'arbre des résultats
	 */
	List<Object> getElement();

	/**
	 * Retourne la liste des elements a mettre en valeur lors de la selection de ce resultat
	 * @return la liste d'identifiants d'objets a mettre en valeur
	 */
	List<Integer> getHighlighted();
	
	/**
	 * Ajoute un ou plusieurs elements à mettre en valeur lors de la selection du sous-resultat
	 * @param toHighlight élément(s) à ajouter
	 */
	void addHighlighted(int... toHighlight);

	/**
	 * @return La liste des attributs qui doivent être mis en valeur
	 */
	Map<Integer, List<String>> getAttributesOutline();
	
	/**
	 * Supprime ce service de la liste des resultats
	 * TODO : changer l'explication en fonction de ce que fait la méthode
	 * @see {@link SessionManager.#getInstance()}
	 */
	void remove();

	/**
	 * Associe un gestionnaire de session avec le sous-arbre de resultats.<br>
	 * Cette methode doit etre appelé sur le noeud pere. Les fils trouveront le gestionnaire par parcours d'arbre
	 * @param sessionManager Le gestionnaire de session
	 * @see SessionManager.#getInstance()
	 */
	void setSessionManager(ISessionManager sessionManager);

	/**
	 * Retourne le gestionnaire de session enregistre pour cet arbre de resultats<br>
	 * La recherche est resursive (en partant des fils... jusqu'au pere)
	 * @return le gestionnaire de session ou null si il est introuvable
	 */
	ISessionManager getSessionManager();

	/**
	 * @return la liste des tips à afficher quand ce résultat est sélectionné
	 */
	List<ICoreTip> getTips();

	/**
	 * @param mayHaveTips ? TODO: Documenter
	 * @return la liste des tips en fonction des identifiants d'objets
	 */
	List<ICoreTip> getTips(List<Integer> mayHaveTips);

	/**
	 * Permet de spécifier la liste des tips pour de résultat
	 * @param tips liste des tips
	 */
	void setTips(List<ICoreTip> tips);
}
