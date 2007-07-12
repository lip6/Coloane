package fr.lip6.move.coloane.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.model.Attribute;
import fr.lip6.move.coloane.model.Model;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;

/**
 * Adaptateur pour le modele generique. Permet d'implementer les interfaces
 * necessaires pour GEF et le MVC de Coloane Cet adapteur doit gerer la
 * coherence entre le modele augmente et le modele generique
 * 
 * @see fr.lip6.move.coloane.interfaces.model.Model
 */
public class ModelImplAdapter extends AbstractModelElement implements IModelImpl, IElement, Serializable {

	/** Id pour la serialisation. */
	private static final long serialVersionUID = 1L;

	/** Modele generique sur lequel s'applique cet adaptateur */
	private IModel model;

	/** Le formalisme associe a ce modele */
	private Formalism formalism;

	/** La liste de INodeImpl. */
	private List<IElement> children = new ArrayList<IElement>();

	/** Date de derniere modification */
	/* TODO : Verifier la necessite de cette initialisation */
	private int date = (int) System.currentTimeMillis();

	/** Indicateur de construction */
	private boolean buildingStatus = true;

	/** Etat du modele par rapport a FK (true -> pas a jour) */
	private boolean dirty = true;

	/**
	 * Construction d'un modele pour un formalisme donne Le modele construit est
	 * vide
	 * 
	 * @param formalism
	 *            Le formalisme du nouveau modele
	 */
	public ModelImplAdapter(Formalism formalism) {
		super();
		this.model = new Model();
		this.model.setFormalism(formalism.getName());
		this.formalism = formalism;

		/*
		 * Construction totale du modele (tous les attributs du modeles doivent
		 * etre construits
		 */
		this.setProperties();
		this.buildingStatus = true;
	}

	/**
	 * Construction d'un modele pour le formalisme et un pour un modele deja
	 * existant -> Lu depuis un fichier par exemple
	 * 
	 * @param model
	 *            Le modele existant
	 * @param formalism
	 *            Le formalisme du modele
	 */
	public ModelImplAdapter(IModel model, Formalism formalism) {
		super();

		try {
			this.model = model;
			this.buildingStatus = true;

			/*
			 * On met a jour si necessaire le nom du formalisme contenu dans le
			 * modele generique
			 */
			/*
			 * Des divergences peuvent apparaitre pour certains vieux modeles
			 * edites par Macao
			 */
			/*
			 * Les informations etaient alors stockee dans la ressource du
			 * fichier non supportee maintenant
			 */
			if (this.model.getFormalism() == formalism.getName()) {
				this.model.setFormalism(formalism.getName());
			}
			this.formalism = formalism;

			/* Creation de tous les adapteurs */
			this.setModelAdapters();

			/* Ajout de tous les attributs deja indiques dans le modele */
			/* Ainsi que tous les attributs qui sont decrits dans le formalisme */
			this.setProperties(model);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erreur lors de la construction du modele");
		}
	}

	/**
	 * Methode implementant les adaptateurs, noeuds et arcs pour le modele Cette
	 * methode sert a creer la premier coherence entre le modele generique et le
	 * modele augmente Les noeuds du cote generique existent deja... Il faut
	 * donc creer les noeuds du cote augemente
	 */
	private void setModelAdapters() throws Exception {

		// Creation de tous les Node du modele augmente
		for (int i = 0; i < this.model.getListOfNodeSize(); i++) {
			INode currentNode = this.model.getNthNode(i);
			INodeImpl node = new NodeImplAdapter(currentNode,
					(ElementBase) this.formalism.string2Node(currentNode
							.getNodeType()));
			node.setModelAdapter(this);
			this.children.add((IElement)node);
		}

		// Creation de tous les Arcs du modele augmente
		for (int j = 0; j < this.model.getListOfArcSize(); j++) {
			IArc currentArc = this.model.getNthArc(j);
			INodeImpl target = null;
			INodeImpl source = null;

			// Pour chaque enfant, on cherche si l'arc est source ou destination
			Iterator iterator = this.children.iterator();
			boolean findSource = false;
			boolean findTarget = false;

			// Parcours de la liste precedemment cree pour trouver le noeud
			// source et cible
			while ((iterator.hasNext()) && ((!findSource) || (!findTarget))) {

				INodeImpl currentNode = (INodeImpl) iterator.next();

				if (currentArc.getEndingNode() == currentNode.getGenericNode()) {
					target = currentNode;
					findTarget = true;
				} else if (currentArc.getStartingNode() == currentNode
						.getGenericNode()) {
					source = currentNode;
					findSource = true;
				}
			}

			// Un arc a forcement une source et une destination... sinon
			// probleme
			if ((target != null) && (source != null)) {
				// Creation de l'Arc adapter (Arc dans le modele augmente)
				IArcImpl arc = new ArcImplAdapter(currentArc, source, target,
						this.formalism.string2Arc(currentArc.getArcType()));
				arc.setModelAdapter(this);
			} else {
				throw new Exception("Source ou destination de l'arc manquante");
			}
		}
	}

