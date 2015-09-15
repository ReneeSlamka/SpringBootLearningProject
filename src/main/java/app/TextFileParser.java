package main.java.app;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.BufferedReader;

public class TextFileParser {
	
	private String filePath;
	public Logger log;
	
	public TextFileParser(String filePath, Logger log) {
		this.filePath = filePath; 
		this.log = log;
	}
	
	//TODO: split this up into more modular functions
	public String[] readFile() throws IOException {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader textReader = new BufferedReader(fileReader);
		int numLines = countLines();
		String[] textData = new String[numLines]; 
		
		for (int i = 0; i < numLines; i++) {
			textData[i] = textReader.readLine();
			log.info(textData[i]);
		}
		
		textReader.close();
		return textData;
	}
	
	public int countLines() throws IOException {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader textReader = new BufferedReader(fileReader);
		
		String singleLine;
		int numLines = 0;
		
		while ((singleLine = textReader.readLine()) != null) {
			numLines++;
		}
		
		textReader.close();
		return numLines;
	}
}
