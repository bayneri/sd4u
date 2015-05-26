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

/**
 * This class takes images to editor by url or file path 
 * @author H. Cetiner & Y.H. Kalayci
 */
public class ImageDownloader {
	
	public String downloadImage(String url,String path) throws WrongFormatException{
		
		//System.out.println("asdasdasdasd"+url);
		
		String format = getFormat(url);
		
		//System.out.println(format);
		
		if(format==null){
			throw new WrongFormatException();
		}
		
		//Image image = new Image(url);
		
		//final ImageView imageView = new ImageView(image);
		
		//StringBuffer sb = new StringBuffer(getClass().getResource("/WebContent/SubjectA/Images").toString());
		//sb.replace(0, "file:/".length()-1, "");
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		//Date date = new Date();
		
		//sb.append("/");
		//sb.append(dateFormat.format(date));
		StringBuffer sb = new StringBuffer(path);
		sb.append(".");
		sb.append(format);
		//System.out.println(sb.toString());
		
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
		
		/*BufferedImage image2 = SwingFXUtils.fromFXImage(image, null); // Get buffered image.
		BufferedImage imageRGB = new BufferedImage(image2.getWidth(), image2.getHeight(), BufferedImage.OPAQUE); // Remove alpha-channel from buffered image.
		//Graphics2D graphics = imageRGB.createGraphics();
		//graphics.drawImage(image, 0, 0, null);
		
		try {
			
			File output = new File( sb.toString() );
			
			if( output.exists() )
				output.createNewFile();
			
			ImageIO.write(SwingFXUtils.fromFXImage(imageView.snapshot(null, null), null), "png", output);
			
			//ImageIO.write(SwingFXUtils.fromFXImage(image, null), format, output);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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
