package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adaptateur pour le modele generique. Permet d'implementer les interfaces
 * necessaires pour GEF et le MVC de Coloane Cet adapteur doit gerer la
 * coherence entre le modele augmente et le modele generique
 *
 * @see fr.lip6.move.coloane.interfaces.model.Model
 */
public class ModelImplAdapter extends AbstractModelElement implements IModelImpl {

	/** Modele generique sur lequel s'applique cet adaptateur */
	private IModel genericModel;

	/** Le formalisme associe a ce modele */
	private Formalism formalism;

	/** La liste de INodeImpl. */
	private List<INodeImpl> nodes = new ArrayList<INodeImpl>();

	/** Date de derniere modification */
	private int date;

	/** Etat du modele par rapport a FK (true -> pas a jour) */
	private boolean dirty = false;

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
	public ModelImplAdapter(IModel model) throws BuildException {
		super();
		this.date = (int) System.currentTimeMillis();

		this.genericModel = model;
		this.formalism = Coloane.getDefault().getMotor().getFormalismManager().getFormalismByName(model.getFormalism());

		if (this.formalism == null) {
			Coloane.getLogger().warning("Erreur lors de la construction du modele : Aucun formalisme associe"); //$NON-NLS-1$
			throw new BuildException(Messages.ModelImplAdapter_9);
		}

		/* Creation de tous les adapteurs */
		try {
			this.setModelAdapters();
		} catch (BuildException e) {
			Coloane.getLogger().warning("Erreur lors de la construction du modele"); //$NON-NLS-1$
			throw e;
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
	private void setModelAdapters() throws BuildException {

		// Creation de tous les Node du modele augmente
		for (int i = 0; i < this.genericModel.getListOfNodeSize(); i++) {
			INode currentNode = this.genericModel.getNthNode(i);
			INodeImpl node = new NodeImplAdapter(currentNode, (ElementFormalism) this.formalism.getNodeFormalism(currentNode.getNodeType()));
			node.setModelAdapter(this);
			this.nodes.add(node);
		}

		// Creation de tous les Arcs du modele augmente
		for (int j = 0; j < this.genericModel.getListOfArcSize(); j++) {
			IArc currentArc = this.genericModel.getNthArc(j);
			INodeImpl target = null;
			INodeImpl source = null;

			// Pour chaque enfant, on cherche si l'arc est source ou destination
			boolean findSource = false;
			boolean findTarget = false;

			// Parcours de la liste precedemment cree pour trouver le noeud source et cible
			for (IElement currentNode : this.nodes) {
				if ((!findSource) || (!findTarget)) {
					INodeImpl cn = (INodeImpl) currentNode;

					if (currentArc.getEndingNode() == cn.getGenericNode()) {
						target = cn;
						findTarget = true;
					} else if (currentArc.getStartingNode() == cn.getGenericNode()) {
						source = cn;
						findSource = true;
					}
				}
			}

			// Un arc a forcement une source et une destination... sinon probleme
			if ((target != null) && (source != null)) {
				// Creation de l'Arc adapter (Arc dans le modele augmente)
				IArcImpl arc = new ArcImplAdapter(currentArc, source, target, this.formalism.getArcFormalism(currentArc.getArcType()));
				arc.setModelAdapter(this);
				this.addArc(arc);
			} else {
				throw new BuildException(Messages.ModelImplAdapter_1);
			}
		}
	}

	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele
	 * generique) Creation des attribut generiques manquants et attributs
	 * adaptes correspondants Cela peut etre utile lorsq'un modele est lu depuis
	 * un fichier.
	 *
	 * @param genericModel Le modele generique qui vient d'etre augemente
	 */
	private void setProperties(IModel m) {

		// Parcours de tous les attributs prevus par le formalisme
		for (AttributeFormalism attributeFormalism : this.getFormalism().getListOfAttribute()) {
			IAttributeImpl attributeAdapter = null;
			IAttribute attribute = null;

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

			// Augmente la liste des proprietes pour le modele (fenetre properties de la vue)
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
				throw new BuildException(Messages.ModelImplAdapter_2 + child.getId());
			}

			// On ajoute le noeud augmente aux fils du modele augemente
			this.nodes.add(child);

			// Evenement pour demander le rafraichissement du modele
			firePropertyChange(NODE_ADDED_PROP, null, child);
		} else {
			throw new BuildException(Messages.ModelImplAdapter_3);
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
				throw new BuildException(Messages.ModelImplAdapter_4);
			}

			// Suppression de tous les arcs entrants
			for (IArcImpl arcIn : child.getTargetArcs()) { this.removeArc(arcIn); }

			// Suppression de tous les arcs sortants
			for (IArcImpl arcOut : child.getSourceArcs()) { this.removeArc(arcOut); }

			// Enleve le noeud au modele augmente
			this.nodes.remove(child);

			// Demande la mise a jour de l'affichage
			firePropertyChange(NODE_REMOVED_PROP, null, child);
		} else {
			throw new BuildException(Messages.ModelImplAdapter_5);
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
			throw new BuildException(Messages.ModelImplAdapter_6 + child.getId());
		}

		// Connexion
		child.getSource().addOutputArc(child);
		child.getTarget().addInputArc(child);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#removeArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public final void removeArc(IArcImpl child) throws BuildException {
		try {
			this.genericModel.removeArc(child.getGenericArc());
		} catch (ModelException e) {
			throw new BuildException(Messages.ModelImplAdapter_7);
		}

