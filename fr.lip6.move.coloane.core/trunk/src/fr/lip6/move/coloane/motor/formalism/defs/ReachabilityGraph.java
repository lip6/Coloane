package fr.lip6.move.coloane.motor.formalism.defs;

import fr.lip6.move.coloane.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.motor.formalism.NodeFormalism;
import fr.lip6.move.coloane.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.ui.model.INodeGraphicInfo;

public class ReachabilityGraph extends Formalism {

	private static final String NAME = "ReachabilityGraph";
	private static final String IMG = "/resources/icons/gma.gif";
	private static final String EXTENSION = "rg";

	private static final int INITIAL_WIDTH = 16;
	private static final int INITIAL_HEIGHT = 16;
	private static final int TERMINAL_WIDTH = 24;
	private static final int TERMINAL_HEIGHT = 8;
	private static final int STATE_WIDTH = 16;
	private static final int STATE_HEIGHT = 8;

	public ReachabilityGraph() {
		super(NAME, IMG, EXTENSION);

		int i = 1;

		// Ajout de tous les attributs d'un graphe d'accessibilite (Attention :
		// different des attributs des elements.)
		addAttributeFormalism(new AttributeFormalism(i++, "title", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "author(s)", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "version", IAttributeGraphicInfo.NOR, true, false, "0.0"));
		addAttributeFormalism(new AttributeFormalism(i++, "information", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "type", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "project", IAttributeGraphicInfo.NOR, true, false));

		// Creation ajout des differents elements de base d'un graphe d'accessibilite :
		// etat initial, etat final, etat, event (arc)

		// L'etat initial:
		ElementBase elt = new NodeFormalism("initial_state", "Initial State", this, INodeGraphicInfo.FIG_DBLCIRCLE, INITIAL_WIDTH, INITIAL_HEIGHT, false); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", IAttributeGraphicInfo.L2, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "initial", IAttributeGraphicInfo.NOR, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "deadlock", false, true));
		elt.setAddrIcone16("/resources/icons/initial16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/icons/initial24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// L'etat terminal:
		elt = new NodeFormalism("terminal_state", "Terminal State", this, INodeGraphicInfo.FIG_CIRCLE, TERMINAL_WIDTH, TERMINAL_HEIGHT, true); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", IAttributeGraphicInfo.L2, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "initial", IAttributeGraphicInfo.NOR, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "deadlock", false, true));
		elt.setAddrIcone16("/resources/icons/terminal16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/icons/terminal24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// L'etat:
		elt = new NodeFormalism("state", "State", this, INodeGraphicInfo.FIG_CIRCLE, STATE_WIDTH, STATE_HEIGHT, false); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", IAttributeGraphicInfo.L2, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "initial", IAttributeGraphicInfo.NOR, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "deadlock", false, true));
		elt.setAddrIcone16("/resources/icons/place16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/icons/place24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// L'event (arc)
		elt = new ArcFormalism("event", "Event", this, IArcGraphicInfo.FIG_ARC_SIMPLE); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.NOR, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "value", false, true));
		elt.setAddrIcone16("/resources/icons/arc16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/icons/arc24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Pas de rules : toute association autorisee
	}
}
