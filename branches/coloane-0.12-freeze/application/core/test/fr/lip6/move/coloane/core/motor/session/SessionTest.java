package fr.lip6.move.coloane.core.motor.session;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SessionTest {
	private ISession session1, session2, session3, session4;

	@Before
	public final void setUp() throws Exception {
		session1 = new Session("session1");
		session2 = new Session("session2");
		session2.setStatus(ISession.CONNECTED);
		session3 = new Session("session3");
		session3.setStatus(ISession.SUSPENDED);
		session4 = new Session("session3");
		session4.setStatus(ISession.ERROR);
	}

	@Test
	public final void testSuspend() {
		session1.suspend();
		assertTrue(session1.getStatus() == ISession.CLOSED);
		session2.suspend();
		assertTrue(session2.getStatus() == ISession.SUSPENDED);
		session3.suspend();
		assertTrue(session3.getStatus() == ISession.SUSPENDED);
		session4.suspend();
		assertTrue(session4.getStatus() == ISession.ERROR);
	}

	@Test
	public final void testResume() {
		session1.resume();
		assertTrue(session1.getStatus() == ISession.CLOSED);
		session2.resume();
		assertTrue(session2.getStatus() == ISession.CONNECTED);
		session3.resume();
		assertTrue(session3.getStatus() == ISession.CONNECTED);
		session4.resume();
		assertTrue(session4.getStatus() == ISession.ERROR);
	}

	@Test
	public final void testGetName() {
		assertTrue(session1.getName().equals("session1"));
		assertTrue(session2.getName().equals("session2"));
	}

	@Test
	public final void testGetModel() {
		assertNull(session1.getModel());
	}

	@Test
	public final void testGetAdminMenu() {
		assertNull(session1.getAdminMenu());
	}

	@Test
	public final void testGetServicesMenu() {
		assertNull(session1.getServicesMenu());
	}

	@Test
	public final void testGetStatus() {
		assertTrue(session1.getStatus() == ISession.CLOSED);
		assertTrue(session2.getStatus() == ISession.CONNECTED);
		assertTrue(session3.getStatus() == ISession.SUSPENDED);
		assertTrue(session4.getStatus() == ISession.ERROR);
	}

	@Test
	public final void testSetStatus() {
		session1.setStatus(ISession.ERROR);
		assertTrue(session1.getStatus() == ISession.ERROR);
		session2.setStatus(ISession.SUSPENDED);
		assertTrue(session2.getStatus() == ISession.SUSPENDED);
		session3.setStatus(ISession.CONNECTED);
		assertTrue(session3.getStatus() == ISession.CONNECTED);
	}
}
