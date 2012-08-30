package de.uni_koeln.arachne.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

/**
 * 
 * @author istari
 *
 */
public class PdfOcrReader {

	private PdfReader pdfReader;
	private InputStream inputStream;
	private List<String> lineList;
	
	/**
	 * Constructor
	 */
	public PdfOcrReader() {
		this.pdfReader = null;
		this.inputStream = null;
		this.lineList = new ArrayList<String>();
	}
	
	public void readFile(String filename) throws FileNotFoundException {
		this.inputStream = new FileInputStream(filename);
	}
	
	public void readOcrText() throws IOException {
		this.lineList.clear();
		
		this.pdfReader = new PdfReader(this.inputStream);
		//String content = new String(pdfReader.getPageContent(1), Charset.forName("UTF-8"));
		SimpleTextExtractionStrategy simpleTextExtractionStrategy = new SimpleTextExtractionStrategy();
		String pageString = PdfTextExtractor.getTextFromPage(pdfReader, 1, simpleTextExtractionStrategy);
		//System.out.println(pageString);
		
		@SuppressWarnings("unused")
		boolean newLine = false;
		//String lineToken = null;
		
		//StringTokenizer stringTokenizer = new StringTokenizer(content, "\r");
		StringTokenizer stringTokenizer = new StringTokenizer(pageString, "\r");
		if(stringTokenizer.countTokens() < 2) {
			//stringTokenizer = new StringTokenizer(content, "\n");
			stringTokenizer = new StringTokenizer(pageString, "\n");
		}
		
		while(stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken();
			//System.out.println("-> " + token);
			lineList.add(token);
		}
		
		/*
		while(stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken();
			if(token.startsWith("BT")) {
				newLine = true;
				lineToken = null;
			}
			
			if(token.startsWith("(")) {
				int start = token.indexOf('(');
				int end = token.indexOf(')');
				if(lineToken == null) {
					lineToken = token.substring(start+1, end);
				} else {
					lineToken += token.substring(start+1, end);
				}
			}
			
			if(token.startsWith("ET")) {
				newLine = false;
				if(lineToken != null) {
				
				//System.out.println(new String(lineToken.getBytes(), Charset.forName("UTF-8")));
				}
				this.lineList.add(lineToken);
			}
		}
		
		
		if(lineList.isEmpty()) {
			lineList.add(lineToken);
		}
		*/
		
	}
	
	public List<String> getLineList() {
		return this.lineList;
	}
}
