package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by imadan on 1/26/19.
 */
public class DriveTrainController implements RobotController {
    
    double insanityFactor = 0.5;
    boolean reverseDrive = false;

    public DriveTrainController() {
        SmartDashboard.putNumber("insanityFactor", insanityFactor);
        SmartDashboard.putBoolean("reverseDrive", reverseDrive);
    }

    @Override
    public String getName() {
        return "DriveTrainController";
    }
    
    double correctAngle; //What the robot should be doing (angle after joyZ stopped changing)
    double actualAngle; //What the gyro currently measures
    double angleError;
    double kError = 1/180;
    boolean checkNextCycle;

    @Override
    public boolean performAction(RobotProperties properties) {
        
        MecanumDrive robotDrive = properties.getRobotDrive();

        insanityFactor = SmartDashboard.getNumber("insanityFactor", insanityFactor);

        reverseDrive = SmartDashboard.getBoolean("reverseDrive", reverseDrive);

        //TODO: Calculate what correctAngle is, angleError = correctAngle - actualAngle, subtract angleError from joyZ (demonstrated)

        //actualAngle = properties.imu.getGyro();

        /*if (properties.joystick.getJoystickZ() == 0 && checkNextCycle) {
            correctAngle = actualAngle;
            checkNextCycle = false;
        } else if (properties.joystick.getJoystickZ() != 0) {
            checkNextCycle = true;
        }*/

        //angleError = correctAngle - actualAngle;

        if (SmartDashboard.getBoolean("Joystick Control", true)) {
            if (properties.joystick.getButtonOne()) {
                robotDrive.driveCartesian(-insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -insanityFactor*properties.joystick.getJoystickZ(), actualAngle);
            } else if (reverseDrive) {
                robotDrive.driveCartesian(insanityFactor*properties.joystick.getJoystickX(), -insanityFactor*properties.joystick.getJoystickY(), -insanityFactor*properties.joystick.getJoystickZ()/* + (angleError * kError)*/);
            } else {
                robotDrive.driveCartesian(-insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -insanityFactor*properties.joystick.getJoystickZ()/* + (angleError * kError)*/);
            }
        }

        return true;
    }
}
