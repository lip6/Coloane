package fr.lip6.move.coloane.core.ui.views;

import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.NodeLinkHandler;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler;
import fr.lip6.move.coloane.core.ui.files.NodeLinkHandler.NodeLink;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler.PublicNode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provider for model file of the models navigator.
 * Display for models additionnals informations : <ol>
 * <li>public nodes
 * <li>links to another models
 * </ol>
 * 
 * @author Clément Démoulins
 */
public final class ModelContentProvider implements ITreeContentProvider {

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile && ((IFile) parentElement).getName().endsWith("model")) { //$NON-NLS-1$
			IFile file = (IFile) parentElement;
			List<Tree<String>> children = new ArrayList<Tree<String>>();

			PublicNodeHandler pnh = ModelLoader.loadFromXML(file, new PublicNodeHandler(file));
			if (pnh.getPublicNodes().size() > 0) {
				Tree<String> publicNodes = new Tree<String>(Messages.ModelContentProvider_0);
				for (PublicNode pn : pnh.getPublicNodes()) {
					publicNodes.addChild(new Tree<String>(pn.getName()));
				}
				children.add(publicNodes);
			}

			NodeLinkHandler nlh = ModelLoader.loadFromXML(file, new NodeLinkHandler());
			if (nlh.getNodeLinks().size() > 0) {
				Tree<String> links = new Tree<String>(Messages.ModelContentProvider_1);
				for (NodeLink nl : nlh.getNodeLinks()) {
					links.addChild(new Tree<String>(nl.getPath().replaceAll("/.*/", "") + "@" + nl.getId())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				children.add(links);
			}

			return children.toArray();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object element) {
		Object[] childs = getChildren(element);
		return childs != null && childs.length > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object inputElement) {
		return null;
	}

	/** {@inheritDoc} */
	public void dispose() { }

	/** {@inheritDoc} */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }

}
