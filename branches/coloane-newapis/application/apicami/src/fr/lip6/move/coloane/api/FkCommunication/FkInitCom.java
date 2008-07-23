package fr.lip6.move.coloane.api.FkCommunication;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;

import fr.lip6.move.coloane.api.interfaces.IListener;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

/**
 * Cette classe construit
 *  - la socket de communcation avec FK et la connecter
 * 	- un objet Listener
 *  - un objet Speaker
 *
 *  Et renvoie les interfaces  ISpeaker et IListener
 *
 *  @author Kahina Bouarab
 *  @author Youcef Bellataf
 */
public class FkInitCom {

	/**
	 * Fabrique un objet Listener.
	 * @param lowLevel Objet bas niveau s'occupant des lectures à partir de la socket.
	 * @param queue file d'objets (InputStream) où le listener déposera pour le parser des flux sur les commandes arrivant de FrameKit.
	 * @return une interface IListener.
	 */
	private static IListener getFkComListener(FkComLowLevel lowLevel, LinkedBlockingQueue<InputStream> queue) {
		return new Listener(lowLevel, queue);
	}

	/**
	 * Fabrique un objet Speaker
	 * @param lowLevel Objet bas niveau s'occupant des lectures à partir de la socket.
	 * @return l'interface ISpeaker
	 */
	private static ISpeaker getFkComSpeaker(FkComLowLevel lowLevel){
		return new Speaker(lowLevel);
	}


	/**
	 * TODO: A documenter
	 * @param ip adresse Ip de la machine hébérgeant FrameKit
	 * @param port d'écoute de FrameKit
	 * @param fifo
	 * @return paire d'objet contenant le Speaker et le Listener
	 * @throws IOException
	 */
	public static Pair<ISpeaker, IListener> initCom(String ip, int port, LinkedBlockingQueue<InputStream> fifo) throws IOException {

		/** Créer une nouvelle connexion */
		FkComLowLevel lowLevel = new FkComLowLevel(ip, port);
		Pair<ISpeaker, IListener> pair = new Pair<ISpeaker, IListener>();

		/** Créer un listener */
		pair.listener = getFkComListener(lowLevel, fifo);
		pair.listener.start();

		/** Créer un speaker */
		pair.speaker  = getFkComSpeaker(lowLevel);

		return pair;
	}
}
