package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Penn
 * creates variables for a table from limelight
 * creates entries for tx value, ty value and tv value
 * 
 * The whole vision class takes the values from limelight and makes the robot spin until it sees the cube, then it drives forward to intake the cube all at the push of a button
 */
public class Vision {
	
	static NetworkTableInstance table = NetworkTableInstance.getDefault();
	static NetworkTable tableTwo = table.getTable("limelight");
	static NetworkTableEntry ledEntry = tableTwo.getEntry("ledMode");
	double ledValue = ledEntry.getDouble(0);

	static NetworkTableEntry txEntry = tableTwo.getEntry("tx");
	static NetworkTableEntry tyEntry = tableTwo.getEntry("ty");
	static NetworkTableEntry tvEntry = tableTwo.getEntry("tv");
	
	/**
	 * Initializing tje table so it can be used in the rest of the cube
	 * creates a tabletwo than gets a table the limelight web interface gives
	 * grabs the LED's from the limelight table and gets the on and off values  0 = on, 1 = off
	 */
	public static void initializeVision() {
		table = NetworkTableInstance.getDefault();
		tableTwo = table.getTable("limelight");
		ledEntry = tableTwo.getEntry("ledMode");
		ledEntry.forceSetNumber(0);
		ledEntry.forceSetNumber(1);
	}
	/**
	 * 
	 * the tv value is the value we use to check if the cube is intakeable and if its greater than 1 it is
	 * @return the tv value and the tv is set to the double it gets from the limelight web interface
	 */
	public static double getTvValue() {
		double tvValue = tvValue = tvEntry.getDouble(0);
		return tvValue;
	}
	/**
	 * the ty value is the value we use to check if the cube is intakable if its less than a given constant
	 * @return the ty value and the ty is set to the double it gets from the limelight web interface
	 */
	public static double getTyValue() {
		double tyValue = tyEntry.getDouble(0);
		return tyValue;
	}
	/**
	 * the tx value is the value of the width of the screen and we use it to check if the cube is at the center of the screen
	 * @return the Tx value which is set to the double it gets from the limelight web interface
	 */
	public static double getTxValue() {
		double txValue = txEntry.getDouble(0);
		return txValue;
	}
	/**
	 * Checks the ty value which is the amount of targets available
	 * @return true if there are 1 or more targets that it can see
	 */
	public static boolean areCubesAvailable() {
		if(getTvValue() >= 1) {
			return true;
		}else {
			return false;
		}
			
	}
	/**
	 * checks to see if the cube is close enough to the intake to be intaked
	 * @return the Ty value if its less than the ty intake constant but greater than 1
	 */
	public static boolean areCubesIntakable() {
		return Math.abs(getTyValue()) < Constants.tyIntake && getTvValue() >=1;
	}
	/**
	 * Spins the robot to the right until it sees a cube, then it will go towards the cube and intakes it automatically
	 */
	public static void cubeTrackRight() {
		//if bot cannot find box turn right
		SmartDashboard.putBoolean("is cube intakeable", areCubesIntakable());

		if(areCubesIntakable() == true) {
			//if bot cannot find box turn left
			Intake.intake(0, Constants.cubeIntakeSpeed);
			Drive.arcadeDrive(0, -.3, false);
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
	
	/**
	 * Spins the robot to the left until it sees a cube, then it will go towards the cube and intakes it automatically
	 */
	public static void cubeTrackLeft() {
		SmartDashboard.putBoolean("is cube intakeable", areCubesIntakable());
		if(areCubesIntakable() == true) {
			//if bot cannot find box turn left
			Intake.intake(0, Constants.cubeIntakeSpeed);
			Drive.arcadeDrive(0,-.3,false);
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