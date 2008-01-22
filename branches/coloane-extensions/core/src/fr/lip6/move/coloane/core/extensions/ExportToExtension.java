package fr.lip6.move.coloane.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import fr.lip6.move.coloane.core.interfaces.IExportTo;

public class ExportToExtension {
	/**
	 * Attributs du point d'extension convert
	 */
	private static final String EXTENSIN_POINT_ID = "fr.lip6.move.coloane.core.exports";
	private static final String NAME_EXTENSION = "name";
	private static final String CLASS_EXTENSION = "class";
	
	/**
	 * Récupére les noms de tous les points d'extension qui définise un convertiseur
	 * @return le tableau des noms des convertiseurs présent
	 */
	public static String[] getAllNameExtensionConvert(){		
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIN_POINT_ID);
		String[] nomsConvertiseurs = new String[contributions.length];
		for (int i = 0; i < contributions.length; i++) {
			nomsConvertiseurs[i] = contributions[i].getAttribute(NAME_EXTENSION);
		}
		return nomsConvertiseurs;
	}
	
	/**
	 * Créer une instance de convertiseur
	 * @param nomConvertiseur nom du convertiseur a instancier
	 * @return un convertiseur
	 */
	public static IExportTo createConvertInstance(String nomConvertiseur){
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIN_POINT_ID);
		IConfigurationElement extensionConvertiseur = null;
		for (int i = 0; i < contributions.length; i++) {
			if(contributions[i].getAttribute(NAME_EXTENSION).equals(nomConvertiseur)) {
				extensionConvertiseur = contributions[i];
				break;
			}
		}
		
		IExportTo convertiseur = null;
		if(extensionConvertiseur != null) {
				try {
					System.out.println("a");
					convertiseur = (IExportTo)extensionConvertiseur.createExecutableExtension(CLASS_EXTENSION);
					System.out.println("b");
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return convertiseur;
	}
}
