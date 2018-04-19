package org.usfirst.frc.team1710.robot;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import commandGroups.BuildPath;
import commandGroups.LeftStartLeftScale;
import commandGroups.LeftStartLeftSwitch;
import commandGroups.LeftStartRightSwitch;
import commandGroups.MiddleToLeftSwitch;
import commandGroups.MiddleToRightSwitch;
import commandGroups.RightStartLeftSwitch;
import commandGroups.RightStartRightScale;
import commandGroups.RightStartRightSwitch;
import commandGroups.Testing;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectory.trajectoryTestCGroup;
import utility.RobotMath;


public class Robot extends IterativeRobot {
	
	public static int cubeAmount, startingPosition, destination;
	boolean wristReset;
	public static Timer autoTime;
	
	@Override
	public void robotInit() {
		wristReset = false;
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
		RobotMap.compressor = new Compressor(0);
		
		RobotMap.pdp = new PowerDistributionPanel();
		
		RobotMap.navx.reset();
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		
		SmartDashboard.putNumber("cube amount", 1);
		SmartDashboard.putNumber("Starting position", 1);
		SmartDashboard.putNumber("destination",1);
		AutoHandler.initAutoMap();
		autoTime = new Timer();
	}

	@Override 
	public void autonomousInit() {
		Constants.inAuto = true;
		AutoHandler.initAutoMap();
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		SubsystemManager.masterReset();
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		
		RobotMap.R1.configClosedloopRamp(.5, 0);
		RobotMap.L1.configClosedloopRamp(.5, 0);
		
		char switchPos = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		char scalePos = DriverStation.getInstance().getGameSpecificMessage().charAt(1);
		
		startingPosition = (int) SmartDashboard.getNumber("Starting position", 0);
		destination = (int) SmartDashboard.getNumber("destination", 0);
		cubeAmount = (int) SmartDashboard.getNumber("cube amount", 0);
		
		CommandGroup autoMode = AutoHandler.getAuto(switchPos, scalePos, cubeAmount,
				destination,startingPosition);
		autoTime.start();
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Robot Heading", RobotMap.navx.getAngle());
		lift.manipulateLift();
		Intake.manipulateWrist();
		Scheduler.getInstance().run();
		RobotMap.compressor.setClosedLoopControl(false);
	}

	@Override
	public void teleopInit() {
		RobotMap.R1.configClosedloopRamp(0, 0);
		RobotMap.L1.configClosedloopRamp(0, 0);
		Constants.inAuto = false;
		lift.safeToLift = true;
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.R1.setSensorPhase(false);
    	RobotMap.L1.setSensorPhase(true);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.compressor.setClosedLoopControl(false);
		Intake.setWristSetpoint(Constants.wristUp);
	}
	
	@Override
	public void teleopPeriodic() {
		lift.safeToLift = true;
		SmartDashboard.putBoolean("Is lift at bottom", lift.isAtBottom());
		SmartDashboard.putBoolean("Is lift at top", lift.isAtTop());
				
		if(RobotMap.driveStick.getPOV() == 90) {
			RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		} else if(RobotMap.driveStick.getPOV() == 270) {
			RobotMap.wrist.setSelectedSensorPosition(1700, 0, 0);
		} else if(RobotMap.driveStick.getPOV() == 0) {
			Intake.setWristSetpoint(Constants.wristUp);
		} else if(RobotMap.driveStick.getPOV() == 180) {
			Intake.setWristSetpoint(Constants.wristDown);
		}
		
		if(ControllerMap.visionActivated() == true) {
			Vision.cubeTrackLeft();
		} else if(ControllerMap.carefulPlace()) {
			Drive.arcadeDrive(0, .25, false);
			Intake.intake(0, .325);
		} else {
			if(lift.getLiftEncPosition() > Constants.scaleNormal) {
				Drive.arcadeDrive(ControllerMap.getTurnPower() * .3, ControllerMap.getForwardPower() * .4, false);
			} else if( ControllerMap.intakeR() > .4) {
				Drive.arcadeDrive(ControllerMap.getTurnPower() * .7, ControllerMap.getForwardPower() * .7, false);
			} else {
				Drive.arcadeDrive(ControllerMap.getTurnPower(), ControllerMap.getForwardPower(), ControllerMap.shift());
			}
			Intake.intake(ControllerMap.intakeR()*.75, ControllerMap.intakeL()*.75);
		}
		
		if(RobotMap.mechStick.getRawButton(2) == true) {
			RobotMap.compressor.setClosedLoopControl(true);
		} else {
			RobotMap.compressor.setClosedLoopControl(false);
		}
		
		if(ControllerMap.matchClimb() == true) {
			RobotMap.climber.set(Math.abs(RobotMap.mechStick.getRawAxis(0)));
		} else if(ControllerMap.practiceClimb() == true){
			RobotMap.climber.set(RobotMap.mechStick.getRawAxis(0));
		} else {
			RobotMap.climber.set(0);
		}
		
		lift.manipulateLift();
		Intake.manipulateWrist();
		SmartDashboard.putNumber("Left Drive", Drive.getLeftPosition());
		SmartDashboard.putNumber("Right Drive", Drive.getRightPosition());
		SmartDashboard.putBoolean("Is cube in", Intake.isCubeInIntake());
		SmartDashboard.putBoolean("Safe To Lift?", lift.isSafeToLift());
		SmartDashboard.putNumber("Intake Ultrasonic", Intake.getUltraSonic());
		SmartDashboard.putNumber("Lift Enc", lift.getLiftEncPosition());
		SmartDashboard.putNumber("Wrist Enc", Intake.getWristEncPosition());
		SmartDashboard.putNumber("Robot Velocity", Drive.getLeftVelocity());
		SmartDashboard.putNumber("Robot Heading", Drive.getNavxAngle());
		SmartDashboard.putNumber("Controller Pov", RobotMap.driveStick.getPOV());
		SmartDashboard.putBoolean("Cube in", Intake.isCubeInIntake());

		if(lift.isAtBottom() == true) {
			RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
		}
	}
	
	
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		Intake.setWristSetpoint(Constants.wristUp);
		Vision.ledEntry.forceSetNumber(1);
	}
	
	@Override
	public void disabledPeriodic() {
	}
}
