package fr.lip6.move.coloane.api.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;  

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;

/**
 * Cette class gère les communications de bas niveau avec la plateforme
 * FrameKit, et en particulier la gestion des sockets.
 * Mais aucun traitement des commandes CAMI n'est fait ici.
 */ 

public class ComLowLevel {
	/** Identifiant de socket permettant de dialoguer avec la plateforme */
    private Socket socket;
    
    /** Objet que vont partager tous les threads pour ecrire sur le canal de communication */
    private DataOutputStream socketOutput;
    
    /** Objet qui permet de recevoir les communications entrantes */
    private DataInputStream socketInput;
    
    /**
     * Ecrire sur le flux de sortie vers Framekit
     * @param commande commande à envoyer à FrameKit
     * @return Retourne true si OK
     * @throws CommunicationCloseException si la lecture se passe mal
     */
    public boolean writeCommande(byte[] commande) throws CommunicationCloseException {
        try {
        	if (!this.socket.isOutputShutdown()) {
        		
        		// <DEBUG>
        		String msg = new String(commande, 4, commande.length - 4);
    			System.out.println("[CO-->FK] : " + msg);
    			// </DEBUG>
    			
        		this.socketOutput.write(commande, 0, commande.length);
        		return true;
        	} else {
        		return false;
        	}
        } catch (Exception e) {
         	System.err.println("Erreur lors de l'écriture sur la socket" + e.getMessage());
        	throw new CommunicationCloseException(e.getMessage());
		}
    }
    
    
    /**
     * Lis une commande sur le flux d'entrée
     * @return Vector L'ensemble des commande qu'on a lues
     */
    public synchronized Vector readCommande() {
    	Vector<String> liste = new Vector<String>();    	
    	int longueurMessage = 0;
    	String commande = "";
      
        try {
        	
        	// Lecture des 4 premiers octets donnant la taille du message
        	longueurMessage = this.socketInput.readInt();

            // Lecture de la socket selon la longueur donnée.
            for (int j = 0; j < longueurMessage; j++) {
            	char monChar = (char) this.socketInput.readByte();
            	
            	if ((monChar == '\n')) {
            		// Nouvelle commande detectée
                	System.out.println("[CO<--FK] : "+commande);
            		liste.add(commande);
                	commande = "";
            	} else {
            		commande += monChar;
            	}
            }
            
            System.out.println("[CO<--FK] : "+commande);
        	liste.add(commande);
            return liste;
            
        } catch (Exception e) {
			e.printStackTrace();
			return null;	
        }
    }
    
    
    /**
     * Permet de creer une socket (utilisation des parametres par défaut)
     * @return Retourne true si OK
     * @throws Exception une exception est levee si il y a un probleme
     */
    public boolean createCom(String ip, int port) 
    throws Exception {
        this.socket = new Socket();
        
        // Ouverture d'une socket
        try {
            this.socket.connect(new InetSocketAddress(ip, port));
        } catch (Exception e) {
            System.err.println("Erreur lors de la creation de la socket :" + e.getMessage());
            throw e;
        }
        
        this.socketInput = new DataInputStream(this.socket.getInputStream());
        this.socketOutput = new DataOutputStream(this.socket.getOutputStream());
        
        return true;
    }
    
    /**
     * Ferme les connexions ouvertes
     * @return boolean Réussite de l'opération
     */
    public boolean closeCom() {
        try {
        	this.socketInput.close();
            this.socketOutput.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
 }
