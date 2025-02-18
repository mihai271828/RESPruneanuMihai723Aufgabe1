package Service;

import Model.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

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


    public void getMinimalCap() {
        System.out.println("Please enter the minimum capacity:");
        Scanner sc = new Scanner(System.in);
        double minCap = Double.parseDouble(sc.nextLine());
        List<Event> matches = readXML();
        for(Event e:matches){
            if(e.getKraftpunkte()>=minCap){
                System.out.println(e.getKraftpunkte());
            }
        }
    }

    public void sortEvents(){
        List<Event> Matchs = readXML();
        List<Event> filteredMatchs = new ArrayList<>();
        for(Event c:Matchs){
            if(c.getStufe().equals(Event.Stufe.Jonin))
            {
                filteredMatchs.add(c);
            }
        }
        Collections.sort(filteredMatchs, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o2.getDatum().compareTo(o1.getDatum());
            }
        });
        for(Event e:filteredMatchs){
            System.out.println(e.getDatum()+ ": " + e.getCharaktername()+ " - " + e.getBeschreibung());
        }

    }


    public void anzahlProEvent() {

        try {
            List<Event> filteredCases = readXML();

            // Count one event per occurrence for each house
            Map<String, Integer> eventCount = new HashMap<>();
            for (Event event : filteredCases) {
                Event.Stufe Disease = event.getStufe();
                eventCount.put(String.valueOf(Disease), eventCount.getOrDefault(Disease, 0) + 1);
            }

            // Convert the map to a list for sorting
            List<Map.Entry<String, Integer>> sortedHouses = new ArrayList<>(eventCount.entrySet());
            Collections.sort(sortedHouses, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    int cmp = Integer.compare(o2.getValue(), o1.getValue()); // Descending by count
                    return cmp;
                }
            });

            // Build output lines in the format "House#Count"
            List<String> outputs = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : sortedHouses) {
                outputs.add(entry.getKey() + "#" + entry.getValue());
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/input/gesammtzahl.txt"))) {
                for (String output : outputs) {
                    bw.write(output);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


































}
