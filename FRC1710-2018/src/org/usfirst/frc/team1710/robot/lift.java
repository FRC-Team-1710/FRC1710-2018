package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;


public class lift {

	public static double setPoint;	
	
	public static void initializeLift() {
		RobotMap.lift1 = new TalonSRX (Constants.lift1Talon);
		RobotMap.lift2 = new TalonSRX (Constants.lift2Talon);
		RobotMap.lift1.follow (RobotMap.lift2);
		RobotMap.liftReset = new DigitalInput(0);
	}

	public static double getLiftSetPoint () {
		
		if( ControllerMap.one == true) {
			setPoint = Constants.intake;
		}else if( ControllerMap.two == true) {
			setPoint = Constants.switchPosition;
		}else if( ControllerMap.three == true) {
			setPoint = Constants.lowLevel;
		}else if(ControllerMap.four == true) {
			setPoint = Constants.highLevel;
		}
		return setPoint;
	}
	
	public static void manipulateLift() {
		if (ControllerMap.liftPower > 0.2 || ControllerMap.liftPower < -0.2){
			RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower);	
			setPoint = getLiftEncPosition();
		} else {	
			RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * ((getLiftError()) *  Constants.kPLift)));
		}	
		if(isAtBottom() == true) {
			RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
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
			return "swich";
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