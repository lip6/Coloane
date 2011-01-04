package fr.lip6.move.coloane.projects.its.ui.forms;


import org.eclipse.core.resources.IFile;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.part.ResourceTransfer;

/**
 * A drag and drop "drop" adapter to allow drop of files onto tree view.
 * @author Yann
 *
 */
public final class TypesViewDropAdapter extends ViewerDropAdapter {

	private ScrolledPropertiesBlock master;

	/**
	 * Constructor.
	 * @param viewer the host viewer
	 * @param master the master block (to access types)
	 */
	public TypesViewDropAdapter(Viewer viewer, ScrolledPropertiesBlock master) {
		super(viewer);
		this.master = master;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validateDrop(Object target, int op, TransferData type) {
		boolean b = FileTransfer.getInstance().isSupportedType(type)
		|| LocalSelectionTransfer.getTransfer().isSupportedType(type)
		|| ResourceTransfer.getInstance().isSupportedType(type);
		return b;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performDrop(Object data) {
		if (data instanceof String[]) {
			final String[] strings = (String[]) data;
			for (String string : strings) {
				master.getPage().getMpe().getAddAction().setHint(string);
				master.getPage().getMpe().getAddAction().run();
			}
			return true;
		} else if (data instanceof TreeSelection) {
			TreeSelection tsel = (TreeSelection) data;
			for (Object element : tsel.toArray()) {
				if (element instanceof IFile) {
					IFile file = (IFile) element;
					master.getPage().getMpe().getAddAction().setHint(file.getLocation().toString());
					master.getPage().getMpe().getAddAction().run();
				}
			}
		}
		return false;
	}


}
