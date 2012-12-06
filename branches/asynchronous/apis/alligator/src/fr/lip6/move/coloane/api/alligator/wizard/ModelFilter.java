package fr.lip6.move.coloane.api.alligator.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;

/**
 * Filter all the file except the Coloane models.
 *
 * @author Clément Démoulins
 */
public class ModelFilter implements IResourceFilter {

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.api.alligator.wizard.IResourceFilter#isFiltered(org.eclipse.core.resources.IResource)
	 */
	@Override
	public final boolean isFiltered(IResource resource) {
		if (resource instanceof IContainer && !resource.getName().startsWith(".")) {
			return false;
		}
		return !resource.getName().endsWith("model");
	}

}
