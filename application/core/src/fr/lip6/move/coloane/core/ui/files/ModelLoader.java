package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.resources.IFile;
import org.xml.sax.SAXException;

public final class ModelLoader {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private ModelLoader() {	}

	public static IGraph loadFromXML(IFile xmlFile) {
		IGraph graph = null;

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
			Coloane.getLogger().warning("Erreur lors du chargement du schema de validation XML"); //$NON-NLS-1$
			Coloane.getLogger().finer("Details : " + e.getMessage()); //$NON-NLS-1$
			return null;
		}

		try {
			// Phase de validation du fichier par rapport au modele global
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			long debut = System.currentTimeMillis();
			saxParser.parse(xmlFile.getLocationURI().toString(), modelHandler);
			LOGGER.info("Temps de chargement : " + (System.currentTimeMillis() - debut) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			LOGGER.warning("Erreur lors de la lecture du fichier " + xmlFile.getName()); //$NON-NLS-1$
			LOGGER.finer("Details : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(Messages.ModelLoader_0 + xmlFile.getName());
			return null;
		}

		// Creation du modele a partir du modele generique
		graph = modelHandler.getGraph();

		// Creation d'une session pour ce modele
		if (graph != null && !Motor.getInstance().createSession(graph, xmlFile.getName())) {
			LOGGER.warning("Erreur lors de la creation de la session associee au modele"); //$NON-NLS-1$
		}
		return graph;
	}
}
