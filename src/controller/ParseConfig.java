package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseConfig {
	
	public String[] parseIBMiJSONConfig(String fileName, String tagName) {
		
		String output[] = new String[3];
		String outString = fileName;
		
		try {
			JSONParser parser = new JSONParser();
			Reader reader = new FileReader(outString);
			Object jsonObj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) jsonObj;
			JSONObject obj = new JSONObject();
			obj = (JSONObject) jsonObject.get(tagName);
			output[0] = (String) obj.get("host");
			output[1] = (String) obj.get("user");
			output[2] = (String) obj.get("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return output;
	}

	public String[] parseIBMiXMLConfig(String fileName, String tagName) {
		
		String output[] = new String[3];
		
		try {
			 File inputFile = new File(fileName);
			 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			 Document doc = dBuilder.parse(inputFile);
			 doc.getDocumentElement().normalize();
			 NodeList nList = doc.getElementsByTagName(tagName);
			 Node nNode = nList.item(0);

			 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				   Element eElement = (Element) nNode;
				   output[0] = eElement.getElementsByTagName("host").item(0).getTextContent();
				   output[1] = eElement.getElementsByTagName("user").item(0).getTextContent();
				   output[2] = eElement.getElementsByTagName("password").item(0).getTextContent();
			 }
			} catch (Exception e) {
		          e.printStackTrace();
          }
		return output;
	}
}