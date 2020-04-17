import java.util.Scanner; //reads from stream
import java.io.IOException;
import java.io.PrintWriter; //writes to the stream
import java.io.FileInputStream;

/**
 * The driver method has two main fuctions. One is to run the 
 * other methods with the correct input and the other is to 
 * have some test data to test the program. 
 * @author lucad
 */
public class Driver {
    /**
     * The driver method is just this main. Takes user input to
     * manually test routes but it also can do preset routes
     * that attempt to push the driver to its limits via 
     * transfers.
     */
    public static void main(String[] args) {
        Scanner keyboardInput = new Scanner(System.in);
        Line line = new Line();
        RouteFinder rf = new RouteFinder();

        rf.readLines("testData.txt");
        
        System.out.print
        ("Enter a number 1 through 6 for a personalized route.");
        System.out.print
        ("They test the limits of my driver. Enter anything");
        System.out.print
        (" else to input your own start and stop");
        String yOrN = keyboardInput.next();
        // Test method for a bunch of different stops
        if(yOrN.equals("1")) {
            rf.hasRoute("Cloud", "Scarecrow");
        } else if (yOrN.equals("2")) {
            rf.hasRoute("Maze", "Bothell");
        } else if (yOrN.equals("3")) {
            rf.hasRoute("Amazon", "Scarecrow");
        } else if (yOrN.equals("4")) {
            rf.hasRoute("Blacksmith", "Altar");
        } else if (yOrN.equals("5")) {
            rf.hasRoute("Blacksmith", "Hollywood");
        } else if (yOrN.equals("6")) {
            rf.hasRoute("Ravine", "Forest");
        } else {
            System.out.println
       ("Not a valid entry, try your own start and stop point");
        }
        
        // Cleans the input
        String filler = keyboardInput.nextLine();
        
        System.out.println("Enter two stations, seperated by a comma: ");
        String input = keyboardInput.nextLine();
        
        // Need to split the input where the comma is
        String[] startAndEnd = new String[2];
        startAndEnd = input.split(",");
        
        String start = startAndEnd[0];
        String end = startAndEnd[1];
        
        rf.hasRoute(start, end);
    }
}
