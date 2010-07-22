package fr.lip6.move.coloane.core.formalisms.constraints;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

/**
 * Definition d'une contrainte sur le nombre de connexion sur un élement de formalisme<br>
 * Cette contrainte <b>interdit</b> à un élement de formalisme :
 * <ul>
 * 	<li>plus de <code>maxIn</code> arcs en entrée (arcs cibles)</li>
 * 	<li>plus de <code>maxOut</code> arcs en sortie (arcs sources)</li>
 * </ul>
 */
public class CardinalityConstraint implements IConstraint, IConstraintLink, IExecutableExtension {

	/** Une instance du logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

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

	/** {@inheritDoc} */
	public final boolean isSatisfied(INode source, INode target, IArcFormalism arcFormalism) {
		// On doit verifier que les contrainte de cardinalites s'applique pour les noeuds source et cible

		// Pour le noeud source on verifie le formalisme pour appliquer la contrainte
		if (source.getNodeFormalism().getName().equals(element)) {
			// Le nombre d'arcs sortant du noeud doit etre strictement inférieur a maxOut
			if ((this.maxOut >= 0) && (source.getOutgoingArcs().size() >= this.maxOut)) {
				return false;
			}
		}

		// Pour le noeud cible on verifie le formalisme pour appliquer la contrainte
		if (target.getNodeFormalism().getName().equals(element)) {

			// Le nombre d'arcs entrant du noeud doit etre strictement inférieur a maxIn
			if ((this.maxIn >= 0) && (target.getIncomingArcs().size() >= this.maxIn)) {
				return false;
			}
		}

		// Si toutes les contraintes sont respectées... Alors on valide !
		return true;
	}

	/** {@inheritDoc} */
	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		Map<String, String> myParams = new HashMap<String, String>();

		// Recupération des paramètres de la contrainte
		IConfigurationElement[] parameters = config.getChildren(PARAMETER_ID);

		// Remplissage de la hasmap de paramètres
		for (IConfigurationElement param : parameters) {
			if ((param.getAttribute(PARAMETER_NAME) != null) && (param.getAttribute(PARAMETER_VALUE) != null)) {
				myParams.put(param.getAttribute(PARAMETER_NAME), param.getAttribute(PARAMETER_VALUE));
			}
		}

		// Vérification de la présence des paramètres obligatoires
		if (!myParams.containsKey("element")) {  //$NON-NLS-1$
			LOGGER.warning("L'element sur lequel port la contrainte a ete omis..."); //$NON-NLS-1$
			throw new CoreException(null);
		}

		if (!myParams.containsKey("maxIn") || !myParams.containsKey("maxOut")) {  //$NON-NLS-1$//$NON-NLS-2$
			LOGGER.warning("L'element sur lequel port la contrainte a ete omis..."); //$NON-NLS-1$
			throw new CoreException(null);
		}

		this.element = myParams.get("element"); //$NON-NLS-1$
		this.maxIn = Integer.valueOf(myParams.get("maxIn")); //$NON-NLS-1$
		this.maxOut = Integer.valueOf(myParams.get("maxOut")); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final String getName() {
		return Messages.CardinalityConstraint_0;
	}
}
