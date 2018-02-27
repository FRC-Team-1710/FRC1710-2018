package trajectory;

import java.io.File;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class FollowTrajectory extends Command {
	Waypoint[] waypoints;
	String _fileName;
	boolean _isInHighGear, _isReversed;
	
	EncoderFollower left, right;
	Trajectory trajectory;

	//isReversed makes it so the robot follows the path, but the robot is facing the other way. 
	//fileName must include extension (.traj)
	/**
	 * 
	 * @param points the raw x,y,heading points of the path to be followed
	 * @param isInHighGear a boolean differentiating whether or not the path should be followed in high or low gear
	 * @param isReversed a boolean differentiating whether or not the path should be followed in reverse
	 * @param fileName a string for the name of the file that will be saved to the rio after building the path
	 */
    public FollowTrajectory(Waypoint[] points, boolean isInHighGear, boolean isReversed, String fileName) {
    	waypoints = points;
    	_isInHighGear = isInHighGear;
    	_isReversed = isReversed;
    	_fileName = fileName;
    }

    protected void initialize() {
    	System.out.println("starting path");
    	SubsystemManager.masterReset();
    	Drive.setBrakeMode();
    	RobotMap.navx.reset();
    	
    	Trajectory.Config config;
    	
    	if(_isInHighGear == true) {
        	config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.02, Constants.maxVHi, Constants.maxAccelHi, 60);
    	} else {
        	config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.02, Constants.maxV, Constants.maxAccel, 60);
    	}
    	trajectory = Pathfinder.generate(waypoints, config);
	    //PathManager.writePathToFile(waypoints, _fileName);
    	//trajectory = PathManager.readTrajFromFile(_fileName);
    	TankModifier modifier = new TankModifier(trajectory).modify(Constants.robotDriveBaseWidth);
    	
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
    	left.reset();
    	right.reset();
    	
    	left.configureEncoder((int) Drive.getLeftPosition(), Constants.ticksPerRev, Constants.wheelDiameter);
    	right.configureEncoder((int) Drive.getRightPosition(), Constants.ticksPerRev, Constants.wheelDiameter);
    	
    	if(_isInHighGear == true) {
        	left.configurePIDVA(Constants.kpPath, Constants.kiPath, Constants.kdPath, 1/ Constants.maxVHi, Constants.accGain);
        	right.configurePIDVA(Constants.kpPath, Constants.kiPath, Constants.kdPath, 1/ Constants.maxVHi, Constants.accGain);
    	} else {
        	left.configurePIDVA(Constants.kpPath, Constants.kiPath, Constants.kdPath, 1/ Constants.maxV, Constants.accGain);
        	right.configurePIDVA(Constants.kpPath, Constants.kiPath, Constants.kdPath, 1/ Constants.maxV, Constants.accGain);
    	}
    	Drive.setShifters(_isInHighGear);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double l = left.calculate((int) Drive.getLeftPosition());
    	double r = right.calculate((int) Drive.getRightPosition());
    	double gyroHeading = Drive.getNavxAngle();
    	double desiredHeading = Pathfinder.r2d(left.getHeading());
    	double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    	//the exit heading is accurate, but a little bit violent sometimes... so if the robot is freaking out then 5.75 is why
		double turn = 3 * (-1.0/80.0) * angleDifference;
		//Drive.straightDriveTele(r,  l, desiredHeading);
		if(_isReversed == true) {
	    	Drive.leftDrive(turn + l);
	    	Drive.rightDrive(turn - r);
		} else {
	    	Drive.leftDrive(turn - l);
	    	Drive.rightDrive(turn + r);
		}

    	
    	//System.out.println("Left " + (turn + l) + " Right " + (turn -r) + " turn " + turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return left.isFinished() && right.isFinished();
    }

    protected void end() {
    	Drive.stopDriving();
    	System.out.println("Path Complete " + RobotMap.navx.getAngle());
    	left.reset();
    	right.reset();
    	Drive.setShifters(false);
    	RobotMap.navx.reset();
    }

    protected void interrupted() {
    	Drive.stopDriving();
    	System.out.println("Path Interrupted");
    	left.reset();
    	right.reset();
    	RobotMap.navx.reset();
    	Drive.setShifters(false);
    }
}
