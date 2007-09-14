package fr.lip6.move.coloane.core.ui.model;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalism.ElementBase;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.objects.IInflexPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;

/**
 * Description de l'adapteur pour un arc du modele.
 * L'arc adapte est generique. L'adapteur fourni les mehodes et structure utiles pour GEF.
 * Cet adapteur doit gerer la coherence entre l'arc generique et l'arc augemente.
 * @see IArcImpl
 */

public class ArcImplAdapter extends AbstractModelElement implements IArcImpl, IElement {

	/** Noeud source repris depuis l'arc generique */
	private INodeImpl source;

	/** Noeud cible repris depuis l'arc generique */
	private INodeImpl target;

	/** Arc generique a adapter */
	private IArc genericArc;

	/** Information graphique de l'arc */
	private IArcGraphicInfo graphicInfo;

	/** Element de base du formalisme associe au noeud */
	private ElementBase elementBase;

	/** Le modele augemente qui contient cet arc augemente */
	private IModelImpl modelAdapter;

	/**
	 * Constructeur <br>
	 * Creation d'un adaptateur pour un arc generique deja existant
	 * @param genericArc Arc generique pour l'adaptateur
	 * @param source Noeud source
	 * @param target Noeud cible
	 * @param base Element de base du formalisme
	 * @throws BuildException
	 */
	public ArcImplAdapter(IArc arc, INodeImpl arcSource, INodeImpl arcTarget, ElementBase base) throws BuildException {
		this.elementBase = base;
		this.genericArc = arc;
		this.source = arcSource;
		this.target = arcTarget;

		// Creation de la liste des attributs
		this.setProperties();

		// On indique a l'arc generique quels sont ces sources et cibles
		this.genericArc.setStartingNode(source.getGenericNode());
		this.genericArc.setEndingNode(target.getGenericNode());

		this.graphicInfo = new ArcGraphicInfo(this);
	}


