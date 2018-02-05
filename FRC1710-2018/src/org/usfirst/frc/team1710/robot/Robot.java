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

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		SubsystemManager.masterinitialization();
		RobotMap.driveStick = new Joystick(0);
		RobotMap.mechStick = new Joystick(1);
	}

	@Override
	public void autonomousInit() {
		//CommandGroup autoMode = new trajectoryTestCGroup();
		//autoMode.start();
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
		//Intake.intake(ControllerMap.intakeR, ControllerMap.intakeL);
		//Intake.manipulateWrist();
		//we dont wanna do this any more, make getter methods in ControllerMap that handle inputs
		ControllerMap.updateControllers();
		lift.manipulateLift();
	}
	
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		SubsystemManager.masterReset();
		Intake.setWristPosition(Constants.wristUp);
		lift.liftSetPoint(Constants.wristUp);
	}
	
	@Override
	public void disabledPeriodic() {
		//reset code for the wrist and intake 
		Intake.manipulateWrist();
		lift.manipulateLift();
	}
}
