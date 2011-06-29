package parser;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeParser;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

public class AuthorParser implements IAttributeParser {

	public boolean parseLine(String line, IAttribute containerAtt) {
		
		try {
			IAttribute a = containerAtt.getReference().getGraph().createAttribute(containerAtt.getReference(), containerAtt, (IAttributeFormalism) containerAtt.getAttributeFormalism(), "best author");
			a.setValue("this is the best author ever!");
		} catch (ModelException e) {
			return false;
		}
		return true;
	}

	public String toString(IAttribute containerAtt) {
		return containerAtt.getAttribute("best author").getValue();
	}

}
