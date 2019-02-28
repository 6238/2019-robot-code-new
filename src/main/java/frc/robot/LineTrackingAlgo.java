/*
This is a PID control algorithm designed to help align the robot with the white tape. Should
it not work as intended, the driver can manually control the robot and toggle a low sensitivity
mode to allow for easier manual alignment.

Algorithm:
1) Read and process the image in the GripPipeline and pass the resulting ArrayList<GripPipeLine.line>
   into this algorithm. 
2) Draw an imaginary horizontal and vertical line that passes through the center of the robot.
3) Take the weighted average of the x and y coordinates of the endpoints of each line segment.
4) With the vertical/horizontal lines as a reference point, turn and translate the robot.
*/
package frc.robot;

import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class LineTrackingAlgo
{
    ArrayList<GripPipeline.Line> currLines;
    
    //these constants control how much the robot turns/moves based off the image
    public final double turnP = 1.0;
    public final double translateP = 1.0;
    public final double turnI = 1.0;
    public final double translateI = 1.0;

    //constructor
    public LineTrackingAlgo()
    {

    }

    //annotates image with vertical and horizontal lines, runs the other methods
    public Mat process(Mat img, ArrayList<GripPipeline.Line> lines,int x, int y)
    {
        Imgproc.line(img, new Point(x/2,0), new Point(x/2,y), new Scalar(0,255,0));
        Imgproc.line(img, new Point(0,y/2), new Point(x,y/2), new Scalar(0,255,0));
        Point offset = weightedXY(lines);
        offset.x -= x;
        offset.y -= y;
        move(offset);
        return img;
    }

    //calculates the weighted average of the x and y coordinates of the lines
    //this determines how offset the robot is from the "center" of the tape
    //and will be used to turn/translate the robot
    public Point weightedXY(ArrayList<GripPipeline.Line> lines)
    {
        double sumx = 0;
        double sumy = 0;
        return new Point();
    }

    //turns and translates the robot
    public void move(Point offset)
    {

    }
}