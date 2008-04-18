package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.formalism.Formalism;
import fr.lip6.move.coloane.core.ui.ColoaneMessages;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.resources.IFile;

public final class ModelLoader {

	private ModelLoader() {	}

	public static IModelImpl loadFromXML(IFile xmlFile) throws Exception {
		IModelImpl model = null;

		// Creation de deux handler.
		// Le premier permet de checker si le fichier est valide pour Coloane
		// Le deuxieme permet de checker si le fichier est valide par rapport a un formalisme
		FileHandler globalHandler = new FileHandler();
		ModelHandler modelHandler = new ModelHandler();

		// Premiere verification concernant la presence de quelques elements cles (definis dans le global.xsd)
		// Presence de l'attribut formalism dans la balide model
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
//			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

//			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/resources/global.xsd")); //$NON-NLS-1$
//			Schema schema = schemaFactory.newSchema(schemaSource);

//			// Phase de validation du fichier par rapport au modele global
//			Validator validator = schema.newValidator();
//			validator.validate(new StreamSource(xmlFile.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlFile.getLocation().toString(), globalHandler);
		} catch (Exception e) {
			Coloane.getLogger().warning("Erreur lors du chargement du fichier " + xmlFile.getName()); //$NON-NLS-1$
			Coloane.getLogger().finer("Details : " + e.getMessage() + " " + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
			Coloane.showErrorMsg(ColoaneMessages.Editor_1 + xmlFile.getName() + " - " + e.toString() + " " + e.getMessage()); //$NON-NLS-2$ //$NON-NLS-1$
		}

		// Verifie la presence d'un indice sur le formalisme
		if (globalHandler.getModel().getFormalism() == "") {
			Coloane.getLogger().fine("Aucun formalisme trouve dans le fichier " + xmlFile.getName());
			return null;
		}

		// On determine le formalisme (objet) pour trouver le bon XSchema
		Formalism currentFormalism = Coloane.getDefault().getMotor().getFormalismManager().getFormalismByName(globalHandler.getModel().getFormalism());

		// Deuxieme verification pour voir si le fichier respecte le formalisme qu'il est cense utiliser
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Source schemaSource = new StreamSource(Coloane.class.getResourceAsStream("/resources/" + currentFormalism.getSchema())); //$NON-NLS-1$
			Schema schema = schemaFactory.newSchema(schemaSource);

			// Phase de validation du fichier par rapport au modele global
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile.getContents()));

			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlFile.getLocation().toString(), modelHandler);
		} catch (Exception e) {
			Coloane.getLogger().warning("Erreur lors du chargement du fichier " + xmlFile.getName()); //$NON-NLS-1$
			Coloane.getLogger().finer("Details : " + e.getMessage() + " " + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
			Coloane.showErrorMsg(ColoaneMessages.Editor_1 + xmlFile.getName() + " - " + e.toString() + " " + e.getMessage()); //$NON-NLS-2$ //$NON-NLS-1$
		}

		// Creation du modele a partir du modele generique
		try {
			model = new ModelImplAdapter(modelHandler.getModel());

			// Creation d'une session pour ce modele
			if (!Motor.getInstance().createSession(model, xmlFile.getName())) {
				Coloane.getLogger().warning("Erreur lors de la creation de la session associee au modele"); //$NON-NLS-1$
			}
		} catch (BuildException e) {
			Coloane.getLogger().warning("Erreur lors de la construction du modele"); //$NON-NLS-1$
			Coloane.showErrorMsg(ColoaneMessages.Editor_3 + e.getMessage());
		}
		return model;
	}
}
