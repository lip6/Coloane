package fr.lip6.move.coloane.extensions.exporttogml;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * A simple class encapsulating an instance of an exporting class and the corresponding FML URL
 * 
 * @author Maximilien Colange
 *
 */
public final class Exporter {
	/**
	 * Extension attributes
	 */
	//private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.extensions.exporttogml"; //$NON-NLS-1$
	//private static final String NAME_EXTENSION = "name"; //$NON-NLS-1$
	//private static final String DESCRIPTION_EXTENSION = "description"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$
	private static final String FMLURL_EXTENSION = "fmlurl"; //$NON-NLS-1$
	
	private String formalismURL;
	private IGMLExport instance;
	
	/**
	 * The constructor
	 * 
	 * @param contrib an extension contribution
	 * @throws CoreException if the creation of the instance of the exporting class fails
	 * @throws MalformedURLException if the formalism URL is not a correct URL
	 */
	Exporter(IConfigurationElement contrib) throws CoreException, MalformedURLException {
		String form = contrib.getAttribute(FMLURL_EXTENSION);
		// test whether the given formalism is a correct URL
		new URL(form);
		formalismURL = form;
		instance = (IGMLExport) contrib.createExecutableExtension(CLASS_EXTENSION);
	}
	
	/**
	 * Accessor for <code>formalismURL</code>
	 * 
	 * @return formalismURL
	 */
	String getFormalism() {
		return formalismURL;
	}
	
	/**
	 * Accessor for <code>instance</code>
	 * 
	 * @return <code>instance</code>
	 */
	IGMLExport getInstance() {
		return instance;
	}
}
