package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import utility.RobotMath;


public class lift {

	public static double setPoint;	
	
	public static void initializeLift() {
		RobotMap.lift1 = new TalonSRX (Constants.liftRightTalon);
		RobotMap.lift2 = new TalonSRX (Constants.liftLeftTalon);
		//commented out for testing which motor
		//RobotMap.lift1.configOpenloopRamp(arg0, arg1)
		RobotMap.lift2.follow (RobotMap.lift1);
		RobotMap.lift2.setInverted(true);
		RobotMap.liftReset = new DigitalInput(0);
	}

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
	
	public static double getLiftOutput() {
		if(getMovementDirection() == "moving up") {
			return (-1 * ((getLiftError()) *  Constants.kPLiftUp));
		} else {
			return (-1 * ((getLiftError()) *  Constants.kPLiftDown));
		}
	}
	
	public static void manipulateLift() {
		
		double outputUp = (-1 * ((getLiftError()) *  Constants.kPLiftUp));
		double outputDown = (-1 * ((getLiftError()) *  Constants.kPLiftDown));
		
		//if the stick is being moved...
		if (ControllerMap.liftPower() > 0.2 || ControllerMap.liftPower() < -0.2){
			//if the stick is being moved down and the lift isn't near the bottom
			if(ControllerMap.liftPower() > 0 && getLiftEncPosition() > 500) {
				RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower() * 0.2);
			//if the stick is moving up and the lift isn't near the top
			} else if(ControllerMap.liftPower() < 0 && getLiftEncPosition() < 9000) {
				RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower() * 0.2);	
			//uh oh
			} else {
				stopLift();
			}
			setPoint = getLiftEncPosition();
		} else {	
			if(getMovementDirection() == "moving up") {
				if(getLiftEncPosition() > 9000) {
					stopLift();
				} else {
					RobotMap.lift1.set(ControlMode.PercentOutput, outputUp);
				}
			} else {
				if(getLiftEncPosition() < 100) {
					stopLift();
				} else {
					RobotMap.lift1.set(ControlMode.PercentOutput, outputDown);
				}
			}
		}	
		if(isAtBottom() == true) {
			RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
		}
	}
	
	public static String getMovementDirection() {
		if (setPoint > getLiftEncPosition()) {
			return "moving up";
		} else {
			return "moving down";
		}
	}
	
	public static double getLiftError() {
		return getLiftSetpoint() - getLiftEncPosition();
	}
	public static boolean isAtBottom() {
		return !RobotMap.liftReset.get();
	}
	
	public static double getLiftEncPosition() {
		return Math.abs(RobotMap.lift1.getSelectedSensorPosition(0));
	}
	
	public static String getLiftPosition() {
		if(setPoint == Constants.intake) {
			return Constants.intakeLevelName;
		} else if(RobotMath.isInRange(setPoint, Constants.intake, 175)) {
			return Constants.switchLevelName;
		} else if(RobotMath.isInRange(setPoint, Constants.scaleLow, 175)) {
			return Constants.lowLevelName;
		} else if(RobotMath.isInRange(setPoint, Constants.scaleNormal, 175)){
			return Constants.normalLevelName;
		} else if(RobotMath.isInRange(setPoint, Constants.scaleHigh, 175)) {
			return Constants.highLevelName;
		} else {
			return Constants.liftingLevelName;
		}

	}
	
	public static void stopLift() {
		RobotMap.lift1.set(ControlMode.PercentOutput, 0);	
	}
	
	public static void setSetpoint(double newSetPoint) {
		setPoint = newSetPoint;		
	}
}