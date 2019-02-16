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
import java.util.*;
public class VisionController implements RobotController{
    private GripPipeline pipeline;
    private ArrayList<GripPipeline.Line> filteredLines;
    private UsbCamera camera1;
    private CvSink cvSink;
    //private UsbCamera camera2;
    private VideoSink server;
    private int curCam;
    private Thread visionThread;
   // private CvSink cvSink;
   // private CvSource outputStream; 
   // private GripPipeline pipeline;
   
   public VisionController(RobotProperties properties) {
        pipeline = new GripPipeline();
        camera1 = CameraServer.getInstance().startAutomaticCapture();
        camera1.setResolution(640, 480);
    
        cvSink = CameraServer.getInstance().getVideo();
        
        //camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        //camera2.setResolution(640, 480);
    }

    @Override
    public String getName() {
        return "VisionController";
    }
               
    @Override
    public boolean performAction(RobotProperties properties) 
    {
        cvSink.setSource(camera1);
        cvSink.setEnabled(true);

        Mat frame = new Mat();

        cvSink.grabFrame(frame);
        
        pipeline.process(frame);

        filteredLines = pipeline.filterLinesOutput();

        GripPipeline.Line line = filteredLines.get(0);

        System.out.println(line.angle());
        return true;
    }
}