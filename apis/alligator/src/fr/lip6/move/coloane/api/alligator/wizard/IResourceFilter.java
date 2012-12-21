package fr.lip6.move.coloane.api.alligator.wizard;

import org.eclipse.core.resources.IResource;

/**
 * Resource filter interface used by the wizard page {@link FilteredResourcesPage}.
 * 
 * @author Clément Démoulins
 */
public interface IResourceFilter {
    /**
     * @param resource
     *        tested resource
     * @return <code>true</code> if the resource must be filtered
     */
        boolean isFiltered(IResource resource);
}
