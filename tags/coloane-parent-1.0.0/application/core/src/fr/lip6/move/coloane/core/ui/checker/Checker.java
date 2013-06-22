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
package fr.lip6.move.coloane.core.ui.checker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A checker contains all necessary checkers like {@link GraphChecker}, {@link NodeChecker}, {@link ArcChecker} and {@link AttributeChecker} for checking a graph.
 * @author Florian David
 */
public class Checker {
	/** Map of {@NodeChecker} stored by node formalism */
	private Map<String, List<NodeChecker>> nodeCheckers;
	/** Map of {@ArcChecker} stored by arc formalism */
	private Map<String, List<ArcChecker>> arcCheckers;
	/** List of {@GraphChecker} */
	private List<GraphChecker> graphCheckers;

	/** Map of {@AttributeChecker} for {@link INode} attributes stored by node formalism and then by attribute name */
	private Map<String, Map<String, List<AttributeChecker>>> nodeAttributeCheckers;
	/** Map of {@AttributeChecker} for {@link IArc} attributes stored by arc formalism and then by attribute name */
	private Map<String, Map<String, List<AttributeChecker>>> arcAttributeCheckers;
	/** Map of {@AttributeChecker} for {@link IGraph} attributes stored by attribute name */
	private Map<String, List<AttributeChecker>> graphAttributeCheckers;

	/** Empty {@AttributeChecker} list */
	private List<AttributeChecker> emptyAttributeList;
	/** Empty {@NodeChecker} list */
	private List<NodeChecker> emptyNodeList;
	/** Empty {@ArcChecker} list */
	private List<ArcChecker> emptyArcList;

	/**
	 * Constructor
	 */
	public Checker() {
		this.emptyAttributeList = Collections.unmodifiableList(new ArrayList<AttributeChecker>(0));
		this.emptyNodeList = Collections.unmodifiableList(new ArrayList<NodeChecker>(0));
		this.emptyArcList = Collections.unmodifiableList(new ArrayList<ArcChecker>(0));

		this.nodeAttributeCheckers = new HashMap<String, Map<String, List<AttributeChecker>>>();
		this.arcAttributeCheckers = new HashMap<String, Map<String, List<AttributeChecker>>>();
		this.graphAttributeCheckers = new HashMap<String, List<AttributeChecker>>();

		this.nodeCheckers = new HashMap<String, List<NodeChecker>>();
		this.arcCheckers = new HashMap<String, List<ArcChecker>>();
		this.graphCheckers = new ArrayList<GraphChecker>();
	}

	/**
	 * This method returns the list of {@NodeChecker} for the given node formalism name.
	 * @param nodeFormalism the name of the node formalism.
	 * @return a list of {@NodeChecker}. Never returns <code>null</code>.
	 */
	public final List<NodeChecker> getNodeCheckers(String nodeFormalism) {
		if (nodeCheckers.containsKey(nodeFormalism)) {
			return nodeCheckers.get(nodeFormalism);
		} else {
			return emptyNodeList;
		}
	}

	/**
	 * This method returns the list of {@ArcChecker} for the given arc formalism name.
	 * @param arcFormalism the name of the arc formalism.
	 * @return a list of {@ArcChecker}. Never returns <code>null</code>.
	 */
	public final List<ArcChecker> getArcCheckers(String arcFormalism) {
		if (arcCheckers.containsKey(arcFormalism)) {
			return arcCheckers.get(arcFormalism);
		} else {
			return emptyArcList;
		}
	}

	/**
	 * This method returns the list of {@GraphChecker}.
	 * @return a list of {@GraphChecker}. Never returns <code>null</code>.
	 */
	public final List<GraphChecker> getGraphCheckers() {
		return graphCheckers;
	}

	/**
	 * This method returns the list of {@AttributeChecker} for the given node formalism name and attribute name.<br>
	 * Only for node attribute.
	 * @param nodeFormalism the name of the node formalism.
	 * @param attributeName the name of the attribute.
	 * @return list of {@AttributeChecker}. Never returns <code>null</code>.
	 */
	public final List<AttributeChecker> getNodeAttributeCheckers(String nodeFormalism, String attributeName) {
		if (nodeAttributeCheckers.containsKey(nodeFormalism)) {
			 if (nodeAttributeCheckers.get(nodeFormalism).containsKey(attributeName)) {
				 return nodeAttributeCheckers.get(nodeFormalism).get(attributeName);
			 } else {
				 return emptyAttributeList;
			 }
		} else {
			return emptyAttributeList;
		}
	}

	/**
	 * This method returns the list of {@AttributeChecker} for the given arc formalism name and attribute name.<br>
	 * Only for arc attribute.
	 * @param arcFormalism the name of the arc formalism.
	 * @param attributeName the name of the attribute.
	 * @return list of {@AttributeChecker}. Never returns <code>null</code>.
	 */
	public final List<AttributeChecker> getArcAttributeCheckers(String arcFormalism, String attributeName) {
		if (arcAttributeCheckers.containsKey(arcFormalism)) {
			 if (arcAttributeCheckers.get(arcFormalism).containsKey(attributeName)) {
				 return arcAttributeCheckers.get(arcFormalism).get(attributeName);
			 } else {
				 return emptyAttributeList;
			 }
		} else {
			return emptyAttributeList;
		}
	}

