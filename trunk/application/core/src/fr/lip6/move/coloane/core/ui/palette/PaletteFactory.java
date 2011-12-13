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
package fr.lip6.move.coloane.core.ui.palette;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.StickyNoteModel;
import fr.lip6.move.coloane.core.model.interfaces.ILink;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.logging.Logger;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Creation d'une palette GEF
 */
public final class PaletteFactory {

	/** Preference ID used to persist the palette location. */
	private static final String PALETTE_DOCK_LOCATION = "PaletteFactory.Location"; //$NON-NLS-1$
	/** Preference ID used to persist the palette size. */
	private static final String PALETTE_SIZE = "PaletteFactory.Size"; //$NON-NLS-1$
	/** Preference ID used to persist the flyout palette's state. */
	private static final String PALETTE_STATE = "PaletteFactory.State"; //$NON-NLS-1$

	/**Le logger du core */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Constructeur */
	protected PaletteFactory() { }

	/**
	 * Creation de la paletteRoot et ajout de tous les elements de la palette
	 * @param formalism : un formalisme
	 * @return une nouvelle PaletteRoot
	 */
	public static PaletteRoot createPalette(IFormalism formalism) {
		if (formalism == null) {
			LOGGER.warning("Impossible de creer la palette d'outils : Formalism nul"); //$NON-NLS-1$
			return null;
		}

		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(createShapesArcDrawer(formalism));
		palette.add(createShapesNodeDrawer(formalism));
		palette.add(createOthersDrawer());

		return palette;
	}

	/**
	 * Creation du groupe des Noeuds de la palette
	 * @param formalism formalisme selectionne
	 * @return Le conteneur d'outils à ajouter à la palette
	 */
	private static PaletteContainer createShapesNodeDrawer(IFormalism formalism) {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsNodeDrawer = new PaletteDrawer(Messages.PaletteFactory_4);

		// Liste des elements de bases associes au formalisme
		CombinedTemplateCreationEntry component; // Un element de la palette

		// Parcours de la liste des elements de base associe au formalisme
		for (IElementFormalism element : formalism.getRootGraph().getAllElementFormalism()) {

			// Si l'element parcouru est un noeur, on l'insere dans la palette
			if (element instanceof INodeFormalism) {
				final INodeFormalism node = (INodeFormalism) element;
				component = new CombinedTemplateCreationEntry(
						node.getGraphicalDescription().getPaletteName(), 	// Nom de l'objet
						node.getGraphicalDescription().getDescription(), 	// Description de l'objet
						new CreationFactory() { 	// Object Template
							public Object getNewObject() { return node;	}
							public Object getObjectType() {	return INode.class; }
						},
						ImageDescriptor.createFromFile(Coloane.class, node.getGraphicalDescription().getIcon16px()),
						ImageDescriptor.createFromFile(Coloane.class, node.getGraphicalDescription().getIcon24px()));

				componentsNodeDrawer.add(component);
			}
		}
		return componentsNodeDrawer;
	}

	/**
	 * Creation du groupe des Arcs de la palette
	 * @param formalism Le formalisme du modele en cours d'edition
	 * @return Le conteneur d'outils à ajouter à la palette
	 */
	private static PaletteContainer createShapesArcDrawer(IFormalism formalism) {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsArcDrawer = new PaletteDrawer(Messages.PaletteFactory_5);

		// Liste des elements de base du formalisme
		ConnectionCreationToolEntry component; /* Un element de la palette */

		// Parcours de la liste des elements de base
		for (IElementFormalism element : formalism.getRootGraph().getAllElementFormalism()) {

			// Si l'element parcouru est un arc
			if (element instanceof IArcFormalism) {
				final IArcFormalism arc = (IArcFormalism) element;
				component = new ConnectionCreationToolEntry(
						arc.getGraphicalDescription().getPaletteName(), // Nom de l'arc
						arc.getGraphicalDescription().getDescription(), // Description de l'arc
						new CreationFactory() {
							public Object getNewObject() { return arc; }
							public Object getObjectType() { return IArc.class; }
						},
						ImageDescriptor.createFromFile(Coloane.class, arc.getGraphicalDescription().getIcon16px()),
						ImageDescriptor.createFromFile(Coloane.class, arc.getGraphicalDescription().getIcon24px()));
				componentsArcDrawer.add(component);
			}
		}
		return componentsArcDrawer;
	}

