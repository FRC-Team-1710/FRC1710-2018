package org.usfirst.frc.team1710.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.HashMap;

import commandGroups.LeftStartDoubleLeftScaleLeftSwitch;
import commandGroups.LeftStartDoubleScaleLeft;
import commandGroups.LeftStartDoubleScaleRight;
import commandGroups.LeftStartLeftScale;
import commandGroups.LeftStartLeftScaleLeftSwitch;
import commandGroups.LeftStartLeftScaleLeftSwitchDriveBy;
import commandGroups.LeftStartLeftScaleRightSwitch;
import commandGroups.LeftStartLeftSwitch;
import commandGroups.LeftStartRightScaleRightSwitch;
import commandGroups.LeftStartRightSwitch;
import commandGroups.MiddleToLeftSwitch;
import commandGroups.MiddleToLeftSwitchRightScale;
import commandGroups.MiddleToRightSwitch;
import commandGroups.RightStartDoubleScale;
import commandGroups.RightStartDoubleScaleLeft;
import commandGroups.RightStartDoubleScaleLeftSwitch;
import commandGroups.RightStartLeftScaleLeftSwitch;
import commandGroups.RightStartLeftSwitch;
import commandGroups.RightStartLeftSwitchDoubleLeftScale;
import commandGroups.RightStartRightScale;
import commandGroups.RightStartRightScaleLeftSwitch;
import commandGroups.RightStartRightScaleRightSwitch;
import commandGroups.RightStartRightSwitch;
import commandGroups.RightStartRightSwitchDoubleRightScale;
import commandGroups.RightStartRightSwitchLeftScale;
import commandGroups.RightStartThreeCubeLeftScale;
import commandGroups.Testing;
import commandGroups.ThreeCubeRightScale;
import commandGroups.diagnostics.DriveDiagnostic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;


public class AutoHandler {
	
	static HashMap<String, CommandGroup> autoMap = new HashMap<String, CommandGroup>();
	
	/**
	 * initializes the hashmap that stores every auto. The key is formatted as follows:
	 * cube amount, starting position, destination, switch position, scale position
	 */
	public static void initAutoMap() {
		//TODO: Get rid of the waits before comp!!! test all autos again when intake is back on
		autoMap.put("213LR", new MiddleToLeftSwitchRightScale());

		// one cube, starts middle, going to switch, switch and scale on left (scale doesn't matter)
		autoMap.put("111LL", new MiddleToLeftSwitch());
		autoMap.put("111LR", new MiddleToLeftSwitch());
		// one cube, starts middle, going to switch, switch and scale on right (scale doesn't matter)
		autoMap.put("111RR", new MiddleToRightSwitch());
		autoMap.put("111RL", new MiddleToRightSwitch());
		// one cube, starts left, going to switch, switch and scale on left (scale doesn't matter)
		autoMap.put("121LL", new LeftStartLeftSwitch());
		autoMap.put("121LR", new LeftStartLeftSwitch());
		autoMap.put("223LR", new LeftStartLeftSwitch());
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
		autoMap.put("223RL", new LeftStartLeftScale());
		autoMap.put("223RR", new LeftStartRightSwitch());
		autoMap.put("223LR", new LeftStartLeftSwitch());
		
		autoMap.put("233RR", new ThreeCubeRightScale());
		autoMap.put("233LR", new ThreeCubeRightScale());
		autoMap.put("233LL", new RightStartLeftScaleLeftSwitch());
		autoMap.put("233RL", new RightStartRightSwitchLeftScale());
		
		autoMap.put("232RR", new ThreeCubeRightScale());
		autoMap.put("232LL", new RightStartDoubleScaleLeft());
		
		autoMap.put("332RR", new ThreeCubeRightScale());
		autoMap.put("332LR", new ThreeCubeRightScale());
		
		autoMap.put("332LL", new RightStartThreeCubeLeftScale());
		autoMap.put("332RL", new RightStartThreeCubeLeftScale());
		
		autoMap.put("333RR", new RightStartRightSwitchDoubleRightScale());
		autoMap.put("333LL", new RightStartLeftSwitchDoubleLeftScale());
		autoMap.put("333LR", new RightStartDoubleScaleLeftSwitch());
		autoMap.put("333RL", new RightStartRightSwitchLeftScale());
		
		autoMap.put("323LL", new LeftStartDoubleLeftScaleLeftSwitch());
		autoMap.put("323LR", new LeftStartLeftSwitch());
		autoMap.put("323RL", new LeftStartLeftScale());
		autoMap.put("323RR", new LeftStartRightScaleRightSwitch());
		
		autoMap.put("222LL", new LeftStartDoubleScaleLeft());
		autoMap.put("232RR", new LeftStartDoubleScaleRight());
		
		autoMap.put("122LL", new LeftStartLeftScale());
		autoMap.put("122RL", new LeftStartLeftScale());

		autoMap.put("132LR", new RightStartRightScale());
		autoMap.put("132RR", new RightStartRightScale());
		
		///autoMap.put("223RR", new LeftStartRightScaleRightSwitch());
		autoMap.put("000RR", new Testing());
		autoMap.put("000LR", new Testing());
		autoMap.put("000RL", new Testing());
		autoMap.put("000LL", new Testing());
	}
	
	public static CommandGroup getAuto(char switchPos, char scalePos, int cubeAmount, int destination, int startPosition) {
		StringBuilder key = new StringBuilder();
		key.append(cubeAmount);
		key.append(startPosition);
		key.append(destination);
		key.append(switchPos);
		key.append(scalePos);
		
		if(autoMap.containsKey(key.toString())) {
			SmartDashboard.putString("key generated", key.toString());
			SmartDashboard.putString("auto that ran", autoMap.get(key.toString()).getName());
			return autoMap.get(key.toString());
		} else {
			return Constants.defaultAuto;
		}
	}
	
}
