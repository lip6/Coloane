package its;

import java.util.ArrayList;
import java.util.Iterator;

public class TypeList implements ITypeList,Iterable<TypeDeclaration>{

	ArrayList<TypeDeclaration> table;
	
	public TypeList() {
		table = new ArrayList<TypeDeclaration>();
	}
	
	@Override
	public void addTypeDeclaration(TypeDeclaration t) {
		table.add(t);
	}

	@Override
	public void removeTypeDeclaration(TypeDeclaration t) {
		table.remove(t);
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
		
	}

	public int size() {
		return table.size();
	}

}
