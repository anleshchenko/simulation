package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BusStation {

    private final int id;
    private String name;
    private List<Passenger> queue;

    public BusStation(int id, String name) {
        this.name = name;
        this.id = id;
        queue = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusStation)) return false;
        BusStation that = (BusStation) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPassenger(Passenger p){
        queue.add(p);
    }

    public int getPassengersNumber(){
        return queue.size();
    }

    public int busApproach(Bus bus, boolean direction){
        int cameIn = 0;
        int available = bus.getFreePlaces();
        List<Passenger> copy = new ArrayList<>(queue);
        for (Passenger p: copy) {
            if (available > 0) {
                if (p.isSatisfied(bus, direction)) {
                    bus.addPassenger(p);
                    queue.remove(p);
                    cameIn++;
                    available--;
                }
            }
        }
        return cameIn;
    }

    @Override
    public String toString() {
        return "BusStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