	/**
	 * Creation du groupe des outils divers de la palette
	 * @return Le conteneur d'outils à ajouter à la palette
	 */
	private static PaletteContainer createOthersDrawer() {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsOthersDrawer = new PaletteDrawer(Messages.PaletteFactory_1);

		// Notes
		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				Messages.PaletteFactory_3,
				Messages.PaletteFactory_2,
				new SimpleFactory(StickyNoteModel.class),
				ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/sticky.png"), //$NON-NLS-1$
				ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/sticky.png")//$NON-NLS-1$
			);
		componentsOthersDrawer.add(combined);

		// Lien entre une note et un élément du graphe
		ConnectionCreationToolEntry connection = new ConnectionCreationToolEntry(
				Messages.PaletteFactory_7,
				Messages.PaletteFactory_8,
				new CreationFactory() { 	// Object Template
					public Object getNewObject() { return null;	}
					public Object getObjectType() {	return ILink.class; }
				},
				ImageDescriptor.createFromFile(Coloane.class, "/resources/formalisms/link_16.png"), //$NON-NLS-1$
				ImageDescriptor.createFromFile(Coloane.class, "/resources/formalisms/link_24.png") //$NON-NLS-1$
			);
		componentsOthersDrawer.add(connection);

		return componentsOthersDrawer;
	}

	/**
	 * Creation du groupe des outils de la palette
	 * @param palette La palette precedemment cree
	 * @return Le conteneur d'outils à ajouter à la palette
	 */
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteGroup toolGroup = new PaletteGroup(Messages.PaletteFactory_6);

		// Outil de selection d'un objet
		ToolEntry tool = new PanningSelectionToolEntry();
		toolGroup.add(tool);
		palette.setDefaultEntry(tool);

		// Outils de selection de plusieurs objets
		MarqueeToolEntry marquee = new MarqueeToolEntry();
		marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,	new Integer(MarqueeSelectionTool.BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS));
		marquee.setDescription(Messages.PaletteFactory_0);

		toolGroup.add(marquee);

		// Un separateur
		toolGroup.add(new PaletteSeparator());

		return toolGroup;
	}

	/**
	 * @return les FlyoutPreferences utilises pour charger ou sauver
	 * les preferences de la flyout palette.
	 */
	public static FlyoutPreferences createPalettePreferences() {
		/**
		 * Implementation of the FlyoutPreferences interface for the Coloane palette
		 * @author Clément Démoulins
		 */
		class ColoaneFlyoutPreferences implements FlyoutPreferences {
			private final IPreferenceStore preferenceStore;
			/**
			 * Create a FlyoutPreferences using as backend a IPreferenceStore and also set some default preferences.
			 * @param preferenceStore where the preference are stored
			 */
			public ColoaneFlyoutPreferences(IPreferenceStore preferenceStore) {
				this.preferenceStore = preferenceStore;
				this.preferenceStore.setDefault(PALETTE_STATE, FlyoutPaletteComposite.STATE_PINNED_OPEN);
			}
			/** {@inheritDoc} */
			public int getDockLocation() {
				return preferenceStore.getInt(PALETTE_DOCK_LOCATION);
			}
			/** {@inheritDoc} */
			public int getPaletteState() {
				return preferenceStore.getInt(PALETTE_STATE);
			}
			/** {@inheritDoc} */
			public int getPaletteWidth() {
				return preferenceStore.getInt(PALETTE_SIZE);
			}
			/** {@inheritDoc} */
			public void setDockLocation(int location) {
				preferenceStore.setValue(PALETTE_DOCK_LOCATION, location);
			}
			/** {@inheritDoc} */
			public void setPaletteState(int state) {
				preferenceStore.setValue(PALETTE_STATE, state);
			}
			/** {@inheritDoc} */
			public void setPaletteWidth(int width) {
				preferenceStore.setValue(PALETTE_SIZE, width);
			}
		}

		return new ColoaneFlyoutPreferences(Coloane.getInstance().getPreferenceStore());
	}
}
