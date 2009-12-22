package fr.lip6.move.coloane.projects.its;

import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A model class to represent a type list.
 * Is iterable like a TypeDeclaration container
 * provides update notifications to registered observers.
 * @author Yann
 *
 */
public final class TypeList extends SimpleObservable implements ITypeList, Iterable<TypeDeclaration>, ISimpleObserver {

	private List<TypeDeclaration> table = new ArrayList<TypeDeclaration>();

	/**
	 * Add a type declaration to the set.
	 * @param t the type to add
	 */
	public void addTypeDeclaration(TypeDeclaration t) {
		table.add(t);
		t.addObserver(this);
		notifyObservers();
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTypeDeclaration(TypeDeclaration t) {
		table.remove(t);
		for (TypeDeclaration td : this) {
			td.unsetTypeDeclaration(t);
		}
		t.deleteObserver(this);
		notifyObservers();
	}


	/**
	 * iterable behavior of the type list
	 * @return free foreach statements
	 */
	public Iterator<TypeDeclaration> iterator() {
		return table.iterator();
	}

	/**
	 * Used in table provider
	 * @return table.toArray().
	 */
	public Object[] toArray() {
		return table.toArray();
	}

	/**
	 * Clear all type declarations
	 */
	public void clear() {
		table.clear();
		notifyObservers();
	}

	/**
	 * @return number of type declarations contained
	 */
	public int size() {
		return table.size();
	}

	/**
	 * invoked when a nested object changes
	 */
	public void update() {
		notifyObservers();
	}

	public void reload() {
		for (TypeDeclaration td : this) {
			try {
				td.reload();
			} catch (IOException e) {
				removeTypeDeclaration(td);
			}
		}
		notifyObservers();
	}
	
}
