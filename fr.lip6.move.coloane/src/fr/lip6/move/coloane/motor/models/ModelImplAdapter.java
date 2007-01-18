package fr.lip6.move.coloane.motor.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;


import fr.lip6.move.coloane.communications.models.*;
import fr.lip6.move.coloane.interfaces.models.*;
import fr.lip6.move.coloane.motor.formalism.*;


/**
 * Adaptateur pour le modele generique. 
 * Permet d'implementer diverses interfaces utilent a different modules
 */
public class ModelImplAdapter extends AbstractModelElement implements IModel,Serializable {

	/** Id pour la serialisation. */
	private static final long serialVersionUID = 1L;

	/** Modele generique sur lequel s'applique cet adaptateur */
	private Model model;

	/** Le formalisme associe a ce modele */
	private Formalism formalism;

	/** La liste de NodeImplAdapter. */
	public List<NodeImplAdapter> children = new ArrayList<NodeImplAdapter>();

	/** Date de derniere modification */
	private int date = (int) System.currentTimeMillis();

	/** L'etat du blocage pour l'edition du model */
	private boolean isLocked = false;

	/** Etat du modele par rapport a FK (Dirty -> pas a jour) */
	private boolean dirty = true;
	
	/**
	 * Construction d'un modele pour le formalisme et un pour un modele vide
	 * @param formalism Le formalisme du modele
	 */
	public ModelImplAdapter(Formalism formalism) {
		super();
		this.model = new Model(formalism.getName());
		this.formalism = formalism;
		this.setProperties();
	}
	
	/**
	 * Construction d'un modele pour le formalisme et un pour un modele deja existant
	 * -> Lu depuis un fichier par exemple
	 * @param formalism Le formalisme du modele
	 * @param model Le modele existant
	 */
	public ModelImplAdapter(Model model, Formalism formalism) {
		super();
		try {
			this.model = model;
			this.formalism = formalism;
			this.setAdapter();
			this.setProperties(model);
		} catch (Exception e) {
			System.err.println("Erreur lors de la construction du modele");
		}
	}


	/**
	 * Methode implementant les adaptateurs, noeuds et arcs pour le modele
	 */
	private void setAdapter() throws Exception {

		// Ajout de tous les noeuds dans l'adapter (parcours de tous les noeuds du modele)
		for (int i = 0; i < this.model.getListOfNodeSize(); i++) {
			Node currentNode = this.model.getNthNode(i);
			NodeImplAdapter node = new NodeImplAdapter(currentNode,(ElementBase) this.formalism.string2Node(currentNode.getNodeType()));
			this.children.add(node);
		}
		
		// ajouts de tous les attributs ???

		// Ajout de tous les Arcs
		for (int j = 0; j < this.model.getListOfArcSize(); j++) {
			Arc currentArc = this.model.getNthArc(j);
			NodeImplAdapter target = null;
			NodeImplAdapter source = null;
	
			// Pour chaque enfant, on cherche si l'arc est source ou destination
			Iterator iterator = this.children.iterator();
			boolean findSource = false;
			boolean findTarget = false;
			
			while ((iterator.hasNext()) && ((!findSource) || (!findTarget)) ) {

				NodeImplAdapter currentNode = (NodeImplAdapter) iterator.next();

				if (currentArc.getEndingNode() == currentNode.getGenericNode()) {
					target = currentNode; 
					findTarget = true;
				} else if (currentArc.getStartingNode() == currentNode.getGenericNode()) {
					source = currentNode; 
					findSource = true;
				}
			}

			// Un arc a forcement une source et une destination... sinon probleme
			if ((target != null) && (source != null)) {
				System.out.println("Linkage ok pour l'arc "+currentArc.getUniqueId());
				// Creation de l'arc adapter
				new ArcImplAdapter(currentArc, source, target, this.formalism.string2Arc(currentArc.getArcType()));
			} else {
				throw new Exception("Source ou destination de l'arc manquante");
			}
		}
	}

