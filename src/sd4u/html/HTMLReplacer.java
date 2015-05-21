package sd4u.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class HTMLReplacer {
	
	//this class replaces all relative paths(css,js) with full path to use at htmleditor.
	public String htmlGetter(File file){
		System.out.println(file.toString());
		
		String initData = "";
		String  thisLine = null;
		
		BufferedReader br=null;
		try {
			br = new BufferedReader( new FileReader(file) );
			while ((thisLine = br.readLine()) != null) {
				//System.out.println(thisLine);
				initData += thisLine + "\n";
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return toReplacedHTML(initData);
		
	}
	
	public String htmlGetter(String initData){
		return toReplacedHTML(initData);
	}
	
	public String toReplacedHTML(String initData){
		
		int initialIndex = 0;
		int inIndex;
		int finalIndex;
		StringBuffer sb = null;
		
		sb = new StringBuffer(initData);
		
		while( (initialIndex = sb.indexOf("href=",initialIndex)) >= 0){
			initialIndex += 6;
			inIndex = initialIndex;
			while( sb.charAt(inIndex) == '/' || sb.charAt(inIndex) == '.')
				inIndex++;
			inIndex--;
			finalIndex = sb.indexOf(".css",inIndex) + 4;
			//System.out.println("--->" + sb.substring((inIndex) , (finalIndex)) + " ??end");
			//System.out.println("-> "+getClass().getResource("/WebContent"+sb.substring((inIndex) , (finalIndex))).toString());
			URL url = getClass().getResource("/WebContent"+sb.substring((inIndex) , (finalIndex)));
			if(url==null)
				continue;
			sb.replace(initialIndex, finalIndex, url.toString());
		} 
		
		initialIndex = 0;
		while( (initialIndex = sb.indexOf("src=",initialIndex)) >= 0){
			initialIndex += 5;
			inIndex = initialIndex;
			while( sb.charAt(inIndex) == '/' || sb.charAt(inIndex) == '.')
				inIndex++;
			inIndex--;
			finalIndex = sb.indexOf(".js", inIndex) + 3;
			//System.out.println("--->" + sb.substring((inIndex) , (finalIndex)));
			//System.out.println("-> "+getClass().getResource("/WebContent"+sb.substring((inIndex) , (finalIndex))).toString());
			URL url = getClass().getResource( "/WebContent"+sb.substring( (inIndex) , (finalIndex) ) );
			if(url==null || url.toString().equals("null"))
				continue;
			sb.replace(initialIndex, finalIndex, url.toString());
		}
		
		return sb.toString();
		
	}
	
}

