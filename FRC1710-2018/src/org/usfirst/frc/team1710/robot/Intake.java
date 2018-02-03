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

	public static void initializeIntake () {
		RobotMap.intakeR = new TalonSRX (Constants.IntakeRtalon);
		RobotMap.intakeL = new TalonSRX (Constants.IntakeLtalon);
		RobotMap.wrist = new TalonSRX (Constants.WristTalon);
	}
	
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
	public static int getWristEncPosition() {
		return RobotMap.wrist.getSelectedSensorPosition(0);
	}
	public static void wristControl(int position) {
		if (ControllerMap.wristUp == true) {
			wristPosition = Constants.wristUp;
		} else if (ControllerMap.wristLaunch == true) {
			wristPosition = Constants.wristLaunch;
		} else if (ControllerMap.wristDown == true) {
			wristPosition = Constants.wristDown;
		}
	}
	public static String getWristPosition() {
		if (getWristEncPosition() > Constants.wristUp + 5 || getWristEncPosition() < Constants.wristUp - 5) {
			return "Keeping cube in";
		} else if (getWristEncPosition() > Constants.wristLaunch + 5 || getWristEncPosition() < Constants.wristLaunch - 5) {
			return "Launch cube upward, at angle";
		} else if (getWristEncPosition() > Constants.wristDown + 5 || getWristEncPosition () < Constants.wristDown - 5) {
			return "Wrist is parallell to the ground";
		} else {
			return "changing position. Or broken";
		}
	}
}
