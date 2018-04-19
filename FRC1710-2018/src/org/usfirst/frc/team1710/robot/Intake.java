package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	static double wristSetpoint, wristOutput;
	
	public static void initializeIntake () {
		RobotMap.intakeR = new Spark (Constants.intakeRSpark);
		RobotMap.intakeL = new Spark (Constants.intakeLSpark);
		RobotMap.cubeIn = new DigitalInput(9);
		RobotMap.wrist = new TalonSRX (Constants.wristTalon);
		RobotMap.ultraSonic = new AnalogInput (0);
		
		RobotMap.wrist.configContinuousCurrentLimit(25, 0);
		RobotMap.wrist.configPeakCurrentLimit(30, 0);
		RobotMap.wrist.configPeakCurrentDuration(100, 0);
		RobotMap.wrist.enableCurrentLimit(false);
		RobotMap.wrist.setSensorPhase(true);
		RobotMap.wrist.setNeutralMode(NeutralMode.Brake);
	}
	
	public static void intake (double in, double out) {
		RobotMap.intakeR.set(in-out);
		RobotMap.intakeL.set(out-in);
	}
	public static int getWristEncPosition() {
		return RobotMap.wrist.getSelectedSensorPosition(0);
	}
	/**
	 * the wrist is controlled by a motor, and it changes positions to up, launch, and down.
	 * handles the changing of the wrist setpoint
	 * @return sets the wrist to a specific position.
	 */
	public static double getWristSetPoint() {
		if (ControllerMap.wristLaunch() == true) {
			wristSetpoint = Constants.wristLaunch;
		} else if(ControllerMap.wristUp() == true) {
			wristSetpoint = Constants.wristUp;
		} else if(ControllerMap.wristDown() == true) {
			wristSetpoint = Constants.wristDown;
		}
		return wristSetpoint;
	}
	/**
	 * controls the wrist speed proportionally to the wrist error
	 */
	public static void manipulateWrist() {
		if(Constants.inAuto) {
		if(ControllerMap.getMechTrigger() == true) {
			if(getWristEncPosition() >= 1750 && RobotMap.mechStick.getRawAxis(0) > .05) {
				wristOutput = 0;
			} else if(getWristEncPosition() <= -300 && RobotMap.mechStick.getRawAxis(0) < -.05) {
				wristOutput = 0;
			} else {
				wristOutput = -RobotMap.mechStick.getRawAxis(0);
			}
			wristSetpoint = getWristEncPosition();
		} else {
			if(getWristEncPosition() >= 1750 && getWristError() * Constants.kPWrist < 0) {
				wristOutput = 0;
			} else if(getWristEncPosition() <= 50 && getWristError() * Constants.kPWrist > 0) {
				wristOutput = 0;
			} else {
				wristOutput = getWristError() * Constants.kPWrist;
			}
		}
		}else {
		if(RobotMap.mechStick.getRawButton(1)) {
			wristOutput = -RobotMap.mechStick.getRawAxis(0) * .5;
		} else {
			wristOutput = 0;
		}
		}

		SmartDashboard.putNumber("calculated output", Constants.kPWrist * getWristError());
		SmartDashboard.putNumber("wrist output", wristOutput);
		SmartDashboard.putNumber("wrist setpoint", getWristSetPoint());
		RobotMap.wrist.set(ControlMode.PercentOutput, wristOutput);
	}
	
	/**
	 * error is distance from the current encoder value to the goal encoder value
	 * @return how far away the goal is from the current position.
	 */
	public static double getWristError() {
		return getWristSetPoint() - getWristEncPosition();
	}
	
	public static void setWristSetpoint(double setpoint) {
		wristSetpoint = setpoint;
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
		return getUltraSonic() < 238;
	}
}
	
	
	
