package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake {
	static boolean toggle = true;
	static boolean toggle2 = true;
	static boolean piston = false;
	static boolean piston2 = false;
	static boolean wristToggle = true;
	static boolean wristPosition = false;
	//TODO: reformat and replace uses of RobotMap.mechStick... with their respective values in ControllerMap
	public static void intake (double right, double left) {
		RobotMap.intakeR.set(ControlMode.PercentOutput, right);
		RobotMap.intakeL.set(ControlMode.PercentOutput, left);
		// Right toggle
		if (toggle && ControllerMap.rightIntakePistonToggle) {
			toggle = false;
			if (piston) {
				piston = false;
				RobotMap.intakeRight.set(DoubleSolenoid.Value.kForward);
			} else if(ControllerMap.rightIntakePistonToggle == false) {
				toggle = true;
			} else {
				piston = true;
				RobotMap.intakeRight.set(DoubleSolenoid.Value.kReverse);
			} 
		}
		// Left toggle
		if (toggle2 && ControllerMap.leftIntakePistonToggle) {
			toggle2 = false;
			if (piston2) {
				piston2 = false;
				RobotMap.intakeLeft.set(DoubleSolenoid.Value.kForward);
			} else if (ControllerMap.leftIntakePistonToggle == false) {
			toggle2 = true;
			} else {
				piston2 = true;
				RobotMap.intakeLeft.set(DoubleSolenoid.Value.kReverse);
			}
		}
		
	}
	//TODO: make this control the wrist motor on the intake using feedback from the encoder
	public static void wristControl(int position) {
		if (wristToggle && RobotMap.mechStick.getRawButton(1)) {
			wristToggle = false;
			if (wristPosition) {
				wristPosition = false;
				RobotMap.wrist.getSelectedSensorPosition(5);
		} else if (RobotMap.mechStick.getRawButton(1) == false) {
			wristToggle = true;
		} else {
			wristPosition = true;
			RobotMap.wrist.getSelectedSensorPosition(1);
		} //stuff happens
		}
	}
}
