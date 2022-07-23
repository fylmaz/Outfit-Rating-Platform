package DataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class FileIO {
	
	public void writeUserData(String XMLstring) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(XMLstring)));
            StreamResult file = new StreamResult(new File("UserData.xml"));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, file);
        }catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	public void writeOutfitData(JSONObject jsonObj) throws IOException {
		FileWriter  file = new FileWriter("OutfitData.json");
        file.write(jsonObj.toJSONString());
        file.close();
	}
	
	public Document readUserData() throws SAXException, IOException, ParserConfigurationException {
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);          
	      DocumentBuilder db = dbf.newDocumentBuilder();
          Document doc = db.parse(new File("UserData.xml"));
          doc.getDocumentElement().normalize();
          return doc; 
	}
	
	public JSONObject readOutfitData() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("OutfitData.json"));
		return (JSONObject) obj;

	}
	
}
