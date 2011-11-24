/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.importExportCAMI.importFromCAMI;

import fr.lip6.move.coloane.core.model.factory.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

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

/**
 * Import a CAMI formatted model into a graph model
 *
 * @author Jean-Baptiste Voron
 */
public class ImportFromImpl implements IImportFrom {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Table needed to link CAMI id to Coloane ID**/
	private Map<Integer, Integer> ids;


	/**
	 * Import a CAMI file into a Graph object
	 * @param filePath The location of the file to be imported
	 * @param formalism The formalism (since CAMI file does not define the model formalism)
	 * @param monitor A monitor to follow the operation progress
	 * @return The resulting model {@link IGraph}
	 * @throws ExtensionException Something went wrong
	 */
	public final IGraph importFrom(String filePath, IFormalism formalism, IProgressMonitor monitor) throws ExtensionException {
		this.ids = new HashMap<Integer, Integer>();
		IGraph model = null;

		LOGGER.finer("Creating the workspace file..."); ////$NON-NLS-1$
		File toImport = new File(filePath);

		try {
			// Read file and store all its line into a buffer
			BufferedReader buffer = new BufferedReader(new FileReader(toImport));

			try {
				// Build the model
				model = this.loadModel(buffer, formalism, monitor);
				LOGGER.fine("Le modele importe est identifie comme instance du formalisme :" + formalism);
			} catch (SyntaxErrorException se) {
				throw new ExtensionException("Error while parsing the model : " + se.getMessage());
			} catch (ModelException me) {
				throw new ExtensionException("Error while building the model : " + me.getMessage());
			}
			buffer.close();
		} catch (FileNotFoundException fe) {
			LOGGER.warning("Le fichier " + filePath + " est introuvable...");
			throw new ExtensionException("Filename " + filePath + "is invalid or does not exist.");
		} catch (IOException ioe) {
			LOGGER.warning("Erreur lors de la lecture du fichier");
			throw new ExtensionException("An error has occured during the file reading.");
		}
		return model;
	}


	/**
	 * Build a node from CAMI command
	 * @param model The model where the node model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the node construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 */
	private IGraph loadNode(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String type = parser.parseString(","); //$NON-NLS-1$
		Integer id = Integer.valueOf(parser.parseInt(")")); //$NON-NLS-1$

		// If the current node is not the top-level one
		if (id.intValue() != 1) {
			INodeFormalism nodeFormalism = (INodeFormalism) model.getFormalism().getRootGraph().getElementFormalism(type);
			INode node = model.createNode(nodeFormalism);
			this.ids.put(id, node.getId());
		}

		LOGGER.finer("Creation du noeud " + this.ids.get(id));
		return model;
	}

	/**
	 * Build an arc from CAMI command
	 * @param model The model where the arc model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the arc construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 */
	private IGraph loadArc(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String type = parser.parseString(",");
		Integer id = Integer.valueOf(parser.parseInt(","));
		Integer source = Integer.valueOf(parser.parseInt(","));
		Integer target = Integer.valueOf(parser.parseInt(")"));

		// Look for source and target
		INode nodeBegin = model.getNode(this.ids.get(source));
		INode nodeEnd = model.getNode(this.ids.get(target));

		if (nodeBegin == null || nodeEnd == null) {
			LOGGER.warning("Impossible de connecter l'arc : Source / Cible manquante");
			throw new SyntaxErrorException("Cannot connect the source node with the target node. One of these is missing");
		}

		// Arc creation
		IArcFormalism arcFormalism = (IArcFormalism) model.getFormalism().getRootGraph().getElementFormalism(type);
		IArc arc = model.createArc(arcFormalism, nodeBegin, nodeEnd);
		this.ids.put(id, arc.getId());
		LOGGER.finer("Creation de l'arc " + this.ids.get(id) + " (" + nodeBegin.getId() + " -> " + nodeEnd + ")");

		return model;
	}

