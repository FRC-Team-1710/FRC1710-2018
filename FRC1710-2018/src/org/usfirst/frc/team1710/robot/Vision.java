package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
	
	static NetworkTableInstance table = NetworkTableInstance.getDefault();
	static NetworkTable tableTwo = table.getTable("limelight");
	static NetworkTableEntry ledEntry = tableTwo.getEntry("ledMode");
	double ledValue = ledEntry.getDouble(0);

	static NetworkTableEntry txEntry = tableTwo.getEntry("tx");
	static NetworkTableEntry tyEntry = tableTwo.getEntry("ty");
	static NetworkTableEntry tvEntry = tableTwo.getEntry("tv");
	
	
	public static void initializeVision() {
		table = NetworkTableInstance.getDefault();
		tableTwo = table.getTable("limelight");
		ledEntry = tableTwo.getEntry("ledMode");
		ledEntry.forceSetNumber(0);
		ledEntry.forceSetNumber(1);
	}
	
	public static double getTvValue() {
		double tvValue = tvValue = tvEntry.getDouble(0);
		return tvValue;
	}
	
	public static double getTyValue() {
		double tyValue = tyEntry.getDouble(0);
		return tyValue;
	}
	
	public static double getTxValue() {
		double txValue = txEntry.getDouble(0);
		return txValue;
	}
	
	public static boolean areCubesAvailable() {
		if(getTvValue() >= 1) {
			return true;
		}else {
			return false;
		}
			
	}
	
	public static boolean areCubesIntakable() {
		return Math.abs(getTyValue()) < Constants.tyIntake && getTvValue() >=1;
	}
	
	public static void cubeTrackRight() {
		//if bot cannot find box turn right
			 
		if(areCubesIntakable() == true) {
			//if bot cannot find box turn left
			Intake.intake(-Constants.cubeIntakeSpeed, Constants.cubeIntakeSpeed);
			Drive.stopDriving();
			//arms closed
		} else {
			if(areCubesAvailable() == false) {
				Drive.leftDrive(-Constants.seekingSpeed);
				Drive.rightDrive(-Constants.seekingSpeed);
			//else go forward to track box
			} else {
				Drive.arcadeDrive(-Constants.kpAim * getTxValue(), -getTyValue() * Constants.kpDistance , false );
			}
			Intake.intake(0, 0);
		}
	}
	public static void cubeTrackLeft() {
	
		if(areCubesIntakable() == true) {
			//if bot cannot find box turn left
			Intake.intake(-Constants.cubeIntakeSpeed, Constants.cubeIntakeSpeed);
			Drive.stopDriving();
			//arms closed
		} else {
			if(areCubesAvailable() == false) {
				Drive.leftDrive(Constants.seekingSpeed);
				Drive.rightDrive(Constants.seekingSpeed);
			//else go forward to track box
			} else {
				Drive.arcadeDrive(-Constants.kpAim * getTxValue(), -getTyValue() * Constants.kpDistance , false );
			}
			Intake.intake(0, 0);
		}
	}
	
}