	/**
	 * This method returns the list of {@AttributeChecker} for the given attribute name.
	 * Only for graph attribute.
	 * @param attributeName the name of the attribute.
	 * @return list of {@AttributeChecker}. Never returns <code>null</code>.
	 */
	public final List<AttributeChecker> getGraphAttributeCheckers(String attributeName) {
		 if (graphAttributeCheckers.containsKey(attributeName)) {
			 return graphAttributeCheckers.get(attributeName);
		 } else {
			 return emptyAttributeList;
		 }
	}

	/**
	 * Add a {@NodeChecker} for the given node formalism name.
	 * @param nodeFormalism the name of the node formalism.
	 * @param nodeChecker the nodeChecker to add.
	 */
	public final void addNodeChecker(String nodeFormalism, NodeChecker nodeChecker) {
		if (nodeCheckers.containsKey(nodeFormalism)) {
			nodeCheckers.get(nodeFormalism).add(nodeChecker);
		} else {
			List<NodeChecker> list = new ArrayList<NodeChecker>();
			list.add(nodeChecker);
			nodeCheckers.put(nodeFormalism, list);
		}
	}

	/**
	 * Add an {@ArcChecker} for the given arc formalism name.
	 * @param arcFormalism the name of the arc formalism.
	 * @param arcChecker the arcChecker to add.
	 */
	public final void addArcChecker(String arcFormalism, ArcChecker arcChecker) {
		if (arcCheckers.containsKey(arcFormalism)) {
			arcCheckers.get(arcFormalism).add(arcChecker);
		} else {
			List<ArcChecker> list = new ArrayList<ArcChecker>();
			list.add(arcChecker);
			arcCheckers.put(arcFormalism, list);
		}
	}

	/**
	 * Add a {@GraphChecker}.
	 * @param graphChecker the graphChecker to add.
	 */
	public final void addGraphChecker(GraphChecker graphChecker) {
		graphCheckers.add(graphChecker);
	}

	/**
	 * Add an {@AttributeChecker} for the given node formalism name and attribute name.<br>
	 * Only for node attribute.
	 * @param nodeFormalism the name of the node formalism.
	 * @param attributeName the name of the attribute.
	 * @param nodeAttributeChecker the node AttributeChecker to add.
	 */
	public final void addNodeAttributeChecker(String nodeFormalism, String attributeName, AttributeChecker nodeAttributeChecker) {
		if (nodeAttributeCheckers.containsKey(nodeFormalism)) {
			if (nodeAttributeCheckers.get(nodeFormalism).containsKey(attributeName)) {
				nodeAttributeCheckers.get(nodeFormalism).get(attributeName).add(nodeAttributeChecker);
			} else {
				List<AttributeChecker> list = new ArrayList<AttributeChecker>();
				list.add(nodeAttributeChecker);
				nodeAttributeCheckers.get(nodeFormalism).put(attributeName, list);
			}
		} else {
			Map<String, List<AttributeChecker>> map = new HashMap<String, List<AttributeChecker>>();
			List<AttributeChecker> list = new ArrayList<AttributeChecker>();

			list.add(nodeAttributeChecker);
			map.put(attributeName, list);
			this.nodeAttributeCheckers.put(nodeFormalism, map);
		}
	}

	/**
	 * Add an {@AttributeChecker} for the given arc formalism name and attribute name.<br>
	 * Only for arc attribute.
	 * @param arcFormalism the name of the arc formalism.
	 * @param attributeName the name of the attribute.
	 * @param arcAttributeChecker the arc AttributeChecker to add.
	 */
	public final void addArcAttributeChecker(String arcFormalism, String attributeName, AttributeChecker arcAttributeChecker) {
		if (arcAttributeCheckers.containsKey(arcFormalism)) {
			if (arcAttributeCheckers.get(arcFormalism).containsKey(attributeName)) {
				arcAttributeCheckers.get(arcFormalism).get(attributeName).add(arcAttributeChecker);
			} else {
				List<AttributeChecker> list = new ArrayList<AttributeChecker>();
				list.add(arcAttributeChecker);
				arcAttributeCheckers.get(arcFormalism).put(attributeName, list);
			}
		} else {
			Map<String, List<AttributeChecker>> map = new HashMap<String, List<AttributeChecker>>();
			List<AttributeChecker> list = new ArrayList<AttributeChecker>();

			list.add(arcAttributeChecker);
			map.put(attributeName, list);
			this.arcAttributeCheckers.put(arcFormalism, map);
		}
	}

	/**
	 * Add an {@AttributeChecker} for the given attribute name.<br>
	 * Only for graph attribute.
	 * @param attributeName the name of the attribute.
	 * @param graphAttributeChecker the graph AttributeChecker to add.
	 */
	public final void addGraphAttributeChecker(String attributeName, AttributeChecker graphAttributeChecker) {
		if (graphAttributeCheckers.containsKey(attributeName)) {
			graphAttributeCheckers.get(attributeName).add(graphAttributeChecker);
		} else {
			List<AttributeChecker> list = new ArrayList<AttributeChecker>();
			list.add(graphAttributeChecker);
			graphAttributeCheckers.put(attributeName, list);
		}
	}
}
