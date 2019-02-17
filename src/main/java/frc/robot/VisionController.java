package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoSink;
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

    private final int width = 640;
    private final int height = 480;

    public VisionController(RobotProperties properties) {
        pipeline = new GripPipeline();
        camera1 = CameraServer.getInstance().startAutomaticCapture();
        camera1.setResolution(width, height);

        cvSink = CameraServer.getInstance().getVideo();
        cvSource = CameraServer.getInstance().putVideo("vision", width, height);

        // camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        // camera2.setResolution(640, 480);

        visionThread = new Thread(() -> {
            Mat source = new Mat();
            Mat output = new Mat();
            while (!Thread.interrupted()) {
                //System.out.println("Hello World");
                if (cvSink.grabFrame(source) == 0) {
                    cvSource.notifyError(cvSink.getError());
                    continue;
                }

                pipeline.process(source);
                if (!pipeline.findLinesOutput().isEmpty()) {
                    System.out.println(pipeline.findLinesOutput().size());
                } else {
                    //System.out.println("nothing found");
                }
                for (int i = 0; i < pipeline.findLinesOutput().size(); i++) 
                { 
                    Imgproc.line(output, new Point(pipeline.findLinesOutput().get(i).x1, pipeline.findLinesOutput().get(i).y1),
                    new Point(pipeline.findLinesOutput().get(i).x2, pipeline.findLinesOutput().get(i).y2), new Scalar(0, 255, 0), 10);
                }
                output = pipeline.cvErodeOutput();
                cvSource.putFrame(output);
            }
        });
        visionThread.setDaemon(true);
        visionThread.start();
    }

    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        /*
         * cvSink.setSource(camera1); cvSink.setEnabled(true);
         * 
         * Mat frame = new Mat();
         * 
         * cvSink.grabFrame(frame);
         * 
         * pipeline.process(frame);
         * 
         * filteredLines = pipeline.filterLinesOutput();
         * 
         * GripPipeline.Line line = filteredLines.get(0);
         * 
         * System.out.println(line.angle());
         */
        return true;
    }
}