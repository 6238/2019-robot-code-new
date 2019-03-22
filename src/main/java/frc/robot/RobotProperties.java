package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotProperties {
    JoystickController joystick;

    public ADXRS450_Gyro gyro;

    public WPI_TalonSRX frontLeft;
    public WPI_TalonSRX frontRight;
    public WPI_TalonSRX rearLeft;
    public WPI_TalonSRX rearRight;

    private MecanumDrive robotDrive;

    private WPI_TalonSRX leftElevator1;
    private WPI_TalonSRX leftElevator2;

    private WPI_TalonSRX intakeWheels;

    private WPI_TalonSRX cargoPivot;
    private WPI_TalonSRX rightElevator1;
    private WPI_TalonSRX rightElevator2;

    private PowerDistributionPanel pdp;

    private SpeedControllerGroup leftElevator;
    private SpeedControllerGroup rightElevator;

    public RobotProperties() {
        joystick = new JoystickController(0);

        gyro = new ADXRS450_Gyro();

        pdp = new PowerDistributionPanel();

        frontLeft = new WPI_TalonSRX(4);
        frontRight = new WPI_TalonSRX(3);
        rearLeft = new WPI_TalonSRX(2);
        rearRight = new WPI_TalonSRX(1);

        frontLeft.setInverted(true);
        frontRight.setInverted(true);
        rearLeft.setInverted(true);
        rearRight.setInverted(true);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        leftElevator1 = new WPI_TalonSRX(5);
        leftElevator2 = new WPI_TalonSRX(6);
        intakeWheels = new WPI_TalonSRX(7);

        cargoPivot = new WPI_TalonSRX(8);
        rightElevator1 = new WPI_TalonSRX(9);
        rightElevator2 = new WPI_TalonSRX(10);

        leftElevator = new SpeedControllerGroup(leftElevator1, leftElevator2);
        rightElevator = new SpeedControllerGroup(rightElevator1, rightElevator2);
    }

    public MecanumDrive getRobotDrive() {
        return robotDrive;
    }

    public SpeedControllerGroup getLeftElevator() {
        return leftElevator;
    }

    public void setLeftElevator(SpeedControllerGroup leftElevator) {
        this.leftElevator = leftElevator;
    }

    public SpeedControllerGroup getRightElevator() {
        return rightElevator;
    }

    public void setRightElevator(SpeedControllerGroup rightElevator) {
        this.rightElevator = rightElevator;
    }

    public WPI_TalonSRX getIntakeWheels() {
        return intakeWheels;
    }

    public void setIntakeWheels(WPI_TalonSRX intakeWheels) {
        this.intakeWheels = intakeWheels;
    }

    public WPI_TalonSRX getCargoPivot() {
        return cargoPivot;
    }

    public void setCargoPivot(WPI_TalonSRX cargoPivot) {
        this.cargoPivot = cargoPivot;
    }

    public void pushData(RobotProperties properties) {
        SmartDashboard.putNumber("Joystick X", properties.joystick.getJoystickX());
        SmartDashboard.putNumber("Joystick Y", properties.joystick.getJoystickY());
        SmartDashboard.putNumber("Joystick Z", properties.joystick.getJoystickZ());

        SmartDashboard.putBoolean("Trigger", properties.joystick.getButtonOne());
        SmartDashboard.putBoolean("SideButton", properties.joystick.getButtonTwo());
        SmartDashboard.putBoolean("ThumbDownLeft", properties.joystick.getButtonThree());
        SmartDashboard.putBoolean("ThumbDownRight", properties.joystick.getButtonFour());
        SmartDashboard.putBoolean("ThumbUpLeft", properties.joystick.getButtonFive());
        SmartDashboard.putBoolean("ThumbUpRight", properties.joystick.getButtonSix());
        SmartDashboard.putBoolean("BaseBackLeft", properties.joystick.getButtonSeven());
        SmartDashboard.putBoolean("BaseBackRight", properties.joystick.getButtonEight());
        SmartDashboard.putBoolean("BaseMiddleLeft", properties.joystick.getButtonNine());
        SmartDashboard.putBoolean("BaseMiddleRight", properties.joystick.getButtonTen());
        SmartDashboard.putBoolean("BaseFrontLeft", properties.joystick.getButtonEleven());
        SmartDashboard.putBoolean("BaseFrontRight", properties.joystick.getButtonTwelve());

        SmartDashboard.putBoolean("DPadUp", properties.joystick.getDPadUp());
        SmartDashboard.putBoolean("DPadUpRight", properties.joystick.getDPadUpRight());
        SmartDashboard.putBoolean("DPadRight", properties.joystick.getDPadRight());
        SmartDashboard.putBoolean("DPadDownRight", properties.joystick.getDPadDownRight());
        SmartDashboard.putBoolean("DPadDown", properties.joystick.getDPadDown());
        SmartDashboard.putBoolean("DPadDownLeft", properties.joystick.getDPadDownLeft());
        SmartDashboard.putBoolean("DPadLeft", properties.joystick.getDPadLeft());
        SmartDashboard.putBoolean("DPadUpLeft", properties.joystick.getDPadUpLeft());
        SmartDashboard.putNumber("Shutoff", properties.joystick.getSlider());

        SmartDashboard.putData(properties.getRobotDrive());

        SmartDashboard.putData("Gyro", gyro);

        SmartDashboard.putData("talon1", rearRight);
        SmartDashboard.putData("talon2", rearLeft);
        SmartDashboard.putData("talon3", frontRight);
        SmartDashboard.putData("talon4", frontLeft);
        SmartDashboard.putData("talon5", leftElevator1);
        SmartDashboard.putData("talon6", leftElevator2);
        SmartDashboard.putData("talon7", intakeWheels);
        SmartDashboard.putData("talon8", cargoPivot);
        SmartDashboard.putData("talon9", rightElevator1);
        SmartDashboard.putData("talon10", rightElevator2);
        
        SmartDashboard.putData("pdp", pdp);
    }
}