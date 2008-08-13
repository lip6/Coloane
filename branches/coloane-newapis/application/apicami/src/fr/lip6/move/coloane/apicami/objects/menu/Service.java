package fr.lip6.move.coloane.apicami.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

/**
 * Définition d'un service sur la plate-forme.
 *
 * @author Jean-Baptiste Voron
 */
public class Service implements IService {
	/** Nom du service */
	private String name;

	/** Nom de la racine du menu dans lequel est situé le service */
	private String root;

	/** Nom du menu parent de ce service */
	private String parent;

	/** Le type du service */
	private int type;

	/** La cardinalité du service */
	private int cardinality;

	/** Est-ce que le service (case à cocher) est validé (cochée) ou non */
	private boolean checked;

	/** Est-ce que des boites de dialogues sont susceptibles d'apparaitre pendant le service */
	private boolean interactive;

	/** Le service peut-il etre stoppé (interrompu) */
	private boolean stoppable;

	/** Formalisme de sortie */
	private String outputFormalism;

	/**
	 * Constructeur
	 * @param question La description de la question par la plate-forme
	 * @param root Le menu racine qui contient la description du service
	 */
	public Service(IQuestion question, ISubMenu root) {
		this.name = question.getName();
		this.root = root.getName();
		this.type = question.getType();
		this.cardinality = question.getBehavior();
		this.checked = question.isValid();
		this.interactive = question.isDialogAllowed();
		this.stoppable = question.isStopAuthorized();
		this.outputFormalism = question.getOutputFormalism();
		this.parent = question.getParent();

		// Calcul du parent
		for (ISubMenu potentialParent : root.getSubMenus()) {
			if (((SubMenu) potentialParent).findMenu(question.getParent()) != null) {
				this.parent = potentialParent.getName();
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getCardinality() {
		return this.cardinality;
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
	public final String getOutputFormalism() {
		return this.outputFormalism;
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
	public final String getRoot() {
		return this.root;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return this.type;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isChecked() {
		return this.checked;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isInteractive() {
		return this.interactive;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isStopAuthorized() {
		return this.stoppable;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getId() {
		return this.root + "_" + this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return this.name + " (root=" + this.root + " ; parent=" + this.parent + ")";
	}
}
