package main.java.app;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class TextFileParser {
	
	private String filePath = "/Users/reneeslamka/dynLeapLearning/NameGenerator/ListNames.txt"; 
	
	public TextFileParser(String filePath) {
		//this.filePath = filePath; 
	}
	
	public String[] openFile() throws IOException {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader textReader = new BufferedReader(fileReader);
		int numLines = 13;
		String[] textData = new String[numLines]; 
		
		for (int i = 0; i < numLines; i++) {
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
}
