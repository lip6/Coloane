package fr.lip6.move.coloane.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import fr.lip6.move.coloane.core.interfaces.IExportTo;

public class ExportToExtension {
	/**
	 * Attributs du point d'extension 'exports'
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.exports";
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
	 * Creer une instance de convertiseur
	 * @param nomConvertiseur nom du convertiseur a instancier
	 * @return un convertiseur
	 * @throws CoreException Exception lors de la creation de une instance
	 */
	public static IExportTo createConvertInstance(String nomConvertiseur) throws CoreException{
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		IConfigurationElement extensionConvertiseur = null;
		for (int i = 0; i < contributions.length; i++) {
			if(contributions[i].getAttribute(NAME_EXTENSION).equals(nomConvertiseur)) {
				extensionConvertiseur = contributions[i];
				break;
			}
		}
		
		IExportTo convertiseur = null;
		if(extensionConvertiseur != null) {
			convertiseur = (IExportTo)extensionConvertiseur.createExecutableExtension(CLASS_EXTENSION);
		}
		return convertiseur;
	}
}
