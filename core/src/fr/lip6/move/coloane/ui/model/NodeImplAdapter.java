package fr.lip6.move.coloane.ui.model;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Node;
import fr.lip6.move.coloane.main.Translate;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * Description de l'adapteur pour un noeud du modele.
 * Le noeud adapte est generique.
 * L'adapteur fourni les mehodes et structures utiles pour GEF.
 * Cet adapteur doit gerer la coherence entre le noeud generique et le noeud augemente.
 * @see INodeImpl
 */

public class NodeImplAdapter extends AbstractModelElement implements INodeImpl, IElement {

	/** Le noeud generique */
	private INode node;

	/** Information de representation graphique */
	private INodeGraphicInfo graphicInfo;

	/** Element de base du formalisme associe au noeud */
	private ElementBase elementBase;

	/** Liste des arcs sortants */
	private List<IArcImpl> sourceArcs = new ArrayList<IArcImpl>();

	/** Liste des arcs entrants */
	private List<IArcImpl> targetArcs = new ArrayList<IArcImpl>();

	/** Le modele adapte */
	private IModelImpl modelAdapter;

	/**
	 * Creation d'un adaptateur pour un noeud
	 * @param node le noeud generique a adapter pour le module
	 * @param base L'element de base du formalisme
	 */
	public NodeImplAdapter(INode n, ElementBase b) {
		super();

		this.elementBase = b;	// Element de base du formalisme
		this.node = n;			// Le noeud generique

		// Les informations graphique sur le noeud
		this.graphicInfo = new NodeGraphicInfo(this);
		this.graphicInfo.setLocation(this.node.getXPosition(), this.node.getYPosition());

		// Instancier les attributs
		setProperties(this.node);
	}

	/**
	 * Creation d'un noeud a partir d'un element de formalisme
	 * @param base Element de base du formalisme
	 */
	public NodeImplAdapter(ElementBase base) {
		super();

		// Element de base du formalisme
		this.elementBase = base;

		// Le noeud generique avec un type et un id unique
		this.node = new Node(base.getName());

		// Le information graphique sur le noeud
		this.graphicInfo = new NodeGraphicInfo(this);
		this.graphicInfo.setLocation(this.node.getXPosition(), this.node.getYPosition());

		// Instancier les attributs
		setProperties();
	}


