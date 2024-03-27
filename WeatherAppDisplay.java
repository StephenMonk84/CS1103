import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*The first import is for the Jar file that allows the program to handle JSON data
 * The weather API I have selected sends back JSON Data
 * 
 * 
 */



public class WeatherAppDisplay extends JFrame {

    private JSONObject weatherInfo;

    public WeatherAppDisplay(){
        /*This is the default constructor that is used to create the basic layout of our frame */

        //The JFrame Class has methods to set the Title of the app on the top
        super("Current Weather");

        /*This sets the default operation when the app closes
         * This setting closes the process so that it doesn't stay open
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //This sets the size of the Application in Pixels
        setSize(450, 900);

        //This selects the screen location where the app first launches, setting it to null launches the app in the center
        setLocationRelativeTo(null);

        //This selects the layout manager, to manually position components I'm passing the null value
        setLayout(null);

        //To prevent the window from being resized
        setResizable(false);

        //This calls one of the methods that puts all the components onto the JFrame
        addGuiComponents();
    }

    private void addGuiComponents(){
        /*This method is private because its only be accessed by this not class, and not any other classes
         * To use this in another class you will need to use the constructor
         */

         /*Creating the search text field and putting it on the JFrame
          * The following method calls set the size and font of the textbox
          */
         JTextField searchLocationField = new JTextField();
         searchLocationField.setBounds(15, 15, 300, 30);
         searchLocationField.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 16));
         add(searchLocationField);

         /*This label disaplys an image of the current weather conditions
          * I chose cloudy as the default because that is a safe bet in vancouver
          * The image location will need to be changed depending on the file structure.  
          */
         JLabel weatherCondImage = new JLabel(loadImage("src/assests/cloudy.png"));
        weatherCondImage.setBounds(0, 125, 450, 217);
        add(weatherCondImage);

        /*This is the label to display the current temperature, selected 0 celcius as the default temperature */
        JLabel tempDisplayText = new JLabel("0 C");
        tempDisplayText.setBounds(0, 350, 450, 54);
        tempDisplayText.setFont(new Font("Dialog", Font.BOLD, 48));
        tempDisplayText.setHorizontalAlignment(SwingConstants.CENTER);
        add(tempDisplayText);

        /*This label displays the weather condition, ie) sunny, cloudy, snowing etc. */
        JLabel weatherCondDesc = new JLabel("Cloudy");
        weatherCondDesc.setBounds(0,405,450,36);
        weatherCondDesc.setFont(new Font("Dialog", Font.BOLD, 32));
        weatherCondDesc.setHorizontalAlignment(SwingConstants.CENTER);

        /*This label displays the humidty in text form*/
        JLabel humidText = new JLabel("<html><b>Humidity</b></html>");
        humidText.setBounds(90,500,85,55);
        humidText.setFont(new Font("Dialog", Font.BOLD, 16));
        add(humidText);

        /*This label displays the humidity image */
        JLabel humidImage = new JLabel(loadImage("src/assests/humidity.png"));
        humidImage.setBounds(15,500,74,66);
        add(humidImage);

        /*This label displays the current windspeed */
        JLabel windSpeedText = new JLabel("<html><b> 0 km/h</b></html>");
        windSpeedText.setBounds(310,500,85,55);
        windSpeedText.setFont(new Font("Dialog", Font.ITALIC, 14));
        add(windSpeedText);

        /*This label displays the windspeed image */
        JLabel windImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windImage.setBounds(220,500,74,66);
        add(windImage);

        /*This Label displays Previous Searches */
        JLabel prevLoc = new JLabel("Nothing has been searched yet.");
        prevLoc.setBounds(15, 615, 450, 170);
        add(prevLoc);

        /*This creates the search button and defines some functionality */
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        searchButton.setBounds(375,13,47,45);

        /*This uses an inner class to handle the ActionListener functionality for the Search Button */
        searchButton.addActionListener(new ActionListener() {
           
           public void actionPerformed(ActionEvent e){
            
            //This part gets the text from the search textbox
            String locationInp = searchLocationField.getText(); 

            //This removes all the whitespace to ensure that there is txt in the textbox
            if(locationInp.replaceAll("\\s", "").length <= 0){
                return;
            }
            //This retrieves the weather data from the website using the WeatherInfo class
            weatherInfo = WeatherInfo.getWeatherData(locationInp);

            //This casts to a string so there is a word to use
            String weatherCondString = (String) weatherInfo.get("weather_condition");

            /*These conditional statements assign the correct image to Weather Condition Label */

            if(weatherCondString.equals("Clear")){
                weatherCondImage.setIcon(loadImage("src/assets/clear.png"));
            } else  if(weatherCondString.equals("Rain")){
                weatherCondImage.setIcon(loadImage("src/assets/rain.png"));
            } else  if(weatherCondString.equals("Snow")){
                weatherCondImage.setIcon(loadImage("src/assets/snow.png"));
            } else  if(weatherCondString.equals("Cloudy")){
                weatherCondImage.setIcon(loadImage("src/assets/cloudy.png"));
            }

            /*This gets the temperature and converts to a double */
            double celTemp = (double) weatherInfo.get("temperature");
            tempDisplayText.setText(celTemp + " C");

            /*Update the Weather Condition Text */
            weatherCondDesc.setText(weatherCondString);

            /*Update the Humidity Text */
            int humid = (int) weatherInfo.get("humidity");
            humidText.setText("<html><b>Humidity</b> " + humid + "%</html>");

            /*Update the Windspeed of the current location */
            double windS = (double) weatherInfo.get("windspeed");
            windSpeedText.setText("<html><b>Windspeed</b> " + windS + "km/h</html>");

           }
            
        });
        add(searchButton);

        /*This Button displays the previous search location */
        JButton prevButton = new JButton("Previous Location");
        prevButton.setBounds(15, 800, 450, 85);
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                prevLoc.setText(getPrevLoc());
            }
        });
        add(prevButton);
    }

    private ImageIcon loadImages(String imagePath){
        /*This private method is used to set the images on various controls */
        try{
            BufferedImage tempImage = ImageIO.read(new File(imagePath));
            return new ImageIcon(tempImage);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("File does not exist in directory.");
        return null;
    }
    
}
