package fr.lip6.move.coloane.core.motor.formalisms.elements;

/**
 * Description d'un arc dans un formalisme.<br>
 * Plusieurs types d'arcs peuvent exister dans un formalisme.<br>
 * Par exemple, dans le formalisme des réseaux de Petri on retrouve :
 * <ul>
 * 	<li>Les arcs simples</li>
 * 	<li>Les arcs inhibiteurs</li>
 * </ul>
 */
public class Arc extends FormalismElement {

	/**
	 * Constructeur
	 * @param name Nom de l'arc dans le formalisme
	 */
	public Arc(String name) {
		super(name);
	}
}
