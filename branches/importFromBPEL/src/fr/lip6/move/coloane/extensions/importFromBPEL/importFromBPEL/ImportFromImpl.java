package fr.lip6.move.coloane.extensions.importFromBPEL.importFromBPEL;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Attribute;
import org.eclipse.core.runtime.IProgressMonitor;


import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ImportFromImpl implements IImportFrom {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** This is for the BPEL file*/
	private Document doc;
	
	public IGraph importFrom(String filePath, String formalism, IProgressMonitor monitor) throws ColoaneException {
		System.err.println("test");
		// operations
		// The transformation methods can be added here.
		// By importing the BPEL files, the BPEL process will
		// transform into Petri Net.
		
//		  dom4jBPELReader dom4jBPELFile = new dom4jBPELReader();
		  
		  /**
		   * 测试文件列表： Test case list:
		   * catalog2.xml
		   * Travel.bpel
		   */
		  String BPELFilePath = "D:/WorkSpace/Cases/BPEL2PN/Travel.bpel";
		  File BPELfile = new File(BPELFilePath);
		  LOGGER.fine("The import BPEL XMLfiles is " + BPELFilePath);

		  
		    SAXReader xmlReader = new SAXReader();
		    
			try {
				doc = xmlReader.read(BPELfile);
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("BPELReader: Read XML file successful!");
		    	
		    
		  //		  try {
//			  Document docment = parseWithSAX(BPELfile);
////			  BPELProcessParser(docment);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		IGraph graph = new GraphModel(formalism);
		INode P_Start;
		try {
			P_Start = graph.createNode("place");
			INode P_End = graph.createNode("place");
			INode T_transition = graph.createNode("transition");
			graph.createArc("arc", P_Start, T_transition);
			graph.createArc("arc", T_transition, P_End);
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return graph;
	}
	

	  /**
	   * Loads a document from a XML file.
	   * Return Document type value.
	   *
	   * @throw a org.dom4j.DocumentException occurs whenever the buildprocess fails.
	   */
	  public Document parseWithSAX(File aFile) throws DocumentException {
	    SAXReader xmlReader = new SAXReader();
	    
	    if (aFile.exists()) {
	    	this.doc = xmlReader.read(aFile);// Read XML files
	    	System.out.println("BPELReader: Read XML file successful!");
	    	return doc;
	    }
	    else{
	    	return null;
	    }	    
	  } 
	  	  
	  
	  /**
	   * Loads a document from a ULR.
	   * Return Document type value.
	   *
	   * @param aURL the data source
	   * @throw a org.dom4j.DocumentExcepiton occurs on parsing failure.
	   */
	  public Document parseWithSAX(URL aURL) throws DocumentException {
	    SAXReader xmlReader = new SAXReader();
	    this.doc = xmlReader.read(aURL);
	    return doc;
	  }
	  
	  /**
	   * Parse the BPEL XML files
	   *
	   */
//	  public void BPELProcessParser(Document document) {
//		
//		  /**
//		 * Adopt recursion way to parser the BPEL Process
//		 * That is the content of element ‘sequence’ in BPEL files
//		 */
//	
//		/*Two ways to get node of "//catalog/sequence" through XPath.
//		  1. through METHOD selectNodes("//catalog/sequence"), which returns a node;
//		  2. through METHOD selectSingleNode("//catalog/sequence"), which returns a list;
//		  3. through METHOD doc.getRootElement().element(SelectElement); (The best way)
//		*/ 
//		  
//		 //the second method
//		/**
//		 *	List list = doc.selectNodes("//catalog/sequence");
//		 *	Iterator iter=list.iterator();
//		 *	Element element = (Element)iter.next();
//		 **/
//		  
//		  //the first method  partnerLinks
//		  //TAKE CARE "//process/sequence"
//		  String SelectElement = "sequence";
//		  try {			  
//			  	Element element = doc.getRootElement().element(SelectElement);
//				BPELProcessParser(element);
//		  } catch (Exception e) {
//        		e.printStackTrace();
//		  }
//	  }



}
