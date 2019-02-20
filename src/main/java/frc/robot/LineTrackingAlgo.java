/*
This class will be used for line tracing used to align the robot with the rocket/cargo ship.
The class stores values of previously found lines and continously adjusts the robot heading 
based off new line values. The list of previously found lines is always stored just in case 
the pipeline misses the lines for a few iterations. The robot will always algin itself based 
off the last known set of lines.

Algorithm:
1) draw an imaginary, vertical line down the center of the frame(create a method or variable 
   to return the points on the line)
2) find the the two edges of the tape
3) whenever the imaginary line crosses over one of the detected lines within some margin of error:

   case 1) the detected lines are updated every iteration:
        
        1a) if the imaginary line crosses with the left detected line(taken for the highest y value),
            turn the robot right until the lines no longer intersect, within some margin of error.

        1b) if the imaginary line crosses with the right detected line(taken for the highest y value),
            turn the robot left until the lines no longer intersect, within some margin of error.

    case 2) the detected lines are not updated for some iteration: using the last dectected 
            lines and trigonometry/alegebra, calculate the angle the robot should turn to align
            itself. This method is far less accurate so it will only be triggered as a backup.
            If lines cannot be detected for an extended amount of time(a predetermined number of
            seconds), the vision feed will cut to a backup feed in which line processing is 
            not carried out, so the driver will have to manually align. An option to lower turn
            speed will be added so the driver can more accurately align.
            
4) Drive forward at a predetermined(likely slow)speed.

IMPORTANT:

All driver inputs will be ignored when this method is triggered to prevent inaccuracies in alignment.
This can be manually overrided by turning the switch off the switch on the smart dashboard or by switching 
camera views.
*/
package frc.robot;

import java.util.ArrayList;

public class LineTrackingAlgo {
    
    // All filtered lines are stored in ArrayList<GripPipeline.Line> lines.
    // This is to ensure that the LineTrackingAlgo still has reference values
    // in case the vision processing is unable to detect the lines at all times.
    private ArrayList<GripPipeline.Line> lines;

    private int iterationsWithoutLines;
    private final int turningThreshold = 2500;
    
    //tells if the robot currently has some value or command to turn
    private Boolean hasTurnCommand;
    private int backUpTurnValue;

    //initializes everything
    public LineTrackingAlgo(ArrayList<GripPipeline.Line> currLines)
    {
        iterationsWithoutLines = 0;
        backUpTurnValue = 0;
        hasTurnCommand = false;
    }

    //checks if currlines is updated, increases iterationsWithoutLines if not
    public Boolean isLinesUpdated(ArrayList<GripPipeline.Line> currLines)
    {
        if(!currLines.isEmpty())
        {
            return true;
        }
        iterationsWithoutLines++;
        return false;
    }

    //tries aligning with the line
    public void selfAlign(RobotProperties properties, ArrayList<GripPipeline.Line> currLines)
    {

    }

    //calculates the amount which the robot must turn to align with tape given the last known line values
    public double turnPeriod(RobotProperties properties)
    {
        return 0.0;
    }

    //turns the robot given that the lines are constantly updated
    //resets iterationsWithoutLines
    public void turnRobot(RobotProperties properties)
    {

    }

    //terminates the selfAlign method if iterationsWithoutLines exceeds turningThreshold 
    public void terminateAutoTurn()
    {

    }

    //calculates which lines the imaginary line intersects with
    //0 = no intesections
    //1 = intersects with left line on the top
    //2 = intersects with right line on the top
    public int findHighestIntersection()
    {
        return 0;
    }
}