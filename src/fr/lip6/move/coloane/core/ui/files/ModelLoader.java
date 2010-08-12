package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.resources.IFile;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Utility class that help to load a model from an XML file.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public final class ModelLoader {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Constructor */
	private ModelLoader() {	}

	/**
	 * Load the high level XML schema to check that the model is currently supported.
	 * @return The XSchema or <code>null</code> if the XSD has not been found
	 */
	private static Schema loadSchema() {
		Schema schema = null;

		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/resources/model.xsd")); //$NON-NLS-1$
			schema = schemaFactory.newSchema(schemaSource);
			return schema;
		} catch (SAXException e) {
			LOGGER.warning("Unable to load the XML Schame"); //$NON-NLS-1$
			LOGGER.warning("Details: " + e.getMessage()); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * Load a model from an XML description
	 * @param modelSource A {@link StreamSource} of the model
	 * @param modelString The model under the form of a Java string
	 * @param <T> Handler The type of handler that can manage the incoming model
	 * @return handler after parse. Some properties will be set during the process
	 * @see FormalismHandler
	 * @see ModelHandler
	 */
	private static <T extends DefaultHandler> T loadFromXML(StreamSource modelSource, String modelString, T handler) {
		try {
			// Fetch the XML schema (high level model definition)
			Schema schema = loadSchema();
			// Validate the model against the definition
			Validator validator = schema.newValidator();
			validator.validate(modelSource);

			// Build the parsing factory & Parse
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(modelString, handler);
		} catch (SAXException e) {
			LOGGER.warning("Unable to parse the file: " + e.getMessage()); //$NON-NLS-1$
		} catch (IOException e) {
			LOGGER.warning("I/O error: " + e.getMessage()); //$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			LOGGER.warning("Wrong parser initialization: " + e.getMessage()); //$NON-NLS-1$
		}

		return handler;
	}
	
	/**
	 * Load a model from an XML file
	 * @param xmlFile IThe file that contains the model description
	 * @param <T> Handler The type of handler that can manage the incoming model
	 * @return handler after parse. Some properties will be set during the process
	 * @see FormalismHandler
	 * @see ModelHandler
	 */
	public static <T extends DefaultHandler> T loadFromXML(IFile xmlFile, T handler) {
		try {
		URI modelURI = xmlFile.getLocationURI();
		StreamSource modelSource = new StreamSource(modelURI.toURL().openStream());
		return loadFromXML(modelSource, modelURI.toString(), handler);
		} catch (IOException e) {
			LOGGER.warning("I/O error: " + e.getMessage()); //$NON-NLS-1$
		}
		return null;
	}
	

	/**
	 * Load a model from an XML file.<br>
	 * The model is entirely read thanks to {@link ModelHandler}.<br>
	 * You can use this method as the simple way to load a model from a file
	 * @param xmlFile IThe file that contains the model description
	 * @return The corresponding {@link IGraph}
	 */
	public static IGraph loadGraphFromXML(IFile xmlFile) {
		return loadFromXML(xmlFile, new ModelHandler()).getGraph();
	}
	
	/**
	 * Load a model from an XML Java string.<br>
	 * The model is entirely read thanks to {@link ModelHandler}.<br>
	 * You can use this method as the simple way to load a model from a XML Java string.
	 * @param xmlModel The Java string that is the model description
	 * @return The corresponding {@link IGraph}
	 */
	public static IGraph loadGraphFromXMLString(String xmlModel) {
		StreamSource modelSource = new StreamSource(new java.io.StringReader(xmlModel));
		return loadFromXML(modelSource, xmlModel, new ModelHandler()).getGraph();
	}
}
