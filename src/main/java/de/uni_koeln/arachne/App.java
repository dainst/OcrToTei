package de.uni_koeln.arachne;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import de.uni_koeln.arachne.builder.TeiFragmentBuilder;
import de.uni_koeln.arachne.builder.TeiOcrBuilder;
import de.uni_koeln.arachne.reader.DirectoryReader;
import de.uni_koeln.arachne.reader.PdfOcrReader;
import de.uni_koeln.arachne.reader.TeiDocumentReader;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting OrcToTei" );
        String directorypath = args[0];
        String teiDocumentPath = args[1];
        System.out.println("Reading directory " + directorypath);
        
        // read the file directory
        DirectoryReader directoryReader = new DirectoryReader(directorypath);
		directoryReader.read();
		
		// create a PdfOcrReader object
        PdfOcrReader pdfOcrReader = new PdfOcrReader();
        
        try {
        	// get the Tei-Document
			TeiDocumentReader teiDocumentReader = new TeiDocumentReader(teiDocumentPath);
			teiDocumentReader.read();
			
			// get the bouldet for the TEI-Document to update
			TeiOcrBuilder teiOcrBuilder = new TeiOcrBuilder(teiDocumentReader.getDocument());
			
			// iterate over all files in the directory
        	for(String filePath : directoryReader.getFilePathList()) {
        		// read the OCR text
        		pdfOcrReader.readFile(filePath);
				pdfOcrReader.readOcrText();
				
				// build a TEI fragment for this file
				TeiFragmentBuilder teiFragmentBuilder = new TeiFragmentBuilder(pdfOcrReader.getLineList());
				teiFragmentBuilder.build();
				
				// ingest fragment in the original DOM
				if(teiOcrBuilder.hasNextNode()) {
					teiOcrBuilder.updateNextNode(teiFragmentBuilder.getMainDiv());
				}
        	}
        	
        	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        	//outputter.output(teiDocumentReader.getDocument(), System.out);
        	outputter.output( teiDocumentReader.getDocument(), new FileOutputStream(new File(teiDocumentPath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
    }
}
