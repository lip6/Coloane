package fr.lip6.move.coloane.core.motor.session;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionManagerTest {
	private ISessionManager manager;
	private ISession session1, session2, session3;

	@Before
	public final void setUp() throws Exception {
		manager = SessionManager.getInstance();
		session1 = manager.newSession("s1");
		session2 = manager.newSession("s2");
		session3 = manager.newSession("s3");
	}

	@Test
	public final void testGetCurrentSession() {
		assertTrue(manager.getCurrentSession().equals(session1));
		manager.newSession("s4");
		assertTrue(manager.getCurrentSession().equals(session1));

		manager.destroyAllSessions();
		assertNull(manager.getCurrentSession());
	}

	@Test
	public final void testGetSession() {
		assertTrue(manager.getSession("s1").equals(session1));
		assertNull(manager.getSession("s5"));
		assertNull(manager.getSession(null));
		assertNull(manager.getSession(""));
	}

	@Test
	public final void testNewSession() {
		assertNull(manager.getSession("s4"));
		ISession session4 = manager.newSession("s4");
		assertNotNull(manager.getSession("s4"));
		assertTrue(manager.getCurrentSession().equals(session1));
		assertTrue(manager.getSession("s4").equals(session4));

		ISession session4bis = manager.newSession("s4");
		assertTrue(session4bis.equals(session4));
		assertNull(manager.newSession(""));
		assertNull(manager.newSession(null));

		manager.destroyAllSessions();
		ISession session5 = manager.newSession("s5");
		assertTrue(manager.getCurrentSession().equals(session5));

		manager.destroySession(manager.getCurrentSession().getName());
		session5 = manager.newSession("s5bis");
		assertTrue(manager.getCurrentSession().equals(session5));
	}

	@Test
	public final void testSuspendSession() {
		assertFalse(manager.suspendSession("s6"));

		ISession current = manager.getCurrentSession();
		assertTrue(current.getStatus() == ISession.CLOSED);
		assertTrue(manager.suspendSession(current.getName()));
		assertNull(manager.getCurrentSession());

		session3.setStatus(ISession.CONNECTED);
		assertTrue(manager.suspendSession("s3"));
		assertTrue(session3.getStatus() == ISession.SUSPENDED);
		assertNull(manager.getCurrentSession());
	}

	@Test
	public final void testResumeSession() {
		assertTrue(manager.resumeSession("s3"));
		assertNotNull(manager.getCurrentSession());
		assertTrue(manager.getCurrentSession().equals(session3));

		assertTrue(manager.resumeSession("s1"));
		assertNotNull(manager.getCurrentSession());
		assertTrue(manager.getCurrentSession().equals(session1));

		assertFalse(manager.resumeSession("s6"));
		assertTrue(manager.getCurrentSession().equals(session1));
	}

	@Test
	public final void testDestroySession() {
		ISession session3 = manager.getSession("s3");
		assertNotNull(session3);

		assertTrue(manager.destroySession("s3"));
		assertNull(manager.getSession("s3"));

		assertFalse(manager.destroySession("s6"));

		assertNotNull(manager.getCurrentSession());
		assertTrue(manager.destroySession(manager.getCurrentSession().getName()));
		assertNull(manager.getCurrentSession());
	}

	@Test
	public final void testDestroyAllSessions() {
		manager.resumeSession("s2");
		assertTrue(manager.getCurrentSession().equals(session2));

		manager.destroyAllSessions();
		assertNull(manager.getCurrentSession());

		assertFalse(manager.resumeSession("s2"));
	}

	@Test
	public final void testAuthentication() {
		assertFalse(manager.isAuthenticated());
		manager.setAuthenticated(true);
		assertTrue(manager.isAuthenticated());
		manager.setAuthenticated(false);
		assertFalse(manager.isAuthenticated());
	}
}
