package fr.lip6.move.coloane.core.copypast;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.ArrayList;

public class ArcContainer {
	private int id;

	private int idSource;
	private int idTarget;
	private ElementFormalism elementFormalism;
	private ArrayList<AttributContainer> attributs = new ArrayList<AttributContainer>();;

	public ArcContainer(IArcImpl arc, int idSource, int idTarget) {
		id = arc.getId();
		this.idSource = idSource;
		this.idTarget = idTarget;
		elementFormalism = arc.getElementBase();
		for (IAttributeImpl attr : arc.getAttributes()) {
			attributs.add(new AttributContainer(attr));
		}
	}

	public final IArcImpl copy(IModelImpl model, INodeImpl source, INodeImpl target) throws BuildException {
		ArcImplAdapter arcAdapter = new ArcImplAdapter(source, target, elementFormalism);
		arcAdapter.setModelAdapter(model);
		for (AttributContainer ac : attributs) {
			arcAdapter.setPropertyValue(ac.getId(), ac.getValue());
		}
		return arcAdapter;
	}

	public final int getIdSource() {
		return idSource;
	}

	public final int getIdTarget() {
		return idTarget;
	}

	public final int getId() {
		return id;
	}
}