		// Deconnexion
		try {
			child.getSource().removeArc(child);
			child.getTarget().removeArc(child);
		} catch (ModelException e) {
			throw new BuildException(Messages.ModelImplAdapter_8);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getAttributes()
	 */
	public final List<IAttributeImpl> getAttributes() {
		List<IAttributeImpl> attrList = new ArrayList<IAttributeImpl>();
		for (IAttributeImpl att : this.getProperties().values()) {
			if (!(att.getValue().equals(att.getDefaultValue())) && att.isDrawable()) {
				attrList.add(att);
			}
		}
		return attrList;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#annouceAttribute()
	 */
	public final void announceAttribute() {
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
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#getFormalism()
	 */
	public final Formalism getFormalism() {
		return formalism;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IModelImpl#modifyDate()
	 */
	public final int modifyDate() {
		Coloane.getLogger().finest("Demande de mise a jour de la date du modele"); //$NON-NLS-1$
		date = (int) System.currentTimeMillis();
		// Si le modele n'etait pas marque comme sale, on le marque
		if (!dirty) {
			setDirty(true);
			return date;
		// Sinon le modele etait deja sale (on a juste mis a jour la date)
		} else {
			return 0;
		}
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
		if (state) {
			Coloane.getLogger().fine("Le modele est maintenant considere comme : SALE"); //$NON-NLS-1$
		} else {
			Coloane.getLogger().fine("Le modele est maintenant considere comme : PROPRE"); //$NON-NLS-1$
		}
		this.dirty = state;
	}

	/**
	 * Retourne la liste des INodeImpl du modele ainsi que tous les IAttributeImpl du modele et des noeuds.<br>
	 * Cette liste est particulièrement destinee a GEF pour la construction des EditParts
	 * @return List de IElement
	 */
	public final List<IElement> getChildren() {
		List<IElement> listChildren = new ArrayList<IElement>();
		HashSet<IArcImpl> allArcs = new HashSet<IArcImpl>();

		// Ajout de tous les noeuds
		listChildren.addAll(this.nodes);

		// Ajout de tous les attributs des noeuds
		for (IElement elt : this.nodes) {
			if (elt.getAttributes() != null) { listChildren.addAll(elt.getAttributes()); }
			allArcs.addAll(((INodeImpl) elt).getAllArcs());
		}

		// ATTENTION : Les arcs ne doivent pas etre ajoutes a a liste !!!
		// Leurs attributs par contre doivent être ajoutés.

		// Recensement de tous les arcs du modele et de leurs attributs
		for (IElement elt : allArcs) {
			if (elt.getAttributes() != null) { listChildren.addAll(elt.getAttributes()); }
		}

		// Ajout des attributs du modele
		listChildren.addAll(this.getAttributes());

		return listChildren;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IModelImpl#getModelObjects()
	 */
	public final List<IElement> getModelObjects() {
		ArrayList<IElement> objectList = new ArrayList<IElement>();
		objectList.addAll(nodes);
		for (INodeImpl node : this.nodes) {
			objectList.addAll(node.getAllArcs());
		}
		return objectList;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IModelImpl#getModelObject(int)
	 */
	public final IElement getModelObject(int id) {
		for (IElement elt : this.getModelObjects()) {
			if (elt.getId() == id) {
				return elt;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IModelImpl#switchoffNodes()
	 */
	public final void switchoffNodes() {
		for (INodeImpl node : this.nodes) {
			node.setSpecial(false);
		}
	}

	/**
	 * Retourne le modele
	 * @return {@link IModelImpl}
	 */
	public final IModelImpl getModelAdapter() {
		return this;
	}

	/**
	 * Retourne l'ID du modele
	 * @return 1
	 */
	public final int getId() {
		return 1;
	}

	public final List<INodeImpl> getNodes() {
		return new ArrayList<INodeImpl>(nodes);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#getElementBase()
	 */
	public final ElementFormalism getElementBase() {
		return null;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#setAttributesSelected(boolean, boolean)
	 */
	public void setAttributesSelected(boolean light, boolean state) { }

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#setModelAdapter(fr.lip6.move.coloane.core.ui.model.IModelImpl)
	 */
	public void setModelAdapter(IModelImpl modelAdapter) { }

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#setSelect(boolean)
	 */
	public void setSelect(boolean state) {	}
	public final INodeImpl getNode(int id) {
		for (INodeImpl node : nodes) {
			if (node.getId() == id) {
				return node;
			}
		}
		return null;
	}

	public final Set<IArcImpl> getArcs() {
		HashSet<IArcImpl> arcs = new HashSet<IArcImpl>();
		for (INodeImpl node : nodes) {
			arcs.addAll(node.getSourceArcs());
			arcs.addAll(node.getTargetArcs());
		}
		return arcs;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#setSpecial(boolean)
	 */
	public void setSpecial(boolean state) {	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.ui.model.IElement#getAttributeValue(java.lang.String)
	 */
	public final String getAttributeValue(String attributeName) {
		for (int i = 0; i < this.genericModel.getListOfAttrSize(); i++) {
			if (this.genericModel.getNthAttr(i).getName().equalsIgnoreCase(attributeName)) {
				return this.genericModel.getNthAttr(i).getValue();
			}
		}
		return ""; //$NON-NLS-1$
	}
}
