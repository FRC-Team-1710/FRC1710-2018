package commands;
import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveLiftToPosition extends Command {
	
	double _setpoint;
	String initialLiftPosition;

    public MoveLiftToPosition(double setpoint) {
    	_setpoint = setpoint;

    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    	lift.setSetpoint(_setpoint);
    	initialLiftPosition = lift.getLiftPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lift.manipulateLift();
    	SmartDashboard.putBoolean("is finished?", lift.getLiftPosition() != "lifting" && lift.getLiftPosition() != initialLiftPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return lift.getLiftOutput() < .1 && lift.getLiftOutput() > -.1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}