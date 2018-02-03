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

	public static void liftingSetPoint () {
		
		if( ControllerMap.one == true) {
			setPoint = Constants.intake;
		}else if( ControllerMap.two == true) {
			setPoint = Constants.switchPosition;
		}else if( ControllerMap.three == true) {
			setPoint = Constants.lowLevel;
		}else if(ControllerMap.four == true) {
			setPoint = Constants.highLevel;
		}
	}
		//when a button is pressed, the lift will go to that position.

		public static void liftMovement() {
		if (ControllerMap.liftPower > 0.2 || ControllerMap.liftPower < -0.2){
						
			RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower);	
			setPoint = getHeight();
		} else {	
			RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * (((setPoint - getHeight()) *  Constants.kPLift))));
		}	
		 
		if(isAtBottom() == true) {
			RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
		}
		}

	public static boolean isAtBottom() {
		return !RobotMap.liftReset.get();
	}
	
	public static double getHeight() {
		return RobotMap.lift1.getSelectedSensorPosition(0);
	}
	
	public static String getLiftPostion() {
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
}