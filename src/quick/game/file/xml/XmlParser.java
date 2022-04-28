package quick.games.file.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import quick.game.core.scene.XmlReaderState;

public class XmlParser extends DefaultHandler {

   boolean floor = false;
   boolean wall = false;
   boolean light = false;
   boolean region = false;
   XmlReaderState reader;

   public void init(@notNull XmlReaderState xml){
       this.reader = xml;
   }

   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       if (qName.equals("floor")) {
           reader.floor(attributes.getValue("coords"));
       }
       else if (qName.equals("wall")) {
           reader.wall(attributes.getValue("coords"));
       }
       else if (qName.equals("light")) {
           reader.light(reader.floor(attributes.getValue("coords")););
       }
       else if (qName.equals("region")) {
           reader.region(reader.floor(attributes.getValue("coords")););
       }
   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
      
   }

   @Override
   public void characters(char ch[], int start, int length) throws SAXException {}
}
