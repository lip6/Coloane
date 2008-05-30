package fr.lip6.move.coloane.core.copypast;

import java.util.HashMap;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.model.ArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.interfaces.model.IArc;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class PasteCommand extends Command {
	private IModelImpl modelAdapter;
	private HashMap<INodeImpl, INodeImpl> nodes = new HashMap<INodeImpl, INodeImpl>();
	private HashMap<IArcImpl, IArcImpl> arcs = new HashMap<IArcImpl, IArcImpl>();

	private static SessionManager manager = Coloane.getDefault().getMotor().getSessionManager();

	@SuppressWarnings("unchecked")
	public final boolean canExecute() {
		modelAdapter = (IModelImpl) Clipboard.getDefault().getContents();
		if (modelAdapter == null || !modelAdapter.getFormalism().equals(manager.getCurrentSessionModel().getFormalism())) {
			return false;
		}
		return true;
	}

	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		for (IArcImpl arc : modelAdapter.getArcs()) {
			arcs.put(arc, null);
		}
		for (INodeImpl node : modelAdapter.getNodes()) {
			nodes.put(node, null);
		}
		redo();
	}

	@Override
	public final void redo() {
		IModelImpl currentModel = manager.getCurrentSessionModel();
		for (IArcImpl key : arcs.keySet()) {
			try {
				INodeImpl source = nodes.get(key.getSource());
				if (source == null) {
					source = (INodeImpl) key.getSource().clone();
					source.setModelAdapter(currentModel);
					currentModel.addNode(source);
					nodes.put(key.getSource(), source);
				}
				INodeImpl target = nodes.get(key.getTarget());
				if (target == null) {
					target = (INodeImpl) key.getTarget().clone();
					target.setModelAdapter(currentModel);
					currentModel.addNode(target);
					nodes.put(key.getTarget(), target);
				}
//				IArcImpl arc = (IArcImpl) key.clone();
				IArcImpl arc = new ArcImplAdapter((IArc) key.clone(), source, target, key.getElementBase());
				arc.setModelAdapter(currentModel);
//				arc.reconnect(source, target);
//				arc.setGraphicInfo(new ArcGraphicInfo(arc));
				currentModel.addArc(arc);

			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			} catch (BuildException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public final boolean canUndo() {
		return false; //model != null;
	}

	@Override
	public final void undo() {
	}
}
