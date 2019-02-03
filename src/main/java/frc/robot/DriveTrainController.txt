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
        double joyX = properties.joystick.getJoystickX();
        double joyY = properties.joystick.getJoystickY();
        double joyZ = properties.joystick.getJoystickZ();

        MecanumDrive robotDrive = properties.getRobotDrive();

        robotDrive.driveCartesian(joyX, joyY, joyZ, 0.0);

        return true;
    }
}
