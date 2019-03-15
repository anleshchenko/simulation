package model;

import java.util.List;
import java.util.Objects;

public class Passenger {
    private static int counter = 0;

    private final int id;
    private BusStation departure;
    private BusStation destination;

    public Passenger(BusStation departure, BusStation destination) {
        id = ++counter;
        this.departure = departure;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public BusStation getDeparture() {
        return departure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id &&
                departure.equals(passenger.departure) &&
                destination.equals(passenger.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departure, destination);
    }

    public void setDeparture(BusStation departure) {
        this.departure = departure;
    }

    public BusStation getDestination() {
        return destination;
    }

    public void setDestination(BusStation destination) {
        this.destination = destination;
    }

    public boolean isSatisfied(Bus bus, boolean direction){
        Route busRoute = bus.getRoute();
        List<BusStation> stations = busRoute.getBusStations();
        if (stations.contains(departure)&&stations.contains(destination)){
            if (direction == stations.indexOf(departure) < stations.indexOf(destination))
                return true;
        }
        return false;
    }
}
