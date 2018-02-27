package trajectory;

import java.io.File;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class CreateTrajectoryFile extends Command {

	Waypoint[] _waypoints;
	String _fileName;
	boolean _isInHighGear;
	
	EncoderFollower left, right;
	Trajectory trajectory;
	
	int count;
	
    public CreateTrajectoryFile(Waypoint[] waypoints, String fileName, boolean isInHighGear) {
    	_fileName = fileName;
    	_waypoints = waypoints;
    	_isInHighGear = isInHighGear;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("creating path");
    	SubsystemManager.masterReset();
    	Drive.setBrakeMode();
    	RobotMap.navx.reset();
    	
    	Trajectory.Config config;
    	
    	if(_isInHighGear == true) {
        	config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.02, Constants.maxVHi, Constants.maxAccelHi, 60);
    	} else {
        	config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.02, Constants.maxV, Constants.maxAccel, 60);
    	}
    	    	
    	PathManager.writePathToFile(_waypoints, _fileName);
    	/*trajectory = PathManager.readTrajFromFile(_fileName);
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
    	}*/
    	//Drive.setShifters(_isInHighGear);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 25;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("path created");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
