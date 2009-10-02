package fr.lip6.move.coloane.extensions.importFromBPEL.importFromBPEL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
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
		// You can also init thread pool in this way.
		/*serverThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
				MAX_POOL_SIZE, KEEPALIVE_TIME, TIME_UNIT, workQueue,
				rejectedExecutionHandler);*/
		pool = Executors.newFixedThreadPool(10);
		try {
//			serverListenSocket = new ServerSocket(PORT);
//			serverListenSocket.setReuseAddress(true);
			
			System.out.println("I'm receiving SOAP messages...");
			
	        try {
	            FileReader fr = new FileReader("e:/SOAPMSGQueueFile.txt");//创建FileReader对象，用来读取字符流
	            BufferedReader br = new BufferedReader(fr);    //缓冲指定文件的输入
	            String procID;    //the Process ID of MSG
	            String objectService;	//where the MSG come from or go to
	            String MSGType;			//The type of MSG (usually just 'in' and 'out')
	            while (br.ready()) {
	            	procID = br.readLine();//Read procID
	                System.out.println(procID);//out put in console
	                if(br.ready()) {
	                	objectService = br.readLine();//read service
		                System.out.println(objectService);//out put in console
	                }
	                else{
	                	System.out.println("ERROR in the MSG input file(MSG not in pair).");
	                	break;
	                }
	                if(br.ready()) {
	                	MSGType = br.readLine();//read MSGType
		                System.out.println(MSGType);//out put in console
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
	            }
	            br.close();
	            fr.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
	
			
			while (times-- > 0) {
				Socket socket = serverListenSocket.accept();
				String welcomeString = "hello";
	//serverThreadPool.execute(new ServiceThread(socket, welcomeString));
				pool.execute(new ServiceThread(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
class ServiceThread implements Runnable, Serializable {
	private static final long serialVersionUID = 0;
	private Socket connectedSocket = null;
	private String helloString = null;
	private static int count = 0;
	private static ReentrantLock lock = new ReentrantLock();
	ServiceThread(Socket socket) {
		connectedSocket = socket;
	}
	public void run() {
		increaseCount();
		int curCount = getCount();
		helloString = "hello, id = " + curCount + "\r\n";
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(new TimeConsumingTask());
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(connectedSocket.getOutputStream());
			dos.write(helloString.getBytes());
			try {
				dos.write("let's do soemthing other.\r\n".getBytes());
				String result = future.get();
				dos.write(result.getBytes());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != connectedSocket) {
				try {
					connectedSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != dos) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			executor.shutdown();
		}
	}
	private int getCount() {
		int ret = 0;
		try {
			lock.lock();
			ret = count;
		} finally {
			lock.unlock();
		}
		return ret;
	}
	private void increaseCount() {
		try {
			lock.lock();
			++count;
		} finally {
			lock.unlock();
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
