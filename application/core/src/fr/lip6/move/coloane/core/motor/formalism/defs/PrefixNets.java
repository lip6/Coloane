package fr.lip6.move.coloane.core.motor.formalism.defs;

import fr.lip6.move.coloane.core.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.motor.formalism.NodeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.Rule;
import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;

public class PrefixNets extends Formalism {

	private static final String NAME = "Branching-Process"; //$NON-NLS-1$
	private static final String IMG = "/resources/icons/cube-red.png"; //$NON-NLS-1$
	private static final String EXTENSION = "prefix"; //$NON-NLS-1$
	private static final String XSCHEMA = "branching-process.xsd"; //$NON-NLS-1$

	/** Informations graphiques pour la place */
	private static final int CONDITION_WIDTH = 15;
	private static final int CONDITION_HEIGHT = 15;
	private static final int EVENT_WIDTH = 23;
	private static final int EVENT_HEIGHT = 7;
	private static final int CUTOFF_WIDTH = 15;
	private static final int CUTOFF_HEIGHT = 7;

	public PrefixNets() {
		super(NAME, IMG, EXTENSION, XSCHEMA);

		int i = 1;

		// Creation du formalisme Prefix nets.

		// Ajout de tous les attributs d'un prefix net (Attention ! Different des attributs des elements.)
		addAttributeFormalism(new AttributeFormalism(1, "tool", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(2, "origin", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$

		/* Creation ajout des differents elements de base d'un Prefix Net :
		 * <ul>
		 *   <li>Condition</li>
		 *   <li>Event</li>
		 *   <li>Cutoff</li>
		 *   <li>Arc</li>
		 * </ul>
		 */

		// Condition
		ElementFormalism elt = new NodeFormalism("condition", Messages.PrefixNets_6, this, INodeGraphicInfo.FIG_CIRCLE, CONDITION_WIDTH, CONDITION_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "marking", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/place16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/place24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// Event:
		elt = new NodeFormalism("event", Messages.PrefixNets_13, this, INodeGraphicInfo.FIG_RECT, EVENT_WIDTH, EVENT_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.NOR, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.NOR, true, true, "true")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.setAddrIcone16("/resources/formalisms/transition16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transition24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// Cutoff:
		elt = new NodeFormalism("cutoff", Messages.PrefixNets_20, this, INodeGraphicInfo.FIG_RECT, CUTOFF_WIDTH, CUTOFF_HEIGHT, true); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "label", IAttributeGraphicInfo.L2, true, true, "true")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "image", IAttributeGraphicInfo.NOR, true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "num_image", false, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.setAddrIcone16("/resources/formalisms/transitionimmediate16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transitionimmediate24.png"); //$NON-NLS-1$

		addElementBase(elt);
		i = 1;

		// Arc
		elt = new ArcFormalism("arc", Messages.PrefixNets_31, this, IArcGraphicInfo.FIG_ARC_SIMPLE); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/arc16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/arc24.png"); //$NON-NLS-1$
		addElementBase(elt);



		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.

		// Interdit condition - condition
		addRule(new Rule(this.getNodeFormalism("condition"), this.getNodeFormalism("condition"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit event - event
		addRule(new Rule(this.getNodeFormalism("event"), this.getNodeFormalism("event"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit cutoff - event
		addRule(new Rule(this.getNodeFormalism("cutoff"), this.getNodeFormalism("event"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit event - cutoff
		addRule(new Rule(this.getNodeFormalism("event"), this.getNodeFormalism("cutoff"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit cutoff - cutoff
		addRule(new Rule(this.getNodeFormalism("cutoff"), this.getNodeFormalism("cutoff"))); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
