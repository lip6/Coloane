package its;

import its.obs.SimpleObservable;

import java.util.ArrayList;
import java.util.List;

public class Concept extends SimpleObservable {
	String name;
	/** The list of labels required in this composite for a given type name */
	private List<String> labels;
	/** The resolved map from Type name in the composite, to effective Type in the Model*/
	private TypeDeclaration effective;
	private CompositeTypeDeclaration parent;

	public Concept(String name, CompositeTypeDeclaration parent) {
		this.name = name;
		labels = new ArrayList<String>();
		effective = null;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public List<String> getLabels() {
		return labels;
	}
	public TypeDeclaration getEffective() {
		return effective;
	}
	public void setEffective(TypeDeclaration effective) {
		if (this.effective != effective) {
			this.effective = effective;
			notifyObservers();
		}
	}

	public CompositeTypeDeclaration getParent() {
		return parent;
	}
}
