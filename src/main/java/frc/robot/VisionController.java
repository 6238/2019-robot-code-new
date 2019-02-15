package frc.robot;

import org.opencv.core.Point;
import org.opencv.core.Scalar;
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

    private UsbCamera camera1;
    private UsbCamera camera2;
    private VideoSink server;
    private int curCam;
    private CvSink cvSink;
    private CvSource outputStream; 
    private GripPipeline pipeline;
    public VisionController(RobotProperties properties) {
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(640, 480);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setResolution(640, 480);

        server = CameraServer.getInstance().getServer();

        curCam = 1;
    }

    @Override
    public String getName() {
        return "VisionController";
    }
    
    private ArrayList<Line> lines;
                
    @Override
    public boolean performAction(RobotProperties properties) {
        
        if (properties.joystick.getButtonTwo()) {
            if (curCam == 1) {
                curCam = 2;
                server.setSource(camera1);
                System.out.println(curCam);
            } else {
                curCam = 1;
                server.setSource(camera2);
                System.out.println(curCam);
                
                cvSink = CameraServer.getInstance().getVideo();
                outputStream = CameraServer.getInstance().putVideo("Line Trace", 640, 480);
                
                Mat source = new Mat();
                Mat output = new Mat();
                
                while(!Thread.interrupted()) {
                    cvSink.grabFrame(source);
                    Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                    //pipeline.process(source);
                    //lines = pipeline.findLinesOutput();   
                    //System.out.println(lines.get(0).length());
                    outputStream.putFrame(output);
                }
            }
        }

        return true;
    }
}
