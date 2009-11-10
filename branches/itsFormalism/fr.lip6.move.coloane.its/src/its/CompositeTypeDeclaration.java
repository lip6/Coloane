package its;

import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;

public class CompositeTypeDeclaration extends TypeDeclaration implements ISimpleObserver {

	/** The list of Type names used in this composite */
	private List<Concept> concepts;
	
	
	public CompositeTypeDeclaration(String typeName, IFile typePath, IGraph graph, TypeList types) {
		// loads the graph object
		super(typeName, typePath,graph,types);
		concepts= new ArrayList<Concept>();
		
		loadConcepts();
	}

	private void loadConcepts() {		
		IGraphFormalism formalism = getGraph().getFormalism().getMasterGraph();
		IElementFormalism inst = formalism.getElementFormalism("instance"); 
		Collection<INode> nodes = getGraph().getNodes();
		
		/** Scan through the Nodes to find all relevant concepts */
		for (Iterator<INode> iterator = nodes.iterator(); iterator.hasNext();) {
			INode node = iterator.next();
			// An instance
			if ( node.getNodeFormalism().equals(inst) ) {
				String typeID = node.getAttribute("type").getValue();
				// Add this concept if it does not exist
				Concept concept= getConcept(typeID);
				
				if (concept==null){
					concept = new Concept(typeID,this);
					addConcept(concept);
				}
				List<String> requiredLabs = concept.getLabels();
				// Scan arcs and add any labels encountered to this concept definition				
				for (IArc arc : node.getIncomingArcs()) {
					handleArc(arc, requiredLabs);	
				}
				for (IArc arc : node.getOutgoingArcs()) {
					handleArc(arc, requiredLabs);	
				}
				
			}
		}
		notifyObservers();
	}

	
	private void addConcept(Concept concept) {
		concepts.add(concept);
		concept.addObserver(this);
	}

	private void handleArc(IArc arc, List<String> requiredLabs) {
		String labels = arc.getAttribute("labels").getValue();
		labels = labels.replace(" ","");
		labels = labels.replace("\t","");
		labels = labels.replace("\n","");
		StringTokenizer st = new StringTokenizer(labels,";");
		while (st.hasMoreTokens()) {
			String lab = st.nextToken();
			if (! requiredLabs.contains(lab))
				requiredLabs.add(lab);
		}									
	}

	public List<Concept> listConcepts () {
		return concepts;
	}

	public Concept getConcept(String conceptID) {
		for (Concept conceptit : concepts) {
			if (conceptit.getName().equals(conceptID)) {
				return conceptit;
			}
		}
		return null;
	}

	/** Specifies if all the concepts of this composite have an effective realization. 
	 * @return true if all concepts are satisfied
	 */
	//@override
	public boolean isSatisfied () {
		for (Concept concept : concepts) 
			if (concept.getEffective() == null)
				return false;
		return true;
	}

	@Override
	public void update() {
		notifyObservers();
	}

	@Override
	public void unsetTypeDeclaration(TypeDeclaration t) {
		super.unsetTypeDeclaration(t);
		for (Concept concept : concepts) {
			if (concept.getEffective().equals(t)) {
				concept.setEffective(null);
			}
		}
	}
}
