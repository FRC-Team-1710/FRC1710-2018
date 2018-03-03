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
	/**
	 * 
	 * @param switchPos position of the switch, retrieved from FMS 'L' or 'R'
	 * @param scalePos position of the scale, retrieved from FMS 'L' or 'R'
	 * @param cubeAmount amount of cubes to be utilized in auto
	 * @param destination switch, scale, or both
	 * @param startPosition left, middle, or right
	 * @return CommandGroup to run based on condition of the field, and conditions input at driverstation
	 */
	public static CommandGroup getAutoToRun(char switchPos, char scalePos, int cubeAmount, int destination, int startPosition) {
    	if(cubeAmount == 1) {
    		//going to switch
    		if(destination == 1) {
    			//starting left
    			if(startPosition == 2) {
    				if(switchPos == 'L') {
    					return (CommandGroup) new LeftStartLeftSwitch();
    				} else {
    					return (CommandGroup) new LeftStartRightSwitch();
    				}
    			//starting right
    			} else if(startPosition == 3) {
    				if(switchPos == 'L') {
    					return (CommandGroup) new RightStartLeftSwitch();
    				} else {
    					return (CommandGroup) new RightStartRightSwitch();
    				}
    			//starting middle
    			} else {
    				if(switchPos=='L') {
    					return (CommandGroup) new MiddleToLeftSwitch();
    				} else {
    					return (CommandGroup) new MiddleToRightSwitch();
    				}
    			}
    		//scale 1 cubes
    		} else {
    			return null;
    		}
    	//2+ cube autos
    	} else {
    		return null;
    	}
	}
}
