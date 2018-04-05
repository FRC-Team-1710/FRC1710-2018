package commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	int _time, count;
	
	/**
	 * 
	 * @param time in ms that robot will do nothing
	 */
    public Wait(int time) {
    	_time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	count++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > (_time/20);
    }

    // Called once after isFinished returns true
    protected void end() {
    	count = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
