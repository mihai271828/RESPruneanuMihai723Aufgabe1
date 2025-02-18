package Service;

import Model.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    public Service() {}
    public List<Event> readXML(){
        List<Event> events = new ArrayList<>();
        try {
            File file=new File("src/input/evenimente.xml");
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc=builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nl=doc.getElementsByTagName("log");
            for(int i=0;i<nl.getLength();i++){
                Node n=nl.item(i);
                if(n.getNodeType()==Node.ELEMENT_NODE){
                    Element bookElement=(Element)n;
                    int id=Integer.parseInt(bookElement.getElementsByTagName("Id").item(0).getTextContent());
                    String Charaktername=bookElement.getElementsByTagName("Charaktername").item(0).getTextContent();
                    Event.Stufe stufe= Event.Stufe.valueOf(bookElement.getElementsByTagName("Stufe").item(0).getTextContent());
                    String Beschreibung=bookElement.getElementsByTagName("Beschreibung").item(0).getTextContent();
                    LocalDate date=LocalDate.parse(bookElement.getElementsByTagName("Datum").item(0).getTextContent());
                    double Kraftpunkte=Double.parseDouble(bookElement.getElementsByTagName("Kraftpunkte").item(0).getTextContent());
                    events.add(new Event(id,Charaktername,stufe,Beschreibung,date,Kraftpunkte));
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return events;
    }





}
