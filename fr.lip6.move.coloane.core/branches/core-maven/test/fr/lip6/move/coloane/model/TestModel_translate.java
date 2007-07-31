package fr.lip6.move.coloane.model;

import junit.framework.TestCase;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

public class TestModel_translate extends TestCase {

	Model model = new Model();

	int id_node, id_node2, id_arc;

	String[] translate; // resultat de la traduction coloane->cami

	public void affichage() {
		String res = new String("Model:\n");
		if (model.getListOfAttrSize() > 0) {
			for (int i = 0; i < model.getListOfAttrSize(); i++) {
				res += " " + model.getNthAttr(i).getName() + ":"
						+ model.getNthAttr(i).getValue() + " refId:"
						+ model.getNthAttr(i).getRefId() + "\n";
			}
		}
		if (model.getListOfNodeSize() > 0) {
			for (int i = 0; i < model.getListOfNodeSize(); i++) {
				INode node = model.getNthNode(i);
				res += "Node:" + node.getId() + "\n";
				if (node.getListOfAttrSize() > 0) {
					for (int j = 0; j < node.getListOfAttrSize(); j++) {
						res += " " + node.getNthAttr(j).getName() + ":"
								+ node.getNthAttr(j).getValue() + " refId:"
								+ node.getNthAttr(j).getRefId() + "\n";
					}
				}
			}
		}
		if (model.getListOfArcSize() > 0) {
			for (int i = 0; i < model.getListOfArcSize(); i++) {
				IArc arc = model.getNthArc(i);
				res += "Arc:" + arc.getId() + "\n";
				res += " start:" + arc.getStartingNode().getId() + " end:"
						+ arc.getEndingNode().getId() + "\n";
				if (arc.getListOfAttrSize() > 0) {
					for (int j = 0; j < arc.getListOfAttrSize(); j++) {
						res += " " + arc.getNthAttr(j).getName() + ":"
								+ arc.getNthAttr(j).getValue() + " refId:"
								+ arc.getNthAttr(j).getRefId() + "\n";
					}
				}
			}
		}
		System.out.println(res);
	}

