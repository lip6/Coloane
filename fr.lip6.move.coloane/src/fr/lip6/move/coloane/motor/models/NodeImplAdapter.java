package fr.lip6.move.coloane.motor.models;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;



import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.lip6.move.coloane.communications.models.*;
import fr.lip6.move.coloane.interfaces.models.INode;
import fr.lip6.move.coloane.interfaces.models.INodeGraphicInfo;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;

/**
 * Un adaptateur pour implementer les interfaces utiles a l'interface graphique. 
 * Pour la manipulation d'un noeud
 */


public class NodeImplAdapter extends AbstractModelElement implements INode {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Le noeud generique */
	private Node node;

	/** Information de representation graphique */
	private INodeGraphicInfo graphicInfo;

	/** Element de base du formalisme associe au noeud */
	private ElementBase elementBase;

	/** Liste des arcs sortants */
	private List<ArcImplAdapter> sourceArcs = new ArrayList<ArcImplAdapter>();

	/** Liste des arcs entrants */
	private List<ArcImplAdapter> targetArcs = new ArrayList<ArcImplAdapter>();

	/** Le modele adapte */
    private ModelImplAdapter modelAdapter;
    
	/**
	 * Creation d'un adaptateur pour un noeud
	 * @param node le noeud generique a adapter pour le module
	 * @param base L'element de base du formalisme
	 */
	public NodeImplAdapter(Node node, ElementBase base) {
		super();

		// Element de base du formalisme
		this.elementBase = base;
		// Le noeud generique
		this.node = node;
        
		// Les informations graphique sur le noeud
		this.graphicInfo = new NodeGraphicInfo(this);
		this.graphicInfo.setLocation(new Point(this.node.getXPosition(),this.node.getYPosition()));

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
		this.graphicInfo.setLocation(new Point(this.node.getXPosition(),this.node.getYPosition()));

		// Instancier les attributs
		setProperties();
	}

	/**
	 * Creation d'un noeud avec position autre que (0,0)
	 * @param nodeType Le type du noeud
	 * @param base L'element de base du formalisme
	 * @param x Position x
	 * @param y Position y
	 */
	public NodeImplAdapter(String nodeType, int x, int y, ElementBase base) {
		super();

		// Element de base du formalisme
		this.elementBase = base;

		// Le noeud generique avec un type et un id unique
		this.node = new Node(nodeType, x, y);

		// Le information graphique sur le noeud
		this.graphicInfo = new NodeGraphicInfo(this);
		this.graphicInfo.setLocation(new Point(this.node.getXPosition(),this.node.getYPosition()));

		// Instancier les attributs
		setProperties();

	}

	
	/**
	 * Creation des adaptation d'attribut a partides atrtibut generique et du formalisme 
	 * a partir des attributs attache a l'lement de base du formalisme
	 */
	public void setProperties() {

        this.properties.clear();
        
        // Creation de tous les attributs du formalisme
        Iterator iterator = this.elementBase.getListOfAttribute().iterator();
        while (iterator.hasNext()) {

            // Les attributs possibles dans le formalisme
            AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();
            
            Attribute attributeGeneric= new Attribute(attributeFormalism.getName(),new String[]{attributeFormalism.getDefaultValue()},node.getId());
            AttributeImplAdapter attributeModel = new AttributeImplAdapter(attributeGeneric,attributeFormalism.isDrawable());
            
            this.properties.put(attributeModel.getId(), attributeModel);
            this.node.addAttribute(attributeGeneric);
        }
    }
       
        
    /**
     * Creation des propriete ˆ partir des attributs deja existants
     * @param node Noeu generique deja existant
     */
    public void setProperties(Node node) {
        // Creer les properties par defaut
        setProperties();

        for (Enumeration e = properties.elements(); e.hasMoreElements();) {
            AttributeImplAdapter property = (AttributeImplAdapter) e.nextElement();
            for (int i = 0; i < this.node.getListOfAttrSize(); i++) {
            	if (property.getGenericAttribute().getName().equalsIgnoreCase(this.node.getNthAttr(i).getName())) {
                    // Creation d'un adaptateur a partir d'une proprite qui existe dans le model de l'API
                    property.setValue(this.node.getNthAttr(i).getValue());
                    break;
                }
            }
        }
    }      
        
  
    /**
     * Recupere les informations graphiques sur le noeud
     * @return INodeGraphicInfo
     */
	public INodeGraphicInfo getGraphicInfo() {
		return this.graphicInfo;
	}

