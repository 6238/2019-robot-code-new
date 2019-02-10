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

        robotDrive.driveCartesian(properties.joystick.getJoystickX(), properties.joystick.getJoystickY(), properties.joystick.getJoystickZ());
        System.out.println(properties.joystick.getSlider());

        return true;
    }
}
