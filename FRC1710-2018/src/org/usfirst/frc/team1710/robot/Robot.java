package org.usfirst.frc.team1710.robot;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectory.trajectoryTestCGroup;

public class Robot extends IterativeRobot {

	static List<Object> thingsToPutOnDashboard = new ArrayList<Object>();
	
	@Override
	public void robotInit() {
		thingsToPutOnDashboard.add(RobotMap.R1);
		thingsToPutOnDashboard.add(RobotMap.L1);
		thingsToPutOnDashboard.add(RobotMap.lift1);
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
	}

	@Override
	public void autonomousInit() {
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		CommandGroup autoMode = new trajectoryTestCGroup();
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("lift position", lift.getLiftPosition());
		SmartDashboard.putNumber("lift setpoint", lift.getLiftSetpoint());
		SmartDashboard.putNumber("lift enc", lift.getLiftEncPosition());
	}

	@Override
	public void teleopInit() {
		lift.setSetpoint(Constants.intake);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
	}
	
	@Override
	public void teleopPeriodic() {
		if(ControllerMap.visionActivated() == true) {
			Vision.cubeTrackLeft();
		} else {
			Drive.arcadeDrive(ControllerMap.getTurnPower(), ControllerMap.getForwardPower(), ControllerMap.shift());
		}
		//Intake.intake(ControllerMap.intakeR(), ControllerMap.intakeL());
		//Intake.manipulateWrist();
		//DashboardInput.updateDashboard(new DashboardReport(thingsToPutOnDashboard));
		lift.manipulateLift();
		SmartDashboard.putNumber("Lift enc", lift.getLiftEncPosition());
		SmartDashboard.putString("Lift Position", lift.getLiftPosition());
		SmartDashboard.putNumber("tx", Vision.txValue);
		SmartDashboard.putNumber("Tv", Vision.tvValue);
		SmartDashboard.putNumber("ticks", RobotMap.R1.getSelectedSensorPosition(0));
	}
	
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		lift.setSetpoint(Constants.intake);
		Vision.ledEntry.forceSetNumber(1);
		//Intake.setWristPosition(Constants.wristUp);
	}
	
	@Override
	public void disabledPeriodic() {
		//reset code for the wrist and intake 
		//Intake.manipulateWrist();
		//lift.manipulateLift();
	}
}
