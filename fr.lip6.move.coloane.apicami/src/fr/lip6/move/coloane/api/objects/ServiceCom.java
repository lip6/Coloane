package fr.lip6.move.coloane.api.objects;

public class ServiceCom {
	
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
	public ServiceCom(String serviceName, boolean serviceActive,boolean serviceSuspensible, boolean dialogueAllwd,boolean defaultValid, String resFormalism) {
		this.name = serviceName;
		this.active = serviceActive;
		this.suspensible = serviceSuspensible;
		this.dialogueAllowed = dialogueAllwd;
		this.defaultValidation = defaultValid;
		this.resultFormalism = resFormalism;
		this.helpMessage = "";		
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public boolean isDefaultValidation() {
		return defaultValidation;
	}


	public void setDefaultValidation(boolean defaultValidation) {
		this.defaultValidation = defaultValidation;
	}


	public boolean isDialogueAllowed() {
		return dialogueAllowed;
	}


	public void setDialogueAllowed(boolean dialogueAllowed) {
		this.dialogueAllowed = dialogueAllowed;
	}


	public String getHelpMessage() {
		return helpMessage;
	}


	public void setHelpMessage(String helpMessage) {
		this.helpMessage = helpMessage;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getResultFormalism() {
		return resultFormalism;
	}


	public void setResultFormalism(String resultFormalism) {
		this.resultFormalism = resultFormalism;
	}


	public boolean isSuspensible() {
		return suspensible;
	}


	public void setSuspensible(boolean suspensible) {
		this.suspensible = suspensible;
	}

}
