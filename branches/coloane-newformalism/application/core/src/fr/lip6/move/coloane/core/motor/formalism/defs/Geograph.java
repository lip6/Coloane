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

public class Geograph extends Formalism {

	private static final String NAME = "Geographie";
	private static final String IMG = "/resources/icons/instance.gif";
	private static final String EXTENSION = "geo";
	private static final String XSCHEMA = "global.xsd";
	
	private static final int CONTINENT_WIDTH = 20;
	private static final int CONTINENT_HEIGHT = 20;
	private static final int PAYS_WIDTH = 20;
	private static final int PAYS_HEIGHT = 10;

	/**
	 * Constructeur du formalisme
	 */
	public Geograph() {
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

		// Le continent
		
		ElementFormalism elt = new NodeFormalism("Continent", Messages.Geo_1, this, INodeGraphicInfo.FIG_CIRCLE, CONTINENT_WIDTH, CONTINENT_HEIGHT, false); 
		
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Nom Continent", IAttributeGraphicInfo.L2, true, true)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Geographiquement", IAttributeGraphicInfo.L2, true, true)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Superficie", IAttributeGraphicInfo.L2, true, true)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Nombre Pays", IAttributeGraphicInfo.L2, true, true));  
		
		elt.setAddrIcone16("/resources/formalisms/continent.png"); 
		elt.setAddrIcone24("/resources/formalisms/continent.png"); 

		addElementBase(elt);

		// Remise a zero de l'indicateur d'affichage
		i = 1;

		// Le Pays:
		elt = new NodeFormalism("Pays", Messages.Geo_2, this, INodeGraphicInfo.FIG_RECT, PAYS_WIDTH, PAYS_HEIGHT, false); 
				
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Nom Pays", IAttributeGraphicInfo.L1, true, false)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Superficie", IAttributeGraphicInfo.L1, true, false)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Capitale", IAttributeGraphicInfo.L1, true, false)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Langue", IAttributeGraphicInfo.L1, true, false)); 
		elt.addAttributeFormalism(new AttributeFormalism(i++, "Monnaie", IAttributeGraphicInfo.L1, true, false));
		
		elt.setAddrIcone16("/resources/formalisms/pays.png");

		addElementBase(elt);

		//Remiseazerodel'indicateurd'affichage
		i=1;

		//L'arc
		elt=new ArcFormalism("arc",Messages.Geo_3,this,IArcGraphicInfo.FIG_ARC_SIMPLE);

		elt.addAttributeFormalism(new AttributeFormalism(i++,"Depuis",IAttributeGraphicInfo.NOR,true,true,"1"));

		elt.setAddrIcone16("/resources/formalisms/arc16.png");
		addElementBase(elt);


		//Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.
		//Interdit Continent - Continent
		addRule(new ConnexionRule(this.getNodeFormalism("Continent"),this.getNodeFormalism("Continent")));
	
		//Interdit Pays - Pays
		addRule(new ConnexionRule(this.getNodeFormalism("Pays"),this.getNodeFormalism("Pays")));
		
		//Règle de Cardinalité, ie:un Pays appartient à un seul pays
		
		addRule(new CardinalityMaxOutRule(this.getNodeFormalism("Pays"),1,this.getNodeFormalism("Continent")));
		
		//Un Contient peut Etre relié au maximum à 2 Pays
		
		addRule(new CardinalityMaxInRule(this.getNodeFormalism("Continent"),2,this.getNodeFormalism("Pays")));
		
		
	}
}
