package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Drive {
	
	static boolean navxReset = false;
	static double setPoint;
	static double lastAngle, angleIntegral, output;

	public static void initializeDrive () {
		RobotMap.R1 = new TalonSRX (Constants.rightLeaderid);
		RobotMap.R2 = new VictorSPX (Constants.rightFollowerid);
		RobotMap.R3 = new VictorSPX (Constants.rightFollowerid2);
		RobotMap.L1 = new TalonSRX (Constants.leftLeaderid);
		RobotMap.L2 = new VictorSPX (Constants.leftFollowerid);
		RobotMap.L3 = new VictorSPX (Constants.leftFollowerid2);
		
		RobotMap.climber = new Talon(2);
		
		RobotMap.R2.follow (RobotMap.R1);
		RobotMap.R3.follow (RobotMap.R1);
		RobotMap.L2.follow (RobotMap.L1);
		RobotMap.L3.follow (RobotMap.L1);
		//these need to be inverted with robot 1 as of now... hopefully matt will fix that
		RobotMap.R2.setInverted(false);
		RobotMap.L3.setInverted(false);
		
		RobotMap.shifter = new DoubleSolenoid(Constants.shifterForward,Constants.shifterReverse);
		
		RobotMap.navx = new AHRS(SPI.Port.kMXP);
		Drive.setBrakeMode();
	}
	
	public static void setShifters(boolean isShifted) {
		if(isShifted == true) {
			RobotMap.shifter.set(Value.kReverse);
		}else {
			RobotMap.shifter.set(Value.kForward);
		}
	}
	public static double rotationsToInches(double rotations) {
		return rotations  * (Constants.wheelDiameter * Math.PI);
	}
	private static double inchesToRotations(double inches) {
        return inches / (Constants.wheelDiameter* Math.PI);
    }
	private static double inchesPerSecondToRpm(double inches_per_second) {
        return inchesToRotations(inches_per_second) * 60;
    }

    public double getRightDistanceInches() {
        return rotationsToInches(RobotMap.R1.getSelectedSensorPosition(8));
    }

    public double getLeftDistanceInches() {
        return rotationsToInches(RobotMap.L1.getSelectedSensorPosition(6));
    }
	public static double rpmToInchesPerSecond(double rpm) {
		return rotationsToInches(rpm) / 60;
	}
	public static double getRightVelocity1() {
		return rpmToInchesPerSecond(RobotMap.R1.getSelectedSensorVelocity(8));
	}
	public static double getLeftVelocity1() {
		return rpmToInchesPerSecond(RobotMap.L1.getSelectedSensorVelocity(6));
	}
	public static void autoShift(double velocityR, double velocityL, double lowThreshold, double highThreshold) {
		velocityR = getRightVelocity1(); 
		velocityL = getLeftVelocity1(); 
		lowThreshold = Constants.shiftLowThreshold;
		highThreshold = Constants.shiftHighThreshold;
		
		if(velocityR > highThreshold && velocityL > highThreshold) {
			setShifters(true);
		}else if(velocityR < lowThreshold && velocityL < lowThreshold) {
			setShifters(false);
		}
	}
	public static void arcadeDrive (double side, double forward, boolean shift) {
		if (shift == true) {
			if(navxReset == false) {
				RobotMap.navx.reset();
				navxReset = true;
			}
			//high gear
			setShifters(true);
			//side is forward for some reason
			RobotMap.R1.set(ControlMode.PercentOutput, side - forward);
			RobotMap.L1.set(ControlMode.PercentOutput, side + forward);
		} else {
			RobotMap.R1.set(ControlMode.PercentOutput, side - forward);
			RobotMap.L1.set(ControlMode.PercentOutput, side + forward);
			//low gear
			setShifters(false);
			navxReset = false;
		}
		
	}
	
	public static void leftDrive(double power) {
		RobotMap.L1.set(ControlMode.PercentOutput, power);
	}
	
	public static void rightDrive (double power) {
		RobotMap.R1.set(ControlMode.PercentOutput, power);
	}
	
	public static void straightDriveAuto (double power) {
		double error = RobotMap.navx.getAngle();
		rightDrive(error *Constants.kpStraight + power);
		leftDrive(error*Constants.kpStraight - power);
	}
	
	public static void straightDriveTele (double power, double heading, boolean high) {
		double currentAngle = Drive.getNavxAngle();
		double error = (currentAngle - heading);
		angleIntegral += error;
		double angleDeriv = currentAngle - lastAngle;
		
		if(high == true) {
			output = (error * Constants.kpStraightHi) + (angleDeriv * Constants.kdStraightHi);
			
			if(Constants.kiStraightHi * angleIntegral > 1) {
				angleIntegral = 1/Constants.kiStraightHi;
			} else if(Constants.kiStraightHi * angleIntegral < -1){
				angleIntegral = -1/Constants.kiStraightHi;
			}
			
			output += (angleIntegral * Constants.kiStraightHi);
		} else {
			output = (error * Constants.kpStraight) + (angleDeriv * Constants.kdStraight);
			
			if(Constants.kiStraight * angleIntegral > 1) {
				angleIntegral = 1/Constants.kiStraight;
			} else if(Constants.kiStraight * angleIntegral < -1){
				angleIntegral = -1/Constants.kiStraight;
			}
			
			output += (angleIntegral * Constants.kiStraight);
		}
		rightDrive(output + power);
		leftDrive(output - power);
		lastAngle = Drive.getNavxAngle();
		SmartDashboard.putNumber("Auto Drive Output", output);
		SmartDashboard.putNumber("Auto Drive Angle", currentAngle);
	}
	
	public static void setRobotHeading(double heading) {
		double error = (RobotMap.navx.getAngle() - heading);
		rightDrive(error*Constants.kpTurn);
		leftDrive(error*Constants.kpTurn);
	}
	
	public static double getLeftVelocity() {
		return RobotMap.L1.getSelectedSensorVelocity(0);
	}
	
	public static double getRightVelocity() {
		return RobotMap.R1.getSelectedSensorVelocity(0);
	}
	
	public static void stopDriving() {
		RobotMap.R1.set(ControlMode.PercentOutput, 0);
		RobotMap.L1.set(ControlMode.PercentOutput, 0);
	}
	
	public static double getNavxAngle() {
		return RobotMap.navx.getAngle();
	}
	
	public static double getLeftPosition() {
		return RobotMap.L1.getSelectedSensorPosition(0);
	}
	
	public static double getRightPosition() {
		return RobotMap.R1.getSelectedSensorPosition(0);
	}
	public static void setBrakeMode() {
		RobotMap.R1.setNeutralMode(NeutralMode.Brake);
		RobotMap.R2.setNeutralMode(NeutralMode.Brake);
		RobotMap.R3.setNeutralMode(NeutralMode.Brake);
		RobotMap.L3.setNeutralMode(NeutralMode.Brake);
		RobotMap.L2.setNeutralMode(NeutralMode.Brake);
		RobotMap.L1.setNeutralMode(NeutralMode.Brake);	
	}
	public static void setCoastMode() {
		RobotMap.R1.setNeutralMode(NeutralMode.Coast);
		RobotMap.R2.setNeutralMode(NeutralMode.Coast);
		RobotMap.R3.setNeutralMode(NeutralMode.Coast);
		RobotMap.L3.setNeutralMode(NeutralMode.Coast);
		RobotMap.L2.setNeutralMode(NeutralMode.Coast);
		RobotMap.L1.setNeutralMode(NeutralMode.Coast);		
	}
}
