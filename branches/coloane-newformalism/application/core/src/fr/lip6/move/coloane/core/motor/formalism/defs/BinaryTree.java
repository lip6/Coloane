package fr.lip6.move.coloane.core.motor.formalism.defs;

import fr.lip6.move.coloane.core.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.motor.formalism.NodeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.permissionRule.CardinalityMaxInRule;
import fr.lip6.move.coloane.core.motor.formalism.permissionRule.CardinalityMaxOutRule;
import fr.lip6.move.coloane.core.motor.formalism.permissionRule.ConnexionRule;
import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;

public class BinaryTree extends Formalism {

	private static final String NAME = "Binary-Tree";
	private static final String IMG = "/resources/icons/instance.gif";
	private static final String EXTENSION = "abr";
	private static final String XSCHEMA = "global.xsd";
	
	private static final int FIGURE_WIDTH = 15;
	private static final int FIGURE_HEIGHT = 15;
	
	/**
	 * Constructeur du formalisme
	 */
	public BinaryTree() {
		super(NAME, IMG, EXTENSION, XSCHEMA);
		int i = 1;

		addAttributeFormalism(new AttributeFormalism(i++, "declaration", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "author(s)", IAttributeGraphicInfo.NOR, true, true)); 
		addAttributeFormalism(new AttributeFormalism(i++, "version", IAttributeGraphicInfo.NOR, true, false, "0,0"));
		addAttributeFormalism(new AttributeFormalism(i++, "project", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "title", IAttributeGraphicInfo.NOR, true, true));
		addAttributeFormalism(new AttributeFormalism(i++, "date", IAttributeGraphicInfo.NOR, true, false));
		addAttributeFormalism(new AttributeFormalism(i++, "code", IAttributeGraphicInfo.NOR, true, true)); 
		addAttributeFormalism(new AttributeFormalism(i++, "note", false, true));

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La Racine :
		
		ElementFormalism elt = new NodeFormalism("Root", Messages.Binary_Root, this, INodeGraphicInfo.FIG_RECT, FIGURE_WIDTH, FIGURE_HEIGHT, false);
		
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Valeur", IAttributeGraphicInfo.L1, true, false));
		
		elt.setAddrIcone16("/resources/formalisms/triangle16.png");
		
		addElementBase(elt);
		
		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// Le Noeud :
		elt = new NodeFormalism("Node", Messages.Binary_Node, this, INodeGraphicInfo.FIG_CIRCLE, FIGURE_WIDTH, FIGURE_HEIGHT, false);
				
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Valeur", IAttributeGraphicInfo.L1, true, false));
		
		elt.setAddrIcone16("/resources/formalisms/place16.png");

		addElementBase(elt);
		
		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// La feuille :
		elt = new NodeFormalism("Leaf", Messages.Binary_Leaf, this, INodeGraphicInfo.FIG_QUEUE, FIGURE_WIDTH, FIGURE_HEIGHT, true);
				
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Valeur", IAttributeGraphicInfo.L1, true, false));		
		
		elt.setAddrIcone16("/resources/formalisms/transition16.png");

		addElementBase(elt);
		
		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// L'arc
		elt = new ArcFormalism("arc", Messages.PetriNets_22, this, IArcGraphicInfo.FIG_ARC_SIMPLE);

		elt.addAttributeFormalism(new AttributeFormalism(i++, "Depuis", IAttributeGraphicInfo.NOR, true, true, "1"));

		elt.setAddrIcone16("/resources/formalisms/arc16.png");
		addElementBase(elt);


		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.

		addRule(new ConnexionRule(this.getNodeFormalism("Root"),this.getNodeFormalism("Root")));
		
		addRule(new ConnexionRule(this.getNodeFormalism("Node"),this.getNodeFormalism("Root")));
		
		addRule(new ConnexionRule(this.getNodeFormalism("Leaf"),this.getNodeFormalism("Leaf")));
		
		addRule(new ConnexionRule(this.getNodeFormalism("Leaf"),this.getNodeFormalism("Node")));
		
		addRule(new ConnexionRule(this.getNodeFormalism("Leaf"),this.getNodeFormalism("Root")));
		
		addRule(new CardinalityMaxOutRule(this.getNodeFormalism("Root"),2,this.getNodeFormalism("Node")));
		
		addRule(new CardinalityMaxInRule(this.getNodeFormalism("Node"),1,this.getNodeFormalism("Root")));
		
		addRule(new CardinalityMaxOutRule(this.getNodeFormalism("Node"),2,this.getNodeFormalism("Node")));
		
		addRule(new CardinalityMaxInRule(this.getNodeFormalism("Node"),1,this.getNodeFormalism("Node")));
	
		addRule(new CardinalityMaxOutRule(this.getNodeFormalism("Node"),2,this.getNodeFormalism("Leaf")));
		
	}
}
