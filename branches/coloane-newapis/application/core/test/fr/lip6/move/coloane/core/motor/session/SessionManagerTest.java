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
		session1 = manager.newSession("s1"); //$NON-NLS-1$
		session2 = manager.newSession("s2"); //$NON-NLS-1$
		session3 = manager.newSession("s3"); //$NON-NLS-1$
	}

	@Test
	public final void testGetCurrentSession() {
		assertTrue(manager.getCurrentSession().equals(session1));
		manager.newSession("s4"); //$NON-NLS-1$
		assertTrue(manager.getCurrentSession().equals(session1));

		manager.disconnectAllSessions();
		assertNull(manager.getCurrentSession());
	}

	@Test
	public final void testGetSession() {
		assertTrue(manager.getSession("s1").equals(session1)); //$NON-NLS-1$
		assertNull(manager.getSession("s5")); //$NON-NLS-1$
		assertNull(manager.getSession(null));
		assertNull(manager.getSession("")); //$NON-NLS-1$
	}

	@Test
	public final void testNewSession() {
		assertNull(manager.getSession("s4")); //$NON-NLS-1$
		ISession session4 = manager.newSession("s4"); //$NON-NLS-1$
		assertNotNull(manager.getSession("s4")); //$NON-NLS-1$
		assertTrue(manager.getCurrentSession().equals(session1));
		assertTrue(manager.getSession("s4").equals(session4)); //$NON-NLS-1$

		ISession session4bis = manager.newSession("s4"); //$NON-NLS-1$
		assertTrue(session4bis.equals(session4));
		assertNull(manager.newSession("")); //$NON-NLS-1$
		assertNull(manager.newSession(null));

		manager.disconnectAllSessions();
		ISession session5 = manager.newSession("s5"); //$NON-NLS-1$
		assertTrue(manager.getCurrentSession().equals(session5));

		manager.deleteSession(manager.getCurrentSession().getName());
		session5 = manager.newSession("s5bis"); //$NON-NLS-1$
		assertTrue(manager.getCurrentSession().equals(session5));
	}

	@Test
	public final void testSuspendSession() {
		assertFalse(manager.suspendSession("s6")); //$NON-NLS-1$

		ISession current = manager.getCurrentSession();
		assertTrue(current.getStatus() == ISession.CLOSED);
		assertTrue(manager.suspendSession(current.getName()));
		assertNull(manager.getCurrentSession());

		session3.setStatus(ISession.CONNECTED);
		assertTrue(manager.suspendSession("s3")); //$NON-NLS-1$
		assertTrue(session3.getStatus() == ISession.SUSPENDED);
		assertNull(manager.getCurrentSession());
	}

	@Test
	public final void testResumeSession() {
		assertTrue(manager.resumeSession("s3")); //$NON-NLS-1$
		assertNotNull(manager.getCurrentSession());
		assertTrue(manager.getCurrentSession().equals(session3));

		assertTrue(manager.resumeSession("s1")); //$NON-NLS-1$
		assertNotNull(manager.getCurrentSession());
		assertTrue(manager.getCurrentSession().equals(session1));

		assertFalse(manager.resumeSession("s6")); //$NON-NLS-1$
		assertTrue(manager.getCurrentSession().equals(session1));
	}

	@Test
	public final void testDestroySession() {
		ISession session3 = manager.getSession("s3"); //$NON-NLS-1$
		assertNotNull(session3);

		manager.deleteSession("s3"); //$NON-NLS-1$
		assertNull(manager.getSession("s3")); //$NON-NLS-1$

		manager.deleteSession("s6"); //$NON-NLS-1$

		assertNotNull(manager.getCurrentSession());
		manager.deleteSession(manager.getCurrentSession().getName());
		assertNull(manager.getCurrentSession());

		manager.deleteSession("s2"); //$NON-NLS-1$
	}

	@Test
	public final void testDestroyAllSessions() {
		manager.resumeSession("s2"); //$NON-NLS-1$
		assertTrue(manager.getCurrentSession().equals(session2));

		manager.disconnectAllSessions();
		assertNull(manager.getCurrentSession());

		assertFalse(manager.resumeSession("s2")); //$NON-NLS-1$
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