	/**
	 * Creation des attributs generique prevus par le formalisme Creation des
	 * attributs adapte correspondant a ces attributs generiques Tous les
	 * attributs sont initialises aux valeurs par defaut prevus par le
	 * formalisme
	 */
	private void setProperties() {
		this.properties.clear();

		// Creation de tous les attributs prevus par le formalisme
		Iterator iterator = this.getFormalism().getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator
					.next();

			// Creation de l'attribut dans le modele
			IAttribute attribute = new Attribute(attributeFormalism.getName(),
					new String(attributeFormalism.getDefaultValue()), 1);
			this.model.addAttribute(attribute);
			
			// Creation de l'adapteur associe a l'attribut generique precedemment cree
			IAttributeImpl attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism,this);
			
			// Augmente la liste des proprietes (fenetre properties de la vue)
			this.properties.put(attributeAdapter.getId(), attributeAdapter);
		}
	}

	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele
	 * generique) Creation des attribut generiques manquants et attributs
	 * adaptes correspondants Cela peut �tre utile lorsq'un modele est lu depuis
	 * un fichier.
	 * 
	 * @param model
	 *            Le modele generique qui vient d'etre augemente
	 */
	private void setProperties(IModel model) {

		// Parcours de tous les attributs prevus par le formalisme
		Iterator iterator = this.getFormalism().getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			IAttributeImpl attributeAdapter = null;
			IAttribute attribute = null;

			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator
					.next();

			// On parcours tous les attributs deja definis dans notre modele
			// generique
			// On cherche l'attribut dans notre modele generique qui correspond
			// a l'attibut prevu par le formalisme (courant)
			boolean find = false;
			for (int i = 0; (i < model.getListOfAttrSize()) && !find; i++) {

				// Si l'attribut du formalisme est bien decrit dans notre
				// modele... On cree l'adapteur
				// Pas besoin de creer un nouvel attribut dans le modele !
				attribute = model.getNthAttr(i);

				if (attributeFormalism.getName().equalsIgnoreCase(attribute.getName())) {
					attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism,this);
					find = true;
				}
			}

			// Si aucun attribut dans notre modele ne correspond a celui du
			// formalisme... alors notre modele n'est pas complet
			// Il faut donc creer un attribut generique et un adapteur pour cet
			// attribut du formalisme
			if (!find) {
				attribute = new Attribute(attributeFormalism.getName(), new String(attributeFormalism.getDefaultValue()), 1);
				attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism,this);
				this.model.addAttribute(attribute);
			}

			// Augmente la liste des proprietes pour le modele (fenetre
			// properties de la vue)
			this.properties.put(attributeAdapter.getId(), attributeAdapter);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#addNode(fr.lip6.move.coloane.ui.model.INodeImpl)
	 */
	public void addNode(INodeImpl child) throws BuildException {
		if (child != null) {

			// On ajoute le nouveau fils au modele generique
			try {
				this.model.addNode(child.getGenericNode());
			} catch (SyntaxErrorException e) {
				throw new BuildException("Ajout impossible: L'identifiant du noeud existe deja");
			}

			// On ajoute le noeud augmente aux fils du modele augemente
			this.children.add((IElement)child);
			
			// Evenement pour demander le rafraichissement du modele
			firePropertyChange(NODE_ADDED_PROP, null, child);
		} else {
			throw new BuildException(
					"Erreur lors de l'ajout d'un noeud au modele");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#removeNode(fr.lip6.move.coloane.ui.model.INodeImpl)
	 */
	public void removeNode(INodeImpl child) throws BuildException {
		if (child != null) {
			// Enleve un noeud au modele generique
			this.model.removeNode(child.getGenericNode());
			// Enleve le noeud au modele augmente
			this.children.remove(child);
			firePropertyChange(NODE_REMOVED_PROP, null, child);
		} else {
			throw new BuildException(
					"Erreurs lors de la suppression d'un noeud du modele");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#addArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public void addArc(IArcImpl child) throws BuildException {
		// Ajout d'un arc au modele
		try {
			this.model.addArc(child.getGenericArc());
		} catch (SyntaxErrorException e) {
			throw new BuildException(
					"Ajout impossible: L'identifiant du noeud existe deja");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#removeArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public void removeArc(IArcImpl child) {
		this.model.removeArc(child.getGenericArc());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getChildren()
	 */
	public List<IElement> getChildren() {
		List<IElement> listNodes = this.children;
		List<IElement> listAttributes = new ArrayList<IElement>();
		for (IElement elt : listNodes) {
			// TODO : Deux appels a elt.getAttributes : Un peu fort en chocolat !
			if (elt.getAttributes() != null) {
				listAttributes.addAll(elt.getAttributes());
			}
		}
		List<IElement> list = new ArrayList<IElement>();
		list.addAll(listNodes);
		list.addAll(listAttributes);
		list.addAll(this.getAttributes());
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getAttributes()
	 */
	public List<IElement> getAttributes() {
    	List<IElement> attrList = new ArrayList<IElement>();
    	Iterator iterator = this.properties.values().iterator();    	
    	while (iterator.hasNext()) {
    		IAttributeImpl att = (IAttributeImpl)iterator.next();
    		if (!(att.getValue().equals(att.getDefaultValue())) && att.isDrawable()) {
        		attrList.add((IElement)att);
    		}
    	}			
    	return attrList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#annouceAttribute()
	 */
	public void annouceAttribute() {
		firePropertyChange(ATTRIBUTE_ADDED_PROP, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getGenericModel()
	 */
	public IModel getGenericModel() {
		return this.model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getFormalism()
	 */
	public Formalism getFormalism() {
		return formalism;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#modifyDate()
	 */
	public int modifyDate() {
		// Le changement de date doit etre effectif si et seulement si le modele
		// n'est pas en construction
		if (this.buildingStatus == false) {
			date = (int) System.currentTimeMillis();

			// Si le modele n'etait pas marque comme sale, on le marque
			if (!dirty) {
				dirty = true;
				return date;
			}
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getDate()
	 */
	public int getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.AbstractModelElement#setPropertyValue(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#isDirty()
	 */
	public boolean isDirty() {
		return dirty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#setDirty(boolean)
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#setBeginBuilding()
	 */
	public void setBeginBuilding() {
		this.buildingStatus = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#setEndBuilding()
	 */
	public void setEndBuilding() {
		this.buildingStatus = false;
	}

	/**
	 * Mise en valeur de noeud
	 * 
	 * @param highlight
	 *            A mettre en valeur
	 * @param unhighlight
	 *            A remettre en position initiale
	 */
	public void highlightNode(String highlight, String unhighlight) {
		String[] tohigh = highlight.split(",");
		String[] tounhigh = unhighlight.split(",");

		for (IElement nodee : this.children) {
			INodeImpl node = (INodeImpl)nodee;
			for (String u : tounhigh) {
				if (node.getId() == Integer.valueOf(u)) {
					node.setSpecial(false);
				}
			}

			for (String h : tohigh) {
				if (node.getId() == Integer.valueOf(h)) {
					node.setSpecial(true);
				}
			}
		}
	}

	public void switchoffNodes() {
		for (IElement node : this.children) {
			((INodeImpl)node).setSpecial(false);
		}
	}

	public IModelImpl getModelAdapter() {
		return this;
	}
}