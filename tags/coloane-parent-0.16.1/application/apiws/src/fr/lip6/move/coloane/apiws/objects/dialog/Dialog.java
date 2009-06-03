package fr.lip6.move.coloane.apiws.objects.dialog;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.DialogBox;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe représent un boîte de dialogue pour le core de Coloane.
 *
 * @author Monir CHAOUKI
 */
public class Dialog implements IDialog {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private int id;

    private int type;

    private int typeButton;

    private String title;

    private String help;

    private String message;

    private int inputType;

    private int lineType;

    private String defaultValue;

    private List<String> lines;

    /**
     * Constructeur
     * @param dialog la boîte de dialogue reçu de la part du wrapper
     */
	public Dialog(DialogBox dialog) {
		LOGGER.finer("Construction d'une boite de dialogue pour le core");
    	this.id = dialog.getId();
        this.type = dialog.getType();
        this.typeButton = dialog.getTypeButton();
        this.title = dialog.getTitle();
        this.help = dialog.getHelp();
        this.message = dialog.getMessage();
        this.inputType = dialog.getInputType();
        this.lineType = dialog.getLineType();
        this.defaultValue = dialog.getDefaultValue();

        this.lines = new ArrayList<String>();
        if (dialog.getLine() != null) {
        	for (int i = 0; i < dialog.getLine().length; i++) {
        		this.lines.add(dialog.getLine()[i]);
        	}
        }
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getHelp() {
		return help;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getInputType() {
		return inputType;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<String> getLines() {
		return lines;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getLineType() {
		return lineType;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getTitle() {
		return title;
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
	public final int getButtonType() {
		return typeButton;
	}

}
