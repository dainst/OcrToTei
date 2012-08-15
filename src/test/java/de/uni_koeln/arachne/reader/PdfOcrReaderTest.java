package de.uni_koeln.arachne.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PdfOcrReaderTest extends TestCase {

	public PdfOcrReaderTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new TestSuite( PdfOcrReaderTest.class );
	}
	
	public void testPdfOcrReader() {
		PdfOcrReader pdfOcrReader = new PdfOcrReader();
		try {
			pdfOcrReader.readFile("/Users/istari/Documents/OcrTestDaten/165487/0012.pdf");
			pdfOcrReader.readOcrText();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
