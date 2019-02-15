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

        if (properties.joystick.getButtonThree() && insanityFactor < 1) {
            insanityFactor = insanityFactor + 0.05;
        } else if (properties.joystick.getButtonFive() && insanityFactor > 0) {
            insanityFactor = insanityFactor - 0.05;
        } else {
            insanityFactor = insanityFactor;
        }

        System.out.println("up:" + properties.joystick.getButtonThree() + "down:" + properties.joystick.getButtonFive() + "factor:" + insanityFactor);

        robotDrive.driveCartesian(-1*insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -1*insanityFactor*properties.joystick.getJoystickZ());
        
        return true;
    }
}
