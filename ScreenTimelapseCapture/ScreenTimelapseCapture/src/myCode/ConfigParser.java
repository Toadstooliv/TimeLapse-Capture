package myCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigParser {
	
	public static String[] Parse(File config){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(config));
			String line = null;
			String[] out = new String[5];
			int i = 0;
			 while ((line = reader.readLine()) != null) {
				 out[i] = line;
				 i+=1;
			 }
			 reader.close();
			 return out;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
		
		
	}

}
