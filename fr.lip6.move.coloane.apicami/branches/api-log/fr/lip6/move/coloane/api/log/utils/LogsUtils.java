package fr.lip6.move.coloane.api.log.utils;
/**
 * 
 * @author cdcharles
 * Cette classe contient des methodes utiles pour les formats d'affichage
 * des logs
 * 
 */
public class LogsUtils {

	/** Cette methode convertit un tableau d'objets en String
	 * @param le tableau d'objets
	 * @return la representation en String de ces objets
	 */
	public String FromArrayToString(Object[] o){
		String s = "";
		
		for(int i=0; i< o.length; i++){
			s += o[i].toString() + " ";
		}
		return  s;
	}

	/** Cette methode prend un String et s'arrete à la premiere accolade rencontree
	 * Cette methode est cree pour repondre à la specification des messages de logs
	 * @param s le string à formater
	 * @return le string sans les
	 */
	public String sous_chaine(String s){
		String res;
		int i = 0;
		while(i< s.length() && s.charAt(i) != '{')
			i++;
		
		res = s.substring(0, i);
		return res;
	}
	
	/** Cette methode imprime la pile lorsque une exception est levee
	 * @param e l'exception
	 * @return la chaine de caractere representant la pile
	                                                                          */
	public String StackToString(Exception e){
		StackTraceElement[] tab = e.getStackTrace();
		String s = "";
		for(int i=0; i<tab.length; i++){
			s+= "\n \t "+tab[i] + " --> " + tab[i].getMethodName() + "\n";
		}
		return s;
	
	}
}
