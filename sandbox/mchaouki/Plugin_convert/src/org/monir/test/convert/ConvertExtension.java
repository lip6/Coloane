package org.monir.test.convert;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class ConvertExtension {
	private static final String EXTENSIN_POINT_ID = "org.monir.test.convert.convertiseur";
	private static final String NAME_EXTENSION = "name";
	private static final String CLASS_EXTENSION = "class";
	
	public static String[] getAllNameExtensionConvert(){		
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIN_POINT_ID);
		String[] nomsConvertiseurs = new String[contributions.length];
		for (int i = 0; i < contributions.length; i++) {
			nomsConvertiseurs[i] = contributions[i].getAttribute(NAME_EXTENSION);
		}
		return nomsConvertiseurs;
	}
	
	public static Convertiseur createConvertInstance(String nomConvertiseur){
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSIN_POINT_ID);
		IConfigurationElement extensionConvertiseur = null;
		for (int i = 0; i < contributions.length; i++) {
			if(contributions[i].getAttribute(NAME_EXTENSION).equals(nomConvertiseur)) {
				extensionConvertiseur = contributions[i];
				break;
			}
		}
		
		Convertiseur convertiseur = null;
		if(extensionConvertiseur != null) {
				try {
					System.out.println("c1");
					convertiseur = (Convertiseur)extensionConvertiseur.createExecutableExtension(CLASS_EXTENSION);
					System.out.println("c2"+convertiseur);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return convertiseur;
	}
}
