package commands;

import org.usfirst.frc.team1710.robot.Constants;
import org.usfirst.frc.team1710.robot.Drive;
import org.usfirst.frc.team1710.robot.RobotMap;
import org.usfirst.frc.team1710.robot.SubsystemManager;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utility.RobotMath;


public class DriveToPosition extends Command {

	double _speed;
	int _encGoal;
	double _slowPercent;
	double _speedFactor;
	boolean _isInHighGear, _endBehavior;
	double _heading;
	double _startingPosition;
	double _totalTicks, _currentTicks, _percentComplete, _error;

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior) {
    	_speed = speed;
    	_encGoal = encGoal * 185;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    }


    protected void initialize() {
    	Drive.setShifters(_isInHighGear);
    	_startingPosition = Math.abs(Drive.getLeftPosition());
    	System.out.println("Goal " + Math.addExact((int) _startingPosition, _encGoal));
    	_totalTicks = Math.addExact((int) _startingPosition, _encGoal);
    }


    protected void execute() { 
    	_currentTicks = Math.abs(Drive.getLeftPosition());
    	_percentComplete = _currentTicks/_totalTicks;
    	_error = _totalTicks - _currentTicks;
    	
    	SmartDashboard.putNumber("velocity", _percentComplete);
    	SmartDashboard.putNumber("Inches", Drive.getLeftPosition()/185);
    	
    	if(_endBehavior == true) {
    		//slow to a stop
	    	Drive.straightDriveTele(_speed * (1.3 - _percentComplete), _heading);
    	} else {
    		//slow into next move
    		if(_percentComplete > .75) {
    	    	Drive.straightDriveTele(_error * 0.0007, _heading);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading);
    		}
    	}
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Drive.getLeftPosition()) >= _encGoal + _startingPosition;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
