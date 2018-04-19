package org.usfirst.frc.team1710.robot;

import commandGroups.Testing;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Constants {
	
	public static int defaultCubeAmount = 2;
	public static int defaultStartPos = 3;
	public static int defaultDestination = 3;
	
	public static int wallToScale = 230;
	public static int wallToSwitchCorner = 192;
	public static int switchCornerToScalePlacepos = 230;
	
	public static double[][] testPoints = {{10, 10}, {20, 10}};
	
	public static boolean inAuto = true;
	//idk, run robot in auto at 60% output and look at the velocity
	public static double liftingNotSafeVelocity = 1600;
	
	public static CommandGroup defaultAuto = new Testing();
	
	public static double psiPerVolts = 44.494;
	public static double intake = 100;
	public static double aboveBump = 1500;
	public static double switchPosition = 3500;
	public static double scaleLow= 4500;
	public static double scaleNormal = 6500;
	public static double scaleHigh = 9000;
	public static String intakeLevelName = "intake";
	public static String switchLevelName = "switch";
	public static String lowLevelName = "low level";
	public static String normalLevelName = "Scale Normal";
	public static String highLevelName = "Scale High";
	public static String liftingLevelName = "lifting";
	//the constant for moving down is less bc when we go down we work with gravity and that prevents things from 
	//getting too violent
	//kpUp - comp: .0004`
	//kpDown - comp : .00008
	public static double kPLift = 0.0006;
	public static double kDLift = 0;	
	public static double kILift = 0;

	//.000215
	public static double kPLiftDown = 0.000215;
	
	public static int shifterReverse = 0;
	public static int shifterForward = 1;
	
	//constants for drive
	//.013
	public static double kpStraight = .0125;
	public static double kiStraight = .001;
	public static double kdStraight = .00075;
	//was .0125
	public static double kpStraightHi = .012;
	public static double kiStraightHi = .000025;
	public static double kdStraightHi = .005;
	
	public static double kiDrive = .000025;
	
	public static double kpTurn = 0.01;
	
	public static int ticksPerInch = 217;
	
	//constants for Vision
	public static double kpAim = .03;
	public static double kpDistance= 0.1;
	public static double seekingSpeed = .3;
	public static double cubeIntakeSpeed = 1;
	public static double tyIntake = 5.5;
	public static double txIntake = 10;
	public static double autoTrackTimeout = 45;
	public static double ultraSonicInIntakeVision = 238;
	
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
	
	//change back to 6
	public static int wristTalon=10;
	public static int liftRightTalon=5;
	public static int liftLeftTalon=6;
	
	//constants for wrist
	public static int wristUp = 0;
	public static int wristLaunch = 300;
	public static int wristDown = 800;
	public static double kPWrist = -.0012;
	
	//pathfinder (dimensions in meters)
	public static double robotDriveBaseWidth = 25;
	public static double wheelDiameter = .1524;
	public static int ticksPerRev = 4096;
	public static double kpPath = 0.01;
	public static double kiPath = 0;
	public static double kdPath = 0;
	
	public static double dt = 0.02;
	public static double maxV = 1.275;
	public static double maxAccel = 1.5;
	public static double accGain = 0.01;
	public static double maxJerk = 60;
	
	public static double maxVHi = 3;
	public static double maxAccelHi = 3;
	
	//rotate to angle
	public static double rotateToAngleHiEnd = 7;
	public static double rotateToAngleLoEnd = 7;
	
	public static int ultraSonicConstant = 4;
	public static double ultraSonic0 = 0;
	public static double ultraSonicInIntake = 235;
	public static double slowDownPercent = 0.1;
	
}