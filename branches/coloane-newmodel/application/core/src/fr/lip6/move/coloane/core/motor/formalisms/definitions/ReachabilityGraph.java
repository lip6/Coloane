package fr.lip6.move.coloane.core.motor.formalisms.definitions;

import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;

public class ReachabilityGraph extends Formalism {

	private static final String NAME = "ReachabilityGraph"; //$NON-NLS-1$
	private static final String IMG = "/resources/icons/instance.gif"; //$NON-NLS-1$
	private static final String EXTENSION = "rg"; //$NON-NLS-1$
	private static final String XSCHEMA = "reachability-graph.xsd"; //$NON-NLS-1$

	public ReachabilityGraph() {
		super(NAME, IMG, EXTENSION, XSCHEMA);

		// Ajout de tous les attributs d'un graphe d'accessibilite (Attention :
		// different des attributs des elements.)
		this.getMasterGraph().addAttribute(new Attribute("title", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("author(s)", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("version", true, false, "0.0")); //$NON-NLS-1$ //$NON-NLS-2$
		this.getMasterGraph().addAttribute(new Attribute("information", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("type", true, true)); //$NON-NLS-1$
		this.getMasterGraph().addAttribute(new Attribute("project", true, false)); //$NON-NLS-1$

		defineInit();
		defineTerminal();
		defineState();
		defineEvent();
		
		// Pas de rules : toute association autorisee
	}
	
	/**
	 * Définition d'un event
	 */
	private void defineEvent() {
		// L'event (arc):
		FormalismElement event = new Arc("event"); //$NON-NLS-1$
		event.addAttribute(new Attribute("label", true, false)); //$NON-NLS-1$
		event.addAttribute(new Attribute("value", false, true)); //$NON-NLS-1$
		addElement(event);
	}
	
	/**
	 * Définition d'un state (état)
	 */
	private void defineState() {
		// L'etat:
		FormalismElement state = new Node("state"); //$NON-NLS-1$
		state.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		state.addAttribute(new Attribute("value", true, true)); //$NON-NLS-1$
		state.addAttribute(new Attribute("initial", true, true)); //$NON-NLS-1$
		state.addAttribute(new Attribute("deadlock", false, true)); //$NON-NLS-1$
		addElement(state);
	}
	
	/**
	 * Définition d'un noeud terminal
	 */
	private void defineTerminal() {
		// L'etat terminal:
		FormalismElement terminal = new Node("terminal state"); //$NON-NLS-1$
		terminal.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		terminal.addAttribute(new Attribute("value", true, true)); //$NON-NLS-1$
		terminal.addAttribute(new Attribute("initial", true, true)); //$NON-NLS-1$
		terminal.addAttribute(new Attribute("deadlock", false, true)); //$NON-NLS-1$
		addElement(terminal);
	}
	
	/**
	 * Définition d'un noeud initial
	 */
	private void defineInit() {
		// L'etat initial:
		FormalismElement init = new Node("initial state"); //$NON-NLS-1$
		init.addAttribute(new Attribute("name", true, false)); //$NON-NLS-1$
		init.addAttribute(new Attribute("value", true, true)); //$NON-NLS-1$
		init.addAttribute(new Attribute("initial", true, true)); //$NON-NLS-1$
		init.addAttribute(new Attribute("deadlock", false, true)); //$NON-NLS-1$
		addElement(init);
	}
}
