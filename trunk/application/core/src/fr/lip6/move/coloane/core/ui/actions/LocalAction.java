package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.commands.ModificationResultCommand;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;

import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;

/**
 * This class defines an action associated to a local tool.
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class LocalAction extends Action {
	
	/** The name of the action */
	private String name;
	
	/** The description of the action */
	private String description;
	
	/** The icon associated to the action */
	private ImageDescriptor icon;
	
	/** The effective ColoaneAction */
	private IColoaneAction action;
	
	/**
	 * Constructor
	 * @param name The action name
	 * @param description The action description
	 * @param icon The icon associated to the action
	 * @param action The effective action to be run
	 */
	public LocalAction(String name, String description, ImageDescriptor icon, IColoaneAction action) {
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.action = action;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDescription() {
		return this.description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToolTipText() {
		return this.description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getText() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ImageDescriptor getImageDescriptor() {
		return this.icon;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run() {
		IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();
		List<ICommand> commands = action.run(currentGraph);
		Command result = new ModificationResultCommand(currentGraph, commands);
		ColoaneEditor ce = (ColoaneEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		GraphicalViewer viewer = (GraphicalViewer) ce.getAdapter(GraphicalViewer.class);
		viewer.getEditDomain().getCommandStack().execute(result);
	}
}
