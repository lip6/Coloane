package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;

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
public class NodeFormalism extends ElementFormalism implements INodeFormalism {

	/** Le noeud peut-il contenir un modèle ? @see {@link GraphFormalism} */
	private boolean container = false;

	/**
	 * Constructeur
	 * @param name Nom de l'element de base.
	 * @param formalism Le formalisme qui définit ce noeud
	 */
	public NodeFormalism(String name, IFormalism formalism) {
		super(name, formalism);
	}

	/** {@inheritDoc} */
	public final boolean isContainer() {
		return container;
	}

}
