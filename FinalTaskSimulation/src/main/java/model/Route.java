package model;

import java.util.*;

public class Route {

    private int id;
    private boolean isCircle;
    private List<StationNode> nodes;
    private int breakTime;

    public Route(int id, boolean isCircle, int breakTime){
        this.id = id;
        this.isCircle = isCircle;
        nodes = new ArrayList<>();
        this.breakTime = breakTime;
    }

    public int getId() {
        return id;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public void addNode(StationNode node, int nextTime){
        node.setNextTime(nextTime);
        if (!nodes.isEmpty())
            node.setPrevTime(nodes.get(nodes.size() - 1).getNextTimeValue());
        else
            node.setPrevTime(-1);
        nodes.add(node);
    }

    public void makeCircle(){
        nodes.get(0).setPrevTime(nodes.get(nodes.size()-1).getNextTimeValue());
    }

    public int getBusesNeeded(int timeBetween){
        int cycle = 0;
        cycle += breakTime * 2;
        for (StationNode node: nodes) {
            if (node.getNextTime().isPresent())
                cycle+=node.getNextTimeValue();
        }
        return cycle/timeBetween;
    }

    public List<StationNode> getStationNodes() {
        return nodes;
    }

    public List<BusStation> getBusStations(){
        List<BusStation> list = new ArrayList<>();
        for (StationNode sn:nodes) {
            list.add(sn.getStation());
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return id == route.id &&
                isCircle == route.isCircle &&
                nodes.equals(route.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isCircle, nodes);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                '}';
    }
}