/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.tools.graphviz;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.interfaces.utils.ProcessController;
import fr.lip6.move.coloane.interfaces.utils.ProcessController.TimeOutException;
import fr.lip6.move.coloane.tools.graphviz.GraphVizActivator.DotAlgo;
import fr.lip6.move.coloane.tools.graphviz.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Point;


/**
 * The entry point to the Graphviz support API.
 */
// TODO generate and load have a lot in common, refactor
// TODO we should just pass the input stream directly (or buffered) to Graphviz, instead of creating a temporary file
public final class GraphViz {
	private static final String DOT_EXTENSION = ".dot"; //$NON-NLS-1$
	private static final String TMP_FILE_PREFIX = "graphviz"; //$NON-NLS-1$
	
	/** hide ctor.
	 */
	private GraphViz() { }

	/**
	 * Generate an output from dot in the provided location.
	 * @param input input to pass to dot
	 * @param format the output format
	 * @param dimension the size of the graph
	 * @param outputLocation the output file to create
	 * @throws CoreException in case of problems
	 */
	public static void generate(final InputStream input, String format, Point dimension, IPath outputLocation)
	throws CoreException {
		MultiStatus status = new MultiStatus(GraphVizActivator.getID(), 0, "Errors occurred while running Graphviz", null);
		File dotInput = null, dotOutput = outputLocation.toFile();
		// we keep the input in memory so we can include it in error messages
		ByteArrayOutputStream dotContents = new ByteArrayOutputStream();
		try {
			// determine the temp input location
			dotInput = File.createTempFile(TMP_FILE_PREFIX, DOT_EXTENSION);
			// dump the contents from the input stream into the temporary file
			// to be submitted to dot
			FileOutputStream tmpDotOutputStream = null;
			try {
				IOUtils.copy(input, dotContents);
				tmpDotOutputStream = new FileOutputStream(dotInput);
				IOUtils.copy(new ByteArrayInputStream(dotContents.toByteArray()), tmpDotOutputStream);
			} finally {
				IOUtils.closeQuietly(tmpDotOutputStream);
			}
			IStatus result = runDot(format, dimension, dotInput, dotOutput);
			if (dotOutput.isFile()) {
				if (!result.isOK()) {
					Logger.getLogger("graphviz").info(status.getMessage());
					GraphVizActivator.getInstance().getLog().log(result);
					Coloane.showErrorMsg(result.getMessage());
					throw new CoreException(result);
				}
				// success!
				return;
			}
		} catch (IOException e) {
			status.add(new Status(IStatus.ERROR, GraphVizActivator.getID(), "", e));
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {

			dotInput.delete();
			IOUtils.closeQuietly(input);
		}
		throw new CoreException(status);
	}

	/**
	 * Runs dot with given parameters.
	 * @param format dot output format
	 * @param dimension graph size
	 * @param dotInput input file
	 * @param dotOutput output file
	 * @return the status at end of run
	 */
	public static IStatus runDot(String format, Point dimension, File dotInput, File dotOutput) {
		// build the command line
		double dpi = 96;
		double widthInInches = dimension.x / dpi;
		double heightInInches = dimension.y / dpi;
		List<String> cmd = new ArrayList<String>();
		cmd.add("-o" + dotOutput.getAbsolutePath());
		cmd.add("-T" + format);
		DotAlgo algo = GraphVizActivator.getInstance().getDotAlgo();
		cmd.add("-K" + algo);
		if (widthInInches > 0 && heightInInches > 0) {
			cmd.add("-Gsize=" + widthInInches + ',' + heightInInches);
		}
		cmd.add(dotInput.getAbsolutePath());
		return runDot(cmd.toArray(new String[cmd.size()]));
	}

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
	public static IStatus runDot(String... options) {
		IPath dotFullPath = GraphVizActivator.getInstance().getDotLocation();
		if (dotFullPath == null || dotFullPath.isEmpty()) {
			return new Status(
					IStatus.ERROR,
					GraphVizActivator.getID(),
					"dot.exe/dot not found in PATH. Please install it from graphviz.org, update the PATH or specify the absolute path through:  Window->Preferences->Coloane->Layout preferences.");
		}
		if (!dotFullPath.toFile().isFile()) {
			return new Status(IStatus.ERROR, GraphVizActivator.getID(), "Could not find Graphviz dot at \"" + dotFullPath
					+ "\"");
		}
		List<String> cmd = new ArrayList<String>();
		cmd.add(dotFullPath.toOSString());
		cmd.addAll(Arrays.asList(options));
		ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();
		try {
			final ProcessController controller =
				new ProcessController(60000, cmd.toArray(new String[cmd.size()]), null, dotFullPath
						.removeLastSegments(1).toFile());
			controller.forwardErrorOutput(errorOutput);
			controller.forwardOutput(System.out);
			int exitCode = controller.execute();
			if (exitCode != 0) {
				return new Status(IStatus.WARNING, GraphVizActivator.getID(), "Graphviz exit code: " + exitCode + "."
						+ createContentMessage(errorOutput));
			}
			if (errorOutput.size() > 0) {
				return new Status(IStatus.WARNING, GraphVizActivator.getID(), createContentMessage(errorOutput));
			}
			return Status.OK_STATUS;
		} catch (TimeOutException e) {
			return new Status(IStatus.ERROR, GraphVizActivator.getID(), "Graphviz process did not finish in a timely way."
					+ createContentMessage(errorOutput));
		} catch (IOException e) {
			return new Status(IStatus.ERROR, GraphVizActivator.getID(), "Unexpected exception executing Graphviz."
					+ createContentMessage(errorOutput), e);
		}
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
		return " dot produced the following error output: \n" + errorOutput;
	}

	/**
	 * Call dot and return the output as stream.
	 * @param dotInput the input file
	 * @param format the dot output format
	 * @param graphSize the graph size
	 * @return dot output
	 * @throws CoreException if any problems
	 */
	public static InputStream generate(
			InputStream dotInput, String format,
			Point graphSize) throws CoreException {
		File dotOutput = null;
		IStatus status = null;
		try {
			dotOutput = File.createTempFile(TMP_FILE_PREFIX, "." + format);
			IPath outputLocation  = new Path(dotOutput.getAbsolutePath());
			generate(dotInput, format, graphSize, outputLocation);
			return new FileInputStream(dotOutput);
		} catch (IOException e) {
			status = new Status(IStatus.WARNING, GraphVizActivator.getID(), "Problem running graphviz."
					+ e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			dotOutput.delete();
		}
		throw new CoreException(status);
	}
}
