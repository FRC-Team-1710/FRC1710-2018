package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		RobotMap.R1 = new TalonSRX (1);
		RobotMap.R2 = new VictorSPX (2);
		RobotMap.R3 = new VictorSPX (3);
		RobotMap.L1 = new TalonSRX (4);
		RobotMap.L2 = new VictorSPX (5);
		RobotMap.L3 = new VictorSPX (6);
		RobotMap.intakeR = new TalonSRX (7);
		RobotMap.intakeL = new TalonSRX (8);
		RobotMap.wrist = new TalonSRX (9);
		RobotMap.lift1 = new TalonSRX (10);
		RobotMap.lift2 = new TalonSRX (11);
		//makes each spx follow their respective srx
		RobotMap.R2.follow (RobotMap.R1);
		RobotMap.R3.follow (RobotMap.R1);
		RobotMap.L2.follow (RobotMap.L1);
		RobotMap.L3.follow (RobotMap.L1);
	}

	@Override
	public void autonomousInit() {

	}


	@Override
	public void autonomousPeriodic() {

	}


	@Override
	public void teleopPeriodic() {
		Drive.arcadeDrive(RobotMap.driveStick.getRawAxis(1), RobotMap.driveStick.getRawAxis(4));
	}

	@Override
	public void testPeriodic() {
	}
}
