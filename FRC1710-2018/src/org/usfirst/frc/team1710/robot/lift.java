package org.usfirst.frc.team1710.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.AnalogInput;



public class lift {

	static double height;
	//make a Constants class that contains all of these setpoints
	public static double distance1 = 0;
	public static double distance2 = 4000;
	public static double distance3 = 6000;
	public static double distance4 = 8000;
	public static double setPoint;
	
	//TODO: a lot of unnecessary parameters make a ControllerMap class with a bunch of global booleans that are set
	//equal to buttons from RobotMap.mechStick
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
		 
		//TODO: instead of calling getRawAxis in here, make double values in ControllerMap that are set equal to these
		if (RobotMap.mechStick.getRawAxis(1) > 0.2 || RobotMap.mechStick.getRawAxis(1) < -0.2){
			//so everywhere "RobotMap.mechStick.getRawAxis()" is used, replace it with ControllerMap.liftPower (or whatever you choose to call it)
			RobotMap.lift1.set(ControlMode.PercentOutput, RobotMap.mechStick.getRawAxis(1));	
			setPoint = height;
		}else {	
			//TODO: the .0008 should be in the Constants class call it kPLift or something
			RobotMap.lift1.set(ControlMode.PercentOutput, (-1 * (((setPoint - height) * .0008))));
		}	
		 
		if(RobotMap.liftReset.get() == false) {
			 RobotMap.lift1.setSelectedSensorPosition(0, 0, 0);	 
		}


	}	
	
}
