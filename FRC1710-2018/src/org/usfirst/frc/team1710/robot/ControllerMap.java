package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap {
	static boolean one, two, three, four, leftIntakePistonToggle, rightIntakePistonToggle, wristUp,
	wristLaunch, wristDown, rampDeploy, rampLift, shift, visionActivated;
	static double liftPower, intakeR, intakeL, turnPower, forwardPower;
	public static void InitializeControllerMap() {
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick (1);
	}
	
	public static void updateControllers() {
		one = RobotMap.mechStick.getRawButton(1);
		two = RobotMap.mechStick.getRawButton(2);
		three = RobotMap.mechStick.getRawButton(3);
		four = RobotMap.mechStick.getRawButton(4);
		leftIntakePistonToggle = RobotMap.mechStick.getRawButton(5);
		rightIntakePistonToggle = RobotMap.mechStick.getRawButton(6);
		wristUp = RobotMap.mechStick.getRawButton(8);
		wristLaunch = RobotMap.mechStick.getRawButton(9);
		wristDown = RobotMap.mechStick.getRawButton(10);

		//Mechstick Axis
		
		liftPower = RobotMap.mechStick.getRawAxis(1);
		intakeR = RobotMap.mechStick.getRawAxis(1);
		intakeL = RobotMap.mechStick.getRawAxis(2);
		
		//Drivestick PLEASE ORGANIZE
		rampDeploy = RobotMap.mechStick.getRawButton(3);
		rampLift = RobotMap.mechStick.getRawButton(4);
		
		shift = RobotMap.driveStick.getRawButton(5);
		visionActivated = RobotMap.driveStick.getRawButton(1) || RobotMap.mechStick.getRawButton(1);

		//Drivestick Axis
		
		turnPower = -RobotMap.driveStick.getRawAxis(4);
		forwardPower = RobotMap.driveStick.getRawAxis(1);
	}
}

