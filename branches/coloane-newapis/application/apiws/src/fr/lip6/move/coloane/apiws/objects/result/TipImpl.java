package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.interfaces.objects.result.ITip;
import fr.lip6.move.wrapper.ws.WrapperStub.IgnoreCommand;

import java.util.logging.Logger;

/**
 * Cette classe représent les informations sur un objet
 */
public class TipImpl implements ITip {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private int idObject;

	private String name;

	private String value;

	/**
	 * Constructeur
	 * @param ignoreCommand la commande ignorée
	 */
	public TipImpl(IgnoreCommand ignoreCommand) {
		LOGGER.finer("Construction d'une commande ignorée");

		this.idObject = ignoreCommand.getId();
		this.name = ignoreCommand.getName1();
		this.value = ignoreCommand.getName2();
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getIdObject() {
		return idObject;
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
	public final String getValue() {
		return value;
	}

}
