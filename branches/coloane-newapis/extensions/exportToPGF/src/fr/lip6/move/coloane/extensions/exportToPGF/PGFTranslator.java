package fr.lip6.move.coloane.extensions.exportToPGF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.motor.formalism.Messages;
import fr.lip6.move.coloane.interfaces.model.Arc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.Node;

/**
 * @author alban
 *
 */
public class PGFTranslator {
	// usepackage{tikz}
	// usetikzlibrary{arrows,petri}
	
	private static final String styles =
		  "[place/.style={circle,draw=black,thick,minimum size=.5cm},"
		+ "queue/.style={circle,draw=black,thick,fill=blue!50,minimum size=.5cm},"
		+ "transition/.style={rectangle,draw=black,thick,minimum size=.5cm},"
		+ "immediate transition/.style={rectangle,draw=black,thick,fill=black,minimum size=.5cm},"
		+ "arc/.style={-latex,draw=black},"
		+" inhibitor arc/.style={-o,draw=black}"
		+"]";
	/**
	 * Creer un tableau de string correspondant au contenu du fichier pgf, pour
	 * le model passer en parametres.
	 * @param model Model du fichier à exporter. 
	 * @return Le contenu du fichier pgf correspondant au model.
	 */
	public final Collection<String> translateModel(IModel model) {
		Collection<String> result = new Vector<String>();
		result.add("\\begin{tikzpicture}" + styles);
		result.addAll(stringOfModel(model));
		result.addAll(stringOfNodes(model.getListOfNodes()));
		result.addAll(stringOfArcs(model.getListOfArcs()));
		result.add("\\end{tikzpicture}");
		return result;
	}
	
	private final <T> String stringOfAttribute(T container, String name, String value){
		Class<? extends PGFTranslator> handlerClass = this.getClass();
		try {
			Method handlerMethod = handlerClass.getMethod("stringOf" + name.toUpperCase(), container.getClass(), String.class);
			if (value.length() == 0){
				Method defaultHandlerMethod = handlerClass.getMethod("stringOf" + name.toUpperCase(), container.getClass());
				return (String) defaultHandlerMethod.invoke(this, container);
			} else {
				return (String) handlerMethod.invoke(this, container, value);
			}
		} catch (NoSuchMethodException e) {
			System.err.println("PGFTranslator : no handler for attribute " + name + " of node : " + e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private final String stringOfAttributes(INode node){
		String result = new String();
		for (IAttribute attribute : node.getListOfAttr()){
			result = result + this.stringOfAttribute(node, attribute.getName(), attribute.getValue());
		}
		return result;
	}
	private final String stringOfAttributes(IArc arc){
		String result = new String();
		for (IAttribute attribute : arc.getListOfAttr()){
			result = result + this.stringOfAttribute(arc, attribute.getName(), attribute.getValue());
		}
		return result;
	}
	private final String stringOfAttributes(IModel model){
		String result = new String();
		for (IAttribute attribute : model.getListOfAttributes()){
			result = result + this.stringOfAttribute(model, attribute.getName(), attribute.getValue());
		}
		return result;
	}
	
	private final Collection<String> stringOfModel(IModel model){
		Collection<String> result = new Vector<String>();
		result.add(this.stringOfAttributes(model));
		return result;
	}
	
	@SuppressWarnings("unused")
	public final String stringOfNAME(Node node, String name){
		return " [label=below:" + name + "]";
	}
	
	@SuppressWarnings("unused")
	public final String stringOfMARKING(Node node, String marking){
		try {
			Integer.valueOf(marking);
			return " [tokens=" + marking + "]";
		} catch (NumberFormatException e) {
			return " [label=above:" + marking + "]";
		}
	}
	
	@SuppressWarnings("unused")
	public final String stringOfGUARD(Node node, String guard){
		return " [label=above:" + guard + "]";
	}
	

	private final double xScale =  0.05;
	
	private final double yScale = -0.05;
	
	/**
	 * Creer un tableau de string correspondant a la declaration des places
	 * qui sera contenu dans le fichier pgf.
	 * @param model Model où récuperer les places
	 * @return La partie correspondant a la declaration des places
	 * @throws UnknownAttributeException 
	 */
	private final Collection<String> stringOfNodes(Collection<INode> nodes) {
		Collection<String> result = new Vector<String>();
		for (INode node : nodes) {
			result.add( "\\node[" + node.getNodeType() + "]"
					  + " (" + node.getId() + ")"
					  + " at (" + node.getXPosition() * xScale + ", " + node.getYPosition() * yScale + ")"
					  + this.stringOfAttributes(node)
					  + " {};"
					  );
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	public final String stringOfVALUATION(Arc arc, String valuation){
		try {
			int value = Integer.valueOf(valuation).intValue();
			if (value == 1){
				return " --";
			} else {
				return " to node { " + valuation + " }";
			}
		} catch (NumberFormatException e) {
			return " to node { " + valuation + " }";
		}
	}
	
	@SuppressWarnings("unused")
	public final String stringOfVALUATION(Arc arc){
		return " -- ";
	}
	
	/**
	 * Creer un tableau de string correspondant a la declaration des arcs
	 * qui sera contenu dans le fichier pgf.
	 * @param model Model où récuperer les arcs
	 * @return La partie correspondant a la declaration des arcs
	 * @throws UnknownAttributeException 
	 */
	private final Collection<String> stringOfArcs(Collection<IArc> arcs) {
		Collection<String> result = new Vector<String>();
		for (IArc arc : arcs){
			result.add( "\\draw[" + arc.getArcType() + "]"
					  + " (" + arc.getStartingNode().getId() + ")"
					  + this.stringOfAttributes(arc)
					  + " (" + arc.getEndingNode().getId() + ")"
					  + ";"
					  );
		}
		return result;
	}
	
	
	/**
	 * Enregistre le model a exporter dans un fichier
	 * @param model Model a exporter/sauvgarder
	 * @param fileName Fichier où exporter/sauvgarder le model
	 * @throws ColoaneException Exception a propager 
	 */
	public final void saveModel(Collection<String> model, String fileName) throws ColoaneException {
		// Creation du fichier
		FileOutputStream wr;
		try {
			wr = new FileOutputStream(new File(fileName)); //$NON-NLS-1$
		} catch (FileNotFoundException e1) {
			throw new ColoaneException(Messages.FormalismManager_7 + fileName); //$NON-NLS-2$
		}
		BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(wr));

		// Ecriture du modele entier
		try {
			Collection<String> pgf = model;
			for (String line : pgf) {
				buff.write(line);
				buff.newLine();
			}
		} catch (Exception e) {
			throw new ColoaneException(Messages.FormalismManager_9);
		}

		try {
			buff.flush();
			wr.flush();
			buff.close();
			wr.close();
		} catch (IOException e) {
			throw new ColoaneException(Messages.FormalismManager_10);
		}
	}
}