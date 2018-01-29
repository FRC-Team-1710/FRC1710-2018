package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake {
	static boolean toggle = true;
	static boolean toggle2 = true;
	static boolean piston = false;
	static boolean piston2 = false;
	public static void intake (double right, double left) {
		RobotMap.intakeR.set(ControlMode.PercentOutput, right);
		RobotMap.intakeL.set(ControlMode.PercentOutput, left);
		// Right toggle
		if (toggle && RobotMap.mechStick.getRawButton(6)) {
			toggle = false;
			if (piston) {
				piston = false;
				RobotMap.right.set(DoubleSolenoid.Value.kForward);
			} else if(RobotMap.mechStick.getRawButton(6) == false) {
				toggle = true;
		} else {
				piston = true;
				RobotMap.right.set(DoubleSolenoid.Value.kReverse);
			} 
			}
		// Left toggle
		if (toggle2 && RobotMap.mechStick.getRawButton(5)) {
			toggle2 = false;
			if (piston2) {
				piston2 = false;
				RobotMap.left.set(DoubleSolenoid.Value.kForward);
			} else if (RobotMap.mechStick.getRawButton(5) == false) {
			toggle2 = true;
		} else {
				piston2 = true;
				RobotMap.left.set(DoubleSolenoid.Value.kReverse);
			}
		}
	}
}
