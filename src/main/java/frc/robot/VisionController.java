package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import org.opencv.core.Mat;
import java.util.*;

public class VisionController implements RobotController {
    private GripPipeline pipeline;
    private bwGripPipeline bwpipeline;
    private ArrayList<GripPipeline.Line> filteredLines;

    private UsbCamera camera1;
    private UsbCamera camera2;

    private CvSink cvSink;
    private CvSource cvSource;

    private Thread visionThread;
    private Boolean prevButton = false;

    private Boolean bwIsRunning = true;

    private final int width = 640;
    private final int height = 480;

    public VisionController(RobotProperties properties) {
        pipeline = new GripPipeline();
        bwpipeline = new bwGripPipeline();

        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(width, height);
        camera1.setFPS(30);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setResolution(width, height);
        camera2.setFPS(30);

        cvSink = CameraServer.getInstance().getVideo(camera1);
        cvSource = CameraServer.getInstance().putVideo("vision", width, height);

        visionThread = new Thread(() -> {
            Mat source = new Mat();
            Mat output = new Mat();
            while (!Thread.interrupted()) {
                if (cvSink.grabFrame(source) == 0) {
                    cvSource.notifyError(cvSink.getError());
                    continue;
                }
                if (bwIsRunning) {
                    bwpipeline.process(source);
                    output = bwpipeline.desaturateOutput();
                    cvSource.putFrame(output);
                } else {

                    pipeline.process(source);
                    output = pipeline.desaturateOutput();

                    /*
                     * if (!pipeline.filterLinesOutput().isEmpty()) { drive(properties,
                     * pipeline.filterLinesOutput()); }
                     */
                    /* ArrayList<GripPipeline.Line> lines = pipeline.filterLinesOutput();
                    if (lines.size() > 0) {
                        for (int i = 0; i < lines.size(); i++) {
                            Imgproc.line(output, new Point(lines.get(i).x1, lines.get(i).y1),
                                    new Point(lines.get(i).x2, lines.get(i).y2), new Scalar(0, 255, 0), 2);
                        }
                        //System.out.println(Double.toString(lines.get(0).angle()));
                        SmartDashboard.putString("Angle", Double.toString(lines.get(0).angle()));
                    } */
                    cvSource.putFrame(output);
                }
            }
        });
        visionThread.start();
    }

    public void drive(RobotProperties properties, ArrayList<GripPipeline.Line> lines) {
        MecanumDrive drive = properties.getRobotDrive();
        if (Math.abs(lines.get(0).angle()) > 0.1) {
            drive.driveCartesian(0, 0, -lines.get(0).angle());
        } else {
            drive.driveCartesian(5, 0, 0);
        }
    }

    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        if (properties.joystick.getButtonTwo() && !prevButton) {
            prevButton = !prevButton;
            bwIsRunning = false;
            cvSink = CameraServer.getInstance().getVideo(camera2);

        } else if (!properties.joystick.getButtonTwo() && prevButton) {
            prevButton = !prevButton;
            bwIsRunning = true;
            cvSink = CameraServer.getInstance().getVideo(camera1);
        }
        prevButton = properties.joystick.getButtonTwo();
        return true;
    }
}