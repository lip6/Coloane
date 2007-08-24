package fr.lip6.move.coloane.ui.model;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.main.Translate;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Adaptateur pour le modele generique. Permet d'implementer les interfaces
 * necessaires pour GEF et le MVC de Coloane Cet adapteur doit gerer la
 * coherence entre le modele augmente et le modele generique
 *
 * @see fr.lip6.move.coloane.interfaces.model.Model
 */
public class ModelImplAdapter extends AbstractModelElement implements IModelImpl, IElement {

	/** Modele generique sur lequel s'applique cet adaptateur */
	private IModel genericModel;

	/** Le formalisme associe a ce modele */
	private Formalism formalism;

	/** La liste de INodeImpl. */
	private List<IElement> children = new ArrayList<IElement>();

	/** Date de derniere modification */
	private int date;

	/** Indicateur de construction */
	private boolean buildingStatus = true;

	/** Etat du modele par rapport a FK (true -> pas a jour) */
	private boolean dirty = true;

	/**
	 * Construction d'un modele pour le formalisme et un pour un modele deja existant
	 * <ul>
	 *   <li>Nouveau fichier : Nouveau modele</li>
	 *   <li>Lu depuis un fichier ouvert</li>
	 *   <li>Import</li>
	 * </ul>
	 *
	 * @param genericModel Le modele existant
	 * @param formalism Le formalisme du modele
	 */
	public ModelImplAdapter(IModel model, Formalism f) {
		super();

		/* Modele en cours de construction */
		this.buildingStatus = true;

		this.date = (int) System.currentTimeMillis();

		this.genericModel = model;
		this.formalism = f;

		/* Creation de tous les adapteurs */
		try {
			this.setModelAdapters();
		} catch (Exception e) {
			System.out.println("Erreur lors de la construction du modele");
		}

		/* Ajout de tous les attributs deja indiques dans le modele */
		/* Ainsi que tous les attributs qui sont decrits dans le formalisme */
		this.setProperties(model);
	}

