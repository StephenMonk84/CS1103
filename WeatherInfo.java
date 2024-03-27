import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/*This class accesses https://open-meteo.com this is a free site that doesn't require any keys */

public class WeatherInfo {
    /*This is the class tha makes the connection and retrieves the data in a JSON format */

    private static List<String> prevLoc = new ArrayList<>();

    public static JSONObject getWeatherData(String locationName){
        /*This creates then connects to the weather service */
        
        /*This gets the location data based on the geolocation API */
        JSONArray locationData = getLocationData(locationName);

        /*These two lines add the latitude and longitude to the JSOn object */
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        /*This builds the API request using the cooridnates */
        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" + longitude +
                "&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=America%2FLos_Angeles";

        try{
            /*This attempts to connect to the service by creating a connection */
            HttpURLConnection conn = fetchApiResponse(urlString);

            /*This checks for a response from the server
             * since we need response 200 to continue working 
             * This just checks for response 200 and returns null if any 
             * response other than 200 is received
             */
            if(conn.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
                return null;
            }

            /*This creates a new scanner object
             * The reads in the data
             */
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while(scanner.hasNext()){
                resultJson.append(scanner.nextLine());
            }

            /*This closes the scanner, and then closes the connection */
            scanner.close();
            conn.disconnect();

            /*These lines are used to parse the raw data */
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            /*This gets the data using hourly intervals */
            JSONObject hourly = (JSONObject) resultJsonObj.get("hourly");

            
            /*This retrieves the current hour, and its index from the site */
            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            /*This gets the current temperature of the selected location. */
            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            /*This gets the weathercodes ie) cloudy, clear etc. */
            JSONArray weathercode = (JSONArray) hourly.get("weathercode");
            String weatherCondition = convertWeatherCode((long) weathercode.get(index));

            /*This gets the relative humidty */
            JSONArray relativeHumidity = (JSONArray) hourly.get("relativehumidity_2m");
            int humidity = (int) relativeHumidity.get(index);

            /*This gets the windspeed */
            JSONArray windspeedData = (JSONArray) hourly.get("windspeed_10m");
            double windspeed = (double) windspeedData.get(index);

            /*This builds the final JSON weather object that will be used */
            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature", temperature);
            weatherData.put("weather_condition", weatherCondition);
            weatherData.put("humidity", humidity);
            weatherData.put("windspeed", windspeed);

            return weatherData;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

      
       public static JSONArray getLocationData(String locationName){
        /*This method converts the location's name ie) Los Angeles to its coordinates */
        prevLoc.add(locationName);
        /*The API doesn't accept whitespace, instead taking + instead */
        locationName = locationName.replaceAll(" ", "+");

        /*This builds the API url for connecting to the API to get the location for the specified location */
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";

        try{
            /*This tries to connect to the service that gets a location based on its name */
            HttpURLConnection conn = fetchApiResponse(urlString);

            /*This checks which http response is received
             * This program needs the response 200 to proceed, so its only checking for 200
             */
            if(conn.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
                return null;
            }else{
                /*This creates a scanner and object to store the data */
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                /*This reads and stores all the data into JSON */
                while(scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }

                /*This closes the scanner and connection once they are done being used. */
                scanner.close();
                conn.disconnect();

                /*This parses the JSON string into a JSON object */
                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                /*This gets the location data from the API, based on the location name
                 * and returns it for use
                  */
                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        /*This is what happens if the location cannot be found */
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString){
        /*This method gets the API response */
        try{
            /*This creates the connection */
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //This uses REST API Get request
            conn.setRequestMethod("GET");

            /*This connects to the API */
            conn.connect();
            return conn;
        }catch(IOException e){
            e.printStackTrace();
        }

        /*This means it was unable to create the connection */
        return null;
    }

    private static int findIndexOfCurrentTime(JSONArray timeList){
        /*This finds the timezone of the local machine */
        String currentTime = getCurrentTime();

        /*This is for the traversal of the list of timezones */
        for(int i = 0; i < timeList.size(); i++){
            String time = (String) timeList.get(i);
            if(time.equalsIgnoreCase(currentTime)){
                return i;
            }
        }
        return 0;
    }

    private static String getCurrentTime(){
        /*This gets the current date and time */
        LocalDateTime currentDateTime = LocalDateTime.now();

        /*This sets the format to the ISO standard that API accepts */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        /*This formats the datetime object into a string */
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }

    
    private static String convertWeatherCode(long weathercode){
        /*This converts the weather codes into human readable information */
        String weatherCondition = "";
        if(weathercode == 0L){
            weatherCondition = "Clear";
        }else if(weathercode > 0L && weathercode <= 3L){
            weatherCondition = "Cloudy";
        }else if((weathercode >= 51L && weathercode <= 67L)
                    || (weathercode >= 80L && weathercode <= 99L)){
            weatherCondition = "Rain";
        }else if(weathercode >= 71L && weathercode <= 77L){
            weatherCondition = "Snow";
        }
        return weatherCondition;
    }
    
    public static String getPrevLoc(){
        /*Currently configured to return the previous location's name */
        int size = prevLoc.size();
        String temp = prevLoc.get(size-1);
        return temp;

    }
    //create get previous search method
}
