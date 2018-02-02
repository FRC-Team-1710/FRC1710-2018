package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.AnalogInput;


public class lift extends Constants{
	
	static double height;

	public static double setPoint;
	
	public static void Lifting () {
	
		height = RobotMap.lift1.getSelectedSensorPosition(0);

		if( ControllerMap.one == true) {
			setPoint = distance1;
		}else if( ControllerMap.two == true) {
			setPoint = distance2;
		}else if( ControllerMap.three == true) {
			setPoint = distance3;
		}else if(ControllerMap.four == true) {
			setPoint = distance4;
		} 
		//when a button is pressed, the lift will go to that position.

		//TODO: make a boolean value in constants to replace this expression with: isLiftManualControl
		if (ControllerMap.liftPower > 0.2 || ControllerMap.liftPower < -0.2){
						
			RobotMap.lift1.set(ControlMode.PercentOutput, ControllerMap.liftPower);	
			setPoint = height;
		} else {	
			RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * (((setPoint - height) * kPLift))));
		}	
		 
		if(RobotMap.liftReset.get() == false) {
			RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
		}
	}		
}