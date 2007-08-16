package fr.lip6.move.coloane.interfaces.concretemodel;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.Model;

import java.util.Vector;

/**
 * Model concret pour permettre les tests unitaires
 */
public class ConcreteModel extends Model {

	/**
	 * Constructeur
	 */
	public ConcreteModel() {

	}

	// Pas besoin de traduction pour cet objet
	@Override
	public final String[] translate() {
		return null;
	}

	// Pas besoin de construction de modele
	public final void buildModel(Vector<String> camiCommande) throws SyntaxErrorException, ModelException{
		return;
	}
}
