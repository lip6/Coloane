package fr.lip6.move.coloane.communications.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Vector;  

import fr.lip6.move.coloane.exceptions.CommunicationCloseException;

/**
 * Cette class gère les communications de bas niveau avec la plateforme
 * FrameKit, et en particulier la gestion des sockets.
 * Mais aucun traitement des commandes CAMI n'est fait ici.
 * 
 * @author M2-SAR 2005-2006 Equipe 2
 * @author Jean-Baptiste Voron
 * 
 */ 

public class ComLowLevel {
	/**
     * C'est l'identifiant de la socket qui permet de communiquer avec la plate-forme distante
     */
    private Socket socket;
    
    /**
     * Objet que vont partager tous les threads pour ecrire sur le canal de communication
     */
    private DataOutputStream socketOutput;
    
    /**
     * Objet qui permet de recevoir les communications entrantes
     */
    private DataInputStream socketInput;
    
    

    
    /**
     * Permet de creer une socket (utilisation des paramètre par défaut)
     * 
     * @return Retourne true si OK
     * @throws Exception une exception est levée si il y a un probleme
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
     * 
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
    
    /**
     * Ecrire sur le flux de sortie vers Framekit
     * 
     * @param commande commande à envoyer à FrameKit
     * @return Retourne true si OK
     * @throws CommunicationCloseException si la lecture se passe mal
     */
    public boolean writeCommande(byte[] commande) throws CommunicationCloseException {
        try {
        	
			String msg = new String(commande, 4, commande.length - 4);
			System.out.println(">> Envoi de : " + msg);
			
			
        	if (!this.socket.isOutputShutdown()) {
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
            System.out.println("Taille du message reseau : "+longueurMessage);

            // Lecture de la socket selon la longueur donnée.
            for (int j = 0; j < longueurMessage; j++) {
            	char monChar = (char) this.socketInput.readByte();
            	
            	if ((monChar == '\n')) {
            		// Nouvelle commande detectée
            		System.out.println("Commande lue: "+commande);
                	liste.add(commande);
                	commande = "";
            	} else {
            		commande += monChar;
            	}
            }
            
            System.out.println("Dernière commande lue: "+commande);
        	liste.add(commande);
            
            return liste;
            
        } catch (Exception e) {
            if (e instanceof CommunicationCloseException) {
            	liste.add("EOS");
				return liste;
			} else {
				e.printStackTrace();
			}
            return null;	
        }
    }
    
    
    
    /**
     * Lis  un caractere sur le flux d'entree
     * @return int le caractere lu
     * @throws Exception leve une execption si il y a un probleme
     */
    public int readChar() throws Exception {
        int charLu;
    
        try {
        	if (!this.socket.isInputShutdown()) {
        		charLu = this.socketInput.readByte();
        		return charLu;
        	} else {
        		throw new CommunicationCloseException("Communication closed");
        	}
        } catch (IOException e) {
            throw e;
        }
    }

}