	/**
	 * Constructeur<br>
	 * Creation d'un arc a partir des deux noeuds source et cible.
	 * @param source Noeud source
	 * @param target Noeud cible
	 * @param base Element de base du formalisme
	 * @throws BuildException
	 */
	public ArcImplAdapter(INodeImpl arcSource, INodeImpl arcTarget, ElementBase base) throws BuildException {
		this.elementBase = base;
		this.source = arcSource;
		this.target = arcTarget;

		// Creation de l'arc generique
		this.genericArc = new Arc(elementBase.getName());

		// Creation de la liste des attributs
		this.setProperties();

		// On indique a l'arc generique quels sont ces sources et cibles
		this.genericArc.setStartingNode(source.getGenericNode());
		this.genericArc.setEndingNode(target.getGenericNode());

		this.graphicInfo = new ArcGraphicInfo(this);
	}

	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele generique)
	 * Creation des attribut generiques manquants et attributs adaptes correspondants
	 * Cela peut etre utile lorsq'un modele est lu depuis un fichier.
	 * @param node Le noeud generique qui vient d'etre augemente
	 */
	private void setProperties() {

		// Parcours de tous les attributs du formalisme
		for (AttributeFormalism attributeFormalism : this.elementBase.getListOfAttribute()) {

			IAttributeImpl attributeAdapter = null;
			IAttribute attribute = null;

			// On parcours tous les attributs generique deja definis dans notre arc generique
			// On cherche l'attribut dans notre noeud generique qui correspond a l'attibut prevu par le formalisme (courant)
			boolean find = false;
			for (int i = 0; (i < this.genericArc.getListOfAttrSize()) && !find; i++) {

				// Si l'attribut du formalisme est bien decrit dans notre modele... On cree l'adapteur
				// Pas besoin de creer un nouvel attribut dans le modele !
				attribute = this.genericArc.getNthAttr(i);
				if (attributeFormalism.getName().equalsIgnoreCase(attribute.getName())) {
					attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);
					find = true;
				}
			}

			// Si aucun attribut generique dans notre modele ne correspond a celui du formalisme... alors notre modele n'est pas complet
			// Il faut donc creer un attribut generique et un adapteur pour cet attribut du formalisme
			if (!find) {
				attribute = new Attribute(attributeFormalism.getName(), new String(attributeFormalism.getDefaultValue()), 1);
				attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism, this);
				this.genericArc.addAttribute(attribute);
			}

			this.addProperty(String.valueOf(attributeAdapter.getId()), attributeAdapter);
		}
	}

	public final void reconnect(INodeImpl newSource, INodeImpl newTarget) {

		// Suppression du lien depuis les anciens noeuds
		try {
			this.source.removeArc(this);
			this.target.removeArc(this);
		} catch (ModelException e) {
			Coloane.getLogger().warning("Impossible de supprimer les references : (" + this.source.getId() + "," + this.target.getId() + ")");
		}

		// Nouvelles cible et source
		this.source = newSource;
		this.target = newTarget;

		// Creation des liens
		try {
			this.target.addInputArc(this);
			this.source.addOutputArc(this);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Impossible de creer les references : (" + this.source.getId() + "," + this.target.getId() + ")");
		}

		// On indique a l'arc generique quels sont ces sources et cibles
		this.genericArc.setStartingNode(source.getGenericNode());
		this.genericArc.setEndingNode(target.getGenericNode());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getId()
	 */
	public final int getId() {
		return this.getGenericArc().getId();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getGraphicInfo()
	 */
	public final IArcGraphicInfo getGraphicInfo() {
		return this.graphicInfo;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getElementBase()
	 */
	public final ElementBase getElementBase() {
		return elementBase;
	}

	/**
	 * Retourne la liste des attributs qui peuvent etre affiches sur l'editeur
	 * @return Le liste des attributs
	 */
	private List<IAttributeImpl> getDrawableAttributes() {
		List<IAttributeImpl> list = new ArrayList<IAttributeImpl>();
		for (IAttributeImpl att : this.getProperties().values()) {
			if (!(att.getValue().equals(att.getDefaultValue())) && att.isDrawable()) {
				list.add(att);
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#setAttributesSelected(boolean, boolean)
	 */
	public final void setAttributesSelected(boolean state) {
		List<IAttributeImpl> list = this.getDrawableAttributes();
		for (IAttributeImpl att : list) {
			att.setSelect(state);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#setSelect(boolean)
	 */
	public final void setSelect(boolean state) {
		if (state) {
			firePropertyChange(IArcImpl.SETSELECT_PROP, null, null);
		} else {
			firePropertyChange(IArcImpl.SETUNSELECT_PROP, null, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IElement#getAttributes()
	 */
	public final List<IElement> getAttributes() {
		List<IElement> list = new ArrayList<IElement>();

		// Ajout des attributs "personnels" du noeud
		List<IAttributeImpl> attributes  = this.getDrawableAttributes();
		for (IAttributeImpl a : attributes) {
			list.add((IElement) a);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getContextMenus()
	 */
	public final Collection getContextMenus() {
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getSource()
	 */
	public final INodeImpl getSource() {
		return source;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getTarget()
	 */
	public final INodeImpl getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getGenericArc()
	 */
	public final IArc getGenericArc() {
		return this.genericArc;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getFormalism()
	 */
	public final Formalism getFormalism() {
		return this.elementBase.getFormalism();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getModelAdapter()
	 */
	public final IModelImpl getModelAdapter() {
		return modelAdapter;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#setModelAdapter(fr.lip6.move.coloane.ui.model.IModelImpl)
	 */
	public final void setModelAdapter(IModelImpl model) {
		this.modelAdapter = model;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getArcValue()
	 */
	public final String getArcValue() {
		String valeur = ""; //$NON-NLS-1$
		for (int i = 0; i < this.genericArc.getListOfAttrSize(); i++) {
			if (this.genericArc.getNthAttr(i).getName().equalsIgnoreCase("valuation")) { //$NON-NLS-1$
				valeur = this.genericArc.getNthAttr(i).getValue();
				break;
			}
		}
		return valeur;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getArcLabel()
	 */
	public final String getArcLabel() {
		String valeur = ""; //$NON-NLS-1$
		for (int i = 0; i < this.genericArc.getListOfAttrSize(); i++) {
			if (this.genericArc.getNthAttr(i).getName().equalsIgnoreCase("label")) { //$NON-NLS-1$
				valeur = this.genericArc.getNthAttr(i).getValue();
				break;
			}
		}
		return valeur;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#addInflexPoint(org.eclipse.draw2d.geometry.Point, int)
	 */
	public final void addInflexPoint(Point p, int index) {
		try {
			this.genericArc.addPI(p.x, p.y, index);
			firePropertyChange(ArcImplAdapter.INFLEXPOINT_PROP, null, this);
		} catch (ModelException e) {
			Coloane.getLogger().warning("Impossible d'ajouter le point d'inflexion sur : " + this.genericArc.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#removeInflexPoint(int)
	 */
	public final void removeInflexPoint(int index) {
		try {
			this.genericArc.removePI(index);
			firePropertyChange(ArcImplAdapter.INFLEXPOINT_PROP, null, this);
		} catch (ModelException e) {
			Coloane.getLogger().warning("Impossible de supprimer le point d'inflexion sur : " + this.genericArc.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#modifyInflexPoint(int, org.eclipse.draw2d.geometry.Point)
	 */
	public final void modifyInflexPoint(int index, Point p) {
		try {
			this.genericArc.modifyPI(index, p.x, p.y);
			firePropertyChange(ArcImplAdapter.INFLEXPOINT_PROP, null, this);
		} catch (ModelException e) {
			Coloane.getLogger().warning("Impossible de modifier le point d'inflexion sur : " + this.genericArc.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getInflexPoint(int)
	 */
	public final Point getInflexPoint(int index) {
		IInflexPoint p = this.genericArc.getNthPI(index);
		return new Point(p.getXPosition(), p.getYPosition());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#getInflexPoints()
	 */
	public final List<Bendpoint> getInflexPoints() {
		List<Bendpoint> bendPoints = new ArrayList<Bendpoint>();
		for (IInflexPoint p : this.genericArc.getListOfPI()) {
			bendPoints.add(new AbsoluteBendpoint(p.getXPosition(), p.getYPosition()));
		}
		return bendPoints;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl#updateAttributesPosition()
	 */
	public final void updateAttributesPosition() {

		// Calcul du nouveau point milieu
		Point newMiddlePoint = this.graphicInfo.findMiddlePoint();

		// Position actuelle
		Point oldMiddlePoint = this.graphicInfo.getMiddlePoint();

		// Calcul du decalage
		int deltaX = newMiddlePoint.x - oldMiddlePoint.x;
		int deltaY = newMiddlePoint.y - oldMiddlePoint.y;

		// Mise a jour des coordonnees des attributs
		for (IAttributeImpl attr : this.getDrawableAttributes()) {
			Point attrLocation = attr.getGraphicInfo().getLocation();
			attr.getGraphicInfo().setLocation(attrLocation.x + deltaX, attrLocation.y + deltaY);
		}

		this.graphicInfo.updateMiddlePoint();
	}
}
