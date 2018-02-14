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
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectory.trajectoryTestCGroup;

public class Robot extends IterativeRobot {
	
	List<Object> thingsToPutOnDashboard = new ArrayList<Object>();
	boolean done;
	DashboardReport report;
	
	@Override
	public void robotInit() {
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
	}

	@Override
	public void autonomousInit() {
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
		lift.setSetpoint(Constants.intake);
		CommandGroup autoMode = new trajectoryTestCGroup();
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		lift.manipulateLift();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		lift.setSetpoint(Constants.intake);
		RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);
		Vision.ledEntry.forceSetNumber(0);
		Vision.ledEntry.forceSetNumber(1);
	}
	
	@Override
	public void teleopPeriodic() {
		if(ControllerMap.visionActivated() == true) {
			Vision.cubeTrackLeft();
		} else {
			Drive.arcadeDrive(ControllerMap.getTurnPower(), ControllerMap.getForwardPower(), ControllerMap.shift());
			Intake.intake(ControllerMap.intakeR(), ControllerMap.intakeL());
		}
		lift.manipulateLift();
		
		//Intake.manipulateWrist();
		SmartDashboard.putNumber("right", Drive.getRightPosition());
		SmartDashboard.putNumber("left", Drive.getLeftPosition());

		SmartDashboard.putNumber("Ultra Sonic Left", Intake.getUltraSonicL());
	}
	
	
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		lift.setSetpoint(Constants.intake);
		Vision.ledEntry.forceSetNumber(1);
	}
	
	@Override
	public void disabledPeriodic() {
	}
}
