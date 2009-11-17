package fr.lip6.move.coloane.extensions.exportToHelena;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Class to export IGraph model to an helena file.
 * 
 * @author Sami Evangelista
 */
public class ExportToImpl implements IExportTo {

	public void export(
			IGraph graph,
			String filePath,
			IProgressMonitor monitor) throws ColoaneException {
		
		int steps = graph.getNodes().size() + 2; 
		monitor.beginTask("Exporting net to helena file.", steps);

		/**
		 * export of the  preamble
		 */
		Date now = Calendar.getInstance().getTime();
		IAttribute title = graph.getAttribute("title");
		IAttribute version = graph.getAttribute("version");
		IAttribute authors = graph.getAttribute("authors");
		IAttribute date = graph.getAttribute("date");
		IAttribute note = graph.getAttribute("note");
		String output =	"";
		output += "/*****\n";
		output += " *\n";
		output += " *  This file has been exported from Coloane";
		output += " at date " + now.toString() + "\n";
		if(title != null)
			output += " *  Title  : " + title.getValue() + "\n";
		if(version != null)
			output += " *  Version: " + version.getValue() + "\n";
		if(authors != null)
			output += " *  Authors: " + authors.getValue() + "\n";
		if(date != null)
			output += " *  Date   : " + date.getValue() + "\n";
		if(note != null)
			output += " *  Note   : " + note.getValue() + "\n";
		output += " *\n";
		output += " *****/\n\n";
		output += "myModel {\n";
		
		/**
		 *  export of the declarative part
		 **/
		IAttribute declaration = graph.getAttribute("declaration");
		if(declaration != null)
			output += "\n//  declarations\n" + declaration.getValue() + "\n";
		monitor.worked(1);
		

		/**
		 *  export of nodes
		 **/
		String places = "";
		String transitions = "";
		for(INode n: graph.getNodes()) {
			String type = n.getNodeFormalism().getName();
			IAttribute name = n.getAttribute("name");

			/**
			 * a place
			 **/
			if("place".equalsIgnoreCase(type)) {
				IAttribute domain = n.getAttribute("domain");
				IAttribute marking = n.getAttribute("marking");
				places += "place " + name.getValue() + " {\n";
				places += "   dom: ";
				if(domain != null)
					places += domain.getValue() + ";\n";
				else
					places += "epsilon;\n";					
				if(marking != null && !"".equals(marking.getValue())) {
					String init = marking.getValue().replace('\n', ' ');
					places += "   init: " + init + ";\n";
				}
				places += "}\n";
			}
			
			/**
			 *  a transition
			 **/
			else if("transition".equalsIgnoreCase(type)) {
				transitions += "transition " + name.getValue() + " {\n";
				IAttribute guard = n.getAttribute("guard");
				String in = "";
				String out = "";
				//  connected places
				for(IArc a: graph.getArcs()) {
					INode target = a.getTarget();
					INode source = a.getSource();
					IAttribute valuation = a.getAttribute("valuation");
					String label = valuation.getValue().replace('\n', ' ');
					if(target == n) {
						in += "      ";
						in += source.getAttribute("name").getValue();
						in += ": " + label + ";\n";
					}
					if(source == n) {
						out += "      ";
						out += target.getAttribute("name").getValue();
						out += ": " + label + ";\n";		
					}
				}
				transitions += "   in  {\n" + in  +	"   }\n";
				transitions += "   out {\n" + out + "   }\n";
				if(guard != null && !guard.getValue().equals("")) {
					transitions += "   guard: " + guard.getValue() + ";\n";
				}
				transitions += "}\n";
			}

			/**
			 *  an unsupported node type
			 **/
			else {
			}
			monitor.worked(1);
		}
		if(!places.equals("")){
			output += "\n//  places\n" + places + "\n";
		}
		if(!transitions.equals("")){
			output += "\n//  transitions\n" + transitions + "\n";
		}
		output += "}\n";
		
		/**
		 *  create the output file and write everything
		 */
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			out.write(output);
			out.close();
			monitor.worked(1);
		} catch (IOException e) {
			System.out.println("IOException: could not create file " +
					filePath);
			e.printStackTrace();
		}
		monitor.done();
	}

}
