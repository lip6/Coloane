package fr.lip6.move.coloane.projects.its;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.lip6.move.coloane.projects.its.expression.EvaluationContext;
import fr.lip6.move.coloane.projects.its.expression.IEvaluationContext;
import fr.lip6.move.coloane.projects.its.variables.GalVariable;
import fr.lip6.move.coloane.projects.its.variables.IModelVariable;
import fr.lip6.move.gal.Variable;
import fr.lip6.move.serialization.SerializationUtil;

public class GALTypeDeclaration extends AbstractTypeDeclaration {
	private fr.lip6.move.gal.System galSystem;
	
	public GALTypeDeclaration(String name, URI file, TypeList types) {
		super (name,file,types);
		
		// load and store handle to GAL System
		this.galSystem = SerializationUtil.fileToGalSystem(file.getPath());
		galSystem.setName(name);
	}

	public String getTypeType() {
		return "Guarded Action Language";
	}
	
	@Override
	public void setTypeName(String typeName) {
		super.setTypeName(typeName);
		galSystem.setName(typeName);
	}
	

	
	@Override
	public List<IModelVariable> computeVariables() {
		// TODO la liste des variables, il faut creer des classes dérivées de LeafModelVariable, 
		// cf PlaceMarkingVariable par exemple
		// Un pour chaque variable de type int
		// Un (avec des fils ?) pour chaque tableau
		// un par liste ?
		
		List<IModelVariable> variables = new ArrayList<IModelVariable>();
		
		for(Variable var : galSystem.getVariables()){
			variables.add(new GalVariable(var));
		}
		
		return variables;
	}


	/**
	 * A priori no parameters are needed for GAL models.
	 */
	public IEvaluationContext getParameters() {
		return new EvaluationContext();
	}



	public void reload() throws IOException {
		// Il faut recharger l'instance de systeme en la lisant depuis le fichier + eventuellement flusher les caches
		// i.e. invoquer clearLabels()
		clearCaches();
		
		galSystem = SerializationUtil.fileToGalSystem(getTypeFile().getPath());
		
		
	}

	
	/**
	 * 
	 * @return the set of public labels of this type
	 * A transition is public if it has a Gal label
	 */
	@Override
	protected Set<String> computeLabels() {
		Set<String> labels = new HashSet<String>();
		
		for(fr.lip6.move.gal.Transition trans : galSystem.getTransitions()){
			final String label = trans.getLabel();
			if(label!=null){
				labels.add(label);
			}
		}
		
		return labels;
	}

	public void writeToFile(String path) {
		// create and write a GAL file based on model in memory
		SerializationUtil.systemToFile(galSystem, path);
	}

	
}


