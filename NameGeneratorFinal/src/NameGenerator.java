import java.util.ArrayList;
import java.io.*;

import java.util.ArrayList;
import java.io.*;
import javax.swing.*;



public class NameGenerator {
	
	
	
	public static int MAX_LINES=600; 
	String fileName;
	String[] lines = new String[MAX_LINES];  
	int nLines;
	private String pathName;
	private int numberOfNames;
	ArrayList<String> names = new ArrayList();
	private String error;
	
	public NameGenerator() {
		
	}
	//sets the error message
	public void setError(String s) {
		error =s;
	}
	public void setPathName(String s) {
		pathName =s;
	}
	public void setNumberofNames(int i) {
		numberOfNames = i;
	}
	
	//constructor that processes the specified file name
	public NameGenerator(String fName) {
		nLines = 0;
		for (int j = 0; j < MAX_LINES; j++) {
			lines[j] = null;
		}
		fileName = fName; //file name
		read(fName);
	}
	
	//gets a random number of lines from the text file based on the integer argument
	public void randomLine() {
		for (int i =0; i <numberOfNames;i++) {
			int r = getRandomInteger(nLines);
			names.add(lines[r]);
		}
	}
	
	//returns a random integer
	public static int getRandomInteger (int max) {
		int randomInteger = (int) (Math.random() * max);
		return randomInteger;
	}
	
	//method that writes text to a specific text file
	public void writeText(ArrayList<String> s) {
		try {
			Writer output = null;
			String userHomeFolder = System.getProperty("user.home");
			File file = new File(userHomeFolder, "names.txt");
			output = new BufferedWriter(new FileWriter(file));
			for(int i =0; i <s.size();i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(this.names.get(i) + ", ");
				output.write(sb.toString());
			}
			output.close();
		} catch(IOException e) {
			this.setError(e.getMessage());
		}
		
	}

	private void read(String fileName) {
		try{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			nLines = 0;
			while ((strLine = br.readLine()) != null) 	{
				lines[nLines++] = strLine;
				if (nLines >= MAX_LINES) {
					System.err.println("Lines array full!");
					break;
				}
			}
			in.close();

		} catch (Exception e) {//Catch exception if any
			this.setError("Error: " + e.getMessage());
		}
	}
}
