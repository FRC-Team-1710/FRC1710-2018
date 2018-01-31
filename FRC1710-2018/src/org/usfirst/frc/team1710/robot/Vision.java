package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
	//TODO: make this class a child of constants and add kpAim, kpDistance to constants
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
		
		double kpAim = .02;
		double kpDistance = .1;
	
		if( tvValue==0) {
				Drive.leftDrive(.3);
				Drive.rightDrive(.3);
			
		 }else {
				Drive.arcadeDrive(kpAim * txValue, -tyValue * kpDistance, false );
				
		 }
			 
		
		
		if(Math.abs(tyValue) > 12.5) {
			System.out.println("INTAKING");
			//Intake.autoIntake(-.75, -.75);
			//arms closed
		}else {
			System.out.println("NOT INTAKING");
			//Intake.autoIntake(0, 0);
			//arms opened

		}
		
	}
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
		
		double kpAim = .02;
		double kpDistance = .1;
	
		if( tvValue==0) {
				Drive.leftDrive(-.3);
				Drive.rightDrive(-.3);
			
		 }else {
				Drive.arcadeDrive(kpAim * txValue, -tyValue * kpDistance, false );
				
		 }
	
		if(Math.abs(tyValue) > 12.5) {
			System.out.println("INTAKING");
			//Intake.autoIntake(-.75, -.75);
			//arms closed
		}else {
			System.out.println("NOT INTAKING");
			//Intake.autoIntake(0, 0);
			//arms opened

		}
	}
}