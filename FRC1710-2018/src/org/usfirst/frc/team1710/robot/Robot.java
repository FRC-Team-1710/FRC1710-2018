package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectory.trajectoryTestCGroup;

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		SubsystemManager.masterinitialization();
	}

	@Override
	public void autonomousInit() {
		CommandGroup autoMode = new trajectoryTestCGroup();
		autoMode.start();
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {
		if(ControllerMap.visionActivated == true) {
			Vision.cubeTrackLeft();
		} else {
			Drive.arcadeDrive(ControllerMap.turnPower, ControllerMap.forwardPower, ControllerMap.shift);
		}
		Intake.intake(ControllerMap.intakeR, ControllerMap.intakeL);
		Intake.manipulateWrist();
		lift.manipulateLift();
	}
	@Override
	public void testPeriodic() {
	}
	@Override
	public void disabledInit() {
		SubsystemManager.masterReset();
		Intake.setWristPosition(Constants.wristUp);
	}
	public void disabledPeriodic() {
		//reset code for the wrist and intake 
		Intake.manipulateWrist();
	}
}
