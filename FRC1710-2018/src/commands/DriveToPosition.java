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
	boolean _isInHighGear, _endBehavior, _ticksGrabbed;
	double _heading;
	double _startingPosition;
	double _totalTicks, _currentTicks, _percentComplete, _error, _output, _deltaPos, _lostTicks, _initTicksAtBadHeading;
	double  _exitAngle = 0;

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior, double exitAngle) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 217;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_exitAngle = exitAngle;
    }

    public DriveToPosition(int encGoal, double speed, boolean isInHighGear, double heading, boolean endBehavior) {
    	_speed = speed;
    	//217 encoder ticks per inch (4096 (in a rev)/18.15 (wheel C))
    	_encGoal = encGoal * 217;
    	_isInHighGear=isInHighGear;
    	_heading = heading;
    	_endBehavior = endBehavior;
    	_exitAngle = heading;
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
    	_error = _startingPosition - _currentTicks;
    	_deltaPos = _currentTicks - _startingPosition;
    	_output = (1-Math.pow((_deltaPos/_totalTicks), 2) + .1);
    	
    	/*if robot is turning in place to change heading this counts up the ticks "eaten" by the turn then adds them
    	back to our goal*/
    	if(!RobotMath.isInRange(Math.abs(Drive.getNavxAngle()), _heading, 10)) {
    		//add ticks here to _lostTicks
    		if(_ticksGrabbed == false) {
    			_initTicksAtBadHeading = _currentTicks;
    			_ticksGrabbed = true;
    		}
    		_lostTicks = _currentTicks - _initTicksAtBadHeading;
    	} else {
			_ticksGrabbed = false;
    	}
    	
    	SmartDashboard.putNumber("velocity", _percentComplete);
    	SmartDashboard.putNumber("Inches", Drive.getLeftPosition()/185);
    	SmartDashboard.putNumber("Angle", Drive.getNavxAngle());
    	
    	if(_endBehavior == true) {
	    	Drive.straightDriveTele(_speed, _heading);
    	} else {
    	   Drive.straightDriveTele(_output, _heading);
    	}
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Drive.getLeftPosition()) >= _encGoal + _startingPosition + _lostTicks ;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Drive.setRobotHeading(_exitAngle);
    	Drive.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
