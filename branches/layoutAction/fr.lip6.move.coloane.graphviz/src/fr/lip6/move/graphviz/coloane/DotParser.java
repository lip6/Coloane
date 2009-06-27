package fr.lip6.move.graphviz.coloane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

/*
 plain ,
 plain-ext 
 The plain and plain-ext formats produce output using a simple, line-based language. The latter format differs in that, on edges, it provides port names on head and tail nodes when applicable. 
 There are four types of statements. 

 graph scale width height
 node name x y width height label style shape color fillcolor
 edge tail head n x1 y1 .. xn yn [label xl yl] style color
 stop

 ***graph 
 The width and height values give the width and height of the drawing. 
 The lower left corner of the drawing is at the origin. 
 The scale value indicates how the drawing should be scaled if a size attribute was given and the drawing needs to be scaled to conform to that size. 
 If no scaling is necessary, it will be set to 1.0. Note that all graph, node and edge coordinates and lengths are given unscaled. 
 ***node 
 The name value is the name of the node, and x and y give the node's position. 
 The width and height are the width and height of the node. 
 The label, style, shape, color and fillcolor give the node's label, style, shape, color and fillcolor, respectively, using attribute default values where necessary. 
 If the node does not have a style attribute, "solid" is used. 
 ***edge 
 The tail and head values give the names of the head and tail nodes. 
 In plain-ext format, the head or tail name will be appended with a colon and a portname if the edge connects to the node at a port. 
 n is the number of control points defining the B-spline forming the edge. 
 This is followed by 2*n numbers giving the x and y coordinates of the control points in order from tail to head. 
 If the edge has a label, this comes next followed by the x and y coordinates of the label's position. 
 The edge description is completed by the edge's style and color. As with nodes, if a style is not defined, "solid" is used. 
 Note: The control points given in an edge statement define the body of the edge. 
 In particular, if the edge has an arrowhead to the head or tail node, there will be a gap 
 between the last or first control points and the boundary of the associated node. 
 [NDE YANN: This behavior is fine in our setting, actually don't want dot to compute the edge]

 Example: IDXX is object of ID "XX" in the IGRaph


graph 0.031513 2.5833 6.6111
node ID3 1.8889 6.3611 0.75 0.5 ID3 solid ellipse black lightgrey
node ID4 0.375 3.9167 0.75 0.5 ID4 solid ellipse black lightgrey
node ID5 0.40278 1.4722 0.75 0.5 ID5 solid ellipse black lightgrey
node ID6 1.4028 1.4722 0.75 0.5 ID6 solid ellipse black lightgrey
node ID7 1.4028 5.1389 0.75 0.5 ID7 solid ellipse black lightgrey
node ID8 0.40278 2.6944 0.75 0.5 ID8 solid ellipse black lightgrey
node ID9 1.4028 2.6944 0.75 0.5 ID9 solid ellipse black lightgrey
node ID10 1.4028 0.25 0.81989 0.5 ID10 solid ellipse black lightgrey
node ID11 1.4028 3.9167 0.81989 0.5 ID11 solid ellipse black lightgrey
edge ID3 ID7 7 1.7448 6.1275 1.6979 6.0453 1.6485 5.951 1.6111 5.8611 1.5672 5.7555 1.529 5.6376 1.4976 5.5282 ID18 1.8056 5.75 solid black
edge ID4 ID8 4 0.38076 3.6634 0.38452 3.4979 0.38951 3.2783 0.39379 3.09 ID20 0.59722 3.3056 solid black
edge ID5 ID10 7 0.56898 1.2478 0.67733 1.1036 0.82321 0.91333 0.95833 0.75 1.0053 0.69329 1.0562 0.63401 1.1063 0.57692 ID13 1.1528 0.86111 solid black
edge ID6 ID10 4 1.4028 1.219 1.4028 1.0535 1.4028 0.83387 1.4028 0.64558 ID16 1.5972 0.86111 solid black
edge ID7 ID4 7 1.2058 4.9218 1.1278 4.8347 1.0379 4.7328 0.95833 4.6389 0.85252 4.514 0.73886 4.3746 0.64024 4.2518 ID19 1.1528 4.5278 solid black
edge ID8 ID5 4 0.40278 2.4412 0.40278 2.2757 0.40278 2.0561 0.40278 1.8678 ID21 0.59722 2.0833 solid black
edge ID9 ID6 4 1.4028 2.4412 1.4028 2.2757 1.4028 2.0561 1.4028 1.8678 ID17 1.5972 2.0833 solid black
edge ID10 ID3 10 1.6403 0.45355 1.8735 0.67731 2.1944 1.0601 2.1944 1.4722 2.1944 5.1389 2.1944 5.1389 2.1944 5.1389 2.1944 5.4269 2.1136 5.742 2.0351 5.9801 ID12 2.3889 3.3056 solid black
edge ID11 ID9 4 1.4028 3.6634 1.4028 3.4979 1.4028 3.2783 1.4028 3.09 ID14 1.5972 3.3056 solid black
stop

 */

