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

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServiceResult extends SimpleObservable {

	public enum Status {
		FAIL, OK, NOK
	};

	private Status success;
	private String report;
	private Calendar date;
	private IServiceResultProvider cs;
	private ParameterList parameters;

	public ServiceResult(Status success, String report, AbstractCheckService cs) {
		this.success = success;
		this.report = report;
		this.date = new GregorianCalendar();
		this.cs = cs;
		this.parameters = new ParameterList(cs.getParameters());
	}

	public ParameterList getParameters() {
		return parameters;
	}

	public String getReport() {
		return report;
	}

	public Status getSuccess() {
		return success;
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy HH:mm:ss");
		return formatter.format(date.getTime());
	}

	public IServiceResultProvider getParent() {
		return cs;
	}

	/** a nice short label describing this result, used in tree label provider. */
	@Override
	public String toString() {
		String ret = success.toString();
		ret += getDate();
		return ret;
	}

}
