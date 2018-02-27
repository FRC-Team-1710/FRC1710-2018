package commands;

import org.usfirst.frc.team1710.robot.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowCurve extends Command {

	int[][] _points;
	int i;
	
    public FollowCurve(int[][] points) {
    	_points = points;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	i++;
    	Drive.straightDriveTele(_points[i][0], _points[i][1]);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
