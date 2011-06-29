package fr.lip6.move.coloane.interfaces.formalism;

import fr.lip6.move.coloane.interfaces.model.IAttribute;

public interface IAttributeParser {

	public boolean parseLine(String line, IAttribute containerAtt);
	public String toString(IAttribute containerAtt);
	
}