	/**
	 * Methode implementant les adaptateurs, noeuds et arcs pour le modele Cette
	 * methode sert a creer la premier coherence entre le modele generique et le
	 * modele augmente Les noeuds du cote generique existent deja... Il faut
	 * donc creer les noeuds du cote augemente
	 */
	private void setModelAdapters() throws Exception {

		// Creation de tous les Node du modele augmente
		for (int i = 0; i < this.genericModel.getListOfNodeSize(); i++) {
			INode currentNode = this.genericModel.getNthNode(i);
			INodeImpl node = new NodeImplAdapter(currentNode, (ElementBase) this.formalism.getNodeFormalism(currentNode.getNodeType()));
			node.setModelAdapter(this);
			this.children.add((IElement) node);
		}

		// Creation de tous les Arcs du modele augmente
		for (int j = 0; j < this.genericModel.getListOfArcSize(); j++) {
			IArc currentArc = this.genericModel.getNthArc(j);
			INodeImpl target = null;
			INodeImpl source = null;

			// Pour chaque enfant, on cherche si l'arc est source ou destination
			Iterator iterator = this.children.iterator();
			boolean findSource = false;
			boolean findTarget = false;

			// Parcours de la liste precedemment cree pour trouver le noeud source et cible
			while ((iterator.hasNext()) && ((!findSource) || (!findTarget))) {

				INodeImpl currentNode = (INodeImpl) iterator.next();

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
				// Creation de l'Arc adapter (Arc dans le modele augmente)
				IArcImpl arc = new ArcImplAdapter(currentArc, source, target, this.formalism.getArcFormalism(currentArc.getArcType()));
				arc.setModelAdapter(this);
				this.addArc(arc);
			} else {
				throw new Exception(Translate.getString("ui.model.ModelImplAdapter.1")); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele
	 * generique) Creation des attribut generiques manquants et attributs
	 * adaptes correspondants Cela peut �tre utile lorsq'un modele est lu depuis
	 * un fichier.
	 *
	 * @param genericModel Le modele generique qui vient d'etre augemente
	 */
	private void setProperties(IModel m) {

		// Parcours de tous les attributs prevus par le formalisme
		Iterator iterator = this.getFormalism().getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			IAttributeImpl attributeAdapter = null;
			IAttribute attribute = null;

			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();

			// On parcours tous les attributs deja definis dans notre modele generique
			// On cherche l'attribut dans notre modele generique qui correspond a l'attibut prevu par le formalisme (courant)
			boolean find = false;
			for (int i = 0; (i < m.getListOfAttrSize()) && !find; i++) {

				// Si l'attribut du formalisme est bien decrit dans notre modele... On cree l'adapteur
				// Pas besoin de creer un nouvel attribut dans le modele !
				attribute = m.getNthAttr(i);

				if (attributeFormalism.getName().equalsIgnoreCase(attribute.getName())) {
					attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);
					find = true;
				}
			}

			// Si aucun attribut dans notre modele ne correspond a celui du
			// formalisme... alors notre modele n'est pas complet
			// Il faut donc creer un attribut generique et un adapteur pour cet attribut du formalisme
			if (!find) {
				attribute = new Attribute(attributeFormalism.getName(), new String(attributeFormalism.getDefaultValue()), 1);
				attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);
				this.genericModel.addAttribute(attribute);
			}

			// Augmente la liste des proprietes pour le modele (fenetre
			// properties de la vue)
			this.addProperty(attributeAdapter.getId(), attributeAdapter);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#addNode(fr.lip6.move.coloane.ui.model.INodeImpl)
	 */
	public final void addNode(INodeImpl child) throws BuildException {
		if (child != null) {

			// On ajoute le nouveau fils au modele generique
			try {
				this.genericModel.addNode(child.getGenericNode());
			} catch (ModelException e) {
				throw new BuildException(Translate.getString("ui.model.ModelImplAdapter.2")); //$NON-NLS-1$
			}

			// On ajoute le noeud augmente aux fils du modele augemente
			this.children.add((IElement) child);

			// Evenement pour demander le rafraichissement du modele
			firePropertyChange(NODE_ADDED_PROP, null, child);
		} else {
			throw new BuildException(Translate.getString("ui.model.ModelImplAdapter.3")); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#removeNode(fr.lip6.move.coloane.ui.model.INodeImpl)
	 */
	public final void removeNode(INodeImpl child) throws BuildException {
		if (child != null) {

			// Enleve un noeud au modele generique
			try {
				this.genericModel.removeNode(child.getGenericNode());
			} catch (ModelException e) {
				e.printStackTrace();
			}

			// Suppression de tous les arcs entrants
			for (IArcImpl arcIn : child.getTargetArcs()) {
				this.removeArc(arcIn);
			}

			// Suppression de tous les arcs sortants
			for (IArcImpl arcOut : child.getSourceArcs()) {
				this.removeArc(arcOut);
			}

			// Enleve le noeud au modele augmente
			this.children.remove(child);

			// Demande la mise a jour de l'affichage
			firePropertyChange(NODE_REMOVED_PROP, null, child);
		} else {
			throw new BuildException(Translate.getString("ui.model.ModelImplAdapter.4")); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#addArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public final void addArc(IArcImpl child) throws BuildException {
		try {
			// Ajout de l'arc au modele generique
			this.genericModel.addArc(child.getGenericArc());
		} catch (ModelException e) {
			throw new BuildException(Translate.getString("ui.model.ModelImplAdapter.5")); //$NON-NLS-1$
		}

		// Connexion
		child.getSource().addOutputArc(child);
		child.getTarget().addInputArc(child);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#removeArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public final void removeArc(IArcImpl child) {
		try {
			this.genericModel.removeArc(child.getGenericArc());
		} catch (ModelException e) {
			e.printStackTrace();
		}

		// Deconnexion
		try {
			child.getSource().removeArc(child);
			child.getTarget().removeArc(child);
		} catch (ModelException e) {
			System.err.println("Cannot remove the arc");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getChildren()
	 */
	public final List<IElement> getChildren() {
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
	public final List<IElement> getAttributes() {
		List<IElement> attrList = new ArrayList<IElement>();
		Iterator iterator = this.getProperties().values().iterator();
		while (iterator.hasNext()) {
			IAttributeImpl att = (IAttributeImpl) iterator.next();
			if (!(att.getValue().equals(att.getDefaultValue())) && att.isDrawable()) {
				attrList.add((IElement) att);
			}
		}
		return attrList;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#annouceAttribute()
	 */
	public final void annouceAttribute() {
		firePropertyChange(ATTRIBUTE_ADDED_PROP, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getGenericModel()
	 */
	public final IModel getGenericModel() {
		return this.genericModel;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getFormalism()
	 */
	public final Formalism getFormalism() {
		return formalism;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#modifyDate()
	 */
	public final int modifyDate() {
		// Le changement de date doit etre effectif si et seulement si le modele
		// n'est pas en construction
		if (!this.buildingStatus) {
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
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getDate()
	 */
	public final int getDate() {
		return date;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#isDirty()
	 */
	public final boolean isDirty() {
		return dirty;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#setDirty(boolean)
	 */
	public final void setDirty(boolean state) {
		this.dirty = state;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#setEndBuilding()
	 */
	public final void setEndBuilding() {
		this.buildingStatus = false;
	}

	/**
	 * Mise en valeur de noeud
	 *
	 * @param highlight A mettre en valeur
	 * @param unhighlight A remettre en position initiale
	 */
	public final void highlightNode(String highlight, String unhighlight) {
		String[] tohigh = highlight.split(","); //$NON-NLS-1$
		String[] tounhigh = unhighlight.split(","); //$NON-NLS-1$

		for (IElement nodee : this.children) {
			INodeImpl node = (INodeImpl) nodee;
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

	public final void switchoffNodes() {
		for (IElement node : this.children) {
			((INodeImpl) node).setSpecial(false);
		}
	}

	/***************************************************************************
	 * DUMP
	 **************************************************************************/
	public final void dumpModel() {
		System.err.println("--> Debut du dump !"); //$NON-NLS-1$
		System.out.println("Liste des noeud :"); //$NON-NLS-1$
		for (int i = 0; i < children.size(); i++) {
			INodeImpl n = (INodeImpl) children.get(i);
			System.out.println(i + ": " + n.getId() + " (" //$NON-NLS-1$ //$NON-NLS-2$
					+ n.getGenericNode().getNodeType() + ")"); //$NON-NLS-1$
		}
	}

	public final IModelImpl getModelAdapter() {
		return this;
	}
}
