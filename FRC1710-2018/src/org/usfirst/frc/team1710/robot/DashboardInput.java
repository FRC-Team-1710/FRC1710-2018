package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class DashboardInput {
	static NetworkTableInstance table = NetworkTableInstance.getDefault();
	static NetworkTable autoChoices = table.getTable("SmartDashboard");
	static NetworkTableEntry destinationOne = autoChoices.getEntry("Destination_Both");
	static NetworkTableEntry destinationTwo = autoChoices.getEntry("Destination_Switch");
	static NetworkTableEntry destinationThree = autoChoices.getEntry("Destination_Scale");
	static NetworkTableEntry positionOne = autoChoices.getEntry("starting_position_Left");
	static NetworkTableEntry positionTwo = autoChoices.getEntry("starting_position_Middle");
	static NetworkTableEntry positionThree = autoChoices.getEntry("starting_position_Right");
	
	
	
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
