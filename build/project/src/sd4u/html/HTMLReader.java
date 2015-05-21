package sd4u.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

public class HTMLReader {
	
	/*
	 * This method reads data and stores to use adding html fragment.
	 * @author Yusuf Hakan Kalayci
	 * @param path this path for reading data to use adding code fragment 
	 * @return read full data from stated file
	 */
	public static String readFile( URL url ){
		
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
