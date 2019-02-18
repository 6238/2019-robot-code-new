package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import java.util.*;

public class VisionController implements RobotController {
    private GripPipeline pipeline;
    private ArrayList<GripPipeline.Line> filteredLines;
    private UsbCamera camera1;

    private CvSink cvSink;
    private CvSource cvSource;

    // private UsbCamera camera2;
    private VideoSink server;
    private int curCam;
    private Thread visionThread;

    private final int width = 320;
    private final int height = 240;

    public void startThread()
    {
        visionThread.start();
    }
    public VisionController(RobotProperties properties) {
        pipeline = new GripPipeline();
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(width, height);

        cvSink = CameraServer.getInstance().getVideo();
        cvSource = CameraServer.getInstance().putVideo("vision", width, height);

        // camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        // camera2.setResolution(640, 480);
        
        System.out.println("Constructor Runs");
        visionThread = new Thread(() -> {
            Mat source = new Mat();
            Mat output = new Mat();
            while (!Thread.interrupted()) {
                if (cvSink.grabFrame(source) == 0) {
                    cvSource.notifyError(cvSink.getError());
                    continue;
                }

                pipeline.process(source);
                output = pipeline.cvResizeOutput();

                /*if (!pipeline.filterLinesOutput().isEmpty()) {
                    drive(properties, pipeline.filterLinesOutput());
                }*/

                cvSource.putFrame(output);
            }
        });
        //visionThread.setDaemon(true);
    }

    /*public void drive(RobotProperties properties, ArrayList<GripPipeline.Line> lines)
    {
        MecanumDrive drive = properties.getRobotDrive();
        if(Math.abs(lines.get(0).angle())>0.1)
        {
            drive.driveCartesian(0, 0, -lines.get(0).angle());
        }
        else
        {
            drive.driveCartesian(5, 0, 0);
        }
    }*/

    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        return true;
    }
}