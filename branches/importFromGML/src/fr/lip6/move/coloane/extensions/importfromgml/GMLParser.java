package fr.lip6.move.coloane.extensions.importfromgml;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class GMLParser {

	IGraph graph;
	ArrayList<IElement> last;
	Attribute currentGraphAtt;
	
	public GMLParser(IGraph graph){
		this.graph = graph;
		last = new ArrayList<IElement>();
	}
	
	//analyseur syntaxique xml
	public void parseLine(BufferedReader reader,String line) throws IOException{
		int start,end,i;
		boolean isStart,isEnd;
		
		while ( (line != null) || ((line = reader.readLine()) != null)){
			if (line.length() == 0) {
				line = null;
				continue;
			}
			
			if (line.charAt(0) != '<') {
				start = line.indexOf("<");
				parseText(line.substring(0, start > 0 ? start : line.length()));
				if (start > 0)  line = line.substring(start);
				else line = null;
				continue;
			}
			isStart = true;
			isEnd = false;
			start = 1;
			
			//si y'a une ouverture:
				//balise de fin
				//balise normale
			if (line.charAt(start) == '/') {
				isEnd = true;
				isStart = false;
				start++;
			}
			
			while ((end = line.indexOf(">",start)) < 0) {
				line = line.concat(reader.readLine());
			}
			
			if (line.charAt(end-1) == '/') {
				isEnd = true;
				end--;
			}
			
			String[]tab = line.substring(start,end).split("[ ]+");
			ArrayList<Attribute> atts = new ArrayList<Attribute>();
			for (i=1;i<tab.length;i++){
				String s = tab[i];
				String[] val = s.split("[ ]*=[ ]*[\"']",2);
				if (val[1].charAt(val[1].length()-1) == '"' || val[1].charAt(val[1].length()-1) == '\'' ) val[1] = val[1].substring(0, val[1].length()-1);
				atts.add(new Attribute(val[0],val[1]));
			}
			
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
			if (isEnd) parseClose(tab[0], atts);
			if (isStart && isEnd) end++;
			end++;
			
			if (end < line.length()) line = line.substring(end);
			else line = null;
			
		}
	}
	
	public String getAtt(ArrayList<Attribute> atts, String type){
		for (Attribute s : atts){
			if (s.getType().equals(type)){
				return s.getValue();
			}
		}
		return null;
	}
	
	public void parseOpen(String nom, ArrayList<Attribute> atts) throws NumberFormatException, ModelException{
		System.out.print("start: "+nom);
		for (Attribute s : atts){
			System.out.print(s.getType()+"  ");
		}
		System.out.println();
		
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
		
		// TODO les attributs pour des attributs... c'est pas encore fait.
		last.add(0, current);
		
	}
	
	public void parseClose(String nom, ArrayList<Attribute> atts){
		System.out.print("end: ");
		for (Attribute s : atts){
			System.out.print(s+"  ");
		}
		System.out.println();
		
		last.remove(0);
		
		if (nom.equals("attribute")){
			IElement current = last.get(0);
			IAttribute attribute = current.getAttribute(currentGraphAtt.getType());
			if (attribute != null) {
				attribute.setValue(currentGraphAtt.getValue());
			}
			currentGraphAtt = null;
		}
		
		
	}
	
	public void parseText(String line){
		System.out.println(line);
		
		if (currentGraphAtt != null) {
			currentGraphAtt.setValue(currentGraphAtt.getValue().concat(line));
		}
		
	}
	
}
