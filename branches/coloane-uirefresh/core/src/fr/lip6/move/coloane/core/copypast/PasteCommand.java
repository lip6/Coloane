package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.HashMap;
import java.util.logging.Logger;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class PasteCommand extends Command {
	private Logger log = Logger.getLogger("fr.lip6.move.coloane.core");

	private ModelContainer modelContainer;
	private HashMap<NodeContainer, INodeImpl> nodes = new HashMap<NodeContainer, INodeImpl>();
	private HashMap<ArcContainer, IArcImpl> arcs = new HashMap<ArcContainer, IArcImpl>();

	private IModelImpl model;

	public PasteCommand(ColoaneEditor editor) {
		model = editor.getModel();
	}

	@SuppressWarnings("unchecked")
	public final boolean canExecute() {
		modelContainer = (ModelContainer) Clipboard.getDefault().getContents();
		if (modelContainer == null || modelContainer.isEmpty()
				|| !modelContainer.getFormalism().equals(model.getFormalism())) {
			return false;
		}
		return true;
	}

	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		log.fine("Collage de la sélection");
		for (NodeContainer nc : modelContainer.getNodes()) {
			INodeImpl node = nc.copy(model);
			nodes.put(nc, node);
		}
		for (ArcContainer ac : modelContainer.getArcs()) {
			INodeImpl source = nodes.get(modelContainer.getNode(ac.getIdSource()));
			INodeImpl target = nodes.get(modelContainer.getNode(ac.getIdTarget()));
			try {
				if (source != null && target != null) {
					IArcImpl arc = ac.copy(model, source, target);
					arc.setModelAdapter(model);
					arcs.put(ac, arc);
				}
			} catch (BuildException e) {
				e.printStackTrace();
			}
		}
		redo();
	}

	@Override
	public final void redo() {
		for (INodeImpl node : nodes.values()) {
			try {
				model.addNode(node);
			} catch (BuildException e) {
				e.printStackTrace();
			}
		}
		for (IArcImpl arc : arcs.values()) {
			try {
				model.addArc(arc);
			} catch (BuildException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public final boolean canUndo() {
		return modelContainer != null;
	}

	@Override
	public final void undo() {
		for (IArcImpl arc : arcs.values()) {
			try {
				model.removeArc(arc);
			} catch (BuildException e) {
				e.printStackTrace();
			}
		}
		for (INodeImpl node : nodes.values()) {
			try {
				model.removeNode(node);
			} catch (BuildException e) {
				e.printStackTrace();
			}
		}
	}
}
