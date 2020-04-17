import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * RouteFinder has three extremely important methods: one that reads from
 * the data file/map. routeFinder that takes the data from the map and 
 * puts it into storage and meaning the computer can manipulate. And the 
 * final method, printRoute turns the data that the computer understands 
 * and puts t into output that the user can understand
 *
 * @lucad
 */
public class RouteFinder {

    // These strings hold information required for any bus routes
    private String startStation;
    private String endStation;
    private String routeName;
    
    // These strings hold information required for transfers
    private String similarStop;
    private String endStationRouteName;
    

    private String localLineName;
    // 10 element long arrays are fine
    private Line[] busLine = new Line[10];

    private int count = 0;

    boolean needTransfer = false;
    boolean hasRouteOutput = true;

    Line line = new Line();

    /**
     * This method reads lines from the file that will be used as the map for
     * the program. Once read this method puts relevant data into an array of 
     * objects that can then be easily accessed in other methods.
     * 
     * @param filename 
     */
    public void readLines(String filename) {

        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        while (input.hasNextLine()) {
            // The name of the line is always in the same order
            String name = input.nextLine();
            line.setName(name);

            // The bus line's stops follow the name of the line
            String rawLine = input.nextLine();
            line.setLine(rawLine);

            //System.out.println(count);
            busLine[count++] = line;
            line = new Line();
        }
        input.close();
    }

    /**
     * This program is almost the driver as it finds the routes from the start 
     * station to the end station, finds the names of the start and end station 
     * and checks certain booleans that other methods like findTransfer and 
     * printRoute can then use to get the desired output.
     * 
     * @param startStation
     * @param endStation
     * @return 
     */
    public boolean hasRoute(String startStation, String endStation) {

        // Need to store the start and end stations
        this.startStation = startStation;
        this.endStation = endStation;

        for (int i = 0; i < busLine.length - (busLine.length - count); i++) {
            routeName = busLine[i].getName();

            // Need to format routeName to follow the syntax provided. I remove
            // the extra number and add Line to the end 
            routeName = routeName.substring(0, routeName.length() - 1);
            routeName += "Line";

            for (int j = 0; j < busLine[i].getLine().length(); j++) {

                // Constructs words that can be tested individually
                localLineName
                        = "" + localLineName + busLine[i].getLine().charAt(j);

                // The most basic of route checkers. Checks to see if the start
                // and end are in the same route and puts it together
                if (busLine[i].getLine().contains(startStation)) {
                    if (busLine[i].getLine().contains(endStation)) {
                        needTransfer = false;
                        printRoute();

                        // Code can end now that route has been found
                        System.exit(0);
                    } else {
                        // These loops checks for transfers
                        for (int a = 0; a < busLine[i].getLine().length(); a++){
                            if (busLine[a].getLine().contains(endStation)) {
                                line.findTransfer(busLine[a]);
                                // Have to ruin syntax to make this section fit
                                for (String similarStop1 : busLine[i].getLine().
                                        split(" ")) {
                                    if (busLine[a].getLine().contains
                                                    (similarStop1)) {
                                        // The transfer line needs to have the 
                                        // numbers removed and have the word
                                        // "line" added. I personally really
                                        // dislike how this looks but it needs
                                        // to be in one method.
                                        endStationRouteName = 
                                                busLine[a].getName();
                                        endStationRouteName = 
             endStationRouteName.substring(0, endStationRouteName.length() - 1);
                                                  endStationRouteName += "Line";
                                        similarStop = similarStop1;
                                        needTransfer = true;
                                    }
                                }

                                // Without this the code crashes
                                printRoute();
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }

        hasRouteOutput = false;

        printRoute();
        return hasRouteOutput;
    }

    /**
     * Prints out the routes of the metro rider from the booleans supplied by 
     * hasRoute. Uses the booleans in a series of if else statements.
     */
    public void printRoute() {
        if (!hasRouteOutput) {
            // If there is no connection
            System.out.println("There is no route between stations "
                    + "" + startStation + " and " + endStation);
        } else if (!needTransfer) {
            // If no transfer is necessary to get from one stop to the other
            System.out.println
      ("Take the " + routeName + " from " + startStation + " to " + endStation);
        } else {
            // If a transfer occurs.
            System.out.println
       ("Take the " + routeName + " from " + startStation + ", transfer to the "
               + endStationRouteName + " at " + similarStop + 
               ", and continue to " + endStation);
        }
    }

}
