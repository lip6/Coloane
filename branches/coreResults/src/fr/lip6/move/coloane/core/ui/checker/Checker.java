package fr.lip6.move.coloane.core.ui.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checker {
	
	private String formalismName;
	
	private Map<String,List<NodeChecker>> nodeCheckers;
	private Map<String,List<ArcChecker>> arcCheckers;
	private Map<String,List<GraphChecker>> graphCheckers;
	
	/**
	 * Liste des attributeChecker ordonné par type de noeud puis par nom d'attributs
	 */
	private Map<String,Map<String,List<AttributeChecker>>> nodeAttributeCheckers;
	private Map<String,Map<String,List<AttributeChecker>>> arcAttributeCheckers;
	private Map<String,Map<String,List<AttributeChecker>>> graphAttributeCheckers;

	Checker(String formalismName) {
		this.formalismName = formalismName;
		this.nodeAttributeCheckers = new HashMap<String,Map<String,List<AttributeChecker>>>();
		this.arcAttributeCheckers = new HashMap<String,Map<String,List<AttributeChecker>>>();
		this.graphAttributeCheckers = new HashMap<String,Map<String,List<AttributeChecker>>>();
		
		this.nodeCheckers = new HashMap<String,List<NodeChecker>>();
		this.arcCheckers = new HashMap<String,List<ArcChecker>>();
		this.graphCheckers = new HashMap<String,List<GraphChecker>>();
	}
	
	
	
	
	
	
	
	public void addArcChecker(String arcType, ArcChecker arcChecker) {
		if (arcCheckers.containsKey(arcType)) {
			arcCheckers.get(arcType).add(arcChecker);
		} else {
			List<ArcChecker> list = new ArrayList<ArcChecker>();
			list.add(arcChecker);
			arcCheckers.put(arcType, list);
		}
	}
	
	public void addNodeChecker(String nodeType, NodeChecker nodeChecker) {
		if (nodeCheckers.containsKey(nodeType)) {
			nodeCheckers.get(nodeType).add(nodeChecker);
		} else {
			List<NodeChecker> list = new ArrayList<NodeChecker>();
			list.add(nodeChecker);
			nodeCheckers.put(nodeType, list);
		}
	}
	
	public void addGraphChecker(String graphType, GraphChecker graphChecker) {
		if (graphCheckers.containsKey(graphType)) {
			graphCheckers.get(graphType).add(graphChecker);
		} else {
			List<GraphChecker> list = new ArrayList<GraphChecker>();
			list.add(graphChecker);
			graphCheckers.put(graphType, list);
		}
	}
	
	public void addArcAttributeChecker(String arcType, String attributeName, AttributeChecker arcAttributeChecker) {
		if (arcAttributeCheckers.containsKey(arcType)) {
			if (arcAttributeCheckers.get(arcType).containsKey(attributeName)) {
				arcAttributeCheckers.get(arcType).get(attributeName).add(arcAttributeChecker);
			} else {
				List<AttributeChecker> list = new ArrayList<AttributeChecker>();
				list.add(arcAttributeChecker);
				arcAttributeCheckers.get(arcType).put(attributeName, list);
			}
		} else {
			HashMap<String,List<AttributeChecker>> map = new HashMap<String,List<AttributeChecker>>();
			List<AttributeChecker> list = new ArrayList<AttributeChecker>();
			
			list.add(arcAttributeChecker);
			map.put(attributeName, list);
			this.arcAttributeCheckers.put(arcType, map);
		}
	}
	
	public void addNodeAttributeChecker(String nodeType, String attributeName, AttributeChecker nodeAttributeChecker) {
		if (nodeAttributeCheckers.containsKey(nodeType)) {
			if (nodeAttributeCheckers.get(nodeType).containsKey(attributeName)) {
				nodeAttributeCheckers.get(nodeType).get(attributeName).add(nodeAttributeChecker);
			} else {
				List<AttributeChecker> list = new ArrayList<AttributeChecker>();
				list.add(nodeAttributeChecker);
				nodeAttributeCheckers.get(nodeType).put(attributeName, list);
			}
		} else {
			HashMap<String,List<AttributeChecker>> map = new HashMap<String,List<AttributeChecker>>();
			List<AttributeChecker> list = new ArrayList<AttributeChecker>();
			
			list.add(nodeAttributeChecker);
			map.put(attributeName, list);
			this.nodeAttributeCheckers.put(nodeType, map);
		}
	}
	
	public void addGraphAttributeChecker(String graphType, String attributeName, AttributeChecker graphAttributeChecker) {
		if (graphAttributeCheckers.containsKey(graphType)) {
			if (graphAttributeCheckers.get(graphType).containsKey(attributeName)) {
				graphAttributeCheckers.get(graphType).get(attributeName).add(graphAttributeChecker);
			} else {
				List<AttributeChecker> list = new ArrayList<AttributeChecker>();
				list.add(graphAttributeChecker);
				graphAttributeCheckers.get(graphType).put(attributeName, list);
			}
		} else {
			HashMap<String,List<AttributeChecker>> map = new HashMap<String,List<AttributeChecker>>();
			List<AttributeChecker> list = new ArrayList<AttributeChecker>();
			
			list.add(graphAttributeChecker);
			map.put(attributeName, list);
			this.nodeAttributeCheckers.put(graphType, map);
		}
	}

	public String getFormalismName() {
		return this.formalismName;
	}
}
