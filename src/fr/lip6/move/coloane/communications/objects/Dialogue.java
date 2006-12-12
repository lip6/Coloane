package fr.lip6.move.coloane.communications.objects;

/**
 * Dialogue de base entre Framekit et l'IHM
 * @author DQS equipe 2 (Styx)
 */
public class Dialogue {
	
	/**
	 * identifiant unique de la boite de dialogue
	 */
	private int uniqueId;
	
	/**
	 * Contenu du message vehicule dans la boite de dialogue
	 */
	private String [] message;
	
	/**
	 * taille de message rempli
	 */
	private int messageLines;
	
	/**
	 * Constructeur
	 * @param uId id unique de la boite de dialogue
	 * @param msg contenu du message veheculé dans la boite de dialogue
	 */
	public Dialogue(int uId, String msg) {
		this.uniqueId = uId;
		this.message = new String [63];
		this.message[0] = msg;
		messageLines = 1;
	}
	
	/**
	 * Constructeur
	 * @param uId id unique de la boite de dialogue
	 * @param msg contenu du message veheculé dans la boite de dialogue
	 */
	public Dialogue(int uId, String [] msg) {
		this.uniqueId = uId;
		this.message = msg;
	}
	
	/**
	 * permet de recuperer le message vehiculé dans la boite de dialogue
	 * @return le message que l'on recupere
	 */
	public String [] getMessage() {
		return this.message;
	}
	
	/**
	 * ajout d'une ligne dans les attributs multiples
	 * @param aLine ligne a ajoutee
	 */
	public void addLine(String aLine) {
		message[this.messageLines] = aLine;
		this.messageLines++;
	}
	
	/**
	 * permet de recuperer l'identifiant de la boite de dialogue
	 * @return l'id de la boite de dialogue
	 */
	public int getUniqueId() {
		return this.uniqueId;
	}
	
	/**
	 * Transforme l'objet en String[] CAMI
	 * @return le tableau de String CAMI
	 */
	public String[] translateToCAMI() {
		String[] s = {"PAR ENCORE IMPLEMENTER"};
		return s;
	}
}
