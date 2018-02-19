package org.usfirst.frc.team1710.robot;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import commandGroups.BuildPath;
import commandGroups.LeftStartLeftScale;
import commandGroups.LeftStartLeftSwitch;
import commandGroups.MiddleToLeftSwitch;
import commandGroups.MiddleToRightSwitch;
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
	
	static NetworkTableInstance table = NetworkTableInstance.getDefault();
	static NetworkTable autoChoices = table.getTable("SmartDashboard");
	static NetworkTableEntry destinationOne = autoChoices.getEntry("RightScale");
	static NetworkTableEntry destinationTwo = autoChoices.getEntry("LeftScale");
	static NetworkTableEntry destinationThree = autoChoices.getEntry("MiddleLeft");
	static NetworkTableEntry positionOne = autoChoices.getEntry("MiddleRight");
	static NetworkTableEntry positionTwo = autoChoices.getEntry("starting_position_Middle");
	static NetworkTableEntry positionThree = autoChoices.getEntry("starting_position_Right");
		
	@Override
	public void robotInit() {
		RobotMap.pressureSensor = new AnalogInput(0);
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
		RobotMap.compressor = new Compressor(0);
		
		DashboardInput.setUpDashboard();
	}

	@Override 
	public void autonomousInit() {
		RobotMap.L1.setSelectedSensorPosition(0, 0, 0);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
		lift.setSetpoint(Constants.intake);
		CommandGroup autoMode = new RightStartRightScale();
		
		if(destinationOne.getBoolean(false) == true) {
			autoMode = new RightStartRightScale();
		} else if (destinationTwo.getBoolean(false) == true){
			autoMode = new LeftStartLeftScale();
		} else if(destinationThree.getBoolean(false) == true) {
			autoMode = new MiddleToLeftSwitch();
		} else if (positionOne.getBoolean(false) == true) {
			autoMode = new MiddleToRightSwitch();
		}
		
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		lift.manipulateLift();
		//Intake.manipulateWrist();
		//Intake.setWristPosition(Constants.wristDown);
		SmartDashboard.putNumber("lift enc", lift.getLiftEncPosition());
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		lift.setSetpoint(Constants.intake);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
		RobotMap.wrist.setSelectedSensorPosition(0, 0, 0);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		RobotMap.compressor.setClosedLoopControl(false);
	}
	
	@Override
	public void teleopPeriodic() {
		if(ControllerMap.visionActivated() == true) {
			Vision.cubeTrackLeft();
		} else {
			Drive.arcadeDrive(ControllerMap.getTurnPower(), ControllerMap.getForwardPower(), ControllerMap.shift());
			Intake.intake(ControllerMap.intakeR()*2, ControllerMap.intakeL()*2);
		}
		lift.manipulateLift();
		Intake.manipulateWrist();
		SmartDashboard.putNumber("lift enc", lift.getLiftEncPosition());
		SmartDashboard.putNumber("wrist enc", Intake.getWristEncPosition());
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
