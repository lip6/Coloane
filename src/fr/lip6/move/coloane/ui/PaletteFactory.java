package fr.lip6.move.coloane.ui;

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

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.*;
import fr.lip6.move.coloane.motor.models.*;
import fr.lip6.move.coloane.ui.factory.NodeFactory;

/**
 * Creation d'une palette GEF
 */
final class PaletteFactory {

	/** Preference ID used to persist the palette location. */
	private static final String PALETTE_DOCK_LOCATION = "PaletteFactory.Location";
	/** Preference ID used to persist the palette size. */
	private static final String PALETTE_SIZE = "PaletteFactory.Size";
	/** Preference ID used to persist the flyout palette's state. */
	private static final String PALETTE_STATE = "PaletteFactory.State";

	
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
		CombinedTemplateCreationEntry component; /* Un element de la palette */
		
		/* Parcours de la liste des elements de base associe au formalisme */
		for (iterator = listOfElements.iterator(); iterator.hasNext() ; ) {
			ElementBase element = (ElementBase) iterator.next();
			
			// Si l'element parcouru est un noeur, on l'insere dans la palette
			if (element instanceof NodeFormalism) {
				System.out.println("Ajout a la palette : Noeud : "+element.getName());
				
				component = new CombinedTemplateCreationEntry(
						element.getName(), // nom de l'objet
						element.getName(), // description de l'objet
						NodeImplAdapter.class,       // Object Template
						new NodeFactory(element), 
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
	 * @return : paletteContainer
	 */
	
	private static PaletteContainer createShapesArcDrawer(Formalism formalism) {
		
		/* Nouveau groupe d'outils de dessin */
		PaletteDrawer componentsArcDrawer = new PaletteDrawer("Arcs");
		
		/* Liste des elements de base du formalisme */
		ArrayList listOfElements = formalism.getListOfElementBase();
		Iterator iterator;
		ConnectionCreationToolEntry component; /* Un element de la palette */
		
		/* Parcours de la liste des elements de base */
		for (iterator = listOfElements.iterator(); iterator.hasNext(); ) {
			final ElementBase element = (ElementBase) iterator.next();
			
			/* Si l'element parcouru est un arc */
			if (element instanceof ArcFormalism) {
				System.out.println("Ajout a la palette : Arc : "+element.getName());
				component = new ConnectionCreationToolEntry(
						element.getName(), // nom de l'arc
						element.getName(), // description de l'arc
						new CreationFactory () {
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
	 * @param PaletteRoot : La palette precedemment cree 
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
	
	/* Utility class. */
	
	private PaletteFactory() {
		// Utility class
	}
	
}
