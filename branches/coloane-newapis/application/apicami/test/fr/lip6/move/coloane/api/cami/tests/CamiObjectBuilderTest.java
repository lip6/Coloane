package fr.lip6.move.coloane.api.cami.tests;

import junit.framework.TestCase;
import java.util.*;

import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;

/**
 * test unitaire de la classe CamiObjectBuilderTest
 *
 * @author Kahoo && uu
 *
 */
public class CamiObjectBuilderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testbuildMenu() {
		ArrayList<ArrayList<String>> tableau = new ArrayList<ArrayList<String>>();
		ArrayList<String> aq1 = new ArrayList<String>();
		aq1.add("Administration");
		aq1.add("3");
		aq1.add("3");
		ArrayList<String> aq2 = new ArrayList<String>();
		aq2.add("Administration");
		aq2.add("User administration...");
		aq2.add("1");
		aq2.add(null);
		aq2.add(null);
		aq2.add("2");
		aq2.add("1");
		aq2.add(null);
		aq2.add("1");
		ArrayList<String> aq3 = new ArrayList<String>();
		aq3.add("Administration");
		aq3.add("Group administration...");
		aq3.add("1");
		aq3.add(null);
		aq3.add("1");
		aq3.add("2");
		aq3.add("1");
		aq3.add(null);
		aq3.add("1");
		ArrayList<String> aq4 = new ArrayList<String>();
		aq4.add("Administration");
		aq4.add("Log messages");
		aq4.add("3");
		aq4.add("3");
		aq4.add(null);
		aq4.add("1");
		aq4.add("1");
		aq4.add(null);
		aq4.add("1");
		ArrayList<String> aq5 = new ArrayList<String>();
		aq5.add("Log messages");
		aq5.add("To all..");
		aq5.add("1");
		aq5.add(null);
		aq5.add(null);
		aq5.add("2");
		aq5.add("1");
		aq5.add(null);
		aq5.add("1");
		ArrayList<String> aq6 = new ArrayList<String>();
		aq6.add("Log messages");
		aq6.add("To some groups...");
		aq6.add("1");
		aq6.add(null);
		aq6.add(null);
		aq6.add("2");
		aq6.add("1");
		aq6.add(null);
		aq6.add("1");
		ArrayList<String> aq7 = new ArrayList<String>();
		aq7.add("Log messages");
		aq7.add("To some users...");
		aq7.add("1");
		aq7.add(null);
		aq7.add(null);
		aq7.add("2");
		aq7.add("1");
		aq7.add(null);
		aq7.add("1");
		ArrayList<String> aq8 = new ArrayList<String>();
		aq8.add("Administration");
		aq8.add("Menu cache management");
		aq8.add("3");
		aq8.add("3");
		aq8.add(null);
		aq8.add("1");
		aq8.add("1");
		aq8.add(null);
		aq8.add("1");
		ArrayList<String> aq9 = new ArrayList<String>();
		aq9.add("Menu cache management");
		aq9.add("Erase for all users...");
		aq9.add("1");
		aq9.add(null);
		aq9.add(null);
		aq9.add("2");
		aq9.add("1");
		aq9.add(null);
		aq9.add("1");
		ArrayList<String> aq10 = new ArrayList<String>();
		aq10.add("Menu cache management");
		aq10.add("Erase cache for selected users...");
		aq10.add("1");
		aq10.add(null);
		aq10.add(null);
		aq10.add("2");
		aq10.add("1");
		aq10.add(null);
		aq10.add("1");

		tableau.add(aq1);
		tableau.add(aq2);
		tableau.add(aq3);
		tableau.add(aq4);
		tableau.add(aq5);
		tableau.add(aq6);
		tableau.add(aq7);
		tableau.add(aq8);
		tableau.add(aq9);
		tableau.add(aq10);

