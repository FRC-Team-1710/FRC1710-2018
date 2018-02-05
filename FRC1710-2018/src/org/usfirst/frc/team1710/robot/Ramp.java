package org.usfirst.frc.team1710.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ramp {
	public static void rampDeploy(){
		
		RobotMap.rampDeploy.set(DoubleSolenoid.Value.kForward);
		RobotMap.rampExtendo.set(DoubleSolenoid.Value.kForward);
		
	}
	public static void rampLift() {
		
		RobotMap.rampLifto.set(DoubleSolenoid.Value.kForward);
		
	}
}
