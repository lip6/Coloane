package fr.lip6.move.coloane.extensions.importFromBPEL.importFromBPEL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import fr.lip6.move.coloane.interfaces.model.IArc;
public class ProcMonitoringServer {
	private static int produceTaskSleepTime = 100;
	private static int consumeTaskSleepTime = 1200;
	private static int produceTaskMaxNumber = 100;
	private static final int CORE_POOL_SIZE = 2;
	private static final int MAX_POOL_SIZE = 100;
	private static final int KEEPALIVE_TIME = 3;
	private static final int QUEUE_CAPACITY = (CORE_POOL_SIZE + MAX_POOL_SIZE) / 2;
	private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 19528;
	private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(
			QUEUE_CAPACITY);
	//private ThreadPoolExecutor serverThreadPool = null;
	
	private ExecutorService pool = null;
	private RejectedExecutionHandler rejectedExecutionHandler = new
	ThreadPoolExecutor.DiscardOldestPolicy();
	private ServerSocket serverListenSocket = null;
	private int times = 5;
	public void start() {
		ServiceThread tempMonitorThread = null;	
		
		// You can also init thread pool in this way.
		/*serverThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAX_POOL_SIZE, KEEPALIVE_TIME, TIME_UNIT, workQueue,
				rejectedExecutionHandler);*/
		pool = Executors.newFixedThreadPool(10);
		
		ArrayList<ItemProcessThread> tablePT = new ArrayList<ItemProcessThread>();
		
		System.out.println("I'm receiving SOAP messages...");
        try {
            FileReader fr = new FileReader("e:/SOAPMSGQueueFile.txt");//创建FileReader对象，用来读取字符流
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
//                	System.out.println("ERROR in the MSG input file(MSG not in pair).");
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
                
                // According to each MSG, check if it belongs to any existing monitor:
                // if yes, then send this MSG to related monitor thread.
                // if no, then new a new monitor thread.
            	SoapMSG m = new SoapMSG(procID, objectService, MSGType); 
//            	System.out.println(m.getMSGType());
                if(tablePT.isEmpty()){
                	//serverThreadPool.execute(new ServiceThread(socket, welcomeString));
                	System.out.println("tablePT.isEmpty()");
                	ServiceThread newMonitorThread = new ServiceThread(m);
//                	System.out.println("tablePT.isEmpty()");
                	new Thread(newMonitorThread).start();    
//                	pool.execute(newMonitorThread);
                	ItemProcessThread tempItem = new ItemProcessThread(Integer.parseInt(procID),newMonitorThread);
                	tablePT.add(tempItem);
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
                		System.out.println("isExisting==true");
                		System.out.println(tablePT.get(indexMSG).getThreadObject().toString());
                		tempMonitorThread = tablePT.get(indexMSG).getThreadObject();
                		
                		tempMonitorThread.setSoapMSG(m);
                		tempMonitorThread.notify();  //这个notify是需要怎么使用的？？？？？？？
                		
                	}
                	else{
                		System.out.println("isExisting!!=true");
                		// A new process is created
                		// so it is necessary to create a new monitor thread.
                		ServiceThread newMonitorThread = new ServiceThread(m);
//	                	pool.execute(newMonitorThread);
                		new Thread(newMonitorThread).start(); 
	                	ItemProcessThread tempItem = new ItemProcessThread(Integer.parseInt(procID),newMonitorThread);
	                	tablePT.add(tempItem);
                	}	                	
                }
                try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		cleanup();
	}
	public void cleanup() {
		if (null != serverListenSocket) {
			try {
				serverListenSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//serverThreadPool.shutdown();
		pool.shutdown();
	}
	public static void main(String args[]) {
		ProcMonitoringServer server = new ProcMonitoringServer();
		server.start();
	}
}


// Monitor Thread
class ServiceThread implements Runnable, Serializable {
//	private static final long serialVersionUID = 0;
//	private Socket connectedSocket = null;
//	private String helloString = null;
//	private static int count = 0;
//	private static ReentrantLock lock = new ReentrantLock();
	
	private SoapMSG Msg = null;
	final	static	int  MSG_SEND = 1; 		// Define send type of MSG
	final	static	int  MSG_RECEIVE = 2;	// Define receive type of MSG
	ProcessMonitor testCase = null;
	
	ServiceThread(SoapMSG m) {
		Msg = m;
		System.out.println("ServiceThread");
	}
	
	public void setSoapMSG(SoapMSG m){
		Msg = m;
	}
	
	public void run(){
		testCase = new ProcessMonitor();
		int msgID = 0;
		while(true){
//			System.out.println("In thread: " + Msg.getMSGType());
			if(Msg.getMSGType().startsWith("out")){
				msgID = 1;
			}
			else if(Msg.getMSGType().startsWith("in")){
				// Right now there are two MSG types (out & in)
				// Actually, just input or output (two directions of MSGs)
				msgID = 2;
			}
			else{
				System.out.println("MSG Type Error!!!! - " + Msg.getMSGType());
			}
//			System.out.println(msgID);
			
			testCase.monitor(msgID);
//			testCase.monitor(msgID);
//			testCase.monitor(msgID);
//			testCase.monitor(1);
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		increaseCount();
//		int curCount = getCount();
//		helloString = "hello, id = " + curCount + "\r\n";
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		Future<String> future = executor.submit(new TimeConsumingTask());
//		DataOutputStream dos = null;
//		try {
//			dos = new DataOutputStream(connectedSocket.getOutputStream());
//			dos.write(helloString.getBytes());
//			try {
//				dos.write("let's do soemthing other.\r\n".getBytes());
//				String result = future.get();
//				dos.write(result.getBytes());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (null != connectedSocket) {
//				try {
//					connectedSocket.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (null != dos) {
//				try {
//					dos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			executor.shutdown();
//		}
		
	}
//	private int getCount() {
//		int ret = 0;
//		try {
//			lock.lock();
//			ret = count;
//		} finally {
//			lock.unlock();
//		}
//		return ret;
//	}
//	private void increaseCount() {
//		try {
//			lock.lock();
//			++count;
//		} finally {
//			lock.unlock();
//		}
//	}
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
	private ServiceThread ThreadObject=null;
	
	public ItemProcessThread(int PID, ServiceThread threadObject){
		BPELProcessID = PID;
		ThreadObject = threadObject;
	}
	
	public int getBPELProcessID(){
		return BPELProcessID;
	}
	
	public ServiceThread getThreadObject(){
		return ThreadObject;
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

class ProcessMonitor{
	
	// Define the Incidence Matrix of Petri Nets
	// in a vector Matrix[][]
	// ** Monitor Generation **
	// It is required to define the incidence matrix 
	// with varibles m and n to define the matrix.
	// ## Monitor Generation ##
//	int[][] Matrix = new int[10][15];
	
	// Define ProcessAnalyzer return Event type
	final   static	int  E_Normal = -1;		// Event: Normal Execution
	final 	int  E_Exception = 1;	// Event: Exception happens
	
	final	static	int  MSG_Receive = 0;
	final	static	int  MSG_InvokeOneWay = 1;
	final	static	int  MSG_2_Receive = 2;
	final	static	int  MSG_InvokeReqRep_Req =3;
	final	static	int  MSG_InvokeReqRep_Res = 4;
	
	private int	num_P = 14;
//	int[] stateCurrent = new int[num_P];
	private int stateCurrent = 0;
	
	public void setStateCurrent(int state){
		stateCurrent = state;
	}
	/**
	 * Test the Analyzer1
	 */
	public int  ProcessAnalyzer1(int msgID){
		switch (stateCurrent) {
			case 0:{
				if(msgID==2){
					stateCurrent = 1;
					System.out.println("Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 1:{
				if(msgID==1){
					stateCurrent = 4;
					System.out.println("Change Current State into " + stateCurrent);
					break;
				}
				else if(msgID==2){
					stateCurrent = 2;
					System.out.println("Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 2:{
				if(msgID==2){
					stateCurrent = 3;
					System.out.println("Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 4:{
				if(msgID==1){
					stateCurrent = 5;
					System.out.println("Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 3:{
				if(msgID==1){
					stateCurrent = 7;
					System.out.println("Change Current State into " + stateCurrent);
					break;
				}
				else{
					return stateCurrent;
				}
			}
			case 5:{
				if(msgID==1){
					stateCurrent = 7;
					System.out.println("Change Current State into " + stateCurrent);
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
	
	public void monitor(int msgID){
		int checkResult = -1;
		System.out.println("Current Status:" + stateCurrent);
		checkResult = ProcessAnalyzer1(msgID);
		
		if(checkResult!=E_Normal){
			System.out.println("ALARM: Process Error!" +
			"Monitor: error happens in state " + checkResult +" with received event " + msgID);
		}
	}
}
