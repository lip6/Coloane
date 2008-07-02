package fr.lip6.move.coloane.core.ui.palette;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;
import fr.lip6.move.coloane.interfaces.model.interfaces.INode;

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
	public static PaletteRoot createPalette(Formalism formalism) {
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
	private static PaletteContainer createShapesNodeDrawer(Formalism formalism) {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsNodeDrawer = new PaletteDrawer(Messages.PaletteFactory_4);

		// Liste des elements de bases associes au formalisme
		CombinedTemplateCreationEntry component; // Un element de la palette

		// Parcours de la liste des elements de base associe au formalisme
		for (FormalismElement element : formalism.getListOfElementBase()) {

			// Si l'element parcouru est un noeur, on l'insere dans la palette
			if (element instanceof Node) {
				final Node node = (Node) element;
				component = new CombinedTemplateCreationEntry(
						node.getPaletteName(), 	// Nom de l'objet
						node.getPaletteName(), 	// Description de l'objet
						new CreationFactory() { 	// Object Template
							public Object getNewObject() { return node;	}
							public Object getObjectType() {	return INode.class; }
						},
						ImageDescriptor.createFromFile(Coloane.class, node.getAddrIcone16()),
						ImageDescriptor.createFromFile(Coloane.class, node.getAddrIcone24()));

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
	private static PaletteContainer createShapesArcDrawer(Formalism formalism) {

		// Nouveau groupe d'outils de dessin
		PaletteDrawer componentsArcDrawer = new PaletteDrawer(Messages.PaletteFactory_5);

		// Liste des elements de base du formalisme
		ConnectionCreationToolEntry component; /* Un element de la palette */

		// Parcours de la liste des elements de base
		for (FormalismElement element : formalism.getListOfElementBase()) {

			// Si l'element parcouru est un arc
			if (element instanceof Arc) {
				final Arc arc = (Arc) element;
				component = new ConnectionCreationToolEntry(
						arc.getPaletteName(), // Nom de l'arc
						arc.getPaletteName(), // Description de l'arc
						new CreationFactory() {
							public Object getNewObject() { return null; }
							public Object getObjectType() { return arc; }
						},
						ImageDescriptor.createFromFile(Coloane.class, arc.getAddrIcone16()),
						ImageDescriptor.createFromFile(Coloane.class, arc.getAddrIcone24()));
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
