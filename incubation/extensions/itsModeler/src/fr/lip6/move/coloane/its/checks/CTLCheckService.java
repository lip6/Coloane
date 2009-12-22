package fr.lip6.move.coloane.its.checks;

import fr.lip6.move.coloane.its.ui.forms.ITSEditorPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.eclipse.core.runtime.IPath;


public class CTLCheckService extends CheckService {

	private static final String CTL_NAME = "CTL Check";
	private static final String CTL_FORMULA_PARAM = "CTL formula";
	private static final String CTL_FILE_NAME = "formula.ctl";

	public CTLCheckService(CheckList parent) {
		super(parent, CTL_NAME);
		addParameter(CTL_FORMULA_PARAM);
	}

	protected List<String> buildCommandArguments() {
		List<String> cmd = super.buildCommandArguments();
		cmd.add("-ctl");
		cmd.add(CTL_FILE_NAME);
		return cmd;
	}

	protected IPath getToolPath() {
		return ITSEditorPlugin.getDefault().getITSCTLPath();
	}

	@Override
	public String run() {
		try {
			File file = new File(getWorkDir()+"/"+CTL_FILE_NAME);
			FileOutputStream writer = new FileOutputStream(file); //$NON-NLS-1$
			BufferedWriter sb = new BufferedWriter(new OutputStreamWriter(writer));
			file.createNewFile();
			sb.append(getParameterValue(CTL_FORMULA_PARAM));
			sb.newLine();
			sb.close();
		} catch (Exception e) {
			String report = "An error occurred during service invocation :" + e + e.getMessage();
			addResult (new ServiceResult(false,report,this));
			return report;
		}

		return super.run();
	}
	
}
