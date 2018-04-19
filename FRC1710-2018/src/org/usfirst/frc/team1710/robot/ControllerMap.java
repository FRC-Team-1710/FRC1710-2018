package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap {
	public static void InitializeControllerMap() {
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick (1);
	}
	//buttons 
	public static boolean bottomLift() {
		return RobotMap.mechStick.getRawButton(7) || RobotMap.driveStick.getRawButton(1);
	}
	public static boolean liftAtSwitchHeight() {
		return RobotMap.mechStick.getRawButton(8) || RobotMap.driveStick.getRawButton(2);
	}
	public static boolean liftAtScaleLow() {
		return RobotMap.mechStick.getRawButton(9);
	}
	public static boolean liftAtScaleNormal() {
		return RobotMap.mechStick.getRawButton(10) || RobotMap.driveStick.getRawButton(3);
	}
	public static boolean liftAtScaleHigh() {
		return RobotMap.mechStick.getRawButton(11) || RobotMap.driveStick.getRawButton(4);
	}
	public static boolean getMechTrigger() {
		return RobotMap.mechStick.getRawButton(1);
	}
	public static boolean leftIntakeToggle() {
		return RobotMap.mechStick.getRawButton(5);
	}
	public static boolean rightIntakeToggle() {
		return RobotMap.mechStick.getRawButton(6);
	}
	public static boolean matchClimb() {
		return RobotMap.mechStick.getRawButton(3);
	}
	public static boolean wristLaunch() {
		return RobotMap.mechStick.getRawButton(12);
	}
	public static boolean wristUp() {
		return RobotMap.mechStick.getRawButton(6);
	}
	public static boolean wristDown() {
		return RobotMap.mechStick.getRawButton(5);
	}
	public static boolean practiceClimb() {
		return RobotMap.mechStick.getRawButton(4);
	}

	public static boolean shift() {
		return RobotMap.driveStick.getRawButton(9);
	}
	public static boolean visionActivated() {
		return false;
	}
	public static boolean ultraSonicIntake() {
		return RobotMap.driveStick.getRawButton(3);
	}
	public static boolean carefulPlace() {
		return RobotMap.driveStick.getRawButton(10);
	}
	//Axis
	public static double getTurnPower() {
		return -RobotMap.driveStick.getRawAxis(4);
	}
	public static double getForwardPower() {
		return RobotMap.driveStick.getRawAxis(1);
	}
	public static double liftPower() {
		if(Math.abs(RobotMap.mechStick.getRawAxis(1)) >= 0.25) {
			return RobotMap.mechStick.getRawAxis(1); 
		}else if(RobotMap.driveStick.getRawButton(5) == true) {
			return .6;
		}else if(RobotMap.driveStick.getRawButton(6) == true) {
			return -.6;
		}else {
			return 0;
		}
	}
	public static double wristPower() {
		return RobotMap.mechStick.getRawAxis(1);
	}
	public static double intakeR() {
		return RobotMap.driveStick.getRawAxis(2);
	}
	public static double intakeL() {
		return RobotMap.driveStick.getRawAxis(3);
	}
}

