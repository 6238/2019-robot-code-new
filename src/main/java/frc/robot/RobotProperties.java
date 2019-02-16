package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.cameraserver.CameraServer;

public class RobotProperties {
    JoystickController joystick;

    IMUController imu;

    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX rearLeft;
    private WPI_TalonSRX rearRight;

    private MecanumDrive robotDrive;

    private WPI_TalonSRX elevator1;
    private WPI_TalonSRX elevator2;


    private Solenoid cargoBay1;
    private Solenoid cargoBay2;

    private WPI_TalonSRX mechanism;

    public RobotProperties() {
        joystick = new JoystickController(0);
        
        imu = new IMUController();

        frontLeft = new WPI_TalonSRX(14);
        frontRight = new WPI_TalonSRX(13);
        rearLeft = new WPI_TalonSRX(16);
        rearRight = new WPI_TalonSRX(15);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        elevator1 = new WPI_TalonSRX(17);
        elevator2 = new WPI_TalonSRX(18);

        mechanism = new WPI_TalonSRX(39);
    }
    // 4 wheel motors - cims talon srx
    // 2 dual gearboxes - 2 775s each
    // one spare 775
    /*

    */

    public WPI_TalonSRX getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(WPI_TalonSRX frontLeft) {
        this.frontLeft = frontLeft;
    }

    public WPI_TalonSRX getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(WPI_TalonSRX frontRight) {
        this.frontRight = frontRight;
    }

    public WPI_TalonSRX getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(WPI_TalonSRX rearLeft) {
        this.rearLeft = rearLeft;
    }

    public WPI_TalonSRX getRearRight() {
        return rearRight;
    }

    public void setRearRight(WPI_TalonSRX rearRight) {
        this.rearRight = rearRight;
    }

    public MecanumDrive getRobotDrive() {
        return robotDrive;
    }

    public void setRobotDrive(MecanumDrive robotDrive) {
        this.robotDrive = robotDrive;
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

    public void pushData(RobotProperties properties) {
        SmartDashboard.putNumber("Joystick X", properties.joystick.getJoystickX());
        SmartDashboard.putNumber("Joystick Y", properties.joystick.getJoystickY());
        SmartDashboard.putNumber("Joystick Z", properties.joystick.getJoystickZ());

        SmartDashboard.putNumber("Gyro", properties.imu.getGyro());
        SmartDashboard.putNumber("Gyro X", properties.imu.getGyroX());
        SmartDashboard.putNumber("Gyro Y", properties.imu.getGyroY());
        SmartDashboard.putNumber("Gyro Z", properties.imu.getGyroZ());
        
        /*System.out.println(properties.imu.getGyro());
        System.out.println(properties.imu.getGyroX());
        System.out.println(properties.imu.getGyroY());
        System.out.println(properties.imu.getGyroZ());*/

        System.out.println(properties.imu.imu.getAngle());
        System.out.println(properties.imu.imu.getAngleX());
        System.out.println(properties.imu.imu.getAngleY());
        System.out.println(properties.imu.imu.getAngleZ());
        
        /*SmartDashboard.putNumber("Accel X", properties.imu.getAccelX());
        SmartDashboard.putNumber("Accel Y", properties.imu.getAccelY());
        SmartDashboard.putNumber("Accel Z", properties.imu.getAccelZ());*/

        SmartDashboard.putNumber("Accel X", properties.imu.imu.getAccelX());
        SmartDashboard.putNumber("Accel Y", properties.imu.imu.getAccelY());
        SmartDashboard.putNumber("Accel Z", properties.imu.imu.getAccelZ());

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
    }
}
