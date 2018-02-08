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
	
	EncoderFollower left, right;
	Trajectory trajectory;
	//p is good, I does nothing. play with d
	//.13
	double kP = .1;
	double kI = 0;
	//.05
	double kD = .01;
	
	//TODO: don't pass through waypoints, pass through a trajectory that has already been calculated from path manager
    public FollowTrajectory(Waypoint[] points) {
    	waypoints = points;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SubsystemManager.masterReset();
    	RobotMap.navx.reset();
    	
		//PathManager.writePathToFile(waypoints, "simple.traj");
    	trajectory = PathManager.readTrajFromFile("simple.traj");
    	TankModifier modifier = new TankModifier(trajectory).modify(Constants.robotDriveBaseWidth);
    	
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
    	left.reset();
    	right.reset();
    	
    	left.configureEncoder((int) Drive.getLeftPosition(), Constants.ticksPerRev, Constants.wheelDiameter);
    	right.configureEncoder((int) Drive.getRightPosition(), Constants.ticksPerRev, Constants.wheelDiameter);
    	
    	left.configurePIDVA(kP, kI, kD, 1/ Constants.maxV, Constants.accGain);
    	right.configurePIDVA(kP, kI, kD, 1/ Constants.maxV, Constants.accGain);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double l = left.calculate((int) Drive.getLeftPosition());
    	double r = right.calculate((int) Drive.getRightPosition());
    	double gyroHeading = Drive.getNavxAngle();
    	double desiredHeading = Pathfinder.r2d(left.getHeading());
    	//take away the bound half degrees method and see what it do
    	double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    	//the exit heading is accurate, but a little bit violent sometimes... so if the robot is freaking out then 5.75 is why
		double turn = 5.75 * (-1.0/80.0) * angleDifference;
    	Drive.leftDrive(turn - l);
    	Drive.rightDrive(turn + r);
    	
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
    	//RobotMap.navx.reset();
    }

    protected void interrupted() {
    	Drive.stopDriving();
    	System.out.println("Path Interrupted");
    	left.reset();
    	right.reset();
    	RobotMap.navx.reset();
    }
}
