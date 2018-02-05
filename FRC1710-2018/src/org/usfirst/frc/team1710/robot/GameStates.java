package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DriverStation;


public class GameStates {

	static String gameData = DriverStation.getInstance().getGameSpecificMessage();
	
	public static boolean isSwitchLeft() {
		if(gameData.charAt(0) == 'L'){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isScaleLeft() {
		if(gameData.charAt(1) == 'L') { 
			return true;
		}else {
			return false;
		}
	}
	
}
