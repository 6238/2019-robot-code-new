package frc.robot;

import com.analog.adis16470.frc.ADIS16470_IMU;

public class IMUController {
    
    public ADIS16470_IMU imu;

    public IMUController() {
        this.imu = new ADIS16470_IMU();
    }
    //TODO: angles -180 to 180?
    public double getGyro() {
        double angle;
        angle = imu.getAngle();
        while (angle >= 360) {
            angle = angle - 360;
        }
        return angle;
    }
    public double getGyroX() {
        double angle;
        angle = imu.getAngleX();
        while (angle >= 360) {
            angle = angle - 360;
        }
        return angle;
    }

    public double getGyroY() {
        double angle;
        angle = imu.getAngleY();
        while (angle >= 360) {
            angle = angle - 360;
        }
        return angle;
    }

    public double getGyroZ() {
        double angle;
        angle = imu.getAngleZ();
        while (angle >= 360) {
            angle = angle - 360;
        }
        return angle;
    }

    public double getAccelX() {
        return imu.getAccelX();
    }

    public double getAccelY() {
        return imu.getAccelY();
    }

    public double getAccelZ() {
        return imu.getAccelZ();
    }
    
    public double getRate() {
        return imu.getRate();
    }

    public double getRateX() {
        return imu.getRateX();
    }

    public double getRateY() {
        return imu.getRateY();
    }

    public double getRateZ() {
        return imu.getRateZ();
    }

    public double getTemp() {
        return imu.getTemperature();
    }

    public double getStatus() {
        return imu.getStatus();
    }

    public double getCounter() {
        return imu.getCounter();
    }

    public void calibrate() {
        imu.calibrate();
    }

    public void reset() {
        imu.reset();
    }
}