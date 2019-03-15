package entities;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Station> stations;
    private int id;
    private boolean circle;
    private int breaktime;

    public Route() {
        stations = new ArrayList<>();
    }

    public void addStation(Station station){
        stations.add(station);
    }

    public boolean hasStation(Station station){
        return stations.contains(station);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    public int getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(int breaktime) {
        this.breaktime = breaktime;
    }

    public List<Station> getStations() {
        return stations;
    }
}
