package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.interfaces.model.IGraph;

/**
 * Interface que doivent implémenter tous les <i>constructeur</i> de modèles d'exemples.
 * @see ExampleExtension
 */
public interface IExample {

	/**
	 * Construction d'un modèle de haut-niveau
	 * @return Le modèle construit
	 */
	IGraph buildModel();

}
