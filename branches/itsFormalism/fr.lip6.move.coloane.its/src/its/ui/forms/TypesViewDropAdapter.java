package its.ui.forms;


import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;

public class TypesViewDropAdapter implements DropTargetListener {

	
	
	private ScrolledPropertiesBlock master;


	public TypesViewDropAdapter(ScrolledPropertiesBlock master) {
		this.master = master;
	}


	public void drop(DropTargetEvent event) {
		Object data = event.data;
		if (data instanceof String[]) {
			final String[] strings = (String[]) data;
			for (int i = 0; i < strings.length; i++) {
				master.getPage().getMpe().getAddAction().setHint(strings[i]);
				master.getPage().getMpe().getAddAction().run();
			}
		}
	}


	@Override
	public void dragEnter(DropTargetEvent event) {
		
	}


	@Override
	public void dragLeave(DropTargetEvent event) {
		
	}


	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dragOver(DropTargetEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub
		
	}

}
