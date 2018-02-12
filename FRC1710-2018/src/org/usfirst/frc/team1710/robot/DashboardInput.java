package org.usfirst.frc.team1710.robot;

import java.util.Iterator;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardInput {
	static NetworkTableInstance table = NetworkTableInstance.getDefault();
	static NetworkTable autoChoices = table.getTable("SmartDashboard");
	static NetworkTableEntry destinationOne = autoChoices.getEntry("Destination Both");
	static NetworkTableEntry destinationTwo = autoChoices.getEntry("Destination Switch");
	static NetworkTableEntry destinationThree = autoChoices.getEntry("Destination Scale");
	static NetworkTableEntry positionOne = autoChoices.getEntry("starting position Left");
	static NetworkTableEntry positionTwo = autoChoices.getEntry("starting position Middle");
	static NetworkTableEntry positionThree = autoChoices.getEntry("starting position Right");
	static NetworkTableEntry oneCube = autoChoices.getEntry("One Cube");
	static NetworkTableEntry twoCube = autoChoices.getEntry("Two Cubes");
	static NetworkTableEntry threeCube = autoChoices.getEntry("Three Cubes");
	
	
	public static void updateDashboard(DashboardReport report) {
		for (int i = 0; i < report.getEncoderValues().size(); i++) {
			SmartDashboard.putNumber("Motor " + Integer.toString(report.getSrxs().get(i).getDeviceID()) + " position: ",
					report.getEncoderValues().get(i));	
		}
	}
	
	public static void initializeDashboard(){
		SmartDashboard.putBoolean("Destination Both", false);
		SmartDashboard.putBoolean("Destination Switch", false);
		SmartDashboard.putBoolean("Destination Scale", false);
		SmartDashboard.putBoolean("starting position Left", false);
		SmartDashboard.putBoolean("starting position Middle", false);
		SmartDashboard.putBoolean("starting position Right", false);
		SmartDashboard.putBoolean("One Cube", false);
		SmartDashboard.putBoolean("Two Cubes", false);
		SmartDashboard.putBoolean("Three Cubes", false);
		
	}
	
	public static boolean isRobot1Cubing() {
		return oneCube.getBoolean(false);
	}
	
	public static boolean isRobot2Cubing() {
		return twoCube.getBoolean(false);
	}

	public static boolean isRobot3Cubing() {
		return threeCube.getBoolean(false);
	}
	
	public static boolean isRobotStartingLeft() {
		return destinationOne.getBoolean(false);
	}
	
	public static boolean isRobotStartingMiddle() {
		return destinationTwo.getBoolean(false);
	}

	public static boolean isRobotStartingRight() {
		return destinationThree.getBoolean(false);
	}

	public static boolean isRobotGoingToSwitch() {
		return positionOne.getBoolean(false);
	}

	public static boolean isRobotGoingToScale() {
		return positionTwo.getBoolean(false);
	}

	public static boolean isRobotGoingToBoth() {
		return positionThree.getBoolean(false);
	}
	
}
