package Business;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, SAXException, ParserConfigurationException  {
		AppManager appManager = new AppManager();
		appManager.run();
	}

}
