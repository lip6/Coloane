package fr.lip6.move.coloane.core.results.reports;

import java.util.List;

import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public interface IReport {
	/**
	 * Construction de l'arbre des résultats qui seront afficher dans la fenetre
	 * "résultats"
	 * @param result Objet contenant les données bruts
	 * @return Arbre des résultats
	 */
	public IResultTree build(IResultsCom result);
	
	/**
	 * Construction d'une liste de noeud à mettre en valeur
	 * @param result Objet contenant les données bruts
	 * @return Liste des id des noeuds à mettre en valeur
	 */
	public List<Integer> highlightNode(IResultsCom result);
}
