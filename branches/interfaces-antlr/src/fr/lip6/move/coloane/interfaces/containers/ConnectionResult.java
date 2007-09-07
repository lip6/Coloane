package fr.lip6.move.coloane.interfaces.containers;

/**
 * Objet utilise pour encapsuler <b>le resultat d'une authentification</b> au pres de la plateforme<br>
 * Doivent y figurer :
 * <ul>
 * 	<li>L'identifiant de la plateforme</li>
 * 	<li>La version de la plateforme</li>
 * </ul>
 */
public final class ConnectionResult {
	
	/** L'identifiant de la plateforme */
	private String FK_id;
	
	/** La version de la plateforme */
	private String FK_version;
	
	/* ------- */

	public String getFK_id() {
		return FK_id;
	}

	public void setFK_id(String fk_id) {
		FK_id = fk_id;
	}

	public String getFK_version() {
		return FK_version;
	}

	public void setFK_version(String fk_version) {
		FK_version = fk_version;
	}

}
