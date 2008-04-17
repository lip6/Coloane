package fr.lip6.move.coloane.extension.importExportCAMI.importFromCAMI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Vector;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalism.Messages;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

public class ImportFromImpl implements IImportFrom {

	/**
	 * Importe un modele CAMI
	 * @param filePath nom de fchier a importer
	 * @return le model adapte correspondant
	 * @throws ColoaneException si le fichier n'est pas valide
	 */
	public IModelImpl importFrom(String filePath, String formalism) throws ColoaneException {
		IModel genericModel = null;

		try {
			File toImport = new File(filePath);

			// Un vecteur de commandes de construction
			Vector<String> commands = new Vector<String>();

			// Lecture du fichier et stockage des lignes CAMI dans un buffer de commandes
			BufferedReader buffer = new BufferedReader(new FileReader(toImport));
			while (buffer.ready()) {
				commands.add(buffer.readLine());
			}
			buffer.close();

			// Construction du model generique en utilisant le traducteur CAMI
			genericModel = new Model(commands, new CamiTranslator());
			genericModel.setFormalism(formalism);
			Coloane.getLogger().fine("Le modele importe est identifie comme du formalisme :"+formalism);

		} catch (SyntaxErrorException e) {
			Coloane.getLogger().warning("Erreur : "+e.getMessage());
			throw new ColoaneException(Messages.FormalismManager_1);

			// Erreur de localisation du fichier
		} catch (FileNotFoundException e) {
			throw new ColoaneException(Messages.FormalismManager_2);

			// Erreur de lecture du fichier
		} catch (IOException e) {
			throw new ColoaneException(Messages.FormalismManager_3);

			// Autre erreur
		} catch (NoSuchElementException e) {
			throw new ColoaneException(Messages.FormalismManager_4);
		}

		// On indique au modele l'identifiant maximal
		// Cette etape est importante, si on souhaite ajouter de nouveaux noeuds et arcs au modele
		genericModel.setMaxId(genericModel.getMaxId());

		try {
			return new ModelImplAdapter(genericModel);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Erreur lors de la construction du modele"); //$NON-NLS-1$
			throw new ColoaneException(e.getMessage());
		}

	}
}