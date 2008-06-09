package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.Session;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JMock.class)
public class ResultTreeImplTest {
	private IResultTree root, tree2, tree3, tree4, tree5;

	// Definition du Mock SessionManager
	private Mockery context = new JUnit4Mockery();
	private final ISessionManager manager = (ISessionManager) context.mock(ISessionManager.class);

	@Before
	public final void setUp() throws Exception {
		root = new ResultTreeImpl("t1_elt1", "t1_elt2", "t1_elt3");
		root.setSessionManager(manager);

		final ISession tempo = new Session("temporaire");

		context.checking(new Expectations() { {
			allowing(manager).getCurrentSession();
			will(returnValue(tempo));
		} });

		tree2 = new ResultTreeImpl(2, "t2_elt1", "t2_elt2", "t2_elt3");

		List<Integer> tmpList = new ArrayList<Integer>();
		tmpList.add(Integer.valueOf(3));
		tmpList.add(Integer.valueOf(5));
		tmpList.add(Integer.valueOf(7));
		tree3 = new ResultTreeImpl(tmpList, "t3_elt1", "t3_elt2", "t3_elt3");
		tree4 = new ResultTreeImpl("t4_elt1");
		tree5 = new ResultTreeImpl();
	}

	@Test
	public final void testGetParent() {
		root.addChild(tree2);
		tree2.addChild(tree3);
		assertTrue(tree2.getParent().equals(root));
		assertTrue(tree3.getParent().equals(tree2));

		/* Le root1 n'a pas de pere... C'est la racine */
		assertNull(root.getParent());
	}

	@Test
	public final void testSetParent() {
		/* Le lien doit etre fait dans le sens fils -> pere seulelement */
		tree4.setParent(root);
		assertTrue(tree4.getParent().equals(root)); /* sens correct */
		assertFalse(root.getChildren().size() > 0);
	}

	@Test
	public final void testGetChildren() {
		root.addChild(tree3);
		root.addChild(tree4);
		tree3.addChild(tree2);
		List<IResultTree> list = root.getChildren();
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).equals(tree3));
		assertTrue(list.get(1).equals(tree4));
		assertFalse(tree4.getChildren().size() > 0);

		assertTrue(tree3.getChildren().size() == 1);
		assertTrue(tree3.getChildren().get(0).equals(tree2));

		assertTrue(tree2.getChildren().size() == 0);
	}

	@Test
	public final void testAddChild() {
		root.addChild(tree3);
		assertTrue(root.getChildren().size() == 1);
		assertTrue(tree3.getParent().equals(root));
		root.addChild(tree2);
		assertTrue(root.getChildren().size() == 2);
		assertTrue(tree2.getParent().equals(root));

		/* Verification de l'ordre d'insertion dans la liste */
		assertTrue(root.getChildren().get(0).equals(tree3));
	}

	@Test
	public final void testGetElement() {
		assertTrue(root.getElement().get(0).equals("t1_elt1"));
		assertTrue(root.getElement().get(1).equals("t1_elt2"));
		assertTrue(root.getElement().get(2).equals("t1_elt3"));
		assertTrue(tree4.getElement().get(0).equals("t4_elt1"));
		assertTrue(tree5.getElement().size() == 0);
	}

	@Test
	public final void testGetHighlighted() {
		List<Integer> tmpList = new ArrayList<Integer>();
		tmpList = root.getHighlighted();
		assertTrue(tmpList.size() == 0);

		tmpList = tree3.getHighlighted();
		assertTrue((tmpList.size() == 3) && (tmpList.get(0) == 3) && (tmpList.get(1) == 5) && (tmpList.get(2) == 7));

		tmpList = tree4.getHighlighted();
		assertTrue((tmpList.size() == 0));

		tmpList = tree5.getHighlighted();
		assertTrue((tmpList.size() == 0));
	}

	@Test
	public final void testAddHighlighted() {
		assertTrue((tree2.getHighlighted().size() == 1) && (tree2.getHighlighted().get(0) == 2));
		tree2.addHighlighted(3);
		assertTrue((tree2.getHighlighted().size() == 2) && (tree2.getHighlighted().get(1) == 3));
		tree2.addHighlighted(5, 6, 7, 8);
		assertTrue((tree2.getHighlighted().size() == 6) && (tree2.getHighlighted().get(4) == 7));
	}

	@Test
	public final void testRemove() {
		root.addChild(tree2);
		root.addChild(tree4);
		tree2.addChild(tree3);
		tree3.addChild(tree5);

//		assertTrue(root.getChildren().size() == 2);
//
//		tree2.remove();
//		assertNull(tree2.getParent());
//		assertFalse(root.getChildren().size() == 2);
//		assertTrue(root.getChildren().size() == 1);
//
//		assertTrue(tree3.getParent().equals(tree2));
	}
}
