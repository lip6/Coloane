package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IImportFrom;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

/**
 * Import GrML model into Coloane by converting the model to IGraph.
 * 
 * The Importer plugin dispatches the effective conversion to a class that inherits from ModelHandler.
 * The implementation is found using the configuration elements of the plugin.
 */
public final class Importer implements IImportFrom {

	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.extensions.importgrml"); //$NON-NLS-1$

	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.extensions.importgrml"; //$NON-NLS-1$

	@Override
	public IGraph importFrom(String file, IFormalism formalism, IProgressMonitor monitor) throws ExtensionException, CancellationException {
		try {
			// Step 1. extract formalism:
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader prereader = factory.createXMLStreamReader(new BufferedReader(new FileReader(file)));
			String formalismUrl = null;
			while (true) {
			    int event = prereader.next();
			    if ((event == XMLStreamConstants.START_ELEMENT) && (prereader.getLocalName().equals("model"))) {
			    	formalismUrl = prereader.getAttributeValue("", "formalismUrl");
			    	break;
			    }
			}
			prereader.close();
			LOGGER.fine("GrML formalism is '" + formalismUrl + "'.");
			// Step 2. find converter for this formalism:
			for (IConfigurationElement converter: Arrays.asList(Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID))) {
				LOGGER.fine("Inspecting '" + converter.getDeclaringExtension().getNamespaceIdentifier() + "' converter...");
				for (IConfigurationElement handledFormalism: Arrays.asList(converter.getChildren("formalism"))) {
					String fml = handledFormalism.getAttribute("fml-url");
					if (fml.equals(formalismUrl)) {
						LOGGER.fine("Formalism " + formalismUrl + " is handled.");
						XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(file));
						// Call tool:
						ModelHandler handler = null;
						if (!Arrays.asList(converter.getChildren("raw-converter")).isEmpty()) {
							LOGGER.fine("Found a raw converter '" + converter.getChildren("raw-converter")[0].getAttribute("handler") + "'.");
							handler = (ModelHandler) converter.getChildren("raw-converter")[0].createExecutableExtension("handler");
						} else if (!Arrays.asList(converter.getChildren("semi-automatic-converter")).isEmpty()) {
							LOGGER.fine("Found a helped converter.");
							handler = new HelpedHandler(converter.getChildren("semi-automatic-converter")[0]);
						}
						return handler.importFrom(reader, monitor);
					}
				}
				LOGGER.fine(converter.getName() + " does not handle formalism '" + formalismUrl + "'.");
			}
			throw new ExtensionException("Could not find any converter for formalism '" + formalismUrl + "'.");
		} catch (CancellationException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.warning("Error during import from GrML: " + e.getMessage());
			throw new ExtensionException();
		}
	}

}
