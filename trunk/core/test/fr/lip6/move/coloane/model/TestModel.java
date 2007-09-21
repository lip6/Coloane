package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.Attribute;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import java.util.Vector;

import junit.framework.TestCase;

/*Scenario de test:
 * Le nombre de tour effectue est specifie par la variable "MAXLOOP"
 * Le nombre d'arc a supprimer par est specifie par la variable "NBREMOVE"
 *
 * Ajout d'un arc de facon correcte ou non aleatoirement
 * Si on ne peut ajouter un arc alors on tente d'ajouter un noeud et un arc dont les identifiants
 * existe deja dans le modele, sinon on ajoute donc l'arc mais on retire un point d'inflexion pris au hasard.
 *
 * Tous les 8 tours, nb_remove arcs sont supprimes / ajout d'un point d'inflexion a un arc pris au hasard.
 *
 * Tous les 15 tours 1 noeud choisi aleatoirement est supprime.Ajout d'un point d'inflexion.
 *
 * Tous les 10 tours on effectue un translate et un buildmodel (export/import) afin d'en comparer les sorties
 * tout en comparant l'ensemble de leurs noeuds et arcs des 2 models
 * Lors de la suppression d'un arc, on compare le model avant la suppression au model apres la suppression
 *
 * A chaque ajout d'un arc on ajoute aleatoirement un attribut au model, a un noeud ou a une arc
 *
 * */

public class TestModel extends TestCase {

	static final int MAXLOOP = 1000;

	static final int NBREMOVE = 2;

	static final int MAXINFLEXPOS = 50;

	private int tour = 1;

	private IModel model = new Model(new CamiTranslator());


	/** SCENARIO **/
	public final void testScenario() {

		TestNode testNode = new TestNode();
		TestModelPI pi = new TestModelPI();
		TestArc testArc = new TestArc();
		TestModelTranslate testTranslate = new TestModelTranslate();
		TestAttribute testAttrs = new TestAttribute();

		Vector<String> translate;
		Vector<String> newBuild;
		int actionAlea; // represente l'action a effectuer (Ajout d'un arc
		// correct ou non)
		int k; // cas du switch suivant le tour...

		// Creation du modele
		model.setFormalism("AMI-Net");
		String[] attributs = {"declaration", "author(s)", "version", "project"};
		String[] valeurs = {"a", "c", "1.0", "d"};
		for (int i = 0; i < 4; i++) {
			String value = new String();
			value = valeurs[i];
			IAttribute attr = new Attribute(attributs[i], value, 1);
			model.addAttribute(attr);
		}

		// Effectue le scenario sur MAXLOOP de boucle
		while (tour <= MAXLOOP) {

			actionAlea = (int) (Math.random() * 2);
			IArc arc = new Arc("Arc");
			k = -1;

			if ((tour % 10) == 0) {
				k = 0;
			} else if ((tour % 8) == 0) {
				k = 1;
			} else if ((tour % 15) == 0) {
				k = 2;
			}

			switch (k) {
			// Test translate
			case 0:
				testTranslate.testTranslate(model);
				break;

			// Test le retrait d'arc et la comparaison du translate sur 2
			// modeles differents
			case 1:
				int i = 0;

				// On retire NBREMOVE arcs
				while (i < NBREMOVE) {

					translate = model.translate();

					// Aucun arc a retirer, on sort de la boucle
					if (model.getListOfArcSize() == 0) {
						break;
					}
					testArc.removeArc(model);
					i = i + 1;

					newBuild = model.translate();
					assertFalse(testTranslate.compareTranslate(newBuild,
							translate));
				}

				// Ajout d'un point d'inflexion
				if (model.getListOfArcSize() != 0) {

					int x = (int) (Math.random() * MAXINFLEXPOS);
					int y = (int) (Math.random() * MAXINFLEXPOS);
					int nthArc = (int) (Math.random() * model
							.getListOfArcSize());

					pi.aleaAddPI(model.getNthArc(nthArc), x, y);
				}
				break;

			case 2:
				// Retrait d'un noeud
				testNode.removeNode(model);

				// Ajout d'un point d'inflexion a un arc
				if (model.getListOfArcSize() != 0) {
					int x = (int) (Math.random() * 50);
					int y = (int) (Math.random() * 50);
					int nthArc = (int) (Math.random() * model
							.getListOfArcSize());

					pi.aleaAddPI(model.getNthArc(nthArc), x, y);
				}
				break;

			default:
				// Ajout autorise
				if (actionAlea == 0) {

					testArc.addArcOK(model, arc);

					if (!(model.getListOfArcSize() == 0)) {

						// On ajoute aleatoirement un attribut a un
						// element du modele
						testAttrs.aleaAttribute(model);

						// On retire un PI a un arc pris au hasard
						int x = (int) (Math.random() * MAXINFLEXPOS);
						int y = (int) (Math.random() * MAXINFLEXPOS);
						int nthArc = (int) (Math.random() * model
								.getListOfArcSize());

						pi.aleaRemovePI(model.getNthArc(nthArc), x, y);
					}

				} else {

					// Action non autorisee
					testArc.switchArcNull(model, arc);

					// Ajout d'un noeud dont
					// l'identifiant existe deja
					testNode.addNodeWrongID(model);

					// Ajout d'un arc dont
					// l'identifiant existe deja
					testArc.addArcWrongID(model);
				}
			}
			tour++;
		}
	}

}
