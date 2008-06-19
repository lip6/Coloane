package fr.lip6.move.coloane.interfaces.translators;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Vector;

public interface Translator {

	int MAXLENGTH = 255;

	Vector<String> translateModel(IModel model);

	Vector<String> translateNode(INode node);

	Vector<String> translateArc(IArc arc);

	Vector<String> translateAttribute(IAttribute attribute);

	IModel loadModel(Vector<String> commands) throws SyntaxErrorException;

}
