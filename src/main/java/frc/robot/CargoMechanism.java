package frc.robot;

import edu.wpi.first.wpilibj.*;

public class CargoMechanism implements RobotController
{
    private Solenoid cargoBay1;
    private Solenoid cargoBay2;
    private JoystickController joystick;
    private boolean cargoTriggered;
    public CargoMechanism(RobotProperties properties)
    {
        cargoBay1 = properties.getCargoBay1();
        cargoBay2 = properties.getCargoBay2();
        joystick = properties.joystick;
    }

    public String getName() {
        return "CargoMechanism";
    }

    public boolean performAction(RobotProperties properties)
    {
        //TODO: Can't use trigger, change it
        cargoTriggered = joystick.getButtonOne(); //arbitrary value. update later
        if(cargoTriggered)
        {
            cargoBay1.set(true);
            cargoBay2.set(true);
        }
        else
        {
            cargoBay1.set(false);
            cargoBay2.set(false);
        }

        return true;
    }
}