package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.AnalogInput;



public class lift {

	static double height;
	public static double distance1 = 0;
	public static double distance2 = 4000;
	public static double distance3 = 6000;
	public static double distance4 = 8000;
	public static double setPoint;
	
	public static void Lifting(boolean up, boolean down, boolean one,  boolean two,  boolean three, boolean four) {
	
		height = RobotMap.lift1.getSelectedSensorPosition(0);

		if( one == true) {
			setPoint = distance1;
		}else if( two == true) {
			setPoint = distance2;
		}else if( three == true) {
			setPoint = distance3;
		}else if(four == true) {
			setPoint = distance4;
		} 
		 
		if (RobotMap.mechStick.getRawAxis(1) > 0.2 || RobotMap.mechStick.getRawAxis(1) < -0.2){
			RobotMap.lift1.set(ControlMode.PercentOutput, RobotMap.mechStick.getRawAxis(1));	
			setPoint = height;
		}else {	
			 RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * (((setPoint - height) * .0008))));
		}	
		 

		 
		if(RobotMap.liftReset.get() == false) {
			 RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
		}


	}	
	
}
