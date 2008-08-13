package fr.lip6.move.coloane.apicami.objects.menu;

/**
 * Contenur d'information pour une question
 */
public class Question implements IQuestion {

	/** activé ou pas */
	private boolean activate;

	/** peut-on l'arreter ou pas */
	private boolean stopAuthorized;

	/** le nom du menu */
	private String name;

	/** son menu parent */
	private String parent;

	/** le comportement de la question */
	private int questionBehavior;

	/** le type de question */
	private int questionType;

	/** dialogue permis ou pas */
	private boolean dialogAllowed;

	/** menu valide ou pas set-item */
	private boolean valid;

	/** le formalisme(le domaine resultat) */
	private String outputFormalism;

	/**
	 * le constructeur de notre classe.
	 *
	 * @param parent
	 * @param name
	 * @param questionType
	 * @param questionBehavior
	 * @param valid
	 * @param dialogAllowed
	 * @param stopAuthorized
	 * @param outputFormalism
	 * @param visibility
	 * @param children
	 */
	public Question(String parent, String name, int questionType, int questionBehavior, boolean valid, boolean dialogAllowed, boolean stopAuthorized, String outputFormalism, boolean visibility) {
		this.activate = visibility;
		this.dialogAllowed = dialogAllowed;
		this.name = name;
		this.outputFormalism = outputFormalism;
		this.parent = parent;
		this.questionBehavior = questionBehavior;
		this.questionType = questionType;
		this.stopAuthorized = stopAuthorized;
		this.valid = valid;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isVisible() {
		return this.activate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isStopAuthorized() {
		return this.stopAuthorized;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getParent() {
		return this.parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getBehavior() {
		return this.questionBehavior;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return this.questionType;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isDialogAllowed() {
		return this.dialogAllowed;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isValid() {
		return this.valid;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getOutputFormalism() {
		return this.outputFormalism;
	}
}
