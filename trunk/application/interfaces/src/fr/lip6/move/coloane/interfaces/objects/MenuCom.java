package fr.lip6.move.coloane.interfaces.objects;


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
	public MenuCom(String menuServiceName, String menuFatherName, boolean state) {
		this.fatherName = menuFatherName;
		this.serviceName = menuServiceName;
		this.enabled = state;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#isEnabled()
	 */
	public final boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#setEnabled(boolean)
	 */
	public final void setEnabled(boolean state) {
		this.enabled = state;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#getFatherName()
	 */
	public final String getFatherName() {
		return fatherName;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.IMenuCom#getServiceName()
	 */
	public final String getServiceName() {
		return serviceName;
	}
}
