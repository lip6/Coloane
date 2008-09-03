package fr.lip6.move.coloane.extension.importExportCAMI.exportToCAMI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbsoluteBendpoint;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ExportToImpl implements IExportTo {
	
	/** Longueur maximum d'une commande CAMI **/
	private static int MAXLENGHT = 255;

	/**
	 * Constructeur par défaut
	 */
	public ExportToImpl() {}

	/**
	 * Exporte un modèle en fichier CAMI
	 * @param model Le modèle à exporter en CAMI
	 * @param filePath nom du fichier de destination
	 * @throws ColoaneException
	 */
	public void export(IGraph model, String filePath, IProgressMonitor monitor) throws ColoaneException {
		FileOutputStream writer;
		
		// Controle sur le nom du fichier
		if (filePath.equalsIgnoreCase("") || filePath == null) { //$NON-NLS-1$
			throw new ColoaneException("The filename is not correct. Please provide a valid filename");
		}

		int totalWork = model.getNodes().size() + model.getArcs().size();
		monitor.beginTask("Export to CAMI", totalWork);

		try {
			// Creation du fichier
			writer = new FileOutputStream(new File(filePath)); //$NON-NLS-1$
			BufferedWriter writerBuffer = new BufferedWriter(new OutputStreamWriter(writer));
			
			// Traduction et écriture
			Vector<String> cami = translateModel(model, monitor);
			for (String line : cami) {
				writerBuffer.write(line);
				writerBuffer.newLine();
			}
			
			// Fin del'écriture : nettoyage et fermeture
			writerBuffer.flush();
			writer.flush();
			writerBuffer.close();
			writer.close();
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Echec lors de la création du fichier : Nom de fichier invalide");
			throw new ColoaneException("Invalid filename !");
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Erreur lors de l'écriture dans le fichier");
			throw new ColoaneException("Write error :" + ioe.getMessage());
		}
		monitor.done();
	}
	
	/**
	 * Traduction d'un modèle en vecteur de commandes CAMI
	 * @param model Le modèle à convertir
	 * @param monitor 
	 * @return Un vecteur de chaines de caractères (commandes CAMI)
	 */
	private final Vector<String> translateModel(IGraph model, IProgressMonitor monitor) {
		Vector<String> toReturn = new Vector<String>();

		// Ajout du noeud du modele
		toReturn.add(new String("CN(3:net,1)")); //$NON-NLS-1$

		// Ajout des attributs
		for (IAttribute attribute : model.getAttributes()) {
			toReturn.addAll(this.translateAttribute(attribute));
		}

		// Ajout des noeuds
		monitor.subTask("Export nodes");
		for (INode node : model.getNodes()) {
			toReturn.addAll(this.translateNode(node));
			monitor.worked(1);
		}

		// Ajout des arcs
		monitor.subTask("Export arcs");
		for (IArc arc : model.getArcs()) {
			toReturn.addAll(this.translateArc(arc));
			monitor.worked(1);
		}

		return toReturn;
	}

	/**
	 * Traduction d'un arc du modèle en commandes CAMI
	 * @param arc L'arc à convertir
	 * @return Un vecteur de chaines de caractères correspondant aux commandes CAMI adéquates
	 */
	private final Vector<String> translateArc(IArc arc) {
		Vector<String> toReturn = new Vector<String>();

		// traduction de la partie principale
		StringBuffer buffer = new StringBuffer();
		buffer.append("CA("); //$NON-NLS-1$
		buffer.append(arc.getArcFormalism().getName().length() + ":" + arc.getArcFormalism().getName() + ","); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(arc.getId() + ","); //$NON-NLS-1$
		buffer.append(arc.getSource().getId() + "," + arc.getTarget().getId()); //$NON-NLS-1$
		buffer.append(")"); //$NON-NLS-1$
		toReturn.add(buffer.toString());

		// Traduction des points intermediaires
		for (AbsoluteBendpoint pi : arc.getInflexPoints()) {
			toReturn.add(new String("PI(-1," + arc.getId() + "," + pi.x + "," + pi.y + ",-1)"));  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
		}

		// Traduction des attributs
		for (IAttribute att : arc.getAttributes()) {
			toReturn.addAll(this.translateAttribute(att));
		}

		return toReturn;
	}

	/**
	 * Traduction d'un attribut du modèle en commandes CAMI
	 * @param arc L'attribut à convertir
	 * @return Un vecteur de chaines de caractères correspondant aux commandes CAMI adéquates
	 */
	private final Vector<String> translateAttribute(IAttribute attribute) {
		Vector<String> toReturn = new Vector<String>();
		String attributeValue = attribute.getValue();

		// Si la valeur de l'attribut est vide... on retourne
		if (attributeValue.equals("")) { //$NON-NLS-1$
			return toReturn;
		}

		// Decoupage de la chaine de charactere suivant un pattern
		String[] valueTable = attributeValue.split("(\n\r)|(\r\n)|(\n)|(\r)"); //$NON-NLS-1$

		// Si la tableau obtenu est de taille 1 et que la ligne est de taille < a 255, on a un attribut d'une ligne
		if (valueTable.length == 1 && valueTable[0].length() > 0 && valueTable[0].length() <= MAXLENGHT) {

				StringBuffer buffer = new StringBuffer();
				buffer.append("CT(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				buffer.append(valueTable[0].length() + ":" + valueTable[0]); //$NON-NLS-1$
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
					buffer.append(valueTable[i].length() + ":" + valueTable[i]); //$NON-NLS-1$
					buffer.append(")"); //$NON-NLS-1$
					toReturn.addElement(buffer.toString());
				} else {
					int start = 0;
					int end = MAXLENGHT;

					// Traduction des n*255 premiers caracteres
					while (end < valueTable[i].length()) {
						String sub = valueTable[i].substring(start, end);
						StringBuffer buffer = new StringBuffer();
						buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						buffer.append(lineCounter++ + ","); //$NON-NLS-1$
						buffer.append(1 + ","); // archaisme de Framekit //$NON-NLS-1$
						buffer.append(sub.length() + ":" + sub); //$NON-NLS-1$
						buffer.append(")"); //$NON-NLS-1$
						toReturn.addElement(buffer.toString());

						start += MAXLENGHT;
						end += MAXLENGHT;
					}

					// Traduction des caracteres restants
					String sub = valueTable[i].substring(start, valueTable[i].length());
					StringBuffer buffer = new StringBuffer();
					buffer.append("CM(" + attribute.getName().length() + ":" + attribute.getName() + "," + attribute.getReference().getId() + ",");   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
					buffer.append(lineCounter++ + ","); //$NON-NLS-1$
					buffer.append(1 + ","); // archaisme de Framekit //$NON-NLS-1$
					buffer.append(sub.length() + ":" + sub); //$NON-NLS-1$
					buffer.append(")"); //$NON-NLS-1$
					toReturn.addElement(buffer.toString());
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
	 * @param arc Le noeud à convertir
	 * @return Un vecteur de chaines de caractères correspondant aux commandes CAMI adéquates
	 */
	private final Vector<String> translateNode(INode node) {
		Vector<String> toReturn = new Vector<String>();
		toReturn.add(new String("CN(" + node.getNodeFormalism().getName().length() + ":" + node.getNodeFormalism().getName() + "," + node.getId() + ")")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		toReturn.add(new String("PO(" + node.getId() + "," + node.getGraphicInfo().getLocation().x + "," + node.getGraphicInfo().getLocation().y + ")"));  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		// Traduction des attributs
		for (IAttribute att : node.getAttributes()) { toReturn.addAll(this.translateAttribute(att));	}

		return toReturn;
	}

}
