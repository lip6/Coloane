package fr.lip6.move.coloane.extensions.importfromgml;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * A parser for models formatted as GML
 * @author Elodie Banel
 *
 */
public class GMLParser {
	
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	IGraph graph;
	ArrayList<IElement> last;
	Attribute currentGraphAtt;
	
	/**
	 * The constructor for a GML parser.
	 * @param graph The graph to be populated by the GML analysis
	 */
	public GMLParser(IGraph graph){
		this.graph = graph;
		last = new ArrayList<IElement>();
	}
	
	/**
	 * An XML parser that calls the functions parseOpen, parseClose and parseText on XML read from a buffer
	 * @param reader the text to analyse
	 * @throws IOException
	 */
	public void parseXML(BufferedReader reader) throws IOException{
		parseXML(reader,null);
	}
	
	private void parseXML(BufferedReader reader,String line) throws IOException{
		
		int start,end,i;
		boolean isStart,isEnd;
		
		//while there is text left in the current line, or if it is empty, lines left in the buffer, continue
		while ( (line != null) || ((line = reader.readLine()) != null)){
			
			//if the line is empty, ignore it
			if (line.length() == 0) {
				//the line must always be set to null to force reading of the next line
				line = null;
				continue;
			}
			
			//if the line does not start with a tag, analyse the text up until the next tag
			if (line.charAt(0) != '<') {
				start = line.indexOf("<");
				parseText(line.substring(0, start > 0 ? start : line.length()));
				//if there is a tag in the line, analyse it
				if (start > 0)  line = line.substring(start);
				else line = null;
				continue;
			}
			
			isStart = true;
			isEnd = false;
			start = 1;
			
			//if there is a slash, it is a closing tag
			if (line.charAt(start) == '/') {
				isEnd = true;
				isStart = false;
				start++;
			}
			
			//add lines until the whole tag is acquired
			while ((end = line.indexOf(">",start)) < 0) {
				line = line.concat(reader.readLine());
			}
			
			//if there is a slash before the close of the tag, it is an autoclosing tag
			if (line.charAt(end-1) == '/') {
				isEnd = true;
				end--;
			}
			
			//split the content of the tag according to spaces
			//the first is the tag name, the rest are attributes
			String[]tab = line.substring(start,end).split("[ ]+");
			ArrayList<Attribute> atts = new ArrayList<Attribute>();
			
			for (i=1;i<tab.length;i++){
				String s = tab[i];
				//split attributes into key and value
				//they may or may not have quotes around the value
				String[] val = s.split("[ ]*=[ ]*[\"']",2);
				if (val[1].charAt(val[1].length()-1) == '"' || val[1].charAt(val[1].length()-1) == '\'' ) val[1] = val[1].substring(0, val[1].length()-1);
				atts.add(new Attribute(val[0],val[1]));
			}
			
			//if the tag is autoclosing, the opening function must be called before the closing function
			if (isStart)
				try {
					parseOpen(tab[0], atts);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (isEnd) parseClose(tab[0]);
			//if the tag is autoclosing, there are two characters at the end: "/>" and not just one
			if (isStart && isEnd) end++;
			end++;
			
			//if there is text left in the line after the closing tag, analyse it too
			if (end < line.length()) line = line.substring(end);
			else line = null;
			
		}
	}
	
	/**
	 * Get the value associated with a given key in a list of attributes
	 * @param atts the list of attributes to search in
	 * @param key the key
	 * @return the value associated with the given key; or null if it does not exist
	 */
	public String getAtt(ArrayList<Attribute> atts, String key){
		for (Attribute s : atts){
			if (s.getKey().equals(key)){
				return s.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Parse an opening GML tag
	 * @param nom the name of the tag
	 * @param atts a list of the attribute names and values of the tag
	 * @throws NumberFormatException
	 * @throws ModelException
	 */
	public void parseOpen(String nom, ArrayList<Attribute> atts) throws NumberFormatException, ModelException{
		LOGGER.fine("Parsing open element: "+nom);
		
		//the current element will be registered in the "last" attribute so as to keep a list of the
		//current attribute and its parent elements
		IElement current = null;
		
		if (nom.equals("model")){
			
			current = graph;
			
		} else if (nom.equals("node")){
			
			String id;
			INodeFormalism nodeFormalism = (INodeFormalism) graph.getFormalism().getRootGraph().getElementFormalism(getAtt(atts,"nodeType"));
			if ((id = getAtt(atts,"id")) != null) current = graph.createNode(nodeFormalism, Integer.valueOf(id));
			else current = graph.createNode(nodeFormalism);
			
		} else if (nom.equals("arc")){
			
			INode nodeBegin = graph.getNode(Integer.valueOf(getAtt(atts,"source")));
			INode nodeEnd = graph.getNode(Integer.valueOf(getAtt(atts,"target")));
			if (nodeBegin == null || nodeEnd == null) {
				// TODO ERROR
			}
			IArcFormalism arcFormalism = (IArcFormalism) graph.getFormalism().getRootGraph().getElementFormalism(getAtt(atts,"arcType"));
			current = graph.createArc(arcFormalism, nodeBegin, nodeEnd, Integer.valueOf(getAtt(atts,"id")));
			
		} else if (nom.equals("attribute")){
			currentGraphAtt = new Attribute(getAtt(atts,"name"),"");
		}
		
		last.add(0, current);
		
	}
	
	/**
	 * Parse an ending GML tag
	 * @param nom the name of the tag being parsed
	 */
	public void parseClose(String nom){
		LOGGER.fine("Parsing close element: "+nom);
		
		//this element is closed: remove it from the chained list
		last.remove(0);
		
		//only attribute ending tags require an action
		if (nom.equals("attribute") && currentGraphAtt != null){
			//the element this attribute tag was contained in
			IElement current = last.get(0);
			IAttribute attribute = current.getAttribute(currentGraphAtt.getKey());
			if (attribute != null) {
				attribute.setValue(currentGraphAtt.getValue());
			}
			currentGraphAtt = null;
		}
		
		
	}
	
	/**
	 * Parse a line of text
	 * @param line the text to be parsed
	 */
	public void parseText(String line){
		LOGGER.fine("Parsing \""+line+"\"");
		
		//if there is a current attribute, the text is the value of this attribute
		//it can be parsed in multiple lines
		if (currentGraphAtt != null) {
			currentGraphAtt.setValue(currentGraphAtt.getValue().concat(line));
		}
		
	}
	
}
