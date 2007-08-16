package fr.lip6.move.coloane.communications.objects;

/**
 * Menu caracterisant un service de l'outil associe au modele courant ouvert par l'utilisateur
 * @author DQS equipe 2 (Styx)
 */
public class Service {

	/** Nom du service */
	private String name;

	/** Informe si le service est actif */
	private boolean active;

	/** Informe si le service est stoppable */
	private boolean suspensible;

	/** Informe si le dialoque est possible entre l'IHM et FK */
	private boolean dialogueAllowed;

	/** Message d'aide associe au service */
	private String helpMessage;

	/** Formalisme du resultat retourne */
	private String resultFormalism;

	/** Informe si le service est valide par defaut */
	private boolean defaultValidation;


	/**
	 * Constructeur
	 * @param serviceName nom du service
	 * @param serviceActive informe si le service est actif ou non
	 * @param serviceSuspensible informe si le service est stoppable ou pas0
	 * @param dialogueAllwd informe si le service autorise le dialogue
	 * @param defaultValid informe si le service est valide par defaut
	 * @param resFormalism formalisme du resultat retourne
	 */
	public Service(String serviceName, boolean serviceActive, boolean serviceSuspensible, boolean dialogueAllwd, boolean defaultValid, String resFormalism) {
		this.name = serviceName;
		this.active = serviceActive;
		this.suspensible = serviceSuspensible;
		this.dialogueAllowed = dialogueAllwd;
		this.defaultValidation = defaultValid;
		this.resultFormalism = resFormalism;
		this.helpMessage = "";
	}

	/**
	 * accesseur
	 * @return le nom du service
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * accesseur
	 * @return si le service est actif
	 */
	public final boolean isActive() {
		return this.active;
	}

	/**
	 * seteur
	 * @param act definit si le service est actif
	 */
	public final void setActive(boolean act) {
		this.active = act;
	}

	/**
	 * accesseur
	 * @return si le service est stoppable
	 */
	public final boolean isSuspensible() {
		return this.suspensible;
	}

	/**
	 * accesseur
	 * @return si le service autorise le dialogue
	 */
	public final boolean isDialogueAllowed() {
		return this.dialogueAllowed;
	}

	/**
	 * accesseur
	 * @return si le service est valide par defaut
	 */
	public final boolean isDefaultValid() {
		return this.defaultValidation;
	}

	/**
	 * accesseur
	 * @return le message d'aide associe au service
	 */
	public final String getHelpMessage() {
		return this.helpMessage;
	}

	/**
	 * seteur
	 * @param hlp message d'aide associe au service
	 */
	public final void setHelpMessage(String hlp) {
		this.helpMessage = hlp;
	}

	/**
	 * accesseur
	 * @return le type de formalisme du resultat
	 */
	public final String getResultFormalism() {
		return this.resultFormalism;
	}

	/**
	 * transforme l'objet en CAMI
	 * @return un StringBuffer contenant tout le CAMI
	 */
	public final StringBuffer translateToCAMI() {
		StringBuffer strBuf = new StringBuffer("");

		strBuf.append("" + this.name.length() + ":" + this.name + ",");
		strBuf.append(")");

		return strBuf;
	}
}
