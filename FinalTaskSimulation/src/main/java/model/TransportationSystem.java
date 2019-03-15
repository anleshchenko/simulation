package model;

import java.util.HashSet;
import java.util.Set;

public class TransportationSystem {
    private Set<Route> routes;

    public TransportationSystem() {
        routes = new HashSet<>();
    }

    public void addRoute(Route route){
        routes.add(route);
    }

    public void removeRoute(Route route){
        routes.remove(route);
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Route route: routes) {
            sb.append(route);
        }
        return sb.toString();
    }
}