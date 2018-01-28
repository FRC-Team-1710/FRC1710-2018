package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drive {
	public static void arcadeDrive (double forward, double side, boolean shift) {
		if (shift == true) {
			//high gear
			RobotMap.shifter.set(Value.kReverse);
		} else {
			//low gear
			RobotMap.shifter.set(Value.kForward);
		}
		
		RobotMap.R1.set(ControlMode.PercentOutput, forward - side);
		RobotMap.L1.set(ControlMode.PercentOutput, forward + side);
	}
}
