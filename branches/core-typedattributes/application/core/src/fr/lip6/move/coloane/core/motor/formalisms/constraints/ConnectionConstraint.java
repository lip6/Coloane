package fr.lip6.move.coloane.core.motor.formalisms.constraints;

import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

/**
 * Definition d'une contrainte pour la connexion de 2 éléments de formalisme<br>
 * Cette contrainte <b>interdit</b> la connexion entre l'élément <code>source</code> et l'élément <code>target</code>
 */
public class ConnectionConstraint implements IConstraint, IConstraintLink, IExecutableExtension {

	/** Element en entree de l'arc. */
	private String source;

	/** Element en sortie de l'arc. */
	private String target;

	/**
	 * Constructeur utilisé par le <i>formalisme builder</i>
	 * @see {@link FormalismManager}
	 */
	public ConnectionConstraint() {	}

	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param source élément source de l'arc
	 * @param target élément cible de l'arc
	 */
	public ConnectionConstraint(String source, String target) {
		this.source = source;
		this.target = target;
	}

	/** {@inheritDoc} */
	public final boolean isSatisfied(INode source, INode target) {
		return (!(this.source.equals(source.getNodeFormalism().getName())) || !(this.target.equals(target.getNodeFormalism().getName())));
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
		if (!myParams.containsKey("source") || !myParams.containsKey("target")) {  //$NON-NLS-1$//$NON-NLS-2$
			throw new CoreException(null);
		}

		this.source = myParams.get("source"); //$NON-NLS-1$
		this.target = myParams.get("target"); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	public final String getName() {
		return Messages.ConnectionConstraint_0;
	}
}