public class DotParser {

	public static void parseGraphPositions(InputStream input, IGraph graph) throws IOException {
		BufferedReader in = new BufferedReader ( new InputStreamReader(input) );
		parseGraphDescription(in);
		while (in.ready()){
			String line = in.readLine();
			StringTokenizer st = new StringTokenizer(line," ");
			assert st.hasMoreElements();
			String cmdType = st.nextToken();
			if ("stop".equals(cmdType)) {
				return;
			} else if ( "node".equals(cmdType)) {
				parseNode(st,graph);
			} else if ( "edge".equals(cmdType)) {
				parseArc(st,graph);				
			}
		}
	}
	/**
	 * NB: edge token consumed
	 *  edge tail head n x1 y1 .. xn yn [label xl yl] style color
	 *
	 * edge ID7 ID11 4 1.4028 4.8856 1.4028 4.7201 1.4028 4.5005 1.4028 4.3122 ID15 1.5972 4.5278 solid black
	 * @param st
	 * @param graph
	 */
	private static void parseArc(StringTokenizer st, IGraph graph) {
		assert st.countTokens() > 3;
		@SuppressWarnings("unused")
		int idSource = parseID(st);
		@SuppressWarnings("unused")
		int idDest = parseID(st);
		int nbInflex = Integer.parseInt(st.nextToken());
		List<Point> newPi = new ArrayList<Point>();
		for (int i = 0; i < nbInflex ; i++) {
			Point pi = parsePoint(st);
			newPi.add(pi);
		}
		int idArc = parseID(st);
		IArc arc = graph.getArc(idArc);
		List<AbsoluteBendpoint> l = arc.getInflexPoints();
		int oldsize = l.size();
		for (int i = 0; i < nbInflex ; i++) {
			if ( i < oldsize ) {
				arc.modifyInflexPoint(i, newPi.get(i));
			} else {
				arc.addInflexPoint(newPi.get(i));
			}
		}
	}

	private static Point parsePoint(StringTokenizer st) {
		float x = 100*Float.parseFloat(st.nextToken());
		float y = 100*Float.parseFloat(st.nextToken());
		return new Point(x,y);
	}
	/**
	 * (node token is already consumed)
	 * node name x y width height label style shape color fillcolor
	 * e.g.
	 * node ID3 1.8889 6.3611 0.75 0.5 ID3 solid ellipse black lightgrey
	 * @param st
	 * @param graph
	 * @throws IOException 
	 */
	private static void parseNode(StringTokenizer st, IGraph graph) throws IOException {
		try {
			assert st.countTokens() > 3;
			int id = parseID(st);
			INode node = graph.getNode(id);
			node.getGraphicInfo().setLocation(parsePoint(st));
		} catch (Exception e) {
			throw new IOException("Bad token in stream while parsing dot node line");
		}
	}

	private static int parseID(StringTokenizer st) {
		String idstr = st.nextToken();
		idstr = idstr.substring(2);
		return Integer.parseInt(idstr);
	}

	private static void parseGraphDescription(BufferedReader in) throws IOException {
		String line = in.readLine();
		StringTokenizer st = new StringTokenizer(line," ");
		assert st.hasMoreElements() ; 
		assert "graph".equals(st.nextToken());
	}

}
