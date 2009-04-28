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
		    	

		    	IGraph graph = new GraphModel(formalism);
		    	IGraph tempGraph = new GraphModel(formalism);
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
					    			   System.out.println("Start is "+nodeName);
					    		   }
					    		   else if (nodeName.endsWith("End")){
					    			   P_End = node;
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
					    	   
//					    	   Iterator iterNode = graph.getNodes().iterator();
//					    	   System.out.println("@@@@@ begin to copy model!!!");
//					    	   while(iterNode.hasNext()){
//					    		   node = (INode) iterNode.next();
//					    		   tempAttr = node.getAttribute("name");
//					    		   nodeName = tempAttr.getValue();
//					    		   System.out.println("name is "+nodeName);
//					    		   if (nodeName.endsWith("Start")){
//					    			   INode T_temp = graph.createNode("transition");
//					    			   graph.createArc("arc", P_temp, T_temp);
//					    			   graph.createArc("arc", T_temp, node);
//					    		   }
//					    		   else if (nodeName.endsWith("End")){
//					    			   P_End = node;
//					    		   }
//					    		   else{
//					    			   // do nothing
//					    		   }
					    		   
//					    		   // Here, try to set up the connection between
//					    		   //graph and the tempGraph (copy the tempGrahp into graph)
////					    		   graph = MergeGraph(graph, tempGraph);
//					    		   Iterator iterNode = tempGraph.getNodes().iterator();
//					    		   System.out.println("@@@@@ begin to copy model!!!");
//					    		   while(iterNode.hasNext()){
//					    			   node = (INode) iterNode.next();
//					    			   graph.addNode(node);
//					    			   tempAttr = node.getAttribute("name");
//					    			   nodeName = tempAttr.getValue();
//					    			   if (nodeName.endsWith("Start")){
//					    				   INode T_temp = graph.createNode("transition");
//					    				   graph.createArc("arc", P_temp, T_temp);
//					    				   graph.createArc("arc", T_temp, node);
//					    			   }
//					    			   else if (nodeName.endsWith("End")){
//					    				   P_End = node;
//					    			   }
//					    			   else{
//					    				   // do nothing
//					    			   }
//					    			   				    			   
//					    		   }
//					    		   P_temp = P_End;
//					    		   Iterator iterArc = tempGraph.getArcs().iterator();
//					    		   while(iterArc.hasNext()){
//					    			   IArc arc = (IArc) iterArc.next();
//					    			   
//					    			   // There is something wrong with the following method
//					    			   // addArc() will use the original ID of Arc.
//					    			   // So the conflict happens.
//					    			   graph.addArc(arc);
////					    			   IArc tempArc = graph.createArc("arc", arc.getSource(), arc.getTarget());
////					    			   tempAttr = tempArc.getAttribute("name");
////					    			   tempAttr.setValue(arc.getAttribute("name").getValue());
//					    		   }
					    		   
//					    	   }
//					    	   P_temp = P_End;
					    	   
					       }
					       else{
				    	   
					    	   return null;
					       }
					       i++;
					}
			    	
					attribute = P_Start.getAttribute("name");
					attribute.setValue("P_"+level+"_"+element.getName()+"_Start");
			    	
					P_End = graph.createNode("place");
					attribute = P_End.getAttribute("name");
					attribute.setValue("P_"+level+"_"+element.getName()+"_End");
					
 				   	INode T_temp = graph.createNode("transition");
 				   	graph.createArc("arc", P_temp, T_temp);
 				   	graph.createArc("arc", T_temp, P_End);
			    	
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	return graph;
		    	
		    }
	    
	    
//	    	   String elemName = elem.getName().toString();
//	    	   if(elemName == "Receive"){
//	    		   
//	    	   }else if(elemName == "Reply"){
//	    		   
//	    	   }else if(elemName == "Invoke"){
//	    		   
//	    	   }else if(elemName == "Wait"){
//	    		   
//	    	   }else if(elemName == "Exit"){
//	    		   
//	    	   }else if(elemName == "Throw"){
//	    		   
//	    	   }else if(elemName == "ReThrow"){
//	    		   
//	    	   } else{
//	    	   			    		   
//	    	   }
//	    		 
	    
	    
	    
	    
	    
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
		   * Generate the IGraph Petri model of 'Reply' activity
		   * according to the transformation rule of 'Reply' activity
		   * 
		   * @param graph the IGraph submodel 
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
