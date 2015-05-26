package sd4u.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
/**
 * This class reads data and stores to use adding html fragment.
 * @author Halil Cetiner & Y. Hakan Kalayci
 *
 */
public class HTMLReader {
	
	/**
	 * This method reads data returns the code as String
	 * @param url this path for reading data to use adding code fragment 
	 * @return returnValue full data from stated file
	 */
	public static String readFile( URL url ){
		
		url = UrlConverter.toJava(url);
		
		if(url==null)
			return "";
		
		File file = new File(url.getPath());
		
		String returnValue="";
		String thisLine = null;
		try{
			BufferedReader br = new BufferedReader( new FileReader(file) );
			while ((thisLine = br.readLine()) != null) {
				//System.out.println(thisLine);
				returnValue += thisLine +"\n";
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnValue;
		
	}
	/**
	 * This data reads file and adds them into an ArrayList line by line
	 * @param url path of file which will be read
	 * @param al ArrayList which provides saving file line by line
	 */
	public static void readFile( URL url,ArrayList<String> al){
		
		File file = new File(url.getPath());
		
		String returnValue="";
		String thisLine = null;
		try{
			BufferedReader br = new BufferedReader( new FileReader(file) );
			while ((thisLine = br.readLine()) != null) {
				//System.out.println(thisLine);
				al.add(thisLine);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
