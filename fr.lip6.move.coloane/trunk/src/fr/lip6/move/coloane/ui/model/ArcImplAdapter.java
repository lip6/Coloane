package fr.lip6.move.coloane.ui.model;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.model.Arc;
import fr.lip6.move.coloane.model.Attribute;
import fr.lip6.move.coloane.motor.formalism.AttributeFormalism;
import fr.lip6.move.coloane.motor.formalism.ElementBase;



/**
 * C'est ici que le veritable arc est cree.<br>
 * Cet arc est l'arc generique plus quelques proprietes (IArc)<br>
 * Les proprietes du PropertiesView sont gerees ici !
 * @see IArcImpl
 */
public class ArcImplAdapter extends AbstractModelElement implements IArcImpl {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Noeud source repris depuis l'arc generique */
	private NodeImplAdapter source;

	/** Noeud cible repris depuis l'arc generique */
	private NodeImplAdapter target;
	
    /** Booleen pour l'etat de la connexion */
	private boolean isConnected;

	/** Arc generique a adapter */
	private IArc arc;

	/** Element de base du formalisme associe au noeud */
	private ElementBase elementBase;
    
    private ModelImplAdapter modelAdapter;
    
	
    /**
	 * Constructeur <br>
	 * Creation d'un adaptateur pour un arc
	 * @param arc Arc generique pour l'adaptateur
     * @param base Element de base du formalisme
	 */
	 public ArcImplAdapter(IArc arc, NodeImplAdapter source, NodeImplAdapter target, ElementBase base) {
		this.elementBase = base;		
        this.arc = arc;
       
        this.reconnect(source,target);
        
		// Creation de la liste des attributs
		this.setProperties(arc);
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
		this.arc = new Arc(base.getName());

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
		this.arc = new Arc(base.getName());

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
            IAttribute attribute = new Attribute(attributeFormalism.getName(),new String[]{attributeFormalism.getDefaultValue()},arc.getId());
            AttributeImplAdapter attributeAdapter = new AttributeImplAdapter(attribute,attributeFormalism.isDrawable());
            
            // Ajout a la liste des proprietes
            this.properties.put(attributeAdapter.getId(), attributeAdapter);
            
            // Ajout de l'attribut a l'arc generique
            this.arc.addAttribute(attribute);
        }
    }
     
    
	/**
	 * Affectation des attributs corrects (ceux contenu dans l'arc generique)
	 * Cela peutêtre utile lorsq'un modele est lu depuis un fichier.
	 */
    public void setProperties(IArc arc) {
    	
    	// Parcours de tous les attributs du formalisme
		Iterator iterator = this.elementBase.getListOfAttribute().iterator();
		while (iterator.hasNext()) {
			AttributeImplAdapter attributeAdapter = null;
			IAttribute attribute = null;
			AttributeFormalism attributeFormalism = (AttributeFormalism) iterator.next();
			
			// On cherche les attributs dans notre modele qui corresponde a l'attibut du formalisme courant
			boolean find = false;
			for (int i = 0; i < arc.getListOfAttrSize(); i++) {
				// Si l'attribut du formalisme est bien decrit dans notre modele... On cree l'adapteur
				// Pas besoin de creer un nouvel attribut dans le modele !
				attribute = arc.getNthAttr(i);
				if (attributeFormalism.getName().equalsIgnoreCase(attribute.getName())) {
					attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism.isDrawable());
					find = true;
					break;
				}
			}
			
			// Si aucun attribut dans notre modele ne correspond a celui du formalisme... alors notre modele n'est pas complet
			// Il faut donc creer un attribut et un adapteur pour cet attribut du formalisme
			if (!find) {
				attribute = new Attribute(attributeFormalism.getName(), new String[]{attributeFormalism.getDefaultValue()}, 1);
				attributeAdapter = new AttributeImplAdapter(attribute, attributeFormalism.isDrawable());
				this.arc.addAttribute(attribute);
			}

			this.properties.put(attributeAdapter.getId(), attributeAdapter);
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
		
        this.disconnect();
        
		if (source == null || target == null) {
			throw new IllegalArgumentException();
		}

        // On indique a l'arc generique quels sont ces sources et cibles
        this.arc.setStartingNode(source.getGenericNode());
		this.arc.setEndingNode(target.getGenericNode());
		
		// Meme indication pour l'arc adapte
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
	public IArc getGenericArc() {
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
        //this.modelAdapter.getGenericModel().addArc(this.getGenericArc());
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
