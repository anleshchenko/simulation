package main;

import model.TransportationSystem;
import simulation.Simulation;
import xml.XMLInput;

public class Main {
    public static void main(String[] args) {
        String path = "classes/input.xml";
        int days = 5, timeBetween = 5, passengersEveryMinute = 7;
        if (args.length == 4){
            path = args[0];
            days = Integer.parseInt(args[1]);
            timeBetween = Integer.parseInt(args[2]);
            passengersEveryMinute = Integer.parseInt(args[3]);
        }

        TransportationSystem system = null;
        XMLInput xmlInput = new XMLInput();
        try {
            system = xmlInput.getFromXML(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Simulation simulation = new Simulation(system, days, timeBetween, passengersEveryMinute);
        simulation.start();

        simulation.exportRoutes(xmlInput.getPool());
        simulation.exportXML(path);
        simulation.exportResult();
    }
}
