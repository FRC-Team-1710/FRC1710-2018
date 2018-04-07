package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.lift;

import edu.wpi.first.wpilibj.command.Command;
import utility.RobotMath;

/**
 *
 */
public class ChangeLiftSetpoint extends Command {

	double _setpoint, _timeout;
	String goalLiftPosition;
	int count, timeoutCount;
	
    public ChangeLiftSetpoint(double setpoint, double timeout) {
    	_setpoint = setpoint;
    	//_timeout is in milliseconds
    	_timeout = timeout;
    }
    
    public ChangeLiftSetpoint(double setpoint) {
    	_setpoint = setpoint;
    	_timeout = 0;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Starting at: " + lift.getLiftEncPosition() + " and going to: " + _setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timeoutCount++;
    	if (timeoutCount>(_timeout/20)) {
    		//count is how long is spends lifting
    		count++;
    		lift.setSetpoint(_setpoint);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// tries to reach the setpoint until its within 500 ticks of it or it spends longer than 2 seconds trying to get there.
    	// if it spends 2+ seconds the lift probably smashed into the scale or is stuck on the switch
        return RobotMath.isInRange(lift.getLiftEncPosition(), _setpoint, 500) || count > 100;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
