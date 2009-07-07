package fr.lip6.move.coloane.apiws.utils;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Attribute;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.AttributeValue;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.BArc;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.BNode;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Model;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Position;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * Cette classe permet de traduire un model en objet pour le wrapper
 *
 * @author Monir CHAOUKI
 */
public final class WrapperModelTranslator {

	private static final int MAXLENGHT = 255;

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

		// La liste des valeurs de l'attributs
		List<AttributeValue> listeOfTheValue = new ArrayList<AttributeValue>();

		// Decoupage de la chaine de charactere suivant un pattern
		String[] valueTable = attribute.getValue().split("(\n\r)|(\r\n)|(\n)|(\r)");

		// Si la tableau obtenu est de taille 1 et que la ligne est de taille < a 255, on a un attribut d'une ligne
		if (valueTable.length == 1 && valueTable[0].length() > 0 && valueTable[0].length() <= MAXLENGHT) {
			AttributeValue theAttributeValue = new AttributeValue();
			theAttributeValue.setValue(valueTable[0]);

			listeOfTheValue.add(theAttributeValue);

			// Sinon, on a un attribut multiligne
		} else {
			for (int i = 0; i < valueTable.length; i++) {

				// Pour chaque ligne, on teste si on doit la decouper car trop longue
				if (valueTable[i].length() < MAXLENGHT) {
					AttributeValue theAttributeValue = new AttributeValue();
					theAttributeValue.setValue(valueTable[i]);

					listeOfTheValue.add(theAttributeValue);
				} else {
					int start = 0;
					int end = MAXLENGHT;

					// Traduction des n*255 premiers caracteres
					while (end < valueTable[i].length()) {
						String sub = valueTable[i].substring(start, end);
						AttributeValue theAttributeValue = new AttributeValue();
						theAttributeValue.setValue(sub);

						listeOfTheValue.add(theAttributeValue);

						start += MAXLENGHT;
						end += MAXLENGHT;
					}

					// Traduction des caracteres restants
					String sub = valueTable[i].substring(start, valueTable[i].length());
					AttributeValue theAttributeValue = new AttributeValue();
					theAttributeValue.setValue(sub);

					listeOfTheValue.add(theAttributeValue);

				}
			}
		}

		// Initialise le tableau de valeur pour l'attribut
		AttributeValue[] theAttributeValueArray = new AttributeValue[listeOfTheValue.size()];
		int cpt = 0;
		for (AttributeValue val : listeOfTheValue) {
			theAttributeValueArray[cpt++] = val;
		}
		theAttribute.setValues(theAttributeValueArray);

		// Initialise la position de l'attribut
		Position pos = new Position();
		pos.setXx(attribute.getGraphicInfo().getLocation().x);
		pos.setYy(attribute.getGraphicInfo().getLocation().y);
		theAttribute.setPos(pos);

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
