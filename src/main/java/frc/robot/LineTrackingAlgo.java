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
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class LineTrackingAlgo {
    ArrayList<GripPipeline.Line> currLines;

    // these constants control how much the robot turns/moves based off the image
    public final double turnP = 0.05;
    public final double forwardP = 0.05;
    public final double leftRight = 0.05;
    
    // constructor
    public LineTrackingAlgo() {

    }

    // annotates image with vertical and horizontal lines, runs the other methods
    public Mat process(Mat img, ArrayList<GripPipeline.Line> lines, int x, int y, RobotProperties properties) {
        Point offsetPosition;
        double offsetAngle;
        if(lines.size()>0)
        {
            offsetPosition = weightedXY(lines);
            offsetAngle = weightedAngle(lines);
        }
        else
        {
            offsetPosition = new Point(0,0);
            offsetAngle = 0;
        }
        Imgproc.circle(img, offsetPosition, 2, new Scalar(0,255,0));
        offsetPosition.x -= x/2;
        offsetPosition.y -= y/2;
        move(offsetPosition, offsetAngle, properties, x, y);
        return img;
    }

    // calculates the weighted average of the x and y coordinates of the lines
    // this determines how offset the robot is from the "center" of the tape
    // and will be used to turn/translate the robot
    public Point weightedXY(ArrayList<GripPipeline.Line> lines) {
        double sumx = 0;
        double sumy = 0;
        for (int i = 0; i < lines.size(); i++) {
            sumx += lines.get(i).x1;
            sumy += lines.get(i).y1;
            sumx += lines.get(i).x2;
            sumy += lines.get(i).y2;
        }
        return new Point(sumx/(2*lines.size()),sumy/(2*lines.size()));
    }

    // calculates the weighted average of the x and y coordinates of the lines
    // this determines how much the robot should turn
    public double weightedAngle(ArrayList<GripPipeline.Line> lines)
    {
        double sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            sum += lines.get(i).angle();
        }
        return sum/lines.size();
    }
    // turns and translates the robot
    // the speed at which it moves forward is proportional to the constant forwardP and the 
    // variable angle(90 = upright 0 = perpendicular)
    // the speed at which it moves left and right is proportional to the constant leftright
    // and the difference between the weighted x of the lines and the center
    // the speed at which it turns is proportional to the difference between the angle and 
    // vertical line and the constant turnP
    public void move(Point offset, double angle, RobotProperties properties, int x, int y) 
    {
        MecanumDrive robotDrive = properties.getRobotDrive();
        robotDrive.driveCartesian(forwardP * angle, leftRight * (x - offset.x), turnP * (angle-90));
    }
}
