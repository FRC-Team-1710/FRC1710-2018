package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeLiftSetpoint extends Command {

	double _setpoint;
	String initialLiftPosition;
	
    public ChangeLiftSetpoint(double setpoint) {
    	_setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialLiftPosition = lift.getLiftPosition();
    	lift.setSetpoint(_setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return lift.getLiftPosition() != initialLiftPosition && lift.getLiftPosition() != Constants.liftingLevelName;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
