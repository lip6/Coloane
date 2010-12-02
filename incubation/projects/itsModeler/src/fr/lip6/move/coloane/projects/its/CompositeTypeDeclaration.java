package fr.lip6.move.coloane.projects.its;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.projects.its.expression.EvaluationContext;
import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.projects.its.variables.CompositeModelVariable;
import fr.lip6.move.coloane.projects.its.variables.InstanceVariable;
import fr.lip6.move.coloane.projects.its.variables.ScalarInstanceVariable;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;

/**
 * A class to handle composite type declarations => nested concepts.
 * @author Yann
 *
 */
public final class CompositeTypeDeclaration extends TypeDeclaration implements ISimpleObserver {

	/** The list of Type names used in this composite */
	private List<Concept> concepts;


	/**
	 * Build a composite type decl.
	 * @param typeName the type name
	 * @param typePath the file containing coloane model
	 * @param graph the graph loaded from that file
	 * @param types the type list / ITS model
	 */
	public CompositeTypeDeclaration(String typeName, IFile typePath, IGraph graph, TypeList types) {
		// loads the graph object
		super(typeName, typePath, graph, types);
		concepts = new ArrayList<Concept>();

		loadConcepts();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EvaluationContext computeParameters() throws ExtensionException {
		if (getTypeType().equals("ITSComposite")) {
			return new EvaluationContext();
		} else {
			return super.computeParameters();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<String> computeLabels() {
		Set<String> labels = new HashSet<String>();
		Collection<INode> nodes = getGraph().getNodes();
		// Composite case
		for (INode node : nodes) {
			if ("synchronization".equals(node.getNodeFormalism().getName())
					|| "delegator".equals(node.getNodeFormalism().getName())) {
				IAttribute atts = node.getAttribute("label");
				if (atts != null && (!"".equals(atts.getValue()))) {
					labels.add(atts.getValue());
				}
			}
		}
		return labels;
	}

	/**
	 * Load concepts from graph description.
	 */
	private void loadConcepts() {
		IGraphFormalism formalism = getGraph().getFormalism().getRootGraph();
		IElementFormalism inst = formalism.getElementFormalism("instance");
		Collection<INode> nodes = getGraph().getNodes();

		/** Scan through the Nodes to find all relevant concepts */
		for (INode node : nodes) {
			// An instance
			if (node.getNodeFormalism().equals(inst)) {
				String typeID = node.getAttribute("type").getValue();
				// Add this concept if it does not exist
				Concept concept = getConcept(typeID);

				if (concept == null) {
					concept = new Concept(typeID, this);
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

	@Override
	protected List<IModelVariable> computeVariables() {
		IGraphFormalism formalism = getGraph().getFormalism().getRootGraph();
		IElementFormalism inst = formalism.getElementFormalism("instance");
		Collection<INode> nodes = getGraph().getNodes();

		List<IModelVariable> vars = new ArrayList<IModelVariable>();

		/** Scan through the Nodes to find all relevant instances */
		for (INode node : nodes) {
			// An instance
			if (node.getNodeFormalism().equals(inst)) {
				String typeID = node.getAttribute("type").getValue();
				Concept concept = getConcept(typeID);

				if (getTypeType().equals("Scalar Set Composite")) {
					IAttribute sizeAtt = getGraph().getAttribute("size");
					int size = getIntegerAttributeValue(sizeAtt);
					for (int i = 0 ; i < size ; ++i) {
						CompositeModelVariable var = new ScalarInstanceVariable(node,concept.getEffective().getTypeType() + " "+ concept.getEffective().getTypeName(),i);

						List<IModelVariable> subvars = concept.getEffective().computeVariables();
						for (IModelVariable v : subvars) {
							var.addChild(v);
						}
						vars.add(var);
						
					}

				} else {
					CompositeModelVariable var = new InstanceVariable(node,concept.getEffective().getTypeType() + " "+ concept.getEffective().getTypeName());

					List<IModelVariable> subvars = concept.getEffective().computeVariables();
					for (IModelVariable v : subvars) {
						var.addChild(v);
					}
					vars.add(var);
				}
			}
		}

		return vars;
	}

	/**
	 * Add a concept to the list and attach as observer on the concept.
	 * @param concept the concept to add
	 */
	private void addConcept(Concept concept) {
		if (concept != null) {
			concept.deleteObserver(this);
		}
		concepts.add(concept);
		concept.addObserver(this);
	}

	/**
	 * Load the set of required labels by parsing an arc of the composite.
	 * @param arc the arc
	 * @param sync the related sync (useful in scalar case)
	 * @param requiredLabs the list to fill up
	 */
	private void handleArc(IArc arc, INode sync, List<String> requiredLabs) {
		IAttribute labatt = arc.getAttribute("labels");
		if (labatt != null) {
			String labels = labatt.getValue();
			labels = labels.replace(" ", "");
			labels = labels.replace("\t", "");
			labels = labels.replace("\n", "");
			StringTokenizer st = new StringTokenizer(labels, ";");
			while (st.hasMoreTokens()) {
				String lab = st.nextToken();
				if (!requiredLabs.contains(lab)) {
					requiredLabs.add(lab);
				}
			}
		} else {
			// Scalar set case
			requiredLabs.add(sync.getAttribute("label").getValue());
		}
	}

	/**
	 * Getter for concepts.
	 * @return the concepts
	 */
	public List<Concept> listConcepts() {
		return concepts;
	}

	/**
	 * Accessor for concept, based on ID
	 * @param conceptID the id of the concept we seek
	 * @return the concept if found or null
	 */
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
	@Override
	public boolean isSatisfied() {
		if (!super.isSatisfied()) {
			return false;
		}
		for (Concept concept : concepts) {
			if (concept.getEffective() == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Unsets the concepts that use this type as effective.
	 * @param t the type decl that is being removed.
	 */
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
