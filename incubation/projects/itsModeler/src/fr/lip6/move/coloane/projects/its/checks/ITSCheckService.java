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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.checks;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.projects.its.checks.ServiceResult.Status;
import fr.lip6.move.coloane.projects.its.io.model.ITSModelWriter;
import fr.lip6.move.coloane.projects.its.order.ITSOrderWriter;
import fr.lip6.move.coloane.projects.its.order.Ordering;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;

public abstract class ITSCheckService extends AbstractCheckService {

	private static final String ORDER_FILE_NAME = "model.ord";
	private static final String QUIET_PARAMETER = "Low Verbosity";
	private OrderParameter order;

	public ITSCheckService(CheckList parent, String serviceName) {
		super(parent, serviceName);
		order = new OrderParameter(parent.getOrders());
		getParameters().addBooleanParameter(QUIET_PARAMETER, true);
	}

	@Override
	protected List<String> buildCommandArguments() {
		List<String> cmd = new ArrayList<String>();
		cmd.add(getToolPath().toOSString());
		if (getParameters().getBoolParameterValue(QUIET_PARAMETER)) {
			cmd.add("--quiet");
		}
		cmd.add("-i");
		cmd.add("modelMain.xml");
		cmd.add("-t");
		cmd.add("ITSXML");
		// Handle order input
		if (buildOrderFile()) {
			cmd.add("--load-order");
			cmd.add(getOrderFileName());
		}

		return cmd;
	}

	private boolean buildOrderFile() {
		Ordering o = order.getSelection();
		if (o == null) {
			return false;
		}

		// build the file
		ITSOrderWriter ow = new ITSOrderWriter();
		try {
			ow.writeOrder(getOrderFileName(), getParent().getType(), o);
		} catch (ExtensionException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private String getOrderFileName() {
		return getWorkDir() + "/" + ORDER_FILE_NAME;
	}

	@Override
	public final String run() {
		ITSModelWriter mw = new ITSModelWriter();
		String report;
		Status success = Status.OK;
		try {
			mw.exportITSModel(getParent().getType().getTypeList(), getParent().getType(),
					getWorkDir());
			report = "Run successful in folder " + getWorkDir();
		} catch (Exception e) {
			success = Status.FAIL;
			report = "An error occurred during Export phase of service invocation :"
					+ e + e.getMessage();
		}

		// RUN THE SERVICE
		IStatus status = runTool(getWorkDirPath());
		if (!status.isOK()) {
			success = Status.FAIL;
			report += "An error occurred during ITS service invocation :"
					+ status.getMessage();
		} else {
			report = getReportText();

			success = interpretResult(report);
		}
		addResult(new ServiceResult(success, report, this));
		return report;
	}

	protected Status interpretResult(String report) {
		return Status.OK;
	}

	public OrderParameter getOrder() {
		return order;
	}
}
