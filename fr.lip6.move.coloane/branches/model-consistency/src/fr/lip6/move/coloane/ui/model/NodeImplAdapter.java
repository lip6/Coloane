package fr.lip6.move.coloane.ui.model;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.model.Attribute;
import fr.lip6.move.coloane.model.Node;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;

/**
 * Description de l'adapteur pour un noeud du modele.
 * Le noeud adapte est generique. L'adapteur fourni les mehodes et strctures utiles pour GEF.
 * Cet adapteur doit gerer la coherence entre le noeud generique et le noeud augemente.
 * @see INodeImpl
 */

public class NodeImplAdapter extends AbstractModelElement implements INodeImpl {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
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
	public NodeImplAdapter(INode node, ElementBase base) {
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
	/** TODO : Je ne trouve pas l'endroit ou le noeud est ajoute au modele */
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
	 * Creation des attributs generique prevus par le formalisme
	 * Creation des attributs adaptes correspondant a ces attributs generiques
	 * Tous les attributs sont initialises aux valeurs par defaut prevus par le formalisme
	 */
	private void setProperties() {
		this.properties.clear();
        
        // Creation de tous les attributs prevus par le formalisme
        Iterator iterator = this.elementBase.getListOfAttribute().iterator();
        
        while (iterator.hasNext()) {
            AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();

            /* Creation de l'attribut generique */
            IAttribute attribute = new Attribute(attributeFormalism.getName(),new String[]{attributeFormalism.getDefaultValue()},node.getId());
            this.node.addAttribute(attribute);
            
            /* Creation de l'attribut adapte */
            AttributeImplAdapter attributeAdapter = new AttributeImplAdapter(attribute,attributeFormalism.isDrawable());
            
            /* Ajout de cet attribut dans la liste des propriete pour la vue GEF */
            this.properties.put(attributeAdapter.getId(), attributeAdapter);
        }
    }
       
        
	/**
	 * Affectation des attributs corrects (ceux contenu dans le modele generique)
	 * Creation des attribut generiques manquants et attributs adaptes correspondants
	 * Cela peut être utile lorsq'un modele est lu depuis un fichier.
	 * @param node Le noeud generique qui vient d'etre augemente
	 */
    private void setProperties(INode node) {
        
    	// Parcours de tous les attributs du formalisme
		Iterator iterator = this.elementBase.getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			AttributeImplAdapter attributeAdapter = null;
			IAttribute attribute = null;
			
			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();
			
			// On parcours tous les attributs deja definis dans notre noeud generique
			// On cherche l'attribut dans notre noeud generique qui correspond a l'attibut prevu par le formalisme (courant)
			boolean find = false;
			for (int i = 0; (i < node.getListOfAttrSize()) && !find; i++) {
				
				// Si l'attribut du formalisme est bien decrit dans notre modele... On cree l'adapteur
				// Pas besoin de creer un nouvel attribut genrique dans le modele !
				attribute = node.getNthAttr(i);
				if (attributeFormalism.getName().equalsIgnoreCase(attribute.getName())) {
					attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism.isDrawable());
					find = true;
				}
			}
			
			// Si aucun attribut dans notre noeud generique ne correspond a celui du formalisme... alors notre noeud n'est pas complet
			// Il faut donc creer un attribut et un adapteur pour cet attribut du formalisme
			if (!find) {
				attribute = new Attribute(attributeFormalism.getName(), new String[]{attributeFormalism.getDefaultValue()}, 1);
				attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism.isDrawable());
				this.node.addAttribute(attribute);
			}
			
			// Augmente la liste des proprietes pour le modele (fenetre properties de la vue) 
			this.properties.put(attributeAdapter.getId(), attributeAdapter);
		}
    }      

    
    /**
     * Recupere l'ID du noeud generique
     * Evite les appels au noeud generique
     * @return ID
     */
    public int getId() {
    	return this.getGenericNode().getId();
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
	 * L'arc doit etre rajoute dans la liste des arcs sortant du noeud adapte
	 * L'arc doit etre ajout dans la liste des arcs sortant du noeud generique
	 * @param arcAdapter
	 * @throws BuildException 
	 */
	public void addInputArc(IArcImpl arcAdapter) throws BuildException {
       	if ((arcAdapter.getGenericArc() != null) && (arcAdapter.getTarget() == this)) {
       		this.targetArcs.add(arcAdapter);
       		this.node.addInputArc(arcAdapter.getGenericArc());
       		firePropertyChange(NodeImplAdapter.TARGET_ARCS_PROP, null,arcAdapter);
       	} else {
			throw new BuildException("Erreur lors de l'ajout d'un arc entrant au modele");
		}
	}

	/**
	 * Ajoute un arc sortant au noeud
	 * L'arc doit etre rajoute dans la liste des arcs sortant du noeud adapte
	 * L'arc doit etre ajout dans la liste des arcs sortant du noeud generique
	 * @param arcAdapter
	 * @throws BuildException 
	 */
	public void addOutputArc(IArcImpl arcAdapter) throws BuildException {
		if ((arcAdapter.getGenericArc() != null) && (arcAdapter.getSource() == this)) {
			this.sourceArcs.add(arcAdapter);
			this.node.addOutputArc(arcAdapter.getGenericArc());
			firePropertyChange(NodeImplAdapter.SOURCE_ARCS_PROP, null,arcAdapter);
		} else {
			throw new BuildException("Erreur lors de l'ajout d'un arc sortant au modele");
		}
	}
	
	/**
	 * Retire un arc du noeud
	 * Cette methode est appelee par l'arc adapte lors de sa deconnexion
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
	 * @return List of IArcImpl
	 */
	public List getSourceArcs() {
        return new ArrayList<IArcImpl>(sourceArcs);
	}

	/**
	 * Retourne la liste des arcs entrants
	 * @return List of IArcImpl
	 */
	public List getTargetArcs() {
		return new ArrayList<IArcImpl>(targetArcs);
	}

	/**
	 * Retourne le noeud generique
	 * @return Node
	 * @see Node
	 */
	public INode getGenericNode() {
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
	 * Ces attributs seront affiches dans la fenetre proprietes de la vue Coloane dans Eclipse
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
	 * TODO : Commentaires
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return super.getPropertyDescriptors();
	}

	
	/**
	 * Retourne le modele augemente auquel est attache le noeud
	 * @return IModeImpl
	 */
    public IModelImpl getModelAdapter() {
        return modelAdapter;
    }

    /**
     * Associe le modele augmente au noeud
     * @param modelAdapter
     */
    public void setModelAdapter(IModelImpl modelAdapter) {
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
	  * Actions entreprises suite à la modification d'un parametres dans la fenetre Properties
	  * @param id L'objet concerne
	  * @param value La nouvelle valeur
	  */
	 public void setPropertyValue(Object id, Object value) {
		 String oldValue = getNodeAttributeValue("name"); // On conserve l'ancienne valeur
		 super.setPropertyValue(id, value); // On appelle la super-methode qui se charge de la modification du modele
		 this.throwEventProperty(oldValue,(String)value); // On leve un evenement pour la mise a jour de la vue
	 }

}
