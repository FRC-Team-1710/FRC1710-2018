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
	boolean _isInHighGear, _endBehavior, _ticksGrabbed, _direction;
	double _heading;
	double _startingPosition;
	double _totalTicks, _currentTicks, _percentComplete, _error, _output, _deltaPos, _lostTicks, _initTicksAtBadHeading;
	double  _exitAngle = 0;

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, double exitAngle) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 215;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_exitAngle = exitAngle;
    }

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, boolean direction) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 215;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_exitAngle = heading;
    	_direction = direction;
    }

    protected void initialize() {
    	Drive.setShifters(_isInHighGear);
    	_startingPosition = Math.abs(Drive.getLeftPosition());
    	//actual goal enc value
    	if(_direction == true) {
    		_totalTicks = _startingPosition - _encGoal;
    	} else {
        	_totalTicks = Math.addExact((int) _startingPosition, _encGoal);
    	}
    }


    protected void execute() { 
    	_currentTicks = Math.abs(Drive.getLeftPosition());
    	_percentComplete = _currentTicks/_totalTicks;
    	_error = _startingPosition - _currentTicks;
    	_deltaPos = _currentTicks - _startingPosition;
    	if(_direction == true) {
        	_output = -1 * (1-Math.pow((_deltaPos/_totalTicks), 2) + .1);
    	} else {
        	_output = (1-Math.pow((_deltaPos/_totalTicks), 2) + .1);
    	}
    	
    	SmartDashboard.putNumber("velocity", _percentComplete);
    	SmartDashboard.putNumber("Inches", Drive.getLeftPosition()/217);
    	SmartDashboard.putNumber("Angle", Drive.getNavxAngle());
    	SmartDashboard.putNumber("lost ticks", _lostTicks);
    	SmartDashboard.putNumber("init ticks", _initTicksAtBadHeading);
    	if(_endBehavior == true) {
    		if(_direction == true) {
    	    	Drive.straightDriveTele(-1 * _speed, _heading);
    		} else {
    	    	Drive.straightDriveTele(_speed, _heading);
    		}
    	} else {
    	   Drive.straightDriveTele(_output, _heading);
    	}
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(_direction == true) {
            return Math.abs(Drive.getLeftPosition()) <= _totalTicks;
    	} else {
            return Math.abs(Drive.getLeftPosition()) >= _totalTicks;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	//haven't tested this yet... if it works correctly lost ticks wont matter bc we could just turn at the end of a movement
    	Drive.setRobotHeading(_exitAngle);
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