	/**
	 * Creation des attributs generique prevus par le formalisme
	 * Creation des attributs adaptes correspondant a ces attributs generiques
	 * Tous les attributs sont initialises aux valeurs par defaut prevus par le formalisme
	 */
	private void setProperties() {
		this.clearProperties();

		// Creation de tous les attributs prevus par le formalisme
		Iterator iterator = this.elementBase.getListOfAttribute().iterator();

		while (iterator.hasNext()) {
			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();

			/* Creation de l'attribut generique */
			IAttribute attribute = new Attribute(attributeFormalism.getName(), new String(attributeFormalism.getDefaultValue()), node.getId());
			this.node.addAttribute(attribute);

			/* Creation de l'attribut adapte */
			IAttributeImpl attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);

			/* Ajout de cet attribut dans la liste des propriete pour la vue GEF */
			this.addProperty(String.valueOf(attributeAdapter.getId()), attributeAdapter);
		}
	}


	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele generique)
	 * Creation des attribut generiques manquants et attributs adaptes correspondants
	 * Cela peut etre utile lorsq'un modele est lu depuis un fichier.
	 * @param node Le noeud generique qui vient d'etre augemente
	 */
	private void setProperties(INode n) {

		// Parcours de tous les attributs du formalisme
		Iterator iterator = this.elementBase.getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			IAttributeImpl attributeAdapter = null;
			IAttribute attribute = null;

			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();

			// On parcours tous les attributs deja definis dans notre noeud generique
			// On cherche l'attribut dans notre noeud generique qui correspond a l'attibut prevu par le formalisme (courant)
			boolean find = false;
			for (int i = 0; (i < n.getListOfAttrSize()) && !find; i++) {

				// Si l'attribut du formalisme est bien decrit dans notre modele... On cree l'adapteur
				// Pas besoin de creer un nouvel attribut genrique dans le modele !
				attribute = n.getNthAttr(i);
				if (attributeFormalism.getName().equalsIgnoreCase(attribute.getName())) {
					attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);
					find = true;
				}
			}

			// Si aucun attribut dans notre noeud generique ne correspond a celui du formalisme... alors notre noeud n'est pas complet
			// Il faut donc creer un attribut et un adapteur pour cet attribut du formalisme
			if (!find) {
				attribute = new Attribute(attributeFormalism.getName(), new String(attributeFormalism.getDefaultValue()), 1);
				attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);
				this.node.addAttribute(attribute);
			}

			// Augmente la liste des proprietes pour le modele (fenetre properties de la vue)
			this.addProperty(String.valueOf(attributeAdapter.getId()), attributeAdapter);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getId()
	 */
	public final int getId() {
		return this.getGenericNode().getId();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getGraphicInfo()
	 */
	public final INodeGraphicInfo getGraphicInfo() {
		return this.graphicInfo;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#addInputArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public final void addInputArc(IArcImpl arcAdapter) throws BuildException {
		if ((arcAdapter.getGenericArc() != null) && (arcAdapter.getTarget() == this)) {
			this.targetArcs.add(arcAdapter);
			this.node.addInputArc(arcAdapter.getGenericArc());
			firePropertyChange(NodeImplAdapter.TARGET_ARCS_PROP, null, arcAdapter);
		} else {
			throw new BuildException(Translate.getString("ui.model.NodeImplAdapter.0")); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#addOutputArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public final void addOutputArc(IArcImpl arcAdapter) throws BuildException {
		if ((arcAdapter.getGenericArc() != null) && (arcAdapter.getSource() == this)) {
			this.sourceArcs.add(arcAdapter);
			this.node.addOutputArc(arcAdapter.getGenericArc());
			firePropertyChange(NodeImplAdapter.SOURCE_ARCS_PROP, null, arcAdapter);
		} else {
			throw new BuildException(Translate.getString("ui.model.NodeImplAdapter.1")); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#removeArc(fr.lip6.move.coloane.ui.model.IArcImpl)
	 */
	public final void removeArc(IArcImpl arcAdapter) throws ModelException {
		if (arcAdapter.getSource() == this) {
			this.sourceArcs.remove(arcAdapter);
			this.node.removeOutputArc(arcAdapter.getGenericArc());
			firePropertyChange(NodeImplAdapter.SOURCE_ARCS_PROP, null, arcAdapter);
		}

		if (arcAdapter.getTarget() == this) {
			this.targetArcs.remove(arcAdapter);
			this.node.removeInputArc(arcAdapter.getGenericArc());
			firePropertyChange(NodeImplAdapter.TARGET_ARCS_PROP, null, arcAdapter);
		}
	}

	/**
	 * Retourne la liste des attributs qui peuvent etre affiches sur l'editeur
	 * @return Le liste des attributs
	 */
	private List<IAttributeImpl> getDrawableAttributes() {
		List<IAttributeImpl> list = new ArrayList<IAttributeImpl>();
		Iterator iterator = this.getProperties().values().iterator();
		while (iterator.hasNext()) {
			IAttributeImpl att = (IAttributeImpl) iterator.next();
			if (!(att.getValue().equals(att.getDefaultValue())) && att.isDrawable()) {
				list.add(att);
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#setAttributesSelected(boolean, boolean)
	 */
	public final void setAttributesSelected(boolean light, boolean state) {
		List<IAttributeImpl> list = this.getDrawableAttributes();
		for (IAttributeImpl att : list) {
			att.setSelect(light, state);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#updateAttributesPosition(int, int)
	 */
	public final void updateAttributesPosition(int deltaX, int deltaY) {
		List<IAttributeImpl> list = this.getDrawableAttributes();
		for (IAttributeImpl att : list) {
			Point loc = att.getGraphicInfo().getLocation();
			att.getGraphicInfo().setLocation(loc.x - deltaX, loc.y - deltaY);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#updateArcAttributesPosition()
	 */
	public final void updateArcAttributesPosition() {

		// Parcours des arcs sortants
		for (IArcImpl arc : this.sourceArcs) {
			arc.updateAttributesPosition();
		}

		// Parcours des arcs entrants
		for (IArcImpl arc : this.targetArcs) {
			arc.updateAttributesPosition();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#setSpecial()
	 */
	public final void setSpecial(boolean state) {
		if (state) {
			firePropertyChange(INodeImpl.SPECIAL_PROP, null, null);
		} else {
			firePropertyChange(INodeImpl.UNSPECIAL_PROP, null, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#setSelect(boolean)
	 */
	public final void setSelect(boolean state) {
		if (state) {
			firePropertyChange(INodeImpl.SELECT_PROP, null, null);
		} else {
			firePropertyChange(INodeImpl.UNSELECT_PROP, null, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getSourceArcs()
	 */
	public final List<IArcImpl> getSourceArcs() {
		return new ArrayList<IArcImpl>(sourceArcs);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getTargetArcs()
	 */
	public final List<IArcImpl> getTargetArcs() {
		return new ArrayList<IArcImpl>(targetArcs);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getGenericNode()
	 */
	public final INode getGenericNode() {
		return node;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getElementBase()
	 */
	public final ElementBase getElementBase() {
		return elementBase;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getModelAdapter()
	 */
	public final IModelImpl getModelAdapter() {
		return modelAdapter;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getAttributes()
	 */
	public final List<IElement> getAttributes() {
		List<IElement> list = new ArrayList<IElement>();

		// Ajout des attributs "personnels" du noeud
		List<IAttributeImpl> attributes  = this.getDrawableAttributes();
		for (IAttributeImpl a : attributes) {
			list.add((IElement) a);
		}

		// On doit ajouter tous les attributs des arcs sourtants
		for (IArcImpl arc : this.sourceArcs) {
			list.addAll(arc.getAttributes());
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#setModelAdapter(fr.lip6.move.coloane.ui.model.IModelImpl)
	 */
	public final void setModelAdapter(IModelImpl model) {
		this.modelAdapter = model;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getNodeAttributeValue(java.lang.String)
	 */
	public final String getNodeAttributeValue(String attribute) {
		String valeur = ""; //$NON-NLS-1$
		for (int i = 0; i < this.node.getListOfAttrSize(); i++) {
			if (this.node.getNthAttr(i).getName().equalsIgnoreCase(attribute)) {
				valeur = this.node.getNthAttr(i).getValue();
				break;
			}
		}
		return valeur;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.INodeImpl#getContextMenus()
	 */
	public final Collection getContextMenus() {
		return null;
	}
}
