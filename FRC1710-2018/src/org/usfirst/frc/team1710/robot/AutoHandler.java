package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


public class AutoHandler {
	
	public static CommandGroup getAutoToRun() {
		if(DashboardInput.isRobotGoingToScale() == true) {
			if(DashboardInput.isRobotStartingLeft() == true) {
				//return command group to go to scale when starting on the left
				return null;
			}else if(DashboardInput.isRobotStartingMiddle() == true) {
				//return command group to go to scale when starting middle
				return null;
			}else {
				//return command group to go to scale starting right
				return null;
			}
		
		}else if(DashboardInput.isRobotGoingToSwitch() == true) {
			if(DashboardInput.isRobotStartingLeft() == true) {
				//return command group to go to switch when starting on the left
				return null;
			}else if(DashboardInput.isRobotStartingMiddle() == true) {
				//return command group to fo to switch when starting in the middle
				return null;
			}else {
				//return command group to go to swtich when starting right
				return null;
			}
		}else {
			if(DashboardInput.isRobotStartingLeft() == true) {
				//return command group to go to both starting left
				return null;
			}else if(DashboardInput.isRobotStartingMiddle() == true) {
				//return command group to go to both starting middle
				return null;
			}else {
				//return command group to go to both starting right
				return null;
			}
		}
	}
}
