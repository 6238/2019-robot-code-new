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

    @Override
    public boolean performAction(RobotProperties properties) {
        
        MecanumDrive robotDrive = properties.getRobotDrive();

        double insanityFactor = 0.5;

        robotDrive.driveCartesian(-1*insanityFactor*properties.joystick.getJoystickX(), insanityFactor*properties.joystick.getJoystickY(), -1*insanityFactor*properties.joystick.getJoystickZ());
        System.out.println(properties.joystick.getSlider());

        return true;
    }
}
