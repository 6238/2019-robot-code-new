package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.cameraserver.CameraServer;

public class RobotProperties {
    JoystickController joystick;

    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX rearLeft;
    private WPI_TalonSRX rearRight;

    private MecanumDrive robotDrive;

    private WPI_TalonSRX elevator1;
    private WPI_TalonSRX elevator2;


    //private Solenoid cargoBay1;
    //private Solenoid cargoBay2;

    private WPI_TalonSRX mechanism;

    //private UsbCamera viewingCamera;
    //private UsbCamera lineTraceCamera;

    public RobotProperties() {
        joystick = new JoystickController(0);

        frontLeft = new WPI_TalonSRX(33);
        frontRight = new WPI_TalonSRX(34);
        rearLeft = new WPI_TalonSRX(35);
        rearRight = new WPI_TalonSRX(36);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        elevator1 = new WPI_TalonSRX(37);
        elevator2 = new WPI_TalonSRX(38);

        //cargoBay1 = new Solenoid(0);
        //cargoBay2 = new Solenoid(1);

        mechanism = new WPI_TalonSRX(39);

        //viewingCamera = CameraServer.getInstance().startAutomaticCapture();
       // lineTraceCamera = CameraServer.getInstance().startAutomaticCapture();
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

    /*public Solenoid getCargoBay1() {
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

    //public UsbCamera getViewingCamera() {
    //    return this.viewingCamera;
    //}

    //public UsbCamera getLineTraceCamera() {
    //    return this.lineTraceCamera;
    //}
}
