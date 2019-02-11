package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
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


    private Solenoid cargoBay1;
    private Solenoid cargoBay2;

    private WPI_TalonSRX mechanism;

    private Gyro gyro;
    private Accelerometer accelerometer;

    //private UsbCamera viewingCamera;
    //private UsbCamera lineTraceCamera;

    public RobotProperties() {
        joystick = new JoystickController(0);

        frontLeft = new WPI_TalonSRX(14);
        frontRight = new WPI_TalonSRX(13);
        rearLeft = new WPI_TalonSRX(12);
        rearRight = new WPI_TalonSRX(11);

        robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

        elevator1 = new WPI_TalonSRX(15);
        elevator2 = new WPI_TalonSRX(16);

        //cargoBay1 = new Solenoid(0);
        //cargoBay2 = new Solenoid(1);

        //mechanism = new WPI_TalonSRX(39);
        
        //TODO: fix this:
        gyro = new AnalogGyro(0);
        
        //TODO: why can't i make a get statement for the accel?
        accelerometer = new BuiltInAccelerometer();
        accelerometer = new BuiltInAccelerometer(Accelerometer.Range.k4G);

        //viewingCamera = CameraServer.getInstance().startAutomaticCapture();
       // lineTraceCamera = CameraServer.getInstance().startAutomaticCapture();
    }
    // 4 wheel motors - cims talon srx
    // 2 dual gearboxes - 2 775s each
    // one spare 775
    /*

    */

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

    public Gyro getGyro() {
        return gyro;
    }

    //public UsbCamera getViewingCamera() {
    //    return this.viewingCamera;
    //}

    //public UsbCamera getLineTraceCamera() {
    //    return this.lineTraceCamera;
    //}
}
