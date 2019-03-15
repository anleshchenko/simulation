package model;

import java.util.Optional;


public class StationNode {
    private BusStation station;
    private Optional<Integer> prevTime, nextTime;

    public StationNode(BusStation station) {
        this.station = station;
    }

    public BusStation getStation() {
        return station;
    }

    public void setStation(BusStation station) {
        this.station = station;
    }

    public Optional<Integer> getPrevTime() {
        return prevTime;
    }

    public int getPrevTimeValue(){
        return prevTime.get();
    }

    public void setPrevTime(int time) {
        Optional<Integer> optTime = Optional.empty();
        if (time > 0)
            optTime = Optional.of(time);
        prevTime = optTime;
    }

    public Optional<Integer> getNextTime() {
        return nextTime;
    }

    public int getNextTimeValue(){
        return nextTime.get();
    }

    public void setNextTime(int time) {
        Optional<Integer> optTime = Optional.empty();
        if (time > 0)
            optTime = Optional.of(time);
        nextTime = optTime;
    }

    @Override
    public String toString() {
        return station.toString();
    }
}
