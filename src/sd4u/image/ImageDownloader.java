package sd4u.image;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import sd4u.html.UrlConverter;

/**
 * This class takes images to editor by url or file path 
 */
public class ImageDownloader {
	
	public String downloadImage(String url,String path) throws WrongFormatException{
		
		url = UrlConverter.toJava(url);
		
		String format = getFormat(url);
		
		if(format==null){
			throw new WrongFormatException();
		}
		
		StringBuffer sb = new StringBuffer(path);
		sb.append(".");
		sb.append(format);
		
		try {
			URL url2 = new URL(url);
			InputStream in = new BufferedInputStream(url2.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(sb.toString()));

			for ( int i; (i = in.read()) != -1; ) {
			    out.write(i);
			}
			in.close();
			out.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
		
	}
	
	/**
	 * This method detects format of the image
	 * @param url url or file path as string
	 * @return format of the image
	 */
	public String getFormat(String url){
		for(int i=url.length()-1,cnt=0;i>=0 && cnt<=5 ;i--,cnt++){
			if( url.charAt(i)=='.' )
				return url.substring(i+1);
		}
		return null;
	}
	
}
