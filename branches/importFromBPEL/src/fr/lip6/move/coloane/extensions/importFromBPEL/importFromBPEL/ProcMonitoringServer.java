package fr.lip6.move.coloane.extensions.importFromBPEL.importFromBPEL;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.*;
//import javax.realtime.HighResolutionTime;


import java.io.PipedInputStream;

/**
 * BPEL Process Monitor Sever Class
 * Receive SOAP Messages, analyze these messages,
 * dispatch messages to related monitor thread by its process(target service and message type).
 * @author ZHU Jun
 *
 */
public class ProcMonitoringServer {
	private static final int CORE_POOL_SIZE = 2;
	private static final int MAX_POOL_SIZE = 100;
	//private ThreadPoolExecutor serverThreadPool = null;
	
	
	/*
	 * Define the Maximum amount of MSG in
	 * each BPEL Process.
	 */
	private static final int MAX_AMOUNT_MSG = 100;
	
	private ExecutorService pool = null;
	
	/**
	 * The principal function of class ProcMonitoringServer
	 * It is used to receive messages, analyze messages,
	 * and then dispatch these messages to specific monitor thread.
	 * By using pipes, the communications between server thread and monitor
	 * thread are implemented.
	 * 
	 * IN ORDER TO INCREASE THE PERFORMANCE OF MONITOR, THE FOLLOWING IMPROVEMENTS
	 * CAN BE DONE:
	 * 1). IMPROVE THE PIPE MECHANISM. USE OTHER EFFECTIVE WAYS;
	 * 2). MAPPING TABLE SHOULD BE KEEP SMALL, SO AS TO REDUCE THE SEARCH TIME.
	 * (RIGHT NOW, ALL THE MAPPING ITEMS ARE RECORDED IN THE LIST, WITHOUT ANY REMOVE
	 * AFTER PROCESS ENDS.)
	 * OR USE OTHER EFFECTIVE DATA STRUCTURE TO HANDLE THE MAPPING TALBE.
	 */
	public void start() {
		DataOutputStream tempPos = null;
		int typeMSG = -1;
		int linkMSG = -1;

		long timeBegin =0;
		
		// You can also init thread pool in this way.
		/*serverThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAX_POOL_SIZE, KEEPALIVE_TIME, TIME_UNIT, workQueue,
				rejectedExecutionHandler);*/
		pool = Executors.newFixedThreadPool(10);
		ArrayList<ItemProcessThread> tablePT = new ArrayList<ItemProcessThread>();
		
		System.out.println("BPEL Process Monitor: I'm receiving SOAP messages...");
        try {
            FileReader fr = new FileReader("e:/SOAPMSGQueueFile(travel).txt");//创建FileReader对象，用来读取字符流
            BufferedReader br = new BufferedReader(fr);    //缓冲指定文件的输入
            String procID;    //the Process ID of MSG
            String objectService;	//where the MSG come from or go to
            String MSGType;			//The type of MSG (usually just 'in' and 'out')
            while (br.ready()) {
            	procID = br.readLine();//Read procID
//                System.out.println(procID);//out put in console
                if(br.ready()) {
                	objectService = br.readLine();//read service
//	                System.out.println(objectService);//out put in console
                }
                else{
                	System.out.println("ERROR in the MSG input file(MSG not in pair).");
                	break;
                }
                if(br.ready()) {
                	MSGType = br.readLine();//read MSGType
//	                System.out.println(MSGType);//out put in console
                }
                else{
                	System.out.println("ERROR in the MSG input file(MSG not in pair).");
                	break;
                }

                // Maybe it is necessary to add some time of waiting (MSG time property)
                // Because not MSGs arrive in random.
                
//            	SoapMSG m = new SoapMSG(procID, objectService, MSGType); 
                
                /*
                 * Time Measurements for Monitor Performance
                 * Record the beginning time  (BEGIN From receiving the SOAP message)
                 */
                timeBegin = System.nanoTime();
//                System.out.println("Begin:" + timeBegin);
                
                // Analyze the SOAP message,
                // translate the MSG type into int.
                typeMSG = ProcessMonitorForTest.AnalyzeSoapMSGTYPE(MSGType);
                linkMSG = ProcessMonitorForTest.AnalyzeSoapMSGPartner(objectService);
                
                // According to each MSG, check if it belongs to any existing monitor:
                // if yes, then send this MSG to related monitor thread.
                // if no, then new a new monitor thread.
                if(tablePT.isEmpty()){
//                	System.out.println("tablePT.isEmpty()");
                	/*
                	 * Use Class DataOutputStream to encapsulate the Pipe Class
                	 * PipedOutputStream, so that many flexible function, such as
                	 * writeInt(),writeLong(), can be used.
                	 */
                	PipedInputStream pis = new PipedInputStream();
                	PipedOutputStream pos = new PipedOutputStream(pis);
                	DataOutputStream in = new DataOutputStream(pos);
                	
                	in.writeInt(typeMSG);
                	in.writeInt(linkMSG);
                	in.writeLong(timeBegin);
//                	System.out.println("write write write write" + typeMSG + linkMSG);
                	
                	ServiceThread newMonitorThread = new ServiceThread(Integer.parseInt(procID),pis);
                	pool.execute(newMonitorThread);
                	ItemProcessThread tempItem = new ItemProcessThread(Integer.parseInt(procID),newMonitorThread, in);
                	tablePT.add(tempItem);
//                	pos.flush();
                }
                else
                {
                	boolean isExisting=false;
                	int indexMSG=0;
                	// find the item with processID in tablePT
                	for(int i=0;i<tablePT.size();i++){
                		ItemProcessThread tempItem = tablePT.get(i);
                		if(procID.startsWith(Integer.toString(tempItem.getBPELProcessID()))){
                			isExisting=true;
                			indexMSG=i;
                			break;
                		}
                	}
                	if(isExisting==true){
                		// find out the related mapping item
                		// and then send the MSG to the related thread NO.$indexMSG
//                		System.out.println("isExisting==true");
                		tempPos = tablePT.get(indexMSG).getpOutput();
                		
                		tempPos.writeInt(typeMSG);
                		tempPos.writeInt(linkMSG);
                		tempPos.writeLong(timeBegin);
//                		System.out.println("timeBegin: "+timeBegin);
                		/*
                		 * In order to solve the problem of EXCEPTION "WRITE END DEAD"
                		 * it is required to close the pipe before ending the thread.
                		 * So if I can get the end of each process, it would be much better.
                		 * 
                		 * Right now, by judging the MSGType == out?? and objectService == client
                		 * it is determined whether it is the end of process.
                		 * ACCTUALLY IT IS NOT A CORRECT WAY TO SOLVE THIS PROBLEM.
                		 */
                		/*
                		 * Temprally for test
                		 */
                		
//                		if(MSGType.startsWith("out") && objectService.startsWith("client")){
//                			tempPos.close();
//                			// Display the pipe has been closed.
////                			System.out.println(tempPos + " end pipes.");
//                		}
                		
//                		System.out.println("write write write write" + typeMSG + linkMSG);
                	}
                	else{
                		// A new process is created
                		// so it is necessary to create a new monitor thread.
//	                	System.out.println("isExisting!!=true");
	                	PipedInputStream pis = new PipedInputStream();
	                	PipedOutputStream pos = new PipedOutputStream(pis);
	                	DataOutputStream in = new DataOutputStream(pos);

	                	in.writeInt(typeMSG);
	                	in.writeInt(linkMSG);
	                	in.writeLong(timeBegin);
	                	
	                	ServiceThread newMonitorThread = new ServiceThread(Integer.parseInt(procID),pis);
	                	pool.execute(newMonitorThread);
	                	ItemProcessThread tempItem = new ItemProcessThread(Integer.parseInt(procID),newMonitorThread, in);
	                	tablePT.add(tempItem);

                	}	                	
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String args[]) {
		ProcMonitoringServer server = new ProcMonitoringServer();
		server.start();
	}
}

// Monitor Thread
class ServiceThread implements Runnable{
	
	private SoapMSG Msg = null;
	final	static	int  MSG_SEND = 1; 		// Define send type of MSG
	final	static	int  MSG_RECEIVE = 2;	// Define receive type of MSG
	ProcessMonitorForTest testCase = null;
	DataInputStream pInput = null;
	int instanceID = -1;
	
	ServiceThread(int ID, PipedInputStream input) {
		instanceID = ID;
		pInput = new DataInputStream(input);
//		System.out.println("New ServiceThread");
	}
	
	/**
	 * This function is used to print content (type byte[])
	 * @param content
	 */
	public void print(byte[] content){
		   for(int i=0;i<content.length;i++){
		    System.out.print((char)content[i]);
		   }
		   System.out.println();
	}
	
	public void setSoapMSG(SoapMSG m){
		Msg = m;
	}
	
	public void run(){
		testCase = new ProcessMonitorForTest(instanceID);
		int typeMsg = -1;
		int serviceMsg = -1;
		long timeBegin = 0;

		try {
//			System.out.println("pInput.available() = "+ pInput.available());
			typeMsg = this.pInput.readInt();
			serviceMsg = this.pInput.readInt();
			timeBegin = this.pInput.readLong();
//			System.out.println("RECEIVE:" + typeMsg + "  "+ serviceMsg + "  " + timeBegin);
			
			while(serviceMsg!=-1){
				testCase.monitor(typeMsg,serviceMsg);
				
				System.out.println(timeBegin + "  " + System.nanoTime());
				typeMsg = this.pInput.readInt();
				serviceMsg = this.pInput.readInt();
				timeBegin = this.pInput.readLong();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
class TimeConsumingTask implements Callable<String> {
	public String call() throws Exception {
		System.out
				.println("It's a 	time-consuming task, you'd better retrieve your result in the furture");
		return "ok, here's the result: It takes me lots of time to produce this result";
	}
}

class ItemProcessThread{
	private int BPELProcessID=0;
	private ServiceThread ThreadObject = null;
	private DataOutputStream pOutput = null;
	
	public ItemProcessThread(int PID, ServiceThread threadObject, DataOutputStream output){
		BPELProcessID = PID;
		ThreadObject = threadObject;
		pOutput = output;
	}
	
	public int getBPELProcessID(){
		return BPELProcessID;
	}
	
	public ServiceThread getThreadObject(){
		return ThreadObject;
	}
	
	public DataOutputStream getpOutput(){
		return pOutput;
	}
	
	public void setpOutput(DataOutputStream output){
		pOutput = output;
	}
	
	public void setBPELProcessID(int ID){
		BPELProcessID=ID;
	}
	
	public void setThreadID(ServiceThread threadObject){
		ThreadObject=threadObject;
	}
}

class SoapMSG{
	private String procID="00000";    //the Process ID of MSG
	private	String objectService="00000";	//where the MSG come from or go to
	private	String MSGType="00000";			//The type of MSG (usually just 'in' and 'out')
	
	public SoapMSG(String PID, String objS, String type){
		procID = PID;
		objectService = objS;
		MSGType = type;
	}
	
	public void setSoapMSG(String PID, String objS, String type){
		procID = PID;
		objectService = objS;
		MSGType = type;
	}
	
	public String getObjectService(){
		return objectService;
	}
	
	public String getMSGType(){
		return MSGType;
	}
	
	public String getProcID(){
		return procID;
	}
}

/**
 * The Class should be automatically generated from the Petri net model
 * by coloane platform using importBPELImpl.
 * 
 * The Classes above can be be reused in other monitors.
 * @author ZHU Jun
 *
 */
class ProcessMonitor{	
	// Define the Incidence Matrix of Petri Nets
	// in a vector Matrix[][]
	// ** Monitor Generation **
	// It is required to define the incidence matrix 
	// with varibles m and n to define the matrix.
	// ## Monitor Generation ##
	
	// Define ProcessAnalyzer return Event type
	final   static	int  E_Normal = -1;		// Event: Normal Execution
	final 	int  E_Exception = 1;	// Event: Exception happens
	
	final	static	int  MSG_Receive = 0;
	final	static	int  MSG_InvokeOneWay = 1;
	final	static	int  MSG_2_Receive = 2;
	final	static	int  MSG_InvokeReqRep_Req =3;
	final	static	int  MSG_InvokeReqRep_Res = 4;
	
	/**
	 * Definition of SOAP Message Type
	 */ 
	private static final int MSG_TYPE_ERROR = -1;
	private static final int MSG_TYPE_OUT = 1;
	private static final int MSG_TYPE_IN = 2;
	
	/**
	 * Definition of Partner Link Services
	 */
	private static final int MSG_PARTNER_ERROR = -1;
	private static final int MSG_PARTNER_SERVER1 = 1;
	private static final int MSG_PARTNER_SERVER2 = 2;
	private static final int MSG_PARTNER_CLIENT = 3;
	
	/**
	 * Important Variables
	 * instanceID: (BPEL instance ID);
	 * stateCurrent: (current state of corresponding BPEL instance)
	 */
	private int instanceID = -1;
	private int stateCurrent = 0;
	
	/**
	 * Definition of Class Constructor 
	 */
	public ProcessMonitor(int ID){
		instanceID = ID;
	}
	
	public void setStateCurrent(int state){
		stateCurrent = state;
	}
	
	/**
	 * Static function AnalyzeSoapMSGTYPE
	 * analyze the SOAP Message
	 * @param typeMSG
	 * @return
	 */
	public static int AnalyzeSoapMSGTYPE(String typeMSG){
		if(typeMSG.startsWith("out")){
			return MSG_TYPE_OUT;
		}
		else if(typeMSG.startsWith("in")){
			// Right now there are two MSG types (out & in)
			// Actually, just input or output (two directions of MSGs)
			return MSG_TYPE_IN;
		}
		else{
			// There is not such a MSG tpye.
			System.out.println("ERROR: There is not such a MSG tpye.");
			return MSG_TYPE_ERROR;
		}
	}
	
	
	/**
	 * Static function SoapMSGTYPEID2String
	 * analyze the SOAP Message Type ID.
	 * Translate it into corresponding String.
	 * @param typeMSG
	 * @return MSGTYPE String
	 */
	public static String SoapMSGTYPEID2String(int typeMSG){
		switch(typeMSG){
			case MSG_TYPE_OUT:
			{
				return "out";
			}
			case MSG_TYPE_IN:
			{
				return "in";
			}	
			default:
			{
				return "ERROR";
			}
		}
	}
	
	
	/**
	 * Static function AnalyzeSoapMSGPartner
	 * Analyze the Partner Links into integers.
	 * @param linkMSG
	 * @return
	 */
	public static int AnalyzeSoapMSGPartner(String linkMSG){
		if(linkMSG.startsWith("Server1")){
			return MSG_PARTNER_SERVER1;
		}
		else if(linkMSG.startsWith("Server2")){
			// Right now there are two Partner Links(server1 & server2)
			return MSG_PARTNER_SERVER2;
		}
		else if(linkMSG.startsWith("client")){
			// Right now there are two Partner Links(server1 & server2)
			return MSG_PARTNER_CLIENT;
		}
		else{
			// There is not such a Partner Links.
			System.out.println("ERROR: There is not such a Partner Links.");
			return MSG_PARTNER_ERROR;
		}
	}
	
	
	/**
	 * Static function AnalyzeSoapMSGPartner
	 * Analyze the Partner Links into integers.
	 * @param linkMSG
	 * @return
	 */
	public static String SoapMSGPartner2String(int plinkMSG){
		switch(plinkMSG){
			case MSG_PARTNER_SERVER1:
			{
				return "Server1";
			}
			case MSG_PARTNER_SERVER2:
			{
				return "Server2";
			}
			case MSG_PARTNER_CLIENT:
			{
				return "client";
			}
			default:
			{
				return "ERROR";
			}
		}
	}
	
	
	
	
	/**
	 * Function ProcessAnalyzer is the core function of 
	 * this class, which stores all the information of transitions
	 * in the Petri Net models. And it uses a kind of "switch" structure
	 * to implement all the functionality.
	 * If the state goes wrong, some warnings will give out.
	 * msgID refers to the transmit direction of SOAP messages (in or out of BPEL service)
	 * msgLink refers to the partner link of the current SOAP message. 
	 * @param msgID
	 * @param msgLink
	 * @return stateCurrent
	 */
	public int  ProcessAnalyzer1(int msgID, int msgLink){
		switch (stateCurrent) {
			case 0:{
				if(msgID==2 && msgLink == 1){
					stateCurrent = 1;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 1:{
				if(msgID==2 && msgLink == 2){
					stateCurrent = 4;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else if(msgID==2 && msgLink == 1){
					stateCurrent = 2;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 2:{
				if(msgID==2  && msgLink == 1){
					stateCurrent = 3;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 4:{
				if(msgID==1  && msgLink == 2){
					stateCurrent = 5;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 3:{
				if(msgID==1  && msgLink == 3){
					stateCurrent = 7;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					System.out.println("Process Instance "+ instanceID +": Current Process execute successfully!!!");
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 5:{
				if(msgID==1 && msgLink == 3){
					stateCurrent = 7;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					System.out.println("Process Instance "+ instanceID +": Current Process execute successfully!!!");
					break;
				}
				else{
					return stateCurrent;
				}
			}
		}
		return E_Normal;
	}
	
	
	/**
	 * The method ProcessAnalyzer() is coded,
	 * according to DemoTestCase "DemoTestCase(Mixed).bpel".
	 * It is used to make out how to generate a monitor from
	 * Petri net model.
	 * 
	 * @param msgID
	 * @return
	 */
	public int ProcessAnalyzer(int msgID){
		System.out.println("Current State: " + stateCurrent + " and msgID:" + msgID);
		int P_0_0_Receive_MSG = 1;
		int P_0_1_0_0_InvokeOneWay_MSG = 2;
		int P_0_1_1_1_Receive_MSG = 3;
		int P_0_1_1_2_Reply_MSG = 4;
		int P_0_2_InvokeReqRep_Req_MSG = 5;
		int P_0_2_InvokeReqRep_Res_MSG = 6;
		switch (stateCurrent) {case 3:
		{ if(msgID == P_0_0_Receive_MSG){
		stateCurrent = 5;
		System.out.println("Change Current State into " + stateCurrent);
		break;
		}
		else
		{
		return stateCurrent;
		}
		}
		case 5:
		{ if(msgID == P_0_1_0_0_InvokeOneWay_MSG){
		stateCurrent = 13;
		System.out.println("Change Current State into " + stateCurrent);
		break;
		}else if(msgID == P_0_1_1_1_Receive_MSG){
		stateCurrent = 20;
		System.out.println("Change Current State into " + stateCurrent);
		break;
		}
		}
		case 20:
		{ if(msgID == P_0_1_1_2_Reply_MSG){
		stateCurrent = 13;
		System.out.println("Change Current State into " + stateCurrent);
		break;
		}
		else
		{
		return stateCurrent;
		}
		}
		case 13:
		{ if(msgID == P_0_2_InvokeReqRep_Req_MSG){
		stateCurrent = 48;
		System.out.println("Change Current State into " + stateCurrent);
		break;
		}
		else
		{
		return stateCurrent;
		}
		}
		case 48:
		{ if(msgID == P_0_2_InvokeReqRep_Res_MSG){
		stateCurrent = 51;
		System.out.println("Change Current State into " + stateCurrent);
		break;
		}
		else
		{
		return stateCurrent;
		}
		}

		default:
		{
		return stateCurrent;
		}
		}
		return E_Normal;
		}
	
	/**
	 * Function "monitor" is used to call the function "ProcessAnalyzer".
	 * According to the check results, output related information.
	 * @param msgID
	 * @param msgLink
	 */
	public void monitor(int msgID, int msgLink){
		int checkResult = -1;
		System.out.println("Process Instance "+ instanceID +": Current Status:" + stateCurrent);
		checkResult = ProcessAnalyzer1(msgID, msgLink);
		
		if(checkResult!=E_Normal){
			System.out.println("ALARM: Process Error!" +
			"Process Instance "+ instanceID +": error happens in state " + checkResult +" with received event " + msgID);
		}
	}
}



/**
 * This ProcessMonitor2 focuses on the BPEL example
 * "Travel".
 * It is generated from the specific BPEL specification.
 * 
 * @author Jones
 *
 */
class ProcessMonitor2{
	/**
	 * Define ProcessAnalyzer return Event type
	 */
	final   static	int  E_Normal = -1;		// Event: Normal Execution
	final 	int  E_Exception = 1;	// Event: Exception happens

	/**
	 * Definition of SOAP Message Type 
	 */
	private static final int MSG_TYPE_ERROR = -1;
	private static final int MSG_TYPE_OUT = 1;
	private static final int MSG_TYPE_IN = 2;

	/**
	 * Definition of Partner Link Services 
	 */
	private static final int MSG_PARTNER_ERROR = -1;
	private static final int MSG_PARTNER_CLIENT = 0;
	private static final int MSG_PARTNER_EMPLOYEETRAVELSTATUS = 1;
	private static final int MSG_PARTNER_AMERICANAIRLINES = 2;
	private static final int MSG_PARTNER_DELTAAIRLINES = 3;

	/**
	 * Important Variables
	 * instanceID: (BPEL instance ID);
	 * stateCurrent: (current state of corresponding BPEL instance)
	 */
	private int instanceID = -1;
	private int stateCurrent = 3;
	
	// stateFLOW1
	int[] stateFlow1 = new int[2];



	/**
	 * Static function AnalyzeSoapMSGTYPE
	 * analyze the SOAP Message
	 * @param typeMSG
	 * @return
	 */
	public static int AnalyzeSoapMSGTYPE(String typeMSG){
		if(typeMSG.startsWith("out")){
			return MSG_TYPE_OUT;
		}
		else if(typeMSG.startsWith("in")){
			return MSG_TYPE_IN;
		}
		else{
			return MSG_TYPE_ERROR;
		}
	}

	
	/**
	 * Static function SoapMSGTYPEID2String
	 * analyze the SOAP Message Type ID.
	 * Translate it into corresponding String.
	 * @param typeMSG
	 * @return MSGTYPE String
	 */
	public static String SoapMSGTYPEID2String(int typeMSG){
		switch(typeMSG){
			case MSG_TYPE_OUT:
			{
				return "out";
			}
			case MSG_TYPE_IN:
			{
				return "in";
			}
			default:
			{
				return "ERROR";
			}
		}
	}

	/**
	 * Static function AnalyzeSoapMSGPartner
	 * Analyze the Partner Links into integers.
	 * @param linkMSG
	 * @return
	 */
	public static int AnalyzeSoapMSGPartner(String linkMSG){
		if(linkMSG.startsWith("client")){
			return MSG_PARTNER_CLIENT;
		}
		else if(linkMSG.startsWith("employeeTravelStatus")){
			return MSG_PARTNER_EMPLOYEETRAVELSTATUS;
		}
		else if(linkMSG.startsWith("AmericanAirlines")){
			return MSG_PARTNER_AMERICANAIRLINES;
		}
		else if(linkMSG.startsWith("DeltaAirlines")){
			return MSG_PARTNER_DELTAAIRLINES;
		}
		else{
			System.out.println("ERROR: There is not such a Partner Links.");
			return MSG_PARTNER_ERROR;
		}
	}

	/**
	 * Static function AnalyzeSoapMSGPartner
	 * Analyze the Partner Links into integers.
	 * @param linkMSG
	 * @return
	 */
	public static String SoapMSGPartner2String(int plinkMSG){
		switch(plinkMSG){
			case MSG_PARTNER_CLIENT:
			{
				return "client";
			}
			case MSG_PARTNER_EMPLOYEETRAVELSTATUS:
			{
				return "employeeTravelStatus";
			}
			case MSG_PARTNER_AMERICANAIRLINES:
			{
				return "AmericanAirlines";
			}
			case MSG_PARTNER_DELTAAIRLINES:
			{
				return "DeltaAirlines";
			}
			default:
			{
					return "ERROR";
			}
		}
	}
	
	
	/**
	 * Definition of Class Constructor
	 */
	public ProcessMonitor2(int ID){
		 instanceID = ID;
		 
		// init stateFLOW1
		stateFlow1[0]=32;
		stateFlow1[1]=35;
	}

	/**
	 * Function ProcessAnalyzer is the core function of 
	 * this class, which stores all the information of transitions
	 * in the Petri Net models. And it uses a kind of "switch" structure
	 * to implement all the functionality.
	 * If the state goes wrong, some warnings will give out.
	 * msgID refers to the transmit direction of SOAP messages (in or out of BPEL service)
	 * msgLink refers to the partner link of the current SOAP message. 
	 * @param msgID
	 * @param msgLink
	 * @return stateCurrent
	 */
	public int  ProcessAnalyzer(int msgID, int msgLink){
		
		switch(stateCurrent){
			case 3:
			{
				if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_CLIENT){
					stateCurrent = 12;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			case 12:
			{
				if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_EMPLOYEETRAVELSTATUS){
					stateCurrent = 19;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			case 19:
			{
				if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_EMPLOYEETRAVELSTATUS){
					stateCurrent = 30;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			
			// handle the fork problem of "FLOW" activity.
			/*
			 * ************************************************
			 * The following part of code is very IMPORTANT for
			 * implementing the function of FORK (flow in BPEL)
			 * ************************************************
			 */
			case 30:
			{
				boolean isException1 = true;
				switch(stateFlow1[0]){
					case 32:
					{
						if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_AMERICANAIRLINES){
							stateFlow1[0] = 39;
							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[0]);
							isException1 = false;
						}
						break;
					}
					case 39:
					{
						if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_AMERICANAIRLINES){
							stateFlow1[0] = 60;
							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[0]);
							isException1 = false;
						}
						break;
					}
				}
				switch(stateFlow1[1]){
					case 35:
					{
						if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_DELTAAIRLINES){
							stateFlow1[1] = 45;
							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[1]);
							isException1 = false;
						}
						break;
					}
					case 45:
					{
						if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_DELTAAIRLINES){
							stateFlow1[1] = 60;
							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[1]);
							isException1 = false;
						}
						break;
					}
				}
				if(isException1 == false){
					if(stateFlow1[0] == 60 && stateFlow1[1]==60){
						stateCurrent = 80;
						System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					}
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			case 80:
			{
				if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_CLIENT){
					stateCurrent = 95;
					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					System.out.println("Process Instance "+ instanceID +": Current Process execute successfully!!!");
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			default:
			{
				return stateCurrent;
			}
		}
		return E_Normal;
	}

	public void monitor(int msgID, int msgLink){
		int checkResult = -1;
		System.out.println("Process Instance "+ instanceID +": Current Status:" + stateCurrent);
		checkResult = ProcessAnalyzer(msgID, msgLink);
		
		if(checkResult!=E_Normal){
			System.out.println("ALARM: Process Error!" +
			"Process Instance "+ instanceID +": error happens in state " 
			+ checkResult +" with received MSG type \"" + SoapMSGTYPEID2String(msgID) 
			+ "\" and partner link \"" + SoapMSGPartner2String(msgLink)+"\"");
		}
		
	}
	
}









/**
 * This ProcessMonitorForTest focuses on the BPEL example
 * "Travel".
 * It is generated from the specific BPEL specification.
 * 
 * *****************************************************
 * 1. It is specially used for TEST the overhead of monitor
 * And the BPEL Process is a circle.
 * It means that the final BPEL state will jump to the 
 * beginning state.
 * 
 * 2. All the print codes, except time, are illuminated, in order to 
 * increase the performance of monitor
 * *****************************************************
 * 
 * @author Jones
 *
 */
class ProcessMonitorForTest{
	/**
	 * Define ProcessAnalyzer return Event type
	 */
	final   static	int  E_Normal = -1;		// Event: Normal Execution
	final 	int  E_Exception = 1;	// Event: Exception happens

	/**
	 * Definition of SOAP Message Type 
	 */
	private static final int MSG_TYPE_ERROR = -1;
	private static final int MSG_TYPE_OUT = 1;
	private static final int MSG_TYPE_IN = 2;

	/**
	 * Definition of Partner Link Services 
	 */
	private static final int MSG_PARTNER_ERROR = -1;
	private static final int MSG_PARTNER_CLIENT = 0;
	private static final int MSG_PARTNER_EMPLOYEETRAVELSTATUS = 1;
	private static final int MSG_PARTNER_AMERICANAIRLINES = 2;
	private static final int MSG_PARTNER_DELTAAIRLINES = 3;

	/**
	 * Important Variables
	 * instanceID: (BPEL instance ID);
	 * stateCurrent: (current state of corresponding BPEL instance)
	 */
	private int instanceID = -1;
	private int stateCurrent = 3;
	
	// stateFLOW1
	int[] stateFlow1 = new int[2];



	/**
	 * Static function AnalyzeSoapMSGTYPE
	 * analyze the SOAP Message
	 * @param typeMSG
	 * @return
	 */
	public static int AnalyzeSoapMSGTYPE(String typeMSG){
		if(typeMSG.startsWith("out")){
			return MSG_TYPE_OUT;
		}
		else if(typeMSG.startsWith("in")){
			return MSG_TYPE_IN;
		}
		else{
			return MSG_TYPE_ERROR;
		}
	}

	
	/**
	 * Static function SoapMSGTYPEID2String
	 * analyze the SOAP Message Type ID.
	 * Translate it into corresponding String.
	 * @param typeMSG
	 * @return MSGTYPE String
	 */
	public static String SoapMSGTYPEID2String(int typeMSG){
		switch(typeMSG){
			case MSG_TYPE_OUT:
			{
				return "out";
			}
			case MSG_TYPE_IN:
			{
				return "in";
			}
			default:
			{
				return "ERROR";
			}
		}
	}

	/**
	 * Static function AnalyzeSoapMSGPartner
	 * Analyze the Partner Links into integers.
	 * @param linkMSG
	 * @return
	 */
	public static int AnalyzeSoapMSGPartner(String linkMSG){
		if(linkMSG.startsWith("client")){
			return MSG_PARTNER_CLIENT;
		}
		else if(linkMSG.startsWith("employeeTravelStatus")){
			return MSG_PARTNER_EMPLOYEETRAVELSTATUS;
		}
		else if(linkMSG.startsWith("AmericanAirlines")){
			return MSG_PARTNER_AMERICANAIRLINES;
		}
		else if(linkMSG.startsWith("DeltaAirlines")){
			return MSG_PARTNER_DELTAAIRLINES;
		}
		else{
			System.out.println("ERROR: There is not such a Partner Links.");
			return MSG_PARTNER_ERROR;
		}
	}

	/**
	 * Static function AnalyzeSoapMSGPartner
	 * Analyze the Partner Links into integers.
	 * @param linkMSG
	 * @return
	 */
	public static String SoapMSGPartner2String(int plinkMSG){
		switch(plinkMSG){
			case MSG_PARTNER_CLIENT:
			{
				return "client";
			}
			case MSG_PARTNER_EMPLOYEETRAVELSTATUS:
			{
				return "employeeTravelStatus";
			}
			case MSG_PARTNER_AMERICANAIRLINES:
			{
				return "AmericanAirlines";
			}
			case MSG_PARTNER_DELTAAIRLINES:
			{
				return "DeltaAirlines";
			}
			default:
			{
					return "ERROR";
			}
		}
	}
	
	
	/**
	 * Definition of Class Constructor
	 */
	public ProcessMonitorForTest(int ID){
		 instanceID = ID;
		 
		// init stateFLOW1
		stateFlow1[0]=32;
		stateFlow1[1]=35;
	}

	/**
	 * Function ProcessAnalyzer is the core function of 
	 * this class, which stores all the information of transitions
	 * in the Petri Net models. And it uses a kind of "switch" structure
	 * to implement all the functionality.
	 * If the state goes wrong, some warnings will give out.
	 * msgID refers to the transmit direction of SOAP messages (in or out of BPEL service)
	 * msgLink refers to the partner link of the current SOAP message. 
	 * @param msgID
	 * @param msgLink
	 * @return stateCurrent
	 */
	public int  ProcessAnalyzer(int msgID, int msgLink){
		
		switch(stateCurrent){
			case 3:
			{
				if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_CLIENT){
					stateCurrent = 12;
//					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			case 12:
			{
				if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_EMPLOYEETRAVELSTATUS){
					stateCurrent = 19;
//					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			case 19:
			{
				if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_EMPLOYEETRAVELSTATUS){
					stateCurrent = 30;
//					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			
			// handle the fork problem of "FLOW" activity.
			/*
			 * ************************************************
			 * The following part of code is very IMPORTANT for
			 * implementing the function of FORK (flow in BPEL)
			 * ************************************************
			 */
			case 30:
			{
				boolean isException1 = true;
				switch(stateFlow1[0]){
					case 32:
					{
						if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_AMERICANAIRLINES){
							stateFlow1[0] = 39;
//							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[0]);
							isException1 = false;
						}
						break;
					}
					case 39:
					{
						if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_AMERICANAIRLINES){
							stateFlow1[0] = 60;
//							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[0]);
							isException1 = false;
						}
						break;
					}
				}
				switch(stateFlow1[1]){
					case 35:
					{
						if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_DELTAAIRLINES){
							stateFlow1[1] = 45;
//							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[1]);
							isException1 = false;
						}
						break;
					}
					case 45:
					{
						if(msgID == MSG_TYPE_IN && msgLink == MSG_PARTNER_DELTAAIRLINES){
							stateFlow1[1] = 60;
//							System.out.println("Process Instance "+ instanceID +": Change Current State into FLOW state " + stateFlow1[1]);
							isException1 = false;
						}
						break;
					}
				}
				if(isException1 == false){
					if(stateFlow1[0] == 60 && stateFlow1[1]==60){
						stateCurrent = 80;
//						System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
					}
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			case 80:
			{
				if(msgID == MSG_TYPE_OUT && msgLink == MSG_PARTNER_CLIENT){
					/*
					 * Actually, it should be "stateCurrent = 95;"
					 * means the end of this BPEL process.
					 * 
					 * In order to used for test,
					 * jump the final state to the beginning state.
					 * 
					 */
//					stateCurrent = 95;
					stateCurrent = 3;
					
					/*
					 * Recover FLOW sub-states.
					 * For TEST
					 */
					// Re-init stateFLOW1
					stateFlow1[0]=32;
					stateFlow1[1]=35;
					
//					System.out.println("Process Instance "+ instanceID +": Change Current State into " + stateCurrent);
//					System.out.println("Process Instance "+ instanceID +": Current Process execute successfully!!!");
					break;
				}
				else
				{
					return stateCurrent;
				}
			}
			default:
			{
				return stateCurrent;
			}
		}
		return E_Normal;
	}

	public void monitor(int msgID, int msgLink){
		int checkResult = -1;
//		System.out.println("Process Instance "+ instanceID +": Current Status:" + stateCurrent);
		checkResult = ProcessAnalyzer(msgID, msgLink);
		
//		if(checkResult!=E_Normal){
//			System.out.println("ALARM: Process Error!" +
//			"Process Instance "+ instanceID +": error happens in state " 
//			+ checkResult +" with received MSG type \"" + SoapMSGTYPEID2String(msgID) 
//			+ "\" and partner link \"" + SoapMSGPartner2String(msgLink)+"\"");
//		}
		
	}
	
}
