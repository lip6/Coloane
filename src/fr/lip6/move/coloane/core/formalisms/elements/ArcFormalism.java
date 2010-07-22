package fr.lip6.move.coloane.core.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

/**
 * Description d'un arc dans un formalisme.<br>
 * Plusieurs types d'arcs peuvent exister dans un formalisme.<br>
 * Par exemple, dans le formalisme des réseaux de Petri on retrouve :
 * <ul>
 * 	<li>Les arcs simples</li>
 * 	<li>Les arcs inhibiteurs</li>
 * </ul>
 */
public class ArcFormalism extends ElementFormalism implements IArcFormalism {

	/**
	 * Constructeur
	 * @param name Nom de l'arc dans le formalisme
	 * @param formalism Le formalisme qui définit cet arc
	 */
	public ArcFormalism(String name, IFormalism formalism) {
		super(name, formalism);
	}
}
