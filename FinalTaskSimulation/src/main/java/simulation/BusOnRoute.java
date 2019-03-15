package simulation;

import model.Bus;
import model.StationNode;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BusOnRoute implements TimeInterface{
    private static final Logger LOG = Logger.getLogger(BusOnRoute.class.getSimpleName());
    private final long MUL = 60 * 1000;     // Milliseconds in minute


    private Bus bus;
    private List<StationNode> stations;
    private Statistics stat;
    private int index = 0;
    private boolean movementDirection = true;
    private long nextStationTime;
    private int breakTime;
    private boolean onBreak = true;

    public BusOnRoute(Bus bus, long initTime) {
        this.bus = bus;
        stations = bus.getRoute().getStationNodes();
        nextStationTime = initTime;
        breakTime = bus.getRoute().getBreakTime();
        stat = Statistics.getInstance();
    }

    @Override
    public void tick(long moment){
        if (moment >= nextStationTime){
            final String inOut;
            if (movementDirection) {
                if (onBreak){
                    onBreak = false;
                    inOut = bus.busStop(stations.get(index).getStation(), movementDirection);
                    nextStationTime = stations.get(index).getNextTimeValue();
                }
                else {
                    inOut = bus.busStop(stations.get(++index).getStation(), movementDirection);
                    Optional<Integer> next = stations.get(index).getNextTime();
                    if (next.isPresent())
                        nextStationTime = moment + next.get() * MUL;
                    else {
                        changeDirection();
                        onBreak = true;
                        nextStationTime = moment + breakTime*MUL;
                    }
                }
            }
            else{
                if (onBreak){
                    onBreak = false;
                    inOut = bus.busStop(stations.get(index).getStation(), movementDirection);
                    nextStationTime = stations.get(index).getPrevTimeValue();
                }
                else {
                    inOut = bus.busStop(stations.get(--index).getStation(), movementDirection);
                    Optional<Integer> prev = stations.get(index).getPrevTime();
                    if (prev.isPresent())
                        nextStationTime = moment + prev.get() * MUL;
                    else {
                        changeDirection();
                        onBreak = true;
                        nextStationTime = moment + breakTime * MUL;
                    }
                }
            }

            LOG.info(Timer.msToDate(moment) + "; " + stations.get(index).toString() + bus.toString() + "; IN/OUT: " + inOut + "; "  );
            stat.setEfficiency(stat.getEfficiency() + ((double) bus.getPassengersNumber())/bus.getCapacity());

        }
    }

    private void changeDirection(){
        if (movementDirection)
            movementDirection = false;
        else
            movementDirection = true;
    }
}
