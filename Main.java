import javax.swing.*;

public class Main {
    /*This is the main driver class */
    public static void main(String[] args){
        /*This uses an anonymous inner class*/
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new WeatherAppDisplay().setVisible(true);
            }
        });
    }
    
}
