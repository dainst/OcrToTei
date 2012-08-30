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
			//pdfOcrReader.readFile("/Users/clemens/Documents/OCR_Arachne/880098/1.Braun_an_Gerhard1832-35Teil150.pdf");
			pdfOcrReader.readFile("/Users/clemens/Documents/OCR_Arachne/156526/0008.pdf");
			pdfOcrReader.readOcrText();
			System.out.println(pdfOcrReader.getLineList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
