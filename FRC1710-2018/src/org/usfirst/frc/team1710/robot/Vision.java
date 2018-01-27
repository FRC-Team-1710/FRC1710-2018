package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
	public static void cubeTracking() {
	NetworkTableInstance table = NetworkTableInstance.getDefault();
	NetworkTable tableTwo = table.getTable("limelight");
	NetworkTableEntry xEntry, yEntry, tvEntry;
	double kp = .023;
	xEntry = tableTwo.getEntry("tx");
	yEntry = tableTwo.getEntry("ty");
	tvEntry = tableTwo.getEntry("tv");
	double txValue = xEntry.getDouble(0);
	double yValue = yEntry.getDouble(0);
	double tvValue = tvEntry.getDouble(0);
	if (tvValue==0) {
		RobotMap.L1.set(ControlMode.PercentOutput,.3);
		RobotMap.R1.set(ControlMode.PercentOutput,.3);
	}else {
	Drive.arcadeDrive(-(23/ Math.pow(yValue,2)), txValue* kp);
	}

	}
}