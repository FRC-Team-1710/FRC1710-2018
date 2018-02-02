package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap {
	//TODO: make names better
	public static void InitilizeControllerMap( ) {
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick (1);
	}
	
	//YALL BETTER ORGANIZE
	
	//one, two, three, four are lift position buttons
	static boolean one = RobotMap.mechStick.getRawButton(1);
	static boolean two = RobotMap.mechStick.getRawButton(2);
	static boolean three = RobotMap.mechStick.getRawButton(3);
	static boolean four = RobotMap.mechStick.getRawButton(4);
	static boolean leftIntakePistonToggle = RobotMap.mechStick.getRawButton(5);
	static boolean rightIntakePistonToggle = RobotMap.mechStick.getRawButton(6);
	static boolean wristUp = RobotMap.mechStick.getRawButton(8);
	static boolean wristLaunch = RobotMap.mechStick.getRawButton(9);
	static boolean wristDown = RobotMap.mechStick.getRawButton(10);

	//Mechstick Axis
	
	static double liftPower = RobotMap.mechStick.getRawAxis(1);
	
	//Drivestick PLEASE ORGANIZE
	static boolean rampDeploy = RobotMap.mechStick.getRawButton(3);
	static boolean rampLift = RobotMap.mechStick.getRawButton(4);
	
	static boolean shift = RobotMap.driveStick.getRawButton(5);
	static boolean visionActivated = RobotMap.driveStick.getRawButton(1) || RobotMap.mechStick.getRawButton(1);

	//Drivestick Axis
	
	static double turnPower = -RobotMap.driveStick.getRawAxis(4);
	static double forwardPower = RobotMap.driveStick.getRawAxis(1);
}

