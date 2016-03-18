package myCode;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.imgscalr.Scalr;

public class Capture extends Thread{
	
	int numberC;
	int percentageC;
	String filetypeC;
	String outstreamC;
	
	
	public Capture(int innum,int inper,String infil,String inoutsrt)
	{
		numberC = innum;
		percentageC = inper;
		filetypeC = infil;
		outstreamC = inoutsrt;
	}
	
	public static BufferedImage capture(int resize,Robot robot, Rectangle screenrect){
		//try {
			//Thread.sleep(10);	
		//} catch(NumberFormatException | InterruptedException nfe) {
		//	System.exit(1);
		//}
		// determine current screen size
		// 	create screen shot
			
			//BufferedImage image = null;
			BufferedImage image = robot.createScreenCapture(screenrect);
			//double rescale = (double)screenSize.width*(double)resize/100.;
			if(screenrect.getWidth()>screenrect.getHeight()){
				image = Scalr.resize(image,((int)screenrect.getWidth()*resize)/100);}
			else{
				image = Scalr.resize(image,((int)screenrect.getHeight()*resize)/100);
			}
			return image;
		//return null;
		
	
	
	// save captured image to PNG file
	
	// use System.exit if the program hangs after writing the file;
	// that's an old bug which got fixed only recently
	// System.exit(0); 
	}
	
	public static byte[] TakeShot(int number, int percentage, String filetype, String outstream, Robot robot, Rectangle rect){
		
		BufferedImage out = capture(percentage,robot, rect);
		/*try{					
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//ImageIO.write( out, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
			}catch(IOException e){
				System.out.println(e.getMessage());
			}*/
		return null;		
	}

	@Override
	public void run() {
		//TakeShot(numberC,percentageC,filetypeC,outstreamC);
		
	}
}


