	/**
	 * Ajoute un arc entrant au noeud 
	 * @param arcAdapter
	 */
	public void addInputArc(ArcImplAdapter arcAdapter) {
		
       	if (arcAdapter.getGenericArc() != null)
       		if (arcAdapter.getTarget() == this) {
       			this.targetArcs.add(arcAdapter);
       			firePropertyChange(NodeImplAdapter.TARGET_ARCS_PROP, null,arcAdapter);
       		}
	}

	/**
	 * Ajoute un arc sortant au noeud
	 * @param arcAdapter
	 */
	public void addOutputArc(ArcImplAdapter arcAdapter) {
		
		if (arcAdapter.getGenericArc() != null)
			if (arcAdapter.getSource() == this) {
				this.sourceArcs.add(arcAdapter);
				firePropertyChange(NodeImplAdapter.SOURCE_ARCS_PROP, null,arcAdapter);
			}
	}

	/**
	 * Retire un arc du noeud
	 * @param arcAdapter arc a retirer
	 */
	public void removeArc(ArcImplAdapter arcAdapter) {
		if (arcAdapter.getSource() == this) {
			this.sourceArcs.remove(arcAdapter);
		    firePropertyChange(NodeImplAdapter.SOURCE_ARCS_PROP, null,arcAdapter);
		}

		if (arcAdapter.getTarget() == this) {
			this.targetArcs.remove(arcAdapter);
		    firePropertyChange(NodeImplAdapter.TARGET_ARCS_PROP, null,arcAdapter);
		}
	}

	/**
	 * Retourne la liste des arcs sortants
	 * @return List
	 */
	public List getSourceArcs() {
        return new ArrayList<ArcImplAdapter>(sourceArcs);
	}

	/**
	 * Retourne la liste des arcs entrants
	 * @return List
	 */
	public List getTargetArcs() {
		return new ArrayList<ArcImplAdapter>(targetArcs);
	}

	/**
	 * Retourne le noeud generique
	 * @return Node
	 * @see Node
	 */
	public Node getGenericNode() {
		return node;
	}

	/**
	 * Retourne l'element de base du formalisme
	 * @return ElementBase
	 * @see ElementBase
	 */
	public ElementBase getElementBase() {
		return elementBase;
	}
	
	/**
	 * Retourne la liste des attributs affichables
	 * @return List
	 */
	public List visibleAttributes() {
		ArrayList<AttributeImplAdapter> visibleAtts = new ArrayList<AttributeImplAdapter>();
		for (Enumeration e = properties.elements(); e.hasMoreElements();) {
            AttributeImplAdapter property = (AttributeImplAdapter) e.nextElement();            
            if (property.isDrawable()) {
            	visibleAtts.add(property);
            }
        }
		return visibleAtts;
	}
	

	/**
	 * Setter de la representation graphique
	 * @param graphicInfo Assigne un objet graphique au noeud
	 */
	public void setGraphicInfo(INodeGraphicInfo graphicInfo) {
		this.graphicInfo = graphicInfo;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return super.getPropertyDescriptors();
	}

    public ModelImplAdapter getModelAdapter() {
        return modelAdapter;
    }

    public void setModelAdapter(ModelImplAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

	public Collection getContextMenus() {
		return null;
	}
	
	public String getNodeAttributeValue(String attribute) {
	   String valeur = "";
	   for (int i = 0; i < this.node.getListOfAttrSize(); i++) {
		   if (this.node.getNthAttr(i).getName().equalsIgnoreCase(attribute)) {
			   valeur = this.node.getNthAttr(i).getValue();
			   break;
		   }
	   }
	   return valeur;
	 }
	 
	    /**
	     * Leve un evenement lors de la modification d'un propriete d'un arc.
	     * Cette methode est appelle par AbstractModelElement si l'evenement correspond bien
	     * @param oldValue L'ancienne valeur de la propriete
	     * @param newValue La nouvelle valeur
	     */
	    public void throwEventProperty (String oldValue, String newValue) {
	    	firePropertyChange(NodeImplAdapter.VALUE_PROP, oldValue,newValue);
	    }
	    
	    /**
	     * Actions entreprises suite ˆ la modification d'un parametres dans la fenetre Properties
	     * @param id L'objet concerne
	     * @param value La nouvelle valeur
	     */
	    public void setPropertyValue(Object id, Object value) {
			String oldValue = getNodeAttributeValue("name"); // On conserve l'ancienne valeur
			super.setPropertyValue(id, value); // On appelle la super-methode qui se charge de la modification du modele
			this.throwEventProperty(oldValue,(String)value); // On leve un evenement pour la mise a jour de la vue
			
	    }

}
