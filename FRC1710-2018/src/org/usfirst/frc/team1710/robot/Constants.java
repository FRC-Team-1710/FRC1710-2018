package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Constants {
	//constants for the lift
	public static double distance1 = 0;
	public static double distance2 = 4000;
	public static double distance3 = 6000;
	public static double distance4 = 8000;
	public static double kPLift = 0.0008;
	
	//constants for drive
	public static double kpStraight = .01;
	
	//constants for Vision
	public static double kpAim = .02;
	public static double kpDistance= .1;
	
	//constants for talons srx 
	public static int rightLeaderid=8;
	public static int rightFollowerid=1;
	public static int rightFollowerid2=3;
	public static int leftLeaderid=9;
	public static int leftFollowerid= 3;
	public static int leftFollowerid2= 4;
	
	//constants for intake 
	public static int IntakeRtalon=7;
	public static int IntakeLtalon=8;
	
	public static int WrisTalon=6;
	public static int lift1Talon=5;
	public static int lift2Talon=10;
	
	
	
	
	
}
