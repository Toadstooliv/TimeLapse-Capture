package timelapseproject.myCode;


import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.imgscalr.Scalr;

public class Capture extends Thread{
	
	int numberC;
	int percentageC;
	String filetypeC;
	String outstreamC;
        ArrayList<BufferedImage> output;
	
	
	public Capture(int innum,int inper,String infil,String inoutsrt)
	{
		numberC = innum;
		percentageC = inper;
		filetypeC = infil;
		outstreamC = inoutsrt;
	}
        
        public Capture(ArrayList<BufferedImage> images, int frame)
        {
            output = images;
            numberC = frame;
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
	
	public static BufferedImage TakeShot(int number, int percentage, String filetype, String outstream, Robot robot, Rectangle rect){
		
		BufferedImage out = capture(percentage,robot, rect);				
			
		return out;		
	}

	@Override
	public void run() {
		for(int i=1; i<output.size();i++)
                {
                    try {
                        File outputfile = new File((i+numberC)+".jpg");
                        ImageIO.write(output.get(i), "jpg", outputfile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Capture.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Capture.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        
                    }
                }
		
	}
}


















