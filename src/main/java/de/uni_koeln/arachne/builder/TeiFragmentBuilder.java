package de.uni_koeln.arachne.builder;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * 
 * @author istari
 *
 */
public class TeiFragmentBuilder {

	private List<String> lineList;
	private Element mainDiv;
	
	/**
	 * Constructor
	 */
	public TeiFragmentBuilder(List<String> lineList) {
		this.lineList = lineList;
		this.mainDiv = new Element("mainDiv");
	}
	
	public void build() {
		for (String lineContent : lineList) {
			Element lineElement = new Element("p", Namespace.getNamespace("http://tei-c.org/ns/1.0"));
			lineElement.setAttribute("id", UUID.randomUUID().toString(), Namespace.XML_NAMESPACE);
			lineElement.addContent(lineContent);
			this.mainDiv.addContent(lineElement);
		}
	}
	
	public Element getMainDiv() {
		return this.mainDiv;
	}
	
	public void testOutput() throws IOException {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(this.mainDiv, System.out);
	}
	
}
