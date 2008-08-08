package fr.lip6.move.coloane.apiws.utils;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.wrapper.ws.WrapperStub.Attribute;
import fr.lip6.move.wrapper.ws.WrapperStub.AttributeValue;
import fr.lip6.move.wrapper.ws.WrapperStub.BArc;
import fr.lip6.move.wrapper.ws.WrapperStub.BNode;
import fr.lip6.move.wrapper.ws.WrapperStub.Model;
import fr.lip6.move.wrapper.ws.WrapperStub.Position;

import org.eclipse.draw2d.geometry.Point;

/**
 * Cette classe permet de traduire un model en objet pour le wrapper
 */
public final class WrapperModelTranslator {

	/**
	 * Constricteur vide
	 */
	private WrapperModelTranslator() { }

	/**
	 * Traduit un model
	 * @param model le model recu de la part du core
	 * @return un model compréhensible par le wrapper
	 */
	public static Model translateModel(IGraph model) {
		Model theModel = new Model();

		theModel.addNodes(createNetNode(model));

		for (INode node : model.getNodes()) {
			theModel.addNodes(translateNode(node));
		}

		for (IArc arc : model.getArcs()) {
			theModel.addArcs(translateArc(arc));
		}

		return theModel;
	}

	/**
	 * Traduit un noeud
	 * @param node le noeud à traduire
	 * @return un noeud compréhensible par le wrapper
	 */
	private static BNode translateNode(INode node) {
		BNode theNode = new BNode();

		theNode.setId(node.getId());
		theNode.setType(node.getNodeFormalism().getName());

		Position pos = new Position();
		pos.setXx(node.getGraphicInfo().getLocation().x);
		pos.setYy(node.getGraphicInfo().getLocation().y);

		for (IAttribute attribute : node.getAttributes()) {
			if (!attribute.getValue().equals("")) {
				theNode.addAtts(translateAttribute(attribute));
			}
		}

		theNode.setPosition(pos);
		return theNode;
	}

	/**
	 * Traduit un arc
	 * @param arc l'arc à traduire
	 * @return un arc compréhensible par le wrapper
	 */
	private static BArc translateArc(IArc arc) {
		BArc theArc = new BArc();

		theArc.setId(arc.getId());
		theArc.setType(arc.getArcFormalism().getName());
		theArc.setSource(arc.getSource().getId());
		theArc.setDestination(arc.getTarget().getId());

		Position[] pts = new Position[arc.getInflexPoints().size()];
		int i = 0;
		for (Point pi : arc.getInflexPoints()) {
			Position pos = new Position();
			pos.setXx(pi.x);
			pos.setYy(pi.y);

			pts[i++] = pos;
		}
		theArc.setPoints(pts);

		for (IAttribute attribute : arc.getAttributes()) {
			if (!attribute.getValue().equals("")) {
				theArc.addAtts(translateAttribute(attribute));
			}
		}

		return theArc;
	}

	/**
	 * Traduit un attribut
	 * @param attribute l'attribut à traduire
	 * @return un attribut compréhensible par le wrapper
	 */
	private static Attribute translateAttribute(IAttribute attribute) {
		Attribute theAttribute = new Attribute();
		theAttribute.setAttName(attribute.getName());

		AttributeValue theAttributeValue = new AttributeValue();
		theAttributeValue.setValue(attribute.getValue());

		AttributeValue[] theAttributeValueArray = new AttributeValue[1];
		theAttributeValueArray[0] = theAttributeValue;

		Position pos = new Position();
		pos.setXx(attribute.getGraphicInfo().getLocation().x);
		pos.setYy(attribute.getGraphicInfo().getLocation().y);
		theAttribute.setPos(pos);

		theAttribute.setValues(theAttributeValueArray);

		return theAttribute;
	}

	/**
	 * Créer le premier noeud du model
	 * @param model le model
	 * @return le premier noeud du model
	 */
	private static BNode createNetNode(IGraph model) {
		BNode theNetNode = new BNode();

		theNetNode.setId(1);
		theNetNode.setType("net");

		for (IAttribute attribute : model.getAttributes()) {
			if (!attribute.getValue().equals("")) {
				theNetNode.addAtts(translateAttribute(attribute));
			}
		}

		return theNetNode;

	}

}
