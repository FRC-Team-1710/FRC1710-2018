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
	
	static double txValue = txEntry.getDouble(0);
	static double tyValue = tyEntry.getDouble(0);
	static double tvValue = tvEntry.getDouble(0);
	
	public static void initializeVision() {
		table = NetworkTableInstance.getDefault();
		tableTwo = table.getTable("limelight");
		ledEntry = tableTwo.getEntry("ledMode");
		ledEntry.forceSetNumber(1);
	}
	
	public static void cubeTrackRight() {
		//if bot cannot find box turn right
		if(tvValue==0) {
			Drive.leftDrive(.3);
			Drive.rightDrive(.3);
		// goes forward to track 
		}else {
			Drive.arcadeDrive(-Constants.kpAim * txValue, -tyValue * Constants.kpDistance, false );
		}
			 
		if(Math.abs(tyValue) > 12.5) {
			Intake.intake(0, 0);
			//arms closed
		} else {
			Intake.intake(-1, 1);
			//arms opened
		}
	}
	public static void cubeTrackLeft() {
		tvValue = tvEntry.getDouble(0);
		tyValue = tyEntry.getDouble(0);
		txValue = txEntry.getDouble(0);
		//if bot cannot find box turn left
		if(tvValue==0) {
			Drive.leftDrive(-.3);
			Drive.rightDrive(-.3);
			System.out.println("I HAVE NO EYES");
		//else go forward to track box
		} else {
			System.out.println("I AM SEE");
			Drive.arcadeDrive(-Constants.kpAim * txValue, -tyValue * Constants.kpDistance , false );
		}
	
		if(Math.abs(tyValue) > 6) {
			Intake.intake(0, 0);
			//arms closed
		} else {
			Intake.intake(-1, 1);
		}
	}
	
}