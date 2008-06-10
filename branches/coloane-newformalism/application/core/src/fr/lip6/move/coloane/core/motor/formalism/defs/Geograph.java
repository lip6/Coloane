package fr.lip6.move.coloane.core.motor.formalism.defs;

import fr.lip6.move.coloane.core.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.ConnexionRule;
import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.motor.formalism.IRule;
import fr.lip6.move.coloane.core.motor.formalism.NodeFormalism;

import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;

public class Geograph extends Formalism {

	private static final String NAME = "Geographie"; //$NON-NLS-1$
	private static final String IMG = "/resources/icons/instance.gif"; //$NON-NLS-1$
	private static final String EXTENSION = "geo"; //$NON-NLS-1$
	private static final String XSCHEMA = "global.xsd"; //$NON-NLS-1$
	
	private static final int PLACE_WIDTH = 20;
	private static final int PLACE_HEIGHT = 20;
	private static final int TRANSITION_WIDTH = 20;
	private static final int TRANSITION_HEIGHT = 10;

	/**
	 * Constructeur du formalisme
	 */
	public Geograph() {
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

		/* Creation ajout des differents elements de base d'un Reseau de Petri :
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
		
		ElementFormalism elt = new NodeFormalism("Continent", Messages.PetriNets_13, this, INodeGraphicInfo.FIG_CIRCLE, PLACE_WIDTH, PLACE_HEIGHT, false); //$NON-NLS-1$
		
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Nom Continent", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Geographiquement", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Superficie", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Nombre Pays", IAttributeGraphicInfo.L2, true, true)); //$NON-NLS-1$		//elt.addAttributeFormalism(new AttributeFormalism(i++, "Mers", false, true)); //$NON-NLS-1$
		
		elt.setAddrIcone16("/resources/formalisms/place16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/place24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La transition:
		elt = new NodeFormalism("Pays", Messages.PetriNets_22, this, INodeGraphicInfo.FIG_RECT, TRANSITION_WIDTH, TRANSITION_HEIGHT, false); //$NON-NLS-1$
				
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Nom Pays", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Superficie", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Capitale", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Langue", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Monnaie", IAttributeGraphicInfo.L1, true, false)); //$NON-NLS-1$
		
		elt.setAddrIcone16("/resources/formalisms/transition16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/transition24.png"); //$NON-NLS-1$

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// L'arc
		elt = new ArcFormalism("arc", Messages.PetriNets_52, this, IArcGraphicInfo.FIG_ARC_SIMPLE); //$NON-NLS-1$

		elt.addAttributeFormalism(new AttributeFormalism(i++, "Depuis", IAttributeGraphicInfo.NOR, true, true, "1")); //$NON-NLS-1$ //$NON-NLS-2$
		//elt.addAttributeFormalism(new AttributeFormalism(i++, "", false, true)); //$NON-NLS-1$
		elt.setAddrIcone16("/resources/formalisms/arc16.png"); //$NON-NLS-1$
		elt.setAddrIcone24("/resources/formalisms/arc24.png"); //$NON-NLS-1$

		addElementBase(elt);


		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.
		// Interdit place - place
		addRule(new ConnexionRule(this.getNodeFormalism("Continent"), this.getNodeFormalism("Continent"))); //$NON-NLS-1$ //$NON-NLS-2$
	
		// Interdit queue - queue
		addRule(new ConnexionRule(this.getNodeFormalism("Pays"), this.getNodeFormalism("Pays"))); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		// essai avec nouvelle règle 
		
		addRule(new ConnexionRule(this.getNodeFormalism("Continent"),this.getNodeFormalism("Pays"))); //$NON-NLS-1$ //$NON-NLS-2$
		
	}
}
