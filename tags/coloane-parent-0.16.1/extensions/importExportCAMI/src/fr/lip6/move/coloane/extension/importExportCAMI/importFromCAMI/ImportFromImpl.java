package fr.lip6.move.coloane.extension.importExportCAMI.importFromCAMI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ImportFromImpl implements IImportFrom {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** Table de hash pour pouvoir associé les identifiant CAMi aux dientifiants du modèle Coloane **/
	private Map<Integer, Integer> ids;


	/**
	 * Importe un modele CAMI
	 * @param filePath nom de fchier a importer
	 * @return le model adapte correspondant
	 * @throws ColoaneException si le fichier n'est pas valide
	 */
	public IGraph importFrom(String filePath, String formalism, IProgressMonitor monitor) throws ColoaneException {
		this.ids = new HashMap<Integer, Integer>();
		IGraph model = null;

		LOGGER.finer("Creation du fichier..."); ////$NON-NLS-1$
		File toImport = new File(filePath);

		try {
			// Lecture du fichier et stockage des lignes CAMI dans un buffer de commandes
			BufferedReader buffer = new BufferedReader(new FileReader(toImport));

			try {
				// Construction du modele
				model = this.loadModel(buffer, formalism, monitor);
				LOGGER.fine("Le modele importe est identifie comme instance du formalisme :"+formalism);
			} catch (SyntaxErrorException se) {
				throw new ColoaneException("Error while parsing the model : " + se.getMessage());
			} catch (ModelException me) {
				throw new ColoaneException("Error while building the model : " + me.getMessage());
			}
			buffer.close();
		} catch (FileNotFoundException fe) {
			LOGGER.warning("Le fichier " + filePath + " est introuvable...");
			throw new ColoaneException("Filename " + filePath + "is invalid or does not exist.");
		} catch (IOException ioe) {
			LOGGER.warning("Erreur lors de la lecture du fichier");
			throw new ColoaneException("An error has occured during the file reading.");
		}
		return model;
	}
	

	/**
	 * Construit un noeud a partir des donnees CAMI
	 * @param model Le modele auquel rattacher le noeud
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction du noeud
	 */
	private IGraph loadNode(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String type = parser.parseString(","); //$NON-NLS-1$
		Integer id = Integer.valueOf(parser.parseInt(")")); //$NON-NLS-1$

		// Si le noeud en cours n'est pas le noeud principal du modele
		if (id.intValue() != 1) {
			INode node = model.createNode(type);
			this.ids.put(id, node.getId());
		}

		LOGGER.finer("Creation du noeud " + this.ids.get(id));
		return model;
	}

	/**
	 * Construit un arc a partir des donnees CAMI
	 * @param model Le modele auquel rattacher l'arc
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction du noeud
	 * @throws SyntaxErrorException
	 */
	private IGraph loadArc(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String type = parser.parseString(",");
		Integer id = Integer.valueOf(parser.parseInt(","));
		Integer source = Integer.valueOf(parser.parseInt(","));
		Integer target = Integer.valueOf(parser.parseInt(")"));

		// Recherche de la source et de la cible
		INode nodeBegin = model.getNode(this.ids.get(source));
		INode nodeEnd = model.getNode(this.ids.get(target));

		if (nodeBegin == null || nodeEnd == null) {
			LOGGER.warning("Impossible de connecter l'arc : Source / Cible manquante");
			throw new SyntaxErrorException("Cannot connect the source node with the target node. One of these is missing");
		}

		// Creation de l'arc
		IArc arc = model.createArc(type, nodeBegin, nodeEnd);
		this.ids.put(id, arc.getId());
		LOGGER.finer("Creation de l'arc " + this.ids.get(id) + " (" + nodeBegin.getId() + " -> " + nodeEnd + ")");

		return model;
	}

	/**
	 * Construit un attribut a partir des donnees CAMI
	 * @param model Le modele auquel rattacher l'attribut
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction de l'attribut
	 * @throws SyntaxErrorException
	 */
	private IGraph loadAttribute(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String name = parser.parseString(",");
		Integer ref = Integer.parseInt(parser.parseInt(","));
		String value = parser.parseString(")");

		// L'attribut peut directement etre attache au modele
		if (ref == 1) {
			IAttribute attribute = model.getAttribute(name);
			if (attribute != null) {
				attribute.setValue(value);
				LOGGER.finest("Attribut " + name + " = " + value + " pour le graphe " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// Est-ce que l'attribut s'attacher a un arc ?
		IArc arc = model.getArc(this.ids.get(ref));
		if (arc != null) {
			IAttribute attribute = arc.getAttribute(name);
			if (attribute != null) {
				attribute.setValue(value);
				LOGGER.finest("Attribut " + name + " = " + value + " pour l'arc " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// Est-ce que l'attribut s'attache a un noeud ?
		INode node = model.getNode(this.ids.get(ref));
		if (node != null) {
			IAttribute attribute = node.getAttribute(name);
			if (attribute != null) {
				attribute.setValue(value);
				LOGGER.finest("Attribut " + name + " = " + value + " pour le noeud " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// Sinon on retourne une erreur
		LOGGER.warning("Impossible de trouver l objet identifie par " + ref);
		throw new ModelException("Cannot find the model element " + ref);
	}

	/**
	 * Construit un attribut <b>multiligne</b> a partir des donnees CAMI
	 * @param model Le modele auquel rattacher l'attribut
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction de l'attribut
	 * @throws SyntaxErrorException
	 */
	private IGraph loadMAttribute(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String name = parser.parseString(",");
		Integer ref = Integer.parseInt(parser.parseInt(","));
		Integer line = Integer.parseInt(parser.parseInt(","));
		parser.parseInt(","); // deprecated
		String value = parser.parseString(")");

		// Si le referent a comme identifiant 1, alors il faut attacher l'attribut au modele
		if (ref == 1) {
			IAttribute attribute = model.getAttribute(name);
			if (attribute != null) {
				if (line == 1) { attribute.setValue(value); } else { attribute.setValue(attribute.getValue() + "\n" + value); }
				LOGGER.finest("Attribut " + name + " = " + attribute.getValue() + " pour l'arc " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// On cherche a savoir si l'attribut s'attache a un arc ?
		IArc arc = model.getArc(this.ids.get(ref));
		if (arc != null) {
			IAttribute attribute = arc.getAttribute(name);
			if (attribute != null) {
				if (line == 1) { attribute.setValue(value); } else { attribute.setValue(attribute.getValue() + "\n" + value); }
				LOGGER.finest("Attribut " + name + " = " + attribute.getValue() + " pour l'arc " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// On cherche a savoir si l'attribut s'attache a un noeud ?
		INode node = model.getNode(this.ids.get(ref));
		if (node != null) {
			IAttribute attribute = node.getAttribute(name);
			if (attribute != null) {
				if (line == 1) { attribute.setValue(value); } else { attribute.setValue(attribute.getValue() + "\n" + value); }
				LOGGER.finest("Attribut " + name + " = " + attribute.getValue() + " pour l'arc " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}			
			return model;
		}

		// Sinon on retourne une erreur
		LOGGER.warning("Impossible de trouver l objet identifie par " + ref);
		throw new ModelException("Cannot find the model element " + ref);
	}

	/**
	 * Positionne un objet a partir des donnees CAMI
	 * @param model Le modele auquel s'applique le positionnement
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres le repositionnement de l'objet
	 * @throws SyntaxErrorException
	 */
	private IGraph loadPosition(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String ref = parser.parseInt(",");
		String x = "";
		String y = "";

		// !! Attention bidouille pour prendre en compte le PO de 3e type
		if (Integer.parseInt(ref) == -1) {
			ref = parser.parseInt(",");
			x = parser.parseInt(",");
			y = parser.parseInt(",");
		} else {
			x = parser.parseInt(",");
			y = parser.parseInt(")");
		}

		if (Integer.parseInt(ref) == 1) {
			LOGGER.warning("Pas de prise en compte des coordonnées du modèle");
			return model;
		}

		INode node = model.getNode(this.ids.get(Integer.parseInt(ref)));
		if (node != null) {
			node.getGraphicInfo().setLocation(new Point(Integer.parseInt(x), Integer.parseInt(y)));
			return model;
		}

		throw new ModelException("Cannot find the object " + ref + "to be moved to");
	}

	/**
	 * Construit un point d'inflexion a partir des donnees CAMI
	 * @param model Le modele auquel rattacher le point d'inflexion
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres la construction du point d'inflexion
	 * @throws SyntaxErrorException
	 */
	private IGraph loadInflexPoint(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		Integer ref = Integer.parseInt(parser.parseInt(","));
		String x = "";
		String y = "";

		// !! Attention bidouille pour prendre en compte le PI avec ref == -1
		if (ref == -1) {
			ref = Integer.parseInt(parser.parseInt(","));
			x = parser.parseInt(",");
			y = parser.parseInt(",");
			// x = ps.parseInt(",");
		} else {
			x = parser.parseInt(",");
			y = parser.parseInt(")");
		}

		// Dernier arc rencontre
		IArc arc = model.getArc(this.ids.get(ref));
		if (arc != null) {
			arc.addInflexPoint(new Point(Integer.parseInt(x), Integer.parseInt(y)));
			return model;
		} else {
			LOGGER.warning("Impossible de trouver l'arc " + this.ids.get(ref));
			throw new ModelException("Cannot find the arc to attach " + ref + " the inflex point");
		}
	}

	/**
	 * Positionne un attribut a partir des données CAMI
	 * @param model Le modele auquel s'applique le repositionement
	 * @param parser Le parser en charge de l'analyse de la commande CAMI
	 * @return Le modele apres le repositionement de l'attribut
	 * @throws SyntaxErrorException
	 */
	private IGraph loadTextPosition(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		Integer ref = Integer.parseInt(parser.parseInt(","));
		String name = parser.parseString(",");
		String x = parser.parseInt(",");
		String y = parser.parseInt(")");

		if (ref == 1) {
			IAttribute attribute = model.getAttribute(name);
			if (attribute != null) {
				attribute.getGraphicInfo().setLocation(new Point(Integer.parseInt(x), Integer.parseInt(y)));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		IArc arc = model.getArc(this.ids.get(ref));
		if (arc != null) {
			IAttribute attribute = arc.getAttribute(name);
			if (attribute != null) {
				attribute.getGraphicInfo().setLocation(new Point(Integer.parseInt(x), Integer.parseInt(y)));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		INode node = model.getNode(this.ids.get(ref));
		if (node != null) {
			IAttribute attribute = node.getAttribute(name);
			if (attribute != null) {
				attribute.getGraphicInfo().setLocation(new Point(Integer.parseInt(x), Integer.parseInt(y)));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		LOGGER.warning("Impossible de trouver l'element " + ref + " a positionner");
		throw new ModelException("Cannot find the element " + ref +" to be moved to");
	}



	private final IGraph loadModel(BufferedReader buffer, String formalism, IProgressMonitor monitor) throws SyntaxErrorException, ModelException, ColoaneException {
		IGraph model = new GraphModel(formalism);

		StringTokenizer tokenizer;
		CamiParser parser;

		try {
			while (buffer.ready()) {
				String line = buffer.readLine();
				monitor.worked(line.getBytes().length);

				if (line.length() <= 3) {
					continue;
				}

				tokenizer = new StringTokenizer(line);
				parser = new CamiParser(line);

				// Type de la commande
				String type = tokenizer.nextToken("(");

				// Decouverte d'un noeud
				if (type.equals("CN")) {
					model = this.loadNode(model, parser);
					continue; // Prochaine commande
				}

				// Decouverte d'un arc
				if (type.equals("CA")) {
					model = this.loadArc(model, parser);
					continue; // Prochaine commande
				}

				// Decouverte d'attribut sur une ligne
				if (type.equals("CT")) {
					model = this.loadAttribute(model, parser);
					continue;
				}

				// Creation d'une ligne dans un attribut multi-ligne
				if (type.equals("CM")) {
					model = this.loadMAttribute(model, parser);
					continue;
				}

				// Decouverte d'une position de noeud
				if (type.equals("PO") || type.equals("pO")) {
					model = this.loadPosition(model, parser);
					continue;
				}

				// Decouverte d'une position intermediaire
				if (type.equals("PI")) {
					model = this.loadInflexPoint(model, parser);
					continue;
				}

				// Decouverte d'une position de texte
				if (type.equals("PT")) {
					model = this.loadTextPosition(model, parser);
					continue;
				}

				LOGGER.warning("Commande inconue : " + type);
			}
		} catch (IOException e) {
			LOGGER.warning("Erreur lors de la lecture du fichier");
			throw new ColoaneException("An error has occured during the file reading.");
		}

		return model;
	}
}