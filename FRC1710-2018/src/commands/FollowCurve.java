package commands;

import org.usfirst.frc.team1710.robot.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowCurve extends Command {

	double _left, _right;
	double _radius, _length, _speed;
	int i;
	
    public FollowCurve(double left, double right) {
    	_left = left;
    	_right = right;
    }
    
    public FollowCurve(double radius, double length, double speed) {
    	_radius = radius;
    	_length = length;
    	_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	i++;
    	Drive.rightDrive(-_right);
    	Drive.leftDrive(_left);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return i > 500;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
