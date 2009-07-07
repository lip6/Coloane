package fr.lip6.move.coloane.apicami.communications;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * Classe responsable de la traduction d'un modèle objet en CAMI
 *
 * @author Jean-Baptiste Voron
 */
public final class CamiModelTranslator {

	private static final int MAXLENGHT = 255;

	/**
	 * Constructeur
	 */
	private CamiModelTranslator() {	}

	/**
	 * Traduction d'un modèle en vecteur de commandes CAMI
	 * @param model Le modèle à convertir
	 * @return Un vecteur de chaines de caractères (commandes CAMI)
	 */
	public static List<String> translateModel(IGraph model) {
		List<String> toReturn = new ArrayList<String>();

		// Ajout du noeud du modele
		toReturn.add(new String("CN(3:net,1)")); //$NON-NLS-1$

		// Ajout des attributs
		for (IAttribute attribute : model.getAttributes()) {
			toReturn.addAll(translateAttribute(attribute));
		}

		// Ajout des noeuds
		for (INode node : model.getNodes()) {
			toReturn.addAll(translateNode(node));
		}

		// Ajout des arcs
		for (IArc arc : model.getArcs()) {
			toReturn.addAll(translateArc(arc));
		}

		return toReturn;
	}

	/**
	 * Traduction d'un arc du modèle en commandes CAMI
	 * @param arc L'arc à convertir
	 * @return Un vecteur de chaines de caractères correspondant aux commandes CAMI adéquates
	 */
	private static List<String> translateArc(IArc arc) {
		List<String> toReturn = new ArrayList<String>();

		// traduction de la partie principale
		StringBuffer buffer = new StringBuffer();
		buffer.append("CA("); //$NON-NLS-1$
		buffer.append(arc.getArcFormalism().getName().length() + ":" + arc.getArcFormalism().getName() + ","); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(arc.getId() + ","); //$NON-NLS-1$
		buffer.append(arc.getSource().getId() + "," + arc.getTarget().getId()); //$NON-NLS-1$
		buffer.append(")"); //$NON-NLS-1$
		toReturn.add(buffer.toString());

		// Traduction des points intermediaires
		for (Point pi : arc.getInflexPoints()) {
			toReturn.add(new String("PI(-1," + arc.getId() + "," + pi.x + "," + pi.y + ",-1)"));  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
		}

		// Traduction des attributs
		for (IAttribute att : arc.getAttributes()) {
			toReturn.addAll(translateAttribute(att));
		}

		return toReturn;
	}

	/**
	 * Traduction d'un attribut du modèle en commandes CAMI
	 * @param attribute L'attribut à convertir
	 * @return Un vecteur de chaines de caractères correspondant aux commandes CAMI adéquates
	 */
	private static List<String> translateAttribute(IAttribute attribute) {
		List<String> toReturn = new ArrayList<String>();
		String attributeValue = attribute.getValue();

		// Si la valeur de l'attribut est vide... on retourne
		if (attributeValue.equals("")) { //$NON-NLS-1$
			return toReturn;
		}

		// Decoupage de la chaine de charactere suivant un pattern
		String[] valueTable = attributeValue.split("(\n\r)|(\r\n)|(\n)|(\r)"); //$NON-NLS-1$

		// Si la tableau obtenu est de taille 1 et que la ligne est de taille < a 255, on a un attribut d'une ligne
		if (valueTable.length == 1 && valueTable[0].length() > 0 && valueTable[0].length() <= MAXLENGHT) {
			// Suppression des accents
			String value = StringUtils.removeStresses(valueTable[0]);

			StringBuffer buffer = new StringBuffer();
			buffer.append("CT(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			buffer.append(value.length() + ":" + value); //$NON-NLS-1$
			buffer.append(")"); //$NON-NLS-1$
			toReturn.add(buffer.toString());

			// Sinon, on a un attribut multiligne
		} else {
			int lineCounter = 1; // compteur ligne utile

			for (int i = 0; i < valueTable.length; i++) {

				// Pour chaque ligne, on teste si on doit la decouper car trop longue
				if (valueTable[i].length() < MAXLENGHT) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					buffer.append(lineCounter++ + ","); //$NON-NLS-1$
					buffer.append(1 + ","); // Archaisme de Framekit //$NON-NLS-1$
					String value = StringUtils.removeStresses(valueTable[i]);
					buffer.append(value.length() + ":" + value); //$NON-NLS-1$
					buffer.append(")"); //$NON-NLS-1$
					toReturn.add(buffer.toString());
				} else {
					int start = 0;
					int end = MAXLENGHT;

					// Traduction des n*255 premiers caracteres
					while (end < valueTable[i].length()) {
						String sub = StringUtils.removeStresses(valueTable[i].substring(start, end));
						StringBuffer buffer = new StringBuffer();
						buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						buffer.append(lineCounter++ + ","); //$NON-NLS-1$
						buffer.append(1 + ","); // archaisme de Framekit //$NON-NLS-1$
						buffer.append(sub.length() + ":" + sub); //$NON-NLS-1$
						buffer.append(")"); //$NON-NLS-1$
						toReturn.add(buffer.toString());

						start += MAXLENGHT;
						end += MAXLENGHT;
					}

					// Traduction des caracteres restants
					String sub = StringUtils.removeStresses(valueTable[i].substring(start, valueTable[i].length()));
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
					buffer.append(lineCounter++ + ","); //$NON-NLS-1$
					buffer.append(1 + ","); // archaisme de Framekit //$NON-NLS-1$
					buffer.append(sub.length() + ":" + sub); //$NON-NLS-1$
					buffer.append(")"); //$NON-NLS-1$
					toReturn.add(buffer.toString());
				}
			}

		}

		//Traduit la position de l'attribut
		if (attribute.getGraphicInfo().getLocation().x != 0 || attribute.getGraphicInfo().getLocation().y != 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("PT(" + attribute.getReference().getId() + "," + attribute.getName().length() + ":" + attribute.getName() + ",");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
			buffer.append(attribute.getGraphicInfo().getLocation().x + "," + attribute.getGraphicInfo().getLocation().y); //$NON-NLS-1$
			buffer.append(")"); //$NON-NLS-1$
			toReturn.add(buffer.toString());
		}

		return toReturn;
	}

	/**
	 * Traduction d'un noeud du modèle en commandes CAMI
	 * @param node Le noeud à convertir
	 * @return Un vecteur de chaines de caractères correspondant aux commandes CAMI adéquates
	 */
	private static List<String> translateNode(INode node) {
		List<String> toReturn = new ArrayList<String>();
		toReturn.add(new String("CN(" + node.getNodeFormalism().getName().length() + ":" + node.getNodeFormalism().getName() + "," + node.getId() + ")")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		toReturn.add(new String("PO(" + node.getId() + "," + node.getGraphicInfo().getLocation().x + "," + node.getGraphicInfo().getLocation().y + ")"));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		// Traduction des attributs
		for (IAttribute att : node.getAttributes()) { toReturn.addAll(translateAttribute(att));	}

		return toReturn;
	}

}
