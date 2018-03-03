package org.usfirst.frc.team1710.robot;

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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectory.trajectoryTestCGroup;
import utility.RobotMath;


public class Robot extends IterativeRobot {
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	List<Object> thingsToPutOnDashboard = new ArrayList<Object>();
	boolean done;
	double pressure;
	DashboardReport report;
	
	int wristTimeout;
		
	public static SendableChooser startPosition, destination, cubeAmount;
	
	@Override
	public void robotInit() {
		RobotMap.pressureSensor = new AnalogInput(0);
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
		RobotMap.compressor = new Compressor(0);
		
		DashboardInput.setUpDashboard();
		startPosition = new SendableChooser();
		destination= new SendableChooser();
		cubeAmount = new SendableChooser();
		
		startPosition.addDefault("Middle", 1);
		startPosition.addObject("Left", 2);
		startPosition.addObject("Right",3);
		startPosition.setName("start position");
		destination.addDefault("switch", 1);
		destination.addObject("Scale", 2);
		destination.addObject("Both", 3);
		cubeAmount.addDefault("0", 0);
		cubeAmount.addObject("1", 1);
		cubeAmount.addObject("2", 2);
		cubeAmount.addObject("3", 3);
		SmartDashboard.putData("Start Position", startPosition);
		SmartDashboard.putData("destination", destination);
		SmartDashboard.putData("cubeAmount", cubeAmount);
	}

	@Override 
	public void autonomousInit() {
		RobotMap.navx.reset();
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.R1.setSelectedSensorPosition(0, 0, 0);
    	RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		lift.setSetpoint(Constants.intake);
		Intake.setWristPosition(Constants.wristDown);
		CommandGroup autoMode = (CommandGroup) new RightStartLeftSwitch();
		
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		lift.manipulateLift();
		Intake.manipulateWrist();
		SmartDashboard.putNumber("lift enc", lift.getLiftEncPosition());
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		Scheduler.getInstance().run();
		RobotMap.compressor.setClosedLoopControl(false);
	}

	@Override
	public void teleopInit() {
		lift.setSetpoint(Constants.intake);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		Intake.setWristPosition(Constants.wristDown);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.compressor.setClosedLoopControl(false);
		wristTimeout = 0;
	}
	
	@Override
	public void teleopPeriodic() {
		if(ControllerMap.visionActivated() == true) {
			Vision.cubeTrackLeft();
		} else {
			Drive.arcadeDrive(ControllerMap.getTurnPower(), ControllerMap.getForwardPower(), ControllerMap.shift());
			Intake.intake(ControllerMap.intakeR()*2, ControllerMap.intakeL()*2);
		}
		
		if(RobotMap.mechStick.getRawButton(2) == true) {
			RobotMap.compressor.setClosedLoopControl(true);
		} else {
			RobotMap.compressor.setClosedLoopControl(false);
		}
		
		lift.manipulateLift();
		Intake.manipulateWrist();
		SmartDashboard.putNumber("Left Drive", Drive.getLeftPosition());
		SmartDashboard.putNumber("Right Drive", Drive.getRightPosition());
		SmartDashboard.putNumber("Wrist enc", Intake.getWristEncPosition());
	}
	
	
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		lift.setSetpoint(Constants.intake);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
	}
	
	@Override
	public void disabledPeriodic() {
	}
}
