package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		RobotMap.R1 = new TalonSRX (8);
		RobotMap.R2 = new VictorSPX (1);
		RobotMap.R3 = new VictorSPX (2);
		RobotMap.L1 = new TalonSRX (9);
		RobotMap.L2 = new VictorSPX (3);
		RobotMap.L3 = new VictorSPX (4);
		RobotMap.intakeR = new TalonSRX (7);
		RobotMap.intakeL = new TalonSRX (8);
		RobotMap.wrist = new TalonSRX (6);
		RobotMap.lift1 = new TalonSRX (5);
		RobotMap.lift2 = new TalonSRX (10);
		//makes each spx follow their respective srx
		RobotMap.R2.follow (RobotMap.R1);
		RobotMap.R3.follow (RobotMap.R1);
		RobotMap.L2.follow (RobotMap.L1);
		RobotMap.L3.follow (RobotMap.L1);
		RobotMap.lift1.follow (RobotMap.lift2);
		RobotMap.liftReset = new DigitalInput(0);
		RobotMap.driveStick = new Joystick(0);
		RobotMap.shifter = new DoubleSolenoid(0,1);
	}

	@Override
	public void autonomousInit() {
		
	}


	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		//TODO: come up with better, iterative way of handling input
		if(ControllerMap.visionActivate == true) {
			Vision.cubeTrackLeft();
		} else {
			//shifting is on left bumper
			Drive.arcadeDrive(ControllerMap.turnPower, ControllerMap.forwardPower, ControllerMap.shift);
		}
	}

	@Override
	public void testPeriodic() {
	}
}
