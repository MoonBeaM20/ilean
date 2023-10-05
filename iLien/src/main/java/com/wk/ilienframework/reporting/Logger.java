package com.wk.ilienframework.reporting;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Logger {

	public String writeExceptionLogs(String message, String errorDetails) {

		try {
			String xmlName = Math.random() + ".xml";

			String xmlLocation = new Reporting().getErrorLogsLocation() + "/" + xmlName;

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("errorLogs");
			doc.appendChild(rootElement);

			// Custom message
			Element customMessage = doc.createElement("Message");
			customMessage.appendChild(doc.createTextNode(message));
			rootElement.appendChild(customMessage);

			// Error details log
			Element logs = doc.createElement("ExceptionLogs");
			logs.appendChild(doc.createTextNode(errorDetails));
			rootElement.appendChild(logs);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlLocation));

			transformer.transform(source, result);

			return xmlName;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		return null;
	}
}
