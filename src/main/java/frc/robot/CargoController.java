package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class CargoController implements RobotController {

    public CargoController() {

    }

    public String getName() {
        return "CargoMechanism";
    }

    public boolean performAction(RobotProperties properties) {
        boolean isMechinButtonPressed;
        boolean isMechoutButtonPressed;
        isMechinButtonPressed = properties.joystick.getButtonFive();
        isMechoutButtonPressed = properties.joystick.getButtonThree();
        WPI_TalonSRX Mechanism = properties.getMechanism();
        Mechanism.set(0);
        if (isMechinButtonPressed && isMechoutButtonPressed) {
            Mechanism.set(0);
        } else if (isMechinButtonPressed) {
            Mechanism.set(-5);
        } else if (isMechoutButtonPressed) {
            Mechanism.set(5);
        } else if (isMechinButtonPressed && isMechoutButtonPressed) {
            Mechanism.set(5);
        } else {
            Mechanism.set(0);
        }

        return true;
    }
}
