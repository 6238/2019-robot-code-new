/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private RobotProperties properties;
  private final List<RobotController> controllers;

  public Robot() {
    //properties = new RobotProperties();

    controllers = new ArrayList<RobotController>();
    controllers.add(new DriveTrainController());
    controllers.add(new VisionController(properties));
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  //@Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    properties = new RobotProperties();
    /*CameraServer server = CameraServer.getInstance();
    UsbCamera camera = server.startAutomaticCapture(0);
    camera.setResolution(640, 480);*/
    
  }

  @Override
  public void teleopPeriodic() {
    if (properties.joystick.getSlider() < -0.5) {
      for (RobotController controller : controllers) 
      {
        controller.performAction(properties);
      }
    }
    
  }
}
