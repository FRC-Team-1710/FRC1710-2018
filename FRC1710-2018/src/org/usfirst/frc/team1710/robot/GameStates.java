package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DriverStation;


public class GameStates {

	static String gameData = DriverStation.getInstance().getGameSpecificMessage();
	/**
	 * Looks for the switch if its on the left side. pulls tag from FMS
	 * @return true is the switch is on the left
	 */
	public static boolean isSwitchLeft() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L'){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * 
	 * @return
	 */
	public static boolean isScaleLeft() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(1) == 'L') { 
			return true;
		}else {
			return false;
		}
	}
	
}
