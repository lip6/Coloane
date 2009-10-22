package fr.lip6.move.coloane.extensions.importFromBPEL.importFromBPEL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;


import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

// Bug list
// Bug 1：Can not process several parallel non-sequence activities.
// For example: <Flow> <Invoke> <Reply> <Receive> </Flow>			
// Discovery Time: 09/06/2009
// Status: Solved
// Last Update Time: 15/06/2009

// Bug 2: The transformation of "PICK" activity is not finished.
// Discovery Time: 15/06/2009
// Status: Solved
// Last Update Time:  18/06/2009

//********************************************************

//Bug 3: The transformation of "WHILE" activity is not finished.
//Discovery Time: 15/06/2009
//Status: NOT Solved
//Last Update Time: 

//Bug 4: The transformation of "REPEAT UNTIL" activity is not finished.
//Discovery Time: 15/06/2009
//Status: NOT Solved
//Last Update Time: 

//Bug 5: The transformation of "EMPTY" activity is not finished.
//Discovery Time: 15/06/2009
//Status: NOT Solved
//Last Update Time: 

//Bug 6: The transformation of "WAIT" activity is not finished.
//Discovery Time: 15/06/2009
//Status: NOT Solved
//Last Update Time: 

//Bug 7: The transformation of "THROW" activity is not finished.
//Discovery Time: 15/06/2009
//Status: NOT Solved
//Last Update Time: 

/**
 * Import the BPEL process XML files into coloane
 * During the import, the BPEL process will be automatically
 * transformed into Petri Net models, which can be displayed 
 * in coloane. And the model can also be used to do any verification,
 * which are provided by coloane.
 * 
 * author: Jun ZHU (LIP6,UPMC, France & School of Computer Science, NUDT, China)
 * e-mail: mail.zhujun@gmail.com
 * create date: 26-04-2009
 */
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
		  
			// The basic utility of Class IGraph and other related class
			graph = BPELPNModelGenerator(docment,formalism);
			GenerateIncidenceMatrix(graph);
			
			// Graph Reduction in level 1 
//			GraphReductionLevel1(graph);
			
			// Graph Reduction in level 2 
			// Level 2 is much better than level 1
			// make the model much smaller.
			
			GraphReductionLevel2(graph);
			
			
			// Test the monitor generation
			PrintGraphNodeID(graph);
