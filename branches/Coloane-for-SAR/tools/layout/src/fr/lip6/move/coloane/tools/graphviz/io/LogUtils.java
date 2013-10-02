/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.tools.graphviz.io;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

/**
 * Class with easier support for Status errors and log usage.
 *
 */
public final class LogUtils {
	
	/**
	 * Hide constructor.
	 */
	private LogUtils() { }
	
	/**
	 * Alias to log(new Status(etc..))
	 * @param severity error severity
	 * @param pluginId ID
	 * @param message error message
	 * @param exception core exception
	 */
	public static void log(int severity, String pluginId, String message, Throwable exception) {
		log(new Status(severity, pluginId, message, exception));
	}

	/**
	 * show a status error in log
	 * @param status the status exception
	 */
	public static void log(IStatus status) {
		if (!Platform.isRunning()) {
			System.err.println(status.getMessage());
			if (status.getException() != null) {
				status.getException().printStackTrace();
			}
			if (status.isMultiStatus()) {
				for (IStatus child : status.getChildren()) {
					log(child);
				}
			}
			return;
		}
		Bundle bundle = Platform.getBundle(status.getPlugin());
		Assert.isNotNull(bundle);
		Platform.getLog(bundle).log(status);
	}
	
	/** 
	 * An "error" level severity log.
	 * @param pluginId ID
	 * @param message message
	 * @param e the exception
	 */
	public static void logError(String pluginId, String message, Throwable e) {
		log(IStatus.ERROR, pluginId, message, e);
	}

	/**
	 * A warn level error.
	 * @param pluginId ID
	 * @param message msg
	 * @param e exception
	 */
	public static void logWarning(String pluginId, String message, Throwable e) {
		log(IStatus.WARNING, pluginId, message, e);
	}
	
	/**
	 * An info level error.
	 * @param pluginId ID
	 * @param message msg
	 * @param e exception
	 */
	public static void logInfo(String pluginId, String message, Throwable e) {
		log(IStatus.INFO, pluginId, message, e);
	}

}
