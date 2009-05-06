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
import fr.lip6.move.coloane.core.model.NodeModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;


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
//			GraphReduction(graph);

		  
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
		    	IGraph tempGraphA = new GraphModel(formalism);
		    	Iterator iter = element.elementIterator();
		    	String tempString, nodeName;
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
					    	   
					    	   if (nodeTemp.getOutcomingArcs().size()!= 0)
					    	   {
					    		   // ********************************************************
					    		   // * This is a very strange problem
					    		   // * numOutcomingArc = nodeTemp.getOutcomingArcs().size()/2
					    		   // * why size()/2 is right?????
					    		   // ********************************************************
					    		   int numOutcomingArc = nodeTemp.getOutcomingArcs().size()/2;
//					    		   System.out.println(nodeTemp.getAttribute("name").getValue() + " nodeTemp.getOutcomingArcs().size() is " + numOutcomingArc);
					    		   
					    		   for (int count = 0; count<numOutcomingArc; count++){
					    			   INode T_temp = nodeTemp.getOutcomingArcs().get(count).getTarget();
					    			   IArc A_temp = graph.createArc("arc", P_temp, T_temp);
					    			   A_temp.getAttribute("valuation").setValue("Reduce_" + StrPrint + "_" + nodeTemp.getAttribute("name").getValue());
//					    			   System.out.println("getTarget() times-" + count + " "+T_temp.getAttribute("name").getValue());
					    		   }
					    		   
					    		   // delete the original arcs
					    		   for (int count = 0; count<numOutcomingArc; count++){
//					    			   System.out.println("Delete Arc times-" + count);
					    			   graph.deleteArc(nodeTemp.getOutcomingArcs().get(count));
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

		    		   tempGraph = GenerateReceive(formalism, level, numArray);
		    		   
		    	   }
		    	   else if((tempString.equalsIgnoreCase("invoke"))){
		    		   if(elem.attribute("outputVariable") == null){
		    			   // One-way invoke
		    			   System.out.println("One-Way invoke");
		    			   tempGraph = GenerateInvokeOneway(formalism, level, numArray);
		    		   }
		    		   else{
		    			   // Request-Response invoke
		    			   System.out.println("Request-Response invoke");
		    			   tempGraph = GenerateInvokeReqrep(formalism, level, numArray);
		    		   }
		    	   }
		    	   else if((tempString.equalsIgnoreCase("reply"))){
		    		   tempGraph = GenerateReply(formalism, level, numArray);
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
	    public IGraph GenerateReceive(String formalism, int level, int [] numArray){
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
		    	attribute.setValue("Arc_"+StrPrint+"Receive_In");
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"Receive_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Receive_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", P_Msg_Rec, T_transition);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"Receive_MSG");
		    	
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
	    public IGraph GenerateInvokeOneway(String formalism, int level, int [] numArray){
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
		    	attribute.setValue("Arc_"+StrPrint+"InvokeOneWay_In");
		    	
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"InvokeOneWay_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeOneWay_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", T_transition, P_Msg_Rec);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"InvokeOneWay_MSG");
		    	
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
	    public IGraph GenerateInvokeReqrep(String formalism, int level, int[] numArray){
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
		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_In");
		    	
		    	//Node Msg Req
		    	INode P_Msg_Req = graph.createNode("place");
		    	attribute = P_Msg_Req.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_MSG_Req");
		    	nodeGraphInfo = P_Msg_Req.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// arc between transition(req) and MSG(req)
		    	arc = graph.createArc("arc", T_transition, P_Msg_Req);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_MSG_Req");
		    	
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
		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_P_Mid");
		    	
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
		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_P_Mid");
		    	
		    	// Msg (Response)
		    	INode P_Msg_Res = graph.createNode("place");
		    	attribute = P_Msg_Res.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"InvokeReqRep_MSG_Res");
		    	nodeGraphInfo = P_Msg_Res.getGraphicInfo();
		    	point.setLocation(x+100, y+100);
		    	nodeGraphInfo.setLocation(point);
		    	
		    	// Arc between transition (Response) and Msg (Response)
		    	arc = graph.createArc("arc", P_Msg_Res, T_transition);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_MSG_Res");
		    	
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
		    	attribute.setValue("Arc_"+StrPrint+"InvokeReqRep_Out");
		    	
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
	    public IGraph GenerateReply(String formalism, int level, int [] numArray){
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
		    	attribute.setValue("Arc_"+StrPrint+"Reply_In");
		    	
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"Reply_Out");
		    	
				INode P_Msg_Rec = graph.createNode("place");
		    	attribute = P_Msg_Rec.getAttribute("name");
		    	attribute.setValue("P_"+StrPrint+"Reply_MSG");
		    	nodeGraphInfo = P_Msg_Rec.getGraphicInfo();
		    	point.setLocation(x+50, y+50);
		    	nodeGraphInfo.setLocation(point);
		    	
				arc = graph.createArc("arc", T_transition, P_Msg_Rec);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"Reply_MSG");
		    	
		    	
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
		    	attribute.setValue("Arc_"+StrPrint+"Assign_In");
		    	
		    	// Arc between T_transition and P_End
				arc = graph.createArc("arc", T_transition, P_End);
		    	attribute = arc.getAttribute("valuation");
		    	attribute.setValue("Arc_"+StrPrint+"Assign_Out");
		    	
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
			    	attribute.setValue("Arc_"+StrPrint+"Flow_In");
			    	
			    	
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
			    	attribute.setValue("Arc_"+StrPrint+"Flow_Out");
			    	
			    	// Initialization
			    	nodeStartFlow = P_Start_Flow;
			    	nodeEndFlow = P_End_Flow;
			    	
		 			level++;
		 				   
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
		 			   Element elemTemp =(Element)iterTemp.next();

		 			   tempGraphA = BPELPNModelGenerator(elemTemp, formalism, level, numArray);
		 			   tempGraph.addGraph(tempGraphA);
		 			   numArray[level]++;
	 			   
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
		   
	    	Element elemTemp;
	    	// Calculate the UNIQUE ID of Place and Transition.
			String StrPrint = "";
			for(int n=0; n<=level; n++){
				StrPrint = StrPrint.concat(Integer.toString(numArray[n])+"_");
			}
	    	
		   	IAttribute attribute;
		   	try {
		   			// The first input place of 'PICK' sub model
					INode P_Start_Pick = tempGraph.createNode("place");
			    	
		    		// The last place of 'PICK' sub model
					INode P_End_Pick = tempGraph.createNode("place");
			    	
				   	INode nodeStartPick = P_Start_Pick;
				   	INode nodeEndPick = P_End_Pick;
					
					level++;
					
			    	Iterator iterTemp = elem.elementIterator();
			    	while(iterTemp.hasNext()){
				    	elemTemp =(Element)iterTemp.next();
				    	LOGGER.fine("elemTempNext is " + elemTemp.getName()); 
				    	
				    	
				    	Iterator iterTemp1 = elemTemp.elementIterator();
				    	Element e1 = (Element)iterTemp1.next();
				    	System.out.println("iterTemp1 is " + e1.getName());
				    	
				    	tempGraphA = BPELPNModelGeneratorEach(e1, formalism, level, numArray);
				    	tempGraph.addGraph(tempGraphA);
				    	numArray[level]++;
			    		
		 			   // Add the connection between the sub model and parent model.
				    	Iterator iterNodePick = tempGraph.getNodes().iterator();
				    	while(iterNodePick.hasNext()){
				    		   INode nodePick = (INode) iterNodePick.next();
				    		   IAttribute tempAttr = nodePick.getAttribute("name");
				    		   String nodeName = tempAttr.getValue();
				    		   
				    		   LOGGER.fine("nodeName is " + nodeName); 
				    		   
				    		   if (nodeName.endsWith("Start")){
				    			   nodeStartPick = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
//				    			   System.out.println("Start is "+nodeName);
				    		   }
				    		   else if (nodeName.endsWith("End")){
				    			   nodeEndPick = nodePick;
				    			   tempAttr.setValue(nodeName+"_0");
//				    			   System.out.println("End is "+nodeName);
				    		   }
				    		   else{
				    			   // do nothing
				    		   }
				    	}
				    	
				    	// Connect Pick branch A with Pick self (up)
				    	INode T_Pick_A_Up = tempGraph.createNode("transition");
				    	tempGraph.createArc("arc", P_Start_Pick, T_Pick_A_Up);
				    	tempGraph.createArc("arc", T_Pick_A_Up, nodeStartPick);
//				    	System.out.println("T_Pick_A_Up 2 arc! " + nodeStartPick.getAttribute("name").getValue());
				    	
				    	// Connect Pick branch A with Pick self (down)
				    	INode T_Pick_A_Down = tempGraph.createNode("transition");
				    	tempGraph.createArc("arc", nodeEndPick, T_Pick_A_Down);
				    	tempGraph.createArc("arc", T_Pick_A_Down, P_End_Pick);
//				    	System.out.println("T_Pick_A_Down 2 arc! " + nodeStartPick.getAttribute("name").getValue());
				    	
				    	T_Pick_A_Up.getAttribute("name").setValue("T_" + StrPrint + "Start" + elemTemp.getName());
				    	T_Pick_A_Down.getAttribute("name").setValue("T_" + StrPrint + "End" + elemTemp.getName());
			    	}
			    	
			    	// ***********************************************
			    	// In order to implement the connection of submodels
			    	// by identifying the name of places or transitions.
			    	// Check whether they end with "start" or "end".
			    	// ************************************************
			    	// Set the name of place 'P_Start_Pick'
			    	attribute = P_Start_Pick.getAttribute("name");
			    	attribute.setValue("P_"+StrPrint+"_Pick_Start");
			    	System.out.println("P_Start_Pick has " + P_Start_Pick.getOutcomingArcs().size());
			    	
			    	// Set the name of place 'P_End_Pick'
			    	attribute = P_End_Pick.getAttribute("name");
			    	attribute.setValue("P_"+StrPrint+"_Pick_End");
			    	
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
		   
	    	int num_count = 0;
	    	
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
				    	Iterator iterNodeSwitch = tempGraph.getNodes().iterator();
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
	    	
	    	int num_A = 0;
	    	// Define the Matrix
	    	// Use array as incidence matrix,
	    	// for the consideration of runtime monitor efficiency. 
	    	String[][] MatrixPre = new String[num_T_Max][num_P_Max];
	    	String[][] MatrixPost = new String[num_T_Max][num_P_Max];
	    	String [] Matrix_T_Name = new String[num_T_Max];
	    	String [] Matrix_P_Name = new String[num_P_Max];
	    	
	    	String tempName = null;
	    	
	    	int size_graph = graph.getNodes().size();
	    	Iterator iterNode = graph.getNodes().iterator();
	    	
	    	// As for every transition,
	    	// calculate every arc of this transition.
	    	// And add to the incidence matrix.
	    	while(iterNode.hasNext()){
	    		INode nodeTemp = (INode) iterNode.next();
	    		if(nodeTemp.getNodeFormalism().getName().equalsIgnoreCase("transition")){
	    			Matrix_T_Name[num_T] = nodeTemp.getAttribute("name").getValue();
	    			Iterator incomingArc = nodeTemp.getIncomingArcs().iterator();
	    			
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
	    			Iterator OutcomingArc = nodeTemp.getOutcomingArcs().iterator();
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
		   * Reduce the graph (petri net model)
		   * Try to eliminate the redundant places and transitions like,
		   * P->T->P->T  reduce to P->T
		   * 
		   * @param graph The original graph (petri net model)
		   * @return graph, which is ruduced
		   */
	    public void GraphReduction(IGraph graph){
	    	
	    	Iterator iterNode = graph.getNodes().iterator();
	    	
	    	// Find the entry of petri net.
	    	// Usually it is the node of sequence start.
	    	// Ex. P_0_sequence_Start
	    	System.out.println("Entry of GraphReduction!");
	    	while(iterNode.hasNext()){
	    		INode nodeTemp = (INode) iterNode.next();
	    		if (nodeTemp.getAttribute("name").getValue().endsWith("Start")){
	    	    	GraphReduction(graph, nodeTemp);
	    			break;
	    		}
	    	}
	    	
//	    	return graph;
	    }
	    
		  /**
		   * Reduce the graph (petri net model)
		   * Try to eliminate the redundant places and transitions like,
		   * P->T->P->T  reduce to P->T
		   * 
		   * @param graph The original graph (petri net model)
		   * @return graph, which is ruduced
		   */
	    public void GraphReduction(IGraph graph, INode node){
	    	
	    	INode nodeCurrent = node;
	    	INode nodeTemp = node;
	    	INode nodeTempPrevious = node;
	    	
	    	int lengthReduce = 0;
	    	int numOutcomingArcs = 0;
	    	int numIncomingArcs = 0;
	    	// Check Next Node
	    	
	    	numOutcomingArcs = nodeCurrent.getOutcomingArcs().size();
	    	System.out.println("Entry of GraphReduction(IGraph graph, INode node)! OutcomingArc size - " + numOutcomingArcs);
		    	
		    	if(numOutcomingArcs == 0)
		    	{
		    		return;// graph;
		    	}
		    	else if(numOutcomingArcs == 1)
		    	{
		    		//nodeTemp = node.getOutcomingArcs().get(0).getTarget();
		    		while(nodeTemp.getOutcomingArcs().size()==1 && nodeTemp.getIncomingArcs().size()==1)
		    		{
		    			nodeTempPrevious = nodeTemp;
		    			nodeTemp = nodeTemp.getOutcomingArcs().get(0).getTarget();
		    			lengthReduce++;
		    		}
		    		// When lengthReduce > 1,
		    		// some places and transitions can be reduced.
		    		
		    		System.out.println("GraphReduction(IGraph graph, INode node): lengthReduce is " + lengthReduce);
		    		if(lengthReduce > 1)
		    		{
		    			System.out.println("Find redundant places and transitions!");
			    		if(lengthReduce%2==0){
			    			// Even integer
			    			IArc arcTemp;
							try {
								arcTemp = graph.createArc("arc", nodeCurrent, nodeTemp);
								arcTemp.getAttribute("name").setValue("Reduce_" + nodeCurrent.getAttribute("name").getValue()+ "_" + nodeTemp.getAttribute("name").getValue());
							} catch (ModelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    			
			    			
			    			// Delete Redundant Arcs and Nodes.
			    			IArc arcNext = nodeCurrent.getOutcomingArcs().get(0);
			    			// i<=lengthReduce
			    			for (int i=0;i<=lengthReduce;i++){
			    				IArc arcDelete = arcNext;
			    				INode nodeDelete = arcDelete.getTarget();
			    				arcNext = nodeDelete.getOutcomingArcs().get(0);
			    				
			    				graph.deleteArc(arcDelete);
			    				graph.deleteNode(nodeDelete);
			    			}
			    			graph.deleteArc(arcNext);
			    		}
			    		else
			    		{
			    			IArc arcTemp;
							try {
								arcTemp = graph.createArc("arc", nodeCurrent, nodeTempPrevious);
								arcTemp.getAttribute("name").setValue("Reduce_" + nodeCurrent.getAttribute("name").getValue()+ "_" + nodeTempPrevious.getAttribute("name").getValue());
					    		
							} catch (ModelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    			
			    			// Delete Redundant Arcs and Nodes.
			    			IArc arcNext = nodeCurrent.getOutcomingArcs().get(0);
			    			// i<lengthReduce
			    			for (int i=0;i<lengthReduce;i++){
			    				IArc arcDelete = arcNext;
			    				INode nodeDelete = arcDelete.getTarget();
			    				arcNext = nodeDelete.getOutcomingArcs().get(0);
			    				
			    				graph.deleteArc(arcDelete);
			    				graph.deleteNode(nodeDelete);
			    			}
			    			graph.deleteArc(arcNext);
			    		}
		    		}
		    		nodeCurrent = nodeTemp;
		    		GraphReduction(graph, nodeCurrent);
		    	}
		    	else
		    	{
		    		Iterator iterArc = nodeCurrent.getOutcomingArcs().iterator();
		    		while(iterArc.hasNext()){
		    			IArc arcTemp = (IArc) iterArc.next();
		    			if(!arcTemp.getTarget().getAttribute("name").getValue().endsWith("MSG"))
		    			{
		    				GraphReduction(graph, arcTemp.getTarget());
		    			}
		    		}
		    	}
	    	
//	    	return graph;
	    }
	    
	    // If want to use IGraph,
	    // it is required to run the application as Eclipse Application.
//		  public static void main(String[] args) {
//				/**
//				 * 测试文件列表： Test case list:
//				 * catalog2.xml
//				 * Travel.bpel
//				 */
//			  
//			  	ImportFromImpl  TestCase = new ImportFromImpl();
//			  	String formalism = "CPN";
//			  	String filePath = "D:/WorkSpace/Cases/BPEL2PN/DemoTestCase/DemoTestCase(Sequence).bpel";
//				File BPELfile = new File(filePath);
//				System.out.println("The import BPEL XMLfiles is " + filePath);
//				IGraph graph = new GraphModel(formalism);
//				  
//				try {
//					Document docment = TestCase.parseWithSAX(BPELfile);
//				  
//					// The basic utility of Class IGraph and other related class
//					graph = TestCase.BPELPNModelGenerator(docment,formalism);
//
//				} catch (DocumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				String [][] matrix = TestCase.GenerateIncidenceMatrix(graph);
//				System.out.println("matrix[0][0] is "+matrix[0][0]);
//	    }
	    
}
