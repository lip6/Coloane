package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.model.ArcGraphicInfo;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.interfaces.model.IModel;

import java.util.HashMap;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

public class CopyCommand extends Command {
	private HashMap<INodeImpl, INodeImpl> nodes = new HashMap<INodeImpl, INodeImpl>();
	private HashMap<IArcImpl, IArcImpl> arcs = new HashMap<IArcImpl, IArcImpl>();

	private SessionManager manager = Coloane.getDefault().getMotor().getSessionManager();

	public final void addNode(INodeImpl node) {
		nodes.put(node, null);
	}

	public final void addArc(IArcImpl arc) {
		arcs.put(arc, null);
	}

	@Override
	public final boolean canExecute() {
		return !nodes.isEmpty() || !arcs.isEmpty();
	}

	@Override
	public final boolean canUndo() {
		return false;
	}

	@Override
	public final void execute() {
		if (!canExecute()) {
			return;
		}
		try {
//			IModelImpl modelAdapter = (ModelImplAdapter) manager.getCurrentSessionModel().clone();
			IModel model = (IModel) manager.getCurrentSessionModel().getGenericModel().clone();
			IModelImpl modelAdapter = new ModelImplAdapter(model);
			for (IArcImpl key : arcs.keySet()) {
				if (nodes.containsKey(key.getSource()) && nodes.containsKey(key.getTarget())) {
					IArcImpl arc = (IArcImpl) key.clone();
					INodeImpl source = nodes.get(key.getSource());
					if (source == null) {
						source = (INodeImpl) key.getSource().clone();
						source.setModelAdapter(modelAdapter);
						modelAdapter.addNode(source);
						nodes.put(key.getSource(), source);
					}
					INodeImpl target = nodes.get(key.getTarget());
					if (target == null) {
						target = (INodeImpl) key.getTarget().clone();
						target.setModelAdapter(modelAdapter);
						modelAdapter.addNode(target);
						nodes.put(key.getTarget(), target);
					}
					arc.reconnect(source, target);
					arc.setGraphicInfo(new ArcGraphicInfo(arc));
					modelAdapter.addArc(arc);
				}
			}
			for (INodeImpl key : nodes.keySet()) {
				if (nodes.get(key) == null) {
//					System.err.println("clone " + key);
					INodeImpl node = (INodeImpl) key.clone();
					node.setModelAdapter(modelAdapter);
					modelAdapter.addNode(node);
					nodes.put(key, node);
				}
			}
			Clipboard.getDefault().setContents(modelAdapter);
			System.err.println(model.hashCode() + " - " + modelAdapter.getArcs().size());
		} catch (CloneNotSupportedException e) {
			Coloane.getLogger().warning("Erreur pendant la copie 1");
			System.err.println(e);
			System.err.println(e.getStackTrace()[0]);
		} catch (BuildException e) {
			Coloane.getLogger().warning("Erreur pendant la copie 2");
			System.err.println(e);
			System.err.println(e.getStackTrace()[0]);
		}
	}

}
