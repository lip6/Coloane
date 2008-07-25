package fr.lip6.move.coloane.api.camiObject.tests;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.Menu;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import junit.framework.TestCase;
import java.util.*;

public class MenuTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMenu() {
		boolean activate = false;
		ArrayList<IMenu> children = new ArrayList<IMenu> ();
		boolean dialogAllowed = true;
		String name = "AMI-NET";
		String outputFormalism ="fd";
		IMenu parent = new Menu();
		int questionBehavior = 1;
		int questionType = 5;
		boolean stopAuthorized = false;
		boolean valid = false;

		IMenu pere = new Menu(parent,name,questionType,questionBehavior,valid,
			dialogAllowed,stopAuthorized,
			outputFormalism,activate,children);

		this.assertEquals(false, pere.isActivate());
		this.assertEquals(children, pere.getChildren());
		this.assertEquals(true, pere.isDialogAllowed());
		this.assertEquals("AMI-NET", pere.getName());
		this.assertEquals("fd", pere.outputFormalism());
		this.assertEquals(parent, pere.getParent());
		this.assertEquals(1, pere.getQuestionBehavior());
		this.assertEquals(5, pere.getType());
		this.assertEquals(false, pere.stopAuthorized());
		this.assertEquals(false, pere.isValid());

	}

	public void testAddMenu() {
		boolean activate = false;
		ArrayList<IMenu> children = new ArrayList<IMenu> ();
		boolean dialogAllowed = true;
		String name = "AMI-NET";
		String outputFormalism ="out1";
		IMenu parent = new Menu();
		int questionBehavior = 1;
		int questionType = 1;
		boolean stopAuthorized = false;
		boolean valid = false;

		IMenu pere = new Menu(parent,name,questionType,questionBehavior,valid,
			dialogAllowed,stopAuthorized,
			outputFormalism,activate,children);

    System.out.println("afficher pere 1ere fois");
    afficher(pere);
		boolean activate2 = false;
		ArrayList<IMenu> children2 = new ArrayList<IMenu> ();
		boolean dialogAllowed2 = true;
		String name2 = "Erase for all users...";
		String outputFormalism2 ="out2";
		IMenu parent2 = new Menu();
		int questionBehavior2 = 2;
		int questionType2 = 2;
		boolean stopAuthorized2 = false;
		boolean valid2 = false;
		IMenu pere2 = new Menu(parent2,name2,questionType2,questionBehavior2,valid2,
				dialogAllowed2,stopAuthorized2,
				outputFormalism2,activate2,children2);
		System.out.println("afficher  fils ");
		afficher(pere2);


		boolean activate3 = false;
		ArrayList<IMenu> children3 = new ArrayList<IMenu> ();
		boolean dialogAllowed3 = true;
		String name3 = "Erase for all users...3...fils.....";
		String outputFormalism3 ="out3";
		IMenu parent3 = new Menu();
		int questionBehavior3 = 3;
		int questionType3 = 3;
		boolean stopAuthorized3 = false;
		boolean valid3 = false;
		IMenu pere3 = new Menu(parent3,name3,questionType3,questionBehavior3,valid3,
				dialogAllowed3,stopAuthorized3,
				outputFormalism3,activate3,children3);
		System.out.println("afficher  fils ");
		afficher(pere3);


		boolean activate4 = false;
		ArrayList<IMenu> children4 = new ArrayList<IMenu> ();
		boolean dialogAllowed4 = true;
		String name4 = "Erase for all users...4..ptit fils.....";
		String outputFormalism4 ="out4";
		IMenu parent4 = new Menu();
		int questionBehavior4 = 4;
		int questionType4 = 4;
		boolean stopAuthorized4 = false;
		boolean valid4 = false;
		IMenu pere4 = new Menu(parent4,name4,questionType4,questionBehavior4,valid4,
				dialogAllowed4,stopAuthorized4,
				outputFormalism4,activate4,children4);
		System.out.println("afficher  fils ");
		afficher(pere4);

		pere.addMenu("AMI-NET", pere2);
		pere.addMenu("AMI-NET", pere3);
		pere.addMenu("Erase for all users...", pere4);
		System.out.println("afficher pere 2eme fois");
		afficher(pere);

}
	public static void afficher(IMenu menu) {
		System.out.print("//////////AQ(");

		System.out.print(menu.getName()+",");
		System.out.print(menu.getType()+",");
		System.out.print(menu.getQuestionBehavior()+",");
		System.out.print(menu.isValid()+",");
		System.out.print(menu.isDialogAllowed()+",");
		System.out.print(menu.stopAuthorized()+",");
		System.out.print(menu.outputFormalism()+",");
		System.out.println(menu.isActivate()+")");
		for (IMenu child : menu.getChildren()) {

			afficher(child);
		}
	}
}