//			GenerateMonitor(graph);
			GenerateMonitorMSGCentred(graph);
		  
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
	 * The method is used to print the IDs of all the place
	 * 
	 * @param graph
	 */
	public void PrintGraphNodeID(IGraph graph){
		
		Iterator<INode> iterNode = graph.getNodes().iterator();
		while(iterNode.hasNext()){
			INode node = (INode) iterNode.next();
			if(node.getNodeFormalism().getName().equalsIgnoreCase("place")){
				LOGGER.fine(node.getAttribute("name").getValue() + " ID is" + node.getId());
			}
		}

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
			  int Level_Max = 100;
			  int [] numArray = new int[Level_Max];
			  
			  for(int i=0; i<Level_Max; i++){
				  numArray[i] = 0;
			  }
			  
			  //the first method  partnerLinks
			  //TAKE CARE "//process/sequence"
			  String SelectElement = "sequence";
			  try {			  
				  	Element element = doc.getRootElement().element(SelectElement);
				  	graph = BPELPNModelGenerator(element, formalism, 0, numArray);

			  } catch (Exception e) {
	        		e.printStackTrace();
			  }
			  return graph;
		  }

		  
		  /**
		   * Generate the Petri net model (recursion)
		   * Activity "sequence" (Process)
		   * It should be firstly called, because the entire process
		   * is enclosed with "sequence"
		   * 
		   * @param element the xml document source
		   * @return IGraph part graph of BPEL.
		   */
		    public IGraph BPELPNModelGenerator(Element element, String formalism, int level, int[] numArray) {
		    	/**
		    	 * Recursion
		    	 */

		    	// Arrange the location of every places and transitions.
		    	int x = 150;
		    	int y = 150;
		    	Point point = new Point(x,y);
		    	
		    	IGraph graph = new GraphModel(formalism);
		    	IGraph tempGraph = new GraphModel(formalism);
//		    	IGraph tempGraphA = new GraphModel(formalism);
		    	Iterator iter = element.elementIterator();
		    	String nodeName;
		    	String StrPrint = "";
		    	IAttribute attribute,tempAttr;
		    	int i = 0;
		    	INode P_Start, P_End, nodeTemp, node, P_temp, P_Strat_New;
		    	boolean hasFirstStartNode = false;
		    	
		    	try {
					P_Start = graph.createNode("place");

					P_Strat_New = P_Start;
					P_temp = P_Start;
					P_End = P_Start;
					nodeTemp = P_Start;
					
			    	// Calculate the UNIQUE ID of Place and Transition.
					StrPrint = "";
					for(int n=0; n<=level; n++){
						StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
					}
					LOGGER.fine("StrPrint is "+StrPrint);
					
					level++;
			    	while(iter.hasNext()){
					       Element elem=(Element)iter.next();
					       if(elem instanceof Element){
					    	   tempGraph = BPELPNModelGeneratorEach(elem, formalism, level, numArray);
					    	   numArray[level]++;
					    	   
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
					    			   if (hasFirstStartNode == false){
					    				   P_Strat_New = node;
					    				   hasFirstStartNode = true;
					    			   }
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
					    	   
					    	   // ***
					    	   // Use transition to connect two different submodels
					    	   // For the reason of reducing the number of place and transitions
					    	   // and scale of model, delete the 3 lines code.
					    	   // ***
//					    	   INode T_temp = graph.createNode("transition");
//			    			   graph.createArc("arc", P_temp, T_temp);
//			    			   graph.createArc("arc", T_temp, nodeTemp);
			    			   
					    	   // ***
			    			   // merge 2 submodel by merging 2 places 
					    	   // (P_end of one submodel and P_start of another submodel)
					    	   // create Arc and delete the place
					    	   // ***
					    	   
					    	   if (nodeTemp.getOutgoingArcs().size()!= 0)
					    	   {
					    		   // ********************************************************
					    		   // * This is a very strange problem (IMPORTANT)
					    		   // * numOutcomingArc = nodeTemp.getOutcomingArcs().size()/2
					    		   // * why size()/2 is right?????
					    		   // * if the example change, problem will probably happen again
					    		   // * The problem has been solved, which is related to the function
					    		   // * getOutcomingArcs().
					    		   // ********************************************************
					    		   int numOutcomingArc = nodeTemp.getOutgoingArcs().size();
					    		   
					    		   LOGGER.fine(nodeTemp.getAttribute("name").getValue() + " nodeTemp.getOutgoingArcs().size() is " + numOutcomingArc);
					    		   
					    		   // Use new function arc.reconnect(P_temp, T_temp) of IArc
					    		   // to reconnect the arc [the method is much better!]
					    		   ArrayList<IArc> List_Arc_Reconnect = new ArrayList<IArc>();
					    		   Iterator iterator = nodeTemp.getOutgoingArcs().iterator();
					    		   while(iterator.hasNext()){
					    			   IArc arc = (IArc) iterator.next();
					    			   LOGGER.fine("Old " + arc.getTarget().toString());
					    			   List_Arc_Reconnect.add(arc);
//					    			   LOGGER.fine(((IArc)iterator.next()).getTarget().toString());
					    		   }
					    		   
					    		   for(int count = 0; count<numOutcomingArc; count++){
					    			   IArc arc = List_Arc_Reconnect.get(count);
					    			   INode T_temp = arc.getTarget();
					    			   arc.reconnect(P_temp, T_temp);
//					    			   LOGGER.fine("******" + arc.toString());
//					    			   LOGGER.fine("New " + arc.getSource().toString());
					    		   }
					    		   
				    			   graph.deleteNode(nodeTemp);
					    	   }
			    			   P_temp = P_End;
			    			   // *******************************************
			    			   
					       }
					       else{
				    	   
					    	   return null;
					       }
					       i++;
					}
			    	
			    	// Delete the temp start node P_Start
			    	// in order to reduce the scale of model.
			    	

			    	// Set the name of the first place 
			    	// Rename the first start node of model
					attribute = P_Start.getAttribute("name");
					attribute.setValue("P_"+StrPrint+element.getName()+"_Start");
					// Manage the localization of the place 
			    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
			    	nodeGraphInfo.setLocation(point);
			    	
			    	// P_End 
			    	// Set the name of the last place
			    	// Rename the last end node of model
					attribute = P_End.getAttribute("name");
					attribute.setValue("P_"+StrPrint+element.getName()+"_End");
					// Manage the localization of the place 
			    	nodeGraphInfo = P_End.getGraphicInfo();
			    	point.setLocation(x, y+300);
			    	nodeGraphInfo.setLocation(point);
			    	
			    	// *******************************************************
			    	// * in order to reduce the scale of model
			    	// * do not generate any unnecessary places and transitions
			    	// * which are used for connecting different submodels
			    	// *******************************************************
			    	// Create the last node (place)
//					P_End = graph.createNode("place");
//					attribute = P_End.getAttribute("name");
//					attribute.setValue("P_"+level+"_"+element.getName()+"_End");
//			    	nodeGraphInfo = P_End.getGraphicInfo();
//			    	point.setLocation(x, y+300);
//			    	nodeGraphInfo.setLocation(point);
//					
// 				   	INode T_temp = graph.createNode("transition");
// 				   	System.out.println("P_temp is "+P_temp.getAttribute("name").getValue());
// 				   	System.out.println("P_End is "+P_End.getAttribute("name").getValue());
// 				   	graph.createArc("arc", P_temp, T_temp);
// 				   	graph.createArc("arc", T_temp, P_End);
			    	
				} catch (ModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	return graph;
		    	
		    }
	    
	    
			  /**
			   * Generate the every activity of the Petri net model (recursion)
			   * According to different activity,
			   * this function will call related function to generate
			   * the submodel. 
			   * 
			   * @param element the xml document source
			   * @return IGraph part graph of BPEL.
			   */
		    public IGraph BPELPNModelGeneratorEach(Element elem, String formalism, int level, int []numArray){
		    	
		    	IGraph tempGraph = new GraphModel(formalism);
		    	String tempString;
		    	System.out.println(elem.getName());
		    	
		    	   //do something 
	    		   
		    	   tempString = elem.getName();
		    	   if ((tempString.equalsIgnoreCase("receive"))){
//		    		   System.out.println("*****partnerLink:" + elem.attribute("partnerLink").getText());

		    		   tempGraph = GenerateReceive(formalism, elem.attribute("partnerLink").getText(), level, numArray);
		    	   }
		    	   else if((tempString.equalsIgnoreCase("invoke"))){
		    		   if(elem.attribute("outputVariable") == null){
		    			   // One-way invoke
		    			   System.out.println("One-Way invoke");
		    			   tempGraph = GenerateInvokeOneway(formalism, elem.attribute("partnerLink").getText(), level, numArray);
		    		   }
		    		   else{
		    			   // Request-Response invoke
		    			   System.out.println("Request-Response invoke");
		    			   tempGraph = GenerateInvokeReqrep(formalism, elem.attribute("partnerLink").getText(), level, numArray);
		    		   }
		    	   }
		    	   else if((tempString.equalsIgnoreCase("reply"))){
		    		   tempGraph = GenerateReply(formalism, elem.attribute("partnerLink").getText(), level, numArray);
		    	   }
		    	   else if((tempString.equalsIgnoreCase("assign"))){
		    		   tempGraph = GenerateAssign(formalism, level, numArray);
		    	   }
		    	   else if((tempString.equalsIgnoreCase("sequence"))){
		    		   tempGraph = BPELPNModelGenerator(elem, formalism, level, numArray);
		    	   }
//		    	   else if((tempString.equalsIgnoreCase("if"))){
//		    		   tempGraph = GenerateAssign(formalism, level+1);
//		    	   }
		    	   else if((tempString.equalsIgnoreCase("flow"))){
		    		   tempGraph = GenerateFlow(elem,formalism, level, numArray);
		    	   }
		    	   else if((tempString.equalsIgnoreCase("pick"))){
		    		   tempGraph = GeneratePick(elem, formalism, level, numArray);
		    	   }
//		    	   else if((tempString.equalsIgnoreCase("while"))){
//		    		   tempGraph = GenerateAssign(formalism, level+1);
//		    	   }
		    	   else if((tempString.equalsIgnoreCase("switch"))){
		    		   tempGraph = GenerateSwitch(elem, formalism, level, numArray);
		    	   }
		    	   else
		    	   {
		    		   tempGraph = BPELPNModelGenerator(elem, formalism, level, numArray);
		    		   //return null;
		    		   //for test
		    	   }
		    	   return tempGraph;
		    }
	    
		  /**
		   * Generate the IGraph Petri model of 'Receive' activity
		   * according to the transformation rule of 'Receive' activity
		   * 
		   * @param graph the IGraph submodel 
		   */
	    public IGraph GenerateReceive(String formalism, String link, int level, int [] numArray){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 200;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
			try {
				// The first input place of 'Receive' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Reveive_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Reveive_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+StrPrint+"Receive");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Receive_In");
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Receive_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Receive_" + link +"_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", P_Msg_Rec, T_transition);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Receive_MSG");
		    	
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
	    public IGraph GenerateInvokeOneway(String formalism, String link, int level, int [] numArray){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 300;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
			try {
				// The first input place of 'Receive' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeOneWay_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeOneWay_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+StrPrint+"InvokeOneWay");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeOneWay_In");
		    	
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeOneWay_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeOneWay_" + link +"_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", T_transition, P_Msg_Rec);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeOneWay_MSG");
		    	
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
	    public IGraph GenerateInvokeReqrep(String formalism, String link, int level, int[] numArray){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 500;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
			try {
				// The first input place of 'Invoke' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_Start");			    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The transition between place P_Start and P_Middle
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+StrPrint+"InvokeReqRep_Req");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between P_Start and transition(req)
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_In");
		    	
		    	//Node Msg Req
		    	INode P_Msg_Req = graph.createNode("place");
		    	attribute = P_Msg_Req.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_Req_" + link +"_MSG");
		    	nodeGraphInfo = P_Msg_Req.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between transition(req) and MSG(req)
		    	arc = graph.createArc("arc", T_transition, P_Msg_Req);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_Req_MSG");
		    	
		    	// Node middle
		    	INode P_Middle = graph.createNode("place");
		    	attribute = P_Middle.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_Mid");
		    	nodeGraphInfo = P_Middle.getGraphicInfo();
		    	point.setLocation(x+50, y+75);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between transition(req) and place P_Middle
		    	arc = graph.createArc("arc", T_transition, P_Middle);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_P_Mid");
		    	
		    	// Transition (Response) between P_Middle and P_End
		    	T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+StrPrint+"InvokeReqRep_Res");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between P_Middle and transition (Response)
		    	arc = graph.createArc("arc", P_Middle, T_transition);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_P_Mid");
		    	
		    	// Msg (Response)
		    	INode P_Msg_Res = graph.createNode("place");
		    	attribute = P_Msg_Res.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_Res_" + link +"_MSG");
		    	nodeGraphInfo = P_Msg_Res.getGraphicInfo();
		    	point.setLocation(x+100, y+100);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between transition (Response) and Msg (Response)
		    	arc = graph.createArc("arc", P_Msg_Res, T_transition);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_Res_MSG");
		    	
		    	// The last output place of 'Invoke' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+150);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between transition (Response) and last place
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_Out");
		    	
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
	    public IGraph GenerateReply(String formalism, String link, int level, int [] numArray){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 200;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
			try {
				// The first input place of 'Reply' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"_Reply_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Reply_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+StrPrint+"Reply");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Reply_In");
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Reply_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Reply_" + link +"_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", T_transition, P_Msg_Rec);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Reply_MSG");
		    	
		    	
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
	    public IGraph GenerateAssign(String formalism, int level, int [] numArray){
	    	IGraph graph = new GraphModel(formalism);
	    	
//	    	IAttribute attribute = new AttributeModel();
	    	INode P_Start;
	    	int x = 500;
	    	int y = 200;
	    	Point point = new Point(x,y);
	    	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
			try {
				// The first input place of 'Assign' sub model
				P_Start = graph.createNode("place");
				// Set the name of place 'P_Start'
		    	IAttribute attribute = P_Start.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Assign_Start");	
		    	
		    	// Set the location of the place 'P_Start' in Coloane
		    	INodeGraphicInfo nodeGraphInfo = P_Start.getGraphicInfo();
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// The last output place of 'Receive' sub model
				INode P_End = graph.createNode("place");
		    	attribute = P_End.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Assign_End");
		    	nodeGraphInfo = P_End.getGraphicInfo();
		    	point.setLocation(x, y+100);
		    	nodeGraphInfo.setLocation(point);

		    	// The transition between place P_Start and P_End
		    	INode T_transition = graph.createNode("transition");
		    	attribute = T_transition.getAttribute("name");
		    	attribute.setValue("T_"+StrPrint+"Assign");
		    	nodeGraphInfo = T_transition.getGraphicInfo();
		    	point.setLocation(x, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between P_Start and T_transition
		    	IArc arc = graph.createArc("arc", P_Start, T_transition);		    	
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Assign_In");
		    	
		    	// Arc between T_transition and P_End
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
//		    	attribute.setValue("Arc_"+StrPrint+"Assign_Out");
		    	
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
	    
	    // Bug1：Flow存在问题，就是当Flow下面紧跟着sequence的情况是正确的，但是如果紧跟着是一个invoke单个的活动
	    // 就会出现问题
	    public IGraph GenerateFlow(Element elem,String formalism, int level, int[] numArray){

	    	IGraph tempGraph = new GraphModel(formalism);
	    	IGraph tempGraphA = new GraphModel(formalism);
	    	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
 		   	INode nodeStartFlow, nodeEndFlow;
 		   	try {
 		   			// The first input place of 'flow' sub model
					INode P_Start_Flow = tempGraph.createNode("place");
	
			    	
		    		// The following transition after the first input place
			    	// of 'flow' sub model
					INode T_Start_Flow = tempGraph.createNode("transition");
					// Set the name of place 'P_Start'
			    	IAttribute attribute = T_Start_Flow.getAttribute("name");
			    	attribute.setValue("T_"+StrPrint+"Flow_In");
			    	
			    	// Add arc between P_Start and T_Start
			    	IArc arc = tempGraph.createArc("arc", P_Start_Flow, T_Start_Flow);		    	
			    	attribute = arc.getAttribute("valuation");
//			    	attribute.setValue("Arc_"+StrPrint+"Flow_In");
			    	
			    	
		    		// The last place of 'flow' sub model
					INode P_End_Flow = tempGraph.createNode("place");
	
			    	
			    	// The last transition of 'flow' sub model
					INode T_End_Flow = tempGraph.createNode("transition");
					// Set the name of place 'P_Start'
			    	attribute = T_End_Flow.getAttribute("name");
			    	attribute.setValue("T_"+StrPrint+"Flow_Out");
			    	
			    	// Add arc between P_Start and T_Start
			    	arc = tempGraph.createArc("arc", T_End_Flow, P_End_Flow);		    	
			    	attribute = arc.getAttribute("valuation");
//			    	attribute.setValue("Arc_"+StrPrint+"Flow_Out");
			    	
			    	// Initialization
			    	nodeStartFlow = P_Start_Flow;
			    	nodeEndFlow = P_End_Flow;
			    	
		 			level++;
		 				   
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
		 			   Element elemTemp =(Element)iterTemp.next();

		 			   // Modified by zj on 15/06/09
		 			   // Content: Solve the bug1 - process several parallel non-sequence activities.
		 			   // For example: <Flow> <Invoke> <Reply> <Receive> </Flow>
		 				   // Used to process parallel non-sequence activities
		 			   tempGraphA = BPELPNModelGeneratorEach(elemTemp, formalism, level, numArray);
		 			   tempGraph.addGraph(tempGraphA);
		 			   numArray[level]++;
	 			   
		 			   // Add the connection between the sub model and parent model.
			    	   Iterator<INode> iterNodeFlow = tempGraph.getNodes().iterator();
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
				    	// In order to implement the connection of submodels
				    	// by identifying the name of places or transitions.
				    	// Check whether they end with "start" or "end".
				    	// ************************************************
						// Set the name of place 'P_Start'
				    	attribute = P_Start_Flow.getAttribute("name");
				    	attribute.setValue("P_"+StrPrint+"_Flow_Start");
				    	
						// Set the name of place 'P_Start'
				    	attribute = P_End_Flow.getAttribute("name");
				    	attribute.setValue("P_"+StrPrint+"_Flow_End");
		    	   } catch (ModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		    	   }

		    	   return tempGraph;
		    	}
	    
	    // It seems to be a bit complex.
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
	    public IGraph GeneratePick(Element elem, String formalism, int level, int [] numArray){

	    	IGraph tempGraph = new GraphModel(formalism);
	    	IGraph tempGraphA = new GraphModel(formalism);
	    	
	    		   
//	    	boolean is_onMessage_exist = false;
	    	
		   	INode nodeStartPick;//[] = new INode[2];
		   	INode nodeEndPick;//[] = new INode[2];
		   	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
		   	
		   	IAttribute attribute;
		   	try {
		   			// The first input place of 'pick' sub model
					INode P_Start_Pick = tempGraph.createNode("place");
			    	
		    		// The last place of 'pick' sub model
					INode P_End_Pick = tempGraph.createNode("place");
					
					nodeStartPick = P_Start_Pick;
					nodeEndPick = P_End_Pick;
			    	
		 			level++;
					
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
				    	Element elemTemp =(Element)iterTemp.next();
//				    	Iterator iterTempNext = elemTemp.elementIterator();
//				    	Element elemTempNext = (Element)iterTempNext.next();
				    	LOGGER.fine("elemTempNext is " + elemTemp.getName()); 
				    	
				    	tempGraphA = BPELPNModelGeneratorEach(elemTemp, formalism, level, numArray);
				    	tempGraph.addGraph(tempGraphA);
				    	numArray[level]++;
		 			   
		 			   // Add the connection between the sub model and parent model.
				    	Iterator<INode> iterNodePick = tempGraph.getNodes().iterator();
				    	while(iterNodePick.hasNext()){
				    		   INode nodePick = (INode) iterNodePick.next();
				    		   IAttribute tempAttr = nodePick.getAttribute("name");
				    		   String nodeName = tempAttr.getValue();
				    		   
				    		   if (nodeName.endsWith("Start")){
				    			   nodeStartPick = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
				    			   System.out.println("Start is "+nodeName);
				    		   }
				    		   else if (nodeName.endsWith("End")){
				    			   nodeEndPick = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
				    			   System.out.println("End is "+nodeName);
				    		   }
				    		   else{
				    			   // do nothing
				    		   }
				    	}
				    	
				    	// Create Branch up transition and related arc
				    	INode T_Pick_Up = tempGraph.createNode("transition");
				    	
				    	if (elemTemp.getName().equalsIgnoreCase("onmessage")){
				    		T_Pick_Up.getAttribute("name").setValue("onMessage: " + elemTemp.attributeValue("variable"));
				    		INode P_Pick_onMessage = tempGraph.createNode("place");
				    		IAttribute tempAttr = P_Pick_onMessage.getAttribute("name");
				    		tempAttr.setValue("P_" + StrPrint + elemTemp.attributeValue("variable") + "_MSG");
				    		tempGraph.createArc("arc", P_Pick_onMessage, T_Pick_Up);
				    	}
				    	else
				    	{
				    		if(elemTemp.attributeValue("for") != null)
				    		{
				    			T_Pick_Up.getAttribute("name").setValue("onAlarm: " + elemTemp.attributeValue("for"));
					    		INode P_Pick_onAlarm = tempGraph.createNode("place");
					    		IAttribute tempAttr = P_Pick_onAlarm.getAttribute("name");
					    		tempAttr.setValue("P_" + StrPrint + elemTemp.attributeValue("for") + "_MSG");
					    		tempGraph.createArc("arc", P_Pick_onAlarm, T_Pick_Up);
				    		}
				    		else
				    		{
				    			T_Pick_Up.getAttribute("name").setValue("onAlarm: " + elemTemp.attributeValue("until"));
					    		INode P_Pick_onAlarm = tempGraph.createNode("place");
					    		IAttribute tempAttr = P_Pick_onAlarm.getAttribute("name");
					    		tempAttr.setValue("P_" + StrPrint + elemTemp.attributeValue("until") + "_MSG");
					    		tempGraph.createArc("arc", P_Pick_onAlarm, T_Pick_Up);
				    		}
				    	}
				    	tempGraph.createArc("arc", P_Start_Pick, T_Pick_Up);
				    	tempGraph.createArc("arc", T_Pick_Up, nodeStartPick);
				    	
				    				    	
				    	LOGGER.fine("T_Pick_Up is " + T_Pick_Up.getAttribute("name").getValue()); 
				    	LOGGER.fine("P_Start_Pick is " + P_Start_Pick.getAttribute("name").getValue()); 
				    	LOGGER.fine("nodeStartPick is " + nodeStartPick.getAttribute("name").getValue()); 
				    	
				    	

				    	// Create Branch down transition and related arc
				    	INode T_Pick_Down = tempGraph.createNode("transition");
				    	if (elemTemp.getName().equalsIgnoreCase("onmessage")){
				    		T_Pick_Down.getAttribute("name").setValue("T_" + StrPrint + "Pick_EndonMessage");
				    	}
				    	else
				    	{
				    		T_Pick_Down.getAttribute("name").setValue("T_" + StrPrint + "Pick_EndonAlarm");
				    	}
				    	tempGraph.createArc("arc", nodeEndPick, T_Pick_Down);
				    	tempGraph.createArc("arc", T_Pick_Down, P_End_Pick);
				    	LOGGER.fine("T_Switch_Down is " + T_Pick_Down.getAttribute("name").getValue()); 
				    	LOGGER.fine("nodeEndSwitch is " + nodeEndPick.getAttribute("name").getValue()); 
				    	LOGGER.fine("P_End_Switch is " + P_End_Pick.getAttribute("name").getValue()); 
			    	}
			    	
			    	// ***********************************************
			    	// In order to implement the conncection of submodels
			    	// by identifying the name of places or transitions.
			    	// Check whether they end with "start" or "end".
			    	// ************************************************
			    	// Set the name of place 'P_Start_Pick'
			    	attribute = P_Start_Pick.getAttribute("name");
			    	attribute.setValue("P_"+StrPrint+"_Switch_Start");
			    	
			    	// Set the name of place 'P_End_Pick'
			    	attribute = P_End_Pick.getAttribute("name");
			    	attribute.setValue("P_"+StrPrint+"_Switch_End");
			    	
			    	
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
	    	
	    	
//	    	IGraph tempGraph = new GraphModel(formalism);
//	    	IGraph tempGraphA = new GraphModel(formalism);
//		   
//	    	Element elemTemp;
//	    	// Calculate the UNIQUE ID of Place and Transition.
//			String StrPrint = "";
//			for(int n=0; n<=level; n++){
//				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
//			}
//	    	
//		   	IAttribute attribute;
//		   	try {
//		   			// The first input place of 'PICK' sub model
//					INode P_Start_Pick = tempGraph.createNode("place");
//			    	
//		    		// The last place of 'PICK' sub model
//					INode P_End_Pick = tempGraph.createNode("place");
//			    	
//				   	INode nodeStartPick = P_Start_Pick;
//				   	INode nodeEndPick = P_End_Pick;
//					
//					level++;
//					
//			    	Iterator iterTemp = elem.elementIterator();
//			    	while(iterTemp.hasNext()){
//				    	elemTemp =(Element)iterTemp.next();
//				    	LOGGER.fine("elemTempNext is " + elemTemp.getName()); 
//				    	
//				    	
//				    	Iterator iterTemp1 = elemTemp.elementIterator();
//				    	Element e1 = (Element)iterTemp1.next();
//				    	System.out.println("iterTemp1 is " + e1.getName());
//				    	
//				    	tempGraphA = BPELPNModelGeneratorEach(e1, formalism, level, numArray);
//				    	tempGraph.addGraph(tempGraphA);
//				    	numArray[level]++;
//			    		
//		 			   // Add the connection between the sub model and parent model.
//				    	Iterator<INode> iterNodePick = tempGraph.getNodes().iterator();
//				    	while(iterNodePick.hasNext()){
//				    		   INode nodePick = (INode) iterNodePick.next();
//				    		   IAttribute tempAttr = nodePick.getAttribute("name");
//				    		   String nodeName = tempAttr.getValue();
//				    		   
//				    		   LOGGER.fine("nodeName is " + nodeName); 
//				    		   
//				    		   if (nodeName.endsWith("Start")){
//				    			   nodeStartPick = nodePick;
//				    			   tempAttr.setValue(nodeName+"_0");
////				    			   System.out.println("Start is "+nodeName);
//				    		   }
//				    		   else if (nodeName.endsWith("End")){
//				    			   nodeEndPick = nodePick;
//				    			   tempAttr.setValue(nodeName+"_0");
////				    			   System.out.println("End is "+nodeName);
//				    		   }
//				    		   else{
//				    			   // do nothing
//				    		   }
//				    	}
//				    	
//				    	// Connect Pick branch A with Pick self (up)
//				    	INode T_Pick_A_Up = tempGraph.createNode("transition");
//				    	tempGraph.createArc("arc", P_Start_Pick, T_Pick_A_Up);
//				    	tempGraph.createArc("arc", T_Pick_A_Up, nodeStartPick);
////				    	System.out.println("T_Pick_A_Up 2 arc! " + nodeStartPick.getAttribute("name").getValue());
//				    	
//				    	// Connect Pick branch A with Pick self (down)
//				    	INode T_Pick_A_Down = tempGraph.createNode("transition");
//				    	tempGraph.createArc("arc", nodeEndPick, T_Pick_A_Down);
//				    	tempGraph.createArc("arc", T_Pick_A_Down, P_End_Pick);
////				    	System.out.println("T_Pick_A_Down 2 arc! " + nodeStartPick.getAttribute("name").getValue());
//				    	
//				    	T_Pick_A_Up.getAttribute("name").setValue("T_" + StrPrint + "Start" + elemTemp.getName());
//				    	T_Pick_A_Down.getAttribute("name").setValue("T_" + StrPrint + "End" + elemTemp.getName());
//			    	}
//			    	
//			    	// ***********************************************
//			    	// In order to implement the connection of submodels
//			    	// by identifying the name of places or transitions.
//			    	// Check whether they end with "start" or "end".
//			    	// ************************************************
//			    	// Set the name of place 'P_Start_Pick'
//			    	attribute = P_Start_Pick.getAttribute("name");
//			    	attribute.setValue("P_"+StrPrint+"_Pick_Start");
//			    	System.out.println("P_Start_Pick has " + P_Start_Pick.getOutgoingArcs().size());
//			    	
//			    	// Set the name of place 'P_End_Pick'
//			    	attribute = P_End_Pick.getAttribute("name");
//			    	attribute.setValue("P_"+StrPrint+"_Pick_End");
//			    	
//			    	// Create the inhibitor arc in Branch A
//			    	// ********************************************
//			    	// * For BPEL Process, it is not necessary to use inhibitor ARC.
//			    	// * It depends on the token
//			    	// * If needed, just delete the "//" of the following 2 liens.
//			    	// ********************************************
////			    	IArc T_Pick_A_Inhib = tempGraph.createArc("inhibitor", nodeStartPick[0], T_Pick_B_Up);
////			    	T_Pick_A_Inhib.getAttribute("valuation").setValue("Inhib_" + level + "_Pick_Branch_A");
//			    	
//			    	// Create the inhibitor arc in Branch A
//			    	// ********************************************
//			    	// * For BPEL Process, it is not necessary to use inhibitor ARC.
//			    	// * It depends on the token
//			    	// * If needed, just delete the "//" of the following 2 liens.
//			    	// ********************************************
////			    	IArc T_Pick_B_Inhib = tempGraph.createArc("inhibitor", nodeStartPick[1], T_Pick_A_Up);
////			    	T_Pick_B_Inhib.getAttribute("valuation").setValue("Inhib_" + level + "_Pick_Branch_B");
//			    	
//			    	
//		    	   	}catch (ModelException e) {
//		    	   	// TODO Auto-generated catch block
//		    	   		e.printStackTrace();
//		    	   }
//
//		    	return tempGraph;
		    }
	    
	    
		  /**
		   * Generate the IGraph Petri model of structured activity 'Switch' 
		   * according to the transformation rule of 'Switch' activity
		   * 
		   * @param elem element in xml document
		   * @param formalism the formalism of the Graph 
		   * @param  level show the level of node (activity) 
		   * @return IGraph
		   */
	    public IGraph GenerateSwitch(Element elem,String formalism, int level, int [] numArray){

	    	IGraph tempGraph = new GraphModel(formalism);
	    	IGraph tempGraphA = new GraphModel(formalism);
		   
//	    	int num_count = 0;
	    	
		   	INode nodeStartSwitch;//[] = new INode[2];
		   	INode nodeEndSwitch;//[] = new INode[2];
		   	
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
		   	
		   	IAttribute attribute;
		   	try {
		   			// The first input place of 'flow' sub model
					INode P_Start_Switch = tempGraph.createNode("place");
			    	
		    		// The last place of 'flow' sub model
					INode P_End_Switch = tempGraph.createNode("place");
					
					nodeStartSwitch = P_Start_Switch;
					nodeEndSwitch = P_End_Switch;
			    	
		 			level++;
					
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
				    	Element elemTemp =(Element)iterTemp.next();
//				    	Iterator iterTempNext = elemTemp.elementIterator();
//				    	Element elemTempNext = (Element)iterTempNext.next();
				    	LOGGER.fine("elemTempNext is " + elemTemp.getName()); 
				    	
				    	tempGraphA = BPELPNModelGeneratorEach(elemTemp, formalism, level, numArray);
				    	tempGraph.addGraph(tempGraphA);
				    	numArray[level]++;
		 			   
		 			   // Add the connection between the sub model and parent model.
				    	Iterator<INode> iterNodeSwitch = tempGraph.getNodes().iterator();
				    	while(iterNodeSwitch.hasNext()){
				    		   INode nodePick = (INode) iterNodeSwitch.next();
				    		   IAttribute tempAttr = nodePick.getAttribute("name");
				    		   String nodeName = tempAttr.getValue();
				    		   
				    		   if (nodeName.endsWith("Start")){
				    			   nodeStartSwitch = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
				    			   System.out.println("Start is "+nodeName);
				    		   }
				    		   else if (nodeName.endsWith("End")){
				    			   nodeEndSwitch = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
				    			   System.out.println("End is "+nodeName);
				    		   }
				    		   else{
				    			   // do nothing
				    		   }
				    	}
				    	

				    	
				    	// Create Branch up transition and related arc
				    	INode T_Switch_Up = tempGraph.createNode("transition");
				    	if (elemTemp.getName().equalsIgnoreCase("case")){
				    		T_Switch_Up.getAttribute("name").setValue("CASE: " + elemTemp.attributeValue("condition"));
				    	}
				    	else
				    	{
				    		T_Switch_Up.getAttribute("name").setValue("OTHERWISE:");
				    	}
				    	tempGraph.createArc("arc", P_Start_Switch, T_Switch_Up);
				    	tempGraph.createArc("arc", T_Switch_Up, nodeStartSwitch);
				    	
				    				    	
				    	LOGGER.fine("T_Switch_Up is " + T_Switch_Up.getAttribute("name").getValue()); 
				    	LOGGER.fine("P_Start_Switch is " + P_Start_Switch.getAttribute("name").getValue()); 
				    	LOGGER.fine("nodeStartSwitch is " + nodeStartSwitch.getAttribute("name").getValue()); 
				    	
				    	

				    	// Create Branch down transition and related arc
				    	INode T_Switch_Down = tempGraph.createNode("transition");
				    	if (elemTemp.getName().equalsIgnoreCase("case")){
				    		T_Switch_Down.getAttribute("name").setValue("T_" + StrPrint + "Switch_EndCase");
				    	}
				    	else
				    	{
				    		T_Switch_Down.getAttribute("name").setValue("T_" + StrPrint + "Switch_EndOtherwise");
				    	}
				    	tempGraph.createArc("arc", nodeEndSwitch, T_Switch_Down);
				    	tempGraph.createArc("arc", T_Switch_Down, P_End_Switch);
				    	LOGGER.fine("T_Switch_Down is " + T_Switch_Down.getAttribute("name").getValue()); 
				    	LOGGER.fine("nodeEndSwitch is " + nodeEndSwitch.getAttribute("name").getValue()); 
				    	LOGGER.fine("P_End_Switch is " + P_End_Switch.getAttribute("name").getValue()); 
			    	}
			    	
			    	// ***********************************************
			    	// In order to implement the conncection of submodels
			    	// by identifying the name of places or transitions.
			    	// Check whether they end with "start" or "end".
			    	// ************************************************
			    	// Set the name of place 'P_Start_Pick'
			    	attribute = P_Start_Switch.getAttribute("name");
			    	attribute.setValue("P_"+StrPrint+"_Switch_Start");
			    	
			    	// Set the name of place 'P_End_Pick'
			    	attribute = P_End_Switch.getAttribute("name");
			    	attribute.setValue("P_"+StrPrint+"_Switch_End");
			    	
			    	
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
	    
		  /**
		   * Generate the Incidence Matrix from IGraph
		   * Matrix consists of Pre Matrix and Post Matrix.
		   * 
		   * @param graph The graph (petri net)
		   * @return Incidence Matrix
		   */
	    public String[][] GenerateIncidenceMatrix(IGraph graph){
	    	int num_P = 0;
	    	int num_T = 0;
	    	
	    	// Define the maximum length of incidence matrix
	    	int num_T_Max = 1000;
	    	int num_P_Max = 1000;
	    	
//	    	int num_A = 0;
	    	// Define the Matrix
	    	// Use array as incidence matrix,
	    	// for the consideration of runtime monitor efficiency. 
	    	String[][] MatrixPre = new String[num_T_Max][num_P_Max];
	    	String[][] MatrixPost = new String[num_T_Max][num_P_Max];
	    	String [] Matrix_T_Name = new String[num_T_Max];
	    	String [] Matrix_P_Name = new String[num_P_Max];
	    	
	    	String tempName = null;
	    	
	    	int size_graph = graph.getNodes().size();
	    	Iterator<INode> iterNode = graph.getNodes().iterator();
	    	
	    	// As for every transition,
	    	// calculate every arc of this transition.
	    	// And add to the incidence matrix.
	    	while(iterNode.hasNext()){
	    		INode nodeTemp = (INode) iterNode.next();
	    		if(nodeTemp.getNodeFormalism().getName().equalsIgnoreCase("transition")){
	    			Matrix_T_Name[num_T] = nodeTemp.getAttribute("name").getValue();
	    			Iterator<IArc> incomingArc = nodeTemp.getIncomingArcs().iterator();
	    			
	    			// Every incoming arc of related transition
	    			while(incomingArc.hasNext()){
	    				IArc tempArc = (IArc)incomingArc.next();
	    				INode tempPlace = tempArc.getSource();
	    				tempName = tempPlace.getAttribute("name").getValue();
	    				boolean is_P_Exist = false;
	    				for(int n=0; n<num_P; n++){
	    					// 如果用名字来区分不同的place，那么就要求转换的过程中，
	    					// 产生的place的命名必须不同，否则就会出现遗漏某些place的问题。
	    					if(Matrix_P_Name[n].equalsIgnoreCase(tempName)){
	    						is_P_Exist=true;
	    						MatrixPre[num_T][n] = tempArc.getAttribute("valuation").getValue();
	    						break;
	    					}
	    				}
	    				// Find new place (is_P_Exist == false)
	    				if(is_P_Exist == false){
	    					Matrix_P_Name[num_P] = tempName;
	    					MatrixPre[num_T][num_P] = tempArc.getAttribute("valuation").getValue();
	    					num_P++;
	    				}
	    			}
	    			
	    			// Every outcoming arc of related transition
	    			Iterator<IArc> OutcomingArc = nodeTemp.getOutgoingArcs().iterator();
	    			while(OutcomingArc.hasNext()){
	    				IArc tempArc = (IArc)OutcomingArc.next();
	    				INode tempPlace = tempArc.getTarget();
	    				tempName = tempPlace.getAttribute("name").getValue();
	    				boolean is_P_Exist = false;
	    				for(int n=0; n<num_P; n++){
	    					if(Matrix_P_Name[n].equalsIgnoreCase(tempName)){
	    						is_P_Exist=true;
	    						MatrixPost[num_T][n] = tempArc.getAttribute("valuation").getValue();
	    						break;
	    					}
	    				}
	    				// Find new place (is_P_Exist == false)
	    				if(is_P_Exist == false){
	    					Matrix_P_Name[num_P] = tempName;
	    					MatrixPost[num_T][num_P] = tempArc.getAttribute("valuation").getValue();
	    					num_P++;
	    				}
	    			}
	    			num_T++;
	    		}
	    	}
//	    	num_P = size_graph - num_T;
	    	LOGGER.fine("The number of nodes in this graph is " + size_graph);
	    	LOGGER.fine("The number of places in this graph is " + num_P);
	    	LOGGER.fine("The number of transitions in this graph is " + num_T);
	    	LOGGER.fine("The number of arcs in this graph is " + graph.getArcs().size());
	    	
	    	System.out.print("Matrix Pre\t");
	    	for (int m=0; m<num_P; m++){
	    		System.out.print("\t\t" + Matrix_P_Name[m]);
	    	}
	    	System.out.println("");
	    	for (int m=0; m<num_T; m++){
	    		System.out.print(Matrix_T_Name[m]+"\t\t");
		    	for (int n=0; n<num_P; n++){
		    		System.out.print(MatrixPre[m][n]+"\t\t");
		    	}
		    	System.out.println("");
	    	}
	    	
	    	System.out.print("\nMatrix Post\t");
	    	for (int m=0; m<num_P; m++){
	    		System.out.print("\t\t" + Matrix_P_Name[m]);
	    	}
	    	System.out.println("");
	    	for (int m=0; m<num_T; m++){
	    		System.out.print(Matrix_T_Name[m]+"\t\t");
		    	for (int n=0; n<num_P; n++){
		    		System.out.print(MatrixPost[m][n]+"\t\t");
		    	}
		    	System.out.println("");
	    	}
	    	return MatrixPre;	
	    }
	    
	    
		  /**
		   * Reduce the graph (petri net model) LEVEL 1
		   * Try to eliminate the redundant places and transitions like,
		   * P->T->P->T  reduce to P->T
		   * 
		   * @param graph The original graph (petri net model)
		   */
	    public void GraphReductionLevel1(IGraph graph){
	    	
	    	Iterator<INode> iterNode = graph.getNodes().iterator();
	    	
	    	// Find the entry of petri net.
	    	// Usually it is the node of sequence start.
	    	// Ex. P_0_sequence_Start
	    	System.out.println("Entry of GraphReduction!");
	    	while(iterNode.hasNext()){
	    		INode nodeTemp = (INode) iterNode.next();
	    		if (nodeTemp.getAttribute("name").getValue().endsWith("Start")){
//	    			System.out.println("GraphReduction() "+ nodeTemp.getAttribute("name").getValue() + " OutComingArc Size " + nodeTemp.getOutgoingArcs().size());
	    			GraphReductionLevel1(graph, nodeTemp);
	    			break;
	    		}
	    	}
	    	
//	    	return graph;
	    }
	    
		  /**
		   * Reduce the graph (petri net model) LEVEL 1
		   * Try to eliminate the redundant places and transitions like,
		   * P->T->P->T  reduce to P->T
		   * 
		   * @param graph The original graph (petri net model)
		   */
	    public void GraphReductionLevel1(IGraph graph, INode node){
	    	
	    	INode nodeCurrent = node;
	    	INode nodeTemp = node;
	    	INode nodeTempPrevious = node;
	    	
	    	int lengthReduce = 0;
	    	int numOutcomingArcs = 0;
//	    	int numIncomingArcs = 0;
	    	// Check Next Node
	    	
	    	numOutcomingArcs = nodeCurrent.getOutgoingArcs().size();
	    	LOGGER.fine("nodeCurrent is " + nodeCurrent.getAttribute("name").getValue());
	    	LOGGER.fine("GraphReduction(IGraph graph, INode node): getOutgoingArcs is " + numOutcomingArcs);
	    	
//		    while(numOutcomingArcs!=0)	
//		    {
		    	if(numOutcomingArcs == 0)
		    	{
		    		return;// graph;
		    	}
		    	else if(numOutcomingArcs == 1)
		    	{
		    		nodeTemp = nodeCurrent.getOutgoingArcs().get(0).getTarget();
//		    		lengthReduce++;
		    		while(nodeTemp.getOutgoingArcs().size()==1 && nodeTemp.getIncomingArcs().size()==1)
		    		{
		    			nodeTempPrevious = nodeTemp;
		    			nodeTemp = nodeTemp.getOutgoingArcs().get(0).getTarget();
		    			lengthReduce++;
		    		}
		    		// When lengthReduce > 1,
		    		// some places and transitions can be reduced.
		    		
		    		LOGGER.fine("GraphReduction(IGraph graph, INode node): lengthReduce is " + lengthReduce);
		    		if(lengthReduce > 1)
		    		{
		    			LOGGER.fine("Find redundant places and transitions!");
			    		if(lengthReduce%2==0){
			    			// Even integer
			    			IArc arcTemp;
							try {
								arcTemp = graph.createArc("arc", nodeCurrent, nodeTemp);
//								arcTemp.getAttribute("valuation").setValue("_Reduce_" + nodeCurrent.getAttribute("name").getValue()+ "_" + nodeTemp.getAttribute("name").getValue());
								LOGGER.fine("Create arc successfully: " + arcTemp.getAttribute("valuation").getValue());
							} catch (ModelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    			
			    			
			    			// Delete Redundant Arcs and Nodes.
			    			IArc arcNext = nodeCurrent.getOutgoingArcs().iterator().next();
			    			ArrayList<INode> listNodeDelete = new ArrayList<INode>();
			    			LOGGER.fine("begin to delete arc and node!");
			    			// i<=lengthReduce
			    			for (int i=0;i<lengthReduce;i++){
			    				IArc arcDelete = arcNext;
			    				INode nodeDelete = arcDelete.getTarget();
			    				listNodeDelete.add(nodeDelete);
			    				arcNext = nodeDelete.getOutgoingArcs().iterator().next();
			    			}
			    			for (int i=0;i<listNodeDelete.size();i++){
			    				graph.deleteNode(listNodeDelete.get(i));
			    			}
//			    			graph.deleteArc(arcNext);
			    		}
			    		else
			    		{
			    			IArc arcTemp;
							try {
								arcTemp = graph.createArc("arc", nodeCurrent, nodeTempPrevious);
//								arcTemp.getAttribute("valuation").setValue("_Reduce_" + nodeCurrent.getAttribute("name").getValue()+ "_" + nodeTempPrevious.getAttribute("name").getValue());
								LOGGER.fine("Create arc successfully: " + arcTemp.getAttribute("valuation").getValue());
							} catch (ModelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    			
			    			// Delete Redundant Arcs and Nodes.
			    			IArc arcNext = nodeCurrent.getOutgoingArcs().iterator().next();
			    			ArrayList<INode> listNodeDelete = new ArrayList<INode>();
			    			// i<lengthReduce
			    			for (int i=0;i<lengthReduce-1;i++){
			    				IArc arcDelete = arcNext;
			    				INode nodeDelete = arcDelete.getTarget();
			    				listNodeDelete.add(nodeDelete);
			    				arcNext = nodeDelete.getOutgoingArcs().iterator().next();
			    			}
			    			for (int i=0;i<listNodeDelete.size();i++){
			    				graph.deleteNode(listNodeDelete.get(i));
			    			}
			    		}
		    		}
		    		if (nodeTemp.getIncomingArcs().size()>1)
		    		{
		    			return;
		    		}
		    		else if(nodeTemp.getOutgoingArcs().size()>1)
		    		{
		    			nodeCurrent = nodeTemp;
		    			GraphReductionLevel1(graph, nodeCurrent);
			    		numOutcomingArcs = nodeCurrent.getOutgoingArcs().size();
		    		}
		    		else
		    		{
		    			// do nothing;
		    		}
		    	}
		    	else
		    	{
		    		Iterator<IArc> iterArc = nodeCurrent.getOutgoingArcs().iterator();
		    		while(iterArc.hasNext()){
		    			IArc arcTemp = (IArc) iterArc.next();
		    			if(!arcTemp.getTarget().getAttribute("name").getValue().endsWith("MSG"))
		    			{
		    				GraphReductionLevel1(graph, arcTemp.getTarget());
		    			}
		    		}
		    	}
	    }
	    
	    
	    /**
		   * Reduce the graph (petri net model) LEVEL 2
		   * Try to eliminate the redundant places and transitions like,
		   * P->T->P->T  reduce to P->T
		   * [THE Differences between LEVEL1 and LEVEL2 is that LEVEL2 start from ARC,
		   * Make the reduction; while LEVEL 1 from node.
		   * AS A RESULT, LEVEL2 can reduce the first node, which is stronger in reduction]
		   * 
		   * @param graph The original graph (petri net model)
		   */
	    public void GraphReductionLevel2(IGraph graph){
	    	
	    	Iterator<INode> iterNode = graph.getNodes().iterator();
	    	
	    	// Find the entry of petri net.
	    	// Usually it is the node of sequence start.
	    	// Ex. P_0_sequence_Start
	    	LOGGER.fine("GraphReductionLevel2():Entry of GraphReduction!");
	    	while(iterNode.hasNext()){
	    		INode nodeTemp = (INode) iterNode.next();
	    		if (nodeTemp.getAttribute("name").getValue().endsWith("Start")){
//	    			System.out.println("GraphReduction() "+ nodeTemp.getAttribute("name").getValue() + " OutComingArc Size " + nodeTemp.getOutgoingArcs().size());
	    			Iterator<IArc> iterArc = nodeTemp.getOutgoingArcs().iterator();
	    			LOGGER.fine("Node Start Arc num is " + nodeTemp.getOutgoingArcs().size());
	    			
	    			ArrayList<IArc> listNodeStartArc = new ArrayList<IArc>();
	    			LOGGER.fine("begin to record arc into ArrayList!");
	    			// i<=lengthReduce
	    			
	    			while(iterArc.hasNext()){
	    				IArc arcTemp = (IArc) iterArc.next();
	    				listNodeStartArc.add(arcTemp);
	    				
	    			}
	    			for (int i=0;i<listNodeStartArc.size();i++){
	    				GraphReductionLevel2(graph, listNodeStartArc.get(i));
	    			}
	    			break;
	    		}
	    	}
	    	
//	    	return graph;
	    }
	    
		  /**
		   * Reduce the graph (petri net model) LEVEL 2
		   * Try to eliminate the redundant places and transitions like,
		   * P->T->P->T  reduce to P->T
		   * 
		   * @param graph The original graph (petri net model)
		   */
	    public void GraphReductionLevel2(IGraph graph, IArc arc){
	    	
	    	INode nodeCurrent = arc.getTarget();
	    	INode nodeTemp = nodeCurrent;
	    	INode nodeTempPrevious = nodeCurrent;
	    	
	    	int lengthReduce = 0;
	    	// Check Next Node
	    	
	    	LOGGER.fine("GraphReductionLevel2(IGraph graph, IArc arc): Entry!" + arc.getTarget().toString());
	    	
	    	while(nodeTemp.getOutgoingArcs().size()==1 && nodeTemp.getIncomingArcs().size()==1)
    		{
    			nodeTempPrevious = nodeTemp;
    			nodeTemp = nodeTemp.getOutgoingArcs().get(0).getTarget();
    			lengthReduce++;
    		}
    		// When lengthReduce > 1,
    		// some places and transitions can be reduced.
    		
    		LOGGER.fine("GraphReduction(IGraph graph, INode node): lengthReduce is " + lengthReduce);
    		if(lengthReduce > 1)
    		{
    			LOGGER.fine("Find redundant places and transitions!");
	    		if(lengthReduce%2==0){
	    			// Even integer
	    			IArc arcTemp;
					try {
						arcTemp = graph.createArc("arc", arc.getSource(), nodeTemp);
//						arcTemp.getAttribute("valuation").setValue("_Reduce_" + nodeCurrent.getAttribute("name").getValue()+ "_" + nodeTemp.getAttribute("name").getValue());
						LOGGER.fine("Create arc successfully: " + arcTemp.getAttribute("valuation").getValue());
					} catch (ModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    			// Delete Redundant Arcs and Nodes.
	    			IArc arcNext = arc;
	    			ArrayList<INode> listNodeDelete = new ArrayList<INode>();
	    			LOGGER.fine("begin to delete arc and node!");
	    			// i<=lengthReduce
	    			for (int i=1;i<=lengthReduce;i++){
	    				IArc arcDelete = arcNext;
	    				INode nodeDelete = arcDelete.getTarget();
	    				listNodeDelete.add(nodeDelete);
	    				arcNext = nodeDelete.getOutgoingArcs().iterator().next();
	    				LOGGER.fine("nodeDelete " +  nodeDelete.getAttribute("name").getValue());
	    			}
	    			for (int i=0;i<listNodeDelete.size();i++){
	    				graph.deleteNode(listNodeDelete.get(i));
	    			}
//	    			graph.deleteArc(arcNext);
	    		}
	    		else
	    		{
	    			IArc arcTemp;
					try {
						arcTemp = graph.createArc("arc", arc.getSource(), nodeTempPrevious);
//						arcTemp.getAttribute("valuation").setValue("_Reduce_" + nodeCurrent.getAttribute("name").getValue()+ "_" + nodeTempPrevious.getAttribute("name").getValue());
						LOGGER.fine("Create arc successfully: " + arcTemp.getAttribute("valuation").getValue());
					} catch (ModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    			// Delete Redundant Arcs and Nodes.
	    			IArc arcNext = arc;
	    			ArrayList<INode> listNodeDelete = new ArrayList<INode>();
	    			// i<lengthReduce
	    			for (int i=1;i<lengthReduce;i++){
	    				IArc arcDelete = arcNext;
	    				INode nodeDelete = arcDelete.getTarget();
	    				listNodeDelete.add(nodeDelete);
	    				arcNext = nodeDelete.getOutgoingArcs().iterator().next();
	    				LOGGER.fine("nodeDelete " +  nodeDelete.getAttribute("name").getValue());
	    			}
	    			for (int i=0;i<listNodeDelete.size();i++){
	    				graph.deleteNode(listNodeDelete.get(i));
	    			}
	    		}
    		}
    		
			if(nodeTemp.getOutgoingArcs().size()>1 || nodeTemp.getIncomingArcs().size()>1)
			{
				nodeCurrent = nodeTemp;
				Iterator<IArc> iterArc = nodeCurrent.getOutgoingArcs().iterator();
				ArrayList<IArc> listArc = new ArrayList<IArc>();
				LOGGER.fine("begin to record arc into ArrayList!");
	    		while(iterArc.hasNext()){
	    			IArc arcTemp = (IArc) iterArc.next();
	    			if(!arcTemp.getTarget().getAttribute("name").getValue().endsWith("MSG"))
	    			{
	    				listArc.add(arcTemp);
	    			}
	    		}
	    		
				for (int i=0;i<listArc.size();i++){
					GraphReductionLevel2(graph, listArc.get(i));
				}
			}
			else
			{
				return;
			}
	    	
	    }
	    
	    
	    public void TreeWalkReduction(IGraph graph){
	    	
	    	try {
				graph.createNode("place");
				graph.createNode("place");
				graph.createNode("transition");
				graph.createNode("transition");
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	/******************************************
			 * Reduction
			 * PreOrder to walkthrough petri net model.
			 ******************************************/
//			
//			// 非递归前序遍历树
//			INode root, nodeTemp;
//			
//			Iterator iterNode = graph.getNodes().iterator();
//	    	
//	    	// Find the entry of petri net.
//	    	// Usually it is the node of sequence start.
//	    	// Ex. P_0_sequence_Start
//	    	while(iterNode.hasNext()){
//	    		nodeTemp = (INode) iterNode.next();
//	    		if (nodeTemp.getAttribute("name").getValue().endsWith("Start")){
//	    			break;
//	    		}
//	    	}
//	    	root = nodeTemp;
//			
//			Stack NodeStack = new Stack<INode>();
//			INode pNode;
//			NodeStack.push(root);
//			    while (!NodeStack.empty())
//			    {
//			        while ((pNode = (INode)NodeStack.peek())!= null)    // 向左走到尽头
//			        {
//			            System.out.println(pNode.getAttribute("name").getValue());    // 访问当前结点
//			            NodeStack.push(pNode.getOutgoingArcs().get(0).getTarget());                    // 左子树根结点入栈
//			        }
//			        NodeStack.pop();                                    // 左子树根结点退栈
//			        if (!NodeStack.empty())
//			        {
//			            pNode = (INode)NodeStack.peek();
//			            NodeStack.pop();                                // 当前结点退栈
//			            NodeStack.push(pNode->pRight);                // 当前结点的右子树根结点入栈
//			        }
//			    }
	    }
	    
	    /**
	     * The function AnalyzePartnerLinks() is used to 
	     * ditill the name of partner link from the name of MSG Places.
	     * The format is like: "P_0_2_InvokeReqRep_Res_employeeTravelStatus_MSG"
	     * @param name
	     * @return String
	     */
	    public String AnalyzePartnerLinks(String name){
	    	String namePL;
	    	int length = name.length();
	    	System.out.println(length);
	    	int endIndex = length - 5;
	    	int i = endIndex;
	    	System.out.println(i);
	    	while(name.charAt(i)!='_'){
	    		i--;
	    	}
	    	int beginIndex = i+1;
	    	namePL = name.substring(beginIndex, endIndex+1);
	    	return namePL;
	    }
		
	    
	    /**
	     * Generate code of the following function in Process Monitor
	     * public static int AnalyzeSoapMSGPartner(String linkMSG)
	     * @param output
	     */
	    public void GenerateFunctionAnalyzeMSGType(BufferedWriter output){
	    	try {	    		
	    		output.write("\n/**\n");
	    		output.write(" * Static function AnalyzeSoapMSGTYPE\n");
	    		output.write(" * analyze the SOAP Message\n");
	    		output.write(" * @param typeMSG\n");
	    		output.write(" * @return\n");
	    		output.write(" */\n");
	    		
				output.write("public static int AnalyzeSoapMSGTYPE(String typeMSG){\n");
				output.write("\tif(typeMSG.startsWith(\"out\")){\n");
				output.write("\t\treturn MSG_TYPE_OUT;}\n");
				output.write("\telse if(typeMSG.startsWith(\"in\")){\n");
				output.write("\t\treturn MSG_TYPE_IN;}\n");
				output.write("\telse{\n\t\treturn MSG_TYPE_ERROR;\n\t}\n}\n\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    /**
	     * Generate code of the following function in Process Monitor
	     * public static int AnalyzeSoapMSGPartner(String linkMSG)
	     * 
	     * @param list
	     * @param output
	     */
	    public void GenerateFunctionAnalyzePartnerLinks(ArrayList <String> list, BufferedWriter output){
	    	int i = 0;
	    	try {
	    		output.write("\n/**\n");
	    		output.write(" * Static function AnalyzeSoapMSGPartner\n");
	    		output.write(" * Analyze the Partner Links into integers.\n");
	    		output.write(" * @param linkMSG\n");
	    		output.write(" * @return\n");
	    		output.write(" */\n");
				
	    		output.write("public static int AnalyzeSoapMSGPartner(String linkMSG){\n");
				output.write("\tif(linkMSG.startsWith(\"" + list.get(i) +"\")){\n");
				output.write("\t\treturn MSG_PARTNER_" + list.get(i).toUpperCase() +";}\n");
				for(;i<list.size()-1;i++){
					output.write("\telse if(linkMSG.startsWith(\"" + list.get(i) + "\")){\n");
					output.write("\t\treturn MSG_PARTNER_" + list.get(i).toUpperCase() +";}\n");
				}
				output.write("\telse{\n");
				output.write("\t\tSystem.out.println(\"ERROR: There is not such a Partner Links.\");\n");
				output.write("\t\treturn MSG_PARTNER_ERROR;}\n\t}\n\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    /**
	     * Generate the Process Analyzer in monitor
	     * using SOAP MSG centred way.
	     * The method can not process the activity flow (fork)
	     * The problem should be solved A.S.A.P
	     * 
	     * @param graph
	     * The method will generate the method called ProcessAnalyzer(int MSGID),
	     * which is only part of the monitor. HOWEVER, it is the core of 
	     * process monitor.
	     */
	    public void GenerateMonitorMSGCentred(IGraph graph){
	    	int	num_P = 0;
//	    	int num_T = 0;
	    	int num_Node = 0;
	    	
	    	String filePath ="D:/WorkSpace/Cases/BPEL2PN/Monitor.java";
	    	String tempStr;
	    	String namePL;
			File Monitorfile = new File(filePath);			
			LOGGER.fine("The generated java file of monitor is " + filePath);
			
		    BufferedWriter output;
			try {
				output = new BufferedWriter(new FileWriter(Monitorfile));
				
				// *******************************
				// * Code generation
				// * Begin to generate annotation and class "ProcessMonitor"
				// *******************************
			    output.write("/**\n");
			    output.write(" * The Class is automatically generated from the Petri net model\n");
			    output.write("* by coloane platform using importBPELImpl.\n");
			    output.write(" * \n");
			    output.write(" * The Classes above can be be reused in other monitors.\n");
			    output.write("* @author ZHU Jun\n");
			    output.write(" */\n");
			    output.write("class ProcessMonitor{\n");
			    
		    	// Find the entry of petri net.
		    	// Usually it is the node of sequence start.
		    	// Ex. P_0_sequence_Start
		    	INode nodeStart = graph.getNode(0);
		    	INode nodeEnd = nodeStart;
	    		int IDMax = 0;
	    		
	    		/*
	    		 * List_MSG is used to store all the SOAP Messages of BPEL Process.
	    		 * And the monitor generation method focuses on these MSG.
	    		 */
	    		ArrayList <INode> List_MSG = new ArrayList<INode>();
	    		/*
	    		 * List_PL (Partner Link) is used to store the name of partner links.
	    		 * It is important to judge the target of arrived messages.
	    		 */
	    		ArrayList <String> List_PL = new ArrayList<String>();
		    	Iterator<INode> iterNode = graph.getNodes().iterator();
		    	LOGGER.fine("GenerateMonitor():Entry!");
		    	num_Node = graph.getNodes().size();

		    	// Do some statistic for the model, in order to generate the monitor 
		    	// Find out the start node and end node
		    	// Calculate the number of place and transition
		    	// And find out the maximum ID of node.
		    	while(iterNode.hasNext()){
		    		INode nodeTemp = (INode) iterNode.next();
		    		if (nodeTemp.getAttribute("name").getValue().endsWith("Start")){
		    	    	nodeStart = nodeTemp;
	//	    			System.out.println("GraphReduction() "+ nodeTemp.getAttribute("name").getValue() + " OutComingArc Size " + nodeTemp.getOutgoingArcs().size());
	//	    			break;
		    		}
		    		else if(nodeTemp.getAttribute("name").getValue().endsWith("Start"))
		    		{
		    			nodeEnd = nodeTemp;
		    		}
		    		else
		    		{
		    			// do nothing;
		    		}
		    		if (nodeTemp.getNodeFormalism().getName().equalsIgnoreCase("place")){
		    			tempStr = nodeTemp.getAttribute("name").getValue();
		    			/*
		    			 * The format of MSG Place should be like this:
		    			 * "P_0_2_InvokeReqRep_Res_employeeTravelStatus_MSG"
		    			 * employeeTravelStatus is the name Partner Links.
		    			 */
		    			if(tempStr.endsWith("MSG")){
		    				List_MSG.add(nodeTemp);
		    				// *******************************
		    				// Code generation
		    				// *******************************
		    				output.write("int " + nodeTemp.getAttribute("name").getValue() + " = " + List_MSG.size()+ ";\n");
		    				

		    				namePL = AnalyzePartnerLinks(tempStr);
		    				if(List_PL.isEmpty()){
		    					List_PL.add(namePL);
//		    					output.write("private static final int MSG_PARTNER_" + namePL + " = " + List_PL.size());
		    				}
		    				else{
		    					boolean isExist = false;
		    					for(int i=0;i<List_PL.size();i++){
		    						if(List_PL.get(i).startsWith(namePL)){
		    							isExist = true;
				    					break;
		    						}
		    					}
		    					if(isExist==false){
		    						List_PL.add(namePL);
//			    					output.write("private static final int MSG_PARTNER_" + namePL + " = " + List_PL.size());
		    					}
		    				}
		    			}
		    			else
		    			{
		    				num_P++;
		    			}
		    		}
		    		
		    		if(IDMax < nodeTemp.getId())
		    		{
		    			IDMax = nodeTemp.getId();
		    		}
		    		
		    	}
		    	
		    	/* 
		    	 * Generate MSG Type Declarations
		    	 */
		    	output.write("/* Definition of SOAP Message Type */\n");
				output.write("private static final int MSG_TYPE_ERROR = -1;\n");
				output.write("private static final int MSG_TYPE_OUT = 1;\n");
				output.write("private static final int MSG_TYPE_IN = 2;\n\n");
		    	
		    	/*
		    	 * Generate MSG Partner Links Declaration
		    	 *
				 * Distill the name of Partner Links from MSG Places
				 * And generate the declarations of partner links in monitor code
				 * 	private static final int MSG_PARTNER_ERROR = -1;
				 *	private static final int MSG_PARTNER_SERVER1 = 1;
				 */
				output.write("/* Definition of Partner Link Services */\n");
				output.write("private static final int MSG_PARTNER_ERROR = -1;\n");
		    	for(int i=0;i<List_PL.size();i++){
		    		output.write("private static final int MSG_PARTNER_" + List_PL.get(i).toUpperCase() + " = " + i + "\n");
		    	}
		    	
		    	/*
		    	 * Generate the function - GenerateFunctionAnalyzeMSGType(output);
		    	 */
		    	GenerateFunctionAnalyzeMSGType(output);
		    	
		    	/*
		    	 * Generate the function - public static int AnalyzeSoapMSGPartner(String linkMSG){}
		    	 */
		    	GenerateFunctionAnalyzePartnerLinks(List_PL,output);
		    	
			    output.write("\t public ProcessMonitor(int ID){\n");
			    output.write("\t\t instanceID = ID;\n\t}\n");
		    	
		    	int [] mapNode = new int[IDMax];
		    	int [] stateVector = new int[num_P];
		    	
		    	// Initialize stateVector
		    	for (int i = 0; i < num_P; i++){
		    		stateVector[i] = 0;
		    	}
		    	
				// *******************************
				// * Code generation
		    	// * Begin to generate FUNC "ProcessAnalyzer"
				// *******************************
		    	output.write("public int  ProcessAnalyzer(int msgID, int msgLink){\n");
			    output.write("switch (stateCurrent) {\n");
		    	
		    	INode t_MSG = nodeStart;
		    	INode p_Succeeding = nodeStart;
		    	INode p_Preceding = nodeStart;
		    	boolean isProcessed = false;
		    	ArrayList<INode> ListProcessNode = new ArrayList<INode>();
		    	for (int i = 0; i < List_MSG.size(); i++){
		    		INode p_MSG = List_MSG.get(i);
		    		
		    		isProcessed = false;
		    		for (int m = 0; m < ListProcessNode.size(); m++)
		    		{
		    			if (ListProcessNode.get(m).equals(p_MSG))
		    			{
		    				isProcessed = true;
		    			}
		    		}
		    		
		    		if (isProcessed == false){
		    			// Find out the transition, which is connected to 
			    		// 
			    		if (p_MSG.getIncomingArcs().size() ==1)
			    		{
			    			t_MSG = p_MSG.getIncomingArcs().get(0).getSource();
			    		}
			    		else
			    		{
			    			t_MSG = p_MSG.getOutgoingArcs().get(0).getTarget();
			    		}
			    		
			    		// Localize the preceding place
			    		
			    		boolean isT_MSG = false;
			    		INode t_temp =t_MSG;
			    		while(isT_MSG == false)
			    		{
				    		p_Preceding = t_temp.getIncomingArcs().get(0).getSource();
				    		if (p_Preceding.getAttribute("name").getValue().endsWith("MSG"))
				    		{
				    			p_Preceding = t_temp.getIncomingArcs().get(1).getSource();
				    		}
				    		if (p_Preceding.getIncomingArcs().size() == 0)
				    		{
				    			// It should be the start node
				    			isT_MSG = true;
				    		}
				    		else
				    		{
				    			t_temp = p_Preceding.getIncomingArcs().get(0).getSource();
				    			if (t_temp.getIncomingArcs().size() > 1 || t_temp.getOutgoingArcs().size() > 1)
				    			{
				    				// Incoming Arcs
				    				if (t_temp.getIncomingArcs().size() > 1){
				    					Iterator iter = t_temp.getIncomingArcs().iterator();
				    					while(iter.hasNext())
				    					{
				    						IArc a_temp = (IArc) iter.next();
				    						if (a_temp.getSource().getAttribute("name").getValue().endsWith("MSG"))
				    						{
				    							isT_MSG = true;
				    						}
				    						else
				    						{
				    							isT_MSG = true;
				    							// do something special for 2P --> T --> P
				    						}
				    					}
				    				}
				    				
				    				// Outcoming Arcs
				    				if (t_temp.getOutgoingArcs().size() > 1){
				    					Iterator<IArc> iter = t_temp.getOutgoingArcs().iterator();
				    					while(iter.hasNext())
				    					{
				    						IArc a_temp = (IArc) iter.next();
				    						if (a_temp.getTarget().getAttribute("name").getValue().endsWith("MSG"))
				    						{
				    							isT_MSG = true;
				    						}
				    					}
				    				}
				    			}
				    		}
			    		}
			    		
			    		// Localize the succeeding place
			    		p_Succeeding = t_MSG.getOutgoingArcs().get(0).getTarget();
			    		if (p_Succeeding.getAttribute("name").getValue().endsWith("MSG"))
			    		{
			    			p_Succeeding = t_MSG.getOutgoingArcs().get(1).getTarget();
			    		}
			    		
			    		LOGGER.fine(t_MSG.toString() + "p_Preceding " + p_Preceding.toString());
			    		LOGGER.fine(t_MSG.toString() + "p_Succeeding " + p_Succeeding.toString());
			    		
			    		// *******************************
						// Code generation
						// *******************************
			    		/*
			    		 * Generate part of code (SWITCH structure) as folows:
			    		 * Part 1
			    		 * *******************************
			    		 * case 0:{
						 * if(msgID==2 && msgLink == 1){
						 * stateCurrent = 1;
						 * System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
						 * break;
					     * }
					     * *******************************
			    		 */
			    		output.write("case " + p_Preceding.getId() + ":\n");
			    		output.write("{ if(msgID == " + p_MSG.getAttribute("name").getValue()+ "){\n");
			    		output.write("stateCurrent = " + p_Succeeding.getId() + ";\n");
			    		output.write("System.out.println(\"Change Current State into \" + stateCurrent);\n");
			    		output.write("break;\n}");

			    		
			    		if (p_Preceding.getOutgoingArcs().size()>1)
			    		{
			    			Iterator<IArc> iterator = p_Preceding.getOutgoingArcs().iterator();
			    			while(iterator.hasNext()){
			    				IArc a_temp = (IArc) iterator.next();
			    				INode nodeTemp = a_temp.getTarget();
			    				String nameMSG = "";
			    				int IDtemp = 0;
			    				if (!nodeTemp.equals(t_MSG)){
			    					if(nodeTemp.getOutgoingArcs().size()==1){
			    						IDtemp = nodeTemp.getOutgoingArcs().get(0).getTarget().getId();
			    						Iterator<IArc> it = nodeTemp.getIncomingArcs().iterator();
			    						while(it.hasNext()){
			    							IArc a_t = (IArc) it.next();
			    							if (!a_t.getSource().equals(p_Preceding))
			    							{
			    								nameMSG = a_t.getSource().getAttribute("name").getValue();
			    								ListProcessNode.add(a_t.getSource());
			    							}
			    						}
			    					}
			    					else
			    					{
			    						nameMSG = nodeTemp.getIncomingArcs().get(0).getSource().getAttribute("name").getValue();
			    						ListProcessNode.add(nodeTemp.getIncomingArcs().get(0).getSource());
			    						Iterator<IArc> it1 = nodeTemp.getOutgoingArcs().iterator();
			    						while(it1.hasNext())
			    						{
			    							IArc a_t1 = (IArc)it1.next();
			    							if (!a_t1.getTarget().getAttribute("name").getValue().endsWith("MSG"))
			    							{
			    								IDtemp = a_t1.getTarget().getId();
			    							}
			    						}
			    					}
						    		// *******************************
									// Code generation
									// *******************************
			    					/*
			    					 * Generate part of code  (SWITCH structure) as folows:
			    					 * Part 2
			    					 * *******************************
			    					 * else if(msgID==2 && msgLink == 1){
									 * stateCurrent = 2;
								     * 	System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
								     * 	break;
								     * }
								     * *******************************
			    					 */
			    					output.write("else if(msgID == " + nameMSG+ "){\n");
			    					output.write("stateCurrent = " + IDtemp + ";\n");
			    		    		output.write("System.out.println(\"Change Current State into \" + stateCurrent);\n");
			    		    		output.write("break;\n}\n}\n");
			    		    		
			    				}
			    			}
			    			
			    		}
			    		else
			    		{
				    		// *******************************
							// Code generation
							// *******************************
			    			/*
			    			 * Generate part of code (SWITCH structure) as folows:
			    			 * Part 3
			    			 * *******************************
			    			 * else{
						     * 	return stateCurrent;
						     * }
						     * }
						     * *******************************
			    			 */
				    		output.write("\nelse\n{\n");
				    		output.write("return stateCurrent;\n}\n}\n");
			    		}
		    		}
		    	}
		    	
		    	// *******************************
				// Code generation
				// *******************************
		    	output.write("\ndefault:\n{\nreturn stateCurrent;\n}\n}\nreturn E_Normal;\n}");
		    	
			    output.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    }
	    
	    public void GenerateMonitor1(IGraph graph){
	    	int	num_P = 0;
	    	int num_T = 0;
	    	int num_Node = 0;
	    	
	    	String filePath ="D:/WorkSpace/Cases/BPEL2PN/Monitor.java";
			File Monitorfile = new File(filePath);			
			LOGGER.fine("The generated java file of monitor is " + filePath);
			
		    BufferedWriter output;
			try {
				output = new BufferedWriter(new FileWriter(Monitorfile));
				
			    output.write("hello");
			    //output.write(s1);
	    	
	    	
		    	// Find the entry of petri net.
		    	// Usually it is the node of sequence start.
		    	// Ex. P_0_sequence_Start
		    	INode nodeStart = graph.getNode(0);
		    	INode nodeEnd = nodeStart;
	    		int IDMax = 0;
		    	Iterator<INode> iterNode = graph.getNodes().iterator();
		    	LOGGER.fine("GenerateMonitor():Entry!");
		    	num_Node = graph.getNodes().size();

		    	// Do some statistic for the model, in order to generate the monitor 
		    	// Find out the start node and end node
		    	// Calculate the number of place and transition
		    	// And find out the maximum ID of node.
		    	while(iterNode.hasNext()){
		    		INode nodeTemp = (INode) iterNode.next();
		    		if (nodeTemp.getAttribute("name").getValue().endsWith("Start")){
		    	    	nodeStart = nodeTemp;
	//	    			System.out.println("GraphReduction() "+ nodeTemp.getAttribute("name").getValue() + " OutComingArc Size " + nodeTemp.getOutgoingArcs().size());
	//	    			break;
		    		}
		    		else if(nodeTemp.getAttribute("name").getValue().endsWith("Start"))
		    		{
		    			nodeEnd = nodeTemp;
		    		}
		    		else
		    		{
		    			// do nothing;
		    		}
		    		if (nodeTemp.getNodeFormalism().getName().equalsIgnoreCase("place")){
		    			num_P++;
		    		}
		    		
		    		if(IDMax < nodeTemp.getId())
		    		{
		    			IDMax = nodeTemp.getId();
		    		}
		    		
		    	}
		    	num_T = num_Node - num_P;
		    	
		    	int [] mapNode = new int[IDMax];
		    	int [] stateVector = new int[num_P];
		    	
		    	// Initialize stateVector
		    	for (int i = 0; i < num_P; i++){
		    		stateVector[i] = 0;
		    	}
		    	
//		    	int state = 0;
//		    	int stateNext = 0;
		    	int countNode = 0;
		    	
		    	INode temp = nodeStart;
	    		// record current state
		    	mapNode[nodeStart.getId()] = 0;
		    	stateVector[countNode] = 1;
		    	countNode++;
		    	int num_outcomingArc = temp.getOutgoingArcs().size();
		    	while(num_outcomingArc != 0)
		    	{
		    		if(num_outcomingArc == 1)
		    		{
		    			INode temp_T = temp.getOutgoingArcs().get(0).getTarget();
		    			if (temp_T.getOutgoingArcs().size() == 1)
		    			{
		    				
		    			}
		    			else if (temp_T.getOutgoingArcs().size() > 1)
		    			{
		    				output.write("case "+ BinaryToLong(stateVector, num_P) + ":");
		    			}
		    			else
		    			{
		    				//do nothing;
		    			}
		    		}
		    		else if(num_outcomingArc > 1)
		    		{
		    			output.write("// ");
		    			output.write("case "+ BinaryToLong(stateVector, num_P) + ":");
		    		}

		    	}
	    	
			    
			    
			    output.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	    
	    public long BinaryToLong(int[] bin, int len){
	    	double tempResult = 0;
	    	for (int i = 0; i < len; i++){
	    		tempResult= tempResult + Math.pow(2, i);
	    	}
	    	return (long)tempResult;
	    }
	    
}
