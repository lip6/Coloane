package its;

import java.util.ArrayList;
import java.util.List;

public class Concept {
	String name;
	/** The list of labels required in this composite for a given type name */
	private List<String> labels;
	/** The resolved map from Type name in the composite, to effective Type in the Model*/
	private TypeDeclaration effective;

	public Concept(String name) {
		this.name = name;
		labels = new ArrayList<String>();
		effective = null;
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
		this.effective = effective;
	}
}
