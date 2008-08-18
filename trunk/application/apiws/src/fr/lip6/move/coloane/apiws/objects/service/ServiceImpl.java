package fr.lip6.move.coloane.apiws.objects.service;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.interfaces.objects.service.IService;

/**
 * Cette classe définie un service
 *
 * @author Monir CHAOUKI
 */
public class ServiceImpl implements IService {


	private String id;
	private int cardinality;
	private String name;
	private String outPutFormalism;
	private String parent;
	private String root;
	private int type;
	private boolean checked;
	private boolean interactive;
	private boolean stopAuthorized;

	/**
	 * Constructeur
	 * @param question le service reçu de la part du wrapper.
	 * @param root le menu principal où est contenu le service.
	 * @param parent le menu parent du service.
	 * @param type le type de service.
	 */
	public ServiceImpl(Question question, String root, String parent, int type) {
		this.id = root + "_" + question.getName();
		this.cardinality = question.getCardinality();
		this.name = question.getName();
		this.parent = root;
		this.root = root;
		this.type = type;
		this.checked = question.getValidation();
		this.interactive = question.getInteraction();
		this.stopAuthorized = question.getStop();

		if (question.getDomain().equals("")) {
			this.outPutFormalism = null;
		} else {
			this.outPutFormalism = question.getDomain();
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public final String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getCardinality() {
		return cardinality;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getOutputFormalism() {
		return outPutFormalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRoot() {
		return root;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isChecked() {
		return checked;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isInteractive() {
		return interactive;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isStopAuthorized() {
		return stopAuthorized;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return this.name + " (root=" + this.root + " ; parent=" + this.parent + ")";
	}

}
