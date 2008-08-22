package fr.lip6.move.coloane.interfaces.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe complementaire du J2SDK sur la manipulation de chaines de caractéres<br>
 * Permet nottament de supprimer les accents d'une chaine de caractères
 *
 * @author divxdede
 * {@link http://java.developpez.com/sources/?page=nombresDatesString}
 *
 */
public final class StringUtils {
	/** Index du 1er caractere accentué **/
	private static final int MIN = 192;
	/** Index du dernier caractere accentué **/
	private static final int MAX = 255;
	/** Vecteur de correspondance entre accent / sans accent **/
	private static final List<String> MAP = initMap();

	/** Constructeur privé */
	private StringUtils() { }

	/**
	 * Initialisation du tableau de correspondance entre les caractéres accentués et leur homologues non accentués
	 * @return La table de caractères initialisée
	 */
	private static List<String> initMap() {
		List<String> result = new ArrayList<String>();

		String car  = new String("A");
		result.add(car);            /* '\u00C0'   À   alt-0192  */
		result.add(car);            /* '\u00C1'   Á   alt-0193  */
		result.add(car);            /* '\u00C2'   Â   alt-0194  */
		result.add(car);            /* '\u00C3'   Ã   alt-0195  */
		result.add(car);            /* '\u00C4'   Ä   alt-0196  */
		result.add(car);            /* '\u00C5'   Å   alt-0197  */
		car = new java.lang.String("AE");
		result.add(car);            /* '\u00C6'   Æ   alt-0198  */
		car = new java.lang.String("C");
		result.add(car);            /* '\u00C7'   Ç   alt-0199  */
		car = new java.lang.String("E");
		result.add(car);            /* '\u00C8'   È   alt-0200  */
		result.add(car);            /* '\u00C9'   É   alt-0201  */
		result.add(car);            /* '\u00CA'   Ê   alt-0202  */
		result.add(car);            /* '\u00CB'   Ë   alt-0203  */
		car = new java.lang.String("I");
		result.add(car);            /* '\u00CC'   Ì   alt-0204  */
		result.add(car);            /* '\u00CD'   Í   alt-0205  */
		result.add(car);            /* '\u00CE'   Î   alt-0206  */
		result.add(car);            /* '\u00CF'   Ï   alt-0207  */
		car = new java.lang.String("D");
		result.add(car);            /* '\u00D0'   Ð   alt-0208  */
		car = new java.lang.String("N");
		result.add(car);            /* '\u00D1'   Ñ   alt-0209  */
		car = new java.lang.String("O");
		result.add(car);            /* '\u00D2'   Ò   alt-0210  */
		result.add(car);            /* '\u00D3'   Ó   alt-0211  */
		result.add(car);            /* '\u00D4'   Ô   alt-0212  */
		result.add(car);            /* '\u00D5'   Õ   alt-0213  */
		result.add(car);            /* '\u00D6'   Ö   alt-0214  */
		car = new java.lang.String("*");
		result.add(car);            /* '\u00D7'   ×   alt-0215  */
		car = new java.lang.String("0");
		result.add(car);            /* '\u00D8'   Ø   alt-0216  */
		car = new java.lang.String("U");
		result.add(car);            /* '\u00D9'   Ù   alt-0217  */
		result.add(car);            /* '\u00DA'   Ú   alt-0218  */
		result.add(car);            /* '\u00DB'   Û   alt-0219  */
		result.add(car);            /* '\u00DC'   Ü   alt-0220  */
		car = new java.lang.String("Y");
		result.add(car);            /* '\u00DD'   Ý   alt-0221  */
		car = new java.lang.String("Þ");
		result.add(car);            /* '\u00DE'   Þ   alt-0222  */
		car = new java.lang.String("B");
		result.add(car);            /* '\u00DF'   ß   alt-0223  */
		car = new java.lang.String("a");
		result.add(car);            /* '\u00E0'   à   alt-0224  */
		result.add(car);            /* '\u00E1'   á   alt-0225  */
		result.add(car);            /* '\u00E2'   â   alt-0226  */
		result.add(car);            /* '\u00E3'   ã   alt-0227  */
		result.add(car);            /* '\u00E4'   ä   alt-0228  */
		result.add(car);            /* '\u00E5'   å   alt-0229  */
		car = new java.lang.String("ae");
		result.add(car);            /* '\u00E6'   æ   alt-0230  */
		car = new java.lang.String("c");
		result.add(car);            /* '\u00E7'   ç   alt-0231  */
		car = new java.lang.String("e");
		result.add(car);            /* '\u00E8'   è   alt-0232  */
		result.add(car);            /* '\u00E9'   é   alt-0233  */
		result.add(car);            /* '\u00EA'   ê   alt-0234  */
		result.add(car);            /* '\u00EB'   ë   alt-0235  */
		car = new java.lang.String("i");
		result.add(car);            /* '\u00EC'   ì   alt-0236  */
		result.add(car);            /* '\u00ED'   í   alt-0237  */
		result.add(car);            /* '\u00EE'   î   alt-0238  */
		result.add(car);            /* '\u00EF'   ï   alt-0239  */
		car = new java.lang.String("d");
		result.add(car);            /* '\u00F0'   ð   alt-0240  */
		car = new java.lang.String("n");
		result.add(car);            /* '\u00F1'   ñ   alt-0241  */
		car = new java.lang.String("o");
		result.add(car);            /* '\u00F2'   ò   alt-0242  */
		result.add(car);            /* '\u00F3'   ó   alt-0243  */
		result.add(car);            /* '\u00F4'   ô   alt-0244  */
		result.add(car);            /* '\u00F5'   õ   alt-0245  */
		result.add(car);            /* '\u00F6'   ö   alt-0246  */
		car = new java.lang.String("/");
		result.add(car);            /* '\u00F7'   ÷   alt-0247  */
		car = new java.lang.String("0");
		result.add(car);            /* '\u00F8'   ø   alt-0248  */
		car = new java.lang.String("u");
		result.add(car);            /* '\u00F9'   ù   alt-0249  */
		result.add(car);            /* '\u00FA'   ú   alt-0250  */
		result.add(car);            /* '\u00FB'   û   alt-0251  */
		result.add(car);            /* '\u00FC'   ü   alt-0252  */
		car = new java.lang.String("y");
		result.add(car);            /* '\u00FD'   ý   alt-0253  */
		car = new java.lang.String("þ");
		result.add(car);            /* '\u00FE'   þ   alt-0254  */
		car = new java.lang.String("y");
		result.add(car);            /* '\u00FF'   ÿ   alt-0255  */
		result.add(car);            /* '\u00FF'       alt-0255  */

		return result;
	}

	/** Transforme une chaine pouvant contenir des accents dans une version sans accent
	 *  @param chaine Chaine a convertir sans accent
	 *  @return Chaine dont les accents ont été supprimé
	 **/
	public static String removeStresses(String chaine) {
		StringBuffer result = new StringBuffer(chaine);

		for (int bcl = 0; bcl < result.length(); bcl++) {
			int carVal = chaine.charAt(bcl);
			if (carVal >= MIN && carVal <= MAX) {
				// Remplacement
				String newVal = (String) MAP.get(carVal - MIN);
				result.replace(bcl, bcl + 1, newVal);
			}
		}
		return result.toString();
	}
}
