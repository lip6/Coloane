package fr.lip6.move.coloane.its;

/**
 * An interface for a type list.
 * NOTE: not used consistently currently.
 * TODO: refactor to pull up more stuff from TypeList.
 * @author Yann
 *
 */
public interface ITypeList  {
	/**
	 * Add a type declaration to this model.
	 * @param t the type declaration
	 */
	void addTypeDeclaration(TypeDeclaration t);
	/**
	 * Remove a type declaration from the model.
	 * @param t to remove
	 */
	void removeTypeDeclaration(TypeDeclaration t);
	
	/**
	 * An array of type declarations, for table editor.
	 * @return the set of constituent typedeclarations.
	 * TODO : kill me when you have the time and no more table editor.
	 */
	Object[] toArray();
}
