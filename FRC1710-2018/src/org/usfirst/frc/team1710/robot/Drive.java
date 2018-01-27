package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drive {
	public static void arcadeDrive (double forward, double side) {
		RobotMap.R1.set(ControlMode.PercentOutput, forward - side);
		RobotMap.L1.set(ControlMode.PercentOutput, forward + side);
	}
}
