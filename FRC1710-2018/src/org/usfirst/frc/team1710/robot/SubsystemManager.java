package org.usfirst.frc.team1710.robot;
public class SubsystemManager {
	public static void masterinitilization() {
		Drive.initilizeDrive();
		Intake.initilizeIntake();
		ControllerMap.InitilizeControllerMap();
		lift.initilizeLift();
		Vision.initilizeVision();
		
	}

}
