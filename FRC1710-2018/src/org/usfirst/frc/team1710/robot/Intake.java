package org.usfirst.frc.team1710.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Intake {
	static boolean toggle = true;
	static boolean toggle2 = true;
	static boolean piston = false;
	static boolean piston2 = false;
	static double wristSetPoint;

	
	/**
	 * 
	 */
	public static void initializeIntake () {
		RobotMap.intakeR = new Spark (Constants.intakeRSpark);
		RobotMap.intakeL = new Spark (Constants.intakeLSpark);
		RobotMap.wrist = new TalonSRX (Constants.wristTalon);
		RobotMap.ultraSonicL = new AnalogInput (0);
		RobotMap.ultraSonicR = new AnalogInput (1);
	}
	
	public static void intake (double in, double out) {
		//if (isCubeInIntake()== false) {
			RobotMap.intakeR.set(in-out);
			RobotMap.intakeL.set(out-in);
		//} else {
			//RobotMap.intakeR.set(0);
			//RobotMap.intakeL.set(0);
		//}
	}
	public static int getWristEncPosition() {
		return RobotMap.wrist.getSelectedSensorPosition(0);
	}
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
	public static void manipulateWrist() {
		//RobotMap.wrist.set(ControlMode.PercentOutput, getWristError() * Constants.kPWrist);
		RobotMap.wrist.set(ControlMode.PercentOutput, (RobotMap.mechStick.getRawAxis(3)+1)/2);
	}
	public static double getWristError() {
		return getWristSetPoint() - getWristEncPosition();
	}
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
	public static double getUltraSonicL() {
		//Ultra sonic L is going to be further up on the robot
		return RobotMap.ultraSonicL.getVoltage();
	}
	public static double getUltraSonicR() {
		//Ultra sonic R is going to be closer to the robot
		return RobotMap.ultraSonicR.getVoltage();
	}
	public static boolean isCubeInIntake() {
		if ((getUltraSonicL() > Constants.ultraSonic0 || getUltraSonicL() < Constants.ultraSonicInIntake) && (getUltraSonicR() > Constants.ultraSonic0 || getUltraSonicR() < Constants.ultraSonicInIntake)) {
			return true;
		} else if ((getUltraSonicL() > Constants.ultraSonicInIntake) && (getUltraSonicR() > Constants.ultraSonicInIntake)) {
			return false;
		} else if ((getUltraSonicL() > Constants.ultraSonicInIntake) || (getUltraSonicR() > Constants.ultraSonicInIntake)) {
				return false;
		} else {
			return false;
		}
	}
}
	
