package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;
import fr.lip6.move.coloane.api.interfaces.ISessionInfo;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;

/**
 * Cette classe est une factory pour le package CamiObject.
 *
 * @author kahoo && uu
 *
 */
public class CamiObjectFactory {

	/**
	 * nous crée un IFkInfo.
	 * @param nameService
	 * @param aboutService
	 * @param incremental
	 * @param resultatCalcule
	 * @return IFkInfo
	 */
	public static ISessionInfo getNewFkInfo (String nameService,String aboutService,String incremental,String resultatCalcule){
	 return new SessionInfo(aboutService, incremental, nameService,resultatCalcule);
}

	/**
	 *nous crée un IFkVersion.
	 * @param fkname
	 * @param fkmajor
	 * @param fkminor
	 * @return IFkVersion
	 */
	public static IConnectionVersion getNewFkVersion (String fkname,int fkmajor,int fkminor){
		 return new ConnectionVersion(fkname, fkmajor, fkminor);
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
