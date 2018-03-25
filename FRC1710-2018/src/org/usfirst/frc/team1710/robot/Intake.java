package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
/** 
 * The class deals with the intake motors and the wrist.
 * @author molly
 *
 */
public class Intake {
	static double wristSetPoint;
	
	public static void initializeIntake () {
		RobotMap.intakeR = new Spark (Constants.intakeRSpark);
		RobotMap.intakeL = new Spark (Constants.intakeLSpark);
		RobotMap.wrist = new TalonSRX (Constants.wristTalon);
		RobotMap.ultraSonic = new AnalogInput (0);
		
		RobotMap.wrist.configContinuousCurrentLimit(10, 0);
		RobotMap.wrist.configPeakCurrentLimit(15, 0);
		RobotMap.wrist.configPeakCurrentDuration(100, 0);
		RobotMap.wrist.enableCurrentLimit(false);
	}
	
	public static void intake (double in, double out) {
		RobotMap.intakeR.set(out-in);
		RobotMap.intakeL.set(in-out);
	}
	public static int getWristEncPosition() {
		return Math.abs(RobotMap.wrist.getSelectedSensorPosition(0));
	}
	/**
	 * the wrist is controlled by a motor, and it changes positions to up, launch, and down.
	 * handles the changing of the wrist setpoint
	 * @return sets the wrist to a specific position.
	 */
	public static double getWristSetPoint() {
		if (ControllerMap.wristUp() == true) {
			wristSetPoint = Constants.wristUp;
		} else if (ControllerMap.wristLaunch() == true) {
			wristSetPoint = Constants.wristLaunch;
		} else if (ControllerMap.wristDown() == true) {
			wristSetPoint = Constants.wristDown;
		}
		return wristSetPoint;
	}
	/**
	 * controls the wrist speed proportionally to the wrist error
	 */
	public static void manipulateWrist() {
				
		//RobotMap.wrist.set(ControlMode.PercentOutput, getWristError() * Constants.kPWrist);

		if(ControllerMap.getMechTrigger() == true) {
			RobotMap.wrist.set(ControlMode.PercentOutput, -RobotMap.mechStick.getRawAxis(0)*.4);
		} else {
			RobotMap.wrist.set(ControlMode.PercentOutput,0);
		}
	}
	/**
	 * error is distance from the current encoder value to the goal encoder value
	 * @return how far away the goal is from the current position.
	 */
	public static double getWristError() {
		return getWristSetPoint() - getWristEncPosition();
	}
	/**
	 * Gets the number from the encoder, and uses it within a range.
	 * @return it will return what position the wrist is in.
	 */
	public static String getWristPosition() {
		if (getWristEncPosition() > Constants.wristUp + 5 || getWristEncPosition() < Constants.wristUp - 5) {
			return "Keeping cube in";
		} else if (getWristEncPosition() > Constants.wristLaunch + 5 || getWristEncPosition() < Constants.wristLaunch - 5) {
			return "Launch cube upward, at angle";
		} else if (getWristEncPosition() > Constants.wristDown + 5 || getWristEncPosition () < Constants.wristDown - 5) {
			return "Wrist is parallell to the ground";
		} else {
			return "changing position. Or broken";
		}
	}
	public static void setWristPosition(double setPoint) {
		wristSetPoint = setPoint;
	}
	/**
	 * Gets the ultra sonic proximity in voltage.
	 * @return returns the ultra sonic voltage
	 */
	public static double getUltraSonic() {
		//Ultra sonic L is going to be further out on the robot
		return RobotMap.ultraSonic.getValue();
	}
	/**
	 * The method checks if the ultra sonic sensor is within a certain range.
	 * If the cube is within an inch of the sensor, then it will read any where from 0 to .28.
	 * If the cube is within 0 or .28, then it will read true. The cube is in the intake.
	 * If the cube is greater than .28, or less than 0, it will return false. The cube is not in the intake.
	 * checks the sensors, left and right.
	 * @return if the cube is in the intake
	 */
	public static boolean isCubeInIntake() {
		if ((getUltraSonic() <= Constants.ultraSonicInIntake)) {
			//when returns, means cube is secure
			return true;
		} else {
			return false;
		}
	}
	/*if ultrasonic sensor is not within intake range AND if a button has been pressed
	 * 		run intake motors
	 * else
	 * 		do nothing
	 */
	
	/*gives an easier way for speed, instead of changing every variable.
	 * "intakeSpeed" gives the speed of the intake. How fast the motors are running it grab a cube.
	 * "outtakeSpeed" gives the speed of the outtake. How fast the motors are running to spit out a cube.
	 */
	
	static double intakeSpeed = .5;
	static double outtakeSpeed = .5;
	static boolean ultraSonicToggle = true;

	public static void ultraSonicIntake() {
		if (isCubeInIntake() == true && ControllerMap.ultraSonicIntake() == true) {
			//checks if the cube is in the intake. If it is, then when button is pressed, it will outtake.
			RobotMap.intakeL.set(outtakeSpeed);
			RobotMap.intakeR.set(outtakeSpeed);
			//spit out cube
		} else if (isCubeInIntake() == true && ControllerMap.ultraSonicIntake() == false) {
			//if the intake button is false, dont do anything
			RobotMap.intakeL.set(0);
			RobotMap.intakeR.set(0);
		} else if (isCubeInIntake() == false && ControllerMap.ultraSonicIntake() == false) {
			//don't do anything since the button is not being pressed.
			RobotMap.intakeL.set(0);
			RobotMap.intakeR.set(0);
		} else if (isCubeInIntake() == false && ControllerMap.ultraSonicIntake() == true) {
			//checks if the cube is in the intake. If it is, then when button is pressed, it will outtake.
			RobotMap.intakeL.set(intakeSpeed);
			RobotMap.intakeR.set(intakeSpeed);
		}
	}
}
	
	
	
