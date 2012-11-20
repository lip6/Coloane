package fr.lip6.move.coloane.projects.its;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;
import fr.lip6.move.coloane.projects.its.variables.IModelVariable;

public abstract class AbstractTypeDeclaration extends SimpleObservable implements ITypeDeclaration {

	
	private String typeName;
	private URI typeFile;
	private Set<String> labels = null;
	private TypeList typeList;
	private List<IModelVariable> variables = null;

	
	public AbstractTypeDeclaration(String typeName, URI modelFile, TypeList types) {
		this.typeName = typeName;
		typeFile = modelFile;
		this.typeList = types;
	}
	
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#getTypeName()
	 */
	public final String getTypeName() {
		return typeName;
	}

	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#setTypeName(java.lang.String)
	 */
	public void setTypeName(String typeName) {
		if (!this.typeName.equals(typeName)) {
			this.typeName = typeName;
			notifyObservers();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#getTypePath()
	 */
	public final String getTypePath() {
		return typeFile.getPath();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#getTypeFile()
	 */
	public final URI getTypeFile() {
		return typeFile;
	}

	
	protected abstract Set<String> computeLabels() ;
	
	
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#getLabels()
	 */
	public final Collection<String> getLabels() {
		if (labels == null) {
			labels = computeLabels();
		}
		return labels;
	}

	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#isSatisfied()
	 */
	public boolean isSatisfied() {
		return true;
	}

	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#getTypeList()
	 */
	public final TypeList getTypeList() {
		return typeList;
	}

	
	/**
	 * Clear any references to this type (see behavior in composite type decl.
	 * 
	 * @param t
	 *            the type to be removed
	 */
	public void unsetTypeDeclaration(ITypeDeclaration t) {
		// NOP
	}

	/**
	 * Call this to force recompute of cached labels
	 */
	protected void clearCaches() {
		labels = null;
		variables = null;
	}
	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#getVariables()
	 */
	public final List<IModelVariable> getVariables() {
		if (variables == null) {
			variables = computeVariables();
		}
		ArrayList<IModelVariable> toret = new ArrayList<IModelVariable>(variables.size());
		for (IModelVariable m : variables) {
			toret.add(m.clone());
		}
		return toret;
	}
	
	protected abstract List<IModelVariable> computeVariables() ;

	
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.projects.its.ITypeDeclaration#findQualifiedVariable(java.lang.String)
	 */
	public final IModelVariable findQualifiedVariable(String name) {
		for (IModelVariable var : getVariables()) {
			IModelVariable found = findQualifiedVariableRec(var, name);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	/**
	 * Private recursive part of resolve variable behavior.
	 * @param var a variable, could have children variables.
	 * @param name the name we are trying to resolve.
	 * @return the variable or null if not found.
	 */
	private IModelVariable findQualifiedVariableRec(IModelVariable var,
			String name) {
		if (var.getQualifiedName().equals(name)) {
			return var;
		}
		for (IModelVariable child : var) {
			IModelVariable found = findQualifiedVariableRec(child, name);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

}
