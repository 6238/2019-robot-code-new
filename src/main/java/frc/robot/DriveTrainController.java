package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * Created by imadan on 1/26/19.
 */
public class DriveTrainController implements RobotController {
    public DriveTrainController() {

    }

    @Override
    public String getName() {
        return "DriveTrainController";
    }

    double insanityFactor = 0.5;
    
    double correctAngle; //What the robot should be doing (angle after joyZ stopped changing)
    double actualAngle; //What the gyro currently measures
    double angleError;
    double kError = 1/180;
    boolean checkNextCycle;

    @Override
    public boolean performAction(RobotProperties properties) {
        
        MecanumDrive robotDrive = properties.getRobotDrive();

        if (properties.joystick.getDPadUp() && insanityFactor < 1) {
            insanityFactor = insanityFactor + 0.05;
        } else if (properties.joystick.getDPadDown() && insanityFactor > 0) {
            insanityFactor = insanityFactor - 0.05;
        }

        //TODO: Calculate what correctAngle is, angleError = correctAngle - actualAngle, subtract angleError from joyZ (demonstrated)

        actualAngle = properties.imu.getGyro();

        if (properties.joystick.getJoystickZ() == 0 && checkNextCycle) {
            correctAngle = actualAngle;
            checkNextCycle = false;
        } else if (properties.joystick.getJoystickZ() != 0) {
            checkNextCycle = true;
        }

        angleError = correctAngle - actualAngle;

        if (properties.joystick.getButtonOne()) {
            robotDrive.driveCartesian(-1*insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -1*insanityFactor*properties.joystick.getJoystickZ(), actualAngle);
        } else {
            robotDrive.driveCartesian(-1*insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -1*insanityFactor*properties.joystick.getJoystickZ() + (angleError * kError));
        }

        return true;
    }
}
