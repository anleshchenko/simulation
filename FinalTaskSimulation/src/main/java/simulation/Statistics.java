package simulation;

public class Statistics {
    private static Statistics instance;

    private long startTime;
    private long endTime;
    private int routesNumber;
    private int days;

    private int busesNumber = 0;
    private int passengersNumber = 0;

    private double efficiency = 0.0;
    private long measuresNumber = 0;

    public static Statistics getInstance() {
        if (instance == null)
            instance = new Statistics();
        return instance;
    }

    private Statistics() {
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getRoutesNumber() {
        return routesNumber;
    }

    public void setRoutesNumber(int routesNumber) {
        this.routesNumber = routesNumber;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getBusesNumber() {
        return busesNumber;
    }

    public void setBusesNumber(int busesNumber) {
        this.busesNumber = busesNumber;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
        measuresNumber++;
    }

    public double countAveEfficiency(){
        return efficiency/measuresNumber;
    }

}
