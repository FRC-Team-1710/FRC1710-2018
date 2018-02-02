package org.usfirst.frc.team1710.robot;

public class ControllerMap {
	//TODO: make names better
	//one, two, three, four are lift position buttons
	static boolean one = RobotMap.mechStick.getRawButton(1);
	static boolean two = RobotMap.mechStick.getRawButton(2);
	static boolean three = RobotMap.mechStick.getRawButton(3);
	static boolean four = RobotMap.mechStick.getRawButton(4);
	static double liftPower = RobotMap.mechStick.getRawAxis(1);
	static double turnPower = -RobotMap.driveStick.getRawAxis(4);
	static double forwardPower = RobotMap.driveStick.getRawAxis(1);
	static boolean visionActivated = RobotMap.driveStick.getRawButton(1) || RobotMap.mechStick.getRawButton(1);
	static boolean shift = RobotMap.driveStick.getRawButton(5);
	static boolean leftIntakePistonToggle = RobotMap.mechStick.getRawButton(5);
	static boolean rightIntakePistonToggle = RobotMap.mechStick.getRawButton(6);
	static boolean wrist = RobotMap.mechStick.getRawButton(7);
	static boolean rampDeploy = RobotMap.mechStick.getRawButton(3);
	static boolean rampLift = RobotMap.mechStick.getRawButton(4);
	
}
