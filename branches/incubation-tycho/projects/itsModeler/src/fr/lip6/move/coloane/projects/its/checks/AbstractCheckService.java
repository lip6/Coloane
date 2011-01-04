package fr.lip6.move.coloane.projects.its.checks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import fr.lip6.move.coloane.interfaces.utils.ProcessController;
import fr.lip6.move.coloane.interfaces.utils.ProcessController.TimeOutException;
import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSEditorPlugin;

public abstract class AbstractCheckService extends SimpleObservable implements IServiceResultProvider {

	private static final int DEFAULT_TIMEOUT = 60;
	private static final String TIMEOUT_DURATION = "Maximum execution time";
	protected String name ;

	protected abstract IPath getToolPath();

	protected abstract List<String> buildCommandArguments();

	private String workdir;
	protected CheckList parent;
	private List<ServiceResult> results = new LinkedList<ServiceResult>();
	private String reportText;
	private ParameterList parameters = new ParameterList();

	
	public AbstractCheckService(CheckList parent, String serviceName) {
		this.parent = parent;
		this.name = serviceName;
		parameters.addParameter(TIMEOUT_DURATION);
		parameters.setParameterValue(TIMEOUT_DURATION,Integer.toString(DEFAULT_TIMEOUT));
	}
	
	/**
	 * format error message
	 * @param errorOutput the message
	 * @return the formatted message
	 */
	private static String createContentMessage(ByteArrayOutputStream errorOutput) {
		if (errorOutput.size() == 0) {
			return "";
		}
		return " Process produced the following error output: \n" + errorOutput;
	}

	public String getName() {
		return name;
	}

	public void setWorkdir(String workdir) {
		if (! workdir.equals(this.workdir)) {
			this.workdir = workdir;
			notifyObservers();
		}
	}

	public ParameterList getParameters() {
		return parameters;
	}

	public String getWorkDir (IFile position) {
		if (workdir == null) {
			workdir = getDefaultWorkDir(position);
		}
		return getWorkDir();
	}
	
	public String getWorkDir() {
		return workdir;
	}

	public IPath getWorkDirPath() {
		return new Path(workdir);
	}


	public abstract String run();
	
	/**
	 * Bare bones API for launching dot. Command line options are passed to
	 * Graphviz as specified in the options parameter. The location for dot is
	 * obtained from the user preferences.
	 * 
	 * @param options
	 *            command line options for dot
	 * @return a non-zero integer if errors happened
	 * @throws IOException
	 */
	public IStatus runTool(IPath workdir) {
		IPath toolFullPath = getToolPath();
		if (toolFullPath == null || toolFullPath.isEmpty()) {
			return new Status(
					IStatus.ERROR,
					ITSEditorPlugin.getID(),
			"Please specify the absolute path to the tool in the preferences page Coloane->ITS Path.");
		}
		if (!toolFullPath.toFile().isFile()) {
			return new Status(IStatus.ERROR, ITSEditorPlugin.getID(), "Could not find ITS tool at \"" + toolFullPath
					+ "\"");
		}
		List<String> cmd = buildCommandArguments();
		ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
		ByteArrayOutputStream stdOutput = new ByteArrayOutputStream();
	
		int timeout = DEFAULT_TIMEOUT;
		try {
			timeout = Integer.parseInt(parameters.getParameterValue(TIMEOUT_DURATION));
		} catch (NumberFormatException e) {
			// NOP, stay at 60 secs.
		}		
			
		try {
			final ProcessController controller =
				new ProcessController(timeout * 1000, cmd.toArray(new String[cmd.size()]), null, workdir.toFile());
			controller.forwardErrorOutput(errorOutput);
			controller.forwardOutput(stdOutput);
			int exitCode = controller.execute();
			if (exitCode != 0) {
				//				return new Status(IStatus.WARNING, ITSEditorPlugin.getID(), "ITS exit code: " + exitCode + "."
				//						+ createContentMessage(errorOutput));
				//			}
				if (errorOutput.size() > 0) {
					return new Status(IStatus.WARNING, ITSEditorPlugin.getID(), createContentMessage(errorOutput));
				}
			}
			reportText = stdOutput.toString();
			return Status.OK_STATUS;
		} catch (TimeOutException e) {
			return new Status(IStatus.ERROR, ITSEditorPlugin.getID(), "Check Service process did not finish in a timely way."
					+ createContentMessage(errorOutput));
		} catch (IOException e) {
			return new Status(IStatus.ERROR, ITSEditorPlugin.getID(), "Unexpected exception executing service."
					+ createContentMessage(errorOutput), e);
		}
	}





	public void addResult(ServiceResult serviceResult) {
		results.add(serviceResult);
		notifyObservers();
	}

	public Iterator<ServiceResult> iterator() {
		return results.iterator();
	}

	public CheckList getParent() {
		return parent;
	}

	protected void setReport(String report) {
		this.reportText = report;
	}

	public String getReportText() {
		return reportText;
	}

	
	public String getDefaultWorkDir (IFile file) {
		IProject proj = file.getProject();		
		IFolder folder = proj.getFolder(getParent().getType().getTypeName() + "_" + getName());
		try {
			folder.create(true, true, null);
		} catch (CoreException e) {
			// folder exists, it's OK.
		}
		return folder.getLocation().toOSString();
	}

}