package trajectory;

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
	double kP = 0.01;
	double kI = 0;
	double kD = 0.001;
	
	//TODO: don't pass through waypoints, pass through a trajectory that has already been calculated from path manager
    public FollowTrajectory(Waypoint[] points) {
    	waypoints = points;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SubsystemManager.masterReset();

    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, Constants.dt, Constants.maxV, Constants.maxAccel, 60);
    	trajectory = Pathfinder.generate(waypoints, config);
    	TankModifier modifier = new TankModifier(trajectory).modify(Constants.robotDriveBaseWidth);
    	
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
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
    	//whether or not the (1/80) is neg/pos messes up the path when the y coord is pos/neg
    	//maybe say if seg.y >= 0 -> (-1/80) else -> (1/80)
    	//.65
		double turn = .65 * (-1.0/80.0) * angleDifference;
    	Drive.leftDrive(turn + l);
    	Drive.rightDrive(turn- r);
    	
    	/*for (int i = 0; i < trajectory.length(); i++) {
    	    Trajectory.Segment seg = trajectory.get(i);
    	    
    	    System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
    	        seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
    	            seg.acceleration, seg.jerk, seg.heading);
    	}*/
    	
    	System.out.println("Left " + (turn + l) + " Right " + (turn -r) + " turn " + turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return left.isFinished() && right.isFinished();
    }

    protected void end() {
    	Drive.stopDriving();
    	System.out.println("Path Complete");
    	left.reset();
    	right.reset();
    	RobotMap.navx.reset();
    }

    protected void interrupted() {
    	Drive.stopDriving();
    	System.out.println("Path Interrupted");
    	left.reset();
    	right.reset();
    	RobotMap.navx.reset();
    }
}
