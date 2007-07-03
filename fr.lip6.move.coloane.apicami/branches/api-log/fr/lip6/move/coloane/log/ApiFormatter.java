package fr.lip6.move.coloane.log;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.Calendar;

public class ApiFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		// TODO Auto-generated method stub
		StringBuffer s = new StringBuffer(1000);
		Calendar c = Calendar.getInstance();
		s.append("[" + record.getLevel() + "] ");
		s.append("" + c.get(Calendar.DAY_OF_MONTH) + "/"
				+ c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " ");
		s.append("" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND));
		s.append("(" + record.getSourceClassName() + " "
				+ record.getSourceMethodName() + ")");
		s.append( "- " + sous_chaine(record.getMessage()) + "- ");
		s.append(" " + FromArray(record.getParameters()));
		

		return s.toString();

	}
	/** Transforme un tableau d'objets en une chaine de caracteres
	 * @param le tableau
	 * @return la chaine de caractères representant le tableau
	 */
	private String FromArray(Object[] o) {
		String s = "";

		for (int i = 0; i < o.length; i++) {
			s += o[i].toString() + " ";
		}
		return s;
	}
	/** Extrait une sous chaine de s
	 * @param s la chaine à extraire
	 * @return la chaine extraite
	 */
	public String sous_chaine(String s){
		String res;
		int i = 0;
		while(i< s.length() && s.charAt(i) != '{')
			i++;
		
		res = s.substring(0, i);
		return res;
	}

}
