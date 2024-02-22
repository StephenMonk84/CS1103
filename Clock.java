import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Clock {
    void getAndDisplay(){
        //Created a constant with the pattern since it is unchanging
        final DateTimeFormatter CUS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        //Created a constant for only displaying the time every second since that is the smallest value displayed
        final int WAIT_TIME = 1000;
        //This creates an object that updates constantly in a thread 
        TimeUpdate timeObj = new TimeUpdate();
        
        //This lambda expression creates a thread, and prints the value every second.
        Runnable displayObj = () ->{
            
            while(true){
                String currentTime = timeObj.getDateTime().format(CUS_FORMATTER);  
                System.out.println(" Current Time: " + currentTime);
            try{Thread.sleep(WAIT_TIME);} catch(InterruptedException e){ e.printStackTrace();}
            } 
        };
        //These two lines create the new threads because this program is using the interface and not the class
        Thread t1 = new Thread(timeObj);
        Thread t2 = new Thread(displayObj);
        //This sets the priority of the thread that prints the time onto the console
        t2.setPriority(Thread.MAX_PRIORITY);

        //These two lines start the threads
        t1.start();
        t2.start();


    }
    
    
    public static void main(String[] args){
        //This is the main method that creats 
        Clock obj1 = new Clock();
        obj1.getAndDisplay();
   }   
}

class TimeUpdate implements Runnable{
    /**This class implements the runnable interface and allows for access to a LocalDateTime Object
     * This class will constantly update the object until the program is closed with one thread
     */
    //This variable is volatile because multiple threads are going to need to access it at the same time
    private volatile LocalDateTime timer;

    public TimeUpdate(){
        timer = LocalDateTime.now();
    }
    //This is run method to update the time of the object
    public void run(){
        while(true){
            timer = LocalDateTime.now();
        }
    }
    //This is the method to access the timer LocalDateTime attribute
    public LocalDateTime getDateTime(){ 
        return timer;
    }
}