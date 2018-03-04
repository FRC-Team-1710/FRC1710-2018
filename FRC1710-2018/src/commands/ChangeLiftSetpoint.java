package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeLiftSetpoint extends Command {

	double _setpoint, _timeout;
	String goalLiftPosition;
	int count, timeoutCount;
	
    public ChangeLiftSetpoint(double setpoint, double timeout) {
    	_setpoint = setpoint;
    	_timeout = timeout;
    	
    }
    public ChangeLiftSetpoint(double setpoint) {
    	_setpoint = setpoint;
    	_timeout = 0;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	timeoutCount++;
    	if (timeoutCount>(_timeout/20)) {
    		count++;
    		if(_setpoint == Constants.intake) {
    			goalLiftPosition = Constants.intakeLevelName;
    		} else if(_setpoint == Constants.switchPosition) {
    			goalLiftPosition = Constants.switchLevelName;
    		} else if(_setpoint == Constants.scaleHigh) {
    			goalLiftPosition = Constants.highLevelName;
    		}
    		lift.setSetpoint(_setpoint);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return lift.getLiftPosition().equals(goalLiftPosition) || count > 200;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("done lifting");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
