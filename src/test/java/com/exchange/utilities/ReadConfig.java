package com.exchange.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		File src =  new File("./Configuration/config.properties");
		
		try {
			
			FileInputStream fis = new FileInputStream(src);			
			pro = new Properties();
			pro.load(fis);
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found " + e.getMessage());			
		} catch (IOException e) {			
			System.out.println("IO Exception " + e.getMessage());
		}		
	}	
	public String getApplicationURL() {
		return pro.getProperty("baseURL");
	}
	
	public String getChromePath() {
		return pro.getProperty("chromepath");
	}
	
	public String getFireFoxPath() {
		return pro.getProperty("firefoxPath");
	}
	
	public String getIEPath() {
		return pro.getProperty("iePath");
	}
}
