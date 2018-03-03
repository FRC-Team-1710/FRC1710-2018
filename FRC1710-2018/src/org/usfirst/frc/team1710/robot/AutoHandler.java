package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import commandGroups.LeftStartLeftSwitch;
import commandGroups.LeftStartRightSwitch;
import commandGroups.MiddleToLeftSwitch;
import commandGroups.MiddleToRightSwitch;
import commandGroups.RightStartLeftSwitch;
import commandGroups.RightStartRightSwitch;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


public class AutoHandler {
	
	public static CommandGroup getAutoToRun(char switchPos, char scalePos, int cubeAmount, int destination, int startPosition) {
    	if(cubeAmount == 1) {
    		if(destination == 2) {
    			if(startPosition == 2) {
    				if(switchPos == 'L') {
    					return (CommandGroup) new LeftStartLeftSwitch();
    				} else {
    					return (CommandGroup) new LeftStartRightSwitch();
    				}
    			} else if(startPosition == 3) {
    				if(switchPos == 'L') {
    					return (CommandGroup) new RightStartLeftSwitch();
    				} else {
    					return (CommandGroup) new RightStartRightSwitch();
    				}
    			} else {
    				if(switchPos=='L') {
    					return (CommandGroup) new MiddleToLeftSwitch();
    				} else {
    					return (CommandGroup) new MiddleToRightSwitch();
    				}
    			}
    		} else {
    			//one cube in switch
    			return null;
    		}
    	} else {
    		return null;
    	}
	}
}
