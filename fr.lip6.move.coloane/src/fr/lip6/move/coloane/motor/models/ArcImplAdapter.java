package fr.lip6.move.coloane.motor.models;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.lip6.move.coloane.communications.models.*;
import fr.lip6.move.coloane.interfaces.models.*;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.formalism.*;

/**
 * C'est ici que le veritable arc est cree.<br>
 * Cet arc est l'arc generique plus quelques proprietes (IArc)<br>
 * Les proprietes du PropertiesView sont gerees ici !
 * @see IArc
 */
public class ArcImplAdapter extends AbstractModelElement implements IArc {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Noeud source repris depuis l'arc generique */
	private NodeImplAdapter source;

	/** Noeud cible repris depuis l'arc generique */
	private NodeImplAdapter target;
	
    /** Booleen pour l'etat de la connexion */
	private boolean isConnected;

	/** Arc generique a adapter */
	private Arc arc;

	/** Element de base du formalisme associe au noeud */
	private ElementBase elementBase;
    
    private ModelImplAdapter modelAdapter;
    
	
    /**
	 * Constructeur <br>
	 * Creation d'un adaptateur pour un arc
	 * @param arc Arc generique pour l'adaptateur
     * @param base Element de base du formalisme
	 */
	 public ArcImplAdapter(Arc arc, NodeImplAdapter source, NodeImplAdapter target, ElementBase base) {
		 this.elementBase = base;		
        this.arc = arc;
       
        this.reconnect(source,target);
        
		// Creation de la liste des attributs
		this.setCorrectProperties();
	}


	/** 
	 * Constructeur<br>
     * Creation d'un arc a partir des deux noeuds source et cible.
	 * 
     * @param source Noeud source
     * @param target Noeud cible
     * @param base Element de base du formalisme         
	 */
	public ArcImplAdapter(NodeImplAdapter source, NodeImplAdapter target, ElementBase base) {
		this.elementBase = base;
		this.arc = new Arc(base.getName(), AbstractModelElement.uniqueId++);

		this.arc.setEndingNode(target.getGenericNode());
        this.arc.setStartingNode(source.getGenericNode());
        
        // Creation de la liste des attributs
		this.setProperties();
		
        this.reconnect(source, target);
	}
	
	/**
	 * Constructeur<br>
	 * Creation d'un arc a partir de la palette
	 * 
	 * @param base Element de base du formalism
	 */
	public ArcImplAdapter(ElementBase base) {
		this.elementBase = base;
		this.arc = new Arc(base.getName(), AbstractModelElement.uniqueId++);

		// Creation de la liste des attributs
		this.setProperties();
	}

	/**
	 * Creer les adaptateurs pour les proprietes a partir des attributs
	 */
	public void setProperties() {
	   
        this.properties.clear();

        // Creation de tout les attributs du formalisme
        Iterator iterator = this.elementBase.getListOfAttribute().iterator();
        while (iterator.hasNext()) {

            // Les attributs possibles dans le formalisme
            AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();
            Attribute attribute=new Attribute(attributeFormalism.getName(),new String[]{attributeFormalism.getDefaultValue()},arc.getUniqueId());
            AttributeImplAdapter property = new AttributeImplAdapter(attribute,attributeFormalism.isDrawable());
            
            // Ajout a la liste des proprietes
            this.properties.put(property.getId(), property);
            
            // Ajout de l'attribut a l'arc generique
            this.arc.addAttribute(attribute);
        }
    }
     
    
	/**
	 * Affectation des attributs corrects (ceux contenu dans l'arc generique)
	 * Cela peutêtre utile lorsq'un modele est lu depuis un fichier.
	 * @param arc
	 */
    public void setCorrectProperties() {
    	// Creer les properties par defaut
        this.setProperties();

        // Pour toutes les proprietes de l'arc
        for (Enumeration e = properties.elements(); e.hasMoreElements();) {
        	AttributeImplAdapter property = (AttributeImplAdapter) e.nextElement();
            // On parcourt tous les attributs de l'arc
        	for (int i = 0; i < this.arc.getListOfAttrSize(); i++) {
        		// Dès qu'il y a concordance entre la propriete et l'attribut
                if (property.getGenericAttribute().getName().equalsIgnoreCase(this.arc.getNthAttr(i).getName())) {
                    // Modification de la valeur de la propriete pour qu'elle calque celle de l'attribut
                    property.setValue(this.arc.getNthAttr(i).getValue());
                    break;
                }
            }
        }
    }      
    

