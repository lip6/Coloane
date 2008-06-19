package fr.lip6.move.coloane.core.motor.formalism.definitions;

import fr.lip6.move.coloane.core.motor.formalisms.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.NodeFormalism;
import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;

public class ReachabilityGraph extends Formalism {

	private static final String NAME = "ReachabilityGraph"; //$NON-NLS-1$
	private static final String IMG = "/resources/icons/instance.gif"; //$NON-NLS-1$
	private static final String EXTENSION = "rg"; //$NON-NLS-1$
	private static final String XSCHEMA = "reachability-graph.xsd"; //$NON-NLS-1$


	private static final int INITIAL_WIDTH = 16;
	private static final int INITIAL_HEIGHT = 16;
	private static final int TERMINAL_WIDTH = 16;
	private static final int TERMINAL_HEIGHT = 16;
	private static final int STATE_WIDTH = 16;
	private static final int STATE_HEIGHT = 16;

	public ReachabilityGraph() {
		super(NAME, IMG, EXTENSION, XSCHEMA);

		int i = 1;

		// Ajout de tous les attributs d'un graphe d'accessibilite (Attention :
		// different des attributs des elements.)
		addAttributeFormalism(new AttributeFormalism(i++, "title", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "author(s)", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "version", IAttributeGraphicInfo.NOR, true, false, "0.0")); //$NON-NLS-1$ //$NON-NLS-2$
		addAttributeFormalism(new AttributeFormalism(i++, "information", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "type", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "project", IAttributeGraphicInfo.NOR, true, false)); //$NON-NLS-1$

		// Creation ajout des differents elements de base d'un graphe d'accessibilite :
		// etat initial, etat final, etat, event (arc)
		i = 1;

		// L'etat initial:
		ElementFormalism elt = new NodeFormalism("initial_state", Messages.ReachabilityGraph_0, this, INodeGraphicInfo.FIG_DBLCIRCLE, INITIAL_WIDTH, INITIAL_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "initial", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "deadlock", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/initial16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/initial24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// L'etat terminal:
		elt = new NodeFormalism("terminal_state", Messages.ReachabilityGraph_1, this, INodeGraphicInfo.FIG_CIRCLE, TERMINAL_WIDTH, TERMINAL_HEIGHT, true); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "initial", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "deadlock", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/terminal16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/terminal24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// L'etat:
		elt = new NodeFormalism("state", Messages.ReachabilityGraph_2, this, INodeGraphicInfo.FIG_CIRCLE, STATE_WIDTH, STATE_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "initial", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "deadlock", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/place16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/place24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// L'event (arc)
		elt = new ArcFormalism("event", Messages.ReachabilityGraph_3, this, IArcGraphicInfo.FIG_ARC_SIMPLE); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.NOR, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/arc16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/arc24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Pas de rules : toute association autorisee
	}
}
