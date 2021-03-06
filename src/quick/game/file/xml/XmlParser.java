package quick.game.file.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import quick.game.core.scene.XmlReaderState;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.jme3.scene.Node;

public class XmlParser {

   public static void parse(String location, XmlReaderState xmlReader) {

      try {
         File inputFile = new File(location);
         SAXParserFactory SAXFactory = SAXParserFactory.newInstance();
         SAXParser saxParser = SAXFactory.newSAXParser();
         UserHandler userhandler = new UserHandler();
         userhandler.init(xmlReader);
         saxParser.parse(inputFile, userhandler);     
      } catch (Exception e) {
          System.out.println("Ignoring exception while parsing Xml:");
          e.printStackTrace();
      }
   }   
}


class UserHandler extends DefaultHandler {

   boolean floor = false;
   XmlReaderState reader;

   public void init(XmlReaderState xmlReader){
       this.reader = xmlReader;
   }

   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       qName = qName.toLowerCase();
       if (qName.equals("floor")) {
           reader.floor(attributes.getValue("coords"));
       }
       else if (qName.equals("wall")) {
           reader.wall(attributes.getValue("coords"));
       }
       else if (qName.equals("light")) {
           reader.light(attributes.getValue("direction"));
       }
       else if (qName.equals("region")) {
           reader.region(attributes.getValue("loc"), attributes.getValue("name"));
       }
       else if (qName.equals("fence")) {
           reader.fence(attributes.getValue("loc"), attributes.getValue("scale"));
       }
       else if (qName.equals("node")) {
           reader.fence(attributes.getValue("loc"), attributes.getValue("scale"));
       }
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      
   }

   @Override
   public void characters(char ch[], int start, int length) throws SAXException {}
}
