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

3) Find intersections of the lines:

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

            If the line detection restarts while the robot is turning under case 2, switch back to 
            case 1 and reset all turning variables.

    Use the following equation to determine how many iterations to turn left/right:

        x = n * w * t
        x is angle
        n is number of iterations
        v is angular speed(turning in place)
        t is time per iteration
            
    case 3) if the lines do not intersect, continue to step 4

    case 4) if the tape is clearly on the screen, but the pipeline cannot find the lines, switch to
            manual turning

4) Draw an imaginary horizontal line across the screen

5) Find the x coordinate(or columns) of the intersections, called xleft and xright:

    case 1) xleft is left of the vertical line and xright is right of the horizontal line: proceed
            to step 6

    case 2) xright is left of the vertical line: xmid = (xleft + xright)/2, move right until 
           xmid = vertical line

    case 3) xleft is right of the vertical line: xmid = (xleft + xright)/2, move left until 
           xmid = vertical line

    for case 2 and 3 use the following equation to determine how many iterations to move left/right:

        x = n * v * t
        x is distance
        n is number of iterations
        v is speed
        t is time per iteration

    should the line tracing fail, the driver must manually strafe the robot

6) Drive forward at a predetermined(likely slow)speed.

IMPORTANT:

All driver inputs will be ignored when this method is triggered to prevent inaccuracies in alignment.
This can be manually overrided by turning the switch off the switch on the smart dashboard(equivalent
to case 2))or by switching camera views.
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
    private int backUpTurnPeriod;

    //initializes everything
    public LineTrackingAlgo(ArrayList<GripPipeline.Line> currLines)
    {
        iterationsWithoutLines = 0;
        backUpTurnPeriod = 0;
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

    /*tries aligning with the line
    
    Algo: check isLinesUpdated() first -> case 2)
    */
    public void selfAlign(RobotProperties properties, ArrayList<GripPipeline.Line> currLines)
    {

    }

    /*calculates the amount which the robot must turn to align with tape given the last known line values
    
    algorithm
    */
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