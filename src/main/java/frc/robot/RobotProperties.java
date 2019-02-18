package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotProperties {
    JoystickController joystick;

    /*IMUController imu;

    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX rearLeft;
    private WPI_TalonSRX rearRight;

    private MecanumDrive robotDrive;

    private WPI_TalonSRX elevator1;
    private WPI_TalonSRX elevator2;

    private WPI_TalonSRX mechanism;*/

    public RobotProperties() {
        joystick = new JoystickController(0);

        /*imu = new IMUController();

        frontLeft = new WPI_TalonSRX(14);
        frontRight = new WPI_TalonSRX(13);
        rearLeft = new WPI_TalonSRX(12);
        rearRight = new WPI_TalonSRX(11);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        elevator1 = new WPI_TalonSRX(15);
        elevator2 = new WPI_TalonSRX(16);
        mechanism = new WPI_TalonSRX(17);*/
    }

    /*public MecanumDrive getRobotDrive() {
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
    }*/

    public void pushData(RobotProperties properties) {
        SmartDashboard.putNumber("Joystick X", properties.joystick.getJoystickX());
        SmartDashboard.putNumber("Joystick Y", properties.joystick.getJoystickY());
        SmartDashboard.putNumber("Joystick Z", properties.joystick.getJoystickZ());

       /* SmartDashboard.putNumber("Gyro", properties.imu.getGyro());
        SmartDashboard.putNumber("Gyro X", properties.imu.getGyroX());
        SmartDashboard.putNumber("Gyro Y", properties.imu.getGyroY());
        SmartDashboard.putNumber("Gyro Z", properties.imu.getGyroZ());
        
        SmartDashboard.putNumber("Accel X", properties.imu.getAccelX());
        SmartDashboard.putNumber("Accel Y", properties.imu.getAccelY());
        SmartDashboard.putNumber("Accel Z", properties.imu.getAccelZ());
*/
        SmartDashboard.putBoolean("Trigger", properties.joystick.getButtonOne());
        SmartDashboard.putBoolean("SideButton", properties.joystick.getButtonTwo());
        SmartDashboard.putBoolean("ThumbUpLeft", properties.joystick.getButtonThree());
        SmartDashboard.putBoolean("ThumbUpRight", properties.joystick.getButtonFour());
        SmartDashboard.putBoolean("ThumbDownLeft", properties.joystick.getButtonFive());
        SmartDashboard.putBoolean("ThumbDownRight", properties.joystick.getButtonSix());
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