package simulation;

import model.BusStation;
import model.Passenger;
import model.Route;

import java.util.List;
import java.util.Random;

public class PassengersOnRoute implements TimeInterface {
    private List<BusStation> stations;
    private int passengersEveryMinute;
    private int max;
    private Statistics stat;
    private Random random;

    public PassengersOnRoute(Route route, int passengersEveryMinute) {
        stations = route.getBusStations();
        this.passengersEveryMinute = passengersEveryMinute;
        max = stations.size();
        stat = Statistics.getInstance();
        random = new Random();
    }

    @Override
    public void tick(long moment) {
        for (int i = 0; i < passengersEveryMinute; i++) {
            BusStation departure = stations.get(random.nextInt(max));
            BusStation destination = stations.get(random.nextInt(max));
            if (!departure.equals(destination)) {
                departure.addPassenger(new Passenger(departure, destination));

                stat.setPassengersNumber(stat.getPassengersNumber()+1);

            }
        }
    }
}