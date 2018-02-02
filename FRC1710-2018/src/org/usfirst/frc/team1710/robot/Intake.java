package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
			} else {
				piston = true;
				RobotMap.intakeRight.set(DoubleSolenoid.Value.kReverse);
			}
		} else if (ControllerMap.rightIntakePistonToggle == false) {
			toggle = true;
		}
		// Left toggle
		if (toggle2 && ControllerMap.leftIntakePistonToggle) {
			toggle2 = false;
			if (piston2) {
				piston2 = false;
				RobotMap.intakeLeft.set(DoubleSolenoid.Value.kForward);
			} else {
				piston2 = true;
				RobotMap.intakeLeft.set(DoubleSolenoid.Value.kReverse);
			}
		} else if (ControllerMap.leftIntakePistonToggle == false) {
			toggle2 = true;
		}
	}
	//TODO: make this control the wrist motor on the intake using feedback from the encoder
	public static void wristControl(int position) {
		if (wristToggle && ControllerMap.wrist) {
			wristToggle = false;
			if (wristPosition) {
				wristPosition = false;
				RobotMap.wrist.getSelectedSensorPosition(0);
			} else {
				wristPosition = true;
				RobotMap.wrist.getSelectedSensorPosition(0);
			}
		} else if (ControllerMap.wrist == false) {
			wristToggle = true;
		}
	}
	public static void initilizeIntake () {
		RobotMap.intakeR = new TalonSRX (Constants.IntakeRtalon);
		RobotMap.intakeL = new TalonSRX (Constants.IntakeLtalon);
		RobotMap.wrist = new TalonSRX (Constants.WristTalon);
	}
}
