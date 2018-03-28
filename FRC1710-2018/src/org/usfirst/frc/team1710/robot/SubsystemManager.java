package org.usfirst.frc.team1710.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SubsystemManager {
	
	public static void masterinitialization() {
		Drive.initializeDrive();
		Intake.initializeIntake();
		ControllerMap.InitializeControllerMap();
		lift.initializeLift();
		Vision.initializeVision();
		RobotMap.navx.reset();
	}
	public static void masterReset() {
		RobotMap.navx.reset();
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.shifter.set(DoubleSolenoid.Value.kOff);
	}
	public static void encoderReset() {
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
	}
	public static void NavxReset() {
		RobotMap.navx.reset();
	}
	public static void rampReset() {
		RobotMap.rampDeploy.set(DoubleSolenoid.Value.kOff);
		RobotMap.rampExtendo.set(DoubleSolenoid.Value.kOff);
		RobotMap.rampLifto.set(DoubleSolenoid.Value.kOff);
		
	}
	public static void intakeReset() {
		RobotMap.intakeRight.set(DoubleSolenoid.Value.kOff);
		RobotMap.intakeLeft.set(DoubleSolenoid.Value.kOff);
	}
	public static void shifterReset() {
		RobotMap.shifter.set(DoubleSolenoid.Value.kOff);
	}
}

