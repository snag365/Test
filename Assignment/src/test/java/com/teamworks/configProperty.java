package com.teamworks;

import java.io.*;
import java.util.*;

public class configProperty {
	
	Properties prop;
	
	public configProperty() {
		try {
			prop = readPropertiesFile(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String get(String key) {
		return prop.getProperty(key);
	}

	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}