	/**
	 * Creer les adaptateurs pour les proprietes a partir des attributs
	 */
	public void setProperties() {
		this.properties.clear();
	
		// Creation de tous les attributs du formalisme
		Iterator iterator = this.getFormalism().getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			// Les attributs possibles dans le formalisme
			AttributeFormalism attribut = (AttributeFormalism) iterator.next();
			
			Attribute attrAPI = new Attribute(attribut.getName(), new String[]{attribut.getDefaultValue()}, 1);
			AttributeImplAdapter property = new AttributeImplAdapter(attrAPI, attribut.isDrawable());

			property.setAttribute(attrAPI);
			this.properties.put(property.getId(), property);
			this.model.addAttribute(attrAPI);
		}
	}

	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele generique)
	 * Cela peut tre utile lorsq'un modele est lu depuis un fichier.
	 * @param model
	 */
	public void setProperties(Model model) {
		// Creation des proprietes par defaut (propriete du formalisme)
		setProperties();

		// Pour chaque propriete du modele adaptater
		for (Enumeration e = properties.elements(); e.hasMoreElements();) {

			AttributeImplAdapter property = (AttributeImplAdapter) e.nextElement();
			for (int i = 0; i < this.model.getListOfAttrSize(); i++) {

				// Si la propriete correspond a l'attribut, on affecte la bonne valeur
				if (property.getGenericAttribute().getName().equalsIgnoreCase(this.model.getNthAttr(i).getName())) {
					property.setValue(this.model.getNthAttr(i).getValue());
					break;
				}
			}
		}
	}

	/**
	 * Ajout d'un noeud au modele
	 * Leve un evenement CHILD_ADDED_PROP
	 */
	public void addChild(NodeImplAdapter child) throws IllegalArgumentException {

		// Ajout d'un noeud au modele
		try {
			this.model.addNode(child.getGenericNode());
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}

		// Ajout a la liste children
		if ((child != null) && this.children.add(child)) {
			firePropertyChange(NODE_ADDED_PROP, null, child);
		} else {
			throw new IllegalArgumentException("Erreur lors de l'ajout d'un noeud au modele");
		}

	}

	/**
	 * Suprimmer un noeud
	 * Lever un evenement CHILD_REMOVED_PROP
	 */
	public void removeChild(NodeImplAdapter child)
			throws IllegalArgumentException {
		try {
			// Enleve un noeud au modele
			this.model.removeNode(child.getGenericNode());
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}

		// Envele un noeud de la liste children
		if ((child != null) && children.remove(child)) {
			firePropertyChange(NODE_REMOVED_PROP, null, child);
		} else {
			throw new IllegalArgumentException("Erreurs lors de la suppression d'un noeud du modele");
		}

	}

	/** 
	 * Retourne la liste des NodeImplAdapter du modele
	 * @return List
	 */
	public List getChildren() {
		return this.children;
	}

	/**
	 * Retourne le modele generique
	 * @return Model
	 * @see Model
	 */
	public Model getGenericModel() {

		return this.model;
	}

	/**
	 * Retourne le formalisme
	 * @return Formalism
	 */
	public Formalism getFormalism() {
		return formalism;
	}

	/**
	 * Obtenir le type du formalisme
	 * return le type du formalisme
	 */
	public String getFormalismType() {
		return this.formalism.getName();
	}

	/**
	 * Indique si le modele est verouille
	 * @return boolean
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * Blocage ou deblocage du modele en edition 
	 * @param isLocked a true on bloque le model en edition 
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * Associe un modele generique avec un modele adaptater
	 * @param model Le modele generique
	 */
	public void setModel(Model model) throws Exception {
		this.model = model;
		this.setAdapter();
		this.setProperties(this.model);
	}

	/**
	 * Modifie la date du modele (necessaire pour synchronisation avec FK)
	 * Indique si l'envoi d'un message a FK est necessaire
	 * @return boolean Indique si un message doit etre envoye a FK en donnant une datee
	 */
	public int modifyDate() {
		date = (int) System.currentTimeMillis();
		
		// Si le modele n'etait pas marque comme sale, on le marque
		if (!dirty) {
			dirty = true;
			return date;
		}
		return 0;
	}
	
	/**
	 * Retourne la date associee au modele
	 * @return int
	 */
	public int getDate() {
		return date;
	}

	/**
	 * Change la valeur de la propriete 
	 * @param id Objet dont il faut modifier la valeur
	 * @param value Nouvelle valeur pour l'objet
	 */
	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
	}

	/**
	 * Inidcateur de fraicheur du modele
	 * @return boolean
	 */
	public boolean isDirty() {
		return dirty;
	}
	
	/**
	 * Permet de rendre obsolete (ou a jour) le modele (pour demande une maj ou sinigifer une maj)
	 * @param dirty
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public Model getModel()
	{
		return model;
	}
}