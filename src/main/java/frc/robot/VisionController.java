package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.util.*;

public class VisionController implements RobotController {
    private GripPipeline2 bwpipeline;
    private ArrayList<GripPipeline.Line> filteredLines;
    private UsbCamera camera1;
    private UsbCamera camera2;
    private CvSink cvSink;
    private VideoSink server;
    private Boolean prevButton = false;
    private Thread bwVisionThread;
    private CvSource cvSource;

    private final int width = 640;
    private final int height = 320;

    public VisionController(RobotProperties properties) {
        bwpipeline = new GripPipeline2();
        camera1 = CameraServer.getInstance().startAutomaticCapture();
        camera1.setResolution(640, 480);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setResolution(640, 480);

        server = CameraServer.getInstance().getServer();

        cvSink = CameraServer.getInstance().getVideo();
        cvSource = CameraServer.getInstance().putVideo("vision", width, height);

        bwVisionThread = new Thread(() -> {

            Mat img = new Mat();
            Mat output = new Mat();
            
            
            cvSink.grabFrame(img);
            bwpipeline.process(img);
            output = bwpipeline.desaturateOutput();
            cvSource.putFrame(output);
        });
        bwVisionThread.start();
    }

    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        cvSink.setSource(camera1);
        cvSink.setEnabled(true);
        // System.out.println("Should show black and white");

        if (properties.joystick.getButtonTwo() && !prevButton) {
            server.setSource(camera2);
        } else if (!properties.joystick.getButtonTwo() && prevButton) {
            server.setSource(camera1);
        }

        prevButton = properties.joystick.getButtonTwo();
        return true;
    }
}