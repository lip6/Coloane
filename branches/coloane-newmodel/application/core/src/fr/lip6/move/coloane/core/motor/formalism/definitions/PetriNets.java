package fr.lip6.move.coloane.core.motor.formalism.definitions;

import fr.lip6.move.coloane.core.motor.formalisms.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.NodeFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.Rule;
import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;

public class PetriNets extends Formalism {

	private static final String NAME = "AMI-Net"; //$NON-NLS-1$
	private static final String IMG = "/resources/icons/instance.gif"; //$NON-NLS-1$
	private static final String EXTENSION = "petri"; //$NON-NLS-1$
	private static final String XSCHEMA = "ami-net.xsd"; //$NON-NLS-1$

	private static final int PLACE_WIDTH = 16;
	private static final int PLACE_HEIGHT = 16;
	private static final int TRANSITION_WIDTH = 24;
	private static final int TRANSITION_HEIGHT = 8;
	private static final int QUEUE_WIDTH = 16;
	private static final int QUEUE_HEIGHT = 8;

	/**
	 * Constructeur du formalisme
	 */
	public PetriNets() {
		super(NAME, IMG, EXTENSION, XSCHEMA);
		int i = 1;

		addAttributeFormalism(new AttributeFormalism(i++, "declaration", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "author(s)", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "version", IAttributeGraphicInfo.NOR, true, false, "0,0")); //$NON-NLS-1$ //$NON-NLS-2$
		addAttributeFormalism(new AttributeFormalism(i++, "project", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "title", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "date", IAttributeGraphicInfo.NOR, true, false)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "code", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$

		/* Creation ajout des différents elements de base d'un Réseau de Petri :
		 * <ul>
		 *   <li>Place</li>
		 *   <li>Transition</li>
		 *   <li>Arc</li>
		 *   <li>Arc inhibiteur</li>
		 * </ul>
		 */

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La place:
		ElementFormalism elt = new NodeFormalism("place", Messages.PetriNets_13, this, INodeGraphicInfo.FIG_CIRCLE, PLACE_WIDTH, PLACE_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "domain", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "marking", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "component", false, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/place16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/place24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La transition:
		elt = new NodeFormalism("transition", Messages.PetriNets_22, this, INodeGraphicInfo.FIG_RECT, TRANSITION_WIDTH, TRANSITION_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "guard", IAttributeGraphicInfo.NOR, true, true, "true")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "priority", IAttributeGraphicInfo.NOR, true, true, "0")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "delay", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "action", false, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/transition16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transition24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La transition immediate:
		elt = new NodeFormalism("immediate transition", Messages.PetriNets_34, this, INodeGraphicInfo.FIG_RECT, TRANSITION_WIDTH, TRANSITION_HEIGHT, true); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "guard", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "priority", IAttributeGraphicInfo.NOR, true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "weight", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/transitionimmediate16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transitionimmediate24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La queue:
		elt = new NodeFormalism("queue", Messages.PetriNets_44, this, INodeGraphicInfo.FIG_QUEUE, QUEUE_WIDTH, QUEUE_HEIGHT, false); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "name", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "domain", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "marking", IAttributeGraphicInfo.NOR, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/queue16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/queue24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// L'arc
		elt = new ArcFormalism("arc", Messages.PetriNets_52, this, IArcGraphicInfo.FIG_ARC_SIMPLE); //$NON-NLS-1$

		elt.addAttributeFormalism(new AttributeFormalism(i++, "valuation", IAttributeGraphicInfo.NOR, true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/arc16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/arc24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// L'arc hinibiteur
		elt = new ArcFormalism("inhibitor arc", Messages.PetriNets_59, this, IArcGraphicInfo.FIG_ARC_INHIBITOR); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "valuation", IAttributeGraphicInfo.NOR, true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "note", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/arcinhibiteur16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/arcinhibiteur24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.
		// Interdit place - place
		addRule(new Rule(this.getNodeFormalism("place"), this.getNodeFormalism("place"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit queue - queue
		addRule(new Rule(this.getNodeFormalism("queue"), this.getNodeFormalism("queue"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit transition - transition
		addRule(new Rule(this.getNodeFormalism("transition"), this.getNodeFormalism("transition"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit transition immediate - transition immediate
		addRule(new Rule(this.getNodeFormalism("immediate transition"), this.getNodeFormalism("immediate transition"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit transition - transition immediate
		addRule(new Rule(this.getNodeFormalism("transition"), this.getNodeFormalism("immediate transition"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit transition immediate - transition
		addRule(new Rule(this.getNodeFormalism("immediate transition"), this.getNodeFormalism("transition"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit transition queue - place
		addRule(new Rule(this.getNodeFormalism("queue"), this.getNodeFormalism("place"))); //$NON-NLS-1$ //$NON-NLS-2$
		// Interdit transition place - queue
		addRule(new Rule(this.getNodeFormalism("place"), this.getNodeFormalism("queue"))); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
