package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.ui.ColoaneMessages;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
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

		// Creation de deux handler.
		// Le premier permet de checker si le fichier est valide pour Coloane
		// Le deuxieme permet de checker si le fichier est valide par rapport a un formalisme
		FileHandler globalHandler = new FileHandler();
		ModelHandler modelHandler = new ModelHandler();

		// Premiere verification concernant la presence de quelques elements cles (definis dans le global.xsd)
		// Presence de l'attribut formalism dans la balide model
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/resources/global.xsd")); //$NON-NLS-1$
			Schema schema = schemaFactory.newSchema(schemaSource);

			// Phase de validation du fichier par rapport au modele global
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlFile.getLocation().toString(), globalHandler);
		} catch (Exception e) {
			LOGGER.warning("Erreur lors du chargement du fichier " + xmlFile.getName()); //$NON-NLS-1$
			LOGGER.finer("Details : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(ColoaneMessages.ModelLoader_2 + xmlFile.getName());
			return null;
		}

		// Verifie la presence d'un indice sur le formalisme
		if (globalHandler.getGraph().getFormalism() == null) {
			LOGGER.fine("Aucun formalisme trouve dans le fichier " + xmlFile.getName()); //$NON-NLS-1$
			return null;
		}

		// On determine le formalisme (objet) pour trouver le bon XSchema
		IFormalism currentFormalism = globalHandler.getGraph().getFormalism();

		// Declaration de quelques variables
		Schema schema;
		SAXParserFactory factory;

		// Deuxieme verification pour voir si le fichier respecte le formalisme qu'il est cense utiliser
		try {
			factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/" + currentFormalism.getSchema())); //$NON-NLS-1$
			schema = schemaFactory.newSchema(schemaSource);
		} catch (SAXException e) {
			Coloane.getLogger().warning("Erreur lors du chargement du schema de validation " + currentFormalism.getSchema()); //$NON-NLS-1$
			Coloane.getLogger().finer("Details : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(ColoaneMessages.ModelLoader_2 + xmlFile.getName() + ColoaneMessages.ModelLoader_0);
			return null;
		}

		try {
			// Phase de validation du fichier par rapport au modele global
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			long debut = System.currentTimeMillis();
			saxParser.parse(xmlFile.getLocation().toString(), modelHandler);
			LOGGER.info("Temps de chargement : " + (System.currentTimeMillis() - debut) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			LOGGER.warning("Erreur lors de la lecture du fichier " + xmlFile.getName()); //$NON-NLS-1$
			LOGGER.finer("Details : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(ColoaneMessages.ModelLoader_2 + xmlFile.getName() + ColoaneMessages.ModelLoader_1);
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
