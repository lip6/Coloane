package its;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;

import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class TypeDeclaration extends SimpleObservable {
	private String typeName;
	private IFile typeFile;
	/** The underlying coloane Graph */
	private IGraph graph;
	private Set<String> labels=null;
	private TypeList typeList;

	protected TypeDeclaration (String typeName, IFile modelFile, IGraph graph, TypeList types) {
		this.typeName = typeName;
		typeFile = modelFile ;
		this.graph = graph;
		typeList = types;
	}

	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
		notifyObservers();
	}
	
	public String getTypePath() {
		return typeFile.getFullPath().toString();
	}
	public IFile getTypeFile() {
		return typeFile;
	}

	public String getTypeType() {
		return graph.getFormalism().getName();
	}

	public IGraph getGraph() {		
		return graph;
	}

	/**  Load a IGraph from a file 
	 * @throws IOException */
	private static IGraph loadGraph(IFile typePath) throws IOException {
		// Construction d'un modele en memoire a partir de se representation en XML
		IGraph graph = ModelLoader.loadGraphFromXML(typePath);

		// Si le chargement a échoué, on annule l'ouverture de l'éditeur
		if (graph == null) {
			throw new IOException("Load from XML file failed !");
		}
		return graph;
	}

	/** Factory operation to build concrete TypeDescriptions */
	public static TypeDeclaration create(String name, IFile file, TypeList types) throws IOException {
		IGraph graph = loadGraph(file);
		if ( graph.getFormalism().getName().equals("ITSComposite") ) {
			return new CompositeTypeDeclaration(name,file,graph,types);
		} else {
			return new TypeDeclaration(name, file, graph, types);
		}
	}

	public Collection<String> getLabels() {
		if (labels == null) {
			labels = new HashSet<String>();
			Collection<INode> nodes = graph.getNodes();
			if (graph.getFormalism().getName().equals("Time Petri Net")) {
				for (INode node : nodes) {
					if ("transition".equals(node.getNodeFormalism().getName())) {
						IAttribute visibility = node.getAttribute("visibility");
						if ("public".equals(visibility.getValue())) {
							IAttribute atts = node.getAttribute("label");
							labels.add(atts.getValue());					
						}
					}
				}
			} else {
				// Composite case
				for (INode node : nodes) {
					if ("synchronization".equals(node.getNodeFormalism().getName())) {						
						IAttribute atts = node.getAttribute("label");
						if (atts != null && (! "".equals(atts.getValue()))) {
							labels.add(atts.getValue());					
						}
					}
				}				
			}
		}
		return labels;
	}

	/** Specifies if all the concepts of this type have an effective realization. 
	 * @return true for a basic type declaration
	 */
	public boolean isSatisfied () {
		return true;
	}

	public TypeList getTypeList() {
		return typeList;
	}

}
