package xml;

import model.BusStation;
import model.Route;
import model.StationNode;
import model.TransportationSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLInput {
    private TransportationSystem system;
    private StationsPool pool;

    public TransportationSystem getFromXML(String path) throws Exception {
        TransportationSystem transportationSystem = new TransportationSystem();

        Document doc;
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(new File(path));
        Node simulation = doc.getDocumentElement();
        pool = new StationsPool();

        for (int i = 0; i < simulation.getChildNodes().getLength(); i++) {

            if (simulation.getChildNodes().item(i).getNodeName().equals("stations")){
                Node stations = simulation.getChildNodes().item(i);
                for (int j = 0; j < stations.getChildNodes().getLength(); j++) {
                    if (stations.getChildNodes().item(j).getNodeName().equals("station")) {
                        Node station = stations.getChildNodes().item(j);
                        int stationId = Integer.parseInt(station.getAttributes().getNamedItem("id").getNodeValue());
                        String stationName = station.getAttributes().getNamedItem("name").getNodeValue();
                        pool.addStation(new BusStation(stationId, stationName));
                    }
                }
            }

            if (simulation.getChildNodes().item(i).getNodeName().equals("routes")){
                Node routes = simulation.getChildNodes().item(i);
                for (int j = 0; j < routes.getChildNodes().getLength(); j++) {
                    if (routes.getChildNodes().item(j).getNodeName().equals("route")) {
                        Node routeNode = routes.getChildNodes().item(j);
                        int routeId = Integer.parseInt(routeNode.getAttributes().getNamedItem("id").getNodeValue());
                        boolean isCircle = Boolean.parseBoolean(routeNode.getAttributes().getNamedItem("circle").getNodeValue());
                        int breakTime = Integer.parseInt(routeNode.getAttributes().getNamedItem("breakTime").getNodeValue());
                        Route route = new Route(routeId, isCircle, breakTime);
                        for (int k = 0; k < routeNode.getChildNodes().getLength(); k++) {
                            if (routeNode.getChildNodes().item(k).getNodeName().equals("node")) {
                                Node stationNode = routeNode.getChildNodes().item(k);
                                int stationId = Integer.parseInt(stationNode.getAttributes().getNamedItem("stationId").getNodeValue());
                                int nextTime = Integer.parseInt(stationNode.getAttributes().getNamedItem("nextTime").getNodeValue());
                                StationNode sn = new StationNode(pool.getStationById(stationId));
                                route.addNode(sn, nextTime);
                            }
                        }
                        if (isCircle) {
                            //route.makeCircle();
                        }
                        System.out.println(route);
                        transportationSystem.addRoute(route);
                    }
                }
            }

        }

        return transportationSystem;
    }

    public StationsPool getPool() {
        return pool;
    }
}
