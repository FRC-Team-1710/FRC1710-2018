package org.usfirst.frc.team1710.robot;

public class Constants {
	//constants for the lift 0 to 9700
	public static double intake = 100;
	public static double switchPosition = 1500;
	public static double scaleLow= 4000;
	public static double scaleNormal = 7000;
	public static double scaleHigh = 9000;
	public static String intakeLevelName = "intake";
	public static String switchLevelName = "switch";
	public static String lowLevelName = "low level";
	public static String normalLevelName = "Scale Normal";
	public static String highLevelName = "Scale High";
	public static String liftingLevelName = "lifting";
	//the constant for moving down is less bc when we go down we work with gravity and that prevents things from 
	//getting too violent
	public static double kPLiftUp = 0.0006;
	public static double kPLiftDown = 0.00025;
	
	public static int shifterReverse = 0;
	public static int shifterForward = 1;
	
	//constants for drive
	public static double kpStraight = .01;
	public static double kpTurn = 0.01;
	
	//constants for Vision
	public static double kpAim = .035;
	public static double kpDistance= .05;
	public static double seekingSpeed = .3;
	public static double cubeIntakeSpeed = 1;
	public static double tyIntake = 3.5;
	
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
	public static int ticksPerRev = 3900;
	public static double kpPath = 0.01;
	public static double kiPath = 0;
	public static double kdPath = 0;
	
	public static double dt = 0.02;
	public static double maxV = 1.275;
	public static double maxAccel = 1.5;
	public static double accGain = 0.01;
	public static double maxJerk = 60;
	
	//rotate to angle
	public static double rotateToAngleHiEnd = 5;
	public static double rotateToAngleLoEnd = 5;
	
	public static int ultraSonicConstant = 4;
	public static double ultraSonic0 = 0;
	public static double ultraSonicInIntake = .28;
	
}
