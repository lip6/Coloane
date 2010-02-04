package fr.lip6.move.coloane.extension.importExportTINA.importFromTINA;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.xml.sax.SAXException;

import fr.lip6.move.coloane.interfaces.model.IGraph;

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

		try {
			SAXParser saxParser = factory.newSAXParser();
			long debut = System.currentTimeMillis();
			saxParser.parse(xmlURI.toString(), modelHandler);
			LOGGER.info("Temps de chargement : " + (System.currentTimeMillis() - debut) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (SAXException e) {
			LOGGER.warning("Parse error while analyzing file "+ xmlURI + ".\n details:"+ e.getMessage()); //$NON-NLS-1$
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.warning("IO exception : " + e.getMessage()); //$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			LOGGER.warning("Error at ITS parser creation. " + e.getMessage()); //$NON-NLS-1$
		}

		return modelHandler.getGraph();
	}

}
