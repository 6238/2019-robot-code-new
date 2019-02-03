package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;

public class TestVision implements RobotController {
    
    public boolean performAction(RobotProperties properties) 
    {
        return true;
    }
    public String getName()
    {
        return "This should just show camera view on Dashboard";
    }
}