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
		NetworkTableInstance table = NetworkTableInstance.getDefault();
		NetworkTable tableTwo = table.getTable("limelight");
		NetworkTableEntry ledEntry = tableTwo.getEntry("ledMode");
		ledEntry.forceSetNumber(1);
		SubsystemManager.masterinitilization();
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
