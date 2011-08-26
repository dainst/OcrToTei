package de.uni_koeln.arachne.builder;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

public class TeiOcrBuilder {

	private Document teiDocument;
	//private int divCount = 0;
	private Iterator<?> nodeListIterator;
	
	public TeiOcrBuilder(Document teiDocument) {
		this.teiDocument = teiDocument;
		this.divCounter();
	}
	
	public boolean hasNextNode() {
		return this.nodeListIterator.hasNext();
	}
	
	public void updateNextNode(Element containerDiv) throws JDOMException {
		if(this.nodeListIterator.hasNext()) {
			Object nodeObject = this.nodeListIterator.next();
			Element node = (Element)nodeObject;
			List<?> pList = containerDiv.getChildren();

			Iterator<?> pIterator = pList.iterator();
			while(pIterator.hasNext()) {
				Object pObject = pIterator.next();
				Element p = (Element)pObject;
				Element newElementP = new Element("p", node.getNamespace());
				newElementP.setAttribute("id", "p." + UUID.randomUUID().toString(), Namespace.XML_NAMESPACE);
				newElementP.setText(p.getText());
				node.addContent(newElementP);
			}
		}
	}
	
	private void divCounter() {
		try {
			XPath xPath = XPath.newInstance("tei:TEI/tei:text/tei:body/tei:div");
			xPath.addNamespace(Namespace.getNamespace("tei", "http://www.tei-c.org/ns/1.0"));
			List<?> nodeList = xPath.selectNodes(this.teiDocument);
			//this.divCount = nodeList.size();
			this.nodeListIterator = nodeList.iterator();
			
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	public void testOutput() throws IOException {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		outputter.output(this.teiDocument, System.out);
	}
}
