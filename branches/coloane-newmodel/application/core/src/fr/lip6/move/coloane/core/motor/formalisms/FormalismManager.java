package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Attribute;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Graph;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Classe du gestionnaire de formalismes.
 * C'est ici que sont definis les formalismes.
 */
public final class FormalismManager {

	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.formalisms"; //$NON-NLS-1$

	/** Liste des formalismes disponibles. */
	private List<Formalism> formalisms = new ArrayList<Formalism>();

	/** L'instance du singleton : FormalismManager */
	private static FormalismManager instance = null;

	/**
	 * Constructeur de la classe FormalismsManager
	 * Constitution de la liste des formalismes
	 * Pour eviter les doublonson utilise le pattern <b>singleton</b>
	 * @see {@link #getInstance()}
	 */
	private FormalismManager() {
		IConfigurationElement[] formalisms = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < formalisms.length; i++) {
			buildFormalism(formalisms[i]);
		}
	}
	
	/**
	 * 
	 * @param description
	 */
	private void buildFormalism (IConfigurationElement description) {
		String name, xschema, extension, image;
		name = description.getAttribute("name");  //$NON-NLS-1$
		xschema = description.getAttribute("xschema"); //$NON-NLS-1$
		extension = description.getAttribute("extension"); //$NON-NLS-1$
		image = description.getAttribute("image"); //$NON-NLS-1$
		
		// Creation et ajout du formalisme a la liste du manager
		Formalism form = new Formalism(name,extension, xschema, image);
		this.formalisms.add(form);
		
		IConfigurationElement[] XMLDescription = description.getChildren("XmlDescription"); //$NON-NLS-1$
		
		// Ajout des definitions de graphes
		IConfigurationElement[] graphes = XMLDescription[0].getChildren("Graph"); //$NON-NLS-1$
		for (IConfigurationElement graph : graphes) {
			Graph g = new Graph(graph.getAttribute("name")); //$NON-NLS-1$
			this.getAttributes(g, graph);
			form.addElement(g);
			
			// Ajout des definitions des noeuds
			IConfigurationElement[] nodes = graph.getChildren("Node"); //$NON-NLS-1$
			for (IConfigurationElement node : nodes) {
				Node n = new Node(node.getAttribute("name")); //$NON-NLS-1$
				this.getAttributes(n, node);
				g.addElement(n);
			}
			
			// Ajout des definitions des arcs
			IConfigurationElement[] arcs = graph.getChildren("Arc"); //$NON-NLS-1$
			for (IConfigurationElement arc : arcs) {
				Arc a = new Arc(arc.getAttribute("name")); //$NON-NLS-1$
				this.getAttributes(a, arc);
				g.addElement(a);
			}			
		}
	}
	
	/**
	 * Extrait les attributs des elements de la description et les ajoute à l'élément de formalisme
	 * @param element L'élément de formalisme qui est entrain d'être construit
	 * @param current L'élément de description entrain d'être lu
	 */
	private void getAttributes(FormalismElement element, IConfigurationElement current) {
		// Ajout des definitions des attributs
		IConfigurationElement[] attributes = current.getChildren("Attribute"); //$NON-NLS-1$
		for (IConfigurationElement attribute : attributes) {
			Attribute a = new Attribute(attribute.getAttribute("name"), getBool(attribute.getAttribute("multiline")), getBool(attribute.getAttribute("drawable")));  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
			element.addAttribute(a);
		}
	}

	/**
	 * Retourne le gestionnaire de formalismes
	 * @return FormalismManager Une instance du gestionnaire de formalismes
	 */
	public static synchronized FormalismManager getInstance() {
		if (instance == null) { instance = new FormalismManager(); }
		return instance;
	}

	/**
	 * Cette methode retourne un formalisme à partir de son nom
	 * @param name Le nom du formalism qu'on cherche
	 * @return Le formalisme attendu ou <code>null</code> sinon
	 */
	public Formalism getFormalismByName(String name) {
		for (Formalism form : formalisms) {
			if (name.toLowerCase().equals(form.getName().toLowerCase())) {
				return form;
			}
		}
		return null;
	}

	/**
	 * @return La liste des formalismes disponibles
	 */
	public List<Formalism> getListOfFormalisms() {
		return formalisms;
	}
	
	/**
	 * Convertit la chaine de caractères true en boolean <code>true</code> et inversement pour false
	 * @param value true or false
	 * @return <code>true</code> ou <code>false</code>
	 */
	private boolean getBool (String value) {
		return "true".equalsIgnoreCase(value); //$NON-NLS-1$
	}
}
