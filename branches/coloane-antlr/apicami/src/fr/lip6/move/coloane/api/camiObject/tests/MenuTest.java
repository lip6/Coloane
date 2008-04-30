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
		this.assertEquals(5, pere.getQuestionType());
		this.assertEquals(false, pere.stopAuthorized());
		this.assertEquals(false, pere.isValid());

	}
}
