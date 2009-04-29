package fr.lip6.move.coloane.extensions.importFromBPEL.importFromBPEL;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Attribute;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;


import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.model.AttributeModel;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

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
		
		
		/**
		 * 测试文件列表： Test case list:
		 * catalog2.xml
		 * Travel.bpel
		 */
		File BPELfile = new File(filePath);
		LOGGER.fine("The import BPEL XMLfiles is " + filePath);
		IGraph graph = new GraphModel(formalism);
		  

		try {
			Document docment = parseWithSAX(BPELfile);
			BPELProcessParser(docment);
		  
			// The basic utility of Class IGraph and other related class

			graph = BPELPNModelGenerator(docment,formalism);
		  
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		IAttribute attribute = graph.getAttribute("authors");
//		attribute.setValue("ZHU Jun");
//		attribute = graph.getAttribute("title");
//		attribute.setValue("Petri Net model of BPEL Process");
		

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
	    	LOGGER.fine("BPELReader: Read XML file successful!");
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
	   * Parse the BPEL XML files (recursion)
	   * @param document the xml document source
	   *
	   */
	  public void BPELProcessParser(Document document) {
		
		  /**
		 * Adopt recursion way to parser the BPEL Process
		 * That is the content of element ‘sequence’ in BPEL files
		 */
	
		/*Two ways to get node of "//catalog/sequence" through XPath.
		  1. through METHOD selectNodes("//catalog/sequence"), which returns a node;
		  2. through METHOD selectSingleNode("//catalog/sequence"), which returns a list;
		  3. through METHOD doc.getRootElement().element(SelectElement); (The best way)
		*/ 
		  
		 //the second method
		/**
		 *	List list = doc.selectNodes("//catalog/sequence");
		 *	Iterator iter=list.iterator();
		 *	Element element = (Element)iter.next();
		 **/
		  
		  //the first method  partnerLinks
		  //TAKE CARE "//process/sequence"
		  String SelectElement = "sequence";
		  try {			  
			  	Element element = doc.getRootElement().element(SelectElement);
				BPELProcessParser(element);
		  } catch (Exception e) {
        		e.printStackTrace();
		  }
	  }

	  
	  /**
	   * Parse the BPEL XML files (recursion)
	   * 
	   * @param element the xml document source
	   */
	    public void BPELProcessParser(Element element) {
	    	/**
	    	 * Recursion
	    	 */
//	    	System.out.println("BPELProcessParser: " + element.getName());
	    	Iterator iter = element.elementIterator();
	    	while(iter.hasNext()){
			       Element elem=(Element)iter.next();
			       if(elem instanceof Element){
			    	   System.out.println(elem.getName());
			    	   //do something
			    	   BPELProcessParser(elem);
			       }
			       else{
		    	   
			    	   return;
			       }
			}
	    	
	    }
	    
	    
	    
	    
		  /**
		   * Generate the Petri net model (recursion)
		   * @param document the xml document source
		   *
		   */
		  public IGraph BPELPNModelGenerator(Document document, String formalism) {
			
			  /**
			 * Adopt recursion way to parser the BPEL Process
			 * That is the content of element ‘sequence’ in BPEL files
			 */
		
			/*Two ways to get node of "//catalog/sequence" through XPath.
			  1. through METHOD selectNodes("//catalog/sequence"), which returns a node;
			  2. through METHOD selectSingleNode("//catalog/sequence"), which returns a list;
			  3. through METHOD doc.getRootElement().element(SelectElement); (The best way)
			*/ 
			  
			 //the second method
			/**
			 *	List list = doc.selectNodes("//catalog/sequence");
			 *	Iterator iter=list.iterator();
			 *	Element element = (Element)iter.next();
			 **/
			  
			  IGraph graph = new GraphModel(formalism);
			  
			  //the first method  partnerLinks
			  //TAKE CARE "//process/sequence"
			  String SelectElement = "sequence";
			  try {			  
				  	Element element = doc.getRootElement().element(SelectElement);
				  	graph = BPELPNModelGenerator(element, formalism, 0);

			  } catch (Exception e) {
	        		e.printStackTrace();
			  }
			  return graph;
		  }

		  
		  /**
		   * Generate the Petri net model (recursion)
		   * 
		   * @param element the xml document source
		   */
		    public IGraph BPELPNModelGenerator(Element element, String formalism, int level) {
		    	/**
		    	 * Recursion
		    	 */

		    	// Arrange the location of every places and transitions.
		    	int x = 150;
		    	int y = 150;
		    	Point point = new Point(x,y);
		    	
		    	IGraph graph = new GraphModel(formalism);
		    	IGraph tempGraph = new GraphModel(formalism);
		    	IGraph tempGraphA = new GraphModel(formalism);
		    	Iterator iter = element.elementIterator();
		    	String tempString, nodeName;
		    	IAttribute attribute,tempAttr;
		    	int i = 0;
		    	INode P_Start, P_End, nodeTemp, node, P_temp; 
		    	
		    	try {
					P_Start = graph.createNode("place");

					P_temp = P_Start;
					P_End = P_Start;
					nodeTemp = P_Start;
					
					
			    	while(iter.hasNext()){
					       Element elem=(Element)iter.next();
					       if(elem instanceof Element){
					    	   System.out.println(elem.getName());
					    	   //do something 
					    	   tempString = elem.getName();
					    	   if ((tempString.equalsIgnoreCase("receive"))){
					    		   tempGraph = GenerateReceive(formalism, level+1);
					    		   
					    	   }
					    	   else if((tempString.equalsIgnoreCase("invoke"))){
					    		   if(elem.attribute("outputVariable") == null){
					    			   // One-way invoke
					    			   System.out.println("One-Way invoke");
					    			   tempGraph = GenerateInvokeOneway(formalism, level+1);
					    		   }
					    		   else{
					    			   // Request-Response invoke
					    			   System.out.println("Request-Response invoke");
					    			   tempGraph = GenerateInvokeReqrep(formalism, level+1);
					    		   }
					    	   }
					    	   else if((tempString.equalsIgnoreCase("reply"))){
					    		   tempGraph = GenerateReply(formalism, level+1);
					    	   }
					    	   else if((tempString.equalsIgnoreCase("assign"))){
					    		   tempGraph = GenerateAssign(formalism, level+1);
					    	   }
					    	   else if((tempString.equalsIgnoreCase("sequence"))){
					    		   tempGraph = GenerateAssign(formalism, level+1);
					    	   }
//					    	   else if((tempString.equalsIgnoreCase("if"))){
//					    		   tempGraph = GenerateAssign(formalism, level+1);
//					    	   }
					    	   else if((tempString.equalsIgnoreCase("flow"))){
					    		   tempGraph = GenerateFlow(elem,formalism, level+1);
					    	   }
					    	   else if((tempString.equalsIgnoreCase("pick"))){
					    		   tempGraph = GeneratePick(elem, formalism, level+1);
					    	   }
//					    	   else if((tempString.equalsIgnoreCase("while"))){
//					    		   tempGraph = GenerateAssign(formalism, level+1);
//					    	   }
					    	   else
					    	   {
					    		   tempGraph = BPELPNModelGenerator(elem, formalism, level+1);
					    		   
					    		   //for test
					    	   }
					    	   
					    	   // *******************************************
					    	   // The following codes is for merging two graphs.
					    	   // And create the connections between two graphs.
					    	   graph.addGraph(tempGraph);
					    	   
					    	   Iterator iterNode = graph.getNodes().iterator();
					    	   System.out.println("@@@@@ begin to show model of Graph!!!");
					    	   while(iterNode.hasNext()){
					    		   node = (INode) iterNode.next();
					    		   tempAttr = node.getAttribute("name");
					    		   nodeName = tempAttr.getValue();
					    		   System.out.println("name is "+nodeName);
					    		   if (nodeName.endsWith("Start")){
					    			   nodeTemp = node;
					    			   tempAttr.setValue(nodeName+"_0");
					    			   System.out.println("Start is "+nodeName);
					    		   }
					    		   else if (nodeName.endsWith("End")){
					    			   P_End = node;
					    			   tempAttr.setValue(nodeName+"_0");
					    			   System.out.println("End is "+nodeName);
					    		   }
					    		   else{
					    			   // do nothing
					    		   }
					    	   }
			    			   INode T_temp = graph.createNode("transition");
			    			   graph.createArc("arc", P_temp, T_temp);
			    			   graph.createArc("arc", T_temp, nodeTemp);
			    			   P_temp = P_End;
			    			   // *******************************************
					    	   
					       }
					       else{
				    	   
					    	   return null;
					       }
					       i++;
					}
			    	
			    	// Set the name of the first place in this element
					attribute = P_Start.getAttribute("name");
					attribute.setValue("P_"+level+"_"+element.getName()+"_Start");
					// Manage the localization of the place 
			    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
			    	nodeGraphInfo.setLocation(point);
			    	
			    	// Create the last node (place)
					P_End = graph.createNode("place");
					attribute = P_End.getAttribute("name");
					attribute.setValue("P_"+level+"_"+element.getName()+"_End");
			    	nodeGraphInfo = P_End.getGraphicInfo();
			    	point.setLocation(x, y+300);
			    	nodeGraphInfo.setLocation(point);
					
					
 				   	INode T_temp = graph.createNode("transition");
 				   	System.out.println("P_temp is "+P_temp.getAttribute("name").getValue());
 				   	System.out.println("P_End is "+P_End.getAttribute("name").getValue());
 				   	graph.createArc("arc", P_temp, T_temp);
 				   	graph.createArc("arc", T_temp, P_End);
			    	
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	return graph;
		    	
		    }
	    
	    
	    
		  /**
		   * Generate the IGraph Petri model of 'Receive' activity
		   * according to the transformation rule of 'Receive' activity
		   * 
		   * @param graph the IGraph submodel 
		   */
	    public IGraph GenerateReceive(String formalism, int level){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 200;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
			try {
				// The first input place of 'Receive' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Reveive_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Reveive_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+level+"Receive");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Receive_In");
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Receive_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Receive_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", P_Msg_Rec, T_transition);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Receive_MSG");
		    	
		    	
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
	    	return graph;
	    }

	    
		  /**
		   * Generate the IGraph Petri model of 'Invoke' activity (One-Way)
		   * according to the transformation rule of 'Invoke' activity
		   * 
		   * @param graph the IGraph submodel 
		   */
	    public IGraph GenerateInvokeOneway(String formalism, int level){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 300;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
			try {
				// The first input place of 'Receive' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeOneWay_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeOneWay_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+level+"_InvokeOneWay");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeOneWay_In");
		    	
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeOneWay_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeOneWay_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", T_transition, P_Msg_Rec);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeOneWay_MSG");
		    	
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	
	    	return graph;
	    }

	    
		  /**
		   * Generate the IGraph Petri model of 'Invoke' activity (Request-Response)
		   * according to the transformation rule of 'Invoke' activity
		   * 
		   * @param graph the IGraph submodel 
		   */
	    public IGraph GenerateInvokeReqrep(String formalism, int level){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 500;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
			try {
				// The first input place of 'Invoke' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeReqRep_Start");			    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The transition between place P_Start and P_Middle
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+level+"_InvokeReqRep_Req");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between P_Start and transition(req)
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeReqRep_In");
		    	
		    	//Node Msg Req
		    	INode P_Msg_Req = graph.createNode("place");
		    	attribute = P_Msg_Req.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeReqRep_MSG_Req");
		    	nodeGraphInfo = P_Msg_Req.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between transition(req) and MSG(req)
		    	arc = graph.createArc("arc", T_transition, P_Msg_Req);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeReqRep_MSG_Req");
		    	
		    	// Node middle
		    	INode P_Middle = graph.createNode("place");
		    	attribute = P_Middle.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeReqRep_Mid");
		    	nodeGraphInfo = P_Middle.getGraphicInfo();
		    	point.setLocation(x+50, y+75);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between transition(req) and place P_Middle
		    	arc = graph.createArc("arc", T_transition, P_Middle);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeReqRep_P_Mid");
		    	
		    	// Transition (Response) between P_Middle and P_End
		    	T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+level+"_InvokeReqRep_Res");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between P_Middle and transition (Response)
		    	arc = graph.createArc("arc", P_Middle, T_transition);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeReqRep_P_Mid");
		    	
		    	// Msg (Response)
		    	INode P_Msg_Res = graph.createNode("place");
		    	attribute = P_Msg_Res.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeReqRep_MSG_Res");
		    	nodeGraphInfo = P_Msg_Res.getGraphicInfo();
		    	point.setLocation(x+100, y+100);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between transition (Response) and Msg (Response)
		    	arc = graph.createArc("arc", P_Msg_Res, T_transition);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeReqRep_MSG_Res");
		    	
		    	// The last output place of 'Invoke' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+level+"_InvokeReqRep_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+150);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between transition (Response) and last place
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_InvokeReqRep_Out");
		    	
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	    	return graph;
	    }
	    
	    
		  /**
		   * Generate the IGraph Petri model of 'Reply' activity
		   * according to the transformation rule of 'Reply' activity
		   * 
		   * @param graph the IGraph submodel 
		   */
	    public IGraph GenerateReply(String formalism, int level){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 200;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
			try {
				// The first input place of 'Reply' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Reply_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Reply_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+level+"_Reply");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Reply_In");
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Reply_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Reply_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", T_transition, P_Msg_Rec);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Reply_MSG");
		    	
		    	
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	
	    	return graph;
	    }
	    
	    
		  /**
		   * Generate the IGraph Petri model of 'Assign' activity
		   * according to the transformation rule of 'Assign' activity
		   * 
		   * @param formalism the formalism of the Graph 
		   * @param  level show the level of node (activity) 
		   * @return IGraph
		   */
	    public IGraph GenerateAssign(String formalism, int level){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 500;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
			try {
				// The first input place of 'Reply' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Assign_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+level+"_Assign_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+level+"_Assign");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between P_Start and T_transition
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Assign_In");
		    	
		    	// Arc between T_transition and P_End
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+level+"_Assign_Out");
		    	
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	    	return graph;
	    }
	    
		  /**
		   * Generate the IGraph Petri model of structured activity 'Flow' 
		   * according to the transformation rule of 'Flow' activity
		   * 
		   * @param elem element in xml document
		   * @param formalism the formalism of the Graph 
		   * @param  level show the level of node (activity) 
		   * @return IGraph
		   */
	    public IGraph GenerateFlow(Element elem,String formalism, int level){

	    	IGraph tempGraph = new GraphModel(formalism);
	    	IGraph tempGraphA = new GraphModel(formalism);
 		   
 		   	INode nodeStartFlow, nodeEndFlow;
 		   	try {
 		   			// The first input place of 'flow' sub model
					INode P_Start_Flow = tempGraph.createNode("place");
	
			    	
		    		// The following transition after the first input place
			    	// of 'flow' sub model
					INode T_Start_Flow = tempGraph.createNode("transition");
					// Set the name of place 'P_Start'
			    	IAttribute attribute = T_Start_Flow.getAttribute("name");
			    	attribute.setValue("T_"+level+"_Flow_In");
			    	
			    	// Add arc between P_Start and T_Start
			    	IArc arc = tempGraph.createArc("arc", P_Start_Flow, T_Start_Flow);		    	
			    	attribute = arc.getAttribute("valuation");
			    	attribute.setValue("Arc_"+level+"_Flow_In");
			    	
			    	
		    		// The last place of 'flow' sub model
					INode P_End_Flow = tempGraph.createNode("place");
	
			    	
			    	// The last transition of 'flow' sub model
					INode T_End_Flow = tempGraph.createNode("transition");
					// Set the name of place 'P_Start'
			    	attribute = T_End_Flow.getAttribute("name");
			    	attribute.setValue("T_"+level+"_Flow_Out");
			    	
			    	// Add arc between P_Start and T_Start
			    	arc = tempGraph.createArc("arc", T_End_Flow, P_End_Flow);		    	
			    	attribute = arc.getAttribute("valuation");
			    	attribute.setValue("Arc_"+level+"_Flow_Out");
			    	
			    	// Initialization
			    	nodeStartFlow = P_Start_Flow;
			    	nodeEndFlow = P_End_Flow;
			    	
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
	 			   Element elemTemp =(Element)iterTemp.next();
	 			   tempGraphA = BPELPNModelGenerator(elemTemp, formalism, level+1);
	 			   tempGraph.addGraph(tempGraphA);
	 			   
	 			   // Add the connection between the sub model and parent model.
			    	   Iterator iterNodeFlow = tempGraph.getNodes().iterator();
	//		    	   System.out.println("@@@@@ begin to show model of Graph!!!");
			    	   while(iterNodeFlow.hasNext()){
			    		   INode nodeFlow = (INode) iterNodeFlow.next();
			    		   IAttribute tempAttr = nodeFlow.getAttribute("name");
			    		   String nodeName = tempAttr.getValue();
			    		   System.out.println("name is "+nodeName);
			    		   if (nodeName.endsWith("Start")){
			    			   nodeStartFlow = nodeFlow;
			    			   tempAttr.setValue(nodeName+"_0");
			    			   System.out.println("Start is "+nodeName);
			    		   }
			    		   else if (nodeName.endsWith("End")){
			    			   nodeEndFlow = nodeFlow;
			    			   tempAttr.setValue(nodeName+"_0");
			    			   System.out.println("End is "+nodeName);
			    		   }
			    		   else{
			    			   // do nothing
			    		   }
			    	   }

		    		   tempGraph.createArc("arc", T_Start_Flow, nodeStartFlow);
			    	   tempGraph.createArc("arc", nodeEndFlow, T_End_Flow);
				    	}
	
				    	// ***********************************************
				    	// In order to implement the conncection of submodels
				    	// by identifying the name of places or transitions.
				    	// Check whether they end with "start" or "end".
				    	// ************************************************
						// Set the name of place 'P_Start'
				    	attribute = P_Start_Flow.getAttribute("name");
				    	attribute.setValue("P_"+level+"_Flow_Start");
				    	
						// Set the name of place 'P_Start'
				    	attribute = P_End_Flow.getAttribute("name");
				    	attribute.setValue("P_"+level+"_Flow_End");
		    	   } catch (ModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		    	   }

		    	   return tempGraph;
		    	}
	    
	    
		  /**
		   * Generate the IGraph Petri model of structured activity 'Pick' 
		   * according to the transformation rule of 'Pick' activity
		   * There are Branch A and Branch B.
		   * 
		   * @param elem element in xml document
		   * @param formalism the formalism of the Graph 
		   * @param  level show the level of node (activity) 
		   * @return IGraph
		   */
	    public IGraph GeneratePick(Element elem, String formalism, int level){

	    	IGraph tempGraph = new GraphModel(formalism);
	    	IGraph tempGraphA = new GraphModel(formalism);
		   
		   	INode nodeStartPick[] = new INode[2];
		   	INode nodeEndPick[] = new INode[2];
		   	int countBranch = 0;
		   	
		   	IAttribute attribute;
		   	try {
		   			// The first input place of 'flow' sub model
					INode P_Start_Pick = tempGraph.createNode("place");
			    	
		    		// The last place of 'flow' sub model
					INode P_End_Pick = tempGraph.createNode("place");
			    	
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
				    	Element elemTemp =(Element)iterTemp.next();
				    	tempGraphA = BPELPNModelGenerator(elemTemp, formalism, level+1);
				    	tempGraph.addGraph(tempGraphA);
		 			   
		 			   // Add the connection between the sub model and parent model.
				    	Iterator iterNodePick = tempGraph.getNodes().iterator();
				    	while(iterNodePick.hasNext()){
				    		   INode nodePick = (INode) iterNodePick.next();
				    		   IAttribute tempAttr = nodePick.getAttribute("name");
				    		   String nodeName = tempAttr.getValue();
				    		   
				    		   LOGGER.fine("nodeName is " + nodeName); 
				    		   
				    		   if (nodeName.endsWith("Start")){
				    			   nodeStartPick[countBranch] = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
				    			   System.out.println("Start is "+nodeName);
				    		   }
				    		   else if (nodeName.endsWith("End")){
				    			   nodeEndPick[countBranch] = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
				    			   System.out.println("End is "+nodeName);
				    		   }
				    		   else{
				    			   // do nothing
				    		   }

				    	}
				    	countBranch++;
			    	}
			    	
			    	// ***********************************************
			    	// In order to implement the conncection of submodels
			    	// by identifying the name of places or transitions.
			    	// Check whether they end with "start" or "end".
			    	// ************************************************
			    	// Set the name of place 'P_Start_Pick'
			    	attribute = P_Start_Pick.getAttribute("name");
			    	attribute.setValue("P_"+level+"_Pick_Start");
			    	
			    	// Set the name of place 'P_End_Pick'
			    	attribute = P_End_Pick.getAttribute("name");
			    	attribute.setValue("P_"+level+"_Pick_End");
			    	
			    	// Connect Pick branch A with Pick self (up)
			    	INode T_Pick_A_Up = tempGraph.createNode("transition");
			    	tempGraph.createArc("arc", P_Start_Pick, T_Pick_A_Up);
			    	tempGraph.createArc("arc", T_Pick_A_Up, nodeStartPick[0]);
			    	
			    	// Connect Pick branch A with Pick self (down)
			    	INode T_Pick_A_Down = tempGraph.createNode("transition");
			    	tempGraph.createArc("arc", nodeEndPick[0], T_Pick_A_Down);
			    	tempGraph.createArc("arc", T_Pick_A_Down, P_End_Pick);
			    	
			    	// Connect Pick branch B with Pick self (up)
			    	INode T_Pick_B_Up = tempGraph.createNode("transition");
			    	tempGraph.createArc("arc", P_Start_Pick, T_Pick_B_Up);
			    	tempGraph.createArc("arc", T_Pick_B_Up, nodeStartPick[1]);
			    	
			    	// Connect Pick branch B with Pick self (down)
			    	INode T_Pick_B_Down = tempGraph.createNode("transition");
			    	tempGraph.createArc("arc", nodeEndPick[1], T_Pick_B_Down);
			    	tempGraph.createArc("arc", T_Pick_B_Down, P_End_Pick);
			    	
			    	// Create the inhibitor arc in Branch A
			    	// ********************************************
			    	// * For BPEL Process, it is not necessary to use inhibitor ARC.
			    	// * It depends on the token
			    	// * If needed, just delete the "//" of the following 2 liens.
			    	// ********************************************
//			    	IArc T_Pick_A_Inhib = tempGraph.createArc("inhibitor", nodeStartPick[0], T_Pick_B_Up);
//			    	T_Pick_A_Inhib.getAttribute("valuation").setValue("Inhib_" + level + "_Pick_Branch_A");
			    	
			    	// Create the inhibitor arc in Branch A
			    	// ********************************************
			    	// * For BPEL Process, it is not necessary to use inhibitor ARC.
			    	// * It depends on the token
			    	// * If needed, just delete the "//" of the following 2 liens.
			    	// ********************************************
//			    	IArc T_Pick_B_Inhib = tempGraph.createArc("inhibitor", nodeStartPick[1], T_Pick_A_Up);
//			    	T_Pick_B_Inhib.getAttribute("valuation").setValue("Inhib_" + level + "_Pick_Branch_B");
			    	
		    	   	}catch (ModelException e) {
		    	   	// TODO Auto-generated catch block
		    	   		e.printStackTrace();
		    	   }

		    	return tempGraph;
		    }
	    
	    
//		  public IGraph MergeGraph(IGraph graphA, IGraph graphB) {
//   		   INode P_temp, P_End, T_temp;
//   		   
//
//				   try {
//						  Iterator iterNode = graphB.getNodes().iterator();
//						   while(iterNode.hasNext()){
//							   INode node = (INode) iterNode.next();
//							   graphA.addNode(node);
//							   IAttribute tempAttr = node.getAttribute("name");
//							   String nodeName = tempAttr.getValue();
//							   if (nodeName.endsWith("Start")){					   
//					   
//					   
//					T_temp = graphA.createNode("transition");
//					   graphA.createArc("arc", P_temp, T_temp);
//					   graphA.createArc("arc", T_temp, node);
//				   }
//				   else if (nodeName.endsWith("End")){
//					   P_End = node;
//				   }
//				   else{
//					   // do nothing
//				   }
//				   				    			   
//			   }
//			   P_temp = P_End;
//			   Iterator iterArc = graphB.getArcs().iterator();
//			   while(iterNode.hasNext()){
//				   IArc arc = (IArc) iterNode.next();
//				   graphA.addArc(arc);
//			   }
//				} catch (ModelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				
//				return graphA;
//				
//			  
//		  }
}
