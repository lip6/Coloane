package essai;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class TraceErrorHandler implements ErrorHandler {
    
	public void error(SAXParseException arg0) throws SAXException {
		// TODO Auto-generated method stub
		
		System.out.println("Ah Non! ERREUR il ne faut pas faire comme ça \n"+
				"va voir ligne     :"+arg0.getLineNumber() +"\n" +
				" l'URI            :"+arg0.getSystemId()+"\n"+
				"le problèeeme est :"+arg0.getMessage());
	}//error
	public void fatalError(SAXParseException arg0) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Mais, c'est pas vrai,ERREUR FATALE On ne programme pas comme ça\n"+
				"va voir ligne     :"+arg0.getLineNumber() +"\n" +
				" l'URI            :"+arg0.getSystemId()+"\n"+
				"le problèeeme est :"+arg0.getMessage());
	}//fatal error
	
	public void warning(SAXParseException arg0) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Attention WARNING \n"+
				"va voir ligne     :"+arg0.getLineNumber() +"\n" +
				" l'URI            :"+arg0.getSystemId()+"\n"+
				"le problèeeme est :"+arg0.getMessage());
	}//Warning

} 