package sd4u.html;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class provides overcome url path differences between HTML and Java
 * @author Halil Cetiner & Y. Hakan Kalayci
 *
 */
public class UrlConverter {
	
	/**
	 * This method convert url path to use in HTML
	 * @param url path
	 * @return converted path
	 */
	public static URL toHTML(URL url){
		try {
			return new URL( url.toString().replaceAll(" ", "%20") );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * This method convert url path to use in Java
	 * @param url path
	 * @return converted path
	 */
	public static URL toJava(URL url){
		try {
			return new URL( url.toString().replaceAll("%20", " ") );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method returns convert url path to use in HTML in String format
	 * @param url path
	 * @return converted path in String
	 */
	public static String toHTML(String url){
		
		return url.replaceAll(" ", "%20");

	}
	/**
	 * This method returns convert url path to use in Java in String format
	 * @param url path
	 * @return converted path in String
	 */
	public static String toJava(String url){

		return url.replaceAll("%20", " ");
		
	}
	
}
