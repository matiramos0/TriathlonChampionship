package XML;

import Model.Athlete.Amateur;
import Model.Athlete.Athlete;
import Model.Athlete.Competence;
import Model.Athlete.Stats;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLCharge {

    public static void chargeTriathlon(List<Athlete> athletes) {

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

                //String city = element.getElementsByTagName("ciudad").item(0).getTextContent();
                //String country = element.getElementsByTagName("pais").item(0).getTextContent();
                //String date = element.getElementsByTagName("fecha").item(0).getTextContent();

            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
