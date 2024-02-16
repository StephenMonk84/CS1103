public class Clock {
    private int hour, minute, second;
    private int day, month, year;

    public Clock(){
        this.second = 0;
        this.minute = 0;
        this.hour = 0;
    }

    public int getSecond(){
        return this.second;
    }
    public int getMinute(){
        return this.minute;
    }
    
}
