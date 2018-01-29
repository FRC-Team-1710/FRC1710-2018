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
	
	public static void straightDrive (double power) {
		double error = RobotMap.navx.getAngle();
		double kp= .01;
		rightDrive(error *kp + power);
		leftDrive(error*kp - power);
	}
}
