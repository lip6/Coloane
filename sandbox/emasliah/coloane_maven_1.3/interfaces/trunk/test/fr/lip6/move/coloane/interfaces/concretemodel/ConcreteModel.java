/**
 * 
 */
package fr.lip6.move.coloane.interfaces.concretemodel;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.Model;

/**
 * @author cdcharles
 * 
 */
public class ConcreteModel extends Model {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ConcreteModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param commands
	 */
	public ConcreteModel(Vector<String> commands) {
		super(commands);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param commandsFile
	 * @throws IOException
	 * @throws SyntaxErrorException
	 */
	public ConcreteModel(File commandsFile) throws IOException,
			SyntaxErrorException {
		super(commandsFile);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.Model#translate()
	 */
	@Override
	public String[] translate() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#buildModel(java.util.Vector)
	 */
	public void buildModel(Vector<String> commands) throws SyntaxErrorException {
		// TODO Auto-generated method stub

	}

}
