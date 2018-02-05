package org.usfirst.frc.team1710.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		SubsystemManager.masterinitialization();
	}

	@Override
	public void autonomousInit() {
		
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
		Intake.manipulateWrist();
	}
}
