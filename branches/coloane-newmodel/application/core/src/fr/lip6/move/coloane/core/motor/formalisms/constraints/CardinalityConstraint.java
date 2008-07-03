package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Definition d'une contrainte sur le nombre de connexion sur un élement de formalisme<br>
 * Cette contrainte <b>interdit</b> à un élement de formalisme :
 * <ul>
 * 	<li>plus de <code>maxIn</code> arcs en entrée (arcs cibles)</li>
 * 	<li>plus de <code>maxOut</code> arcs en sortie (arcs sources)</li>
 * </ul>
 */
public class CardinalityConstraint implements IConstraint, IExecutableExtension {

	/** Elément sur lequel s'applique la contrainte */
	private String element;

	/** Le nombre maximum d'arcs en entrée (arcs cibles) */
	private int maxIn;
	
	/** Le nombre maximum d'arcs en sortie (arcs sources) */
	private int maxOut;

	
	/**
	 * Constructeur utilisé par le <i>formalisme builder</i>
	 * @see {@link FormalismManager}
	 */
	public CardinalityConstraint() { }
	
	/**
	 * Constructeur<br>
	 * Définit les valeurs de la contrainte.<br>
	 * Pour ne pas attribuer une limite pour <code>maxIn</code> ou <code>maxOut</code> : -1<br>
	 * @param element L'élement de formalisme sur lequel s'applique la contrainte
	 * @param maxIn Nombre maximum d'arcs en entrée (arcs cibles)
	 * @param maxOut Nombre maximum d'arcs en sortie (arcs sources)
	 */
	public CardinalityConstraint(String element, int maxIn, int maxOut) {
		this.element = element;
		this.maxIn = maxIn;
		this.maxOut = maxOut;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#isSatisfied(fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement, fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement)
	 */
	public boolean isSatisfied(INode source, INode target) {
		// On doit verifier que les contrainte de cardinalites s'applique pour les noeuds source et cible
		
		// Pour le noeud source on verifie le formalisme pour appliquer la contrainte
		if (source.getNodeFormalism().getName().equals(element)) {
			
			// Le nombre d'arcs entrant du noeud doit etre strictement inférieur a maxIn
			if (source.getIncomingArcs().size() >= this.maxIn) {
				return false;
			}
			
			// Le nombre d'arcs sortant du noeud doit etre strictement inférieur a maxOut
			if (source.getOutcomingArcs().size() >= this.maxOut) {
				return false;
			}
		}
		
		// Pour le noeud cible on verifie le formalisme pour appliquer la contrainte
		if (target.getNodeFormalism().getName().equals(element)) {
			
			// Le nombre d'arcs entrant du noeud doit etre strictement inférieur a maxIn
			if (target.getIncomingArcs().size() >= this.maxIn) {
				return false;
			}
			
			// Le nombre d'arcs sortant du noeud doit etre strictement inférieur a maxOut
			if (target.getOutcomingArcs().size() >= this.maxOut) {
				return false;
			}
		}
		
		// Si toutes les contraintes sont respectées... Alors on valide !
		return true;
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		if ((config.getAttribute("element") == null)) { //$NON-NLS-1$
			Coloane.getLogger().warning("L'element sur lequel port la contrainte a ete omis..."); //$NON-NLS-1$
			throw new CoreException(null);
		}
		
		if ((config.getAttribute("maxIn") == null) || (config.getAttribute("maxOut") == null)) {  //$NON-NLS-1$//$NON-NLS-2$
			Coloane.getLogger().warning("La cardinalité in et/ou out n'a pas ete precisee..."); //$NON-NLS-1$
			throw new CoreException(null);
		}
		
		this.element = config.getAttribute("element"); //$NON-NLS-1$
		this.maxIn = Integer.valueOf(config.getAttribute("maxIn")); //$NON-NLS-1$
		this.maxOut = Integer.valueOf(config.getAttribute("maxOut")); //$NON-NLS-1$		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint#getName()
	 */
	public String getName() {
		return "Cardinality constraint";
	}
}
