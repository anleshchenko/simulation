package simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Timer {
    private final int MINUTE = 60 * 1000;

    private List<TimeInterface> list;
    private long initTime;
    private long endTime;

    public Timer(long initTime, long endTime) {
        list = new ArrayList<>();
        this.initTime = initTime;
        this.endTime = endTime;
    }

    public void addInstance(TimeInterface instance){
        list.add(instance);
    }


    public void startTimer(){
        for (long curr = initTime; curr <= endTime ; curr+=MINUTE) {
            for (TimeInterface instance:list) {
                instance.tick(curr);
            }
        }
    }

    public static String msToDate(long ms){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date(ms);
        return dateFormat.format(date);
    }

    public static String msToDaysDate(long ms){
        DateFormat dateFormat = new SimpleDateFormat("dd days HH:mm:ss");
        Date date = new Date(ms);
        return dateFormat.format(date);
    }

}
