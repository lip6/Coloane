package fr.lip6.move.coloane.model.tests;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.model.Arc;
import fr.lip6.move.coloane.model.Attribute;
import fr.lip6.move.coloane.model.Model;
import fr.lip6.move.coloane.model.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Vector;

import junit.framework.TestCase;

public class TestModelTranslate extends TestCase {

	private Model model = new Model();
	private String[] translate; // resultat de la traduction coloane->cami

	public final void affichage() {
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

	public final String lireFichier(String fichier) {
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
	public final void afficheTranslate(String[] t) {
		for (int i = 0; i < Array.getLength(t); i++) {
			System.out.println(t[i]);
		}
	}

	/* Verifie si toutes les valeurs de t1 sont dans t2 */
	public final boolean compareTab(String[] t1, String[] t2) {
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
	public final void testTranslate() {
		try {
			model.setFormalism("net");
			/* 1er modele */
			System.out.println("\nModele 1");

			// Ajout des 4 attributs du model
			String[] attributs = {"declaration", "author(s)", "version", "project"};
			String[] valeurs = {"a", "c", "1.0", "d"};
			for (int i = 0; i < attributs.length; i++) {
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
			afficheTranslate(translate);
			// affichage();

			Vector<String> cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model modelOut = new Model(cami);
				String[] newBuild = modelOut.translate();
				afficheTranslate(newBuild);
				assertTrue(Array.getLength(translate) == (Array.getLength(newBuild)));
				assertTrue(compareTab(translate, newBuild));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			// 2eme modele

			System.out.println("\nModele 2");

			// Suppression de l'arc 8
			IArc arcToRemove = model.getAnArc(8);
			model.removeArc(arcToRemove);

			// Suppression de l'arc 10
			arcToRemove = model.getAnArc(10);
			model.removeArc(arcToRemove);

			// Ajout de l'arc entre le 1er noeud et le 3eme noeud
			IArc arcToAdd = new Arc("arc");
			arcToAdd.setStartingNode(model.getANode(2));
			arcToAdd.setEndingNode(model.getANode(5));

			model.addArc(arcToAdd);

			// Ajout de l'arc entre le 2eme noeud et le 4eme noeud
			arcToAdd = new Arc("arc");
			arcToAdd.setStartingNode(model.getANode(3));
			arcToAdd.setEndingNode(model.getANode(6));

			model.addArc(arcToAdd);

			translate = model.translate();
			afficheTranslate(translate);

			cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model modelOut = new Model(cami);
				String[] newBuild = modelOut.translate();
				afficheTranslate(newBuild);
				assertTrue(Array.getLength(translate) == (Array.getLength(newBuild)));
				assertTrue(compareTab(translate, newBuild));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			// 3eme modele
			System.out.println("\nModele 3");
			model.removeNode(model.getANode(3));
			model.removeNode(model.getANode(7));

			INode node = new Node("place");

			model.addNode(node);

			translate = model.translate();
			afficheTranslate(translate);

			cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model modelOut = new Model(cami);
				String[] newBuild = modelOut.translate();
				afficheTranslate(newBuild);
				assertTrue(Array.getLength(translate) == (Array.getLength(newBuild)));
				assertTrue(compareTab(translate, newBuild));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			// 4eme modele
			System.out.println("\nModele 4");
			String value = new String();
			value = "New";
			IAttribute attr = new Attribute("attr", value, 1);

			model.addAttribute(attr);

			for (int i = 0; i < model.getListOfNodeSize(); i++) {
				IAttribute attrs = new Attribute("name", value, model.getNthNode(i).getId());
				model.getNthNode(i).addAttribute(attrs);
			}

			for (int i = 0; i < model.getListOfArcSize(); i++) {
				IAttribute attrs = new Attribute("valuation", value, model.getNthArc(i).getId());
				model.getNthArc(i).addAttribute(attrs);
			}

			translate = model.translate();
			afficheTranslate(translate);

			cami = new Vector<String>();
			for (int i = 0; i < Array.getLength(translate); i++) {
				cami.addElement(translate[i]);
			}

			System.out.println("\nBUILD MODEL");

			try {
				Model modelOut = new Model(cami);
				String[] newBuild = modelOut.translate();
				afficheTranslate(newBuild);
				assertTrue(Array.getLength(translate) == (Array.getLength(newBuild)));
				assertTrue(compareTab(translate, newBuild));
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			System.out.println("FIN TEST");
		} catch (ModelException e) {
			System.out.println(e.toString());
		}

	}

}
