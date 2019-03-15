package entities;

public class Result {
    private String start, end, between;
    private double efficiency;
    private int routes, buses, passengers;

    public Result() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBetween() {
        return between;
    }

    public void setBetween(String between) {
        this.between = between;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public int getRoutes() {
        return routes;
    }

    public void setRoutes(int routes) {
        this.routes = routes;
    }

    public int getBuses() {
        return buses;
    }

    public void setBuses(int buses) {
        this.buses = buses;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
