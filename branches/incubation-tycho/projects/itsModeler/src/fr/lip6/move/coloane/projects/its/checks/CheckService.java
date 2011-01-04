package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.projects.its.ui.forms.ITSEditorPlugin;

import org.eclipse.core.runtime.IPath;

public class CheckService extends ITSCheckService {


	public CheckService(CheckList parent) {
		super(parent, "ITS Reachability");
	}


	@Override
	protected IPath getToolPath() {
		return ITSEditorPlugin.getDefault().getITSReachPath();
	}

}
