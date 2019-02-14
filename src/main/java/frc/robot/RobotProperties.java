package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Solenoid;

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
        rearLeft = new WPI_TalonSRX(12);
        rearRight = new WPI_TalonSRX(11);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        elevator1 = new WPI_TalonSRX(15);
        elevator2 = new WPI_TalonSRX(16);

        //cargoBay1 = new Solenoid(0);
        //cargoBay2 = new Solenoid(1);

        //mechanism = new WPI_TalonSRX(17);
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

    /*public WPI_TalonSRX getMechanism() {
        return mechanism;
    }

    public void setMechanism(WPI_TalonSRX mechanism) {
        this.mechanism = mechanism;
    }

    public Solenoid getCargoBay1() {
        return cargoBay1;
    }

    public void setCargoBay1(Solenoid cargoBay1) {
        this.cargoBay1 = cargoBay1;
    }

    public Solenoid getCargoBay2() {
        return cargoBay2;
    }

    public void setCargoBay2(Solenoid cargoBay2) {
        this.cargoBay2 = cargoBay2;
    }*/
}