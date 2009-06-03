package fr.lip6.move.coloane.apicami.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

/**
 * Définition d'une modification de menu
 */
public class UpdateMenu implements IUpdateMenu {

	/** Le nom du menu racine */
    private String rootName;

    /** Le nom du service */
    private String serviceName;

    /** L'état de l'item désigné */
    private boolean state;

    /**
     * Constructeur
     * @param rootName Le menu racine
     * @param serviceName Le nom du service
     * @param state L'état de l'item
     */
    public UpdateMenu(String rootName, String serviceName, boolean state) {
    	this.rootName = rootName;
    	this.serviceName = serviceName;
    	this.state = state;
    }

    /**
     * {@inheritDoc}
     */
	public final String getRootName() {
		return this.rootName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return this.serviceName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean getState() {
		return this.state;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return serviceName + " [" + state + "]"; //$NON-NLS-1$//$NON-NLS-2$
	}
}
