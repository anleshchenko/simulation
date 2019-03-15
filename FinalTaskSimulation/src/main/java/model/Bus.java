package model;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Bus {
    private static int counter = 0;

    private final int id;
    private final String model;
    private final int capacity;

    private Set<Passenger> passengers;
    private Route route;

    public Bus(String model, int capacity, Route route) {
        id = ++counter;
        this.model = model;
        this.capacity = capacity;
        this.route = route;
        passengers = new HashSet<>();
    }

    public Bus(BusModel busModel, Route route){
        this(busModel.model, busModel.capacity, route);
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPassengersNumber(){
        return passengers.size();
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String busStop(BusStation station, boolean direction){
        int out = 0, in = 0;
        Set<Passenger> copy = new HashSet<>(passengers);
        for (Passenger pas:copy) {
            if(pas.getDestination().equals(station)){
                passengers.remove(pas);
                out++;
            }
        }
        in = station.busApproach(this, direction);
        return in + "/" + out;
    }

    public int getFreePlaces(){
        return capacity-passengers.size();
    }

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                " " + getRoute().toString() +
                " passengers: " + getPassengersNumber() +
                '}';
    }
}