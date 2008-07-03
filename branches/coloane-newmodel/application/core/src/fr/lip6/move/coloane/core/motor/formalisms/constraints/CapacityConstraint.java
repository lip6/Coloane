package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Definition d'une contrainte sur le nombre d'élément qui peuvent être contenu<br>
 * Cette contrainte <b>interdit</b> à un élement de formalisme de contenir un {@link GraphFormalism} 
 * de plus de {@link #max} objets
 */
public class CapacityConstraint implements IConstraint, IConstraintNode, IExecutableExtension {

	/** Elément sur lequel s'applique la contrainte */
	private String element;

	/** Le nombre maximum d'objets contenu dans le graphe fils du noeud */
	private int max;
	
	/**
	 * Constructeur utilisé par le <i>formalisme builder</i>
	 * @see {@link FormalismManager}
	 */
	public CapacityConstraint() { }
	
	/**
	 * Constructeur<br>
	 * Définit les valeurs de la contrainte.<br>
	 * @param element L'élement de formalisme sur lequel s'applique la contrainte
	 * @param max Nombre maximum d'élément pouvant être contenu dans le graphe fils du noeud
	 * @see {@link GraphFormalism}
	 */
	public CapacityConstraint(String element, int max) {
		this.element = element;
		this.max = max;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#isSatisfied(fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement, fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement)
	 */
	public boolean isSatisfied(INode node) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#getName()
	 */
	public String getName() {
		return "Capacity constraint";
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		if ((config.getAttribute("element") == null)) { //$NON-NLS-1$
			Coloane.getLogger().warning("L'element sur lequel port la contrainte a ete omis..."); //$NON-NLS-1$
			throw new CoreException(null);
		}
		
		if ((config.getAttribute("max") == null)) {  //$NON-NLS-1$//$NON-NLS-2$
			Coloane.getLogger().warning("La cardinalité in et/ou out n'a pas ete precisee..."); //$NON-NLS-1$
			throw new CoreException(null);
		}
		
		this.element = config.getAttribute("element"); //$NON-NLS-1$
		this.max = Integer.valueOf(config.getAttribute("maxIn")); //$NON-NLS-1$
	}
}
