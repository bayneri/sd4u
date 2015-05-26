package sd4u.html;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlConverter {
	
	public static URL toHTML(URL url){
		try {
			return new URL( url.toString().replaceAll(" ", "%20") );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static URL toJava(URL url){
		try {
			return new URL( url.toString().replaceAll("%20", " ") );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String toHTML(String url){
		
		return url.replaceAll(" ", "%20");

	}
	public static String toJava(String url){

		return url.replaceAll("%20", " ");
		
	}
	
}
