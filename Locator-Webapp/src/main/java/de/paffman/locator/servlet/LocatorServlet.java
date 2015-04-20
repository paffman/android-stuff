package de.paffman.locator.servlet;

import de.paffman.locator.service.model.Location;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Andre Paffenholz
 */
public class LocatorServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LocatorServlet.class);

    private static final String TEST_USER = "test";

    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, List<Location>> userData = new HashMap<String, List<Location>>();

    @Override
    /**
     * Methode zum Verarbeiten der Daten aus der App
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locData = req.getParameter("location").toString();
        Location jsonLocation = mapper.readValue(locData, Location.class);

        if(userData.get(jsonLocation.getUser()) != null) {
            ArrayList<Location> currentLocations = (ArrayList) userData.get(jsonLocation.getUser());
            currentLocations.add(jsonLocation);
            userData.put(jsonLocation.getUser(), currentLocations);

        } else {
            ArrayList<Location> locations = new ArrayList<Location>();
            locations.add(jsonLocation);
            userData.put(jsonLocation.getUser(), locations);
        }
    }

    @Override
    /**
     * Methode zum Lesen der Daten im Webclient
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userData.size() > 0){
            List<Location> locations = userData.get(TEST_USER);
            PrintWriter writer = resp.getWriter();
            writer.write(mapper.writeValueAsString(locations));
        }
    }

}
