package fr.lip6.move.coloane.projects.its;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;

import fr.lip6.move.coloane.projects.its.expression.EvaluationContext;
import fr.lip6.move.coloane.projects.its.expression.IEvaluationContext;
import fr.lip6.move.coloane.projects.its.variables.IModelVariable;

public class GALTypeDeclaration extends AbstractTypeDeclaration {
	
	public GALTypeDeclaration(String name, IFile file, TypeList types) {
		super (name,file,types);
		
		// Todo : load and store handle to GAL System
	
	}

	public String getTypeType() {
		return "Guarded Action Language";
	}

	
	@Override
	public List<IModelVariable> computeVariables() {
		// TODO la liste des variables, il faut creer des classes dérivées de LeafModelVariable, cf PlaceMarkingVariable par exemple
		// Un pour chaque variable de type int
		// Un (avec des fils ?) pour chaque tableau
		// un par liste ?
		
		return Collections.EMPTY_LIST;
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
	}

	
	/**
	 * 
	 */
	@Override
	protected Set<String> computeLabels() {
		// TODO rendre la liste des labels de transitions "public"
		return Collections.EMPTY_SET;
	}

	
}
