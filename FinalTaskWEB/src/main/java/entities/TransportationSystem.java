package entities;

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

    public Set<Route> getRoutes() {
        return routes;
    }
}
