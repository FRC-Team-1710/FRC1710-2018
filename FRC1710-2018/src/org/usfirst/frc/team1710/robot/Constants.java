package org.usfirst.frc.team1710.robot;

public class Constants {
	//constants for the lift
	public static double intake = 0;
	public static double switchPosition = 4000;
	public static double lowLevel= 6000;
	public static double highLevel = 8000;
	public static double kPLift = 0.0008;
	
	public static int shifterReverse = 0;
	public static int shifterForward = 1;
	
	//constants for drive
	public static double kpStraight = .01;
	public static double kpTurn = 0.01;
	
	//constants for Vision
	public static double kpAim = .02;
	public static double kpDistance= .1;
	
	//constants for talons srx 
	public static int rightLeaderid=8;
	public static int rightFollowerid=1;
	public static int rightFollowerid2=3;
	public static int leftLeaderid=9;
	public static int leftFollowerid= 2;
	public static int leftFollowerid2= 4;
	
	//constants for intake 
	public static int intakeRSpark=0;
	public static int intakeLSpark=1;
	
	public static int wristTalon=6;
	public static int liftRightTalon=5;
	public static int liftLeftTalon=10;
	
	//constants for wrist
	public static int wristUp = 0;
	public static int wristLaunch = 1;
	public static int wristDown = 2;
	public static double kPWrist = .01;
	
	//pathfinder (dimensions in meters)
	public static double robotDriveBaseWidth = .6985;
	public static double wheelDiameter = .1524;
	public static int ticksPerRev = 1500;
	
	public static double dt = 0.02;
	public static double maxV = 2.55;
	public static double maxAccel = 3;
	public static double accGain = 0.01;
	public static double maxJerk = 60;
	
	//rotate to angle
	public static double rotateToAngleHiEnd = 5;
	public static double rotateToAngleLoEnd = 5;
}
