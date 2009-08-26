package syntax.tpn;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

public class Result implements IResult {

	private List<ISubResult> subResults = new ArrayList<ISubResult>();

	@Override
	public List<ICommand> getModificationsOnCurrentGraph() {
		return new ArrayList<ICommand>();
	}

	@Override
	public IGraph getNewGraph() {
		return null;
	}

	@Override
	public String getRootName() {
		return "tpn";
	}

	@Override
	public String getServiceName() {
		return "tpn.syntax";
	}

	@Override
	public List<ISubResult> getSubResults() {
		return subResults;
	}

	@Override
	public List<ITip> getTipsList() {
		return new ArrayList<ITip>();
	}

	public void addChild(SubResult sr) {
		subResults.add(sr);
	}

}
