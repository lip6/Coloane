package fr.lip6.move.graphviz.io;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

public class LogUtils {
	public static void log(int severity, String pluginId, String message, Throwable exception) {
		log(new Status(severity, pluginId, message, exception));
	}

	public static void log(IStatus status) {
		if (!Platform.isRunning()) {
			System.err.println(status.getMessage());
			if (status.getException() != null)
				status.getException().printStackTrace();
			if (status.isMultiStatus())
				for (IStatus child : status.getChildren())
					log(child);
			return;
		}
		Bundle bundle = Platform.getBundle(status.getPlugin());
		Assert.isNotNull(bundle);
		Platform.getLog(bundle).log(status);
	}
	
	public static void logError(String pluginId, String message, Throwable e) {
		log(IStatus.ERROR, pluginId, message, e);
	}

	public static void logWarning(String pluginId, String message, Throwable e) {
		log(IStatus.WARNING, pluginId, message, e);
	}
	
	public static void logInfo(String pluginId, String message, Throwable e) {
		log(IStatus.INFO, pluginId, message, e);
	}

}
