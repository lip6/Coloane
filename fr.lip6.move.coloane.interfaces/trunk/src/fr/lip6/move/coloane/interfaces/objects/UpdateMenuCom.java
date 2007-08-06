package fr.lip6.move.coloane.interfaces.objects;

/**
 * Cette classe decrit une mise a jour d'un element d'un menu de service.
 * Chaque mise a jour comporte obligatoirement :
 * <ul>
 * 	<li>Un menu de reference</li>
 * 	<li>Un service</li>
 * 	<li>Un nouvel etat</li>
 * </ul>
 */
public abstract class UpdateMenuCom implements IUpdateMenuCom {

	/** Le menu root auquel le service est attache */
	private String root;

	/** Le service qui doit etre mis a jour */
	private String service;

	/** Le nouvel etat */
	private boolean state;

	/**
	 * Constructeur
	 * @param root Le menu racine auquel le service est attache
	 * @param service Le service qui doit etre mis a jour
	 * @param state Le nouvel etat du service
	 */
	public UpdateMenuCom(String menuRoot, String menuService, boolean menuState) {
		this.root = menuRoot;
		this.service = menuService;
		this.state = menuState;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom#getRoot()
	 */
	public final String getRoot() {
		return root;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom#getService()
	 */
	public final String getService() {
		return service;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom#getState()
	 */
	public final boolean getState() {
		return state;
	}
}
