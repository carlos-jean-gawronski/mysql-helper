package br.com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class CredentialReader {

	public Properties getVariables() {
		Properties prop = new Properties();
		File credentialFile = new File("credentials.txt");
		Scanner reader;
		try {
			reader = new Scanner(credentialFile);
			while (reader.hasNextLine()) {
				String str = reader.nextLine();
				
				if(str.startsWith("DB_USER")){
					prop.put(str.substring(0, 7), str.substring(8));					
				} else if(str.startsWith("DB_PASSWORD")) {
					prop.put(str.substring(0, 11), str.substring(12));
				}else if(str.startsWith("DB_NAME")) {
					prop.put(str.substring(0, 7), str.substring(8));
				}
				else if(str.startsWith("SCOPE")) {
					prop.put(str.substring(0, 5), str.substring(6));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
