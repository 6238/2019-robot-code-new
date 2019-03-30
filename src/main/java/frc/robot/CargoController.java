package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CargoController implements RobotController {

    // private Encoder cargoMech;

    // right now, this constructor initializes the encoders on the pivot arm
    // the point of this is to get the relative distance between when the arm is
    // retracted and extended
    public CargoController(RobotProperties properties) {
        // properties.getCargoPivot().configContinuousCurrentLimit(10, 10);
        // properties.getCargoPivot().enableCurrentLimit(true);
        // properties.getCargoPivot().configPeakOutputForward(0.2);
        // properties.getCargoPivot().configPeakOutputReverse(0.2);
        /*
         * int count; double distance; cargoMech = new Encoder(0, 1, false,
         * Encoder.EncodingType.k4X);
         * 
         * // these lines initialize different parameters of the encoder, but aren't
         * used // to control anything so far cargoMech.setMaxPeriod(.1);
         * cargoMech.setMinRate(10); cargoMech.setDistancePerPulse(5);
         * cargoMech.setReverseDirection(true); cargoMech.setSamplesToAverage(7);
         * 
         * // these lines display the encoder distance on the shuffleboard for testing
         * // purposes, it isn't necessary for competition count = cargoMech.get();
         * distance = cargoMech.getDistance(); System.out.println("Encoders " +
         * distance); SmartDashboard.putNumber("Encoders", distance);
         * SmartDashboard.putNumber("wristSpeed", wristSpeed);
         */
    }

    public String getName() {
        return "CargoMechanism";
    }

    // this constant represents the relative distance between when the arm is up and
    // down
    private final int turnMax = 10; // it needs to be updated experimentally

    // these variables help control whether the arm is released or retracted
    private boolean isSet = true;
    private boolean isReleased = false;
    private boolean wristIn = true;
    private double wristSpeed = 1.0;

    private int restingPos = 20;
    private int extendedPos = 600;

    public void moveCargoMech(RobotProperties properties) {
        WPI_TalonSRX cargoPivot = properties.getCargoPivot();
        // cargoPivot.SetFeedbackDevice(CTRE.TalonSrx.FeedbackDevice.CtreMagEncoder_Relative);
        boolean isCargoUp = properties.joystick.getButtonTen();// temporary button number
        boolean isCargoDown = properties.joystick.getButtonTwelve();
        // wristSpeed = SmartDashboard.getNumber("wristSpeed", wristSpeed);
        SmartDashboard.putNumber("cargoMech", cargoPivot.getSelectedSensorPosition());

        if (isCargoDown) {// } && cargoPivot.getSelectedSensorPosition() >= restingPos) {
            cargoPivot.set(wristSpeed);
            System.out.println("going down");
        } else if (isCargoUp) {// } && cargoPivot.getSelectedSensorPosition() <= extendedPos) {
            cargoPivot.set(-wristSpeed);
            System.out.println("going up");
        } else {
            cargoPivot.set(0);
        }

        /*
         * if (isCargoButtonPressed) { if (wristIn) { cargoPivot.set(-wristSpeed);
         * if(cargoPivot.getSelectedSensorPosition() >= extendedPos) { wristIn = false;
         * } } else { cargoPivot.set(wristSpeed);
         * if(cargoPivot.getSelectedSensorPosition() <= restingPos) { wristIn = true; }
         * } } else { cargoPivot.set(0); }
         */

        // checks for whether the cargo button is pressed
        // this sets isSet to false which is used in the following statement to control
        // the arm
        /*
         * if (isSet && isCargoButtonPressed) { isSet = false; }
         * 
         * if (!isSet) {
         * 
         * // isReleased represents whether the arm is currently in an up or down
         * position if (isReleased) { // if it is in the down position, we keep turning
         * it until the distance returns // to 0 if (cargoMech.getDistance() != 0) {
         * cargoPivot.set(-5); }
         * 
         * // once the distance is 0, isReleased is set to false, representing the up //
         * position, // and isSet is set to true representing that the arm is currently
         * either in an // up or down position else { isSet = true; cargoPivot.set(0);
         * isReleased = false; } } else { // these statements represent the same
         * mechanism as the previous if statements, // except it turns the // arm the
         * other way to release it if (cargoMech.getDistance() != turnMax) {
         * cargoPivot.set(5); } else { isSet = true; cargoPivot.set(0); isReleased =
         * true; } } }
         */

    }

    public boolean performAction(RobotProperties properties) {

        boolean isMechInButtonPressed = properties.joystick.getButtonFive();
        boolean isMechOutButtonPressed = properties.joystick.getButtonThree();
        WPI_TalonSRX intakeWheels = properties.getIntakeWheels();

        // intakeWheels don't move by default, buttons 3 and 5 control whether they spin
        // inside or outside
        intakeWheels.set(0);
        if (isMechInButtonPressed && isMechOutButtonPressed) {
            intakeWheels.set(0);
        } else if (isMechInButtonPressed) { // check sign
            intakeWheels.set(-5);
        } else if (isMechOutButtonPressed) {
            intakeWheels.set(5);
        } /*
           * else if (isMechInButtonPressed && isMechOutButtonPressed) {
           * intakeWheels.set(5); } else { intakeWheels.set(0); }
           */

        int distance = properties.getCargoPivot().getSelectedSensorPosition();
        SmartDashboard.putNumber("encoder", distance);
        moveCargoMech(properties);
        return true;
    }
}
