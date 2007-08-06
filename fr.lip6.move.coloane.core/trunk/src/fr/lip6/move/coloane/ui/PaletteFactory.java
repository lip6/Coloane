package fr.lip6.move.coloane.ui;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.motor.formalism.NodeFormalism;
import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

import java.util.ArrayList;
import java.util.Iterator;

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
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Creation d'une palette GEF
 */
public final class PaletteFactory {

	/** Preference ID used to persist the palette location. */
	private static final String PALETTE_DOCK_LOCATION = "PaletteFactory.Location";
	/** Preference ID used to persist the palette size. */
	private static final String PALETTE_SIZE = "PaletteFactory.Size";
	/** Preference ID used to persist the flyout palette's state. */
	private static final String PALETTE_STATE = "PaletteFactory.State";

	/**
	 * Creation de la paletteRoot et ajout de tous les elements de la palette
	 * @param formalism : un formalisme
	 * @return une nouvelle PaletteRoot
	 */
	static PaletteRoot createPalette(Formalism formalism) {
		if (formalism == null) {
			System.out.println("Erreur lors de la creation de la palette");
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
		PaletteDrawer componentsNodeDrawer = new PaletteDrawer("Nodes");

		// Liste des elements de bases associes au formalisme
		ArrayList listOfElements = formalism.getListOfElementBase();
		Iterator iterator;
		CombinedTemplateCreationEntry component; // Un element de la palette

		// Parcours de la liste des elements de base associe au formalisme
		for (iterator = listOfElements.iterator(); iterator.hasNext();) {
			final ElementBase element = (ElementBase) iterator.next();

			// Si l'element parcouru est un noeur, on l'insere dans la palette
			if (element instanceof NodeFormalism) {
				component = new CombinedTemplateCreationEntry(
						element.getPaletteName(), 			// Nom de l'objet
						element.getPaletteName(), 			// Description de l'objet
						new CreationFactory() { 	// Object Template
							public Object getNewObject() {
								return new NodeImplAdapter(element);
							}

							public Object getObjectType() {
								return NodeImplAdapter.class;
							}

						},
						ImageDescriptor.createFromFile(Coloane.class, element.getAddrIcone16()),
						ImageDescriptor.createFromFile(Coloane.class, element.getAddrIcone24()));

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

		/* Nouveau groupe d'outils de dessin */
		PaletteDrawer componentsArcDrawer = new PaletteDrawer("Arcs");

		/* Liste des elements de base du formalisme */
		ArrayList listOfElements = formalism.getListOfElementBase();
		Iterator iterator;
		ConnectionCreationToolEntry component; /* Un element de la palette */

		/* Parcours de la liste des elements de base */
		for (iterator = listOfElements.iterator(); iterator.hasNext();) {
			final ElementBase element = (ElementBase) iterator.next();

			/* Si l'element parcouru est un arc */
			if (element instanceof ArcFormalism) {
				component = new ConnectionCreationToolEntry(
						element.getPaletteName(), // nom de l'arc
						element.getPaletteName(), // description de l'arc
						new CreationFactory() {
							public Object getNewObject() {
								return null;
							}

							public Object getObjectType() {
								return element;
							}

						},
						ImageDescriptor.createFromFile(Coloane.class, element.getAddrIcone16()),
						ImageDescriptor.createFromFile(Coloane.class, element.getAddrIcone24()));
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
		PaletteGroup toolGroup = new PaletteGroup("Tools");

		// Outil de selection d'un objet
		ToolEntry tool = new PanningSelectionToolEntry();
		toolGroup.add(tool);
		palette.setDefaultEntry(tool);

		// Outils de selection de plusieurs objets
		toolGroup.add(new MarqueeToolEntry());

		// Un separateur
		toolGroup.add(new PaletteSeparator());

		return toolGroup;
	}


	/**
	 * @return les FlyoutPreferences utilises pour charger ou sauver
	 * les preferences de la flyout palette.
	 */
	static FlyoutPreferences createPalettePreferences() {
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
