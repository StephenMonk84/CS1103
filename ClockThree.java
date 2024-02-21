import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ClockThree {
    void getAndDisplay(){
        final DateTimeFormatter CUS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        final int WAIT_TIME = 1000;
        
        Runnable timeObj = () -> {
            LocalDateTime timer;
            while(true){
                timer = LocalDateTime.now();
            }
            
        };

        Runnable displayObj = () ->{
            LocalDateTime ldt;
            while(true){
               ldt = LocalDateTime.now();
                String currentTime = ldt.format(CUS_FORMATTER);  
                System.out.println(" Current Time: " + currentTime);
            try{Thread.sleep(WAIT_TIME);} catch(InterruptedException e){ e.printStackTrace();}
            } 
        };

        Thread t1 = new Thread(timeObj);
        Thread t2 = new Thread(displayObj);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();


    }
    
    
    public static void main(String[] args){
        ClockThree obj1 = new ClockThree();
        obj1.getAndDisplay();
    }



    
}