package fr.lip6.move.coloane.core.results;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTreeImplTest {
	private IResultTree tree1, tree2, tree3, tree4, tree5;

	@Before
	public final void setUp() throws Exception {
		tree1 = new ResultTreeImpl(1, "t1_elt1", "t1_elt2", "t1_elt3");
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
		tree1.addChild(tree2);
		tree3.addChild(tree1);
		assertTrue(tree1.getParent().equals(tree3));
		assertTrue(tree2.getParent().equals(tree1));

		/* Le tree3 n' pas de pere... C'estl a racine */
		assertNull(tree3.getParent());
	}

	@Test
	public final void testSetParent() {
		/* Le lien doit etre fait dans le sens fils -> pere seulelement */
		tree4.setParent(tree1);
		assertTrue(tree4.getParent().equals(tree1)); /* sens correct */
		assertFalse(tree1.getChildren().size() > 0);
	}

	@Test
	public final void testGetChildren() {
		tree3.addChild(tree1);
		tree3.addChild(tree2);
		List<IResultTree> list = tree3.getChildren();
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).equals(tree1));
		assertTrue(list.get(1).equals(tree2));
		assertFalse(tree1.getChildren().size() > 0);
	}

	@Test
	public final void testAddChild() {
		tree1.addChild(tree3);
		assertTrue(tree1.getChildren().size() == 1);
		assertTrue(tree3.getParent().equals(tree1));
		tree1.addChild(tree2);
		assertTrue(tree1.getChildren().size() == 2);
		assertTrue(tree2.getParent().equals(tree1));

		/* Verification de l'ordre d'insertion dans la liste */
		assertTrue(tree1.getChildren().get(0).equals(tree3));
	}

	@Test
	public final void testGetElement() {
		assertTrue(tree1.getElement().get(0).equals("t1_elt1"));
		assertTrue(tree1.getElement().get(1).equals("t1_elt2"));
		assertTrue(tree1.getElement().get(2).equals("t1_elt3"));
		assertTrue(tree4.getElement().get(0).equals("t4_elt1"));
		assertTrue(tree5.getElement().size() == 0);
	}

	@Test
	public final void testGetHighlighted() {
		List<Integer> tmpList = new ArrayList<Integer>();
		tmpList = tree1.getHighlighted();
		assertTrue((tmpList.size() == 1) && (tmpList.get(0) == 1));

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
		tree1.addChild(tree2);
		tree1.addChild(tree4);
		tree2.addChild(tree3);
		tree3.addChild(tree5);

		assertTrue(tree1.getChildren().size() == 2);

		tree2.remove();
		assertNull(tree2.getParent());
		assertFalse(tree1.getChildren().size() == 2);
		assertTrue(tree1.getChildren().size() == 1);

		assertTrue(tree3.getParent().equals(tree2));
	}

}
