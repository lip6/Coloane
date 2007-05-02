package fr.lip6.move.coloane.ui.model;

import java.util.List;


//import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.motor.formalism.Formalism;

/**
 * Interface generale du modele
 * La classe qui implemente cette interface doit heriter de la classe AbstractModelElement pour avoir des fonctionalites de proprietes
 */
public interface IModelImpl {

    /** ID de propriete lorsqu'un noeud est ajoute au modele */
    public static final String NODE_ADDED_PROP = "Model.AddingNode";

    /** ID de propriete lorsqu'un noeud est supprime du modele */
    public static final String NODE_REMOVED_PROP = "Model.RemovingNode";
  
    /**
     * Retourne le formalisme
     * @return Formalism
     * @see Formalism
     */
    public Formalism getFormalism();
    
  
    /**
     * Ajouter un noeud
     * @param child Noeud a ajouter au modele
     * @see NodeImplAdapter
     */
    public void addNode(INodeImpl child)  throws BuildException;

    /**
     * Supprimer un noeud
     * @param child Noeud a supprimer
     * @see NodeImplAdapter
     */
    public void removeNode(INodeImpl child)  throws BuildException;
    
    public void addArc(IArcImpl child);
    
    public void removeArc(IArcImpl child);
    
    public IModel getGenericModel();
    
    public int modifyDate();
    
    public int getDate();
    
    public void setPropertyValue(Object id, Object value);
    
    public boolean isDirty();
    
    public void setDirty(boolean dirty);
    
    public void setBeginBuilding();
    
    public void setEndBuilding();

    /**
     * Retourne la liste de tous les noeud du modele
     * @return List
     */
    public List getChildren();
}
