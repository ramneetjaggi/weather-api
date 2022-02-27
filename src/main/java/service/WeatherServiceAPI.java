package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Temperature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherServiceAPI {

    public void getMaxTemp(List<String> coordinates, int noOfDays, String metrics)
    {
        //Creating a JSONParser object
        JSONParser jsonParser = new JSONParser();

        try {
            String urlString;
            URLConnection conn;
            BufferedReader reader;
            //Getting API key
            DocumentBuilderFactory dbfaFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbfaFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse("./src/main/resources/api_keys.xml");
            String API_KEY = doc.getElementsByTagName("key").item(0).getTextContent();
            DecimalFormat df = new DecimalFormat("#.####");

            //Getting temperature for days
            for (int i = 1; i <= noOfDays; i++) {

                //Getting Epoch value of current date minus the number of days
                Instant queryDay = Instant.now().minus(Duration.ofDays(i));
                DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .withZone(ZoneId.systemDefault());

                Long queryTimestamp = queryDay.getEpochSecond();
                urlString = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat="
                        + df.format(Double.parseDouble(coordinates.get(1).trim())) + "&lon="
                        + df.format(Double.parseDouble(coordinates.get(0).trim()))
                        + "&dt=" + queryTimestamp
                        + "&appid=" + API_KEY + "&units="+metrics;

                URL url = new URL(urlString);
                //System.out.println(url);
                conn = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                // Parse JSON
                JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
                JSONArray hourlyTempList = (JSONArray) jsonObject.get("hourly");
                List<Double> temperature = new ArrayList<Double>();
                // Loop through each item containing hourly list of temperatures
                for (Object o : hourlyTempList) {
                    JSONObject forecast = (JSONObject) o;
                    if (forecast.get("temp") instanceof Number) {
                        // Adding temperature for each day -hourly
                        Double tempDouble = ((Number) forecast.get("temp")).doubleValue();
                        temperature.add(tempDouble);
                    }

                }
                System.out.println("On Date:"+DATE_TIME_FORMATTER.format(queryDay) +", Max Temperature is: "+ Collections.max(temperature));
            }
        }
        catch(FileNotFoundException e )
            {
                e.printStackTrace();
            }
        catch(IOException e )
            {
                e.printStackTrace();
            }
        catch(ParseException e )
            {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