	/**
	 * Build an attribute from CAMI command
	 * @param model The model where the attribute model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the attribute construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 */
	private IGraph loadAttribute(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String name = parser.parseString(",");
		Integer ref = Integer.parseInt(parser.parseInt(","));
		String value = parser.parseString(")");

		// Attribute can be directly fixed to the model
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

		// Is the attribute attached to an arc
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

		// Is the attribute attached to a node
		INode node = model.getNode(this.ids.get(ref));
		if (node != null) {
			IAttribute attribute = node.getAttribute(name);
			/** Special case to allow import of CAMI P/T nets into Time Petri nets:
			 * "name" attribute is the transition "label" in TPN.
			 */
			if (attribute == null && name.equals("name")) {
				attribute = node.getAttribute("label");
			}

			if (attribute != null) {
				attribute.setValue(value);
				LOGGER.finest("Attribut " + name + " = " + value + " pour le noeud " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// Otherwise an error is raised
		LOGGER.warning("Impossible de trouver l objet identifie par " + ref);
		throw new ModelException("Cannot find the model element " + ref);
	}

	/**
	 * Build an multiple line attribute from CAMI command
	 * @param model The model where the attribute model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the attribute construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 */
	private IGraph loadMAttribute(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String name = parser.parseString(",");
		Integer ref = Integer.parseInt(parser.parseInt(","));
		Integer line = Integer.parseInt(parser.parseInt(","));
		parser.parseInt(","); // Deprecated
		String value = parser.parseString(")");

		// Attribute can be attached directly to the model
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

		// Is the attribute attached to an arc
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

		// Is the attribute attached to a node
		INode node = model.getNode(this.ids.get(ref));
		if (node != null) {
			IAttribute attribute = node.getAttribute(name);
			/** Special case to allow import of CAMI P/T nets into Time Petri nets:
			 * "name" attribute is the transition "label" in TPN.
			 */
			if (attribute == null && name.equals("name")) {
				attribute = node.getAttribute("label");
			}
			if (attribute != null) {
				if (line == 1) { attribute.setValue(value); } else { attribute.setValue(attribute.getValue() + "\n" + value); }
				LOGGER.finest("Attribut " + name + " = " + attribute.getValue() + " pour l'arc " + this.ids.get(ref));
			} else {
				LOGGER.fine("Attribut " + name + " inconnu... Ignore !");
			}
			return model;
		}

		// Otherwise, an error is raised
		LOGGER.warning("Impossible de trouver l objet identifie par " + ref);
		throw new ModelException("Cannot find the model element " + ref);
	}

	/**
	 * Locate an object (attribute, node) from CAMI command
	 * @param model The model where the attribute model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the attribute construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 */
	private IGraph loadPosition(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		String ref = parser.parseInt(",");
		String x = "";
		String y = "";

		// !! Warning : Tips to deal with 3rd type PO command
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
	 * Build a bend point from CAMI command
	 * @param model The model where the attribute model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the attribute construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 */
	private IGraph loadInflexPoint(IGraph model, CamiParser parser) throws SyntaxErrorException, ModelException {
		Integer ref = Integer.parseInt(parser.parseInt(","));
		String x = "";
		String y = "";

		// !! Warning ! Tip to deal with PI command with ref=1
		if (ref == -1) {
			ref = Integer.parseInt(parser.parseInt(","));
			x = parser.parseInt(",");
			y = parser.parseInt(",");
			// x = ps.parseInt(",");
		} else {
			x = parser.parseInt(",");
			y = parser.parseInt(")");
		}

		// Last parsed arc
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
	 * Locate an attribute thanks to a CAMI command
	 * @param model The model where the attribute model should be fixed
	 * @param parser The parser responsible for the command parse
	 * @return The model after the attribute construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
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
		throw new ModelException("Cannot find the element " + ref + " to be moved to");
	}

	/**
	 * Build an attribute from CAMI command
	 * @param buffer The set of CAMI commands
	 * @param formalism The formalism used by Coloane
	 * @param monitor The monitor to follow the operation process
	 * @return The model after the attribute construction
	 * @throws SyntaxErrorException Error during the CAMI parse (Extension side)
	 * @throws ModelException Error during model construction (Coloane side)
	 * @throws ExtensionException Error throws if an IO exception is raised
	 */
	private IGraph loadModel(BufferedReader buffer, IFormalism formalism, IProgressMonitor monitor) throws SyntaxErrorException, ModelException, ExtensionException {
		IGraph model = new GraphModelFactory().createGraph(formalism);

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

				// Command type
				String type = tokenizer.nextToken("(");

				// Node
				if (type.equals("CN")) {
					model = this.loadNode(model, parser);
					continue; // Prochaine commande
				}

				// Arc
				if (type.equals("CA")) {
					model = this.loadArc(model, parser);
					continue; // Prochaine commande
				}

				// Single line attribute
				if (type.equals("CT")) {
					model = this.loadAttribute(model, parser);
					continue;
				}

				// Multiple line attribute
				if (type.equals("CM")) {
					model = this.loadMAttribute(model, parser);
					continue;
				}

				// Node position
				if (type.equals("PO") || type.equals("pO")) {
					model = this.loadPosition(model, parser);
					continue;
				}

				// Bend point
				if (type.equals("PI")) {
					model = this.loadInflexPoint(model, parser);
					continue;
				}

				// Attribute position
				if (type.equals("PT")) {
					model = this.loadTextPosition(model, parser);
					continue;
				}

				LOGGER.warning("Commande inconue : " + type);
			}
		} catch (IOException e) {
			LOGGER.warning("Erreur lors de la lecture du fichier");
			throw new ExtensionException("An error has occured during the file reading.");
		}

		return model;
	}
}