	public String lireFichier(String fichier) {
		BufferedReader lecteurAvecBuffer = null;
		String ligne, contenu;
		contenu = new String("");

		try {
			File sourceF = new File(fichier);
			lecteurAvecBuffer = new BufferedReader(new FileReader(sourceF));
		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture");
		}
		try {
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				contenu = contenu + ligne;
			}
			lecteurAvecBuffer.close();

		} catch (IOException e) {
			System.out.println("Erreur de lecture:" + e.toString());
		}
		return contenu;
	}

	/* Affiche le contenu du tableau t */
	public void affiche_translate(String[] t) {
		for (int i = 0; i < Array.getLength(t); i++) {
			System.out.println(t[i]);
		}
	}

	/* Verifie si toutes les valeurs de t1 sont dans t2 */
	public boolean compareTab(String[] t1, String[] t2) {
		boolean b = true;
		boolean tmp = false;
		assertTrue(Array.getLength(t1) == Array.getLength(t2));
		if (Array.getLength(t1) == Array.getLength(t2)) {
			int i = 0;
			while (i < Array.getLength(t1)) {
				for (int j = 0; j < Array.getLength(t2); j++) {
					tmp = tmp || t1[i].equals(t2[j]);
				}
				i++;
			}
			b = b && tmp;
		}
		return b;
	}

	/* Test */
	public void testTranslate() {
		try {
			model.setFormalism("net");
			/* 1er modele */
			System.out.println("\nModele 1");

			// Ajout des 4 attributs du model
			String[] attributs = { "declaration", "author(s)", "version","project" };
			String[] valeurs = { "a", "c", "1.0", "d" };
			for (int i = 0; i < 4; i++) {
				String value = new String();
				value = valeurs[i];
				IAttribute attr = new Attribute(attributs[i], value, 1);
				model.addAttribute(attr);

			}

			// Ajout des 6 noeuds
			int ascii = 65;
			for (int i = 0; i < 6; i++) {
				INode node;
				if (i % 2 == 0) {
					node = new Node("place");
				} else {
					node = new Node("transition");
				}
				// Ajout d'1 attribut au noeud
				String value = new String();
				value = "" + (char) ascii;
				IAttribute attr = new Attribute("name", value, 0);
				node.addAttribute(attr);
				ascii++;

				model.addNode(node);

			}

			// Ajout des 6 arcs
			for (int i = 0; i < 6; i++) {
				IArc arc = new Arc("arc");

				String value = new String("1");
				IAttribute attr = new Attribute("valuation", value, 0);
				arc.addAttribute(attr);

				INode start = model.getNthNode(i);
				INode end = model.getNthNode((i + 1) % 6);
				arc.setStartingNode(start);
				arc.setEndingNode(end);

				model.addArc(arc);

			}

			// Traduction CAMI
			translate = model.translate();
			affiche_translate(translate);
			// affichage();

			Vector<String> cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model model_out = new Model(cami);
				String[] new_build = model_out.translate();
				affiche_translate(new_build);
				assertTrue(Array.getLength(translate) == (Array
						.getLength(new_build)));
				assertTrue(compareTab(translate, new_build));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			// 2ème modele

			System.out.println("\nModele 2");

			// Suppression de l'arc 8
			IArc arc_to_remove = model.getAnArc(8);
			model.removeArc(arc_to_remove);

			// Suppression de l'arc 10
			arc_to_remove = model.getAnArc(10);
			model.removeArc(arc_to_remove);

			// Ajout de l'arc entre le 1er noeud et le 3eme noeud
			IArc arc_to_add = new Arc("arc");
			arc_to_add.setStartingNode(model.getANode(2));
			arc_to_add.setEndingNode(model.getANode(5));

			model.addArc(arc_to_add);

			// Ajout de l'arc entre le 2eme noeud et le 4eme noeud
			arc_to_add = new Arc("arc");
			arc_to_add.setStartingNode(model.getANode(3));
			arc_to_add.setEndingNode(model.getANode(6));

			model.addArc(arc_to_add);

			translate = model.translate();
			affiche_translate(translate);

			cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model model_out = new Model(cami);
				String[] new_build = model_out.translate();
				affiche_translate(new_build);
				assertTrue(Array.getLength(translate) == (Array
						.getLength(new_build)));
				assertTrue(compareTab(translate, new_build));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			// 3ème modele
			System.out.println("\nModele 3");
			model.removeNode(model.getANode(3));
			model.removeNode(model.getANode(7));

			INode node = new Node("place");

			model.addNode(node);

			translate = model.translate();
			affiche_translate(translate);

			cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model model_out = new Model(cami);
				String[] new_build = model_out.translate();
				affiche_translate(new_build);
				assertTrue(Array.getLength(translate) == (Array
						.getLength(new_build)));
				assertTrue(compareTab(translate, new_build));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			// 4ème modele
			System.out.println("\nModele 4");
			String value = new String();
			value = "New";
			IAttribute attr = new Attribute("attr", value, 1);

			model.addAttribute(attr);

			for (int i = 0; i < model.getListOfNodeSize(); i++) {
				IAttribute attrs = new Attribute("name", value, model
						.getNthNode(i).getId());
				model.getNthNode(i).addAttribute(attrs);
			}

			for (int i = 0; i < model.getListOfArcSize(); i++) {
				IAttribute attrs = new Attribute("valuation", value, model
						.getNthArc(i).getId());
				model.getNthArc(i).addAttribute(attrs);
			}

			translate = model.translate();
			affiche_translate(translate);

			cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model model_out = new Model(cami);
				String[] new_build = model_out.translate();
				affiche_translate(new_build);
				assertTrue(Array.getLength(translate) == (Array
						.getLength(new_build)));
				assertTrue(compareTab(translate, new_build));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			System.out.println("FIN TEST");
		} catch (SyntaxErrorException e) {
			System.out.println(e.toString());
		}

	}

}
