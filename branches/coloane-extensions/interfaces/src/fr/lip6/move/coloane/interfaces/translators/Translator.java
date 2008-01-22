package fr.lip6.move.coloane.interfaces.translators;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Vector;

public abstract class Translator {

	static final int MAXLENGTH = 255;

	public Translator() { }

	public abstract Vector<String> translateModel(IModel model);

	public abstract Vector<String> translateNode(INode node);

	public abstract Vector<String> translateArc(IArc arc);

	public abstract Vector<String> translateAttribute(IAttribute attribute);

	public abstract IModel loadModel(Vector<String> commands) throws SyntaxErrorException;

}
