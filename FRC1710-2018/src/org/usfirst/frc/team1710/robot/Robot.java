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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		//setting talonSRXs
		NetworkTableInstance table = NetworkTableInstance.getDefault();
		NetworkTable tableTwo = table.getTable("limelight");
		NetworkTableEntry ledEntry = tableTwo.getEntry("ledMode");
		ledEntry.forceSetNumber(1);
		
		RobotMap.R1 = new TalonSRX (Constants.rightLeaderid);
		RobotMap.R2 = new VictorSPX (Constants.rightFollowerid);
		RobotMap.R3 = new VictorSPX (Constants.rightFollowerid2);
		RobotMap.L1 = new TalonSRX (Constants.leftLeaderid);
		RobotMap.L2 = new VictorSPX (Constants.leftFollowerid);
		RobotMap.L3 = new VictorSPX (Constants.leftFollowerid2);
		
		RobotMap.intakeR = new TalonSRX (Constants.IntakeRtalon);
		RobotMap.intakeL = new TalonSRX (Constants.IntakeLtalon);
		RobotMap.wrist = new TalonSRX (Constants.WrisTalon);
		RobotMap.lift1 = new TalonSRX (Constants.lift1Talon);
		RobotMap.lift2 = new TalonSRX (Constants.lift2Talon);
		
		
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
		if(ControllerMap.visionActivated == true) {
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
