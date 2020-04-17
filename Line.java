/**
 * Line is a class that is used to hold data in an object. Its primary data that
 * it holds is the lineName and line which holds one bus route's path(line) and
 * the bus route's name(lineName). There are methods that are not getters and 
 * setters and they are used to make the other parts of the program more
 * managable or for bug fixing.
 * 
 * @author lucad
 */
public class Line {

    // Holds the lines the public transit takes will be a partially filled array
    private String line = "";

    private String station = "";

    // Holds the name of the line
    private String lineName = "";

    public Line() {
    }
    /**
    * Sets the name of this object.
    * 
    * @param rawLineName 
    */
    public void setName(String rawLineName) {
        this.lineName = rawLineName;
        //System.out.println(this.lineName + " " + lineName);
    }

    /**
     * This method sets the line of stops in the line class.
     *
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
        //System.out.println(this.line + " " + line);
    }

    /**
     * A testing method that is also a getter for the private String that is
     * this line's name
     *
     * @return
     */
    public String getName() {
        return this.lineName;
    }

    /**
     * A testing method that is also a getter for the private String that is
     * this line's stops
     *
     * @return
     */
    public String getLine() {
        return this.line;
    }

    /**
     * Method that when called upon finds and prints the transfer station
     * between the two lines.
     * 
     * @param otherLine
     * @return 
     */
    public String findTransfer(Line otherLine) {
        String output = "";
        for (String similarStop : otherLine.getLine().split(" ")) {
            if (this.line.contains(similarStop)) {
                output = similarStop;
            }
        }
        return output;
    }
    /**
     * Adds a station to this line. I used this for debugging.
     * 
     * @param station 
     */
    public void addStation(String station) {
        this.station = station;
    }
    /**
     * This checks if the input is the same as what is already inside the object
     * I used this too for debugging.
     * 
     * @param station
     * @return 
     */
    public boolean hasStation(String station) {
        if(this.station.equals(station)) {
            return true;
        } else {
            return false;
        }
    }
}
