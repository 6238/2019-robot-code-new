package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.*;

public class VisionController implements RobotController {
    
    //finds and filters lines in an image
    private GripPipeline pipeline;

    //converts an image to black and white
    private bwGripPipeline bwpipeline;

    //stores the results of pipeline
    private ArrayList<GripPipeline.Line> filteredLines;

    //camera1 is for sandstorm, camera2 is for alignment
    private UsbCamera camera1;
    private UsbCamera camera2;

    //sink determines which camer is used, source displays the video
    private CvSink cvSink;
    private CvSource cvSource;

    //vision code is carried out in the thread
    private Thread visionThread;

    //determines which camera is used and what parts of the threads are running
    private Boolean prevButton = false;
    private Boolean bwIsRunning = true;

    //dimensions of the video
    private final int width = 160;
    private final int height = 120;
    private final int fps = 120;

    //determines when the robot should begin aligning
    boolean selfAlign = false;

    public VisionController(RobotProperties properties) {

        //adds button to smartdashboard
        SmartDashboard.putBoolean("selfAlign", selfAlign);

        //initializes pipelines
        pipeline = new GripPipeline();
        bwpipeline = new bwGripPipeline();

        //initializes both cameras
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(width, height);
        camera1.setFPS(fps);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setResolution(width, height);
        camera2.setFPS(fps);

        //initializes the source and sink
        cvSink = CameraServer.getInstance().getVideo(camera1);// camera1
        cvSource = CameraServer.getInstance().putVideo("vision", width, height);

        visionThread = new Thread(() -> {
            Mat source = new Mat();
            Mat output = new Mat();
            while (!Thread.interrupted()) {

                //grabs current frame from cvSink
                if (cvSink.grabFrame(source) == 0) {
                    cvSource.notifyError(cvSink.getError());
                    continue;
                }

                //button on dashboard triggers the LineTrackingAlgo
                SmartDashboard.getBoolean("selfAlign", selfAlign);

                if (bwIsRunning) {

                    //displays b+w video, this is the default setting
                    bwpipeline.process(source);
                    output = bwpipeline.desaturateOutput();
                    cvSource.putFrame(output);
                } else {
                    
                    //displays b+w video, along with all detected lines highlighted
                    pipeline.process(source);
                    output = pipeline.desaturateOutput();
                    ArrayList<GripPipeline.Line> lines = pipeline.filterLinesOutput();
                    if (lines.size() > 0) {
                        for (int i = 0; i < lines.size(); i++) {
                            Imgproc.line(output, new Point(lines.get(i).x1, lines.get(i).y1),
                                    new Point(lines.get(i).x2, lines.get(i).y2), new Scalar(0, 255, 0), 2);
                        }
                        //SmartDashboard.putString("Angle", Double.toString(lines.get(0).angle()));
                    }
                    cvSource.putFrame(output);
                }
            }
        });

        //automatically starts thread
        visionThread.start();
    }

    //for troubleshooting
    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        
        //restarts thread if it unexpectedly crashes. The program has been 
        //tested to eliminated errors with memory so this is likely never run
        if (!visionThread.isAlive()) {
            visionThread.start();
        }

        //switches view to camera2 and begins line tracing whenever button 2 is pressed
        //automatically reverts to camera1
        if (properties.joystick.getButtonTwo() && !prevButton) {
            prevButton = !prevButton;
            bwIsRunning = false;
            cvSink = CameraServer.getInstance().getVideo(camera2);

        } else if (!properties.joystick.getButtonTwo() && prevButton) {
            prevButton = !prevButton;
            bwIsRunning = true;
            cvSink = CameraServer.getInstance().getVideo(camera1); // camera1
        }
        prevButton = properties.joystick.getButtonTwo();
        return true;
    }
}