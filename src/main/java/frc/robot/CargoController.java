package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CargoController implements RobotController {

    private Encoder cargoMech;

    public CargoController() {
        int count;
        double distance;

        cargoMech = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        cargoMech.setMaxPeriod(.1);
        cargoMech.setMinRate(10);
        cargoMech.setDistancePerPulse(5);
        cargoMech.setReverseDirection(true);
        cargoMech.setSamplesToAverage(7);

        count = cargoMech.get();
        distance = cargoMech.getDistance();
        SmartDashboard.putNumber("Encoders", distance);

    }

    public String getName() {
        return "CargoMechanism";
    }

    private double turnMax = 10;
    private boolean isSet = true;
    private boolean isReleased = false;

    public void moveCargoMech(RobotProperties properties) {
        WPI_TalonSRX cargoPivot = properties.getCargoPivot();
        boolean isCargoButtonPressed = properties.joystick.getButtonEleven();// temporary button number
        if (isCargoButtonPressed) {
            isSet = false;
        }
        if (!isSet) {
            if (isReleased) {
                if(cargoMech.getDistance() != 0)
                {
                    cargoPivot.set(-5);
                }
                else
                {
                    isSet = true;
                    cargoPivot.set(0);
                }
            } else {
                if(cargoMech.getDistance() != turnMax)
                {
                    cargoPivot.set(5);
                }
                else
                {
                    isSet = true;
                    cargoPivot.set(0);
                }
            }
        }

    }

    public boolean performAction(RobotProperties properties) {
        boolean isMechinButtonPressed;
        boolean isMechoutButtonPressed;
        isMechinButtonPressed = properties.joystick.getButtonFive();
        isMechoutButtonPressed = properties.joystick.getButtonThree();
        WPI_TalonSRX intakeWheels = properties.getIntakeWheels();
        intakeWheels.set(0);
        if (isMechinButtonPressed && isMechoutButtonPressed) {
            intakeWheels.set(0);
        } else if (isMechinButtonPressed) {
            intakeWheels.set(-5);
        } else if (isMechoutButtonPressed) {
            intakeWheels.set(5);
        } /*
           * else if (isMechinButtonPressed && isMechoutButtonPressed) {
           * intakeWheels.set(5); }
           */ else {
            intakeWheels.set(0);
        }
        //moveCargoMech();
        return true;
    }
}
