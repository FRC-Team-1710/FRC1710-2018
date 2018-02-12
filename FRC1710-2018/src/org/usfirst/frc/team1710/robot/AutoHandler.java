package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


public class AutoHandler {
	
	public static CommandGroup getAutoToRun() {
		if(DashboardInput.isRobot1Cubing() == true) {
			
			if(DashboardInput.isRobotStartingLeft() == true) {
				
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 1 cube starting left going to switch	
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 1 cube starting left going to scale
				}
			}else if(DashboardInput.isRobotStartingMiddle() == true) {
				
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 1 cube starting middle going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 1 cube starting middle going scale
				}
			}else {
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 1 cube starting right going switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 1 cube starting right going to scale
				}
			}
		
		}else if(DashboardInput.isRobot2Cubing() == true) {
			
			if(DashboardInput.isRobotStartingLeft() == true) {
				
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 2 cube starting left going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 2 cube starting left going to scale
				}else if(DashboardInput.isRobotGoingToBoth() == true) {
					return null;
					//run auto 2 cube starting left going to both
				}
			}else if(DashboardInput.isRobotStartingMiddle() == true) {
			
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 2 cube starting middle going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 2 cube starting middle going to scale
				}else if(DashboardInput.isRobotGoingToBoth() == true) {
					return null;
					//run auto 2 cube starting middle going to both
				}
			}else {
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 2 cube starting right going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 2 cube starting right going to scale
				}else if(DashboardInput.isRobotGoingToBoth() == true) {
					return null;
					//run auto 2 cube starting right going to both
				}
			}
		}else if(DashboardInput.isRobot3Cubing() == true) {
			
			if(DashboardInput.isRobotStartingLeft() == true) {
			
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 3 cube starting left going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 3 cube starting left going to scale
				}else if(DashboardInput.isRobotGoingToBoth() == true) {
					return null;
					//run auto 3 cube starting left going to both
				}
			}else if(DashboardInput.isRobotStartingMiddle() == true) {
				
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 3 cube starting middle going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 3 cube starting middle going to scale
				}else if(DashboardInput.isRobotGoingToBoth() == true) {
					return null;
					//run auto 3 cube starting middle going to both
				}
			}else {
				if(DashboardInput.isRobotGoingToSwitch() == true) {
					return null;
					//run auto 3 cube starting right going to switch
				}else if(DashboardInput.isRobotGoingToScale() == true) {
					return null;
					//run auto 3 cube starting right going to scale
				}else if(DashboardInput.isRobotGoingToBoth() == true) {
					return null;
					//run auto 3 cube starting right going to both
				}
			}
		}
	}
}
