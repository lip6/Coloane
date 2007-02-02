package fr.lip6.move.coloane.interfaces.models;

import java.util.List;


import fr.lip6.move.coloane.models.*;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;

/**
 * Interface generale du modele
 * La classe qui implemente cette interface doit heriter de la classe AbstractModelElement pour avoir des fonctionalites de proprietes
 */
public interface IModel {

    /** ID de propriete lorsqu'un noeud est ajoute au modele */
    public static final String NODE_ADDED_PROP = "Model.AddingNode";

    /** ID de propriete lorsqu'un noeud est supprime du modele */
    public static final String NODE_REMOVED_PROP = "Model.RemovingNode";

    /**
     * Retourne le modele generique
     * @return Model
     */
    public Model getGenericModel();

    /**
     * Associe un modele
     * @throws Exception 
     */
    public void setModel(Model model) throws Exception;
  
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
    public void addChild(NodeImplAdapter child);

    /**
     * Supprimer un noeud
     * @param child Noeud a supprimer
     * @see NodeImplAdapter
     */
    public void removeChild(NodeImplAdapter child);

    /**
     * Retourne la liste de tous les noeud du modele
     * @return List
     */
    public List getChildren();

    /**
     * Consulte l'etat du model
     * @return boolean
     */
    public boolean isLocked();
    
    /**
     * Blocage ou deblocage du model en edition 
     * @param isLocked True on bloque le model en edition 
     */
    public void setLocked(boolean isLocked); 

}
