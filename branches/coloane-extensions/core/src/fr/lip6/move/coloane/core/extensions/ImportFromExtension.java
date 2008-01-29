package fr.lip6.move.coloane.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import fr.lip6.move.coloane.core.interfaces.IImportFrom;

public class ImportFromExtension {
	/**
	 * Attributs du point d'extension 
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.imports";
	private static final String NAME_EXTENSION = "name";
	private static final String CLASS_EXTENSION = "class";
	
	/**
	 * Consulte le registre des extensions pour trouver les contributions au point d'extension 'EXTENSION_POINT_ID'
	 * et recupere la valeur de l'attribut 'NAME_EXTENSION' de chaque extension.
	 * @return le tableau des noms des convertiseurs presents
	 */
	public static String[] getAllNameExtensionConvert(){		
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		String[] nomsConvertiseurs = new String[contributions.length];
		for (int i = 0; i < contributions.length; i++) {
			nomsConvertiseurs[i] = contributions[i].getAttribute(NAME_EXTENSION);
		}
		return nomsConvertiseurs;
	}
	
	/**
	 * Créer une instance de convertiseur.
	 * @param nomConvertiseur nom du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IImportFrom createConvertInstance(String nomConvertiseur) throws CoreException{
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement extensionConvertiseur = null;
		for (int i = 0; i < contributions.length; i++) {
			if(contributions[i].getAttribute(NAME_EXTENSION).equals(nomConvertiseur)) {
				extensionConvertiseur = contributions[i];
				break;
			}
		}
		
		IImportFrom convertiseur = null;
		if(extensionConvertiseur != null) {
			convertiseur = (IImportFrom)extensionConvertiseur.createExecutableExtension(CLASS_EXTENSION);
		}
		return convertiseur;
	}
}
