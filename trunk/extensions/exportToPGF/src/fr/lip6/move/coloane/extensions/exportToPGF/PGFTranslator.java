package fr.lip6.move.coloane.extensions.exportToPGF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.motor.formalism.Messages;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;

public class PGFTranslator {
	private static final String styles = "";
	/**
	 * Creer un tableau de string correspondant au contenu du fichier pgf, pour
	 * le model passer en parametres.
	 * @param model Model du fichier à exporter. 
	 * @return Le contenu du fichier pgf correspondant au model.
	 */
	public final Collection<String> translateModel(IModel model) {
		Collection<String> result = new Vector<String>();
		result.add("\\begin{tikzpicture}[" + styles + "]");
		// Recupere les noeuds du modèle...
		try {
			result.addAll(stringOfNodes(model.getListOfNodes()));
			// Recupere les arcs du modèle...
			result.addAll(stringOfArcs(model.getListOfArcs()));
		} catch (UnknownAttributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// contenu de la fin du fichier pgf
		result.add("\\end{tikzpicture}");
		return result;
	}
	
	private final static class UnknownAttributeException extends Exception{
		private static final long serialVersionUID = -4546847625740453603L;
		
	}
	
	private static String getAttribute(Collection<IAttribute> attributes, String name) throws UnknownAttributeException{
		for (IAttribute attribute : attributes){
			if (attribute.getName().equals(name)){
				return attribute.getValue();
			}
		}
		throw new UnknownAttributeException();
	}
	
	
	private final String stringOfTokens(INode node) throws UnknownAttributeException{
		String marking = getAttribute(node.getListOfAttr(), "marking");
		try {
			Integer.valueOf(marking);
			return "[tokens=" + marking + "]";
		} catch (NumberFormatException e) {
			return "[label=right:" + marking + "]";
		}
	}
	
	private final String stringOfNodeAttributes(INode node){
		String result = new String();
		for (IAttribute attribute : node.getListOfAttr()){
			if (attribute.getName().equals("name") || attribute.getName().equals("marking")) {
				;
			} else {
				result = result + "[label=below:" + attribute.getName() + ":" + attribute.getValue() + "]";
			}
		}
		return result;
	}
	
	/**
	 * Creer un tableau de string correspondant a la declaration des places
	 * qui sera contenu dans le fichier pgf.
	 * @param model Model où récuperer les places
	 * @return La partie correspondant a la declaration des places
	 * @throws UnknownAttributeException 
	 */
	private final Collection<String> stringOfNodes(Collection<INode> nodes) throws UnknownAttributeException {
		Collection<String> result = new Vector<String>();
		for (INode node : nodes) {
			result.add( "\\node[" + node.getNodeType() + "]"
					  + " (" + getAttribute(node.getListOfAttr(), "name") + ")"
					  + " at (" + node.getXPosition() + ", " + node.getYPosition() + ")"
					  + " [label=below:" + getAttribute(node.getListOfAttr(), "name") + "]"
					  + this.stringOfTokens(node)
					  + this.stringOfNodeAttributes(node)
					  + "{};"
					  );
		}
		return result;
	}
	
	private final String stringOfArcAttributes(IArc arc){
		String result = new String();
		for (IAttribute attribute : arc.getListOfAttr()){
			if (attribute.getName().equals("valuation")) {
				;
			} else {
				result = result + "[label=below:" + attribute.getName() + ":" + attribute.getValue() + "]";
			}
		}
		return result;
	}
		/**
	 * Creer un tableau de string correspondant a la declaration des arcs
	 * qui sera contenu dans le fichier pgf.
	 * @param model Model où récuperer les arcs
	 * @return La partie correspondant a la declaration des arcs
	 * @throws UnknownAttributeException 
	 */
	private final Collection<String> stringOfArcs(Collection<IArc> arcs) throws UnknownAttributeException {
		Collection<String> result = new Vector<String>();
		for (IArc arc : arcs){
			result.add( "\\draw[->]"
					  + " (" + getAttribute(arc.getStartingNode().getListOfAttr(), "name") + ")"
					  + " to node {" + getAttribute(arc.getListOfAttr(), "valuation") + "}"
					  + " (" + getAttribute(arc.getEndingNode().getListOfAttr(), "name") + ")"
					  + this.stringOfArcAttributes(arc)
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