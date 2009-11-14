package its;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import its.expression.EvaluationContext;
import its.expression.IVariable;
import its.expression.Variable;
import its.obs.ISimpleObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

	protected EvaluationContext computeParameters() throws ColoaneException {
		if (getTypeType().equals("ITSComposite"))
			return new EvaluationContext();
		else {
			EvaluationContext ec = new EvaluationContext();
			IVariable size = new Variable("$SIZE");
			ec.declareVariable(size);
			return ec;
		}
	}

	@Override
	protected Set<String> computeLabels() {
		Set<String> labels = new HashSet<String>();
		Collection<INode> nodes = getGraph().getNodes();
		// Composite case
		for (INode node : nodes) {
			if ("synchronization".equals(node.getNodeFormalism().getName())
					|| "delegator".equals(node.getNodeFormalism().getName())) {						
				IAttribute atts = node.getAttribute("label");
				if (atts != null && (! "".equals(atts.getValue()))) {
					labels.add(atts.getValue());					
				}
			}
		}
		return labels;
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
					handleArc(arc, arc.getSource(), requiredLabs);	
				}
				for (IArc arc : node.getOutgoingArcs()) {
					handleArc(arc, arc.getTarget(), requiredLabs);	
				}

			}
		}
		notifyObservers();
	}


	private void addConcept(Concept concept) {
		if (concept != null)
			concept.deleteObserver(this);
		concepts.add(concept);
		concept.addObserver(this);
	}

	private void handleArc(IArc arc, INode sync, List<String> requiredLabs) {
		IAttribute labatt = arc.getAttribute("labels");
		if (labatt != null) {
			String labels = labatt.getValue();
			labels = labels.replace(" ","");
			labels = labels.replace("\t","");
			labels = labels.replace("\n","");
			StringTokenizer st = new StringTokenizer(labels,";");
			while (st.hasMoreTokens()) {
				String lab = st.nextToken();
				if (! requiredLabs.contains(lab))
					requiredLabs.add(lab);
			}
		} else {
			// Scalar set case
			requiredLabs.add(sync.getAttribute("label").getValue());
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
		if (!super.isSatisfied())
			return false;
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
