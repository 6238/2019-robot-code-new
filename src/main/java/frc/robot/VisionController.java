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

    private final int width = 320;
    private final int height = 240;

    public VisionController(RobotProperties properties) {
        bwpipeline = new GripPipeline2();
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(width, height);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setResolution(width, height);

        // server = CameraServer.getInstance().getServer();

        cvSink = CameraServer.getInstance().getVideo(camera1);
        cvSource = CameraServer.getInstance().putVideo("vision", width, height);

        bwVisionThread = new Thread(() -> {

            Mat output = new Mat();

            while (!Thread.interrupted()) {
                if (cvSink.grabFrame(output) == 0) {
                    cvSource.notifyError(cvSink.getError());
                    continue;
                }
                cvSink.grabFrame(output);
                bwpipeline.process(output);
                output = bwpipeline.desaturateOutput();
                cvSource.putFrame(output);
            }
        });
        bwVisionThread.start();
    }

    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
       //cvSink.setSource(camera1);
       // cvSink.setEnabled(true);
        // System.out.println("Should show black and white");

        if (properties.joystick.getButtonTwo() && !prevButton) {
            prevButton = !prevButton;
            cvSink = CameraServer.getInstance().getVideo(camera2);
        } else if (!properties.joystick.getButtonTwo() && prevButton) {
            prevButton = !prevButton;
            cvSink = CameraServer.getInstance().getVideo(camera1);
        }

        prevButton = properties.joystick.getButtonTwo();
        return true;
    }
}