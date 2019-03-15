package xml;

import model.BusStation;

import java.util.HashSet;
import java.util.Set;

public class StationsPool {
    private Set<BusStation> stations;

    public StationsPool() {
        stations = new HashSet<>();
    }

    public void addStation(BusStation station){
        stations.add(station);
    }

    public Set<BusStation> getStations() {
        return stations;
    }

    public BusStation getStationById(int id){
        for (BusStation station:stations) {
            if(station.getId() == id)
                return station;
        }
        return null;
    }
}
