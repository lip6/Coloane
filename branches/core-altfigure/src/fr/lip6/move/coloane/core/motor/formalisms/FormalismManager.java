package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintLink;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintNode;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphicalDescription;
import fr.lip6.move.coloane.core.motor.formalisms.elements.NodeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Classe du gestionnaire de formalismes.
 * C'est ici que sont definis les formalismes.
 */
public final class FormalismManager {

	/** Une instance du logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Le nom du point d'extension qui contient les definitions de formalismes */
	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.formalisms"; //$NON-NLS-1$

	/** L'instance du singleton : FormalismManager */
	private static FormalismManager instance = null;

	/** Liste des formalismes disponibles. */
	private List<IFormalism> formalisms = new ArrayList<IFormalism>();

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
	 * Construction du formalisme à partir de la description XML
	 * @param description L'élément de haut niveau décrivant le formalisme
	 */
	private void buildFormalism(IConfigurationElement description) {
		String id, name, fkname, xschema, image;
		id = description.getAttribute("id"); //$NON-NLS-1$
		name = description.getAttribute("name");  //$NON-NLS-1$
		fkname = description.getAttribute("fkname");  //$NON-NLS-1$
		xschema = description.getAttribute("xschema"); //$NON-NLS-1$
		image = description.getAttribute("image"); //$NON-NLS-1$

		LOGGER.fine("Construction du formalisme " + name + "(parent : " + fkname + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		LOGGER.finer("Details du formalisme " + name + " : "); //$NON-NLS-1$ //$NON-NLS-2$
		LOGGER.finer("XSchema : " + xschema + " - Image : " + image); //$NON-NLS-1$ //$NON-NLS-2$

		// Creation et ajout du formalisme a la liste du manager
		Formalism form = new Formalism(id, name, fkname, image);

		IConfigurationElement[] xmlDescription = description.getChildren("XmlDescription"); //$NON-NLS-1$

		try {

			// Ajout des definitions de graphes
			IConfigurationElement[] graphes = xmlDescription[0].getChildren("Graph"); //$NON-NLS-1$
			for (IConfigurationElement graph : graphes) {
				GraphFormalism g = new GraphFormalism(graph.getAttribute("name"), form); //$NON-NLS-1$
				LOGGER.finer("Construction de l'element graphe : " + g.getName()); //$NON-NLS-1$
				this.buildAttributes(g, graph);
				form.setMasterGraph(g);

				// Ajout des definitions des noeuds
				IConfigurationElement[] nodes = graph.getChildren("Node"); //$NON-NLS-1$
				for (IConfigurationElement node : nodes) {
					NodeFormalism n = new NodeFormalism(node.getAttribute("name"), form); //$NON-NLS-1$
					LOGGER.finer("Construction de l'element node : " + n.getName()); //$NON-NLS-1$
					this.buildAttributes(n, node);
					this.buildGraphicalDescription(n, node);
					g.addElement(n);
				}

				// Ajout des definitions des arcs
				IConfigurationElement[] arcs = graph.getChildren("Arc"); //$NON-NLS-1$
				for (IConfigurationElement arc : arcs) {
					ArcFormalism a = new ArcFormalism(arc.getAttribute("name"), form); //$NON-NLS-1$
					LOGGER.finer("Construction de l'element arc : " + a.getName()); //$NON-NLS-1$
					this.buildAttributes(a, arc);
					this.buildGraphicalDescription(a, arc);
					g.addElement(a);
				}
			}

		} catch (NumberFormatException badNumber) {
			LOGGER.warning("Erreur dans le formalisme ! Une valeur incorrecte a ete detectee : " + badNumber.getMessage()); //$NON-NLS-1$
			return;
		}

		// Prise en comptes des contraintes.
		// Attention, les contraintes peuvent être de 2 types : Lien ou Noeud, il faut donc les ajouter en tenant compte de leur type
		try {
			IConfigurationElement[] constraints = xmlDescription[0].getChildren("Constraint"); //$NON-NLS-1$
			for (IConfigurationElement constraint : constraints) {

				// Dans le cas de contrainte de lien
				if (this.getBool(constraint.getAttribute("link"))) { //$NON-NLS-1$
					form.addConstraintLink((IConstraintLink) constraint.createExecutableExtension("type")); //$NON-NLS-1$
				// Dans le cas de contrainte de noeud
				} else {
					form.addConstraintNode((IConstraintNode) constraint.createExecutableExtension("type")); //$NON-NLS-1$
				}
			}
		} catch (CoreException core) {
			LOGGER.warning("Erreur dans le formalisme ! Une contrainte a mal ete definie: " + core.getMessage()); //$NON-NLS-1$
			core.printStackTrace();
			return;
		}

		// Ajout du formalisme construit à la liste des formalismes
		this.formalisms.add(form);
	}

	/**
	 * Extrait les attributs des elements de la description et les ajoute à l'élément de formalisme
	 * @param element L'élément de formalisme qui est entrain d'être construit
	 * @param current L'élément de description entrain d'être lu
	 */
	private void buildAttributes(ElementFormalism element, IConfigurationElement current) {
		// Ajout des definitions des attributs
		IConfigurationElement[] attributes = current.getChildren("Attribute"); //$NON-NLS-1$
		for (IConfigurationElement attribute : attributes) {
			AttributeFormalism a = new AttributeFormalism(attribute.getAttribute("name"), getBool(attribute.getAttribute("drawable")), getBool(attribute.getAttribute("multiline")));  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
			LOGGER.finer("Construction de l'attribut " + a.getName() + " pour l'element : " + element.getName()); //$NON-NLS-1$ //$NON-NLS-2$

			// Prise en compte de la valeur par defaut de l'attribut
			if (attribute.getAttribute("default") != null) { //$NON-NLS-1$
				a.setDefaultValue(attribute.getAttribute("default")); //$NON-NLS-1$
				LOGGER.finer("Ajout de la valeur par default " + a.getDefaultValue() + " pour l'attribut : " + a.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			}

			// Prise en compte de la valeur par defaut de l'attribut
			if (attribute.getAttribute("bold") != null) { //$NON-NLS-1$
				a.setBold(this.getBool(attribute.getAttribute("bold"))); //$NON-NLS-1$
				LOGGER.finer("Ajout de l'indicateur de gras pour l'attribut : " + a.getName()); //$NON-NLS-1$
			}

			// Prise en compte de la valeur par defaut de l'attribut
			if (attribute.getAttribute("italic") != null) { //$NON-NLS-1$
				a.setItalic(this.getBool(attribute.getAttribute("italic"))); //$NON-NLS-1$
				LOGGER.finer("Ajout de l'indicateur d'italique pour l'attribut : " + a.getName()); //$NON-NLS-1$
			}

			// Prise en compte de la valeur par defaut de l'attribut
			if (attribute.getAttribute("size") != null) { //$NON-NLS-1$
				a.setSize(attribute.getAttribute("size")); //$NON-NLS-1$
				LOGGER.finer("Ajout de l'indicateur de taille de police pour l'attribut : " + a.getName()); //$NON-NLS-1$
			}

			element.addAttribute(a);
		}
	}

	/**
	 * Construction de la représentation graphique des éléments
	 * @param element L'élément concerné par la description graphique
	 * @param current L'élément XML en cours de parse
	 */
	private void buildGraphicalDescription(ElementFormalism element, IConfigurationElement current) {
		// Ajout des considérations graphiques
		IConfigurationElement[] graphicInfoTable = current.getChildren("GraphicInfo"); //$NON-NLS-1$
		IConfigurationElement graphicInfo = graphicInfoTable[0];

		// Construction de base
		GraphicalDescription gd = new GraphicalDescription(getBool(graphicInfo.getAttribute("palettable")), getBool(graphicInfo.getAttribute("drawable"))); //$NON-NLS-1$ //$NON-NLS-2$
		LOGGER.finer("Construction de la description graphique pour l'element : " + element.getName()); //$NON-NLS-1$

		// Prise en compte du nom de palette associé à l'élément de formalisme
		if (graphicInfo.getAttribute("paletteName") != null) { //$NON-NLS-1$
			gd.setPaletteName(graphicInfo.getAttribute("paletteName")); //$NON-NLS-1$
			LOGGER.finest("Ajout du nom de palette pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de la description de l'élément de formalisme
		if (graphicInfo.getAttribute("description") != null) { //$NON-NLS-1$
			gd.setDescription(graphicInfo.getAttribute("description")); //$NON-NLS-1$
			LOGGER.finest("Ajout de la description pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de la hauteur de l'élément de formalisme
		if (graphicInfo.getAttribute("fill") != null) { //$NON-NLS-1$
			gd.setFilled(getBool(graphicInfo.getAttribute("fill"))); //$NON-NLS-1$
			LOGGER.finest("Ajout de l'indicateur de remplissage pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de la hauteur de l'élément de formalisme
		if (graphicInfo.getAttribute("height") != null) { //$NON-NLS-1$
			gd.setHeight(graphicInfo.getAttribute("height")); //$NON-NLS-1$
			LOGGER.finest("Ajout de la hauteur pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de la largeur de l'élément de formalisme
		if (graphicInfo.getAttribute("width") != null) { //$NON-NLS-1$
			gd.setWidth(graphicInfo.getAttribute("width")); //$NON-NLS-1$
			LOGGER.finest("Ajout de la largeur pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de l'icone 16px de l'élément de formalisme
		if (graphicInfo.getAttribute("icon16px") != null) { //$NON-NLS-1$
			gd.setIcon16px(graphicInfo.getAttribute("icon16px")); //$NON-NLS-1$
			LOGGER.finest("Ajout de l'icone 16px pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de l'icone 24px de l'élément de formalisme
		if (graphicInfo.getAttribute("icon24px") != null) { //$NON-NLS-1$
			gd.setIcon24px(graphicInfo.getAttribute("icon24px")); //$NON-NLS-1$
			LOGGER.finest("Ajout de l'icone 24px pour l'element : " + element.getName()); //$NON-NLS-1$
		}

		// Prise en compte de la figure (JAVA) associée à l'élement de formalisme
		if (graphicInfo.getAttribute("associatedFigure") != null) { //$NON-NLS-1$
			try {
				Object associatedFigure = graphicInfo.createExecutableExtension("associatedFigure"); //$NON-NLS-1$
				gd.setAssociatedFigure(associatedFigure.getClass());
				LOGGER.finest("Ajout de la figure associee pour l'element : " + element.getName()); //$NON-NLS-1$
			} catch (CoreException e) {
				LOGGER.warning("Echec lors de l'association de la figure a l'element : " + element.getName() + " ( " + e.getMessage() + " )"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}

		element.setGraphicalDescription(gd);
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
	 * @return Le IFormalism
	 * @throws IllegalArgumentException si le formalisme n'est pas connu
	 */
	public IFormalism getFormalismByName(String name) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (name.toLowerCase().equals(form.getName().toLowerCase())) {
				return form;
			}
		}
		LOGGER.warning("Ce formalisme n'est pas connu : '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("Ce formalisme n'est pas connu : '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Cette methode retourne un formalisme à partir de son id
	 * @param id L'id du formalism qu'on cherche
	 * @return Le IFormalism
	 * @throws IllegalArgumentException si le formalisme n'est pas connu
	 */
	public IFormalism getFormalismById(String id) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (form.getId().equals(id)) {
				return form;
			}
		}
		LOGGER.warning("Ce formalisme n'est pas connu : '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("Ce formalisme n'est pas connu : '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Cette methode retourne un formalisme à partir de son nom
	 * @param fkName Le nom du formalism qu'on cherche
	 * @return Le IFormalism
	 * @throws IllegalArgumentException si le formalisme n'est pas connu
	 */
	public IFormalism getFormalismByFkName(String fkName) throws IllegalArgumentException {
		// TODO : meilleur gestion du lien formalisme/coloane <-> formalisme/framekit
		if (fkName.toLowerCase().equals("ami-net")) { //$NON-NLS-1$
			for (IFormalism form : formalisms) {
				if ("Colored Petri Net".toLowerCase().equals(form.getName().toLowerCase())) { //$NON-NLS-1$
					return form;
				}
			}
		}
		for (IFormalism form : formalisms) {
			if (fkName.toLowerCase().equals(form.getFKName().toLowerCase())) {
				return form;
			}
		}
		LOGGER.warning("Ce formalisme n'est pas connu : '" + fkName + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("Ce formalisme n'est pas connu : '" + fkName + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @return La liste des formalismes disponibles
	 */
	public List<IFormalism> getListOfFormalisms() {
		return formalisms;
	}

	/**
	 * Convertit la chaine de caractères true en boolean <code>true</code> et inversement pour false
	 * @param value true or false
	 * @return <code>true</code> ou <code>false</code>
	 */
	private boolean getBool(String value) {
		return "true".equalsIgnoreCase(value); //$NON-NLS-1$
	}
}
