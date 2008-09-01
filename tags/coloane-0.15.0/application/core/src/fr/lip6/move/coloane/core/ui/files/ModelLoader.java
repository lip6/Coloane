package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.IOException;
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
	 * @param xmlFile fichier xml représentant le modèle
	 * @return IGraph construit à partir du fichier xml
	 */
	public static IGraph loadFromXML(IFile xmlFile) {
		ModelHandler modelHandler = new ModelHandler();

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
			validator.validate(new StreamSource(xmlFile.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			long debut = System.currentTimeMillis();
			saxParser.parse(xmlFile.getLocationURI().toString(), modelHandler);
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

		return modelHandler.getGraph();
	}
}
