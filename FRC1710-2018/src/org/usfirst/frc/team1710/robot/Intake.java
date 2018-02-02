package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake {
	static boolean toggle = true;
	static boolean toggle2 = true;
	static boolean piston = false;
	static boolean piston2 = false;
	static double wristPosition;
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
		if (ControllerMap.wristUp == true) {
			wristPosition = Constants.wristUp;
		} else if (ControllerMap.wristLaunch == true) {
			wristPosition = Constants.wristLaunch;
		} else if (ControllerMap.wristDown == true) {
			wristPosition = Constants.wristDown;
		}
	}
	public static void initilizeIntake () {
		RobotMap.intakeR = new TalonSRX (Constants.IntakeRtalon);
		RobotMap.intakeL = new TalonSRX (Constants.IntakeLtalon);
		RobotMap.wrist = new TalonSRX (Constants.WristTalon);
	}
}
