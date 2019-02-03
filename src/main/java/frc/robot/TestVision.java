package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class TestVision implements RobotController {
    
    public boolean performAction(RobotProperties properties) 
    {
        CameraServer.getInstance().startAutomaticCapture();
        return true;
    }
    public String getName()
    {
        return "This should just show camera view on Dashboard";
    }
}