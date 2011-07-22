package attribute;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import attribute.language.Element;
import attribute.language.Model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeParser;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

public class Parser implements IAttributeParser {

	/**
	 * This function parses text entered by the user
	 * @param line The text entered by the user
	 * @param containerAtt The top level attribute
	 * @return whether the line could be parsed or not
	 */
	public boolean parseLine(String line, IAttribute containerAtt) {

		ResourceSet rs = new ResourceSetImpl();
		new LanguageStandaloneSetup().createInjectorAndDoEMFRegistration();
		Resource resource = rs.getResource( URI.createURI(line),true);
		EObject eobject = resource.getContents().get(0);
		
		//replace Model by the top-level element in your grammar
		Model model = (Model) eobject;
		
		//you may now use getters to access the parsed elements of your grammar
		//as well as the values defined by the user
		
		try {
			//the name and value will most likely be obtained from the parsed elements
			IAttribute a = containerAtt.getReference().getGraph().createAttribute(
					containerAtt.getReference(), containerAtt, (IAttributeFormalism) containerAtt.getAttributeFormalism(), "Name");
			a.setValue("Value");
		} catch (ModelException e1) {
			//do not forget to return false if parsing has not proceeded as expected
			return false;
		}
		
		return true;
		
	}

	/**
	 * This function transforms a tree of attributes into a line of text
	 * which should be parsable using parseLine to return the same tree of attributes.
	 * @param containerAtt The top level attribute of the tree
	 * @return The serialised line of text describing the attributes
	 */
	public String toString(IAttribute containerAtt) {
		String s="";
		if (containerAtt.isLeaf()) {
			//modify this part to return a line that conforms to your grammar
			return containerAtt.getValue();
		}
		else {
			for (IAttribute att: containerAtt.getAttributes()) {
				//you may also need to modify this to conform to your grammar
				s += toString(att);
			}
		}
		return s;
	}

}
