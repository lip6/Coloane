package fr.lip6.move.coloane.core.extensions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import fr.lip6.move.coloane.core.interfaces.IImportTo;

public class ImportToExtension {
	/**
	 * Attributs du point d'extension 
	 */
	private static final String EXTENSIN_POINT_ID = "fr.lip6.move.coloane.core.imports";
	private static final String NAME_EXTENSION = "name";
	private static final String CLASS_EXTENSION = "class";
	
	/**
	 * Consulte le registre des extensions pour trouver les contributions au point d'extension 'EXTENSIN_POINT_ID'
	 * et récupère la valeur de l'attribut 'NAME_EXTENSION' de chaque extension.
	 * @return le tableau des noms des convertiseurs présents
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
	 * Créer une instance de convertiseur.
	 * @param nomConvertiseur nom du convertiseur à instancier
	 * @return un convertiseur
	 */
	public static IImportTo createConvertInstance(String nomConvertiseur){
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIN_POINT_ID);
		IConfigurationElement extensionConvertiseur = null;
		for (int i = 0; i < contributions.length; i++) {
			if(contributions[i].getAttribute(NAME_EXTENSION).equals(nomConvertiseur)) {
				extensionConvertiseur = contributions[i];
				break;
			}
		}
		
		IImportTo convertiseur = null;
		if(extensionConvertiseur != null) {
				try {
					System.out.println("a");
					convertiseur = (IImportTo)extensionConvertiseur.createExecutableExtension(CLASS_EXTENSION);
					System.out.println("b");
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return convertiseur;
	}
}
