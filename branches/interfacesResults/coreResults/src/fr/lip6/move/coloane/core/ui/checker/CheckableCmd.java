package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.commands.Command;

/**
 * Users who want to implement a new Command which involve modifying attributes or adding, deleting, ... elements like {@link INode}, {@link IArc} <b>must</b> extend this class.<br>
 * If not, the checker won't check these modified, added, deleted ... elements.<br>
 * Just use the {@link CheckableCmd#addCheckableElement(IElement)} method to add the elements to check.<br>
 * For attributes, add the referenced element.
 * @author Florian David
 */
public abstract class CheckableCmd extends Command {
	/** List of {@link IElement} to check when the command executed */
	private List<IElement> checkableElements = new ArrayList<IElement>();
	
	/** Constructor corresponding to {@link Command#Command()} */
	public CheckableCmd() {	
		super();
	}
	
	/** Constructor corresponding to {@link Command#Command(String)} */
	public CheckableCmd(String label) {
		super(label);
	}
	
	/**
	 * Add an element which will be check when the command is executed.
	 * @param element the element to check.
	 */
	protected void addCheckableElement(IElement element) {
		this.checkableElements.add(element);
	}
	
	/**
	 * Call checkers on all elements in the checkableElements list.
	 */
	public void checkElements() {
		// First we get the current session, the resource associated with the session, the session checker and the session graph.
		ISession session = SessionManager.getInstance().getCurrentSession();
		IGraph graph = session.getGraph();
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(session.getName()));
		Checker checker = session.getChecker();
		
		// We call the checker on the graph.
		CheckerManager.getInstance().checkGraph(checker, resource, graph);
		// Then for each IElement of checkableElement list, . . .
		for (IElement element : checkableElements) {
			if (element instanceof INode) {
				// We call the CheckerManager.getInstance().checkNode(...) method if it's a node . . .
				CheckerManager.getInstance().checkNode(checker, resource, graph, (INode) element);
			} else if (element instanceof IArc) {
				// or the CheckerManager.getInstance().checkArc(...) method if it's an arc.
				CheckerManager.getInstance().checkArc(checker, resource, graph, (IArc) element);
			}
		}
	}
}
