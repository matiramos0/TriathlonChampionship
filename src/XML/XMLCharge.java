package XML;

import Model.Athlete.Amateur;
import Model.Athlete.Athlete;
import Model.Athlete.Competence;
import Model.Athlete.Stats;
import Model.City.City;
import Model.City.Country;
import Model.Discipline.Cycling;
import Model.Discipline.DistanceDiscipline;
import Model.Discipline.Pedestrianism;
import Model.Discipline.Provisioning;
import Model.Discipline.Swimming;
import Model.Modality.Modality;
import Model.Modality.Modalities;
import Model.Race.Race;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLCharge {
	
	private static Set<String> setNationalities = new HashSet<>();
	private static Set<String> setModalities = new HashSet<>();  

    public static void chargeTriathlon(List<Athlete> athletes, List<Race> races) {

        try {

            InputStream xml = XMLCharge.class.getResourceAsStream("triatlon.xml");

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(xml);

            Element root = doc.getDocumentElement();

            final NodeList listAthletes = root.getElementsByTagName("atleta");

            for (int i = 0; i < listAthletes.getLength(); i++) {

                Element element = (Element) listAthletes.item(i);

                String number = element.getAttributeNode("numero").getValue();
                String name = element.getElementsByTagName("nombre").item(0).getTextContent();
                String last = element.getElementsByTagName("apellido").item(0).getTextContent();
                String nationality = element.getElementsByTagName("nacionalidad").item(0).getTextContent();
                String birthdate = element.getElementsByTagName("fechaNacimiento").item(0).getTextContent();
                String gender = element.getElementsByTagName("genero").item(0).getTextContent();
                String category = element.getElementsByTagName("categoria").item(0).getTextContent();

                int dni = Integer.parseInt(element.getElementsByTagName("dni").item(0).getTextContent());
                int rank = Integer.parseInt(element.getElementsByTagName("ranking").item(0).getTextContent());
                int weight = Integer.parseInt(element.getElementsByTagName("peso").item(0).getTextContent());
                int porcentageRacesCompleted = Integer.parseInt(element.getElementsByTagName("porcentajeCarrerasTerminadas").item(0).getTextContent());

                float swimming = Float.parseFloat(element.getElementsByTagName("aptitudNatacion").item(0).getTextContent());
                float cycling = Float.parseFloat(element.getElementsByTagName("aptitudCiclismo").item(0).getTextContent());
                float stoning = Float.parseFloat(element.getElementsByTagName("aptitudPedestrismo").item(0).getTextContent());
                float resistance = Float.parseFloat(element.getElementsByTagName("resistencia").item(0).getTextContent());
                float psychological = Float.parseFloat(element.getElementsByTagName("fortalezaPsicologica").item(0).getTextContent());

                float height = Float.parseFloat(element.getElementsByTagName("altura").item(0).getTextContent());

                double economy = Double.parseDouble(element.getElementsByTagName("presupuestoEconomico").item(0).getTextContent());
                
                setNationalities.add(nationality);

                Stats stats = new Stats(swimming, cycling, stoning, resistance, psychological);

                Athlete athlete = null;

                if (category.equals("Amateur")) {
                    athlete = new Amateur(number, name, last, nationality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, stats);
                } else if (category.equals("Competición")) {
                    athlete = new Competence(number, name, last, nationality, dni, porcentageRacesCompleted, weight, height, economy, birthdate, stats);
                }

                if (athlete != null) {
                    if (gender.equals("Masculino"))
                        athlete.setGender(Athlete.Gender.MALE);
                    else if (gender.equals("Feminino"))
                        athlete.setGender(Athlete.Gender.FEMALE);
                }

                athletes.add(athlete);

            }

            final NodeList raceList = root.getElementsByTagName("carrera");

            for (int i = 0; i < raceList.getLength(); i++) {

                Element element = (Element) raceList.item(i);

                String city = element.getElementsByTagName("ciudad").item(0).getTextContent();               
                String country = element.getElementsByTagName("pais").item(0).getTextContent();
                String date = element.getElementsByTagName("fecha").item(0).getTextContent();
                String descriptModality = element.getElementsByTagName("modalidad").item(0).getTextContent();
                
                float distanceSwimming = Float.parseFloat(element.getElementsByTagName("natacion").item(0).getTextContent());
                float distanceCycling = Float.parseFloat(element.getElementsByTagName("ciclismo").item(0).getTextContent());
                float distanceStoning = Float.parseFloat(element.getElementsByTagName("pedestrismo").item(0).getTextContent());
                
                DistanceDiscipline swimming = new DistanceDiscipline(distanceSwimming, new Swimming());
                DistanceDiscipline cycling = new DistanceDiscipline(distanceCycling, new Cycling());
                DistanceDiscipline stoning = new DistanceDiscipline(distanceStoning, new Pedestrianism());
                
                Modality modality = new Modality(swimming, cycling, stoning);
                
                if (Modalities.SPRINT.getDescription().equals(descriptModality))
                	modality.setModality(Modalities.SPRINT);
                else if (Modalities.OLYMPIC.getDescription().equals(descriptModality))
                	modality.setModality(Modalities.OLYMPIC);
                else if (Modalities.MIDDLE.getDescription().equals(descriptModality))
                	modality.setModality(Modalities.MIDDLE);
                else if (Modalities.LONG.getDescription().equals(descriptModality))
                	modality.setModality(Modalities.LONG);
                
                setModalities.add(descriptModality);
                
                NodeList listPoints = element.getElementsByTagName("puesto");
                
                Map <Integer, Provisioning> provisionings = new HashMap<>();
                
                for (int j = 0; j < listPoints.getLength(); j++) {
                	
                	Element elementChild = (Element) element.getElementsByTagName("puesto").item(j);
                	
                	String type = elementChild.getAttributeNode("tipo").getValue();
                	
                	int number = j+1;
                	
                	float distance = Float.parseFloat(elementChild.getElementsByTagName("distancia").item(0).getTextContent());
                	
                	Provisioning provisioning = null;
                	
                	if (type.equals("ciclismo"))
                		provisioning = new Provisioning(number, distance, new Cycling());
                	else if (type.equals("pedestrismo"))
                		provisioning = new Provisioning(number, distance, new Pedestrianism());
                	
                	provisionings.put(number, provisioning);
                		
                	//System.out.println(provisioning);
                	
                }
                
                //System.out.println();
                
                Race race = null;
                
                try {
					race = new Race(modality, new City(city, new Country(country)), provisionings);
				} catch (SQLException e) {
					e.printStackTrace();
				}
                
                races.add(race);
                
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
