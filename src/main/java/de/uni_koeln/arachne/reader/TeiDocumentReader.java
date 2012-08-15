package de.uni_koeln.arachne.reader;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class TeiDocumentReader {

	private String filePath;
	private Document document;
	
	public TeiDocumentReader(String filePath) {
		this.filePath = filePath;
	}
	
	public void read() throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		this.document = builder.build(this.filePath);
	}
	
	public Document getDocument() {
		return this.document;
	}
}
