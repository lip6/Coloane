/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fr.lip6.move.graphviz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Executes an external process synchronously, allowing the client to define a
 * maximum amount of time for the process to complete.
 */
public class ProcessController {
	/**
	 * Thrown when a process being executed exceeds the maximum amount of time
	 * allowed for it to complete.
	 */
	public class TimeOutException extends Exception {
		/**
		 * All serializable objects should have a stable serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		public TimeOutException() {
			super();
		}

		public TimeOutException(String message) {
			super(message);
		}
	}

	private boolean finished;
	private OutputStream forwardStdErr;
	private InputStream forwardStdIn;
	private OutputStream forwardStdOut;
	private boolean killed;
	private String[] params;
	private Process process;
	private long startupTime;
	private long timeLimit;
	private String[] env;
	private File baseDir;

	/**
	 * Constructs an instance of ProcessController. This does not creates an OS
	 * process. <code>run()</code> does that.
	 * 
	 * @param timeout
	 *            the maximum time the process should take to run
	 * @param params
	 *            the parameters to be passed to the controlled process
	 */
	public ProcessController(long timeout, String[] params, String[] env, File baseDir) {
		timeLimit = timeout;
		this.params = params;
		this.env = env;
		this.baseDir = baseDir;
	}

	private void controlProcess() {
		new Thread("Process controller") {
			@Override
			public void run() {
				while (!isFinished() && !timedOut())
					synchronized (this) {
						try {
							wait(100);
						} catch (InterruptedException e) {
							break;
						}
					}
				kill();
			}
		}.start();
	}

	/**
	 * Causes the process to start executing. This call will block until the
	 * process has completed. If <code>timeout</code> is specified, the
	 * process will be interrupted if it takes more than the specified amount of
	 * time to complete, causing a <code>TimedOutException</code> to be
	 * thrown. Specifying zero as <code>timeout</code> means the process is
	 * not time constrained.
	 * 
	 * @return the process exit value
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws TimeOutException
	 *             if the process did not complete in time
	 */
	public int execute() throws InterruptedException, IOException, TimeOutException {
		startupTime = System.currentTimeMillis();
		process = Runtime.getRuntime().exec(params, env, baseDir);
		if (forwardStdErr != null)
			forwardStream("stderr", process.getErrorStream(), forwardStdErr);
		if (forwardStdOut != null)
			forwardStream("stdout", process.getInputStream(), forwardStdOut);
		if (forwardStdIn != null)
			forwardStream("stdin", forwardStdIn, process.getOutputStream());
		if (timeLimit > 0)
			// ensures process execution time does not exceed the time limit
			controlProcess();
		try {
			return process.waitFor();
		} finally {
			markFinished();
			if (wasKilled())
				throw new TimeOutException();
		}
	}

	/**
	 * Forwards the process standard error output to the given output stream.
	 * Must be called before execution has started.
	 * 
	 * @param err
	 *            an output stream where to forward the process standard error
	 *            output to
	 */
	public void forwardErrorOutput(OutputStream err) {
		forwardStdErr = err;
	}

	/**
	 * Forwards the given input stream to the process standard input. Must be
	 * called before execution has started.
	 * 
	 * @param in
	 *            an input stream where the process standard input will be
	 *            forwarded to
	 */
	public void forwardInput(InputStream in) {
		forwardStdIn = in;
	}

	/**
	 * Forwards the process standard output to the given output stream. Must be
	 * called before execution has started.
	 * 
	 * @param out
	 *            an output stream where to forward the process standard output
	 *            to
	 */
	public void forwardOutput(OutputStream out) {
		forwardStdOut = out;
	}

	private void forwardStream(final String name, final InputStream in, final OutputStream out) {
		new Thread("Stream forwarder [" + name + "]") {
			@Override
			public void run() {
				try {
					while (!isFinished()) {
						while (safeIsAvailable(in) > 0)
							out.write(in.read());
						synchronized (this) {
							this.wait(100);
						}
					}
					out.flush();
				} catch (IOException e) {
					GraphVizActivator.logUnexpected(null, e);
				} catch (InterruptedException e) {
					GraphVizActivator.logUnexpected(null, e);
				}
			}

			private int safeIsAvailable(InputStream in) {
				try {
					return in.available();
				} catch (IOException e) {
					// stream has been closed
					return 0;
				}
			}
		}.start();
	}

	/**
	 * Returns the controled process. Will return <code>null</code> before
	 * <code>execute</code> is called.
	 * 
	 * @return the underlying process
	 */
	public Process getProcess() {
		return process;
	}

	protected synchronized boolean isFinished() {
		return finished;
	}

	/**
	 * Kills the process. Does nothing if it has been finished already.
	 */
	public void kill() {
		synchronized (this) {
			if (isFinished())
				return;
			killed = true;
		}
		process.destroy();
	}

	private synchronized void markFinished() {
		finished = true;
		notifyAll();
	}

	protected synchronized boolean timedOut() {
		return System.currentTimeMillis() - startupTime > timeLimit;
	}

	/**
	 * Returns whether the process was killed due to a time out.
	 * 
	 * @return <code>true</code> if the process was killed, <code>false</code>
	 *         if the completed normally
	 */
	public boolean wasKilled() {
		return killed;
	}
}
