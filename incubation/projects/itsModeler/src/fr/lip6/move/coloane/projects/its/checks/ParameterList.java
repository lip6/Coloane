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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

public class ParameterList extends SimpleObservable {

	
	private Map<String, String> parameters = new HashMap<String, String>();
	private Map<String, Boolean> bParameters = new HashMap<String, Boolean>();

	
	public ParameterList() {
	}
	
	public ParameterList(ParameterList other) {
		parameters = new HashMap<String, String>(other.parameters);
		bParameters = new HashMap<String, Boolean>(other.bParameters);
	}

	public Set<String> getParameters () {
		return parameters.keySet();
	}

	public Set<String> getBoolParameters () {
		return bParameters.keySet();
	}

	public Boolean getBoolParameterValue (String bParam) {
		return bParameters.get(bParam);
	}
	
	public String getParameterValue (String param) {
		return parameters.get(param);
	}
	
	public void addParameter (String paramName) {
		parameters.put(paramName, "");
	}

	
	public boolean setParameterValue (String param, String value) {
		if (parameters.containsKey(param)) {
			if (!parameters.get(param).equals(value)) {
				parameters.put(param,value);
				notifyObservers();
			}
			return true;
		}
		return false;
	}

	public boolean setBoolParameterValue (String param, boolean value) {
		if (bParameters.containsKey(param)) {
			if (!bParameters.get(param).equals(value)) {
				bParameters.put(param,value);
				notifyObservers();
			}
			return true;
		}
		return false;
	}
	
	public void addBooleanParameter(String bparam, boolean initial) {
		bParameters.put(bparam, initial);
	}
	
	@Override
	public String toString() {
		return parameters.toString() + bParameters.toString(); 
	}
}
