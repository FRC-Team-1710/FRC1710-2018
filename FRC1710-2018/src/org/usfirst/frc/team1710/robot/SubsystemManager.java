package org.usfirst.frc.team1710.robot;
public class SubsystemManager {
	
	public static void masterinitialization() {
		Drive.initializeDrive();
		Intake.initializeIntake();
		ControllerMap.InitializeControllerMap();
		lift.initializeLift();
		Vision.initializeVision();
	}

}
