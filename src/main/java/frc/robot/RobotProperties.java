package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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

    private SpeedControllerGroup leftElevator;
    private SpeedControllerGroup rightElevator;

    private PowerDistributionPanel pdp;

    public RobotProperties() {
        joystick = new JoystickController(0);

        gyro = new ADXRS450_Gyro();

        pdp = new PowerDistributionPanel();

        frontLeft = new WPI_TalonSRX(4);
        frontRight = new WPI_TalonSRX(3);
        rearLeft = new WPI_TalonSRX(2);
        rearRight = new WPI_TalonSRX(1);
        
        SmartDashboard.putBoolean("selfAlign", false);
        SmartDashboard.putBoolean("ReverseTurn", false);

        frontLeft.setInverted(true);
        frontRight.setInverted(true);
        rearLeft.setInverted(true);
        rearRight.setInverted(true);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
 
        leftElevator1 = new WPI_TalonSRX(6);
        leftElevator2 = new WPI_TalonSRX(7);
        intakeWheels = new WPI_TalonSRX(10);

        cargoPivot = new WPI_TalonSRX(5);
        rightElevator1 = new WPI_TalonSRX(8);
        rightElevator2 = new WPI_TalonSRX(9);

        leftElevator = new SpeedControllerGroup(leftElevator1, leftElevator2);
        rightElevator = new SpeedControllerGroup(rightElevator1, rightElevator2);

        leftElevator1.setInverted(true);
        leftElevator2.setInverted(false);

        rightElevator1.setInverted(true);
        rightElevator2.setInverted(false);
        
        leftElevator.setInverted(false);
        rightElevator.setInverted(false);
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
        
        //adds button to smartdashboard
        
        SmartDashboard.putNumber("Joystick X", properties.joystick.getJoystickX());
        SmartDashboard.putNumber("Joystick Y", properties.joystick.getJoystickY());
        SmartDashboard.putNumber("Joystick Z", properties.joystick.getJoystickZ());

        SmartDashboard.putNumber("Shutoff", properties.joystick.getSlider());

        SmartDashboard.putNumber("autoTurnSpeed", 0.0035);
        SmartDashboard.putNumber("autoDriveSpeed", 0.025);

        SmartDashboard.putData(properties.getRobotDrive());

        SmartDashboard.putData("Gyro", gyro);
        
        SmartDashboard.putData("pdp", pdp);
    }
}