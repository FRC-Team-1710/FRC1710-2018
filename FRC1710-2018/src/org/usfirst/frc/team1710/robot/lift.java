package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.RobotMath;


public class lift {

	public static double setPoint, outputUp, outputDown;	
	/**
	 * give talons their id numbers
	 */
	public static void initializeLift() {
		RobotMap.lift1 = new TalonSRX (Constants.liftRightTalon);
		RobotMap.lift2 = new TalonSRX (Constants.liftLeftTalon);
		//commented out for testing which motor
		RobotMap.lift2.follow (RobotMap.lift1);
		RobotMap.lift2.setInverted(true);
		RobotMap.lift1.setInverted(false);
		RobotMap.liftBottom = new DigitalInput(1);
		RobotMap.liftTop = new DigitalInput(0);
		
		RobotMap.lift1.setNeutralMode(NeutralMode.Brake);
		RobotMap.lift2.setNeutralMode(NeutralMode.Brake);
		
		RobotMap.lift1.configContinuousCurrentLimit(10, 0);
		RobotMap.lift1.configPeakCurrentLimit(20, 0);
		RobotMap.lift1.configPeakCurrentDuration(100, 0);
		RobotMap.lift1.enableCurrentLimit(true);
		
		RobotMap.lift2.configContinuousCurrentLimit(10, 0);
		RobotMap.lift2.configPeakCurrentLimit(20, 0);
		RobotMap.lift2.configPeakCurrentDuration(100, 0);
		RobotMap.lift2.enableCurrentLimit(true);
	}
/**
 * tells lift what set-points to move to based on the mech-driver input
 * @return lifts location
 */
	public static double getLiftSetpoint () {
		if(ControllerMap.bottomLift() == true) {
			setPoint = Constants.intake;
		}else if(ControllerMap.liftAtSwitchHeight() == true) {
			setPoint = Constants.switchPosition;
		}else if(ControllerMap.liftAtScaleLow() == true) {
			setPoint = Constants.scaleLow;
		}else if(ControllerMap.liftAtScaleNormal() == true) {
			setPoint = Constants.scaleNormal;
		}else if(ControllerMap.liftAtScaleHigh() == true) {
			setPoint = Constants.scaleHigh;
		}
		return setPoint;
	}
	/**
	 * tells if we are moving up or down to account for gravity
	 * @return output for lift motor based on direction
	 */
	public static double getLiftOutput() {
		if(getMovementDirection() == "moving up") {
			return (-1 * ((getLiftError()) *  Constants.kPLiftUp));
		} else {
			return (-1 * ((getLiftError()) *  Constants.kPLiftDown));
		}
	}
	/**
	 * handles Manual control for the mech-driver 
	 * checks the set-point and calculates how to move to the set-point 
	 * Prevents the lift from slamming into the top or bottom
	 */
	public static void manipulateLift() {
		
		outputUp = (-1 * (getLiftError() *  Constants.kPLiftUp));
		//outputDown = (-1 * ((getLiftError()) *  Constants.kPLiftDown));
				
		//if the stick is being moved...
		if ((ControllerMap.liftPower() > 0.2 || ControllerMap.liftPower() < -0.2) && ControllerMap.getMechTrigger() == false){
			//if the stick is being moved down and the lift isn't near the bottom
			if(ControllerMap.liftPower() >= 0 && isAtBottom() == false) {
				RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower() * .35);
			//if the stick is moving up and the lift isn't near the top
			} else if(ControllerMap.liftPower() <= 0 && isAtTop() == false) {
				RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower() * .8);	
				System.out.println("upsies");
			}
			setPoint = getLiftEncPosition();
		} else {	
			if(Math.abs(RobotMap.navx.getPitch()) < 15) {
				if(outputUp > .4) {
					RobotMap.lift1.set(ControlMode.PercentOutput, .4);
					System.out.println("Overriding output");
				} else if(outputUp < -.7) {
					RobotMap.lift1.set(ControlMode.PercentOutput, -.7);
					System.out.println("Overriding output");
				} else {
					RobotMap.lift1.set(ControlMode.PercentOutput, outputUp);
				}	
			} else {
				setPoint = Constants.intake;
			}
		}	
	}
	/**
	 * find the direction to travel
	 * @return direction
	 */
	public static String getMovementDirection() {
		if (setPoint > getLiftEncPosition()) {
			return "moving up";
		} else {
			return "moving down";
		}
	}
	/**
	 * tells lift how far it needs to travel
	 * @return destination
	 */
	public static double getLiftError() {
		return getLiftSetpoint() - getLiftEncPosition();
	}
	/**
	 * tells if lift is at the bottom using hall effect sensors.
	 * @return value of the hall effect sensors.
	 */
	public static boolean isAtBottom() {
		return !RobotMap.liftBottom.get();
	}
	/**
	 * tell if lifts at the top using hall effect sensors.
	 * @return value of the hall effect sensors.
	 */
	public static boolean isAtTop() {
		return !RobotMap.liftTop.get();
	}
	/**
	 * tells what position lift is at
	 * @return value of the lift encoder
	 */
	public static double getLiftEncPosition() {
		return Math.abs(RobotMap.lift1.getSelectedSensorPosition(0));
	}
	
	/**
	 * tells what position lift is at
	 * @return the name of the position - can be used on the dash board
	 */
	public static String getLiftPosition() {
		if(RobotMath.isInRange(getLiftEncPosition(), Constants.intake, 175)) {
			return Constants.intakeLevelName;
		} else if(RobotMath.isInRange(getLiftEncPosition(), Constants.switchPosition, 175)) {
			return Constants.switchLevelName;
		} else if(RobotMath.isInRange(getLiftEncPosition(), Constants.scaleLow, 175)) {
			return Constants.lowLevelName;
		} else if(RobotMath.isInRange(getLiftEncPosition(), Constants.scaleNormal, 175)){
			return Constants.normalLevelName;
		} else if(RobotMath.isInRange(getLiftEncPosition(), Constants.scaleHigh, 175)) {
			return Constants.highLevelName;
		} else {
			return Constants.liftingLevelName;
		}

	}
	/**
	 * tells lift to stop
	 */
	public static void stopLift() {
		RobotMap.lift1.set(ControlMode.PercentOutput, 0);	
		RobotMap.lift2.set(ControlMode.PercentOutput, 0);	
	}
	/**
	 * manually changes the set-point
	 * @param newSetPoint constant value that is the encode position the lift should move to
	 */
	public static void setSetpoint(double newSetPoint) {
		setPoint = newSetPoint;		
	}
}