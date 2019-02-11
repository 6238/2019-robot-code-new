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

    @Override
    public boolean performAction(RobotProperties properties) {
        
        MecanumDrive robotDrive = properties.getRobotDrive();

        if (properties.joystick.getDPadUp() && insanityFactor < 1) {
            insanityFactor = insanityFactor + 0.05;
        } else if (properties.joystick.getDPadDown() && insanityFactor > 0) {
            insanityFactor = insanityFactor - 0.05;
        }

        if (properties.joystick.getButtonOne()) {
            robotDrive.driveCartesian(-1*insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -1*insanityFactor*properties.joystick.getJoystickZ(), properties.getGyro().getAngle());
        } else {
            robotDrive.driveCartesian(-1*insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -1*insanityFactor*properties.joystick.getJoystickZ(), 0.0);
        }
        

        return true;
    }
}
