package org.usfirst.frc.team1710.robot;

import java.util.Iterator;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardInput {
	
	public static void setUpDashboard() {
	}
	
	public static void updateDashboard(DashboardReport report) {
		for (int i = 0; i < report.getEncoderValues().size(); i++) {
			SmartDashboard.putNumber("Motor " + Integer.toString(report.getSrxs().get(i).getDeviceID()) + " position: ",
					report.getEncoderValues().get(i));
		}
	}
	

	public static boolean isRobotGoingToSwitch() {
		return true;
	}

	public static boolean isRobotGoingToScale() {
		return true;
		
	}

	public static boolean isRobotGoingToBoth() {
		return true;
	}
	
}
