package commands;

import org.usfirst.frc.team1710.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTimer extends Command {

	int count;
	
    public AutoTimer() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    	Robot.autoTime.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Auto completed in: " + Robot.autoTime.get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
