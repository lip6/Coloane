package fr.lip6.move.coloane.core.motor.formalisms.elements;

/**
 * Description d'un noeud dans un formalisme.<br>
 * Plusieurs types de noeuds peuvent exister dans un formalisme.<br>
 * Par exemple, dans le formalisme des réseaux de Petri on retrouve :
 * <ul>
 * 	<li>Les places</li>
 * 	<li>Les transitions</li>
 * </ul>
 * 
 * Dans certains cas, les noeuds peuvent contenir un ensemble de noeuds, arcs, attributs etc...<br>
 * Cette capacité permet de définir des formalismes où la hiérarchie est possible.</br>
 * Cette définition définit un attribut {@link #container} qui permet de préciser cette compétence.
 */
public class Node extends FormalismElement {
	
	/** Le noeud peut-il contenir un modèle ? @see {@link Graph} */
	private boolean container = false;

	/**
	 * Constructeur
	 * @param name Nom de l'element de base.
	 */
	public Node(String name) {
		super(name);
	}

	/**
	 * @return Un indicateur qui permet de savoir si le noeud peut contenir un modèle
	 */
	public boolean isContainer() {
		return container;
	}

}
