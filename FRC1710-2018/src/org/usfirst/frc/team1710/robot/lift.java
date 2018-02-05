package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;


public class lift {

	public static double setPoint;	
	
	public static void initializeLift() {
		RobotMap.lift1 = new TalonSRX (Constants.liftRightTalon);
		RobotMap.lift2 = new TalonSRX (Constants.liftLeftTalon);
		//commented out for testing which motor
		RobotMap.lift2.follow (RobotMap.lift1);
		RobotMap.lift2.setInverted(true);
		RobotMap.liftReset = new DigitalInput(0);
	}

	public static double getLiftSetPoint () {
		if(ControllerMap.mechStickOne() == true) {
			setPoint = Constants.intake;
		}else if(ControllerMap.mechStickTwo() == true) {
			setPoint = Constants.switchPosition;
		}else if(ControllerMap.mechStickThree() == true) {
			setPoint = Constants.lowLevel;
		}else if(ControllerMap.mechStickFour() == true) {
			setPoint = Constants.highLevel;
		}
		return setPoint;
	}
	
	public static void manipulateLift() {
		if (ControllerMap.liftPower() > 0.2 || ControllerMap.liftPower() < -0.2){
			RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower());	
			//RobotMap.lift2.set(ControlMode.PercentOutput, -ControllerMap.liftPower);	
			setPoint = getLiftEncPosition();
		} else {	
			RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * ((getLiftError()) *  Constants.kPLift)));
		}	
		if(isAtBottom() == true) {
			RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
		}
	}
	
	public static void testing() {
		if(ControllerMap.mechStickOne() == true) {
			RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower());
		} else {
			RobotMap.lift2.set(ControlMode.PercentOutput, ControllerMap.liftPower());
		}
	}
	public static double getLiftError() {
		return getLiftSetPoint() - getLiftEncPosition();
	}
	public static boolean isAtBottom() {
		return !RobotMap.liftReset.get();
	}
	
	public static double getLiftEncPosition() {
		return RobotMap.lift1.getSelectedSensorPosition(0);
	}
	
	public static String getLiftPosition() {
		if(setPoint == Constants.intake) {
			return "intake";
		} else if(setPoint == Constants.switchPosition) {
			return "switch";
		} else if(setPoint == Constants.lowLevel) {
			return "low level";
		} else if(setPoint == Constants.highLevel){
			return "high level";
		} else {
			return "lifting";
		}

	}
	public static void liftSetPoint(double newSetPoint) {
		setPoint=newSetPoint;		
	}
}