		IMenu menu = CamiObjectBuilder.buildMenu(tableau);
		afficher(menu);

	}

	public void afficher(IMenu menu) {
		System.out.print("AQ(");
		if (menu.getParent() != null) {
			String parent = menu.getParent().getName();
			if (parent != null) {
				System.out.print(parent + ",");
			} else {
				System.out.print("null" + ",");
			}
		}
		System.out.print(menu.getName() + ",");
		if (menu.getType() != -1) {
			System.out.print(menu.getType() + ",");
		} else {
			System.out.print(",");
		}
		if (menu.getQuestionBehavior() != -1) {
			System.out.print(menu.getQuestionBehavior() + ",");
		} else {
			System.out.print(",");
		}

		boolean tmpValid = menu.isValid();
		if (tmpValid) {
			System.out.print("1" + ",");
		} else {
			System.out.print("2" + ",");
		}

		boolean tmpDialog = menu.isDialogAllowed();
		if (tmpDialog) {
			System.out.print("2" + ",");
		} else {
			System.out.print("1" + ",");
		}

		boolean tmpStop = menu.stopAuthorized();
		if (tmpStop) {
			System.out.print("2" + ",");
		} else {
			System.out.print("1" + ",");
		}

		if (menu.outputFormalism() != null) {
			System.out.print(menu.outputFormalism() + ",");
		} else {
			System.out.print(",");
		}
		boolean tmpActive = menu.isActivate();
		if (tmpActive) {
			System.out.print("1");
		} else {
			System.out.print("2");
		}

		System.out.println(")");
		ArrayList<IMenu> tmp = menu.getChildren();
		for (IMenu child : tmp) {
			afficher(child);
		}
	}

	/**
	 * test de la construction des IUpdateItem
	 */
	public void testbuildUpdateItem() {
		ArrayList<ArrayList<String>> tableau = new ArrayList<ArrayList<String>>();
		ArrayList<String> tq1 = new ArrayList<String>();
		tq1.add("Administration");
		tq1.add("User administration...");
		tq1.add("7");
		ArrayList<String> tq2 = new ArrayList<String>();
		tq2.add("Administration");
		tq2.add("Group administration...");
		tq2.add("7");
		ArrayList<String> tq3 = new ArrayList<String>();
		tq3.add("Administration");
		tq3.add("Log messages");
		tq3.add("7");
		ArrayList<String> tq4 = new ArrayList<String>();
		tq4.add("Administration");
		tq4.add("To all...");
		tq4.add("7");
		ArrayList<String> tq5 = new ArrayList<String>();
		tq5.add("Administration");
		tq5.add("To some groups...");
		tq5.add("7");
		ArrayList<String> tq6 = new ArrayList<String>();
		tq6.add("Administration");
		tq6.add("To some users...");
		tq6.add("7");
		ArrayList<String> tq7 = new ArrayList<String>();
		tq7.add("AMI-Net");
		tq7.add("Suppress structuraly useless places and transitions");
		tq7.add("8");
		ArrayList<String> tq8 = new ArrayList<String>();
		tq8.add("AMI-Net");
		tq8.add("Structural properties");
		tq8.add("8");
		ArrayList<String> tq9 = new ArrayList<String>();
		tq9.add("AMI-Net");
		tq9.add("Symbolic model checking - GreatSPN and LIP6 tools");
		tq9.add("8");
		ArrayList<String> tq10 = new ArrayList<String>();
		tq10.add("AMI-Net");
		tq10.add("Model Checking with PROD");
		tq10.add("8");

		tableau.add(tq1);
		tableau.add(tq2);
		tableau.add(tq3);
		tableau.add(tq4);
		tableau.add(tq5);
		tableau.add(tq6);
		tableau.add(tq7);
		tableau.add(tq8);
		tableau.add(tq9);
		tableau.add(tq10);

		ArrayList<IUpdateItem> modifMenu = CamiObjectBuilder
				.buildUpdateItem(tableau);
		afficher(modifMenu);
	}

	public void afficher(ArrayList<IUpdateItem> modifMenu) {

		for (IUpdateItem tq : modifMenu) {
			System.out.print("TQ(");
			System.out.print(tq.getRootName() + ",");
			System.out.print(tq.getServiceName() + ",");
			if (tq.getState()) {
				System.out.println("7, )");
			} else {
				System.out.println("8, )");
			}
		}
	}
}
