package fr.lip6.move.coloane.api.objects;

import fr.lip6.move.coloane.interfaces.IMenuCom;

/**
 * Cette classe definie un menu d'un menu de service ou d'administration<br>
 * Un menu se compose de plusieurs elements :
 * <ul>
 * 	<li>Un nom de service associe</li>
 * 	<li>Un nom de pere dans la hierarchie des menus</li>
 * 	<li>Un statut (actif, inactif)</li>
 * </ul> 
 */

public class MenuCom implements IMenuCom {
	private String fatherName;
	private String serviceName;
	private boolean enabled;

	/**
	 * Constructeur
	 * @param serviceName Le nom du service associe au menu
	 * @param fatherName Le nom du pere du menu
	 * @param enabled Le status (actif ou inactif) du menu : true = actif
	 */
	public MenuCom (String serviceName, String fatherName, boolean enabled) {
		this.fatherName = fatherName;
		this.serviceName = serviceName;
		this.enabled = enabled;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#isEnabled()
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#getFatherName()
	 */
	public String getFatherName() {
		return fatherName;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#setFatherName(String)
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#getServiceName()
	 */
	public String getServiceName() {
		return serviceName;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#setServiceName(String)
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
