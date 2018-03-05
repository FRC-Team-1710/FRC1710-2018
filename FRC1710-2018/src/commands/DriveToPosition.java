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
    	_startingPosition = Math.abs(Drive.getRightPosition());
    	//actual goal enc value
    	if(_direction == true) {
    		_totalTicks = _startingPosition - _encGoal;
    	} else {
        	_totalTicks = _startingPosition + _encGoal;
    	}
    	System.out.println("goal" + _totalTicks/215);
    }


    protected void execute() { 
    	_currentTicks = (Math.abs(Drive.getRightPosition()) + Math.abs(Drive.getLeftPosition()))/2;
    	_percentComplete = _currentTicks/_totalTicks;
    	_error = _startingPosition - _currentTicks;
    	_deltaPos = _currentTicks - _startingPosition;
    	if(_direction == true) {
        	_output = -1 * (_speed-Math.pow((_deltaPos/_totalTicks) * _speed, 2) + .1);
    	} else {
        	_output = (_speed-Math.pow((_deltaPos/_totalTicks) * _speed, 2) + .1);
    	}
    	
    	SmartDashboard.putNumber("percent complete", _percentComplete);
    	SmartDashboard.putNumber("Inches", Drive.getRightPosition()/215);
    	SmartDashboard.putNumber("Starting Position", _startingPosition);
    	SmartDashboard.putNumber("Goal Inches", _totalTicks/215);
    	SmartDashboard.putNumber("Angle", Drive.getNavxAngle());

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
            return _currentTicks <= _totalTicks;
    	} else {
            return _currentTicks >= _totalTicks;
    	}
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
