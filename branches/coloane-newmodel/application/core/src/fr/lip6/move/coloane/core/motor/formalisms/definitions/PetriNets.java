package fr.lip6.move.coloane.core.motor.formalisms.definitions;

import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;

public class PetriNets extends Formalism {

	private static final String NAME = "AMI-Net"; //$NON-NLS-1$
	private static final String IMG = "/resources/icons/instance.gif"; //$NON-NLS-1$
	private static final String EXTENSION = "petri"; //$NON-NLS-1$
	private static final String XSCHEMA = "ami-net.xsd"; //$NON-NLS-1$

	/**
	 * Constructeur de l'instance du formalisme
	 */
	public PetriNets() {
		super(NAME, EXTENSION, XSCHEMA, IMG);
		
		this.getMasterGraph().addAttribute(new Attribute("declaration", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("author(s)", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("version", true, false, "0,0")); //$NON-NLS-1$ //$NON-NLS-2$
		this.getMasterGraph().addAttribute(new Attribute("project", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("title", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("date", true, false)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("code", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("note", false, true, "Designed with Coloane")); //$NON-NLS-1$ //$NON-NLS-2$

		// Definition des elements du formalisme
		definePlace();
		defineTransition();
		defineImmediateTransition();
		defineQueue();
		defineArc();
		defineInhibitorArc();
		
		// Definition des contraintes liées au formalisme
		defineConstraints();
	}
	
	/**
	 * Definition d'une place
	 */
	private void definePlace() {
		// La place:
		FormalismElement place = new Node("place"); //$NON-NLS-1$
		place.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		place.addAttribute(new Attribute("domain", true, true)); //$NON-NLS-1$
		place.addAttribute(new Attribute("marking", true, true)); //$NON-NLS-1$
		place.addAttribute(new Attribute("component", false, true)); //$NON-NLS-1$
		place.addAttribute(new Attribute("note", false, true)); //$NON-NLS-1$
		this.addElement(place);
	}
	
	/**
	 * Definition d'une transition
	 */
	private void defineTransition() {
		// La transition:
		FormalismElement transition = new Node("transition"); //$NON-NLS-1$
		transition.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		transition.addAttribute(new Attribute("guard", true, true, "true")); //$NON-NLS-1$ //$NON-NLS-2$
		transition.addAttribute(new Attribute("priority", true, true, "0")); //$NON-NLS-1$ //$NON-NLS-2$
		transition.addAttribute(new Attribute("delay", true, true)); //$NON-NLS-1$
		transition.addAttribute(new Attribute("action", false, true)); //$NON-NLS-1$
		transition.addAttribute(new Attribute("note", false, true)); //$NON-NLS-1$
		addElement(transition);
	}
	
	/**
	 * Definition d'une transition immédiate
	 */
	private void defineImmediateTransition() {
		// La transition immediate:
		FormalismElement itransition = new Node("immediate transition"); //$NON-NLS-1$
		itransition.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		itransition.addAttribute(new Attribute("guard", true, true, "true")); //$NON-NLS-1$ //$NON-NLS-2$
		itransition.addAttribute(new Attribute("priority", true, true, "0")); //$NON-NLS-1$ //$NON-NLS-2$
		itransition.addAttribute(new Attribute("weight", true, true)); //$NON-NLS-1$
		itransition.addAttribute(new Attribute("note", false, true)); //$NON-NLS-1$
		addElement(itransition);
	}
	
	/**
	 * Définition d'une queue
	 */
	private void defineQueue() {
		// La queue:
		FormalismElement queue = new Node("queue"); //$NON-NLS-1$
		queue.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		queue.addAttribute(new Attribute("domain", true, true)); //$NON-NLS-1$
		queue.addAttribute(new Attribute("marking", true, true)); //$NON-NLS-1$
		queue.addAttribute(new Attribute("note", false, true)); //$NON-NLS-1$
		addElement(queue);
	}
	
	/**
	 * Définition d'un arc simple
	 */
	private void defineArc() {
		// L'arc:
		FormalismElement arc = new Arc("arc"); //$NON-NLS-1$
		arc.addAttribute(new Attribute("valuation", true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		arc.addAttribute(new Attribute("note", false, true)); //$NON-NLS-1$
		addElement(arc);
	}
	
	/**
	 * Définition d'un arc inhibiteur
	 */
	private void defineInhibitorArc() {
		// L'arc hinibiteur
		FormalismElement iarc = new Arc("inhibitor arc"); //$NON-NLS-1$
		iarc.addAttribute(new Attribute("valuation", true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		iarc.addAttribute(new Attribute("note", false, true)); //$NON-NLS-1$
		addElement(iarc);
	}
	
	/**
	 * Définition des contraintes liés au formalisme
	 */
	private void defineConstraints() {
		return;
	}
}
