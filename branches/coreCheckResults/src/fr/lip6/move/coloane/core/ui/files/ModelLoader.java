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
	 * @param <T> Handler type
	 * @param xmlFile IFile
	 * @param handler handler for parse the file
	 * @return handler after parse
	 */
	public static <T extends DefaultHandler> T loadFromXML(IFile xmlFile, T handler) {
		return loadFromXML(xmlFile.getLocationURI(), handler);
	}

	/**
	 * @param <T> Handler type
	 * @param xmlURI uri
	 * @param handler handler for parse the file
	 * @return handler after parse
	 */
	public static <T extends DefaultHandler> T loadFromXML(URI xmlURI, T handler) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		Schema schema = loadSchema();

		// Validate the model against the high level definition
		Validator validator = schema.newValidator();
		try {
			validator.validate(new StreamSource(xmlURI.toURL().openStream()));

			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlURI.toString(), handler);
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
	 * @param xmlFile XML File
	 * @return IGraph loaded or <code>null</code>
	 */
	public static IGraph loadGraphFromXML(IFile xmlFile) {
		return loadFromXML(xmlFile, new ModelHandler()).getGraph();
	}

	/**
	 * @param xmlURI URI to an XML File
	 * @return IGraph loaded or <code>null</code>
	 */
	public static IGraph loadGraphFromXML(URI xmlURI) {
		return loadFromXML(xmlURI, new ModelHandler()).getGraph();
	}
}
