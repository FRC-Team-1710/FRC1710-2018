package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drive {
	
	public static void arcadeDrive (double forward, double side, boolean shift) {
		if (shift == true) {
			//high gear
			RobotMap.shifter.set(Value.kReverse); 
		} else {
			//low gear
			RobotMap.shifter.set(Value.kForward);
		}
		
		RobotMap.R1.set(ControlMode.PercentOutput, forward - side);
		RobotMap.L1.set(ControlMode.PercentOutput, forward + side);
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
	
	public static void straightDriveTele (double power, double heading) {
		double error = (RobotMap.navx.getAngle() - (heading * 180));
		rightDrive(error *Constants.kpStraight + power);
		leftDrive(error*Constants.kpStraight - power);
	}
	
	public static double getLeftVelocity() {
		return RobotMap.L1.getSelectedSensorVelocity(0);
	}
	
	public static double getRightVelocity() {
		return RobotMap.R1.getSelectedSensorVelocity(0);
	}
	
	public static double getLeftPosition() {
		return RobotMap.L1.getSelectedSensorPosition(0);
	}
	
	public static double getRightPosition() {
		return RobotMap.R1.getSelectedSensorPosition(0);
	}

}
