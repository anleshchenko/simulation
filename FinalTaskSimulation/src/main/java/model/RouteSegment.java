package model;

public class RouteSegment {
    private static int counter = 0;

    private final int id;
    private BusStation stationA, stationB;
    private int averageTime;

    public RouteSegment() {
        this.id = ++counter;
    }

    public RouteSegment(BusStation stationA, BusStation stationB, int averageTime) {
        this();
        this.stationA = stationA;
        this.stationB = stationB;
        this.averageTime = averageTime;
    }

    public int getId() {
        return id;
    }

    public BusStation getStationA() {
        return stationA;
    }

    public void setStationA(BusStation stationA) {
        this.stationA = stationA;
    }

    public BusStation getStationB() {
        return stationB;
    }

    public void setStationB(BusStation stationB) {
        this.stationB = stationB;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }

    public BusStation getConnected(BusStation bs){
        if(bs.equals(stationA))
            return stationB;
        if(bs.equals(stationB))
            return stationA;
        return null;
    }

    public boolean hasStation(BusStation bs){
        return bs.equals(stationA) || bs.equals(stationB);
    }
}