package fr.lip6.move.coloane.motor.formalism.defs;

import fr.lip6.move.coloane.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.motor.formalism.NodeFormalism;
import fr.lip6.move.coloane.motor.formalism.Rule;
import fr.lip6.move.coloane.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.ui.model.INodeGraphicInfo;

public class PrefixNets extends Formalism {

	private static final String NAME = "Branching-Process";
	private static final String IMG = "/resources/icons/instance.gif";
	private static final String EXTENSION = "prefix";

	/** Informations graphiques pour la place */
	private static final int CONDITION_WIDTH = 16;
	private static final int CONDITION_HEIGHT = 16;
	private static final int EVENT_WIDTH = 24;
	private static final int EVENT_HEIGHT = 8;
	private static final int CUTOFF_WIDTH = 16;
	private static final int CUTOFF_HEIGHT = 8;

	public PrefixNets() {
		super(NAME, IMG, EXTENSION);

		int i = 1;

		// Creation du formalisme Prefix nets.

		// Ajout de tous les attributs d'un prefix net (Attention ! Different des attributs des elements.)
		addAttributeFormalism(new AttributeFormalism(1, "tool", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(2, "origin", IAttributeGraphicInfo.NOR, true, true));

		/* Creation ajout des differents elements de base d'un Prefix Net :
		 * <ul>
		 *   <li>Condition</li>
		 *   <li>Event</li>
		 *   <li>Cutoff</li>
		 *   <li>Arc</li>
		 * </ul>
		 */

		// Condition
		ElementBase elt = new NodeFormalism("condition", "Condition", this, INodeGraphicInfo.FIG_CIRCLE, CONDITION_WIDTH, CONDITION_HEIGHT, false); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.L2, true, true));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "marking", IAttributeGraphicInfo.NOR, true, true));
		elt.setAddrIcone16("/resources/formalisms/place16.gif"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/place24.gif"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// Event:
		elt = new NodeFormalism("event", "Event", this, INodeGraphicInfo.FIG_RECT, EVENT_WIDTH, EVENT_HEIGHT, false); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.NOR, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.NOR, true, true, "true"));
		elt.setAddrIcone16("/resources/formalisms/transition16.gif"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transition24.gif"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// Cutoff:
		elt = new NodeFormalism("cutoff", "Cutoff", this, INodeGraphicInfo.FIG_RECT, CUTOFF_WIDTH, CUTOFF_HEIGHT, true); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.L2, true, true, "true"));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "image", IAttributeGraphicInfo.NOR, true, true, "1"));
		elt.addAttributeFormalism(new AttributeFormalism(i++, "num_image", false, true, "1"));
		elt.setAddrIcone16("/resources/formalisms/transitionimmediate16.gif"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transitionimmediate24.gif"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// Arc
		elt = new ArcFormalism("arc", "Arc", this, IArcGraphicInfo.FIG_ARC_SIMPLE); //$NON-NLS-1$ //$NON-NLS-2$
		elt.setAddrIcone16("/resources/formalisms/arc16.gif"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/arc24.gif"); //$NON-NLS-1$
		addElementBase(elt);



		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.

		// Interdit condition - condition
		addRule(new Rule(this.getNodeFormalism("condition"), this.getNodeFormalism("condition")));
		// Interdit event - event
		addRule(new Rule(this.getNodeFormalism("event"), this.getNodeFormalism("event")));
		// Interdit cutoff - event
		addRule(new Rule(this.getNodeFormalism("cutoff"), this.getNodeFormalism("event")));
		// Interdit event - cutoff
		addRule(new Rule(this.getNodeFormalism("event"), this.getNodeFormalism("cutoff")));
		// Interdit cutoff - cutoff
		addRule(new Rule(this.getNodeFormalism("cutoff"), this.getNodeFormalism("cutoff")));
	}
}