	/**
     * Retourne l'attribut ContextMenus
	 * @return Collection
	 */
    /** TODO: A faire */
    public Collection getContextMenus() {
		return null;
	}

	/**
     * Retourne le noeud source de l'arc
     * @return NodeImplAdapter noeud source
	 */
	public NodeImplAdapter getSource() {
		return source;
	}

	/**
	 * Retourne le noeud cible de l'arc
     * @return target noeud cible
	 */
	public NodeImplAdapter getTarget() {
		return target;
	}

	/**
	 * Reconnecter
	 */
	public void reconnect() {
		if (!isConnected) {
			source.addOutputArc(this);
			target.addInputArc(this);
			isConnected = true;
		}
	}

	/**
	 * Reconnecter
	 * @param source Noeud source de l'arc
	 * @param target Noeud cible de l'arc
     * @throws IllegalArgumentException IllegalArgumentException
	 */
	public void reconnect(NodeImplAdapter source, NodeImplAdapter target) throws IllegalArgumentException {
		
		// 
        this.disconnect();
        
		if (source == null || target == null) {
			throw new IllegalArgumentException();
		}

        // On indique a l'arc generique quels sont ces sources et cibles
        this.arc.setStartingNode(source.getGenericNode());
		this.arc.setEndingNode(target.getGenericNode());
		
		this.source = source;
		this.target = target;
		
		this.reconnect();

	}

	/**
	 * Deconnecter un arc
	 */
	public void disconnect() {
		if (isConnected) {
            this.arc.setStartingNode(null);
            this.arc.setEndingNode(null);
            
            source.removeArc(this);
			target.removeArc(this);
			
			// On indique la deconnexion			
			this.source = null;
			this.target = null;
			
			isConnected = false;
		}

	}

	/**
     * Retourne l'arc generique
	 * @return Arc
	 * @see Arc
	 */
	public Arc getGenericArc() {
		return this.arc;
	}

	/**
	 * Obtenirle style de fin de fleche
     * @return int
	 */
	public int getFigureStyle() {
		return this.elementBase.getNumFigure();
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
	 * Retourne le modele generique
	 * @return ModelImplAdapter
	 * @see ModelImplAdapter
	 */
    public ModelImplAdapter getModelAdapter() {
        return modelAdapter;
    }

    /** 
     * Associe l'arc generique au modele
     * @param modelAdapter
     */
    public void setModelAdapter(ModelImplAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
        this.modelAdapter.getGenericModel().addArc(this.getGenericArc());
    }


    /** 
     * Liste des proprietes a afficher dans la fenetre de PropertyView
     * @return IPropertyDescriptor[]
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
		return super.getPropertyDescriptors();

	}
    
    /**
     * Methode d'acces a la valeur de l'arc generique
     * @return String
     */
    public String getArcValue() {
    	String valeur = "";
    	for (int i = 0; i < this.arc.getListOfAttrSize(); i++) {
    		if (this.arc.getNthAttr(i).getName().equalsIgnoreCase("valuation")) {
    			valeur = this.arc.getNthAttr(i).getValue();
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
    	firePropertyChange(ArcImplAdapter.VALUE_PROP, oldValue,newValue);
		Coloane.getDefault().notifyModelChange();
    }
    
    /**
     * Actions entreprises suite à la modification d'un parametres dans la fenetre Properties
     * @param id L'objet concerne
     * @param value La nouvelle valeur
     */
    public void setPropertyValue(Object id, Object value) {
		String oldValue = getArcValue(); // On conserve l'ancienne valeur
		super.setPropertyValue(id, value); // On appelle la super-methode qui se charge de la modification du modele
		this.throwEventProperty(oldValue,(String)value); // On leve un evenement pour la mise a jour de la vue
		
    }
}
