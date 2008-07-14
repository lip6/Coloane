package fr.lip6.move.coloane.core.ui.palette;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.StickyNote;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

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

	protected PaletteFactory() { }

	/**
	 * Creation de la paletteRoot et ajout de tous les elements de la palette
	 * @param formalism : un formalisme
	 * @return une nouvelle PaletteRoot
	 */
	public static PaletteRoot createPalette(IFormalism formalism) {
		if (formalism == null) {
			Coloane.getLogger().warning("Impossible de creer la palette d'outils : Formalism nul"); //$NON-NLS-1$
			return null;
		}

		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(createShapesArcDrawer(formalism));
		palette.add(createShapesNodeDrawer(formalism));

		return palette;
	}

	/**
	 * Creation du groupe des Noeuds de la palette
	 * @param form : formalisme selectionne
	 * @return : paletteContainer
	 */
	private static PaletteContainer createShapesNodeDrawer(IFormalism formalism) {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsNodeDrawer = new PaletteDrawer(Messages.PaletteFactory_4);

		// Liste des elements de bases associes au formalisme
		CombinedTemplateCreationEntry component; // Un element de la palette

		// Parcours de la liste des elements de base associe au formalisme
		for (IElementFormalism element : formalism.getMasterGraph().getAllElementFormalism()) {

			// Si l'element parcouru est un noeur, on l'insere dans la palette
			if (element instanceof INodeFormalism) {
				final INodeFormalism node = (INodeFormalism) element;
				component = new CombinedTemplateCreationEntry(
						node.getGraphicalDescription().getPaletteName(), 	// Nom de l'objet
						node.getGraphicalDescription().getPaletteName(), 	// Description de l'objet
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
	 * @param formalism : Le formalisme du modele en cours d'edition
	 * @return PaletteContainer
	 */
	private static PaletteContainer createShapesArcDrawer(IFormalism formalism) {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsArcDrawer = new PaletteDrawer(Messages.PaletteFactory_5);

		// Liste des elements de base du formalisme
		ConnectionCreationToolEntry component; /* Un element de la palette */

		// Parcours de la liste des elements de base
		for (IElementFormalism element : formalism.getMasterGraph().getAllElementFormalism()) {

			// Si l'element parcouru est un arc
			if (element instanceof IArcFormalism) {
				final IArcFormalism arc = (IArcFormalism) element;
				component = new ConnectionCreationToolEntry(
						arc.getGraphicalDescription().getPaletteName(), // Nom de l'arc
						arc.getGraphicalDescription().getPaletteName(), // Description de l'arc
						new CreationFactory() {
							public Object getNewObject() { return null; }
							public Object getObjectType() { return arc; }
						},
						ImageDescriptor.createFromFile(Coloane.class, arc.getGraphicalDescription().getIcon16px()),
						ImageDescriptor.createFromFile(Coloane.class, arc.getGraphicalDescription().getIcon24px()));
				componentsArcDrawer.add(component);
			}
		}
		return componentsArcDrawer;
	}

	/**
	 * Creation du groupe des outils de la palette
	 * @param palette La palette precedemment cree
	 * @return PaletteContainer
	 */
	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteGroup toolGroup = new PaletteGroup(Messages.PaletteFactory_6);

		// Outil de selection d'un objet
		ToolEntry tool = new PanningSelectionToolEntry();
		toolGroup.add(tool);
		palette.setDefaultEntry(tool);

		// Outils de selection de plusieurs objets
		MarqueeToolEntry marquee = new MarqueeToolEntry();
		marquee.setToolProperty(MarqueeSelectionTool.PROPERTY_MARQUEE_BEHAVIOR,	new Integer(MarqueeSelectionTool.BEHAVIOR_NODES_AND_CONNECTIONS));

		toolGroup.add(marquee);

		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				"Sticky",
				"StickyNote",
				new SimpleFactory(StickyNote.class),
				ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/sticky.png"), //$NON-NLS-1$
				ImageDescriptor.createFromFile(Coloane.class, "/resources/icons/sticky.png")//$NON-NLS-1$
			);
		toolGroup.add(combined);

		// Un separateur
		toolGroup.add(new PaletteSeparator());

		return toolGroup;
	}


	/**
	 * @return les FlyoutPreferences utilises pour charger ou sauver
	 * les preferences de la flyout palette.
	 */
	public static FlyoutPreferences createPalettePreferences() {
		return new FlyoutPreferences() {
			private IPreferenceStore getPreferenceStore() {
				return Coloane.getDefault().getPreferenceStore();
			}
			public int getDockLocation() {
				return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
			}
			public int getPaletteState() {
				return getPreferenceStore().getInt(PALETTE_STATE);
			}
			public int getPaletteWidth() {
				return getPreferenceStore().getInt(PALETTE_SIZE);
			}
			public void setDockLocation(int location) {
				getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);
			}
			public void setPaletteState(int state) {
				getPreferenceStore().setValue(PALETTE_STATE, state);
			}
			public void setPaletteWidth(int width) {
				getPreferenceStore().setValue(PALETTE_SIZE, width);
			}
		};
	}
}
