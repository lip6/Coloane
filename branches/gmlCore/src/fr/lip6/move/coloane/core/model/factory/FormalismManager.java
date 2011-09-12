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
package fr.lip6.move.coloane.core.model.factory;

import fr.lip6.move.coloane.core.formalisms.Formalism;
import fr.lip6.move.coloane.core.formalisms.elements.ArcFormalism;
import fr.lip6.move.coloane.core.formalisms.elements.GraphicalDescription;
import fr.lip6.move.coloane.core.formalisms.elements.NodeFormalism;
import fr.lip6.move.coloane.core.ui.figures.arcs.DirectedArc;
import fr.lip6.move.coloane.core.ui.figures.nodes.EllipseNode;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeParser;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IXtextProvider;
import fr.lip6.move.coloane.interfaces.formalism.constraints.IConstraintLink;
import fr.lip6.move.coloane.interfaces.formalism.constraints.IConstraintNode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.SAXException;

/**
 * Formalism Manager.<br>
 * This class is called in order to:
 * <ul>
 * 	<li>Load and Build all formalisms at runtime (when the editor is launched)</li>
 * 	<li>Give a way to access the formalism by its name, its identifier...</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public final class FormalismManager {

	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The name of the extension point that contains formalisms definition */
	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.formalisms"; //$NON-NLS-1$

	/** The FormalismManager singleton */
	private static FormalismManager instance = null;

	/** Available formalisms list */
	private List<IFormalism> formalisms = new ArrayList<IFormalism>();

	/**
	 * Constructor.<br>
	 * All available (connected as extension) formalisms are built.
	 * @see {@link #getInstance()}
	 */
	private FormalismManager() {
		IConfigurationElement[] formalisms = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < formalisms.length; i++) {
			buildFormalism(formalisms[i]);
		}
	}

	/**
	 * @return The FormalismManager instance
	 */
	public static synchronized FormalismManager getInstance() {
		if (instance == null) { instance = new FormalismManager(); }
		return instance;
	}

	/**
	 * Build a formalism thanks to its XML definition
	 * @param description Description loads from the extension point
	 */
	private void buildFormalism(IConfigurationElement description) {
		String id, name, image, href;
		id = description.getAttribute("id"); //$NON-NLS-1$
		name = description.getAttribute("name");  //$NON-NLS-1$
		image = description.getAttribute("image"); //$NON-NLS-1$
		href = description.getAttribute("href");  //$NON-NLS-1$
		Formalism form;
		LOGGER.fine("Build a formalism " + name + "- Image : " + image); //$NON-NLS-1$ //$NON-NLS-2$
		form = new Formalism(id, name, href, image);
		URL url;

		try {
			url = new URL(href);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SaxHandler handler = new SaxHandler((Formalism) form);
			saxParser.parse(url.openStream(), handler);
			form = handler.getFormalism();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get the graphic descriptions for nodes and arcs
		IConfigurationElement[] graphics = description.getChildren("GraphicInfo"); //$NON-NLS-1$
		for (IConfigurationElement graphic : graphics) {
			IElementFormalism e = form.getElementFormalism(graphic.getAttribute("refName")); //$NON-NLS-1$
			this.buildGraphicalDescription(e, graphic);
		}

		// Get the graphic descriptions for attributes
		graphics = description.getChildren("AttributeGraphicInfo"); //$NON-NLS-1$
		for (IConfigurationElement graphic : graphics) {
			IAttributeFormalism e = form.getAttributeFormalism(graphic.getAttribute("name"), graphic.getAttribute("refName")); //$NON-NLS-1$ //$NON-NLS-2$
			this.buildAttributeGraphicalDescription(graphic, e);
		}

		// Build constraints
		// Two kinds of constraint exist: Links or Nodes (beware of their type)
		try {
			IConfigurationElement[] constraints = description.getChildren("Constraint"); //$NON-NLS-1$
			for (IConfigurationElement constraint : constraints) {
				// Link constraint
				if (Boolean.parseBoolean(constraint.getAttribute("link"))) { //$NON-NLS-1$
					form.addConstraintLink((IConstraintLink) constraint.createExecutableExtension("type")); //$NON-NLS-1$
				// Node constraint
				} else {
					form.addConstraintNode((IConstraintNode) constraint.createExecutableExtension("type")); //$NON-NLS-1$
				}
			}
		} catch (CoreException core) {
			LOGGER.warning("Formalism definition error! A constraint has not been correctly defined: " + core.getMessage()); //$NON-NLS-1$
			return;
		}

		// Add the formalism to the FormalismManager's list
		this.formalisms.add(form);
	}

	/**
	 * Builds the list of attributes described by a configuration element.
	 * @param description The configuration element
	 * @param attribute The attribute to modify
	 */
	private void buildAttributeGraphicalDescription(IConfigurationElement description, IAttributeFormalism attribute) {

		if (description.getAttribute("displayed_default") != null) { //$NON-NLS-1$
			attribute.setDefaultValueDrawable(Boolean.parseBoolean(description.getAttribute("displayed_default"))); //$NON-NLS-1$
		}

		// Set default location (delta_x and delta_y)
		if (description.getAttribute("delta_x") != null) { //$NON-NLS-1$
			attribute.setXDelta(Integer.parseInt(description.getAttribute("delta_x"))); //$NON-NLS-1$
			LOGGER.finer("Add relative location (X) for the attribute : " + attribute.getName()); //$NON-NLS-1$
		}

		if (description.getAttribute("delta_y") != null) { //$NON-NLS-1$
			attribute.setYDelta(Integer.parseInt(description.getAttribute("delta_y"))); //$NON-NLS-1$
			LOGGER.finer("Add relative location (Y) for the attribute : " + attribute.getName()); //$NON-NLS-1$
		}

		// Parse graphical considerations
		if (description.getAttribute("bold") != null) { //$NON-NLS-1$
			attribute.setBold(Boolean.parseBoolean(description.getAttribute("bold"))); //$NON-NLS-1$
			LOGGER.finer("Ajout de l'indicateur de gras pour l'attribut : " + attribute.getName()); //$NON-NLS-1$
		}
		if (description.getAttribute("italic") != null) { //$NON-NLS-1$
			attribute.setItalic(Boolean.parseBoolean(description.getAttribute("italic"))); //$NON-NLS-1$
			LOGGER.finer("Add italic state for the attribute : " + attribute.getName()); //$NON-NLS-1$
		}

		if (description.getAttribute("size") != null) { //$NON-NLS-1$
			attribute.setSize(description.getAttribute("size")); //$NON-NLS-1$
			LOGGER.finer("Add bold state for the attribute : " + attribute.getName()); //$NON-NLS-1$
		}

		// Parse the parser class
		if (description.getAttribute("parser") != null) { //$NON-NLS-1$
			IAttributeParser attributeFormatter = null;
			try {
				attributeFormatter = (IAttributeParser) description.createExecutableExtension("parser"); //$NON-NLS-1$
			} catch (CoreException e) {
				e.printStackTrace();
				LOGGER.warning("Something went wrong when we tried to add the parser to attribute : " + attribute.getName()); //$NON-NLS-1$
			}
			attribute.setParser(attributeFormatter);
			LOGGER.finer("Add a parser for the attribute : " + attribute.getName()); //$NON-NLS-1$
		}

		// Parse the xtext setup class
		if (description.getAttribute("xtext_injector") != null) { //$NON-NLS-1$
			IXtextProvider provider = null;
			try {
				provider = (IXtextProvider) description.createExecutableExtension("xtext_injector"); //$NON-NLS-1$
			} catch (CoreException e) {
				e.printStackTrace();
				LOGGER.warning("Something went wrong when we tried to add the xtext setup to attribute : " + attribute.getName()); //$NON-NLS-1$
			}
			attribute.setInjector(provider.getInjector());
			LOGGER.finer("Add a setup for the attribute : " + attribute.getName()); //$NON-NLS-1$
		}

	}
	
	/**
	 * Extract graphical descriptions for elements.<br>
	 * <b>The first available graphical description is the default one.</b></br>
	 * Others are considered as alternatives.
	 * @param element Formalism element that is currently parsed. (considered as the parent)
	 * @param description Element description. This description may contains attributes
	 */
	private void buildGraphicalDescription(IElementFormalism element, IConfigurationElement description) {
		// Browse graphical description for the element
		IConfigurationElement[] graphicalDescriptions = description.getChildren("GraphicInfo"); //$NON-NLS-1$

		for (IConfigurationElement graphicalDescription : graphicalDescriptions) {

			// Build a GraphicalDescription object
			GraphicalDescription gd = new GraphicalDescription(Boolean.parseBoolean(graphicalDescription.getAttribute("palettable")), Boolean.parseBoolean(graphicalDescription.getAttribute("drawable"))); //$NON-NLS-1$ //$NON-NLS-2$
			LOGGER.finer("Build the graphical decription for the element : " + element.getName()); //$NON-NLS-1$

			if (graphicalDescription.getAttribute("paletteName") != null) { //$NON-NLS-1$
				gd.setPaletteName(graphicalDescription.getAttribute("paletteName")); //$NON-NLS-1$
				LOGGER.finest("Add a palette name for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("description") != null) { //$NON-NLS-1$
				gd.setDescription(graphicalDescription.getAttribute("description")); //$NON-NLS-1$
				LOGGER.finest("Add a description for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("fill") != null) { //$NON-NLS-1$
				gd.setFilled(Boolean.parseBoolean(graphicalDescription.getAttribute("fill"))); //$NON-NLS-1$
				LOGGER.finest("Add a fill state for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("height") != null) { //$NON-NLS-1$
				gd.setHeight(graphicalDescription.getAttribute("height")); //$NON-NLS-1$
				LOGGER.finest("Add the height of the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("width") != null) { //$NON-NLS-1$
				gd.setWidth(graphicalDescription.getAttribute("width")); //$NON-NLS-1$
				LOGGER.finest("Add the width of the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("icon16px") != null) { //$NON-NLS-1$
				gd.setIcon16px(graphicalDescription.getAttribute("icon16px")); //$NON-NLS-1$
				LOGGER.finest("Add the 16px icon for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("icon24px") != null) { //$NON-NLS-1$
				gd.setIcon24px(graphicalDescription.getAttribute("icon24px")); //$NON-NLS-1$
				LOGGER.finest("Add the 24px icon for the element : " + element.getName()); //$NON-NLS-1$
			}

			// Associate a graphical figure description (class) to the element
			if (graphicalDescription.getAttribute("associatedFigure") != null) { //$NON-NLS-1$
				try {
					Object associatedFigure = graphicalDescription.createExecutableExtension("associatedFigure"); //$NON-NLS-1$
					gd.setAssociatedFigure(associatedFigure.getClass());
					LOGGER.finest("Add the associated figure for the element : " + element.getName()); //$NON-NLS-1$
				} catch (CoreException e) {
					LOGGER.warning("Something went wrong when we tried to add the figure to the element : " + element.getName() + " ( " + e.getMessage() + " )"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			} else {
			// if no figure is given, give one by default, else there is a crash when
			// the figure is created, and crashing is bad.
				if (element instanceof ArcFormalism) {
					gd.setAssociatedFigure(DirectedArc.class);
				} else if (element instanceof NodeFormalism) {
					gd.setAssociatedFigure(EllipseNode.class);
				}
			}

			// Add the graphical description to the parent's list
			element.addGraphicalDescription(gd);
		}
	}

	/**
	 * Returns a formalism from its name or throws an {@link IllegalArgumentException} is no formalism is found.
	 * @param name The name of the formalism
	 * @return The formalism {@link IFormalism}
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public IFormalism getFormalismByName(String name) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (name.toLowerCase().equals(form.getName().toLowerCase())) {
				return form;
			}
		}
		LOGGER.warning("This formalism is not known : '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("This formalism is not known : '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Returns a formalism from its id or throws an {@link IllegalArgumentException} is no formalism is found.
	 * @param id The id of the formalism
	 * @return The formalism {@link IFormalism}
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public IFormalism getFormalismById(String id) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (form.getId().equals(id)) {
				return form;
			}
		}
		LOGGER.warning("This formalism is not known : '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("This formalism is not known : '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * Returns a formalism from its url or throws an {@link IllegalArgumentException} is no formalism is found.
	 * @param url the url of the formalism
	 * @return The formalism {@link IFormalism}
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public IFormalism getFormalismByUrl(String url) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (form.getHref().equals(url)) {
				return form;
			}
		}
		LOGGER.warning("This formalism is not known : '" + url + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("This formalism is not known : '" + url + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}


	/**
	 * @return The list of available formalisms
	 */
	public List<IFormalism> getListOfFormalisms() {
		return formalisms;
	}
}
