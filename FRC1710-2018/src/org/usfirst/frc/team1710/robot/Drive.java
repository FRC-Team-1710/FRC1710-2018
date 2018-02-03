package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drive {
	
	public static void initializeDrive () {
		RobotMap.R1 = new TalonSRX (Constants.rightLeaderid);
		RobotMap.R2 = new VictorSPX (Constants.rightFollowerid);
		RobotMap.R3 = new VictorSPX (Constants.rightFollowerid2);
		RobotMap.L1 = new TalonSRX (Constants.leftLeaderid);
		RobotMap.L2 = new VictorSPX (Constants.leftFollowerid);
		RobotMap.L3 = new VictorSPX (Constants.leftFollowerid2);
		
		RobotMap.R2.follow (RobotMap.R1);
		RobotMap.R3.follow (RobotMap.R1);
		RobotMap.L2.follow (RobotMap.L1);
		RobotMap.L3.follow (RobotMap.L1);
		
		RobotMap.shifter = new DoubleSolenoid(0,1);
		
		RobotMap.navx = new AHRS(SPI.Port.kMXP);
	}
	
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
	
	public static void leftDrive(double power) {
		RobotMap.L1.set(ControlMode.PercentOutput, power);
	}
	
	public static void rightDrive (double power) {
		RobotMap.R1.set(ControlMode.PercentOutput, power);
	}
	
	public static void straightDriveAuto (double power) {
		double error = RobotMap.navx.getAngle();
		rightDrive(error *Constants.kpStraight + power);
		leftDrive(error*Constants.kpStraight - power);
	}
	
	public static void straightDriveTele (double power, double heading) {
		double error = (RobotMap.navx.getAngle() - (heading * 180));
		rightDrive(error *Constants.kpStraight + power);
		leftDrive(error*Constants.kpStraight - power);
	}
	
	public static double getLeftVelocity() {
		return RobotMap.L1.getSelectedSensorVelocity(0);
	}
	
	public static double getRightVelocity() {
		return RobotMap.R1.getSelectedSensorVelocity(0);
	}
	
	public static void stopDriving() {
		RobotMap.R1.set(ControlMode.PercentOutput, 0);
		RobotMap.L1.set(ControlMode.PercentOutput, 0);
	}
	
	public static double getNavxAngle() {
		return RobotMap.navx.getAngle();
	}
	
	public static double getLeftPosition() {
		return RobotMap.L1.getSelectedSensorPosition(0);
	}
	
	public static double getRightPosition() {
		return RobotMap.R1.getSelectedSensorPosition(0);
	}

}
