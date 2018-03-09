package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drive {
	
	static boolean navxReset = false;
	static double setPoint;

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
		//these need to be inverted with robot 1 as of now... hopefully matt will fix that
		RobotMap.R2.setInverted(true);
		RobotMap.L3.setInverted(true);
		
		RobotMap.L1.configOpenloopRamp(.15, 0);
		RobotMap.R1.configOpenloopRamp(.15, 0);
		
		RobotMap.shifter = new DoubleSolenoid(Constants.shifterForward,Constants.shifterReverse);
		
		RobotMap.navx = new AHRS(SPI.Port.kMXP);
		Drive.setBrakeMode();
	}
	
	public static void setShifters(boolean isShifted) {
		if(isShifted == true) {
			RobotMap.shifter.set(Value.kReverse);
		}else {
			RobotMap.shifter.set(Value.kForward);
		}
	}
	
	public static void arcadeDrive (double side, double forward, boolean shift) {
		if (shift == true) {
			if(navxReset == false) {
				RobotMap.navx.reset();
				navxReset = true;
			}
			//high gear
			setShifters(true);
			//side is forward for some reason
			RobotMap.R1.set(ControlMode.PercentOutput, side - forward);
			RobotMap.L1.set(ControlMode.PercentOutput, side + forward);
		} else {
			if (RobotMap.driveStick.getPOV() == 0) {
				setPoint = 0;
				setRobotHeading (setPoint);
			} else if (RobotMap.driveStick.getPOV() == 90) {
				setPoint = 90;
				setRobotHeading (setPoint);
			} else if (RobotMap.driveStick.getPOV() == 180) {
				setPoint = 180;
				setRobotHeading (setPoint);
			} else if (RobotMap.driveStick.getPOV() == 270) {
				setPoint = 270;
				setRobotHeading (setPoint);
			} else {
				if (lift.getLiftEncPosition() >= Constants.scaleNormal) {
					RobotMap.R1.set(ControlMode.PercentOutput, side * .5 - forward * .5);
					RobotMap.L1.set(ControlMode.PercentOutput, side * .5 + forward * .5);
				} else {
					RobotMap.R1.set(ControlMode.PercentOutput, side - forward);
					RobotMap.L1.set(ControlMode.PercentOutput, side + forward);
				}
			}
			//low gear
			setShifters(false);
			navxReset = false;
		}
		
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
		double error = (RobotMap.navx.getAngle() - heading);
		rightDrive(error *Constants.kpStraight + power);
		leftDrive(error*Constants.kpStraight - power);
	}
	
	public static void setRobotHeading(double heading) {
		double error = (RobotMap.navx.getAngle() - heading);
		rightDrive(error*Constants.kpTurn);
		leftDrive(error*Constants.kpTurn);
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
	public static void setBrakeMode() {
		RobotMap.R1.setNeutralMode(NeutralMode.Brake);
		RobotMap.R2.setNeutralMode(NeutralMode.Brake);
		RobotMap.R3.setNeutralMode(NeutralMode.Brake);
		RobotMap.L3.setNeutralMode(NeutralMode.Brake);
		RobotMap.L2.setNeutralMode(NeutralMode.Brake);
		RobotMap.L1.setNeutralMode(NeutralMode.Brake);	
	}
	public static void setCoastMode() {
		RobotMap.R1.setNeutralMode(NeutralMode.Coast);
		RobotMap.R2.setNeutralMode(NeutralMode.Coast);
		RobotMap.R3.setNeutralMode(NeutralMode.Coast);
		RobotMap.L3.setNeutralMode(NeutralMode.Coast);
		RobotMap.L2.setNeutralMode(NeutralMode.Coast);
		RobotMap.L1.setNeutralMode(NeutralMode.Coast);		
	}
}
