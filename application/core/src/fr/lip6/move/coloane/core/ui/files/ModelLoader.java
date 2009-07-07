package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
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
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;

/**
 * Classe regroupant les outils utiles au chargement d'un modèle à partir d'un fichier xml
 */
public final class ModelLoader {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Classe ne contenant que des méthode statique.
	 */
	private ModelLoader() {	}

	/**
	 * Charge le schema WML de haut niveau pour vérifier que le modèle est un modèle de graphe supporté
	 * @return Le schéma ou <code>null</code> si le XSD n'a pas été trouvé
	 */
	private static Schema loadSchema() {
		Schema schema = null;

		try {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/resources/model.xsd")); //$NON-NLS-1$
			schema = schemaFactory.newSchema(schemaSource);
			return schema;
		} catch (SAXException e) {
			LOGGER.warning("Erreur lors du chargement du schema de validation XML"); //$NON-NLS-1$
			LOGGER.finer("Details : " + e.getMessage()); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * @param xmlFile fichier xml représentant le modèle
	 * @return IGraph construit à partir du fichier XML
	 */
	public static IGraph loadFromXML(IFile xmlFile) {
		return loadFromXML(xmlFile.getLocationURI());
	}

	/**
	 * @param xmlURI URI du fichier XML contenant le modèle à charger
	 * @return IGraph construit à partir du fichier XML
	 */
	public static IGraph loadFromXML(URI xmlURI) {
		ModelHandler modelHandler = new ModelHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		Schema schema = loadSchema();

		// Phase de validation du fichier par rapport au modele global
		Validator validator = schema.newValidator();
		try {
			validator.validate(new StreamSource(xmlURI.toURL().openStream()));

			SAXParser saxParser = factory.newSAXParser();
			long debut = System.currentTimeMillis();
			saxParser.parse(xmlURI.toString(), modelHandler);
			LOGGER.info("Temps de chargement : " + (System.currentTimeMillis() - debut) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (SAXException e) {
			LOGGER.warning("Impossible de parser le fichier. " + e.getMessage()); //$NON-NLS-1$
		} catch (IOException e) {
			LOGGER.warning("Erreur d'E/S : " + e.getMessage()); //$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			LOGGER.warning("Erreur lors de la création du parser. " + e.getMessage()); //$NON-NLS-1$
		}

		return modelHandler.getGraph();
	}

	/**
	 * @param file fichier xml
	 * @return le formalisme ou <code>null</code> en cas de problème.
	 */
	public static IFormalism loadFormalismFromXml(IFile file) {
		FormalismHandler formalismHandler = new FormalismHandler();

		// Declaration de quelques variables utiles ;o)
		Schema schema;
		SAXParserFactory factory;

		try {
			factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/resources/model.xsd")); //$NON-NLS-1$
			schema = schemaFactory.newSchema(schemaSource);
		} catch (SAXException e) {
			LOGGER.warning("Erreur lors du chargement du schema de validation XML"); //$NON-NLS-1$
			LOGGER.finer("Details : " + e.getMessage()); //$NON-NLS-1$
			return null;
		}

		// Phase de validation du fichier par rapport au modele global
		Validator validator = schema.newValidator();
		try {
			validator.validate(new StreamSource(file.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			long debut = System.currentTimeMillis();
			saxParser.parse(file.getLocationURI().toString(), formalismHandler);
			LOGGER.info("Temps de chargement : " + (System.currentTimeMillis() - debut) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (SAXException e) {
			LOGGER.warning("Impossible de parser le fichier. " + e.getMessage()); //$NON-NLS-1$
		} catch (IOException e) {
			LOGGER.warning("Erreur d'E/S : " + e.getMessage()); //$NON-NLS-1$
		} catch (CoreException e) {
			LOGGER.warning("Erreur lors de la lecture du fichier xml : " + e.getMessage()); //$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			LOGGER.warning("Erreur lors de la création du parser. " + e.getMessage()); //$NON-NLS-1$
		}

		return formalismHandler.getFormalism();
	}
}
