package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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

    private WPI_TalonSRX elevator1;
    private WPI_TalonSRX elevator2;

    private WPI_TalonSRX mechanism;

    private WPI_TalonSRX motor8;
    private WPI_TalonSRX motor9;
    private WPI_TalonSRX motor10;

    private PowerDistributionPanel pdp;

    public RobotProperties() {
        joystick = new JoystickController(0);

        gyro = new ADXRS450_Gyro();

        frontLeft = new WPI_TalonSRX(4);
        frontRight = new WPI_TalonSRX(3);
        rearLeft = new WPI_TalonSRX(2);
        rearRight = new WPI_TalonSRX(1);

        frontLeft.setInverted(true);
        frontRight.setInverted(true);
        rearLeft.setInverted(true);
        rearRight.setInverted(true);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        elevator1 = new WPI_TalonSRX(5);
        elevator2 = new WPI_TalonSRX(6);
        mechanism = new WPI_TalonSRX(7);

        motor8 = new WPI_TalonSRX(8);
        motor9 = new WPI_TalonSRX(9);
        motor10 = new WPI_TalonSRX(10);
    }

    public MecanumDrive getRobotDrive() {
        return robotDrive;
    }

    public WPI_TalonSRX getElevator1() {
        return elevator1;
    }

    public void setElevator1(WPI_TalonSRX elevator1) {
        this.elevator1 = elevator1;
    }

    public WPI_TalonSRX getElevator2() {
        return elevator2;
    }

    public void setElevator2(WPI_TalonSRX elevator2) {
        this.elevator2 = elevator2;
    }

    public WPI_TalonSRX getMechanism() {
        return mechanism;
    }

    public void setMechanism(WPI_TalonSRX mechanism) {
        this.mechanism = mechanism;
    }

    public WPI_TalonSRX getMotor8() {
        return motor8;
    }

    public void setMotor8(WPI_TalonSRX motor8) {
        this.motor8 = motor8;
    }

    public WPI_TalonSRX getMotor9() {
        return motor9;
    }

    public void setMotor9(WPI_TalonSRX motor9) {
        this.motor9 = motor9;
    }

    public WPI_TalonSRX getMotor10() {
        return motor10;
    }

    public void setMotor10(WPI_TalonSRX motor10) {
        this.motor10 = motor10;
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
        SmartDashboard.putData("talon5", elevator1);
        SmartDashboard.putData("talon6", elevator2);
        SmartDashboard.putData("talon7", mechanism);
        SmartDashboard.putData("talon8", motor8);
        SmartDashboard.putData("talon9", motor9);
        SmartDashboard.putData("talon10", motor10);
        
        SmartDashboard.putData("pdp", pdp);
    }
}