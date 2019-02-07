package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;

public class VisionController implements RobotController{

    UsbCamera camera1;
    //UsbCamera camera2;
    VideoSink server;
    int curCam;
   
    public VisionController(RobotProperties properties) {
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setResolution(320, 240);
        camera1.setFrameRate(15);
        //camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        //camera2.setResolution(640, 480);

        server = CameraServer.getInstance().getServer();

        curCam = 1;
    }

    @Override
    public String getName() {
        return "VisionController";
    }

    @Override
    public boolean performAction(RobotProperties properties) {
        
        if (properties.joystick.getButtonTwo()) {
            if (curCam == 1) {
                curCam = 2;
                server.setSource(camera1);
                System.out.println(curCam);
            } else {
                curCam = 1;
                server.setSource(camera1);
                System.out.println(curCam);
            }
        }

        return true;
    }
}
