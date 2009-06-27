package its;

public interface ITypeList  {
	
	public void addTypeDeclaration (TypeDeclaration t);
	public void removeTypeDeclaration (TypeDeclaration t);
	public void updateTypeDeclaration (TypeDeclaration t);
	public Object[] toArray();	
}
