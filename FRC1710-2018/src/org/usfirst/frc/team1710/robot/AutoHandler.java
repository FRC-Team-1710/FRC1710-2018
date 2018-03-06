package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;

import java.util.HashMap;

import commandGroups.LeftStartLeftScaleLeftSwitch;
import commandGroups.LeftStartLeftScaleRightSwitch;
import commandGroups.LeftStartLeftSwitch;
import commandGroups.LeftStartRightSwitch;
import commandGroups.MiddleToLeftSwitch;
import commandGroups.MiddleToRightSwitch;
import commandGroups.RightStartLeftSwitch;
import commandGroups.RightStartRightScaleLeftSwitch;
import commandGroups.RightStartRightScaleRightSwitch;
import commandGroups.RightStartRightSwitch;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


public class AutoHandler {
	
	static HashMap<String, CommandGroup> autoMap = new HashMap<String, CommandGroup>();
	
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
		System.out.println("analyzing... gorgonomics...");
		if(cubeAmount == 1) {
    		//going to switch
    		System.out.println("placing one cube");
    		if(destination == 1) {
        		System.out.println("going to switch");
    			//starting left
    			if(startPosition == 2) {
            		System.out.println("starting left");
    				if(switchPos == 'L') {
                		System.out.println("switch is on left");
    					return (CommandGroup) new LeftStartLeftSwitch();
    				} else {
                		System.out.println("switch is on right");
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
    	} else if (cubeAmount == 2){
    		System.out.println("doing 2 cubes");
    		//switch
    		if(destination == 1) {
    			return null;
    		//scale
    		} else if(destination == 2) {
    			return null;
    		//both
    		} else {
        		System.out.println("going to switch and scale");
    			if(startPosition == 2) {
            		System.out.println("starting left");
    				if(switchPos == 'L' && scalePos == 'L') {
    					//223
    					return (CommandGroup) new LeftStartLeftScaleLeftSwitch();
    				} else if(switchPos == 'R' && scalePos == 'L'){
    					return (CommandGroup) new LeftStartLeftScaleRightSwitch();
    				} else {
    					return null;
    				}
    			} else if(startPosition == 3){
            		System.out.println("starting right");
    				if(switchPos == 'R' && scalePos == 'R') {
    					//223
    					return (CommandGroup) new RightStartRightScaleRightSwitch();
    				} else if(switchPos == 'L' && scalePos == 'R'){
    					return (CommandGroup) new RightStartRightScaleLeftSwitch();
    				} else {
    					return null;
    				}
    			} else {
    				return null;
    			}
    		}
    	} else {
    		return null;
    	}
	}
	
	/**
	 * initializes the hashmap that stores every auto. The key is formatted as follows:
	 * cube amount, starting position, destination, switch position, scale position
	 */
	public static void initAutoMap() {
		// one cube, starts middle, going to switch, switch and scale on left (scale doesn't matter)
		autoMap.put("111LL", new MiddleToLeftSwitch());
		autoMap.put("111LR", new MiddleToRightSwitch());
		// one cube, starts middle, going to switch, switch and scale on right (scale doesn't matter)
		autoMap.put("111RR", new MiddleToRightSwitch());
		autoMap.put("111RL", new MiddleToRightSwitch());
		// one cube, starts left, going to switch, switch and scale on left (scale doesn't matter)
		autoMap.put("121LL", new LeftStartLeftSwitch());
		autoMap.put("121LR", new LeftStartLeftSwitch());
		// one cube, starts left, going to switch, switch and scale on right (scale doesn't matter)
		autoMap.put("121RL", new LeftStartRightSwitch());
		autoMap.put("121RR", new LeftStartRightSwitch());
		// one cube, starts right, going to switch, switch and scale on left (scale doesn't matter)
		autoMap.put("131LL", new RightStartLeftSwitch());
		autoMap.put("131LR", new RightStartLeftSwitch());
		// one cube, starts right, going to switch, switch and scale on right (scale doesn't matter)
		autoMap.put("131RL", new RightStartRightSwitch());
		autoMap.put("131RR", new RightStartRightSwitch());
		// two cubes, starts left, going to switch and scale, switch and scale on left 
		autoMap.put("223LL", new LeftStartLeftScaleLeftSwitch());
		autoMap.put("223LR", new LeftStartLeftScaleLeftSwitch());
	}
	
	public static CommandGroup getAuto(char switchPos, char scalePos, int cubeAmount, int destination, int startPosition) {
		StringBuilder key = new StringBuilder();
		key.append(cubeAmount);
		key.append(startPosition);
		key.append(destination);
		key.append(switchPos);
		key.append(scalePos);
		return autoMap.get(key);
	}
	
}
