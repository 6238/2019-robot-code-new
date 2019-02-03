package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import org.opencv.core.Mat;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

public class VisionControl implements RobotController{

    private UsbCamera viewingCamera;
    private UsbCamera lineTraceCamera;
    private boolean isViewingCamera;
    private CvSink cvSinkView;
    private CvSink cvSinkTrace;
    private CvSource outputStream;
    private Mat image;
    public VisionControl(RobotProperties properties)
    {
        viewingCamera = properties.getViewingCamera();
        lineTraceCamera = properties.getLineTraceCamera();
        cvSinkView = CameraServer.getInstance().getVideo(viewingCamera);
        cvSinkTrace = CameraServer.getInstance().getVideo(lineTraceCamera);
        outputStream = CameraServer.getInstance().putVideo("Switcher", 640, 480);
        isViewingCamera = true;
        image = new Mat();
        //viewingCamera.setResolution(640, 480);
        //CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
    }

    @Override
    public String getName() {
        return "VisionControl";
    }

    public void tracingCameraControl()
    {
        //System.out.println("Setting camera 2\n");
    }
    public boolean performAction(RobotProperties properties)
    {
        isViewingCamera = properties.joystick.getDPadUp();//arbitrarily set, change as needed
        if(isViewingCamera) {
            isViewingCamera = !isViewingCamera;
        }
        if(isViewingCamera){
            cvSinkView.grabFrame(image);
        }
        else{
            cvSinkTrace.grabFrame(image);
        }
        outputStream.putFrame(image);
        return true;
    }
}
