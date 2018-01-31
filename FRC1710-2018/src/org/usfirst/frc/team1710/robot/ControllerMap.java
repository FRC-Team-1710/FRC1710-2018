package org.usfirst.frc.team1710.robot;

public class ControllerMap {
	
	static boolean one = RobotMap.mechStick.getRawButton(1);
	static boolean two = RobotMap.mechStick.getRawButton(2);
	static boolean three = RobotMap.mechStick.getRawButton(3);
	static boolean four = RobotMap.mechStick.getRawButton(4);
	static double liftPower = RobotMap.mechStick.getRawAxis(1);
	static double turnPower = -RobotMap.driveStick.getRawAxis(4);
	static double forwardPower = RobotMap.driveStick.getRawAxis(1);
	static boolean visionActivate = RobotMap.driveStick.getRawButton(1) || RobotMap.mechStick.getRawButton(1);
	static boolean shift = RobotMap.driveStick.getRawButton(5);

}
