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

public class TypeDeclaration {
	private String typeName;
	private IFile typeFile;
	/** The underlying coloane Graph */
	private IGraph graph;
	private Set<String> labels=null;
	
	protected TypeDeclaration (String typeName, IFile modelFile, IGraph graph) {
		this.typeName = typeName;
		typeFile = modelFile ;
		this.graph = graph;
	}
	
	public String getTypeName() {
		return typeName;
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

		// Si le chargement a �chou�, on annule l'ouverture de l'�diteur
		if (graph == null) {
			throw new IOException("Load from XML file failed !");
			}
		return graph;
	}

	/** Factory operation to build concrete TypeDescriptions */
	public static TypeDeclaration create(String name, IFile file) throws IOException {
		IGraph graph = loadGraph(file);
		if ( graph.getFormalism().getName().equals("ITSComposite") ) {
			return new CompositeTypeDeclaration(name,file,graph);
		} else {
			return new TypeDeclaration(name,file, graph);
		}
	}

	public Collection<String> getLabels() {
		if (labels == null) {
			labels = new HashSet<String>();
			Collection<INode> nodes = graph.getNodes();
			for (INode node : nodes) {
				IAttribute atts = node.getAttribute("label");
				if (atts != null && (! atts.getValue().equals(""))) {
					labels.add(atts.getValue());
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
}
