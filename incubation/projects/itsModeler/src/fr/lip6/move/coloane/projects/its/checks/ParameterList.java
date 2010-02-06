package fr.lip6.move.coloane.projects.its.checks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;

public class ParameterList extends SimpleObservable {

	
	private Map<String, String> parameters = new HashMap<String, String>();

	public ParameterList() {
	}
	
	public ParameterList(ParameterList other) {
		parameters = new HashMap<String, String>(other.parameters);
	}

	public Set<String> getParameters () {
		return parameters.keySet();
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
}
