package fr.lip6.move.coloane.interfaces;

/**
 * Interface fournie par l'API pour Coloane
 * Cette interface propose les interractions possible avec un element de dialogue
 * construit par l'API a l'initiative de la palte-forme
 */
public interface IDialogCom {

	public abstract int getButtonType();

	public abstract void setButtonType(int buttonType);

	public abstract String getDef();

	public abstract void setDef(String def);

	public abstract String getHelp();

	public abstract void setHelp(String help);

	public abstract int getId();

	public abstract void setId(int id);

	public abstract int getInputType();

	public abstract void setInputType(int inputType);

	public abstract String getMessage();

	public abstract void setMessage(String message);

	public abstract int getMultiLine();

	public abstract void setMultiLine(int multiLine);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract int getType();

	public abstract void setType(int type);

}