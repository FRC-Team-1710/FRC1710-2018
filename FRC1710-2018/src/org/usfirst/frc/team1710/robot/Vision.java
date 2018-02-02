package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
	//getting information from limelight and turning right to track cube 
	public static void cubeTrackRight() {
		NetworkTableInstance table = NetworkTableInstance.getDefault();
		NetworkTable tableTwo = table.getTable("limelight");
		NetworkTableEntry ledEntry = tableTwo.getEntry("ledMode");
		double ledValue = ledEntry.getDouble(0);
		
		NetworkTableEntry txEntry = tableTwo.getEntry("tx");
		NetworkTableEntry tyEntry = tableTwo.getEntry("ty");
		NetworkTableEntry tvEntry = tableTwo.getEntry("tv");
		double txValue = txEntry.getDouble(0);
		double tyValue = tyEntry.getDouble(0);
		double tvValue = tvEntry.getDouble(0);
	
		//if bot cannot find box turn
		if(tvValue==0) {
			Drive.leftDrive(.3);
			Drive.rightDrive(.3);
		// goes forward to track 
		}else {
			Drive.arcadeDrive(Constants.kpAim * txValue, -tyValue * Constants.kpDistance, false );
		}
			 
		if(Math.abs(tyValue) > 12.5) {
			System.out.println("INTAKING");
			Intake.intake(0, 0);
			//arms closed
		} else {
			System.out.println("NOT INTAKING");
			Intake.intake(-1, 1);
			//arms opened
		}
	}
	//getting information from limelight and turning left to track cube 
	public static void cubeTrackLeft() {
		NetworkTableInstance table = NetworkTableInstance.getDefault();
		NetworkTable tableTwo = table.getTable("limelight");
		NetworkTableEntry ledEntry = tableTwo.getEntry("ledMode");
		double ledValue = ledEntry.getDouble(0);

		NetworkTableEntry txEntry = tableTwo.getEntry("tx");
		NetworkTableEntry tyEntry = tableTwo.getEntry("ty");
		NetworkTableEntry tvEntry = tableTwo.getEntry("tv");
		double txValue = txEntry.getDouble(0);
		double tyValue = tyEntry.getDouble(0);
		double tvValue = tvEntry.getDouble(0);
		
		//if bot cannot find box turn
		if(tvValue==0) {
			Drive.leftDrive(-.3);
			Drive.rightDrive(-.3);
		//else go forward to track box
		} else {
			Drive.arcadeDrive(Constants.kpAim * txValue, -tyValue * Constants.kpDistance, false );
		}
	
		if(Math.abs(tyValue) > 12.5) {
			System.out.println("INTAKING");
			//Intake.autoIntake(-.75, -.75);
			//arms closed
		} else {
			System.out.println("NOT INTAKING");
			//Intake.autoIntake(0, 0);
			//arms opened

		}
	}
}