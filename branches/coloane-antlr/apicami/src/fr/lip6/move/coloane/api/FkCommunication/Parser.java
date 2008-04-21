package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Parser extends Thread {

	LinkedBlockingQueue fifo;

	public Parser(LinkedBlockingQueue queue){
		this.fifo = queue;
	}

	public void run(){
		// TODO ********************************************************

		while(true){
			InputStream is;
			try {
				is = (InputStream)fifo.take();
				int c;
				String s = "";
				while(true){
					c = is.read();
					if (c==-1)
						break;
					s += (char) c;
				}

				System.out.println(s);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e){

			}
			// read jusqu'a la fin de fichier


		}
	}

}
