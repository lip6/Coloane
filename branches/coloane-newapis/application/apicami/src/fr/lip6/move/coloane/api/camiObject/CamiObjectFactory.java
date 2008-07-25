package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;

/**
 * Cette classe est une factory pour le package CamiObject.
 *
 * @author kahoo && uu
 *
 */
public final class CamiObjectFactory {

	/**
	 * Constructeur privé
	 */
	private CamiObjectFactory() { }

	/**
	 * Création d'un objet ISessionInfo
	 * @param nameService TODO A documenter
	 * @param aboutService TODO A documenter
	 * @param incremental TODO A documenter
	 * @param resultatCalcule TODO A documenter
	 * @return L'objet contenant les informations sur la session
	 */
	public static ISessionInfo getNewFkInfo(String nameService, String aboutService, String incremental, int resultatCalcule) {
	 return new SessionInfo(aboutService, incremental, nameService, resultatCalcule);
}

	/**
	 *nous crée un IFkVersion.
	 * @param fkname
	 * @param fkmajor
	 * @param fkminor
	 * @return IFkVersion
	 */
	public static IConnectionInfo getNewFkVersion (String fkname,int fkmajor,int fkminor){
		 return new ConnectionInfo(fkname, fkmajor, fkminor);
	}

	/**
	 * nous crée un IMenu.
	 * @param parent
	 * @param name
	 * @param questionType
	 * @param questionBehavior
	 * @param valid
	 * @param dialogAllowed
	 * @param stopAuthorized
	 * @param outputFormalism
	 * @param activate
	 * @param children
	 * @return IMenu
	 */
	public static IMenu getNewMenu (IMenu parent, String name, int questionType,
			int questionBehavior, boolean valid, boolean dialogAllowed,
			boolean stopAuthorized, String outputFormalism, boolean activate,
			ArrayList<IMenu> children){
		 return new Menu(parent, name, questionType,
					questionBehavior, valid, dialogAllowed, stopAuthorized,
					outputFormalism, activate, children);
	}

	/**
	 * nous crée un IUpdateItem.
	 * @param rootName
	 * @param serviceName
	 * @param state
	 * @return IUpdateItem
	 */
	public static IUpdateItem getNewUpdateItem(String rootName,String serviceName,boolean state){
		 return new UpdateItem(rootName, serviceName, state);
	}

	


}
