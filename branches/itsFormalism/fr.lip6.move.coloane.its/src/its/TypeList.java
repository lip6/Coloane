package its;

import its.obs.ISimpleObserver;
import its.obs.SimpleObservable;

import java.util.ArrayList;
import java.util.Iterator;

public class TypeList extends SimpleObservable implements ITypeList,Iterable<TypeDeclaration>, ISimpleObserver{

	ArrayList<TypeDeclaration> table;
	
	public TypeList() {
		table = new ArrayList<TypeDeclaration>();
	}
	
	@Override
	public void addTypeDeclaration(TypeDeclaration t) {
		table.add(t);
		t.addObserver(this);
		notifyObservers();
	}

	@Override
	public void removeTypeDeclaration(TypeDeclaration t) {
		table.remove(t);
		for (TypeDeclaration td : this) {
			td.unsetTypeDeclaration(t);
		}
		t.deleteObserver(this);
		notifyObservers();
	}

	@Override
	public void updateTypeDeclaration(TypeDeclaration t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<TypeDeclaration> iterator() {
		return table.iterator();
	}

	@Override
	public Object[] toArray() {
		return table.toArray();
	}

	public void clear() {
		table.clear();
		notifyObservers();
	}

	public int size() {
		return table.size();
	}

	/**
	 * invoked when a nested object changes
	 */
	@Override
	public void update() {
		notifyObservers();
	}

}
